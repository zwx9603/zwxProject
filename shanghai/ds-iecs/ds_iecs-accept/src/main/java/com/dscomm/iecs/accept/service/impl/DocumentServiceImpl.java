package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.DocumentEntity;
import com.dscomm.iecs.accept.dal.repository.DocumentRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.DocumentSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentGuidanceInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DocumentBean;
import com.dscomm.iecs.accept.service.DocumentService;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.ext.RelationObjectBakEnum;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.keydata.service.timeline.TimeLineService;
import com.dscomm.iecs.keydata.service.timeline.bo.EsTimelineRequestBO;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ????????????????????? ???????????????
 */
@Component("documentServiceImpl")
public class DocumentServiceImpl implements DocumentService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private DocumentRepository documentRepository;
    private OrganizationService organizationService;
    private TimeLineService timeLineService;
    private Environment env;
    private UserService userService;
    private ServletService servletService;
    private IncidentService incidentService;
    private NotifyActionService notifyActionService;
    private DictionaryService dictionaryService;
    private PushDataService pushDataService;
    private AttachmentService attachmentService;


    private List<String> dics;

    /**
     * ?????????????????????
     */
    @Autowired
    public DocumentServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                               DocumentRepository documentRepository, OrganizationService organizationService,
                               TimeLineService timeLineService, Environment env, UserService userService,
                               ServletService servletService, IncidentService incidentService, NotifyActionService notifyActionService,
                               DictionaryService dictionaryService, PushDataService pushDataService, AttachmentService attachmentService
    ) {
        this.accessor = accessor;
        this.logService = logService;
        this.documentRepository = documentRepository;
        this.organizationService = organizationService;
        this.timeLineService = timeLineService;
        this.env = env;
        this.userService = userService;
        this.servletService = servletService;
        this.incidentService = incidentService;
        this.notifyActionService = notifyActionService;
        this.dictionaryService = dictionaryService;
        this.pushDataService = pushDataService;
        this.attachmentService = attachmentService;

        dics = new ArrayList<>(Arrays.asList("WSLX", "AJZT"));
    }

    /**
     * {@inheritDoc}
     *
     * @see DocumentService#findDocument(String, String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<DocumentBean> findDocument(String incidentId, String organizationId, String type) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findDocument", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findDocument", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<DocumentBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findDocumentEntityIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<DocumentEntity> documentEntityList = null;
            if (Strings.isBlank(organizationId)) {
                if (Strings.isBlank(type)) {
                    documentEntityList = documentRepository.findDocumentByIncidentId(incidentId);
                } else {
                    documentEntityList = documentRepository.findDocumentByIncidentIdAndType(incidentId, type);
                }

            } else {
                if (Strings.isBlank(type)) {
                    documentEntityList = documentRepository.findDocumentByIncidentIdAndOrganizationId(incidentId, organizationId);
                } else {
                    documentEntityList = documentRepository.findDocumentByIncidentIdAndOrganizationIdAndType(incidentId, organizationId, type);
                }

            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findDocumentEntityIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            if (documentEntityList != null && documentEntityList.size() > 0) {

                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // ????????????id-????????????map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                for (DocumentEntity documentEntity : documentEntityList) {
                    DocumentBean bean = HandleDispatchTransformUtil.transform(documentEntity, organizationNameMap, dicsMap);
                    res.add(bean);
                }
                documentEntityList.clear();
                documentEntityList=null;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDocument", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DOCUMENT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see DocumentService#saveDocument(DocumentSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public DocumentBean saveDocument(DocumentSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveDocument", "DocumentSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveDocument", "service is started...");
            Long logStart = System.currentTimeMillis();

            DocumentBean res = null;

            Long currentTime = servletService.getSystemTime();

            DocumentEntity documentEntity = HandleDispatchTransformUtil.transform(inputInfo);

            if (null != documentEntity) {

                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // ????????????id-????????????map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                if (!StringUtils.isBlank(documentEntity.getFeedbackOrganizationId())&&organizationNameMap!=null){
                    documentEntity.setFeedbackOrganizationName(organizationNameMap.get(documentEntity.getFeedbackOrganizationId()));
                }

                if (documentEntity.getRecordTime() == null) {
                    documentEntity.setRecordTime(currentTime);
                }
                if (documentEntity.getFeedbackTime() == null) {
                    documentEntity.setFeedbackTime(currentTime);
                }

                logService.infoLog(logger, "repository", "save(dbDocumentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(documentEntity);
                documentEntity.setId(documentEntity.getId());
                accessor.save(documentEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbDocumentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            //?????????????????????
            Boolean timelineEnablebl = Boolean.parseBoolean(env.getProperty("timeline.enable"));
            if (timelineEnablebl) {
                saveTimeline(inputInfo);
            }

            //??????????????????????????????
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(inputInfo.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_DOCUMENT.getCode(), res, orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //??????
            Map<String, String> otherParams = new HashMap<>();
            pushDataService.pushIncidentDocument(res, otherParams);

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveDocument", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DOCUMENT_FAIL);
        }
    }


    /**
     * ???????????? ?????????????????????
     *
     * @return
     */
    private Boolean saveTimeline(DocumentSaveInputInfo inputInfo) {

        //??????????????????
        UserInfo userInfo = userService.getUserInfo();

        EsTimelineRequestBO esTLineRequestBO = new EsTimelineRequestBO();
        esTLineRequestBO.setCzrxm(userInfo.getPersonName());// ???????????????
        esTLineRequestBO.setCzryhm(userInfo.getAccount());// ????????????????????????????????????
        esTLineRequestBO.setFqdw(userInfo.getOrgCode());// ????????????code
        esTLineRequestBO.setFqdwm(userInfo.getOrgName());// ???????????????
        esTLineRequestBO.setLybh(env.getProperty("system.code")); // ??????????????????(??????????????????)
        esTLineRequestBO.setLylx("HJSL");// ??????????????????(??????????????????)
        esTLineRequestBO.setLzbh(inputInfo.getIncidentId()); // ????????????
        // ??????????????????????????????????????? 2019-10-22 13:53:30
        Long time = servletService.getSystemTime();
        esTLineRequestBO.setSj(DateUtils.get19Date(new Date(time)));// ??????
        esTLineRequestBO.setTitle("????????????"); //??????
        esTLineRequestBO.setCzcode(inputInfo.getType()); // ????????????
//        esTLineRequestBO.setBs(   EnumUtils.getEnum()   EnumUtils.get( inputInfo.getType() , DocumentTypeEnum .class)  );// ??????
        esTLineRequestBO.setContent(inputInfo.getContent());// ????????????

        return timeLineService.saveTimeline(esTLineRequestBO);
    }


    /**
     * {@inheritDoc}
     *
     * @see #findDocumentOrganization(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OrganizationBean> findDocumentOrganization(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findDocumentOrganization", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findDocumentOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<OrganizationBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findDocumentOrganizationIdByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<String> documentOrganizationIds = documentRepository.findDocumentOrganizationIdByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findDocumentOrganizationIdByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            if (documentOrganizationIds != null && documentOrganizationIds.size() > 0) {
                res = organizationService.findOrganizationsByIds(documentOrganizationIds);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDocumentOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDocumentOrganization", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DOCUMENT_FAIL);
        }
    }

    /**
     * ????????????????????????
     *
     * @param inputInfos
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public DocumentBean saveIncidentGuidance(IncidentGuidanceInputInfo inputInfos) {
        if (inputInfos == null) {
            logService.infoLog(logger, "service", "saveDocument", "DocumentSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveDocument", "service is started...");
            Long logStart = System.currentTimeMillis();

            DocumentBean res = null;

            Long currentTime = servletService.getSystemTime();

            //???????????????
            DocumentSaveInputInfo inputInfo = inputInfos.getDocumentSaveInputInfo();

            DocumentEntity documentEntity = HandleDispatchTransformUtil.transform(inputInfo);

            if (null != documentEntity) {
                //??? ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? ?????? / ???????????????????????????
                String content = inputInfos.getWhetherTrapped() + "/" + inputInfos.getFeedbackNumber() + "/" + inputInfos.getAddressTextInfo() +
                        "/" + inputInfos.getReportDescription() + "/" + inputInfos.getIncidentGis_x() + "/" + inputInfos.getIncidentGis_y() + "/" + inputInfos.getIncidentAddress()
                        + "/" + inputInfos.getReportGis_x() + "/" + inputInfos.getReportGis_y() + "/" + inputInfos.getReportAddress();
                documentEntity.setContent(content);
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // ????????????id-????????????map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                if (!StringUtils.isBlank(documentEntity.getFeedbackOrganizationId())&&organizationNameMap!=null){
                    documentEntity.setFeedbackOrganizationName(organizationNameMap.get(documentEntity.getFeedbackOrganizationId()));
                }

                if (documentEntity.getRecordTime() == null) {
                    documentEntity.setRecordTime(currentTime);
                }
                if (documentEntity.getFeedbackTime() == null) {
                    documentEntity.setFeedbackTime(currentTime);
                }
                if (Strings.isBlank(documentEntity.getType())) {
                    documentEntity.setType("300"); //????????????
                }
                if (Strings.isBlank(documentEntity.getTypeSubitemCode())) {
                    documentEntity.setTypeSubitemCode("300"); // ????????????
                }


                logService.infoLog(logger, "repository", "save(dbDocumentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(documentEntity);
                documentEntity.setId(documentEntity.getId());
                accessor.save(documentEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbDocumentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

                res = HandleDispatchTransformUtil.transform(documentEntity, organizationNameMap, dicsMap);
            }

            //?????????????????????
            Boolean timelineEnablebl = Boolean.parseBoolean(env.getProperty("timeline.enabl"));
            if (timelineEnablebl) {
                saveTimeline(inputInfo);
            }

            //??????????????????
            List<AttachmentSaveInputInfo> attachments = inputInfos.getAttachmentSaveInputInfos();
            if (attachments != null && attachments.size() > 0) {
                for (AttachmentSaveInputInfo attachmentSaveInputInfo : attachments) {
                    attachmentSaveInputInfo.setIncidentId(inputInfo.getIncidentId());
                    attachmentSaveInputInfo.setRelationId(res.getId());
                    attachmentSaveInputInfo.setRelationObject(RelationObjectBakEnum.RELATION_OBJECT_XCTP.getCode());
                    attachmentSaveInputInfo.setOrganizationId(inputInfo.getFeedbackOrganizationId());
//                    attachmentSaveInputInfo.setSeatNumber( queryBean.getFeedbackObjectId() );
//                    attachmentSaveInputInfo.setAcceptancePersonNumber( queryBean.getFeedbackObjectName() ) ;
                }
                List<AttachmentBean> attachmentBeans = attachmentService.saveAttachmentList(attachments);
                res.setAttachmentBeans(attachmentBeans);
            }
            //??????????????????????????????
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(inputInfo.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.INCIDENTGUIDANCE.getCode(), res, orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //??????
//            Map<String, String > otherParams = new HashMap<>( ) ;
//            pushDataService.pushIncidentDocument( res , otherParams  ) ;

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveDocument", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DOCUMENT_FAIL);
        }
    }


}
