package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述:第三方用户信息
 *
 * @author YangFuxi
 * Date Time 2019/10/17 9:56
 */
public class SubUserSaveInputInfo {
    private String userAccount;//用户(接警员)账号
    private String userName;//用户名称
    private String agentNum;//坐席号码
    private String areaCode;//辖区
    private String orgCode;//用户所属机构
    private String orgName;//用户名称
    private String ip;
    private String system;//所属系统


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

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
