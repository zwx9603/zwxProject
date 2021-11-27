package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：指令记录
 *
 * @author ZYL Date： 2018年5月6日 上午9:48:18
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_COC_FIRE_ZLJL")
public class InstructionRecordEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId;// 事件ID

    @Column(name = "ZHID", length = 100 )
    private String commandId;// 指挥ID

    @Column(name = "ZLDID", length = 100 )
    private String instructionId;// 指令单ID


    @Column(name = "SLLX", length = 100)
    private String receivingObjectType;// 受令对象类型 可参考InstructionsTypeEnum 枚举
    /**
     *     ORGANIZATION("ORGANIZATION","机构"),
     *     VEHICLE("VEHICLE","车辆"),
     *     PERSON("PERSON","人员"),
     *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
     *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
     *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
     */
//    @Column(name = "SLDWID", length = 100)
    @Column(name = "JSR_ID", length = 100)
    private String receivingObjectId;//todo 字段 受令对象ID

//    @Column(name = "SLDXMC", length = 200)
    @Column(name = "JSR_XM", length = 200)
    private String receivingObjectName;// todo 字段 受令对象名称

    @Column(name = "JSDW_TYWYSBM", length = 100)
    private String receivingObjectOrganizationId;// todo 字段 受令对象单位ID

    @Column(name = "JSDW_TYWYSMC", length = 200)
    private String receivingObjectOrganizationName;//todo 字段 受令对象单位名称

    @Column(name = "ZT", length = 100)
    private String instructState;// 指令单状态（已下达、已签收 ） 可参考InstructionsTypeEnum 枚举
    /**
     *     STATUS_GIVEN("STATUS_GIVEN","已下达"),
     *     STATUS_SIGNED("STATUS_SIGNED","已签收"),
     */

    @Column(name = "XDSJ")
    private Long instructionRecordTime;// 指令下达时间

    @Column(name = "JSSJ")
    private Long receivingTime;//接收时间

    @Column(name = "CSSJ")
    private Long timeout;//超时时间

    @Column(name = "CLJG_PDBZ" )
    private Integer handleSign ;// todo 字段 处理结果 判断标识

    @Column(name = "CLJG_JYQK", length = 1000 )
    private String handleResultDesc;// todo 字段 处理结果 简要情况

    @Column(name = "CLR_XM", length = 100)
    private String handlePersonName;// todo 字段 处理人_姓名

    @Column(name = "CHL_RQSJ" )
    private Long handleTime ;//todo 字段 处理_日期时间

    @Column(name = "BZ", length = 800)
    private String remarks;// 备注

    public Long getInstructionRecordTime() {
        return instructionRecordTime;
    }

    public void setInstructionRecordTime(Long instructionRecordTime) {
        this.instructionRecordTime = instructionRecordTime;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getReceivingObjectType() {
        return receivingObjectType;
    }

    public void setReceivingObjectType(String receivingObjectType) {
        this.receivingObjectType = receivingObjectType;
    }

    public String getReceivingObjectId() {
        return receivingObjectId;
    }

    public void setReceivingObjectId(String receivingObjectId) {
        this.receivingObjectId = receivingObjectId;
    }

    public String getReceivingObjectName() {
        return receivingObjectName;
    }

    public void setReceivingObjectName(String receivingObjectName) {
        this.receivingObjectName = receivingObjectName;
    }

    public String getInstructState() {
        return instructState;
    }

    public void setInstructState(String instructState) {
        this.instructState = instructState;
    }

    public Long getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Long receivingTime) {
        this.receivingTime = receivingTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getReceivingObjectOrganizationId() {
        return receivingObjectOrganizationId;
    }

    public void setReceivingObjectOrganizationId(String receivingObjectOrganizationId) {
        this.receivingObjectOrganizationId = receivingObjectOrganizationId;
    }

    public String getReceivingObjectOrganizationName() {
        return receivingObjectOrganizationName;
    }

    public void setReceivingObjectOrganizationName(String receivingObjectOrganizationName) {
        this.receivingObjectOrganizationName = receivingObjectOrganizationName;
    }

    public Integer getHandleSign() {
        return handleSign;
    }

    public void setHandleSign(Integer handleSign) {
        this.handleSign = handleSign;
    }

    public String getHandleResultDesc() {
        return handleResultDesc;
    }

    public void setHandleResultDesc(String handleResultDesc) {
        this.handleResultDesc = handleResultDesc;
    }

    public String getHandlePersonName() {
        return handlePersonName;
    }

    public void setHandlePersonName(String handlePersonName) {
        this.handlePersonName = handlePersonName;
    }

    public Long getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Long handleTime) {
        this.handleTime = handleTime;
    }
}
