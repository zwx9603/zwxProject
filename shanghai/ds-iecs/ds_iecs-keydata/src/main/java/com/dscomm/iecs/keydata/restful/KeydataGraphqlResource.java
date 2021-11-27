package com.dscomm.iecs.keydata.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mx.service.rest.GraphQLBaseResource;
import org.mx.service.rest.graphql.GraphQLFactory;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.i18n.I18nService;
import org.mx.spring.session.SessionDataStore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * 描述:graphql对外调用rest服务入口
 *
 * @author YangFuxi
 * Date Time 2019/7/21 21:24
 */
@Path("iecs/v1.0/graphql/keydata")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KeydataGraphqlResource extends GraphQLBaseResource {
    private SessionDataStore sessionDataStore;
    private I18nService i18nService;

    /**
     * 构造函数
     *
     * @param factory          GraphQL工厂接口
     * @param sessionDataStore 线程作用域类
     * @param i18nService      i18n配置
     */
    @Autowired
    public KeydataGraphqlResource(GraphQLFactory factory, SessionDataStore sessionDataStore, I18nService i18nService) {
        super(factory);
        this.sessionDataStore = sessionDataStore;
        this.i18nService = i18nService;
    }

    @Path("mutation")
    @POST
    public DataVO<JSONObject> mutation(@Context HttpHeaders headers, JSON request) {
//        sessionDataStore.setCurrentUserCode("cad");
//        i18nService.setLocal(headers.getHeaderString("language"));
        i18nService.setLocal("zh_CN");
        DataVO<JSONObject> result = super.mutation(request);
//        sessionDataStore.removeCurrentUserCode();
        i18nService.setLocal(null);
        return result;
    }

    @Path("query")
    @POST
    public DataVO<JSONObject> query(@Context HttpHeaders headers, JSON request) {
//        sessionDataStore.setCurrentUserCode("cad");
//        i18nService.setLocal(headers.getHeaderString("language"));
        i18nService.setLocal("zh_CN");
        DataVO<JSONObject> result = super.query(request);
//        sessionDataStore.removeCurrentUserCode();
        i18nService.setLocal(null);
        return result;
    }

    @Path("test")
    @GET
    public DataVO<String> query() {
//        userInfoService.getUserInfo("qizhiqiang");
        return new DataVO<>("success");
    }


}
