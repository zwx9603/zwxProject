package com.dscomm.iecs.garage.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.garage.dal.repository.WeatherRepository;
import com.dscomm.iecs.garage.exception.GarageException;
import com.dscomm.iecs.garage.service.ServletService;
import com.dscomm.iecs.garage.service.WeatherService;
import com.dscomm.iecs.garage.service.bean.WeatherBean;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * 描述：天气 服务类实现
 */
@Component("weatherServiceImpl")
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private Environment env;
    private LogService logService;
    private ServletService servletService;
    private WeatherRepository weatherRepository ;
    /**
     * 默认的构造函数
     */
    @Autowired
    public WeatherServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor ,
                              ServletService servletService ,WeatherRepository weatherRepository
    ) {
        this.env = env;
        this.logService = logService;
        this.servletService = servletService ;
        this.weatherRepository = weatherRepository ;
    }

    /**
     * {@inheritDoc}
     *
     * @see WeatherService#findWeather(String)
     */
    @Transactional(readOnly = true)
    @Override
    public WeatherBean findWeather(String districtCode) {
        if ( Strings.isBlank(districtCode)) {
            logService.infoLog(logger, "service", "findWeather", " districtCode is blank.");
            throw new GarageException(GarageException.GarageErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "findWeather", "service is started...");
            Long logStart = System.currentTimeMillis();

            WeatherBean res = null ;
            String districtCodeSearch = districtCode+"00000";

            logService.infoLog(logger, "repository", "findWeatherDistrictCode(districtCode)", "repository is started...");
            Long start = System.currentTimeMillis();
            Date currentTime =  servletService.getSystemTimeFormat() ;
            List<Object[]> weatherEntityList  = weatherRepository.findWeatherDistrictCode(districtCodeSearch , currentTime  );

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findWeatherDistrictCode(districtCode)", String.format("repository is finished,execute time is :%sms", end - start));

            if( null != weatherEntityList && weatherEntityList.size() > 0 ){
                Object []  weatherEntity = weatherEntityList.get(0) ;
                res = transform(weatherEntity );
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findWeather", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findWeather", "execution error", ex);
            throw new GarageException(GarageException.GarageErrors.FIND_DATA_FAIL);
        }

    }

    /**
     * 天气信息转换
     *
     * @param source 天气信息PO
     * @return 天气信息BO
     */
    public  WeatherBean transform( Object [] source ) {
        if (null != source) {
            WeatherBean target = new WeatherBean();
            target.setId( toString(source[0] ) );
            target.setForecastTime( toDateLong( source[1])    );
            target.setForecastStartTime( toDateLong( source[2])   );
            target.setForecastEndTime( toDateLong( source[3])   );
            target.setOrganizationId( toString(source[4] ) );
            target.setOrganizationName(toString(source[5] ));
            target.setDistrictCode( toString(source[6] ));
            target.setDistrictName( toString(source[7] ));
            target.setMinTemperature( toFloat(source[8] ));
            target.setMaxTemperature(toFloat(source[9] ));
            target.setWeatherInformationCode1( toString(source[10] ));
            target.setWeatherInformationName1( toString(source[11] ));
            target.setWeatherInformationCode2( toString(source[12] ));
            target.setWeatherInformationName2(toString(source[13] ));
            target.setMinWindpower(toString(source[14] ));
            target.setMinWindpowerName(  toString(source[15] )  );
            target.setMaxWindpower(toString(source[16] ));
            target.setMaxWindpowerName(toString(source[17] ) );
            target.setWinddirection1( toString(source[18] ) );
            target.setWinddirection1Name( toString(source[19] )  );
            target.setWinddirection2( toString(source[20] ) );
            target.setWinddirection2Name( toString(source[21] ) );
            target.setPrecipitationProbability( toString(source[22] ));
            target.setMinRelativeHumidity( toString(source[23] ) );
            target.setMaxRelativeHumidity( toString(source[24] ) );
            target.setDisasterForecast( toString(source[25] ) );
            target.setRemarks( toString(source[26] ) );
            return target;
        }
        return null;
    }

    /**
     * 转换为string类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private String toString(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * 转换为float类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Float  toFloat(Object obj) {
        return (obj == null) ? null  : Float.valueOf( obj.toString() );
    }

    /**
     * 转换为Date类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Date toDate( Object obj ) {
        return (obj == null) ? null : (Timestamp)obj ;
    }

    /**
     * 转换为Date long类
     *
     * @param obj 参数
     * @return 返回字符串
     */
    private Long toDateLong( Object obj ) {
        return (obj == null) ? null : (  (Timestamp)obj  ).getTime()  ;
    }


}
