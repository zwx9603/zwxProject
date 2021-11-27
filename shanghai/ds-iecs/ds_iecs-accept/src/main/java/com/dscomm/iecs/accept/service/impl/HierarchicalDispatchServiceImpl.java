package com.dscomm.iecs.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.accept.dal.po.HierarchicalDispatchEntity;
import com.dscomm.iecs.accept.dal.repository.AcceptNativeQueryRepository;
import com.dscomm.iecs.accept.dal.repository.HierarchicalDispatchRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.HierarchicalDispatchQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.HierarchicalDispatchSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.CaseAutoUpdateWarnBean;
import com.dscomm.iecs.accept.graphql.typebean.HierarchicalDispatchBean;
import com.dscomm.iecs.accept.graphql.typebean.HierarchicalDispatchVehicleBean;
import com.dscomm.iecs.accept.service.CaseAutoUpdateWarnSerivce;
import com.dscomm.iecs.accept.service.DispatchPlanUsageRecordService;
import com.dscomm.iecs.accept.service.HierarchicalDispatchService;
import com.dscomm.iecs.accept.utils.transform.HandleDispatchTransformUtil;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.DateHandleUtils;
import com.dscomm.iecs.basedata.dal.po.OrganizationEntity;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.ext.WarnTypeBakEnum;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.Pagination;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;

/**
 * 描述：等级调派 服务类实现
 */
@Component("hierarchicalDispatchServiceImpl")
public class HierarchicalDispatchServiceImpl implements HierarchicalDispatchService {
    private static final Logger logger = LoggerFactory.getLogger(HierarchicalDispatchServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private HierarchicalDispatchRepository hierarchicalDispatchRepository;
    private AuditLogService auditLogService ;
    private SubAuditService subAuditService;
    private SystemConfigurationService systemConfigurationService ;
    private UserService userService ;
    private ServletService servletService ;
    private AcceptNativeQueryRepository  acceptNativeQueryRepository ;

    private CaseAutoUpdateWarnSerivce caseAutoUpdateWarnSerivce ;
    private DispatchPlanUsageRecordService dispatchPlanUsageRecordService;


    private List<String> dics;
    /**
     * 默认的构造函数
     */
    @Autowired
    public HierarchicalDispatchServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor, LogService logService, Environment env,
                                           HierarchicalDispatchRepository hierarchicalDispatchRepository, DictionaryService dictionaryService,
                                           OrganizationService organizationService,
                                           AuditLogService auditLogService, SubAuditService subAuditService, SystemConfigurationService systemConfigurationService,
                                           UserService userService, ServletService servletService, AcceptNativeQueryRepository acceptNativeQueryRepository,
                                           CaseAutoUpdateWarnSerivce caseAutoUpdateWarnSerivce,

                                           DispatchPlanUsageRecordService dispatchPlanUsageRecordService) {
        this.accessor = accessor;
        this.env = env ;
        this.logService = logService;
        this.accessor = accessor;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.hierarchicalDispatchRepository = hierarchicalDispatchRepository;
        this.auditLogService = auditLogService ;
        this.subAuditService = subAuditService ;
        this.systemConfigurationService = systemConfigurationService ;
        this.userService = userService ;
        this.servletService = servletService ;
        this.acceptNativeQueryRepository = acceptNativeQueryRepository ;

        this.caseAutoUpdateWarnSerivce = caseAutoUpdateWarnSerivce ;
        this.dispatchPlanUsageRecordService = dispatchPlanUsageRecordService;

        dics = new ArrayList<>(Arrays.asList("AJLX", "CZDX", "AJDJ" ,"WLCLLX"  ));

    }

    /**
     * {@inheritDoc}
     *
     * @see HierarchicalDispatchService#findHierarchicalDispatchCondition(HierarchicalDispatchQueryInputInfo)
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationBean<HierarchicalDispatchBean> findHierarchicalDispatchCondition(HierarchicalDispatchQueryInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "findHierarchicalDispatchCondition", "HierarchicalDispatchQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findHierarchicalDispatchCondition", "service is started...");
            Long logStart = System.currentTimeMillis();

            PaginationBean res = new PaginationBean ();
            List<HierarchicalDispatchBean> beans = new ArrayList<>();

            //判断是否存在查询机构范围 无 默认登录机构的等级方案
            if( Strings.isBlank( inputInfo.getScopeSquadronId() )){
                UserInfo userInfo = userService.getUserInfo() ;
                inputInfo.setScopeSquadronId( userInfo.getOrgId() );
            }

            logService.infoLog(logger, "repository", "findHierarchicalDispatchCondition", "repository is started...");
            Long start = System.currentTimeMillis();

            List<HierarchicalDispatchEntity> entityList = hierarchicalDispatchRepository.findHierarchicalDispatchCondition( inputInfo.getScopeSquadronId() , inputInfo.getIncidentTypeCode(), inputInfo.getIncidentLevelCode(), inputInfo.getDisposalObjectCode() ,
                         false ,inputInfo.getPagination().getPage(), inputInfo.getPagination().getSize()  );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findHierarchicalDispatchCondition", String.format("repository is finished,execute time is :%sms", end - start));

            if (entityList != null && entityList.size() > 0) {
                //查询方案历史调度次数 方便排序
                Map<String, Integer> map = dispatchPlanUsageRecordService.countDispatchPlanUsageRecord();
                // 查询出所有需要用到的字典
                Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
                Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();
                for (HierarchicalDispatchEntity entity : entityList) {
                    HierarchicalDispatchBean bean = HandleDispatchTransformUtil.transform(entity, dicsMap, organizationNameMap);
                    if (map.containsKey(bean.getId())){
                        bean.setUsageTimes(map.get(bean.getId()));
                    }
                    beans.add(bean);
                }
            }

            logService.infoLog(logger, "repository", "findPlanConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total =  hierarchicalDispatchRepository.findHierarchicalDispatchTotal(  inputInfo.getScopeSquadronId() , inputInfo.getIncidentTypeCode(), inputInfo.getIncidentLevelCode(), inputInfo.getDisposalObjectCode() ) ;

            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));
            beans.sort((a,b)->b.getUsageTimes().compareTo(a.getUsageTimes()));
            if (inputInfo.getWhetherPage()){
                int i=(inputInfo.getPagination().getPage()-1)*inputInfo.getPagination().getSize();
                int j=inputInfo.getPagination().getPage()*inputInfo.getPagination().getSize();
                if (j>beans.size()){
                    j=beans.size();
                }
                beans=beans.subList(i,j);
            }

            Pagination pagination = new Pagination();
            pagination.setPage(inputInfo.getPagination().getPage());
            pagination.setSize(inputInfo.getPagination().getSize());
            pagination.setTotal(total);

            res.setPagination(pagination);
            res.setList(beans);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHierarchicalDispatchCondition", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHierarchicalDispatchCondition", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HIERARCHICAL_DISPATCH_FAIL);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see HierarchicalDispatchService#saveHierarchicalDispatch(HierarchicalDispatchSaveInputInfo)
     */
    @Transactional( rollbackFor = Exception.class)
    @Override
    public HierarchicalDispatchBean saveHierarchicalDispatch(HierarchicalDispatchSaveInputInfo inputInfo) {
        if (inputInfo == null) {
            logService.infoLog(logger, "service", "saveHierarchicalDispatch", "HierarchicalDispatchSaveInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try{
            //判断是否单位 等级 类型 对象
            logService.infoLog(logger, "repository", "findPlanConditionTotal", "repository is started...");
            Long countStart = System.currentTimeMillis();

            Integer total = 0 ;
            if( Strings.isBlank( inputInfo.getId() )){
                total =  hierarchicalDispatchRepository.findHierarchicalDispatchTotal(  inputInfo.getOrganizationId() ,
                        inputInfo.getIncidentTypeCode(), inputInfo.getIncidentLevelCode(), inputInfo.getDisposalObjectCode() ) ;
            }else{
                total =  hierarchicalDispatchRepository.whetherHierarchicalDispatchTotal( inputInfo.getId() ,   inputInfo.getOrganizationId() ,
                        inputInfo.getIncidentTypeCode(), inputInfo.getIncidentLevelCode(), inputInfo.getDisposalObjectCode() ) ;
            }


            Long countEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findPlanConditionTotal", String.format("repository is finished,execute time is :%sms", countEnd - countStart));

            if( total >= 1 ){
                logService.infoLog(logger, "service", "saveHierarchicalDispatch", "hierarchicalDispatch is having.");
                throw new AcceptException(AcceptException.AccetpErrors.HIERARCHICAL_DISPATCH_HAVING);
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveHierarchicalDispatch", "execution error", ex);
            throw ex ;
         }
        try {
            logService.infoLog(logger, "service", "saveHierarchicalDispatch", "service is started...");
            Long logStart = System.currentTimeMillis();

            decodeHierarchicalDispatchSaveInputInfo(inputInfo);//URLDecoder inputInfo转码

            HierarchicalDispatchBean res = null ;

            HierarchicalDispatchEntity hierarchicalDispatchEntity = HandleDispatchTransformUtil.transform(inputInfo);
            UserInfo userInfo = userService.getUserInfo() ;
            hierarchicalDispatchEntity.setMakePersonId ( userInfo.getAccount() );
            hierarchicalDispatchEntity.setMakePersonName( userInfo.getPersonName() );
            hierarchicalDispatchEntity.setMakeTime(  servletService.getSystemTime() );

            logService.infoLog(logger, "repository", "save(dbHierarchicalDispatchEntity)", "repository is started...");
            Long start = System.currentTimeMillis();

            accessor.save(hierarchicalDispatchEntity);

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbHierarchicalDispatchEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap();

            res = HandleDispatchTransformUtil.transform(hierarchicalDispatchEntity, dicsMap, organizationNameMap);

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveHierarchicalDispatch", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveHierarchicalDispatch", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_HIERARCHICAL_DISPATCH_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see HierarchicalDispatchService#removeHierarchicalDispatch(String)
     */
    @Transactional( rollbackFor = Exception.class)
    @Override
    public  Boolean removeHierarchicalDispatch( String hierarchicalDispatchId) {
        if ( Strings.isBlank( hierarchicalDispatchId )) {
            logService.infoLog(logger, "service", "removeHierarchicalDispatch", "hierarchicalDispatchId is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "removeHierarchicalDispatch", "service is started...");
            Long logStart = System.currentTimeMillis();

            Boolean res = true  ;


            HierarchicalDispatchEntity hierarchicalDispatchEntity =  accessor.getById( hierarchicalDispatchId , HierarchicalDispatchEntity.class ) ;

            if( null != hierarchicalDispatchEntity ){

                hierarchicalDispatchEntity.setValid( false );

                logService.infoLog(logger, "repository", "remove(dbHierarchicalDispatchEntity)", "repository is started...");
                Long start = System.currentTimeMillis();

                accessor.save(hierarchicalDispatchEntity);

                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "remove(dbHierarchicalDispatchEntity)", String.format("repository is finished,execute time is :%sms", end - start));

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "removeHierarchicalDispatch", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeHierarchicalDispatch", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_HIERARCHICAL_DISPATCH_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see HierarchicalDispatchService#findHierarchicalDispatchVehicle(HierarchicalDispatchQueryInputInfo)
     */
    @Transactional(  rollbackFor =  Exception.class )
    @Override
    public List<HierarchicalDispatchBean>  findHierarchicalDispatchVehicle (HierarchicalDispatchQueryInputInfo queryBean  ){
        if ( queryBean == null) {
            logService.infoLog(logger, "service", "findHierarchicalDispatchVehicle", "HierarchicalDispatchQueryInputInfo is null.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "findHierarchicalDispatchVehicle", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<HierarchicalDispatchBean> res = new ArrayList<>()  ;

            List<HierarchicalDispatchEntity> entityList = hierarchicalDispatchRepository.findHierarchicalDispatchCondition(queryBean.getScopeSquadronId() ,  queryBean.getIncidentTypeCode(), queryBean.getIncidentLevelCode(), queryBean.getDisposalObjectCode() ,
                    false  , -1 , -1   );

            //判断是否为 夜间
            Boolean  whetherYj = buildYjCondition( queryBean.getIncidentTypeCode() ) ;

            //字典数据
            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics) ;
            //机构数据
            Map<String, String> organizationNameMap =  organizationService.findOrganizationNameMap();

            if( entityList != null && entityList.size() >0  ){
                List<String> dispatchStatusList = new ArrayList<>();
                SystemConfigurationBean vehicleDispatchStatusBean = systemConfigurationService.getSystemConfigByConfigType("vehicleDispatchStatus") ;
                if ( vehicleDispatchStatusBean != null &&  Strings.isNotBlank(  vehicleDispatchStatusBean.getConfigValue()   )) {
                    String vehicleDispatchStatus = vehicleDispatchStatusBean.getConfigValue() ;
                    String[] dispatchStatus = vehicleDispatchStatus.split(",");
                    dispatchStatusList = Arrays.asList(dispatchStatus);
                }


                String searchPath = queryBean.getScopeSquadronId(); //机构查询码 0根机构 1非根机构
                //判断是否为 根机构信息 ;
                if (1 == queryBean.getScopeType()) {
                    OrganizationEntity organization = accessor.getById(queryBean.getScopeSquadronId(), OrganizationEntity.class);
                    if (organization != null && organization.isValid()) {
                        searchPath = organization.getSearchPath() + "%";
                    }
                } else {
                    searchPath = null;
                }

                //根据配置信息获得照明车辆类型
                String  lightingVehicleTypeCode = "21010303,21010304" ; // 默认照明车类型
                SystemConfigurationBean lightingVehicle = systemConfigurationService.getSystemConfigByConfigType("lightingVehicleTypeCode");
                if( lightingVehicle != null && Strings.isNotBlank( lightingVehicle.getConfigValue() ) ){
                    lightingVehicleTypeCode = lightingVehicle.getConfigValue() ;
                }
                String []   lightingVehicleTypeCodesz =     lightingVehicleTypeCode.split(",");
                List<String> lightingVehicleTypeCodes =  Arrays.asList( lightingVehicleTypeCodesz ) ;

                for( HierarchicalDispatchEntity hierarchicalDispatchEntity :  entityList ){
                    HierarchicalDispatchBean hierarchicalDispatchBean = HandleDispatchTransformUtil.transform( hierarchicalDispatchEntity , dicsMap , organizationNameMap ) ;
                    List<HierarchicalDispatchVehicleBean> hierarchicalDispatchVehicleBeanList =  hierarchicalDispatchBean.getHierarchicalDispatchVehicleBeanList() ;

                    String  hierarchicalDispatchTips = "" ;  //等级调派推荐提示信息
                    String  tempHierarchicalDispatchTip = "" ; //车辆类型提示信息
                    //主管中队车辆id 集合
                    List<String >  squadronIds = new ArrayList<>( ) ;
                    //调派总数  实际总数
                    Integer total = 0 ;
                    Integer total2 = 0 ;

                    List<String> hierarchicalDispatchVehicleIdList = new ArrayList<>( ) ;

                    //判断 是否添加额外添加照明车辆信息
                    Boolean whetherZMC = true    ;
                    for( HierarchicalDispatchVehicleBean hierarchicalDispatchVehicleBean : hierarchicalDispatchVehicleBeanList){
                        //判断
                        if(   lightingVehicleTypeCodes.contains( hierarchicalDispatchVehicleBean.getVehicleTypeCode()   )
                          ){
                            whetherZMC = false ;
                            break ;
                        }
                    }


                    for( HierarchicalDispatchVehicleBean hierarchicalDispatchVehicleBean : hierarchicalDispatchVehicleBeanList){
                        hierarchicalDispatchVehicleBean.setVehicleNum(0);

                        //添加可调派车辆信息
                        List<String> vehicleIds = new ArrayList<>() ;
                        //优先主管机构获得可调派车辆信息
                        List<String> squadronVehicleList = acceptNativeQueryRepository.findEquipmentVehicle(queryBean.getSquadronOrganizationId(), hierarchicalDispatchVehicleBean.getVehicleTypeCode(),
                                dispatchStatusList,  hierarchicalDispatchVehicleBean.getDispatchNum() ) ;
                        if( squadronVehicleList != null && squadronVehicleList.size() > 0 &&
                                squadronVehicleList.size()  <= hierarchicalDispatchVehicleBean.getDispatchNum()    ){
                            vehicleIds.addAll( squadronVehicleList ) ;
                            squadronIds.addAll( squadronVehicleList ) ; //加入主管中队实际出动车辆信息
                        }
                        //如果优先机构可调派车辆信息 不足从毗邻机构获得
                        //计算相差数量
                        int clcgsl = hierarchicalDispatchVehicleBean.getDispatchNum() - ( squadronVehicleList == null ? 0 : squadronVehicleList.size() );
                        if( clcgsl > 0 )   {
                            List<String> adjacentPriorityVehicleList =  null ;
                            if( Strings.isBlank( searchPath )){
                                adjacentPriorityVehicleList = acceptNativeQueryRepository.findEquipmentVehicleByAdjacentPriority(queryBean.getSquadronOrganizationId(),
                                        hierarchicalDispatchVehicleBean.getVehicleTypeCode(), dispatchStatusList,  clcgsl  ) ;
//                                adjacentPriorityVehicleList = acceptNativeQueryRepository.findEquipmentVehicleByAdjacentPriority(queryBean.getSquadronOrganizationId(),
//                                        hierarchicalDispatchVehicleBean.getVehicleTypeCode(), dispatchStatusList  ) ;
                            }else{
                                adjacentPriorityVehicleList = acceptNativeQueryRepository.findEquipmentVehicleByAdjacentPriority(queryBean.getSquadronOrganizationId(), searchPath  ,
                                        hierarchicalDispatchVehicleBean.getVehicleTypeCode(), dispatchStatusList,  clcgsl  ) ;
//                                adjacentPriorityVehicleList = acceptNativeQueryRepository.findEquipmentVehicleByAdjacentPriority(queryBean.getSquadronOrganizationId(), searchPath  ,
//                                        hierarchicalDispatchVehicleBean.getVehicleTypeCode(), dispatchStatusList  ) ;
                            }

                            if( adjacentPriorityVehicleList != null ){
                                vehicleIds.addAll( adjacentPriorityVehicleList ) ;
                            }
                        }
                        //将车辆转换bean
                        if( vehicleIds != null && vehicleIds.size() > 0  ){
                            hierarchicalDispatchVehicleBean.setVehicleNum( vehicleIds.size() );
                            hierarchicalDispatchVehicleBean.setVehicles( vehicleIds );
                            hierarchicalDispatchVehicleIdList.addAll(vehicleIds  ) ;
                        }
                        if( hierarchicalDispatchVehicleBean.getVehicleNum() <  hierarchicalDispatchVehicleBean.getDispatchNum() ){
                            tempHierarchicalDispatchTip =  tempHierarchicalDispatchTip  +  dicsMap.get( "WLCLLX"  ).get( hierarchicalDispatchVehicleBean.getVehicleTypeCode()  ) +  "差" +
                                    (  hierarchicalDispatchVehicleBean.getDispatchNum() - hierarchicalDispatchVehicleBean.getVehicleNum() )  + "辆; " ;
                        }
                        total = total + hierarchicalDispatchVehicleBean.getDispatchNum() ;
                        total2 = total2 +  hierarchicalDispatchVehicleBean.getVehicleNum() ;

                    }

                    //添加夜间照明车辆
                    if( whetherYj && whetherZMC ){
                        //优先主管中队照明车
                        List<String> squadronVehicleZMCList = acceptNativeQueryRepository.findEquipmentVehicle(queryBean.getSquadronOrganizationId(),
                                lightingVehicleTypeCodes , dispatchStatusList,  1 ) ;
                        //如果 主管中队 无照明车辆信息  从优先级机构获得
                        if( squadronVehicleZMCList != null && squadronVehicleZMCList.size() > 0 ) {
                            squadronIds.addAll( squadronVehicleZMCList ); //加入主管中队实际出动车辆信息
                            hierarchicalDispatchVehicleIdList.addAll( squadronVehicleZMCList ) ;
                        }else{
                            //如果优先机构可调派车辆信息 不足从毗邻机构获得
                            List<String> adjacentPriorityVehicleZMCList =  null ;
                            if( Strings.isBlank( searchPath )){
                                adjacentPriorityVehicleZMCList = acceptNativeQueryRepository.findEquipmentVehicleByAdjacentPriority(queryBean.getSquadronOrganizationId(),
                                        lightingVehicleTypeCodes , dispatchStatusList,  1  ) ;
                            }else{
                                adjacentPriorityVehicleZMCList = acceptNativeQueryRepository.findEquipmentVehicleByAdjacentPriority(queryBean.getSquadronOrganizationId(), searchPath  ,
                                        lightingVehicleTypeCodes , dispatchStatusList,  1  ) ;
                            }
                            //将 照明车辆添加到 调派车辆信息
                            if( adjacentPriorityVehicleZMCList != null && adjacentPriorityVehicleZMCList.size() > 0  ){
                                hierarchicalDispatchVehicleIdList.addAll( adjacentPriorityVehicleZMCList ) ;
                            }
                        }

                    }



                    //如果主管中队无车辆 必填加派主管 其他类型 1辆  如果还是没有提示 主管中队无可用车辆信息
                    if( squadronIds == null || squadronIds.size() == 0 ){
                         List<String> squadronVehicleTempList = acceptNativeQueryRepository.findEquipmentVehicle(queryBean.getSquadronOrganizationId(),
                                dispatchStatusList,  1 ) ;
                        if( squadronVehicleTempList == null || squadronVehicleTempList.size() == 0 ){
                            hierarchicalDispatchTips = hierarchicalDispatchTips + "主管中队无车可派;" ;
                        }else{
                            squadronIds.addAll( squadronVehicleTempList ) ;
                            hierarchicalDispatchVehicleIdList.addAll(squadronVehicleTempList  ) ;
                        }
                    }
                    if( total2 < total ){
                        hierarchicalDispatchTips = hierarchicalDispatchTips  +    "" ;
                        hierarchicalDispatchTips  = hierarchicalDispatchTips + "" +tempHierarchicalDispatchTip ;
                    }
                    hierarchicalDispatchBean.setHierarchicalDispatchTips( hierarchicalDispatchTips );
                    hierarchicalDispatchBean.setHierarchicalDispatchVehicleIdList( hierarchicalDispatchVehicleIdList );
                    res.add( hierarchicalDispatchBean ) ;
                 }
            }

            //保存操作日志记录
            UserInfo userInfo = userService.getUserInfo();
            AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo() ;
            auditLogSaveInputInfo.setOperateTime( System.currentTimeMillis() );
            auditLogSaveInputInfo.setOperateType( String.valueOf( OperationTypeEnum.OPERATIONTYPE_HIERARCHICALDISPATCH.getCode() ) );
            auditLogSaveInputInfo.setOrganizationId( userInfo.getOrgId() );
            auditLogSaveInputInfo.setOrganizationName( userInfo.getOrgName() );
            auditLogSaveInputInfo.setOperateSeatNumber( userInfo.getAgentNum());
            auditLogSaveInputInfo.setOperateSeatName( userInfo.getAgentRoom() );
            auditLogSaveInputInfo.setAcceptancePersonNumber( userInfo.getAccount() );
            auditLogSaveInputInfo.setAcceptancePersonName( userInfo.getPersonName() );
            auditLogSaveInputInfo.setIpAddress( userInfo.getClientIp() );
            String desc =  String.format("incident hierarchical dispatch vehicle content:%s ", JSON.toJSONString( queryBean ) ) ;
            auditLogSaveInputInfo.setDesc( desc  );
            auditLogSaveInputInfo.setRemarks( null );
            auditLogService.saveAuditLog( auditLogSaveInputInfo) ;
            subAuditService.buildSubAuditLog(userInfo.getAccount() , userInfo.getUserName() , userInfo.getOrgId() , userInfo.getOrgName(),
                    "Add", "Ok",  OperationTypeEnum.OPERATIONTYPE_HIERARCHICALDISPATCH.getName() );


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findHierarchicalDispatchVehicle", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findHierarchicalDispatchVehicle", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_HIERARCHICAL_DISPATCH_VEHICLE_FAIL);
        }

    }

    /**等级调派保存inputinfo转码*/
    private void decodeHierarchicalDispatchSaveInputInfo (HierarchicalDispatchSaveInputInfo inputInfo){
        try {
            if (!StringUtils.isBlank(inputInfo.getTitle())){
                inputInfo.setTitle(URLDecoder.decode(inputInfo.getTitle(),"UTF-8"));
            }
            if (!StringUtils.isBlank(inputInfo.getRemarks())){
                inputInfo.setRemarks(URLDecoder.decode(inputInfo.getRemarks(),"UTF-8"));
            }
        }catch (Exception e){
            logService.erorLog(logger,"service","decodeHierarchicalDispatchSaveInputInfo","execution error",e);
            throw new AcceptException(AcceptException.AccetpErrors.DECODE_FAIL);
        }
    }

    /**
     *  判断 当前时间点是否为夜间
     * @return
     */
    private Boolean  buildYjCondition( String incidentTypeCode ) {

        Long currentTime = servletService.getSystemTime() ;

        //获得夜间时间段信息
        Boolean  whetherYj = false ;

        Calendar cal = DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
        Date today = new Date(currentTime);
        cal.setTime(today);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long todayStartTime  = cal.getTimeInMillis();
        Long location = currentTime - todayStartTime ;

        //默认夜间时间段

        Long startYJ =  5 * 60 * 60 * 1000l  ;
        Long endYJ = 20 * 60 * 60 * 1000l ;

        CaseAutoUpdateWarnBean caseAutoUpdateWarnBean = caseAutoUpdateWarnSerivce.findCaseAutoUpdateWarnByCaseType( incidentTypeCode , WarnTypeBakEnum.WARN_TYPE_YJJQ.getCode() );
        if( caseAutoUpdateWarnBean != null ){

            Long  yjkssj =  Long.parseLong( caseAutoUpdateWarnBean.getMinimum()) ;
            Calendar calYjks =  DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date  Yjks  = new Date(   yjkssj );
            cal.setTime(today);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Long yjStartTime  = cal.getTimeInMillis();
            startYJ =  yjkssj  - yjStartTime  ;


            Long  yjjssj =  Long.parseLong( caseAutoUpdateWarnBean.getMinimum()) ;
            Calendar calYjjs =  DateHandleUtils.buildCalendar(  env.getProperty("timeZone") ) ;
            Date  Yjjs  = new Date(  yjjssj );
            calYjjs.setTime(today);
            calYjjs.set(Calendar.HOUR_OF_DAY, 0);
            calYjjs.set(Calendar.MINUTE, 0);
            calYjjs.set(Calendar.SECOND, 0);
            calYjjs.set(Calendar.MILLISECOND, 0);
            Long yjEndTime  = cal.getTimeInMillis();
            endYJ =  yjjssj  - yjEndTime  ;
        }

        if( location <= startYJ || location >= endYJ ){
            whetherYj = true ;
        }

        return  whetherYj ;

    }

}
