package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/10 13:55
 * @Describe 排队早释查询
 */
public class ReleaseCallQueryInputInfo {

    private Long startTime;//开始时间
    private Long endTime;//结束时间
    private String orgId;//排队单位

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
