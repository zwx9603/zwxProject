package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述 : 坐席acd实体类 从坐席信息构建视图
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/11 11:15
 */
@Entity
@Table(name = "v_bd_zxacd")
public class AgentAcdEntity extends BaseEntity {
    /**
     * 台号
     */
    @Column(name = "th", nullable = false)
    private String agentNumber;
    /**
     * acd
     */
    @Column(name = "acd", length = 10, nullable = false)
    private String acd;
    /**
     * 技能级别
     */
    @Column(name = "jnjb", nullable = false)
    private Integer skillLevel;
    /**
     * 时间戳
     */
    @Column(name = "sjc")
    private Long sjc;


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
