package com.dscomm.iecs.accept.hangzhou.bean;

import java.util.Date;

public class IncidentShowBean {

    private String incidentAddress;//案件地址
    private Date inctdentTime;//案发时间
    private String incidentType;//案件类型

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public Date getInctdentTime() {
        return inctdentTime;
    }

    public void setInctdentTime(Date inctdentTime) {
        this.inctdentTime = inctdentTime;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
}
