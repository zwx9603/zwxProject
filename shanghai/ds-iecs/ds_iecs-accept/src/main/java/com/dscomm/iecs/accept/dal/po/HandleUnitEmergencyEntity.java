package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 调派应急联动单位
 *
 */
@Entity
@Table(name = "JCJ_DPYJDW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleUnitEmergencyEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; //案件ID

    @Column(name = "CJJLID", length = 100)
    private String handleId;// 处警记录ID

    @Column(name = "YJLDDWID", length = 100 )
    private String emergencyUnitId ;// 应急联动单位ID

    @Column(name="YJLDDWMC", length = 200)
    private String emergencyUnitName;//应急联动单位名称

    @Column(name = "DPSJ")
    private Long dispatchTime;// 调派时间

    @Column(name = "LYH", length = 100 )
    private String relayRecordNumber;//录音号

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getEmergencyUnitId() {
        return emergencyUnitId;
    }

    public void setEmergencyUnitId(String emergencyUnitId) {
        this.emergencyUnitId = emergencyUnitId;
    }

    public String getEmergencyUnitName() {
        return emergencyUnitName;
    }

    public void setEmergencyUnitName(String emergencyUnitName) {
        this.emergencyUnitName = emergencyUnitName;
    }

    public Long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
