package com.dscomm.iecs.agent.dal.po.DistributeStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述:分配处警台案由表
 *
 * @author Zhuqihong
 * Date Time 2019/9/26
 */
@Table(name = "t_fp_cjtay")
@Entity
public class DistributeCaseNaturePO implements Serializable {
    @Id
    @Column(name = "lsh", length = 40)
    private String id;
    @Column(name = "zxh", length = 4)
    private Integer agentNum;
    @Column(name = "ay", length = 400)
    private String caseNatureId;
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

    public String getCaseNatureId() {
        return caseNatureId;
    }

    public void setCaseNatureId(String caseNatureId) {
        this.caseNatureId = caseNatureId;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }
}
