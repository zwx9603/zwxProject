package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:系统角色
 *
 * @author LiLu
 * Date Time 2019/7/29 0029 17:29
 */
@Entity
@Table(name = "jcj_bd_xtjs")
public class SystemRoleEntity extends BaseEntity {

    @Column(name = "bh")
    private String roleCode; //编号

    @Column(name = "mc")
    private String name; //名称

    @Column(name = "jsms")
    private String roleDescribe; //角色描述

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
