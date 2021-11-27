package com.dscomm.iecs.agent.graphql.typebean;

/**
 * 描述:坐席休眠审核参数
 *
 */
public class AuditAgentSleepParamBO {
    private String agentNum,account,auditResult,reasonCode,reason;//坐席号，申请人账号，审核结果(1 同意休眠，0拒绝休眠),申请原因编码，申请原因

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
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
