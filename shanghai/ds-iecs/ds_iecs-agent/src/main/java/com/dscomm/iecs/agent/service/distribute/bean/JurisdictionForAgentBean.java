package com.dscomm.iecs.agent.service.distribute.bean;

import java.util.List;

/**
 * @author Zhuqihong
 * 所有辖区与接管其的坐席集合
 * Date Time 2019/10/29
 */
public class JurisdictionForAgentBean {

    private String id;//辖区ID

    private String name;//辖区名称

    private List<String> agentBOList;//坐席号集合


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAgentBOList() {
        return agentBOList;
    }

    public void setAgentBOList(List<String> agentBOList) {
        this.agentBOList = agentBOList;
    }
}