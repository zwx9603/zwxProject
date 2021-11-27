package com.dscomm.iecs.garage.service.bean;

/**
 * 描述：时间趋势bean
 *
 */

public class TimeTrendBean   {

    private Long startTime;             //开始时间

    private Long endTime;               //结束时间

    private Integer dimensionCount;              // 总数


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

    public Integer getDimensionCount() {
        return dimensionCount;
    }

    public void setDimensionCount(Integer dimensionCount) {
        this.dimensionCount = dimensionCount;
    }
}
