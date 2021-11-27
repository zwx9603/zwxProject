package com.dscomm.iecs.keydata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:关键数据变更记录
 *
 */
@Entity
@Table(name = "JCJ_GJSJBDJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class KeyDataChangeRecordEntity extends BaseEntity {

    @Column(name = "YWBM", length = 100 )
    private String businessTableName;  //业务表名

    @Column(name = "DJID", length = 100)
    private String businessDataId; //单据ID ( 业务数据id  )

    @Column(name = "YWBZD", length = 100 )
    private String businessField; // 变更业务字段

    @Column(name = "BGSJ" )
    private Long changeTime;// 变更时间

    @Column(name = "YSSJ", length = 1000)
    private String originalDate; // 原始数据

    @Column(name = "BGHSJ", length = 1000 )
    private String changeDate;//变更后数据

    @Column(name = "XFJGBH", length = 100)
    private String organizationId ;// 消防机构编号

    @Column(name = "BGRGH", length = 50)
    private  String personNumber;//变更人工号

    @Column(name = "ZXH", length = 50)
    private String seatNumber; // 坐席号

    @Column(name = "ZDLX", length = 100)
    private String terminalType; // 终端类型

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public String getBusinessTableName() {
        return businessTableName;
    }

    public void setBusinessTableName(String businessTableName) {
        this.businessTableName = businessTableName;
    }

    public String getBusinessDataId() {
        return businessDataId;
    }

    public void setBusinessDataId(String businessDataId) {
        this.businessDataId = businessDataId;
    }

    public String getBusinessField() {
        return businessField;
    }

    public void setBusinessField(String businessField) {
        this.businessField = businessField;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }

    public String getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(String originalDate) {
        this.originalDate = originalDate;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
