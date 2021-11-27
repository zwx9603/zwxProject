package com.dscomm.iecs.agent.graphql.typebean;

/**
 * 描述:班长审核休眠结果
 */
public class AuditAgentSleepBO {
    private String supervisorAgentNum;//班长坐席号
    private String supervisorName;//班长名称
    private String supervisorAccount;//班长坐席号
    private String auditResult;//审核结果
    private Long auditTime;//审核时间
    private String agentNum;//申请者坐席号
    private String reasonCode;//申请原因编码
    private String reason;//申请原因

    public String getSupervisorAgentNum() {
        return supervisorAgentNum;
    }

    public void setSupervisorAgentNum(String supervisorAgentNum) {
        this.supervisorAgentNum = supervisorAgentNum;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorAccount() {
        return supervisorAccount;
    }

    public void setSupervisorAccount(String supervisorAccount) {
        this.supervisorAccount = supervisorAccount;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
