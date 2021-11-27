package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述：车辆状态审批记录
 *
 */

public class VehicleStatusChangeCheckInputInfo  {

    private String id;


    private String incidentId;//案件id


    private String appliedOrganizationId; // 申请机构编号


    private String  appliedVehicleId;   //  申请车辆编号


    private String vehicleStatusCode;//车辆状态代码

    private String appliedChangeDesc;//申请变动描述


    private Integer checkStatus;//审批状态


    private Long checkTime;//审批时间


    private String checkDesc;//审批说明


    private String checkOrganizationId;//审批机构


    private String checkPersonId;//审批用户编号

    private String remarks; // 备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
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

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getCheckOrganizationId() {
        return checkOrganizationId;
    }

    public void setCheckOrganizationId(String checkOrganizationId) {
        this.checkOrganizationId = checkOrganizationId;
    }

    public String getCheckPersonId() {
        return checkPersonId;
    }

    public void setCheckPersonId(String checkPersonId) {
        this.checkPersonId = checkPersonId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
