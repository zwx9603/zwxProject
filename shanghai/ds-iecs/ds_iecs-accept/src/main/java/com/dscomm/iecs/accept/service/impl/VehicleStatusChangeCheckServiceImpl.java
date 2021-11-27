package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.VehicleStatusChangeCheckEntity;
import com.dscomm.iecs.accept.dal.repository.VehicleStatusChangeCheckRepository;
import com.dscomm.iecs.accept.graphql.inputbean.VehicleStatusChangeCheckSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.VehicleStatusChangeCheckBean;
import com.dscomm.iecs.accept.service.VehicleStatusChangeCheckService;
import com.dscomm.iecs.accept.service.VehicleStatusChangeService;
import com.dscomm.iecs.accept.utils.transform.AuditVehicleTransformUtil;
import com.dscomm.iecs.agent.enums.RoleEnum;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.enums.EnableEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.*;
import com.dscomm.iecs.basedata.service.impl.VehicleServiceImpl;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XXDD;
import com.dscomm.iecs.keydata.service.ServletService;
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
 * 车辆状态审批记录 服务实现
 */
@Component("vehicleStatusChangeCheckServiceImpl")
public class VehicleStatusChangeCheckServiceImpl implements VehicleStatusChangeCheckService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private LogService logService;
    private Environment env;
    private GeneralAccessor accessor;
    private VehicleStatusChangeCheckRepository repository;
    private OrganizationService organizationService;
    private OrganizationOtherService  organizationOtherService ;
    private NotifyActionService notifyActionService;
    private SystemConfigurationService systemConfigurationService;
    private UserService userService ;
    private DictionaryService dictionaryService  ;
    private ServletService servletService ;
    private VehicleService vehicleService ;
    private VehicleStatusChangeService vehicleStatusChangeService ;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired

    public VehicleStatusChangeCheckServiceImpl(LogService logService, Environment env, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                               VehicleStatusChangeCheckRepository repository ,
                                               OrganizationService organizationService, NotifyActionService notifyActionService,
                                                SystemConfigurationService systemConfigurationService,DictionaryService dictionaryService,
                                               UserService userService , ServletService servletService ,
                                               OrganizationOtherService  organizationOtherService  ,  VehicleService vehicleService ,
                                               VehicleStatusChangeService vehicleStatusChangeService ) {
        this.logService = logService;
        this.env = env;
        this.accessor = accessor;
        this.repository = repository;
        this.organizationService = organizationService;
        this.notifyActionService = notifyActionService;
        this.systemConfigurationService = systemConfigurationService;
        this.userService = userService;
        this.dictionaryService = dictionaryService ;
        this.servletService = servletService ;
        this.organizationOtherService = organizationOtherService ;
        this.vehicleService = vehicleService ;
        this.vehicleStatusChangeService = vehicleStatusChangeService ;

        dics = new ArrayList<>(Arrays.asList( "WLCLZT" ));

    }

    /**
     * {@inheritDoc}
     *
     * @see #updateVehicleStatusChangeCheck( VehicleStatusChangeCheckSaveInputInfo , Boolean )
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateVehicleStatusChangeCheck(VehicleStatusChangeCheckSaveInputInfo queryBean  , Boolean whetherCheck ){
        if ( queryBean == null || Strings.isBlank(queryBean.getAppliedOrganizationId() )
                || Strings.isBlank(queryBean.getAppliedVehicleId() )
                || Strings.isBlank( queryBean.getVehicleStatusCode()  )   ) {
            logService.infoLog(logger, "service", "updateVehicleStatusChangeCheck",
                    "applied vehicleId or organizationId or   vehiclestatusCode  is blank " );
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }

        try {
            logService.infoLog(logger, "service", "updateVehicleStatusChangeCheck", "service is started...");
            Long logStart = System.currentTimeMillis();

           Boolean res = false ;

            //根据车辆id 获得车辆信息
            EquipmentVehicleBean vehicleBean =  vehicleService.findVehicle( queryBean.getAppliedVehicleId() ) ;
            if( vehicleBean == null ){
                logService.infoLog(logger, "service", "updateVehicleStatusChangeCheck", "vehicleBean  is blank " );
                throw new BasedataException(BasedataException.BasedataErrors.DATA_FAIL_NULL);
            }
            if( queryBean.getVehicleStatusCode().equals( vehicleBean.getVehicleStatusCode() ) ){
                return  true ;
            }
            if( Strings.isBlank( queryBean.getAppliedOrganizationId() ) ){
                UserInfo userInfo = userService.getUserInfo() ;
                queryBean.setAppliedOrganizationId( userInfo.getOrgId());
            }

            //首先判断是否需要 车辆状态审批环节
            if( whetherCheck && judgeWhetherCheck( queryBean.getAppliedOrganizationId() )  ){
                //需要审批

                //需要审批
                //判断车辆状态是否可以修改  作战中车辆状态不能直接修改
                if( judgeWhetherVehicleChange( vehicleBean.getVehicleStatusCode()    ) ){
                    logService.infoLog(logger, "service", "updateVehicleStatusChangeCheck", "vehicleStatusCode is vehicleOnDutyStatus.");
                    throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_STATUS_DUTY);
                }else {
                    //保存车辆审批记录 并且 发送消息审批机构
                    VehicleStatusChangeCheckBean vehicleStatusChangeCheckBean = saveVehicleStatusChangeCheck(queryBean, vehicleBean.getVehicleStatusCode(), vehicleBean);
                    //发送消息 主要发送审核机构信息
                    String sendOrganizationId = buildCheckOrganizationId(queryBean.getAppliedOrganizationId());
                    //发送消息
                    Set<String> orgs = new HashSet<>();
                    List<String> orgIds = new ArrayList<>();
                    // 是独立接警大队 给独立接警大队发送消息
                    orgIds.add(sendOrganizationId);
                    List<String> orgCodes = organizationService.findOrganizationCodesByIds(orgIds);
                    orgs.addAll(orgIds);
                    orgs.addAll(orgCodes);
                    // 单位 业务角色 为 接警班长 发送消息
                    //接警角色
                    Set<String> roles = new HashSet<>();
                    roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //接警员
                    roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //接警班长
                    notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.CHECK_VEHICLE_STATUS.getCode(), vehicleStatusChangeCheckBean
                            , orgs, roles);

                    res = true;
                }
            }else{
                //不需要审批
                // 修改车辆状态
                vehicleStatusChangeService.updateVehicleStatus( null , null , queryBean.getAppliedVehicleId() ,null,
                        queryBean.getVehicleStatusCode() , queryBean.getChangeSource() );

                res = true ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "updateVehicleExpandInfo", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;

        }  catch (Exception ex) {
            logService.erorLog(logger, "service", "updateVehicleExpandInfo", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_EXPAND_INFO_FAIL);
        }
    }

    /**
     *  保存车辆申请记录 并且发送消息
     */
    private VehicleStatusChangeCheckBean  saveVehicleStatusChangeCheck(  VehicleStatusChangeCheckSaveInputInfo queryBean  ,
                    String oldVehicleStatusCode ,  EquipmentVehicleBean vehicleBean   ){
        //  插入一条待审核记录
        VehicleStatusChangeCheckEntity vehicleStatusChangeCheckEntity = AuditVehicleTransformUtil.transform( oldVehicleStatusCode , queryBean );
        vehicleStatusChangeCheckEntity.setAppliedTime( servletService.getSystemTime() );
        vehicleStatusChangeCheckEntity.setIncidentId( vehicleBean.getIncidentNumber() );
        logService.infoLog(logger, "repository", "save(dbVehicleStatusChangeCheck)", "repository is started...");
        Long start = System.currentTimeMillis();

        accessor.save( vehicleStatusChangeCheckEntity ) ;

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "repository", "save(businessDataIdVehicleStatusChangeCheck)", String.format("repository is finished,execute time is :%sms", end - start));

        Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
        Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
        VehicleStatusChangeCheckBean vehicleStatusChangeCheckBean =
                AuditVehicleTransformUtil.transform( vehicleStatusChangeCheckEntity  , organizationNameMap , dicsMap ,  vehicleBean  );

        return  vehicleStatusChangeCheckBean ;

    }


    //申请车辆状态变更机构 是否需要审核操作
    private Boolean  judgeWhetherCheck( String organizationId ){
        boolean isVerifyVehicle = Boolean.parseBoolean(  env.getProperty("isVerifyVehicle")  );
        if( isVerifyVehicle ){
            OrganizationBean organizationBean  =  organizationService.findOrganizationByOrganizationId ( organizationId ) ;
            if( organizationBean != null ){
                //判断是否为中队 中队需要审核 非中队（ 大队 支队）不需要审核
                if(  Strings.isNotBlank( organizationBean.getOrganizationNatureCode() ) &&
                        organizationBean.getOrganizationNatureCode().startsWith( ORGANIZATION_NATURE_HEAD_XJZD.getCode() )  ){
                    return true  ;
                }else{
                    return false  ;
                }
            }else{
                return  false  ;
            }
        }else{
            return  false ;
        }
    }


    /**
     * 判断 现有车辆状态 是否在处警中
     */
    private Boolean judgeWhetherVehicleChange( String oldVehicleStatusCode   ){
        boolean isVerifyHandleVehicle = Boolean.parseBoolean(  env.getProperty("isVerifyHandleVehicle")  );
        if( isVerifyHandleVehicle ){
            SystemConfigurationBean vehicleOnDutyStatus = systemConfigurationService.getSystemConfigByConfigType("vehicleOnDutyStatus");
            if( vehicleOnDutyStatus != null && Strings.isNotBlank( vehicleOnDutyStatus.getConfigValue() )  ){
                String[] values = vehicleOnDutyStatus.getConfigValue().split(",");
                boolean contains = Arrays.asList(values).contains( oldVehicleStatusCode );
                if (contains ){
                    return  true ;
                }else{
                    return  false  ;
                }
            }else{
                return  false ;
            }
        }else{
            return  false ;
        }



    }



    /**
     *  申请机构为中队时 调用
     *  申请机构为中队时 审核车辆状态时 判断车辆审核机构 （ 支队 or 接警大队 ）id   市辖区中队通知支队  独立接警大队所属中队通知大队
     */
    private String  buildCheckOrganizationId ( String organizationId ){
        //获得申请机构的上级机构信息
        OrganizationBean parentOrganizationBean = organizationService.findOrganizationHigherlevel( organizationId );
        //判断申请机构的上级是否为大队性质 如果为大队性质 判断是否 是否为可接警大队 如果为可接警大队返回车辆所在机构 非可接警大队 返回支队信息
        if (   null != parentOrganizationBean  ) {
            if (  Strings.isNotBlank( parentOrganizationBean.getOrganizationNatureCode() ) &&
                    ORGANIZATION_NATURE_HEAD_XXDD.getCode().equals( parentOrganizationBean.getOrganizationNatureCode().substring(0, 2)  )  ) {
                //获得可接警大队信息
                List<OrganizationBean> receiveOrganization  = organizationOtherService.findReceiveOrganization();
                Boolean count = false ;
                for( OrganizationBean receive  : receiveOrganization ){
                    if( receive.getId().equals( parentOrganizationBean.getId()  ) ){
                        count = true ;
                        break ;
                    }
                }
                if ( count ) {
                    return  parentOrganizationBean.getId()  ;
                }else{
                    return   organizationService.findOrganizationHigherlevel( parentOrganizationBean.getId() ).getId() ;
                }
            }else{
                return   organizationId;
            }
        }else{
            return   organizationId;
        }
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public  VehicleStatusChangeCheckBean checkVehicleStatusChangeCheck(  VehicleStatusChangeCheckSaveInputInfo queryBean ) {
        if ( queryBean == null || Strings.isBlank( queryBean.getId() ) ) {
            logService.infoLog(logger, "service", "checkVehicleStatusChangeCheck", " id  is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "checkVehicleStatusChangeCheck", "service is started...");
            Long logStart = System.currentTimeMillis();


            VehicleStatusChangeCheckEntity vehicleStatusChangeCheckEntity =   accessor.getById( queryBean.getId() ,VehicleStatusChangeCheckEntity.class);
            if ( vehicleStatusChangeCheckEntity == null ){
                logService.infoLog(logger, "service", "checkVehicleStatusChangeCheck", "VehicleStatusChangeCheck is blank " );
                throw new BasedataException(BasedataException.BasedataErrors.DATA_FAIL_NULL);
            }

            //根据审批结构 更改车辆状态 0拒绝 1同意
            if(EnableEnum.ENABLE_TRUE.getCode() == queryBean.getCheckStatus() ){
                vehicleStatusChangeService.updateVehicleStatus( null , null , vehicleStatusChangeCheckEntity.getAppliedVehicleId() , null ,
                        vehicleStatusChangeCheckEntity.getVehicleStatusCode() ,   queryBean.getChangeSource()  );
            }

            //判断审核结果
            UserInfo userInfo = userService.getUserInfo();
            vehicleStatusChangeCheckEntity.setCheckTime( servletService.getSystemTime() );
            vehicleStatusChangeCheckEntity.setCheckStatus( queryBean.getCheckStatus() );
            vehicleStatusChangeCheckEntity.setCheckDesc( queryBean.getCheckDesc() );
            vehicleStatusChangeCheckEntity.setCheckOrganizationId( userInfo.getOrgId() );
            vehicleStatusChangeCheckEntity.setCheckPersonId( userInfo.getAccount() );

            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( vehicleStatusChangeCheckEntity.getAppliedVehicleId()  );
            VehicleStatusChangeCheckBean  res =
                    AuditVehicleTransformUtil.transform( vehicleStatusChangeCheckEntity, organizationNameMap ,dicsMap , vehicleBean   );

            Set<String> orgSet = new HashSet<>();

            //通知审批机构  申请机构 审批结果
            // 审批机构
            Set<String> orgs = new HashSet<>();
            List<String> orgIds = new ArrayList<>();
            // 是独立接警大队 给独立接警大队发送消息
            orgIds.add( res.getCheckOrganizationId()  );
            List<String> orgCodes = organizationService.findOrganizationCodesByIds( orgIds );
            orgs.addAll( orgIds ) ;
            orgs.addAll(orgCodes);
            // 单位 业务角色 为 接警班长 发送消息
            //接警角色
            Set<String> roles = new HashSet<>() ;
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //接警员
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //接警班长
            notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.CHECK_VEHICLE_RESULT.getCode(), res
                    , orgs , roles );

            //  中队机构
            Set<String> orgSet1 = new HashSet<>();
            List<String> orgIds1 = new ArrayList<>();
            orgIds1.add( res.getAppliedOrganizationId() );
            List<String> orgCodes1 = organizationService.findOrganizationCodesByIds(orgIds1);
            orgSet1.addAll( orgIds1 ) ;
            orgSet1.addAll(orgCodes1);

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.CHECK_VEHICLE_RESULT.getCode(), res, orgSet1 ) ;

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "auditVehicleStatusChangeCheck", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "auditVehicleStatusChangeCheck", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_STATUS_FAIL);
        }

    }


    @Transactional(readOnly = true)
    @Override
    public List<VehicleStatusChangeCheckBean> findVehicleStatusChangeCheck( ) {
        try {
            logService.infoLog(logger, "service", "findVehicleStatusChangeCheck", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<VehicleStatusChangeCheckBean> res = new ArrayList<>() ;

            List<VehicleStatusChangeCheckEntity> list = repository.findVehicleStatusChangeCheck();

            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;
            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;

            if( list != null && list.size() > 0 ){
                for( VehicleStatusChangeCheckEntity vehicleStatusChangeCheckEntity :  list ){
                    EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( vehicleStatusChangeCheckEntity.getAppliedVehicleId() ) ;
                    VehicleStatusChangeCheckBean bean =  AuditVehicleTransformUtil.transform(vehicleStatusChangeCheckEntity ,
                            organizationNameMap ,dicsMap , vehicleBean );
                    res.add( bean ) ;
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findVehicleStatusChangeCheck", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findVehicleStatusChangeCheck", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DATA_FAIL);
        }

    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean auditVehicleExpand(EquipmentVehicleExpandInputInfo inputInfo) {
        try {
            logService.infoLog(logger, "service", "auditVehicleExpand", "service is started...");
            Long logStart = System.currentTimeMillis();

            //获取车辆
            EquipmentVehicleBean vehicleBean = vehicleService.findVehicle( inputInfo.getId() ) ;
            //  插入一条待审核记录
            VehicleStatusChangeCheckEntity entity = new VehicleStatusChangeCheckEntity();
            entity.setIncidentId(vehicleBean.getIncidentNumber());
            entity.setOldVehicleStatusCode( vehicleBean.getVehicleStatusCode() );
            entity.setVehicleStatusCode( inputInfo.getVehicleStatusCode() );
            entity.setAppliedOrganizationId(inputInfo.getOrganizationId());
            entity.setAppliedVehicleId(inputInfo.getId() );
            entity.setAppliedTime( servletService.getSystemTime() );
            logService.infoLog(logger, "repository", "save(VehicleStatusChangeCheckEntity)", "repository is started...");
            Long start2 = System.currentTimeMillis();

            accessor.save(entity);

            Long end2 = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(VehicleStatusChangeCheckEntity)", String.format("repository is finished,execute time is :%sms", end2 - start2));

            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            VehicleStatusChangeCheckBean  vehicleStatusChangeCheckBean = AuditVehicleTransformUtil.transform(entity, organizationNameMap ,dicsMap , vehicleBean );

            Set<String> orgs = new HashSet<>();

            List<String> orgIds = new ArrayList<>();

            Map map = new HashMap();
            map.put("modificationVO", inputInfo);
            map.put("auditVO",vehicleStatusChangeCheckBean );
            OrganizationBean finalOrganizationBean = organizationService.findOrganizationHigherlevel(inputInfo.getOrganizationId());
            //接警角色
            Set<String> roles = new HashSet<>() ;
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //接警员
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //接警班长

            String organizationNatureCode = finalOrganizationBean.getOrganizationNatureCode();
            if (null != finalOrganizationBean && !Strings.isBlank(organizationNatureCode)) {
                if ( ORGANIZATION_NATURE_HEAD_XXDD.getCode().equals(organizationNatureCode.substring(0, 2))) {
                    //   根据机构编码查询系统配置独立接警大队
                    // todo 独立接警大队下的中队向上级大队发送车辆状态变更申请
                    SystemConfigurationBean  whetherReceive  = systemConfigurationService.getSystemConfigByConfigType( "whetherReceive" );
                    if ( whetherReceive != null && Strings.isNotBlank( whetherReceive.getConfigValue() ) ) {
                        Boolean count = whetherReceive.getConfigValue().contains( finalOrganizationBean.getOrganizationCode() );
                        if ( count ) {
                            // 是独立接警大队 给独立接警大队发送消息
                            orgIds.add( finalOrganizationBean.getId() );
                            orgs.add( finalOrganizationBean.getOrganizationCode() );
                            // 单位 业务角色 为 接警班长 发送消息
                            notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.AUDIT_VEHICLE_STATUS.getCode(), map, orgs , roles );
                            Long logEnd = System.currentTimeMillis();
                            logService.infoLog(logger, "service", "auditVehicleExpand", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                            return false;
                        }
                    }
                }
            }
            // todo 市辖区内所有中队向支队发送状态变更申请
            // 非独立接警大队 给消防支队发送消息
            // 单位 业务角色 为 高级班长 发送消息
            OrganizationBean parentLevel = organizationService.findOrganizationHigherlevel(finalOrganizationBean.getOrganizationCode());
            orgs.add( parentLevel.getId() ) ;
            orgs.add(parentLevel.getOrganizationCode());
            notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.AUDIT_VEHICLE_STATUS.getCode(), map, orgs , roles );
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "auditVehicleExpand", String.format("service is finished,execute time is :%sms", logEnd - logStart));
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "auditVehicleExpand", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_EXPAND_INFO_FAIL);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean auditVehicleStatusChangeCheck(EquipmentVehicleExpandInputInfo inputInfo) {
        if (Objects.isNull(inputInfo) || Strings.isBlank(inputInfo.getAuditId())) {
            logService.infoLog(logger, "service", "auditVehicleStatusChangeCheck", "incidentId or VehicleStatusChangeCheckInputInfo is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "auditVehicleStatusChangeCheck", "service is started...");
            Long logStart = System.currentTimeMillis();
            VehicleStatusChangeCheckEntity entity =   accessor.getById(inputInfo.getAuditId(),VehicleStatusChangeCheckEntity.class);
            if ( entity == null ){
                return false;
            }

            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics) ;
            Map<String, String> organizationNameMap = organizationService.findOrganizationNameMap() ;

            EquipmentVehicleBean vehicleBean = vehicleService.findVehicleCache( inputInfo.getId() ) ;

            Set<String> orgSet = new HashSet<>();
            UserInfo userInfo = userService.getUserInfo();
            if (inputInfo.getWhetherConsent() == 0){ //
                // todo 不同意修改 拒绝原因
                entity.setCheckStatus(inputInfo.getWhetherConsent());
                entity.setCheckTime( servletService.getSystemTime() );
                entity.setCheckDesc(inputInfo.getRefuseReason());
                entity.setCheckPersonId(userInfo.getAccount());
                entity.setCheckOrganizationId(userInfo.getOrgId());
                repository.save(entity);

                VehicleStatusChangeCheckBean  vehicleStatusChangeCheckBean = AuditVehicleTransformUtil.transform(entity, organizationNameMap ,dicsMap , vehicleBean   );

                //通知审批机构  申请机构 审批结果
                // 审批机构
                Set<String> orgs = new HashSet<>();
                List<String> orgIds = new ArrayList<>();
                // 是独立接警大队 给独立接警大队发送消息
                orgIds.add( vehicleStatusChangeCheckBean.getCheckOrganizationId()  );
                List<String> orgCodes = organizationService.findOrganizationCodesByIds( orgIds );
                orgs.addAll( orgIds ) ;
                orgs.addAll(orgCodes);
                // 单位 业务角色 为 接警班长 发送消息
                //接警角色
                Set<String> roles = new HashSet<>() ;
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //接警员
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //接警班长

                notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.AUDIT_VEHICLE_RESULT.getCode(), vehicleStatusChangeCheckBean
                        , orgs , roles );

                //  中队机构
                Set<String> orgSet1 = new HashSet<>();
                List<String> orgIds1 = new ArrayList<>();
                orgIds1.add( vehicleStatusChangeCheckBean.getAppliedOrganizationId() );
                List<String> orgCodes1 = organizationService.findOrganizationCodesByIds(orgIds1);
                orgSet1.addAll( orgIds1 ) ;
                orgSet1.addAll(orgCodes1);

                notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.AUDIT_VEHICLE_RESULT.getCode(), vehicleStatusChangeCheckBean, orgSet1 ) ;


                Long logEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "auditVehicleStatusChangeCheck", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                return false;
            }

            // todo 修改审核记录
            entity.setCheckStatus(inputInfo.getWhetherConsent());
            entity.setCheckTime( servletService.getSystemTime() );
            entity.setCheckDesc(inputInfo.getRefuseReason());
            entity.setCheckPersonId(userInfo.getAccount());
            entity.setCheckOrganizationId(userInfo.getOrgId());

            repository.save(entity);

            VehicleStatusChangeCheckBean  vehicleStatusChangeCheckBean = AuditVehicleTransformUtil.transform(entity, organizationNameMap ,dicsMap , vehicleBean  );

            //通知审批机构  申请机构 审批结果
            // 审批机构
            Set<String> orgs = new HashSet<>();
            List<String> orgIds = new ArrayList<>();
            // 是独立接警大队 给独立接警大队发送消息
            orgIds.add( vehicleStatusChangeCheckBean.getCheckOrganizationId()  );
            List<String> orgCodes = organizationService.findOrganizationCodesByIds( orgIds );
            orgs.addAll( orgIds ) ;
            orgs.addAll(orgCodes);
            // 单位 业务角色 为 接警班长 发送消息
            //接警角色
            Set<String> roles = new HashSet<>() ;
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //接警员
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //接警班长
            notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.AUDIT_VEHICLE_RESULT.getCode(), vehicleStatusChangeCheckBean
                    , orgs , roles );

            //  中队机构
            Set<String> orgSet1 = new HashSet<>();
            List<String> orgIds1 = new ArrayList<>();
            orgIds1.add( vehicleStatusChangeCheckBean.getAppliedOrganizationId() );
            List<String> orgCodes1 = organizationService.findOrganizationCodesByIds(orgIds1);
            orgSet1.addAll( orgIds1 ) ;
            orgSet1.addAll(orgCodes1);

            notifyActionService.pushMessageToDefaultSystemBusinessOrg(WebsocketCodeEnum.AUDIT_VEHICLE_RESULT.getCode(), vehicleStatusChangeCheckBean, orgSet1 ) ;


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "auditVehicleStatusChangeCheck", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "auditVehicleStatusChangeCheck", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_STATUS_FAIL);
        }
        return true;
    }


}
