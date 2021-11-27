package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 增援请求 详情（车辆类型、数量、装备及要求）
 */
public class ReinforcementAskDetailInputInfo {

    private String vehicleTypeCode; // 车辆类型代码

    private Integer vehicleCount;//车辆数量

    private String equipmentTypeCode; // 装备类型代码

    private Integer equipmentCount;//装备数量

    private String remarks; // 备注/其他要求

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public Integer getEquipmentCount() {
        return equipmentCount;
    }

    public void setEquipmentCount(Integer equipmentCount) {
        this.equipmentCount = equipmentCount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
