package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:预案分发索引
 */
@Entity
@Table(name = "YAGL_YAFFSY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PlanDistributionIndexEntity extends BaseEntity {

    @Column(name = "YAID", length = 100)
    private String planId; //预案ID

    @Column(name = "YAZL")
    private Integer planType; //预案种类

    @Column(name = "YABH", length = 100)
    private String planCode; //预案编号

    @Column(name = "YALXDM", length = 100)
    private String planTypeCode; //预案类型代码

    @Column(name = "YAMC", length = 100)
    private String planName; //预案名称

    @Column(name = "DXID", length = 100)
    private String keyUnitId; //重点单位ID

    @Column(name = "BWID", length = 100)
    private String positionId; //部位ID

    @Column(name = "DXLXDM", length = 100)
    private String objectTypeCode; //对象类型代码

    @Column(name = "SFKQY")
    private Integer whetherCrossRegion; //是否跨区域

    @Column(name = "ZZRID", length = 100)
    private String makePersonId; //制作人ID

    @Column(name = "ZZDWID", length = 100)
    private String makeOrganizationId; //制作单位ID

    @Column(name = "ZZRQ")
    private Long makeTime; //制作日期

    @Column(name = "CZYID", length = 100)
    private String operatorId; //操作员ID

    @Column(name = "SFMYA")
    private Integer whetherParentPlan; //是否母预案

    @Column(name = "DXMC", length = 200)
    private String objectName; //对象名称

    @Column(name = "BWMC", length = 200)
    private String positionName; //部位名称

    @Column(name = "JSJGID", length = 100)
    private String receiveOrganizationId; //接受机构ID

    @Column(name = "FFJGID", length = 100)
    private String distributeOrganizationId; //分发机构ID

    @Column(name = "JGID", length = 100)
    private String organizationId; //机构ID

    @Column(name = "JLZT")
    private Integer JLZT; //记录状态

    @Column(name = "CSZT")
    private Integer CSZT; //传输状态

    @Column(name = "SJC")
    private Long SJC; //时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB; //数据版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID; //业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB; //基库数据版本

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
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

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    public Integer getWhetherCrossRegion() {
        return whetherCrossRegion;
    }

    public void setWhetherCrossRegion(Integer whetherCrossRegion) {
        this.whetherCrossRegion = whetherCrossRegion;
    }

    public String getMakePersonId() {
        return makePersonId;
    }

    public void setMakePersonId(String makePersonId) {
        this.makePersonId = makePersonId;
    }

    public String getMakeOrganizationId() {
        return makeOrganizationId;
    }

    public void setMakeOrganizationId(String makeOrganizationId) {
        this.makeOrganizationId = makeOrganizationId;
    }

    public Long getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Long makeTime) {
        this.makeTime = makeTime;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getWhetherParentPlan() {
        return whetherParentPlan;
    }

    public void setWhetherParentPlan(Integer whetherParentPlan) {
        this.whetherParentPlan = whetherParentPlan;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getReceiveOrganizationId() {
        return receiveOrganizationId;
    }

    public void setReceiveOrganizationId(String receiveOrganizationId) {
        this.receiveOrganizationId = receiveOrganizationId;
    }

    public String getDistributeOrganizationId() {
        return distributeOrganizationId;
    }

    public void setDistributeOrganizationId(String distributeOrganizationId) {
        this.distributeOrganizationId = distributeOrganizationId;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}