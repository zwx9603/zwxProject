package com.dscomm.iecs.accept.graphql;

import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.fireinputbean.LedSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.inputbean.outside.OutsideInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.restful.vo.UserSMSLocationVO;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.accept.service.bean.CombatUnitBean;
import com.dscomm.iecs.accept.service.bean.ConfirmSmartRecommendBean;
import com.dscomm.iecs.accept.service.outside.OutsideService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.OrgRelationshipSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrgRelationshipBean;
import com.dscomm.iecs.ext.incident.status.*;
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
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 描述:接警受理模块graphql查询类执行器
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:47
 */
@Component("acceptMutationExecution")
public class AcceptMutationExecution implements GraphQLAnnotationExecution {
    private static final Logger logger = LoggerFactory.getLogger(AcceptMutationExecution.class);
    private LogService logService;
    private TelephoneService telephoneService;
    private ViolationService violationService;
    private TagLabelService tagLabelService;
    private EarlyWarningService earlyWarningService;
    private WeatherService weatherService;
    private IncidentService incidentService;
    private HierarchicalDispatchService hierarchicalDispatchService;
    private DispatchDailyRecordService dispatchDailyRecordService;
    private HandleService handleService;
    private ParticipantFeedbackService participantFeedbackService;
    private ReinforcementService reinforcementService;
    private FireSafetyMonitoringService fireSafetyMonitoringService;
    private LocaleService localeService;
    private AccidentService accidentService;
    private DocumentService documentService;
    private VehicleStatusChangeService vehicleStatusChangeService;
    private SquadronFillInService squadronFillInService;
    private OutsideService outsideService;
    private DrillPlanService drillPlanService;
    private BlacklistService blacklistService;
    private AttentionService attentionService;
    private InstructionService instructionService;
    private NewsCircularService newsCircularService;
    private IncidentInfoService incidentInfoService;
    private IncidentCirculationService incidentCirculationService;
    private InteractiveRecordService interactiveRecordService;
    private UnTrafficAlarmService unTrafficAlarmService;
    private HandoverRecordSerivce handoverRecordSerivce;
    private LocationService locationService;
    private OrgRelationshipService orgRelationshipService;
    private CommonTipsService commonTipsService;
    private CombatInformationService combatInformationService;
    private VehicleStatusChangeCheckService vehicleStatusChangeCheckService;
    private HandleMiniatureStationService handleMiniatureStationService;
    private VehicleChangeService vehicleChangeService;
    private LedService ledService;
    private ReleaseCallService releaseCallService;
    private CombatUnitService combatUnitService;
    private DispatchPlanUsageRecordService dispatchPlanUsageRecordService;
    private StandardService standardService;
    private SmartRecommendationService smartRecommendationService;

    @Override
    public String getTypeName() {
        return "Mutation";
    }

    @Autowired
    public AcceptMutationExecution(LogService logService, TelephoneService telephoneService, ViolationService violationService,
                                   TagLabelService tagLabelService, EarlyWarningService earlyWarningService, WeatherService weatherService,
                                   IncidentService incidentService, HierarchicalDispatchService hierarchicalDispatchService,
                                   DispatchDailyRecordService dispatchDailyRecordService, HandleService handleService,
                                   ParticipantFeedbackService participantFeedbackService, FireSafetyMonitoringService fireSafetyMonitoringService,
                                   ReinforcementService reinforcementService, LocaleService localeService, AccidentService accidentService,
                                   DocumentService documentService,
                                   VehicleStatusChangeService vehicleStatusChangeService, SquadronFillInService squadronFillInService,
                                   OutsideService outsideService, DrillPlanService drillPlanService,
                                   BlacklistService blacklistService, AttentionService attentionService, InstructionService instructionService,
                                   NewsCircularService newsCircularService, IncidentInfoService incidentInfoService,
                                   IncidentCirculationService incidentCirculationService, InteractiveRecordService interactiveRecordService,
                                   UnTrafficAlarmService unTrafficAlarmService, HandoverRecordSerivce handoverRecordSerivce, LocationService locationService,
                                   OrgRelationshipService orgRelationshipService, CommonTipsService commonTipsService, CombatInformationService combatInformationService,
                                   VehicleStatusChangeCheckService vehicleStatusChangeCheckService,
                                   HandleMiniatureStationService handleMiniatureStationService,
                                   VehicleChangeService vehicleChangeService, LedService ledService,
                                   ReleaseCallService releaseCallService,
                                   StandardService standardService,


                                   CombatUnitService combatUnitService, DispatchPlanUsageRecordService dispatchPlanUsageRecordService, SmartRecommendationService smartRecommendationService) {
        this.logService = logService;
        this.telephoneService = telephoneService;
        this.violationService = violationService;
        this.tagLabelService = tagLabelService;
        this.earlyWarningService = earlyWarningService;
        this.weatherService = weatherService;
        this.incidentService = incidentService;
        this.dispatchDailyRecordService = dispatchDailyRecordService;
        this.hierarchicalDispatchService = hierarchicalDispatchService;
        this.handleService = handleService;
        this.participantFeedbackService = participantFeedbackService;
        this.reinforcementService = reinforcementService;
        this.fireSafetyMonitoringService = fireSafetyMonitoringService;
        this.localeService = localeService;
        this.accidentService = accidentService;
        this.documentService = documentService;
        this.vehicleStatusChangeService = vehicleStatusChangeService;
        this.squadronFillInService = squadronFillInService;
        this.outsideService = outsideService;
        this.drillPlanService = drillPlanService;
        this.blacklistService = blacklistService;
        this.attentionService = attentionService;
        this.instructionService = instructionService;
        this.newsCircularService = newsCircularService;
        this.incidentInfoService = incidentInfoService;
        this.incidentCirculationService = incidentCirculationService;
        this.interactiveRecordService = interactiveRecordService;
        this.unTrafficAlarmService = unTrafficAlarmService;
        this.handoverRecordSerivce = handoverRecordSerivce;
        this.locationService = locationService;
        this.orgRelationshipService = orgRelationshipService;
        this.commonTipsService = commonTipsService;
        this.combatInformationService = combatInformationService;
        this.vehicleStatusChangeCheckService = vehicleStatusChangeCheckService;
        this.handleMiniatureStationService = handleMiniatureStationService;
        this.vehicleChangeService = vehicleChangeService;
        this.ledService = ledService;
        this.releaseCallService = releaseCallService;
        this.combatUnitService = combatUnitService;
        this.dispatchPlanUsageRecordService = dispatchPlanUsageRecordService;
        this.standardService = standardService;
        this.smartRecommendationService = smartRecommendationService;
    }

    /**
     * graphql测试
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("testMutation")
    public Boolean testMutation(DataFetchingEnvironment environment) {
        return true;
    }


    /**
     * 非话务 生成警情id  受理id 信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("buildIdsNoCalling")
    public TelephoneBean buildIncidentId(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "buildIncidentId", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String alarmPhone = environment.getArgument("alarmPhone");
        //执行逻辑处理
        TelephoneBean res = telephoneService.buildIdsNoCalling(alarmPhone);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "buildIncidentId", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 振铃时生成电话信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("ringSaveTelephoneRecord")
    public TelephoneBean ringSaveTelephoneRecord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveTelephone", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断

        String callingTelephone = environment.getArgument("callingTelephone");
        String calledTelephone = environment.getArgument("calledTelephone");
        Boolean isCallback = environment.getArgument("isCallback");
        String incidentId = environment.getArgument("incidentId");
        if (StringUtils.isBlank(callingTelephone) || StringUtils.isBlank(calledTelephone)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        TelephoneBean res = telephoneService.ringSaveTelephoneRecord(callingTelephone, calledTelephone, isCallback, incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveTelephone", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 摘机更新通话信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("pickUpUpdateTelephone")
    public TelephoneBean pickUpUpdateTelephone(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveTelephone", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String telephoneId = environment.getArgument("telephoneId");
        if (Strings.isBlank(telephoneId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        TelephoneBean res = telephoneService.pickUpUpdateTelephone(telephoneId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveTelephone", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 上传录音号更新通话信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("upLoadSoundRecordUpdateTelephone")
    public TelephoneBean upLoadSoundRecordUpdateTelephone(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveTelephone", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String telephoneId = environment.getArgument("telephoneId");
        String agentCallRecordNum = environment.getArgument("agentCallRecordNum");
        if (Strings.isBlank(telephoneId) || StringUtils.isBlank(agentCallRecordNum)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        TelephoneBean res = telephoneService.upLoadSoundRecordUpdateTelephone(telephoneId, agentCallRecordNum);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveTelephone", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 挂机更新通话信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("hangUpUpdateTelephone")
    public TelephoneBean hangUpUpdateTelephone(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveTelephone", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String telephoneId = environment.getArgument("telephoneId");
        if (Strings.isBlank(telephoneId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        TelephoneBean res = telephoneService.hangUpUpdateTelephone(telephoneId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveTelephone", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存语音转写信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveVoiceTranscribe")
    public VoiceTranscribeBean saveVoiceTranscribe(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveVoiceTranscribe", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        VoiceTranscribeSaveInputInfo queryBean = GraphQLUtils.parse(info, VoiceTranscribeSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        VoiceTranscribeBean res = telephoneService.saveVoiceTranscribe(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveVoiceTranscribe", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 更新 电话报警记录的报警经纬度信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateTelephoneCoordinates")
    public TelephoneBean updateTelephoneCoordinates(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveTelephone", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String telephoneId = environment.getArgument("telephoneId");
        String longitude = environment.getArgument("longitude");
        String latitude = environment.getArgument("latitude");
        String height = environment.getArgument("height");
        if (Strings.isBlank(telephoneId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        TelephoneBean res = telephoneService.updateTelephoneCoordinates(telephoneId, longitude, latitude, height);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveTelephone", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 立案 ( 保存接警录入警单信息，通过处理类型来区分有效/无效警情 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveIncident")
    public IncidentBean saveIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        IncidentBean res = incidentService.saveIncident(queryBean, null, true, false);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存警情与调度
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveIncidentHandle")
    public HandleIncidentBean saveIncidentHandle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveIncidentHandle", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentHandleSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentHandleSaveInputInfo.class);
        if (queryBean == null || null == queryBean.getHandleSaveInputInfo()
                || Strings.isBlank(queryBean.getIncidentSaveInputInfo().getTelephoneId()) || null == queryBean.getHandleSaveInputInfo()) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        HandleIncidentBean res = incidentService.saveIncidentHandle(queryBean, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentHandle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 修改警情信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateIncident")
    public IncidentBean updateIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        IncidentBean res = incidentService.updateIncident(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情合并
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveIncidentMerge")
    public Boolean saveIncidentMerge(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveIncidentMerge", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentMergeSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentMergeSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getMainIncidentId()) || Strings.isBlank(queryBean.getMergeIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = incidentService.saveIncidentMerge(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentMerge", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情拆分 ( 拆分过程 记录火场文书 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveIncidentSplit")
    public Boolean saveIncidentSplit(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveIncidentSplit", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentSplitSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentSplitSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getMainIncidentId()) || Strings.isBlank(queryBean.getSplitIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = incidentService.saveIncidentSplit(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentSplit", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存标记标签
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveTagLabel")
    public TagLabelBean saveTagLabel(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveTagLabel", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        TagLabelSaveInputInfo queryBean = GraphQLUtils.parse(info, TagLabelSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        TagLabelBean res = tagLabelService.saveTagLabel(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveTagLabel", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 撤销标记标签
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("removeTagLabel")
    public Boolean removeTagLabel(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "undoTagLabel", "graphql is started...");
        Long start = System.currentTimeMillis();

        String tagLabelId = environment.getArgument("tagLabelId");
        //参数判断
        if (Strings.isBlank(tagLabelId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        Boolean res = tagLabelService.removeTagLabel(tagLabelId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "undoTagLabel", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存预警信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveEarlyWarning")
    public List<EarlyWarningBean> saveEarlyWarning(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveEarlyWarning", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        EarlyWarningListSaveInputInfo inputInfo = GraphQLUtils.parse(info, EarlyWarningListSaveInputInfo.class);
        if (inputInfo == null || inputInfo.getEarlyWarningList() == null
                || inputInfo.getEarlyWarningList().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<EarlyWarningBean> res = earlyWarningService.saveEarlyWarning(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveEarlyWarning", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据预警id 变更预警状态为 已接收
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("changeEarlyWarningStatus")
    public Boolean changeEarlyWarningStatus(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "changeEarlyWarningStatus", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IdsInputInfo queryBean = GraphQLUtils.parse(info, IdsInputInfo.class);
        if (queryBean == null || queryBean.getIds() == null || queryBean.getIds().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = earlyWarningService.changeEarlyWarningStatus(queryBean.getIds());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "changeEarlyWarningStatus", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据案件id取消预警信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("removeEarlyWarning")
    public Boolean removeEarlyWarning(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removeEarlyWarning", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String earlyWarningType = environment.getArgument("earlyWarningType");
        String organizationId = environment.getArgument("organizationId");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        List<String> orgIds=new ArrayList<>();
        if (!StringUtils.isBlank(organizationId)){
            orgIds.add(organizationId);
        }
        //执行逻辑处理
        Boolean res = earlyWarningService.removeEarlyWarning(incidentId, earlyWarningType, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeEarlyWarning", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存调派
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveHandle")
    public HandleBean saveHandle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveHandle", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        HandleSaveInputInfo queryBean = GraphQLUtils.parse(info, HandleSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        HandleBean res = handleService.saveHandle(queryBean, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveHandle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 处警单位 接收确认接口
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateHandleStatus")
    public HandleBean updateHandleStatus(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateHandleStatus", "graphql is started...");
        Long start = System.currentTimeMillis();


        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String handleId = environment.getArgument("handleId");
        String organizationId = environment.getArgument("organizationId");
        Integer handleWay = environment.getArgument("handleWay");
        if (Strings.isBlank(incidentId) || Strings.isBlank(handleId) || Strings.isBlank(organizationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        if (handleWay == null) {
            handleWay = 0;
        }
        //执行逻辑处理
        HandleBean res = handleService.updateHandleStatus(incidentId, handleId, organizationId, handleWay);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateHandleStatus", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存通话记录( 调度日志 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveDispatchDailyRecord")
    public DispatchDailyRecordBean saveDispatchDailyRecord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveDispatchDailyRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        DispatchDailyRecordSaveInputInfo queryBean = GraphQLUtils.parse(info, DispatchDailyRecordSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        DispatchDailyRecordBean res = dispatchDailyRecordService.saveDispatchDailyRecord(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveDispatchDailyRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存短信记录
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveSMS")
    public SendSMSBean saveSMS(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveSMS", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        SendSMSSaveInputInfo queryBean = GraphQLUtils.parse(info, SendSMSSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        SendSMSBean res = dispatchDailyRecordService.saveSMS(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveSMS", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 请求增援
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveReinforcementAsk")
    public ReinforcementAskBean saveReinforcementAsk(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveReinforcementAsk", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        ReinforcementAskSaveInputInfo queryBean = GraphQLUtils.parse(info, ReinforcementAskSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        ReinforcementAskBean res = reinforcementService.saveReinforcementAsk(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveReinforcementAsk", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 设置出动/返队人员
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveParticipant")
    public List<ParticipantFeedbackBean> saveParticipant(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveParticipant", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        ParticipantSaveInputInfo queryBean = GraphQLUtils.parse(info, ParticipantSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId()) || null == queryBean.getParticipantFeedbackSaveInputInfo()
                || queryBean.getParticipantFeedbackSaveInputInfo().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //重新构建
        List<ParticipantSaveInputInfo> queryBeans = new ArrayList<>();
        Map<String, ParticipantSaveInputInfo> participantSaveInputInfoMap = new HashMap<>();
        for (ParticipantFeedbackSaveInputInfo participantFeedback : queryBean.getParticipantFeedbackSaveInputInfo()) {
            String vehicleId = participantFeedback.getVehicleId();
            ParticipantSaveInputInfo participant = participantSaveInputInfoMap.get(vehicleId);
            if (participant == null) {
                participant = new ParticipantSaveInputInfo();
                participant.setVehicleId(vehicleId);
                participant.setIncidentId(queryBean.getIncidentId());
                participant.setCheckPersonId(queryBean.getCheckPersonId());
                participant.setCheckPersonName(queryBean.getCheckPersonName());
                List<ParticipantFeedbackSaveInputInfo> vehicleParticipantFeedback = new ArrayList<>();
                vehicleParticipantFeedback.add(participantFeedback);
                participant.setParticipantFeedbackSaveInputInfo(vehicleParticipantFeedback);

            } else {
                participant.getParticipantFeedbackSaveInputInfo().add(participantFeedback);
            }
            participantSaveInputInfoMap.put(vehicleId, participant);
        }
        queryBeans.addAll(participantSaveInputInfoMap.values());
        //执行逻辑处理
        List<ParticipantFeedbackBean> res = participantFeedbackService.saveParticipant(queryBeans);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveParticipant", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 出入火场登记( 保存火场安全监控 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveFireSafetyMonitoring")
    public List<FireSafetyMonitoringBean> saveFireSafetyMonitoring(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveFireSafetyMonitoring", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        FireSafetySaveInputInfo queryBean = GraphQLUtils.parse(info, FireSafetySaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId()) || queryBean.getFireSafetyType() == null
                || null == queryBean.getFireSafetyMonitoringSaveInputInfo() || queryBean.getFireSafetyMonitoringSaveInputInfo().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        List<FireSafetyMonitoringBean> res = fireSafetyMonitoringService.saveFireSafetyMonitoring(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveFireSafetyMonitoring", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存现场反馈信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveLocale")
    public LocaleBean saveLocale(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveLocale", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        LocaleSaveInputInfo queryBean = GraphQLUtils.parse(info, LocaleSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        LocaleBean res = localeService.saveLocale(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveLocale", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存结案反馈 （保存事故情况）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveAccident")
    public AccidentBean saveAccident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveAccident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        AccidentSaveInputInfo queryBean = GraphQLUtils.parse(info, AccidentSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        AccidentBean res = accidentService.saveAccident(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveAccident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存修改警情文书
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveDocument")
    public DocumentBean saveDocument(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveDocument", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        DocumentSaveInputInfo queryBean = GraphQLUtils.parse(info, DocumentSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        queryBean.setWhetherUpdate(1); //是否可以修改 0 不可以修改 1 可以修改 默认不可以修改
        DocumentBean res = documentService.saveDocument(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveDocument", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 修改案件车辆状态（多车） 兼容 车辆状态变动
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateIncidentVehicleStatus")
    public Boolean updateIncidentVehicleStatus(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateIncidentVehicleStatus", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentVehicleStatusUpdateInputInfo queryBean = GraphQLUtils.parse(info, IncidentVehicleStatusUpdateInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getVehicleStatusCode())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        } else if (Strings.isBlank(queryBean.getIncidentId()) && (queryBean.getVehicleIds() == null || queryBean.getVehicleIds().size() < 1)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        } else if (Strings.isNotBlank(queryBean.getIncidentId()) && (queryBean.getIncidentVehicleHandles() == null || queryBean.getIncidentVehicleHandles().size() < 1)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        if (Strings.isNotBlank(queryBean.getIncidentId())) {
            handleService.updateIncidentVehicleStatus(queryBean);
        } else {
            vehicleStatusChangeService.updateVehicleStatus(null, null, queryBean.getVehicleIds(), null, queryBean.getVehicleStatusCode(), queryBean.getChangeSource());
        }

        Boolean res = true;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateIncidentVehicleStatus", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 修改案件车辆状态（单车） 兼容车辆状态变动
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("buildIncidentVehicleStatus")
    public Boolean buildIncidentVehicleStatus(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "buildIncidentVehicleStatus", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String handleId = environment.getArgument("handleId");
        String vehicleId = environment.getArgument("vehicleId");
        String vehicleStatusCode = environment.getArgument("vehicleStatusCode");
        String changeSource = environment.getArgument("changeSource");
        //参数判断
        if (Strings.isBlank(vehicleId) || Strings.isBlank(vehicleStatusCode)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        if (Strings.isNotBlank(incidentId)) {
            handleService.buildIncidentVehicleStatus(incidentId, handleId, vehicleId, vehicleStatusCode, changeSource);
        } else {
            vehicleStatusChangeService.updateVehicleStatus(null, null, vehicleId, null, vehicleStatusCode, changeSource);
        }
        Boolean res = true;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "buildIncidentVehicleStatus", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 等级调派方案保存修改
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveHierarchicalDispatch")
    public HierarchicalDispatchBean saveHierarchicalDispatch(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveHierarchicalDispatch", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        HierarchicalDispatchSaveInputInfo inputInfo = GraphQLUtils.parse(info, HierarchicalDispatchSaveInputInfo.class);
        if (inputInfo == null || inputInfo.getHierarchicalDispatchVehicleSaveInputInfoList() == null ||
                inputInfo.getHierarchicalDispatchVehicleSaveInputInfoList().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        HierarchicalDispatchBean res = hierarchicalDispatchService.saveHierarchicalDispatch(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveHierarchicalDispatch", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据等级调度id 移除等级调度
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("removeHierarchicalDispatch")
    public Boolean removeHierarchicalDispatch(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removeHierarchicalDispatch", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String hierarchicalDispatchId = environment.getArgument("hierarchicalDispatchId");
        if (Strings.isBlank(hierarchicalDispatchId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = hierarchicalDispatchService.removeHierarchicalDispatch(hierarchicalDispatchId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeHierarchicalDispatch", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存修改违规记录
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveViolation")
    public ViolationRecordBean saveViolation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveViolation", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        ViolationSaveInputInfo queryBean = GraphQLUtils.parse(info, ViolationSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        ViolationRecordBean res = violationService.saveViolation(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveViolation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存天气信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveWeather")
    public WeatherBean saveWeather(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveWeather", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        WeatherSaveInputInfo queryBean = GraphQLUtils.parse(info, WeatherSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        WeatherBean res = weatherService.saveWeather(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveWeather", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 修改车辆拓展信息( 可修改车辆状态 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateVehicleExpandInfo")
    public EquipmentVehicleBean updateVehicleExpandInfo(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateVehicleExpandInfo", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        EquipmentVehicleExpandInputInfo inputInfo = GraphQLUtils.parse(info, EquipmentVehicleExpandInputInfo.class);

        if (inputInfo == null || Strings.isBlank(inputInfo.getId())) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        //执行逻辑处理
        EquipmentVehicleBean res = vehicleChangeService.updateVehicleExpandInfo(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateVehicleExpandInfo", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据车辆ID集合修改车辆状态信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateVehicleListStatus")
    public Boolean updateVehicleListStatus(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateVehicleListStatus", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        List<String> vehicleIds = environment.getArgument("vehicleIds");
        String vehicleStatusCode = environment.getArgument("vehicleStatusCode");
        String changeSource = environment.getArgument("changeSource");
        if (vehicleIds == null || vehicleIds.size() < 1 || Strings.isBlank(vehicleStatusCode)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = vehicleStatusChangeService.updateVehicleStatus(null, null, vehicleIds
                , null, vehicleStatusCode, changeSource);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateVehicleListStatus", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据车辆ID修改车辆状态信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateVehicleStatus")
    public Boolean updateVehicleStatus(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateVehicleStatus", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String vehicleId = environment.getArgument("vehicleId");
        String vehicleStatusCode = environment.getArgument("vehicleStatusCode");
        String changeSource = environment.getArgument("changeSource");
        if (Strings.isBlank(vehicleId) || Strings.isBlank(vehicleStatusCode)) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = vehicleStatusChangeService.updateVehicleStatus(null, null, vehicleId, null, vehicleStatusCode, changeSource);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateVehicleStatus", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 修改车辆状态信息( 包含审核流程 )
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateVehicleStatusChangeCheck")
    public Boolean updateVehicleStatusChangeCheck(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateVehicleStatusChangeCheck", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        VehicleStatusChangeCheckSaveInputInfo queryBean = GraphQLUtils.parse(info, VehicleStatusChangeCheckSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getAppliedOrganizationId())
                || Strings.isBlank(queryBean.getAppliedVehicleId())
                || Strings.isBlank(queryBean.getVehicleStatusCode())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = vehicleStatusChangeCheckService.updateVehicleStatusChangeCheck(queryBean, true);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateVehicleStatusChangeCheck", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存车辆审批结构 （ 同意/拒绝 ）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("checkVehicleStatusChangeCheck")
    public VehicleStatusChangeCheckBean checkVehicleStatusChangeCheck(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "checkVehicleStatusChangeCheck", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        VehicleStatusChangeCheckSaveInputInfo queryBean = GraphQLUtils.parse(info, VehicleStatusChangeCheckSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        VehicleStatusChangeCheckBean res = vehicleStatusChangeCheckService.checkVehicleStatusChangeCheck(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "checkVehicleStatusChangeCheck", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情删除
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("removeIncident")
    public Boolean removeIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removeIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = incidentService.removeIncident(incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情 控制火势
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("controlFire")
    public Boolean controlFire(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "controlFire", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //获取控制火势状态码
        String statusCode = INCIDENT_STATUS_KZHS.getCode();
        //执行逻辑处理
        Boolean res = incidentService.updateIncidentStatus(incidentId, statusCode, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "controlFire", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 警情基本扑灭
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("extinguishFire")
    public Boolean extinguishFire(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "extinguishFire", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //获取基本扑灭状态码
        String statusCode = INCIDENT_STATUS_KZXM.getCode();
        //执行逻辑处理
        Boolean res = incidentService.updateIncidentStatus(incidentId, statusCode, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "extinguishFire", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情结案
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("finishIncident")
    public Boolean finishIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "finishIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //获取结案状态码
        String statusCode = INCIDENT_STATUS_JA.getCode();
        //执行逻辑处理
        Boolean res = incidentService.updateIncidentStatus(incidentId, statusCode, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "finishIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 警情归档
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("archiveIncident")
    public Boolean archiveIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "archiveIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //获取归档状态码
        String statusCode = INCIDENT_STATUS_GD.getCode();
        //执行逻辑处理
        Boolean res = incidentService.updateIncidentStatus(incidentId, statusCode, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "archiveIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情 取回复审
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("recheckIncident")
    public Boolean recheckIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "recheckIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //获取归档复审状态码
        String statusCode = INCIDENT_STATUS_FS.getCode();
        //执行逻辑处理
        Boolean res = incidentService.updateIncidentStatus(incidentId, statusCode, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "recheckIncident", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情状态变更
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateIncidentStatus")
    public Boolean updateIncidentStatus(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateIncidentStatus", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String statusCode = environment.getArgument("statusCode");
        if (Strings.isBlank(incidentId) || Strings.isBlank(statusCode)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = incidentService.updateIncidentStatus(incidentId, statusCode, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateIncidentStatus", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 虚实警转换: 1 实警；0 虚警
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateIncidentNature")
    public Boolean updateIncidentNature(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateIncidentNature", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String incidentNature = environment.getArgument("incidentNature");
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = incidentService.updateIncidentNature(incidentId, incidentNature);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateIncidentNature", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存 中队填报信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveSquadronFillIn")
    public SquadronFillInBean saveSquadronFillIn(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveSquadronFillIn", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        SquadronFillInSaveInputInfo queryBean = GraphQLUtils.parse(info, SquadronFillInSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        SquadronFillInBean res = squadronFillInService.saveSquadronFillIn(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveSquadronFillIn   ", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 转警 消防 转警 110系统
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("transferOutIncident")
    public Boolean transferOutIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "transferOutIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        OutsideInputInfo queryBean = GraphQLUtils.parse(info, OutsideInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId()) || queryBean.getType() == null || queryBean.getType().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = outsideService.transferOutIncident(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "transferOutIncident   ", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 接收消防转警警情确定回执操作
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("receiveTransferOutIncident")
    public Boolean receiveTransferOutIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "receiveTransferOutIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String originalIncidentNumber = environment.getArgument("originalIncidentNumber");
        if (Strings.isBlank(originalIncidentNumber)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = false;
//                outsideService.receiveTransferOutIncident( originalIncidentNumber ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "receiveTransferOutIncident   ", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 错位接警
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("dislocationIncident")
    public Boolean dislocationIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "dislocationIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        OutsideInputInfo queryBean = GraphQLUtils.parse(info, OutsideInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = outsideService.dislocationIncident(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "dislocationIncident   ", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 错位接警 关联错位接警录音号
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("dislocationRelayRecordNumber")
    public Boolean dislocationRelayRecordNumber(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "dislocationRelayRecordNumber", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String dislocationId = environment.getArgument("dislocationId");
        String relayRecordNumber = environment.getArgument("relayRecordNumber");
        if (Strings.isBlank(dislocationId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = outsideService.dislocationRelayRecordNumber(dislocationId, relayRecordNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "dislocationRelayRecordNumber   ", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 请求协助
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("assistIncident")
    public Boolean assistIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "assistIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        OutsideInputInfo queryBean = GraphQLUtils.parse(info, OutsideInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = outsideService.assistIncident(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "assistIncident   ", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存  集合演练方案
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveDrillPlan")
    public DrillPlanBean saveDrillPlan(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveDrillPlan", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        DrillPlanSaveInputInfo queryBean = GraphQLUtils.parse(info, DrillPlanSaveInputInfo.class);
        if (queryBean == null || queryBean.getDrillPlanDispatchSaveInputInfos() == null || queryBean.getDrillPlanDispatchSaveInputInfos().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        DrillPlanBean res = drillPlanService.saveDrillPlan(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveDrillPlan", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据id 移除 集合演练方案
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("removeDrillPlan")
    public Boolean removeDrillPlan(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removeDrillPlan", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        Boolean res = drillPlanService.removeDrillPlan(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeDrillPlan", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 修改警情地址 经纬度变更
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("updateIncidentAddress")
    public IncidentBean updateIncidentAddress(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "updateIncidentAddress", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IncidentSaveInputInfo queryBean = GraphQLUtils.parse(info, IncidentSaveInputInfo.class);

        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getId()) ||
                Strings.isBlank(queryBean.getLongitude()) || Strings.isBlank(queryBean.getLatitude())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        IncidentBean res = incidentService.updateIncidentAddress(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "updateIncidentAddress", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存/修改 黑名单
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveBlacklist")
    public BlacklistBean saveBlacklist(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveBlacklist", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        BlacklistSaveInputInfo queryBean = GraphQLUtils.parse(info, BlacklistSaveInputInfo.class);
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        BlacklistBean res = blacklistService.saveBlacklist(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveBlacklist", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 删除 黑名单
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("removeBlacklist")
    public Boolean removeBlacklist(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removeBlacklist", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String blacklistId = environment.getArgument("blacklistId");
        if (Strings.isBlank(blacklistId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = blacklistService.removeBlacklist(blacklistId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeBlacklist", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 主动接管
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("monitorInsert")
    public Boolean monitorInsert(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "monitorInsert", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        Integer agentNum = (Integer) env.getArgument("agentNum");
        Boolean res = telephoneService.monitorInsert(agentNum);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "monitorInsert", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res;
    }

    /**
     * 班长插话
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("monitorBarge")
    public Boolean monitorBarge(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "monitorBarge", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        Integer agentNum = (Integer) env.getArgument("agentNum");
        Boolean res = telephoneService.monitorBarge(agentNum);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "monitorBarge", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res;
    }


    /**
     * 接警席收到主动接管推送警情
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("getmonitorInsertToPushCaseinfo")
    public Boolean getmonitorInsertToPushCaseinfo(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "getmonitorInsertToPushCaseinfo", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        Map<String, Object> inputInfo = env.getArgument("inputInfo");
        MonitotTakeOverIncidentInputInfo infoBaseBO = GraphQLUtils.parse(inputInfo, MonitotTakeOverIncidentInputInfo.class);
        Integer agentNum = (Integer) env.getArgument("agentNum");
        Boolean res = telephoneService.getmonitorInsertToPushCaseinfo(infoBaseBO, agentNum);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getmonitorInsertToPushCaseinfo", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return res;
    }

    /**
     * 成功收到接警席主动接管推送的警情
     *
     * @param env 上下文环境
     * @return 是否成功
     */
    @GraphQLFieldAnnotation("getmonitorInsertToPushCaseinfoResult")
    public Boolean getmonitorInsertToPushCaseinfoResult(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "getmonitorInsertToPushCaseinfoResult", "graphql is started...");
        Long logStart = System.currentTimeMillis();

        Map<String, Object> inputInfo = env.getArgument("inputInfo");
        MonitotTakeOverIncidentInputInfo caseInfoBaseBO = GraphQLUtils.parse(inputInfo, MonitotTakeOverIncidentInputInfo.class);

        Integer agentNum = (Integer) env.getArgument("agentNum");

        Boolean res = telephoneService.getmonitorInsertToPushCaseinfoResult(caseInfoBaseBO, agentNum);

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "getmonitorInsertToPushCaseinfoResult", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));

        return res;
    }

    /**
     * 回呼，用于接警中/警情详情界面回呼报警人(无振铃)
     *
     * @param env 上下文环境
     * @return 呼入编号
     */
    @GraphQLFieldAnnotation("callback")
    public TelephoneBean callBack(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "callBack", "graphql is started...");
        Long logStart = System.currentTimeMillis();
        Map<String, Object> map = env.getArgument("inputInfo");
        CallBackSaveInputInfo inputInfo = GraphQLUtils.parse(map, CallBackSaveInputInfo.class);
        TelephoneBean id = telephoneService.callBackSaveCallRecord(inputInfo);
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "callBack", String.format("graphql is finished,execute time is :%s ms", logEnd - logStart));
        return id;
    }


    /**
     * 接管警情 传递警情信息
     *
     * @param env 上下文环境变量
     * @return 返回操作结果
     */
    @GraphQLFieldAnnotation("incidentCirculation")
    public Boolean incidentCirculation(DataFetchingEnvironment env) {

        logService.infoLog(logger, "graphql", "incidentCirculation", "graphql is started...");
        Map<String, Object> inputInfo = env.getArgument("inputInfo");

        IncidentCirculationBean bo = GraphQLUtils.parse(inputInfo, IncidentCirculationBean.class);
        Long start = System.currentTimeMillis();

        Boolean res = incidentCirculationService.circulation(bo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "incidentCirculation", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 通知警情接管成功
     *
     * @param env 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("confirmIncidentCirculation")
    public Boolean confirmIncidentCirculation(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "confirmIncidentCirculation", "graphql is started...");
        Map<String, Object> inputInfo = env.getArgument("inputInfo");

        IncidentCirculationConfirmBean bo = GraphQLUtils.parse(inputInfo, IncidentCirculationConfirmBean.class);

        Long start = System.currentTimeMillis();

        Boolean res = incidentCirculationService.notifyCirculationSuccess(bo);

        Long end = System.currentTimeMillis();

        logService.infoLog(logger, "graphql", "confirmIncidentCirculation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 警情关注
     *
     * @param env 上下文变量
     * @return 保存后的信息
     */
    @GraphQLFieldAnnotation("saveAlarmAttention")
    public AttentionBean saveAlarmAttention(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "saveAlarmAttention", "graphql is started...");
        Long start = System.currentTimeMillis();
        Map<String, Object> inputInfo = env.getArgument("inputInfo");

        String incidentId = env.getArgument("incidentId");
        Integer attentionType = env.getArgument("attentionType");
        Integer type = env.getArgument("type");
        //参数判断
        if (Strings.isBlank(incidentId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        AttentionBean res = attentionService.saveAttention(incidentId, attentionType, type, 2, null);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveAlarmAttention", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 警情取消关注
     *
     * @param env 上下文变量
     * @return 返回操作结果
     */
    @GraphQLFieldAnnotation("deleteAlarmAttention")
    public Boolean deleteAlarmAttention(DataFetchingEnvironment env) {
        logService.infoLog(logger, "graphql", "deleteAlarmAttention", "graphql is started...");
        Long start = System.currentTimeMillis();
        //参数判断
        String incidentId = env.getArgument("incidentId");
        Integer attentionType = env.getArgument("attentionType");
        Integer type = env.getArgument("type");
        Boolean res = attentionService.deleteAttention(incidentId, attentionType, type, 2);
        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteAlarmAttention", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存指令单信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveInstruction")
    public InstructionBean saveInstruction(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveInstruction", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        InstructionSaveInputInfo queryBean = GraphQLUtils.parse(input, InstructionSaveInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        InstructionBean res = instructionService.saveInstruction(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveInstruction", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    /**
     * 保存指令单处理信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveInstructionRecordHandle")
    public Boolean saveInstructionRecordHandle(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveInstructionRecordHandle", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        InstructionRecordHandleSaveInputInfo queryBean = GraphQLUtils.parse(input, InstructionRecordHandleSaveInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        Boolean res = instructionService.saveInstructionRecordHandle(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveInstructionRecordHandle", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 保存通知信息
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveNewsCircular")
    public NewsCircularBean saveNewsCircular(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveNewsCircular", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        NewsCircularSaveInputInfo queryBean = GraphQLUtils.parse(input, NewsCircularSaveInputInfo.class);
        //参数判断
        if (queryBean == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        NewsCircularBean res = newsCircularService.saveNewsCircular(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveNewsCircular", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }


    /**
     * 根据消息接收者id 变更消息接收状态为 已接收
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("receiveNewsCircular")
    public Boolean receiveNewsCircular(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "receiveNewsCircular", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        IdsInputInfo queryBean = GraphQLUtils.parse(info, IdsInputInfo.class);
        if (queryBean == null || queryBean.getIds() == null || queryBean.getIds().size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = newsCircularService.receiveNewsCircular(queryBean.getIds());

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "receiveNewsCircular", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 根据消息接收者id 变更消息接收状态为 已接收
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveIncidentGroup")
    public Boolean saveIncidentGroup(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveIncidentGroup", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String incidentGroupId = environment.getArgument("incidentGroupId");

        if (Strings.isBlank(incidentId) || Strings.isBlank(incidentGroupId)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = incidentInfoService.saveIncidentGroup(incidentId, incidentGroupId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveIncidentGroup", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 根据ajid,clid,cllx 设置头车（尾车）
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveHandleVehicleIdentification")
    public Boolean saveHandlevehicleIdentification(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveHandleVehicleIdentification", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        String incidentId = environment.getArgument("incidentId");
        String vehicleId = environment.getArgument("vehicleId");
        String vehicleIdentification = environment.getArgument("vehicleIdentification");

        if (Strings.isBlank(incidentId) || Strings.isBlank(vehicleId) || Strings.isBlank(vehicleIdentification)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = handleService.saveHandleVehicleIdentification(incidentId, vehicleId, vehicleIdentification);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveHandleVehicleIdentification", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存交互记录
     */
    @GraphQLFieldAnnotation("saveInteractiveRecord")
    public InteractiveRecordBean saveUncallAccept(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveInteractiveRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> inputInfo = environment.getArgument("inputInfo");
        String alarmNumber = (String) environment.getArgument("alarmNumber");
        InteractiveRecordSaveInputInfo queryBean = GraphQLUtils.parse(inputInfo, InteractiveRecordSaveInputInfo.class);
        //执行逻辑处理
        InteractiveRecordBean res = interactiveRecordService.saveInteractiveRecord(queryBean, alarmNumber);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveInteractiveRecord", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 交互记录 关联警情
     */
    @GraphQLFieldAnnotation("saveInteractiveRecordToIncident")
    public Boolean saveInteractiveRecordToIncident(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveInteractiveRecordToIncident", "graphql is started...");
        Long start = System.currentTimeMillis();

        String incidentId = (String) environment.getArgument("incidentId");
        String alarmNumber = (String) environment.getArgument("alarmNumber");
        if (Strings.isBlank(incidentId) || Strings.isBlank(alarmNumber)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = interactiveRecordService.saveInteractiveRecordToIncident(alarmNumber, incidentId);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveInteractiveRecordToIncident", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 锁定非话务
     */
    @GraphQLFieldAnnotation("lockUncallAccept")
    public Boolean lockUncallAccept(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "lockUncallAccept", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        Boolean res = unTrafficAlarmService.lockUncallAccept(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "lockUncallAccept", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 删除非话务
     */
    @GraphQLFieldAnnotation("uncallAcceptListDelete")
    public Boolean uncallAcceptListDelete(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveUncallAccept", "graphql is started...");
        Long start = System.currentTimeMillis();

        String id = environment.getArgument("id");
        //参数判断
        if (Strings.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        Boolean res = unTrafficAlarmService.uncallAcceptListDelete(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveUncallAccept", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存交接班日志
     */
    @GraphQLFieldAnnotation("saveHandoverRecord")
    public HandoverRecordBean saveHandoverRecord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveHandoverRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> inputInfo = environment.getArgument("inputInfo");
        HandoverRecordSaveInputInfo queryBean = GraphQLUtils.parse(inputInfo, HandoverRecordSaveInputInfo.class);
        //参数判断
        if (queryBean == null || StringUtils.isBlank(queryBean.getHandoverPersonId()) || StringUtils.isBlank(queryBean.getHandoverPersonName()) || queryBean.getHandoverTime() == null) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        HandoverRecordBean res = handoverRecordSerivce.saveHandoverRecord(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveHandoverRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 删除交接班日志
     */
    @GraphQLFieldAnnotation("deleteHandoverRecord")
    public Boolean deleteHandoverRecord(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "deleteHandoverRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<String> ids = environment.getArgument("ids");
        //参数判断
        if (ids == null || ids.size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        Boolean res = handoverRecordSerivce.deleteHandoverRecord(ids);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteHandoverRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存 发送短信定位信息
     *
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveSMSLocation")
    public Boolean saveSMSLocation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveSMSLocation", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> inputInfo = environment.getArgument("inputInfo");
        UserSMSLocationVO queryBean = GraphQLUtils.parse(inputInfo, UserSMSLocationVO.class);
        //参数判断
        if (Strings.isBlank(queryBean.getUserAccount()) || Strings.isBlank(queryBean.getPhoneNumber())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        Boolean res = locationService.saveSMSLocation(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveSMSLocation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 添加消防转警关系
     *
     * @return
     */
    @GraphQLFieldAnnotation("saveOrgRelationship")
    public OrgRelationshipBean saveOrgRelationship(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveSMSLocation", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> inputInfo = environment.getArgument("inputInfo");
        OrgRelationshipSaveInputInfo queryBean = GraphQLUtils.parse(inputInfo, OrgRelationshipSaveInputInfo.class);
        //参数判断
        if (queryBean == null || Strings.isBlank(queryBean.getFireDeptId()) || Strings.isBlank(queryBean.getTransferType()) ||
                Strings.isBlank(queryBean.getTransferDeptCode())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //逻辑
        OrgRelationshipBean res = orgRelationshipService.saveOrgRelationship(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveSMSLocation", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 删除消防转警关系
     *
     * @return
     */
    @GraphQLFieldAnnotation("deleteOrgRelationship")
    public Boolean deleteOrgRelationship(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "deleteOrgRelationship", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<String> ids = environment.getArgument("ids");
        //参数判断
        if (ids == null || ids.size() < 1) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = orgRelationshipService.deleteOrgRelationship(ids);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteOrgRelationship", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;

    }


    /**
     * 保存安全提醒
     *
     * @return
     */
    @GraphQLFieldAnnotation("saveCommonTips")
    public CommonTipsBean saveCommonTips(DataFetchingEnvironment environment) throws UnsupportedEncodingException {
        logService.infoLog(logger, "graphql", "saveCommonTips", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> input = environment.getArgument("inputInfo");
        CommonTipsInputInfo inputInfo = GraphQLUtils.parse(input, CommonTipsInputInfo.class);
        if (Objects.nonNull(inputInfo)) {
            inputInfo.setHandleTips(URLDecoder.decode(inputInfo.getHandleTips(), "UTF-8"));
            inputInfo.setReceiveTips(URLDecoder.decode(inputInfo.getReceiveTips(), "UTF-8"));
            inputInfo.setSafetyTips(URLDecoder.decode(inputInfo.getSafetyTips(), "UTF-8"));
        }

        //执行逻辑处理
        CommonTipsBean safetyWarnBean = commonTipsService.saveCommonTips(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCommonTips", String.format("graphql is finished,execute time is :%sms", end - start));

        return safetyWarnBean;

    }

    /**
     * 删除安全提示
     *
     * @return
     */
    @GraphQLFieldAnnotation("removeCommonTips")
    public Boolean removeCommonTips(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "removeCommonTips", "graphql is started...");
        Long start = System.currentTimeMillis();

        List<String> ids = environment.getArgument("ids");
        //参数判断
        if (CollectionUtils.isEmpty(ids)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        Boolean res = commonTipsService.removeCommonTips(ids);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "removeCommonTips", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;

    }

    /**
     * 保存作战信息卡
     */
    @GraphQLFieldAnnotation("saveCombatInformation")
    public CombatInformationBean saveCombatInformation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveCombatInformation", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> input = environment.getArgument("inputInfo");
        CombatInformationInputInfo inputInfo = GraphQLUtils.parse(input, CombatInformationInputInfo.class);

        if (inputInfo == null || StringUtils.isBlank(inputInfo.getOrganizationId()) || StringUtils.isBlank(inputInfo.getFileURL()) || StringUtils.isBlank(inputInfo.getFileName())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        CombatInformationBean res = combatInformationService.saveCombatInformation(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveCombatInformation", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }

    /**
     * 删除作战信息卡
     */
    @GraphQLFieldAnnotation("deleteCombatInformation")
    public Boolean deleteCombatInformation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "deleteCombatInformation", "graphql is started...");
        Long start = System.currentTimeMillis();


        String id = environment.getArgument("id");
        if (StringUtils.isBlank(id)) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        Boolean res = combatInformationService.deleteCombatInformation(id);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "deleteCombatInformation", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 保存车辆状态审批记录
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("auditVehicleStatusChangeCheck")
    public Boolean auditVehicleStatusChangeCheck(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "auditVehicleStatusChangeCheck", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        VehicleStatusChangeCheckInputInfo queryBean = GraphQLUtils.parse(info, VehicleStatusChangeCheckInputInfo.class);

        //执行逻辑处理
//        Boolean res = vehicleStatusChangeCheckService.auditVehicleStatusChangeCheck(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "auditVehicleStatusChangeCheck", String.format("graphql is finished,execute time is :%sms", end - start));
        return false;
    }

    /**
     * 保存微站调派
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("saveHandleMiniatureStation")
    public HandleBean saveHandleMiniatureStation(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveHandleMiniatureStation", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        HandleSaveInputInfo queryBean = GraphQLUtils.parse(info, HandleSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        HandleBean res = handleMiniatureStationService.saveHandleMiniatureStation(queryBean, null);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveHandleMiniatureStation", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }


    /**
     * 保存微站调派反馈
     *
     * @return 返回结果
     */

    @GraphQLFieldAnnotation("saveHandleMiniatureStationFeedback")
    public HandleMiniatureStationFeedbackBean saveHandleMiniatureStationFeedback(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveHandleMiniatureStationFeedback", "graphql is started...");
        Long start = System.currentTimeMillis();

        //参数判断
        Map<String, Object> info = environment.getArgument("inputInfo");
        HandleMiniatureStationFeedbackSaveInputInfo queryBean = GraphQLUtils.parse(info, HandleMiniatureStationFeedbackSaveInputInfo.class);
        if (queryBean == null || Strings.isBlank(queryBean.getIncidentId()) || Strings.isBlank(queryBean.getHandleId())
                || Strings.isBlank(queryBean.getHandleMiniatureStationId())) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        //执行逻辑处理
        HandleMiniatureStationFeedbackBean res = handleMiniatureStationService.saveHandleMiniatureStationFeedback(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveHandleMiniatureStationFeedback", String.format("graphql is finished,execute time is :%sms", end - start));

        return res;
    }


    /**
     * 维护LED
     *
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveLed")
    public Boolean saveLed(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveLed", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> inputInfo = environment.getArgument("inputInfo");
        LedSaveInputInfo queryBean = GraphQLUtils.parse(inputInfo, LedSaveInputInfo.class);

        //逻辑
        Boolean res = ledService.saveLed(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveLed", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;
    }

    /**
     * 保存排队早释记录
     *
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveReleaseCall")
    public Boolean saveReleaseCall(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveReleaseCall", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        ReleaseCallSaveInputInfo queryBean = GraphQLUtils.parse(input, ReleaseCallSaveInputInfo.class);

        //执行逻辑处理
        Boolean res = releaseCallService.saveReleaseCall(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveReleaseCall", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

//    /**
//     * 更改参战人员状态 进场 退出火场等
//     *
//     * @param environment 参数
//     * @return 返回结果
//     */
//    @GraphQLFieldAnnotation("changeParticpantPersonState")
//    public List<ParticipantFeedbackBean> changeParticpantPersonState(DataFetchingEnvironment environment) {
//        logService.infoLog(logger, "graphql", "changeParticpantPersonState", "graphql is started...");
//        Long start = System.currentTimeMillis();
//
//        Map<String, Object> input = environment.getArgument("inputInfo");
//        ParticipantBackInputInfo queryBean = GraphQLUtils.parse(input, ParticipantBackInputInfo.class);
//
//        //执行逻辑处理
//        List<ParticipantFeedbackBean> res = participantFeedbackService.changeParticipantPersonState(queryBean);
//
//        Long end = System.currentTimeMillis();
//        logService.infoLog(logger, "graphql", "changeParticpantPersonState", String.format("graphql is finished,execute time is :%sms", end - start));
//        return res;
//    }

    /**
     * 保存或修改作战单元
     *
     * @param environment 上下文环境变量
     * @return 返回数据
     */
    @GraphQLFieldAnnotation("saveOrUpdateCombatUnit")
    public CombatUnitBean saveOrUpdateCombatUnit(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveOrUpdateCombatUnit", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        CombatUnitInputInfo queryBean = GraphQLUtils.parse(input, CombatUnitInputInfo.class);

        //执行逻辑处理
        CombatUnitBean combatUnitBean = combatUnitService.saveOrUpdateCombatUnit(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveOrUpdateCombatUnit", String.format("graphql is finished,execute time is :%sms", end - start));
        return combatUnitBean;
    }

    @GraphQLFieldAnnotation("saveDispatchPlanUsageRecord")
    public Boolean saveDispatchPlanUsageRecord(DataFetchingEnvironment environment){
        logService.infoLog(logger, "graphql", "saveDispatchPlanUsageRecord", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        DispachPlanUsageRecordInputInfo inputInfo = GraphQLUtils.parse(input, DispachPlanUsageRecordInputInfo.class);

        //执行逻辑处理
        Boolean ret = dispatchPlanUsageRecordService.saveDispatchPlanUsageRecord(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveDispatchPlanUsageRecord", String.format("graphql is finished,execute time is :%sms", end - start));
        return ret;
    }
    /**
     * 保存排队记录
     *
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("saveQueueCall")
    public Boolean saveQueueCall(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveQueueCall", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        ReleaseCallSaveInputInfo queryBean = GraphQLUtils.parse(input, ReleaseCallSaveInputInfo.class);

        //执行逻辑处理
        Boolean res = releaseCallService.saveQueueCalls(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveQueueCall", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    /**
     * 结束排队，从排队列表移除
     *
     * @param environment
     * @return
     */
    @GraphQLFieldAnnotation("removeQuenceCall")
    public Boolean removeQuenceCall(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "saveQueueCall", "graphql is started...");
        Long start = System.currentTimeMillis();

        String callNum = environment.getArgument("callNum");

        //执行逻辑处理
        Boolean res = releaseCallService.removeQuenceCall(callNum);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "saveQueueCall", String.format("graphql is finished,execute time is :%sms", end - start));
        return res;

    }

    /**
     * 新增，修改，删除警情提示
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("savaOrRemoveStandard")
    public UpdateStandardBean savaOrRemoveStandard(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "savaOrRemoveStandard", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        UpdateStandardInfo queryBean = GraphQLUtils.parse(input, UpdateStandardInfo.class);
        //参数判断
        if (queryBean == null ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        UpdateStandardBean bean =standardService.saveOrUpdateStandard(queryBean);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "savaOrRemoveStandard", String.format("graphql is finished,execute time is :%sms", end - start));
        return bean;
    }

    /**
     * 确认智能推荐结果、差异原因
     *
     * @param environment 上下文环境变量
     * @return 返回结果
     */
    @GraphQLFieldAnnotation("confirmSmartRecommend")
    public Boolean confirmSmartRecommend(DataFetchingEnvironment environment) {
        logService.infoLog(logger, "graphql", "confirmSmartRecommend", "graphql is started...");
        Long start = System.currentTimeMillis();

        Map<String, Object> input = environment.getArgument("inputInfo");
        ConfirmSmartRecommendBean param = GraphQLUtils.parse(input, ConfirmSmartRecommendBean.class);
        //参数判断
        if (param == null ) {
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }

        //执行逻辑处理
        Boolean result = smartRecommendationService.confirmSmartRecommend(param);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "graphql", "confirmSmartRecommend", String.format("graphql is finished,execute time is :%sms", end - start));
        return result;
    }

}
