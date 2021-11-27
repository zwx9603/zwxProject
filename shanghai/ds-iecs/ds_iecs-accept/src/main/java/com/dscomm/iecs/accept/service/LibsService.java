package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.service.bean.DeviceLocationBean;

/**
 * 描述 : libs 接口服务
 */
public interface LibsService {

    /**
     *  根据 终端编号 获得 终端位置 信息
     * @param deviceCode
     * @return
     */
    DeviceLocationBean findCurrentDeviceLocation(String deviceCode );


}
