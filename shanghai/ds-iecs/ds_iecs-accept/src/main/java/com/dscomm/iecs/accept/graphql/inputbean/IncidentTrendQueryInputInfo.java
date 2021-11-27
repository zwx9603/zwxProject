package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述：警情趋势查询参数
 *
 */

public class IncidentTrendQueryInputInfo {

    private String scopeSquadronId ; //查询范围辖区Id

    private Integer scopeType = 0 ; //查询范围辖区类型 0根机构 1非根机构

    private Long startTime; //开始时间

    private Long endTime;   //结束时间

    private String timeType;    //时间类型

    private String districtCode ; // 行政区代码

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getScopeSquadronId() {
        return scopeSquadronId;
    }

    public void setScopeSquadronId(String scopeSquadronId) {
        this.scopeSquadronId = scopeSquadronId;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }
}
