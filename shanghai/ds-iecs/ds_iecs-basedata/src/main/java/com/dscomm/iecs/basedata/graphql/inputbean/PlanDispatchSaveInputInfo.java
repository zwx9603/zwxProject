package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 描述:预案车辆调集 （预案调派）保存修改参数
 */
public class PlanDispatchSaveInputInfo  {

    private String id  ; //主键

    private String subordinateUnitId;  //所属单位ID

    private String organizationId;  //机构编号

    private String vehicleId;  //车辆ID

    private String dispatchGroup;  //出动组别

    private String vehicleNumber;  //车牌号码

    private String vehicleTypeCode;  //车辆类型代码

    private String vehicleLevelCode;  //车辆等级代码

    private String operationalFunctionCode;  //作战功能代码

    private Integer dispatchOrderNum;  //出动顺序

    private Long attendanceTime;  //到场时间

    private String remarks;  //备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubordinateUnitId() {
        return subordinateUnitId;
    }

    public void setSubordinateUnitId(String subordinateUnitId) {
        this.subordinateUnitId = subordinateUnitId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}