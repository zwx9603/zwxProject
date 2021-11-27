package com.dscomm.iecs.accept.utils.transform;


import com.dscomm.iecs.accept.dal.po.ViolationRecordEntity;
import com.dscomm.iecs.accept.dal.po.WeatherEntity;
import com.dscomm.iecs.accept.graphql.inputbean.ViolationSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.WeatherSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ViolationRecordBean;
import com.dscomm.iecs.accept.graphql.typebean.WeatherBean;
import com.dscomm.iecs.basedata.graphql.typebean.RegionBean;
import org.apache.logging.log4j.util.Strings;
import org.mx.DigestUtils;
import org.mx.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 :  值班信息 其他 转换工具
 */
public class OndutyTransformUtil {


    private Boolean isTransformWindpower;

    /**
     * 违规记录转换
     *
     * @param source 违规记录INPUT
     * @return 违规记录PO
     */
    public static ViolationRecordEntity transform(ViolationSaveInputInfo source, Long currentSystemTime) {
        if (null != source) {
            ViolationRecordEntity target = new ViolationRecordEntity();
            if (StringUtils.isBlank(source.getId())) {
                String uuid = DigestUtils.uuid();
                uuid = uuid.replaceAll("-", "");
                target.setId(uuid);
            }
            if (Strings.isBlank(source.getBillId())) {
                target.setBillId(target.getId());
            }
            target.setViolationType(source.getViolationType());
            target.setViolationTime(currentSystemTime);
            target.setStandardValue(source.getStandardValue());
            target.setActualValue(source.getActualValue());
            target.setViolationPersonNumber(source.getViolationPersonNumber());
            target.setViolationPersonName(source.getViolationPersonName());
            target.setViolationSeatNumber(source.getViolationSeatNumber());
            target.setOrganizationId(source.getOrganizationId());
            target.setViolationDesc(source.getViolationDesc());
            target.setViolationStatus(source.getViolationStatus());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 违规记录转换
     *
     * @param source 违规记录PO
     * @return 违规记录BO
     */
    public static ViolationRecordBean transform(ViolationRecordEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            ViolationRecordBean target = new ViolationRecordBean();
            target.setId(source.getId());
            target.setBillId(source.getBillId());
            target.setViolationType(source.getViolationType());
            if (!StringUtils.isBlank(source.getViolationType())&&dicsMap.get("WGLX")!=null){
                target.setViolationTypeName(dicsMap.get("WGLX").get(source.getViolationType()));
            }
            target.setViolationTime(source.getViolationTime());
            target.setStandardValue(source.getStandardValue());
            target.setActualValue(source.getActualValue());
            target.setViolationPersonNumber(source.getViolationPersonNumber());
            target.setViolationPersonName(source.getViolationPersonName());
            target.setViolationSeatNumber(source.getViolationSeatNumber());
            target.setOrganizationId(source.getOrganizationId());
            if (!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }
            target.setViolationDesc(source.getViolationDesc());
            target.setViolationStatus(source.getViolationStatus());
            target.setRemarks(source.getRemarks());
            return target;
        }
        return null;
    }

    /**
     * 天气信息转换
     *
     * @param source 天气信息INPUT
     * @return 天气信息PO
     */
    public static WeatherEntity transform(WeatherSaveInputInfo source, Long currentSystemTime) {
        if (null != source) {
            WeatherEntity target = new WeatherEntity();
            target.setId(source.getId());
            target.setForecastTime(currentSystemTime);
            target.setForecastStartTime(source.getForecastStartTime());
            target.setForecastEndTime(source.getForecastEndTime());
            target.setOrganizationId(source.getOrganizationId());
            target.setDistrictCode(source.getDistrictCode());
            target.setMinTemperature(source.getMinTemperature());
            target.setMaxTemperature(source.getMaxTemperature());
            target.setWeatherInformationCode1(source.getWeatherInformationCode1());
            target.setWeatherInformationCode2(source.getWeatherInformationCode2());
            target.setMinWindpower(source.getMinWindpower());
            target.setMaxWindpower(source.getMaxWindpower());
            target.setWinddirection1(source.getWinddirection1());
            target.setWinddirection2(source.getWinddirection2());
            target.setPrecipitationProbability(source.getPrecipitationProbability());
            target.setMinRelativeHumidity(source.getMinRelativeHumidity());
            target.setMaxRelativeHumidity(source.getMaxRelativeHumidity());
            target.setDisasterForecast(source.getDisasterForecast());
            target.setRecordingMode(source.getRecordingMode());
            target.setRemarks(source.getRemarks());
            target.setId(source.getId());
            return target;
        }
        return null;
    }


    /**
     * 天气信息转换
     *
     * @param source 天气信息PO
     * @return 天气信息BO
     */
    public static WeatherBean transform(WeatherEntity source,
                                        Map<String, Map<String, String>> dicsMap,
                                        Map<String, String> organizationNameMap ,
                                        RegionBean  regionBean
    ) {

        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            WeatherBean target = new WeatherBean();
            target.setForecastTime(source.getForecastTime());
            target.setForecastStartTime(source.getForecastStartTime());
            target.setForecastEndTime(source.getForecastEndTime());
            target.setOrganizationId(source.getOrganizationId());
            if(!StringUtils.isBlank(source.getOrganizationId())){
                target.setOrganizationName(organizationNameMap.get(source.getOrganizationId()));
            }


            target.setDistrictCode(source.getDistrictCode());
            if (!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode() ) );
            }


            if( regionBean != null ){
                target.setLongitude( regionBean.getLongitude() );
                target.setLatitude( regionBean.getLatitude() );
                target.setRegionRange( regionBean.getRegionRange( ) );
            }

            target.setMinTemperature(source.getMinTemperature());
            target.setMaxTemperature(source.getMaxTemperature());
            target.setWeatherInformationCode1(source.getWeatherInformationCode1());
            if (!StringUtils.isBlank(source.getWeatherInformationCode1())&&dicsMap.get("TQQX")!=null){
                target.setWeatherInformationName1(dicsMap.get("TQQX").get(source.getWeatherInformationCode1()));
            }
            target.setWeatherInformationCode2(source.getWeatherInformationCode2());
            if (!StringUtils.isBlank(source.getWeatherInformationCode2())&&dicsMap.get("TQQX")!=null){
                target.setWeatherInformationName2(dicsMap.get("TQQX").get(source.getWeatherInformationCode2()));
            }
            target.setMinWindpower(source.getMinWindpower());
            if (!StringUtils.isBlank(source.getMinWindpower())&&dicsMap.get("FLDJ")!=null){
                target.setMinWindpowerName(dicsMap.get("FLDJ").get(source.getMinWindpower()));
            }
            target.setMaxWindpower(source.getMaxWindpower());
            if (!StringUtils.isBlank(source.getMaxWindpower())&&dicsMap.get("FLDJ")!=null){
                target.setMaxWindpowerName(dicsMap.get("FLDJ").get(source.getMaxWindpower()));
            }
            target.setWinddirection1(source.getWinddirection1());
            if (!StringUtils.isBlank(source.getWinddirection1())&&dicsMap.get("FX")!=null){
                target.setWinddirection1Name(dicsMap.get("FX").get(source.getWinddirection1()));
            }
            target.setWinddirection2(source.getWinddirection2());
            if (!StringUtils.isBlank(source.getWinddirection2())&&dicsMap.get("FX")!=null){
                target.setWinddirection2Name(dicsMap.get("FX").get(source.getWinddirection2()));
            }
            target.setPrecipitationProbability(source.getPrecipitationProbability());
            target.setMinRelativeHumidity(source.getMinRelativeHumidity());
            target.setMaxRelativeHumidity(source.getMaxRelativeHumidity());
            target.setDisasterForecast(source.getDisasterForecast());
            target.setRecordingMode(source.getRecordingMode());
            target.setRemarks(source.getRemarks());
            target.setId(source.getId());

            return target;
        }
        return null;
    }



}
