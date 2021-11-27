package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 车辆案件状态关系 车辆状态对案件状态的影响
 *
 */
@Entity
@Table(name = "JCJ_CLAJZTGX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VehicleIncidentStatusMappingEntity extends BaseEntity {

    @Column(name = "AJLX", length = 100)
    private String incidentTypeCode;// 案件类型编码

    @Column(name = "CLZT", length = 100)
    private String vehicleStatusCode;// 车辆状态编码

    @Column(name = "AJZT", length = 100)
    private String incidentStatusCode;// 案件状态编码

    @Column(name = "YXFS", length = 100)
    private String influenceMode;// 影响方式

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getInfluenceMode() {
        return influenceMode;
    }

    public void setInfluenceMode(String influenceMode) {
        this.influenceMode = influenceMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
