package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 处警统计查询参数
 */
public class HandleStatisticsQueryInputInfo {

    private String scopeSquadronId; //查询范围辖区Id

    private Integer scopeType = 0; //查询范围辖区类型 0根机构 1非根机构

    private Long startTime; //查询开始时间

    private Long endTime;  //查询结束时间

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
