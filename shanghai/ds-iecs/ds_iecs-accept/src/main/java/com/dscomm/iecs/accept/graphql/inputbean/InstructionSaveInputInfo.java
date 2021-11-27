package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述: 警情 指令单保存参数
 *
 */
public class InstructionSaveInputInfo {

    private String incidentId; // 事件ID

    private String commandId; // 指挥ID

    private String superiorInstructionId; // 关联上级指令Id

    private String instructionsType;// 指令类型 可参考InstructionsTypeEnum 枚举
    /**
     *     INSTRUCTION("INSTRUCTION","指令"),
     *     ASK_FOR_LEADER("ASK_FOR_LEADER","请示领导"),
     *     CONSULT_EXPERT("CONSULT_EXPERT","咨询专家"),
     *     NOTIFIED("NOTIFIED","通报"),
     *     CONTACT("CONTACT","联系"),
     *     APPROVAL("APPROVAL","批示"),
     */

    private String instructionsOrganizationId ;// 指令下达单位编码

    private String instructionsOrganizationName; // 指令下达单位名称

    private String instructionsPersonId;// 指令下达人ID

    private String instructionsPersonName;// 指令下达人员姓名

    private String instructionsPersonRank;// 指令下达人员姓名职务编码

    private String instructionsPersonRankName;// 指令下达人员姓名职务

    private Long instructionsPersonArriveTime;// 指令下达人员到场时间

    private Long instructionsPersonBackTime;// 指令下达人员返回时间

    private String instructionsSource;// 指令来源  1 接处警 2 实战指挥  3 移动 可拓展

    private Long instructionsTime;// 指令下达时间

    private String instructionsMode;// 指令下达方式 可参考InstructionsTypeEnum 枚举
    /**
     *     TEXT("TEXT","文本"),
     *     SMS("SMS","短信"),
     *     VOICE("VOICE","语音"),
     *     MICRO_GROUP("MICRO_GROUP","微群"),
     */

    private String instructionTitle; // 指令单标题

    private String instructionsContent;// 指令单内容

    private String instructionsContentExtension;// 指令单内容扩展

    private String remarks; // 备注

    private Boolean  whetherInHandle  = false  ; //是否生成处警单 默认false


    private List<InstructionRecordSaveInputInfo> instructionRecordList ; // 指令记录

    private String instructionTypeCode; //  警情指令类型代码

    private String instructionCategoryCode; //   警情信息类别代码

    public String getInstructionTypeCode() {
        return instructionTypeCode;
    }

    public void setInstructionTypeCode(String instructionTypeCode) {
        this.instructionTypeCode = instructionTypeCode;
    }

    public String getInstructionCategoryCode() {
        return instructionCategoryCode;
    }

    public void setInstructionCategoryCode(String instructionCategoryCode) {
        this.instructionCategoryCode = instructionCategoryCode;
    }

    public Long getInstructionsPersonBackTime() {
        return instructionsPersonBackTime;
    }

    public void setInstructionsPersonBackTime(Long instructionsPersonBackTime) {
        this.instructionsPersonBackTime = instructionsPersonBackTime;
    }

    public Boolean getWhetherInHandle() {
        return whetherInHandle;
    }

    public void setWhetherInHandle(Boolean whetherInHandle) {
        this.whetherInHandle = whetherInHandle;
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

    public String getSuperiorInstructionId() {
        return superiorInstructionId;
    }

    public void setSuperiorInstructionId(String superiorInstructionId) {
        this.superiorInstructionId = superiorInstructionId;
    }

    public String getInstructionsType() {
        return instructionsType;
    }

    public void setInstructionsType(String instructionsType) {
        this.instructionsType = instructionsType;
    }

    public String getInstructionsOrganizationId() {
        return instructionsOrganizationId;
    }

    public void setInstructionsOrganizationId(String instructionsOrganizationId) {
        this.instructionsOrganizationId = instructionsOrganizationId;
    }

    public String getInstructionsOrganizationName() {
        return instructionsOrganizationName;
    }

    public void setInstructionsOrganizationName(String instructionsOrganizationName) {
        this.instructionsOrganizationName = instructionsOrganizationName;
    }

    public String getInstructionsPersonId() {
        return instructionsPersonId;
    }

    public void setInstructionsPersonId(String instructionsPersonId) {
        this.instructionsPersonId = instructionsPersonId;
    }

    public String getInstructionsPersonName() {
        return instructionsPersonName;
    }

    public void setInstructionsPersonName(String instructionsPersonName) {
        this.instructionsPersonName = instructionsPersonName;
    }

    public String getInstructionsPersonRankName() {
        return instructionsPersonRankName;
    }

    public void setInstructionsPersonRankName(String instructionsPersonRankName) {
        this.instructionsPersonRankName = instructionsPersonRankName;
    }

    public Long getInstructionsPersonArriveTime() {
        return instructionsPersonArriveTime;
    }

    public void setInstructionsPersonArriveTime(Long instructionsPersonArriveTime) {
        this.instructionsPersonArriveTime = instructionsPersonArriveTime;
    }

    public String getInstructionsSource() {
        return instructionsSource;
    }

    public void setInstructionsSource(String instructionsSource) {
        this.instructionsSource = instructionsSource;
    }

    public Long getInstructionsTime() {
        return instructionsTime;
    }

    public void setInstructionsTime(Long instructionsTime) {
        this.instructionsTime = instructionsTime;
    }

    public String getInstructionsMode() {
        return instructionsMode;
    }

    public void setInstructionsMode(String instructionsMode) {
        this.instructionsMode = instructionsMode;
    }

    public String getInstructionTitle() {
        return instructionTitle;
    }

    public void setInstructionTitle(String instructionTitle) {
        this.instructionTitle = instructionTitle;
    }

    public String getInstructionsContent() {
        return instructionsContent;
    }

    public void setInstructionsContent(String instructionsContent) {
        this.instructionsContent = instructionsContent;
    }

    public String getInstructionsContentExtension() {
        return instructionsContentExtension;
    }

    public void setInstructionsContentExtension(String instructionsContentExtension) {
        this.instructionsContentExtension = instructionsContentExtension;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<InstructionRecordSaveInputInfo> getInstructionRecordList() {
        return instructionRecordList;
    }

    public void setInstructionRecordList(List<InstructionRecordSaveInputInfo> instructionRecordList) {
        this.instructionRecordList = instructionRecordList;
    }

    public String getInstructionsPersonRank() {
        return instructionsPersonRank;
    }

    public void setInstructionsPersonRank(String instructionsPersonRank) {
        this.instructionsPersonRank = instructionsPersonRank;
    }
}
