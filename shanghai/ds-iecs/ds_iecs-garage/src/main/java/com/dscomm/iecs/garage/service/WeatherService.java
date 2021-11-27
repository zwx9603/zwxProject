package com.dscomm.iecs.garage.service;


import com.dscomm.iecs.garage.service.bean.WeatherBean;

/**
 * 描述：天气信息 服务
 */
public interface WeatherService {

    /**
     * 根据  行政区编码 获得天气信息
     * @param districtCode 行政区编码
     * @return 天气信息
     */
    WeatherBean findWeather(String districtCode);



}
