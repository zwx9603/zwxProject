package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.dal.repository.*;
import com.dscomm.iecs.accept.enums.MajorIncidentRuleEnum;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.GenerateUtil;
import com.dscomm.iecs.accept.utils.JudgeIncidentVehicleStatusUtil;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.bean.LocationRangeBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DistanceUtils;
import com.dscomm.iecs.base.utils.JpaUtil;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitSimpleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.ext.comm.call.CALL_DIRECTION_HR;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_AFDZBG;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_JQDJBG;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_JQLXBG;
import com.dscomm.iecs.ext.incident.alarm.INCIDENT_ALARM_MODE_GKLWBJ;
import com.dscomm.iecs.ext.incident.alarm.INCIDENT_ALARM_MODE_YCJKBJ;
import com.dscomm.iecs.ext.incident.alarm.INCIDENT_ALARM_MODE_YYZDBJ_GZ;
import com.dscomm.iecs.ext.incident.handle.*;
import com.dscomm.iecs.ext.incident.nature.INCIDENT_NATURE_SCJJ;
import com.dscomm.iecs.ext.incident.nature.INCIDENT_NATURE_ZJ;
import com.dscomm.iecs.ext.incident.register.INCIDENT_REGISTER_MODE_110LA;
import com.dscomm.iecs.ext.incident.register.INCIDENT_REGISTER_MODE_XFFASLA;
import com.dscomm.iecs.ext.incident.register.INCIDENT_REGISTER_MODE_XFLA;
import com.dscomm.iecs.ext.incident.source.INCIDENT_SOURCE_FAS;
import com.dscomm.iecs.ext.incident.source.INCIDENT_SOURCE_GA;
import com.dscomm.iecs.ext.incident.source.INCIDENT_SOURCE_XF;
import com.dscomm.iecs.ext.incident.status.*;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.inputbean.KeyDataChangeRecordSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.KeyDataChangeRecordService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.*;

import static com.dscomm.iecs.ext.IncidentNatureBakEnum.INCIDENT_NATURE_GLJQ;

/**
 * 描述：警情 服务类实现
 */
@Component("incidentServiceImpl")
public class IncidentServiceImpl implements IncidentService {
    private static final Logger logger = LoggerFactory.getLogger(IncidentServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private IncidentRepository incidentRepository;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private KeyUnitService keyUnitService;
    private KeyDataChangeRecordService keyDataChangeRecordService;
    private IncidentStatusChangeService incidentStatusChangeService;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;
    private AcceptanceService acceptanceService;
    private TelephoneService telephoneService;
    private HandleService handleService;
    private IncidentMergeService incidentMergeService;
    private SystemConfigurationService systemConfigurationService;
    private NotifyActionService notifyActionService;
    private UserService userService;
    private ServletService servletService;
    private EarlyWarningService earlyWarningService;
    private AcceptNativeQueryRepository acceptNativeQueryRepository;
    private ParticipantFeedbackService participantFeedbackService;
    private AttachmentService attachmentService;
    private DocumentService documentService;
    private AttentionService attentionService;
    private List<String> dics;
    private CommonTipsService commonTipsService;
    private SoundRecordService soundRecordService ;
    private MajorIncidentRuleRepository majorIncidentRuleRepository;
    private PushDataService pushDataService ;

    private String handlePersonNumType = "4"  ; //1参战人员 2杭州 案件车辆人员 3车辆人员 4车辆载人数 默认4
    private Boolean whetherInSquadron ;
    private HandleOrganizationRepository handleOrganizationRepository;
    private LocationRecordService locationRecordService;
    private LocationRecordRepository locationRecordRepository;

    /**
     * 默认的构造函数
     */
    @Autowired
    @Lazy(true)
    public IncidentServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, OrganizationService organizationService,
                               NotifyActionService notifyActionService,
                               Environment env, DictionaryService dictionaryService, IncidentRepository incidentRepository, KeyUnitService keyUnitService,
                               KeyDataChangeRecordService keyDataChangeRecordService, IncidentStatusChangeService incidentStatusChangeService,
                               AuditLogService auditLogService, SubAuditService subAuditService, SystemConfigurationService systemConfigurationService,
                               AcceptanceService acceptanceService, TelephoneService telephoneService, HandleService handleService,
                               ServletService servletService, DocumentService documentService, IncidentMergeService incidentMergeService, UserService userService,
                               EarlyWarningService earlyWarningService, AcceptNativeQueryRepository acceptNativeQueryRepository,
                               ParticipantFeedbackService participantFeedbackService, AttachmentService attachmentService,
                               AttentionService attentionService,
                               CommonTipsService commonTipsService,
                               SoundRecordService soundRecordService,
                               PushDataService pushDataService,
                               MajorIncidentRuleRepository majorIncidentRuleRepository,
                               HandleOrganizationRepository handleOrganizationRepository,
                               LocationRecordService locationRecordService,
                               LocationRecordRepository locationRecordRepository) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.incidentRepository = incidentRepository;
        this.dictionaryService = dictionaryService;
        this.keyUnitService = keyUnitService;
        this.organizationService = organizationService;
        this.keyDataChangeRecordService = keyDataChangeRecordService;
        this.incidentStatusChangeService = incidentStatusChangeService;
        this.auditLogService = auditLogService;
        this.subAuditService = subAuditService;
        this.acceptanceService = acceptanceService;
        this.telephoneService = telephoneService;
        this.handleService = handleService;
        this.documentService = documentService;
        this.incidentMergeService = incidentMergeService;
        this.systemConfigurationService = systemConfigurationService;
        this.notifyActionService = notifyActionService;
        this.userService = userService;
        this.servletService = servletService;
        this.earlyWarningService = earlyWarningService;
        this.acceptNativeQueryRepository = acceptNativeQueryRepository;
        this.participantFeedbackService = participantFeedbackService;
        this.attachmentService = attachmentService;
        this.attentionService = attentionService;
        this.commonTipsService = commonTipsService;
        this.soundRecordService = soundRecordService ;
        this.majorIncidentRuleRepository = majorIncidentRuleRepository;
        this.pushDataService = pushDataService ;
        this.handleOrganizationRepository = handleOrganizationRepository;
        this.locationRecordService = locationRecordService;


        handlePersonNumType =  env.getProperty("handlePersonNumType")  ;
        whetherInSquadron = Boolean.parseBoolean(env.getProperty("whetherInSquadron"));
        this.locationRecordRepository = locationRecordRepository;

        dics = new ArrayList<>(Arrays.asList("XB", "XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJZT", "AJXZ", "CZDX", "ZDDWLX", "JZJG", "YWQK", "ZHCS", "CLLX", "JQBQ", "JQDX"));
    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentTemp(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBrieflyBean> findIncidentTemp(IncidentQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentTemp", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentTemp", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码


            PaginationBean<IncidentBrieflyBean> res = new PaginationBean<>();
            List<IncidentBrieflyBean> beans = new ArrayList<>();


            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }


            String searchPath = null; //机构查询码 0根机构 1非根机构 默认根机构
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                }
            } else {
                searchPath = null;
            }

            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }
            List<String> incidentStatusCodes = new ArrayList<>();
            incidentStatusCodes.add(String.valueOf( INCIDENT_STATUS_ZC.getCode()));

            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(searchPath, false, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {
                    IncidentBrieflyBean bean = IncidentTransformUtil.transformBriefly(incidentObj, dicsMap, organizationNameMap);
                    beans.add(bean);
                }
                //补充警情属性
                setIncidentBrieflyProperties( beans ) ;
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = incidentRepository.findIncidentConditionTotal(searchPath, false, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentTemp", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentTemp", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentsReceiverObjectIdUnfinished(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBrieflyBean> findIncidentsReceiverObjectIdUnfinished(IncidentQueryInputInfo queryBean) {
        if (queryBean == null || Strings.isBlank(queryBean.getReceiverObjectId())) {
            logService.infoLog(logger, "service", "findIncidentsReceiverObjectIdUnfinished", "receiverObjectId  is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentsReceiverObjectIdUnfinished", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码

            PaginationBean<IncidentBrieflyBean> res = new PaginationBean<>();
            List<IncidentBrieflyBean> beans = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }


            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }
            List<String> incidentStatusCodes = new ArrayList<>();
            String vehicleOnDutyStatus = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinished").getConfigValue();
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] incidentEndStatus = vehicleOnDutyStatus.split(",");
                incidentStatusCodes = Arrays.asList(incidentEndStatus);
            }

            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(null, false, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {
                    IncidentBrieflyBean bean = IncidentTransformUtil.transformBriefly(incidentObj, dicsMap, organizationNameMap);
                    beans.add(bean);
                }
                //补充警情属性
                setIncidentBrieflyProperties( beans ) ;
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = incidentRepository.findIncidentConditionTotal(null, false, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentsReceiverObjectIdUnfinished", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentsReceiverObjectIdUnfinished", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_UNFINISHED_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findIncidentsUnfinished(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBrieflyBean> findIncidentsUnfinished(IncidentQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentsUnfinished", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentsUnfinished", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码

            PaginationBean<IncidentBrieflyBean> res = new PaginationBean<>();
            List<IncidentBrieflyBean> beans = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }


            String searchPath = null; //机构查询码 0根机构 1非根机构 默认根机构
            //判断是否只查询参与警情 中队  只查询参与警情 为true   默认 false ;
            Boolean whetherHandleIncident = false;
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    String organizationNature = organization.getOrganizationNatureCode();
                    if ( !whetherInSquadron && Strings.isNotBlank(organizationNature) &&
                            (organizationNature.startsWith( ORGANIZATION_NATURE_HEAD_XJZD.getCode()))) {
                        whetherHandleIncident = true;
                    }

                }
            } else {
                searchPath = null;
            }

            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }
            List<String> incidentStatusCodes = new ArrayList<>();
            SystemConfigurationBean vehicleOnDutyStatusBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinished");
            String vehicleOnDutyStatus = null;
            if (vehicleOnDutyStatusBean != null) {
                vehicleOnDutyStatus = vehicleOnDutyStatusBean.getConfigValue();

            }
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] incidentEndStatus = vehicleOnDutyStatus.split(",");
                incidentStatusCodes = Arrays.asList(incidentEndStatus);
            }

            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {
                    IncidentBrieflyBean bean = IncidentTransformUtil.transformBriefly(incidentObj, dicsMap, organizationNameMap);
                    beans.add(bean);
                }
                //补充警情属性
                setIncidentBrieflyProperties(beans) ;
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = incidentRepository.findIncidentConditionTotal(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());


            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentsUnfinished", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentsUnfinished", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_UNFINISHED_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentsUnfinishedFilterIncident(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBrieflyBean> findIncidentsUnfinishedFilterIncident(IncidentQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentsUnfinished", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentsUnfinished", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码

            PaginationBean<IncidentBrieflyBean> res = new PaginationBean<>();
            List<IncidentBrieflyBean> beans = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }


            String searchPath = null; //机构查询码 0根机构 1非根机构 默认根机构
            //判断是否只查询参与警情 中队  只查询参与警情 为true   默认 false ;
            Boolean whetherHandleIncident = false;
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    String organizationNature = organization.getOrganizationNatureCode();
                    if ( !whetherInSquadron && Strings.isNotBlank(organizationNature) &&
                            (organizationNature.startsWith( ORGANIZATION_NATURE_HEAD_XJZD.getCode()))) {
                        whetherHandleIncident = true;
                    }

                }
            } else {
                searchPath = null;
            }

            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }
            List<String> incidentStatusCodes = new ArrayList<>();
            SystemConfigurationBean vehicleOnDutyStatusBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinished");
            String vehicleOnDutyStatus = null;
            if (vehicleOnDutyStatusBean != null) {
                vehicleOnDutyStatus = vehicleOnDutyStatusBean.getConfigValue();

            }
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] incidentEndStatus = vehicleOnDutyStatus.split(",");
                incidentStatusCodes = Arrays.asList(incidentEndStatus);
            }

            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {
                    IncidentBrieflyBean bean = IncidentTransformUtil.transformBriefly(incidentObj, dicsMap, organizationNameMap);
                    beans.add(bean);
                }
                //补充警情属性
                setIncidentBrieflyProperties(beans) ;
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = incidentRepository.findIncidentConditionTotal(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());


            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentsUnfinished", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentsUnfinished", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_UNFINISHED_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findIncidentsCondition(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBrieflyBean> findIncidentsCondition(IncidentQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentsCondition", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentsCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码

            PaginationBean<IncidentBrieflyBean> res = new PaginationBean<>();
            List<IncidentBrieflyBean> beans = new ArrayList<>();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }

            String searchPath = null; //机构查询码 0根机构 1非根机构 默认根机构
            //判断是否只查询参与警情 中队  只查询参与警情 为true   默认 false ;
            Boolean whetherHandleIncident = false;
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    String organizationNature = organization.getOrganizationNatureCode();
                    if ( !whetherInSquadron && Strings.isNotBlank(organizationNature) &&
                            (organizationNature.startsWith( ORGANIZATION_NATURE_HEAD_XJZD.getCode()))) {
                        whetherHandleIncident = true;
                    }

                }
            } else {
                searchPath = null;
            }
            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();


            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), queryBean.getIncidentStateCodes(),
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {
                    IncidentBrieflyBean bean = IncidentTransformUtil.transformBriefly(incidentObj, dicsMap, organizationNameMap);
                    beans.add(bean);
                }
                //补充警情属性
                setIncidentBrieflyProperties(beans);

            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();


            Integer total = incidentRepository.findIncidentConditionTotal(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), queryBean.getIncidentStateCodes(),
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentsCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentsCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findIncidentAttentionCondition(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBrieflyBean> findIncidentAttentionCondition(IncidentQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentAttentionCondition", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentAttentionCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码

            PaginationBean<IncidentBrieflyBean> res = new PaginationBean<>();
            List<IncidentBrieflyBean> beans = new ArrayList<>();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }

            String searchPath = null; //机构查询码 0根机构 1非根机构 默认根机构
            //判断是否只查询参与警情 中队  只查询参与警情 为true   默认 false ;
            Boolean whetherHandleIncident = false;
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    String organizationNature = organization.getOrganizationNatureCode();
                    if (  !whetherInSquadron &&  Strings.isNotBlank(organizationNature) &&
                            (organizationNature.startsWith( ORGANIZATION_NATURE_HEAD_XJZD.getCode()))) {
                        whetherHandleIncident = true;
                    }

                }
            } else {
                searchPath = null;
            }

            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();


            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), queryBean.getIncidentStateCodes(),
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, true, 1, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {
                    IncidentBrieflyBean bean = IncidentTransformUtil.transformBriefly(incidentObj, dicsMap, organizationNameMap);
                    beans.add(bean);
                }
                //补充警情属性
                setIncidentBrieflyProperties(beans) ;
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = incidentRepository.findIncidentConditionTotal(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), queryBean.getIncidentStateCodes(),
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, true, 1, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentAttentionCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentAttentionCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentImportantCondition(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBrieflyBean> findIncidentImportantCondition(IncidentQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentImportantCondition", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentImportantCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码

            PaginationBean<IncidentBrieflyBean> res = new PaginationBean<>();
            List<IncidentBrieflyBean> beans = new ArrayList<>();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }

            String searchPath = null; //机构查询码 0根机构 1非根机构 默认根机构
            //判断是否只查询参与警情 中队  只查询参与警情 为true   默认 false ;
            Boolean whetherHandleIncident = false;
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    String organizationNature = organization.getOrganizationNatureCode();
                    if ( !whetherInSquadron && Strings.isNotBlank(organizationNature) &&
                            (organizationNature.startsWith( ORGANIZATION_NATURE_HEAD_XJZD.getCode()))) {
                        whetherHandleIncident = true;
                    }
                }
            } else {
                searchPath = null;
            }

            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();


            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();


            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), queryBean.getIncidentStateCodes(),
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, true, 2, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {
                    IncidentBrieflyBean bean = IncidentTransformUtil.transformBriefly(incidentObj, dicsMap, organizationNameMap);
                    beans.add(bean);
                }
                //补充警情属性
                setIncidentBrieflyProperties(beans) ;
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = incidentRepository.findIncidentConditionTotal(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), queryBean.getIncidentStateCodes(),
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, true, 2, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentImportantCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentImportantCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #setIncidentProperties(List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<IncidentBrieflyBean> setIncidentBrieflyProperties(List<IncidentBrieflyBean> incidentBeanList) {
        if (null == incidentBeanList) {
            logService.infoLog(logger, "service", "setIncidentBrieflyProperties", "incidentBeanList is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            //事件id 关联事件id列表
            Set<String> incidentIdSet = new HashSet<>();
            // 获取事件id  列表
            List<String> incidentIds = new ArrayList<>();
            for (IncidentBrieflyBean incidentBean : incidentBeanList) {
                incidentIds.add(incidentBean.getId());
            }
            //主警情 与关联警情id (包含主警情)
            Map<String, List<String>> releationIdMap = new HashMap<>();
            List<Object[]> incidentRelationIncidentIds = incidentRepository.findRelationIncidentNumberByIds(incidentIds);
            for (Object[] incidentRelationIncidentId : incidentRelationIncidentIds) {
                String incidentId = toString(incidentRelationIncidentId[0]);
                String relationIncidentId = toString(incidentRelationIncidentId[1]);
                List<String> relationIncidentIdList = releationIdMap.get(incidentId);
                if (relationIncidentIdList == null) {
                    relationIncidentIdList = new ArrayList<>();
                }
                relationIncidentIdList.add(relationIncidentId);
                releationIdMap.put(incidentId, relationIncidentIdList);
                incidentIdSet.add(relationIncidentId);
            }

            List<String> incidentIdList = new ArrayList<>(incidentIdSet);


            // 获取电话报警录音信息
            List<Integer> typeList = new ArrayList<>() ;
            typeList.add( 1 ) ;
            typeList.add( 2 ) ;
            Map<String, List<SoundRecordBean>> soundRecordMap = soundRecordService.findSoundRecordMapByIncidentIdList(incidentIdList , typeList );

            for (IncidentBrieflyBean incidentBean : incidentBeanList) {
                String incidentId = incidentBean.getId();
                //关联案件 包含本案件
                List<String > relationIncidentIdList = releationIdMap.get( incidentId ) ;
                // 录音记录
                List<SoundRecordBean>  soundRecordBeanList = new ArrayList<>();;
                if( relationIncidentIdList != null && relationIncidentIdList.size() > 0 ){
                    for( String relationIncidentId : relationIncidentIdList ){
                        List<SoundRecordBean> soundRecordrelationList = soundRecordMap.get(relationIncidentId);
                        if( soundRecordrelationList != null && soundRecordrelationList.size() > 0 ){
                            soundRecordBeanList.addAll( soundRecordrelationList );
                        }
                    }
                }
                incidentBean.setSoundRecordList(soundRecordBeanList);
            }
            return incidentBeanList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "setIncidentBrieflyProperties", "set incidentInfo properties fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMPLETE_PROPERTIES_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findIncident(String, Boolean)
     */
    @Transactional(readOnly = true)
    @Override
    public IncidentBean findIncident(String incidentId, Boolean whetherProperties) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncident", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentBean res = null;

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            String account = userService.getAccount();

            //查询带 报警人电话 和 联系方式的案件
            List<Object[]> queryResult = incidentRepository.findIncidentDetailed(incidentId, null, null,
                    account);

            if (queryResult != null && queryResult.size() > 0) {
                Object[] objects = queryResult.get(0);
                if (objects != null && objects.length > 0) {
                    res = IncidentTransformUtil.transform(objects, dicsMap, organizationNameMap , handlePersonNumType  );


                    //获取调派单位id列表 杭州新增需求
                    List<String> handleOegIds =
                            handleOrganizationRepository.findOrganizationIdsByIncidentId( incidentId);
                    if (handleOegIds!=null&&handleOegIds.size()>0){
//                        StringBuilder handleOrganization=new StringBuilder();
//                        for (String orgId:handleOegIds
//                             ) {
//                            handleOrganization.append(orgId).append(",");
//                        }
                        res.setHandleOrganization(StringUtils.merge(handleOegIds,","));
                    }
                    if (Strings.isNotBlank(res.getKeyUnitId())) {
                        KeyUnitSimpleBean keyUnitSimple = keyUnitService.findKeyUnitSimple(res.getKeyUnitId());
                        if (keyUnitSimple != null) {
                            res.setKeyUnitName(keyUnitSimple.getUnitName());
                        }
                    }
                  
                    //  2、通过警情类型和处置对象编码获取安全提示
                    CommonTipsBean commonTips = commonTipsService.findCommonTips(res.getIncidentTypeCode(), res.getDisposalObjectCode());
                    if (Objects.nonNull(commonTips)) {
                        res.setSafetyTips(commonTips.getSafetyTips());
                        res.setHandleTips(commonTips.getHandleTips());
                        res.setReceiveTips(commonTips.getReceiveTips());
                    }
                  
                    if (whetherProperties) {
					    List<IncidentBean> beans = new ArrayList<>();
					    beans.add(res);
	                    //补充警情属性
	                    setIncidentProperties(beans);                       
						//案件详情补充 案件出动车辆信息
                        List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList = handleService.findHandleOrganizationVehicle(res.getId(), null, false);
                        res.setHandleOrganizationVehicleList(handleOrganizationVehicleBeanList);
                        //案件详情补充 案件处置简要
                        List<HandleShowBean> showBeans = buildHandleShow(handleOrganizationVehicleBeanList);
                        res.setHandleShowBeanList(showBeans);
                        //案件详情 定位记录
                        List<LocationRecordBean> locationRangeBeans = locationRecordService.getLocationRecord(incidentId);
                        res.setLocationRecordBeans(locationRangeBeans);
                        //统计案件车辆携带的水量、泡沫量
                        List<Object[]> vechileMedicament = incidentRepository.countIncidentVechileMedicament(res.getId());
                        if (vechileMedicament!=null&&!vechileMedicament.isEmpty()){
                            Object[] objs = vechileMedicament.get(0);
                            if (objs!=null&&objs.length==2){
                                String totalFoam= IncidentTransformUtil.toString(objs[0]) ;
                                String totalWater= IncidentTransformUtil.toString(objs[1]);
                                res.setTotalFoam(totalFoam);
                                res.setTotalWater(totalWater);
                            }
                        }
                    }

                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }

    }


    private List<HandleShowBean> buildHandleShow(List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList) {
        Map<String, HandleShowBean> handleShowBeanMap = new HashMap<>();
        for (HandleOrganizationVehicleBean handleOrganizationVehicleBean : handleOrganizationVehicleBeanList) {
            HandleShowBean handleShowBean = handleShowBeanMap.get(handleOrganizationVehicleBean.getHandleId());
            if (handleShowBean == null) {
                handleShowBean = new HandleShowBean();
                handleShowBean.setHandleId(handleOrganizationVehicleBean.getHandleId());
                handleShowBean.setHandleTime(handleOrganizationVehicleBean.getHandleEndTime());
                handleShowBean.setHandleBatch(handleOrganizationVehicleBean.getHandleBatch());
            }
            List<String> dispatchOrganizationName = handleShowBean.getDispatchOrganizationName();
            if (!dispatchOrganizationName.contains(handleOrganizationVehicleBean.getOrganizationName())) {
                dispatchOrganizationName.add(handleOrganizationVehicleBean.getOrganizationName());
            }
            handleShowBean.setDispatchOrganizationName(dispatchOrganizationName);
            Integer dispatchParticipant = handleShowBean.getDispatchParticipant();
            if (handleOrganizationVehicleBean.getParticipantFeedbackBeanList() != null) {
                dispatchParticipant = dispatchParticipant + handleOrganizationVehicleBean.getParticipantFeedbackBeanList().size();
            }
            handleShowBean.setDispatchParticipant(dispatchParticipant);
            ;
            List<String> dispatchVehicle = handleShowBean.getDispatchVehicle();
            if (!dispatchVehicle.contains(handleOrganizationVehicleBean.getVehicleId())) {
                dispatchVehicle.add(handleOrganizationVehicleBean.getVehicleId());
            }
            handleShowBean.setDispatchVehicle(dispatchVehicle);

            List<HandleShowVehicleBean> handleShowVehicleBeans = handleShowBean.getHandleShowVehicleBeans();
            HandleShowVehicleBean handleShowVehicleBean = null;
            for (HandleShowVehicleBean handleShowVehicle : handleShowVehicleBeans) {
                if (handleOrganizationVehicleBean.getVehicleTypeCode() != null && handleOrganizationVehicleBean.getVehicleTypeCode().equals(handleShowVehicle.getVehicleTypeCode())) {
                    handleShowVehicleBean = handleShowVehicle;
                }
            }
            if (handleShowVehicleBean == null) {
                handleShowVehicleBean = new HandleShowVehicleBean();
                handleShowVehicleBeans.add(handleShowVehicleBean);
            }
            handleShowVehicleBean.setVehicleTypeCode(handleOrganizationVehicleBean.getVehicleTypeCode());
            handleShowVehicleBean.setVehicleTypeName(handleOrganizationVehicleBean.getVehicleTypeName());
            handleShowVehicleBean.setVehicleTypeNum(handleShowVehicleBean.getVehicleTypeNum() + 1);

            handleShowBean.setHandleShowVehicleBeans(handleShowVehicleBeans);
            handleShowBeanMap.put(handleOrganizationVehicleBean.getHandleId(), handleShowBean);
        }
        List<HandleShowBean> beans = new ArrayList<>(handleShowBeanMap.values());
        //排序
        beans.sort(new Comparator<HandleShowBean>() {
            @Override
            public int compare(HandleShowBean o1, HandleShowBean o2) {
                Long d1 = o1.getHandleTime();
                Long d2 = o2.getHandleTime();
                return d2.compareTo(d1);
            }
        });
        return beans;
    }


    /**
     * {@inheritDoc}
     *
     * @see #setIncidentProperties(List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<IncidentBean> setIncidentProperties(List<IncidentBean> incidentBeanList) {
        if (null == incidentBeanList) {
            logService.infoLog(logger, "service", "setIncidentProperties", "incidentBeanList is null");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            //事件id 关联事件id列表
            Set<String> incidentIdSet = new HashSet<>();
            // 获取事件id  列表
            List<String> incidentIds = new ArrayList<>();
            for (IncidentBean incidentBean : incidentBeanList) {
                incidentIds.add(incidentBean.getId());
            }
            //主警情 与关联警情id (包含主警情)
            Map<String, List<String>> releationIdMap = new HashMap<>();
            List<Object[]> incidentRelationIncidentIds = incidentRepository.findRelationIncidentNumberByIds(incidentIds);
            for (Object[] incidentRelationIncidentId : incidentRelationIncidentIds) {
                String incidentId = toString(incidentRelationIncidentId[0]);
                String relationIncidentId = toString(incidentRelationIncidentId[1]);
                List<String> relationIncidentIdList = releationIdMap.get(incidentId);
                if (relationIncidentIdList == null) {
                    relationIncidentIdList = new ArrayList<>();
                }
                relationIncidentIdList.add(relationIncidentId);
                releationIdMap.put(incidentId, relationIncidentIdList);
                incidentIdSet.add(relationIncidentId);
            }

            List<String> incidentIdList = new ArrayList<>(incidentIdSet);

            if (incidentIdList != null && incidentIdList.size() > 0) {

                // 获取附件列表信息
                Map<String, List<AttachmentBean>> attachmentBeanListMap = attachmentService.findAttachmentByRelationIds(incidentIdList, null);

                // 获取接警以及接警回拨录音信息
                List<Integer> typeList = new ArrayList<>() ;
                typeList.add( 1 ) ;
                typeList.add( 2 ) ;
                Map<String, List<SoundRecordBean>> soundRecordMap = soundRecordService.findSoundRecordMapByIncidentIdList(incidentIdList , typeList );

                for (IncidentBean incidentBean : incidentBeanList) {
                    String incidentId = incidentBean.getId();
                    //关联案件
                    List<String > relationIncidentIdList = releationIdMap.get( incidentId ) ;
                    // 附件
                    List<AttachmentBean> attachmentBeanList = new ArrayList<>();
                    if( relationIncidentIdList != null && relationIncidentIdList.size() > 0 ){
                        for( String relationIncidentId : relationIncidentIdList ){
                            List<AttachmentBean> attachmentrelationList = attachmentBeanListMap.get(relationIncidentId);
                            if( attachmentrelationList != null && attachmentrelationList.size() > 0 ){
                                attachmentBeanList.addAll( attachmentrelationList );
                            }
                        }
                    }
                    incidentBean.setAttachmentNum(attachmentBeanList.size());
                    incidentBean.setAttachmentList(attachmentBeanList);

                    // 录音记录
                    List<SoundRecordBean> soundRecordBeanList = new ArrayList<>();
                    if( relationIncidentIdList != null && relationIncidentIdList.size() > 0 ){
                        for( String relationIncidentId : relationIncidentIdList ){
                            List<SoundRecordBean> soundRecordrelationList = soundRecordMap.get(relationIncidentId);
                            if( soundRecordrelationList != null && soundRecordrelationList.size() > 0 ){
                                soundRecordBeanList.addAll( soundRecordrelationList );
                            }
                        }
                    }
                    incidentBean.setSoundRecordList(soundRecordBeanList);

                    //定位记录
                    List<LocationRecordBean> locationRangeBeans = locationRecordService.getLocationRecord(incidentId);
                    if (locationRangeBeans != null && locationRangeBeans.size()>0){
                        incidentBean.setLocationRecordBeans(locationRangeBeans);
                    }
                }
            }

            return incidentBeanList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "setIncidentInfoProperties", "set incidentInfo properties fail.", ex);
            throw new AcceptException(AcceptException.AccetpErrors.COMPLETE_PROPERTIES_FAIL);
        }
    }

    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private String toString(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#saveIncident(IncidentSaveInputInfo, UserInfo, Boolean, Boolean)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IncidentBean saveIncident(IncidentSaveInputInfo queryBean, UserInfo userInfo, Boolean distribute , Boolean acceptAndDispatch  ) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "saveIncident", "IncidentSaveInputInfo    is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            if (userInfo == null) {
                userInfo = userService.getUserInfo(); // new UserInfo();
            }

            IncidentBean res = null;

            decodeIncidentSaveInputInfo(queryBean);//URLDecoder inputInfo转码
            if (StringUtils.isBlank(queryBean.getDistrictCode())){
                queryBean.setDistrictCode(userInfo.getAreaCode());
            }
            //补充
            Long currentTime = servletService.getSystemTime();
            TelephoneEntity telephoneEntity = null;
            if (Strings.isNotBlank(queryBean.getTelephoneId())) {
                telephoneEntity = accessor.getById(queryBean.getTelephoneId(), TelephoneEntity.class);
            }
            if (telephoneEntity != null) {
                // 报警补充信息
                if( Strings.isBlank(  queryBean.getAlarmPhone() ) ){
                    if(  Strings.isBlank( telephoneEntity.getCallDirection() )
                            ||  CALL_DIRECTION_HR.getCode().equals( telephoneEntity.getCallDirection() ) ){
                        queryBean.setAlarmPhone(telephoneEntity.getCallingNumber());
                    }else{
                        queryBean.setAlarmPhone(telephoneEntity.getCalledNumber() );
                    }
                }
//                queryBean.setAlarmStartTime( telephoneEntity.getRingingTime() );
//                queryBean.setReceiveStartTime( telephoneEntity.getAnswerTime() );
                if (telephoneEntity.getEndTime() != null &&
                        telephoneEntity.getEndTime() > currentTime  ) {
                    queryBean.setAlarmEndTime(telephoneEntity.getEndTime());
                    queryBean.setReceiveEndTime(telephoneEntity.getEndTime());
                }else{
                    queryBean.setAlarmEndTime(currentTime);
                    queryBean.setReceiveEndTime(currentTime);
                }
                queryBean.setRelayRecordNumber(telephoneEntity.getRelayRecordNumber());
            } else {
//                queryBean.setAlarmStartTime( currentTime );
//                queryBean.setReceiveStartTime( currentTime );
                queryBean.setAlarmEndTime(currentTime);
                queryBean.setReceiveEndTime(currentTime);
            }
            queryBean.setRegisterIncidentTime(currentTime);
            //生成案件编号  2021-04-24时间（到毫秒）+单位编码（行政区）+工号（
            if (Strings.isBlank(queryBean.getIncidentNumber())) {
                Date nowDate = new Date(currentTime);
                String agentNum = userInfo.getAgentNum();
                String incidentNumber = GenerateUtil.GenerateIncidentNumber(nowDate,queryBean.getDistrictCode(),agentNum);
                queryBean.setIncidentNumber(incidentNumber);
            }

            queryBean.setRegisterOrganizationId(userInfo.getOrgId());
            queryBean.setAcceptancePerson(userInfo.getAccount());
            queryBean.setAcceptancePersonId(userInfo.getAccount());
            queryBean.setRegisterSeatNumber(userInfo.getAgentNum());
            queryBean.setAcceptancePersonName(userInfo.getPersonName());
            queryBean.setAcceptancePersonNumber(userInfo.getAccount());
            //设置警情性质为空 默认为实警
            if (Strings.isBlank(queryBean.getIncidentNatureCode())) {
                queryBean.setIncidentNatureCode( INCIDENT_NATURE_ZJ.getCode());
            }
            //设置 警情标签为空 默认为实警
            if (Strings.isBlank(queryBean.getIncidentLabel())) {
                queryBean.setIncidentLabel( INCIDENT_NATURE_ZJ.getCode());
            }
            //设置警情状态为空 默认立案
            if (Strings.isBlank(queryBean.getIncidentStatusCode())) {
                queryBean.setIncidentStatusCode( INCIDENT_STATUS_LA.getCode());
            }
            //设置立案方式为空 默认 消防立案
            if (Strings.isBlank(queryBean.getRegisterModeCode())) {
                //特殊处理
                if(INCIDENT_ALARM_MODE_YYZDBJ_GZ.getCode().equals( queryBean.getAlarmModeCode() )){
                    queryBean.setRegisterModeCode( INCIDENT_REGISTER_MODE_110LA.getCode());
                }else  if(   INCIDENT_ALARM_MODE_GKLWBJ.getCode().equals( queryBean.getAlarmModeCode() )
                        || INCIDENT_ALARM_MODE_YCJKBJ.getCode().equals( queryBean.getAlarmModeCode() )){
                    queryBean.setIncidentSource(  INCIDENT_REGISTER_MODE_XFFASLA.getCode() );
                }else{
                    queryBean.setRegisterModeCode( INCIDENT_REGISTER_MODE_XFLA.getCode());
                }

            }
            //设置警情来源 为空 默认 消防
            if (Strings.isBlank(queryBean.getIncidentSource())) {
                //特殊处理
                if( INCIDENT_ALARM_MODE_YYZDBJ_GZ.getCode().equals( queryBean.getAlarmModeCode() )){
                    queryBean.setIncidentSource(  INCIDENT_SOURCE_GA.getCode() );
                }else  if(   INCIDENT_ALARM_MODE_GKLWBJ.getCode().equals( queryBean.getAlarmModeCode() )
                        || INCIDENT_ALARM_MODE_YCJKBJ.getCode().equals( queryBean.getAlarmModeCode() )){
                    queryBean.setIncidentSource(  INCIDENT_SOURCE_FAS.getCode() );
                }else{
                    queryBean.setIncidentSource(  INCIDENT_SOURCE_XF.getCode() );
                }

            }

            // 获得重点单位信息
            KeyUnitSimpleBean keyUnitSimple = null;
            if (Strings.isNotBlank(queryBean.getKeyUnitId())) {
                keyUnitSimple = keyUnitService.findKeyUnitSimple(queryBean.getKeyUnitId());
            }
            //判断 受伤 死亡人数 为空 默认0
            if( Strings.isBlank( queryBean.getInjuredCount())){
                queryBean.setInjuredCount( "0" );
            }
            if( Strings.isBlank( queryBean.getDeathCount())){
                queryBean.setDeathCount( "0" );
            }
            //判断是否 存在

            //立案   保存受理记录->保存警情表->电话报警记录与警情关联
            //保存受理单
            AcceptanceEntity acceptanceEntity =  IncidentTransformUtil.transform(queryBean);
            acceptanceEntity.setAcceptanceTime(currentTime);
            if (keyUnitSimple != null) {
                acceptanceEntity.setKeyUnitTypeCode(keyUnitSimple.getUnitTypeCode());
            }

            logService.infoLog(logger, "repository", "save(dbAcceptanceEntity)", "repository is started...");
            Long startAcceptance = System.currentTimeMillis();

            accessor.save(acceptanceEntity);

            //获得受理单id
            String acceptanceId = acceptanceEntity.getId();

            acceptanceEntity.setIdCode(acceptanceId);

            Long endAcceptance = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbAcceptanceEntity)", String.format("repository is finished,execute time is :%sms", endAcceptance - startAcceptance));

            //警情信息
            String incidentId = null;
            IncidentEntity incidentEntity =  IncidentTransformUtil.transform(queryBean, acceptanceId);
            //立案 默认出动机构 出动车辆
            incidentEntity.setDispatchOrganization( "0" );
            incidentEntity.setDispatchVehicle( "0" );

            //判断是否为正常立案警情
            if ( HANDLE_TYPE_LA.getCode().equals(queryBean.getHandleType())) {
                if (  Strings.isBlank(incidentEntity.getBrigadeOrganizationId()) &&
                        Strings.isNotBlank( incidentEntity .getSquadronOrganizationId() )   ) {
                    OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(incidentEntity.getSquadronOrganizationId());
                    incidentEntity.setBrigadeOrganizationId(organizationBean == null ? null : organizationBean.getOrganizationParentId());
                }

                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
                Long startIncident = System.currentTimeMillis();
                //判定是否为重大警情
                getWhetherImportSign(incidentEntity);
                accessor.save(incidentEntity);

                //获得警情id
                incidentId = incidentEntity.getId();
                incidentEntity.setIdCode( incidentId );
                incidentEntity.setRelationIncidentNumber(incidentId); // 关联案件单编号
                incidentEntity.setRetentionIncidentNumber(incidentId); // 保留案件编号
                accessor.save(incidentEntity);

                Long endIncident = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", endIncident - startIncident));


                //设置总要警情  当前警情等级 > 3 插入关注信息
                String level = incidentEntity.getIncidentLevelCode();
                if (Objects.nonNull(level) && Integer.valueOf(level) > 3 ) {
                    //保存重要警情关注信息 type1 警情关注 2 重要警情关注 attentionType关注类型 1 普通关注（个人）；2班长关注 （ 全系统 ）attentionWay关注方式  1 系统关注  2 人工关注
                    AttentionBean attentionBean = attentionService.saveAttention(incidentEntity.getId(), 1, 2, 1, null);
                }

                //构建警情返回信息
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // 查询机构id-名称缓存map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                res = IncidentTransformUtil.transform(incidentEntity, dicsMap, organizationNameMap);
                if (keyUnitSimple != null) {
                    res.setKeyUnitName(keyUnitSimple.getUnitName());
                }

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_ADDCASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                String desc = String.format(" save incident  incident :%s ", incidentId);
                auditLogSaveInputInfo.setDesc(desc);
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_ADDCASE.getName());

                //判断是否已经向主管中队已发预警信息  如果已发则 不发送
                if( Strings.isNotBlank( res.getSquadronOrganizationId() ) && Strings.isNotBlank(  organizationService.getRootOrgId() )
                        && !res.getSquadronOrganizationId() .equals(   organizationService.getRootOrgId() )  ){
                    List<EarlyWarningBean> earlyWarningBeans = earlyWarningService.findEarlyWarningByIncidentIdAndOrganizationId(incidentId, res.getSquadronOrganizationId());
                    if (earlyWarningBeans == null || earlyWarningBeans.size() < 1) {
                        EarlyWarningSaveInputInfo earlyWarningSaveInputInfo = new EarlyWarningSaveInputInfo();
                        earlyWarningSaveInputInfo.setIncidentId(incidentId);
                        earlyWarningSaveInputInfo.setReceiveOrganizationId(res.getSquadronOrganizationId());
                        //预警类型为 正式预警 1
                        earlyWarningSaveInputInfo.setEarlyWarningType(String.valueOf(EnableEnum.ENABLE_TRUE.getCode()));
                        earlyWarningSaveInputInfo.setOrganizationId(res.getSquadronOrganizationId());
                        earlyWarningSaveInputInfo.setDistrictCode(res.getDistrictCode());
                        earlyWarningSaveInputInfo.setAlarmTime(queryBean.getAlarmStartTime());
                        earlyWarningSaveInputInfo.setCrimeAddress(res.getCrimeAddress());
                        earlyWarningSaveInputInfo.setLongitude(res.getLongitude());
                        earlyWarningSaveInputInfo.setLatitude(res.getLatitude());
                        earlyWarningSaveInputInfo.setHeight(res.getHeight());
                        earlyWarningSaveInputInfo.setIncidentTypeCode(res.getIncidentTypeCode());
                        earlyWarningSaveInputInfo.setIncidentTypeSubitemCode(res.getIncidentTypeSubitemCode());
                        earlyWarningSaveInputInfo.setIncidentLevelCode(res.getIncidentLevelCode());
                        earlyWarningSaveInputInfo.setAlarmPhone(res.getAlarmPhone());
                        earlyWarningSaveInputInfo.setSendOrganizationId(res.getSquadronOrganizationId());
                        earlyWarningSaveInputInfo.setSendPersonNumber(res.getAcceptancePersonNumber());
                        earlyWarningSaveInputInfo.setSendSeatNumber(res.getRegisterSeatNumber());
                        earlyWarningSaveInputInfo.setRemarks(null);
                        earlyWarningSaveInputInfo.setWhetherTestSign(res.getWhetherTestSign());
                        EarlyWarningListSaveInputInfo earlyWarningListSaveInputInfo = new EarlyWarningListSaveInputInfo();
                        List<EarlyWarningSaveInputInfo> earlyWarningSaveInputInfoList = new ArrayList<>();
                        earlyWarningSaveInputInfoList.add(earlyWarningSaveInputInfo);
                        earlyWarningListSaveInputInfo.setEarlyWarningList(earlyWarningSaveInputInfoList);
                        earlyWarningService.saveEarlyWarning(earlyWarningListSaveInputInfo);
                    }
                }

                //是否接处合一 消防无接处分离
//                if( !acceptAndDispatch ){

                //消息通知立案机构 主管单位所属大队
                Set<String> orgIds = new HashSet<>();
                List<String> orgIdList = new ArrayList<>();
                if(  Strings.isNotBlank(  res.getRegisterOrganizationId ()  )  ){
                    orgIdList.add(res.getRegisterOrganizationId());
                }
                if(  Strings.isNotBlank(  res.getBrigadeOrganizationId ()  ) ){
                    orgIds.add(res.getBrigadeOrganizationId());
                }
                // 主管单位
                if( whetherInSquadron  && Strings.isNotBlank(  res.getSquadronOrganizationId ()  )  ){
                    orgIds.add(  res.getSquadronOrganizationId ()  );
                }

                if( orgIdList.size() < 1 ){
                    orgIds.add( organizationService.getRootOrgId() ) ;
                }
                //获得单位上级
                List<String> parentIds = organizationService.findParentOrganizationIds(orgIdList);
                orgIds.addAll(parentIds);

                List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgIds));
                orgIds.addAll(orgCodes);
                if (orgCodes != null && orgCodes.size() > 0) {
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_NEW_INCIDENT.getCode(), res, orgIds);
                }
                //推送重大警情，
                pushIncident(orgCodes,incidentEntity,res,orgIds);
//                }

            } else if ( HANDLE_TYPE_WLA.getCode().equals(queryBean.getHandleType())) {
                //未立案处理 警情无效  无消息发送
                if (Strings.isBlank(incidentEntity.getBrigadeOrganizationId())  &&
                        Strings.isNotBlank( incidentEntity .getSquadronOrganizationId() )    ) {
                    if (Strings.isNotBlank(incidentEntity.getSquadronOrganizationId())) {
                        OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(incidentEntity.getSquadronOrganizationId());
                        incidentEntity.setBrigadeOrganizationId(organizationBean == null ? null : organizationBean.getOrganizationParentId());
                    }
                }
                //未立案处理 警情无效  无消息发送 标识 设置删除警情
                incidentEntity.setIncidentLabel( INCIDENT_NATURE_SCJJ.getCode());
                incidentEntity.setValid(false);

                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
                Long startIncident = System.currentTimeMillis();

                accessor.save(incidentEntity);
                incidentId = incidentEntity.getId() ;
                incidentEntity.setIdCode( incidentId );
                incidentEntity.setRelationIncidentNumber(incidentId); // 关联案件单编号
                incidentEntity.setRetentionIncidentNumber(incidentId); // 保留案件编号
                accessor.save(incidentEntity);

                Long endIncident = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", endIncident - startIncident));

            } else if ( HANDLE_TYPE_SR.getCode().equals(queryBean.getHandleType()) ||
                     HANDLE_TYPE_WS.getCode().equals(queryBean.getHandleType()) ||
                     HANDLE_TYPE_ZX.getCode().equals(queryBean.getHandleType())) {
                //无效警情（咨询，无声，骚扰）处理
                //去掉预警信息
                if (Strings.isNotBlank(queryBean.getId())) {
                    earlyWarningService.removeEarlyWarning(queryBean.getId(), String.valueOf(EnableEnum.ENABLE_TRUE.getCode()), null);
                }
            } else if ( HANDLE_TYPE_CFBJ.getCode().equalsIgnoreCase(queryBean.getHandleType())) {
                //重复警情处理
                incidentId = queryBean.getRepeatPrimaryIncidentId();
                //去掉预警信息
                if (Strings.isNotBlank(queryBean.getId())) {
                    earlyWarningService.removeEarlyWarning(queryBean.getId(), String.valueOf(EnableEnum.ENABLE_TRUE.getCode()), null);
                }
            }

            //受理单 警情单 其他警情信息 保存
            logService.infoLog(logger, "repository", "save(dbIncidentEntity,dbAcceptanceEntity)", "repository is started...");
            Long startOther = System.currentTimeMillis();

            acceptanceEntity.setIncidentId(incidentId); //案件ID
            acceptanceEntity.setOriginalIncidentNumber(incidentId); //原始案件编号

            accessor.save(acceptanceEntity);

            Long endOther = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbIncidentEntity,dbAcceptanceEntity)", String.format("repository is finished,execute time is :%sms", endOther - startOther));

            //电话报警记录与警情关联
            logService.infoLog(logger, "repository", "update(dbTelephoneEntity)", "repository is started...");
            Long startTelephone = System.currentTimeMillis();

            //补充电话报警记录信息 更新录音记录
            if (null != telephoneEntity) {
                telephoneEntity.setAcceptanceId(acceptanceId);
                telephoneEntity.setIncidentId(incidentId);
                telephoneEntity.setOriginalIncidentNumber(incidentId);
                telephoneEntity.setAlarmPersonName(queryBean.getAlarmPersonName());
                telephoneEntity.setAlarmPersonSex(queryBean.getAlarmPersonSex());
                telephoneEntity.setAlarmPersonPhone(queryBean.getAlarmPersonPhone());
                telephoneEntity.setAlarmAddress(queryBean.getAlarmAddress());
                telephoneEntity.setWhetherTrappedSign(queryBean.getWhetherTrappedSign());
                accessor.save(telephoneEntity);

                //更新 录音记录
                SoundRecordBean soundRecordBean = soundRecordService.updateSoundRecordIncidentId( telephoneEntity.getId() , incidentId ) ;
                //其他系统
                if( soundRecordBean != null ){
                    Map<String, String > otherParams = new HashMap<>( ) ;
                    pushDataService.pushSoundRecord( soundRecordBean , otherParams ) ;
                }

            }

            try {
                if (!StringUtils.isBlank(incidentEntity.getLatitude())&&!StringUtils.isBlank(incidentEntity.getLongitude())){
                    Float lon=Float.valueOf(incidentEntity.getLongitude());
                    Float lat=Float.valueOf(incidentEntity.getLatitude());
                    List<LocationRecordEntity> locations = locationRecordRepository.findLocationRecordEntityByIncidentIdAndLocation(incidentId, lon, lat);
                    if (locations!=null&&!locations.isEmpty()){
                        LocationRecordEntity locationRecordEntity = locations.get(0);
                        if (locationRecordEntity.getAcquisitionFlag()==null||locationRecordEntity.getAcquisitionFlag()!=1)
                        locationRecordEntity.setAcquisitionFlag(1);
                        accessor.save(locationRecordEntity);
                    }
                }
            }catch (Exception ex){

            }


            Long endTelephone = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", endTelephone - startTelephone));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            if( res != null ){

                //推送数据 外部系统
                Map<String, String > otherParams = new HashMap<>()  ;
                otherParams.put( "unTrafficAlarmId" , queryBean.getUnTrafficAlarmId() ) ;
                pushDataService.pushNewIncident ( res , otherParams  ) ;

                //保存警情状态变动记录
                incidentStatusChangeService.saveIncidentStatusChange(incidentId, incidentEntity.getIncidentStatusCode(),
                        false , null   );

            }



            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_INCIDENT_FAIL);
        }
    }






    /**
     * {@inheritDoc}
     *
     * @see IncidentService#saveIncidentHandle(IncidentHandleSaveInputInfo, UserInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HandleIncidentBean saveIncidentHandle(IncidentHandleSaveInputInfo queryBean, UserInfo userInfo) {
        if (queryBean == null || null == queryBean.getHandleSaveInputInfo()
                || null == queryBean.getHandleSaveInputInfo()) {
            logService.infoLog(logger, "service", "saveIncidentHandle", "IncidentHandleSaveInputInfo  or telephoneId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentHandle", "service is started...");
            Long logStart = System.currentTimeMillis();

            HandleIncidentBean res = null;

            //保存警情信息
            if (null != queryBean.getHandleSaveInputInfo()) {
                if (userInfo == null) {
                    userInfo = userService.getUserInfo();
                }

                res = new HandleIncidentBean();
                decodeIncidentSaveInputInfo(queryBean.getIncidentSaveInputInfo());//URLDecoder inputInfo转码
                IncidentBean incidentBean = saveIncident(queryBean.getIncidentSaveInputInfo(), userInfo, false, true);
                //获得警情id
                String incidentId = incidentBean.getId();
                res.setIncidentBean(incidentBean);
                //获得调派信息
                HandleSaveInputInfo handleSaveInputInfo = queryBean.getHandleSaveInputInfo();
                handleSaveInputInfo.setIncidentId(incidentId);
                //保存调派信息
                HandleBean handleBean = handleService.saveHandle(handleSaveInputInfo, userInfo);
                res.setHandleBean(handleBean);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentHandle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#updateIncident(IncidentSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IncidentBean updateIncident(IncidentSaveInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "updateIncident", "IncidentUpdateInputInfo   is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentUpdateInputInfo(queryBean);

            IncidentBean res = null;

            IncidentEntity incidentEntity = accessor.getById(queryBean.getId(), IncidentEntity.class);

            UserInfo userInfo = userService.getUserInfo();

            if (null != incidentEntity) {

                res = findIncident(queryBean.getId(), false);

                //复制数据库警情 生成临时警情 用以关键数据变更比对
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // 查询机构id-名称缓存map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();


                IncidentBean tempBean = IncidentTransformUtil.transform(incidentEntity, dicsMap, organizationNameMap);
                if (Strings.isNotBlank(tempBean.getKeyUnitId())) {
                    KeyUnitSimpleBean keyUnitSimple = keyUnitService.findKeyUnitSimple(tempBean.getKeyUnitId());
                    if (keyUnitSimple != null) {
                        tempBean.setKeyUnitName(keyUnitSimple.getUnitName());
                    }
                }

                //更新警情信息
                IncidentTransformUtil.transform(queryBean, incidentEntity);
                if (Strings.isBlank(incidentEntity.getBrigadeOrganizationId())) {
                    OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(incidentEntity.getSquadronOrganizationId());
                    incidentEntity.setBrigadeOrganizationId(organizationBean == null ? null : organizationBean.getOrganizationParentId());
                }


                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
                Long startIncident = System.currentTimeMillis();

                accessor.save(incidentEntity);

                Long endIncident = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", endIncident - startIncident));

                List<TelephoneEntity> telephoneEntityList = incidentRepository.findTelephoneByOriginalIncidentNumber(incidentEntity.getId());
                TelephoneEntity telephoneEntity = null;
                if (null != telephoneEntityList && telephoneEntityList.size() > 0) {
                    telephoneEntity = telephoneEntityList.get(0);
                    telephoneEntity.setAlarmPersonName(queryBean.getAlarmPersonName());
                    telephoneEntity.setAlarmPersonSex(queryBean.getAlarmPersonSex());
                    telephoneEntity.setAlarmPersonPhone(queryBean.getAlarmPersonPhone());
                    telephoneEntity.setAlarmAddress(queryBean.getAlarmAddress());
                    telephoneEntity.setWhetherTrappedSign(queryBean.getWhetherTrappedSign());

                    logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", "repository is started...");
                    Long startTelephone = System.currentTimeMillis();

                    accessor.save(telephoneEntity);

                    Long endTelephone = System.currentTimeMillis();
                    logService.infoLog(logger, "repository", "save(dbTelephoneEntity)", String.format("repository is finished,execute time is :%sms", endTelephone - startTelephone));

                }
                IncidentTransformUtil.transform(res, incidentEntity, dicsMap, organizationNameMap);
                if (Strings.isNotBlank(res.getKeyUnitId())) {
                    KeyUnitSimpleBean keyUnitSimple = keyUnitService.findKeyUnitSimple(res.getKeyUnitId());
                    if (keyUnitSimple != null) {
                        res.setKeyUnitName(keyUnitSimple.getUnitName());
                    }
                }

                //关键数据变更记录 报错不抛出异常 不影响主流程
                incidentKeyDataChange(tempBean, res, userInfo);


            } else {
                logService.infoLog(logger, "service", "getById(dbIncidentEntity)", "incident data by id  is null.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }



            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = findIncidentParticipantOrganizationId(queryBean.getId());
            orgIds.add(queryBean.getSquadronOrganizationId());
            orgIds.add(queryBean.getBrigadeOrganizationId());
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_INCIDENT.getCode(), res, orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            //推送数据 外部系统
            Map<String, String > otherParams = new HashMap<>()  ;
           pushDataService.pushUpdateIncident( res  , otherParams  ) ;


            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_FAIL);
        }
    }



    /**
     * 警情关键数据变更记录
     * 警情关键数据： 案件坐标、案发地址、案件等级、案件类型、燃烧对象、被困人数、火灾场所、烟雾情况、起火楼层
     *
     * @param tempBean   警情临时原始数据
     * @param changeBean 警情变更数据
     */
    private void incidentKeyDataChange(IncidentBean tempBean, IncidentBean changeBean, UserInfo userInfo) {
        try {
            String tableName = JpaUtil.getTableName(IncidentEntity.class);
            Map<String, Field> fieldMap = JpaUtil.getColumnField(IncidentEntity.class);

            //案件关键数据  经度 纬度 高度 有变更保存
            //经纬度 变更比较
            String oldlon = tempBean.getLongitude() == null ? "" : tempBean.getLongitude();
            String oldlat = tempBean.getLatitude() == null ? "" : tempBean.getLatitude();
            String oldhei = tempBean.getHeight() == null ? "" : tempBean.getHeight();

            String changelon = changeBean.getLongitude() == null ? "" : changeBean.getLongitude();
            String changelat = changeBean.getLatitude() == null ? "" : changeBean.getLatitude();
            String changehei = changeBean.getHeight() == null ? "" : changeBean.getHeight();
            if (whetherChange(oldlon, changelon)
                    || whetherChange(oldlat, changelat)
                /* || whetherChange(oldhei, changehei)*/
            ) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField("{" + JpaUtil.getColumnName(
                        fieldMap.get("longitude")) + ","
                        + JpaUtil.getColumnName(fieldMap.get("latitude")) + /*","
                        + JpaUtil.getColumnName(fieldMap.get("height")) + */"}");
                keyDataChangeRecord.setOriginalDate("{" + oldlon + "," + oldlat + /*"," + oldhei +*/ "}");
                keyDataChangeRecord.setChangeDate("{" + changelon + "," + changelat + /*"," + changehei +*/ "}");
                keyDataChangeRecord.setRemarks("警情经度纬度高度变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                //保存文书
                DocumentSaveInputInfo inputInfo = new DocumentSaveInputInfo();
                inputInfo.setIncidentId(changeBean.getId());
                inputInfo.setDateSourceId( changeBean.getId() );
                inputInfo.setTitle( DOCUMENT_TYPE_AFDZBG.getName());
                inputInfo.setContent("经度纬度由" + "{" + oldlon + "," + oldlat + /*"," + oldhei +*/ "}" +
                        "变更为" + "{" + changelon + "," + changelat + /*"," + changehei +*/ "}" + "");
                inputInfo.setType( DOCUMENT_TYPE_AFDZBG.getCode());
                inputInfo.setFeedbackPerson(userInfo.getPersonName());
                inputInfo.setFeedbackOrganizationId(userInfo.getOrgId());
                inputInfo.setTerminalId(null);
                inputInfo.setRemarks(null);
                documentService.saveDocument(inputInfo);

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("longitude  latitude height from " + keyDataChangeRecord.getOriginalDate() + " change to " + changeBean.getCrimeAddress() + "");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());


            }
            // 案件关键数据 地址变更
            if (whetherChange(tempBean.getCrimeAddress(), changeBean.getCrimeAddress())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("crimeAddress")));
                keyDataChangeRecord.setOriginalDate(tempBean.getCrimeAddress());
                keyDataChangeRecord.setChangeDate(changeBean.getCrimeAddress());
                keyDataChangeRecord.setRemarks("警情案发地址变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                //保存文书
                DocumentSaveInputInfo inputInfo = new DocumentSaveInputInfo();
                inputInfo.setIncidentId(changeBean.getId());
                inputInfo.setDateSourceId( changeBean.getId() );
                inputInfo.setTitle( DOCUMENT_TYPE_AFDZBG.getName());
                inputInfo.setContent("案发地址由" + tempBean.getCrimeAddress() + "变更为" + changeBean.getCrimeAddress() + "");
                inputInfo.setType( DOCUMENT_TYPE_AFDZBG.getCode());
                inputInfo.setFeedbackPerson(userInfo.getPersonName());
                inputInfo.setFeedbackOrganizationId(userInfo.getOrgId());
                inputInfo.setTerminalId(null);
                inputInfo.setRemarks(null);
                documentService.saveDocument(inputInfo);

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("incident " + tempBean.getId() + " crimeAddress from " + tempBean.getCrimeAddress() + " change to " + changeBean.getCrimeAddress() + "");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }
            // 案件关键数据 等级变更
            if (whetherChange(tempBean.getIncidentLevelCode(), changeBean.getIncidentLevelCode())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("incidentLevelCode")));
                keyDataChangeRecord.setOriginalDate(tempBean.getIncidentLevelCode());
                keyDataChangeRecord.setChangeDate(changeBean.getIncidentLevelCode());
                keyDataChangeRecord.setRemarks("案件等级变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                //案件等级变更 保存文书
                DocumentSaveInputInfo inputInfo = new DocumentSaveInputInfo();
                inputInfo.setIncidentId(changeBean.getId());
                inputInfo.setDateSourceId( changeBean.getId() );
                inputInfo.setTitle( DOCUMENT_TYPE_JQDJBG.getName());
                inputInfo.setContent("案件等级由" + tempBean.getIncidentLevelName() + "变更为" + changeBean.getIncidentLevelName() + "");
                inputInfo.setType( DOCUMENT_TYPE_JQDJBG.getCode());
                inputInfo.setFeedbackPerson(userInfo.getPersonName());
                inputInfo.setFeedbackOrganizationId(userInfo.getOrgId());
                inputInfo.setTerminalId(null);
                inputInfo.setRemarks(null);
                documentService.saveDocument(inputInfo);

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("incident " + tempBean.getId() + " level from  " + tempBean.getIncidentLevelName() + " change to " + changeBean.getIncidentLevelName() + "");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }
            // 案件关键数据 类型变更
            if (whetherChange(tempBean.getIncidentTypeCode(), changeBean.getIncidentTypeCode())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("incidentTypeCode")));
                keyDataChangeRecord.setOriginalDate(tempBean.getIncidentTypeCode());
                keyDataChangeRecord.setChangeDate(changeBean.getIncidentTypeCode());
                keyDataChangeRecord.setRemarks("案件类型变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                //案件类型变更 保存文书
                DocumentSaveInputInfo inputInfo = new DocumentSaveInputInfo();
                inputInfo.setIncidentId(changeBean.getId());
                inputInfo.setDateSourceId( changeBean.getId() );
                inputInfo.setTitle( DOCUMENT_TYPE_JQLXBG.getName());
                inputInfo.setContent("案件类型由" + tempBean.getIncidentTypeName() + "变更为" + changeBean.getIncidentTypeName() + "");
                inputInfo.setType( DOCUMENT_TYPE_JQLXBG.getCode());
                inputInfo.setFeedbackPerson(userInfo.getPersonName());
                inputInfo.setFeedbackOrganizationId(userInfo.getOrgId());
                inputInfo.setTerminalId(null);
                inputInfo.setRemarks(null);
                documentService.saveDocument(inputInfo);

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("incident " + tempBean.getId() + " type from " + tempBean.getIncidentTypeName() + " change to " + changeBean.getIncidentTypeName() + "");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }

            // 案件关键数据子项 类型变更
            if (whetherChange(tempBean.getIncidentTypeSubitemCode(), changeBean.getIncidentTypeSubitemCode())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("incidentTypeSubitemCode")));
                keyDataChangeRecord.setOriginalDate(tempBean.getIncidentTypeSubitemCode());
                keyDataChangeRecord.setChangeDate(changeBean.getIncidentTypeSubitemCode());
                keyDataChangeRecord.setRemarks("案件类型子项变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("incident " + tempBean.getId() + " subitem type from " + tempBean.getIncidentTypeSubitemName() + " change to " + changeBean.getIncidentTypeSubitemName() + "");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }

            // 案件关键数据 燃烧对象变更
            if (whetherChange(tempBean.getDisposalObjectCode(), changeBean.getDisposalObjectCode())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("disposalObjectCode")));
                keyDataChangeRecord.setOriginalDate(tempBean.getDisposalObjectCode());
                keyDataChangeRecord.setChangeDate(changeBean.getDisposalObjectCode());
                keyDataChangeRecord.setRemarks("警情处置对象变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("incident " + tempBean.getId() + " disposalObject from " + tempBean.getDisposalObjectName() + " change to " + changeBean.getDisposalObjectName() + "");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }

            // 案件关键数据 主管中队变更
            if (whetherChange(tempBean.getSquadronOrganizationId(), changeBean.getSquadronOrganizationId())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("squadronOrganizationId")));
                keyDataChangeRecord.setOriginalDate(tempBean.getSquadronOrganizationId());
                keyDataChangeRecord.setChangeDate(changeBean.getSquadronOrganizationId());
                keyDataChangeRecord.setRemarks("主管中队变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);


                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("incident " + tempBean.getId() + " squadronOrganization from " + tempBean.getSquadronOrganizationName() + " change to " + changeBean.getSquadronOrganizationName() + "");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }

            // 案件关键数据 被困人数变更
            if (whetherChange(tempBean.getTrappedCount(), changeBean.getTrappedCount())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("trappedCount")));
                keyDataChangeRecord.setOriginalDate(tempBean.getTrappedCount());
                keyDataChangeRecord.setChangeDate(changeBean.getTrappedCount());
                keyDataChangeRecord.setRemarks("警情被困人数变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

//                //保存操作日志记录
//                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
//                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
//                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
//                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
//                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
//                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
//                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
//                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount() );
//                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
//                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
//                auditLogSaveInputInfo.setDesc("被困人数由" + tempBean.getTrappedCount() + "变更为" + changeBean.getTrappedCount() + "");
//                auditLogSaveInputInfo.setRemarks(null);
//                auditLogService.saveAuditLog(auditLogSaveInputInfo);
//                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
//                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }
            // 案件关键数据 灾害场所变更
            if (whetherChange(tempBean.getDisasterSites(), changeBean.getDisasterSites())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("disasterSites")));
                keyDataChangeRecord.setOriginalDate(tempBean.getDisasterSites());
                keyDataChangeRecord.setChangeDate(changeBean.getDisasterSites());
                keyDataChangeRecord.setRemarks("警情灾害场所变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

//                //保存操作日志记录
//                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
//                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
//                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
//                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
//                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
//                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
//                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
//                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount() );
//                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
//                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
//                auditLogSaveInputInfo.setDesc("灾害场所由" + tempBean.getDisasterSitesName() + "变更为" + changeBean.getDisasterSitesName() + "");
//                auditLogSaveInputInfo.setRemarks(null);
//                auditLogService.saveAuditLog(auditLogSaveInputInfo);
//                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
//                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }
            // 案件关键数据 烟雾情况变更
            if (whetherChange(tempBean.getSmogSituationCode(), changeBean.getSmogSituationCode())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("smogSituationCode")));
                keyDataChangeRecord.setOriginalDate(tempBean.getSmogSituationName());
                keyDataChangeRecord.setChangeDate(changeBean.getSmogSituationName());
                keyDataChangeRecord.setRemarks("警情烟雾情况变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

//
//                //保存操作日志记录
//                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
//                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
//                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
//                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
//                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
//                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
//                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
//                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount() );
//                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
//                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
//                auditLogSaveInputInfo.setDesc( "烟雾情况由" + tempBean.getSmogSituationName() + "变更为" + changeBean.getSmogSituationName() + "" );
//                auditLogSaveInputInfo.setRemarks(null);
//                auditLogService.saveAuditLog(auditLogSaveInputInfo);
//                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
//                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());

            }
            // 案件关键数据 作战楼层（起火楼层）变更
            if (whetherChange(tempBean.getBurningFloor(), changeBean.getBurningFloor())) {
                KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                //警情变更通用属性设置
                keyDataChangeRecord.setBusinessTableName(tableName);
                keyDataChangeRecord.setBusinessDataId(changeBean.getId());
                keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("burningFloor")));
                keyDataChangeRecord.setOriginalDate(tempBean.getBurningFloor());
                keyDataChangeRecord.setChangeDate(changeBean.getBurningFloor());
                keyDataChangeRecord.setRemarks("警情作战楼层（起火楼层）变更");

                //关键数据变更
                keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

//                //保存操作日志记录
//                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
//                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
//                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
//                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
//                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
//                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
//                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
//                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount() );
//                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
//                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
//                auditLogSaveInputInfo.setDesc( "作战楼层（起火楼层）" + tempBean.getBurningFloor() + "变更为" + changeBean.getBurningFloor() + "" );
//                auditLogSaveInputInfo.setRemarks(null);
//                auditLogService.saveAuditLog(auditLogSaveInputInfo);
//                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
//                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
            }

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveKeyDataChangeRecord", "save  KeyDataChangeRecord  fail.", ex);
        }
    }

    /**
     * 判断 两个数据是否 存在变动
     *
     * @param sourceData
     * @param changeData
     * @return
     */
    private Boolean whetherChange(String sourceData, String changeData) {
        if (null == sourceData) {
            if (null != changeData) {
                return true;
            }
        } else {
            if (sourceData.equals(changeData)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#saveIncidentMerge(IncidentMergeSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveIncidentMerge(IncidentMergeSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveIncidentMerge", "IncidentMergeSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentMerge", "service is started...");
            Long logStart = System.currentTimeMillis();

            //获取主案件详情
            IncidentEntity mainIncidentEntity = accessor.getById(inputInfo.getMainIncidentId(), IncidentEntity.class);
            if (mainIncidentEntity == null || Strings.isBlank(mainIncidentEntity.getId())) {
                logService.infoLog(logger, "service", "saveIncidentMerge", "mainIncidentEntity is null or mainIncidentId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            String mainIncidentId = mainIncidentEntity.getId();
            //获取从案件详情
            String mergeIncidentId = inputInfo.getMergeIncidentId();
            IncidentEntity mergeIncidentEntity = accessor.getById(mergeIncidentId, IncidentEntity.class);
            if (mergeIncidentEntity == null || Strings.isBlank(mergeIncidentEntity.getId())) {
                logService.infoLog(logger, "service", "saveIncidentMerge", "mergeIncidentEntity is null or mergeIncidentId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            List<IncidentEntity> mergeIncidentEntityList = incidentRepository.findRelationIncidentNumberById(mergeIncidentId);
            //主案件 是否为合并主案件变更为1
            mainIncidentEntity.setWhetherMainMerge(EnableEnum.ENABLE_TRUE.getCode());
            accessor.save(mainIncidentEntity);
            //将从案件合并状态置为1，并且关联到主案件id 案件有效性设置为0
            for (IncidentEntity mergeIncident : mergeIncidentEntityList) {
                mergeIncident.setMergeStatus(EnableEnum.ENABLE_TRUE.getCode());
                mergeIncident.setIncidentLabel( INCIDENT_NATURE_GLJQ.getCode());//警情标识  设置为关联警情  ;
                mergeIncident.setWhetherMainMerge(EnableEnum.ENABLE_FALSE.getCode());
                mergeIncident.setValid(EnableEnum.ENABLE_FALSE.getCode());
                mergeIncident.setRelationIncidentNumber(mainIncidentId);
            }
            accessor.save(mergeIncidentEntityList);

            //变更受理单关联的案件id
            acceptanceService.changeAcceptanceIncidentId(mergeIncidentId, mainIncidentId);

            //变更电话报警记录 关联的案件id
            telephoneService.changeTelephoneIncidentId(mergeIncidentId, mainIncidentId);

            //变更处警记录 关联的案件id
            //并且 根据处警记录id 变更 调派信息、调派单位装备信息、作战车辆信息 相关的案件id
            handleService.changeHandleIncidentId(mergeIncidentId, mainIncidentId);

            //变更参战人员信息
            participantFeedbackService.saveIncidentParticipantMerge(mergeIncidentId, mainIncidentId);

            //保存警情合并记录
            incidentMergeService.saveIncidentMergeRecord(inputInfo);

            UserInfo userInfo = userService.getUserInfo();


            //保存操作日志记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_INCIDENT_MERGE.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc(" merge incident from  " + mergeIncidentEntity.getId() + " merge to " + mainIncidentEntity.getId());
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_INCIDENT_MERGE.getName());


            //案件信息
            IncidentBean mainIncidentBean = findIncident(inputInfo.getMainIncidentId(), false);
            IncidentBean mergeIncidentBean = findIncident(inputInfo.getMergeIncidentId(), false);
            MergeSpiltWebSocketPushBean webSocketPushBean = new MergeSpiltWebSocketPushBean();
            webSocketPushBean.setMainIncidentBean(mainIncidentBean);
            webSocketPushBean.setIncidentBean(mergeIncidentBean);

            //消息通知立案机构、主管单位、以及所属大队
            Set<String> orgSet = new HashSet();
            List<String> mainOrgs = findIncidentParticipantOrganizationId(mainIncidentEntity.getId());
            List<String> mergeOrgs = findIncidentParticipantOrganizationId(mergeIncidentEntity.getId());
            orgSet.addAll(mainOrgs);
            orgSet.addAll(mergeOrgs);

            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.INCIDENT_MERGE.getCode(), webSocketPushBean, orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentMerge", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentMerge", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_INCIDENT_MERGE_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see IncidentService#saveIncidentSplit(IncidentSplitSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveIncidentSplit(IncidentSplitSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveIncidentSplit", "IncidentMergeSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveIncidentSplit", "service is started...");
            Long logStart = System.currentTimeMillis();

            //获取主案件详情
            IncidentEntity mainIncidentEntity = accessor.getById(inputInfo.getMainIncidentId(), IncidentEntity.class);
            if (mainIncidentEntity == null || Strings.isBlank(mainIncidentEntity.getId())) {
                logService.infoLog(logger, "service", "saveIncidentMerge", "mainIncidentEntity is null or mainIncidentId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            //获取从案件详情
            IncidentEntity mergeIncidentEntity = accessor.getById(inputInfo.getSplitIncidentId(), IncidentEntity.class);
            if (mergeIncidentEntity == null || Strings.isBlank(mergeIncidentEntity.getId())) {
                logService.infoLog(logger, "service", "saveIncidentMerge", "mergeIncidentEntity is null or mergeIncidentId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            //主案件 是否为合并主案件变更为0 需要判断是否为 主案件
            Integer splitNum = incidentRepository.findRelationNumByIncidentIdAndSpliptIncidentId( inputInfo.getMainIncidentId(), inputInfo.getSplitIncidentId()  ) ;
            if( splitNum > 0 ){
                mainIncidentEntity.setWhetherMainMerge(EnableEnum.ENABLE_TRUE.getCode());
            }else {
                mainIncidentEntity.setWhetherMainMerge(EnableEnum.ENABLE_FALSE.getCode());
            }
            accessor.save(mainIncidentEntity);
            //将从案件合并状态置为0，并且恢复原案件id 案件有效性设置为1
            String mergeIncidentId = mergeIncidentEntity.getId();
            mergeIncidentEntity.setMergeStatus(EnableEnum.ENABLE_FALSE.getCode());
            mergeIncidentEntity.setIncidentLabel( INCIDENT_NATURE_ZJ.getCode());//警情标识  设置为真警  ;
            mergeIncidentEntity.setWhetherMainMerge(EnableEnum.ENABLE_FALSE.getCode());
            mergeIncidentEntity.setValid(EnableEnum.ENABLE_TRUE.getCode());
            mergeIncidentEntity.setRelationIncidentNumber(mergeIncidentEntity.getRetentionIncidentNumber());
            accessor.save(mergeIncidentEntity);

            //恢复受理单 原始案件id
            acceptanceService.recoverAcceptanceIncidentId(mergeIncidentId);

            //恢复电话报警记录 原始案件id
            telephoneService.recoverTelephoneIncidentId(mergeIncidentId);

            //恢复处警记录 原始案件id
            //并且 根据处警记录id 变更 调派信息、调派单位装备信息、作战车辆信息 相关的案件id
            handleService.recoverHandleIncidentId(mergeIncidentId);
            //恢复参战人员信息
            participantFeedbackService.saveIncidentParticipantSplit(mergeIncidentId);


            UserInfo userInfo = userService.getUserInfo();

            //保存操作日志记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_INCIDENT_SPLIT.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc(" split incident split  " + mergeIncidentEntity.getId() + "  from  " + mainIncidentEntity.getId());
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_INCIDENT_SPLIT.getName());


            //案件信息
            IncidentBean mainIncidentBean = findIncident(inputInfo.getMainIncidentId(), false);
            IncidentBean splitIncidentBean = findIncident(inputInfo.getSplitIncidentId(), false);
            MergeSpiltWebSocketPushBean webSocketPushBean = new MergeSpiltWebSocketPushBean();
            webSocketPushBean.setMainIncidentBean(mainIncidentBean);
            webSocketPushBean.setIncidentBean(splitIncidentBean);

            //消息通知立案机构、主管单位、以及所属大队
            Set<String> orgSet = new HashSet();
            List<String> mainOrgs = findIncidentParticipantOrganizationId(mainIncidentEntity.getId());
            List<String> mergeOrgs = findIncidentParticipantOrganizationId(mergeIncidentEntity.getId());
            orgSet.addAll(mainOrgs);
            orgSet.addAll(mergeOrgs);

            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.INCIDENT_SPLIT.getCode(), webSocketPushBean, orgSet);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveIncidentSplit", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveIncidentSplit", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_INCIDENT_SPLIT_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findIncidentMerge(String)
     */
    @Transactional(readOnly = true)
    @Override
    public IncidentMergeBean findIncidentMerge(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentMerge", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentMerge", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentMergeBean res = null;
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            //获取主案件详情
            IncidentEntity mainIncidentEntity = accessor.getById(incidentId, IncidentEntity.class);
            if (mainIncidentEntity == null || Strings.isBlank(mainIncidentEntity.getId())) {
                logService.infoLog(logger, "service", "findIncidentMerge", "mainIncidentEntity is null or mainIncidentId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            res = new IncidentMergeBean();

            IncidentBean mainIncidentBean = findIncident(incidentId, false);
            res.setMainIncident(mainIncidentBean);

            String account = userService.getAccount();

            //获得被合并案件信息
            logService.infoLog(logger, "repository", "findIncidentMergeByMainIncidentId( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();


            List<Object[]> incidentMergeEntityList = incidentRepository.findIncidentDetailed(null, incidentId, null, account);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentMergeByMainIncidentId( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));
            Map<String,IncidentBean> map=new HashMap<>();
            if (incidentMergeEntityList != null && incidentMergeEntityList.size() > 0) {
                for (Object[] incidentObj : incidentMergeEntityList) {
                    IncidentBean bean = IncidentTransformUtil.transform(incidentObj, dicsMap, organizationNameMap , handlePersonNumType);
                    if (Strings.isNotBlank(bean.getKeyUnitId())) {
                        KeyUnitSimpleBean keyUnitSimple = keyUnitService.findKeyUnitSimple(bean.getKeyUnitId());
                        if (keyUnitSimple != null) {
                            bean.setKeyUnitName(keyUnitSimple.getUnitName());
                        }
                    }
                    map.put(bean.getId(),bean);
                }
                List<Object[]> ret = incidentRepository.countIncidentsVechileMedicament(new ArrayList<>(map.keySet()));
                if (ret!=null&&!ret.isEmpty()){
                    for (Object[] objects : ret) {
                        String id = IncidentTransformUtil.toString(objects[0]);
                        String totalFoam=IncidentTransformUtil.toString(objects[1]);
                        String totalWater=IncidentTransformUtil.toString(objects[2]);
                        IncidentBean incidentBean = map.get(id);
                        if (incidentBean!=null){
                            incidentBean.setTotalWater(totalWater);
                            incidentBean.setTotalFoam(totalFoam);
                        }
                    }
                }

            }
            res.setMergeIncidents(new ArrayList<>(map.values()));





            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentMerge", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentMerge", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_MERGE_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#removeIncident(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeIncident(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "removeIncident", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            //获取案件详情
            IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);
            if (incidentEntity == null || Strings.isBlank(incidentEntity.getId())) {
                logService.infoLog(logger, "service", "removeIncident", "incidentEntity is null or incidentId is blank.");
                return true;
            }

            //案件有效性置为0 案件性质 警情标识设置删除警情
            incidentEntity.setValid(false);
            incidentEntity.setIncidentLabel( INCIDENT_NATURE_SCJJ.getCode()); //设置警情标识 删除警情
            //保存更改
            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(incidentEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            UserInfo userInfo = userService.getUserInfo();

            //保存操作日志记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_INCIDENT_REMOVE.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc(" remove incident  from   " + incidentEntity.getId() + " ");
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_INCIDENT_REMOVE.getName());


            //消息通知立案机构、主管单位、以及所属大队
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = findIncidentParticipantOrganizationId(incidentId);
            orgSet.addAll(orgIds);
            IncidentBean incidentBean = findIncident(incidentId, false);

            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.INCIDENT_REMOVE.getCode(), incidentBean, orgSet);


            //其他
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushUpdateIncident( incidentBean , otherParams ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.REMOVE_INCIDENT_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see  #updateIncidentStatus(String, String , String    )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateIncidentStatus(String incidentId, String statusCode ,
                                        String handleId   ) {
        if (Strings.isBlank(incidentId) || Strings.isBlank(statusCode)) {
            logService.infoLog(logger, "service", "updateIncidentStatus", "incidentId or statusCode is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateIncidentStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            //获取案件详情
            IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);
            if (incidentEntity == null || Strings.isBlank(incidentEntity.getId())) {
                logService.infoLog(logger, "service", "updateIncidentStatus", "incidentEntity is null or incidentId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            //需要判断 案件状态是否可以修改
            if (  !JudgeIncidentVehicleStatusUtil.JudgeIncidentStatus( incidentEntity.getIncidentStatusCode() , statusCode )   ) {
                return  true ;
            }
            //案件状态变更
            incidentEntity.setIncidentStatusCode(statusCode);

            if(   INCIDENT_STATUS_TZ.getCode().equals( statusCode )  ){
                incidentEntity.setTransmitTime( servletService.getSystemTime() );     // 通知时间

            }else if(   INCIDENT_STATUS_CD.getCode().equals( statusCode )  ){
                incidentEntity.setDispatchTime( servletService.getSystemTime() );   // 出动时间

            }else if(  INCIDENT_STATUS_DC.getCode().equals( statusCode ) ){
                incidentEntity.setArriveTime( servletService.getSystemTime()) ;  // 到达时间
                incidentEntity.setFightTime( servletService.getSystemTime() ); // 战斗展开时间

            }else  if(  INCIDENT_STATUS_KSZZ.getCode().equals( statusCode ) ){
                incidentEntity.setFightStartTime( servletService.getSystemTime() ); // （出水）作战开始时间

            }else  if(  INCIDENT_STATUS_KZHS.getCode().equals( statusCode ) ){
                incidentEntity.setFireControlTime( servletService.getSystemTime() ); // （控制）火势控制时间

            }else  if(  INCIDENT_STATUS_KZXM.getCode().equals( statusCode ) ){
                incidentEntity.setExtinguishTime( servletService.getSystemTime() ); // （熄灭）基本扑灭时间

            }else  if(  INCIDENT_STATUS_FH.getCode().equals( statusCode ) ){
                incidentEntity.setFightEdnTime( servletService.getSystemTime() ); //  返回 作战结束时间

            }else  if(  INCIDENT_STATUS_ZF.getCode().equals( statusCode ) ){
                incidentEntity.setHalfwayReturnTime( servletService.getSystemTime() ); //中返时间

            }else  if(  INCIDENT_STATUS_FD.getCode().equals( statusCode ) ) {
                incidentEntity.setReturnTime(servletService.getSystemTime()); // 归队时间

            } else   if ( INCIDENT_STATUS_JA.getCode().equals(statusCode)) {
                incidentEntity.setIncidentEndTime(servletService.getSystemTime()); // 结案

            } else   if ( INCIDENT_STATUS_FS.getCode().equals(statusCode)) {
                incidentEntity.setPlaceFileIncidentTime( null ); // 归档复审取回

            } else   if ( INCIDENT_STATUS_GD.getCode().equals(statusCode) ) {
                incidentEntity.setPlaceFileIncidentTime(servletService.getSystemTime()); //归档

            }

            //保存更改
            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(incidentEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            //保存警情状态变动记录
            incidentStatusChangeService.saveIncidentStatusChange(incidentId, incidentEntity.getIncidentStatusCode(),
                    true , handleId  );


            UserInfo userInfo = userService.getUserInfo();

            //保存操作日志记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASESTATUS.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum() );
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom() );
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            String desc =  String.format("incident update status code incident:%s , status:%s ", incidentId , incidentEntity.getIncidentStatusCode()  ) ;
            auditLogSaveInputInfo.setDesc( desc  );
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASESTATUS.getName());

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateIncidentStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncidentStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_STATUS_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see IncidentService#updateIncidentNature(String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateIncidentNature(String incidentId, String incidentNature) {
        if (Strings.isBlank(incidentId) || Strings.isBlank(incidentNature)) {
            logService.infoLog(logger, "service", "updateIncidentNature", "incidentId or incidentNature is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateIncidentNature", "service is started...");
            Long logStart = System.currentTimeMillis();

            //获取案件详情
            IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);
            if (incidentEntity == null || Strings.isBlank(incidentEntity.getId())) {
                logService.infoLog(logger, "service", "updateIncidentNature", "incidentEntity is null or incidentId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }


            String old = incidentEntity.getIncidentNatureCode();
            //案件状态变更
            incidentEntity.setIncidentNatureCode(incidentNature);
            incidentEntity.setIncidentLabel(incidentNature);
            //保存更改
            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(incidentEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            UserInfo userInfo = userService.getUserInfo();


            Map<String, Map<String, String>> dicsMap =    dictionaryService.findDictionaryMap( dics ) ;

            //保存操作日志记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc("incident " + incidentId + "  nature from " +  dicsMap.get("AJXZ").get( old )    + " change to " +  dicsMap.get("AJXZ").get( incidentNature )   + "");
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());


            //消息通知立案机构、主管单位、以及所属大队
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = findIncidentParticipantOrganizationId(incidentId);
            orgSet.addAll(orgIds);
            IncidentBean incidentBean = findIncident(incidentId, false);
//            incidentBean.setIncidentNatureCode( incidentNature );
//
//            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
//            incidentBean.setIncidentNatureName(  dicsMap.get("AJXZ").get( incidentNature )   ) ;

            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_INCIDENT_NATURE.getCode(), incidentBean, orgSet);


            //其他
            Map<String, String > otherParams = new HashMap<>( ) ;
            pushDataService.pushUpdateIncident( incidentBean , otherParams ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateIncidentNature", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncidentNature", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_NATURE_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#writeBackAccidentToIncident(AccidentBean)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void writeBackAccidentToIncident(AccidentBean accidentBean) {
        if (accidentBean == null) {
            logService.infoLog(logger, "service", "writeBackAccidentToIncident", "accidentBean is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "writeBackAccidentToIncident", "service is started...");
            Long logStart = System.currentTimeMillis();


            IncidentEntity incidentEntity = accessor.getById(accidentBean.getIncidentId(), IncidentEntity.class);

            //警情变更前数据
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            IncidentBean oldIncidentBean = IncidentTransformUtil.transform(incidentEntity, dicsMap, organizationNameMap);
            if (Strings.isNotBlank(oldIncidentBean.getKeyUnitId())) {
                KeyUnitSimpleBean keyUnitSimple = keyUnitService.findKeyUnitSimple(oldIncidentBean.getKeyUnitId());
                if (keyUnitSimple != null) {
                    oldIncidentBean.setKeyUnitName(keyUnitSimple.getUnitName());
                }
            }

            if (incidentEntity != null) {

                incidentEntity.setInjuredCount(accidentBean.getInjuredNum());
                incidentEntity.setDeathCount(accidentBean.getDeathNum());
                incidentEntity.setCrimeAddress(accidentBean.getActualCrimeAddress());
                incidentEntity.setBuildingStructureCode(accidentBean.getBuildingStructure());
                incidentEntity.setSmogSituationCode(accidentBean.getSmogSituation());
                incidentEntity.setBurningFloor(accidentBean.getBurningFloor());
                incidentEntity.setBurningArea(accidentBean.getBurningArea());
                incidentEntity.setSupplementInfo(accidentBean.getAccidentDescribe());

                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
                Long incidentStart = System.currentTimeMillis();

                accessor.save(incidentEntity);

                Long incidentEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", incidentEnd - incidentStart));

                IncidentBean incidentBean = findIncident(accidentBean.getIncidentId(), false);
                incidentBean.setInjuredCount(accidentBean.getInjuredNum());
                incidentBean.setDeathCount(accidentBean.getDeathNum());
                incidentBean.setCrimeAddress(accidentBean.getActualCrimeAddress());
                incidentBean.setBuildingStructureCode(accidentBean.getBuildingStructure());
                incidentBean.setSmogSituationCode(accidentBean.getSmogSituation());
                incidentBean.setBurningFloor(accidentBean.getBurningFloor());
                incidentBean.setBurningArea(accidentBean.getBurningArea());
                incidentBean.setSupplementInfo(accidentBean.getAccidentDescribe());

                //关键数据变更记录 报错不抛出异常 不影响主流程
                UserInfo userInfo = userService.getUserInfo();
                incidentKeyDataChange(oldIncidentBean, incidentBean, userInfo);

                //保存操作日志记录
                AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                auditLogSaveInputInfo.setDesc("accident  save to incident " + accidentBean.getIncidentId() + " ");
                auditLogSaveInputInfo.setRemarks(null);
                auditLogService.saveAuditLog(auditLogSaveInputInfo);
                subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                        "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());

                //消息通知立案机构、主管单位、以及所属大队
                Set<String> orgSet = new HashSet<>();
                List<String> orgIds = findIncidentParticipantOrganizationId(accidentBean.getIncidentId());
                orgSet.addAll(orgIds);

                List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                orgSet.addAll(orgCodes);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.WRITE_BACK_ACCIDENT_TO_INCIDENT.getCode(), incidentBean, orgSet);

                //通知
                Map<String, String > otherParams = new HashMap<>() ;
                pushDataService.pushUpdateIncident( incidentBean ,otherParams  ) ;

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "writeBackAccidentToIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "writeBackAccidentToIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.WRITE_BACK_ACCIDENT_TO_INCIDENT_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findScopeIncident(String, String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<IncidentBean> findScopeIncident(String longitude, String latitude, String radius) {
        if (Strings.isBlank(longitude) || Strings.isBlank(latitude)) {
            logService.infoLog(logger, "service", "findScopeIncident", "longitude or latitude is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findScopeIncident", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<IncidentBean> res = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            //获取控制半径
            if (Strings.isBlank(radius)) {
                SystemConfigurationBean controlCircleConfig = systemConfigurationService.getSystemConfigByConfigType("controlCircle");
                radius = controlCircleConfig.getConfigValue();
            }
            //控制查询时间点
            SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
            int daynum = -30;
            if (systemConfigurationBean != null) {
                daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
            }
            Long currentTime = servletService.getSystemTime();
            Long endTime = currentTime;
            Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();


            //获取未结案状态代码
            List<String> incidentStatusCodes = new ArrayList<>();
            String vehicleOnDutyStatus = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinished").getConfigValue();
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] incidentEndStatus = vehicleOnDutyStatus.split(",");
                incidentStatusCodes = Arrays.asList(incidentEndStatus);
            }

            logService.infoLog(logger, "repository", "findScopeIncident", "repository is started...");
            Long start = System.currentTimeMillis();

            //查询控制半径范围内历史案件的id
            LocationRangeBean locationRangeBean = DistanceUtils.buildLocationRange(Double.parseDouble(longitude), Double.parseDouble(latitude), Integer.parseInt(radius));
            List<String> incidentIdList = acceptNativeQueryRepository.findScopeIncident(startTime, endTime,
                    locationRangeBean.getMinLng(), locationRangeBean.getMaxLng(), locationRangeBean.getMinLat(), locationRangeBean.getMaxLat(), incidentStatusCodes);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findScopeIncident", String.format("repository is finished,execute time is :%sms", end - start));

            String account = userService.getAccount();

            //查询案件详情
            if (incidentIdList != null && incidentIdList.size() > 0) {
                List<Object[]> queryResult = incidentRepository.findIncidentDetailed(null, null, incidentIdList, account);
                if (queryResult != null && queryResult.size() > 0) {
                    for (Object[] incidentObj : queryResult) {
                        IncidentBean bean = IncidentTransformUtil.transform(incidentObj, dicsMap, organizationNameMap , handlePersonNumType);
                        if (Strings.isNotBlank(bean.getKeyUnitId())) {
                            KeyUnitSimpleBean keyUnitSimple = keyUnitService.findKeyUnitSimple(bean.getKeyUnitId());
                            if (keyUnitSimple != null) {
                                bean.setKeyUnitName(keyUnitSimple.getUnitName());
                            }
                        }
                        Integer distanceRice = DistanceUtils.getDistanceRice(Double.parseDouble(longitude), Double.parseDouble(latitude),
                                Double.parseDouble(bean.getLongitude()), Double.parseDouble(bean.getLatitude()));
                        if (distanceRice <= Integer.parseInt(radius)) {
                            res.add(bean);
                        }
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findScopeIncident", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findScopeIncident", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#updateIncidentAddress(IncidentSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IncidentBean updateIncidentAddress(IncidentSaveInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "updateIncidentAddress", "IncidentUpdateInputInfo   is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateIncidentAddress", "service is started...");
            Long logStart = System.currentTimeMillis();

            IncidentBean res = null;

            IncidentEntity incidentEntity = accessor.getById(queryBean.getId(), IncidentEntity.class);

            UserInfo userInfo = userService.getUserInfo();

            if (null != incidentEntity) {
                //经纬度 变更比较
                String oldlon = incidentEntity.getLongitude() == null ? "" : incidentEntity.getLongitude();
                String oldlat = incidentEntity.getLatitude() == null ? "" : incidentEntity.getLatitude();
                String oldhei = incidentEntity.getHeight() == null ? "" : incidentEntity.getHeight();

                String changelon = queryBean.getLongitude() == null ? "" : queryBean.getLongitude();
                String changelat = queryBean.getLatitude() == null ? "" : queryBean.getLatitude();
                String changehei = queryBean.getHeight() == null ? "" : queryBean.getHeight();

                //案件关键数据  经度 纬度 高度 有变更保存 不影响主流程
                String tableName = JpaUtil.getTableName(IncidentEntity.class);
                Map<String, Field> fieldMap = JpaUtil.getColumnField(IncidentEntity.class);
                if (whetherChange(oldlon, changelon)
                        || whetherChange(oldlat, changelat)
                    /* || whetherChange(oldhei, changehei)*/
                ) {
                    incidentEntity.setLatitude(queryBean.getLatitude());
                    incidentEntity.setLongitude(queryBean.getLongitude());
                    incidentEntity.setHeight(queryBean.getHeight());

                    KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                    //警情变更通用属性设置
                    keyDataChangeRecord.setBusinessTableName(tableName);
                    keyDataChangeRecord.setBusinessDataId(incidentEntity.getId());
                    keyDataChangeRecord.setOrganizationId(incidentEntity.getSquadronOrganizationId());
                    keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                    keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                    keyDataChangeRecord.setBusinessField("{" + JpaUtil.getColumnName(
                            fieldMap.get("longitude")) + ","
                            + JpaUtil.getColumnName(fieldMap.get("latitude")) + ","
                            /*+ JpaUtil.getColumnName(fieldMap.get("height"))*/ + "}");
                    keyDataChangeRecord.setOriginalDate("{" + oldlon + "," + oldlat + /*"," + oldhei +*/ "}");
                    keyDataChangeRecord.setChangeDate("{" + changelon + "," + changelat + /*"," + changehei +*/ "}");
                    keyDataChangeRecord.setRemarks("警情经度纬度变更");

                    //关键数据变更
                    keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                    //保存文书
                    DocumentSaveInputInfo inputInfo = new DocumentSaveInputInfo();
                    inputInfo.setIncidentId(queryBean.getId());
                    inputInfo.setDateSourceId( queryBean.getId() );
                    inputInfo.setTitle( DOCUMENT_TYPE_AFDZBG.getName());
                    inputInfo.setContent("经度纬度由" + "{" + oldlon + "," + oldlat + /*"," + oldhei +*/ "}" +
                            "变更为" + "{" + changelon + "," + changelat + /*"," + changehei +*/ "}" + "");
                    inputInfo.setType( DOCUMENT_TYPE_AFDZBG.getCode());
                    inputInfo.setFeedbackPerson(userInfo.getPersonName());
                    inputInfo.setFeedbackOrganizationId(userInfo.getOrgId());
                    inputInfo.setTerminalId(null);
                    inputInfo.setRemarks(null);
                    documentService.saveDocument(inputInfo);


                    //保存操作日志记录
                    AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                    auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                    auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                    auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                    auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                    auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                    auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                    auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                    auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                    auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                    auditLogSaveInputInfo.setDesc("incident " + queryBean.getId() + " location from " + "{" + oldlon + "," + oldlat + /*"," + oldhei +*/ "}" +
                            " change to " + "{" + changelon + "," + changelat + /*"," + changehei +*/ "}" + "");
                    auditLogSaveInputInfo.setRemarks(null);
                    auditLogService.saveAuditLog(auditLogSaveInputInfo);
                    subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                            "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());

                }


                //经纬度 变更比较
                String oldAddress = incidentEntity.getCrimeAddress();

                String changeAddress = queryBean.getCrimeAddress();

                // 案件关键数据 地址变更
                if (Strings.isNotBlank(changeAddress) && whetherChange(oldAddress, changeAddress)) {

                    incidentEntity.setCrimeAddress(queryBean.getCrimeAddress());

                    KeyDataChangeRecordSaveInputInfo keyDataChangeRecord = new KeyDataChangeRecordSaveInputInfo();
                    //警情变更通用属性设置
                    keyDataChangeRecord.setBusinessTableName(tableName);
                    keyDataChangeRecord.setBusinessDataId(queryBean.getId());
                    keyDataChangeRecord.setOrganizationId(userInfo.getOrgId());
                    keyDataChangeRecord.setPersonNumber(userInfo.getAccount());
                    keyDataChangeRecord.setSeatNumber(userInfo.getAgentNum());
                    keyDataChangeRecord.setBusinessField(JpaUtil.getColumnName(fieldMap.get("crimeAddress")));
                    keyDataChangeRecord.setOriginalDate(oldAddress);
                    keyDataChangeRecord.setChangeDate(changeAddress);
                    keyDataChangeRecord.setRemarks("警情案发地址变更");

                    //关键数据变更
                    keyDataChangeRecordService.saveKeyDataChangeRecord(keyDataChangeRecord);

                    //保存文书
                    DocumentSaveInputInfo inputInfo = new DocumentSaveInputInfo();
                    inputInfo.setIncidentId(queryBean.getId());
                    inputInfo.setDateSourceId( queryBean.getId() );
                    inputInfo.setTitle( DOCUMENT_TYPE_AFDZBG.getName());
                    inputInfo.setContent("案发地址由" + oldAddress + "变更为" + changeAddress + "");
                    inputInfo.setType( DOCUMENT_TYPE_AFDZBG.getCode());
                    inputInfo.setFeedbackPerson(userInfo.getPersonName());
                    inputInfo.setFeedbackOrganizationId(userInfo.getOrgId());
                    inputInfo.setTerminalId(null);
                    inputInfo.setRemarks(null);
                    documentService.saveDocument(inputInfo);

                    //保存操作日志记录
                    AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                    auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                    auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getCode()));
                    auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                    auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
                    auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
                    auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                    auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                    auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
                    auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
                    auditLogSaveInputInfo.setDesc("incident " + queryBean.getId() + " crimeAddress from " + oldAddress + " change to " + changeAddress + "");
                    auditLogSaveInputInfo.setRemarks(null);
                    auditLogService.saveAuditLog(auditLogSaveInputInfo);
                    subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                            "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_UPDATECASE.getName());
                }

                //更新警情信息
                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", "repository is started...");
                Long startIncident = System.currentTimeMillis();

                accessor.save(incidentEntity);

                Long endIncident = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbIncidentEntity)", String.format("repository is finished,execute time is :%sms", endIncident - startIncident));


                res = findIncident(queryBean.getId(), false);
                res.setLatitude(queryBean.getLatitude());
                res.setLongitude(queryBean.getLongitude());
                res.setHeight(queryBean.getHeight());

                //消息通知立案机构、主管单位、以及所属大队
                Set<String> orgSet = new HashSet<>();
                List<String> orgIds = findIncidentParticipantOrganizationId(queryBean.getId());
                orgSet.addAll(orgIds);

                List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
                orgSet.addAll(orgCodes);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_INCIDENT.getCode(), res, orgSet);

                //推送消息
                Map<String, String > otherParams = new HashMap<>() ;
                pushDataService.pushUpdateIncident( res , otherParams ) ;

            } else {
                logService.infoLog(logger, "service", "getById(dbIncidentEntity)", "incident data by id  is null.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateIncidentAddress", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncidentAddress", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findIncidentParticipantOrganizationId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findIncidentParticipantOrganizationId(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentParticipantOrganizationId", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentParticipantOrganizationId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();

            Set<String> orgIds = new HashSet<>();

            //查询案件信息
            IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);
            if (incidentEntity != null) {
                orgIds.add(incidentEntity.getRegisterOrganizationId());
                orgIds.add(incidentEntity.getBrigadeOrganizationId());
                if( whetherInSquadron ){
                    orgIds.add(incidentEntity.getSquadronOrganizationId());
                }
            } else {
                return res;
            }
            //预警单位
            List<String> earlyWarningOrgIds = earlyWarningService.findEarlyWarningOrganizationIds(incidentId);
            if (earlyWarningOrgIds != null && earlyWarningOrgIds.size() > 0) {
                orgIds.addAll(earlyWarningOrgIds);
            }
            //指令出动单位
            List<String> handleOrgIds = handleService.findIncidentHandleOrganization(incidentId);
            if (handleOrgIds != null && handleOrgIds.size() > 0) {
                orgIds.addAll(handleOrgIds);
            }
            // 获得机构以及父机构id集合
            res = organizationService.findParentOrganizationIds(new ArrayList<>(orgIds));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentParticipantOrganizationId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            logService.infoLog(logger, "service", "findIncidentParticipantOrganizationId",  "incident participant Organization  is  : " + JSON.toJSONString( res) );

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentParticipantOrganizationId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_PARTICIPANT_ORGANIZATION_FAIL);
        }

    }

    /**
     * 对IncidentSaveInputInfo中需要前端手动输入的属性进行解码
     * URLDecoder
     */
    private void decodeIncidentSaveInputInfo(IncidentSaveInputInfo source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getAlarmPersonName())) {
                    source.setAlarmPersonName((URLDecoder.decode(source.getAlarmPersonName(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getAlarmPersonPhone())) {
                    source.setAlarmPersonPhone((URLDecoder.decode(source.getAlarmPersonPhone(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getAlarmAddress())) {
                    String alarmAddress = source.getAlarmAddress();
                    source.setAlarmAddress(URLDecoder.decode(alarmAddress, "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getCrimeAddress())) {
                    String crimeAddress = source.getCrimeAddress();
                    source.setCrimeAddress((URLDecoder.decode(crimeAddress, "utf-8")));
                }
                if (!StringUtils.isBlank(source.getIncidentDescribe())) {
                    String incidentDescribe = source.getIncidentDescribe();
                    source.setIncidentDescribe((URLDecoder.decode(incidentDescribe, "utf-8")));
                }
                if (!StringUtils.isBlank(source.getBurningFloor())) {
                    source.setBurningFloor((URLDecoder.decode(source.getBurningFloor(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getBurningArea())) {
                    source.setBurningArea((URLDecoder.decode(source.getBurningArea(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getTrappedCount())) {
                    source.setTrappedCount((URLDecoder.decode(source.getTrappedCount(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getInjuredCount())) {
                    source.setInjuredCount((URLDecoder.decode(source.getInjuredCount(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getDeathCount())) {
                    source.setDeathCount((URLDecoder.decode(source.getDeathCount(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getSecurityContactPerson())) {
                    source.setSecurityContactPerson((URLDecoder.decode(source.getSecurityContactPerson(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getContactPersonPhone())) {
                    source.setContactPersonPhone((URLDecoder.decode(source.getContactPersonPhone(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getLatitude())) {
                    source.setLatitude(URLDecoder.decode(source.getLatitude(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getLongitude())) {
                    source.setLongitude(URLDecoder.decode(source.getLongitude(), "UTF-8"));
                }
            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }


    /**
     * 对IncidentUpdateInputInfo中需要前端手动输入的属性进行解码
     * URLDecoder
     */
    private void decodeIncidentUpdateInputInfo(IncidentSaveInputInfo source) {
        if (source != null) {
            try {
                if (!StringUtils.isBlank(source.getAlarmPersonName())) {
                    source.setAlarmPersonName((URLDecoder.decode(source.getAlarmPersonName(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getAlarmPersonPhone())) {
                    source.setAlarmPersonPhone((URLDecoder.decode(source.getAlarmPersonPhone(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getAlarmAddress())) {
                    String alarmAddress = source.getAlarmAddress();
                    source.setAlarmAddress(URLDecoder.decode(alarmAddress, "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getCrimeAddress())) {
                    String crimeAddress = source.getCrimeAddress();
                    source.setCrimeAddress((URLDecoder.decode(crimeAddress, "utf-8")));
                }
                if (!StringUtils.isBlank(source.getIncidentDescribe())) {
                    String incidentDescribe = source.getIncidentDescribe();
                    source.setIncidentDescribe((URLDecoder.decode(incidentDescribe, "utf-8")));
                }
                if (!StringUtils.isBlank(source.getBurningFloor())) {
                    source.setBurningFloor((URLDecoder.decode(source.getBurningFloor(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getBurningArea())) {
                    source.setBurningArea((URLDecoder.decode(source.getBurningArea(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getTrappedCount())) {
                    source.setTrappedCount((URLDecoder.decode(source.getTrappedCount(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getInjuredCount())) {
                    source.setInjuredCount((URLDecoder.decode(source.getInjuredCount(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getDeathCount())) {
                    source.setDeathCount((URLDecoder.decode(source.getDeathCount(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getSecurityContactPerson())) {
                    source.setSecurityContactPerson((URLDecoder.decode(source.getSecurityContactPerson(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getContactPersonPhone())) {
                    source.setContactPersonPhone((URLDecoder.decode(source.getContactPersonPhone(), "utf-8")));
                }
                if (!StringUtils.isBlank(source.getLatitude())) {
                    source.setLatitude(URLDecoder.decode(source.getLatitude(), "UTF-8"));
                }
                if (!StringUtils.isBlank(source.getLongitude())) {
                    source.setLongitude(URLDecoder.decode(source.getLongitude(), "UTF-8"));
                }
            } catch (Exception e) {
                throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
            }
        }
    }

    /**
     * IncidentQueryInputInfo转码
     */
    private void decodeIncidentQueryInputInfo(IncidentQueryInputInfo source) {
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
    private void getWhetherImportSign(IncidentEntity incidentEntity){
        try{
            Integer whetherImportSign = 0;
            List<MajorIncidentRuleEntity> majorIncidentRuleList= majorIncidentRuleRepository.findAllMajor();
            if(majorIncidentRuleList != null && majorIncidentRuleList.size() > 0){
                for (int i = 0 ;i < majorIncidentRuleList.size();i++ ){
                    String ruleType = majorIncidentRuleList.get(i).getRuleType();
                    if (MajorIncidentRuleEnum.BKRS.getCode().equals(ruleType)) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(incidentEntity.getTrappedCount())) {
                            if (Integer.valueOf(incidentEntity.getTrappedCount()) >= Integer.valueOf(majorIncidentRuleList.get(i).getJudgmentValue())) {
                                whetherImportSign = 1;
                                if(majorIncidentRuleList.get(i).getPriority()==1){
                                    break;
                                }
                            }
//                            else {
//                                whetherImportSign = 0;
//                            }
                        }
                    }
                    if (MajorIncidentRuleEnum.RSMJ.getCode().equals(ruleType)) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(incidentEntity.getBurningArea())) {
                            if (Integer.valueOf(incidentEntity.getBurningArea()) >= Integer.valueOf(majorIncidentRuleList.get(i).getJudgmentValue())) {
                                whetherImportSign = 1;
                                if(majorIncidentRuleList.get(i).getPriority()==1){
                                    break;
                                }
                            }
//                            else {
//                                whetherImportSign = 0;
//                            }
                        }
                    }
                    if (MajorIncidentRuleEnum.AJDJ.getCode().equals(ruleType)) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(incidentEntity.getIncidentLevelCode()) && !incidentEntity.getIncidentLevelCode().equals("9")) {
                            if (Integer.valueOf(incidentEntity.getIncidentLevelCode()) >= Integer.valueOf(majorIncidentRuleList.get(i).getJudgmentValue())) {
                                whetherImportSign = 1;
                                if(majorIncidentRuleList.get(i).getPriority()==1){
                                    break;
                                }
                            }
//                            else {
//                                whetherImportSign = 0;
//                            }
                        }
                    }
                    if (MajorIncidentRuleEnum.SSRS.getCode().equals(ruleType)) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(incidentEntity.getInjuredCount())) {
                            if (Integer.valueOf(incidentEntity.getInjuredCount()) >= Integer.valueOf(majorIncidentRuleList.get(i).getJudgmentValue())) {
                                whetherImportSign = 1;
                                if(majorIncidentRuleList.get(i).getPriority()==1){
                                    break;
                                }
                            }
//                            else {
//                                whetherImportSign = 0;
//                            }
                        }
                    }
                    if (MajorIncidentRuleEnum.SWRS.getCode().equals(ruleType)) {
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(incidentEntity.getDeathCount())) {
                            if (Integer.valueOf(incidentEntity.getDeathCount()) >= Integer.valueOf(majorIncidentRuleList.get(i).getJudgmentValue())) {
                                whetherImportSign = 1;
                                if(majorIncidentRuleList.get(i).getPriority()==1){
                                    break;
                                }
                            }
//                            else {
//                                whetherImportSign = 0;
//                            }
                        }
                    }

                }
            }
            incidentEntity.setWhetherImportSign(whetherImportSign);
        }catch (Exception e){
            logService.erorLog(logger, "service", "getWhetherImportSign", "execution error", e);
        }

    }

    private void pushIncident(List<String> orgCodes,IncidentEntity incidentEntity,IncidentBean res,Set<String> orgIds) {
        try{
            if (orgCodes != null && orgCodes.size() > 0 && incidentEntity.getWhetherImportSign() == 1) {
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_MAJOR_INCIDENT.getCode(), res, orgIds);
            }
        }catch (Exception e){
            logService.erorLog(logger, "service", "pushIncident", "execution error", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see IncidentService#findIncidentsUnfinished(IncidentQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<IncidentBean> findZJIncidents(IncidentQueryInputInfo queryBean) {
        if (queryBean == null) {
            logService.infoLog(logger, "service", "findIncidentsUnfinished", "IncidentQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentsUnfinished", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeIncidentQueryInputInfo(queryBean);//URLDecoder Query转码

            PaginationBean<IncidentBean> res = new PaginationBean<>();
            List<IncidentBean> beans = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            String account = userService.getAccount();

            //如果没有开始时间 结束时间 设置默认时间段
            if (queryBean.getStartTime() == null && queryBean.getEndTime() == null) {
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinishedDay");
                int daynum = -30;
                if (systemConfigurationBean != null) {
                    daynum = (-Integer.parseInt(systemConfigurationBean.getConfigValue()));
                }
                Long currentTime = servletService.getSystemTime();
                Long endTime = currentTime;
                Long startTime = DateUtils.add(new Date(currentTime), DateUtils.FieldType.DAY, daynum).getTime();
                queryBean.setStartTime(startTime);
                queryBean.setEndTime(endTime);
            }


            String searchPath = null; //机构查询码 0根机构 1非根机构 默认根机构
            //判断是否只查询参与警情 中队  只查询参与警情 为true   默认 false ;
            Boolean whetherHandleIncident = false;
            if (1 == queryBean.getScopeType()) {
                OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                if (organization != null && organization.isValid()) {
                    searchPath = organization.getSearchPath();
                    String organizationNature = organization.getOrganizationNatureCode();
                    if ( !whetherInSquadron && Strings.isNotBlank(organizationNature) &&
                            (organizationNature.startsWith( ORGANIZATION_NATURE_HEAD_XJZD.getCode()))) {
                        whetherHandleIncident = true;
                    }

                }
            } else {
                searchPath = null;
            }

            //获得机构查询码
            List<String> searchPaths = new ArrayList<>();
            if (queryBean.getSquadronIds() != null && queryBean.getSquadronIds().size() > 0) {
                searchPaths = organizationService.findSearchPathByIds(queryBean.getSquadronIds());
            }
            List<String> incidentStatusCodes = new ArrayList<>();
            SystemConfigurationBean vehicleOnDutyStatusBean = systemConfigurationService.getSystemConfigByConfigType("incidentUnfinished");
            String vehicleOnDutyStatus = null;
            if (vehicleOnDutyStatusBean != null) {
                vehicleOnDutyStatus = vehicleOnDutyStatusBean.getConfigValue();

            }
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] incidentEndStatus = vehicleOnDutyStatus.split(",");
                incidentStatusCodes = Arrays.asList(incidentEndStatus);
            }

            logService.infoLog(logger, "repository", "findIncidentCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> incidentEntitys = incidentRepository.findIncidentCondition(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    queryBean.getWhetherPage(), queryBean.getPagination().getPage(), queryBean.getPagination().getSize(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentCondition", String.format("repository is finished,execute time is :%sms", end - start));


            if (incidentEntitys != null && incidentEntitys.size() > 0) {
                for (Object[] incidentObj : incidentEntitys) {

                    IncidentBean incidentBean = findIncident((String)incidentObj[0],true);

                    beans.add(incidentBean);
                }
            }

            logService.infoLog(logger, "repository", "findIncidentConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = incidentRepository.findIncidentConditionTotal(searchPath, whetherHandleIncident, searchPaths, queryBean.getKeyword(),
                    queryBean.getIncidentTypeCodes(), queryBean.getIncidentLevelCodes(), incidentStatusCodes,
                    queryBean.getWhetherKeyUnit(), queryBean.getWhetherImport(), queryBean.getStartTime(), queryBean.getEndTime(),
                    account, false, null, queryBean.getReceiverObjectId(), queryBean.getFilterIncidentIds());


            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            Pagination pagination = new Pagination();
            pagination.setPage(queryBean.getPagination().getPage());
            pagination.setSize(queryBean.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentsUnfinished", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentsUnfinished", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENTS_UNFINISHED_FAIL);
        }

    }
}
