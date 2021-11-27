package com.dscomm.iecs.agent.restful;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.agent.graphql.typebean.AgentBean;
import com.dscomm.iecs.agent.graphql.typebean.UserLanguage;
import com.dscomm.iecs.agent.service.AgentCacheService;
import com.dscomm.iecs.agent.service.UserCacheService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.ObtainIPUtils;
import com.dscomm.iecs.basedata.graphql.typebean.user.AccessBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.SubToken;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * 描述:缓存同步，通知其他节点服务更新缓存
 *
 * @author YangFuxi
 * Date Time 2019/9/17 9:39
 */
//@Path("rest/ecs/v3.0/notifyOtherNodes")
@Path("iecs/v1.0/notifyOtherNodes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotifyOherNodesCacheResource {
    private static final Logger logger = LoggerFactory.getLogger(NotifyOherNodesCacheResource.class);
    private LogService logService;
    private AgentCacheService agentCacheService;
    private Environment environment;
    private  UserCacheService userCacheService ;

    @Autowired
    public NotifyOherNodesCacheResource(LogService logService, AgentCacheService agentCacheService,
                                        Environment environment , UserCacheService userCacheService ) {
        this.logService = logService;
        this.agentCacheService = agentCacheService;
        this.environment = environment;
        this.userCacheService = userCacheService ;
    }

    /**
     * 访问记录缓存
     *
     * @param operation 操作
     * @param bo        数据
     * @return 结果
     */
    @Path("accessCache/{operation}")
    @POST
    public DataVO<Boolean> notifyAccessCache( @Context HttpServletRequest request, @PathParam("operation") String operation, AccessBean bo) {

        logService.infoLog(logger, "restful", "notifyAccessCache",  "notify Access Cache from ip:" +
                ObtainIPUtils.getIpAddress(request) + "to node ip:" +  ObtainIPUtils.getLocalIp() +
                "operation:" + operation + "data:" + JSONObject.toJSONString( bo ));

        userCacheService.modifyAccessCache(environment.getProperty("cacheKeyPrefix"), operation, bo, false);
        return new DataVO<>(true);
    }


    /**
     * 用户信息缓存
     *
     * @param operation 操作
     * @param userInfo  数据
     * @return 返回结果
     */
    @Path("userInfoCache/{operation}")
    @POST
    public DataVO<Boolean> notifyUserInfoCache(@Context HttpServletRequest request, @PathParam("operation") String operation, UserInfo userInfo) {

        logService.infoLog(logger, "restful", "notifyUserInfoCache",  "notify UserInfo Cache from ip:" +
                ObtainIPUtils.getIpAddress(request) + "to node ip:" +  ObtainIPUtils.getLocalIp() +
                "operation:" + operation + "data:" + JSONObject.toJSONString( userInfo ));

        userCacheService.modifyOnlineUserInfoCache(environment.getProperty("cacheKeyPrefix"), operation, userInfo, false);
        return new DataVO<>(true);
    }


    /**
     * 坐席缓存操作
     *
     * @param operation 操作
     * @param bo        数据
     * @return 返回结果
     */
    @Path("agentCache/{operation}")
    @POST
    public DataVO<Boolean> notifyAgentCache(@Context HttpServletRequest request, @PathParam("operation") String operation, AgentBean bo) {

        logService.infoLog(logger, "restful", "notifyAgentCache",  "notify Agent Cache from ip:" +
                ObtainIPUtils.getIpAddress(request) + "to node ip:" +  ObtainIPUtils.getLocalIp() +
                "operation:" + operation + "data:" + JSONObject.toJSONString( bo ));

        agentCacheService.mergeAgentCache(operation, bo, environment.getProperty("cacheKeyPrefix"), false);
        return new DataVO<>(true);
    }



    /**
     * 第三方访问记录缓存
     *
     * @param bo 数据
     * @return 返回结果
     */
    @Path("subAccessCache")
    @POST
    public DataVO<Boolean> notifySubAccessCache(@Context HttpServletRequest request, SubToken bo) {
        userCacheService.modifySubSystemAccessCache(environment.getProperty("cacheKeyPrefix"), bo.getClientIp(), bo.getAccessTime(), false);
        return new DataVO<>(true);
    }

    /**
     * 用户语言缓存
     *
     * @param bo 数据
     * @return 结果
     */
    @Path("userLanguageCache")
    @POST
    public DataVO<Boolean> notifyUserLanguageCache(@Context HttpServletRequest request, UserLanguage bo) {
       userCacheService.modifyUserLanguage(environment.getProperty("cacheKeyPrefix"), bo.getAccount(), bo.getLanguage(), false);
        return new DataVO<>(true);
    }


}
