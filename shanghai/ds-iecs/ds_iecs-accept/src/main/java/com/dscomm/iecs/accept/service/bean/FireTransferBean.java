package com.dscomm.iecs.accept.service.bean;


/**
 * 描述:消防转公安数据BO
 *
 * @author : XuZhiHeng
 * Date Time : 2020/10/14 18:00
 */
public class FireTransferBean {

    /**
     * 报警编号
     */
    private String  alarmNumber;
    /**
     * 报警人联系地址
     */
    private String alarmPersonAddress;
    /**
     * 报警人联系电话
     */
    private String alarmPersonContact;
    /**
     * 事发地址
     */
    private String address;
    /**
     * 事件详情
     */
    private String content;
    /**
     * X坐标/经度
     */
    private String longitude;
    /**
     * Y坐标/纬度
     */
    private String latitude;
    /**
     * 报警人性别
     */
    private String alarmPersonSex;
    /**
     * 报警人
     */
    private String alarmPersonName;
    /**
     * 报警方式
     */
    private String alarmWay;
    /**
     * 录音号
     */
    private String record;
    /**
     * 报警方式
     */
    private String alertCode;
    /**
     * 发送单位Code
     */
    private String sendOrgCode;

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public String getAlarmPersonContact() {
        return alarmPersonContact;
    }

    public void setAlarmPersonContact(String alarmPersonContact) {
        this.alarmPersonContact = alarmPersonContact;
    }

    public String getAlarmPersonAddress() {
        return alarmPersonAddress;
    }

    public void setAlarmPersonAddress(String alarmPersonAddress) {
        this.alarmPersonAddress = alarmPersonAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getAlarmPersonSex() {
        return alarmPersonSex;
    }

    public void setAlarmPersonSex(String alarmPersonSex) {
        this.alarmPersonSex = alarmPersonSex;
    }

    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
    }

    public String getAlarmWay() {
        return alarmWay;
    }

    public void setAlarmWay(String alarmWay) {
        this.alarmWay = alarmWay;
    }

    public String getRecord(){return record;}

    public void setRecord(String record){this.record = record;}

    public String getAlertCode(){return alertCode;}

    public void setAlertCode(String alertCode){this.alertCode = alertCode;}

    public String getSendOrgCode(){return sendOrgCode;}

    public void setSendOrgCode(String sendOrgCode){this.sendOrgCode = sendOrgCode;}
}
