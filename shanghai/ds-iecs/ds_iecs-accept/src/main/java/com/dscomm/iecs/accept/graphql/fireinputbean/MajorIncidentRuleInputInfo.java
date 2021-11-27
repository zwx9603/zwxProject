package com.dscomm.iecs.accept.graphql.fireinputbean;

public class MajorIncidentRuleInputInfo {
    private String id;
    private String incidentType; //案件类型
    private String ruleType; //规则类型
    private String judgmentValue; //判定值
    private int priority; //优先级
    private String describe; //描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
