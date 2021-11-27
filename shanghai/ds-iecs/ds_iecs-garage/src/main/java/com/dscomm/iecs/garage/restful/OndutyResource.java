package com.dscomm.iecs.garage.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.OnDutyService;
import com.dscomm.iecs.garage.service.bean.OnDutySummaryBean;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 描述: 值班信息
 *
 * @author YangFuxi
 * Date Time 2019/9/3 8:45
 */
@Path("iecs/v1.0/garage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OndutyResource {
    private static final Logger logger = LoggerFactory.getLogger(OndutyResource.class);
    private LogService logService;
    private OnDutyService onDutyService ;

    @Autowired
    public OndutyResource(LogService logService , OnDutyService onDutyService  ) {

        this.logService = logService;
        this.onDutyService = onDutyService ;
    }

    /**
     * 值班信息
     http://localhost:19999/iecs/v1.0/garage/findAllOrganizationOnDuty
     * @return 返回
     */
    @Path("findAllOrganizationOnDuty")
    @GET
    public DataVO<List<OnDutySummaryBean>> findAllOrganizationOnDuty( @QueryParam("organizationId") String organizationId  ) {
        try {
            logService.infoLog(logger, "restful", "findAllOrganizationOnDuty", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if(Strings.isBlank( organizationId  )){
                throw new GarageException(GarageException.GarageErrors.DATA_NULL);
            }
            List<OnDutySummaryBean> res = onDutyService.findAllOrganizationOnDuty(organizationId) ;

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "findAllOrganizationOnDuty", String.format("end login ,total time is:%s", end - start));
            return new DataVO<>(res);
        }  catch (Exception ex) {
            return new DataVO<>(new GarageException(GarageException.GarageErrors.FIND_DATA_FAIL));
        }
    }

    /**
     * 值班信息
     http://localhost:19999/iecs/v1.0/garage/findOrganizationOnDuty
     * @return 返回
     */
    @Path("findOrganizationOnDuty")
    @GET
    public DataVO<List<OnDutySummaryBean>> findOrganizationOnDuty( @QueryParam("organizationId") String organizationId  ) {
        try {
            logService.infoLog(logger, "restful", "findOrganizationOnDuty", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if(Strings.isBlank( organizationId  )){
                throw new GarageException(GarageException.GarageErrors.DATA_NULL);
            }
            List<OnDutySummaryBean> res = onDutyService.findOrganizationOnDuty(organizationId) ;

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "findOrganizationOnDuty", String.format("end login ,total time is:%s", end - start));
            return new DataVO<>(res);
        }  catch (Exception ex) {
            return new DataVO<>(new GarageException(GarageException.GarageErrors.FIND_DATA_FAIL));
        }
    }

    /**
     * 值班信息
     http://localhost:19999/iecs/v1.0/garage/findChildrenOrganizationOnDuty
     * @return 返回
     */
    @Path("findChildrenOrganizationOnDuty")
    @GET
    public DataVO<List<OnDutySummaryBean>> findChildrenOrganizationOnDuty( @QueryParam("organizationId") String organizationId  ) {
        try {
            logService.infoLog(logger, "restful", "findChildrenOrganizationOnDuty", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if(Strings.isBlank( organizationId  )){
                throw new GarageException(GarageException.GarageErrors.DATA_NULL);
            }
            List<OnDutySummaryBean> res = onDutyService.findChildrenOrganizationOnDuty(organizationId) ;

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "findChildrenOrganizationOnDuty", String.format("end login ,total time is:%s", end - start));
            return new DataVO<>(res);
        }  catch (Exception ex) {
            return new DataVO<>(new GarageException(GarageException.GarageErrors.FIND_DATA_FAIL));
        }
    }

    /**
     * 值班信息
     http://localhost:19999/iecs/v1.0/garage/findSquadronOrganizationOnDuty
     * @return 返回
     */
    @Path("findSquadronOrganizationOnDuty")
    @GET
    public DataVO<List<OnDutySummaryBean>> findSquadronOrganizationOnDuty( @QueryParam("organizationId") String organizationId  ) {
        try {
            logService.infoLog(logger, "restful", "findSquadronOrganizationOnDuty", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if(Strings.isBlank( organizationId  )){
                throw new GarageException(GarageException.GarageErrors.DATA_NULL);
            }
            List<OnDutySummaryBean> res = onDutyService.findSquadronOrganizationOnDuty(organizationId) ;

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "findSquadronOrganizationOnDuty", String.format("end login ,total time is:%s", end - start));
            return new DataVO<>(res);
        }  catch (Exception ex) {
            return new DataVO<>(new GarageException(GarageException.GarageErrors.FIND_DATA_FAIL));
        }
    }


}