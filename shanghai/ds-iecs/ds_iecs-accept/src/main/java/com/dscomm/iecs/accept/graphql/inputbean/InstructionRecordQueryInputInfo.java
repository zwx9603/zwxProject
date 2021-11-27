package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述：指令记录查询参数
 *
 */
public class InstructionRecordQueryInputInfo {

    private String incidentId; // 事件ID

    private String commandId; // 指挥ID

    private String instructionId;// 调派指令单ID

    private String receivingObjectId;// 受令对象ID

    private List<String>  instructState ;// 指令单状态


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

    public String getReceivingObjectId() {
        return receivingObjectId;
    }

    public void setReceivingObjectId(String receivingObjectId) {
        this.receivingObjectId = receivingObjectId;
    }

    public List<String> getInstructState() {
        return instructState;
    }

    public void setInstructState(List<String> instructState) {
        this.instructState = instructState;
    }
}
