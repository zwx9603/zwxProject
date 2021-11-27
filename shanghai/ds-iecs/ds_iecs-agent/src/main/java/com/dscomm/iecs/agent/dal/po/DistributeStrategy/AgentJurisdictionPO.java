package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述: 分配坐席接管辖区表
 *
 * @author YangFuxi
 * Date Time 2019/9/19 8:50
 */
@Table(name = "t_fp_cjtxq")
@Entity
public class AgentJurisdictionPO implements Serializable {

    @Id
    @GenericGenerator(name = "generuuid", strategy = "uuid")
    @GeneratedValue(generator = "generuuid")
    @Column(name = "lsh", length = 40)
    private String id;
    @Column(name = "zxh", length = 4)
    private Integer agentNum;
    @Column(name = "cjxqbh", length = 40)
    private String jurisdictionId;
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

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }
}
