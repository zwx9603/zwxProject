package com.dscomm.iecs.accept.graphql.firetypebean;



import java.util.List;

public class InjuriesAndDeathsBean {
    private Long startTime;             //开始时间
    private Long endTime;               //结束时间
    private Long injuriesNum  ;//受伤人数d
    private Long deathsNum  ;//死亡人数
    private List<InjuriesAndDeathsNumBean> beans;    //总数构成信息

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

    public List<InjuriesAndDeathsNumBean> getBeans() {
        return beans;
    }

    public void setBeans(List<InjuriesAndDeathsNumBean> beans) {
        this.beans = beans;
    }

    public Long getInjuriesNum() {
        return injuriesNum;
    }

    public void setInjuriesNum(Long injuriesNum) {
        this.injuriesNum = injuriesNum;
    }

    public Long getDeathsNum() {
        return deathsNum;
    }

    public void setDeathsNum(Long deathsNum) {
        this.deathsNum = deathsNum;
    }
}
