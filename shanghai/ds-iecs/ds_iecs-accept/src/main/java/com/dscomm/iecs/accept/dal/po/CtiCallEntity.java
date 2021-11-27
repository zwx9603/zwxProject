package com.dscomm.iecs.accept.dal.po;

import org.apache.commons.net.ntp.TimeStamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 13:15
 * @Describe cti 通话记录
 */

@Entity
@Table(name = "cti_call_in_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CtiCallEntity {
    @Id
    @Column(name = "call_seq_id")
    private String id;//主键

    @Column(name = "call_acd")
    private String acd;//acd组

    @Column(name = "caller_no")
    private String callNumber;//主叫号码

    @Column(name = "callee_no")
    private String calledNumber;//被叫号码

    @Column(name = "agent_id")
    private Integer agentNumber;//坐席号

    @Column(name = "call_in_time")
    private Timestamp startTime;//呼入时间

    @Column(name = "call_end_time")
    private Timestamp endTime;//结束时间

    @Column(name = "call_time_len")
    private Integer duration;//持续时间

    @Column(name = "person_id")
    private String personId;//用户id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcd() {
        return acd;
    }

    public void setAcd(String acd) {
        this.acd = acd;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public Integer getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(Integer agentNumber) {
        this.agentNumber = agentNumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
