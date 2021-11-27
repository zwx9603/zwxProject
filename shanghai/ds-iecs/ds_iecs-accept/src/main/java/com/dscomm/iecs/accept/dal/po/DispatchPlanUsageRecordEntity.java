package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:调派方案使用记录
 * author:YangFuXi
 * Date:2021/6/21 Time:14:07
 **/
@Entity
@Table(name = "JCJ_DPFASYJL")
public class DispatchPlanUsageRecordEntity extends BaseEntity {
    @Column(name = "FAID")
    private String planId;//方案id
    @Column(name = "FALX")
    private String planType;//方案类型
    @Column(name = "AJID")
    private String incidentId;//案件编号
    @Column(name = "CJJLID")
    private String handleId;//处警记录id
    @Column(name = "CJYBH")
    private String personId;//处警员编号
    @Column(name = "CJYXM")
    private String personName;//处警员姓名
    @Column(name = "CJYJGID")
    private String orgId;//处警单位id
    @Column(name = "BZ")
    private String remark;//备注

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
