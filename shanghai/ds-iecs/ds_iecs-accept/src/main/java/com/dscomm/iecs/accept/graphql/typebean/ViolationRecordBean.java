package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：违规操作记录
 *
 */
public class ViolationRecordBean extends BaseBean {

    private String billId;//单据编号

    private String violationType; // 违规类型

    private String violationTypeName; // 违规类型

    private Long violationTime;//违规时间

    private String  standardValue;   //  标准值

    private String  actualValue;   // 实际值

    private String  violationPersonNumber;   // 违规人员工号

    private String  violationPersonName;   // 违规人员姓名

    private String  violationSeatNumber;   // 违规坐席号

    private String organizationId; //机构ID

    private String organizationName; //机构名称

    private String  violationDesc;   // 操作描述

    private String  violationStatus;   // 违规状态(1违规中,2结束违规)

    private String remarks; // 备注

    public String getViolationTypeName() {
        return violationTypeName;
    }

    public void setViolationTypeName(String violationTypeName) {
        this.violationTypeName = violationTypeName;
    }

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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
