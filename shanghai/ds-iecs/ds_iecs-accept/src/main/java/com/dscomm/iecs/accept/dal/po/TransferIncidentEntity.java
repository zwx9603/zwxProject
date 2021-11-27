package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  描述: 转警记录（转出）
 *
 */
@Entity
@Table(name = "JCJ_ZJJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TransferIncidentEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId; //消防 案件ID

    @Column(name = "ZJXTAJID", length = 100 )
    private String transferIncidentId; //转警系统 案件ID

    @Column(name = "ZJLX", length = 100 )
    private String transferType; //转警类型 10转警 20错位接警 30请求协助

    @Column(name = "ZJDW", length = 200 )
    private String transferUnit; //转警单位

    @Column(name = "ZJRGH", length = 100 )
    private String transferPersonId; //转警人员编号

    @Column(name = "ZJRXM", length = 100 )
    private String transferPersonName; //转警人员姓名

    @Column(name = "ZJZXH", length = 100 )
    private String transferSeatNumber; //转警坐席号

    @Column(name = "JSDW", length = 200 )
    private String receiveUnit; //接收单位

    @Column(name = "JSZT", length = 100 )
    private String receiveStatus; //接收状态

    @Column(name = "FSZJSJ")
    private Long transferTime;// 发送转警时间

    @Column(name = "JSZJSJ")
    private Long receiveTime;// 接收转警时间

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getTransferIncidentId() {
        return transferIncidentId;
    }

    public void setTransferIncidentId(String transferIncidentId) {
        this.transferIncidentId = transferIncidentId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferUnit() {
        return transferUnit;
    }

    public void setTransferUnit(String transferUnit) {
        this.transferUnit = transferUnit;
    }

    public String getTransferPersonId() {
        return transferPersonId;
    }

    public void setTransferPersonId(String transferPersonId) {
        this.transferPersonId = transferPersonId;
    }

    public String getTransferPersonName() {
        return transferPersonName;
    }

    public void setTransferPersonName(String transferPersonName) {
        this.transferPersonName = transferPersonName;
    }

    public String getTransferSeatNumber() {
        return transferSeatNumber;
    }

    public void setTransferSeatNumber(String transferSeatNumber) {
        this.transferSeatNumber = transferSeatNumber;
    }

    public String getReceiveUnit() {
        return receiveUnit;
    }

    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Long getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Long transferTime) {
        this.transferTime = transferTime;
    }

    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
