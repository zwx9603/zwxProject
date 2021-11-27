package com.dscomm.iecs.accept.graphql;


import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.MajorIncidentRuleQueryInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.PlanWordQueryInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.*;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.service.DangerousChemicalsService;
import com.dscomm.iecs.basedata.service.LeaderService;
import com.dscomm.iecs.basedata.service.WaterService;
import com.dscomm.iecs.keydata.service.ServletService;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.service.rest.graphql.GraphQLAnnotationExecution;
import org.mx.service.rest.graphql.GraphQLFieldAnnotation;
import org.mx.service.rest.graphql.GraphQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 描述:图上指挥  模块graphql查询类执行器
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:47
 */
@Component("fireQueryExecution")
public class FireQueryExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(FireQueryExecution.class);
    private LogService logService;
    private CombatReadinessService combatReadinessService;
    private PlanWordService planWordService;
    private IncidentFireService incidentFireService;
    private IncidentImportantService incidentImportantService;
    private CommanderService commanderService;
    private RallyPointService rallyPointService;
    private EarlyWarningImportantService earlyWarningImportantService;
    private ActionSummaryService actionSummaryService;
    private ExpertService expertService;
    private HandleFireService handleFireService;
    private LeaderService leaderService;
    private ImportantReminderService importantReminderService;
    private StatisticsService statisticsService;
    private ConsensusInformationService consensusInformationService;
    private SecurityHintsService securityHintsService;
    private QueryUnitService queryUnitService;
    private DangerousChemicalsService dangerousChemicalsService;
    private DistrictChartService districtChartService;
    private CommonPhraseService commonPhraseService;
    private StatisticsFireService statisticsFireService;
    private ServletService servletService;
    private WaterService waterService;
    private CaseAutoUpdateWarnSerivce caseAutoUpdateWarnSerivce;
    private OrgRelationshipService orgRelationshipService;
    private MajorIncidentRuleService majorIncidentRuleService;//重大案件规则
    private LocationRecordService locationRecordService;

    @Override
    public String getTypeName() {
        return "Query";
    }

    @Autowired
    public FireQueryExecution(LogService logService, CombatReadinessService combatReadinessService,
                              PlanWordService planWordService, IncidentImportantService incidentImportantService,
                              RallyPointService rallyPointService,
                              EarlyWarningImportantService earlyWarningImportantService, ActionSummaryService actionSummaryService,
                              CommanderService commanderService,
                              ExpertService expertService, IncidentFireService incidentFireService, HandleFireService handleFireService,
                              LeaderService leaderService, ImportantReminderService importantReminderService, StatisticsService statisticsService,

                              ConsensusInformationService consensusInformationService, SecurityHintsService securityHintsService,
                              QueryUnitService queryUnitService, DangerousChemicalsService dangerousChemicalsService,
                              DistrictChartService districtChartService, CommonPhraseService commonPhraseService,
                              StatisticsFireService statisticsFireService,
                              ServletService servletService, WaterService waterService,
                              CaseAutoUpdateWarnSerivce caseAutoUpdateWarnSerivce,
                              OrgRelationshipService  orgRelationshipService,
                              MajorIncidentRuleService majorIncidentRuleService,LocationRecordService locationRecordService
    ) {
        this.logService = logService;
        this.combatReadinessService = combatReadinessService;
        this.planWordService = planWordService;
        this.incidentImportantService = incidentImportantService;
        this.rallyPointService = rallyPointService;
        this.commanderService = commanderService;
        this.earlyWarningImportantService = earlyWarningImportantService;
        this.actionSummaryService = actionSummaryService;


        this.expertService = expertService;

        this.incidentFireService = incidentFireService;
        this.handleFireService = handleFireService;
        this.leaderService = leaderService;
        this.importantReminderService = importantReminderService;

        this.statisticsService = statisticsService;
        this.consensusInformationService = consensusInformationService;
        this.securityHintsService = securityHintsService;
        this.queryUnitService = queryUnitService;
        this.dangerousChemicalsService = dangerousChemicalsService;
        this.districtChartService = districtChartService;

        this.commonPhraseService = commonPhraseService;
        this.statisticsFireService = statisticsFireService;

        this.servletService = servletService;
        this.waterService = waterService;
        this.caseAutoUpdateWarnSerivce = caseAutoUpdateWarnSerivce;
        this.orgRelationshipService = orgRelationshipService;
        this.majorIncidentRuleService = majorIncidentRuleService ;
        this.locationRecordService = locationRecordService;
    }


    /**
     * 文本预案列表
     *
     * @param environment 上下文环境变量
     * @return 文本预案
     * 2020/7/20
     */
    @GraphQLFieldAnnotation("findPlanWordCondition")
    public PaginationBean<PlanWordBean> findPlanWordCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlanWordAll", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> info = environment.getArgument("inputInfo");
        PlanWordQueryInputInfo queryBean = GraphQLUtils.parse(info, PlanWordQueryInputInfo.class);

        //执行业务
        PaginationBean<PlanWordBean> res = planWordService.findPlanWordCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanWordAll", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据id查询文本预案
     *
     * @param environment 上下文环境变量
     * @return 文本预案
     * 2020/7/20
     */
    @GraphQLFieldAnnotation("findPlanWord")
    public PlanWordBean findPlanWord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlanWord", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (null == id) {
            logService.infoLog(logger, "graphql", "findPlanWord", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行业务
        PlanWordBean res = planWordService.findPlanWord(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanWord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据id查询战备信息
     *
     * @param environment 上下文环境变量
     * @return 战备信息
     * 2020/7/17
     */
    @GraphQLFieldAnnotation("findCombatReadiness")
    public CombatReadinessBean findCombatReadiness(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCombatReadiness", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (null == id) {
            logService.infoLog(logger, "graphql", "findCombatReadiness", "id is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行业务
        CombatReadinessBean res = combatReadinessService.findCombatReadinessById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCombatReadiness", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据类型,时间查询战备信息
     *
     * @param environment 上下文环境变量
     * @return 战备信息
     * 2020/7/17
     */
    @GraphQLFieldAnnotation("findCombatReadinessByType")
    public List<CombatReadinessBean> findCombatReadinessByType(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCombatReadinessByType", "graphql is started...");
        Long start = System.currentTimeMillis();

        String type = environment.getArgument("type");
        Long showStartTime = environment.getArgument("showStartTime");
        Long showEndTime = environment.getArgument("showEndTime");

        //参数判断
        if (null == type || null == showStartTime || null == showEndTime) {
            logService.infoLog(logger, "graphql", "findCombatReadinessByType", "CombatReadinessInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行业务
        List<CombatReadinessBean> res = combatReadinessService.findCombatReadiness(type, showStartTime, showEndTime);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCombatReadinessByType", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 查询重要警情规则
     *
     * @param environment 上下文环境变量
     * @return 重要警情规则
     */

    @GraphQLFieldAnnotation("findIncidentImportantRuleCondition")
    public List<IncidentImportantRuleBean> findIncidentImportantRuleCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentImportantRuleCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断

        //执行业务
        List<IncidentImportantRuleBean> res = incidentImportantService.findIncidentImportantRuleCondition();

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentImportantRuleCondition", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根據案件id查詢现场指挥员列表
     *
     * @param environment 上下文环境变量
     * @return 现场指挥员列表
     * 2020/7/17
     */
    @GraphQLFieldAnnotation("findCommanderByIncidentId")
    public List<CommanderBean> findCommanderByIncidentId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCommanderByIncidentId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (null == incidentId) {
            logService.infoLog(logger, "graphql", "findCommanderByIncidentId", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行业务
        List<CommanderBean> res = commanderService.findCommander(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCommanderByIncidentId", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 警情集结点查询
     */
    @GraphQLFieldAnnotation("findRallyPoint")
    public List<RallyPointBean> findRallyPoint(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findRallyPoint", "graphql is started...");
        Long start = System.currentTimeMillis();
        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //查询
        List<RallyPointBean> res = rallyPointService.findRallyPoint(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findRallyPoint", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 警情id 集结力量id 集结项查询
     */
    @GraphQLFieldAnnotation("findRallyItem")
    public List<RallyItemBean> findRallyItem(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findRallyItem", "graphql is started...");
        Long start = System.currentTimeMillis();
        String incidentId = environment.getArgument("incidentId");
        String rallyPowerId = environment.getArgument("rallyPowerId");
        //参数判断
        if (Strings.isBlank(incidentId) || Strings.isBlank(rallyPowerId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //查询
        List<RallyItemBean> res = rallyPointService.findRallyItem(incidentId, rallyPowerId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findRallyItem", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 集结点详情
     */
    @GraphQLFieldAnnotation("findRallyPointDetail")
    public RallyPointBean findRallyPointDetail(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findRallyPointDetail", "graphql is started...");
        Long start = System.currentTimeMillis();
        String rallyPointId = environment.getArgument("rallyPointId");
        //参数判断
        if (Strings.isBlank(rallyPointId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //查询
        RallyPointBean res = rallyPointService.findRallyPointDetail(rallyPointId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findRallyPointDetail", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 查询集结项反馈
     */
    @GraphQLFieldAnnotation("findRallyItemFeedbackCondition")
    public List<RallyItemFeedbackBean> findRallyItemFeedbackCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findRallyItemFeedbackCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        RallyItemFeedbackQueryInputInfo queryBean = GraphQLUtils.parse(input, RallyItemFeedbackQueryInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //查询
        List<RallyItemFeedbackBean> res = rallyPointService.findRallyItemFeedbackCondition(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findRallyItemFeedbackCondition", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 统计各个等级重点单位数量信息
     *
     * @param environment 环境变量 但实际上并不需要传递参数
     * @return 各个等级重点单位数量信息
     */
    @GraphQLFieldAnnotation("countKeyUnitLevel")
    public List<KeyUnitLevelCountBean> countKeyUnitLevel(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "countKeyUnitLevel", "graphql is started...");
        Long start = System.currentTimeMillis();
        List<KeyUnitLevelCountBean> keyUnitLevelCountBeans = statisticsFireService.countKeyUnitLevel();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "countKeyUnitLevel", String.format("graphql is finished,execute time is :%sms", end - start));
        return keyUnitLevelCountBeans;
    }


    /**
     * 重大灾害预警列表
     */
    @GraphQLFieldAnnotation("findEarlyWarningImportant")
    public List<EarlyWarningImportantBean> findEarlyWarningImportant(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEarlyWarningImportant", "graphql is started...");
        Long start = System.currentTimeMillis();
        String currentTime = environment.getArgument("currentTime");
        //参数判断
        if (Strings.isBlank(currentTime)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        List<EarlyWarningImportantBean> res = earlyWarningImportantService.findEarlyWarningImportant(currentTime);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEarlyWarningImportant", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 重大灾害预警id详情
     */
    @GraphQLFieldAnnotation("findEarlyWarningImportantById")
    public EarlyWarningImportantBean findEarlyWarningImportantById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findEarlyWarningImportantById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //查询
        EarlyWarningImportantBean res = earlyWarningImportantService.findEarlyWarningImportantById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findEarlyWarningImportantById", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 战评列表
     */
    @GraphQLFieldAnnotation("findActionSummary")
    public List<ActionSummaryBean> findActionSummary(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findActionSummary", "graphql is started...");
        Long start = System.currentTimeMillis();
        String incidentId = environment.getArgument("incidentId");

        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //查询
        List<ActionSummaryBean> res = actionSummaryService.findActionSummary(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findActionSummary", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 根据id获得战评信息
     */
    @GraphQLFieldAnnotation("findActionSummaryBeanById")
    public ActionSummaryBean findActionSummaryBeanById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findActionSummaryBeanById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //查询
        ActionSummaryBean res = actionSummaryService.findActionSummaryBeanById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findActionSummaryBeanById", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 专家列表查询
     *
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findSmartExpertList")
    public List<ExpertBean> findSmartExpertList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSmartExpertList", "graphql is started...");
        Long start = System.currentTimeMillis();

        String expertField = environment.getArgument("expertField");
        String expertType = environment.getArgument("expertType");
        String expertName = environment.getArgument("expertName");

        //查询
        List<ExpertBean> expertBeans = expertService.findSmartExpertList(expertField, expertType, expertName);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSmartExpertList", String.format("graphql is finished,execute time is :%sms", end - start));

        return expertBeans;
    }

    /**
     * 专家智能检索
     */
    @GraphQLFieldAnnotation("findExpertList")
    public List<ExpertBean> findExpertList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findExpertList", "graphql is started...");
        Long start = System.currentTimeMillis();

        String keyword = environment.getArgument("keyword");
        //查询
        List<ExpertBean> expertBeans = expertService.findSmartExpertList(keyword);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findExpertList", String.format("graphql is finished,execute time is :%sms", end - start));

        return expertBeans;
    }

    /**
     * 专家详情
     *
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findExpertDetailById")
    public ExpertBean findExpertDetailById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findExpertDetailById", "graphql is started...");
        Long start = System.currentTimeMillis();
        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //查询
        ExpertBean bean = expertService.findExpertDetailById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findExpertDetailById", String.format("graphql is finished,execute time is :%sms", end - start));

        return bean;
    }

    /**
     * 综合展示全年、当月、昨日、当日等不同时段的属地警情统计数据，下属各地警情分布情况和数量变化趋势
     */
    @GraphQLFieldAnnotation("countIncidentAndTrend")
    public IncidentCountBean countIncidentAndTrend(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findExpertDetailById", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> input = environment.getArgument("inputInfo");
        IncidentStatisticsAndTrendInputInfo inputInfo = GraphQLUtils.parse(input, IncidentStatisticsAndTrendInputInfo.class);
        IncidentTrendQueryInputInfo incidentTrendQueryInputInfo = new IncidentTrendQueryInputInfo();
        IncidentStatisticsQueryInputInfo incidentStatisticsQueryInputInfo = new IncidentStatisticsQueryInputInfo();
        IncidentCountBean incidentCountBean = new IncidentCountBean();
        if (inputInfo != null) {

            incidentTrendQueryInputInfo.setScopeSquadronId(inputInfo.getScopeSquadronId());
            incidentStatisticsQueryInputInfo.setScopeSquadronId(inputInfo.getScopeSquadronId());

            incidentTrendQueryInputInfo.setScopeType(inputInfo.getScopeType());
            incidentStatisticsQueryInputInfo.setScopeType(inputInfo.getScopeType());

            incidentTrendQueryInputInfo.setStartTime(inputInfo.getStartTime());
            incidentStatisticsQueryInputInfo.setStartTime(inputInfo.getStartTime());

            incidentTrendQueryInputInfo.setEndTime(inputInfo.getEndTime());
            incidentStatisticsQueryInputInfo.setEndTime(inputInfo.getEndTime());

            incidentTrendQueryInputInfo.setTimeType(inputInfo.getTimeType());
            incidentTrendQueryInputInfo.setDistrictCode(inputInfo.getDistrictCode());

            incidentStatisticsQueryInputInfo.setSquadronIds(inputInfo.getSquadronIds());
            incidentStatisticsQueryInputInfo.setKeyword(inputInfo.getKeyword());
            incidentStatisticsQueryInputInfo.setIncidentTypeCodes(inputInfo.getIncidentTypeCodes());
            incidentStatisticsQueryInputInfo.setIncidentLevelCodes(inputInfo.getIncidentLevelCodes());
            incidentStatisticsQueryInputInfo.setIncidentStateCodes(inputInfo.getIncidentStateCodes());
            incidentStatisticsQueryInputInfo.setWhetherImport(inputInfo.getWhetherImport());
            incidentStatisticsQueryInputInfo.setWhetherKeyUnit(inputInfo.getWhetherKeyUnit());

        }
        List<TimeTrendBean> trendBeans = statisticsService.findIncidentTimeTrend(incidentTrendQueryInputInfo);
        DimensionAssembleStatisticsBean countBeans = statisticsService.findIncidentTypeStatistics(incidentStatisticsQueryInputInfo);
        incidentCountBean.setIncidentTrend(trendBeans);
        incidentCountBean.setIncidentCount(countBeans);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findExpertDetailById", String.format("graphql is finished,execute time is :%sms", end - start));
        return incidentCountBean;
    }

    /**
     * 出动力量统计:整体概述周边救援中队信息，调度车辆总数、参战人员总数
     */
    @GraphQLFieldAnnotation("findIncidentHandle")
    public DispatchAgencyBean findIncidentHandle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findExpertDetailById", "graphql is started...");
        Long start = System.currentTimeMillis();
        String incidentId = environment.getArgument("incidentId");
        DispatchAgencyBean dispatchAgencyBean = new DispatchAgencyBean();
        List<DispatchAgencyCarBean> dispatchAgencyCarBeans = handleFireService.findIncidentHandleVehicle(incidentId);
        List<OrganizationBean> organizationBeans = handleFireService.findIncidentHandleOrganizationBean(incidentId);
        Integer participant = handleFireService.findIncidentParticipant(incidentId);
        dispatchAgencyBean.setCars(dispatchAgencyCarBeans);
        dispatchAgencyBean.setOrganization(organizationBeans);
        dispatchAgencyBean.setPerson(participant);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findExpertDetailById", String.format("graphql is finished,execute time is :%sms", end - start));
        return dispatchAgencyBean;
    }


    /**
     * 获取领导查询
     *
     * @param environment type
     * @return 结果集
     */
    @GraphQLFieldAnnotation("LeaderList")
    public List<LeaderBean> LeaderList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "LeaderList", "graphql is started...");
        Long start = System.currentTimeMillis();
        String type = environment.getArgument("type");
        //查询
        List<LeaderBean> res = leaderService.LeaderList(type);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "LeaderList", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 要事详情
     */
    @GraphQLFieldAnnotation("importantReminderDetail")
    public ImportantReminderBean importantReminderDetail(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "importantReminderDetail", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        ImportantReminderBean importantReminderBean = importantReminderService.importantReminderDetail(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "importantReminderDetail", String.format("graphql is finished,execute time is :%sms", end - start));
        return importantReminderBean;
    }


    /**
     * 要事详情
     */
    @GraphQLFieldAnnotation("listImportantReminder")
    public PaginationBean<ImportantReminderBean> listImportantReminder(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "listImportantReminder", "graphql is started...");
        Long start = System.currentTimeMillis();

        Integer pageSize = environment.getArgument("pageSize");
        Integer pageNum = environment.getArgument("pageNum");
        Map<String, Object> input = environment.getArgument("inputInfo");
        ImportantReminderInputInfo inputInfo = GraphQLUtils.parse(input, ImportantReminderInputInfo.class);
        //参数判断
        PaginationBean<ImportantReminderBean> res = importantReminderService.listImportantReminder(inputInfo, pageSize, pageNum);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "listImportantReminder", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 警情总览
     */
    @GraphQLFieldAnnotation("findIncidentSummary")
    public IncidentSummaryBean findIncidentSummary(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentSummary", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //查询
        IncidentSummaryBean incidentSummaryBean = incidentFireService.findIncidentSummary(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentSummary", String.format("graphql is finished,execute time is :%sms", end - start));
        return incidentSummaryBean;

    }

    /**
     * 警情卷宗
     *
     * @param environment
     * @return
     */

    @GraphQLFieldAnnotation("findIncidentDossierDrawing")
    public IncidentDossierDrawingBean findIncidentDossierDrawing(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findIncidentDossierDrawingBean", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //查询
        IncidentDossierDrawingBean incidentDossierDrawingBean = incidentFireService.findIncidentDossierDrawing(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findIncidentDossierDrawingBean", String.format("graphql is finished,execute time is :%sms", end - start));
        return incidentDossierDrawingBean;

    }


    /**
     * 根据重点单位id查询文本预案
     *
     * @param environment 上下文环境变量
     * @return 文本预案
     * 2020/7/20
     */
    @GraphQLFieldAnnotation("findPlanWordByKeyUnitId")
    public List<PlanWordBean>
    findPlanWordByKeyUnitId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findPlanWordByKeyUnitId", "graphql is started...");
        Long start = System.currentTimeMillis();

        String keyUnitId = environment.getArgument("keyUnitId");
        //参数判断
        if (null == keyUnitId) {
            logService.infoLog(logger, "graphql", "findPlanWordByKeyUnitId", "keyUnitId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行业务
        List<PlanWordBean> res = planWordService.findPlanWordByKeyUnitId(keyUnitId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanWordByKeyUnitId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 舆情id详情
     */
    @GraphQLFieldAnnotation("findConsensusInformationById")
    public ConsensusInformationBean findConsensusInformationById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findConsensusInformationById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //查询
        ConsensusInformationBean res = consensusInformationService.findConsensusInformationById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findConsensusInformationById", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 根据当前时间查询舆情列表
     */
    @GraphQLFieldAnnotation("findConsensusInformation")
    public List<ConsensusInformationBean> findConsensusInformation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findConsensusInformation", "graphql is started...");
        Long start = System.currentTimeMillis();

        Long currentTime = servletService.getSystemTime();

        Long currentTimeStart = currentTime - 3600 * 1000 * 24;
        Long currentTimeEnd = currentTime;

        //查询
        List<ConsensusInformationBean> res = consensusInformationService.findConsensusInformation(currentTimeStart, currentTimeEnd);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findConsensusInformation", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 联勤联动单位查询
     *
     * @param environment type
     * @return 结果集
     */
    @GraphQLFieldAnnotation("getUnitList")
    public List<QueryUnitBean> getUnitList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "getUnitList", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        OrgUnitInfo info = GraphQLUtils.parse(input, OrgUnitInfo.class);
        //查询
        List<QueryUnitBean> res = queryUnitService.queryUnitList(info);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getUnitList", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 获取已经经过DI预处理的回溯内容
     *
     * @param environment 环境变量
     * @return 返回回溯结果
     */
    @GraphQLFieldAnnotation("incidentReplayReady")
    public IncidentReplayBean incidentReplayReady(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "incidentReplayReady", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        IncidentReplayBean res = incidentFireService.incidentReplayReady(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "incidentReplayReady", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    /**
     * 危化品列表
     */
    @GraphQLFieldAnnotation("getDangerousChemicalsList")
    public List<DangerousChemicalsBean> getDangerousChemicalsList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "getDangerousChemicalsList", "graphql is started...");
        Long start = System.currentTimeMillis();

        String keyword = environment.getArgument("keyword");
        List<DangerousChemicalsBean> res = dangerousChemicalsService.getDangerousChemicalsList(keyword);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getDangerousChemicalsList", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据 短语类型 案件类型获取 常用短语 信息
     */
    @GraphQLFieldAnnotation("findCommonPhrase")
    public List<CommonPhraseBean> findCommonPhrase(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "listSecurity", "graphql is started...");
        Long start = System.currentTimeMillis();

        String phraseType = environment.getArgument("phraseType");
        String incidentType = environment.getArgument("incidentType");
        //参数判断

        List<CommonPhraseBean> res = commonPhraseService.findCommonPhrase(phraseType, incidentType);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "listSecurity", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 根据警情id获得 特别警示下达历史记录
     */
    @GraphQLFieldAnnotation("findSecurityHistory")
    public List<SecurityBean> findSecurityHistory(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSecurityHistory", "graphql is started...");

        Long start = System.currentTimeMillis();
        String incidentId = environment.getArgument("incidentId");

        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<SecurityBean> res = securityHintsService.findSecurityHintsList(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSecurityHistory", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 根据警情id 接收对象id 获得 安全提示信息
     */
    @GraphQLFieldAnnotation("findSecurityReceiverList")
    public List<SecurityReceiverBean> findSecurityReceiverList(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findSecurityReceiverList", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        String receiverId = environment.getArgument("receiverId");
        if (Strings.isBlank(incidentId) || Strings.isBlank(receiverId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<SecurityReceiverBean> res = securityHintsService.findSecurityReceiverList(incidentId, receiverId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findSecurityReceiverList", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 支队值守力量
     */
    @GraphQLFieldAnnotation("dutyPowerDetachment")
    public DistrictChartOnDutyBean dutyPowerDetachment(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "dutyPowerDetachment", "graphql is started...");
        Long start = System.currentTimeMillis();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "dutyPowerDetachment", String.format("graphql is finished,execute time is :%sms", end - start));
        DistrictChartOnDutyBean res = districtChartService.findDistrictChartOnDutyBean();
        return res;
    }

    /**
     * 大队值守力量
     */
    @GraphQLFieldAnnotation("dutyPowerBrigade")
    public DistrictChartBean dutyPowerBrigade(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "dutyPowerBrigade", "graphql is started...");
        Long start = System.currentTimeMillis();
        String organizationId = environment.getArgument("organizationId");
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "dutyPowerBrigade", String.format("graphql is finished,execute time is :%sms", end - start));
        DistrictChartBean res = districtChartService.findDistrictChartBean(organizationId);
        return res;
    }


    /**
     * 消防栓基本信息
     */
    @GraphQLFieldAnnotation("findFireHydrantBeanById")
    public WaterBaseBean findFireHydrantBeanById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findFireHydrantBeanById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        WaterBaseBean res = waterService.findFireHydrantBeanById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findFireHydrantBeanById", String.format("graphql is finished,execute time is :%sms", end - start));


        return res;
    }

    /**
     * 消防水池详情
     */
    @GraphQLFieldAnnotation("findWaterPoolDetailsById")
    public WaterBaseBean findWaterPoolDetailsById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findWaterPoolDetailsById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        WaterBaseBean res = waterService.findWaterPoolDetailsById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findWaterPoolDetailsById", String.format("graphql is finished,execute time is :%sms", end - start));


        return res;
    }

    /**
     * 水鹤详情
     */
    @GraphQLFieldAnnotation("findWaterCraneDetailsById")
    public WaterBaseBean findWaterCraneDetailsById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findWaterCraneDetailsById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        WaterBaseBean res = waterService.findWaterCraneDetailsById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findWaterCraneDetailsById", String.format("graphql is finished,execute time is :%sms", end - start));


        return res;
    }

    /**
     * 天然水源的详情
     */
    @GraphQLFieldAnnotation("findNaturalWaterSourceDetailsById")
    public WaterBaseBean findNaturalWaterSourceDetailsById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findNaturalWaterSourceDetailsById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        WaterBaseBean res = waterService.findNaturalWaterSourceDetailsById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findNaturalWaterSourceDetailsById", String.format("graphql is finished,execute time is :%sms", end - start));


        return res;
    }

    /**
     * 取水码头的详情
     */
    @GraphQLFieldAnnotation("findWaterIntakeWharfDetailsById")
    public WaterBaseBean findWaterIntakeWharfDetailsById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findWaterIntakeWharfDetailsById", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        WaterBaseBean res = waterService.findWaterIntakeWharfDetailsById(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findWaterIntakeWharfDetailsById", String.format("graphql is finished,execute time is :%sms", end - start));


        return res;
    }

    /**
     * 通过ID查询 案件等级自动升级提醒
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findCaseAutoUpdateWarnById")
    public CaseAutoUpdateWarnBean findCaseAutoUpdateWarnById(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarnById", "graphql is started...");
        Long start = System.currentTimeMillis();
        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        CaseAutoUpdateWarnBean caseAutoUpdateWarnById = caseAutoUpdateWarnSerivce.findCaseAutoUpdateWarnById(id);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarnById", String.format("graphql is finished,execute time is :%sms", end - start));
        return caseAutoUpdateWarnById;
    }

    /**
     * 查询 案件等级自动升级提醒记录
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findCaseAutoUpdateWarn")
    public List<CaseAutoUpdateWarnBean> findCaseAutoUpdateWarn(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarn", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<CaseAutoUpdateWarnBean> res = caseAutoUpdateWarnSerivce.findCaseAutoUpdateWarn();
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarn", String.format("graphql is finished,execute time is :%sms", end - start));
        return res ;
    }

    /**
     * 查询 案件等级自动升级提醒记录
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("findCaseAutoUpdateWarnCondition")
    public List<CaseAutoUpdateWarnBean> findCaseAutoUpdateWarnCondition(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarnCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        CaseAutoUpdateWarnQueryInputInfo queryBean = GraphQLUtils.parse(input, CaseAutoUpdateWarnQueryInputInfo.class);
        //参数判断
        if( queryBean == null ){
            queryBean = new CaseAutoUpdateWarnQueryInputInfo() ;
        }

        List<CaseAutoUpdateWarnBean> res = caseAutoUpdateWarnSerivce.findCaseAutoUpdateWarnCondition( queryBean );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarnCondition", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }
    @GraphQLFieldAnnotation("findMajorIncidentRule")
    public PaginationBean<MajorIncidentRuleBean> findMajorIncidentRule(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "findMajorIncidentRule", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> info = environment.getArgument("inputInfo");
        MajorIncidentRuleQueryInputInfo queryBean = GraphQLUtils.parse(info, MajorIncidentRuleQueryInputInfo.class);
        //执行业务
        PaginationBean<MajorIncidentRuleBean> res = majorIncidentRuleService.findMajorIncidentRule(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findPlanWordAll", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据警情id查询定位记录
     * */
    @GraphQLFieldAnnotation("getLocationRecord")
    public List<LocationRecordBean> getLocationRecord(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarnCondition", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");
        if (StringUtils.isBlank(incidentId)){
            logService.infoLog(logger, "service", "getLocationRecord", "LocationRecordInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        List<LocationRecordBean> res = locationRecordService.getLocationRecord(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "findCaseAutoUpdateWarnCondition", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }




}
