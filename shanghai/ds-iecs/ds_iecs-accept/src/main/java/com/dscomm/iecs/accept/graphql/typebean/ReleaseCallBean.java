package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述:排队早释电话
 *
 * @author YangFuxi
 * Date Time 2021/7/30 14:27
 */
public class ReleaseCallBean {
    private String orgId;//排队单位id
    private String orgName;//排队单位名称
    private String agentNumber;//坐席号
    private Long queuedTime;//排队时间（振铃时间,开始时间）
    private Long releasedTime;//早释时间（结束时间）
    private String personNumber;//工号
    private String callNumber;//主叫号码

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
