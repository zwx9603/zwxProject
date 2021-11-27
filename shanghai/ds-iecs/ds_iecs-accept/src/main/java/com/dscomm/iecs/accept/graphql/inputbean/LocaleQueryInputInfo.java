package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述：现场信息 保存参数 ( 包含 处警反馈  指令反馈 集结反馈)
 *
 */
public class LocaleQueryInputInfo {

    private String incidentId;//案件ID

    private String commandId  ; // 指挥id

    private   String instructId  ; //指令单id

    private String instructRecordId  ; // 指令记录id

    private List<Integer> localeType ; //类型

    private List<Integer> localeSource  ; //来源

    private String  feedbackObjectId ; // 反馈对象id

    private String keyword  ; // 关键字反馈 现场信息/现场信息补充

    public List<Integer> getLocaleType() {
        return localeType;
    }

    public void setLocaleType(List<Integer> localeType) {
        this.localeType = localeType;
    }

    public List<Integer> getLocaleSource() {
        return localeSource;
    }

    public void setLocaleSource(List<Integer> localeSource) {
        this.localeSource = localeSource;
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

    public String getInstructId() {
        return instructId;
    }

    public void setInstructId(String instructId) {
        this.instructId = instructId;
    }

    public String getInstructRecordId() {
        return instructRecordId;
    }

    public void setInstructRecordId(String instructRecordId) {
        this.instructRecordId = instructRecordId;
    }

    public String getFeedbackObjectId() {
        return feedbackObjectId;
    }

    public void setFeedbackObjectId(String feedbackObjectId) {
        this.feedbackObjectId = feedbackObjectId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
