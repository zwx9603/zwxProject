package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：车辆状态变动记录
 *
 */
@Entity
@Table(name = "JCJ_CLZTBDJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VehicleStatusChangeEntity extends BaseEntity {

    @Column(name = "AJID", length = 100)
    private String incidentId;//案件id

    @Column(name = "CJJLID", length = 100)
    private String handleId;// 处警记录ID

    @Column(name = "ZZCLXXID", length = 100)
    private String  handleOrganizationVehicleId ; //调派单位车辆信息 （ 作战车辆信息 ）id

    @Column(name = "CLBH", length = 100)
    private String vehicleId;   //  车辆编号

    @Column(name = "CLZT", length = 100)
    private String vehicleStatusCode;//车辆状态

    @Column(name = "BDSJ" )
    private Long changeTime;//变动时间

    @Column(name = "BDLY", length = 100)
    private String changeSource;//变动来源  IECS接处警/COC实战指挥/YD移动终端

    @Column(name = "XFJGBH", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "ZXH", length = 100)
    private String seatNumber; // 坐席号

    @Column(name = "YHBH", length = 100)
    private String personId; // 用户编号

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

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

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeSource() {
        return changeSource;
    }

    public void setChangeSource(String changeSource) {
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
