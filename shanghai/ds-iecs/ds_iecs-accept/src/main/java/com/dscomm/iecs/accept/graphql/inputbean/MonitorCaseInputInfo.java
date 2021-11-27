package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * @author Zhuqihong
 * 主动接管websocket对象
 * Date Time 2019/11/8
 */
public class MonitorCaseInputInfo {
    /**
     * 警单信息
     */
    private MonitotTakeOverIncidentInputInfo caseInfoBO;

    /**
     * 坐席号
     */
    private Integer agentNum;

    public MonitotTakeOverIncidentInputInfo getCaseInfoBO() {
        return caseInfoBO;
    }

    public void setCaseInfoBO(MonitotTakeOverIncidentInputInfo caseInfoBO) {
        this.caseInfoBO = caseInfoBO;
    }

    public Integer getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Integer agentNum) {
        this.agentNum = agentNum;
    }
}
