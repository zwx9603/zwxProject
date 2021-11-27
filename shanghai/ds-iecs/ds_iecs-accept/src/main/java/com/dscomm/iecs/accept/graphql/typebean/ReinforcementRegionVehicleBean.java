package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述:跨区域增援车辆
 *
 */
public class ReinforcementRegionVehicleBean extends BaseBean {

    private String incidentId; // 案件信息ID

    private String dispatchId;// 出动单ID

    private String vehicleId; // 车辆ID

    private String vehicleTypeCode; // 车辆类型代码

    private String vehicleTypeName; // 车辆类型名称

    private String vehicleName;// 装备名称 车辆名称

    private String vehicleNum;// 车牌号码

    private String organizationId; // 消防机构代码

    private String organizationName; // 消防机构名称

    private String remarks;//备注

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
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

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
