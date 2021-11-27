package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  定位 短信 发送记录信息
 */
@Entity
@Table(name = "JCJ_DWDX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LocationSMSEntity extends BaseEntity {


    @Column(name = "YHBH" , length =  100 )
    private String userAccount; //用户账号

    @Column(name = "SJHMS" , length =  50 )
    private String phoneNumber ;//短信号码

    @Column(name = "DXFSSJ"   )
    private Long smsSendTime  ;//短信发送时间

    @Column(name = "AJID" , length =  100 )
    private String incidentId;//案件ID

    @Column(name = "DXNR" , length =  1000 )
    private String smsContent ;//短信内容

    @Column(name = "DWDXHFSJ"   )
    private Long smsReceiveTime  ;//定位短信回复时间

    @Column(name = "DZXX", length = 400)
    private String address;// 地址信息

    @Column(name = "DQJD", length = 50)
    private String longitude;// 经度

    @Column(name = "DQWD", length = 50)
    private String latitude;//  纬度

    @Column(name = "XFJGBH", length = 100)
    private String sendOrganizationId; //  发送消防机构编号

    @Column(name = "FSRGH", length = 100)
    private String sendPersonNumber; // 发送人工号

    @Column(name = "FSZXH", length = 100)
    private String sendSeatNumber; // 发送坐席号

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSmsSendTime() {
        return smsSendTime;
    }

    public void setSmsSendTime(Long smsSendTime) {
        this.smsSendTime = smsSendTime;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Long getSmsReceiveTime() {
        return smsReceiveTime;
    }

    public void setSmsReceiveTime(Long smsReceiveTime) {
        this.smsReceiveTime = smsReceiveTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
