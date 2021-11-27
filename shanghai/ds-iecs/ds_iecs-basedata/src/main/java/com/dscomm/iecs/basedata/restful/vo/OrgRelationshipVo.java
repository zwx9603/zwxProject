package com.dscomm.iecs.basedata.restful.vo;


/**
 * 消防转公安的机构编码关系
 */

public class OrgRelationshipVo {
    private String transferType;  //转警类型

    private String transferDeptCode;  //转警单位编码

    private Integer isAudit;  //是否审核

    private String fireDeptId;  //消防机构ID

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
