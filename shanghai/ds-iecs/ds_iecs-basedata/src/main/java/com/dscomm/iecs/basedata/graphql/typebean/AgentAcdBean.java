package com.dscomm.iecs.basedata.graphql.typebean;

/**
 * 描述 : 坐席acd bo
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/11 11:18
 */
public class AgentAcdBean {
    /**
     * 流水号
     */
    private String id;
    /**
     * 台号
     */
    private String agentNumber;
    /**
     * acd
     */
    private String acd;
    /**
     * 技能级别
     */
    private Integer skillLevel;
    /**
     * 时间戳
     */
    private Long sjc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getAcd() {
        return acd;
    }

    public void setAcd(String acd) {
        this.acd = acd;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }
}
