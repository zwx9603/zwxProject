package com.dscomm.iecs.garage.service.bean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 车辆信息
 *
 */
public class EquipmentVehicleBean extends BaseBean {

    private String  vehicleCode;//车辆编号

    private String vehicleName;// 装备名称 车辆名称

    private String vehicleShortName;// 车辆简称

    private String vehicleTypeCode;// 装备类型代码 车辆类型代码

    private String vehicleTypeName;// 装备类型代码 车辆类型名称

    private String vehicleStatusCode;// 车辆状态代码 ( 车辆状态代码_灭火 )

    private String vehicleStatusName;// 车辆状态名称 ( 车辆状态代码_灭火 )

    private String vehicleNumber;// 车牌号码

    private String garageCode;// 车库门编号

    private String eicdCode;// EICD编号

    private String roomNumber;// 房间编号

    private String garageStatusCode;// 车库门状态 1=出库，2=入库

    private String vehicleGarageStatusCode;// 车辆在库门状态 1=出库，2=入库

    public String getVehicleGarageStatusCode() {
        return vehicleGarageStatusCode;
    }

    public void setVehicleGarageStatusCode(String vehicleGarageStatusCode) {
        this.vehicleGarageStatusCode = vehicleGarageStatusCode;
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

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getGarageCode() {
        return garageCode;
    }

    public void setGarageCode(String garageCode) {
        this.garageCode = garageCode;
    }

    public String getEicdCode() {
        return eicdCode;
    }

    public void setEicdCode(String eicdCode) {
        this.eicdCode = eicdCode;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getGarageStatusCode() {
        return garageStatusCode;
    }

    public void setGarageStatusCode(String garageStatusCode) {
        this.garageStatusCode = garageStatusCode;
    }
}
