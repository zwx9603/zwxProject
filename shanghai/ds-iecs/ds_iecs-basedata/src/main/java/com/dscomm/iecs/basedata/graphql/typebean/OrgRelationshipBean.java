package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 消防转警的机构编码关系
 */

public class OrgRelationshipBean extends BaseBean {

    private String fireDeptId;  //消防机构ID

    private String fireDeptName;  //消防机构名称

    private String transferType;  //转警类型

    private String transferTypeName;  //转警类型

    private String transferDeptCode;  //转警单位编码

    private String transferDeptName;  //转警单位名称

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

    public String getTransferTypeName() {
        return transferTypeName;
    }

    public void setTransferTypeName(String transferTypeName) {
        this.transferTypeName = transferTypeName;
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

    public String getFireDeptName() {
        return fireDeptName;
    }

    public void setFireDeptName(String fireDeptName) {
        this.fireDeptName = fireDeptName;
    }
}
