package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/10 13:52
 * @Describe 排队早释保存
 */
public class ReleaseCallSaveInputInfo {

    private String orgId;//排队单位

    private String agentNumber;//坐席号

    private Long queuedTime;//排队时间（振铃时间,开始时间）

    private Long releasedTime;//早释时间（结束时间）

    private String personNumber;//工号

    private String callNumber;//主叫号码
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public Long getQueuedTime() {
        return queuedTime;
    }

    public void setQueuedTime(Long queuedTime) {
        this.queuedTime = queuedTime;
    }

    public Long getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(Long releasedTime) {
        this.releasedTime = releasedTime;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
}
