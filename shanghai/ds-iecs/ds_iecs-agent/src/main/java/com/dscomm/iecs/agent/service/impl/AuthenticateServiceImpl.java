package com.dscomm.iecs.agent.service.impl;

import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.AgentLoginAccountLoginService;
import com.dscomm.iecs.agent.service.AuthenticateService;
import com.dscomm.iecs.agent.service.UserCacheService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.AES256Helper;
import com.dscomm.iecs.basedata.graphql.typebean.user.AccessBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.SubToken;
import com.dscomm.iecs.basedata.graphql.typebean.user.TokenBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述:验证鉴权服务
 *
 * @author YangFuxi
 * Date Time 2019/9/2 18:57
 */
@Component()
public class AuthenticateServiceImpl implements AuthenticateService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateServiceImpl.class);
    private LogService logService;
    private SessionDataStore sessionDataStore;
    private UserCacheService userCacheService;
    @Value("${cacheKeyPrefix:cad}")
    private String cacheKeyPrefix;
    @Value("${authenticate.offsettime:0}")
    private String accessOffset;
    @Value("${authenticate.needEncrypte:false}")
    private String encrypte;
    @Value("${authenticate.needValidateAccessTime:false}")
    private String needValidateAccessTime;
    private ServletService servletService;
    @Value("${sub.openSubAuthenticate:true}")
    private String openSubAuthenticate;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private AgentLoginAccountLoginService agentLoginAccountLoginService ;
    private AgentCacheService agentCacheService;


    @Autowired
    public AuthenticateServiceImpl(LogService logService,ServletService servletService,
                                   SessionDataStore sessionDataStore,
                                   UserCacheService userCacheService ,
                                   AgentLoginAccountLoginService agentLoginAccountLoginService ,
                                   AgentCacheService agentCacheService ) {
        this.logService = logService;
        this.sessionDataStore = sessionDataStore;
        this.userCacheService = userCacheService;
        this.servletService = servletService;
        this.agentLoginAccountLoginService = agentLoginAccountLoginService ;
        this.agentCacheService = agentCacheService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see #beforeAccess(HttpHeaders)
     */
    @Override
    public void beforeAccess(HttpHeaders headers) {
        String token = headers.getHeaderString("tokenkey");
        String language = headers.getHeaderString("language");
//        i18nService.setLocal(language);
        if (StringUtils.isBlank(token)) {
            logService.erorLog(logger, "service", "beforeAccess", String.format("The required field is blank," +
                    "token:%s,language:%s", token, language), null);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL_TOKEN_NULL);
        } else {
            TokenBean bo = transformEncryptedToken(token, null, null);
            sessionDataStore.setCurrentUserCode(bo.getUserAccount());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #afterAccess(HttpHeaders)
     */
    @Override
    public void afterAccess(HttpHeaders headers) {
        sessionDataStore.removeCurrentUserCode();
    }

    @Transactional( readOnly = true )
    @Override
    public TokenBean transformEncryptedToken(String token, String ip , String sessionAccount ) {
        try {
            //[访问时间],[ip],[icp类型],[用户账号],[用户密码]
            if (!StringUtils.isBlank(token)) {
                if ("true".equals(encrypte)) {
                    token = AES256Helper.decrypt(token);
                }
                String[] strs = token.split(",", -1 ); //保留空格
                Long utcTime = Long.valueOf(strs[0].trim());
                String currentIP = strs[1].trim();
                if (!StringUtils.isBlank(ip)&&StringUtils.isBlank(currentIP)) {
                    //用于兼容登陆
                    currentIP = ip.trim();
                }
                String icpType = strs[2];
                String  userAccount = null ;
                if( Strings.isNotBlank( strs[3] )){
                    userAccount = strs[3].trim();
                }

                if(Strings.isBlank( userAccount ) && Strings.isNotBlank( sessionAccount ) ){
                    userAccount = sessionAccount ;
                }
                if (("true".equals(needValidateAccessTime) && utcTime == null) || StringUtils.isBlank(currentIP)
                        || StringUtils.isBlank(userAccount) || currentIP.length() < 7) {
                    //接处警模式 0 or 1 0接警模式  1受理模式
                    if( "0".equals( icpType ) ||  "1".equals( icpType )   ){
                        String account  =  agentLoginAccountLoginService.findAgentAccount( currentIP ) ;
                        if( Strings.isNotBlank( account ) ){
                            userAccount = account ;
                        }else{
                            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
                        }
                    }else{
                        throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
                    }

                }
                sessionDataStore.setCurrentUserCode(userAccount);
                String password = null;
                if (strs.length == 5 ) {
                    if( Strings.isNotBlank( strs[4] )){
                        password = strs[4].trim();
                    }

                }
                TokenBean bo = new TokenBean();
                bo.setCurrentIP(currentIP);
                bo.setIcpType(icpType);
                bo.setUtcTime(utcTime);
                bo.setUserAccount(userAccount);
                bo.setUserPassword(password);
                if (strs.length == 8) {
                    String agentState = strs[5];
                    String agentOperateState = strs[6];
                    String agentPhoneState = strs[7];
                    bo.setAgentState(agentState);
                    bo.setAgentOperateState(agentOperateState);
                    bo.setAgentPhoneState(agentPhoneState);
                }
                if (strs.length>=9){
                    String authenticate=strs[8];
                    if ("1".equals(authenticate)){
                        bo.setNeedAuthenticate(true);
                    }
                }
                return bo;
            } else {
                logService.erorLog(logger, "service", "transformEncryptedToken", "authentication fail ,the token is null", null);
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
            }
        }
//        catch (UserInterfaceException ex) {
//            logService.erorLog(logger, "service", "transformEncryptedToken", "authentication fail ,the token is null", null);
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "transformEncryptedToken", "authentication fail", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);

        }
    }

    @Override
    public Boolean authentication(String token) {
        try {
            logService.infoLog(logger, "service", "authentication", String.format("now start authenticate... "));
            Long systemTime = servletService.getSystemTime();
            long start = System.currentTimeMillis();
            TokenBean bo = transformEncryptedToken(token, null ,null );
            sessionDataStore.setCurrentUserCode(bo.getUserAccount());
            Map<String, AccessBean> acceses = userCacheService.findAllAccess(cacheKeyPrefix);
            if (acceses == null) {
                acceses = new HashMap<>();
            }
            //判断 访问记录
            AccessBean accessBean = acceses.get(bo.getUserAccount() );
            long offset = Long.parseLong(accessOffset) * 1000l;
            if (accessBean == null) {
                //执行登录操作
                return false;
            } else {
                if ("true".equals(needValidateAccessTime)) {
                    if (   (bo.getUtcTime() + offset) >=  accessBean.getLastAccessTime()   ) {
                        logService.infoLog(logger, "service", "authentication",
                                String.format("accessTime:%s,lastAccessTime:%s", sdf.format(bo.getUtcTime()), sdf.format(accessBean.getLastAccessTime())));
//                        accessBean.setLastAccessTime(systemTime);
//                        userCacheService.modifyAccessCache(cacheKeyPrefix, "put", accessBean, true);
                    } else {
                        throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
                    }
                }
            }
            //判断用户信息
            UserInfo userInfo = null;
            Map<String, UserInfo> allOnlineUsers = userCacheService.findAllOnlineUserInfoCache(cacheKeyPrefix);
            if (allOnlineUsers != null && allOnlineUsers.size() > 0) {
                userInfo = allOnlineUsers.get(sessionDataStore.getCurrentUserCode());
            }
            if (userInfo == null) {
                return false;
            }
            //判断 坐席
            AgentBean agentBean = null;
            if( Strings.isNotBlank( userInfo.getAgentNum() ) ){
                Map<String, AgentBean> agentBeanMap = agentCacheService.findAllAgentCache(cacheKeyPrefix);
                if ( agentBeanMap != null && agentBeanMap.size() > 0 ) {
                    agentBean = agentBeanMap.get( userInfo.getAgentNum() );
                    if( agentBean == null || agentBean.getPersonBean() == null || agentBean.getOnlineSign() == 0 ){
                        return false;
                    }
                }
            }

            sessionDataStore.set("userInfo", userInfo);
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "service", "authentication", String.format("now end authenticate,the totalTime is:%sms ", end - start));

            return true;

        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "authentication", "authenticate fail.token :" + token , ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
        }
    }

    @Transactional( rollbackFor =  Exception.class )
    @Override
    public SubToken subAuthenticate(String token) {
        try {
            if ("true".equals(openSubAuthenticate)) {


                if (StringUtils.isBlank(token)) {
                    logService.erorLog(logger, "service", "subAuthenticate", "authenticate fail,token is null", null);
                    throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL_TOKEN_NULL);
                }
                token = AES256Helper.decrypt(token);
                String[] strs = token.split(",");
                if (strs.length != 3 || !isIP(strs[1]) || Long.valueOf(strs[0]) == null) {
                    logService.erorLog(logger, "service", "subAuthenticate", String.format("authenticate fail,token format is false"), null);
                    throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL_TOKEN_FORMAT_FAIL);
                } else {
                    SubToken subToken = new SubToken();
                    subToken.setAccessTime(Long.valueOf(strs[0]));
                    subToken.setClientIp(strs[1]);
                    subToken.setSystem(strs[2]);
                    Map<String, Long> allSubSystemAccessCache = userCacheService.findAllSubSystemAccessCache(cacheKeyPrefix);
                    if (allSubSystemAccessCache == null) {
                        allSubSystemAccessCache = new HashMap<>();
                    }
                    Long accessTime = allSubSystemAccessCache.get(subToken.getClientIp());
//                Long now = baseNaiveQuery.getSystemTime();
                    if (accessTime == null) {
                        userCacheService.modifySubSystemAccessCache(cacheKeyPrefix, subToken.getClientIp(), subToken.getAccessTime(), true);
                    } else {
                        //(accessTime + Long.parseLong(accessOffset) * 1000l) >= accessTime
                        if (subToken.getAccessTime() > accessTime) {
                            userCacheService.modifySubSystemAccessCache(cacheKeyPrefix, subToken.getClientIp(), subToken.getAccessTime(), true);
                        } else {
                            logService.erorLog(logger, "service", "subAuthenticate", String.format("authenticate fail,accessTime is over time "), null);
                            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
                        }
                    }
                    return subToken;
                }
            } else {
                return null;
            }
        }
//        catch (UserInterfaceException ex) {
//            throw ex;
//        }
        catch (Exception ex) {
            logService.erorLog(logger, "service", "subAuthenticate", String.format("authenticate fail"), ex);
            logService.erorLog(logger, "service", "subAuthenticate", String.format("authenticate fail"), ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.AUTHENTICATE_FAIL);
        }
    }


    /**
     * 判断是否是合法的IP地址
     */
    private boolean isIP(String ip) {
        Pattern ipPattern = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        Matcher matcher = ipPattern.matcher(ip);
        return matcher.matches();
    }
}
