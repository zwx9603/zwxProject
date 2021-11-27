package com.dscomm.iecs.agent.service.distribute.bean;

/**
 * 描述:坐席案由分配表
 *
 * @author YangFuxi
 * Date Time 2019/9/23 15:37
 */
public class AgentIncidentTypeBean {

    private String id;

    private Integer agentNum;

    private String incidentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Integer agentNum) {
        this.agentNum = agentNum;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }


}
