package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述：车辆状态申请保存参数
 *
 */

public class VehicleStatusChangeCheckSaveInputInfo {

    private String id ;

    private String changeSource  = "PC" ; //申请来源 包含 PC or YD 默认PC

    private String appliedOrganizationId; // 申请机构编号

    private String  appliedVehicleId;   //  申请车辆id

    private String vehicleStatusCode;//申请变更车辆状态代码

    private String appliedChangeDesc;//申请变动描述

    private Integer checkStatus;//审批状态 0/1

    private String checkDesc;//审批说明

    private String remarks; // 备注

    public String getChangeSource() {
        return changeSource;
    }

    public void setChangeSource(String changeSource) {
        this.changeSource = changeSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAppliedOrganizationId() {
        return appliedOrganizationId;
    }

    public void setAppliedOrganizationId(String appliedOrganizationId) {
        this.appliedOrganizationId = appliedOrganizationId;
    }

    public String getAppliedVehicleId() {
        return appliedVehicleId;
    }

    public void setAppliedVehicleId(String appliedVehicleId) {
        this.appliedVehicleId = appliedVehicleId;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getAppliedChangeDesc() {
        return appliedChangeDesc;
    }

    public void setAppliedChangeDesc(String appliedChangeDesc) {
        this.appliedChangeDesc = appliedChangeDesc;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
