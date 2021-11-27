package com.dscomm.iecs.agent.graphql.typebean;

/**
 *  上下岗记录
 */
public class LogRecordBean {

    private String personName;  //人员姓名

    private String personNumber;//人员工号

    private Long loginTime ; // 登录时间

    private Long logoutTime ; //退出时间

    private Long  duration ; // 登录时间

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
