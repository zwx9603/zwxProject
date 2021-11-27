package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述:
 * author:YangFuXi
 * Date:2021/6/19 Time:19:01
 **/
public class IncidentVehicleStatisticsInputInfo {
    private Long startTime;//统计开始时间
    private Long endTime;//统计结束时间
    private String disposalObjectCode;// 处置对象代码
    private String incidentLevelCode;//案件等级代码

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

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }
}
