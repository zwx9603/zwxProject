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
 * ???????????????????????? ????????????
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
     * ?????????????????????
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

            //????????????id ??????????????????
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

            //???????????????????????? ????????????????????????
            if( whetherCheck && judgeWhetherCheck( queryBean.getAppliedOrganizationId() )  ){
                //????????????

                //????????????
                //????????????????????????????????????  ???????????????????????????????????????
                if( judgeWhetherVehicleChange( vehicleBean.getVehicleStatusCode()    ) ){
                    logService.infoLog(logger, "service", "updateVehicleStatusChangeCheck", "vehicleStatusCode is vehicleOnDutyStatus.");
                    throw new BasedataException(BasedataException.BasedataErrors.UPDATE_VEHICLE_STATUS_DUTY);
                }else {
                    //???????????????????????? ?????? ????????????????????????
                    VehicleStatusChangeCheckBean vehicleStatusChangeCheckBean = saveVehicleStatusChangeCheck(queryBean, vehicleBean.getVehicleStatusCode(), vehicleBean);
                    //???????????? ??????????????????????????????
                    String sendOrganizationId = buildCheckOrganizationId(queryBean.getAppliedOrganizationId());
                    //????????????
                    Set<String> orgs = new HashSet<>();
                    List<String> orgIds = new ArrayList<>();
                    // ????????????????????? ?????????????????????????????????
                    orgIds.add(sendOrganizationId);
                    List<String> orgCodes = organizationService.findOrganizationCodesByIds(orgIds);
                    orgs.addAll(orgIds);
                    orgs.addAll(orgCodes);
                    // ?????? ???????????? ??? ???????????? ????????????
                    //????????????
                    Set<String> roles = new HashSet<>();
                    roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //?????????
                    roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //????????????
                    notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.CHECK_VEHICLE_STATUS.getCode(), vehicleStatusChangeCheckBean
                            , orgs, roles);

                    res = true;
                }
            }else{
                //???????????????
                // ??????????????????
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
     *  ???????????????????????? ??????????????????
     */
    private VehicleStatusChangeCheckBean  saveVehicleStatusChangeCheck(  VehicleStatusChangeCheckSaveInputInfo queryBean  ,
                    String oldVehicleStatusCode ,  EquipmentVehicleBean vehicleBean   ){
        //  ???????????????????????????
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


    //?????????????????????????????? ????????????????????????
    private Boolean  judgeWhetherCheck( String organizationId ){
        boolean isVerifyVehicle = Boolean.parseBoolean(  env.getProperty("isVerifyVehicle")  );
        if( isVerifyVehicle ){
            OrganizationBean organizationBean  =  organizationService.findOrganizationByOrganizationId ( organizationId ) ;
            if( organizationBean != null ){
                //????????????????????? ?????????????????? ???????????? ?????? ????????????????????????
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
     * ?????? ?????????????????? ??????????????????
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
     *  ???????????????????????? ??????
     *  ???????????????????????? ????????????????????? ???????????????????????? ??? ?????? or ???????????? ???id   ???????????????????????????  ??????????????????????????????????????????
     */
    private String  buildCheckOrganizationId ( String organizationId ){
        //???????????????????????????????????????
        OrganizationBean parentOrganizationBean = organizationService.findOrganizationHigherlevel( organizationId );
        //???????????????????????????????????????????????? ????????????????????? ???????????? ???????????????????????? ???????????????????????????????????????????????? ?????????????????? ??????????????????
        if (   null != parentOrganizationBean  ) {
            if (  Strings.isNotBlank( parentOrganizationBean.getOrganizationNatureCode() ) &&
                    ORGANIZATION_NATURE_HEAD_XXDD.getCode().equals( parentOrganizationBean.getOrganizationNatureCode().substring(0, 2)  )  ) {
                //???????????????????????????
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

            //?????????????????? ?????????????????? 0?????? 1??????
            if(EnableEnum.ENABLE_TRUE.getCode() == queryBean.getCheckStatus() ){
                vehicleStatusChangeService.updateVehicleStatus( null , null , vehicleStatusChangeCheckEntity.getAppliedVehicleId() , null ,
                        vehicleStatusChangeCheckEntity.getVehicleStatusCode() ,   queryBean.getChangeSource()  );
            }

            //??????????????????
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

            //??????????????????  ???????????? ????????????
            // ????????????
            Set<String> orgs = new HashSet<>();
            List<String> orgIds = new ArrayList<>();
            // ????????????????????? ?????????????????????????????????
            orgIds.add( res.getCheckOrganizationId()  );
            List<String> orgCodes = organizationService.findOrganizationCodesByIds( orgIds );
            orgs.addAll( orgIds ) ;
            orgs.addAll(orgCodes);
            // ?????? ???????????? ??? ???????????? ????????????
            //????????????
            Set<String> roles = new HashSet<>() ;
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //?????????
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //????????????
            notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.CHECK_VEHICLE_RESULT.getCode(), res
                    , orgs , roles );

            //  ????????????
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

            //????????????
            EquipmentVehicleBean vehicleBean = vehicleService.findVehicle( inputInfo.getId() ) ;
            //  ???????????????????????????
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
            //????????????
            Set<String> roles = new HashSet<>() ;
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //?????????
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //????????????

            String organizationNatureCode = finalOrganizationBean.getOrganizationNatureCode();
            if (null != finalOrganizationBean && !Strings.isBlank(organizationNatureCode)) {
                if ( ORGANIZATION_NATURE_HEAD_XXDD.getCode().equals(organizationNatureCode.substring(0, 2))) {
                    //   ??????????????????????????????????????????????????????
                    // todo ???????????????????????????????????????????????????????????????????????????
                    SystemConfigurationBean  whetherReceive  = systemConfigurationService.getSystemConfigByConfigType( "whetherReceive" );
                    if ( whetherReceive != null && Strings.isNotBlank( whetherReceive.getConfigValue() ) ) {
                        Boolean count = whetherReceive.getConfigValue().contains( finalOrganizationBean.getOrganizationCode() );
                        if ( count ) {
                            // ????????????????????? ?????????????????????????????????
                            orgIds.add( finalOrganizationBean.getId() );
                            orgs.add( finalOrganizationBean.getOrganizationCode() );
                            // ?????? ???????????? ??? ???????????? ????????????
                            notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.AUDIT_VEHICLE_STATUS.getCode(), map, orgs , roles );
                            Long logEnd = System.currentTimeMillis();
                            logService.infoLog(logger, "service", "auditVehicleExpand", String.format("service is finished,execute time is :%sms", logEnd - logStart));
                            return false;
                        }
                    }
                }
            }
            // todo ?????????????????????????????????????????????????????????
            // ????????????????????? ???????????????????????????
            // ?????? ???????????? ??? ???????????? ????????????
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
                // todo ??????????????? ????????????
                entity.setCheckStatus(inputInfo.getWhetherConsent());
                entity.setCheckTime( servletService.getSystemTime() );
                entity.setCheckDesc(inputInfo.getRefuseReason());
                entity.setCheckPersonId(userInfo.getAccount());
                entity.setCheckOrganizationId(userInfo.getOrgId());
                repository.save(entity);

                VehicleStatusChangeCheckBean  vehicleStatusChangeCheckBean = AuditVehicleTransformUtil.transform(entity, organizationNameMap ,dicsMap , vehicleBean   );

                //??????????????????  ???????????? ????????????
                // ????????????
                Set<String> orgs = new HashSet<>();
                List<String> orgIds = new ArrayList<>();
                // ????????????????????? ?????????????????????????????????
                orgIds.add( vehicleStatusChangeCheckBean.getCheckOrganizationId()  );
                List<String> orgCodes = organizationService.findOrganizationCodesByIds( orgIds );
                orgs.addAll( orgIds ) ;
                orgs.addAll(orgCodes);
                // ?????? ???????????? ??? ???????????? ????????????
                //????????????
                Set<String> roles = new HashSet<>() ;
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //?????????
                roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //????????????

                notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.AUDIT_VEHICLE_RESULT.getCode(), vehicleStatusChangeCheckBean
                        , orgs , roles );

                //  ????????????
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

            // todo ??????????????????
            entity.setCheckStatus(inputInfo.getWhetherConsent());
            entity.setCheckTime( servletService.getSystemTime() );
            entity.setCheckDesc(inputInfo.getRefuseReason());
            entity.setCheckPersonId(userInfo.getAccount());
            entity.setCheckOrganizationId(userInfo.getOrgId());

            repository.save(entity);

            VehicleStatusChangeCheckBean  vehicleStatusChangeCheckBean = AuditVehicleTransformUtil.transform(entity, organizationNameMap ,dicsMap , vehicleBean  );

            //??????????????????  ???????????? ????????????
            // ????????????
            Set<String> orgs = new HashSet<>();
            List<String> orgIds = new ArrayList<>();
            // ????????????????????? ?????????????????????????????????
            orgIds.add( vehicleStatusChangeCheckBean.getCheckOrganizationId()  );
            List<String> orgCodes = organizationService.findOrganizationCodesByIds( orgIds );
            orgs.addAll( orgIds ) ;
            orgs.addAll(orgCodes);
            // ?????? ???????????? ??? ???????????? ????????????
            //????????????
            Set<String> roles = new HashSet<>() ;
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING.getCode() ) ) ; //?????????
            roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //????????????
            notifyActionService.pushMessageToDefaultSystemBusinessOrgRole(WebsocketCodeEnum.AUDIT_VEHICLE_RESULT.getCode(), vehicleStatusChangeCheckBean
                    , orgs , roles );

            //  ????????????
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
