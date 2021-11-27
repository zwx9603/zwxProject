package com.dscomm.iecs.accept.service.bean;

/**
 *  公安警情 与 消防警情 关联关系
 */
public class TransferAssociateBean {

    private String  transferIncidentId ; //转警事件单编号

    private  String incidentId ; //公安事件单编号

    public String getTransferIncidentId() {
        return transferIncidentId;
    }

    public void setTransferIncidentId(String transferIncidentId) {
        this.transferIncidentId = transferIncidentId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }
}
