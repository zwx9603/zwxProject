package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 消防机构 机构地方非消防机构车辆详情
 *
 */
public class OrganizationLocalFullTimeUnitBean extends BaseBean {

    private String organizationId;// 所属消防机构ID

    private String organizationName;// 所属消防机构名称

    private String equipmentSortCode;// 装备分类编码

    private String equipmentSortName;// 装备分类名称

    private String vehicleLevel;// 车辆等级

    private String specificationsNumber;// 规格型号

    private String vehicleNumber;// 车牌号

    private String whetherValid;// 是否有效

    private String equipmentStatus;// 装备状态

    private Long equipmentTime;// 配备时间

    private String synopsis;// 简介

    private String remarks; // 备注

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


    public String getEquipmentSortCode() {
        return equipmentSortCode;
    }

    public void setEquipmentSortCode(String equipmentSortCode) {
        this.equipmentSortCode = equipmentSortCode;
    }

    public String getEquipmentSortName() {
        return equipmentSortName;
    }

    public void setEquipmentSortName(String equipmentSortName) {
        this.equipmentSortName = equipmentSortName;
    }

    public String getVehicleLevel() {
        return vehicleLevel;
    }

    public void setVehicleLevel(String vehicleLevel) {
        this.vehicleLevel = vehicleLevel;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getWhetherValid() {
        return whetherValid;
    }

    public void setWhetherValid(String whetherValid) {
        this.whetherValid = whetherValid;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public Long getEquipmentTime() {
        return equipmentTime;
    }

    public void setEquipmentTime(Long equipmentTime) {
        this.equipmentTime = equipmentTime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
