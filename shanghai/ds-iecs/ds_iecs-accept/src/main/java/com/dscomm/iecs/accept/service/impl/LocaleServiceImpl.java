package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.LocaleEntity;
import com.dscomm.iecs.accept.dal.repository.LocaleRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.LocaleQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.LocaleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.LocaleBean;
import com.dscomm.iecs.accept.service.IncidentService;
import com.dscomm.iecs.accept.service.LocaleService;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.AttachmentService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.ext.RelationObjectBakEnum;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：现场信息 服务类实现
 */
@Component("localeServiceImpl")
public class LocaleServiceImpl implements LocaleService {
    private static final Logger logger = LoggerFactory.getLogger(LocaleServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private OrganizationService organizationService;
    private DictionaryService dictionaryService;
    private LocaleRepository localeRepository;
    private ServletService servletService;
    private NotifyActionService notifyActionService;
    private IncidentService incidentService;
    private AttachmentService attachmentService;
    private UserService userService ;
    private PushDataService pushDataService ;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public LocaleServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                             OrganizationService organizationService, DictionaryService dictionaryService, LocaleRepository localeRepository,
                             ServletService servletService, IncidentService incidentService,
                             NotifyActionService notifyActionService, AttachmentService attachmentService,
                             UserService userService , PushDataService pushDataService

    ) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.organizationService = organizationService;
        this.dictionaryService = dictionaryService;
        this.localeRepository = localeRepository;
        this.servletService = servletService;
        this.notifyActionService = notifyActionService;
        this.incidentService = incidentService;
        this.attachmentService = attachmentService;
        this.userService = userService ;
        this.pushDataService = pushDataService ;

        dics = new ArrayList<>(Arrays.asList("TQQX", "FX", "FLDJ", "AJDJ", "YWQK", "AJLX", "AJLXZX", "JZJG"));


    }

    /**
     * {@inheritDoc}
     *
     * @see LocaleService#saveLocale(LocaleSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public LocaleBean saveLocale(LocaleSaveInputInfo queryBean) {
        if (null == queryBean || Strings.isBlank(queryBean.getIncidentId())) {
            logService.infoLog(logger, "service", "saveLocale", "ParticipantSaveInputInfo or incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveLocale", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeLocaleSaveInputInfo(queryBean); //URLDecoder inputInfo转码

            LocaleBean res = null;
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            UserInfo userInfo = userService.getUserInfo() ;

            //保存参数转换
            LocaleEntity localeEntity = HandleDispatchTransformUtil.transform(queryBean, servletService.getSystemTime());
            //填写反馈人信息
            if( localeEntity != null && Strings.isNotBlank( localeEntity.getFeedbackObjectId() ) ){
                localeEntity.setFeedbackObjectId( userInfo.getAccount() );
                localeEntity.setFeedbackObjectName( userInfo.getUserName() );
            }

            logService.infoLog(logger, "repository", "save(dbLocaleEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(localeEntity);
            localeEntity.setIdCode(localeEntity.getId());
            accessor.save(localeEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbLocaleEntity)", String.format("repository is finished,execute time is :%sms", end - start));


            res = HandleDispatchTransformUtil.transform(localeEntity, dicsMap,null);

            //保存附件信息
            List<AttachmentSaveInputInfo> attachments = queryBean.getAttachments();
            if (attachments != null && attachments.size() > 0) {
                for (AttachmentSaveInputInfo attachmentSaveInputInfo : attachments) {
                    attachmentSaveInputInfo.setIncidentId(queryBean.getIncidentId());
                    attachmentSaveInputInfo.setRelationId(res.getId());
                    attachmentSaveInputInfo.setRelationObject(RelationObjectBakEnum.RELATION_OBJECT_XCTP.getCode());
                    attachmentSaveInputInfo.setOrganizationId( queryBean.getFeedbackOrganizationId() );
                    attachmentSaveInputInfo.setSeatNumber( queryBean.getFeedbackObjectId() );
                    attachmentSaveInputInfo.setAcceptancePersonNumber( queryBean.getFeedbackObjectName() ) ;
                }
                List<AttachmentBean> attachmentBeans = attachmentService.saveAttachmentList(attachments);
                res.setAttachmentBeans(attachmentBeans);
            }

            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(queryBean.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_LOCALE.getCode(), res, orgSet);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveLocale", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //其他
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushIncidentLocale( res , otherParams  ) ;

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveLocale", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public LocaleBean saveLocaleMobile(LocaleSaveInputInfo queryBean) {
        if (null == queryBean || Strings.isBlank(queryBean.getIncidentId())) {
            logService.infoLog(logger, "service", "saveLocaleMobile", "ParticipantSaveInputInfo or incidentId is " +
                    "null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveLocaleMobile", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeLocaleSaveInputInfo(queryBean); //URLDecoder inputInfo转码

            LocaleBean res = null;
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

           // UserInfo userInfo = userService.getUserInfo() ;

            //保存参数转换
            LocaleEntity localeEntity = HandleDispatchTransformUtil.transform(queryBean, servletService.getSystemTime());
            //填写反馈人信息
            if( localeEntity != null && Strings.isNotBlank( localeEntity.getFeedbackObjectId() ) ){
                localeEntity.setFeedbackObjectId( queryBean.getFeedbackObjectId() );
                localeEntity.setFeedbackObjectName( queryBean.getFeedbackObjectName() );
            }

            logService.infoLog(logger, "repository", "save(dbLocaleEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(localeEntity);
            localeEntity.setIdCode(localeEntity.getId());
            accessor.save(localeEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbLocaleEntity)", String.format("repository is finished,execute time is :%sms", end - start));


            res = HandleDispatchTransformUtil.transform(localeEntity, dicsMap,null);

            //保存附件信息
            List<AttachmentSaveInputInfo> attachments = queryBean.getAttachments();
            if (attachments != null && attachments.size() > 0) {
                for (AttachmentSaveInputInfo attachmentSaveInputInfo : attachments) {
                    attachmentSaveInputInfo.setIncidentId(queryBean.getIncidentId());
                    attachmentSaveInputInfo.setRelationId(res.getId());
                    attachmentSaveInputInfo.setRelationObject(RelationObjectBakEnum.RELATION_OBJECT_XCTP.getCode());
                    attachmentSaveInputInfo.setOrganizationId( queryBean.getFeedbackOrganizationId() );
                    attachmentSaveInputInfo.setSeatNumber( queryBean.getFeedbackObjectId() );
                    attachmentSaveInputInfo.setAcceptancePersonNumber( queryBean.getFeedbackObjectName() ) ;
                }
                List<AttachmentBean> attachmentBeans = attachmentService.saveAttachmentList(attachments);
                res.setAttachmentBeans(attachmentBeans);
            }

            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(queryBean.getIncidentId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_LOCALE.getCode(), res, orgSet);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveLocaleMobile", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //其他
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushIncidentLocale( res , otherParams  ) ;

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveLocaleMobile", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see LocaleService#findLocale(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<LocaleBean> findLocale(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findLocale", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findLocale", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<LocaleBean> res = new ArrayList<>();
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            logService.infoLog(logger, "repository", "findLocaleByIncidentId( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<LocaleEntity> localeEntityList = localeRepository.findLocaleByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findLocaleByIncidentId( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));

            if (!CollectionUtils.isEmpty(localeEntityList)) {
                //  查询现场反馈附件
                List<String> ids = localeEntityList.stream().map(o -> o.getId()).collect(Collectors.toList());
                Map<String, List<AttachmentBean>>  attachmentByRelationIds = attachmentService.findAttachmentByRelationIds(ids, RelationObjectBakEnum.RELATION_OBJECT_XCTP.getCode());
                Set<String> set = null;
                List<AttachmentBean> attachmentBeans = null;
                if (!CollectionUtils.isEmpty(attachmentByRelationIds)) {
                    set = attachmentByRelationIds.keySet();
                }
                for (LocaleEntity localeEntity : localeEntityList) {
                    if (!CollectionUtils.isEmpty(set)) {
                        set = attachmentByRelationIds.keySet();
                        for (String k : set) {
                            if (k.equals(localeEntity.getId())) {
                                attachmentBeans = attachmentByRelationIds.get(k);
                            }
                        }
                    }
                    LocaleBean localeBean = HandleDispatchTransformUtil.transform(localeEntity, dicsMap,attachmentBeans);
                    res.add(localeBean);
                }
                localeEntityList.clear();
                localeEntityList=null;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findLocale", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findLocale", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #findLocaleCondition(LocaleQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public List<LocaleBean> findLocaleCondition(LocaleQueryInputInfo queryBean) {
        if (Strings.isBlank(queryBean.getIncidentId())) {
            logService.infoLog(logger, "service", "findLocaleCondition", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            //查询转码
            decodeLocaleQueryInputInfo(queryBean);

            logService.infoLog(logger, "service", "findLocaleCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<LocaleBean> res = new ArrayList<>();
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            logService.infoLog(logger, "repository", "findLocaleCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<LocaleEntity> localeEntityList = localeRepository.findLocaleCondition(queryBean.getIncidentId(), queryBean.getCommandId(), queryBean.getInstructId(), queryBean.getInstructRecordId(),
                    queryBean.getLocaleType(), queryBean.getLocaleSource(), queryBean.getFeedbackObjectId(), queryBean.getKeyword());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findLocaleCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (!CollectionUtils.isEmpty(localeEntityList)) {
                //  查询现场反馈附件
                List<String> ids = localeEntityList.stream().map(o -> o.getId()).collect(Collectors.toList());
                Map<String, List<AttachmentBean>>  attachmentByRelationIds = attachmentService.findAttachmentByRelationIds(ids, RelationObjectBakEnum.RELATION_OBJECT_XCTP.getCode());
                Set<String> set = null;
                List<AttachmentBean> attachmentBeans = null;
                if (!CollectionUtils.isEmpty(attachmentByRelationIds)) {
                    set = attachmentByRelationIds.keySet();
                }
                for (LocaleEntity localeEntity : localeEntityList) {
                    if (!CollectionUtils.isEmpty(set)) {
                        set = attachmentByRelationIds.keySet();
                        for (String k : set) {
                            if (k.equals(localeEntity.getId())) {
                                attachmentBeans = attachmentByRelationIds.get(k);
                            }
                        }
                    }
                    LocaleBean localeBean = HandleDispatchTransformUtil.transform(localeEntity, dicsMap,attachmentBeans);
                    res.add(localeBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findLocaleCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findLocaleCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }

    }


    /**
     * LocaleSaveInputInfo 解码
     */
    private void decodeLocaleSaveInputInfo(LocaleSaveInputInfo source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getLocaleDesc())) {
                    source.setLocaleDesc(URLDecoder.decode(source.getLocaleDesc(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getLocaleExtension())) {
                    source.setLocaleExtension(URLDecoder.decode(source.getLocaleExtension(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getTempRallyPointAddress())) {
                    source.setTempRallyPointAddress(URLDecoder.decode(source.getTempRallyPointAddress(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getCrimeAddress())) {
                    source.setCrimeAddress(URLDecoder.decode(source.getCrimeAddress(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getFeedbackObjectName())) {
                    source.setFeedbackObjectName(URLDecoder.decode(source.getFeedbackObjectName(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getFeedbackOrganizationName())) {
                    source.setFeedbackOrganizationName(URLDecoder.decode(source.getFeedbackOrganizationName(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getFeedbackObjectId())) {
                    source.setFeedbackObjectId(URLDecoder.decode(source.getFeedbackObjectId(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getFeedbackOrganizationId())) {
                    source.setFeedbackOrganizationId(URLDecoder.decode(source.getFeedbackOrganizationId(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getOperatorPerson())) {
                    source.setOperatorPerson(URLDecoder.decode(source.getOperatorPerson(), "UTF-8"));
                }
            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }


    /**
     * LocaleQueryInputInfo 解码
     */
    private void decodeLocaleQueryInputInfo(LocaleQueryInputInfo source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getKeyword())) {
                    source.setKeyword(URLDecoder.decode(source.getKeyword(), "UTF-8"));
                }
            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }


}
