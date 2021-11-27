package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.typebean.Pagination;

public class FindSmartRecommendationInfo {

    private String callManualNumber ; //  字段 调用人工号

    private String callUnitId ; //  字段 调用单位id

    private Long startTime;

    private Long endTime;

    private String type ; //  字段 类型,DJTJ,LLDP

    private String  incidentId; //  字段 案件id

    private Pagination pagination = new Pagination();

    public String getCallManualNumber() {
        return callManualNumber;
    }

    public void setCallManualNumber(String callManualNumber) {
        this.callManualNumber = callManualNumber;
    }

    public String getCallUnitId() {
        return callUnitId;
    }

    public void setCallUnitId(String callUnitId) {
        this.callUnitId = callUnitId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
