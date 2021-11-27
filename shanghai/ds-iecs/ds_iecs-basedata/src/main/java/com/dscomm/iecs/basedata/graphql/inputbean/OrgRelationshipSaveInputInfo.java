package com.dscomm.iecs.basedata.graphql.inputbean;


/**
 * 消防转公安的机构编码关系
 */

public class OrgRelationshipSaveInputInfo {


    private String fireDeptId;  //消防机构ID

    private String transferType;  //转警类型

    private String transferDeptName;  //转警单位名称

    private String transferDeptCode;  //转警单位编码

    private Integer isAudit;  //是否审核

    private String transferOtherHandleOne ;  //转警单位其他操作1

    private String transferOtherHandleTwo ;  //转警单位其他操作2

    private String transferOtherHandleThree ;  //转警单位其他操作3

    public String getTransferDeptName() {
        return transferDeptName;
    }

    public void setTransferDeptName(String transferDeptName) {
        this.transferDeptName = transferDeptName;
    }

    public String getTransferOtherHandleOne() {
        return transferOtherHandleOne;
    }

    public void setTransferOtherHandleOne(String transferOtherHandleOne) {
        this.transferOtherHandleOne = transferOtherHandleOne;
    }

    public String getTransferOtherHandleTwo() {
        return transferOtherHandleTwo;
    }

    public void setTransferOtherHandleTwo(String transferOtherHandleTwo) {
        this.transferOtherHandleTwo = transferOtherHandleTwo;
    }

    public String getTransferOtherHandleThree() {
        return transferOtherHandleThree;
    }

    public void setTransferOtherHandleThree(String transferOtherHandleThree) {
        this.transferOtherHandleThree = transferOtherHandleThree;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferDeptCode() {
        return transferDeptCode;
    }

    public void setTransferDeptCode(String transferDeptCode) {
        this.transferDeptCode = transferDeptCode;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public String getFireDeptId() {
        return fireDeptId;
    }

    public void setFireDeptId(String fireDeptId) {
        this.fireDeptId = fireDeptId;
    }
}
