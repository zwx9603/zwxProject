package com.dscomm.iecs.accept.graphql;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.*;
import com.dscomm.iecs.accept.graphql.firetypebean.*;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.base.service.log.LogService;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.util.Strings;
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
@Component("fireMutationExecution")
public class FireMutationExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(FireMutationExecution.class);
    private LogService logService;


    private CombatReadinessService combatReadinessService;
    private PlanWordService planWordService;
    private IncidentImportantService incidentImportantService;
    private RallyPointService rallyPointService ;
    private IncidentCircleService incidentCircleService;
    private CommandService commandService;
    private CommanderService commanderService;
    private EarlyWarningImportantService earlyWarningImportantService;
    private ActionSummaryService actionSummaryService ;
    private ImportantReminderService importantReminderService;
    private SecurityHintsService securityHintsService;
    private CommonPhraseService commonPhraseService ;
    private CaseAutoUpdateWarnSerivce caseAutoUpdateWarnSerivce;

    private OrgRelationshipService orgRelationshipService;
    private MajorIncidentRuleService majorIncidentRuleService;//重大案件规则
    private LocationRecordService locationRecordService;
    private DocumentService documentService;

    @Override
    public String getTypeName() {
        return "Mutation";
    }

    @Autowired
    public FireMutationExecution(LogService logService,CombatReadinessService combatReadinessService,
                                 PlanWordService planWordService,IncidentImportantService incidentImportantService,
                                 RallyPointService rallyPointService,
                                 IncidentCircleService incidentCircleService,CommandService commandService,CommanderService commanderService,
                                 EarlyWarningImportantService earlyWarningImportantService,ActionSummaryService actionSummaryService,
                                 ImportantReminderService importantReminderService,
                                 SecurityHintsService securityHintsService , CommonPhraseService commonPhraseService,
                                 CaseAutoUpdateWarnSerivce caseAutoUpdateWarnSerivce,
                                 OrgRelationshipService orgRelationshipService,
                                 DocumentService documentService,
                                 MajorIncidentRuleService majorIncidentRuleService,LocationRecordService locationRecordService

    ) {
        this.logService = logService;
        this.combatReadinessService=combatReadinessService;
        this.planWordService=planWordService;
        this.incidentImportantService = incidentImportantService;
        this.incidentCircleService=incidentCircleService;
        this.commandService=commandService;
        this.commanderService=commanderService;
        this.rallyPointService = rallyPointService;
        this.earlyWarningImportantService=earlyWarningImportantService;
        this.actionSummaryService=actionSummaryService;
        this.importantReminderService = importantReminderService;
        this.securityHintsService = securityHintsService;
        this.commonPhraseService = commonPhraseService ;
        this.caseAutoUpdateWarnSerivce = caseAutoUpdateWarnSerivce;
        this.orgRelationshipService = orgRelationshipService;
        this.majorIncidentRuleService = majorIncidentRuleService ;
        this.locationRecordService = locationRecordService;
        this.documentService = documentService;
    }


    /**
     * 保存文本预案，附件
     * @param environment 上下文环境变量
     * @return  文本预案
     * 2020/7/20
     */
    @GraphQLFieldAnnotation("savePlanWord")
    public PlanWordBean savePlanWord(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "savePlanWord", "graphql is started...");
        Long start = System.currentTimeMillis();


        Map<String, Object> info = environment.getArgument("inputInfo");
        PlanWordSaveInputInfo queryBean = GraphQLUtils.parse(info, PlanWordSaveInputInfo.class);

        if(null==queryBean){
            logService.infoLog(logger, "graphql", "savePlanWord", "PlanWordInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //保存
        PlanWordBean res = planWordService.savePlanWord(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "savePlanWord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 删除文本预案
     * @param environment 上下文环境变量
     * @return  文本预案
     * 2020/7/20
     */
    @GraphQLFieldAnnotation("removePlanWord")
    public Boolean removePlanWord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removePlanWord", "graphql is started...");
        Long start = System.currentTimeMillis();
        String id = environment.getArgument("id");
        //参数判断
        if(null==id){
            logService.infoLog(logger, "graphql", "removePlanWord", "PlanWordInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res = planWordService.removePlanWord(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removePlanWord", "graphql is started...");

        return res;
    }




    /**
     * 根据id删除战备信息
     * @param environment 上下文环境变量
     * @return  战备信息
     * 2020/7/17
     */
    @GraphQLFieldAnnotation("removeCombatReadiness")
    public Boolean removeCombatReadiness(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removeCombatReadiness", "graphql is started...");
        Long start = System.currentTimeMillis();
        String id = environment.getArgument("id");
        //参数判断
        if(null==id){
            logService.infoLog(logger, "graphql", "removeCombatReadiness", "CombatReadinessInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res = combatReadinessService.removeCombatReadiness(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeCombatReadiness", "graphql is started...");

        return res;
    }


    /**
     * 保存修改战备信息
     * @param environment 上下文环境变量
     * @return  战备信息
     * 2020/7/17
     */
    @GraphQLFieldAnnotation("saveCombatReadiness")
    public CombatReadinessBean saveCombatReadiness(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveCombatReadiness", "graphql is started...");
        Long start = System.currentTimeMillis();


        Map<String, Object> info = environment.getArgument("inputInfo");
        CombatReadinessInputInfo queryBean = GraphQLUtils.parse(info, CombatReadinessInputInfo.class);

        if(null==queryBean){
            logService.infoLog(logger, "graphql", "saveCombatReadiness", "CombatReadinessInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //保存
        CombatReadinessBean res = combatReadinessService.saveCombatReadiness(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCombatReadiness", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 重要警情规则信息修改
     * @param environment 上下文环境变量
     * @return 重要警情规则
     */
    @GraphQLFieldAnnotation("saveIncidentImportant")
    public IncidentImportantRuleBean saveIncidentImportant(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveCombatReadiness", "graphql is started...");
        Long start = System.currentTimeMillis();


        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentImportantRuleSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentImportantRuleSaveInputInfo.class);

        if(null==queryBean){
            logService.infoLog(logger, "graphql", "saveIncidentImportant", "CombatReadinessInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //保存
        IncidentImportantRuleBean res = incidentImportantService.saveIncidentImportant(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentImportant", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 删除重要警情规则
     * @param environment 上下文环境变量
     * @return
     */
    @GraphQLFieldAnnotation("removeIncidentImportant")
    public Boolean removeIncidentImportant(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeIncidentImportant", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentImportantRuleId = environment.getArgument("incidentImportantRuleId");

        //参数判断
        if(Strings.isBlank(incidentImportantRuleId)){
            logService.infoLog(logger, "graphql", "removeIncidentImportant", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        Boolean res = incidentImportantService.removeIncidentImportantRule(incidentImportantRuleId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeIncidentImportant", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 启用重要警情规则
     * @param environment 上下文环境变量
     * @return
     */
    @GraphQLFieldAnnotation("enabledIncidentImportant")
    public Boolean enabledIncidentImportant(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "enabledIncidentImportant", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentImportantRuleId = environment.getArgument("incidentImportantRuleId");

        //参数判断
        if(Strings.isBlank(incidentImportantRuleId)){
            logService.infoLog(logger, "graphql", "enabledIncidentImportant", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        Boolean res = incidentImportantService.enabledIncidentImportantRule(incidentImportantRuleId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "enabledIncidentImportant", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     *  判断警情是否为重要警情
     * @param environment 上下文环境变量
     * @return
     */
    @GraphQLFieldAnnotation("judgeIncidentImportant")
    public Boolean judgeIncidentImportant(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "judgeIncidentImportant", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = environment.getArgument("incidentId");

        //参数判断
        if(Strings.isBlank(incidentId)){
            logService.infoLog(logger, "graphql", "enabledIncidentImportant", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        Boolean res = incidentImportantService.judgeIncidentImportant(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "judgeIncidentImportant", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



    /**
     * 救援圈设定
     * @param environment 上下文环境变量
     * @return IncidentCircleBean
     */
    @GraphQLFieldAnnotation("saveIncidentCircle")
    public IncidentCircleBean saveIncidentCircle(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveIncidentCircle", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentCircleSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentCircleSaveInputInfo.class);

        //参数判断
        if(null==queryBean  || Strings.isBlank( queryBean.getIncidentId() ) ){
            logService.infoLog(logger, "graphql", "saveIncidentCircle", "IncidentCircleSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        IncidentCircleBean res = incidentCircleService.saveIncidentCircle(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentCircle", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }



    /**
     * 指挥设定
     * @param environment 上下文环境变量
     * @return CommandBean
     */
    @GraphQLFieldAnnotation("saveCommand")
    public CommandBean saveCommand(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveCommand", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> info = environment.getArgument("inputInfo");
        CommandSaveInputInfo  queryBean = GraphQLUtils.parse(info, CommandSaveInputInfo.class);

        //参数判断
        if(null==queryBean || Strings.isBlank( queryBean.getIncidentId() )){
            logService.infoLog(logger, "graphql", "saveCommand", "CommandSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        CommandBean res = commandService.saveCommand(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCommand", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 现场指挥员保存
     * @param environment 上下文环境变量
     * @return CommandBean
     */
    @GraphQLFieldAnnotation("saveCommander")
    public CommanderBean saveCommander(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveCommander", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> info = environment.getArgument("inputInfo");
        CommanderSaveInputInfo  queryBean = GraphQLUtils.parse(info, CommanderSaveInputInfo.class);

        //参数判断
        if(null==queryBean || Strings.isBlank( queryBean.getIncidentId() )){
            logService.infoLog(logger, "graphql", "saveCommander", "CommanderSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        CommanderBean res = commanderService.saveCommander(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCommander", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 现场指挥员保存集合
     * @param environment 上下文环境变量
     * @return CommandBean
     */
    @GraphQLFieldAnnotation("saveCommanderList")
    public Boolean saveCommanderList(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveCommanderList", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> info = environment.getArgument("inputInfo");
        CommanderListSaveInputInfo  queryBean = GraphQLUtils.parse(info, CommanderListSaveInputInfo.class);

        //参数判断
        if(null==queryBean  ){
            logService.infoLog(logger, "graphql", "saveCommanderList", "CommanderSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        Boolean res = commanderService.saveCommanderList(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCommanderList", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 现场指挥员删除
     * @param environment 上下文环境变量
     * @return CommandBean
     */
    @GraphQLFieldAnnotation("removeCommander")
    public Boolean removeCommander(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeCommander", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");

        //保存
        Boolean res = commanderService.removeCommander(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeCommander", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     *  保存集结点集结项
     *
     */
    @GraphQLFieldAnnotation("saveOrUpdateRallyPoint")
    public List<RallyPointBean> saveOrUpdateRallyPoint(DataFetchingEnvironment environment  ) {
        logService.infoLog(logger, "graphql", "saveOrUpdateRallyPoint", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        RallyPointSaveTargetInputInfo queryBean = GraphQLUtils.parse(input, RallyPointSaveTargetInputInfo.class);
        //参数判断
        if ( queryBean == null || Strings.isBlank( queryBean.getIncidentId() )
                || Strings.isBlank( queryBean.getCommandId() ) || queryBean.getRallyPoints() == null
                || queryBean.getRallyPoints().size() <= 0 ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL)  ;
        }
        //保存
        List<RallyPointBean> res = rallyPointService.saveOrUpdateRallyPoint( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveOrUpdateRallyPoint", String.format("graphql is finished,execute time is :%sms", end - start));
        return res ;
    }

    /**
     * 移除集结点
     *
     */
    @GraphQLFieldAnnotation("removeRallyPoint")
    public  Boolean  removeRallyPoint ( DataFetchingEnvironment environment  ) {
        logService.infoLog(logger, "graphql", "removeRallyPoint", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        RallyPointDeleteTargetInputInfo queryBean = GraphQLUtils.parse(input, RallyPointDeleteTargetInputInfo.class);
        //参数判断
        if ( queryBean == null || Strings.isBlank( queryBean.getRallyPointId()  )  ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL)  ;
        }
        //保存
        Boolean res = rallyPointService.removeRallyPoint( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeRallyPoint", String.format("graphql is finished,execute time is :%sms", end - start));

        return  res ;
    }



    /**
     * 重大灾害预警列表删除
     * @param environment 上下文环境变量
     * @return CommandBean
     */
    @GraphQLFieldAnnotation("removeEarlyWarningImportant")
    public Boolean removeEarlyWarningImportant(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeEarlyWarningImportant", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        if(Strings.isBlank(id)){
            logService.infoLog(logger, "graphql", "removeEarlyWarningImportant", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //删除
        Boolean res = earlyWarningImportantService.removeEarlyWarningImportant(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeEarlyWarningImportant", String.format("graphql is finished,execute time is :%sms", end - start));

        return  res ;
    }
    /**
     * 保存常用短语
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveCommonPhrase")
    public CommonPhraseBean saveCommonPhrase(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveCommonPhrase", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        CommonPhraseSaveInputInfo queryBean = GraphQLUtils.parse(input, CommonPhraseSaveInputInfo.class);
        //参数判断
        if (  queryBean == null || Strings.isBlank( queryBean.getPhraseType() ) ||  Strings.isBlank( queryBean.getIncidentType()  )  ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL)  ;
        }
        //保存
        CommonPhraseBean res = commonPhraseService.saveCommonPhrase(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCommonPhrase", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     *  删除常用短语
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("removeCommonPhrase")
    public Boolean removeCommonPhrase(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeCommonPhrase", "graphql is started...");
        Long start = System.currentTimeMillis();

        String commonPhraseId = environment.getArgument("commonPhraseId");
        //参数判断
        if (   Strings.isBlank( commonPhraseId  )  ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL)  ;
        }
        //保存
        Boolean res = commonPhraseService.removeCommonPhrase(commonPhraseId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeCommonPhrase", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 战评总结删除
     * @param environment 上下文环境变量
     * @return CommandBean
     */
    @GraphQLFieldAnnotation("removeActionSummary")
    public Boolean removeActionSummary(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeActionSummary", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");

        //参数判断
        if(Strings.isBlank(id)){
            logService.infoLog(logger, "graphql", "removeActionSummary", "id is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //删除
        Boolean res = actionSummaryService.removeActionSummary(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeActionSummary", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 保存战评总结
     *
     */
    @GraphQLFieldAnnotation("saveActionSummary")
    public ActionSummaryBean  saveActionSummary ( DataFetchingEnvironment environment   ) {
        logService.infoLog(logger, "graphql", "saveActionSummary", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        ActionSummarySaveInputInfo queryBean = GraphQLUtils.parse(input, ActionSummarySaveInputInfo.class);
        //参数判断
        if (  queryBean == null || Strings.isBlank( queryBean.getIncidentId()  )  ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL)  ;
        }
        //保存
        ActionSummaryBean  res = actionSummaryService.saveActionSummary( queryBean ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveActionSummary", String.format("graphql is finished,execute time is :%sms", end - start));

        return  res ;
      }

    /**
     * 保存要事提醒
     *
     * */

    @GraphQLFieldAnnotation("saveImportantReminder")
    public Boolean saveImportantReminder(DataFetchingEnvironment environment)  {
        logService.infoLog(logger, "graphql", "saveImportantReminder", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> input = environment.getArgument("inputInfo");
        ImportantReminderInputInfo inputInfo = GraphQLUtils.parse(input, ImportantReminderInputInfo.class);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveImportantReminder", String.format("graphql is finished,execute time is :%sms", end - start));
        return importantReminderService.saveImportantReminder(inputInfo);
    }


    /**
     * 删除要事提醒
     *
     * */
    @GraphQLFieldAnnotation("deleteImportantReminder")
    public Boolean deleteImportantReminder(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "deleteImportantReminder", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        if( Strings.isBlank( id) ){
            logService.infoLog(logger, "graphql", "deleteImportantReminder", "CommanderSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        Boolean res =  importantReminderService.deleteImportantReminder(id) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteImportantReminder", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }


    /**
     * 预警信息保存
     * @param environment 上下文环境变量
     * @return CommandBean
     */
    @GraphQLFieldAnnotation("saveEarlyWarningImportant")
    public EarlyWarningImportantBean saveEarlyWarningImportant(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveEarlyWarningImportant", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> info = environment.getArgument("inputInfo");
        EarlyWarningImportantSaveInputInfo  queryBean = GraphQLUtils.parse(info, EarlyWarningImportantSaveInputInfo.class);

        //参数判断
        if(null==queryBean){
            logService.infoLog(logger, "graphql", "saveEarlyWarningImportant", "CommanderSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //保存
        EarlyWarningImportantBean res = earlyWarningImportantService.saveEarlyWarningImportant(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveEarlyWarningImportant", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     *下达特别警示
     * @param
     * @return
     */
    @GraphQLFieldAnnotation("saveSecurity")
    public Boolean saveSecurity(DataFetchingEnvironment environment){

        logService.infoLog(logger, "graphql", "saveSecurityHints", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        SecurityInputInfo queryBean = GraphQLUtils.parse(input, SecurityInputInfo.class);
        //参数判断
        if( queryBean == null || Strings.isBlank( queryBean.getIncidentId() )
                ||  queryBean.getReceivers() == null || queryBean.getReceivers().size() < 0  ){

        }
        Boolean res =  securityHintsService.saveSecurityHints(queryBean) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveSecurityHints", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }


    /**
     * 根据安全提示对象ids 签收安全提示信息
     * @param
     * @return
     */
    @GraphQLFieldAnnotation("signSecurityHints")
    public Boolean signSecurityHints(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "signSecurityHints", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IdsInputInfo queryBean = GraphQLUtils.parse(info, IdsInputInfo.class);
        if ( queryBean == null || queryBean.getIds() == null || queryBean.getIds().size() < 1 ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res =  securityHintsService.signSecurityHints( queryBean.getIds() ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "signSecurityHints", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }



    /**
     * 保存更新 案件等级自动升级提醒
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveCaseAutoUpdateWarn")
    public CaseAutoUpdateWarnBean saveCaseAutoUpdateWarn (DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveCaseAutoUpdateWarn", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        CaseAutoUpdateWarnInputInfo inputInfo = GraphQLUtils.parse(input, CaseAutoUpdateWarnInputInfo.class);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCaseAutoUpdateWarn", String.format("graphql is finished,execute time is :%sms", end - start));

        CaseAutoUpdateWarnBean res = caseAutoUpdateWarnSerivce.saveCaseAutoUpdateWarn(inputInfo) ;

        return res ;

    }

    /**
     * 删除自动升级提醒
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("removeCaseAutoUpdateWarn")
    public Boolean removeCaseAutoUpdateWarn (DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeCaseAutoUpdateWarn", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String id = environment.getArgument("id");
        if( Strings.isBlank( id) ){
            logService.infoLog(logger, "graphql", "removeCaseAutoUpdateWarn", "CommanderSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        Boolean res =  caseAutoUpdateWarnSerivce.removeCaseAutoUpdateWarn(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeCaseAutoUpdateWarn", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;

    }
    /**
     *  保存重大案件判定规则
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveMajorIncidentRule")
    public MajorIncidentRuleBean saveMajorIncidentRule(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveMajorIncidentRule", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        MajorIncidentRuleInputInfo inputInfo = GraphQLUtils.parse(info, MajorIncidentRuleInputInfo.class);
        /*if (inputInfo == null||inputInfo.getJudgmentValue().equals("")||inputInfo.getRoleIds()==null||inputInfo.getRoleIds().size()==0) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }*/
        if (inputInfo == null||inputInfo.getJudgmentValue().equals("")||inputInfo.getRuleType()==null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        MajorIncidentRuleBean res = majorIncidentRuleService.saveMajorIncidentRule(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveFireRoleUserByRoleIds", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }
    /**
     *  修改重大案件判定规则
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateMajorIncidentRule")
    public MajorIncidentRuleBean updateMajorIncidentRule(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveMajorIncidentRule", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        MajorIncidentRuleInputInfo inputInfo = GraphQLUtils.parse(info, MajorIncidentRuleInputInfo.class);
        /*if (inputInfo == null||inputInfo.getJudgmentValue().equals("")||inputInfo.getRoleIds()==null||inputInfo.getRoleIds().size()==0) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }*/
        if (inputInfo == null||inputInfo.getJudgmentValue().equals("")||inputInfo.getRuleType()==null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        MajorIncidentRuleBean res = majorIncidentRuleService.updateMajorIncidentRule(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveFireRoleUserByRoleIds", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 删除重大案件判定规则
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("deleteMajorIncidentRule")
    public Boolean deleteMajorIncidentRule (DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "deleteHandoverRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<String> ids = environment.getArgument("ids");
        //参数判断
        if( ids == null || ids.size()<1 ){
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        Boolean res = majorIncidentRuleService.deleteMajorIncidentRule( ids );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteHandoverRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /***
     * 保存定位记录
     *
     */
    @GraphQLFieldAnnotation("saveLocationRecord")
    public LocationRecordBean saveLocationRecord(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "removeCaseAutoUpdateWarn", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        LocationRecordInputInfo queryBean = GraphQLUtils.parse(info, LocationRecordInputInfo.class);
        if ( queryBean == null  ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        LocationRecordBean res = locationRecordService.saveLocationRecord(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeCaseAutoUpdateWarn", String.format("graphql is finished,execute time is :%sms", end - start));

        return res ;
    }


    /**
     * 保存警情引导文书
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveIncidentGuidance")
    public DocumentBean saveIncidentGuidance (DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveIncidentGuidance", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> inputInfo = environment.getArgument("inputInfo");
        IncidentGuidanceInputInfo queryBean = GraphQLUtils.parse(inputInfo, IncidentGuidanceInputInfo.class);

        //逻辑
        DocumentBean res =  documentService.saveIncidentGuidance(queryBean);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentGuidance", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }



}
