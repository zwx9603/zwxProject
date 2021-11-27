package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:ACD组
 *
 * Date Time 2019/7/25 0025 16:07
 */
@Entity
@Table(name = "jcj_bd_acd")
public class AcdEntity extends BaseEntity {


    /**
     * 序号
     */
    @Column(name = "xh", length = 400)
    private String number;

    /**
     * system
     */
    @Column(name = "acd", length = 400)
    private String acd;
    /**
     * 描述
     */
    @Column(name = "ms", length = 200)
    private String describe;
    /**
     * 警种
     */
    @Column(name = "jz", length = 200)
    private String policeType;
    /**
     * 话务类型
     */
    @Column(name = "hwlx", length = 200)
    private String callType;
    /**
     * 报警号码
     */
    @Column(name = "bjhm", length = 200)
    private String alarmNumber;
    /**
     * 时间戳
     */
    @Column(name = "sjc")
    private Long sjc;
    /**
     * 字典项id
     */
    @Column(name = "zdxid", length = 50)
    private String id;
    /**
     * 单位编号
     */
    @Column(name = "dwbh", length = 40)
    private String orgCode;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAcd() {
        return acd;
    }

    public void setAcd(String acd) {
        this.acd = acd;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}

