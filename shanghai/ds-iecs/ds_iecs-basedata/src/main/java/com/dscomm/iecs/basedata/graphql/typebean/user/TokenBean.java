package com.dscomm.iecs.basedata.graphql.typebean.user;

/**
 * 描述:token信息
 *
 * @author YangFuxi
 * Date Time 2019/8/27 10:04
 */
public class TokenBean {
    private Long utcTime;//访问世间
    private String currentIP;//IP
    private String icpType;//ICP类型 0 接处警 需要认证坐席与账号( 坐席 ) 1 接处警 账号认证   2 实战指挥 需要认证账号
    private String userAccount;//用户账号
    private String userPassword;//密码
    private String agentState;//坐席状态
    private String agentOperateState;//坐席操作状态
    private String agentPhoneState;//坐席话机状态
    private Boolean needAuthenticate=false;//是否分权分域

    public Boolean getNeedAuthenticate() {
        return needAuthenticate;
    }

    public void setNeedAuthenticate(Boolean needAuthenticate) {
        this.needAuthenticate = needAuthenticate;
    }

    public Long getUtcTime() {
        return utcTime;
    }

    public void setUtcTime(Long utcTime) {
        this.utcTime = utcTime;
    }

    public String getCurrentIP() {
        return currentIP;
    }

    public void setCurrentIP(String currentIP) {
        this.currentIP = currentIP;
    }

    public String getIcpType() {
        return icpType;
    }

    public void setIcpType(String icpType) {
        this.icpType = icpType;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getAgentState() {
        return agentState;
    }

    public void setAgentState(String agentState) {
        this.agentState = agentState;
    }

    public String getAgentOperateState() {
        return agentOperateState;
    }

    public void setAgentOperateState(String agentOperateState) {
        this.agentOperateState = agentOperateState;
    }

    public String getAgentPhoneState() {
        return agentPhoneState;
    }

    public void setAgentPhoneState(String agentPhoneState) {
        this.agentPhoneState = agentPhoneState;
    }
}
