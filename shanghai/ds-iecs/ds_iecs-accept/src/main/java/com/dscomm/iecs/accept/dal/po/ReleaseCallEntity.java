package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/7 19:18
 * @Describe 排队早释记录
 */

@Entity
@Table(name = "cti_pdzsjl")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ReleaseCallEntity extends BaseEntity {

    @Column(name = "pddw")
    private String orgId;//排队单位id
    @Column(name = "dwmc")
    private String orgName;//排队单位名称
    @Column(name = "zx")
    private String agentNumber;//坐席号
    @Column(name = "pdsj")
    private Long queuedTime;//排队时间（振铃时间,开始时间）
    @Column(name = "zssj")
    private Long releasedTime;//早释时间（结束时间）
    @Column(name = "gh")
    private String personNumber;//工号
    @Column(name = "zjhm")
    private String callNumber;//主叫号码


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public Long getQueuedTime() {
        return queuedTime;
    }

    public void setQueuedTime(Long queuedTime) {
        this.queuedTime = queuedTime;
    }

    public Long getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(Long releasedTime) {
        this.releasedTime = releasedTime;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
}
