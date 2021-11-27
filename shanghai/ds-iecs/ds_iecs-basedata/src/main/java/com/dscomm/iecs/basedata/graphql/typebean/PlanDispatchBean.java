package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:预案车辆调集 （预案调派）
 */
public class PlanDispatchBean extends BaseBean {

    private String planId;  //预案id

    private String subordinateUnitId;  //所属单位ID

    private String subordinateUnitName;  //所属单位名称

    private String organizationId;  //机构编号

    private String organizationName;  //机构名称

    private String vehicleId;  //车辆ID

    private String vehicleName;  //车辆名称

    private String dispatchGroup;  //出动组别

    private String vehicleNumber;  //车牌号码

    private String vehicleTypeCode;  //车辆类型代码

    private String vehicleTypeName;  //车辆类型名称

    private String vehicleLevelCode;  //车辆等级代码

    private String vehicleLevelName;  //车辆等级名称

    private String operationalFunctionCode;  //作战功能代码

    private String operationalFunctionName;  //作战功能名称

    private Integer dispatchOrderNum;  //出动顺序

    private Long attendanceTime;  //到场时间

    private String remarks;  //备注

    private Integer usageTimes=0;//使用次数



    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getSubordinateUnitName() {
        return subordinateUnitName;
    }

    public void setSubordinateUnitName(String subordinateUnitName) {
        this.subordinateUnitName = subordinateUnitName;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleLevelName() {
        return vehicleLevelName;
    }

    public void setVehicleLevelName(String vehicleLevelName) {
        this.vehicleLevelName = vehicleLevelName;
    }

    public String getOperationalFunctionName() {
        return operationalFunctionName;
    }

    public void setOperationalFunctionName(String operationalFunctionName) {
        this.operationalFunctionName = operationalFunctionName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getSubordinateUnitId() {
        return subordinateUnitId;
    }

    public void setSubordinateUnitId(String subordinateUnitId) {
        this.subordinateUnitId = subordinateUnitId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDispatchGroup() {
        return dispatchGroup;
    }

    public void setDispatchGroup(String dispatchGroup) {
        this.dispatchGroup = dispatchGroup;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleLevelCode() {
        return vehicleLevelCode;
    }

    public void setVehicleLevelCode(String vehicleLevelCode) {
        this.vehicleLevelCode = vehicleLevelCode;
    }

    public String getOperationalFunctionCode() {
        return operationalFunctionCode;
    }

    public void setOperationalFunctionCode(String operationalFunctionCode) {
        this.operationalFunctionCode = operationalFunctionCode;
    }

    public Integer getDispatchOrderNum() {
        return dispatchOrderNum;
    }

    public void setDispatchOrderNum(Integer dispatchOrderNum) {
        this.dispatchOrderNum = dispatchOrderNum;
    }

    public Long getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Long attendanceTime) {
        this.attendanceTime = attendanceTime;
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

    public Integer getUsageTimes() {
        return usageTimes;
    }

    public void setUsageTimes(Integer usageTimes) {
        this.usageTimes = usageTimes;
    }
}