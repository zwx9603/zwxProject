package com.dscomm.iecs.agent.service.distribute.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述:案由坐席信息
 *
 */
public class CauseFoAgentBean{

    private String id;//案由id

    private String code;//案由编码

    private String name;//案由名称

    private Set<AgentBrieflyBean> agents=new HashSet<>();//坐席列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AgentBrieflyBean> getAgents() {
        return agents;
    }

    public void setAgents(Set<AgentBrieflyBean> agents) {
        this.agents = agents;
    }


}
