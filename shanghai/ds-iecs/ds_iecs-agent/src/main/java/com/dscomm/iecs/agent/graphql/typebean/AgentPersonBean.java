package com.dscomm.iecs.agent.graphql.typebean;

import com.dscomm.iecs.base.enums.BasicEnumNumberBean;

import java.util.List;

/**
 * 描述:坐席人员信息
 *
 */
public class AgentPersonBean   {
    // 用户id
    private String userId;
    // 用户代码（=人员id）
    private String userCode;
    // 用户登录账号
    private String account;
    // 用户姓名
    private String userName;
    // 所属机构id
    private String personOrgId;
    // 所属机构代码
    private String personOrgCode;
    // 所属机构名称
    private String personOrgName;
    //人员角色 接警员 处警 接警班长 处警班长 技防接警员 高级处警员 高级班长 接处合一
    private List<BasicEnumNumberBean> personRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public String getPersonOrgId() {
        return personOrgId;
    }

    public void setPersonOrgId(String personOrgId) {
        this.personOrgId = personOrgId;
    }

    public String getPersonOrgCode() {
        return personOrgCode;
    }

    public void setPersonOrgCode(String personOrgCode) {
        this.personOrgCode = personOrgCode;
    }

    public String getPersonOrgName() {
        return personOrgName;
    }

    public void setPersonOrgName(String personOrgName) {
        this.personOrgName = personOrgName;
    }

    public List<BasicEnumNumberBean> getPersonRole() {
        return personRole;
    }

    public void setPersonRole(List<BasicEnumNumberBean> personRole) {
        this.personRole = personRole;
    }
}
