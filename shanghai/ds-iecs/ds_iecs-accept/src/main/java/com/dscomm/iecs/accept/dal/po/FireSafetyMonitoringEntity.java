package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 火场安全监控记录
 *
 */
@Entity
@Table(name = "JCJ_HCAQJKJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FireSafetyMonitoringEntity extends BaseEntity {

    @Column(name = "AJID" , length =  100 )
    private String incidentId;//案件ID

    @Column(name = "XFJGBH", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "XFJGMC", length = 100)
    private String organizationName; // 消防机构名称

    @Column(name = "RYID", length = 100)
    private String personId; //人员ID

    @Column(name = "RYMC", length = 100)
    private String personName ; // 人员名称

    @Column(name = "JRHCSJ")
    private Long enterFireTime;//进入火场时间

    @Column(name = "CCHCSJ")
    private Long withdrawFireTime;//撤出火场时间

    @Column(name = "YCCHCSJ")
    private Long planWithdrawFireTime;//应撤出火场时间

    @Column(name = "BZ", length = 800 )
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
