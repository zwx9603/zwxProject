package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 预案信息记录 ( 预案文本信息 )
 *
 */
@Entity
@Table(name = "T_COC_FIRE_YAWBXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PlanWordEntity extends BaseEntity {

    @Column(name="YADL" , length =  100 )
    private String planTypeCode;// 预案统计分类

    @Column(name="YALX", length =  100 )
    private String incidentTypeCode ;//案件类型编码

    @Column(name="YAMC", length =  200 )
    private String planName;// 预案名称

    @Column(name="YASYJGBM", length =  100 )
    private String organizationCode;// 预案使用机构编码

    @Column(name="SFZDDWYA"  )
    private Integer  whetherKeyUnit;//是否重点单位预案 0非重点单位预案  1重点单位预案

    @Column(name="ZDDWBM", length =  100 )
    private String keyUnitId ;//重点单位id

    @Column(name = "BZ", length = 800)
    private String remarks; //备注


    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getPlanTypeCode() {
        return planTypeCode;
    }

    public void setPlanTypeCode(String planTypeCode) {
        this.planTypeCode = planTypeCode;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getWhetherKeyUnit() {
        return whetherKeyUnit;
    }

    public void setWhetherKeyUnit(Integer whetherKeyUnit) {
        this.whetherKeyUnit = whetherKeyUnit;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
