package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 重大案件判定规则
 */
@Entity
@Table(name = "JCJ_ZDJQGZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MajorIncidentRuleEntity extends BaseEntity {
    @Column(name = "AJLX" , length =  100 )
    private String incidentType; //案件类型
    @Column(name = "GZLX" , length =  100 )
    private String ruleType; //规则类型
    @Column(name = "PDZ" , length =  255 )
    private String judgmentValue; //判定值
    @Column(name = "YXJ" , length =  32 )
    private int priority; //优先级
    @Column(name = "MS"  )
    private String describe; //描述

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getJudgmentValue() {
        return judgmentValue;
    }

    public void setJudgmentValue(String judgmentValue) {
        this.judgmentValue = judgmentValue;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
