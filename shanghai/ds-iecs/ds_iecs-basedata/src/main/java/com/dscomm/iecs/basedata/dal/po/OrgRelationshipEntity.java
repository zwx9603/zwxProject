package com.dscomm.iecs.basedata.dal.po;
 



import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消防转公安的机构编码关系表实体类
 */
@Entity
@Table(name = "JCJ_XFGLGA")
@DynamicInsert
@DynamicUpdate
public class OrgRelationshipEntity extends BaseEntity {

    @Column(name = "XFJG_ID", length =  40 )
    private String fireDeptId;  //消防机构ID

    @Column(name = "ZJLX", length =  200 )
    private String transferType;  //转警类型

    @Column(name = "ZJDWMC", length =  200 )
    private String transferDeptName;  //转警单位名称

    @Column(name = "ZJDWBM", length =  100 )
    private String transferDeptCode;  //转警单位编码/id

    @Column(name = "SFSH", length =  10 )
    private Integer isAudit;  //是否审核

    @Column(name = "ZJQTCZONE", length =  200 )
    private String transferOtherHandleOne ;  //转警单位其他操作1

    @Column(name = "ZJQTCZTWO", length =  200 )
    private String transferOtherHandleTwo ;  //转警单位其他操作2

    @Column(name = "ZJQTCZTHREE", length =  200 )
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
