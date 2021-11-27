package com.dscomm.iecs.accept.dal.po;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:黑名单
 *
 */
@Entity
@Table(name = "EYPB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class BlacklistEntity   {

    @Id
    @Column(name = "DHHM", length = 50)
    private String phoneNumber;//电话号码


    @Column(name = "BJBH", nullable = false, length = 100)
    private String alarmNumber;//报警编号


    @Column(name = "KSSJ" )
    private Long startTime; // 开始时间

    @Column(name = "JSSJ" )
    private Long endTime ;//结束时间

    @Column(name = "YHBH", length = 100)
    private String personId;//用户编号

    @Column(name = "YHMC", length = 100)
    private String personName;//用户名称

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
