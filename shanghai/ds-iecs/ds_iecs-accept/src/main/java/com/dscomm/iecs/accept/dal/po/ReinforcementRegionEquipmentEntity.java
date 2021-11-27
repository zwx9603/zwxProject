package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述:跨区域增援装备表
 *
 */
@Entity
@Table(name = "JCJ_KQYZYZB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ReinforcementRegionEquipmentEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; // 案件信息ID

    @Column(name = "CDDID", length = 100 )
    private String dispatchId;// 出动单ID

    @Column(name = "XFJGDM", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "ZBLXDM", length = 100)
    private String vehicleTypeCode; // 装备类型代码

    @Column(name = "DPSL")
    private Integer dispatchNum;// 调派数量

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
