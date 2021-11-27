package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 车辆状态 保存参数
 *
 */
public class IncidentVehicleHandleUpdateInputInfo {

    private String vehicleId; // 车辆id

    private String handleId ; //处警记录id

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }
}
