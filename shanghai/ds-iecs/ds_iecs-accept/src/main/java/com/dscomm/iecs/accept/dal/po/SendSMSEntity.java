package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 短信发送
 *
 */
@Entity
@Table(name = "JCJ_DXFS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SendSMSEntity extends BaseEntity {

    @Column(name = "AJID" , length =  100 )
    private String incidentId;//案件ID

    @Column(name = "DXLX" , length =  100 )
    private String smsType ;//短信类型

    @Column(name = "SJHMS" , length =  50 )
    private String smsNumber ;//短信号码

    @Column(name = "DXNR" , length =  1000 )
    private String smsContent ;//短信内容

    @Column(name = "XTDM" , length =  100 )
    private String systemCode  ;//系统代码

    @Column(name = "YXJ" , length =  10 )
    private String priorityNum  ;//优先级

    @Column(name = "FSZT" , length =  100 )
    private String sendStatus  ;//发送状态

    @Column(name = "DXTJSJ"   )
    private Long smsAddTime  ;//短信提交时间

    @Column(name = "DXFSSJ"   )
    private Long smsSendTime  ;//短信发送时间

    @Column(name = "DXSDSJ"   )
    private Long smsReceiveTime  ;//短信收到时间

    @Column(name = "XFJGBH", length = 100)
    private String sendOrganizationId; //  发送消防机构编号

    @Column(name = "FSRGH", length = 100)
    private String sendPersonNumber; // 发送人工号

    @Column(name = "FSZXH", length = 100)
    private String sendSeatNumber; // 发送坐席号

    @Column(name = "BZ", length = 800 )
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
