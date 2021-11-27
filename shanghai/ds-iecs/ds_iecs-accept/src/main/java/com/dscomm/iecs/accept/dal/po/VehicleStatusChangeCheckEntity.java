package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：车辆状态审批记录
 *
 */
@Entity
@Table(name = "JCJ_CLZTSPJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VehicleStatusChangeCheckEntity extends BaseEntity {

    @Column(name = "AJID", length = 100)
    private String incidentId;//案件id

    @Column(name = "SQSJ" )
    private Long appliedTime; // 申请时间 todo 新增

    @Column(name = "SQJGBH", length = 100)
    private String appliedOrganizationId; // 申请机构编号

    @Column(name = "SQCLBH", length = 100)
    private String  appliedVehicleId;   //  申请车辆编号

    @Column(name = "BDQCLZTDM", length = 100)
    private String oldVehicleStatusCode;//申请前车辆状态代码

    @Column(name = "CLZTDM", length = 100)
    private String vehicleStatusCode;//申请变更车辆状态代码

    @Column(name = "SQBDSM", length = 800)
    private String appliedChangeDesc;//申请变动描述

    @Column(name = "SPZT" )
    private Integer checkStatus;//审批状态 0拒绝 1同意

    @Column(name = "SPSJ" )
    private Long checkTime;//审批时间

    @Column(name = "SPSM" , length = 800)
    private String checkDesc;//审批说明

    @Column(name = "SPJG" , length = 100)
    private String checkOrganizationId;//审批机构

    @Column(name = "SPYHBH" , length = 100)
    private String checkPersonId;//审批用户编号

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注


    public Long getAppliedTime() {
        return appliedTime;
    }

    public void setAppliedTime(Long appliedTime) {
        this.appliedTime = appliedTime;
    }

    public String getOldVehicleStatusCode() {
        return oldVehicleStatusCode;
    }

    public void setOldVehicleStatusCode(String oldVehicleStatusCode) {
        this.oldVehicleStatusCode = oldVehicleStatusCode;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getAppliedOrganizationId() {
        return appliedOrganizationId;
    }

    public void setAppliedOrganizationId(String appliedOrganizationId) {
        this.appliedOrganizationId = appliedOrganizationId;
    }

    public String getAppliedVehicleId() {
        return appliedVehicleId;
    }

    public void setAppliedVehicleId(String appliedVehicleId) {
        this.appliedVehicleId = appliedVehicleId;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getAppliedChangeDesc() {
        return appliedChangeDesc;
    }

    public void setAppliedChangeDesc(String appliedChangeDesc) {
        this.appliedChangeDesc = appliedChangeDesc;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getCheckOrganizationId() {
        return checkOrganizationId;
    }

    public void setCheckOrganizationId(String checkOrganizationId) {
        this.checkOrganizationId = checkOrganizationId;
    }

    public String getCheckPersonId() {
        return checkPersonId;
    }

    public void setCheckPersonId(String checkPersonId) {
        this.checkPersonId = checkPersonId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
