package com.dscomm.iecs.garage.service.bean;


import com.dscomm.iecs.base.service.bean.BaseBean;


/**
 * 描述:案件基本信息
 *
 */
public class IncidentBean extends BaseBean {

    private String incidentNumber; // 案件编号（日期坐席工号）

    private String acceptanceId; // 受理单Id

    private  Long registerIncidentTime;// 立案时间

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String incidentStatusCode;// 案件状态代码

    private String incidentStatusName;// 案件状态名称

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public Long getRegisterIncidentTime() {
        return registerIncidentTime;
    }

    public void setRegisterIncidentTime(Long registerIncidentTime) {
        this.registerIncidentTime = registerIncidentTime;
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

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getIncidentStatusName() {
        return incidentStatusName;
    }

    public void setIncidentStatusName(String incidentStatusName) {
        this.incidentStatusName = incidentStatusName;
    }
}
