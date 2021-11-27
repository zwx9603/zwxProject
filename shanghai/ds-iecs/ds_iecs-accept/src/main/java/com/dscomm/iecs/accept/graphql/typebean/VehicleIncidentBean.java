package com.dscomm.iecs.accept.graphql.typebean;


/**
 * 描述: 车辆 参与案件id 车辆状态
 *
 */
public class VehicleIncidentBean   {


    private String vehicleId; // 车辆id

    private String incidentId  ; //案件id

    private String vehicleStatusCode;// 车辆状态代码 ( 车辆状态代码_灭火 )

    private String vehicleStatusName;// 车辆状态名称 ( 车辆状态代码_灭火 )

    private  String vehicleIdentification;  //车辆标识 0 头车 1指挥车 2尾车  可拓展

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getVehicleStatusName() {
        return vehicleStatusName;
    }

    public void setVehicleStatusName(String vehicleStatusName) {
        this.vehicleStatusName = vehicleStatusName;
    }

    public String getVehicleIdentification() {
        return vehicleIdentification;
    }

    public void setVehicleIdentification(String vehicleIdentification) {
        this.vehicleIdentification = vehicleIdentification;
    }
}
