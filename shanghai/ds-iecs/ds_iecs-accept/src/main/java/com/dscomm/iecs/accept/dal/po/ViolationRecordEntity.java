package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：违规操作记录
 *
 */
@Entity
@Table(name = "JCJ_WGCZJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ViolationRecordEntity extends BaseEntity {

    @Column(name = "DJBH", length = 100)
    private String billId;//单据编号

    @Column(name = "WGLX", length = 100)
    private String violationType; // 违规类型

    @Column(name = "WGSJ" )
    private Long violationTime;//违规时间

    @Column(name = "BZZ", length = 100)
    private String  standardValue;   //  标准值

    @Column(name = "SJZ", length = 100)
    private String  actualValue;   // 实际值

    @Column(name = "WGRYGH", length = 100)
    private String  violationPersonNumber;   // 违规人员工号

    @Column(name = "WGRYXM", length = 100)
    private String  violationPersonName;   // 违规人员姓名

    @Column(name = "WGZXH", length = 50)
    private String  violationSeatNumber;   // 违规坐席号

    @Column(name = "JGID", length = 100)
    private String organizationId; //机构ID

    @Column(name = "CZMS", length = 800)
    private String  violationDesc;   // 操作描述

    @Column(name = "WGZT", length = 100)
    private String  violationStatus;   // 违规状态(1违规中,2结束违规)

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注


    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public Long getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Long violationTime) {
        this.violationTime = violationTime;
    }

    public String getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getViolationPersonNumber() {
        return violationPersonNumber;
    }

    public void setViolationPersonNumber(String violationPersonNumber) {
        this.violationPersonNumber = violationPersonNumber;
    }

    public String getViolationPersonName() {
        return violationPersonName;
    }

    public void setViolationPersonName(String violationPersonName) {
        this.violationPersonName = violationPersonName;
    }

    public String getViolationSeatNumber() {
        return violationSeatNumber;
    }

    public void setViolationSeatNumber(String violationSeatNumber) {
        this.violationSeatNumber = violationSeatNumber;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getViolationDesc() {
        return violationDesc;
    }

    public void setViolationDesc(String violationDesc) {
        this.violationDesc = violationDesc;
    }

    public String getViolationStatus() {
        return violationStatus;
    }

    public void setViolationStatus(String violationStatus) {
        this.violationStatus = violationStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
