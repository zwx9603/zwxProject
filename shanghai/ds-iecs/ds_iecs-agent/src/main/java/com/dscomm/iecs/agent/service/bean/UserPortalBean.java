package com.dscomm.iecs.agent.service.bean;

import java.util.List;

public class UserPortalBean {

    private String         userId;           //用户ID
    private String         userKey;          //用户Key
    private String         userName;         //用户名称
    private String         systemName;       //用户账号（系统名称）
    private String         password;         //密码（已加密）
    private Long           createTime;       //创建时间
    private Boolean        isEnabled;        //是否有效
    private String         currentSkin;      //用户当前皮肤
    private String         remark;           //备注
    private String         defaultUnit;      //用户默认所属单位ID
    private String         defaultUnitName;  //用户默认所属单位名称
    private String          lang ;
    private PersonPortalBean       person;           //用户对应人员
    private List<RolePortalBean> roles;            //系统角色集合，此处为null

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getCurrentSkin() {
        return currentSkin;
    }

    public void setCurrentSkin(String currentSkin) {
        this.currentSkin = currentSkin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public String getDefaultUnitName() {
        return defaultUnitName;
    }

    public void setDefaultUnitName(String defaultUnitName) {
        this.defaultUnitName = defaultUnitName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public PersonPortalBean getPerson() {
        return person;
    }

    public void setPerson(PersonPortalBean person) {
        this.person = person;
    }

    public List<RolePortalBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePortalBean> roles) {
        this.roles = roles;
    }
}
