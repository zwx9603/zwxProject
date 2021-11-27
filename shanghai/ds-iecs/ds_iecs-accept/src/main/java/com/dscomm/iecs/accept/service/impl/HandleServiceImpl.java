package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.dal.repository.*;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.*;
import com.dscomm.iecs.accept.graphql.typebean.*;
import com.dscomm.iecs.accept.hangzhou.service.TTSSpeechService;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.GenerateUtil;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.dal.repository.EquipmentVehiclePersonRepository;
import com.dscomm.iecs.basedata.graphql.typebean.*;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.basedata.service.enums.ParticipantPersonStateEnum;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_CCD;
import com.dscomm.iecs.ext.comm.document.DOCUMENT_TYPE_DPQC;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_TZ;
import com.dscomm.iecs.ext.instructions.STATUS_GIVEN;
import com.dscomm.iecs.ext.instructions.STATUS_SIGNED;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDFD;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_CDZTFD;
import com.dscomm.iecs.ext.vehicle.status.VEHICLE_STATUS_TZ;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.DateUtils;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：处警记录（调派记录）服务类实现
 */
@Component("handleServiceImpl")
public class HandleServiceImpl implements HandleService {
    private static final Logger logger = LoggerFactory.getLogger(HandleServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private HandleRepository handleRepository;
    private OrganizationService organizationService;
    private HandleOrganizationRepository handleOrganizationRepository;
    private HandleOrganizationVehicleRepository handleOrganizationVehicleRepository;
    private HandleOrganizationEquipmentRepository handleOrganizationEquipmentRepository;
    private DictionaryService dictionaryService;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;
    private VehicleService vehicleService;
    private ServletService servletService;
    private DocumentService documentService;
    private VehicleStatusChangeService vehicleStatusChangeService;
    private UserService userService;
    private NotifyActionService notifyActionService;
    private ParticipantFeedbackService participantFeedbackService;
    private EquipmentService equipmentService;
    private SystemConfigurationService systemConfigurationService;
    private IncidentService incidentService;
    private EarlyWarningService earlyWarningService;
    private OnDutyService onDutyService;
    private HandleStatusChangeService handleStatusChangeService;
    private HandleMiniatureStationService handleMiniatureStationService;
    private HandleMiniatureStationRepository handleMiniatureStationRepository;
    private PushDataService pushDataService;
    private AgentService agentService;
    private VehiclePersonService vehiclePersonService;
    private TTSSpeechService ttsSpeechService;
    private TTSCacheService ttsCacheService;
    private EquipmentVehiclePersonRepository equipmentVehiclePersonRepository;
//    private Map<String,String> ttsSpeakToFileMap = new ConcurrentHashMap<>();


    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    @Lazy(true)
    public HandleServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, Environment env,
                             HandleRepository handleRepository, OrganizationService organizationService,
                             HandleOrganizationRepository handleOrganizationRepository,
                             HandleOrganizationVehicleRepository handleOrganizationVehicleRepository,
                             HandleOrganizationEquipmentRepository handleOrganizationEquipmentRepository,
                             DictionaryService dictionaryService,
                             AuditLogService auditLogService, SubAuditService subAuditService,
                             VehicleService vehicleService, ServletService servletService,
                             DocumentService documentService, VehicleStatusChangeService vehicleStatusChangeService,
                             UserService userService,
                             NotifyActionService notifyActionService,
                             ParticipantFeedbackService participantFeedbackService, EquipmentService equipmentService,
                             SystemConfigurationService systemConfigurationService,
                             IncidentService incidentService, EarlyWarningService earlyWarningService,
                             OnDutyService onDutyService,
                             @Qualifier("handleStatusChange") HandleStatusChangeService handleStatusChangeService,
                             HandleMiniatureStationService handleMiniatureStationService,
                             HandleMiniatureStationRepository handleMiniatureStationRepository,
                             PushDataService pushDataService,
                             VehiclePersonService vehiclePersonService, TTSSpeechService ttsSpeechService,
                             AgentService agentService,


                             TTSCacheService ttsCacheService, EquipmentVehiclePersonRepository equipmentVehiclePersonRepository) {

        this.accessor = accessor;
        this.logService = logService;
        this.env = env;
        this.handleRepository = handleRepository;
        this.organizationService = organizationService;
        this.dictionaryService = dictionaryService;
        this.handleOrganizationRepository = handleOrganizationRepository;
        this.handleOrganizationVehicleRepository = handleOrganizationVehicleRepository;
        this.handleOrganizationEquipmentRepository = handleOrganizationEquipmentRepository;
        this.dictionaryService = dictionaryService;
        this.auditLogService = auditLogService;
        this.subAuditService = subAuditService;
        this.vehicleService = vehicleService;
        this.servletService = servletService;
        this.documentService = documentService;
        this.vehicleStatusChangeService = vehicleStatusChangeService;
        this.userService = userService;
        this.notifyActionService = notifyActionService;
        this.participantFeedbackService = participantFeedbackService;
        this.equipmentService = equipmentService;
        this.systemConfigurationService = systemConfigurationService;
        this.incidentService = incidentService;
        this.earlyWarningService = earlyWarningService;
        this.onDutyService = onDutyService;
        this.handleStatusChangeService = handleStatusChangeService;
        this.handleMiniatureStationService = handleMiniatureStationService;
        this.handleMiniatureStationRepository = handleMiniatureStationRepository;
        this.pushDataService = pushDataService;
        this.vehiclePersonService = vehiclePersonService;
        this.ttsSpeechService = ttsSpeechService;
        this.agentService = agentService;
        this.ttsCacheService = ttsCacheService;
        this.equipmentVehiclePersonRepository = equipmentVehiclePersonRepository;

        dics = new ArrayList<>(Arrays.asList("ZLZT", "ZLDP", "WLCLLX", "WLZZGN", "WLCLZT", "ZBLX", "AJZT",
                "XB", "XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJXZ", "CZDX", "ZDDWLX", "JZJG", "YWQK", "ZHCS", "CLLX", "JQBQ", "JQDX"));

    }


    /**
     * {@inheritDoc}
     *
     * @see HandleService#saveHandle(HandleSaveInputInfo, UserInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public HandleBean saveHandle(HandleSaveInputInfo queryBean, UserInfo userInfo) {
        if (null == queryBean || Strings.isBlank(queryBean.getIncidentId())) {
            logService.infoLog(logger, "service", "saveHandle", "HandleSaveInputInfo or incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        logService.infoLog(logger, "service", "findHandle", "service is started...");
        Long logStart = System.currentTimeMillis();
        boolean isSend = Boolean.parseBoolean(env.getProperty("dh.send")); // 是否转发大华
        String incidentId = queryBean.getIncidentId();
        IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);
        if (null == incidentEntity) {
            logService.infoLog(logger, "service", "saveHandle", "incidentEntity is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
        }
        if (null == userInfo) {
            userInfo = userService.getUserInfo();
        }

        //出动单位
        List<HandleOrganizationSaveInputInfo> handleOrganizationSaveInputInfo = queryBean.getHandleOrganizationSaveInputInfo();
        //出动车辆
//            List<HandleOrganizationVehicleSaveInputInfo> handleOrganizationVehicleSaveInputInfo = queryBean.getHandleOrganizationVehicleSaveInputInfo() ;
        //保存处警装备
//            List<HandleOrganizationEquipmentSaveInputInfo> handleOrganizationEquipmentSaveInputInfo  = queryBean.getHandleOrganizationEquipmentSaveInputInfo() ;

        List<HandleOrganizationVehicleSaveInputInfo> handleOrganizationVehicleSaveInputInfo = new ArrayList<>();
        List<HandleOrganizationEquipmentSaveInputInfo> handleOrganizationEquipmentSaveInputInfo = new ArrayList<>();
        if (null != handleOrganizationSaveInputInfo && handleOrganizationSaveInputInfo.size() > 0) {

            for (HandleOrganizationSaveInputInfo handleOrganization : handleOrganizationSaveInputInfo) {
                //保存处警车辆（作战车辆信息）
                List<HandleOrganizationVehicleSaveInputInfo> handleOrganizationVehicle = handleOrganization.getHandleOrganizationVehicleSaveInputInfo();
                if (handleOrganizationVehicle != null && handleOrganizationVehicle.size() > 0) {
                    for (HandleOrganizationVehicleSaveInputInfo inputInfo : handleOrganizationVehicle) {
                        inputInfo.setOrganizationId(handleOrganization.getOrganizationId());
                    }
                    handleOrganizationVehicleSaveInputInfo.addAll(handleOrganizationVehicle);
                }
                //保存处警装备
                List<HandleOrganizationEquipmentSaveInputInfo> handleOrganizationEquipment = handleOrganization.getHandleOrganizationEquipmentSaveInputInfo();
                if (handleOrganizationEquipment != null && handleOrganizationEquipment.size() > 0) {
                    for (HandleOrganizationEquipmentSaveInputInfo inputInfo : handleOrganizationEquipment) {
                        inputInfo.setOrganizationId(handleOrganization.getOrganizationId());
                    }
                    handleOrganizationEquipmentSaveInputInfo.addAll(handleOrganizationEquipment);
                }
            }
        }
        try {
            //判断是否车辆是否可以参与调派
            if (null != handleOrganizationVehicleSaveInputInfo && handleOrganizationVehicleSaveInputInfo.size() > 0) {
                List<String> judgeVehicleIds = new ArrayList<>();
                for (HandleOrganizationVehicleSaveInputInfo handleOrganizationVehicle : handleOrganizationVehicleSaveInputInfo) {
                    //添加关联案件的车辆id
                    judgeVehicleIds.add(handleOrganizationVehicle.getVehicleId());
                }
                //判断 是否可以车辆是可以调派
                Boolean bl = judgeWhetherHandle(judgeVehicleIds);
                if (!bl) {
                    logService.infoLog(logger, "service", "saveHandle", "vehicle status is not all  dispatch .");
                    throw new AcceptException(AcceptException.AccetpErrors.VEHICLE_STATUS_NO_HANLE);
                }
            }
        } catch (AcceptException ex) {
            logService.infoLog(logger, "service", "saveHandle", "vehicle status is not all  dispatch ");
            throw ex;
        }
        try {

            //保存调派参数完善

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            //处警记录保存
            //生成处警编号
            if (Strings.isBlank(queryBean.getHandleNumber())) {
                Date nowDate = new Date(servletService.getSystemTime());
                String agentNum = userInfo.getAgentNum();
                OrganizationBean handleOrg = null;
                if (Strings.isNotBlank(queryBean.getHandleOrganizationId())) {
                    handleOrg = organizationService.findOrganizationByOrganizationId(queryBean.getHandleOrganizationId());
                }
                if (handleOrg == null) {
                    handleOrg = organizationService.getRootOrg();
                }
//                //消防无 机构编码  使用消防所在行政区划
//                String handleNumber = GenerateUtil.GenerateCJDBH(nowDate, agentNum, handleOrg.getDistrictCode() );
                String handleNumber = GenerateUtil.GenerateSJDBH(nowDate, agentNum);
                queryBean.setHandleNumber(handleNumber);
            }
            Long currentTime = servletService.getSystemTime();
            if (queryBean.getHandleStartTime() == null || queryBean.getHandleStartTime() == 0) {
                //如果为空  设置默认 立案时间为处警开始时间
                queryBean.setHandleStartTime(incidentEntity.getRegisterIncidentTime());
            }
            if (queryBean.getHandleEndTime() == null || queryBean.getHandleEndTime() == 0) {
                queryBean.setHandleEndTime(currentTime);
            }

            HandleEntity handleEntity = HandleDispatchTransformUtil.transform(queryBean, incidentId);
            handleEntity.setHandleOrganizationName(organizationNameMap.get(handleEntity.getHandleOrganizationId()));

            //判断此处调度批次
            logService.infoLog(logger, "repository", "save(dbHandleEntity)", "repository is started...");
            Long startHandleBatch = System.currentTimeMillis();

            Integer handleBatchNum = handleRepository.findHandleBatchByIncidentId(queryBean.getIncidentId());

            Long endHandleBatch = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleEntity)", String.format("repository is finished,execute time is :%sms", endHandleBatch - startHandleBatch));

            handleBatchNum = handleBatchNum + 1;

            handleEntity.setHandleBatch(String.valueOf(handleBatchNum));

            logService.infoLog(logger, "repository", "save(dbHandleEntity)", "repository is started...");
            Long startHandle = System.currentTimeMillis();

            accessor.save(handleEntity);

            Long endHandle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleEntity)", String.format("repository is finished,execute time is :%sms", endHandle - startHandle));

            //获得处警记录id
            String handleId = handleEntity.getId();
            handleEntity.setIdCode(handleId);

            //关联案件的车辆id 单位id集合
            List<String> organizationIds = new ArrayList<>(); //调派单位信息
            List<String> vehicleIds = new ArrayList<>(); // 调派车辆ids
            List<String> handleOrganizationVehicleIds = new ArrayList<>(); //调派作战车辆id
            Integer equipmentTotal = 0; //调派装备ids

            //保存调派单位信息
            Map<String, HandleOrganizationBean> handleOrganizationBeanMap = saveHandleOrganization(handleOrganizationSaveInputInfo, handleEntity,
                    dicsMap, organizationNameMap, organizationIds);
            //保存调派车辆信息
            Map<String, List<HandleOrganizationVehicleBean>> handleOrganizationVehicleBeanMap = saveHandleOrganizationVehicle(handleOrganizationVehicleSaveInputInfo, handleEntity,
                    userInfo, handleOrganizationBeanMap, dicsMap, organizationNameMap, vehicleIds);
            //保存调派 装备信息
            Map<String, List<HandleOrganizationEquipmentBean>> handleEquipmentBeanMap = saveHandleOrganizationEquipment(handleOrganizationEquipmentSaveInputInfo, handleEntity,
                    userInfo, handleOrganizationBeanMap, dicsMap, organizationNameMap);


            //进行业务逻辑 处理
            String organizationNameVehicle = ""; //调派单位车辆
            String organizationNameEquipment = ""; //调派单位器材
            List<HandleOrganizationBean> handleOrganizationBeans = new ArrayList<>();
            List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeans = new ArrayList<>();
            List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBeans = new ArrayList<>();
            if (handleOrganizationBeanMap != null && handleOrganizationBeanMap.size() > 0) {
                for (String handleOrganizationId : handleOrganizationBeanMap.keySet()) {
                    HandleOrganizationBean handleOrganizationBean = handleOrganizationBeanMap.get(handleOrganizationId);
                    if (handleOrganizationBean != null) {
                        organizationNameVehicle = organizationNameVehicle + " " + handleOrganizationBean.getOrganizationName();
                        organizationNameEquipment = organizationNameEquipment + " " + handleOrganizationBean.getOrganizationName();
                        //单位车辆
                        List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList = handleOrganizationVehicleBeanMap.get(handleOrganizationId);
                        if (handleOrganizationVehicleBeanList != null && handleOrganizationVehicleBeanList.size() > 0) {
                            organizationNameVehicle = organizationNameVehicle + "(" + handleOrganizationVehicleBeanList.size() + "辆）";
                            handleOrganizationVehicleBeans.addAll(handleOrganizationVehicleBeanList);
                            handleOrganizationBean.setHandleOrganizationVehicleBean(handleOrganizationVehicleBeanList);
                        }
                        //单位装备
                        List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBeanList = handleEquipmentBeanMap.get(handleOrganizationId);
                        if (handleOrganizationEquipmentBeanList != null && handleOrganizationEquipmentBeanList.size() > 0) {
                            equipmentTotal = equipmentTotal + handleOrganizationEquipmentBeanList.size();
                            organizationNameEquipment = organizationNameEquipment + "(" + handleOrganizationEquipmentBeanList.size() + "件）";
                            handleOrganizationEquipmentBeans.addAll(handleOrganizationEquipmentBeanList);
                            handleOrganizationBean.setHandleOrganizationEquipmentBean(handleOrganizationEquipmentBeanList);
                        }
                        handleOrganizationBeans.add(handleOrganizationBean);
                        //机构排序
                        handleOrganizationBeans.sort(new Comparator<HandleOrganizationBean>() {
                            @Override
                            public int compare(HandleOrganizationBean o1, HandleOrganizationBean o2) {
                                Integer d1 = o1.getOrganizationOrderNum();
                                Integer d2 = o2.getOrganizationOrderNum();
                                return d1.compareTo(d2);
                            }
                        });

                    }
                }
                handleEntity.setDispatchEquipmentNum(equipmentTotal);
            }

            //保存出动车辆  出动单位
            if (organizationIds != null) {
                handleEntity.setDispatchOrganization(String.valueOf(organizationIds.size()));
            }
            if (vehicleIds != null) {
                handleEntity.setDispatchVehicle(String.valueOf(vehicleIds.size()));
            }
            accessor.save(handleEntity);

            HandleBean res = HandleDispatchTransformUtil.transform(handleEntity, dicsMap, organizationNameMap);
            HandleBean tempHandle = HandleDispatchTransformUtil.transform(handleEntity, dicsMap, organizationNameMap);
            res.setHandleOrganizationBean(handleOrganizationBeans);

            //保存警情里面出动车辆  出动单位信息
            List<String> hisOrganizationIds = handleOrganizationRepository.findOrganizationIdByIncidentId(incidentId);
            List<String> hisVehicleIds = handleOrganizationVehicleRepository.findVehicleIdByIncidentId(incidentId);

            if (hisOrganizationIds == null) {
                hisOrganizationIds = new ArrayList<>();
            }
            if (hisVehicleIds == null) {
                hisVehicleIds = new ArrayList<>();
            }
            hisOrganizationIds.addAll(organizationIds);
            hisVehicleIds.addAll(vehicleIds);

            //更新案件调派车辆单位信息 去重
            List<String> tempOrganizationIds = hisOrganizationIds.stream().distinct().collect(Collectors.toList());
            List<String> tempVehicleIds = hisVehicleIds.stream().distinct().collect(Collectors.toList());

            if (tempOrganizationIds != null) {
                incidentEntity.setDispatchOrganization(String.valueOf(tempOrganizationIds.size()));
            }
            if (tempVehicleIds != null) {
                incidentEntity.setDispatchVehicle(String.valueOf(tempVehicleIds.size()));
            }

            logService.infoLog(logger, "repository", "changeIncidentHandle", "repository is started...");
            Long startIncidentHandle = System.currentTimeMillis();

            accessor.save(incidentEntity);

            Long endIncidentHandle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "changeIncidentHandle", String.format("repository is finished,execute time is :%sms", endIncidentHandle - startIncidentHandle));


            //获得 针对案件已下达的预警信息 未调派车辆 单位去掉预警信息
            List<String> earlyWarningOrganizationIdList = earlyWarningService.findEarlyWarningOrganizationIds(incidentId);
            // 获得 案件调派参与单位
            List<String> oldHandleOrganizationIds = findIncidentHandleOrganization(incidentId);
            List<String> temp = new ArrayList<>();
            temp.addAll(earlyWarningOrganizationIdList);
            temp.removeAll(organizationIds);
            if (oldHandleOrganizationIds != null && oldHandleOrganizationIds.size() > 0) {
                temp.removeAll(oldHandleOrganizationIds);
            }
            if (temp != null && temp.size() > 0) {
                earlyWarningService.removeEarlyWarning(incidentId, String.valueOf(EnableEnum.ENABLE_TRUE.getCode()), temp);
            }

            IncidentBean incidentBean = incidentService.findIncident(incidentId, false);

            //处警单位 推送出车单信息

//            if( incidentBean.getWhetherTestSign() != 1 ){


            logService.infoLog(logger, "websocket", "bathNotifyAction", "websocket is started...");
            Long startIncidentHandleWebscoket = System.currentTimeMillis();

            //根据单位分批次发送消息
            bathNotifyAction(incidentBean, tempHandle, handleOrganizationBeans);

            Long endIncidentHandleWebscoket = System.currentTimeMillis();
            logService.infoLog(logger, "websocket", "bathNotifyAction", String.format("websocket is finished,execute time is :%sms", endIncidentHandleWebscoket - startIncidentHandleWebscoket));

//            Set<String> handleSet = new HashSet<>();
//
//            List<String> handleOrgIds = new ArrayList<>();
//            handleOrgIds.addAll(organizationIds);
//            List<String> handleOrgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(handleOrgIds));
//            handleSet.addAll(handleOrgCodes);
//            HandleWebSocketPushBean handleWebSocketPushBean = new HandleWebSocketPushBean();
//
//            handleWebSocketPushBean.setIncidentBean(incidentBean);
//            handleWebSocketPushBean.setHandleBean(res);
//
//            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_HANDLE.getCode(), handleWebSocketPushBean, handleSet);

            if (tempOrganizationIds != null) {
                incidentBean.setDispatchOrganization(String.valueOf(tempOrganizationIds.size()));
            }
            if (tempVehicleIds != null) {
                incidentBean.setDispatchVehicle(String.valueOf(tempVehicleIds.size()));
            }


            //通知其他相关单位
            Set<String> orgs = new HashSet<>();
            List<String> orgIds = new ArrayList<>();
            //通知处警单位
            orgIds.add(queryBean.getHandleOrganizationId());
            //通知警情相关的单位
            orgIds.add(incidentEntity.getBrigadeOrganizationId());
            orgIds.add(incidentEntity.getSquadronOrganizationId());
            orgIds.add(incidentEntity.getRegisterOrganizationId());
            //通知相关出动单位
            orgIds.addAll(hisOrganizationIds);
            List<String> parentOrganizationId = organizationService.findParentOrganizationIds(orgIds);
            orgs.addAll(parentOrganizationId);
            // 去掉 取消预警的机构id
            if (temp != null && temp.size() > 0) {
                orgs.removeAll(temp);
            }
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(parentOrganizationId));

            orgs.addAll(orgCodes);
            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.NOTIFY_HANDLE.getCode(), incidentBean, orgs);
//            }

            if (handleOrganizationBeans != null && handleOrganizationBeans.size() > 0) {
                //出动单 保存文书
                DocumentSaveInputInfo documentSaveInputInfoHandlde = new DocumentSaveInputInfo();
                documentSaveInputInfoHandlde.setIncidentId(incidentId);
                documentSaveInputInfoHandlde.setDateSourceId(res.getId());
                documentSaveInputInfoHandlde.setTitle(DOCUMENT_TYPE_CCD.getName());
                documentSaveInputInfoHandlde.setContent(DOCUMENT_TYPE_CCD.getName() + organizationNameVehicle);
                documentSaveInputInfoHandlde.setType(DOCUMENT_TYPE_CCD.getCode());
                documentSaveInputInfoHandlde.setFeedbackPerson(userInfo.getPersonName());
                documentSaveInputInfoHandlde.setFeedbackOrganizationId(userInfo.getOrgId());
                documentSaveInputInfoHandlde.setTerminalId(null);
                documentSaveInputInfoHandlde.setRemarks(null);
                documentService.saveDocument(documentSaveInputInfoHandlde);
            }


            if (handleOrganizationEquipmentBeans != null && handleOrganizationEquipmentBeans.size() > 0) {
                //出动单 保存文书
                DocumentSaveInputInfo documentSaveInputInfoHandlde = new DocumentSaveInputInfo();
                documentSaveInputInfoHandlde.setIncidentId(incidentId);
                documentSaveInputInfoHandlde.setDateSourceId(res.getId());
                documentSaveInputInfoHandlde.setTitle(DOCUMENT_TYPE_DPQC.getName());
                documentSaveInputInfoHandlde.setContent(DOCUMENT_TYPE_DPQC.getName() + organizationNameEquipment);
                documentSaveInputInfoHandlde.setType(DOCUMENT_TYPE_DPQC.getCode());
                documentSaveInputInfoHandlde.setFeedbackPerson(userInfo.getPersonName());
                documentSaveInputInfoHandlde.setFeedbackOrganizationId(userInfo.getOrgId());
                documentSaveInputInfoHandlde.setTerminalId(null);
                documentSaveInputInfoHandlde.setRemarks(null);
                documentService.saveDocument(documentSaveInputInfoHandlde);
            }


            //车辆关联案件id 车辆状态变更  更新车辆状态 状态为通知
            if (null != vehicleIds && vehicleIds.size() > 0) {
                logService.infoLog(logger, "repository", "updateEquipmentVehicleIncidentNumber( vehicleIds , incidentId )", "repository is started...");
                Long startVehicleIncidentNumber = System.currentTimeMillis();

                handleOrganizationVehicleRepository.updateEquipmentVehicleIncidentNumber(vehicleIds, incidentId, servletService.getSystemTime());

                Long endVehicleIncidentNumber = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "updateEquipmentVehicleIncidentNumber( vehicleIds , incidentId )", String.format("repository is finished,execute time is :%sms", endVehicleIncidentNumber - startVehicleIncidentNumber));

                vehicleStatusChangeService.updateVehicleStatus(incidentId, handleId, vehicleIds,
                        handleOrganizationVehicleIds, VEHICLE_STATUS_TZ.getCode(), "PC");

            }

            //更新案件状态
            if (Integer.parseInt(INCIDENT_STATUS_TZ.getCode()) > Integer.parseInt(incidentEntity.getIncidentStatusCode())) {

                //保存警情状态变更通知状态
                incidentService.updateIncidentStatus(incidentId, INCIDENT_STATUS_TZ.getCode(), handleId);

                incidentBean.setIncidentStatusCode(INCIDENT_STATUS_TZ.getCode());
                incidentBean.setIncidentStatusName(dicsMap.get("AJZT").get(incidentBean.getIncidentStatusCode()));
                incidentBean.setTransmitTime(currentTime);

            }


            //保存操作记录
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_DISPATCH.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            String desc = String.format("incident dispatch content   incident:%s ,organization:%s , vehicle:%s ", incidentId, JSON.toJSONString(organizationIds), JSON.toJSONString(vehicleIds));
            auditLogSaveInputInfo.setDesc(desc);
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_DISPATCH.getName());

            Map<String, String> otherParams = new HashMap<>();
            pushDataService.pushIncidentHandle(incidentBean, res, otherParams);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveHandle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_HANDLE_FAIL);
        }
    }


    /**
     * 根据车辆ids集合 判断车辆是否可以全部调派
     *
     * @param vehicleIds 车辆ids
     * @return 处警记录
     */
    private Boolean judgeWhetherHandle(List<String> vehicleIds) {
        //获得可调派状态码
        List<String> dispatchStatusList = new ArrayList<>();
        String vehicleDispatchStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleDispatchStatus").getConfigValue();
        if (Strings.isNotBlank(vehicleDispatchStatus)) {
            String[] dispatchStatus = vehicleDispatchStatus.split(",");
            dispatchStatusList = Arrays.asList(dispatchStatus);
        }
//        //判断车辆状态是否属于可调派
//        List<EquipmentVehicleBean> equipmentVehicleBeanList =
//                vehicleService.findVehicleByIdsAndVehicleStatus( vehicleIds , dispatchStatusList  ) ;
//        if( equipmentVehicleBeanList != null && equipmentVehicleBeanList.size() == vehicleIds.size() ){
//            return  true ;
//        }
//        return  false ;
        return true;
    }

    /**
     * 分批次推送处警信息
     */
    private void bathNotifyAction(IncidentBean incidentBean, HandleBean handleBean,
                                  List<HandleOrganizationBean> handleOrganizationBeans) {
        if (handleOrganizationBeans != null && handleOrganizationBeans.size() > 0) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (HandleOrganizationBean handleOrganizationBean : handleOrganizationBeans) {
                        HandleBean copyHandele = handleBean;
                        List<HandleOrganizationBean> orghandle = new ArrayList<>();
                        orghandle.add(handleOrganizationBean);
                        copyHandele.setHandleOrganizationBean(orghandle);
                        Set<String> handleSet = new HashSet<>();

                        List<String> handleOrgIds = new ArrayList<>();
                        handleOrgIds.add(handleOrganizationBean.getOrganizationId());

                        handleSet.addAll(handleOrgIds);
                        List<String> handleOrgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(handleOrgIds));
                        handleSet.addAll(handleOrgCodes);
                        HandleWebSocketPushBean handleWebSocketPushBean = new HandleWebSocketPushBean();

                        handleWebSocketPushBean.setIncidentBean(incidentBean);
                        handleWebSocketPushBean.setHandleBean(copyHandele);
                        //生成 tts播报路径
                        String speakToFileUrl = ttsSpeechService.buildSpeakToFile(incidentBean, copyHandele);
                        handleWebSocketPushBean.setSpeakToFileUrl(speakToFileUrl);

                        ttsCacheService.modifyHandleOrganizationSpeakFileCache("put", handleOrganizationBean.getId(), speakToFileUrl);

                        notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.SAVE_HANDLE.getCode(), handleWebSocketPushBean, handleSet);
                    }


                }
            });
            thread.start();
        }
    }


    /**
     * 保存调派单位
     */
    private Map<String, HandleOrganizationBean> saveHandleOrganization(List<HandleOrganizationSaveInputInfo> queryBean, HandleEntity handleEntity,
                                                                       Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap, List<String> organizationIds) {

        Map<String, HandleOrganizationBean> handleOrganizationBeanMap = new HashMap<>();

        String incidentId = handleEntity.getIncidentId(); //警情id
        String handleId = handleEntity.getId();  //调派单id

        if (null != queryBean && queryBean.size() > 0) {
            for (HandleOrganizationSaveInputInfo handleOrganization : queryBean) {
                //保存 处警单位
                HandleOrganizationEntity handleOrganizationEntity = HandleDispatchTransformUtil.transform(handleOrganization, incidentId, handleId, organizationNameMap);
                handleOrganizationEntity.setNoticeTime(servletService.getSystemTime());

                logService.infoLog(logger, "repository", "save(dbHandleOrganizationEntity)", "repository is started...");
                Long startHandleOrganization = System.currentTimeMillis();

                accessor.save(handleOrganizationEntity);

                Long endHandleOrganization = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationEntity)", String.format("repository is finished,execute time is :%sms", endHandleOrganization - startHandleOrganization));

                //获得处警信息id
                String organizationId = handleOrganizationEntity.getOrganizationId();
                organizationIds.add(organizationId);

                OrganizationBean organizationBean = organizationService.findOrganizationByOrganizationId(organizationId);
                HandleOrganizationBean handleOrganizationBean = HandleDispatchTransformUtil.transform(handleOrganizationEntity, dicsMap, organizationNameMap);
                HandleDispatchTransformUtil.transform(handleOrganizationBean, organizationBean);
                handleOrganizationBean.setHandleBatch(handleEntity.getHandleBatch());
                handleOrganizationBean.setHandleStartTime(handleEntity.getHandleStartTime());
                handleOrganizationBean.setHandleEndTime(handleEntity.getHandleEndTime());

                handleOrganizationBeanMap.put(handleOrganizationBean.getOrganizationId(), handleOrganizationBean);
            }


        }
        return handleOrganizationBeanMap;

    }

    /**
     * 保存调派车辆
     */
    private Map<String, List<HandleOrganizationVehicleBean>> saveHandleOrganizationVehicle(
            List<HandleOrganizationVehicleSaveInputInfo> queryBean, HandleEntity handleEntity,
            UserInfo userInfo, Map<String, HandleOrganizationBean> handleOrganizationBeanMap,
            Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap,
            List<String> vehicleIds) {
        Map<String, List<HandleOrganizationVehicleBean>> handleVehicleBeanMap = new HashMap<>();

        String incidentId = handleEntity.getIncidentId(); //警情id
        String handleId = handleEntity.getId();  //调派单id


        if (null != queryBean && queryBean.size() > 0) {

            for (HandleOrganizationVehicleSaveInputInfo handleOrganizationVehicleSaveInputInfo : queryBean) {
                //添加关联案件的车辆id
                vehicleIds.add(handleOrganizationVehicleSaveInputInfo.getVehicleId());

            }
            //根据车辆id查询车辆详情列表
            List<EquipmentVehicleBean> equipmentVehicleBeans = vehicleService.findVehicleByIds(vehicleIds);
            Map<String, EquipmentVehicleBean> equipmentVehicleMap = new HashMap<>();
            for (EquipmentVehicleBean equipmentVehicleBean : equipmentVehicleBeans) {
                equipmentVehicleMap.put(equipmentVehicleBean.getId(), equipmentVehicleBean);
            }
            List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeans = new ArrayList<>();

            List<HandleOrganizationVehicleEntity> handleOrganizationVehicleEntityList = new ArrayList<>();
            for (HandleOrganizationVehicleSaveInputInfo handleOrganizationVehicleSaveInputInfo : queryBean) {
                String handlePoliceId = handleOrganizationBeanMap.get(handleOrganizationVehicleSaveInputInfo.getOrganizationId()).getId();
                HandleOrganizationVehicleEntity handleOrganizationVehicleEntity = HandleDispatchTransformUtil.transform(handleOrganizationVehicleSaveInputInfo, incidentId, handleId,
                        handlePoliceId, handleOrganizationVehicleSaveInputInfo.getOrganizationId(), servletService.getSystemTime());
                EquipmentVehicleBean vehicleBean = equipmentVehicleMap.get(handleOrganizationVehicleEntity.getVehicleId());
                if (vehicleBean != null) {
                    handleOrganizationVehicleEntity.setVehicleNumber(vehicleBean.getVehicleNumber());
                }

                handleOrganizationVehicleEntity.setNoticeTime(servletService.getSystemTime());
                handleOrganizationVehicleEntityList.add(handleOrganizationVehicleEntity);
            }

            logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntityList)", "repository is started...");
            Long startHandleOrganizationVehicle = System.currentTimeMillis();

            accessor.save(handleOrganizationVehicleEntityList);
            //保存参战人员
            SystemConfigurationBean config = systemConfigurationService.getSystemConfigByConfigType("sftbbcscczry");
            String sftbbcscczry=null;//是否同步保存随车参战人员
            if (config!=null){
                sftbbcscczry=config.getConfigValue();
            }
            if (!StringUtils.isBlank(sftbbcscczry)&&"1".equals(sftbbcscczry)){
                //获取值班开始和结束时间
                Date now=new Date(servletService.getSystemTime());
                Long dutyStart=DateUtils.getMinTime(now).getTime();
                Long dutyEnd=DateUtils.getMaxTime(now).getTime();
                if (vehicleIds!=null&&!vehicleIds.isEmpty()){
//                    List<Object[]> ret = equipmentVehiclePersonRepository.findVehiclePersonByDutyTimeAndVhicleIds(vehicleIds, dutyStart, dutyEnd);
                    //暂时不过滤值班信息
                    List<Object[]> ret = equipmentVehiclePersonRepository.findVehiclePersonByVhicleIds(vehicleIds);
                    if (ret!=null&&!ret.isEmpty()){
                        List<ParticipantFeedbackEntity> participantFeedbackEntities=new ArrayList<>();
                        for (Object[] objects : ret) {
                            String vehicleId=IncidentTransformUtil.toString(objects[0]);
                            String personId=IncidentTransformUtil.toString(objects[1]);
                            String personName=IncidentTransformUtil.toString(objects[2]);
                            String organizationId=IncidentTransformUtil.toString(objects[3]);
                            String contactNumber=IncidentTransformUtil.toString(objects[4]);
                            ParticipantFeedbackEntity entity=new ParticipantFeedbackEntity();
                            entity.setHandleId(handleId);
                            entity.setIncidentId(incidentId);
                            entity.setOrganizationId(organizationId);
                            entity.setPersonId(personId);
                            entity.setPersonName(personName);
                            entity.setPersonPhone(contactNumber);
                            entity.setState(ParticipantPersonStateEnum.PERSON_DISPATCHED.getCode());
                            entity.setVehicleId(vehicleId);
                            participantFeedbackEntities.add(entity);
                        }
                        accessor.save(participantFeedbackEntities);
                    }
                }
            }
            Long endHandleOrganizationVehicle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntityList)", String.format("repository is finished,execute time is :%sms", endHandleOrganizationVehicle - startHandleOrganizationVehicle));

            //根据车辆id查询车辆详情列表

            for (HandleOrganizationVehicleEntity handleOrganizationVehicleEntity : handleOrganizationVehicleEntityList) {
                HandleOrganizationVehicleBean handleOrganizationVehicleBean = HandleDispatchTransformUtil.transform(handleOrganizationVehicleEntity, dicsMap, organizationNameMap);
                HandleDispatchTransformUtil.transform(handleOrganizationVehicleBean, equipmentVehicleMap.get(handleOrganizationVehicleEntity.getVehicleId()));
                handleOrganizationVehicleBean.setHandleBatch(handleEntity.getHandleBatch());
                handleOrganizationVehicleBean.setHandleStartTime(handleEntity.getHandleStartTime());
                handleOrganizationVehicleBean.setHandleEndTime(handleEntity.getHandleEndTime());

                handleOrganizationVehicleBeans.add(handleOrganizationVehicleBean);

                List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList = handleVehicleBeanMap.get(handleOrganizationVehicleBean.getOrganizationId());
                if (handleOrganizationVehicleBeanList == null) {
                    handleOrganizationVehicleBeanList = new ArrayList<>();
                }
                handleOrganizationVehicleBeanList.add(handleOrganizationVehicleBean);
                //车辆排序
                handleOrganizationVehicleBeanList.sort(new Comparator<HandleOrganizationVehicleBean>() {
                    @Override
                    public int compare(HandleOrganizationVehicleBean o1, HandleOrganizationVehicleBean o2) {
                        Integer d1 = o1.getVehicleOrderNum();
                        Integer d2 = o2.getVehicleOrderNum();
                        return d1.compareTo(d2);
                    }
                });
                handleVehicleBeanMap.put(handleOrganizationVehicleBean.getOrganizationId(), handleOrganizationVehicleBeanList);

            }
        }

        return handleVehicleBeanMap;
    }


    /**
     * 保存调派装备
     */
    private Map<String, List<HandleOrganizationEquipmentBean>> saveHandleOrganizationEquipment(List<HandleOrganizationEquipmentSaveInputInfo> queryBean,
                                                                                               HandleEntity handleEntity,
                                                                                               UserInfo userInfo, Map<String, HandleOrganizationBean> handleOrganizationBeanMap,
                                                                                               Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {


        Map<String, List<HandleOrganizationEquipmentBean>> handleEquipmentBeanMap = new HashMap<>();

        String incidentId = handleEntity.getIncidentId(); //警情id
        String handleId = handleEntity.getId();  //调派单id

        //保存处警装备
        if (null != queryBean && queryBean.size() > 0) {

            List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBeans = new ArrayList<>();

            List<HandleOrganizationEquipmentEntity> handleOrganizationEquipmentEntityList = new ArrayList<>();

            for (HandleOrganizationEquipmentSaveInputInfo handleOrganizationEquipmentSaveInputInfo : queryBean) {
                String handlePoliceId = handleOrganizationBeanMap.get(handleOrganizationEquipmentSaveInputInfo.getOrganizationId()).getId();
                HandleOrganizationEquipmentEntity handleOrganizationEquipmentEntity = HandleDispatchTransformUtil.transform(handleOrganizationEquipmentSaveInputInfo,
                        incidentId, handleId, handlePoliceId, handleOrganizationEquipmentSaveInputInfo.getOrganizationId());
                handleOrganizationEquipmentEntity.setDispatchStartTime(handleEntity.getHandleStartTime());
                handleOrganizationEquipmentEntity.setDispatchEndTime(handleEntity.getHandleEndTime());

                handleOrganizationEquipmentEntityList.add(handleOrganizationEquipmentEntity);
            }

            logService.infoLog(logger, "repository", "save(dbHandleOrganizationEquipmentEntityList)", "repository is started...");
            Long startHandleOrganizationVehicle = System.currentTimeMillis();

            accessor.save(handleOrganizationEquipmentEntityList);

            Long endHandleOrganizationVehicle = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleOrganizationEquipmentEntityList)", String.format("repository is finished,execute time is :%sms", endHandleOrganizationVehicle - startHandleOrganizationVehicle));


            for (HandleOrganizationEquipmentEntity handleOrganizationEquipmentEntity : handleOrganizationEquipmentEntityList) {
                HandleOrganizationEquipmentBean handleOrganizationEquipmentBean = HandleDispatchTransformUtil.transform(handleOrganizationEquipmentEntity, dicsMap, organizationNameMap);
                handleOrganizationEquipmentBean.setHandleBatch(handleEntity.getHandleBatch());
                handleOrganizationEquipmentBean.setHandleStartTime(handleEntity.getHandleStartTime());
                handleOrganizationEquipmentBean.setHandleEndTime(handleEntity.getHandleEndTime());

                handleOrganizationEquipmentBeans.add(handleOrganizationEquipmentBean);


                List<HandleOrganizationEquipmentBean> handleOrganizationEquipmentBeanList = handleEquipmentBeanMap.get(handleOrganizationEquipmentBean.getOrganizationId());
                if (handleOrganizationEquipmentBeanList == null) {
                    handleOrganizationEquipmentBeanList = new ArrayList<>();
                }
                handleOrganizationEquipmentBeanList.add(handleOrganizationEquipmentBean);

                handleEquipmentBeanMap.put(handleOrganizationEquipmentBean.getOrganizationId(), handleOrganizationEquipmentBeanList);

            }
        }

        return handleEquipmentBeanMap;

    }


    /**
     * 保存 微型消防站
     */
    private Map<String, List<HandleMiniatureStationBean>> saveHandleMiniatureStation(
            List<HandleMiniatureStationSaveInputInfo> queryBean, HandleEntity handleEntity, UserInfo userInfo,
            Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {

        Map<String, List<HandleMiniatureStationBean>> handleMiniatureStationBeanMap = new HashMap<>();

        String incidentId = handleEntity.getIncidentId(); //警情id
        String handleId = handleEntity.getId();  //调派单id

        if (null != queryBean && queryBean.size() > 0) {

            List<HandleMiniatureStationEntity> handleMiniatureStationEntityList = new ArrayList<>();

            for (HandleMiniatureStationSaveInputInfo handleMiniatureStation : queryBean) {
                //保存 处警单位
                HandleMiniatureStationEntity handleMiniatureStationEntity = HandleDispatchTransformUtil.transform(handleMiniatureStation, incidentId, handleId);

                handleMiniatureStationEntity.setHandleTime(servletService.getSystemTime());
                handleMiniatureStationEntity.setHandleOrganizationId(userInfo.getOrgId());
                handleMiniatureStationEntity.setHandlePersonNumber(userInfo.getAccount());
                handleMiniatureStationEntity.setHandleSeatNumber(userInfo.getAgentNum());

                handleMiniatureStationEntityList.add(handleMiniatureStationEntity);

            }

            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationEntity)", "repository is started...");
            Long startHandleOrganization = System.currentTimeMillis();

            accessor.save(handleMiniatureStationEntityList);

            Long endHandleOrganization = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationEntity)", String.format("repository is finished,execute time is :%sms", endHandleOrganization - startHandleOrganization));


            //根据车辆id查询车辆详情列表
            for (HandleMiniatureStationEntity handleMiniatureStationEntity : handleMiniatureStationEntityList) {
                HandleMiniatureStationBean handleMiniatureStationBean = HandleDispatchTransformUtil.transform(handleMiniatureStationEntity, dicsMap, organizationNameMap);

                handleMiniatureStationBean.setHandleBatch(handleEntity.getHandleBatch());

                List<HandleMiniatureStationBean> handleMiniatureStationBeanList =
                        handleMiniatureStationBeanMap.get(handleMiniatureStationBean.getOrganizationId());
                if (handleMiniatureStationBeanList == null) {
                    handleMiniatureStationBeanList = new ArrayList<>();
                }
                handleMiniatureStationBeanList.add(handleMiniatureStationBean);
                handleMiniatureStationBeanMap.put(handleMiniatureStationBean.getOrganizationId(), handleMiniatureStationBeanList);

            }
        }

        return handleMiniatureStationBeanMap;
    }


    /**
     * {@inheritDoc}
     *
     * @see #updateHandleStatus(String, String, String, Integer)
     */
    @Override
    public HandleBean updateHandleStatus(String incidentId, String handleId, String organizationId, Integer handleWay) {
        if (Strings.isBlank(incidentId) || Strings.isBlank(handleId) || Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "updateHandleStatus", "  handleId or organizationId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateHandleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            HandleBean res = null;

            IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);

            if (incidentEntity == null) {
                logService.infoLog(logger, "service", "updateHandleStatus", "  incident entity  is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }

            buildHandleOrganizationStatus(handleId, organizationId, handleWay);
            if (incidentEntity.getReceiveTime() == null) {
                //更新警情接收时间
                incidentEntity.setReceiveTime(servletService.getSystemTime());

                logService.infoLog(logger, "repository", "save(IncidentEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(incidentEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(IncidentEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            res = findHandleByHandleId(handleId);

            //去掉无效数据
            res.setDispatchOrganization(null);
            res.setDispatchVehicle(null);
            //针对 出动单位去掉 出动单位下出动车辆信息
            for (HandleOrganizationBean handleOrganizationBean : res.getHandleOrganizationBean()) {
                handleOrganizationBean.setHandleOrganizationEquipmentBean(null);
                handleOrganizationBean.setHandleOrganizationVehicleBean(null);
            }

//            //消息通知案件参与单位
            Set<String> orgSet = new HashSet<>();

            List<String> orgIds = incidentService.findIncidentParticipantOrganizationId(incidentId);
            orgSet.addAll(orgIds);
            List<String> orgCodes = organizationService.findOrganizationCodesByIds(new ArrayList<>(orgSet));
            orgSet.addAll(orgCodes);

            List<ReceiverMessageBean> receivers = new ArrayList<>();

            //获取机构下的用户
            List<AgentBean> agentBeanList = agentService.findAgentByOrganizationId(res.getHandleOrganizationId());

            ReceiverMessageBean receiverMessageBean = new ReceiverMessageBean();
            receiverMessageBean.setId(res.getHandlePersonNumber());
            receiverMessageBean.setType("user");
            receivers.add(receiverMessageBean);

            if (agentBeanList != null && agentBeanList.size() > 0) {
                for (AgentBean agent : agentBeanList
                        ) {
                    if (agent.getPersonBean() != null) {
                        receiverMessageBean.setId(agent.getPersonBean().getAccount());
                        receivers.add(receiverMessageBean);

                    }
                }
            }

            HandleWebSocketPushBean handleWebSocketPushBean = new HandleWebSocketPushBean();

            IncidentBean incidentBean = incidentService.findIncident(incidentId, false);
            handleWebSocketPushBean.setIncidentBean(incidentBean);
            handleWebSocketPushBean.setHandleBean(res);

            if (handleWay == 1) {
//                notifyActionService.pushMessage(WebsocketCodeEnum.UPDATE_HANDLE_PERSON_TIME.getCode(), handleWebSocketPushBean, receivers);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_HANDLE_PERSON_TIME.getCode(), handleWebSocketPushBean, orgSet);
            } else {
//                notifyActionService.pushMessage(WebsocketCodeEnum.UPDATE_HANDLE_SYSTEM_TIME.getCode(), handleWebSocketPushBean, receivers);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_HANDLE_SYSTEM_TIME.getCode(), handleWebSocketPushBean, orgSet);

            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateHandleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateHandleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SIGN_HANDLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #buildHandleOrganizationStatus(String, String, Integer)
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void buildHandleOrganizationStatus(String handleId, String organizationId, Integer handleWay) {
        if (Strings.isBlank(handleId) || Strings.isBlank(organizationId)) {
            logService.infoLog(logger, "service", "buildHandleOrganizationStatus", "  handleId or organizationId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "method", "buildHandleOrganizationStatus", "private method is started...");
            Long logStart = System.currentTimeMillis();

            JudgeUpdateHandleStatus(handleId, organizationId, handleWay);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "method", "buildIncidentVehicleStatus", String.format("private method is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncidentVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SIGN_HANDLE_FAIL);

        }

    }


    private void JudgeUpdateHandleStatus(String handleId, String organizationId, Integer handleWay) {
        logService.infoLog(logger, "method", "JudgeUpdateHandleStatus", "private method is started...");
        Long logStart = System.currentTimeMillis();

        logService.infoLog(logger, "method", "updateIncidentStatus", "private method is started...");
        Long logStartHandleStatus = System.currentTimeMillis();
        //查询处警单信息
        HandleEntity handleEntity = accessor.getById(handleId, HandleEntity.class);
        if (handleEntity == null) {
            return;
        }

        //查询案件处警单位信息
        List<HandleOrganizationEntity> handleOrganizationEntityList = handleOrganizationRepository.findHandleOrganizationByHandleId(handleId);
        Boolean isModifyStatus = true; //单位处警单状态是否 影响 处警单状态
        for (HandleOrganizationEntity handleOrganizationEntity : handleOrganizationEntityList) {
            if (handleOrganizationEntity.getOrganizationId().equals(organizationId)) {  // 修改本单位处警单状态

            } else {
                // 只要存在一处警单位的状态为已通知状态 ，则不修改状态
                if (STATUS_GIVEN.getCode().equals(handleOrganizationEntity.getHandlePoliceStatus())) {
                    isModifyStatus = false;
                    break;
                }
            }
        }

        if (isModifyStatus) {
            handleEntity.setHandleStatus(STATUS_SIGNED.getCode());
            accessor.save(handleEntity);
        }

        HandleOrganizationEntity handleOrganizationEntity = handleOrganizationRepository.findHandleOrganizationByHandleIdAndOrganizationId(handleId, organizationId);
        Long currentTime = servletService.getSystemTime();
        if (handleWay == 1) {
            if (null == handleOrganizationEntity.getPersonFeedbackPersonTime()) {
                handleOrganizationEntity.setHandlePoliceStatus(STATUS_SIGNED.getCode());
                handleOrganizationEntity.setPersonFeedbackPersonTime(currentTime);
            }
        }
        if (null == handleOrganizationEntity.getSystemFeedbackTime()) {
            handleOrganizationEntity.setSystemFeedbackTime(currentTime);
        }
        UserInfo userInfo = userService.getUserInfo();
        handleOrganizationEntity.setFeedbackPersonId(userInfo.getAccount());
        handleOrganizationEntity.setFeedbackPersonName(userInfo.getPersonName());

        accessor.save(handleOrganizationEntity);

        Long logEndHandleStatus = System.currentTimeMillis();
        logService.infoLog(logger, "method", "updateVehicleStatus", String.format("private method is finished,execute time is :%sms", logEndHandleStatus - logStartHandleStatus));


        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "method", "JudgeUpdateCase", String.format("private method is finished,execute time is :%sms", logEnd - logStart));
    }


    /**
     * {@inheritDoc}
     *
     * @see HandleService#findHandle(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<HandleBean> findHandle(String incidentId, String organizationId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandle", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandle", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<HandleBean> res = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            logService.infoLog(logger, "repository", "findHandleByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            //查询调派记录
            List<HandleEntity> handleEntityList = null;
            if (Strings.isBlank(organizationId)) {
                handleEntityList = handleRepository.findHandleByIncidentId(incidentId);
            } else {
                handleEntityList = handleRepository.findHandleByIncidentIdAndHandleOrganizationId(incidentId, organizationId);
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //查询调派微站信息
            List<HandleMiniatureStationBean> handleMiniatureStationBeanList = handleMiniatureStationService.findHandleMiniatureStation(incidentId, null);
            //将调派微站按照handleId分组
            Map<String, List<HandleMiniatureStationBean>> handelMiniatureBeansMap = new HashMap<>();
            if (handleMiniatureStationBeanList != null && handleMiniatureStationBeanList.size() > 0) {
                for (HandleMiniatureStationBean handleMiniatureStationBean : handleMiniatureStationBeanList) {
                    String handleId = handleMiniatureStationBean.getHandleId();
                    if (Strings.isNotBlank(handleId)) {
                        List<HandleMiniatureStationBean> list = handelMiniatureBeansMap.get(handleId);
                        if (list == null) {
                            list = new ArrayList<>();
                            handelMiniatureBeansMap.put(handleId, list);
                        }
                        list.add(handleMiniatureStationBean);
                    }
                }
            }

            //查询调派单位车辆信息
            List<HandleOrganizationVehicleBean> vehicleBeanList = findHandleOrganizationVehicle(incidentId, organizationId, false);
            //将车辆信息按照handlePoliceId分组
            Map<String, List<HandleOrganizationVehicleBean>> handlePoliceIdAndVehicleBeansMap = new HashMap<>();
            if (vehicleBeanList != null && vehicleBeanList.size() > 0) {
                for (HandleOrganizationVehicleBean vehicle : vehicleBeanList) {
                    String handlePoliceId = vehicle.getHandlePoliceId();
                    if (Strings.isNotBlank(handlePoliceId)) {
                        List<HandleOrganizationVehicleBean> list = handlePoliceIdAndVehicleBeansMap.get(handlePoliceId);
                        if (list == null) {
                            list = new ArrayList<>();
                            handlePoliceIdAndVehicleBeansMap.put(handlePoliceId, list);
                        }
                        list.add(vehicle);
                    }
                }
            }
            //查询调派单位信息
            List<HandleOrganizationBean> handleOrganizationBeanList = findHandleOrganization(incidentId, organizationId);
            Map<String, List<HandleOrganizationBean>> handleIdAndHandleOrganizationBeansMap = new HashMap<>();
            if (handleOrganizationBeanList != null && handleOrganizationBeanList.size() > 0) {
                //根据map分组信息 将装备信息和车辆信息装配到 对应调派信息bean中
                for (HandleOrganizationBean handleOrganizationBean : handleOrganizationBeanList) {
                    String handlePoliceId = handleOrganizationBean.getId();
                    if (Strings.isNotBlank(handlePoliceId)) {
                        handleOrganizationBean.setHandleOrganizationVehicleBean(handlePoliceIdAndVehicleBeansMap.get(handlePoliceId));
                    }
                }
                //将调派信息按照handleId分组
                for (HandleOrganizationBean handleOrganization : handleOrganizationBeanList) {
                    String handleId = handleOrganization.getHandleId();
                    if (Strings.isNotBlank(handleId)) {
                        List<HandleOrganizationBean> list = handleIdAndHandleOrganizationBeansMap.get(handleId);
                        if (list == null) {
                            list = new ArrayList<>();
                            handleIdAndHandleOrganizationBeansMap.put(handleId, list);
                        }
                        list.add(handleOrganization);
                    }

                }
            }

            //构建调派记录
            if (handleEntityList != null && handleEntityList.size() > 0) {
                // 查询机构id-名称缓存map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                for (HandleEntity entity : handleEntityList) {
                    HandleBean bean = HandleDispatchTransformUtil.transform(entity, dicsMap, organizationNameMap);
                    if (bean != null && Strings.isNotBlank(bean.getId())) {
                        //根据map分组信息 将调派信息装配到 对应调派记录bean中
                        String handleId = bean.getId();
                        bean.setHandleOrganizationBean(handleIdAndHandleOrganizationBeansMap.get(handleId));
                        bean.setHandleMiniatureStationBean(handelMiniatureBeansMap.get(handleId));
                    }
                    res.add(bean);
                    //调派排序
                    res.sort(new Comparator<HandleBean>() {
                        @Override
                        public int compare(HandleBean o1, HandleBean o2) {
                            Integer d1 = Integer.parseInt(o1.getHandleBatch());
                            Integer d2 = Integer.parseInt(o2.getHandleBatch());
                            return d1.compareTo(d2);
                        }
                    });

                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see HandleService#findHandleGroup(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<HandleGroupBean> findHandleGroup(String incidentId, String organizationId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandleGroup", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleGroup", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleGroupBean> res = new ArrayList<>();

            List<HandleBean> handleBeanList = findHandle(incidentId, organizationId);

            if (handleBeanList != null && handleBeanList.size() > 0) {
                Map<String, HandleGroupBean> handleGroupBeanMap = new HashMap<>();
                Map<String, HandleOrganizationGroupBean> handleOrganizationGroupBeanMap = new HashMap<>();
                Map<String, List<String>> handleGroupIdMap = new HashMap<>();
                for (HandleBean handleBean : handleBeanList) {
                    //获得批次调派单位信息
                    List<HandleOrganizationBean> handleOrganizationBean = handleBean.getHandleOrganizationBean();
                    if (handleOrganizationBean != null && handleOrganizationBean.size() > 0) {

                        String handleBatch = "1";
                        if (handleGroupBeanMap.keySet() != null && handleGroupBeanMap.keySet().size() > 0) {
                            handleBatch = "2";
                        }

                        HandleGroupBean handleGroupBean = handleGroupBeanMap.get(handleBatch);
                        if (handleGroupBean == null) {
                            handleGroupBean = new HandleGroupBean();
                            handleGroupBean.setHandleBatch(handleBatch);
                            handleGroupBean.setIncidentId(incidentId);
                            List<HandleOrganizationGroupBean> handleOrganizationGroupBean = new ArrayList<>();
                            handleGroupBean.setHandleOrganizationBean(handleOrganizationGroupBean);
                        }
                        handleGroupBeanMap.put(handleBatch, handleGroupBean);

                        buildGroupHandle(handleOrganizationGroupBeanMap, handleBatch, handleGroupIdMap, handleOrganizationBean);
                    }

                }
                for (String handleGroupKey : handleGroupBeanMap.keySet()) {
                    HandleGroupBean handleGroupBean = handleGroupBeanMap.get(handleGroupKey);
                    List<String> handleGroupOrganizationkey = handleGroupIdMap.get(handleGroupKey);

                    for (String key : handleGroupOrganizationkey) {
                        HandleOrganizationGroupBean handleOrganizationGroupBean = handleOrganizationGroupBeanMap.get(key);
                        List<HandleOrganizationGroupBean> handleOrganizationBean = handleGroupBean.getHandleOrganizationBean();
                        handleOrganizationBean.add(handleOrganizationGroupBean);
                        //出动车辆排序
                        handleOrganizationBean.sort(new Comparator<HandleOrganizationGroupBean>() {
                            @Override
                            public int compare(HandleOrganizationGroupBean o1, HandleOrganizationGroupBean o2) {
                                Integer d1 = o1.getOrganizationOrderNum();
                                Integer d2 = o2.getOrganizationOrderNum();
                                return d1.compareTo(d2);
                            }
                        });
                    }

                }
                res.addAll(handleGroupBeanMap.values());
                //出动车辆排序
                res.sort(new Comparator<HandleGroupBean>() {
                    @Override
                    public int compare(HandleGroupBean o1, HandleGroupBean o2) {
                        Integer d1 = Integer.parseInt(o1.getHandleBatch());
                        Integer d2 = Integer.parseInt(o2.getHandleBatch());
                        return d1.compareTo(d2);
                    }
                });
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleGroup", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleGroup", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }

    /**
     * 调派车辆构建 批次 机构分组
     */
    private void buildGroupHandle(Map<String, HandleOrganizationGroupBean> handleOrganizationGroupBeanMap,
                                  String handleBatch, Map<String, List<String>> handleGroupIdMap,
                                  List<HandleOrganizationBean> handleOrganizationBeanList) {
        if (handleOrganizationBeanList != null && handleOrganizationBeanList.size() > 0) {
            for (HandleOrganizationBean handleOrganizationBean : handleOrganizationBeanList) {
                String handleOrganizationGroupKey = handleBatch + "-" + handleOrganizationBean.getOrganizationId();

                List<String> handleGroupIdList = handleGroupIdMap.get(handleBatch);
                if (handleGroupIdList == null) {
                    handleGroupIdList = new ArrayList<>();
                }
                if (!handleGroupIdList.contains(handleOrganizationGroupKey)) {
                    handleGroupIdList.add(handleOrganizationGroupKey);
                    handleGroupIdMap.put(handleBatch, handleGroupIdList);
                }


                HandleOrganizationGroupBean handleOrganizationGroupBean = handleOrganizationGroupBeanMap.get(handleOrganizationGroupKey);
                if (handleOrganizationGroupBean == null) {
                    handleOrganizationGroupBean = new HandleOrganizationGroupBean();

                    handleOrganizationGroupBean.setIncidentId(handleOrganizationBean.getIncidentId());

                    handleOrganizationGroupBean.setOrganization(handleOrganizationBean.getOrganization());
                    handleOrganizationGroupBean.setOrganizationId(handleOrganizationBean.getOrganizationId());
                    handleOrganizationGroupBean.setOrganizationName(handleOrganizationBean.getOrganizationName());

                    handleOrganizationGroupBean.setOrganizationCode(handleOrganizationBean.getOrganizationCode());
                    handleOrganizationGroupBean.setOrganizationName(handleOrganizationBean.getOrganizationName());

                    handleOrganizationGroupBean.setPinyinHeader(handleOrganizationBean.getPinyinHeader());

                    handleOrganizationGroupBean.setLongitude(handleOrganizationBean.getLongitude());
                    handleOrganizationGroupBean.setLatitude(handleOrganizationBean.getLatitude());
                    handleOrganizationGroupBean.setHeight(handleOrganizationBean.getHeight());

                    handleOrganizationGroupBean.setOrganizationParentId(handleOrganizationBean.getOrganizationParentId());
                    handleOrganizationGroupBean.setOrganizationOrderNum(handleOrganizationBean.getOrganizationOrderNum());

                    handleOrganizationGroupBean.setDistrictCode(handleOrganizationBean.getDistrictCode());
                    handleOrganizationGroupBean.setDistrictName(handleOrganizationBean.getDistrictName());

                    handleOrganizationGroupBean.setContactPerson(handleOrganizationBean.getContactPerson());
                    handleOrganizationGroupBean.setContactPersonName(handleOrganizationBean.getContactPersonName());
                    handleOrganizationGroupBean.setDispatchPhone(handleOrganizationBean.getDispatchPhone());

                    handleOrganizationGroupBean.setMailCode(handleOrganizationBean.getMailCode());
                    handleOrganizationGroupBean.setFaxNumber(handleOrganizationBean.getFaxNumber());
                    handleOrganizationGroupBean.setContactPhone(handleOrganizationBean.getContactPhone());
                    handleOrganizationGroupBean.setEmail(handleOrganizationBean.getEmail());
                    handleOrganizationGroupBean.setContactPhone(handleOrganizationBean.getContactPhone());

                    handleOrganizationGroupBean.setHandleOrganizationVehicleBean(new ArrayList<>());
                }
                List<HandleOrganizationVehicleBean> handleOrganizationVehicleBean = handleOrganizationGroupBean.getHandleOrganizationVehicleBean();
                handleOrganizationVehicleBean.addAll(handleOrganizationBean.getHandleOrganizationVehicleBean());
                //出动车辆排序
                handleOrganizationVehicleBean.sort(new Comparator<HandleOrganizationVehicleBean>() {
                    @Override
                    public int compare(HandleOrganizationVehicleBean o1, HandleOrganizationVehicleBean o2) {
                        Integer d1 = Integer.parseInt(o1.getHandleBatch());
                        Integer d2 = Integer.parseInt(o2.getHandleBatch());
                        return d1.compareTo(d2);
                    }
                });
                handleOrganizationGroupBean.setHandleOrganizationVehicleBean(handleOrganizationVehicleBean);

                handleOrganizationGroupBeanMap.put(handleOrganizationGroupKey, handleOrganizationGroupBean);
            }
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see HandleService#findHandle(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public HandleBean findHandleByHandleId(String id) {
        if (Strings.isBlank(id)) {
            logService.infoLog(logger, "service", "findHandleByHandleId", "handleId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleByHandleId", "service is started...");
            Long logStart = System.currentTimeMillis();


            HandleBean res = null;

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            logService.infoLog(logger, "repository", "getById(dbHandleEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            //查询调派记录
            HandleEntity handleEntity = accessor.getById(id, HandleEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getById(dbHandleEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            if (handleEntity != null && handleEntity.isValid()) {
                String incidentId = handleEntity.getIncidentId();


                //查询调派微站信息
                List<HandleMiniatureStationBean> handleMiniatureStationBeanList = handleMiniatureStationService.findHandleMiniatureStation(incidentId, id);
                //将调派微站按照handleId分组
                Map<String, List<HandleMiniatureStationBean>> handelMiniatureBeansMap = new HashMap<>();
                if (handleMiniatureStationBeanList != null && handleMiniatureStationBeanList.size() > 0) {
                    for (HandleMiniatureStationBean handleMiniatureStationBean : handleMiniatureStationBeanList) {
                        String handleId = handleMiniatureStationBean.getHandleId();
                        if (Strings.isNotBlank(handleId)) {
                            List<HandleMiniatureStationBean> list = handelMiniatureBeansMap.get(handleId);
                            if (list == null) {
                                list = new ArrayList<>();
                                handelMiniatureBeansMap.put(handleId, list);
                            }
                            list.add(handleMiniatureStationBean);
                        }
                    }
                }


                //查询调派单位车辆信息
                List<HandleOrganizationVehicleBean> vehicleBeanList = findHandleOrganizationVehicle(incidentId, null, false);
                //将车辆信息按照handlePoliceId分组
                Map<String, List<HandleOrganizationVehicleBean>> handlePoliceIdAndVehicleBeansMap = new HashMap<>();
                if (vehicleBeanList != null && vehicleBeanList.size() > 0) {
                    for (HandleOrganizationVehicleBean vehicle : vehicleBeanList) {
                        String handlePoliceId = vehicle.getHandlePoliceId();
                        if (Strings.isNotBlank(handlePoliceId)) {
                            List<HandleOrganizationVehicleBean> list = handlePoliceIdAndVehicleBeansMap.get(handlePoliceId);
                            if (list == null) {
                                list = new ArrayList<>();
                                handlePoliceIdAndVehicleBeansMap.put(handlePoliceId, list);
                            }
                            list.add(vehicle);
                        }
                    }
                }

                //查询调派单位信息
                List<HandleOrganizationBean> handleOrganizationBeanList = findHandleOrganization(incidentId, null);
                Map<String, List<HandleOrganizationBean>> handleIdAndHandleOrganizationBeansMap = new HashMap<>();
                if (handleOrganizationBeanList != null && handleOrganizationBeanList.size() > 0) {
                    //根据map分组信息 将装备信息和车辆信息装配到 对应调派信息bean中
                    for (HandleOrganizationBean handleOrganizationBean : handleOrganizationBeanList) {
                        String handlePoliceId = handleOrganizationBean.getId();
                        if (Strings.isNotBlank(handlePoliceId)) {
                            handleOrganizationBean.setHandleOrganizationVehicleBean(handlePoliceIdAndVehicleBeansMap.get(handlePoliceId));
                        }
                    }
                    //将调派信息按照handleId分组
                    for (HandleOrganizationBean handleOrganization : handleOrganizationBeanList) {
                        String handleId = handleOrganization.getHandleId();
                        if (Strings.isNotBlank(handleId)) {
                            List<HandleOrganizationBean> list = handleIdAndHandleOrganizationBeansMap.get(handleId);
                            if (list == null) {
                                list = new ArrayList<>();
                                handleIdAndHandleOrganizationBeansMap.put(handleId, list);
                            }
                            list.add(handleOrganization);
                        }

                    }
                }
                // 查询机构id-名称缓存map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                res = HandleDispatchTransformUtil.transform(handleEntity, dicsMap, organizationNameMap);
                if (res != null && Strings.isNotBlank(res.getId())) {
                    //根据map分组信息 将调派信息装配到 对应调派记录bean中
                    String handleId = res.getId();
                    res.setHandleOrganizationBean(handleIdAndHandleOrganizationBeansMap.get(handleId));
                    res.setHandleMiniatureStationBean(handelMiniatureBeansMap.get(handleId));
                }

            } else {
                logService.infoLog(logger, "service", "findHandleByHandleId", "handleId is blank.");
                throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleByHandleId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleByHandleId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see HandleService#findHandleOrganization(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<HandleOrganizationBean> findHandleOrganization(String incidentId, String organizationId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandleOrganization", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleOrganizationBean> res = new ArrayList<>();
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();


            logService.infoLog(logger, "repository", "findHandleOrganizationByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> entityList = null;
            List<String> incidentOrganizationIds = null;
            if (Strings.isBlank(organizationId)) {
                entityList = handleOrganizationRepository.findHandleOrganizationByIncidentId(incidentId);
                incidentOrganizationIds = handleOrganizationRepository.findOrganizationIdByIncidentId(incidentId);
            } else {
                entityList = handleOrganizationRepository.findHandleOrganizationByIncidentIdAndOrganizationId(incidentId, organizationId);
                incidentOrganizationIds = handleOrganizationRepository.findOrganizationIdByIncidentIdAndOrganizationId(incidentId, organizationId);
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            if (entityList != null && entityList.size() > 0) {
                //根据机构id查询机构详情列表
                List<OrganizationBean> organizationBeans = organizationService.findOrganizationsByIds(incidentOrganizationIds);
                Map<String, OrganizationBean> organizationBeanMap = new HashMap<>();
                for (OrganizationBean organizationBean : organizationBeans) {
                    organizationBeanMap.put(organizationBean.getId(), organizationBean);
                }
                for (Object[] entity : entityList) {
                    HandleOrganizationEntity handleOrganizationEntity = (HandleOrganizationEntity) entity[0];
                    HandleEntity handleEntity = (HandleEntity) entity[1];
                    HandleOrganizationBean handleOrganizationBean = HandleDispatchTransformUtil.transform(handleOrganizationEntity, dicsMap, organizationNameMap);
                    HandleDispatchTransformUtil.transform(handleOrganizationBean, organizationBeanMap.get(handleOrganizationBean.getOrganizationId()));
                    handleOrganizationBean.setHandleBatch(handleEntity.getHandleBatch());
                    handleOrganizationBean.setHandleStartTime(handleEntity.getHandleStartTime());
                    handleOrganizationBean.setHandleEndTime(handleEntity.getHandleEndTime());
                    res.add(handleOrganizationBean);
                }
                entityList.clear();
                entityList = null;
            }

            //机构排序
            res.sort(new Comparator<HandleOrganizationBean>() {
                @Override
                public int compare(HandleOrganizationBean o1, HandleOrganizationBean o2) {
                    Integer d1 = o1.getOrganizationOrderNum();
                    Integer d2 = o2.getOrganizationOrderNum();
                    if (d1 != null && d2 != null) {
                        return d1.compareTo(d2);
                    } else {
                        if (d1 == null && d2 != null) {
                            return -1;
                        } else if (d1 != null && d2 == null) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }

                }
            });


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleOrganization", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see HandleService#findHandleOrganizationVehicle(String, String, Boolean)
     */
    @Transactional(readOnly = true)
    @Override
    public List<HandleOrganizationVehicleBean> findHandleOrganizationVehicleGroup(String incidentId, String organizationId, Boolean whetherVehicleLoad) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicleGroup", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicleGroup", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleOrganizationVehicleBean> res = new ArrayList<>();

            List<HandleGroupBean> handleGroupBeanList = findHandleGroup(incidentId, organizationId);

            if (handleGroupBeanList != null && handleGroupBeanList.size() > 0) {
                for (HandleGroupBean handleGroupBean : handleGroupBeanList) {
                    List<HandleOrganizationGroupBean> handleOrganizationBean = handleGroupBean.getHandleOrganizationBean();
                    for (HandleOrganizationGroupBean handleOrganizationGroupBean : handleOrganizationBean) {
                        if (handleOrganizationGroupBean != null) {
                            List<HandleOrganizationVehicleBean> handleOrganizationVehicleBean = handleOrganizationGroupBean.getHandleOrganizationVehicleBean();
                            if (handleOrganizationVehicleBean != null && handleOrganizationVehicleBean.size() > 0) {
                                res.addAll(handleOrganizationVehicleBean);
                            }
                        }
                    }

                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleOrganizationVehicleGroup", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleOrganizationVehicleGroup", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see HandleService#findHandleOrganizationVehicle(String, String, Boolean)
     */
    @Transactional(readOnly = true)
    @Override
    public List<HandleOrganizationVehicleBean> findHandleOrganizationVehicle(String incidentId, String organizationId, Boolean whetherVehicleLoad) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleOrganizationVehicleBean> res = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
            //机构缓存
//            Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();

            logService.infoLog(logger, "repository", "findHandleOrganizationEquipmentsByHandleId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> entityList = null;
            List<String> incidentVehicleIds = null;
            if (Strings.isBlank(organizationId)) {
                entityList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentId(incidentId);
                incidentVehicleIds = handleOrganizationVehicleRepository.findVehicleIdByIncidentId(incidentId);
            } else {
                entityList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentIdAndOrganizationId(incidentId, organizationId);
                incidentVehicleIds = handleOrganizationVehicleRepository.findVehicleIdByIncidentIdAndOrganizationId(incidentId, organizationId);
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationEquipmentsByHandleId", String.format("repository is finished,execute time is :%sms", end - start));

            if (entityList != null && entityList.size() > 0) {
                //获得 车辆指挥员角色编码
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("vehicleCommander");
                String vehicleCommanderCode = systemConfigurationBean.getConfigValue();

                Map<String, List<EquipmentVehicleLoadBean>> equipmentVehicleLoadBeanMap = new HashMap<>();
                if (whetherVehicleLoad) {
                    //查找车载装备
                    List<EquipmentVehicleLoadBean> equipmentVehicleLoadBeanList = equipmentService.findEquipmentVehicleByVehicleIds(incidentVehicleIds);
                    // 车辆id-车载装备id
                    if (equipmentVehicleLoadBeanList != null && equipmentVehicleLoadBeanList.size() > 0) {
                        for (EquipmentVehicleLoadBean equipmentVehicleLoadBean : equipmentVehicleLoadBeanList) {
                            List<EquipmentVehicleLoadBean> vehicleLoadBeanList = equipmentVehicleLoadBeanMap.get(equipmentVehicleLoadBean.getVehicleId());
                            if (vehicleLoadBeanList == null) {
                                vehicleLoadBeanList = new ArrayList<>();
                            }
                            vehicleLoadBeanList.add(equipmentVehicleLoadBean);
                            equipmentVehicleLoadBeanMap.put(equipmentVehicleLoadBean.getVehicleId(), vehicleLoadBeanList);
                        }
                    }
                }

                //查找出动人员
                // 车辆id 与 人员关系
                List<ParticipantFeedbackBean> participantFeedbackBeanList = participantFeedbackService.findIncidentParticipant(incidentId, incidentVehicleIds);
                Map<String, List<ParticipantFeedbackBean>> participantFeedbackBeanMap = new HashMap<>();
                Map<String, ParticipantFeedbackBean> participantCommanderBeanMap = new HashMap<>();
                if (participantFeedbackBeanList != null && participantFeedbackBeanList.size() > 0) {
                    for (ParticipantFeedbackBean participantFeedbackBean : participantFeedbackBeanList) {
                        List<ParticipantFeedbackBean> participantBeanList = participantFeedbackBeanMap.get(participantFeedbackBean.getVehicleId() + participantFeedbackBean.getHandleId());
                        if (participantBeanList == null) {
                            participantBeanList = new ArrayList<>();
                        }
                        participantBeanList.add(participantFeedbackBean);
                        //判断是否
                        if (participantFeedbackBean.getPersonRole() != null && vehicleCommanderCode.contains(participantFeedbackBean.getPersonRole())) {
                            participantCommanderBeanMap.put(participantFeedbackBean.getVehicleId() + participantFeedbackBean.getHandleId(), participantFeedbackBean);
                        }
                        participantFeedbackBeanMap.put(participantFeedbackBean.getVehicleId() + participantFeedbackBean.getHandleId(), participantBeanList);
                    }
                }

                //根据车辆id查询车辆详情列表
                List<EquipmentVehicleBean> equipmentVehicleBeans = vehicleService.findVehicleCacheList(incidentVehicleIds);
                Map<String, EquipmentVehicleBean> equipmentVehicleMap = new HashMap<>();
                for (EquipmentVehicleBean equipmentVehicleBean : equipmentVehicleBeans) {
                    equipmentVehicleMap.put(equipmentVehicleBean.getId(), equipmentVehicleBean);
                }
                //查询计划的随车人员(wl_clryxx)
                List<VehiclePersonsBean> vehiclePersonsBeans = vehiclePersonService.findVehiclePersonsSplit(incidentVehicleIds);
                Map<String,List<VehiclePersonsBean>> vehiclePersonMap=new HashMap<>();
                if (vehiclePersonsBeans!=null&&!vehiclePersonsBeans.isEmpty()){
                    for (VehiclePersonsBean vehiclePersonsBean : vehiclePersonsBeans) {
                        List<VehiclePersonsBean> list = vehiclePersonMap.get(vehiclePersonsBean.getVehicleId());
                        if (list==null){
                            list=new ArrayList<>();
                        }
                        list.add(vehiclePersonsBean);
                        vehiclePersonMap.put(vehiclePersonsBean.getVehicleId(),list);
                    }
                }

                //转换
                for (Object[] entity : entityList) {
                    HandleOrganizationVehicleEntity handleOrganizationVehicleEntity = (HandleOrganizationVehicleEntity) entity[0];
                    HandleEntity handleEntity = (HandleEntity) entity[1];
                    HandleOrganizationVehicleBean handleOrganizationVehicleBean = HandleDispatchTransformUtil.transform(handleOrganizationVehicleEntity, dicsMap, organizationNameMap);
                    HandleDispatchTransformUtil.transform(handleOrganizationVehicleBean, equipmentVehicleMap.get(handleOrganizationVehicleBean.getVehicleId()));
                    handleOrganizationVehicleBean.setHandleBatch(handleEntity.getHandleBatch());
                    handleOrganizationVehicleBean.setHandleStartTime(handleEntity.getHandleStartTime());
                    handleOrganizationVehicleBean.setHandleEndTime(handleEntity.getHandleEndTime());
                    handleOrganizationVehicleBean.setOrganizationName(organizationNameMap.get(handleOrganizationVehicleBean.getOrganizationId()));
                    if (whetherVehicleLoad) {
                        handleOrganizationVehicleBean.setEquipmentVehicleLoadBeanList(equipmentVehicleLoadBeanMap.get(handleOrganizationVehicleBean.getVehicleId()));
                    }

                    handleOrganizationVehicleBean.setParticipantFeedbackBeanList(participantFeedbackBeanMap.get(handleOrganizationVehicleBean.getVehicleId() + handleOrganizationVehicleBean.getHandleId()));
                    ParticipantFeedbackBean participantFeedbackBean = participantCommanderBeanMap.get(handleOrganizationVehicleBean.getVehicleId() + handleOrganizationVehicleBean.getHandleId());
                    if (participantFeedbackBean != null) {
                        handleOrganizationVehicleBean.setVehicleCommander(participantFeedbackBean.getPersonId());
                        handleOrganizationVehicleBean.setVehicleCommander(participantFeedbackBean.getPersonName());
                        handleOrganizationVehicleBean.setVehicleCommanderPhone(participantFeedbackBean.getPersonPhone());
                    }
                    //装配计划的车辆人员信息(wl_clryxx)
                    handleOrganizationVehicleBean.setVehiclePersonsBeans(vehiclePersonMap.get(handleOrganizationVehicleBean.getVehicleId()));
                    res.add(handleOrganizationVehicleBean);
                }

                //出动车辆排序
                res.sort(new Comparator<HandleOrganizationVehicleBean>() {
                    @Override
                    public int compare(HandleOrganizationVehicleBean o1, HandleOrganizationVehicleBean o2) {
                        Integer d1 = o1.getVehicleOrderNum();
                        Integer d2 = o2.getVehicleOrderNum();
                        if (d1 != null && d2 != null) {
                            return d1.compareTo(d2);
                        } else {
                            if (d1 == null && d2 != null) {
                                return -1;
                            } else if (d1 != null && d2 == null) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }

                    }
                });
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleOrganizationVehicle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_VEHICLE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findHandleOrganizationVehicle(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public HandleOrganizationVehicleBean findHandleOrganizationVehicle(String incidentId, String vehicleId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            HandleOrganizationVehicleBean res = null;

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId , vehicleId)", "repository is started...");
            Long start = System.currentTimeMillis();

            List<HandleOrganizationVehicleEntity> handleOrganizationVehicleEntityList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId, vehicleId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId , vehicleId) ", String.format("repository is finished,execute time is :%sms", end - start));


            if (null != handleOrganizationVehicleEntityList && handleOrganizationVehicleEntityList.size() > 0) {
                HandleOrganizationVehicleEntity handleOrganizationVehicleEntity = handleOrganizationVehicleEntityList.get(0);
                res = HandleDispatchTransformUtil.transform(handleOrganizationVehicleEntity, dicsMap, organizationNameMap);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleOrganizationVehicle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_VEHICLE_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #findHandleOrganizationVehicle(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public HandleOrganizationVehicleBean findHandleOrganizationVehicleById(String handleOrganizationVehicleId) {
        if (Strings.isBlank(handleOrganizationVehicleId)) {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "handleOrganizationVehicleId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            HandleOrganizationVehicleBean res = null;

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId , vehicleId)", "repository is started...");
            Long start = System.currentTimeMillis();

            HandleOrganizationVehicleEntity handleOrganizationVehicleEntityList = accessor.getById(handleOrganizationVehicleId, HandleOrganizationVehicleEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId , vehicleId) ", String.format("repository is finished,execute time is :%sms", end - start));


            if (null != handleOrganizationVehicleEntityList) {
                res = HandleDispatchTransformUtil.transform(handleOrganizationVehicleEntityList, dicsMap, organizationNameMap);
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleOrganizationVehicle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_VEHICLE_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see HandleService#findHandleOrganizationEquipment(String, String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<HandleOrganizationEquipmentBean> findHandleOrganizationEquipment(String incidentId, String organizationId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandleOrganizationEquipment", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleOrganizationEquipment", "service is started...");
            Long logStart = System.currentTimeMillis();
            List<HandleOrganizationEquipmentBean> res = new ArrayList<>();

            logService.infoLog(logger, "repository", "findHandleOrganizationEquipmentByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();
            List<Object[]> entityList = null;
            if (Strings.isBlank(organizationId)) {
                entityList = handleOrganizationEquipmentRepository.findHandleOrganizationEquipmentByIncidentId(incidentId);
            } else {
                entityList = handleOrganizationEquipmentRepository.findHandleOrganizationEquipmentByIncidentId(incidentId, organizationId);
            }
            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationEquipmentByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            if (entityList != null && entityList.size() > 0) {
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                // 查询机构id-名称缓存map
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                for (Object[] entity : entityList) {
                    HandleOrganizationEquipmentEntity handleOrganizationEquipmentEntity = (HandleOrganizationEquipmentEntity) entity[0];
                    HandleEntity handleEntity = (HandleEntity) entity[1];
                    HandleOrganizationEquipmentBean handleOrganizationEquipmentBean = HandleDispatchTransformUtil.transform(handleOrganizationEquipmentEntity, dicsMap, organizationNameMap);
                    handleOrganizationEquipmentBean.setHandleBatch(handleEntity.getHandleBatch());
                    handleOrganizationEquipmentBean.setHandleStartTime(handleEntity.getHandleStartTime());
                    handleOrganizationEquipmentBean.setHandleEndTime(handleEntity.getHandleEndTime());
                    res.add(handleOrganizationEquipmentBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleOrganizationEquipment", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleOrganizationEquipment", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_EQUIPMENT_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see HandleService#changeHandleIncidentId(String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeHandleIncidentId(String sourceIncidentId, String targetIncidentId) {
        if (Strings.isBlank(sourceIncidentId) || Strings.isBlank(targetIncidentId)) {
            logService.infoLog(logger, "service", "changeHandleIncidentId", "source or target IncidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "changeHandleIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据原案件id查找处警记录
            logService.infoLog(logger, "repository", "findHandleByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<HandleEntity> handleEntityList = handleRepository.findHandleByIncidentId(sourceIncidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将处警记录 关联的案件id置为目标案件id
            if (handleEntityList != null && handleEntityList.size() > 0) {
                for (HandleEntity entity : handleEntityList) {
                    entity.setIncidentId(targetIncidentId);
                    //根据处警记录id 变更 调派信息、调派单位装备信息、作战车辆信息 作战车辆状态变更 相关的案件id
                    changeHandleOrganizationsIncidentId(entity.getId(), targetIncidentId);
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbHandleEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(handleEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeHandleIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeHandleIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.CHANGE_HANDLE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see HandleService#recoverHandleIncidentId(String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recoverHandleIncidentId(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "recoverHandleIncidentId", "incidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "recoverHandleIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据案件id查找处警记录
            logService.infoLog(logger, "repository", "findHandleByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<HandleEntity> handleEntityList = handleRepository.findHandleByOriginalIncidentNumber(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));

            //将处警记录 关联的案件id置为 原始案件id
            if (handleEntityList != null && handleEntityList.size() > 0) {
                for (HandleEntity entity : handleEntityList) {
                    entity.setIncidentId(entity.getOriginalIncidentNumber());
                    //根据处警记录id 变更 调派信息、调派单位装备信息、作战车辆信息 相关的案件id
                    changeHandleOrganizationsIncidentId(entity.getId(), entity.getOriginalIncidentNumber());
                }

                //保存更改
                logService.infoLog(logger, "repository", "save(dbHandleEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(handleEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "recoverHandleIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "recoverHandleIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.RECOVER_HANDLE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see HandleService#changeHandleOrganizationsIncidentId(String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeHandleOrganizationsIncidentId(String handleId, String targetIncidentId) {
        if (Strings.isBlank(handleId) || Strings.isBlank(targetIncidentId)) {
            logService.infoLog(logger, "service", "changeHandleOrganizationsIncidentId", "handleId or targetIncidentId is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "changeHandleOrganizationsIncidentId", "service is started...");
            Long logStart = System.currentTimeMillis();
            //根据处警记录id查找处警记录
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<HandleOrganizationEntity> organizationEntityList = handleOrganizationRepository.findHandleOrganizationByHandleId(handleId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", String.format("repository is finished,execute time is :%sms", end - start));

            //将处警记录 关联的案件id置为目标案件id
            if (organizationEntityList != null && organizationEntityList.size() > 0) {
                for (HandleOrganizationEntity organization : organizationEntityList) {
                    organization.setIncidentId(targetIncidentId);
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(organizationEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            //根据处警记录id查找作战车辆信息
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", "repository is started...");
            Long vehicleStart = System.currentTimeMillis();

            List<HandleOrganizationVehicleEntity> vehicleEntityList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByHandleId(handleId);

            Long vehicleEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", String.format("repository is finished,execute time is :%sms", vehicleEnd - vehicleStart));

            //将作战车辆信息 关联的案件id置为目标案件id
            if (vehicleEntityList != null && vehicleEntityList.size() > 0) {
                List<String> vehicleIds = new ArrayList<>();
                for (HandleOrganizationVehicleEntity vehicleEntity : vehicleEntityList) {
                    vehicleEntity.setIncidentId(targetIncidentId);
                    vehicleIds.add(vehicleEntity.getVehicleId());
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(vehicleEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

                //更新 车辆关联案件信息
                handleOrganizationVehicleRepository.updateEquipmentVehicleIncidentNumber(vehicleIds, targetIncidentId, servletService.getSystemTime());
            }


            //根据处警记录id查找车辆状态变更
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", "repository is started...");
            Long vehicleStatusChangeStart = System.currentTimeMillis();

            List<VehicleStatusChangeEntity> vehicleStatusChangeEntityList = handleOrganizationVehicleRepository.findVehicleStatusChangeByHandleId(handleId);

            Long vehicleStatusChangeEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", String.format("repository is finished,execute time is :%sms", vehicleStatusChangeEnd - vehicleStatusChangeStart));

            //将车辆状态变更 关联的案件id置为目标案件id
            if (vehicleStatusChangeEntityList != null && vehicleStatusChangeEntityList.size() > 0) {

                for (VehicleStatusChangeEntity vehicleStatusChangeEntity : vehicleStatusChangeEntityList) {
                    vehicleStatusChangeEntity.setIncidentId(targetIncidentId);
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(vehicleEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }


            //根据处警记录id查找 调派单位装备信息
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", "repository is started...");
            Long equipmentStart = System.currentTimeMillis();

            List<HandleOrganizationEquipmentEntity> equipmentEntityList = handleOrganizationEquipmentRepository.findHandleOrganizationEquipmentByHandleId(handleId);

            Long equipmentEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationByHandleId", String.format("repository is finished,execute time is :%sms", equipmentEnd - equipmentStart));

            //将 调派单位装备信息 关联的案件id置为目标案件id
            if (equipmentEntityList != null && equipmentEntityList.size() > 0) {
                for (HandleOrganizationEquipmentEntity equipmentEntity : equipmentEntityList) {
                    equipmentEntity.setIncidentId(targetIncidentId);
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationEquipmentEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(equipmentEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationEquipmentEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            //根据处警记录id查找 调派调派微站信息
            logService.infoLog(logger, "repository", "findHandleMiniatureStationByHandleId", "repository is started...");
            Long miniatureStationStart = System.currentTimeMillis();

            List<HandleMiniatureStationEntity> miniatureStationEntityList = handleMiniatureStationRepository.findHandleMiniatureStationByHandleId(handleId);

            Long miniatureStationEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleMiniatureStationByHandleId", String.format("repository is finished,execute time is :%sms", miniatureStationEnd - miniatureStationStart));

            //将 调派微站 关联的案件id置为目标案件id
            if (miniatureStationEntityList != null && miniatureStationEntityList.size() > 0) {
                for (HandleMiniatureStationEntity miniatureStationEntity : miniatureStationEntityList) {
                    miniatureStationEntity.setIncidentId(targetIncidentId);
                }
                //保存更改
                logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationEntityList)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save(miniatureStationEntityList);

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleMiniatureStationEntityList)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "changeHandleOrganizationsIncidentId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "changeHandleOrganizationsIncidentId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.CHANGE_HANDLE_ORGANIZATIONS_INCIDENT_ID_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentHandleOrganization(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findIncidentHandleOrganization(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentHandleOrganization", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentHandleOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();

            //根据案件id 从调派单位中 获取 单位id
            logService.infoLog(logger, "repository", "findOrganizationIdByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            res = handleOrganizationRepository.findOrganizationIdByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOrganizationIdByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentHandleOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentHandleOrganization", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_HANDLE_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentHandleOrganizationVehicleId(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findIncidentHandleOrganizationVehicleId(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentHandleOrganizationVehicleId", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentHandleOrganizationVehicleId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();

            //根据案件id 从调派车辆中 获取 车辆id
            logService.infoLog(logger, "repository", "findHandleOrganizationVehicleIdByIncidentId", "repository is started...");
            Long start = System.currentTimeMillis();

            res = handleOrganizationVehicleRepository.findVehicleIdByIncidentId(incidentId);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationVehicleIdByIncidentId", String.format("repository is finished,execute time is :%sms", end - start));


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentHandleOrganizationVehicleId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentHandleOrganizationVehicleId", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_HANDLE_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentFightVehicleIds(String, List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findIncidentFightVehicleIds(String incidentId, List<String> vehicleIds) {
        if (Strings.isBlank(incidentId) || vehicleIds == null || vehicleIds.size() < 1) {
            logService.infoLog(logger, "service", "findIncidentFightVehicleIds", "incidentId  or  vehicleIds is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentFightVehicleIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<String> res = new ArrayList<>();

            List<String> vehicleStutsCodes = new ArrayList<>();
            vehicleStutsCodes.add(VEHICLE_STATUS_CDFD.getCode());
            vehicleStutsCodes.add(VEHICLE_STATUS_CDZTFD.getCode());
            //根据案件id 从调派单位中 获取 单位id
            logService.infoLog(logger, "repository", "findIncidentFightVehicleIds", "repository is started...");
            Long start = System.currentTimeMillis();

            res = handleOrganizationVehicleRepository.findIncidentFightVehicleIds(incidentId, vehicleIds, vehicleStutsCodes);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findIncidentFightVehicleIds", String.format("repository is finished,execute time is :%sms", end - start));

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentFightVehicleIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentHandleOrganization", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_HANDLE_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findIncidentFightVehicleIds(String, List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<OnDutySummaryBean> findIncidentOrganizationOnDuty(String incidentId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentOrganizationOnDuty", "incidentId   is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentOrganizationOnDuty", "service is started...");
            Long logStart = System.currentTimeMillis();


            List<String> organizationIds = incidentService.findIncidentParticipantOrganizationId(incidentId);

            List<OnDutySummaryBean> res = onDutyService.findOnDutyByOrganizationIds(organizationIds);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentOrganizationOnDuty", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentOrganizationOnDuty", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_HANDLE_ORGANIZATION_FAIL);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveHandleVehicleIdentification(String incidentId, String vehicleId, String vehicleIdentification) {
        if (Strings.isBlank(incidentId) || Strings.isBlank(vehicleId) || Strings.isBlank(vehicleIdentification)) {
            logService.infoLog(logger, "service", "saveHandleVehicleIdentification", "incidentId vehicleId vehicleIdentification  is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            Boolean res = false;
            List<HandleOrganizationVehicleEntity> handleOrganizationVehicleList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId, vehicleId);
            if (handleOrganizationVehicleList != null && handleOrganizationVehicleList.size() > 0) {
                HandleOrganizationVehicleEntity vehicleEntity = handleOrganizationVehicleList.get(0);
                vehicleEntity.setVehicleIdentification(vehicleIdentification);
                accessor.save(vehicleEntity);

                res = true;
            }
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveHandleVehicleIdentification", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_INCIDENT_HANDLE_ORGANIZATION_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #updateIncidentVehicleStatus(IncidentVehicleStatusUpdateInputInfo)
     */
    @Override
    public void updateIncidentVehicleStatus(IncidentVehicleStatusUpdateInputInfo inputInfo) {
        try {
            logService.infoLog(logger, "service", "updateIncidentVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            handleStatusChangeService.updateIncidentVehicleStatus(inputInfo);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateIncidentVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncidentVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_VEHICLE_STATUS_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #updateHandleOrganizationVehicleStatus(String, String, String, String)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateHandleOrganizationVehicleStatus(String incidentId, String handleId, String vehicleId, String newVehicleStatus) {
        if (Strings.isBlank(incidentId) || Strings.isBlank(vehicleId) || Strings.isBlank(newVehicleStatus)) {
            logService.infoLog(logger, "service", "updateHandleOrganizationVehicleStatus", "incidentId or vehicleId or newVehicleStatus is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateHandleOrganizationVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            String handleOrganizationVehicleId = handleStatusChangeService.updateHandleOrganizationVehicleStatus(incidentId, handleId, vehicleId, newVehicleStatus);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateHandleOrganizationVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return handleOrganizationVehicleId;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateHandleOrganizationVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.RECOVER_HANDLE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see #buildIncidentVehicleStatus(String, String, String, String, String)
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void buildIncidentVehicleStatus(String incidentId, String handleId, String vehicleId,
                                           String newVehicleStatus, String changeSource) {
        if (Strings.isBlank(incidentId) || Strings.isBlank(vehicleId) || Strings.isBlank(newVehicleStatus)) {
            logService.infoLog(logger, "service", "changeHandleOrganizationsIncidentId", "incidentId or vehicleId or newVehicleStatus is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "method", "buildIncidentVehicleStatus", "private method is started...");
            Long logStart = System.currentTimeMillis();

            handleStatusChangeService.buildIncidentVehicleStatus(incidentId, handleId, vehicleId, newVehicleStatus, changeSource);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "method", "buildIncidentVehicleStatus", String.format("private method is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncidentVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_VEHICLE_STATUS_FAIL);

        }

    }


    /**
     * {@inheritDoc}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateHandleOrganizationSpeakFile() {

        try {
            logService.infoLog(logger, "service", "updateHandleOrganizationSpeakFile", "service is started...");
            Long logStart = System.currentTimeMillis();
            Map<String, String> ttsSpeakToFileMap = ttsCacheService.findAllHandleOrganizationSpeakFileCache();
            if (ttsSpeakToFileMap != null && ttsSpeakToFileMap.size() > 0) {
                Set<String> handleOrganizationIdSet = ttsSpeakToFileMap.keySet();
                List<String> handleOrganizationIds = new ArrayList<>();
                handleOrganizationIds.addAll(handleOrganizationIdSet);
                handleOrganizationIds.remove(null);
                List<HandleOrganizationEntity> handleOrganizationEntityList =
                        handleOrganizationRepository.findHandleOrganizationByIds(handleOrganizationIds);
                for (HandleOrganizationEntity handleOrganizationEntity : handleOrganizationEntityList) {
                    String fileUrl = ttsSpeakToFileMap.get(handleOrganizationEntity.getId());
                    handleOrganizationEntity.setSpeakToFileUrl(fileUrl);
                }

                accessor.save(handleOrganizationEntityList);

                for (String key : ttsSpeakToFileMap.keySet()) {
                    ttsCacheService.modifyHandleOrganizationSpeakFileCache("remove", key, null);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateHandleOrganizationSpeakFile", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateHandleOrganizationSpeakFile", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_HANDLE_FILE_FAIL);
        }

    }

}
