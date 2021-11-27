package com.dscomm.iecs.agent.service.bean;

public class RolePortalBean {

    private String roleId;           //角色id
    private String roleKey;          //角色key
    private String roleCode;         //系统角色编码
    private String roleName;         //系统角色名称
    private String roleDescription; //系统角色描述
    private String availableSystem; //所属系统
    private String roleType;         //角色类型
    private boolean isEnabled;       //是否有效

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getAvailableSystem() {
        return availableSystem;
    }

    public void setAvailableSystem(String availableSystem) {
        this.availableSystem = availableSystem;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
