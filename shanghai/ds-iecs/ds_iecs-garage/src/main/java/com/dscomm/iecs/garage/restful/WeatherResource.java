package com.dscomm.iecs.garage.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.WeatherService;
import com.dscomm.iecs.garage.service.bean.WeatherBean;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 描述: 天气信息
 *
 * @author YangFuxi
 * Date Time 2019/9/3 8:45
 */
@Path("iecs/v1.0/garage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {
    private static final Logger logger = LoggerFactory.getLogger(WeatherResource.class);
    private LogService logService;

    private WeatherService weatherService ;

    @Autowired
    public WeatherResource(   LogService logService ,  WeatherService weatherService ) {

        this.logService = logService;
        this.weatherService = weatherService ;
    }

    /**
     * 天气信息
     http://localhost:19999/iecs/v1.0/garage/findWeather
     * @return 返回
     */
    @Path("findWeather")
    @GET
    public DataVO<WeatherBean> weather( @QueryParam("districtCode") String districtCode  ) {
        try {
            logService.infoLog(logger, "restful", "weather", "start login");
            long start = System.currentTimeMillis();

            //参数判断
            if(Strings.isBlank( districtCode  )){
                throw new GarageException(GarageException.GarageErrors.DATA_NULL);
            }
            WeatherBean res = weatherService.findWeather(districtCode) ;

            long end = System.currentTimeMillis();
            logService.infoLog(logger, "restful", "weather", String.format("end login ,total time is:%s", end - start));
            return new DataVO<>(res);
        }  catch (Exception ex) {
            return new DataVO<>(new GarageException(GarageException.GarageErrors.FIND_DATA_FAIL));
        }
    }


}