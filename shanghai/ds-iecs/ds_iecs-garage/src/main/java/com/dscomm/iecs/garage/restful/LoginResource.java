package com.dscomm.iecs.garage.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.ServletService;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 描述:登录操作
 *
 * @author YangFuxi
 * Date Time 2019/9/3 8:45
 */
@Path("iecs/v1.0/garage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);
    private LogService logService;
    private ServletService servletService;

    @Autowired
    public LoginResource(   LogService logService , ServletService servletService ) {
        this.servletService = servletService;
        this.logService = logService;
    }

    /**
     * 测试
     http://localhost:19999/iecs/v1.0/garage/test
     * @return 返回
     */
    @Path("login")
    @GET
    public DataVO<Long> login() {
        try {
            logService.infoLog(logger, "restful", "login", "start login");
            long start = System.currentTimeMillis();
            Long res = servletService.getSystemTime() ;
            long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "login", String.format("end login ,total time is:%s", end - start));
            return new DataVO<>(res);
        }  catch (Exception ex) {
            return new DataVO<>(new GarageException(GarageException.GarageErrors.FIND_DATA_FAIL));
        }
    }







}
