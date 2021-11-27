package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;


/**
 * 描述：车辆状态变动记录 服务
 */
public interface VehicleChangeService {


    /**
     * 描述：修改车辆拓展信息
     *
     * @param inputInfo 车辆拓展信息参数
     * @return 车辆信息
     */
    EquipmentVehicleBean updateVehicleExpandInfo(EquipmentVehicleExpandInputInfo inputInfo);







}
