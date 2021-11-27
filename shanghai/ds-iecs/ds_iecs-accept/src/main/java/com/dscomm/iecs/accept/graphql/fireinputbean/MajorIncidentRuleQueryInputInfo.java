package com.dscomm.iecs.accept.graphql.fireinputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

public class MajorIncidentRuleQueryInputInfo {
    private String id;
    private String incidentType; //案件类型
    private String ruleType; //规则类型
    private String judgmentValue; //判定值
    private int priority; //优先级
    private String describe; //描述
    private Boolean whetherPage = false ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

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

    public Boolean getWhetherPage() {
        return whetherPage;
    }

    public void setWhetherPage(Boolean whetherPage) {
        this.whetherPage = whetherPage;
    }

    public PaginationInputInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInputInfo pagination) {
        this.pagination = pagination;
    }
}
