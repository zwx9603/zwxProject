package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 处警调度
 *
 */
@Entity
@Table(name = "JCJ_CJDD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleDispatchEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; //案件ID

    @Column(name = "DDLX", length = 100 )
    private Long dispatchType;// 调度类型

    @Column(name = "ZJHM", length = 50 )
    private String callingNumber;//主叫号码

    @Column(name = "BJHM", length = 50)
    private String calledNumber;//被叫号码

    @Column(name = "LYH", length = 100 )
    private String relayRecordNumber;//录音号

    @Column(name = "DDSJ")
    private Long dispatchTime;// 调度时间

    @Column(name = "ZXBH", length = 100)
    private String seatNumber; // 座席号

    @Column(name = "YHBH", length = 100)
    private String personId; // 用户编号

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public Long getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(Long dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public Long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Long dispatchTime) {
        this.dispatchTime = dispatchTime;
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
