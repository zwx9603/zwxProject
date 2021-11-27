package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:坐席 最近 登录用户
 */
@Entity
@Table(name = "jcj_bd_zx_zxdl")
public class AgentAccountEntity extends BaseEntity {

    /**
     * id 为坐席台号
     */

    @Column(name = "th")
    private String agentNumber; //台号ip地址

    @Column(name = "thip")
    private String agentIp; //台号ip地址

    @Column(name = "latesttime")
    private Long latesttime; //最新时间

    @Column(name = "account")
    private String account; // 账号

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getAgentIp() {
        return agentIp;
    }

    public void setAgentIp(String agentIp) {
        this.agentIp = agentIp;
    }

    public Long getLatesttime() {
        return latesttime;
    }

    public void setLatesttime(Long latesttime) {
        this.latesttime = latesttime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
