package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: zs
 * @Date: 15:58 2020/7/24
 * desc: 领导实体类
 */
@Entity
@Table(name="T_COC_FIRE_LD")
public class LeaderEntity extends BaseEntity {

    @Column(name="XM")
    private String leaderName;//领导姓名
    @Column(name="YDDH")
    private String mobliPhone;//移动电话
    @Column(name="BGDH")
    private String officeNumber;//办公电话
    @Column(name="DWMC")
    private String unitName;//所属单位名称
    @Column(name="ZW")
    private String duty;//职位
    @Column(name="LX")
    private String type;//类型（内部领导或外部领导）

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getMobliPhone() {
        return mobliPhone;
    }

    public void setMobliPhone(String mobliPhone) {
        this.mobliPhone = mobliPhone;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
