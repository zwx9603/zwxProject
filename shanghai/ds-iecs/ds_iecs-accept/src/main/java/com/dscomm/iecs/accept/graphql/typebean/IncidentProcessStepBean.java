package com.dscomm.iecs.accept.graphql.typebean;



import java.util.List;

/**
 * 描述：案件状态进程步骤
 *
 * @author YangFuXi Date Time 2018/7/6 15:06
 */
public class IncidentProcessStepBean {
    //警情状态代码
    private String statusCode;
    //警情状态名称
    private String statusName;
    //环节开始时间
    private Long startTime;
    //环节结束时间
    private Long endTime;
    //持续时间
    private Long duration;
    //时间节点集合
    //private List<TimeNodeBean> timeNodes;
    //时间节点集合
    private List<TimeNodeBean> timeNode;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List<TimeNodeBean> getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(List<TimeNodeBean> timeNode) {
        this.timeNode = timeNode;
    }
}
