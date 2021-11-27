package com.dscomm.iecs.accept.graphql.firetypebean;

public class InstructionRecordBean {
    private String incidentId;// 事件ID

    private String commandId;// 指挥ID

    private String instructionId;// 指令单ID

    private String receivingObjectType;// 受令对象类型 可参考InstructionsTypeEnum 枚举
    /**
     *     VEHICLE("VEHICLE","车辆"),
     *     PERSON("PERSON","人员"),
     *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
     *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
     *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
     */
    private String receivingObjectId;// 受令对象ID

    private String receivingObjectName;// 受令对象名称

    private String instructState;// 指令单状态（已下达、已签收、已反馈） 可参考InstructionsTypeEnum 枚举
    /**
     *     STATUS_GIVEN("STATUS_GIVEN","已下达"),
     *     STATUS_SIGNED("STATUS_SIGNED","已签收"),
     */

    private Long instructionRecordTime;// 指令下达时间

    private Long receivingTime;//接收时间

    private Long timeout;//超时时间

    private String remarks;// 备注

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

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
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

    public Long getInstructionRecordTime() {
        return instructionRecordTime;
    }

    public void setInstructionRecordTime(Long instructionRecordTime) {
        this.instructionRecordTime = instructionRecordTime;
    }

    public Long getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Long receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
