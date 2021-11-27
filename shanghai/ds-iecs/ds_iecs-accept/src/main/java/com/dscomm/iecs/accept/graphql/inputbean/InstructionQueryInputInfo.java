package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述:  指令单查询参数
 *
 */
public class InstructionQueryInputInfo {

    private String incidentId; // 事件ID

    private String commandId; // 指挥ID

    private List<String> instructionType;// 指令类型 APPROVAL ( 批示 ) INSTRUCTION ( 指令  ) ASK_FOR_LEADER ( 汇报 )  CONSULT_EXPERT( 咨询 ) NOTIFIED ( 通报)

    private List<String> instructionsSource ; // 指令来源  1 接处警 2 图上指挥  3 移动 可拓展

    private String keyword ; // 关键字查询  指令内容/指令内容扩展 模糊

    public List<String> getInstructionsSource() {
        return instructionsSource;
    }

    public void setInstructionsSource(List<String> instructionsSource) {
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

    public List<String> getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(List<String> instructionType) {
        this.instructionType = instructionType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
