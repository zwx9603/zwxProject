package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 等级调度车辆信息
 *
 */
public class HierarchicalDispatchVehicleSaveInputInfo {


    private String vehicleTypeCode;// 车辆类型代码


    private Integer dispatchNum;// 调派数量

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }
}
