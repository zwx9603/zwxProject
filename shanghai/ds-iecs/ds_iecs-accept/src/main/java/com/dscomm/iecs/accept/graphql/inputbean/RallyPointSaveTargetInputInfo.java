package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述： 集结点目标保存参数
 *
 */
public class RallyPointSaveTargetInputInfo {


    private String incidentId;// 事件ID

    private String commandId;// 指挥ID

    private String organizationId;// 部署机构ID

    private String organizationName;// 部署机构名称

    private String personId;// 部署人Id

    private String personName;// 部署人名称

    private List<RallyPointSaveInputInfo> rallyPoints;// 集结点集合

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public List<RallyPointSaveInputInfo> getRallyPoints() {
        return rallyPoints;
    }

    public void setRallyPoints(List<RallyPointSaveInputInfo> rallyPoints) {
        this.rallyPoints = rallyPoints;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
