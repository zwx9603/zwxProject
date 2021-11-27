package com.dscomm.iecs.accept.graphql.firetypebean;

public class IncidentCircleBean {

    private  String id ;//主键

    private String incidentId;//案件ID

    private String radius;//半径

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
