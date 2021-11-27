package com.dscomm.iecs.basedata.restful;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mx.dbcp.Dbcp2DataSourceFactory;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *  basedata servlet  接口
 */
@Path("iecs/v1.0/servlet")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  ServletResource {

    private static final Logger logger = LoggerFactory.getLogger(ServletResource.class);
    private LogService logService;
    private Dbcp2DataSourceFactory dbcp2DataSourceFactory ;
    private ServletService servletService ;

    @Autowired
    public ServletResource( LogService logService , Dbcp2DataSourceFactory  dbcp2DataSourceFactory , ServletService servletService

    ) {
        this.logService = logService;
        this.dbcp2DataSourceFactory = dbcp2DataSourceFactory ;
        this.servletService = servletService ;
    }


    /**
     *  获得数据库信息
     *
     * @param type
     * @return 返回结果
     */
    @Path("getDataSource")
    @GET
    public DataVO<JSONObject> findDictionaryByGrid(@Context HttpHeaders headers, @QueryParam("type") String type) {
        logService.infoLog(logger, "restful", "findDictionaryByGrid", "restful is started...");
        Long start = System.currentTimeMillis();

        BasicDataSource dataSource = ( BasicDataSource ) dbcp2DataSourceFactory.getDataSource();
        JSONObject res = new JSONObject();
        res.put("tatalNum",  dataSource.getMaxTotal() );
        res.put("activeNum", dataSource.getNumActive());
        res.put("idleNum", dataSource.getNumIdle());
        res.put("currentTime", servletService.getSystemTime() );

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "findDictionaryByGrid", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res);
    }





}
