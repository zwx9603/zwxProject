package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 调派 联勤保障单位
 *
 */
@Entity
@Table(name = "JCJ_DPBZDW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleUnitJointServiceEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; //案件ID

    @Column(name = "CJJLID", length = 100)
    private String handleId;// 处警记录ID

    @Column(name = "LQBZDWID", length = 100 )
    private String safeguardUnitId ;// 联勤保障单位ID

    @Column(name = "BZLXDM", length = 100 )
    private String safeguardTypeCode ;// 保障类型代码

    @Column(name="DPSL" )
    private Integer dispatchNum;//调派数量

    @Column(name="BZNLLXMC" , length = 200 )
    private Integer safeguardAbilityTypeName;//保障能力类型名称

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

    public String getSafeguardUnitId() {
        return safeguardUnitId;
    }

    public void setSafeguardUnitId(String safeguardUnitId) {
        this.safeguardUnitId = safeguardUnitId;
    }

    public String getSafeguardTypeCode() {
        return safeguardTypeCode;
    }

    public void setSafeguardTypeCode(String safeguardTypeCode) {
        this.safeguardTypeCode = safeguardTypeCode;
    }

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public Integer getSafeguardAbilityTypeName() {
        return safeguardAbilityTypeName;
    }

    public void setSafeguardAbilityTypeName(Integer safeguardAbilityTypeName) {
        this.safeguardAbilityTypeName = safeguardAbilityTypeName;
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
