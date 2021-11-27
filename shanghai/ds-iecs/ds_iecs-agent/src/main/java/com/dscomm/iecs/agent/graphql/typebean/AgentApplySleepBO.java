package com.dscomm.iecs.agent.graphql.typebean;

/**
 * 描述:申请休眠信息
 *
 * @author YangFuxi
 * Date Time 2019/11/15 15:01
 */
public class AgentApplySleepBO {
    private String agentNum;//坐席号
    private String userAccount;//用户账号
    private String userName;//用户名
    private String orgId;//单位id
    private String orgCode;//单位code
    private String orgName;//单位名称
    private String reasonCode;//休眠原因code
    private String reason;//休眠原因
    private Long applayTime;//申请时间

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public Long getApplayTime() {
        return applayTime;
    }

    public void setApplayTime(Long applayTime) {
        this.applayTime = applayTime;
    }
}
