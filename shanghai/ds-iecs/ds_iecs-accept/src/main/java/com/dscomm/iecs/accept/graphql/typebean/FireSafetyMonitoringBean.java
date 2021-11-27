package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 火场安全监控记录
 *
 */
public class FireSafetyMonitoringBean extends BaseBean {

    private String incidentId;//案件ID

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String personId; //人员ID

    private String personName ; // 人员名称

    private Long enterFireTime;//进入火场时间

    private Long withdrawFireTime;//撤出火场时间

    private Long planWithdrawFireTime;//应撤出火场时间

    private String remarks; // 备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public Long getEnterFireTime() {
        return enterFireTime;
    }

    public void setEnterFireTime(Long enterFireTime) {
        this.enterFireTime = enterFireTime;
    }

    public Long getWithdrawFireTime() {
        return withdrawFireTime;
    }

    public void setWithdrawFireTime(Long withdrawFireTime) {
        this.withdrawFireTime = withdrawFireTime;
    }

    public Long getPlanWithdrawFireTime() {
        return planWithdrawFireTime;
    }

    public void setPlanWithdrawFireTime(Long planWithdrawFireTime) {
        this.planWithdrawFireTime = planWithdrawFireTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
