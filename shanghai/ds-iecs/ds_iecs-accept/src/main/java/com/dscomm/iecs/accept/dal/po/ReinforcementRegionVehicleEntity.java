package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述:跨区域增援车辆
 *
 */
@Entity
@Table(name = "JCJ_KQYZYCL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ReinforcementRegionVehicleEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; // 案件信息ID

    @Column(name = "CDDID", length = 100 )
    private String dispatchId;// 出动单ID

    @Column(name = "CLID", length = 100)
    private String vehicleId; // 车辆ID

    @Column(name = "CLLXDM", length = 100)
    private String vehicleTypeCode; // 车辆类型代码

    @Column(name = "CLMC", length = 200)
    private String vehicleName;// 装备名称 车辆名称

    @Column(name = "CPHM", length = 50)
    private String vehicleNum;// 车牌号码

    @Column(name = "XFJGDM", length = 100)
    private String organizationId; // 消防机构代码

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

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
