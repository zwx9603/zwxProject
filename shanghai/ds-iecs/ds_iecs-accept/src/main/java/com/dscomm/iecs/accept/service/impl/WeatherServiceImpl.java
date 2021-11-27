package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.WeatherEntity;
import com.dscomm.iecs.accept.dal.repository.AcceptNativeQueryRepository;
import com.dscomm.iecs.accept.dal.repository.WeatherRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.WeatherSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.WeatherBean;
import com.dscomm.iecs.accept.service.WeatherService;
import com.dscomm.iecs.accept.utils.WeatherParamsUtil;
import com.dscomm.iecs.accept.utils.transform.OndutyTransformUtil;
import com.dscomm.iecs.accept.utils.transform.WeatherTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.graphql.typebean.RegionBean;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.basedata.service.RegionService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * 描述：天气 服务类实现
 */
@Component("weatherServiceImpl")
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private WeatherRepository weatherRepository;
    private DictionaryService dictionaryService;
    private OrganizationService organizationService;
    private ServletService servletService;
    private AcceptNativeQueryRepository acceptNativeQueryRepository ;
    private RegionService regionService ;

    private List<String> dics;

    /**
     * 默认的构造函数
     */
    @Autowired
    public WeatherServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor, OrganizationService organizationService,
                              Environment env, WeatherRepository weatherRepository, DictionaryService dictionaryService, ServletService servletService ,
                              AcceptNativeQueryRepository acceptNativeQueryRepository , RegionService regionService
    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.weatherRepository = weatherRepository;
        this.dictionaryService = dictionaryService;
        this.organizationService = organizationService;
        this.servletService = servletService;
        this.acceptNativeQueryRepository = acceptNativeQueryRepository ;
        this.regionService = regionService ;

        dics = new ArrayList<>(Arrays.asList("XZQX", "TQQX", "FX", "FLDJ"));
    }

    /**
     * {@inheritDoc}
     *
     * @see WeatherService#findWeather(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<WeatherBean> findWeather(String districtCode) {
        try {
            logService.infoLog(logger, "service", "findWeather", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<WeatherBean> res = new ArrayList<>();

            Long currentTime = servletService.getSystemTime();

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);
            Map<String, RegionBean> regionMap = regionService.findRegionMap(  null ) ;
            Boolean isTransformWindPower = Boolean.parseBoolean(env.getProperty("isTransformWindPower"));
            logService.infoLog(logger, "repository", "findWeatherDistrictCode(districtCode)", "repository is started...");
            Long start = System.currentTimeMillis();
            List<WeatherEntity> weatherEntityList = new ArrayList<>();
            if (Strings.isBlank(districtCode)) { //默认获取所有辖区天气
                Set<String> xzqx = dicsMap.get("XZQX").keySet();
                List<Object[]> weather = acceptNativeQueryRepository.findWeather(new ArrayList<>(xzqx), currentTime);
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findWeather()", String.format("repository is finished,execute time is :%sms", end - start));
                if (CollectionUtils.isEmpty(weather)) {
                    return null;
                }
                for (Object[] objects : weather) {
                    WeatherEntity weatherEntity = WeatherTransformUtil.transform(objects);
                    RegionBean regionBean=null;
                    if (!StringUtils.isBlank(weatherEntity.getDistrictCode())){
                        regionBean = regionMap.get(weatherEntity.getDistrictCode());
                    }
                    WeatherBean transform = OndutyTransformUtil.transform(weatherEntity, dicsMap, organizationService.findOrganizationNameMap() , regionBean );
                    if (isTransformWindPower) {
                        List<String> windPowerInfo = WeatherParamsUtil.windPower(transform.getMaxWindpower());
                        transform.setWindPowerLevelInfo(windPowerInfo);
                        res.add(transform);
                    }
                }


            } else {
                weatherEntityList = weatherRepository.findWeatherDistrictCode(districtCode, currentTime);
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findWeatherDistrictCode(districtCode)", String.format("repository is finished,execute time is :%sms", end - start));

                if (CollectionUtils.isEmpty(weatherEntityList)) {
                    return null;
                }

                WeatherEntity weatherEntity = weatherEntityList.get(0);
                RegionBean regionBean=null;
                if (!StringUtils.isBlank(weatherEntity.getDistrictCode())){
                    regionBean = regionMap.get(districtCode);
                }
                WeatherBean transform = OndutyTransformUtil.transform(weatherEntity, dicsMap, organizationService.findOrganizationNameMap() , regionBean);
                // 是否翻译 气象风力等级
                if (isTransformWindPower) {
                    List<String> windPowerInfo = WeatherParamsUtil.windPower(transform.getMaxWindpower());
                    transform.setWindPowerLevelInfo(windPowerInfo);
                }
                res.add(transform);
            }


            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findWeather", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findWeather", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_WEATHER_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see WeatherService#saveWeather(WeatherSaveInputInfo)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WeatherBean saveWeather(WeatherSaveInputInfo queryBean) {
        if (null == queryBean) {
            logService.infoLog(logger, "service", "saveWeather", " WeatherSaveInputInfo is blank.");
            throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
        }
        try {
            logService.infoLog(logger, "service", "saveWeather", "service is started...");
            Long logStart = System.currentTimeMillis();

            Map<String, Map<String, String>> dicsMap = dictionaryService.findDictionaryMap(dics);

            WeatherEntity weatherEntity = OndutyTransformUtil.transform(queryBean, servletService.getSystemTime());

            logService.infoLog(logger, "repository", "save(dbWeatherEntity)", "repository is started...");
            Long saveStart = System.currentTimeMillis();

            accessor.save(weatherEntity);

            Long saveEnd = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "save(dbWeatherEntity)", String.format("repository is finished,execute time is :%sms", saveEnd - saveStart));

            Map<String, RegionBean> regionMap = regionService.findRegionMap(  null ) ;
            WeatherBean res = OndutyTransformUtil.transform(weatherEntity, dicsMap, organizationService.findOrganizationNameMap() , regionMap.get( weatherEntity.getDistrictCode() ));
            // 是否翻译 气象风力等级
            Boolean isTransformWindPower = Boolean.parseBoolean(env.getProperty("isTransformWindPower"));
            if (isTransformWindPower) {
                List<String> windPowerInfo = WeatherParamsUtil.windPower(res.getMaxWindpower());
                res.setWindPowerLevelInfo(windPowerInfo);
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "saveWeather", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return res;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "saveWeather", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.SAVE_WEATHER_FAIL);
        }

    }


}
