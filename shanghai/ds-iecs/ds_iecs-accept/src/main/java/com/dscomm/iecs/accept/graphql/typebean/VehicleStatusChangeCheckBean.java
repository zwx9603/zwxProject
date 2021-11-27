package  com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述：车辆状态审批记录
 *
 */

public class VehicleStatusChangeCheckBean {

    private String id ;

    private String incidentId;//案件id

    private Long appliedTime; // 申请时间

    private String appliedOrganizationId; // 申请机构编号

    private String appliedOrganizationName; // 申请机构名称

    private String  appliedVehicleId;   //  申请车辆id

    private String  appliedVehicleName;   //  申请车辆名称

    private String  appliedVehicleNumber;   //  申请车辆车牌号

    private String  appliedVehicleTypeCode ;   //  申请车辆车辆类型编码

    private String  appliedVehicleTypeName;   //  申请车辆车辆类型名称

    private String oldVehicleStatusCode;//申请前车辆状态代码

    private String oldVehicleStatusName;//申请前车辆状态名称

    private String vehicleStatusCode;//申请变更车辆状态代码

    private String vehicleStatusName;//申请变更车辆状态代名称

    private String appliedChangeDesc;//申请变动描述

    private Integer checkStatus;//审批状态 0拒绝 1同意

    private Long checkTime;//审批时间

    private String checkDesc;//审批说明

    private String checkOrganizationId;//审批机构

    private String checkOrganizationName;//审批机构名称

    private String checkPersonId;//审批用户编号

    private String remarks; // 备注


    public String getAppliedVehicleTypeCode() {
        return appliedVehicleTypeCode;
    }

    public void setAppliedVehicleTypeCode(String appliedVehicleTypeCode) {
        this.appliedVehicleTypeCode = appliedVehicleTypeCode;
    }

    public String getAppliedVehicleTypeName() {
        return appliedVehicleTypeName;
    }

    public void setAppliedVehicleTypeName(String appliedVehicleTypeName) {
        this.appliedVehicleTypeName = appliedVehicleTypeName;
    }

    public String getOldVehicleStatusName() {
        return oldVehicleStatusName;
    }

    public void setOldVehicleStatusName(String oldVehicleStatusName) {
        this.oldVehicleStatusName = oldVehicleStatusName;
    }

    public String getVehicleStatusName() {
        return vehicleStatusName;
    }

    public void setVehicleStatusName(String vehicleStatusName) {
        this.vehicleStatusName = vehicleStatusName;
    }

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

    public Long getAppliedTime() {
        return appliedTime;
    }

    public void setAppliedTime(Long appliedTime) {
        this.appliedTime = appliedTime;
    }

    public String getAppliedOrganizationId() {
        return appliedOrganizationId;
    }

    public void setAppliedOrganizationId(String appliedOrganizationId) {
        this.appliedOrganizationId = appliedOrganizationId;
    }

    public String getAppliedOrganizationName() {
        return appliedOrganizationName;
    }

    public void setAppliedOrganizationName(String appliedOrganizationName) {
        this.appliedOrganizationName = appliedOrganizationName;
    }

    public String getAppliedVehicleId() {
        return appliedVehicleId;
    }

    public void setAppliedVehicleId(String appliedVehicleId) {
        this.appliedVehicleId = appliedVehicleId;
    }

    public String getAppliedVehicleName() {
        return appliedVehicleName;
    }

    public void setAppliedVehicleName(String appliedVehicleName) {
        this.appliedVehicleName = appliedVehicleName;
    }

    public String getAppliedVehicleNumber() {
        return appliedVehicleNumber;
    }

    public void setAppliedVehicleNumber(String appliedVehicleNumber) {
        this.appliedVehicleNumber = appliedVehicleNumber;
    }

    public String getOldVehicleStatusCode() {
        return oldVehicleStatusCode;
    }

    public void setOldVehicleStatusCode(String oldVehicleStatusCode) {
        this.oldVehicleStatusCode = oldVehicleStatusCode;
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

    public String getCheckOrganizationName() {
        return checkOrganizationName;
    }

    public void setCheckOrganizationName(String checkOrganizationName) {
        this.checkOrganizationName = checkOrganizationName;
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
