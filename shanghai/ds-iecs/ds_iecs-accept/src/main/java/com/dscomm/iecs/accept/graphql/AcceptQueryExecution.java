package com.dscomm.iecs.accept.graphql;


import com.dscomm.iecs.accept.dal.po.ReleaseCallEntity;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.LedQueryInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.CommanderBean;
import com.dscomm.iecs.accept.graphql.firetypebean.InjuriesAndDeathsBean;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.accept.service.bean.*;
import com.dscomm.iecs.accept.service.bean.cad.IncidentDossierVO;
import com.dscomm.iecs.accept.service.outside.OutsideService;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.KeyUnitQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogParam;
import com.dscomm.iecs.keydata.graphql.typebean.AuditLogBean;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 描述:接警受理模块graphql查询类执行器
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:47
 */
@Component("acceptQueryExecution")
public class AcceptQueryExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(AcceptQueryExecution.class);
    private LogService logService;
    private Environment env;
    private TagLabelService tagLabelService;
    private StatisticsService statisticsService;
    private KeyUnitService keyUnitService;
    private CommonTipsService commonTipsService;
    private PlanDispatchService planDispatchService;
    private WeatherService weatherService;
    private IncidentService incidentService;
    private OnDutyService onDutyService;
    private HierarchicalDispatchService hierarchicalDispatchService;
    private IncidentStatusChangeService incidentStatusChangeService;
    private AttachmentService attachmentService;
    private EarlyWarningService earlyWarningService;
    private TelephoneService telephoneService;
    private HandleService handleService;
    private AcceptanceService acceptanceService;
    private ParticipantFeedbackService participantFeedbackService;
    private FireSafetyMonitoringService fireSafetyMonitoringService;
    private LocaleService localeService;
    private AccidentService accidentService;
    private DocumentService documentService;
    private ViolationService violationService;
    private SquadronFillInService squadronFillInService;
    private DrillPlanService drillPlanService;
    private UnTrafficAlarmService unTrafficAlarmService;
    private IncidentInfoService incidentInfoService;
    private BlacklistService blacklistService;
    private InstructionService instructionService;
    private NewsCircularService newsCircularService;

    private ServletService servletService;
    private OrganizationOtherService organizationOtherService;

    private InteractiveRecordService interactiveRecordService;
    private HandoverRecordSerivce handoverRecordSerivce;

    private VehicleService vehicleService;
    private LibsService libsService;

    private OrgRelationshipService orgRelationshipService;
    private OutsideService outsideService ;

    private CombatInformationService combatInformationService;
    private VehicleStatusChangeCheckService vehicleStatusChangeCheckService ;

    private HandleMiniatureStationService handleMiniatureStationService ;

    private LedService ledService;
    private StandardService standardService;
    private VehicleStatusChangeService vehicleStatusChangeService;
    private ReleaseCallService releaseCallService;
    private AuditLogService auditLogService;
    private IncidentGuidanceService incidentGuidanceService;
    private CtiCallService ctiCallService;
    private CombatUnitService combatUnitService;
    private SmartRecommendationService smartRecommendationService;

    @Override
    public String getTypeName() {
        return "Query";
    }

    @Autowired


    public AcceptQueryExecution(LogService logService, Environment env, StatisticsService statisticsService, TagLabelService tagLabelService,
                                KeyUnitService keyUnitService, CommonTipsService commonTipsService, WeatherService weatherService,
                                PlanDispatchService planDispatchService, IncidentService incidentService, OnDutyService onDutyService,
                                HierarchicalDispatchService hierarchicalDispatchService, IncidentStatusChangeService incidentStatusChangeService,
                                AttachmentService attachmentService, EarlyWarningService earlyWarningService, TelephoneService telephoneService,
                                HandleService handleService, AcceptanceService acceptanceService, ParticipantFeedbackService participantFeedbackService,
                                FireSafetyMonitoringService fireSafetyMonitoringService, LocaleService localeService, AccidentService accidentService,
                                DocumentService documentService, ViolationService violationService,
                                SquadronFillInService squadronFillInService, DrillPlanService drillPlanService, UnTrafficAlarmService unTrafficAlarmService,
                                IncidentInfoService incidentInfoService, BlacklistService blacklistService, InstructionService instructionService,
                                NewsCircularService newsCircularService, ServletService servletService, OrganizationOtherService organizationOtherService,
                                InteractiveRecordService interactiveRecordService, HandoverRecordSerivce handoverRecordSerivce,
                                VehicleService vehicleService, LibsService libsService, OrgRelationshipService orgRelationshipService,
                                OutsideService outsideService, CombatInformationService combatInformationService, VehicleStatusChangeCheckService vehicleStatusChangeCheckService,
                                HandleMiniatureStationService handleMiniatureStationService, LedService ledService,
                                StandardService standardService,
                                VehicleStatusChangeService vehicleStatusChangeService,
                                ReleaseCallService releaseCallService,
                                IncidentGuidanceService incidentGuidanceService,
                                CtiCallService ctiCallService,


                                AuditLogService auditLogService, CombatUnitService combatUnitService, SmartRecommendationService smartRecommendationService) {
        this.logService = logService;
        this.env = env ;
        this.statisticsService = statisticsService;
        this.keyUnitService = keyUnitService;
        this.tagLabelService = tagLabelService;
        this.commonTipsService = commonTipsService;
        this.planDispatchService = planDispatchService;
        this.weatherService = weatherService;
        this.incidentService = incidentService;
        this.onDutyService = onDutyService;
        this.earlyWarningService = earlyWarningService;
        this.hierarchicalDispatchService = hierarchicalDispatchService;
        this.incidentStatusChangeService = incidentStatusChangeService;
        this.attachmentService = attachmentService;
        this.telephoneService = telephoneService;
        this.handleService = handleService;
        this.acceptanceService = acceptanceService;
        this.participantFeedbackService = participantFeedbackService;
        this.fireSafetyMonitoringService = fireSafetyMonitoringService;
        this.localeService = localeService;
        this.accidentService = accidentService;
        this.documentService = documentService;
        this.violationService = violationService;
        this.squadronFillInService = squadronFillInService;
        this.drillPlanService = drillPlanService;
        this.incidentInfoService = incidentInfoService;
        this.blacklistService = blacklistService;
        this.unTrafficAlarmService = unTrafficAlarmService;
        this.instructionService = instructionService;
        this.newsCircularService = newsCircularService;
        this.servletService = servletService;
        this.organizationOtherService = organizationOtherService;
        this.interactiveRecordService = interactiveRecordService;
        this.handoverRecordSerivce = handoverRecordSerivce;

        this.vehicleService = vehicleService;
        this.libsService = libsService;

        this.orgRelationshipService = orgRelationshipService;
        this.outsideService = outsideService ;
        this.combatInformationService = combatInformationService;
        this.vehicleStatusChangeCheckService = vehicleStatusChangeCheckService ;

        this.handleMiniatureStationService = handleMiniatureStationService ;

        this.ledService=ledService;
        this.standardService = standardService ;

        this.vehicleStatusChangeService = vehicleStatusChangeService;
        this.releaseCallService = releaseCallService;
        this.auditLogService = auditLogService;
        this.incidentGuidanceService = incidentGuidanceService;
        this.ctiCallService = ctiCallService;
        this.combatUnitService = combatUnitService;
        this.smartRecommendationService = smartRecommendationService;
    }

    /**
     * graphql测试
     *
     * @param env 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("testQuery")
    public Boolean testQuery(DataFetchingEnvironment env) {


        return true;
    }


    /**
     * 全部警情状态为暂存警情信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentTemp")
    public PaginationBean<IncidentBrieflyBean> findTempIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentTemp", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentTemp(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentTemp", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.4 根据接收对象id、警情地址模糊匹配、警情类型、警情等级查询未结案警情信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentsReceiverObjectIdUnfinished")
    public PaginationBean<IncidentBrieflyBean> findIncidentsReceiverObjectIdUnfinished(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentsUnfinishedByReceiverObjectId", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getReceiverObjectId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsReceiverObjectIdUnfinished(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentsUnfinishedByReceiverObjectId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.4 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级查询未结案警情信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentsUnfinished")
    public PaginationBean<IncidentBrieflyBean> findIncidentsUnfinished(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentsUnfinished", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsUnfinished(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentsUnfinished", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.4 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级 过滤警情ids 查询未结案警情信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentsUnfinishedFilterIncident")
    public PaginationBean<IncidentBrieflyBean> findIncidentsUnfinishedFilterIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentsUnfinishedFilterIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        if (queryBean == null || queryBean.getFilterIncidentIds() == null || queryBean.getFilterIncidentIds().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsUnfinishedFilterIncident(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentsUnfinishedFilterIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.5 根据固定时间段 辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态查询警情信息(分页)  （ 昨天10点- 今日10点 ）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentFixedTimeCondition")
    public PaginationBean<IncidentBrieflyBean> findIncidentFixedTimeCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentFixedTimeCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        //参数判断
        if (queryBean == null) {
            queryBean = new IncidentQueryInputInfo();
        }
        //设置固定时间信息
        Long currentTime = servletService.getSystemTime();
        //设置今日10时间点
        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        cal.setTime(new Date(currentTime));
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Long startTime = cal.getTimeInMillis();
        //设置 昨日10 时间点
        Long endTime = DateUtils.add(new Date(startTime), DateUtils.FieldType.DAY, -1).getTime();
        queryBean.setStartTime(startTime);
        queryBean.setEndTime(endTime);

        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsCondition(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentFixedTimeCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.5 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段查询警情信息(分页)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentCondition")
    public PaginationBean<IncidentBrieflyBean> findIncidentCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentsCondition(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段查询关注警情信息(分页)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentAttentionCondition")
    public PaginationBean<IncidentBrieflyBean> findIncidentAttentionCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentAttentionCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentAttentionCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentAttentionCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 2.5 根据辖区ID集合、警情地址模糊匹配、警情类型、警情等级、警情状态、警情时间段查询重要警情信息(分页)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentImportantCondition")
    public PaginationBean<IncidentBrieflyBean> findIncidentImportantCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentImportantCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<IncidentBrieflyBean> res = incidentService.findIncidentImportantCondition(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentImportantCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据条件 查询标签( businessTable必填 1为电话类型)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findTagLabelCondition")
    public PaginationBean<TagLabelBean> findTagLabelCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findTagLabelCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        TagLabelQueryInputInfo queryBean = GraphQLUtils.parse(input, TagLabelQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getBusinessTable())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<TagLabelBean> res = tagLabelService.findTagLabelCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findTagLabelCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据单据ID(变更数据id)查询最新标签
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findTagLabel")
    public TagLabelBean findTagLabel(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findTagLabel", "graphql is started...");
        Long start = System.currentTimeMillis();

        String businessDataId = environment.getArgument("businessDataId");
        String businessTable = environment.getArgument("businessTable");

        //参数判断
        if (Strings.isBlank(businessDataId) || Strings.isBlank(businessTable)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        TagLabelBean res = tagLabelService.findTagLabelsBusinessDataId(businessDataId, businessTable);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findTagLabel", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 2.9 获取接警处警提示 ( 根据案件等级和案件类型获取接警处警提示信息 or 根据案件等级和案件类型获取处警提示信息  )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findCommonTips")
    public CommonTipsBean findCommonTips(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCommonTips", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentTypeCode = environment.getArgument("incidentTypeCode");
        String disposalObjectCode = environment.getArgument("disposalObjectCode");

        //参数判断
        if (Strings.isBlank(incidentTypeCode)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        CommonTipsBean res = commonTipsService.findCommonTips(incidentTypeCode, disposalObjectCode);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCommonTips", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 3.1 根据案件id获取案件基础信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncident")
    public IncidentBean findIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        IncidentBean res = incidentService.findIncident(incidentId, true);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 3.2 根据案件id获取案件报警信息 ( 受理记录 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAcceptance")
    public AcceptanceInformationBean findAcceptance(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAcceptance", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        AcceptanceInformationBean res = acceptanceService.findAcceptance(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAcceptance", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 3.3 根据案件id获取案件非话务报警信息 ( 非话务报警 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnTrafficAlarm")
    public List<UnTrafficAlarmBean> findUnTrafficAlarm(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findUnTrafficAlarm", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<UnTrafficAlarmBean> res = unTrafficAlarmService.findUnTrafficAlarm(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findUnTrafficAlarm", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 3.4 根据案件id获取案件接警信息(电话报警记录)
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findTelephone")
    public TelephoneInformationBean findTelephone(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findTelephone", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        TelephoneInformationBean res = telephoneService.findTelephone(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findTelephone", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 回呼，用于接警中/警情详情界面回呼报警人(无振铃)
     *
     * @param env 上下文环境
     * @return 呼入编号
     */
    @GraphQLFieldAnnotation("findTelephonePagination")
    public PaginationBean<TelephoneBean> findTelephonePagination(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "findTelephonePagination", "graphql is started...");
        Long logStart = System.currentTimeMillis();

        Map<String, Object> map = env.getArgument("inputInfo");
        TelephoneQueryInputInfo inputInfo = GraphQLUtils.parse(map, TelephoneQueryInputInfo.class);
        if( inputInfo == null ){
            inputInfo = new TelephoneQueryInputInfo() ;
        }

        PaginationBean<TelephoneBean> res  = telephoneService.findTelephonePagination(inputInfo);

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findTelephonePagination", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));

        return res ;
    }



    /**
     * 根据主案件id 获得 主案件信息  合并案件信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentMerge")
    public IncidentMergeBean findIncidentMerge(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentMerge", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        IncidentMergeBean res = incidentService.findIncidentMerge(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentMerge", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 3.5 根据案件id获取案件处警信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHandle")
    public List<HandleBean> findHandle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHandle", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<HandleBean> res = handleService.findHandle(incidentId, organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 3.5 根据案件id获取案件处警信息 根据单位分组
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHandleGroup")
    public List<HandleGroupBean> findHandleGroup(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHandle", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<HandleGroupBean> res = handleService.findHandleGroup(incidentId, organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 3.6 根据案件id获取案件状态信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentStatusChange")
    public List<IncidentStatusChangeBean> findIncidentStatusChange(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentStatusChange", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<IncidentStatusChangeBean> res = incidentStatusChangeService.findIncidentStatusChange(incidentId);


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentStatusChange", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据案件id获取案件附件
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAttachment")
    public List<AttachmentBean> findAttachment(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAttachment", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String relationId = environment.getArgument("relationId");
        //参数判断
        if (Strings.isBlank(incidentId) && Strings.isBlank(relationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<AttachmentBean> res = attachmentService.findAttachment(incidentId, relationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAttachment", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据预警条件 查询预警信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findEarlyWarning")
    public List<EarlyWarningBean> findEarlyWarning(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEarlyWarning", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        EarlyWarningQueryInputInfo queryBean = GraphQLUtils.parse(input, EarlyWarningQueryInputInfo.class);
        //参数判断
        if (null == queryBean || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<EarlyWarningBean> res = earlyWarningService.findEarlyWarning(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEarlyWarning", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 3.9 根据案件编号获取接警时已经下达过预警指令的中队列表（获取预警中队） （去重）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findEarlyWarningOrganization")
    public List<EarlyWarningBean> findEarlyWarningOrganization(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEarlyWarningOrganization", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<EarlyWarningBean> res = earlyWarningService.findEarlyWarningByIncidentId(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEarlyWarningOrganization", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.0 根据案件id查询 出动单位
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentHandleOrganization")
    public List<HandleOrganizationBean> findIncidentHandleOrganization(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentHandleOrganization", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<HandleOrganizationBean> res = handleService.findHandleOrganization(incidentId, organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentHandleOrganization", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.1 根据案件id查询 出动车辆
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentHandleVehicle")
    public List<HandleOrganizationVehicleBean> findIncidentHandleVehicle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentHandleVehicle", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

//        //执行逻辑处理
//        List<HandleOrganizationVehicleBean> res = handleService.findHandleOrganizationVehicle(incidentId, organizationId, false );
        //执行逻辑处理
        List<HandleOrganizationVehicleBean> res = handleService.findHandleOrganizationVehicleGroup(incidentId, organizationId, false );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentHandleVehicle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }





    /**
     * 4.1 根据案件id查询 出动装备
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentHandleEquipment")
    public List<HandleOrganizationEquipmentBean> findIncidentHandleEquipment(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentHandleEquipment", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<HandleOrganizationEquipmentBean> res = handleService.findHandleOrganizationEquipment(incidentId, organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentHandleEquipment", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.2 根据案件id查询出动/返队人员信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentParticipant")
    public List<ParticipantFeedbackBean> findIncidentParticipant(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentParticipant", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<ParticipantFeedbackBean> res = participantFeedbackService.findIncidentParticipant(incidentId, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentParticipant", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.3 根据案件id查询火场出入记录 ( 火场安全监控记录 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentFireSafetyMonitoring")
    public List<FireSafetyMonitoringBean> findIncidentFireSafetyMonitoring(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentFireSafetyMonitoring", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<FireSafetyMonitoringBean> res = fireSafetyMonitoringService.findIncidentFireSafetyMonitoring(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentFireSafetyMonitoring", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.4 根据警情id获得现场信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findLocale")
    public List<LocaleBean> findLocale(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findLocale", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<LocaleBean> res = localeService.findLocale(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findLocale", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 4.4 根据条件 获得现场信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findLocaleCondition")
    public List<LocaleBean> findLocaleCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findLocaleCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        LocaleQueryInputInfo queryBean = GraphQLUtils.parse(input, LocaleQueryInputInfo.class);
        //参数判断
        if (Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<LocaleBean> res = localeService.findLocaleCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findLocaleCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 4.5 查询结案反馈信息 （根据警情id获得事故信息）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAccident")
    public AccidentBean findAccident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAccident", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        AccidentBean res = accidentService.findAccident(incidentId);


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAccident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.6 查询文书信息 （根据警情id获得文书信息）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDocument")
    public List<DocumentBean> findDocument(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDocument", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        String type = environment.getArgument("type");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<DocumentBean> res = documentService.findDocument(incidentId, organizationId, type);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDocument", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 4.6 查询文书信息 （根据警情id获得文书信息）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDocumentOrganization")
    public List<OrganizationBean> findDocumentOrganization(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDocumentOrganization", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        String type = environment.getArgument("type");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<OrganizationBean> res = documentService.findDocumentOrganization(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDocumentOrganization", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 4.7 根据报警电话查询重点单位信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findKeyUnitAlarmPersonPhone")
    public KeyUnitBean findKeyUnitAlarmPersonPhone(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findKeyUnitAlarmPersonPhone", "graphql is started...");
        Long start = System.currentTimeMillis();

        String alarmPersonPhone = environment.getArgument("alarmPersonPhone");
        //参数判断
        if (Strings.isBlank(alarmPersonPhone)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        KeyUnitBean res = keyUnitService.findKeyUnitByPhone(alarmPersonPhone);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findKeyUnitAlarmPersonPhone", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 4.8 获得全部重点单位简单信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findKeyUnitAll")
    public List<KeyUnitSimpleBean> findKeyUnitAll(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findKeyUnitAll", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String organizationId = environment.getArgument("organizationId");
        //执行逻辑处理
        List<KeyUnitSimpleBean> res = keyUnitService.findKeyUnitAuthenticate( organizationId );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findKeyUnitAll", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.8 根据查询条件获得重点单位列表
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findKeyUnitCondition")
    public List<KeyUnitBean> findKeyUnitCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findKeyUnitCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        KeyUnitQueryInputInfo queryBean = GraphQLUtils.parse(input, KeyUnitQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<KeyUnitBean> res = keyUnitService.findKeyUnitCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findKeyUnitCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 4.9 根据预案编号查询重点单位详情
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findKeyUnitPlanId")
    public KeyUnitBean findKeyUnitPlanId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findKeyUnitPlanId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String planId = environment.getArgument("planId");
        //参数判断
        if (Strings.isBlank(planId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        KeyUnitBean res = keyUnitService.findKeyUnitByPlanId(planId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findKeyUnitPlanId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 5.0 根据重点单位编号查询重点单位详情
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findKeyUnit")
    public KeyUnitBean findKeyUnit(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findKeyUnit", "graphql is started...");
        Long start = System.currentTimeMillis();

        String keyUnitId = environment.getArgument("keyUnitId");
        //参数判断
        if (Strings.isBlank(keyUnitId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        KeyUnitBean res = keyUnitService.findKeyUnitById(keyUnitId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findKeyUnit", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.3 根据重点单位编号查询该重点单位所有预案 调派力量
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findPlanDispatchKeyUnitId")
    public List<PlanDispatchBean> findPlanDispatchKeyUnitId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlanDispatchKeyUnitId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String keyUnitId = environment.getArgument("keyUnitId");
        //参数判断
        if (Strings.isBlank(keyUnitId)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<PlanDispatchBean> res = planDispatchService.findPlanDispatchByKeyUnitId(keyUnitId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanDispatchKeyUnitId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据预案编号查询该 预案 调派力量
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findPlanDispatchByPlanId")
    public List<PlanDispatchBean> findPlanDispatchByPlanId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlanDispatchByPlanId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String planId = environment.getArgument("planId");
        //参数判断
        if (Strings.isBlank(planId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<PlanDispatchBean> res = planDispatchService.findPlanDispatchByPlanId(planId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanDispatchByPlanId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据重点单位id的集合 查询 预案调派车辆id ( 推荐车辆id 提示信息 补充信息  )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findPlanDispatchSupplementKeyUnitId")
    public List<PlanBean> findPlanDispatchByKeyUnitIds(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlanDispatchByKeyUnitIds", "graphql is started...");
        Long start = System.currentTimeMillis();


        String keyUnitId =  environment.getArgument("keyUnitId");
        //参数判断
        if ( Strings.isBlank( keyUnitId )) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<PlanBean> res = planDispatchService.findPlanDispatchSupplementKeyUnitId( keyUnitId );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanDispatchByKeyUnitIds", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.5 根据车辆类型和数量获取具体车辆 ( 等级调派力量 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHierarchicalDispatchVehicle")
    public List<HierarchicalDispatchBean> findHierarchicalDispatchVehicle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHierarchicalDispatchVehicle", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        HierarchicalDispatchQueryInputInfo queryBean = GraphQLUtils.parse(input, HierarchicalDispatchQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getScopeSquadronId()) || Strings.isBlank(queryBean.getIncidentTypeCode()) ||
                Strings.isBlank(queryBean.getSquadronOrganizationId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<HierarchicalDispatchBean> res = hierarchicalDispatchService.findHierarchicalDispatchVehicle(queryBean);


        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHierarchicalDispatchVehicle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.5 根据案件id获得案件参与单位值班信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentOrganizationOnDuty")
    public List<OnDutySummaryBean> findIncidentOrganizationOnDuty(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentOrganizationOnDuty", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OnDutySummaryBean> res = handleService.findIncidentOrganizationOnDuty(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentOrganizationOnDuty", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.6 等级调派方案查询
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHierarchicalDispatchCondition")
    public PaginationBean<HierarchicalDispatchBean> findHierarchicalDispatchCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHierarchicalDispatchCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        HierarchicalDispatchQueryInputInfo queryBean = GraphQLUtils.parse(input, HierarchicalDispatchQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<HierarchicalDispatchBean> res = hierarchicalDispatchService.findHierarchicalDispatchCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHierarchicalDispatchCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.7根据开始时间和结束时间查询 获得警情时间趋势图
     *
     * @param environment 上下文环境变量
     * @return 符合条件的警情类型及数量
     */
    @GraphQLFieldAnnotation("findIncidentTimeTrend")
    public List<TimeTrendBean> findIncidentTimeTrend(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentTimeTrend", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentTrendQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentTrendQueryInputInfo.class);
        //参数判断
        if (queryBean == null || queryBean.getStartTime() == null || queryBean.getEndTime() == null
                || Strings.isBlank(queryBean.getScopeSquadronId()) || Strings.isBlank(queryBean.getTimeType())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<TimeTrendBean> res = statisticsService.findIncidentTimeTrend(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentTimeTrend", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.8 根据辖区ID集合 警情地址模糊匹配 警情类型 警情等级 警情状态 警情时间段统计警情类型信息
     *
     * @param environment 上下文环境变量
     * @return 返回统计结果
     */
    @GraphQLFieldAnnotation("findIncidentTypeStatistics")
    public DimensionAssembleStatisticsBean findIncidentTypeStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentTypeStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentStatisticsQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentStatisticsQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getScopeSquadronId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        DimensionAssembleStatisticsBean res = statisticsService.findIncidentTypeStatistics(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentTypeStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 6.0 根据机构id获得 本机构以及子孙机构的车辆状态统计信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleStatusStatistics")
    public DimensionAssembleNestingStatisticsBean findVehicleStatusStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleStatusStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        VehicleStatisticsQueryInputInfo queryBean = GraphQLUtils.parse(input, VehicleStatisticsQueryInputInfo.class);
        //参数判断
        if (null == queryBean && Strings.isBlank(queryBean.getScopeSquadronId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        DimensionAssembleNestingStatisticsBean res = statisticsService.findVehicleStatusStatistics(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleStatusStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据车辆统计参数 获得车辆类型统计信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleTypeStatistics")
    public DimensionAssembleNestingStatisticsBean findVehicleTypeStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleTypeStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        VehicleStatisticsQueryInputInfo queryBean = GraphQLUtils.parse(input, VehicleStatisticsQueryInputInfo.class);
        //参数判断
        if (null == queryBean && Strings.isBlank(queryBean.getScopeSquadronId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        DimensionAssembleNestingStatisticsBean res = statisticsService.findVehicleTypeStatistics(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleTypeStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据车辆统计参数 统计机构车辆信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleOrganizationStatistics")
    public DimensionAssembleNestingStatisticsBean findVehicleOrganizationStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleOrganizationStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        VehicleStatisticsQueryInputInfo queryBean = GraphQLUtils.parse(input, VehicleStatisticsQueryInputInfo.class);
        //参数判断
        if (null == queryBean && Strings.isBlank(queryBean.getScopeSquadronId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        DimensionAssembleNestingStatisticsBean res = statisticsService.findVehicleOrganizationStatistics(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleOrganizationStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 6.6 根据时间段 坐席号 人员编号 获得违规操作记录
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSeatViolation")
    public PaginationBean<ViolationRecordBean> findSeatViolation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSeatViolation", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        MonitorQueryInputInfo queryBean = GraphQLUtils.parse(input, MonitorQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        PaginationBean<ViolationRecordBean> res = violationService.findViolationTimeSeatPersonNumber(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSeatViolation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 6.6 根据时间段 坐席号 人员编号 获得违规操作记录类型统计结果
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSeatViolationStatistics")
    public DimensionAssembleStatisticsBean findSeatViolationStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSeatViolationStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        MonitorQueryInputInfo queryBean = GraphQLUtils.parse(input, MonitorQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        DimensionAssembleStatisticsBean res = violationService.findSeatViolationStatistics(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSeatViolationStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据案件id 获得案件 出动力量统计结果
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHandlePowerStatistics")
    public IncidentPowerStatisticsBean findHandlePowerStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHandlePowerStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        IncidentPowerStatisticsBean res = statisticsService.findHandlePowerStatistics(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandlePowerStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据值班时间 机构id 获得本机构以及全部下级机构的值班信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAllOrganizationOnDuty")
    public List<OnDutySummaryOrganizationBean> findAllOrganizationOnDuty(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAllOrganizationOnDuty", "graphql is started...");
        Long start = System.currentTimeMillis();


        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<OnDutySummaryOrganizationBean> res = onDutyService.findAllOrganizationOnDuty(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllOrganizationOnDuty", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据值班时间 机构id 获得本机构值班信息
     * <p>
     * 若机构性质是大队，则一并获取其上级支队机构的值班信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationOnDuty")
    public List<OnDutySummaryOrganizationBean> findOrganizationOnDuty(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationOnDuty", "graphql is started...");
        Long start = System.currentTimeMillis();


        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<OnDutySummaryOrganizationBean> res = onDutyService.findOrganizationOnDuty(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationOnDuty", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据值班时间 机构id 获得下级机构值班信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findChildrenOrganizationOnDuty")
    public List<OnDutySummaryOrganizationBean> findChildrenOrganizationOnDuty(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findChildrenOrganizationOnDuty", "graphql is started...");
        Long start = System.currentTimeMillis();


        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<OnDutySummaryOrganizationBean> res = onDutyService.findChildrenOrganizationOnDuty(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findChildrenOrganizationOnDuty", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据值班时间 机构id 获得机构管辖中队值班信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSquadronOrganizationOnDuty")
    public List<OnDutySummaryOrganizationBean> findSquadronOrganizationOnDuty(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSquadronOrganizationOnDuty", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(organizationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<OnDutySummaryOrganizationBean> res = onDutyService.findSquadronOrganizationOnDuty(organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSquadronOrganizationOnDuty", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 7.3 根据  行政区编码 获得天气信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findWeather")
    public List<WeatherBean> findWeather(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findWeather", "graphql is started...");
        Long start = System.currentTimeMillis();

        String districtCode = environment.getArgument("districtCode");
        //参数判断，
//        if (Strings.isBlank(districtCode)) {
//            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
//        }

        //执行逻辑处理
        List<WeatherBean> res = weatherService.findWeather(districtCode);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findWeather", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 7.5 根据辖区ID集合 统计案件同环比（案件类型分类）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentTypeContrastStatistics")
    public IncidentTypeContrastStatisticsBean findIncidentTypeContrastStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentStatisticsIncidentType", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String scopeSquadronId = environment.getArgument("scopeSquadronId");
        Integer scopeType = environment.getArgument("scopeType");
        //参数判断
        if (Strings.isBlank(scopeSquadronId) || scopeType == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        IncidentTypeContrastStatisticsBean res = statisticsService.findIncidentTypeContrastStatistics(scopeSquadronId, scopeType);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentStatisticsIncidentType", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 5.9 根据辖区ID集合 统计处警量信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHandleStatistics")
    public DimensionAssembleStatisticsBean findHandleStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHandleStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        HandleStatisticsQueryInputInfo queryBean = GraphQLUtils.parse(input, HandleStatisticsQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getScopeSquadronId()) || queryBean.getStartTime() == null || queryBean.getEndTime() == null) {
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
        }
        DimensionAssembleStatisticsBean res = statisticsService.findHandleStatistics(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandleStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据机构id，查询时间段内子级机构警情分类统计
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findOrganizationDisasterStatistics")
    public List<OrganizationDisasterStatisticsBean> findOrganizationDisasterStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrganizationDisasterStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentStatisticsQueryInputInfo queryInputInfo = GraphQLUtils.parse(input, IncidentStatisticsQueryInputInfo.class);

        //参数判断
        if (queryInputInfo == null || queryInputInfo.getStartTime() == null || queryInputInfo.getEndTime() == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<OrganizationDisasterStatisticsBean> res = statisticsService.findOrganizationDisasterStatistics(queryInputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrganizationDisasterStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据时间段获得 辖区警情统计信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentDistrictStatistics")
    public IncidentDistrictStatisticsBean findIncidentDistrictStatistics(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentDistrictStatistics", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Long startTime = environment.getArgument("startTime");
        Long endTime = environment.getArgument("endTime");
        String districtCode = environment.getArgument("districtCode");
        //参数判断
        if (startTime == null || endTime == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        IncidentDistrictStatisticsBean res = statisticsService.findIncidentDistrictStatistics(startTime, endTime, districtCode);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentDistrictStatistics", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据案件id 获得中队填报信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSquadronFillIn")
    public List<SquadronFillInBean> findSquadronFillIn(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSquadronFillIn", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String organizationId = environment.getArgument("organizationId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<SquadronFillInBean> res = squadronFillInService.findSquadronFillIn(incidentId, organizationId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSquadronFillIn", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 条件查询 集合演练方案
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDrillPlanCondition")
    public PaginationBean<DrillPlanBean> findDrillPlanCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDrillPlanCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        DrillPlanQueryInputInfo queryBean = GraphQLUtils.parse(info, DrillPlanQueryInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        PaginationBean<DrillPlanBean> res = drillPlanService.findDrillPlanCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDrillPlanCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据id  获得集合演练方案
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findDrillPlanById")
    public DrillPlanBean findDrillPlanById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findDrillPlanById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");

        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        DrillPlanBean res = drillPlanService.findDrillPlanById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findDrillPlanById", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 获取案件周边范围内案件
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findScopeIncident")
    public List<IncidentBean> findScopeIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findScopeIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        String radius = environment.getArgument("radius");
        //参数判断
        if (Strings.isBlank(longitude) || Strings.isBlank(latitude)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        List<IncidentBean> res = incidentService.findScopeIncident(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findScopeIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 获取警情卷宗
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentDossier")
    public IncidentDossierBean findIncidentDossier(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentDossier", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        IncidentDossierBean res = incidentInfoService.findIncidentDossier(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentDossier", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据报警电话 获取固定电话信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findPhoneLibraryByPhoneNumber")
    public List<PhoneLibraryBean> findPhoneLibraryByPhoneNumber(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPhoneLibraryByPhoneNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        String phoneNumber = environment.getArgument("phoneNumber");
        //参数判断
        if (Strings.isBlank(phoneNumber)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<PhoneLibraryBean> res = telephoneService.findPhoneLibraryByPhoneNumber(phoneNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPhoneLibraryByPhoneNumber", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据报警电话 查询报警次数（报警记录个数）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findAlarmCount")
    public Long findAlarmCount(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAlarmCount", "graphql is started...");
        Long start = System.currentTimeMillis();

        String callingNumber = environment.getArgument("callingNumber");
        //参数判断
        if (Strings.isBlank(callingNumber)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        Long res = telephoneService.findAlarmCount(callingNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAlarmCount", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据条件查询黑名单信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findBlacklistPhoneCondition")
    public PaginationBean<BlacklistBean> findBlacklistPhoneCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findBlacklistPhoneNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        BlackListQueryInputInfo queryBean = GraphQLUtils.parse(input, BlackListQueryInputInfo.class);
        //参数判断
        //执行逻辑处理
        PaginationBean<BlacklistBean> res = blacklistService.findBlacklistPhoneCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findBlacklistPhoneNumber", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据电话号码查询 黑名单
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findBlacklistByPhoneNumber")
    public BlacklistBean findBlacklistByPhoneNumber(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findBlacklistByPhoneNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String phoneNumber = environment.getArgument("phoneNumber");
        if (Strings.isBlank(phoneNumber)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        BlacklistBean res = blacklistService.findBlacklistByPhoneNumber(phoneNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findBlacklistByPhoneNumber", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据案件经纬度 查询范围内重点单位id列表
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findKeyUnitIncidentRange")
    public List<String> findKeyUnitIncidentRange(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findKeyUnitIncidentRange", "graphql is started...");
        Long start = System.currentTimeMillis();

        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        //参数判断
        if (Strings.isBlank(longitude) || Strings.isBlank(latitude)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<String> res = keyUnitService.findKeyUnitIncidentRange(longitude, latitude);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findKeyUnitIncidentRange", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据案件id查询 警情出动车辆信息（包含车载装备、出动人员）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findIncidentVehicle")
    public List<HandleOrganizationVehicleBean> findIncidentVehicle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentVehicle", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<HandleOrganizationVehicleBean> res = handleService.findHandleOrganizationVehicle(incidentId, null, false );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentVehicle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据案件id必填 查询 指令单信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findInstructionCondition")
    public List<InstructionBean> findInstructionCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findInstructionCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        InstructionQueryInputInfo queryBean = GraphQLUtils.parse(input, InstructionQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<InstructionBean> res = instructionService.findInstructionCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findInstructionCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据案件id必填 其他条件 查询指令记录信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findInstructionRecordCondition")
    public List<InstructionRecordBean> findInstructionRecordCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findInstructionRecordCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        InstructionRecordQueryInputInfo queryBean = GraphQLUtils.parse(input, InstructionRecordQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<InstructionRecordBean> res = instructionService.findInstructionRecordCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findInstructionRecordCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据条件 查询消息通知
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findNewsCircularCondition")
    public PaginationBean<NewsCircularBean> findNewsCircularCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findNewsCircularCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        NewsCircularQueryInputInfo queryBean = GraphQLUtils.parse(input, NewsCircularQueryInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<NewsCircularBean> res = newsCircularService.findNewsCircularCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findNewsCircularCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 查询接收的消息通知
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findNewsCircularReceiverCondition")
    public PaginationBean<NewsCircularReceiverBean> findNewsCircularReceiverCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findNewsCircularReceiverCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        NewsCircularQueryInputInfo queryBean = GraphQLUtils.parse(input, NewsCircularQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getReceivingObjectId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<NewsCircularReceiverBean> res = newsCircularService.findNewsCircularReceiverCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findNewsCircularReceiverCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    /**
     * 根据坐标查范围内联动单位
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findUnitEmergencyRange")
    public List<UnitEmergencyBean> findUnitEmergencyRange(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findUnitEmergencyRange", "graphql is started...");
        Long start = System.currentTimeMillis();

        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        String radius = environment.getArgument("radius");
        //参数判断
        if (Strings.isBlank(longitude) || Strings.isBlank(latitude)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        List<UnitEmergencyBean> res = organizationOtherService.findUnitEmergencyRange(longitude, latitude, radius);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findUnitEmergencyRange", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据电话号编号查询交互记录
     *
     * @param environment 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("findAllInteractiveRecordByPhoneNum")
    public List<InteractiveRecordBean> findAllInteractiveRecordByPhoneNum(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAllInteractiveRecordByPhoneNum", "graphql is started...");
        Long start = System.currentTimeMillis();

        String phoneNum = environment.getArgument("phoneNum");
        //参数判断
        if (Strings.isBlank(phoneNum)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<InteractiveRecordBean> res = interactiveRecordService.findAllInteractiveRecordByPhoneNum(phoneNum);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllInteractiveRecordByPhoneNum", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据 报警编号 查询交互记录
     *
     * @param environment 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("findAllInteractiveRecordByAlarmNumber")
    public List<InteractiveRecordBean> findAllInteractiveRecordByAlarmNumber(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAllInteractiveRecordByAlarmNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        String alarmNumber = environment.getArgument("alarmNumber");
        //参数判断
        if (Strings.isBlank(alarmNumber)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<InteractiveRecordBean> res = interactiveRecordService.findAllInteractiveRecordByAlarmNumber(alarmNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllInteractiveRecordByAlarmNumber", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据 警情id 查询交互记录
     *
     * @param environment 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("findAllInteractiveRecordByIncidentId")
    public List<InteractiveRecordBean> findAllInteractiveRecordByIncidentId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAllInteractiveRecordByIncidentId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<InteractiveRecordBean> res = interactiveRecordService.findAllInteractiveRecordByIncidentId(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllInteractiveRecordByIncidentId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 查询交接班日志
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHandoverRecordList")
    public PaginationBean<HandoverRecordBean> findHandoverRecordList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHandoverRecordList", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        HandoverRecordQueryInputInfo queryBean = GraphQLUtils.parse(input, HandoverRecordQueryInputInfo.class);
        //参数判断
        if (queryBean == null || (queryBean.getWhetherPage() && (queryBean.getPagination() == null || queryBean.getPagination().getSize() < 0 || queryBean.getPagination().getPage() < 1))) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        PaginationBean<HandoverRecordBean> res = handoverRecordSerivce.findHandoverRecordList(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandoverRecordList", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 查询交接班日志
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleDeviceLocation")
    public List<VehicleDeviceLocationBean> findVehicleDeviceLocation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleDeviceLocation", "graphql is started...");
        Long start = System.currentTimeMillis();

        String vehicleIdStr = environment.getArgument("vehicleIds");
        //参数判断
        if (Strings.isBlank(vehicleIdStr)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        String[] vehicleIdsj = vehicleIdStr.split(",");
        List<String> vehicleIds = Arrays.asList(vehicleIdsj);
        //执行逻辑处理
        List<VehicleDeviceLocationBean> res = new ArrayList<>();
        List<EquipmentVehicleBean> vehicleBeanList = vehicleService.findVehicleCacheList(vehicleIds);
        //车辆查询事所在位置
        if (vehicleBeanList != null && vehicleBeanList.size() > 0) {
            for (EquipmentVehicleBean vehicleBean : vehicleBeanList) {
                String locationNumber = vehicleBean.getLocationNumber();
                VehicleDeviceLocationBean bean = new VehicleDeviceLocationBean();
                bean.setId(vehicleBean.getId());
                bean.setVehicleId(vehicleBean.getId());
                bean.setOrganizationId(vehicleBean.getOrganizationId());
                bean.setOrganizationName(vehicleBean.getOrganizationName());
                bean.setVehicleTypeCode(vehicleBean.getVehicleTypeCode());
                bean.setVehicleTypeName(vehicleBean.getVehicleTypeName());
                bean.setVehicleStatusCode(vehicleBean.getVehicleStatusCode());
                bean.setVehicleStatusName(vehicleBean.getVehicleStatusName());
                bean.setVehicleCode(vehicleBean.getVehicleCode());
                bean.setVehicleName(vehicleBean.getVehicleName());
                bean.setVehicleShortName(vehicleBean.getVehicleShortName());
                bean.setVehicleLevelCode(vehicleBean.getVehicleLevelCode());
                bean.setVehicleLevelName(vehicleBean.getVehicleLevelName());
                bean.setVehicleNumber(vehicleBean.getVehicleNumber());
                bean.setGpsNumber(vehicleBean.getGpsNumber());
                bean.setLocationNumber(vehicleBean.getLocationNumber());
                bean.setRadioCallSign(vehicleBean.getRadioCallSign());
                if (Strings.isNotBlank(locationNumber)) {
                    DeviceLocationBean deviceLocation = libsService.findCurrentDeviceLocation(locationNumber);
                    bean.setDeviceLocationBean(deviceLocation);
                }
                res.add(bean);
            }
        }

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleDeviceLocation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 消防转警 关系列表
     */

    @GraphQLFieldAnnotation("findOrgRelationshipList")
    public List<OrgRelationshipBean> findOrgRelationshipList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrgRelationshipList", "graphql is started...");
        Long start = System.currentTimeMillis();
        String fireDeptId = environment.getArgument("fireDeptId");
        //参数判断
        if (Strings.isBlank(fireDeptId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<OrgRelationshipBean> res = orgRelationshipService.findOrgRelationshipList(fireDeptId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrgRelationshipList", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 消防转警 关系列表
     */

    @GraphQLFieldAnnotation("findOrgRelationship")
    public OrgRelationshipBean  findOrgRelationship(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findOrgRelationshipList", "graphql is started...");
        Long start = System.currentTimeMillis();
        String fireDeptId = environment.getArgument("fireDeptId");
        String transferType = environment.getArgument("transferType");
        //参数判断
        if (Strings.isBlank(fireDeptId) || Strings.isBlank( transferType) ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        OrgRelationshipBean res = orgRelationshipService.findOrgRelationship(fireDeptId , transferType );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findOrgRelationshipList", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }



    /**
     * 根据警情ID 查询关联公安警情卷宗
     *
     * @param environment
     */
    @GraphQLFieldAnnotation("findIncidentDossier110")
    public IncidentDossierVO findIncidentDossier110(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentDossier110", "graphql is started...");
        Long start = System.currentTimeMillis();
        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理

        IncidentDossierVO incidentDossier110 = outsideService.findIncidentDossier110(incidentId);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentDossier110", String.format("graphql is finished,execute time is :%sms", end - start));
        return incidentDossier110;

    }


    /**
     * 根据警情ID 查询安全提示
     *
     * @param environment
     */
    @GraphQLFieldAnnotation("findCommonTipsById")
    public CommonTipsBean findCommonTipsById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCommonTipsById", "graphql is started...");
        Long start = System.currentTimeMillis();
        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        CommonTipsBean res = commonTipsService.findById(id);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCommonTipsById", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     *  查询所有接處警提示
     *
     * @param
     */
    @GraphQLFieldAnnotation("findAllCommonTips")
    public List<CommonTipsBean> findAllCommonTips(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findAllCommonTips", "graphql is started...");
        Long start = System.currentTimeMillis();

        //执行逻辑处理
        List<CommonTipsBean> safetyWarnServiceAll = commonTipsService.findAll();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAllCommonTips", String.format("graphql is finished,execute time is :%sms", end - start));
        return safetyWarnServiceAll;
    }

    /**
     * 查询作战信息卡列表
     * */
    @GraphQLFieldAnnotation("listCombatInformation")
    public PaginationBean<CombatInformationBean> listCombatInformation(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "listCombatInformation", "graphql is started...");
        Long start = System.currentTimeMillis();

        String organizationId = environment.getArgument("organizationId");
        Boolean whetherPage = environment.getArgument("whetherPage");
        Map<String, Object> page = environment.getArgument("pagination");
        PaginationInputInfo paginationInputInfo = GraphQLUtils.parse(page, PaginationInputInfo.class);

        if (StringUtils.isBlank(organizationId)){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        PaginationBean<CombatInformationBean> res = combatInformationService.listCombatInformation(organizationId,whetherPage,paginationInputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "listCombatInformation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 查询作战信息卡详情
     * */
    @GraphQLFieldAnnotation("detailCombatInformation")
    public CombatInformationBean detailCombatInformation(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "detailCombatInformation", "graphql is started...");
        Long start = System.currentTimeMillis();


        String id = environment.getArgument("id");
        if (StringUtils.isBlank(id)){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        CombatInformationBean res = combatInformationService.detailCombatInformation(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "detailCombatInformation", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }

    /**
     *  查询车辆状态审批记录
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehicleStatusChangeCheck")
    public List<VehicleStatusChangeCheckBean> findVehicleStatusChangeCheck(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleStatusChangeCheck", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<VehicleStatusChangeCheckBean> res = vehicleStatusChangeCheckService.findVehicleStatusChangeCheck( );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleStatusChangeCheck", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     *  根据警情id（必填） 调派单id 获得 微站调派信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findHandleMiniatureStation")
    public  List<HandleMiniatureStationBean>   findHandleMiniatureStation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findHandleMiniatureStation", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String handleId = environment.getArgument("handleId");
        //参数判断
        if (  Strings.isBlank( incidentId ) ) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        List<HandleMiniatureStationBean>    res = handleMiniatureStationService.findHandleMiniatureStation( incidentId , handleId );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandleMiniatureStation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据案件id 调派微站记录id 获得微站反馈信息
     *
     * @return 返回结果
     */

    @GraphQLFieldAnnotation("findHandleMiniatureStationFeedback")
    public  List<HandleMiniatureStationFeedbackBean>  findHandleMiniatureStationFeedback( DataFetchingEnvironment environment ) {
        logService.infoLog(logger, "graphql", "findHandleMiniatureStationFeedback", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String handleMiniatureStationId = environment.getArgument("handleMiniatureStationId");
        if (  Strings.isBlank( incidentId ) || Strings.isBlank( handleMiniatureStationId  ) )  {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<HandleMiniatureStationFeedbackBean> res = handleMiniatureStationService.findHandleMiniatureStationFeedback( incidentId , handleMiniatureStationId );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findHandleMiniatureStationFeedback", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }



    /**
     * 根据案件id查询 现场指挥员信息( 车辆 ) 杭州
     *
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findVehiclePersonsByIncidentId")
    public  List<CommanderBean>  findVehiclePersonsByIncidentId(DataFetchingEnvironment environment ) {
        logService.infoLog(logger, "graphql", "findVehiclePersons", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        Boolean whetherNotCommander = environment.getArgument("whetherNotCommander");
        if (  Strings.isBlank( incidentId )   )  {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<CommanderBean> res = incidentInfoService.findVehiclePersonsByIncidentId( incidentId  , whetherNotCommander  );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehiclePersons", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 查询LED列表
     *
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findLedByOrgId")
    public PaginationBean<LedBean> findLedByOrgId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findLedByOrgId", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        LedQueryInputInfo queryBean = GraphQLUtils.parse(input, LedQueryInputInfo.class);

        //执行逻辑处理
        PaginationBean<LedBean> res = ledService.findLedList(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findLedByOrgId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }
    /**
     * 获取用语规范
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findStandard")
    public List<StandardBean> findStandard(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findTestXHAll", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断

        Map<String, Object> input = environment.getArgument("inputInfo");
        StandardQueryInputInfo queryBean = GraphQLUtils.parse(input, StandardQueryInputInfo.class);
        //执行逻辑处理
        List<StandardBean> res = standardService.findStandard(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findTestXHAll", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 伤亡人员统计
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findInjuriesAndDeaths")
    public List<InjuriesAndDeathsBean> findInjuriesAndDeaths(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentTimeTrend", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentTrendQueryInputInfo queryBean = GraphQLUtils.parse(input, IncidentTrendQueryInputInfo.class);
        //参数判断
        if (queryBean == null || queryBean.getStartTime() == null || queryBean.getEndTime() == null
                || Strings.isBlank(queryBean.getTimeType())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<InjuriesAndDeathsBean> res = statisticsService.findInjuriesAndDeaths(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentTimeTrend", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 车辆状态变更记录（非战斗状态）
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findVehicleStateChangeRecord")
    public PaginationBean<VehicleStatusChangeBean> findVehicleStateChangeRecord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findVehicleStateChangeRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        VehicleStateChangeQueryInputInfo queryBean = GraphQLUtils.parse(input, VehicleStateChangeQueryInputInfo.class);

        //执行逻辑处理
        PaginationBean<VehicleStatusChangeBean> res = vehicleStatusChangeService.findVehicleStateChangeRecord(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findVehicleStateChangeRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 获取排队早释记录
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findReleaseCalls")
    public List<ReleaseCallEntity> findReleaseCalls(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findReleaseCalls", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        ReleaseCallQueryInputInfo queryBean = GraphQLUtils.parse(input, ReleaseCallQueryInputInfo.class);

        //执行逻辑处理
        List<ReleaseCallEntity> res = releaseCallService.findReleaseCalls(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findReleaseCalls", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    /**
     * 根据条件查询审计日志(黑名单时操作类型为 350 360 570)
     * @param environment 上下文环境变量
     * @return 返回结果集
     */
    @GraphQLFieldAnnotation("findAuditLog")
    public PaginationBean<AuditLogBean> findAuditLog(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "findAuditLog", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("condition");
        Map<String, Object> paginationParam = environment.getArgument("pagination");
        AuditLogParam queryBean = GraphQLUtils.parse(input, AuditLogParam.class);
        Pagination pagination = GraphQLUtils.parse(paginationParam, Pagination.class);

        //执行逻辑处理
        PaginationBean<AuditLogBean> res = auditLogService.findAuditLogByCondition(queryBean, pagination);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findAuditLog", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 根据案件id获取文书和附件信息
     * @return
     */
    @GraphQLFieldAnnotation("findIncidentGuidanceInfo")
    public List<DocumentBean> findIncidentGuidanceInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentGuidanceInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        List<DocumentBean> documentBeans = incidentGuidanceService.findIncidentGuidanceInfo(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentGuidanceInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return documentBeans;
    }


    /**
     * 获取呼入记录
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findCtiCallCondition")
    public PaginationBean<CtiCallBean>  findCtiCallCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCtiCallCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        CtiCallQueryInputInfo queryBean = GraphQLUtils.parse(input, CtiCallQueryInputInfo.class);

        //执行逻辑处理
        PaginationBean<CtiCallBean> res = ctiCallService.findCtiCallCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCtiCallCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    @GraphQLFieldAnnotation("findCombatUnit")
    public PaginationBean<CombatUnitBean> findCombatUnit(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "findCombatUnit", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        CombatUnitQueryInputInfo queryBean = GraphQLUtils.parse(input, CombatUnitQueryInputInfo.class);

        //执行逻辑处理
        PaginationBean<CombatUnitBean> res = combatUnitService.findCombatUnitList(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCombatUnit", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 统计车辆调派分布情况（以案件处置对象、等级为维度统计车辆类型分布情况）
     * @param environment 参数
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("statisticsVehicleDispatch")
    public List<StatisticsBean> statisticsVehicleDispatch(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "findCombatUnit", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentVehicleStatisticsInputInfo queryBean = GraphQLUtils.parse(input, IncidentVehicleStatisticsInputInfo.class);

        //执行逻辑处理
        List<StatisticsBean> result = statisticsService.staticsIncidentVehicles(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCombatUnit", String.format("graphql is finished,execute time is :%sms", end - start));
        return result;
    }

    /**
     * 查询排队列表
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findQueueList")
    public List<ReleaseCallBean> findQueueList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findQueueList", "graphql is started...");
        Long start = System.currentTimeMillis();
        //执行逻辑处理
        List<ReleaseCallBean> list = releaseCallService.findQueueList();

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findQueueList", String.format("graphql is finished,execute time is :%sms", end - start));
        return list;
    }

    /**
     * 智能推荐案件等级
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("recommendIncidentLevel")
    public GradeJudgeResultBean recommendHandleVehicle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "recommendIncidentLevel", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> input =environment.getArgument("inputInfo");
        GradeJudgeQueryParamBean queryBean=GraphQLUtils.parse(input,GradeJudgeQueryParamBean.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        GradeJudgeResultBean result = smartRecommendationService.transformGradeJudge(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "recommendIncidentLevel", String.format("graphql is finished,execute time is :%sms", end - start));
        return result;
    }

    /**
     * 智能推荐案件调派力量
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("recommendHandleVehicleDetail")
    public SmartRecommendVehicleBean recommendHandleVehicleDetail(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "recommendHandleVehicleDetail", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> input =environment.getArgument("inputInfo");
        PowerTransferQueryParam queryBean=GraphQLUtils.parse(input,PowerTransferQueryParam.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        SmartRecommendVehicleBean result = smartRecommendationService.smartRecommendVehicle(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "recommendHandleVehicleDetail", String.format("graphql is finished,execute time is :%sms", end - start));
        return result;
    }

    /**
     * 查询智能调用
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("findSmartRecommendationRecord")
    public PaginationBean<FindSmartRecommendationBean> findSmartRecommendationRecord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSmartRecommendationRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        FindSmartRecommendationInfo queryBean = GraphQLUtils.parse(input, FindSmartRecommendationInfo.class);
        //参数判断
        if (queryBean == null) {
            logService.infoLog(logger, "graphql", "findSmartRecommendationRecord", "queryBean is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);

        }

        //执行逻辑处理
        PaginationBean<FindSmartRecommendationBean> beanList =smartRecommendationService.findSmartRecommendationRecord(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSmartRecommendationRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return beanList;
    }

}
