package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.HandleOrganizationVehicleEntity;
import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.po.VehicleStatusChangeEntity;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationRepository;
import com.dscomm.iecs.accept.dal.repository.HandleOrganizationVehicleRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentVehicleHandleUpdateInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentVehicleStatusUpdateInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HandleOrganizationVehicleBean;
import com.dscomm.iecs.accept.graphql.typebean.HandleWebSocketPushListBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.VehicleIncidentStatusMappingBean;
import com.dscomm.iecs.accept.service.*;
import com.dscomm.iecs.accept.service.pushData.PushDataService;
import com.dscomm.iecs.accept.utils.JudgeIncidentVehicleStatusUtil;
import com.dscomm.iecs.accept.utils.transform.IncidentTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.basedata.utils.EquipmentTransformUtil;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_FS;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_GD;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_JA;
import com.dscomm.iecs.ext.incident.status.INCIDENT_STATUS_TB;
import com.dscomm.iecs.ext.vehicle.status.*;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ??????????????????????????????????????????????????????
 */
@Component("handleStatusChangeServiceImpl")
public class HandleStatusChangeServiceImpl implements HandleStatusChangeService {
    private static final Logger logger = LoggerFactory.getLogger(HandleStatusChangeServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private HandleOrganizationRepository handleOrganizationRepository;
    private HandleOrganizationVehicleRepository handleOrganizationVehicleRepository;
    private ServletService servletService ;
    private VehicleIncidentStatusMappingService vehicleIncidentStatusMappingService ;
    private VehicleStatusChangeService vehicleStatusChangeService ;
    private NotifyActionService notifyActionService ;
    private IncidentService incidentService ;
    private HandleService handleService ;
    private DictionaryService dictionaryService;
    private SystemConfigurationService systemConfigurationService;
    private UserService userService;
    private PushDataService pushDataService;

    /**
     * ?????????????????????
     */
    @Autowired
    @Lazy(true)
    public HandleStatusChangeServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                         OrganizationService organizationService,
                                         HandleOrganizationRepository handleOrganizationRepository,
                                         HandleOrganizationVehicleRepository handleOrganizationVehicleRepository,
                                         ServletService servletService,
                                         VehicleIncidentStatusMappingService vehicleIncidentStatusMappingService,
                                         VehicleStatusChangeService vehicleStatusChangeService,
                                         NotifyActionService notifyActionService,
                                         IncidentService incidentService,
                                         HandleService handleService,

                                         DictionaryService dictionaryService, SystemConfigurationService systemConfigurationService, UserService userService, PushDataService pushDataService) {

        this.accessor = accessor;
        this.logService = logService;
        this.organizationService = organizationService;
        this.handleOrganizationRepository = handleOrganizationRepository;
        this.handleOrganizationVehicleRepository = handleOrganizationVehicleRepository;
        this.servletService = servletService ;
        this.vehicleIncidentStatusMappingService = vehicleIncidentStatusMappingService ;
        this.vehicleStatusChangeService = vehicleStatusChangeService ;
        this.notifyActionService = notifyActionService;
        this.incidentService = incidentService ;
        this.handleService = handleService  ;

        this.dictionaryService = dictionaryService;
        this.systemConfigurationService = systemConfigurationService;
        this.userService = userService;
        this.pushDataService = pushDataService;
    }





    /**
     * {@inheritDoc}
     *
     * @see #updateIncidentVehicleStatus( IncidentVehicleStatusUpdateInputInfo  )
     */
    @Override
    public  void  updateIncidentVehicleStatus(IncidentVehicleStatusUpdateInputInfo inputInfo    ) {
        try {
            logService.infoLog(logger, "service", "updateIncidentVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            String incidentId  = inputInfo.getIncidentId() ;
            List<IncidentVehicleHandleUpdateInputInfo> incidentVehicleHandles = inputInfo.getIncidentVehicleHandles() ;
            String newVehicleStatus = inputInfo.getVehicleStatusCode() ;
            String changeSource = inputInfo.getChangeSource() ;
            Long systemTime = servletService.getSystemTime();
            UserInfo userInfo = userService.getUserInfo();
            if (userInfo==null){
                userInfo=new UserInfo();
            }
            List<String> dics = new ArrayList<>(Arrays.asList("WLCLLX", "WLCLDJ", "WLCLZT", "YS", "JLDW", "WLRYZW", "QCLX", "PMLX","WLCLXZ"));
            //????????????-??????????????????
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            //????????????
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
            Map<String, OrganizationBean> organizationMap = organizationService.findOrganizationAll();

            //1.??????????????????
            //??????????????????????????????????????????
            List<HandleOrganizationVehicleEntity> handleOrganizationVehicles = handleOrganizationVehicleRepository.findHandleOrganizationVehiclesByIncidentId(incidentId);
            Map<String,HandleOrganizationVehicleEntity> map=new HashMap<>();
            Map<String,Map<String,String>> tempMap=new HashMap<>();
            List<String> changed=new ArrayList<>();
            List<String> vehicleIds=new ArrayList<>();
            List<String> updateVehicleIds=new ArrayList<>();
            //???????????????????????????
            List<VehicleStatusChangeEntity> vehicleStatusChangeEntities=new ArrayList<>();
            //????????????????????????
            String vehicleOnDutyStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleOnDutyStatus").getConfigValue();
            List<String> ondutyStatusList = new ArrayList<>();
            if (Strings.isNotBlank(vehicleOnDutyStatus)) {
                String[] ondutyStatus = vehicleOnDutyStatus.split(",");
                ondutyStatusList = Arrays.asList(ondutyStatus);
            }
            // ?????? ???????????????????????? ?????? ????????????????????????
            if (handleOrganizationVehicles!=null&&!handleOrganizationVehicles.isEmpty()){
                for (HandleOrganizationVehicleEntity handleOrganizationVehicle : handleOrganizationVehicles) {
                    map.put(handleOrganizationVehicle.getId(),handleOrganizationVehicle);
                    Map<String, String> temp = tempMap.get(handleOrganizationVehicle.getHandleId());
                    if (temp==null){
                        temp=new HashMap<>();
                    }
                    temp.put(handleOrganizationVehicle.getVehicleId(),handleOrganizationVehicle.getId());
                    tempMap.put(handleOrganizationVehicle.getHandleId(),temp);
                    vehicleIds.add(handleOrganizationVehicle.getVehicleId());
                }
                handleOrganizationVehicles.clear();
            }
            //????????????????????????????????????
            Map<String,String> vehicleMappingMap=new HashMap<>();
            //??????????????????????????????
            List<EquipmentVehicleEntity> vehicles=new ArrayList<>();
            List<Object[]> res = handleOrganizationVehicleRepository.findVehicles(vehicleIds);
            Map<String,EquipmentVehicleEntity> vehiclemMap=new HashMap<>();
            if (res!=null&&!res.isEmpty()){
                for (Object[] objects : res) {
                    EquipmentVehicleEntity vehicle= (EquipmentVehicleEntity) objects[0];
                    String mappingGroupId= IncidentTransformUtil.toString(objects[1]);
                    vehiclemMap.put(vehicle.getId(),vehicle);
                    if (!StringUtils.isBlank(mappingGroupId)){
                        vehicleMappingMap.put(vehicle.getId(),mappingGroupId);
                    }
                }
            }
            for (IncidentVehicleHandleUpdateInputInfo vehicleHandle : incidentVehicleHandles) {
                Map<String, String> temp = tempMap.get(vehicleHandle.getHandleId());
                HandleOrganizationVehicleEntity organizationVehicleEntity=null;
                if (temp!=null&&temp.containsKey(vehicleHandle.getVehicleId())){
                    organizationVehicleEntity = map.get(temp.get(vehicleHandle.getVehicleId()));
                    //???????????????????????????????????????
                    Boolean rt = JudgeIncidentVehicleStatusUtil.JudgeCombatVehicleStatus(organizationVehicleEntity.getVehicleStatusCode(), newVehicleStatus);
                    if (rt){
                        if( newVehicleStatus.equals( VEHICLE_STATUS_CD.getCode() )){ //	??????
                            organizationVehicleEntity.setSetOutTime( systemTime);
                        }
                        if( newVehicleStatus.equals( VEHICLE_STATUS_CDDC.getCode()  )){ //	??????
                            organizationVehicleEntity.setArriveTime( systemTime);
                        }
                        if( newVehicleStatus.equals( VEHICLE_STATUS_CDCS.getCode()  )){ //	??????
                            organizationVehicleEntity.setSendWaterTime( systemTime );
                        }
                        if( newVehicleStatus.equals( VEHICLE_STATUS_CDTS.getCode()  )){ //	??????
                            organizationVehicleEntity.setStopWaterTime( systemTime);
                        }
                        if( newVehicleStatus.equals( VEHICLE_STATUS_CDFH.getCode()  )){ //	??????
                            organizationVehicleEntity.setReturnLoadTime( systemTime);
                        }
                        if( newVehicleStatus.equals( VEHICLE_STATUS_CDZTFD.getCode()  )){ //	??????
                            organizationVehicleEntity.setHalfwayReturnTime(systemTime );
                        }
                        if( newVehicleStatus.equals( VEHICLE_STATUS_CDFD.getCode()  )){ //	??????
                            organizationVehicleEntity.setReturnTime( systemTime );
                        }
                        organizationVehicleEntity.setVehicleStatusCode( newVehicleStatus );
                        changed.add(organizationVehicleEntity.getId());

                        handleOrganizationVehicles.add(organizationVehicleEntity);

                    }
                    //??????????????????
                    //????????????????????????
                    EquipmentVehicleEntity equipmentVehicleEntity = vehiclemMap.get(vehicleHandle.getVehicleId());
                    if (equipmentVehicleEntity!=null&&!newVehicleStatus.equals(equipmentVehicleEntity.getVehicleStatusCode())){
                        equipmentVehicleEntity.setVehicleStatusCode(newVehicleStatus);
                        //???????????????????????????????????????  , ??????????????????  ?????? ?????? ??? ?????????????????????
                        if (!(ondutyStatusList.contains(newVehicleStatus))
                                || VEHICLE_STATUS_CDFD.getCode().equals(newVehicleStatus)
                                || VEHICLE_STATUS_CDZTFD.getCode().equals(newVehicleStatus)) {
                            equipmentVehicleEntity.setIncidentNumber(null);
                        }
                        updateVehicleIds.add(equipmentVehicleEntity.getId());
                        vehicles.add(equipmentVehicleEntity);
                        //??????????????????????????????
                        VehicleStatusChangeEntity vehicleStatusChangeEntity = preapareVehicleStatusChangeStatus(incidentId, newVehicleStatus, changeSource, systemTime, userInfo, vehicleHandle, organizationVehicleEntity);
                        vehicleStatusChangeEntities.add(vehicleStatusChangeEntity);
                    }
                }
            }
            //???????????????????????????
            if (!handleOrganizationVehicles.isEmpty()){
                accessor.save(handleOrganizationVehicles);
            }
            //???????????????
            if (!vehicles.isEmpty()){
                accessor.save(vehicles);
            }
            //???????????????????????????
            if (!vehicleStatusChangeEntities.isEmpty()){
                accessor.save(vehicleStatusChangeEntities);
            }
            //???????????????????????????????????????
            Set<String> orgs = new HashSet<>();

            for (EquipmentVehicleEntity vehicle : vehicles) {
                EquipmentVehicleBean bean = EquipmentTransformUtil.transform(vehicle,null, dicsMap, organizationNameMap, organizationMap);
                bean.setMappingGroupId(vehicleMappingMap.get(vehicle.getId()));
                if (!orgs.contains(bean.getOrganizationId())){
                    List<String> orgIds = new ArrayList<>();
                    orgIds.add(bean.getOrganizationId());

                    List<String> parentOrganizationId = organizationService.findParentOrganizationIds( orgIds );
                    orgs.addAll(parentOrganizationId ) ;
                    List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(parentOrganizationId));
                    orgs.addAll(orgCodes);
                }

                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.UPDATE_VEHICLE_STATUS.getCode(),bean,orgs);


            }
            // ?????????????????????????????????
            Map<String, String > otherParams  = new HashMap<>() ;
            pushDataService.pushVehicleStatusChange(   incidentId ,   incidentVehicleHandles.get(0).getHandleId() ,      updateVehicleIds  , newVehicleStatus , otherParams   ) ;

            //2.??????????????????
            updateCaseStatus(incidentId,map,changed,newVehicleStatus,incidentVehicleHandles.get(0).getHandleId() );
            //
            //??????websocekt??????
            //????????????????????????
            Set<String> orgSet = new HashSet<>();
            List<String> orgIds = new ArrayList<>();
            //????????????????????????
            List<String> hisOrganizationIds = handleOrganizationRepository.findOrganizationIdByIncidentId(incidentId) ;
            //????????????????????????
            orgIds.addAll(hisOrganizationIds);
            List<String> parentOrganizationId = organizationService.findParentOrganizationIds( orgIds );
            orgSet.addAll(parentOrganizationId ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(parentOrganizationId ));
            orgSet.addAll(orgCodes);

            HandleWebSocketPushListBean handleWebSocketPushBean = new HandleWebSocketPushListBean();

            IncidentBean incidentBean = incidentService.findIncident(incidentId , true ) ;

            List<HandleOrganizationVehicleBean> handleOrganizationVehicleList = incidentBean.getHandleOrganizationVehicleList();
            List<HandleOrganizationVehicleBean> list=new ArrayList<>();
            if (handleOrganizationVehicleList!=null&&!handleOrganizationVehicleList.isEmpty()){
                Map<String,HandleOrganizationVehicleBean> temp=new HashMap<>();
                for (HandleOrganizationVehicleBean handleOrganizationVehicleBean : handleOrganizationVehicleList) {
                    temp.put(handleOrganizationVehicleBean.getId(),handleOrganizationVehicleBean);
                }
                for (String id : changed) {
                    list.add(temp.get(id));
                }
            }

            incidentBean.setHandleOrganizationVehicleList(null);
            incidentBean.setHandleShowBeanList(null);
            incidentBean.setLocationRecordBeans(null);
            handleWebSocketPushBean.setIncidentBean(incidentBean);
            if(list!=null&&list.size()>30){
                int i=0,j=0;
                while (i<list.size()){
                    j=i+30;
                    if (j>list.size()){
                        j=list.size();
                    }
                    handleWebSocketPushBean.setHandleOrganizationVehicleBean(  list.subList(i,j));
                    notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.NOTIFY_HANDLE_STATUS.getCode(), handleWebSocketPushBean , orgSet );
                    i=j;
                }
            }else {
                handleWebSocketPushBean.setHandleOrganizationVehicleBean(  list);
                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.NOTIFY_HANDLE_STATUS.getCode(), handleWebSocketPushBean , orgSet );
            }



//            for(IncidentVehicleHandleUpdateInputInfo  incidentVehicleHandle :  incidentVehicleHandles ){
//                logService.infoLog(logger, "service", "buildIncidentVehicleStatus(" + incidentVehicleHandle.getVehicleId() + ")", "method is started...");
//                Long logStartMethod = System.currentTimeMillis();
//
//                buildIncidentVehicleStatus(incidentId , incidentVehicleHandle.getHandleId() , incidentVehicleHandle.getVehicleId()  ,
//                        newVehicleStatus  , changeSource     ) ;
//
//                Long logEndMethod = System.currentTimeMillis();
//                logService.infoLog(logger, "service", "buildIncidentVehicleStatus("+ incidentVehicleHandle.getVehicleId() + ")", String.format("method is finished,execute time is :%sms", logEndMethod - logStartMethod ));
//            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateIncidentVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateIncidentVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_VEHICLE_STATUS_FAIL);
        }

    }

    private VehicleStatusChangeEntity preapareVehicleStatusChangeStatus(String incidentId, String newVehicleStatus, String changeSource, Long systemTime, UserInfo userInfo, IncidentVehicleHandleUpdateInputInfo vehicleHandle, HandleOrganizationVehicleEntity organizationVehicleEntity) {
        VehicleStatusChangeEntity vehicleStatusChangeEntity = new VehicleStatusChangeEntity();
        vehicleStatusChangeEntity.setIncidentId( incidentId );
        vehicleStatusChangeEntity.setHandleId( vehicleHandle.getHandleId() );
        vehicleStatusChangeEntity.setVehicleId( vehicleHandle.getVehicleId() );
        if (organizationVehicleEntity!=null){
            vehicleStatusChangeEntity.setHandleOrganizationVehicleId( organizationVehicleEntity.getId() );
        }

        vehicleStatusChangeEntity.setVehicleStatusCode( newVehicleStatus );
        vehicleStatusChangeEntity.setChangeTime(systemTime);
        vehicleStatusChangeEntity.setChangeSource( changeSource);
        vehicleStatusChangeEntity.setOrganizationId( userInfo.getOrgId() );
        vehicleStatusChangeEntity.setSeatNumber( userInfo.getAgentNum() );
        vehicleStatusChangeEntity.setPersonId( userInfo.getAccount() );
        vehicleStatusChangeEntity.setRemarks( null );
        return vehicleStatusChangeEntity;
    }


    /**
     * ???????????????????????????????????????????????????
     * @return
     */
    private  void  JudgeUpdateCase( String incidentId , String vehicleId  , String newVehicleStatus  , String  handleId   ) {
        logService.infoLog(logger, "method", "JudgeUpdateCase", "private method is started...");
        Long logStart = System.currentTimeMillis();

        //??????????????????
        IncidentEntity incidentEntity = accessor.getById(  incidentId  , IncidentEntity.class );
        if( incidentEntity == null  ){
            return    ;
        }

        logService.infoLog(logger, "method", "updateIncidentStatus", "private method is started...");
        Long logStartIncidentStatus = System.currentTimeMillis();

        //?????????????????? ?????? ?????????  ????????????????????????????????????????????????????????????
        if (  incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_JA.getCode() )
                || incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_TB.getCode() )
                || incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_FS.getCode() )
                || incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_GD.getCode() )  )
        {
            //???????????? ??????????????????
        }else {
            //?????? ???????????? ???????????? ??????
            //?????? ????????????
            VehicleIncidentStatusMappingBean statusChangeRule = vehicleIncidentStatusMappingService.findVehicleIncidentStatusMapping(incidentEntity.getIncidentTypeCode(), newVehicleStatus);
            if (null != statusChangeRule) {
                Boolean isModifyStatus = true; //????????????????????????????????????
                // ???????????????????????????????????????????????????

                //????????????
                if ("1".equals(statusChangeRule.getInfluenceMode())) {
                    if (  JudgeIncidentVehicleStatusUtil.JudgeIncidentStatus( incidentEntity.getIncidentStatusCode() ,statusChangeRule.getIncidentStatusCode() )   ) {
                        //????????????????????????
                        isModifyStatus = true ;
                    } else {
                        isModifyStatus = false;
                    }
                } else { //????????????
                    //????????????????????????
                    List<HandleOrganizationVehicleBean> combatVehicleBeans =  handleService.findHandleOrganizationVehicle(incidentId , null , false );
                    for (HandleOrganizationVehicleBean combatVehicleBean : combatVehicleBeans) {
                        if (combatVehicleBean.getVehicleId().equals(vehicleId)) {  // ?????????????????????
                            continue;
                        } else {
                            // ??????????????????????????????????????????????????????????????????????????????????????????????????????
                            if (  JudgeIncidentVehicleStatusUtil.JudgeCombatVehicleStatus( combatVehicleBean.getVehicleStatusCode() , newVehicleStatus )  ) {
                                isModifyStatus = false;
                                break;
                            }
                        }
                    }
                }
                if (isModifyStatus) {

                    //???????????????????????????????????????????????????????????????????????????
                    String  changeIncidentStautsCode =  statusChangeRule.getIncidentStatusCode() ;
                    // ?????? ??????????????????( ?????? ?????? ?????? ?????????  ???????????????????????? )
                    if(  VEHICLE_STATUS_CDZTFD.getCode().equals( newVehicleStatus ) ){
                        //?????????????????? ????????????
                        Integer fdNum = handleOrganizationVehicleRepository.findHandleOrganizationVehicleStatusCountNum( incidentId , VEHICLE_STATUS_CDFD.getCode()  )  ;
                        if( fdNum != null && fdNum > 0 ){
                            VehicleIncidentStatusMappingBean fdChangeRule =
                                    vehicleIncidentStatusMappingService.findVehicleIncidentStatusMapping(incidentEntity.getIncidentTypeCode(), VEHICLE_STATUS_CDFD.getCode() );
                            changeIncidentStautsCode = fdChangeRule.getIncidentStatusCode() ;
                        }
                    }
                    //??????????????????????????????
                    incidentService.updateIncidentStatus(incidentId,  changeIncidentStautsCode , handleId    );

                }
            }
        }

        Long logEndIncidentStatus = System.currentTimeMillis();
        logService.infoLog(logger, "method", "updateVehicleStatus", String.format("private method is finished,execute time is :%sms", logEndIncidentStatus - logStartIncidentStatus ));

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "method", "JudgeUpdateCase", String.format("private method is finished,execute time is :%sms", logEnd - logStart ));
    }


    /**
     * ???????????????????????????????????????????????????
     * @return
     */
    private  void  updateCaseStatus( String incidentId , Map<String,HandleOrganizationVehicleEntity> map,List<String> handeVehicleIds , String newVehicleStatus,String  handleId) {
        logService.infoLog(logger, "method", "JudgeUpdateCase", "private method is started...");
        Long logStart = System.currentTimeMillis();

        //??????????????????
        IncidentEntity incidentEntity = accessor.getById(  incidentId  , IncidentEntity.class );
        if( incidentEntity == null  ){
            return    ;
        }

        logService.infoLog(logger, "method", "updateIncidentStatus", "private method is started...");
        Long logStartIncidentStatus = System.currentTimeMillis();

        //?????????????????? ?????? ?????????  ????????????????????????????????????????????????????????????
        if (  incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_JA.getCode() )
                || incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_TB.getCode() )
                || incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_FS.getCode() )
                || incidentEntity.getIncidentStatusCode().equals(  INCIDENT_STATUS_GD.getCode() )  )
        {
            //???????????? ??????????????????
        }else {
            //?????? ???????????? ???????????? ??????
            //?????? ????????????
            VehicleIncidentStatusMappingBean statusChangeRule = vehicleIncidentStatusMappingService.findVehicleIncidentStatusMapping(incidentEntity.getIncidentTypeCode(), newVehicleStatus);
            if (null != statusChangeRule) {
                Boolean isModifyStatus = true; //????????????????????????????????????
                // ???????????????????????????????????????????????????

                //????????????
                if ("1".equals(statusChangeRule.getInfluenceMode())) {
                    if (  JudgeIncidentVehicleStatusUtil.JudgeIncidentStatus( incidentEntity.getIncidentStatusCode() ,statusChangeRule.getIncidentStatusCode() )   ) {
                        //????????????????????????
                        isModifyStatus = true ;
                    } else {
                        isModifyStatus = false;
                    }
                } else { //????????????
                    //????????????????????????
//                    List<HandleOrganizationVehicleBean> combatVehicleBeans =  handleService.findHandleOrganizationVehicle(incidentId , null , false );
                    for (String key : map.keySet()) {
                        if (!handeVehicleIds.contains(key)){
                            // ??????????????????????????????????????????????????????????????????????????????????????????????????????
                            if (JudgeIncidentVehicleStatusUtil.JudgeCombatVehicleStatus( map.get(key).getVehicleStatusCode() , newVehicleStatus ) ){
                                isModifyStatus = false;
                                break;
                            }
                        }
                    }

                }
                if (isModifyStatus) {

                    //???????????????????????????????????????????????????????????????????????????
                    String  changeIncidentStautsCode =  statusChangeRule.getIncidentStatusCode() ;
                    // ?????? ??????????????????( ?????? ?????? ?????? ?????????  ???????????????????????? )
                    if(  VEHICLE_STATUS_CDZTFD.getCode().equals( newVehicleStatus ) ){
                        //?????????????????? ????????????
                        Integer fdNum = handleOrganizationVehicleRepository.findHandleOrganizationVehicleStatusCountNum( incidentId , VEHICLE_STATUS_CDFD.getCode()  )  ;
                        if( fdNum != null && fdNum > 0 ){
                            VehicleIncidentStatusMappingBean fdChangeRule =
                                    vehicleIncidentStatusMappingService.findVehicleIncidentStatusMapping(incidentEntity.getIncidentTypeCode(), VEHICLE_STATUS_CDFD.getCode() );
                            changeIncidentStautsCode = fdChangeRule.getIncidentStatusCode() ;
                        }
                    }
                    //??????????????????????????????
                    incidentService.updateIncidentStatus(incidentId,  changeIncidentStautsCode , handleId    );

                }
            }
        }

        Long logEndIncidentStatus = System.currentTimeMillis();
        logService.infoLog(logger, "method", "updateVehicleStatus", String.format("private method is finished,execute time is :%sms", logEndIncidentStatus - logStartIncidentStatus ));

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "method", "JudgeUpdateCase", String.format("private method is finished,execute time is :%sms", logEnd - logStart ));
    }



    /**
     * ???????????????????????????????????????????????????
     * @return
     */
    private  String  JudgeUpdateVehicleStatus( String incidentId  ,  String  handleId  , String vehicleId  , String newVehicleStatus , String changeSource  ) {
        logService.infoLog(logger, "method", "JudgeUpdateVehicleStatus", "private method is started...");
        Long logStart = System.currentTimeMillis();

        //????????????????????????
        String  handleOrganizationVehicleId = updateHandleOrganizationVehicleStatus( incidentId  ,    handleId  , vehicleId , newVehicleStatus );

        //?????????????????? ????????????????????????????????????
        vehicleStatusChangeService.updateVehicleStatus( incidentId , handleId , vehicleId ,  handleOrganizationVehicleId ,  newVehicleStatus ,  changeSource );

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "method", "JudgeUpdateVehicleStatus", String.format("private method is finished,execute time is :%sms", logEnd - logStart));

        return  handleOrganizationVehicleId ;
    }


    /**
     * {@inheritDoc}
     *
     * @see  #updateHandleOrganizationVehicleStatus(String , String , String , String      )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public  String   updateHandleOrganizationVehicleStatus( String incidentId  ,  String  handleId  , String vehicleId , String newVehicleStatus  )  {
        if ( Strings.isBlank(incidentId)  ||  Strings.isBlank(vehicleId) ||  Strings.isBlank(newVehicleStatus) ) {
            logService.infoLog(logger, "service", "updateHandleOrganizationVehicleStatus", "incidentId or vehicleId or newVehicleStatus is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "updateHandleOrganizationVehicleStatus", "service is started...");
            Long logStart = System.currentTimeMillis();

            String handleOrganizationVehicleId = null ;

            //????????????id ??????id
            List<HandleOrganizationVehicleEntity> handleOrganizationVehicleEntityList  = null ;

                    logService.infoLog(logger, "repository", "findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId , vehicleId ", "repository is started...");
            Long start = System.currentTimeMillis();

            if( Strings.isBlank( handleId )){
                handleOrganizationVehicleEntityList = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId  ,  vehicleId );

            }else{
                handleOrganizationVehicleEntityList  = handleOrganizationVehicleRepository.findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId ,handleId ,  vehicleId );
            }


            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHandleOrganizationVehicleByIncidentIdAndVehicleId(incidentId , vehicleId ", String.format("repository is finished,execute time is :%sms", end - start));


            if( null != handleOrganizationVehicleEntityList && handleOrganizationVehicleEntityList.size() > 0 ){
                HandleOrganizationVehicleEntity handleOrganizationVehicleEntity = handleOrganizationVehicleEntityList.get(0) ;

                if(  !JudgeIncidentVehicleStatusUtil.JudgeCombatVehicleStatus( handleOrganizationVehicleEntity.getVehicleStatusCode() , newVehicleStatus ) ){
                    handleOrganizationVehicleId = handleOrganizationVehicleEntity.getId() ;
                    return  handleOrganizationVehicleId ;
                }

                if( newVehicleStatus.equals( VEHICLE_STATUS_CD.getCode() )){ //	??????
                    handleOrganizationVehicleEntity.setSetOutTime( servletService.getSystemTime() );
                }
                if( newVehicleStatus.equals( VEHICLE_STATUS_CDDC.getCode()  )){ //	??????
                    handleOrganizationVehicleEntity.setArriveTime( servletService.getSystemTime()  );
                }
                if( newVehicleStatus.equals( VEHICLE_STATUS_CDCS.getCode()  )){ //	??????
                    handleOrganizationVehicleEntity.setSendWaterTime( servletService.getSystemTime()  );
                }
                if( newVehicleStatus.equals( VEHICLE_STATUS_CDTS.getCode()  )){ //	??????
                    handleOrganizationVehicleEntity.setStopWaterTime( servletService.getSystemTime()  );
                }
                if( newVehicleStatus.equals( VEHICLE_STATUS_CDFH.getCode()  )){ //	??????
                    handleOrganizationVehicleEntity.setReturnLoadTime( servletService.getSystemTime()  );
                }
                if( newVehicleStatus.equals( VEHICLE_STATUS_CDZTFD.getCode()  )){ //	??????
                    handleOrganizationVehicleEntity.setHalfwayReturnTime( servletService.getSystemTime()  );
                }
                if( newVehicleStatus.equals( VEHICLE_STATUS_CDFD.getCode()  )){ //	??????
                    handleOrganizationVehicleEntity.setReturnTime( servletService.getSystemTime()  );
                }
                handleOrganizationVehicleEntity.setVehicleStatusCode( newVehicleStatus );

                handleOrganizationVehicleId = handleOrganizationVehicleEntity.getId() ;


                //????????????
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntity)", "repository is started...");
                Long saveStart = System.currentTimeMillis();

                accessor.save( handleOrganizationVehicleEntity );

                Long saveEnd = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "save(dbHandleOrganizationVehicleEntity)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));



            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateHandleOrganizationVehicleStatus", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  handleOrganizationVehicleId ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "updateHandleOrganizationVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.RECOVER_HANDLE_INCIDENT_ID_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see  #buildIncidentVehicleStatus( String, String ,  String   , String ,String    )
     */
    @Transactional( rollbackFor =  Exception.class , propagation= Propagation.REQUIRES_NEW  )
    @Override
    public   void  buildIncidentVehicleStatus( String incidentId   ,  String  handleId ,   String  vehicleId   ,
                                               String newVehicleStatus  , String changeSource    ){
        if (Strings.isBlank(incidentId) || Strings.isBlank(vehicleId) || Strings.isBlank(newVehicleStatus) ) {
            logService.infoLog(logger, "service", "changeHandleOrganizationsIncidentId", "incidentId or vehicleId or newVehicleStatus is Blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try{
            logService.infoLog(logger, "method", "buildIncidentVehicleStatus", "private method is started...");
            Long logStart = System.currentTimeMillis();


            //???????????????????????????????????????????????????
            if( Strings.isNotBlank( incidentId )){
                JudgeUpdateCase( incidentId , vehicleId , newVehicleStatus , handleId    ) ;
            }

            //??????????????????  ????????????????????????
            String handleOrganizationVehicleId = JudgeUpdateVehicleStatus(incidentId, handleId , vehicleId   ,  newVehicleStatus , changeSource ) ;


            //??????websocekt??????
            //????????????????????????
            Set<String> orgs = new HashSet<>();
            List<String> orgIds = new ArrayList<>();
            //????????????????????????
            List<String> hisOrganizationIds = handleOrganizationRepository.findOrganizationIdByIncidentId(incidentId) ;
            //????????????????????????
            orgIds.addAll(hisOrganizationIds);
            List<String> parentOrganizationId = organizationService.findParentOrganizationIds( orgIds );
            orgs.addAll(parentOrganizationId ) ;
            List<String> orgCodes =  organizationService.findOrganizationCodesByIds(new ArrayList<>(parentOrganizationId ));
            orgs.addAll(orgCodes);

            HandleWebSocketPushListBean handleWebSocketPushBean = new HandleWebSocketPushListBean();

            IncidentBean incidentBean = incidentService.findIncident(incidentId , false ) ;
            handleWebSocketPushBean.setIncidentBean(incidentBean);

            HandleOrganizationVehicleBean  handleOrganizationVehicleBean =
                    handleService.findHandleOrganizationVehicleById( handleOrganizationVehicleId )   ;
            handleWebSocketPushBean.setHandleOrganizationVehicleBean(  Arrays.asList(handleOrganizationVehicleBean ));

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.NOTIFY_HANDLE_STATUS.getCode(), handleWebSocketPushBean , orgs );



            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "method", "buildIncidentVehicleStatus", String.format("private method is finished,execute time is :%sms", logEnd - logStart));

        }
        catch (Exception ex){
            logService.erorLog(logger, "service", "updateIncidentVehicleStatus", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.UPDATE_INCIDENT_VEHICLE_STATUS_FAIL);

        }

    }










}
