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
 * 描述：警情文书 服务类实现
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
     * 默认的构造函数
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
                // 查询机构id-名称缓存map
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
                // 查询机构id-名称缓存map
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

            //是否保存时间轴
            Boolean timelineEnablebl = Boolean.parseBoolean(env.getProperty("timeline.enable"));
            if (timelineEnablebl) {
                saveTimeline(inputInfo);
            }

            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(inputInfo.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_DOCUMENT.getCode(), res, orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //其他
            Map<String, String> otherParams = new HashMap<>();
            pushDataService.pushIncidentDocument(res, otherParams);

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveDocument", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DOCUMENT_FAIL);
        }
    }


    /**
     * 文书信息 保存时间轴信息
     *
     * @return
     */
    private Boolean saveTimeline(DocumentSaveInputInfo inputInfo) {

        //获得用户信息
        UserInfo userInfo = userService.getUserInfo();

        EsTimelineRequestBO esTLineRequestBO = new EsTimelineRequestBO();
        esTLineRequestBO.setCzrxm(userInfo.getPersonName());// 操作人姓名
        esTLineRequestBO.setCzryhm(userInfo.getAccount());// 操作人用户名填写登陆账号
        esTLineRequestBO.setFqdw(userInfo.getOrgCode());// 发起单位code
        esTLineRequestBO.setFqdwm(userInfo.getOrgName());// 发起单位名
        esTLineRequestBO.setLybh(env.getProperty("system.code")); // 信息来源编号(所属系统编号)
        esTLineRequestBO.setLylx("HJSL");// 信息来源类型(所属系统名称)
        esTLineRequestBO.setLzbh(inputInfo.getIncidentId()); // 流转编号
        // 时间不能为毫秒，格式应该为 2019-10-22 13:53:30
        Long time = servletService.getSystemTime();
        esTLineRequestBO.setSj(DateUtils.get19Date(new Date(time)));// 时间
        esTLineRequestBO.setTitle("火场文书"); //标题
        esTLineRequestBO.setCzcode(inputInfo.getType()); // 操作代码
//        esTLineRequestBO.setBs(   EnumUtils.getEnum()   EnumUtils.get( inputInfo.getType() , DocumentTypeEnum .class)  );// 标识
        esTLineRequestBO.setContent(inputInfo.getContent());// 信息内容

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
     * 保存警情引导文书
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

            //原文书输入
            DocumentSaveInputInfo inputInfo = inputInfos.getDocumentSaveInputInfo();

            DocumentEntity documentEntity = HandleDispatchTransformUtil.transform(inputInfo);

            if (null != documentEntity) {
                //将 是否有被困人、反馈号码、地址文字信息、上报描述、案发经度、案发纬度、案发地址、上报经度、上报纬度、上报地址 用” / “隔开存入文书内容
                String content = inputInfos.getWhetherTrapped() + "/" + inputInfos.getFeedbackNumber() + "/" + inputInfos.getAddressTextInfo() +
                        "/" + inputInfos.getReportDescription() + "/" + inputInfos.getIncidentGis_x() + "/" + inputInfos.getIncidentGis_y() + "/" + inputInfos.getIncidentAddress()
                        + "/" + inputInfos.getReportGis_x() + "/" + inputInfos.getReportGis_y() + "/" + inputInfos.getReportAddress();
                documentEntity.setContent(content);
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // 查询机构id-名称缓存map
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
                    documentEntity.setType("300"); //文书类型
                }
                if (Strings.isBlank(documentEntity.getTypeSubitemCode())) {
                    documentEntity.setTypeSubitemCode("300"); // 文书类型
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

            //是否保存时间轴
            Boolean timelineEnablebl = Boolean.parseBoolean(env.getProperty("timeline.enabl"));
            if (timelineEnablebl) {
                saveTimeline(inputInfo);
            }

            //保存附件信息
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
            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(inputInfo.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.INCIDENTGUIDANCE.getCode(), res, orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveDocument", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //其他
//            Map<String, String > otherParams = new HashMap<>( ) ;
//            pushDataService.pushIncidentDocument( res , otherParams  ) ;

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveDocument", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DOCUMENT_FAIL);
        }
    }


}
