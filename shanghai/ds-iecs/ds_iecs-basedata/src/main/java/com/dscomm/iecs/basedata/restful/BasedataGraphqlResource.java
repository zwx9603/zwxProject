package com.dscomm.iecs.basedata.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.basedata.exception.BasedataException;
import org.mx.error.UserInterfaceException;
import org.mx.service.rest.GraphQLBaseResource;
import org.mx.service.rest.graphql.GraphQLFactory;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * 描述:graphql对外调用rest服务入口
 *
 */
@Path("iecs/v1.0/graphql/basedata")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BasedataGraphqlResource extends GraphQLBaseResource {

    private static final Logger logger = LoggerFactory.getLogger(BasedataGraphqlResource.class);
    /**
     * 构造函数
     *
     */
    @Autowired
    public BasedataGraphqlResource( GraphQLFactory factory) {
        super(factory);
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
        try {
            DataVO<JSONObject> result = super.mutation(request);
            return result;
        } catch (UserInterfaceException ex) {
            logger.error("visit fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex){
            logger.error("visit mutation fail",ex);
            return new DataVO<>(new BasedataException());
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
        try {
            DataVO<JSONObject> result = super.query(request);
            return result;
        }catch (UserInterfaceException ex){
            logger.error("visit query fail",ex);
            return new DataVO<>(ex);
        }catch (Exception ex) {
            logger.error("visit query fail",ex);
            return new DataVO<>(new BasedataException());
        }
    }


}
