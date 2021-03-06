package com.dscomm.iecs.agent.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.agent.enums.AgentOperateStateEnum;
import com.dscomm.iecs.agent.enums.AgentPhoneStateEnum;
import com.dscomm.iecs.agent.enums.AgentStateEnum;
import com.dscomm.iecs.agent.enums.RoleEnum;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.graphql.typebean.AgentPersonBean;
import com.dscomm.iecs.agent.service.*;
import com.dscomm.iecs.agent.service.bean.ImeiPortalBean;
import com.dscomm.iecs.agent.service.bean.OrganizationPortalBean;
import com.dscomm.iecs.agent.service.bean.RolePortalBean;
import com.dscomm.iecs.agent.service.bean.UserPortalBean;
import com.dscomm.iecs.base.enums.BasicEnumNumberBean;
import com.dscomm.iecs.base.enums.BasicEnumNumberUtils;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.basedata.dal.po.AgentAccountEntity;
import com.dscomm.iecs.basedata.dal.repository.UserRepository;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.SystemConfigurationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.AccessBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.RoleBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.TokenBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.ext.organization.naturehead.ORGANIZATION_NATURE_HEAD_XJZD;
import com.dscomm.iecs.keydata.enums.OperationTypeEnum;
import com.dscomm.iecs.keydata.graphql.inputbean.AuditLogSaveInputInfo;
import com.dscomm.iecs.keydata.service.AuditLogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.spring.session.SessionDataStore;
import org.mx.spring.utils.I18nMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import static com.dscomm.iecs.agent.enums.RoleEnum.*;
import static com.dscomm.iecs.base.websocket.WebsocketCodeEnum.USER_INFO_LOGIN;
import static com.dscomm.iecs.base.websocket.WebsocketCodeEnum.USER_INFO_LOGOUT;

/**
 * ??????:????????????
 *
 * @author YangFuxi
 * Date Time 2019/8/30 13:58
 */
@Component
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private SessionDataStore sessionDataStore;
    private LogService logService;
    private UserCacheService userCacheService;
    private UserRepository userRepository;
    private AgentService agentService;
    private AgentCacheService agentCacheService;
    @Value("${cacheKeyPrefix:cad}")
    private String cacheKeyPrefix;
    @Value("${authenticate.offsettime:0}")
    private String accessOffset;
    private AuthenticateService authenticateService;
    private SystemConfigurationService systemConfigurationService;
    private AuditLogService auditLogService;
    private SubAuditService subAuditService;
    private ServletService servletService;
    private Environment env;
    private UserStoreService userStoreService ;
    private OrganizationService organizationService ;
    private AgentLoginAccountLoginService agentLoginAccountLoginService ;
    private VehicleService vehicleService ;
    private DataFilterService dataFilterService;
    private NotifyActionService notifyActionService ;
    private GeneralAccessor accessor;

    @Autowired
    public UserServiceImpl(ServletService servletService, SessionDataStore sessionDataStore,
                           LogService logService, UserCacheService userCacheService, UserRepository userRepository,
                           AgentService agentService , AuthenticateService authenticateService,
                           SystemConfigurationService systemConfigurationService,
                           AuditLogService auditLogService, SubAuditService subAuditService ,
                           Environment env  , UserStoreService userStoreService , OrganizationService organizationService ,
                           AgentCacheService agentCacheService  , AgentLoginAccountLoginService agentLoginAccountLoginService ,
                           @Qualifier("generalAccessor") GeneralAccessor accessor,
                           VehicleService vehicleService  , DataFilterService dataFilterService , NotifyActionService notifyActionService   ) {
        this.sessionDataStore = sessionDataStore;
        this.logService = logService;
        this.userCacheService = userCacheService;
        this.userRepository = userRepository;
        this.servletService = servletService;
        this.authenticateService = authenticateService;
        this.systemConfigurationService = systemConfigurationService;
        this.auditLogService = auditLogService;
        this.agentService = agentService;
        this.subAuditService = subAuditService;
        this.env = env ;
        this.userStoreService = userStoreService ;
        this.organizationService = organizationService ;
        this.agentCacheService = agentCacheService ;
        this.agentLoginAccountLoginService = agentLoginAccountLoginService ;
        this.vehicleService = vehicleService ;
        this.dataFilterService = dataFilterService ;
        this.notifyActionService = notifyActionService ;
        this.accessor = accessor;
    }





    /**
     * {@inheritDoc}
     *
     * @see #getUserInfo()
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public UserInfo getUserInfo() {
        try {
            UserInfo userInfo = null;
            Map<String, UserInfo> allOnlineUsers = userCacheService.findAllOnlineUserInfoCache(cacheKeyPrefix);
            if (allOnlineUsers != null && allOnlineUsers.size() > 0) {
                String userCode =  sessionDataStore.getCurrentUserCode() ;
                userInfo = allOnlineUsers.get(userCode);
            }
//            userInfo.setUserName(i18nService.getMessage(String.format("t_tyqx_yh.mc.%s", userInfo.getUserId())));//????????????
//            userInfo.setUserName(i18nService.getMessage(String.format("t_tyqx_ry.xm.%s", userInfo.getUserId())));//????????????
//            userInfo.setOrgName(i18nService.getMessage(String.format("t_tyqx_zzjg.mc.%s", userInfo.getOrgId())));//????????????
//            userInfo.setOrgName(i18nService.getMessage(String.format("t_tyqx_zzjg.qmc.%s", userInfo.getOrgId())));//????????????
//            userInfo.setUserRole(i18nService.getMessage(String.format("t_tyqx_xtjs.mc.%s", userInfo.getOrgId())));//????????????
            if (userInfo == null) {
                userInfo = new UserInfo();
            }
            return userInfo;
        }
//        catch (UserInterfaceException ex) {
//            logService.erorLog(logger, "service", "getUserInfo", "get userInfo fail", ex);
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "getUserInfo", "get userInfo fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #getUserInfo()
     */
    @Override
    public String  getAccount() {
        try {
           return  sessionDataStore.getCurrentUserCode() ;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "getUserInfo", "get userInfo fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #login(String, String , String ,boolean)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public UserInfo login(String token, String ip , String sessionAccount,boolean loginFlag) {
        try {
            logService.infoLog(logger, "service", "login", "start login");
            long start = System.currentTimeMillis();
            Long systemTime = servletService.getSystemTime();
            //token??????
            TokenBean bo = authenticateService.transformEncryptedToken( token, ip , sessionAccount);
            // ??????????????????icp?????? ICP?????? 0?????????( ????????? ) ??????????????????????????? 1?????????( ????????? )???????????? 2 ????????????  3 ?????????imei   9 ???????????????
            String icpType =   buildIcpType( bo.getIcpType() , bo.getUserAccount() ) ;

            logService.infoLog(logger, "service", "buildIcpType", ip + " || " + bo.getUserAccount() + " buildIcpType  from " + bo.getIcpType()
                    + " icptype to "  + icpType );


            sessionDataStore.setCurrentUserCode(bo.getUserAccount());
            logService.infoLog(logger, "service", "login", String.format("login....,totalTime-1 :%sms", System.currentTimeMillis() - start));

            //???????????????????????????
            String rootOrganizationId = organizationService.getRootOrgId() ;

            if( "0".equals( icpType ) ){

                //??????????????????
                UserInfo userInfo = findUserInfoBySystemName(bo.getUserAccount(), bo.getCurrentIP());
                userInfo.setIcpType( icpType );
                //????????????????????????????????????  0????????? 1????????????
                if( Strings.isNotBlank( userInfo.getOrgId() ) && userInfo.getOrgId().equals( rootOrganizationId ) ){
                    userInfo.setScopeType(0);
                }else{
                    userInfo.setScopeType(1);
                }
                userInfo.setNeedAuthenticate(true);
                sessionDataStore.set("userInfo", userInfo);
                AccessBean accessBean = new AccessBean();
                accessBean.setClientIp(userInfo.getClientIp());
                accessBean.setLastAccessTime(systemTime);
                String systemName = sessionDataStore.getCurrentUserCode();
                accessBean.setSystemName(systemName);
                accessBean.setAgentNum(userInfo.getAgentNum());
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime0 :%sms", System.currentTimeMillis() - start));

                //??????????????????????????????????????????????????????????????????????????????
                List<AgentBean> allAgent = agentService.findAllAgent();
                if (allAgent != null && allAgent.size() > 0) {
                    for (AgentBean agentBO : allAgent) {
//                    if (agentBO.getAgentState() != null && (AgentStateEnum.AGENT_STATE_OFFLINE.getCode() != agentBO.getAgentState().getCode())
//                            && agentBO.getPersonBO() != null && systemName.equals(agentBO.getPersonBO().getAccount())) {
//                        //??????????????????
//                        Map<String, AccessBO> allAccess = userCacheService.findAllAccess(cacheKeyPrefix);
//                        if (allAccess != null) {
//                            AccessBO access = allAccess.get(agentBO.getIp());
//                            if (access != null) {
//                                userCacheService.modifyAccessCache(cacheKeyPrefix, "remove", access, true);
//                            }
//                        }
//                        //??????????????????????????????
//                        agentService.agentLogOut(agentBO.getAgentNum(), OperationTypeEnum.OPERATIONTYPE_FORCEDEXIT);
//
//                        break;
//                    }

                        if ( agentBO.getAgentState() != null && ( AgentStateEnum.AGENT_STATE_OFFLINE.getCode() != agentBO.getAgentState().getCode() )
                                && agentBO.getPersonBean() != null   ) {
                            String loginNum = userInfo.getAgentNum() ; //????????????
                            String agentNum = agentBO.getAgentNumber() ; // ????????????
                            if( loginFlag&&agentNum.equals( loginNum )
                                   // && !systemName.equals( agentBO.getPersonBean().getAccount())
                            ){
                                logService.writeLog(logger, "service", "login",  "login user message:" + JSON.toJSONString( agentBO )  );
//                                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_FAIL_AGENT_LOGIN_);
                                String errorMessage =  agentBO.getPersonBean().getAccount()  + "???????????????" +  loginNum + "?????????"   ;
                                throw    new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_FAIL_AGENT_LOGIN_.getErrorCode() ,
                                        errorMessage  )  ;
                            }else{
                               if(loginFlag&&systemName.equals( agentBO.getPersonBean().getAccount() )
                                      // &&  !agentNum.equals( loginNum )
                               ){
                                   logService.writeLog(logger, "service", "login",  "login user message:" + JSON.toJSONString( agentBO )  );
//                                   throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_FAIL_ALREADY_LOGIN);
                                   String errorMessage =  userInfo.getAccount() + "???????????????" +  loginNum + "?????????";
                                   throw    new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_FAIL_ALREADY_LOGIN.getErrorCode() ,
                                           errorMessage  )  ;
                               }
                            }
                        }

                    }
                }
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime1 :%sms", System.currentTimeMillis() - start));
                //????????????????????????
                userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBean, true);
                userInfo.setLatesttime(systemTime);
                //????????????????????????
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime2 :%sms", System.currentTimeMillis() - start));
                //??????????????????
                AgentBean agentBean = prepareAgentInfo(  userInfo, bo);
                agentBean.setLoginTime( accessBean.getLastAccessTime() );
                agentBean.setAccessBean( accessBean );
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime3 :%sms", System.currentTimeMillis() - start));
                agentService.agentLogin(agentBean);
                agentLoginAccountLoginService.saveAgentAccount( userInfo.getClientIp() , userInfo.getClientIp() , userInfo.getAgentNum() , userInfo.getAccount() );
                long end = System.currentTimeMillis();
                logService.infoLog(logger, "service", "login", String.format("end login,totalTime :%sms", end - start));

                return userInfo;
            }else if( "1".equals( icpType ) ) {

                //????????? ????????????

                //?????? ???????????????????????? ?????? ip???????????????
                UserInfo userInfo = userCacheService.findAllOnlineUserInfoCache(cacheKeyPrefix).get(  bo.getUserAccount( )  );
                if (loginFlag&&userInfo != null
                       // && !ip.equals( userInfo.getClientIp() )
                ) {
                    logService.writeLog(logger, "service", "login",  "login user message:" + JSON.toJSONString( userInfo )  );
                    String errorMessage =  userInfo.getAccount() +  "???????????????" +  userInfo.getClientIp() + "";
                    throw    new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_FAIL_ALREADY_LOGIN.getErrorCode() ,
                            errorMessage  )  ;
                }else{
//                UserInfo  userInfo = loginByAccount( bo.getUserAccount( ) )  ;
                    userInfo = findUserInfoBySystemName( bo.getUserAccount( ) )  ;
                    userInfo.setClientIp( bo.getCurrentIP()  );
                    userInfo.setIcpType( icpType );
                    //????????????????????????????????????  0????????? 1????????????
                    if( Strings.isNotBlank( userInfo.getOrgId() ) && userInfo.getOrgId().equals( rootOrganizationId ) ){
                        userInfo.setScopeType(0);
                    }else{
                        userInfo.setScopeType(1);
                    }
                    sessionDataStore.set("userInfo", userInfo);

                    //????????????????????????
                    AccessBean accessBean = new AccessBean();
                    accessBean.setClientIp(userInfo.getClientIp());
                    accessBean.setLastAccessTime(systemTime);
                    String systemName = sessionDataStore.getCurrentUserCode();
                    accessBean.setSystemName(systemName);
                    accessBean.setAgentNum(userInfo.getAgentNum());
                    userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBean, true);
                    logService.infoLog(logger, "service", "login", String.format("login....,totalTime1 :%sms", System.currentTimeMillis() - start));
                    //????????????????????????
                    userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);
                    //todo ??????????????????????????? ????????????
                    userInfo.setOnlineSign( 1 ); // ???????????? 0 ?????? 1 ??????
                    userInfoSendWebSocketToSuperior( USER_INFO_LOGIN.getCode() ,  userInfo , userInfo.getOrgId() ) ;

                    agentLoginAccountLoginService.saveAgentAccount( userInfo.getClientIp() , userInfo.getClientIp() , userInfo.getAgentNum() , userInfo.getAccount() );
                    logService.infoLog(logger, "service", "login", String.format("login....,totalTime2 :%sms", System.currentTimeMillis() - start));

                    return userInfo;
                }

            }else if( "2".equals( icpType ) ) {

                //???????????? ??????????????????
//                UserInfo  userInfo = loginByAccount( bo.getUserAccount( ) )  ;
                UserInfo  userInfo = findUserInfoBySystemName( bo.getUserAccount( ) )  ;
                userInfo.setClientIp( bo.getCurrentIP()  );
                userInfo.setIcpType( icpType );
                //????????????????????????????????????  0????????? 1????????????
                if( Strings.isNotBlank( userInfo.getOrgId() ) && userInfo.getOrgId().equals( rootOrganizationId ) ){
                    userInfo.setScopeType(0);
                }else{
                    userInfo.setScopeType(1);
                }
//                //?????????  ?????????
//                userInfo.setAgentId( "991" );
//                userInfo.setAgentNum( "991" );

                sessionDataStore.set("userInfo", userInfo);

                //????????????????????????
                AccessBean accessBean = new AccessBean();
                accessBean.setClientIp(userInfo.getClientIp());
                accessBean.setLastAccessTime(systemTime);
                String systemName = sessionDataStore.getCurrentUserCode();
                accessBean.setSystemName(systemName);
                accessBean.setAgentNum(userInfo.getAgentNum());
                userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBean, true);
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime1 :%sms", System.currentTimeMillis() - start));
                //????????????????????????
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime2 :%sms", System.currentTimeMillis() - start));

                return userInfo;

            }else if( "3".equals( icpType ) ){

                // ????????????imei ??????????????????
//                UserInfo  userInfo = loginByImei(  bo.getUserAccount( ) )  ;
//                userInfo.setClientIp( bo.getCurrentIP()  );
                UserInfo  userInfo = loginByVehicleId(  bo.getUserAccount( ) )  ;
                userInfo.setClientIp( bo.getCurrentIP()  );
                userInfo.setIcpType( icpType );


                //????????????????????????????????????  0????????? 1????????????
                if( Strings.isNotBlank( userInfo.getOrgId() ) &&   userInfo.getOrgId().equals( rootOrganizationId ) ){
                    userInfo.setScopeType(0);
                }else{
                    userInfo.setScopeType(1);
                }
//                //?????????  ?????????
//                userInfo.setAgentId( "993" );
//                userInfo.setAgentNum( "993" );

                sessionDataStore.set("userInfo", userInfo);

                //????????????????????????
                AccessBean accessBean = new AccessBean();
                accessBean.setClientIp(userInfo.getClientIp());
                accessBean.setLastAccessTime(systemTime);
                String systemName = sessionDataStore.getCurrentUserCode();
                accessBean.setSystemName(systemName);
                accessBean.setAgentNum(userInfo.getAgentNum());
                userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBean, true);
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime1 :%sms", System.currentTimeMillis() - start));
                //????????????????????????
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);

                logService.infoLog(logger, "service", "login", String.format("login....,totalTime2 :%sms", System.currentTimeMillis() - start));

                return userInfo;
            }else if( "9".equals( icpType ) ){

                //9 ?????????
                UserInfo  userInfo = new UserInfo() ;
                userInfo.setAccount(  bo.getUserAccount( )  );
                userInfo.setClientIp( bo.getCurrentIP()  );
                userInfo.setOrgId(  bo.getUserAccount( )  );
                userInfo.setScopeType(1);
                userInfo.setIcpType( icpType );
//                //?????????  ?????????
//                userInfo.setAgentId( "999" );
//                userInfo.setAgentNum( "999" );

                sessionDataStore.set("userInfo", userInfo);

                //????????????????????????
                AccessBean accessBean = new AccessBean();
                accessBean.setClientIp(userInfo.getClientIp());
                accessBean.setLastAccessTime(systemTime);
                String systemName = sessionDataStore.getCurrentUserCode();
                accessBean.setSystemName(systemName);
                accessBean.setAgentNum(userInfo.getAgentNum());
                userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBean, true);
                logService.infoLog(logger, "service", "login", String.format("login....,totalTime1 :%sms", System.currentTimeMillis() - start));
                //????????????????????????
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);

                logService.infoLog(logger, "service", "login", String.format("login....,totalTime2 :%sms", System.currentTimeMillis() - start));

                return userInfo;
            }
            return  null ;

        } catch ( UserInterfaceAgentException ex) {
                logService.erorLog(logger, "service", "login", String.format("login fail,token is:%s", token), ex);
                throw ex ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "login", String.format("login fail,token is:%s", token), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #loginByAccount(String )
     */
    @Transactional( rollbackFor =  Exception.class )
    protected  UserInfo loginByAccount (String account  ) {
        try {
            logService.infoLog(logger, "service", "loginByAccount", "start login");
            long start = System.currentTimeMillis();


            UserPortalBean  userPortalBean = userStoreService.getUser( account  ) ;
            UserInfo userInfo = buildUserInfo( userPortalBean )  ;

            logService.infoLog(logger, "service", "login", String.format("loginByAccount....,totalTime2 :%sms", System.currentTimeMillis() - start));

            return userInfo;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "loginByAccount", String.format("login fail,account is:%s", account), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
        }
    }

    /**
     *  ?????????????????? ??????
     * @param userPortalBean
     * @return
     */
    private UserInfo buildUserInfo(  UserPortalBean userPortalBean ){
        UserInfo userInfo  = null ;
        if( userPortalBean != null ){
            userInfo = new UserInfo();
            userInfo.setAccount( userPortalBean.getSystemName() );
            userInfo.setFileExplain( userPortalBean.getPassword()  );
            userInfo.setUserId( userPortalBean.getUserId() );
            userInfo.setUserCode( userPortalBean.getUserKey() );
            userInfo.setUserName( userPortalBean.getUserName() );

            if( userPortalBean.getPerson() != null ){
                userInfo.setPersonId(userPortalBean.getPerson().getPersonId() );
                userInfo.setPersonCode( userPortalBean.getPerson().getPersonCode() );
                userInfo.setPoliceNum( userPortalBean.getPerson().getPoliceNumber() );
                userInfo.setPersonName( userPortalBean.getPerson().getPersonName() );

                //??????????????????
                List<OrganizationPortalBean> personOrg  = userPortalBean.getPerson().getPersonOrg() ;
                if( personOrg != null && personOrg.size() > 0 ){

                    userInfo.setOrgId( personOrg.get(0).getId() );
                    userInfo.setOrgCode( personOrg.get(0).getCode() );

                    userInfo.setOrgType( personOrg.get(0).getOrgType() );
                    userInfo.setPoliceType(  personOrg.get(0).getPoliceType() );
                    userInfo.setOrgFullKeyId(  personOrg.get(0).getFullKeyId() );
                    userInfo.setOrgParentId( personOrg.get(0).getParentId() );

                    userInfo.setOrgName(  personOrg.get(0).getName() );
                    userInfo.setOrgFullName(  personOrg.get(0).getFullName() );
                    //?????? ????????????
                    userInfo.setOrgNature( null ) ;
                    if( null != personOrg.get(0).getOrgRegion() ){
                        userInfo.setAreaCode(  personOrg.get(0).getCode()  );
                    }
                }
            }
            List<RolePortalBean> userRoles = userPortalBean.getRoles() ;
            if (userRoles != null && userRoles.size() > 0) {
                List<BasicEnumNumberBean> basicEnumBO = new ArrayList<>();
                List<RoleBean> roles = new ArrayList<>();
                userRoles.forEach(objs -> {
                    RoleBean role = new RoleBean();
                    role.setRoleId( objs.getRoleId() );
                    role.setRoleCode( objs.getRoleCode() );
                    String mc = objs.getRoleName() ;
                    role.setRoleName(I18nMessageUtils.getI18nMessage(String.format("jcj_bd_xtjs.mc.%s", mc), mc));
                    roles.add(role);
                });
                userInfo.setUserRole(basicEnumBO);
                userInfo.setBusinessRoles(roles);
            }
        }
        return userInfo  ;
    }


    /**
     * {@inheritDoc}
     *
     * @see #loginByImei(String )
     */
    @Transactional( rollbackFor =  Exception.class )
    public  UserInfo loginByImei (  String imei  ) {
        try {
            logService.infoLog(logger, "service", "loginByAccount", "start login");
            long start = System.currentTimeMillis();


            ImeiPortalBean imeiPortalBean = userStoreService.getImei( imei  ) ;
            UserInfo userInfo = buildUserInfo( imeiPortalBean )  ;



            logService.infoLog(logger, "service", "login", String.format("loginByAccount....,totalTime2 :%sms", System.currentTimeMillis() - start));

            return userInfo;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "loginByAccount", String.format("login fail,imei is:%s", imei), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
        }
    }


    /**
     *  ?????????????????? ??????
     * @param imeiPortalBean
     * @return
     */
    private UserInfo buildUserInfo(  ImeiPortalBean imeiPortalBean ){
        UserInfo userInfo  = null ;
        if( imeiPortalBean != null ){
            userInfo = new UserInfo();
            userInfo.setAccount( imeiPortalBean.getImei() );
            userInfo.setFileExplain( null  );
            userInfo.setUserId( imeiPortalBean.getId() );
            userInfo.setUserCode( imeiPortalBean.getImei() );
            userInfo.setUserName( imeiPortalBean.getName() );

            //??????????????? ?????? ??????
            if("SCZD".equals( imeiPortalBean.getType() ) ){
                userInfo.setPersonId( imeiPortalBean.getPersonId() );
                userInfo.setPersonName( imeiPortalBean.getPersonName() );
            }else if( "CZZD".equals (  imeiPortalBean.getType() )  ){
                if( null != imeiPortalBean.getVehicle() ){
                    userInfo.setPersonId( imeiPortalBean.getVehicle().getId()  );
                    userInfo.setPersonName( imeiPortalBean.getVehicle().getPlateNumber() );
                }
            }
            userInfo.setPoliceNum( null );
            userInfo.setPersonCode( null );


            userInfo.setOrgId( imeiPortalBean.getOrgId() );
            userInfo.setOrgCode( imeiPortalBean.getOrgCode() );

            userInfo.setOrgType( null );
            userInfo.setPoliceType(  null );
            userInfo.setOrgFullKeyId( null );
            userInfo.setOrgParentId( null  );

            userInfo.setOrgName(  imeiPortalBean.getOrgName() );
            userInfo.setOrgFullName(  imeiPortalBean.getOrgName() );
            //?????? ????????????
            userInfo.setOrgNature( null ) ;
            userInfo.setAreaCode( null );

            userInfo.setUserRole( null );
            userInfo.setBusinessRoles( null );
        }
        return userInfo  ;
    }



    /**
     * {@inheritDoc}
     *
     * @see #loginByImei(String )
     */
    @Transactional
    protected   UserInfo loginByVehicleId (  String vehicleId   ) {
        try {
            logService.infoLog(logger, "service", "loginByAccount", "start login");
            long start = System.currentTimeMillis();


            EquipmentVehicleBean equipmentVehicleBean  = vehicleService.findVehicle( vehicleId ) ;
            UserInfo userInfo = buildUserInfo( equipmentVehicleBean )  ;
            if( userInfo == null ){
                userInfo = new UserInfo();
                userInfo.setAccount( equipmentVehicleBean.getId() );
            }


            logService.infoLog(logger, "service", "login", String.format("loginByAccount....,totalTime2 :%sms", System.currentTimeMillis() - start));

            return userInfo;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "loginByAccount", String.format("login fail,imei is:%s", vehicleId ), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
        }
    }



    /**
     *  ?????????????????? ??????
     * @param equipmentVehicleBean
     * @return
     */
    private UserInfo buildUserInfo(   EquipmentVehicleBean equipmentVehicleBean  ){
        UserInfo userInfo  = null ;
        if( equipmentVehicleBean != null ){
            userInfo = new UserInfo();
            userInfo.setAccount( equipmentVehicleBean.getId() );
            userInfo.setFileExplain( null  );
            userInfo.setUserId( equipmentVehicleBean.getId() );
            userInfo.setUserCode( equipmentVehicleBean.getVehicleCode() );
            userInfo.setUserName( equipmentVehicleBean.getVehicleName() );


            userInfo.setPersonId( equipmentVehicleBean.getId() );
            userInfo.setPersonName( equipmentVehicleBean.getVehicleName() );

            userInfo.setPoliceNum( null );
            userInfo.setPersonCode( null );

            userInfo.setOrgId( equipmentVehicleBean.getOrganizationId() );
            userInfo.setOrgCode( null  );

            userInfo.setOrgType( null );
            userInfo.setPoliceType(  null );
            userInfo.setOrgFullKeyId( null );
            userInfo.setOrgParentId( null  );

            userInfo.setOrgName(  equipmentVehicleBean.getOrganizationName() );
            userInfo.setOrgFullName(  equipmentVehicleBean.getOrganizationName() );
            //?????? ????????????
            userInfo.setOrgNature( null ) ;
            userInfo.setAreaCode( null );

            userInfo.setUserRole( null );
            userInfo.setBusinessRoles( null );
        }
        return userInfo  ;
    }







    /**
     * {@inheritDoc}
     *
     * @see #heart(String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean heart(String token) {
        try {
            //
            Boolean authenticateResult = authenticateService.authentication(token);
            if (!authenticateResult) {
                login(token, null , null ,false);
            } else {
                TokenBean tokenBO = authenticateService.transformEncryptedToken(token, null , null );
                //??????????????????

                //????????????????????????
                String account = tokenBO.getUserAccount()  ;
                AccessBean accessBean = userCacheService.findKeyAccess( account ) ;
                accessBean.setLastAccessTime( servletService.getSystemTime() );
                userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBean, true);

                //????????????????????????
                UserInfo userInfo = (UserInfo) sessionDataStore.get().get("userInfo");
                if (userInfo.getNeedAuthenticate()!=tokenBO.getNeedAuthenticate()){
                    userInfo.setNeedAuthenticate(tokenBO.getNeedAuthenticate());
                    userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix,"put",userInfo,true);
                }

                //??????????????????
                Map<String, AgentBean> cache = agentCacheService.findAllAgentCache(cacheKeyPrefix);
                if( Strings.isNotBlank( userInfo.getAgentNum() ) ){
                    AgentBean agentBO = cache.get( userInfo.getAgentNum() );
                    if (agentBO != null) {
                        boolean res = false;
                        boolean senWebsocket=false;
                        if (!StringUtils.isBlank(tokenBO.getAgentOperateState())) {
                            Integer operateState = Integer.valueOf(tokenBO.getAgentOperateState());
                            if (operateState != null && !operateState.equals(agentBO.getAgentOperateState().getCode())) {
                                agentBO.setAgentOperateState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentOperateStateEnum.class, operateState));
                                res = true;
                                senWebsocket=true;
                            }
                        }
                        if (!StringUtils.isBlank(tokenBO.getAgentState())) {
                            Integer agentState = Integer.valueOf(tokenBO.getAgentState());
                            if (agentState != null && !agentState.equals(agentBO.getAgentState().getCode())) {
                                agentBO.setAgentState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentStateEnum.class, agentState));
                                res = true;
                                senWebsocket=true;
                            }
                        }
                        if (!StringUtils.isBlank(tokenBO.getAgentPhoneState())) {
                            Integer phoneState = Integer.valueOf(tokenBO.getAgentPhoneState());
                            if (phoneState != null && !phoneState.equals(agentBO.getAgentPhoneState().getCode())) {
                                agentBO.setAgentPhoneState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentPhoneStateEnum.class, phoneState));
                                res = true;
                                senWebsocket=true;
                            }
                        }
                        if (!tokenBO.getNeedAuthenticate().equals(agentBO.getNeedAuthenticate())){
                            agentBO.setNeedAuthenticate(tokenBO.getNeedAuthenticate());
                            res=true;
                        }
                        if (res){
                            agentBO.setViolateInfo(null);
                            agentCacheService.mergeAgentCache("put",agentBO,cacheKeyPrefix,true);
                            if (senWebsocket){
                                agentService.agentSendWebSocketToSuperior(WebsocketCodeEnum.AGENTSTATECHANGE.getCode(), agentBO, agentBO.getPersonBean().getPersonOrgId() );
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "heart", "heart fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.HEART_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
        public void checkHeart() {
        try {
            long systemTime = servletService.getSystemTime();
            SystemConfigurationBean config = systemConfigurationService.getSystemConfigByConfigType("loginTimeOut");
            //????????????????????????
            long limtTime = systemTime-10*1000l;
            if (config != null && !StringUtils.isBlank(config.getConfigValue())) {
                limtTime = systemTime - Long.valueOf(config.getConfigValue()) * 1000l;
            }
            Map<String, AccessBean> accesses = userCacheService.findAllAccess(cacheKeyPrefix);
            if (accesses != null && accesses.size() > 0) {
                for (String key : accesses.keySet()) {
                    AccessBean accessBean = accesses.get(key);
                    if (accessBean.getLastAccessTime() < limtTime) {
                        logService.infoLog(logger, "service", "checkHeart", String.format("system logout accessBO:%s,limit heart time:%s", JSONObject.toJSONString(accessBean), systemTime));
                        doLogout(accessBean);
                        //??????????????????
                        //TODO:???UserInfo???????????????????????????
                        Map<String, Object> map = sessionDataStore.get();
                        UserInfo userInfo = (UserInfo) map.get("userInfo");
                        if (userInfo==null){
                            userInfo=new UserInfo();
                        }
                        String desc = String.format("check heart,clearing timeout user,systemTime:%s,limitTime:%s,lastAccessTime:%s", systemTime, limtTime, accessBean.getLastAccessTime());
                        AuditLogSaveInputInfo auditLogSaveInputInfo = new AuditLogSaveInputInfo();
                        auditLogSaveInputInfo.setOperateTime(System.currentTimeMillis());
                        auditLogSaveInputInfo.setOperateType(String.valueOf(OperationTypeEnum.OPERATIONTYPE_CHERT_HEART_OVERTIME.getCode()));
                        auditLogSaveInputInfo.setOrganizationId(userInfo.getOrgId());
                        auditLogSaveInputInfo.setOrganizationName(userInfo.getOrgFullName());
                        auditLogSaveInputInfo.setOperateSeatNumber(accessBean.getAgentNum());
                        auditLogSaveInputInfo.setOperateSeatName(userInfo.getAgentRoom());
                        auditLogSaveInputInfo.setAcceptancePersonNumber(userInfo.getAccount());
                        auditLogSaveInputInfo.setAcceptancePersonName(accessBean.getSystemName());
                        auditLogSaveInputInfo.setIpAddress(accessBean.getClientIp());
                        auditLogSaveInputInfo.setDesc(desc);
                        auditLogSaveInputInfo.setRemarks(null);
                        auditLogService.saveAuditLog(auditLogSaveInputInfo);
                        subAuditService.buildSubAuditLog(userInfo.getAccount(), userInfo.getUserName(), userInfo.getOrgCode(), userInfo.getOrgName(),
                                "Add", "Ok", desc);
                    }
                }
            }

            //?????? ??????
            Map<String, UserInfo> userInfoCache  = userCacheService.findAllOnlineUserInfoCache( cacheKeyPrefix ) ;
            accesses = userCacheService.findAllAccess(cacheKeyPrefix);
            if (userInfoCache != null && userInfoCache.size() > 0) {
                for (String key : userInfoCache.keySet()) {
                    UserInfo userInfo = userInfoCache.get(key) ;
                    if (      accesses == null ||  (  accesses != null && accesses.get( userInfo.getAccount() ) == null )    ) {
                        logService.infoLog(logger, "service", "checkHeart", String.format("system logout user account:%s",    key  )   );
                        //????????????
                        userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "remove", userInfo, true);
                    }
                }
            }

            //?????? ??????
            Map<String, AgentBean> agentCache = agentCacheService.findAllAgentCache(cacheKeyPrefix);
            accesses = userCacheService.findAllAccess(cacheKeyPrefix);
            userInfoCache    = userCacheService.findAllOnlineUserInfoCache( cacheKeyPrefix ) ;
            if (agentCache != null && agentCache.size() > 0) {
                for (String key : agentCache.keySet()) {
                    AgentPersonBean personBO = agentCache.get(key).getPersonBean();
                    //?????? ??????????????????????????? ????????????????????????
                    if(personBO != null ){

                        //?????? ???????????????????????? ??????????????????????????????
                        if(    userInfoCache != null  && userInfoCache.get(personBO.getAccount()) != null
                                && accesses !=null  &&  accesses.get(personBO.getAccount()) != null ) {
                            //?????? ?????????????????????  ????????????  ????????????  ?????????????????????
                            //??????????????????
                        }else {
                            //????????????
                            agentService.agentLogOut(key, OperationTypeEnum.OPERATIONTYPE_LOGOUT);
                            //???????????? ??????????????????
                            if(    userInfoCache !=null  && userInfoCache.get( personBO.getAccount() )  != null  ){
                                //????????????
                                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "remove",
                                        userInfoCache.get( personBO.getAccount() ) , true);
                            }
                            //???????????? ????????????????????????
                            if(    accesses !=null  && accesses.get( personBO.getAccount() )  != null  ){
                                //??????????????????
                                userCacheService.modifyAccessCache(cacheKeyPrefix, "remove",
                                        accesses.get( personBO.getAccount() ) , true);
                            }
                        }
                    }

                }
            }



        } catch (Exception ex) {
            logService.erorLog(logger, "service", "checkHeart", "fail to check heart", ex);
        }
    }
//
//        @Override
//    public void loginInit(AccessBO accessBO, UserInfo userInfo) {
//        try {
//            userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "put", userInfo, true);
//            userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBO, true);
//            AgentBean agentBean = prepareAgentInfo(userInfo);
//            agentService.agentLogin(agentBean);
//        } catch (UserInterfaceException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            logService.erorLog(logger, "service", "loginInitAgent", "login init agent fail", ex);
//            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_INIT_FAIL);
//        }
//    }

//    /**
//     * {@inheritDoc}
//     *
//     * @see #authentication(String)
//     */
//    @Override
//    public Boolean authentication(String token) {
//        try {
//            logService.infoLog(logger, "service", "authentication", String.format("now start authenticate,the token is:%s ", token));
//            long start = System.currentTimeMillis();
//            TokenBO bo = transformEncryptedToken(token);
//            Map<String, AccessBO> acceses = userCacheService.findAllAccess(cacheKeyPrefix);
//            AccessBO accessBO = acceses.get(bo.getCurrentIP());
//            long offset = Long.parseLong(accessOffset) * 1000l;
//            if (accessBO != null && !StringUtils.isBlank(sessionDataStore.getCurrentUserCode()) &&
//                    sessionDataStore.getCurrentUserCode().equals(accessBO.getAccount()) && bo.getUtcTime() + offset >= accessBO.getLastAccessTime()) {
//                accessBO.setLastAccessTime(bo.getUtcTime());
//                userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBO, true);
//                long end = System.currentTimeMillis();
//                logService.infoLog(logger, "service", "authentication", String.format("now end authenticate,the totalTime is:%sms ", end - start));
//                return true;
//            } else {
//                logService.erorLog(logger, "service", "authentication", "authenticate fail,the clientIp is not find in cache.", null);
//                throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.AUTHENTICATE_FAIL);
//            }
//        } catch (UserInterfaceException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            logService.erorLog(logger, "service", "authentication", "authenticate fail.", null);
//            throw new UserInterfaceKeydataException(UserInterfaceKeydataException.KeydataErrors.AUTHENTICATE_FAIL);
//        }
//    }

    /**
     * {@inheritDoc}
     *
     * @see #logout(String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean logout(String token) {
        try {
            TokenBean tokenBean = authenticateService.transformEncryptedToken(token, null, null);
            AccessBean accessBean = userCacheService.findAllAccess(cacheKeyPrefix).get(tokenBean.getUserAccount());
            return doLogout(accessBean);
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "removeAccessAndUserInfoFromCache", "log out fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DESTROY_USER_FAIL);
        }
    }

    /**
     * ????????????
     *
     * @param accessBean ????????????
     * @return ??????????????????
     */
    private Boolean doLogout(AccessBean accessBean) {
        if( accessBean != null ){
            //?????? ?????? ?????? ????????????
            UserInfo userInfo = userCacheService.findAllOnlineUserInfoCache(cacheKeyPrefix).get(accessBean.getSystemName());
            if (userInfo != null ) {
                sessionDataStore.set("userInfo", userInfo);
                //????????????
                BasicEnumNumberBean basicEnumBo = BasicEnumNumberUtils.getBasicEnumBean(AgentStateEnum.class, AgentStateEnum.AGENT_STATE_OFFLINE.name());
                agentService.agentLogOut(userInfo.getAgentNum(), OperationTypeEnum.OPERATIONTYPE_LOGOUT);
                //????????????
                userCacheService.modifyOnlineUserInfoCache(cacheKeyPrefix, "remove", userInfo, true);
                //todo ??????????????????????????? ????????????
                userInfo.setClientIp( null );
                userInfo.setOnlineSign( 0 ); // ???????????? 0 ?????? 1 ??????
                userInfoSendWebSocketToSuperior( USER_INFO_LOGOUT.getCode() ,  userInfo , userInfo.getOrgId() ) ;
            }
            //??????????????????
            userCacheService.modifyAccessCache(cacheKeyPrefix, "remove", accessBean, true);

            sessionDataStore.removeCurrentUserCode();
            logService.infoLog(logger, "service", "removeAccessAndUserInfoFromCache", String.format("now destroy userInfo,clientIp is:%s,systemName is:%s",
                    accessBean.getClientIp(), accessBean.getSystemName()));
        }
        return true;
    }

    //    /**
//     * ???????????????token
//     *
//     * @param encryptedToken ????????????token
//     * @return ??????????????????token
//     */
//    private TokenBO transformEncryptedToken(String encryptedToken) {
//        try {
//            if (!StringUtils.isBlank(encryptedToken)) {
//                String decrypt = AES256Helper.decrypt(encryptedToken);
//                String[] strs = decrypt.split(",");
//                Long utcTime = Long.valueOf(strs[0]);
//                String currentIP = strs[1];
//                String icpType = strs[2];
//                if (utcTime == null || StringUtils.isBlank(currentIP) || currentIP.length() < 7) {
//                    throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
//                }
//                TokenBO bo = new TokenBO();
//                bo.setCurrentIP(currentIP);
//                bo.setIcpType(icpType);
//                bo.setUtcTime(utcTime);
//                return bo;
//            } else {
//                logService.erorLog(logger, "service", "transformEncryptedToken", "authentication fail ,the token is null", null);
//                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
//            }
//        } catch (UserInterfaceException ex) {
//            logService.erorLog(logger, "service", "transformEncryptedToken", "authentication fail ,the token is null", null);
//            throw ex;
//        } catch (Exception ex) {
//            logService.erorLog(logger, "service", "transformEncryptedToken", "authentication fail", ex);
//            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
//
//        }
//    }

    /**
     * ??????????????????
     *
     * @param systemName ????????????
     * @param clientIp   ??????IP
     * @return ??????????????????
     */
    @Transactional(readOnly = true)
    protected UserInfo findUserInfoBySystemName(String systemName, String clientIp) {
        try {
            long userStart = System.currentTimeMillis();
            UserInfo userInfo = null;
            logService.infoLog(logger, "service", "findUserInfoBySystemName", "now start execute sql to find userInfo");
            long start = System.currentTimeMillis();
            List<Object[]> result = userRepository.findUserInfoBySystemName(systemName);
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUserInfoBySystemName", String.format("end execute sql to find userInfo,totalTime:%sms", end - start));
            logService.infoLog(logger, "service", "findUserInfoBySystemName", "now start execute sql to find AgentInfo by clientIp");
            long agentStart = System.currentTimeMillis();

            String[] ips = clientIp.split(";");


            AgentBean agent = agentService.findAgentByIp(ips);

            long agentEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUserInfoBySystemName", String.format("end execute sql to find agentInfo,totalTime:%sms", agentEnd - agentStart));
            logService.infoLog(logger, "service", "findUserInfoBySystemName", "now start execute sql to find userRole");
            Long roleStart = System.currentTimeMillis();
            List<Object[]> userRoles = userRepository.getUserRoles(systemName);
            Long roleEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUserInfoBySystemName", String.format("end execute sql to find userRole,totalTime:%sms", roleEnd - roleStart));

            if (result != null && result.size() > 0 && agent != null) {
                userInfo = new UserInfo();
                userInfo.setAccount(systemName);
                Object[] objects = result.get(0);
                userInfo.setUserId((String) objects[0]);
                userInfo.setUserCode((String) objects[1]);
                userInfo.setUserName((String) objects[2]);
                userInfo.setPersonId((String) objects[3]);
                userInfo.setPersonCode((String) objects[4]);

                userInfo.setPoliceNum((String) objects[5]);
                userInfo.setPersonName((String) objects[6]);
                userInfo.setOrgId((String) objects[7]);
                userInfo.setOrgCode((String) objects[8]);

                userInfo.setOrgType((String) objects[9]);
                userInfo.setPoliceType((String) objects[10]);
                userInfo.setOrgFullKeyId((String) objects[11]);
                userInfo.setOrgParentId((String) objects[12]);
                userInfo.setAreaCode((String) objects[13]);

                userInfo.setOrgName((String) objects[14]);
                userInfo.setOrgFullName((String) objects[15]);
                userInfo.setFileExplain((String) objects[16]);

                //?????? ???????????? ??????
                userInfo.setOrgNature( (String) objects[17]) ;
                userInfo.setOrgOrderNum(  ( Integer ) objects[18]   ) ;
                userInfo.setOrgParentName( (String) objects[19]); ;


                if (userRoles != null && userRoles.size() > 0) {
                    List<BasicEnumNumberBean> basicEnumBO = new ArrayList<>();
                    List<RoleBean> roles = new ArrayList<>();
                    userRoles.forEach(objs -> {
                        RoleBean role = new RoleBean();
                        role.setRoleId((String) objs[0]);
                        role.setRoleCode((String) objs[1]);
                        String mc = (String) objs[2];
                        role.setRoleName(I18nMessageUtils.getI18nMessage(String.format("jcj_bd_xtjs.mc.%s", mc), mc));
                        if (!StringUtils.isBlank(role.getRoleCode())) {
                            int code = 0;
                            try {
                                code = Integer.valueOf(role.getRoleCode());
                            } catch (NumberFormatException e) {
                                logger.info("?????????????????????????????????");
                            }
                            //????????????
                            if (0 <= code && code <= 99) {
                                basicEnumBO.add(BasicEnumNumberUtils.getBasicEnumBeanByCode(RoleEnum.class, code));
                            }
                            //????????????
                            if (100 <= code && code <= 199) {
                                roles.add(role);
                            }
                        }
                    });

                    userInfo.setUserRole(basicEnumBO);
                    userInfo.setBusinessRoles(roles);
                }

                userInfo.setAgentNum( agent.getAgentNumber() );
                userInfo.setAcdGroupNumber( agent.getLoginAcd() );
                userInfo.setClientIp(agent.getIp());
                userInfo.setDeskphone(agent.getExtensionNumber());
                userInfo.setAgentId(agent.getId());
                userInfo.setAgentOrder(agent.getOrder());
                userInfo.setAgentRoom(agent.getRoom());
                long userEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "findUserInfoBySystemName", String.format("total time:%sms", userEnd - userStart));
                return userInfo;
            } else {
                logService.erorLog(logger, "service", "findUserInfoBySystemName", String.format("cannot find userInfo or agentInfo by systemName:%s and ip:%s,agent is not exist", systemName, clientIp), null);
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.USER_AGENT_NOTEXIT);
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUserInfoBySystemName", "find userInfo from database fail,error:", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }



    /**
     * ??????????????????
     *
     * @param systemName ????????????
     * @return ??????????????????
     */
    @Transactional(readOnly = true)
    protected UserInfo findUserInfoBySystemName(String systemName ) {
        try {
            long userStart = System.currentTimeMillis();
            UserInfo userInfo = null;
            logService.infoLog(logger, "service", "findUserInfoBySystemName", "now start execute sql to find userInfo");
            long start = System.currentTimeMillis();
            List<Object[]> result = userRepository.findUserInfoBySystemName(systemName);
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUserInfoBySystemName", String.format("end execute sql to find userInfo,totalTime:%sms", end - start));

            logService.infoLog(logger, "service", "findUserInfoBySystemName", "now start execute sql to find userRole");
            Long roleStart = System.currentTimeMillis();
            List<Object[]> userRoles = userRepository.getUserRoles(systemName);
            Long roleEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUserInfoBySystemName", String.format("end execute sql to find userRole,totalTime:%sms", roleEnd - roleStart));

            if (result != null && result.size() > 0  ) {
                userInfo = new UserInfo();
                userInfo.setAccount(systemName);
                Object[] objects = result.get(0);
                userInfo.setUserId((String) objects[0]);
                userInfo.setUserCode((String) objects[1]);
                userInfo.setUserName((String) objects[2]);
                userInfo.setPersonId((String) objects[3]);
                userInfo.setPersonCode((String) objects[4]);

                userInfo.setPoliceNum((String) objects[5]);
                userInfo.setPersonName((String) objects[6]);
                userInfo.setOrgId((String) objects[7]);
                userInfo.setOrgCode((String) objects[8]);

                userInfo.setOrgType((String) objects[9]);
                userInfo.setPoliceType((String) objects[10]);
                userInfo.setOrgFullKeyId((String) objects[11]);
                userInfo.setOrgParentId((String) objects[12]);
                userInfo.setAreaCode((String) objects[13]);

                userInfo.setOrgName((String) objects[14]);
                userInfo.setOrgFullName((String) objects[15]);
                userInfo.setFileExplain((String) objects[16]);

                //?????? ????????????
                userInfo.setOrgNature( (String) objects[17]) ;
                userInfo.setOrgOrderNum(  ( Integer ) objects[18]   ) ;
                userInfo.setOrgParentName( (String) objects[19]); ;

                if (userRoles != null && userRoles.size() > 0) {
                    List<BasicEnumNumberBean> basicEnumBO = new ArrayList<>();
                    List<RoleBean> roles = new ArrayList<>();
                    userRoles.forEach(objs -> {
                        RoleBean role = new RoleBean();
                        role.setRoleId((String) objs[0]);
                        role.setRoleCode((String) objs[1]);
                        String mc = (String) objs[2];
                        role.setRoleName(I18nMessageUtils.getI18nMessage(String.format("jcj_bd_xtjs.mc.%s", mc), mc));
                        if (!StringUtils.isBlank(role.getRoleCode())) {
                            int code = 0;
                            try {
                                code = Integer.valueOf(role.getRoleCode());
                            } catch (NumberFormatException e) {
                                logger.info("?????????????????????????????????");
                            }
                            //????????????
                            if (0 <= code && code <= 99) {
                                basicEnumBO.add(BasicEnumNumberUtils.getBasicEnumBeanByCode(RoleEnum.class, code));
                            }
                            //????????????
                            if (100 <= code && code <= 199) {
                                roles.add(role);
                            }
                        }
                    });

                    userInfo.setUserRole(basicEnumBO);
                    userInfo.setBusinessRoles(roles);
                }

                long userEnd = System.currentTimeMillis();
                logService.infoLog(logger, "service", "findUserInfoBySystemName", String.format("total time:%sms", userEnd - userStart));
                return userInfo;
            } else {
                logService.erorLog(logger, "service", "findUserInfoBySystemName", String.format("cannot find userInfo or agentInfo by systemName:%s   is not exist", systemName ), null);
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.USER_AGENT_NOTEXIT);
            }
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUserInfoBySystemName", "find userInfo from database fail,error:", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }




    /**
     * ??????????????????
     *
     * @param userInfo ????????????
     * @return ??????????????????
     */
    private AgentBean prepareAgentInfo(   UserInfo userInfo, TokenBean tokenBean) {
        try {
            long start = System.currentTimeMillis();
            AgentPersonBean personBean = new AgentPersonBean();
            personBean.setUserCode(userInfo.getPersonCode());
            personBean.setUserId(userInfo.getPersonId());
            personBean.setUserName(userInfo.getPersonName());
            personBean.setPersonOrgCode(userInfo.getOrgCode());
            personBean.setPersonOrgId(userInfo.getOrgId());
            personBean.setPersonOrgName(userInfo.getOrgName());
            personBean.setAccount(userInfo.getAccount());
            personBean.setPersonRole(userInfo.getUserRole());
            //???????????? ?????????
//            List<String> accepts = new ArrayList<>();
//            List<RoleBO> userRoles = userInfo.getBusinessRoles();
//            if (userRoles != null && userRoles.size() > 0) {
//                userRoles.forEach(role -> {
//                    if (!StringUtils.isBlank(role.getRoleCode()) && isInteger(role.getRoleCode())) {
//                        int code = Integer.valueOf(role.getRoleCode());
//                        //????????????
//                        accepts.add(String.valueOf(code - 100));
//                    }
//                });
//            }
//            DistributeModeBO distributeMode = distributeService.getDistributeMode(userInfo.getAccount(), Integer.valueOf(userInfo.getAgentNum()), accepts);
//            AgentBean agentBean = new AgentBean();
            AgentBean agentBean = agentService.findAgent( userInfo.getAgentNum() ) ;
            if( agentBean == null ){
                 agentBean = new AgentBean();
            }
            if (tokenBean != null && !StringUtils.isBlank(tokenBean.getAgentState()) && isInteger(tokenBean.getAgentState())) {
                agentBean.setAgentState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentStateEnum.class, Integer.valueOf(tokenBean.getAgentState())));
            } else {
                agentBean.setAgentState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentStateEnum.class, AgentStateEnum.AGENT_STATE_IDLE.getCode()));
            }
            if (tokenBean != null && !StringUtils.isBlank(tokenBean.getAgentOperateState()) && isInteger(tokenBean.getAgentOperateState())) {
                agentBean.setAgentOperateState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentOperateStateEnum.class, Integer.valueOf(tokenBean.getAgentOperateState())));
            } else {
                agentBean.setAgentOperateState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentOperateStateEnum.class, AgentOperateStateEnum.AGENT_OPERATE_STATE_LOGIN.getCode()));
            }
            if (tokenBean != null && !StringUtils.isBlank(tokenBean.getAgentPhoneState()) && isInteger(tokenBean.getAgentPhoneState())) {
                agentBean.setAgentPhoneState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentPhoneStateEnum.class, Integer.valueOf(tokenBean.getAgentPhoneState())));
            } else {
                agentBean.setAgentPhoneState(BasicEnumNumberUtils.getBasicEnumBeanByCode(AgentPhoneStateEnum.class, AgentPhoneStateEnum.AGENT_PHONE_STATE_IDLE.getCode()));
            }
            //TODO ????????????
//            if (!StringUtils.isBlank(phoneType)){
//                Integer type = Integer.valueOf(phoneType);
//                agentBean.setAgentCallType(BasicEnumUtils.getBasicEnumBoByCode(AgentEnum.class, type));
//            }
            agentBean.setAgentNumber(userInfo.getAgentNum());
            agentBean.setPersonBean(personBean);
//            agentBean.setPhone(userInfo.getDeskphone());
            if (!StringUtils.isBlank(userInfo.getAgentOrder())) {
                agentBean.setOrder(userInfo.getAgentOrder());
            }else{
                agentBean.setOrder( userInfo.getAgentNum() );
            }
            //????????????????????????
            agentBean.setOnlineSign( 1 ); // ????????????  0 ????????? 1 ??????

            agentBean.setIp(userInfo.getClientIp());
            agentBean.setRoom(userInfo.getAgentRoom());
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "prepareAgentInfo", String.format("total time:%sms", end - start));
            return agentBean;
        }
//        catch (UserInterfaceException ex) {
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "initAgentInfo", String.format("init agentInfo fail when" +
                    " login ,user:%s,agent:%s", userInfo.getAccount(), userInfo.getAgentNum()), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AGENT_LOGIN_FAIL);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #setUserLanguage(String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public String setUserLanguage(String language) {
        try {
            Map<String, String> map = userCacheService.modifyUserLanguage(cacheKeyPrefix, sessionDataStore.getCurrentUserCode(), language, true);
        } catch (Exception ex) {
            //TODO ????????????
            logService.erorLog(logger, "service", "setUserLanguage", "set user's language fail", ex);
        }
        return language;
    }

    /**
     * {@inheritDoc}
     *
     * @see #getUserLanguage(String)
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public String getUserLanguage(String systemName) {
        try {
            String language = null;
            Map<String, String> map = userCacheService.findAllUserLanguage(cacheKeyPrefix);
            if (map != null && map.size() > 0) {
                language = map.get(systemName);
            }
            return language;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getUserLanguage", String.format("get user:%s language fail"), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USER_LANGUAGE_FAIL);
        }
    }


    private String  buildIcpType( String icpType , String userName ){
        if( "0".equals( icpType )){
            //?????????????????? ?????? ????????????
            UserInfo  userInfo = findUserInfoBySystemName( userName )  ;
            // ???????????? ??????????????? ??????????????? (???????????????????????????????????????????????????????????????)
            if( userInfo != null && userInfo.getUserRole() != null ){
                List<BasicEnumNumberBean> userRole  = userInfo.getUserRole() ;
//                Integer  userRoleCode = userRole.getCode() ;
                if( userRole != null && userRole.size() == 1 && null != userRole.get(0).getCode() && RoleEnum.AGENT_NO_CALLTAKING.getCode() == userRole.get(0).getCode()  ){
                    return  "1"  ;
                }
            }
            //???????????? //todo ???????????????????????????????????????
            if( userInfo != null && Strings.isNotBlank( userInfo.getOrgNature() ) ){
                String orgNature = userInfo.getOrgNature().substring( 0 , 2  ) ;
                if(  ORGANIZATION_NATURE_HEAD_XJZD.getCode().equals( orgNature ) ){
                    return  "1"  ;
                }
            }
            return  icpType  ;
        }else{
            return  icpType  ;
        }
    }


    /**
     *  ???????????? ???????????? ??????webSocket
     *
     * @param code    websocket?????????
     * @param obj     ????????????
     * @param orgId ?????????????????????????????????
     * @return ??????????????????
     */
    public Boolean userInfoSendWebSocketToSuperior( String code, Object obj, String orgId) {
        try {
            // ???????????????????????????
            List<Integer> supervisors = new ArrayList<>();

            if ("cad".equals(cacheKeyPrefix)) {
                supervisors = this.getAllSupervisorCode();
            }
            if ("mdc".equals(cacheKeyPrefix)) {
                supervisors.add(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode());
            }
            if (StringUtils.isBlank(orgId)) {
                OrganizationBean rootOrg = organizationService.getRootOrg();
                orgId = rootOrg.getId();
            }
            //????????????????????????
            List<String> tos = dataFilterService.findAllOnlineAccountByCondition(Arrays.asList(orgId), "1", supervisors, null);

            // ??????webSocket
            notifyActionService.pushMessageToUsers(code, obj, tos);

            return true;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "userInfoSendWebSocketToSuperior", "agent send webSocket fail.", ex);
            return false;
        }
    }

    /**
     * ????????????????????????code
     *
     * @return ????????????????????????code
     */
    private List<Integer> getAllSupervisorCode() {
        List<Integer> list =new ArrayList<>();
        list.add(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode());
        list.add(RoleEnum.AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode());
        list.add(AGENT_PERSONROLE_ALL_SUPERVISOR.getCode());
        return list;
    }


    private Boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * {@inheritDoc}
     *
     * @see #initUserInfoCache()
     */
    @Override
    public  Boolean initUserInfoCache()  {
        try {

            List<String> roleIds = new ArrayList<>();
            //??????  ???????????????
            roleIds.add(String.valueOf(AGENT_PERSONROLE_CALLTAKING.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_DISPATCH.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_CALLTAKING_DISPATCH.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_ALL_SUPERVISOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_INTELLIGENT.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_SENIOR_DISPATCH.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_ADMINISTRATOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_NO_CALLTAKING.getCode() ) );



            List<String> accounts =    userRepository.findAccountByRoleIds( roleIds );

           if( accounts != null && accounts.size() > 0 ){

               for( String  account : accounts ){
                   UserInfo  userInfo = findUserInfoBySystemName(  account )  ;
                   //????????????????????????
                   userCacheService.modifyAllUserInfoCache (cacheKeyPrefix, "put", userInfo, true);
               }
           }

            return  true ;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "getAllOnlineUserInfo", "get all online userInfo fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }

    //?????? ??????ids ?????? ??????????????????
    private Map<String, UserInfo> getAllUserInfoOutAgentUser( List<String> roleIds   ){

        Map<String, UserInfo> tempOnlineUsers  = new HashMap<>() ;

        Map<String, UserInfo> allOnlineUsers = userCacheService.findAllOnlineUserInfoCache(cacheKeyPrefix);
        Map<String, UserInfo> onlineUsers = new HashMap<>( allOnlineUsers ) ;

        Map<String, UserInfo> allUsers = userCacheService.findAllUserInfoCache(cacheKeyPrefix);
        Map<String, UserInfo> users = new HashMap<>( allUsers ) ;

        List<String> accounts = new ArrayList<>() ;
        // ?????? ????????????????????????
        if( roleIds == null || roleIds.size() < 1 ){
            //??????  ???????????????
            roleIds = new ArrayList<>() ;
            roleIds.add(String.valueOf(AGENT_PERSONROLE_CALLTAKING.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_DISPATCH.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_DISPATCH_SUPERVISOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_CALLTAKING_DISPATCH.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_ALL_SUPERVISOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_INTELLIGENT.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_SENIOR_DISPATCH.getCode() ) );
            roleIds.add(String.valueOf(AGENT_PERSONROLE_ADMINISTRATOR.getCode() ) );
            roleIds.add(String.valueOf(AGENT_NO_CALLTAKING.getCode() ) );

            accounts = userRepository.findAccountByRoleIds( roleIds ) ;
        }else {
            accounts = userRepository.findAccountByRoleIds( roleIds ) ;
        }

        if( accounts != null && accounts.size() > 0 ){
            for(String  account  : accounts ){
                UserInfo userInfo = null ;
                if( onlineUsers.containsKey( account ) ){
                    userInfo =  onlineUsers.get( account) ;
                    userInfo.setOnlineSign( 1 );  // ???????????? 0 ?????? 1 ??????
                    tempOnlineUsers.put( account ,userInfo ) ;
                }else if( users.containsKey( account ) ) {
                    userInfo =  users.get( account)  ;
                    userInfo.setOnlineSign( 0 );  // ???????????? 0 ?????? 1 ??????
                    tempOnlineUsers.put( account ,userInfo ) ;
                }

                if( userInfo == null ){
                    userInfo = findUserInfoBySystemName( account ) ;
                    tempOnlineUsers.put( account ,userInfo ) ;

                    userCacheService.modifyAllUserInfoCache (cacheKeyPrefix, "put", userInfo, true);
                }

            }
        }
        return tempOnlineUsers ;
    }


    /**
     * {@inheritDoc}
     *
     * @see #getAllUserInfoByAuthorization()
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<UserInfo> findAllOnlineUserInfo() {
        try {
            long start = System.currentTimeMillis();


            Map<String, UserInfo> allOnlineUserMap =  userCacheService.findAllOnlineUserInfoCache( cacheKeyPrefix   );

            List<UserInfo> list= new ArrayList<>(allOnlineUserMap.values());

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOnlineUserInfo", String.format("end findAllOnlineUserInfoByAuthorization,totalTime:%sms", end - start));

            return list;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOnlineUserInfo", "get all online userInfo fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #getAllUserInfoByAuthorization()
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<UserInfo> getAllUserInfoByAuthorization() {
        try {
            long start = System.currentTimeMillis();
            List<UserInfo> list = new ArrayList<>();

            UserInfo userInfo = getUserInfo();
            Map<String, OrganizationBean> idOrgMap = organizationService.findOrganizationAllAuthorization(userInfo.getOrgId(), null);

            List<String> roleIds =  new ArrayList<>() ;
            roleIds.add(String.valueOf(AGENT_NO_CALLTAKING.getCode() ) );
            Map<String, UserInfo> allOnlineUserMap =  getAllUserInfoOutAgentUser( roleIds );

            List<String> orgIds = new ArrayList<>(idOrgMap.keySet());
            if (null == allOnlineUserMap) {
                logService.infoLog(logger, "service", "findAllOnlineUserInfoCache", "cache allOnlineUserMap is null.");
                allOnlineUserMap = new HashMap<>();
            }
            List<UserInfo> userInfoList = new ArrayList<>(allOnlineUserMap.values());
            if (orgIds != null && orgIds.size() > 0) {
                userInfoList.forEach( user -> {
                    if (   orgIds.contains( user.getOrgId() )   ) {
                        //2021-04-22 ?????? ????????????????????????
                        String account = user.getAccount();
                        List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
                        conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("account", account));
                        GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
                        AgentAccountEntity agentAccountEntity= accessor.findOne(conditionGroup,
                                AgentAccountEntity.class);
                        if (agentAccountEntity!=null) {
                            user.setLatesttime(agentAccountEntity.getLatesttime());
                        }
                        list.add( user );
                    }
                });
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOnlineUserInfoByAuthorization", String.format("end findAllOnlineUserInfoByAuthorization,totalTime:%sms", end - start));
            return list;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "getAllOnlineUserInfo", "get all online userInfo fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_USERINFO_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #findAllOnlineUserInfoByAuthorization()
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public List<UserInfo> findAllOnlineUserInfoByAuthorization() {
        try {
            long start = System.currentTimeMillis();
            List<UserInfo> list = new ArrayList<>();

            UserInfo userInfo = getUserInfo();
            Map<String, OrganizationBean> idOrgMap = organizationService.findOrganizationAllAuthorization(userInfo.getOrgId(), null);

            List<String> roleIds =  new ArrayList<>() ;
            roleIds.add(String.valueOf(AGENT_NO_CALLTAKING.getCode() ) );
            Map<String, UserInfo> allOnlineUserMap =  getAllUserInfoOutAgentUser( roleIds );

            List<String> orgIds = new ArrayList<>(idOrgMap.keySet());
            if (null == allOnlineUserMap) {
                logService.infoLog(logger, "service", "findAllOnlineUserInfoCache", "cache allOnlineUserMap is null.");
                allOnlineUserMap = new HashMap<>();
            }
            List<UserInfo> userInfoList = new ArrayList<>(allOnlineUserMap.values());
            if (orgIds != null && orgIds.size() > 0) {
                userInfoList.forEach( user -> {
                    if ( 1 == user.getOnlineSign() &&  orgIds.contains( user.getOrgId() )   ) {
                        list.add( user );
                    }
                });
            }
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllOnlineUserInfoByAuthorization", String.format("end findAllOnlineUserInfoByAuthorization,totalTime:%sms", end - start));
            return list;
        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllOnlineUserInfoByAuthorization", "find all online userInfo by authorizate fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.FIND_USER_FAIL);
        }
    }






}
