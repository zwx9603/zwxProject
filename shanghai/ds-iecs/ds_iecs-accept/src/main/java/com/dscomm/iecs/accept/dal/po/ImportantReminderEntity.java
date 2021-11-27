package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 要事提醒实体类
 *
 * */

@Entity
@Table(name = "T_COC_FIRE_YSTX")
public class ImportantReminderEntity extends BaseEntity {

    @Column(name = "BT")
    private String title;           //要事标题
    @Column(name = "NR" ,length = 1000 )
    private String context;          //要事内容
    @Column(name = "TXRID")
    private String personId;         //填写人id
    @Column(name = "TXRDWID")
    private String personUnitId;   //填写人单位id
    @Column(name = "TXRXM")
    private String personName;   //填写人姓名
    @Column(name = "FBSJ")
    private Long releaseTime;       //发布时间
    @Column(name = "TXRDWMC")
    private String personUnitName; //填写人单位名称
    @Column(name = "KSSJ")
    private Long beginTime;       //开始时间
    @Column(name = "JSSJ")
    private Long endTime;         //结束时间
    @Column(name = "BZ" ,length =  1000 )
    private String remarks ;        //备注

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonUnitId() {
        return personUnitId;
    }

    public void setPersonUnitId(String personUnitId) {
        this.personUnitId = personUnitId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPersonUnitName() {
        return personUnitName;
    }

    public void setPersonUnitName(String personUnitName) {
        this.personUnitName = personUnitName;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
