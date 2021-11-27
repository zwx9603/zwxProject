package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述: 违规记录保存参数
 *
 */
public class ViolationSaveInputInfo  {

    private String id; //主键

    private String billId;//单据编号

    private String violationType; // 违规类型

    private String  standardValue;   //  标准值

    private String  actualValue;   // 实际值

    private String  violationPersonNumber;   // 违规人员工号

    private String  violationPersonName;   // 违规人员姓名

    private String  violationSeatNumber;   // 违规坐席号

    private String organizationId; //机构ID

    private String  violationDesc;   // 操作描述

    private String  violationStatus;   // 违规状态(1违规中,2结束违规)

    private String remarks; // 备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
