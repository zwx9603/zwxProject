package com.dscomm.iecs.out.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.out.dal.po.QueryAuditEntity;
import com.dscomm.iecs.out.graphql.inputinfo.QueryInputInfo;
import com.dscomm.iecs.out.graphql.typebean.CountBean;
import com.dscomm.iecs.out.service.AuditLogOutService;
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
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/22 19:06
 * @Describe
 */

@Path("v1/count")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountResource {

    private static final Logger logger = LoggerFactory.getLogger(CountResource.class);
    private LogService logService;
    private AuditLogOutService auditLogOutService;

    @Autowired
    public CountResource(LogService logService,
                         AuditLogOutService auditLogOutService){
        this.logService = logService;
        this.auditLogOutService = auditLogOutService;
    }


    /**
     * 列表展示
     * @param headers
     * @param info
     * @return
     */
    @Path("findInterfaceAuditByParams")
    @POST
    public DataVO<List<QueryAuditEntity>> getCountList(@Context HttpHeaders headers,
                                                       QueryInputInfo info){

        logService.infoLog(logger, "restful", "getCountList", "restful is started...");
        Long start = System.currentTimeMillis();

        List<QueryAuditEntity> res = auditLogOutService.findInterfaceAuditByParams(info);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "getCountList", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }


    /**
     * 统计数
     * @param headers
     * @param info
     * @return
     */
    @Path("countByParams")
    @POST
    public DataVO<CountBean> countByParams(@Context HttpHeaders headers,
                                          QueryInputInfo info){

        logService.infoLog(logger, "restful", "countByParams", "restful is started...");
        Long start = System.currentTimeMillis();

        CountBean res = auditLogOutService.countPostByParams(info);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "countByParams", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);

    }

}
