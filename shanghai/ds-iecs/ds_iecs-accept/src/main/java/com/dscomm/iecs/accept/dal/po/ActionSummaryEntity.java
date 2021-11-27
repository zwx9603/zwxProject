package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：战评表
 *
 */
@Entity
@Table(name = "T_COC_FIRE_ZPZJ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ActionSummaryEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId;//事件ID

    @Column(name = "ZHID", length = 100 )
    private String commandId;//指挥ID

    @Column(name = "ZPRSSDWID", length = 40)
    private String evaluatorOrganizationId;//战评人所属机构ID
    @Column(name = "ZPRSSDWMC", length = 200)
    private String evaluatorOrganizatioName;//战评人所属机构名称

    @Column(name = "ZPRID", length = 40)
    private String evaluatorPersonId;//战评人ID
    @Column(name = "ZPRXM", length = 100)
    private String evaluatorPersonIName;//战评人姓名

    @Column(name = "ZPSJ")
    private Long evaluationTime;//战评时间

    @Column(name = "ZFFS", length = 100)
    private String score;//战评分数

    @Column(name = "ZPNR", length = 4000)
    private String content;//战评内容

    @Column(name = "ZPJD", length = 100)
    private String procedure;//表示评价的是那个战斗环节

    @Column(name = "SJHJSJ")
    private Long procedureTime;//战评事件环节时间

    @Column(name = "JYZJ", length = 1000)
    private String experience;//经验总结

    @Column(name = "JXZJ", length = 1000)
    private String lesson;//教训总结

    @Column(name = "BQ", length = 100)
    private String label;//标签

    @Column(name = "BZ", length = 800 )
    private String remarks;//备注

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
