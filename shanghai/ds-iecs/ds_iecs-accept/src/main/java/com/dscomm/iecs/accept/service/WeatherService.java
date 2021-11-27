package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.inputbean.WeatherSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.WeatherBean;

import java.util.List;

/**
 * 描述：天气信息 服务
 */
public interface WeatherService {

    /**
     * 根据  行政区编码 获得天气信息
     * @param districtCode 行政区编码
     * @return 天气信息
     */
    List<WeatherBean> findWeather(String districtCode );


    /**
     *  保存修改 天气信息
     * @param queryBean 天气信息 保存修改参数
     * @return 返回接口
     */
    WeatherBean saveWeather ( WeatherSaveInputInfo queryBean );

}
