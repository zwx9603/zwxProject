package com.dscomm.iecs.accept.utils.transform;


import com.dscomm.iecs.accept.dal.po.NewsCircularEntity;
import com.dscomm.iecs.accept.dal.po.NewsCircularReceiverEntity;
import com.dscomm.iecs.accept.dal.po.ViolationRecordEntity;
import com.dscomm.iecs.accept.dal.po.WeatherEntity;
import com.dscomm.iecs.accept.graphql.inputbean.NewsCircularReceiverSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.NewsCircularSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ViolationSaveInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.WeatherSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.NewsCircularBean;
import com.dscomm.iecs.accept.graphql.typebean.NewsCircularReceiverBean;
import com.dscomm.iecs.accept.graphql.typebean.ViolationRecordBean;
import com.dscomm.iecs.accept.graphql.typebean.WeatherBean;
import com.dscomm.iecs.base.enums.EnableEnum;
import org.mx.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 : 系统配置 值班信息 其他 转换工具
 */
public class SystemTransformUtil {


    /**
     * 违规记录转换
     *
     * @param source 违规记录INPUT
     * @return 违规记录PO
     */
    public static ViolationRecordEntity transform(ViolationSaveInputInfo source ,  Long currentSystemTime ) {
        if (null != source) {
            ViolationRecordEntity target = new ViolationRecordEntity();
            target.setId(source.getId());
            target.setBillId(source.getBillId());
            target.setViolationType(source.getViolationType());
            target.setViolationTime( currentSystemTime );
            target.setStandardValue(source.getStandardValue());
            target.setActualValue(source.getActualValue());
            target.setViolationPersonNumber(source.getViolationPersonNumber());
            target.setViolationPersonName(source.getViolationPersonName());
            target.setViolationSeatNumber(source.getViolationSeatNumber());
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
    public static ViolationRecordBean transform(ViolationRecordEntity source) {
        if (null != source) {
            ViolationRecordBean target = new ViolationRecordBean();
            target.setId(source.getId());
            target.setBillId(source.getBillId());
            target.setViolationType(source.getViolationType());
            target.setViolationTime(source.getViolationTime());
            target.setStandardValue(source.getStandardValue());
            target.setActualValue(source.getActualValue());
            target.setViolationPersonNumber(source.getViolationPersonNumber());
            target.setViolationPersonName(source.getViolationPersonName());
            target.setViolationSeatNumber(source.getViolationSeatNumber());
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
    public static WeatherEntity transform(WeatherSaveInputInfo source ,  Long currentSystemTime ) {
        if (null != source) {
            WeatherEntity target = new WeatherEntity();
            target.setId(source.getId());
            target.setForecastTime( currentSystemTime );
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
    public static WeatherBean transform(WeatherEntity source, Map<String, Map<String, String>> dicsMap, Map<String, String> organizationNameMap) {
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
            if(!StringUtils.isBlank(source.getDistrictCode())&&dicsMap.get("XZQX")!=null){
                target.setDistrictName(dicsMap.get("XZQX").get(source.getDistrictCode()));
            }


            target.setMinTemperature(source.getMinTemperature());
            target.setMaxTemperature(source.getMaxTemperature());
            target.setWeatherInformationCode1(source.getWeatherInformationCode1());
            if(!StringUtils.isBlank(source.getWeatherInformationCode1())&&dicsMap.get("TQQX")!=null){
                target.setWeatherInformationName1(dicsMap.get("TQQX").get(source.getWeatherInformationCode1()));
            }


            target.setWeatherInformationCode2(source.getWeatherInformationCode2());
            if(!StringUtils.isBlank(source.getWeatherInformationCode2())&&dicsMap.get("TQQX")!=null){
                target.setWeatherInformationName2(dicsMap.get("TQQX").get(source.getWeatherInformationCode2()));
            }


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
     * 转换工具，把指令单Bean转换成指令单Entity
     *
     * @param source 参数，指令单bean
     * @return 返回转换后的结果
     */
    public static NewsCircularEntity transform(NewsCircularSaveInputInfo source   ) {
        if (null != source) {
            NewsCircularEntity target = new NewsCircularEntity();
            target.setType( source.getType() );
            target.setTitle( source.getTitle() );
            target.setContent( source.getContent() );
            target.setSource ( source.getSource()  );
            target.setRemarks( source.getRemarks() );
            return target;
        }
        return null;
    }


    /**
     * 转换工具，把指令单Bean转换成指令单Entity
     *
     * @param source 参数，指令单bean
     * @return 返回转换后的结果
     */
    public static NewsCircularBean transform(NewsCircularEntity source , Map<String, String> organizationNameMap  ) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if (null != source) {
            NewsCircularBean target = new NewsCircularBean();
            target.setId( source.getId() );
            target.setType( source.getType() );
            target.setTitle( source.getTitle() );
            target.setContent( source.getContent() );
            target.setSource ( source.getSource()  );
            target.setCircularTime( source.getCircularTime()  );
            target.setCircularPersonId ( source.getCircularPersonId() );
            target.setCircularPersonName( source.getCircularPersonName() );
            target.setCircularOrganizationId( source.getCircularOrganizationId() );
            if(!StringUtils.isBlank( target.getCircularOrganizationId())){
                target.setCircularOrganizationName( organizationNameMap.get( target.getCircularOrganizationId() ));
            }


            target.setRemarks( source.getRemarks() );
            return target;
        }
        return null;
    }


    /**
     * 转换工具，把指令单Bean转换成指令单Entity
     *
     * @param source 参数，指令单bean
     * @return 返回转换后的结果
     */
    public static NewsCircularReceiverEntity transform( NewsCircularReceiverSaveInputInfo source  , String newsCircularId  ) {
        if (null != source) {
            NewsCircularReceiverEntity target = new NewsCircularReceiverEntity();
            target.setNewsCircularId( newsCircularId );
            target.setReceiverObjectId(source.getReceiverObjectId()  );
            target.setReceivingObjectName( source.getReceivingObjectName());
            target.setReceivingObjectType( source.getReceivingObjectType() );
            target.setNewsCircularStatus(EnableEnum.ENABLE_FALSE.getCode());		//状态 0已通知 1已接收
            target.setRemarks( source.getRemarks() );
            return target;
        }
        return null;
    }




    /**
     * 转换工具，把指令单Bean转换成指令单Entity
     *
     * @param source 参数，指令单bean
     * @return 返回转换后的结果
     */
    public static NewsCircularReceiverBean  transform( NewsCircularReceiverEntity  source    ) {
        if (null != source) {
            NewsCircularReceiverBean target = new NewsCircularReceiverBean ();
            target.setId( source.getId() );
            target.setNewsCircularId( source.getNewsCircularId() );
            target.setReceivingObjectId( source.getReceiverObjectId() );
            target.setReceivingObjectName( source.getReceivingObjectName() );
            target.setReceivingObjectType( source.getReceivingObjectType() );
            target.setNewsCircularStatus( source.getNewsCircularStatus() ); ;		//状态 0已通知 1已接收
            target.setCircularTime( source.getCircularTime() );
            target.setOperateTime( source.getOperateTime() );
            target.setOperatePersonId( source.getOperatePersonId() );
            target.setOperatePersonName( source.getOperatePersonName() );
            target.setRemarks( source.getRemarks() );
            return target;
        }
        return null;
    }



}
