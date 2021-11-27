package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 描述：预警信息 查询
 *
 */

public class EarlyWarningQueryInputInfo {

    private String incidentId; //案件ID

    private String earlyWarningType ; //预警类型 0预警前置 1预警

    private String receiveOrganizationId; //预警接收单位编号

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getEarlyWarningType() {
        return earlyWarningType;
    }

    public void setEarlyWarningType(String earlyWarningType) {
        this.earlyWarningType = earlyWarningType;
    }

    public String getReceiveOrganizationId() {
        return receiveOrganizationId;
    }

    public void setReceiveOrganizationId(String receiveOrganizationId) {
        this.receiveOrganizationId = receiveOrganizationId;
    }
}
