package com.dscomm.iecs.accept.restful;

import com.dscomm.iecs.accept.utils.PrintAccessRecordUtils;
import com.dscomm.iecs.agent.exception.UserInterfaceAgentException;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.restful.vo.WebsocketTestVO;
import com.dscomm.iecs.agent.service.AgentService;
import com.dscomm.iecs.agent.service.AuthenticateService;
import com.dscomm.iecs.agent.service.UserCacheService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.SubAuditService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.AES256Helper;
import com.dscomm.iecs.base.utils.ObtainIPUtils;
import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import com.dscomm.iecs.basedata.service.OrganizationService;
import org.mx.StringUtils;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:登录操作
 *
 * @author YangFuxi
 * Date Time 2019/9/3 8:45
 */
@Path("iecs/v1.0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);
    private SessionDataStore sessionDataStore;
    private UserService userService;
    private LogService logService;
    private AuthenticateService authenticateService;
    private OrganizationService organizationService;
    private SubAuditService subAuditService;
    private NotifyActionService notifyActionService ;
    private AgentService agentService;
    private UserCacheService userCacheService;
    private Environment env;

    @Autowired
    public LoginResource(SessionDataStore sessionDataStore,
                         UserService userService,
                         LogService logService, AuthenticateService authenticateService, OrganizationService organizationService,
                         SubAuditService subAuditService, NotifyActionService notifyActionService, AgentService agentService, UserCacheService userCacheService, Environment env) {
        this.sessionDataStore = sessionDataStore;
        this.userService = userService;
        this.logService = logService;
        this.authenticateService = authenticateService;
        this.organizationService = organizationService;
        this.subAuditService = subAuditService;
        this.notifyActionService = notifyActionService;
        this.agentService = agentService;
        this.userCacheService = userCacheService;
        this.env = env;
    }

    /**
     * 获取机机认证key
     *
     * @return 返回key
     */
    @Path("getkey")
    @GET
    public DataVO<String> getKey() {
        try {
            String key = StringUtils.byte2HexString(AES256Helper.keyByte);
            return new DataVO<>(key);
        } catch (Exception ex) {
            logService.erorLog(logger, "resource", "getKey", "get key fail.", ex);
            throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.GET_KEY_FAIL);
        }
    }


    /**
     *  获得 session 账号
     *
     * @param request 请求对象
     * @return 返回session 账号
     */
    @Path("getSessionAccount")
    @GET
    public DataVO<String> getSessionAccount(@Context HttpServletRequest request) {
        try {

            logService.infoLog(logger, "rest", "getSessionAccount", "start login");
            long start = System.currentTimeMillis();

            org.jasig.cas.client.validation.Assertion assertion =
                    (org.jasig.cas.client.validation.Assertion) request.getSession().getAttribute(org.jasig.cas.client.util.AbstractCasFilter.CONST_CAS_ASSERTION);
            String sessionAccount = null ;
            if( assertion != null ){
                org.jasig.cas.client.authentication.AttributePrincipal principal = assertion.getPrincipal();
                sessionAccount  = principal.getName();   //登录用户账号（系统名称）
            }
            logService.infoLog(logger, "rest", "getSessionAccount", String.format("sessionAccount is:%s", sessionAccount));

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "getSessionAccount", String.format("end login ,total time is:%s", end - start));

            return new DataVO<>(sessionAccount);
        } catch (UserInterfaceAgentException ex) {
            return new DataVO<>(ex);
        } catch (Exception ex) {
            return new DataVO<>(new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_INIT_FAIL));
        }
    }



    /**
     * 登录操作( 用户信息)
     *
     * @param request 请求对象
     * @return 返回用户信息
     */
    @Path("login")
    @GET
    public DataVO<UserInfo> login(@Context HttpServletRequest request) {
        String token =null;
        try {

            logService.infoLog(logger, "rest", "login", "start login");
            long start = System.currentTimeMillis();
            token = request.getHeader("tokenkey");
            String language = request.getHeader("language");
//            i18nService.setLocal(language);
            String ip = ObtainIPUtils.getIpAddress(request);
            if (StringUtils.isBlank(token) || StringUtils.isBlank(ip)) {
                logService.erorLog(logger, "resource", "login", String.format("login fail,token:%s", token), null);
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
            }
//            i18nService.setLocal(language);

            org.jasig.cas.client.validation.Assertion assertion =
                    (org.jasig.cas.client.validation.Assertion) request.getSession().getAttribute(org.jasig.cas.client.util.AbstractCasFilter.CONST_CAS_ASSERTION);
            String sessionAccount = null ;
            if( assertion != null ){
                org.jasig.cas.client.authentication.AttributePrincipal principal = assertion.getPrincipal();
                sessionAccount  = principal.getName();   //登录用户账号（系统名称）
            }
            logService.infoLog(logger, "rest", "login", String.format("sessionAccount is:%s", sessionAccount));

            UserInfo userInfo = userService.login(token, ip , sessionAccount,true);
            long end = System.currentTimeMillis();

            logService.infoLog(logger, "rest", "login", String.format("end login ,total time is:%s", end - start));
            sessionDataStore.clean();
            PrintAccessRecordUtils.printRequest("login",token,null,start);
            return new DataVO<>(userInfo);
        } catch (UserInterfaceAgentException ex) {
            PrintAccessRecordUtils.printRequestFailRecord("login",token,null,ex);
            return new DataVO<>(ex);
        } catch (Exception ex) {
            PrintAccessRecordUtils.printRequestFailRecord("login",token,null,ex);
            return new DataVO<>(new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.LOGIN_INIT_FAIL));
        }
    }

    /**
     * 心跳操作
     *
     * @param headers 请求头
     * @return 返回心跳结果
     */
    @Path("heart")
    @GET
    public DataVO<Boolean> heart(  @Context HttpHeaders headers) {
        String token =null;
        try {
            logService.infoLog(logger, "rest", "heart", "start heart");
            long start = System.currentTimeMillis();
            token = headers.getHeaderString("tokenkey");
            String language = headers.getHeaderString("language");
//            i18nService.setLocal(language);

            logService.infoLog(logger, "rest", "login",  "send heart" +
                       String.format("heart,token:%s", token) );

            if (StringUtils.isBlank(token)) {
                logService.erorLog(logger, "rest", "login", String.format("login fail,token:%s", token), null);
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
            }
            Boolean result = userService.heart(token);
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "heart", String.format("end heart ,total time is:%s", end - start));
            sessionDataStore.clean();
            PrintAccessRecordUtils.printRequest("heart",token,null,start);
            return new DataVO<>(result);
        } catch (UserInterfaceAgentException ex) {
            logService.erorLog(logger, "rest", "heart", String.format("heart fail " ), ex);
            PrintAccessRecordUtils.printRequestFailRecord("heart",token,null,ex);
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logService.erorLog(logger, "rest", "heart", String.format("heart fail " ), ex);
            PrintAccessRecordUtils.printRequestFailRecord("heart",token,null,ex);
            return new DataVO<>(new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.OTHER_FAIL));
        }
    }

    /**
     * 登陆推出操作
     *
     * @param headers 请求头
     * @return 返回结果
     */
    @Path("logout")
    @GET
    public DataVO<Boolean> logout(@Context HttpHeaders headers) {
        String token =null;
        try {
            logService.infoLog(logger, "rest", "logout", "start logout");
            long start = System.currentTimeMillis();
            token = headers.getHeaderString("tokenkey");
            String language = headers.getHeaderString("language");

            logService.infoLog(logger, "resource", "logout",  "send logout" +
                    String.format("heart,token:%s", token) );


            if (StringUtils.isBlank(token)) {
                logService.erorLog(logger, "resource", "logout", String.format("login fail,token:%s", token), null);
                throw new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.DATA_NULL);
            }
//            i18nService.setLocal(language);
            Boolean res = userService.logout(token);
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "rest", "logout", String.format("end logout ,total time is:%s", end - start));
            sessionDataStore.clean();
            PrintAccessRecordUtils.printRequest("logout",token,null,start);
            return new DataVO<>(res);
        } catch (UserInterfaceAgentException ex) {
            logService.erorLog(logger, "rest", "logout", String.format("logout fail " ), ex);
            PrintAccessRecordUtils.printRequestFailRecord("logout",token,null,ex);
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logService.erorLog(logger, "rest", "logout", String.format("logout fail " ), ex);
            PrintAccessRecordUtils.printRequestFailRecord("logout",token,null,ex);
            return new DataVO<>(new UserInterfaceAgentException(UserInterfaceAgentException.AgentErrors.OTHER_FAIL));
        }
    }


    @Path("test/deleteAgent")
    @GET
    public DataVO<Boolean > deleteAgent() {
        try {
            agentService.deleteAgent();
            return new DataVO<>(true);
        } catch (Exception ex) {
            return new DataVO<>(false);
        }
    }

    @Path("test/getIp")
    @GET
    public DataVO<String> getIp(@Context HttpServletRequest request) {
        String ip = ObtainIPUtils.getIpAddress(request);
        return new DataVO<>(ip);
    }

    @Path("test/testSendWebsocket")
    @POST
    public DataVO<String> testWebsocket(WebsocketTestVO message) {
        notifyActionService.pushMessageToUsers(message.getCode(), message.getBody(), message.getReceivers());
        return new DataVO<>("success");
    }

    @Path("test/getAllAgents")
    @GET
    public DataVO<List<AgentBean>> getAllAgents() {
        List<AgentBean> agents = agentService.findAllAgent();
        return new DataVO<>(agents);
    }

    @Path("testLog")
    @GET
    public DataVO<String> testLog() {
        UserInfo userInfo = userService.getUserInfo();
        subAuditService.buildSubAuditLog(userInfo.getAccount(),userInfo.getOrgId(),userInfo.getOrgCode(),
                userInfo.getOrgName(),"Test", "Ok", "测试第三方审计日志");
        return new DataVO<>("success");
    }


    @Path("test/getServerIp")
    @GET
    public DataVO< String > getServerIp() {
        try {
            return new DataVO<>(ObtainIPUtils.getLocalIp());
        } catch (Exception ex) {
            return new DataVO<>();
        }
    }

    @Path("test/getServerIps")
    @GET
    public DataVO< List<String> > getServerIps() {
        try {
            return new DataVO<>(ObtainIPUtils.getLocalIpList());
        } catch (Exception ex) {
            return new DataVO<>();
        }
    }


    @Path("test/getServerIpAndPortList")
    @GET
    public DataVO<List<String>> getServerIpAndPortList() {
        try {
            return new DataVO<>(ObtainIPUtils.getLocalTomcatIpAndPortList());
        } catch (Exception ex) {
            return new DataVO<>();
        }
    }

    @Path("test/getAllUsers")
    @GET
    public DataVO<List<UserInfo>> getAllUsers() {
        try {
            return new DataVO<>(new ArrayList<>(userCacheService.findAllUserInfoCache(env.getProperty("cacheKeyPrefix")).values()));
        } catch (Exception ex) {
            return new DataVO<>();
        }
    }

    @Path("getAllOrgs")
    @GET
    public DataVO<Map<String,OrganizationBean>> findAllOrganization(){
        return new DataVO<>(organizationService.findOrganizationAll());
    }

    @Path("getRootOrg")
    @GET
    public DataVO<OrganizationBean> getRootOrg(){
        return new DataVO<>(organizationService.getRootOrg());
    }
    @Path("getOrgTree")
    @GET
    public DataVO<List<OrganizationBean>> getOrgTree(){
        return new DataVO<>(organizationService.findTreeOrganization());
    }
}
