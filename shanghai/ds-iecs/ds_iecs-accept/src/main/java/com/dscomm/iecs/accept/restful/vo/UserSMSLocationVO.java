package com.dscomm.iecs.accept.restful.vo;

/**
 * 描述 : 用户短信辅助定位vo，用于第三方请求
 *
 * @author : ZhaiYanTao
 * Date Time : 2020/1/8 13:52
 */
public class UserSMSLocationVO {
    /**
     * 用户账号 / 坐席号
     */
    private String userAccount;
    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 案件id
     */
    private String incidentId ;

    /**
     *  发送短信内容
     */
    private String smsContent ;


    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 地址
     */
    private String address;

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}
