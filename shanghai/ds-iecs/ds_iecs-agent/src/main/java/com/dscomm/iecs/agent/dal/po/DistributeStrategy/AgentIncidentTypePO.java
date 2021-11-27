package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 描述:分配坐席案由表
 *
 * @author YangFuxi
 * Date Time 2019/9/23 15:37
 */
@Table(name = "t_fp_cjtay")
@Entity
public class AgentIncidentTypePO {
    @Id
    @GenericGenerator(name = "generuuid", strategy = "uuid")
    @GeneratedValue(generator = "generuuid")
    @Column(name = "lsh", length = 40)
    private String id;
    @Column(name = "zxh", length = 4)
    private Integer agentNum;
    @Column(name = "ay", length = 100)
    private String incidentType;
    @Column(name = "sjc", length = 20)
    private Long sjc;

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

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }
}
