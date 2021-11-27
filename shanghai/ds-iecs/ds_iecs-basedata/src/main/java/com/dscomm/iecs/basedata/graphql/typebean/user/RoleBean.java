package com.dscomm.iecs.basedata.graphql.typebean.user;

/**
 * 描述:用户角色
 *
 */
public class RoleBean {
    private String roleId; //角色主键
    private String roleCode; //角色编码
    private String roleName; //角色名称

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
}
