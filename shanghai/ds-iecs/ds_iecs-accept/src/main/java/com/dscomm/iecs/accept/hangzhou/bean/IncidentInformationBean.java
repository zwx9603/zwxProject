package com.dscomm.iecs.accept.hangzhou.bean;

public class IncidentInformationBean {

    private Integer fireCount;//火情数量
    private Integer rescueCount;//抢险数量
    private IncidentShowBean lastIncident;//最新警情

    public Integer getFireCount() {
        return fireCount;
    }

    public void setFireCount(Integer fireCount) {
        this.fireCount = fireCount;
    }

    public Integer getRescueCount() {
        return rescueCount;
    }

    public void setRescueCount(Integer rescueCount) {
        this.rescueCount = rescueCount;
    }

    public IncidentShowBean getLastIncident() {
        return lastIncident;
    }

    public void setLastIncident(IncidentShowBean lastIncident) {
        this.lastIncident = lastIncident;
    }

}
