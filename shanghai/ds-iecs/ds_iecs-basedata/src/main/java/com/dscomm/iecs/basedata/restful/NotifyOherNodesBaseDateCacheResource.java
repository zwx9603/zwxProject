package com.dscomm.iecs.basedata.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.BaseDateCacheService;
import com.dscomm.iecs.basedata.service.SystemConfigurationService;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
public class NotifyOherNodesBaseDateCacheResource {
    private static final Logger logger = LoggerFactory.getLogger(NotifyOherNodesBaseDateCacheResource.class);
    private LogService logService;
    private BaseDateCacheService baseDateCacheService;
    private  SystemConfigurationService systemConfigurationService ;
    private Environment environment;

    @Autowired
    public NotifyOherNodesBaseDateCacheResource(LogService logService, BaseDateCacheService baseDateCacheService , Environment environment ,
                                                SystemConfigurationService systemConfigurationService ) {
        this.logService = logService;
        this.baseDateCacheService = baseDateCacheService;
        this.environment = environment ;
        this.systemConfigurationService = systemConfigurationService ;
    }




    /**
     * 基础数据 缓存
     *
     * @return 返回结果
     */
    @Path("baseDate/cache")
    @POST
    public DataVO<Boolean> notifyAgentCache( ) {
        baseDateCacheService.updateBaseDateCache(   );
        return new DataVO<>(true);
    }


    /**
     * 基础数据 缓存
     *
     * @return 返回结果
     */
    @Path("baseDate/cache/nokey")
    @POST
    public DataVO<Boolean> notifyAgentNokeyCache( ) {
        baseDateCacheService.updateBaseDateNoKeyCache(   );
        return new DataVO<>(true);
    }



}
