package com.dscomm.iecs.accept.graphql.typebean;

public class KPIStatisticsBean {

    private Integer acceptanceTotal;  //接警量

    private Integer incidentTotal;//警情量

    private Integer violationTotal ; // 违规量

    private Long avgAcceptanceDuration ; // 平均接警时长

    private Long avgHandleDuration ; // 平均处警时长

    public Integer getAcceptanceTotal() {
        return acceptanceTotal;
    }

    public void setAcceptanceTotal(Integer acceptanceTotal) {
        this.acceptanceTotal = acceptanceTotal;
    }

    public Integer getIncidentTotal() {
        return incidentTotal;
    }

    public void setIncidentTotal(Integer incidentTotal) {
        this.incidentTotal = incidentTotal;
    }

    public Integer getViolationTotal() {
        return violationTotal;
    }

    public void setViolationTotal(Integer violationTotal) {
        this.violationTotal = violationTotal;
    }

    public Long getAvgAcceptanceDuration() {
        return avgAcceptanceDuration;
    }

    public void setAvgAcceptanceDuration(Long avgAcceptanceDuration) {
        this.avgAcceptanceDuration = avgAcceptanceDuration;
    }

    public Long getAvgHandleDuration() {
        return avgHandleDuration;
    }

    public void setAvgHandleDuration(Long avgHandleDuration) {
        this.avgHandleDuration = avgHandleDuration;
    }
}
