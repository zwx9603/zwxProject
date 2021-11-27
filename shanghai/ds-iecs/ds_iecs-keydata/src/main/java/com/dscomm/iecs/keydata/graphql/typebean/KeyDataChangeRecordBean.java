package com.dscomm.iecs.keydata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;


/**
 * 描述:关键数据变更记录
 *
 */
public class KeyDataChangeRecordBean extends BaseBean {

    private String businessTableName;  //业务表名

    private String businessDataId; //单据ID ( 业务数据id  )

    private String businessField; // 变更业务字段

    private Long changeTime;// 变更时间

    private String originalDate; // 原始数据

    private String changeDate;//变更后数据

    private String organizationId ;// 消防机构编号

    private String personpNumber;//变更人工号

    private String seatNumber; // 坐席号

    private String terminalType; // 终端类型

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

    public String getPersonpNumber() {
        return personpNumber;
    }

    public void setPersonpNumber(String personpNumber) {
        this.personpNumber = personpNumber;
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
