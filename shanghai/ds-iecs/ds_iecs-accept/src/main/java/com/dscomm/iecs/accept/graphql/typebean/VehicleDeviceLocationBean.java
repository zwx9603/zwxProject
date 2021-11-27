package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.accept.service.bean.DeviceLocationBean;
import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述: 车辆位置信息
 *
 */
public class VehicleDeviceLocationBean extends BaseBean {

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String vehicleId; // 车辆编号

    private String vehicleTypeCode; // 装备类型代码 （车辆类型代码）

    private String vehicleTypeName; // 装备类型名称

    private String fightFunctionCode; // 作战功能代码

    private String fightFunctionName; // 作战功能名称

    private String vehicleStatusCode;// 车辆状态代码 ( 车辆状态代码_灭火 )

    private String vehicleStatusName;// 车辆状态名称 ( 车辆状态代码_灭火 )

    private String  vehicleCode;//车辆编号

    private String vehicleName;// 装备名称 车辆名称

    private String vehicleShortName;// 车辆简称

    private String vehicleLevelCode;// 车辆等级代码

    private String vehicleLevelName;// 车辆等级名称

    private String vehicleNumber;// 车牌号码

    private String gpsNumber;// GPS编号

    private String locationNumber;// 定位编号

    private String radioCallSign;// 电台呼号

    //车辆位置信息
    private DeviceLocationBean deviceLocationBean ;

    public DeviceLocationBean getDeviceLocationBean() {
        return deviceLocationBean;
    }

    public void setDeviceLocationBean(DeviceLocationBean deviceLocationBean) {
        this.deviceLocationBean = deviceLocationBean;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getFightFunctionCode() {
        return fightFunctionCode;
    }

    public void setFightFunctionCode(String fightFunctionCode) {
        this.fightFunctionCode = fightFunctionCode;
    }

    public String getFightFunctionName() {
        return fightFunctionName;
    }

    public void setFightFunctionName(String fightFunctionName) {
        this.fightFunctionName = fightFunctionName;
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

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleShortName() {
        return vehicleShortName;
    }

    public void setVehicleShortName(String vehicleShortName) {
        this.vehicleShortName = vehicleShortName;
    }

    public String getVehicleLevelCode() {
        return vehicleLevelCode;
    }

    public void setVehicleLevelCode(String vehicleLevelCode) {
        this.vehicleLevelCode = vehicleLevelCode;
    }

    public String getVehicleLevelName() {
        return vehicleLevelName;
    }

    public void setVehicleLevelName(String vehicleLevelName) {
        this.vehicleLevelName = vehicleLevelName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getGpsNumber() {
        return gpsNumber;
    }

    public void setGpsNumber(String gpsNumber) {
        this.gpsNumber = gpsNumber;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getRadioCallSign() {
        return radioCallSign;
    }

    public void setRadioCallSign(String radioCallSign) {
        this.radioCallSign = radioCallSign;
    }
}
