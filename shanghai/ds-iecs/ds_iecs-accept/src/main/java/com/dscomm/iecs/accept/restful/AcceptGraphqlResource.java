package com.dscomm.iecs.accept.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.utils.PrintAccessRecordUtils;
import com.dscomm.iecs.agent.service.AuthenticateService;
import com.dscomm.iecs.agent.service.UserService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.error.UserInterfaceException;
import org.mx.service.rest.GraphQLBaseResource;
import org.mx.service.rest.graphql.GraphQLFactory;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;

/**
 * 描述:接警受理模块graphql服务rest入口
 *
 * @author YangFuxi
 * Date Time 2020/4/9 12:26
 */
@Path("iecs/v1.0/graphql")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcceptGraphqlResource extends GraphQLBaseResource {
    private static final Logger logger = LoggerFactory.getLogger(AcceptGraphqlResource.class);
    private SessionDataStore sessionDataStore;
    private ServletService servletService;
    private AuthenticateService authenticateService;
    private UserService userService;
    private Environment env;


    @Autowired
    public AcceptGraphqlResource(GraphQLFactory factory, SessionDataStore sessionDataStore, ServletService servletService,
                                 AuthenticateService authenticateService, UserService userService, Environment env) {
        super(factory);
        this.sessionDataStore = sessionDataStore;
        this.servletService = servletService;
        this.authenticateService = authenticateService;
        this.userService = userService;
        this.env = env;
    }

    /**
     * graphql增删改服务入口
     *
     * @param headers 请求头参数
     * @param request 请求体
     * @return 返回结果
     */
    @POST
    @Path("mutation")
    public DataVO<JSONObject> mutation(@Context HttpHeaders headers, JSON request) {
        String tokenKey=null;
        try {
            Long start=System.currentTimeMillis();
            tokenKey=headers.getHeaderString("tokenkey");
            String token = prepareRequest(headers);
            DataVO<JSONObject> result = super.mutation(request);
            PrintAccessRecordUtils.printRequest("mutation",token,request,start);
            return result;
        } catch (UserInterfaceException ex) {
            logger.error("visit fail", ex);
            PrintAccessRecordUtils.printRequestFailRecord("mutation",tokenKey,request,ex);
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logger.error("visit mutation fail", ex);
            PrintAccessRecordUtils.printRequestFailRecord("mutation",tokenKey,request,ex);
            return new DataVO<>(new AcceptException());
        }
    }

    /**
     * graphql查询服务入口
     *
     * @param headers 请求头参数
     * @param request 请求体
     * @return 返回结果
     */
    @POST
    @Path("query")
    public DataVO<JSONObject> query(@Context HttpHeaders headers, JSON request) {
        String tokenKey=null;
        try {
            Long start=System.currentTimeMillis();
            tokenKey=headers.getHeaderString("tokenkey");
            String token=prepareRequest(headers);
            DataVO<JSONObject> result = super.query(request);
            PrintAccessRecordUtils.printRequest("query",token,request,start);
            return result;
        } catch (UserInterfaceException ex) {
            PrintAccessRecordUtils.printRequestFailRecord("query",tokenKey,request,ex);
            logger.error("visit query fail", ex);
            return new DataVO<>(ex);
        } catch (Exception ex) {
            logger.error("visit query fail", ex);
            PrintAccessRecordUtils.printRequestFailRecord("query",tokenKey,request,ex);
            return new DataVO<>(new AcceptException());
        }
    }

    /**
     * 预处理方法
     */
    private String prepareRequest(HttpHeaders headers) {
        sessionDataStore.set("currentSystemTime", servletService.getSystemTime());
//        i18nService.setLocal(headers.getHeaderString("language"));
        String token = headers.getHeaderString("tokenkey");
        Boolean res = authenticateService.authentication(token);
        if (!res) {
            userService.login(token, null, null,false);
        }
        return token;
    }
}
