package com.dscomm.iecs.keydata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：系统操作日志
 *
 */
@Entity
@Table(name="JCJ_XTDLJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AuditLogEntity extends BaseEntity {

    @Column(name = "CZSJ" )
    private Long operateTime; //操作时间

    @Column(name = "CZLX", length = 100)
    private String operateType; //操作类型

    @Column(name = "JGID",   length = 100)
    private String organizationId; //消防机构id

    @Column(name = "JGMC",   length = 200)
    private String organizationName; //消防机构名称

    @Column(name = "CZTH",   length = 50)
    private String operateSeatNumber; //操作台号

    @Column(name = "CZTHMC",   length = 200)
    private String operateSeatName; //操作台名称

    @Column(name = "JJYGH",   length = 100)
    private String acceptancePersonNumber;// 接警员工号

    @Column(name = "JJYMC",   length = 100)
    private String acceptancePersonName;// 接警员姓名

    @Column(name = "IPDZ",   length = 100)
    private String ipAddress; //ip地址

    @Column(name = "MS",   length = 800)
    private String desc; //描述

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注
    @Column(name = "DJBH")
    private String documentNumber;//单据编号
    @Column(name = "XGDJBH")
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
