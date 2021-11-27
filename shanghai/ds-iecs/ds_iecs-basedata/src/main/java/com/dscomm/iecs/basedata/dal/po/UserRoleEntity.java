package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:用户角色
 *
 * @author LiLu
 * Date Time 2019/7/30 0030 8:52
 */

@Entity
@Table(name = "jcj_bd_yhjs")
public class UserRoleEntity extends BaseEntity {

    @Column(name = "yh")
    private String userId; //用户ID

    @Column(name = "js")
    private String roleId; //角色

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
