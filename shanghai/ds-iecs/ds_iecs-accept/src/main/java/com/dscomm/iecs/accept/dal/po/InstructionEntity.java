package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：指令单表
 *
 */
@Entity
@Table(name = "T_COC_FIRE_ZLD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InstructionEntity extends BaseEntity {


    @Column(name = "JQZL_TYWYSBM", length = 40)
    private String idCode; //todo 字段  警情指令_通用唯一识别码

//    @Column(name = "AJID", length = 100 )
    @Column(name = "JQ_TYWYSBM", length = 100 )
    private String incidentId; // todo 字段 事件ID 警情_通用唯一识别码

    @Column(name = "ZHID", length = 100 )
    private String commandId; // 指挥ID


    @Column(name = "SJZL", length = 100 )
    private String superiorInstructionId; // 关联上级指令Id

//    @Column(name = "XDDWBM", length = 100)
    @Column(name = "FSDW_TYWYSBM", length = 100)
    private String instructionsOrganizationId ;//todo 字段 指令下达单位编码

//    @Column(name = "XDDWMC", length = 200)
    @Column(name = "FSDW_TYWYMC", length = 200)
    private String instructionsOrganizationName; //todo 字段 指令下达单位名称

//    @Column(name = "XDRID", length = 100)
    @Column(name = "FSR_ID", length = 100)
    private String instructionsPersonId;//todo 字段 指令下达人ID

//    @Column(name = "XDRXM", length = 100)
    @Column(name = "FSR_XM", length = 100)
    private String instructionsPersonName;//todo 字段 指令下达人员姓名 发送人_姓名

    @Column(name = "XDRZWBM", length = 100)
    private String instructionsPersonRank;// 指令下达人员姓名职务编码

    @Column(name = "XDRZW", length = 200)
    private String instructionsPersonRankName;// 指令下达人员姓名职务

    @Column(name = "XDRDCSJ")
    private Long instructionsPersonArriveTime;// 指令下达人员到场时间

    @Column(name = "XDRFHSJ")
    private Long instructionsPersonBackTime;// 指令下达人员返回时间

    @Column(name = "ZLLY" , length =  100 )
    private String  instructionsSource;// 指令来源  1 接处警 2 图上指挥  3 移动 可拓展

//    @Column(name = "XDSJ")
    @Column(name = "FS_RQSJ")
    private Long instructionsTime;//todo 字段 指令下达时间

    @Column(name = "ZLLX", length = 100)
    private String instructionsType;// 指令类型 可参考InstructionsTypeEnum 枚举
    /**
     *     INSTRUCTION("INSTRUCTION","指令"),
     *     ASK_FOR_LEADER("ASK_FOR_LEADER","请示领导"),
     *     CONSULT_EXPERT("CONSULT_EXPERT","咨询专家"),
     *     NOTIFIED("NOTIFIED","通报"),
     *     CONTACT("CONTACT","联系"),
     *     APPROVAL("APPROVAL","批示"),
     */


    @Column(name = "ZLXDFS", length = 100)
    private String instructionsMode;// 指令下达方式 可参考InstructionsTypeEnum 枚举
    /**
     *     TEXT("TEXT","文本"),
     *     SMS("SMS","短信"),
     *     VOICE("VOICE","语音"),
     *     MICRO_GROUP("MICRO_GROUP","微群"),
     */

    @Column(name = "JQZLLXDM", length = 100 )
    private String instructionTypeCode; //todo 字段 警情指令类型代码

    @Column(name = "JQXXLBDM", length = 100 )
    private String instructionCategoryCode; //todo 字段  警情信息类别代码


//    @Column(name = "ZLBT", length = 500)
    @Column(name = "BT_MC", length = 500)
    private String instructionTitle; //todo 字段 指令单标题

//    @Column(name = "ZLNR", length = 4000)
    @Column(name = "XXNR_JYQK", length = 4000)
    private String instructionsContent;//todo 字段 指令单内容

//    @Column(name = "ZLKZ", length = 4000)
    @Column(name = "XXNRKZ_JYQK", length = 4000)
    private String instructionsContentExtension;//todo 字段 指令单内容扩展

    @Column(name = "BZ", length = 800)
    private String remarks; // 备注


    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

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

    public String getInstructionsPersonRank() {
        return instructionsPersonRank;
    }

    public void setInstructionsPersonRank(String instructionsPersonRank) {
        this.instructionsPersonRank = instructionsPersonRank;
    }



}
