package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;

/**
 * 描述:车辆装备缓存
 * author:YangFuXi
 * Date:2021/5/19 Time:18:19
 **/
public interface VehicleCacheService {
    /**
     * 修改车辆装备缓存
     *
     * @param id 车辆id
     * @param bean 车辆信息
     * @return 车辆信息缓存
     */
    EquipmentVehicleBean modifyVehicleCache(String id, EquipmentVehicleBean bean);

    /**
     * 获取车辆装备缓存
     *
     * @param id 车辆id
     * @return 车辆信息缓存
     */
    EquipmentVehicleBean findVehicleCache(String id);
}
