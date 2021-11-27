package com.dscomm.iecs.out.service.impl;

import com.dscomm.iecs.accept.dal.po.*;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationRepository;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationVehicleRepository;
import com.dscomm.iecs.accept.dal.repository.ParticipanFeedbackRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.FireSafetyMonitoringBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationVehicleBean;
import com.dscomm.iecs.accept.graphql.typebean.ParticipantFeedbackBean;
import com.dscomm.iecs.accept.service.FireSafetyMonitoringService;
import com.dscomm.iecs.accept.service.HandleService;
import com.dscomm.iecs.accept.service.ParticipantFeedbackService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleLoadEntity;
import com.dscomm.iecs.basedata.dal.repository.EquipmentRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleLoadBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.dal.repository.GetDispatchListByRepository;
import com.dscomm.iecs.out.enums.InterfaceTypeEnum;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.*;
import com.dscomm.iecs.out.service.HandleOutService;
import com.dscomm.iecs.out.utils.GetQueryAuditUtil;
import com.dscomm.iecs.out.utils.TimeUtils;
import com.dscomm.iecs.out.utils.TransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/19 22:40
 * @Describe
 */
@Component
public class HandleOutServiceImpl implements HandleOutService {

    private static final Logger logger = LoggerFactory.getLogger(HandleOutServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private HandleService handleService;
    private SystemConfigurationService systemConfigurationService;
    private HandleOrganizationRepository handleOrganizationRepository;
    private OrganizationService organizationService;
    private DictionaryService dictionaryService;
    private HandleOrganizationVehicleRepository handleOrganizationVehicleRepository;
    private EquipmentService equipmentService;
    private ParticipantFeedbackService participantFeedbackService;
    private VehicleService vehicleService;
    private ParticipanFeedbackRepository participanFeedbackRepository;
    private FireSafetyMonitoringService fireSafetyMonitoringService;
    private EquipmentRepository equipmentRepository;
    private GetDispatchListByRepository getDispatchListByRepository;
    private SessionDataStore dataStore;
    private List<String> dics;
    //设置机构数据缓存数据 机构id 与 机构名称  缓存  机构id  与 bean 的缓存
    private static Map<String, EquipmentVehicleBean> vehicleBeanMap = new HashMap<>();
    @Autowired
    public HandleOutServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                HandleService handleService,
                                SystemConfigurationService systemConfigurationService,
                                HandleOrganizationRepository handleOrganizationRepository,
                                OrganizationService organizationService,
                                DictionaryService dictionaryService,
                                HandleOrganizationVehicleRepository handleOrganizationVehicleRepository,
                                EquipmentService equipmentService,
                                ParticipantFeedbackService participantFeedbackService,
                                VehicleService vehicleService,
                                ParticipanFeedbackRepository participanFeedbackRepository,
                                FireSafetyMonitoringService fireSafetyMonitoringService,
                                EquipmentRepository equipmentRepository,
                                GetDispatchListByRepository getDispatchListByRepository, SessionDataStore dataStore) {
        this.logService = logService;
        this.accessor = accessor;
        this.handleService = handleService;
        this.systemConfigurationService = systemConfigurationService;
        this.handleOrganizationRepository=handleOrganizationRepository;
        this.organizationService=organizationService;
        this.dictionaryService=dictionaryService;
        this.handleOrganizationVehicleRepository=handleOrganizationVehicleRepository;
        this.equipmentService=equipmentService;
        this.participantFeedbackService=participantFeedbackService;
        this.vehicleService=vehicleService;
        this.participanFeedbackRepository=participanFeedbackRepository;
        this.fireSafetyMonitoringService=fireSafetyMonitoringService;
        this.equipmentRepository=equipmentRepository;
        this.getDispatchListByRepository =getDispatchListByRepository ;
        this.dataStore = dataStore;
        dics = new ArrayList<>(Arrays.asList("WLRYZW","ZBZT", "JLDW", "ZLZT", "ZLDP", "WLCLLX", "WLZZGN", "WLCLZT", "ZBLX", "AJZT",
                "XB", "XZQX", "BJFS", "LAFS", "AJLX", "AJLXZX", "AJDJ", "AJXZ", "CZDX", "ZDDWLX", "JZJG", "YWQK", "ZHCS", "CLLX", "JQBQ", "JQDX"));
    }


    /**
     * 获取调派信息列表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<HandleDateBean> getDispatchListByTime(Long startTime, Long endTime, String username) {
        QueryAuditEntity queryAuditEntity = new QueryAuditEntity();
        try {
            logService.infoLog(logger, "service", "getAlarmJqcjDpListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            GetQueryAuditUtil.getBeforeQueryAudit(username, InterfaceTypeEnum.JQDPXX.getCode()
                    , "JCJ_CJJL", accessor, queryAuditEntity, logStart);
            List<HandleDateBean> res = getHandleDateListByTime(startTime, endTime);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqcjDpListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            GetQueryAuditUtil.getAfterQueryAudit("success", res.size(), queryAuditEntity, logEnd);
            return res;
        } catch (Exception ex) {
            GetQueryAuditUtil.getAfterQueryAudit("fail", 0, queryAuditEntity, System.currentTimeMillis());
            logService.erorLog(logger, "service", "getAlarmJqcjDpListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_DISPATCH_LIST_FAIL);
        } finally {
            saveAuditLog(queryAuditEntity);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAuditLog(QueryAuditEntity queryAuditEntity) {
        accessor.save(queryAuditEntity);

    }

    @Transactional(readOnly = true)
    public List<HandleDateBean> getHandleDateListByTime(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            logService.infoLog(logger, "service", "getAlarmJqcjDpListByTime", "query time is Blank.");
            throw new OutException(OutException.AccetpErrors.DATA_NULL);
        }
        try {
            systemConfigurationService.forceUpdateCacheSystemConfiguration();
            logService.infoLog(logger, "service", "getAlarmJqcjDpListByTime", "service is started...");
            Long logStart = System.currentTimeMillis();
            //创建一个list 存放ConditionGroup
            List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
            //创建一个大于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.gte("createdTime", startTime));//查询开始时间

            //创建一个小于等于条件  创建时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.lte("createdTime", endTime));//查询结束时间
            conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("valid", 1));
            GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
            Long start = System.currentTimeMillis();

            List<HandleEntity> entityList = accessor.find(conditionGroup, HandleEntity.class);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "getAlarmJqcjDpListByTime", String.format("repository is finished,execute time is :%sms", end - start));

            List<HandleDateBean> res = new ArrayList<>();
            String deptCode= (String) dataStore.get().get("deptCode");
            if (entityList != null && entityList.size() > 0) {
                for (HandleEntity entity : entityList
                ) {
                    String incidentId = entity.getIncidentId();

                    HandleDateBean bean = new HandleDateBean();
                    //1.处警信息转换
                    bean = TransformUtil.transform(entity, logService, logger);
                    bean.setDeptCode(deptCode);
                    IncidentEntity incidentEntity = accessor.getById(incidentId, IncidentEntity.class);
                    if (incidentEntity != null && incidentEntity.isValid()) {
                        bean.setLasj(TimeUtils.transformaStrTime(incidentEntity.getRegisterIncidentTime()));//立案时间
                    }

                    //2.调派中队集合
                    List<DispatchOrganizationBean> dzList = new ArrayList<>();
                    //调用产品接口 根据案件id获取调派机构信息
                    List<HandleOrganizationOutBean> handleOrganizationBeanList =
                            findOutHandleOrganization(incidentId, entity.getId());
                    if (handleOrganizationBeanList != null && handleOrganizationBeanList.size() > 0) {
                        for (HandleOrganizationOutBean handleOrg : handleOrganizationBeanList
                        ) {
                            //HandleOrganizationBean handleOrg = new  HandleOrganizationBean();
                           // BeanUtils.copyProperties(handleOrgOut,handleOrg);
                            DispatchOrganizationBean dispatchOrganizationBean = new DispatchOrganizationBean();
                            String orgId = handleOrg.getOrganizationId();
                            //2.1 中队基本信息
                            dispatchOrganizationBean = TransformUtil.transform(handleOrg, logService, logger);
                            dispatchOrganizationBean.setDeptCode(deptCode);
                            //2.2 中队调派车辆列表  调用产品接口
                           /* List<HandleOrganizationVehicleBean> handleOrganizationVehicleBeanList =
                                    handleService.findHandleOrganizationVehicle(incidentId, orgId, true);*/
                            List<HandleOrganizationVehicleOutBean> handleOrganizationVehicleBeanList = findHandleOrganizationVehicle(incidentId, orgId, true,entity.getId());
                            //车辆
                            List<DispatchVehicleBean> dpclList = new ArrayList<>();
                            //人员
                            List<DispatchPerson> dpryList = new ArrayList<>();
                            //装备
                            List<DispatchEquipment> dpzbList = new ArrayList<>();

                            //转换车辆信息
                            if (handleOrganizationVehicleBeanList != null && handleOrganizationVehicleBeanList.size() > 0) {
                                for (HandleOrganizationVehicleOutBean vehicleBean : handleOrganizationVehicleBeanList
                                ) {
                                    DispatchVehicleBean dispatchVehicleBean = new DispatchVehicleBean();
                                    String vehicleId = vehicleBean.getVehicleId();
                                    dispatchVehicleBean = TransformUtil.transform(vehicleBean);
                                    //获取车辆实体 转换基本数据
                                    EquipmentVehicleEntity vehicleEntity = accessor.getById(vehicleId,
                                            EquipmentVehicleEntity.class);
                                    if (vehicleEntity != null && vehicleEntity.isValid()) {
                                        try {
                                            if (vehicleEntity.getPassengersNum() != null)
                                                dispatchVehicleBean.setEdzrs(Integer.valueOf(vehicleEntity.getPassengersNum().toString()));//核定载人数
                                            if (vehicleEntity.getMaxLiftingHeight() != null)
                                                dispatchVehicleBean.setJgcs(vehicleEntity.getMaxLiftingHeight().toString());//举高参数
                                            dispatchVehicleBean.setPmzzl(vehicleEntity.getCarrierBubble());//泡沫装载量
                                            dispatchVehicleBean.setUpdateTime(TimeUtils.transformaStrTime(vehicleEntity.getUpdatedTime()));//更新时间
                                            dispatchVehicleBean.setZzsrj(vehicleEntity.getWaterCarrier());//装载水_容积
                                        } catch (Exception e) {
                                            System.out.println(" the error id of table WL_CLXX is: " + vehicleEntity.getId());
                                        }
                                    }
                                    dispatchVehicleBean.setDeptCode(deptCode);
                                    dpclList.add(dispatchVehicleBean);

                                    //2.3 中队调派人员信息
                                    List<ParticipantFeedbackOutBean> participantFeedbackBeanList =
                                            vehicleBean.getParticipantFeedbackBeanList();
                                    if (participantFeedbackBeanList != null && participantFeedbackBeanList.size() > 0) {
                                        for (ParticipantFeedbackOutBean person : participantFeedbackBeanList
                                        ) {
                                            DispatchPerson dispatchPerson = new DispatchPerson();
                                            dispatchPerson = TransformUtil.transform(person);
                                            dispatchPerson.setDeptCode(deptCode);
                                            dpryList.add(dispatchPerson);
                                        }
                                    }

                                    //2.4 中队调派准备信息
                                    List<EquipmentVehicleLoadOutBean> equipmentVehicleLoadBeanList =
                                            vehicleBean.getEquipmentVehicleLoadBeanList();
                                    if (equipmentVehicleLoadBeanList != null && equipmentVehicleLoadBeanList.size() > 0) {
                                        for (EquipmentVehicleLoadOutBean equip : equipmentVehicleLoadBeanList
                                        ) {
                                            DispatchEquipment dispatchEquipment = new DispatchEquipment();
                                            dispatchEquipment = TransformUtil.transform(equip);
                                            dispatchEquipment.setJqcjdpTywysbm(vehicleBean.getHandleId());//警情处警调派_通用唯一识别码
                                            dispatchEquipment.setJqcjdpzbDpsj(incidentId);//警情处警调派装备_调派事件
                                            dispatchEquipment.setDeptCode(deptCode);
                                            dpzbList.add(dispatchEquipment);
                                        }
                                    }

                                }
                            }
                            //装配 车辆列表
                            dispatchOrganizationBean.setDpclList(dpclList);
                            dispatchOrganizationBean.setDpclSl(dpclList.size());
                            // 装配人员 列表
                            dispatchOrganizationBean.setDpryList(dpryList);
                            dispatchOrganizationBean.setDprySl(dpryList.size());
                            //装配装备 列表
                            dispatchOrganizationBean.setDpzbList(dpzbList);
                            dispatchOrganizationBean.setDpzbSl(dpzbList.size());

                            dzList.add(dispatchOrganizationBean);

                        }
                    }

                    bean.setDzList(dzList);
                    res.add(bean);
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getAlarmJqcjDpListByTime", String.format("service is finished,execute time is :%sms", logEnd - logStart));


            return res;
        } catch (Exception ex) {

            logService.erorLog(logger, "service", "getAlarmJqcjDpListByTime", "execution error", ex);
            throw new OutException(OutException.AccetpErrors.FIND_DISPATCH_LIST_FAIL);
        }
    }


    public List<HandleOrganizationOutBean> findOutHandleOrganization(String incidentId, String handleId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findOutHandleOrganization", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findOutHandleOrganization", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleOrganizationOutBean> res = new ArrayList<>();
            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();


            logService.infoLog(logger, "repository", "findOutHandleOrganization", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> entityList = null;
            List<String> incidentOrganizationIds = null;

            entityList = getDispatchListByRepository.findbyincidentId(incidentId,handleId);

            incidentOrganizationIds = getDispatchListByRepository.findOrganizationIdByIncidentId(incidentId,handleId);



            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findOutHandleOrganization", String.format("repository is finished,execute time is :%sms", end - start));

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

                    HandleOrganizationOutBean handleOrganizationOutBean = new HandleOrganizationOutBean();
                    BeanUtils.copyProperties(handleOrganizationBean,handleOrganizationOutBean);
                    handleOrganizationOutBean.setUid(handleOrganizationEntity.getId());
                    handleOrganizationOutBean.setCreateTime(TimeUtils.transformaStrTime(handleOrganizationEntity.getCreatedTime()));

                    res.add(handleOrganizationOutBean);
                }
            }

           /* //机构排序
            res.sort(new Comparator<HandleOrganizationBean>() {
                @Override
                public int compare( HandleOrganizationBean o1, HandleOrganizationBean o2 ) {
                    Integer d1 =    o1.getOrganizationOrderNum()    ;
                    Integer d2 =    o2.getOrganizationOrderNum()    ;
                    return d1.compareTo(d2);
                }
            });*/


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findOutHandleOrganization", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findOutHandleOrganization", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_FAIL);
        }
    }
    public List<HandleOrganizationVehicleOutBean> findHandleOrganizationVehicle(String incidentId, String organizationId, Boolean whetherVehicleLoad,String handleId) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "incidentId is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HandleOrganizationVehicleOutBean> res = new ArrayList<>();

            // 查询出所有需要用到的字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            // 查询机构id-名称缓存map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
            //机构缓存
            Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();

            logService.infoLog(logger, "repository", "findHandleOrganizationEquipmentsByHandleId", "repository is started...");
            Long start = System.currentTimeMillis();

            List<Object[]> entityList = null;
            List<String> incidentVehicleIds = null;

            if (Strings.isBlank(organizationId)) {
                entityList = getDispatchListByRepository.findHandleOrganizationVehicleByIncidentId(incidentId,handleId);

                incidentVehicleIds = getDispatchListByRepository.findVehicleIdByIncidentId(incidentId,handleId);

                //entityList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentId(incidentId);
                //incidentVehicleIds = handleOrganizationVehicleRepository.findVehicleIdByIncidentId(incidentId);
            } else {
                entityList = getDispatchListByRepository.findHandleOrganizationVehicleByIncidentIdAndOrganizationId(incidentId, organizationId,handleId);
                incidentVehicleIds = getDispatchListByRepository.findVehicleIdByIncidentIdAndOrganizationId(incidentId, organizationId,handleId);
                //entityList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentIdAndOrganizationId(incidentId, organizationId);
                //incidentVehicleIds = handleOrganizationVehicleRepository.findVehicleIdByIncidentIdAndOrganizationId(incidentId, organizationId);
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationEquipmentsByHandleId", String.format("repository is finished,execute time is :%sms", end - start));

            if (entityList != null && entityList.size() > 0) {
                //获得 车辆指挥员角色编码
                SystemConfigurationBean systemConfigurationBean = systemConfigurationService.getSystemConfigByConfigType("vehicleCommander");
                String vehicleCommanderCode = systemConfigurationBean.getConfigValue();

                Map<String, List<EquipmentVehicleLoadOutBean>> equipmentVehicleLoadBeanMap = new HashMap<>();
                if (whetherVehicleLoad) {
                    //查找车载装备
                    List<EquipmentVehicleLoadOutBean> equipmentVehicleLoadBeanList = findEquipmentVehicleByVehicleIds(incidentVehicleIds);
                    // 车辆id-车载装备id
                    if (equipmentVehicleLoadBeanList != null && equipmentVehicleLoadBeanList.size() > 0) {
                        for (EquipmentVehicleLoadOutBean equipmentVehicleLoadBean : equipmentVehicleLoadBeanList) {
                            List<EquipmentVehicleLoadOutBean> vehicleLoadBeanList = equipmentVehicleLoadBeanMap.get(equipmentVehicleLoadBean.getVehicleId());
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
                List<ParticipantFeedbackOutBean> participantFeedbackBeanList = findIncidentParticipant(incidentId, incidentVehicleIds,handleId);
                Map<String, List<ParticipantFeedbackOutBean>> participantFeedbackBeanMap = new HashMap<>();
                Map<String, ParticipantFeedbackOutBean> participantCommanderBeanMap = new HashMap<>();
                if (participantFeedbackBeanList != null && participantFeedbackBeanList.size() > 0) {
                    for (ParticipantFeedbackOutBean participantFeedbackBean : participantFeedbackBeanList) {
                        List<ParticipantFeedbackOutBean> participantBeanList = participantFeedbackBeanMap.get(participantFeedbackBean.getVehicleId() + participantFeedbackBean.getHandleId());
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
                //转换
                for (Object[] entity : entityList) {
                    HandleOrganizationVehicleEntity handleOrganizationVehicleEntity = (HandleOrganizationVehicleEntity) entity[0];
                    HandleEntity handleEntity = (HandleEntity) entity[1];
                    HandleOrganizationVehicleBean handleOrganizationVehicleBean = HandleDispatchTransformUtil.transform(handleOrganizationVehicleEntity, dicsMap, organizationNameMap);
                    //EquipmentVehicleBean equipmentVehicleBean = new EquipmentVehicleBean();

                    //BeanUtils.copyProperties(equipmentVehicleMap.get(handleOrganizationVehicleBean.getVehicleId()),equipmentVehicleBean);
                    HandleDispatchTransformUtil.transform(handleOrganizationVehicleBean, equipmentVehicleMap.get(handleOrganizationVehicleBean.getVehicleId()));
                    //HandleDispatchTransformUtil.transform(handleOrganizationVehicleBean, equipmentVehicleBean);
                    handleOrganizationVehicleBean.setHandleBatch(handleEntity.getHandleBatch());
                    handleOrganizationVehicleBean.setHandleStartTime(handleEntity.getHandleStartTime());
                    handleOrganizationVehicleBean.setHandleEndTime(handleEntity.getHandleEndTime());
                    handleOrganizationVehicleBean.setOrganizationName(organizationNameMap.get(handleOrganizationVehicleBean.getOrganizationId()));


                    //handleOrganizationVehicleBean.setParticipantFeedbackBeanList(participantFeedbackBeanMap.get(handleOrganizationVehicleBean.getVehicleId() + handleOrganizationVehicleBean.getHandleId()));
                    ParticipantFeedbackOutBean participantFeedbackBean = participantCommanderBeanMap.get(handleOrganizationVehicleBean.getVehicleId() + handleOrganizationVehicleBean.getHandleId());
                    if (participantFeedbackBean != null) {
                        handleOrganizationVehicleBean.setVehicleCommander(participantFeedbackBean.getPersonId());
                        handleOrganizationVehicleBean.setVehicleCommander(participantFeedbackBean.getPersonName());
                        handleOrganizationVehicleBean.setVehicleCommanderPhone(participantFeedbackBean.getPersonPhone());
                    }
                    HandleOrganizationVehicleOutBean handleOrganizationVehicleOutBean = new HandleOrganizationVehicleOutBean();
                    if (whetherVehicleLoad) {
                        handleOrganizationVehicleOutBean.setEquipmentVehicleLoadBeanList(equipmentVehicleLoadBeanMap.get(handleOrganizationVehicleBean.getVehicleId()));

                    }
                    BeanUtils.copyProperties(handleOrganizationVehicleBean,handleOrganizationVehicleOutBean);
                    handleOrganizationVehicleOutBean.setCreateTime(TimeUtils.transformaStrTime(handleOrganizationVehicleEntity.getCreatedTime()));
                    handleOrganizationVehicleOutBean.setUid(handleOrganizationVehicleEntity.getId());
                    handleOrganizationVehicleOutBean.setParticipantFeedbackBeanList(participantFeedbackBeanMap.get(handleOrganizationVehicleBean.getVehicleId() + handleOrganizationVehicleBean.getHandleId()));
                    res.add(handleOrganizationVehicleOutBean);
                }

                /*//出动车辆排序
                res.sort(new Comparator<HandleOrganizationVehicleBean>() {
                    @Override
                    public int compare( HandleOrganizationVehicleBean o1, HandleOrganizationVehicleBean o2 ) {
                        Integer d1 =  o1.getVehicleOrderNum() ;
                        Integer d2 =  o2.getVehicleOrderNum() ;
                        return d1.compareTo(d2);
                    }
                });*/
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHandleOrganizationVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHandleOrganizationVehicle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HANDLE_ORGANIZATION_VEHICLE_FAIL);
        }
    }
    public  List<ParticipantFeedbackOutBean> findIncidentParticipant( String  incidentId ,  List<String>  vehicleIds ,String handleId ) {
        if (Strings.isBlank(incidentId)) {
            logService.infoLog(logger, "service", "findIncidentParticipant", "incidentId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findIncidentParticipant", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<ParticipantFeedbackOutBean> res = new ArrayList<>();
            //获得机构名称map
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId( incidentId )", "repository is started...");
            Long start = System.currentTimeMillis();

            List<ParticipantFeedbackEntity> participantFeedbackEntityList = null;
            if (vehicleIds == null || vehicleIds.size() < 1) {

                participantFeedbackEntityList = getDispatchListByRepository.findParticipantFeedbackByIncidentId(incidentId,handleId);
                //participantFeedbackEntityList = participanFeedbackRepository.findParticipantFeedbackByIncidentId(incidentId);
            } else {
                participantFeedbackEntityList = getDispatchListByRepository.findParticipantFeedbackByIncidentId(incidentId, vehicleIds,handleId);
                //participantFeedbackEntityList = participanFeedbackRepository.findParticipantFeedbackByIncidentId(incidentId, vehicleIds);
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findParticipantFeedbackByIncidentId( incidentId )", String.format("repository is finished,execute time is :%sms", end - start));

            if (null != participantFeedbackEntityList && participantFeedbackEntityList.size() > 0) {
                List<String> personIds = new ArrayList<>();
                for (ParticipantFeedbackEntity participantFeedbackEntity : participantFeedbackEntityList) {
                    personIds.add(participantFeedbackEntity.getPersonId());
                }
                //获得 案件人员进入火场信息
                Map<String, FireSafetyMonitoringBean> fireSafetyEnterMap = fireSafetyMonitoringService.findLastFireSafetyMonitoring(incidentId, personIds);

                //获取名称-代码对应字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
                for (ParticipantFeedbackEntity participantFeedbackEntity : participantFeedbackEntityList) {
                    ParticipantFeedbackBean participantFeedbackBean = HandleDispatchTransformUtil.transform(participantFeedbackEntity, dicsMap, organizationNameMap);
                    FireSafetyMonitoringBean fireSafetyMonitoringBean = fireSafetyEnterMap.get(participantFeedbackBean.getPersonId());
                    if (fireSafetyMonitoringBean == null) {
                        participantFeedbackBean.setWhetherEnterFireSafety(0);
                    } else if (fireSafetyMonitoringBean != null
                            && fireSafetyMonitoringBean.getEnterFireTime() != null
                            && fireSafetyMonitoringBean.getWithdrawFireTime() == null) {
                        participantFeedbackBean.setWhetherEnterFireSafety(1);
                        participantFeedbackBean.setEnterFireTime(fireSafetyMonitoringBean.getEnterFireTime());
                    } else if (fireSafetyMonitoringBean != null
                            && fireSafetyMonitoringBean.getEnterFireTime() != null
                            && fireSafetyMonitoringBean.getWithdrawFireTime() != null) {
                        participantFeedbackBean.setWhetherEnterFireSafety(0);
                        participantFeedbackBean.setEnterFireTime(fireSafetyMonitoringBean.getEnterFireTime());
                        participantFeedbackBean.setWithdrawFireTime(fireSafetyMonitoringBean.getWithdrawFireTime());
                    }
                    ParticipantFeedbackOutBean participantFeedbackOutBean = new ParticipantFeedbackOutBean();
                    BeanUtils.copyProperties(participantFeedbackBean,participantFeedbackOutBean);
                    participantFeedbackOutBean.setUid(participantFeedbackEntity.getId());
                    participantFeedbackOutBean.setCreateTime(TimeUtils.transformaStrTime(participantFeedbackEntity.getCreatedTime()));
                    res.add(participantFeedbackOutBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findIncidentParticipant", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findIncidentParticipant", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_DATA_FAIL);
        }
    }
    public List<EquipmentVehicleLoadOutBean> findEquipmentVehicleByVehicleIds(List<String> vehicleIds ){
        if (vehicleIds == null || vehicleIds.isEmpty()) {
            logService.infoLog(logger, "service", "findEquipmentCondition", "vehicleIds is null or empty.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findEquipmentVehicleByVehicleIds", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<EquipmentVehicleLoadOutBean> res = new ArrayList<>();

            //获取名称-代码对应字典
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;

            logService.infoLog(logger, "repository", "findEquipmentVehicleLoadByVehicleIds", "repository is started...");
            Long start = System.currentTimeMillis();

            List<EquipmentVehicleLoadEntity>  equipmentVehicleLoadEntityList = new ArrayList<>() ;
            if(  vehicleIds != null && vehicleIds.size() > 0 && vehicleIds.size() <= 900 ){
                equipmentVehicleLoadEntityList = equipmentRepository.findEquipmentVehicleLoadByVehicleIds(vehicleIds) ;
            }else if(  vehicleIds != null && vehicleIds.size()  > 900 ){
                int page = ( int ) Math.ceil( vehicleIds.size() / 900.0 ) ;
                for( int i = 0 ; i < page ; i++ ){
                    int startnum = i * 900 ;
                    int endnum = ( i + 1 ) * 900 ;
                    if( endnum > vehicleIds.size() ){
                        endnum = vehicleIds.size() ;
                    }
                    List<String>  batchVehcileIds = vehicleIds.subList( startnum , endnum ) ;
                    List<EquipmentVehicleLoadEntity>  bathEquipmentVehicleLoadEntityList = equipmentRepository.findEquipmentVehicleLoadByVehicleIds(batchVehcileIds) ;
                    if( bathEquipmentVehicleLoadEntityList != null && bathEquipmentVehicleLoadEntityList.size() > 0 ){
                        equipmentVehicleLoadEntityList.addAll( bathEquipmentVehicleLoadEntityList ) ;
                    }
                }
            }

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findEquipmentVehicleLoadByVehicleIds", String.format("repository is finished,execute time is :%sms", end - start));

            if( equipmentVehicleLoadEntityList != null && equipmentVehicleLoadEntityList.size() > 0 ){
                for( EquipmentVehicleLoadEntity equipmentVehicleLoadEntity  :  equipmentVehicleLoadEntityList){
                    EquipmentVehicleLoadBean equipmentVehicleLoadBean = EquipmentTransformUtil.transform( equipmentVehicleLoadEntity , dicsMap ) ;
                    EquipmentVehicleLoadOutBean equipmentVehicleLoadOutBean = new EquipmentVehicleLoadOutBean();
                    BeanUtils.copyProperties(equipmentVehicleLoadBean,equipmentVehicleLoadOutBean);
                    equipmentVehicleLoadOutBean.setCreateTime(TimeUtils.transformaStrTime(equipmentVehicleLoadEntity.getCreatedTime()));
                    equipmentVehicleLoadOutBean.setUid(equipmentVehicleLoadEntity.getId());
                    res.add(equipmentVehicleLoadOutBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findEquipmentVehicleByVehicleIds", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findEquipmentVehicleByVehicleIds", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_EQUIPMENT_FAIL);
        }
    }

}
