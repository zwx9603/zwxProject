package com.dscomm.iecs.accept.graphql.typebean;


/**
 * 描述:指令websocket消息体
 *
 */
public class InstructionWebSocketPushBean {

    private IncidentBean incidentBean; //案件详情

    private InstructionBean instructionBean ; //指令详情

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }

    public InstructionBean getInstructionBean() {
        return instructionBean;
    }

    public void setInstructionBean(InstructionBean instructionBean) {
        this.instructionBean = instructionBean;
    }
}
