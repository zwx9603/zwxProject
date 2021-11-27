package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.repository.AcceptNativeQueryRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.service.DispatchPlanUsageRecordService;
import com.dscomm.iecs.accept.service.PlanDispatchService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.PlanDispatchEntity;
import com.dscomm.iecs.basedata.dal.po.PlanEntity;
import com.dscomm.iecs.basedata.dal.repository.PlanRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.PlanBean;
import com.dscomm.iecs.basedata.graphql.typebean.PlanDispatchBean;
import com.dscomm.iecs.basedata.graphql.typebean.PlanSupplementBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.basedata.utils.KeyUnitTransformUtil;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
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
 * 描述：预案 服务类实现
 */
@Component("planDispatchServiceImpl")
public class PlanDispatchServiceImpl implements PlanDispatchService {
    private static final Logger logger = LoggerFactory.getLogger(PlanDispatchServiceImpl.class);
    private LogService logService;
    private GeneralAccessor accessor;
    private Environment env;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private PlanRepository planRepository;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;
    private UserService userService ;
    private VehicleService vehicleService ;
    private SystemConfigurationService systemConfigurationService ;
    private AcceptNativeQueryRepository  acceptNativeQueryRepository ;
    private DispatchPlanUsageRecordService dispatchPlanUsageRecordService;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public PlanDispatchServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                   Environment env, DictionaryService dictionaryService, OrganizationService organizationService,
                                   PlanRepository planRepository,
                                   AuditLogService auditLogService, SubAuditService subAuditService,
                                   UserService userService, VehicleService vehicleService,
                                   SystemConfigurationService systemConfigurationService,
                                   AcceptNativeQueryRepository acceptNativeQueryRepository,

                                   DispatchPlanUsageRecordService dispatchPlanUsageRecordService) {
        this.logService = logService;
        this.accessor = accessor;
        this.env = env;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.planRepository = planRepository;
        this.auditLogService = auditLogService;
        this.subAuditService = subAuditService;
        this.userService = userService ;
        this.vehicleService = vehicleService ;
        this.systemConfigurationService = systemConfigurationService ;
        this.acceptNativeQueryRepository = acceptNativeQueryRepository;
        this.dispatchPlanUsageRecordService = dispatchPlanUsageRecordService;

        dics = new ArrayList<>(Arrays.asList("WLCLLX", "WLCLDJ", "WLZZGN","YAZL", "YALX", "YAZT" ,"YADXLX"));

    }



    /**
     * {@inheritDoc}
     *
     * @see  #findPlanDispatchByPlanId(String)
     */
    @Transactional(  rollbackFor =  Exception.class )
    @Override
    public List<PlanDispatchBean> findPlanDispatchByPlanId(String planId) {
        if (Strings.isBlank(planId)) {
            logService.infoLog(logger, "service", "findPlanDispatchByPlanId", "planId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPlanDispatchByPlanId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PlanDispatchBean> res = new ArrayList<>();

            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", "repository is started...");
            Long startIn = System.currentTimeMillis();

            List<PlanDispatchEntity> planDispatchEntityList = planRepository.findPlanDispatchByPlanId(planId);

            Long endIn = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", String.format("repository is finished,execute time is :%sms", endIn - startIn));

            if (null != planDispatchEntityList && planDispatchEntityList.size() > 0) {
                for (PlanDispatchEntity planDispatchEntity : planDispatchEntityList) {
                    EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( planDispatchEntity.getVehicleId() ) ;
                    PlanDispatchBean planDispatchBean = KeyUnitTransformUtil.transform(planDispatchEntity, dicsMap, organizationNameMap , vehicleBean);
                    res.add(planDispatchBean);
                }
            }

            //保存操作日志记录
            UserInfo userInfo = userService.getUserInfo();
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_PLANDISPATCH.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc( " dispatch plan vehicle  planId :  \"" + planId  + "\" ");
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_PLANDISPATCH.getName());


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPlanByKeyUnitId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPlanByKeyUnitId", String.format(" execution error  "  ), ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_PLAN_DISPATCH_FAIL);
        }

    }



    /**
     * {@inheritDoc}
     *
     * @see  #findPlanDispatchByKeyUnitId(String)
     */
    @Transactional(  rollbackFor =  Exception.class )
    @Override
    public List<PlanDispatchBean> findPlanDispatchByKeyUnitId(String keyUnitId) {
        if (Strings.isBlank(keyUnitId)) {
            logService.infoLog(logger, "service", "findPlanDispatchByKeyUnitId", "keyUnitId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPlanDispatchByKeyUnitId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PlanDispatchBean> res = new ArrayList<>();

            Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", "repository is started...");
            Long startIn = System.currentTimeMillis();

            List<PlanDispatchEntity> planDispatchEntityList = planRepository.findPlanDispatchByKeyUnitId(keyUnitId);

            Long endIn = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanDispatchByPlanId( planId )", String.format("repository is finished,execute time is :%sms", endIn - startIn));

            if (null != planDispatchEntityList && planDispatchEntityList.size() > 0) {
                //获取历史使用次数
                Map<String, Integer> map = dispatchPlanUsageRecordService.countDispatchPlanUsageRecord();
                for (PlanDispatchEntity planDispatchEntity : planDispatchEntityList) {
                    EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( planDispatchEntity.getVehicleId() ) ;
                    PlanDispatchBean planDispatchBean = KeyUnitTransformUtil.transform(planDispatchEntity, dicsMap, organizationNameMap , vehicleBean );
                    if (map.containsKey(planDispatchBean.getId())){
                        planDispatchBean.setUsageTimes(map.get(planDispatchBean.getId()));
                    }
                    res.add(planDispatchBean);
                }
            }

            //保存操作日志记录
            UserInfo userInfo = userService.getUserInfo();
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
            auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
            auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_PLANDISPATCH.getCode()));
            auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
            auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgName());
            auditLogSaveInputInfo.setOperateSeatNumber(userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
            auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
            auditLogSaveInputInfo.setAcceptancePersonName(userInfo.getPersonName());
            auditLogSaveInputInfo.setIpAddress(userInfo.getClientIp());
            auditLogSaveInputInfo.setDesc( " dispatch keyUnit plan vehicle  keyUnitId :  \"" + keyUnitId  + "\" " );
            auditLogSaveInputInfo.setRemarks(null);
            auditLogService.saveAuditLog(auditLogSaveInputInfo);
            subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgId(), userInfo.getOrgName(),
                    "Add", "Ok", OperationTypeEnum.OPERATIONTYPE_PLANDISPATCH.getName());


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPlanByKeyUnitId", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            res.sort((a,b)->b.getUsageTimes().compareTo(a.getUsageTimes()));
            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPlanByKeyUnitId", String.format(" execution error" ), ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_PLAN_DISPATCH_FAIL);
        }

    }





    /**
     * {@inheritDoc}
     *
     * @see #findPlanDispatchSupplementKeyUnitId( String )
     */
    @Transactional(  rollbackFor =  Exception.class )
    @Override
    public    List<PlanBean> findPlanDispatchSupplementKeyUnitId( String keyUnitId )  {
        if ( Strings.isBlank( keyUnitId) ) {
            logService.infoLog(logger, "service", "findPlanDispatchSupplementKeyUnitId", "organizationIds is null or empty.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findPlanDispatchSupplementKeyUnitId", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<PlanBean> res = new ArrayList<>();

            //根据重点单位id 获得 预案
            logService.infoLog(logger, "repository", "findPlanByKeyUnitIds( keyUnitIds )", "repository is started...");
            Long planStart = System.currentTimeMillis();

            List<PlanEntity> planEntityList = planRepository.findPlanByKeyUnitId( keyUnitId );

            Long planEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanByKeyUnitIds( keyUnitIds )", String.format("repository is finished,execute time is :%sms", planEnd - planStart));


            //装配返回结果（预案 调派信息 ）
            if (null != planEntityList && planEntityList.size() > 0) {

                Map<String, Map<String, String>> dicsMap  = dictionaryService.findDictionaryMap(dics) ;
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

                //查询可调派车辆的状态代码集合
                List<String> dispatchStatusList = new ArrayList<>();
                String vehicleDispatchStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleDispatchStatus").getConfigValue();
                if (Strings.isNotBlank(vehicleDispatchStatus)) {
                    String[] dispatchStatus = vehicleDispatchStatus.split(",");
                    dispatchStatusList = Arrays.asList(dispatchStatus);
                }

                //根据重点单位id集合、车辆状态代码集合 查询调派车辆信息
                logService.infoLog(logger, "repository", "findPlanDispatchByKeyUnitIds( organizationIds,dispatchStatusList )", "repository is started...");
                Long startIn = System.currentTimeMillis();

                //获得 重点单位预案中 可调派车辆id
                List<Object []> planDispatchVehicleList = planRepository.findPlanDispatchVehicleKeyUnitId( keyUnitId   );
                //获得重点单位预案中  原调派车辆id
                List<Object []> planDispatchVehicleStatusList = planRepository.findPlanDispatchVehicleKeyUnitIdAndVehicleStatus( keyUnitId , dispatchStatusList );

                Long endIn = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findPlanDispatchByKeyUnitIds( organizationIds,dispatchStatusList )", String.format("repository is finished,execute time is :%sms", endIn - startIn));


                //构建 预案维护调派车辆id 预案可调派车辆id
                //构建 预案维护调派车辆id  预案可调派车辆id
                Map<String,List<String> > planDispatchVehicleMap = new HashMap<>() ;
                Map<String,List<String> > planDispatchVehicleStatusMap = new HashMap<>() ;
                for( Object [] planDispatchVehicle :  planDispatchVehicleList ){
                    String planId = ( String ) planDispatchVehicle[0];
                    String dispatchVehicleId = ( String ) planDispatchVehicle[1];
                    List<String> dispatchVehicleIdList = planDispatchVehicleMap.get( planId );
                    if( dispatchVehicleIdList == null ){
                        dispatchVehicleIdList = new ArrayList<>() ;
                    }
                    //构建
                    dispatchVehicleIdList.add( dispatchVehicleId );
                    planDispatchVehicleMap.put( planId  , dispatchVehicleIdList );
                    //构建可调派信息
                    List<String> dispatchVehicleStatusIdList = planDispatchVehicleStatusMap.get( planId );
                    if( dispatchVehicleStatusIdList == null ){
                        dispatchVehicleStatusIdList = new ArrayList<>() ;
                    }
                    planDispatchVehicleStatusMap.put( planId  , dispatchVehicleStatusIdList );


                }
                //补充 可调派信息
                for( Object [] planDispatchVehicleStatus :  planDispatchVehicleStatusList ){
                    String planId = ( String ) planDispatchVehicleStatus[0];
                    String dispatchVehicleId = ( String ) planDispatchVehicleStatus[1];
                    List<String> dispatchVehicleStatusIdList = planDispatchVehicleStatusMap.get( planId );
                    dispatchVehicleStatusIdList.add( dispatchVehicleId );
                    planDispatchVehicleStatusMap.put( planId  , dispatchVehicleStatusIdList );
                }

                //填充 预案调派西悉尼
                for (PlanEntity planEntity : planEntityList) {
                    PlanBean planBean = KeyUnitTransformUtil.transform(planEntity, dicsMap, organizationNameMap);
                    String planId = planBean.getId();
                    //获得 预案维护车辆id 集合
                    List<String> dispatchVehicleIdList = planDispatchVehicleMap.get( planId ) ;
                    //获得 预案维护可调派车辆id 集合
                    List<String> dispatchVehicleStatusIdList = planDispatchVehicleStatusMap.get( planId );

                    planBean.setPlanDispatchNum( dispatchVehicleIdList == null ? 0 : dispatchVehicleIdList.size() );
                    planBean.setPlanDispatchVehicleIdList( dispatchVehicleIdList );

                    planBean.setDispatchStatusNum( dispatchVehicleStatusIdList == null ? 0 : dispatchVehicleStatusIdList.size()  );
                    planBean.setDispatchVehicleStatusIdList( dispatchVehicleStatusIdList );

                    //不存在车辆信息id 集合
                    List<String> noVehicle = new ArrayList<>() ;
                    //不可调派车辆id 补充车辆id 集合
                    List<String> disabledDispatchVehicleIdList = new ArrayList<>();
                    String  planDispatchTips = "" ;
                    List<PlanSupplementBean> planSupplementBeanList = new ArrayList<>() ;
                    if( planBean.getPlanDispatchNum() > 0 && planBean.getPlanDispatchNum() > planBean.getDispatchStatusNum() ){
                        Map<String , PlanSupplementBean > planSupplementMap = new HashMap<>() ;
                        for( String dispatchVehicleId  :  dispatchVehicleIdList ){
                            Boolean whether = dispatchVehicleStatusIdList.contains( dispatchVehicleId ) ;
                            if(  !whether  ){
                                disabledDispatchVehicleIdList.add( dispatchVehicleId ) ;
                                //添加案件补充车辆信息
                                EquipmentVehicleBean vehicleBean =  vehicleService.findVehicleCache( dispatchVehicleId ) ;
                                // 判断维护车辆是否存在
                                if( vehicleBean != null ){
                                    String vehicleTypeCode = vehicleBean.getVehicleTypeCode() ; //车辆类型编码
                                    PlanSupplementBean planSupplementVehicleTypeBean  = planSupplementMap.get( vehicleTypeCode ) ;
                                    //判断 类型车辆是否已经添加补充信息
                                    if( planSupplementVehicleTypeBean == null ){
                                        //构建 补充信息
                                        planSupplementVehicleTypeBean = new PlanSupplementBean() ;
                                        //判定没有装备类型代码 车辆类型名称的时候 用装备名称 车辆名称代替
                                        if (StringUtils.isNotBlank(vehicleBean.getVehicleTypeName())) {
                                            planSupplementVehicleTypeBean.setVehicleTypeName( vehicleBean.getVehicleTypeName() );
                                        } else {
                                            planSupplementVehicleTypeBean.setVehicleTypeName( vehicleBean.getVehicleName() );
                                        }
                                        planSupplementVehicleTypeBean.setVehicleTypeCode( vehicleBean.getVehicleTypeCode()  );


                                        List<String> disabledDispatchVehicleType = new ArrayList<>() ;
                                        disabledDispatchVehicleType.add( dispatchVehicleId ) ;

                                        List<String>  planSupplementVehicleIdList = new ArrayList<>() ;
                                        String vehicleOrganizationId = vehicleBean.getOrganizationId() ; //车辆所在机构
                                        List<String> organizationOtherVehicleIds  = acceptNativeQueryRepository.findEquipmentVehicle(
                                                vehicleOrganizationId , vehicleTypeCode , dispatchStatusList ) ;
                                        List<String> adjacentPriorityOrganizationVehicleIds = acceptNativeQueryRepository.findEquipmentVehicleByAdjacentPriority(
                                                vehicleOrganizationId , vehicleTypeCode , dispatchStatusList ) ;
                                        if( organizationOtherVehicleIds != null ){
                                            planSupplementVehicleIdList.addAll( organizationOtherVehicleIds );
                                        }
                                        if( organizationOtherVehicleIds != null ){
                                            planSupplementVehicleIdList.addAll( adjacentPriorityOrganizationVehicleIds  );
                                        }
                                        planSupplementVehicleTypeBean.setDisabledDispatchVehicleIdList( disabledDispatchVehicleType );
                                        planSupplementVehicleTypeBean.setPlanSupplementVehicleIdList( planSupplementVehicleIdList );
                                        planSupplementVehicleTypeBean.setPlanSupplementNum( planSupplementVehicleTypeBean.getDisabledDispatchVehicleIdList().size() );

                                    }else{
                                        List<String> disabledDispatchVehicleType = planSupplementVehicleTypeBean.getDisabledDispatchVehicleIdList() ;
                                        disabledDispatchVehicleType.add( dispatchVehicleId ) ;
                                        planSupplementVehicleTypeBean.setDisabledDispatchVehicleIdList( disabledDispatchVehicleType );
                                        planSupplementVehicleTypeBean.setPlanSupplementNum( planSupplementVehicleTypeBean.getDisabledDispatchVehicleIdList().size() );

                                    }
                                    planSupplementMap.put( vehicleTypeCode , planSupplementVehicleTypeBean ) ;

                                }else{
                                    //添加不存在车辆信息id 集合
                                    noVehicle.add( dispatchVehicleId ) ;
                                }

                            }
                        }
                        if( planSupplementMap.size() > 0  ){
                            planSupplementBeanList.addAll( planSupplementMap.values() ) ;
                            //构建提示信息
                            for( PlanSupplementBean planSupplementBean : planSupplementMap.values() ){
                                planDispatchTips = planDispatchTips + planSupplementBean.getVehicleTypeName()
                                        + "缺" + planSupplementBean.getDisabledDispatchVehicleIdList().size() + "辆;"  ;
                            }
                        }
                    }
                    planBean.setNoVehicleIdList( noVehicle );
                    planBean.setDisabledDispatchVehicleIdList( disabledDispatchVehicleIdList );
                    planBean.setPlanDispatchTips( planDispatchTips );
                    planBean.setPlanSupplementBeanList( planSupplementBeanList );

                    res.add(planBean);
                }
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findPlanDispatchSupplementKeyUnitId", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findPlanDispatchSupplementKeyUnitId", String.format(" execution error" ), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_PLAN_FAIL);
        }

    }






















}
