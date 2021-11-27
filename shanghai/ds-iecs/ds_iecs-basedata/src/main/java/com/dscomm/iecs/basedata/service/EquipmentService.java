package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleLoadBean;

import java.util.List;
import java.util.Set;

/**
 * 描述：装备（消防设备）服务
 */
public interface EquipmentService {

    /**
     * 缓存车辆装载 设备信息
     *
     * @return 消防装备列表
     */
    void forceUpdateCacheEquipmentVehicleLoadCache( );




    /**
     * 根据车辆id获得 车辆装载 设备信息
     * 缓存获取
     *
     * @return 消防装备列表
     */
    List<EquipmentVehicleLoadBean> findEquipmentVehicleLoadByVehicleId(  String vehicleId  );


    /**
     * 根据条件获得消防装备(分页)
     *
     * @param inputInfo 查询参数
     * @return 消防装备列表
     */
    PaginationBean<EquipmentBean> findEquipmentCondition(EquipmentQueryInputInfo inputInfo);

    /**
     * 根据装备类型codes 获取装载了该装备的车辆id列表
     * 缓存获取
     *
     * @return 车辆id列表
     */
    Set<String> findVehicleIdsByEquipmentTypeCode( List<String> equipmentTypeCodes  );


    /**
     * 根据装备类型code 获取装载了该装备的车辆id列表
     * 缓存获取
     *
     * @return 车辆id列表
     */
    Set<String> findVehicleIdsByEquipmentKeyWord (  String  keyword   );

    /**
     * 根据车辆id列表 查询车辆装备信息
     *
     * @return 车辆装备信息列表
     */
    List<EquipmentVehicleLoadBean> findEquipmentVehicleByVehicleIds(List<String> vehicleIds );

}
