package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * @Author: zs
 * @Date: 16:03 2020/7/24
 * desc: 领导bean
 */
public class LeaderBean extends BaseBean {

    private String leaderName;//领导姓名
    private String mobliPhone;//移动电话
    private String officeNumber;//办公电话
    private String unitName;//所属单位名称
    private String duty;//职位
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
