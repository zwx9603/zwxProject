package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 短信发送
 *
 */
public class SendSMSBean extends BaseBean {

    private String incidentId;//案件ID

    private String smsType ;//短信类型

    private String smsNumber ;//短信号码

    private String smsContent ;//短信内容

    private String systemCode  ;//系统代码

    private String priorityNum  ;//优先级

    private String sendStatus  ;//发送状态

    private Long smsAddTime  ;//短信提交时间

    private Long smsSendTime  ;//短信发送时间

    private Long smsReceiveTime  ;//短信收到时间

    private String sendOrganizationId; //  发送消防机构编号

    private String sendOrganizationName; //  发送消防机构名称

    private String sendPersonNumber; // 发送人工号

    private String sendSeatNumber; // 发送坐席号

    private String remarks; // 备注



    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getPriorityNum() {
        return priorityNum;
    }

    public void setPriorityNum(String priorityNum) {
        this.priorityNum = priorityNum;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Long getSmsAddTime() {
        return smsAddTime;
    }

    public void setSmsAddTime(Long smsAddTime) {
        this.smsAddTime = smsAddTime;
    }

    public Long getSmsSendTime() {
        return smsSendTime;
    }

    public void setSmsSendTime(Long smsSendTime) {
        this.smsSendTime = smsSendTime;
    }

    public Long getSmsReceiveTime() {
        return smsReceiveTime;
    }

    public void setSmsReceiveTime(Long smsReceiveTime) {
        this.smsReceiveTime = smsReceiveTime;
    }

    public String getSendOrganizationId() {
        return sendOrganizationId;
    }

    public void setSendOrganizationId(String sendOrganizationId) {
        this.sendOrganizationId = sendOrganizationId;
    }

    public String getSendOrganizationName() {
        return sendOrganizationName;
    }

    public void setSendOrganizationName(String sendOrganizationName) {
        this.sendOrganizationName = sendOrganizationName;
    }

    public String getSendPersonNumber() {
        return sendPersonNumber;
    }

    public void setSendPersonNumber(String sendPersonNumber) {
        this.sendPersonNumber = sendPersonNumber;
    }

    public String getSendSeatNumber() {
        return sendSeatNumber;
    }

    public void setSendSeatNumber(String sendSeatNumber) {
        this.sendSeatNumber = sendSeatNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
