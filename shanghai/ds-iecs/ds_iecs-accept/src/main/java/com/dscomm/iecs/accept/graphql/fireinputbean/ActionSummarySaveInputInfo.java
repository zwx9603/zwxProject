package com.dscomm.iecs.accept.graphql.fireinputbean;

public class ActionSummarySaveInputInfo {

    private String id; //主键

    private String incidentId;//事件ID

    private String commandId;//指挥ID

    private String evaluatorOrganizationId;//战评人所属机构ID

    private String evaluatorOrganizatioName;//战评人所属机构名称

    private String evaluatorPersonId;//战评人ID

    private String evaluatorPersonIName;//战评人姓名

    private Long evaluationTime;//战评时间

    private String score;//战评分数

    private String content;//战评内容

    private String procedure;//表示评价的是那个战斗环节

    private Long procedureTime;//战评事件环节时间

    private String experience;//经验总结

    private String lesson;//教训总结

    private String label;//标签

    private String remarks;//备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getEvaluatorOrganizationId() {
        return evaluatorOrganizationId;
    }

    public void setEvaluatorOrganizationId(String evaluatorOrganizationId) {
        this.evaluatorOrganizationId = evaluatorOrganizationId;
    }

    public String getEvaluatorOrganizatioName() {
        return evaluatorOrganizatioName;
    }

    public void setEvaluatorOrganizatioName(String evaluatorOrganizatioName) {
        this.evaluatorOrganizatioName = evaluatorOrganizatioName;
    }

    public String getEvaluatorPersonId() {
        return evaluatorPersonId;
    }

    public void setEvaluatorPersonId(String evaluatorPersonId) {
        this.evaluatorPersonId = evaluatorPersonId;
    }

    public String getEvaluatorPersonIName() {
        return evaluatorPersonIName;
    }

    public void setEvaluatorPersonIName(String evaluatorPersonIName) {
        this.evaluatorPersonIName = evaluatorPersonIName;
    }

    public Long getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Long evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public Long getProcedureTime() {
        return procedureTime;
    }

    public void setProcedureTime(Long procedureTime) {
        this.procedureTime = procedureTime;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
