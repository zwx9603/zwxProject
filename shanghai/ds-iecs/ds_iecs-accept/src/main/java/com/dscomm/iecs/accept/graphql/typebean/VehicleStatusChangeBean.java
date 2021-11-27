package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：车辆状态变动记录
 *
 */
public class VehicleStatusChangeBean extends BaseBean {

    private String incidentId;//案件id

    private String handleId;// 处警记录ID

    private String  handleOrganizationVehicleId ; //调派单位车辆信息 （ 作战车辆信息 ）id

    private String vehicleId;   //  车辆编号

    private String vehicleStatusCode;//车辆状态编码

    private String vehicleStatusName;//车辆状态名称

    private Long changeTime;//变动时间

    private Long changeSource;//变动来源

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String seatNumber; // 坐席号

    private String personId; // 用户编号

    private String remarks; // 备注

    //2021-05-01 新增
    private String brigadeOrganizationId;//所属大队
    private String brigadeOrganizationName;//所属大队
    private String vehicleNumber;// 车牌号码
    private String vehicleName;// 装备名称 车辆名称

    public String getBrigadeOrganizationName() {
        return brigadeOrganizationName;
    }

    public void setBrigadeOrganizationName(String brigadeOrganizationName) {
        this.brigadeOrganizationName = brigadeOrganizationName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getHandleOrganizationVehicleId() {
        return handleOrganizationVehicleId;
    }

    public void setHandleOrganizationVehicleId(String handleOrganizationVehicleId) {
        this.handleOrganizationVehicleId = handleOrganizationVehicleId;
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

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }


    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }

    public Long getChangeSource() {
        return changeSource;
    }

    public void setChangeSource(Long changeSource) {
        this.changeSource = changeSource;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
