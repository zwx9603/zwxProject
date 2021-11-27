package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述:警情流转
 *
 */
public class IncidentCirculationBean {
    private String receiver;//接收者账号
    private IncidentBean incidentBean; //接警单信息

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }
}
