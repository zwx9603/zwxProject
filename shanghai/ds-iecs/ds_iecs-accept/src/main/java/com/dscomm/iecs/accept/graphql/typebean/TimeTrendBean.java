package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述：时间趋势bean
 *
 */

public class TimeTrendBean extends DimensionAssembleStatisticsBean {

    private Long startTime;             //开始时间

    private Long endTime;               //结束时间


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
