package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 描述：预警信息表
 *
 */

public class EarlyWarningSaveInputInfo   {

    private String incidentId; //案件ID

    private String receiveOrganizationId; //预警接收单位编号

    private String earlyWarningType="2"; //预警类型 0预警前置 1预警 2其他预警，当为2时一个案件可以多次发送预警

    private String organizationId; //主管中队

    private String districtCode;//  行政区划

    private Long alarmTime;  //报警时间

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String incidentTypeCode;// 案件类型

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级

    private String alarmPhone;// 报警电话

    private String sendOrganizationId;// 发送单位编号

    private String sendPersonNumber;// 发送人工号

    private String sendSeatNumber;// 发送坐席号

    private String remarks;//备注

    private Integer whetherTestSign;//是否测试警情 0非测试警情  1测试警情

    public Integer getWhetherTestSign() {
        return whetherTestSign;
    }

    public void setWhetherTestSign(Integer whetherTestSign) {
        this.whetherTestSign = whetherTestSign;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getReceiveOrganizationId() {
        return receiveOrganizationId;
    }

    public void setReceiveOrganizationId(String receiveOrganizationId) {
        this.receiveOrganizationId = receiveOrganizationId;
    }

    public String getEarlyWarningType() {
        return earlyWarningType;
    }

    public void setEarlyWarningType(String earlyWarningType) {
        this.earlyWarningType = earlyWarningType;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Long getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getCrimeAddress() {
        return crimeAddress;
    }

    public void setCrimeAddress(String crimeAddress) {
        this.crimeAddress = crimeAddress;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getAlarmPhone() {
        return alarmPhone;
    }

    public void setAlarmPhone(String alarmPhone) {
        this.alarmPhone = alarmPhone;
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
