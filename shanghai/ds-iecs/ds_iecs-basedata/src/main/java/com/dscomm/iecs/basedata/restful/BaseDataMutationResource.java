package com.dscomm.iecs.basedata.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.mx.spring.session.SessionDataStore;
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
 *  basedata restful mutation  接口
 */
@Path("iecs/v1.0/mutation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BaseDataMutationResource {

    private static final Logger logger = LoggerFactory.getLogger(BaseDataMutationResource.class);
    private LogService logService;
    private VehicleService vehicleService;
    private SessionDataStore sessionDataStore;
    private ServletService servletService ;


    @Autowired
    public BaseDataMutationResource(LogService logService, VehicleService vehicleService,
                                    SessionDataStore sessionDataStore ,  ServletService servletService ) {
        this.logService = logService;
        this.vehicleService = vehicleService;
        this.sessionDataStore = sessionDataStore ;
        this.servletService = servletService ;
    }


    /**
     * 修改车辆拓展信息
     *
     * @param inputInfo EquipmentVehicleExpandInputInfo
     * @return 返回结果
     */
    @Path("updateVehicleExpandInfo")
    @POST
    public DataVO<EquipmentVehicleBean> updateVehicleExpandInfo(@Context HttpHeaders headers, EquipmentVehicleExpandInputInfo inputInfo) {
        logService.infoLog(logger, "restful", "updateVehicleExpandInfo", "restful is started...");
        Long start = System.currentTimeMillis();

        sessionDataStore.set("currentSystemTime", servletService.getSystemTime() ) ;

        //参数判断
        if (inputInfo == null || Strings.isBlank(inputInfo.getId())) {
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        //执行逻辑处理
        EquipmentVehicleBean res = vehicleService.updateVehicleExpandInfo(inputInfo);

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "updateVehicleExpandInfo", String.format("restful is finished,execute time is :%sms", end - start));
        return new DataVO<>(res) ;
    }




}
