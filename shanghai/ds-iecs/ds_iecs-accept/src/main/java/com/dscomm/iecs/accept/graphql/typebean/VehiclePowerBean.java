package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述：出动力量-车辆
 *
 * @author YangFuXi Date Time 2018/7/8 13:31
 */
public class VehiclePowerBean {
    //车辆ID
    private String vehicleId;
    //状态代码
    private Integer statusCode;
    //状态名称 如出动，到场，出水等等
    private String status;
    //车辆名称
    private String vehicleName;
    //车牌号
    private String vehicleNumber;
    //定位编号
    private String locationNumber;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }


}
