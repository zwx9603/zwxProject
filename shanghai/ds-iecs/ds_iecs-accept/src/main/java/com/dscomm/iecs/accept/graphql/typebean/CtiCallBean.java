package com.dscomm.iecs.accept.graphql.typebean;


import java.sql.Timestamp;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 14:26
 * @Describe
 */
public class CtiCallBean {

    private String personId;//用户id
    private Integer duration;//持续时间
    private Long endTime;//结束时间
    private Long startTime;//呼入时间
    private Integer agentNumber;//坐席号
    private String calledNumber;//被叫号码
    private String callNumber;//主叫号码
    private String acd;//acd组
    private String id;//主键
    private String name;//用户名
    private String orgName;//用户所属机构名称

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(Integer agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getAcd() {
        return acd;
    }

    public void setAcd(String acd) {
        this.acd = acd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
