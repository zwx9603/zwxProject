package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：预警信息表
 *
 */

public class EarlyWarningBean extends BaseBean {

    private String incidentId; //案件ID

    private String receiveOrganizationId; //预警接收单位编号

    private String receiveOrganizationName; //预警接收单位名称

    private String earlyWarningType ; //预警类型 0预警前置 1预警

    private String earlyWarningStatus ; //预警状态 0已通知 1已接收

    private String receiveOrganizationDispatchPhone;// 预警接收单位调度电话

    private String receiveOrganizationMailCode;// 预警接收单位邮政编码

    private String receiveOrganizationFaxNumber;// 预警接收单位传真号码

    private String receiveOrganizationContactPerson;// 预警接收单位单位联系人

    private String receiveOrganizationContactPhone;// 预警接收单位单位联系电话


    private String organizationId; //主管中队

    private String organizationName; //主管中队名称

    private String districtCode;//  行政区划编码

    private String districtName;//  行政区划名称

    private Long alarmTime;  //报警时间

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String incidentTypeCode;// 案件类型

    private String incidentTypeName;// 案件类型名称

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentTypeSubitemName;// 案件类型子项名称（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级

    private String incidentLevelName;// 案件等级名称

    private String alarmPhone;// 报警电话

    private String sendOrganizationId;// 发送单位编号

    private String sendOrganizationName;// 发送单位名称

    private String sendPersonNumber;// 发送人工号

    private String sendSeatNumber;// 发送坐席号

    private Long sendTime;  //发送时间

    private Long revokeTime; //撤销时间

    private String remarks;//备注

    private Integer whetherTestSign;//是否测试警情 0非测试警 1 测试警


    private String  organizationLongitude;// 单位经度

    private String  organizationLatitude;// 单位纬度

    private String organizationHeight;// 单位高度

    private double distance ; // 单位距离警情的距离

    private String  speakToFileUrl  ; // tts播报语音文件路径

    public String getSpeakToFileUrl() {
        return speakToFileUrl;
    }

    public void setSpeakToFileUrl(String speakToFileUrl) {
        this.speakToFileUrl = speakToFileUrl;
    }

    public String getOrganizationLongitude() {
        return organizationLongitude;
    }

    public void setOrganizationLongitude(String organizationLongitude) {
        this.organizationLongitude = organizationLongitude;
    }

    public String getOrganizationLatitude() {
        return organizationLatitude;
    }

    public void setOrganizationLatitude(String organizationLatitude) {
        this.organizationLatitude = organizationLatitude;
    }

    public String getOrganizationHeight() {
        return organizationHeight;
    }

    public void setOrganizationHeight(String organizationHeight) {
        this.organizationHeight = organizationHeight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

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

    public String getIncidentTypeSubitemName() {
        return incidentTypeSubitemName;
    }

    public void setIncidentTypeSubitemName(String incidentTypeSubitemName) {
        this.incidentTypeSubitemName = incidentTypeSubitemName;
    }

    public String getReceiveOrganizationDispatchPhone() {
        return receiveOrganizationDispatchPhone;
    }

    public void setReceiveOrganizationDispatchPhone(String receiveOrganizationDispatchPhone) {
        this.receiveOrganizationDispatchPhone = receiveOrganizationDispatchPhone;
    }

    public String getReceiveOrganizationMailCode() {
        return receiveOrganizationMailCode;
    }

    public void setReceiveOrganizationMailCode(String receiveOrganizationMailCode) {
        this.receiveOrganizationMailCode = receiveOrganizationMailCode;
    }

    public String getReceiveOrganizationFaxNumber() {
        return receiveOrganizationFaxNumber;
    }

    public void setReceiveOrganizationFaxNumber(String receiveOrganizationFaxNumber) {
        this.receiveOrganizationFaxNumber = receiveOrganizationFaxNumber;
    }

    public String getReceiveOrganizationContactPerson() {
        return receiveOrganizationContactPerson;
    }

    public void setReceiveOrganizationContactPerson(String receiveOrganizationContactPerson) {
        this.receiveOrganizationContactPerson = receiveOrganizationContactPerson;
    }

    public String getReceiveOrganizationContactPhone() {
        return receiveOrganizationContactPhone;
    }

    public void setReceiveOrganizationContactPhone(String receiveOrganizationContactPhone) {
        this.receiveOrganizationContactPhone = receiveOrganizationContactPhone;
    }

    public String getEarlyWarningStatus() {
        return earlyWarningStatus;
    }

    public void setEarlyWarningStatus(String earlyWarningStatus) {
        this.earlyWarningStatus = earlyWarningStatus;
    }

    public String getReceiveOrganizationName() {
        return receiveOrganizationName;
    }

    public void setReceiveOrganizationName(String receiveOrganizationName) {
        this.receiveOrganizationName = receiveOrganizationName;
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

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Long getRevokeTime() {
        return revokeTime;
    }

    public void setRevokeTime(Long revokeTime) {
        this.revokeTime = revokeTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getIncidentLevelName() {
        return incidentLevelName;
    }

    public void setIncidentLevelName(String incidentLevelName) {
        this.incidentLevelName = incidentLevelName;
    }

    public String getSendOrganizationName() {
        return sendOrganizationName;
    }

    public void setSendOrganizationName(String sendOrganizationName) {
        this.sendOrganizationName = sendOrganizationName;
    }
}
