package com.dscomm.iecs.keydata.graphql.inputbean;

/**
 * 描述：系统操作日志 保存参数
 *
 */
public class AuditLogSaveInputInfo   {

    private Long operateTime; //操作时间

    private String operateType; //操作类型

    private String organizationId; //消防机构id

    private String organizationName; //消防机构名称

    private String operateSeatNumber; //操作台号

    private String operateSeatName; //操作台名称

    private String acceptancePersonNumber;// 接警员工号

    private String acceptancePersonName;// 接警员姓名

    private String ipAddress; //ip地址

    private String desc; //描述

    private String remarks; // 备注
    private String documentNumber;//单据编号
    private String relevantDocumentNumber;//相关单据编号

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
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

    public String getOperateSeatNumber() {
        return operateSeatNumber;
    }

    public void setOperateSeatNumber(String operateSeatNumber) {
        this.operateSeatNumber = operateSeatNumber;
    }

    public String getOperateSeatName() {
        return operateSeatName;
    }

    public void setOperateSeatName(String operateSeatName) {
        this.operateSeatName = operateSeatName;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getAcceptancePersonName() {
        return acceptancePersonName;
    }

    public void setAcceptancePersonName(String acceptancePersonName) {
        this.acceptancePersonName = acceptancePersonName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getRelevantDocumentNumber() {
        return relevantDocumentNumber;
    }

    public void setRelevantDocumentNumber(String relevantDocumentNumber) {
        this.relevantDocumentNumber = relevantDocumentNumber;
    }
}
