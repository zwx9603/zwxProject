package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:系统用户
 *
 * @author LiLu
 * Date Time 2019/7/29 0029 18:08
 */
@Entity
@Table(name = "jcj_bd_xtyh")
public class SystemUserEntity extends BaseEntity {

    @Column(name = "xm")
    private String name; //姓名

    @Column(name = "rybh")
    private String personId; //人员编号

    @Column(name = "zh")
    private String userCode; //账号

    @Column(name = "mm")
    private String password; //密码

    @Column(name = "ssdw")
    private String organizationCode; //所属单位

    @Column(name = "SCBZ")
    private Integer deleteState; //删除标志 0

    @Column(name = "zdzt")
    private Integer terminalState; //终端状态

    @Column(name = "repassword")
    private String rePassword; //repassword

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getTerminalState() {
        return terminalState;
    }

    public void setTerminalState(Integer terminalState) {
        this.terminalState = terminalState;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
