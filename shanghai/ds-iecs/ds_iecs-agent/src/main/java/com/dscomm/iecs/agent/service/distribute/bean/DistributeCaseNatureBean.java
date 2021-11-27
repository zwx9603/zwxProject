package com.dscomm.iecs.agent.service.distribute.bean;

/**
 * 描述:处警台案由分配表
 *
 * @author Zhuqihong
 * Date Time 2019/9/26
 */
public class DistributeCaseNatureBean  {

    private String id;

    private Integer agentNum; //坐席号

    private String caseNatureId; ; //案由字符串


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

    public String getCaseNatureId() {
        return caseNatureId;
    }

    public void setCaseNatureId(String caseNatureId) {
        this.caseNatureId = caseNatureId;
    }

}
