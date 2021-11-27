package com.dscomm.iecs.agent.service.distribute.bean;

/**
 * 描述:坐席信息(简要信息)
 *
 * @author YangFuxi
 * Date Time 2019/11/20 10:34
 */
public class AgentBrieflyBean  {

    private String agentNum;

    private String account;

    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
