package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:集合演练方案
 *
 */
@Entity
@Table(name = "JCJ_JHYLFA")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DrillPlanEntity extends BaseEntity {

    @Column(name = "AJLX", length = 100)
    private String incidentTypeCode ;//案件类型

    @Column(name = "YLFAMC", length = 200)
    private String drillPlanName ;//演练方案名称

    @Column(name = "YLFANR", length = 800)
    private String drillPlanContent; // 演练方案内容

    @Column(name = "ZZDWBH", length = 200)
    private String makeOrganizationId;//演练方案制作单位

    @Column(name = "ZZRID" )
    private String makePersonId; // 演练方案制作人编号

    @Column(name = "ZZRXM", length = 200)
    private String makePersonName;//演练方案制作人姓名

    @Column(name = "SFQY" )
    private Integer whetherEnable ;//是否启用

    @Column(name = "QYSJ" )
    private Long  enableTime ; //生效时间

    @Column(name = "GQSJ" )
    private Long invalidTime ; //过期时间

    @Column(name = "ZZSJ" )
    private Long  makeTime ;//制作日期

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    public Long getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Long enableTime) {
        this.enableTime = enableTime;
    }

    public Long getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Long invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getDrillPlanName() {
        return drillPlanName;
    }

    public void setDrillPlanName(String drillPlanName) {
        this.drillPlanName = drillPlanName;
    }

    public String getDrillPlanContent() {
        return drillPlanContent;
    }

    public void setDrillPlanContent(String drillPlanContent) {
        this.drillPlanContent = drillPlanContent;
    }

    public String getMakeOrganizationId() {
        return makeOrganizationId;
    }

    public void setMakeOrganizationId(String makeOrganizationId) {
        this.makeOrganizationId = makeOrganizationId;
    }

    public String getMakePersonId() {
        return makePersonId;
    }

    public void setMakePersonId(String makePersonId) {
        this.makePersonId = makePersonId;
    }

    public String getMakePersonName() {
        return makePersonName;
    }

    public void setMakePersonName(String makePersonName) {
        this.makePersonName = makePersonName;
    }

    public Integer getWhetherEnable() {
        return whetherEnable;
    }

    public void setWhetherEnable(Integer whetherEnable) {
        this.whetherEnable = whetherEnable;
    }

    public Long getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Long makeTime) {
        this.makeTime = makeTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
