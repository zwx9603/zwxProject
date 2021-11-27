package com.dscomm.iecs.accept.graphql.firetypebean;
/**
 * 描述:指挥类bean
 *
 */
public class CommandBean {
    private String id; //主键

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String incidentId;//案件ID

    private String startOrganizationId;        //启动单位ID

    private String startOrganizationName;    //启动单位名称

    private String initiatorId ;        //启动人ID

    private String initiatorName;    //启动人名字

    private Long startTime;        //启动时间

    private Long endTime;            //结束时间

    private String typicalIncident ; //是否典型案例

    public String getTypicalIncident() {
        return typicalIncident;
    }

    public void setTypicalIncident(String typicalIncident) {
        this.typicalIncident = typicalIncident;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getStartOrganizationId() {
        return startOrganizationId;
    }

    public void setStartOrganizationId(String startOrganizationId) {
        this.startOrganizationId = startOrganizationId;
    }

    public String getStartOrganizationName() {
        return startOrganizationName;
    }

    public void setStartOrganizationName(String startOrganizationName) {
        this.startOrganizationName = startOrganizationName;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
