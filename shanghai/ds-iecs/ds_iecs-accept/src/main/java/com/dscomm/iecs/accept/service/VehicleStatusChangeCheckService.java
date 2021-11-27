package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.VehicleStatusChangeCheckSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.VehicleStatusChangeCheckBean;
import com.dscomm.iecs.basedata.graphql.inputbean.EquipmentVehicleExpandInputInfo;

import java.util.List;

/**
 *  车辆状态审批记录 服务层
 */
public interface VehicleStatusChangeCheckService {


    /**
     *  修改车辆状态信息  ( 可走审批流程  不走审批流程  )
     */
    Boolean    updateVehicleStatusChangeCheck(VehicleStatusChangeCheckSaveInputInfo queryBean  , Boolean whetherCheck  );


    /**
     * 保存车辆审批结构 （ 同意/拒绝 ）
     */
    VehicleStatusChangeCheckBean checkVehicleStatusChangeCheck(  VehicleStatusChangeCheckSaveInputInfo queryBean ) ;


    /**
     *  查询车辆审批列表
     * @return
     */
    List<VehicleStatusChangeCheckBean> findVehicleStatusChangeCheck( );

    /**
     * 审计车辆拓展
     * @param inputInfo
     */
    Boolean   auditVehicleExpand(EquipmentVehicleExpandInputInfo inputInfo);

    /**
     * 审核
     * @param queryBean
     * @return
     */
    Boolean  auditVehicleStatusChangeCheck(EquipmentVehicleExpandInputInfo queryBean);





}
