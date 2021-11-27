package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述: 转警记录（转出）
 *
 */
public class TransferIncidentBean extends BaseBean {

    private String incidentId; //案件ID

    private String transferType; //转警类型

    private String transferUnit; //转警单位

    private String transferPersonId; //转警人员员工号

    private String transferPersonName; //转警人员姓名

    private String transferSeatNumber; //转警坐席号

    private String receiveUnit; //接收单位

    private String receiveStatus; //接收状态

    private Long transferTime;// 发送转警时间

    private Long receiveTime;// 接收转警时间

    private String remarks;//备注

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
