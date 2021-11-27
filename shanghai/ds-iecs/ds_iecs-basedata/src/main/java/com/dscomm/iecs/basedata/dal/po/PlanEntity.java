package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:预案基本信息
 */
@Entity
@Table(name = "YAGL_YAJBXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PlanEntity extends BaseEntity {

    @Column(name = "YJYA_TYWYSBM", length = 100)
    private String idCode; //todo  字段 应急预案_通用唯一识别码

    @Column(name = "MBID", length = 100)
    private String templateId; //模板ID

//    @Column(name = "YAZL", length = 100)
    @Column(name = "YAZLDM", length = 100)
    private String planCategoryCode; //todo  字段 预案种类

//    @Column(name = "YALXDM", length = 100)
    @Column(name = "YJYALXLBDM", length = 100)
    private String planTypeCode; //todo  字段 预案类型代码

    @Column(name = "YABH", length = 100)
    private String planCode; //预案编号

//    @Column(name = "YAMC", length = 200)
    @Column(name = "MC", length = 200)
    private String planName; //todo  字段 预案名称

//    @Column(name = "YAMS", length = 800)
    @Column(name = "YANR_JYQK", length = 800 )
    private String planDesc; //todo  字段 预案描述

    @Column(name = "YAGS_JYQK", length = 2000)
    private String planBrieflyDesc; //todo  字段 预案概述_简要情况

    @Column(name = "YAZTDM", length = 100)
    private String planStatusCode; //预案状态代码

//    @Column(name = "DXID", length = 100)
    @Column(name = "YADX", length = 100)
    private String keyUnitId; //todo  字段 重点单位ID( 对象id )

    @Column(name = "DXMC", length = 200)
    private String objectName; //对象名称

    @Column(name = "DXLXDM", length = 100)
    private String objectTypeCode; //对象类型代码

    @Column(name = "YASXH" )
    private Integer planOrderNum; //预案顺序号

    @Column(name = "BWID", length = 100)
    private String positionId; //部位ID

    @Column(name = "BWMC", length = 200)
    private String positionName; //部位名称

    @Column(name = "GXBBH", length = 100)
    private String mappingVersionNum; //类型与内容项对应

//    @Column(name = "JGID", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String organizationId; //todo  字段  机构ID(消防救援机构_通用唯一识别码)

    @Column(name = "XZQHDM", length = 100)
    private String districtCode; // todo  字段  行政区划代码

    @Column(name = "YABBH", length = 100)
    private String versionNum; //预案版本号

    @Column(name = "BMDJ", length = 100)
    private String securityClassification; //保密等级

    @Column(name = "SFKQY")
    private Integer whetherCrossRegion; //是否跨区域

    @Column(name = "SFMYA")
    private Integer whetherParentPlan; //是否母预案

    @Column(name = "SFWLSJ")
    private Integer whetherExternalSystemData; //是否外部系统数据

    @Column(name = "ZZRID", length = 100)
    private String makePersonId; //制作人ID

//    @Column(name = "ZZRMC", length = 200)
    @Column(name = "YAZZR_XM", length = 200)
    private String makePersonName; //todo  字段  制作人名称

//    @Column(name = "ZZDWID", length = 100)
    @Column(name = "YAZZDW_DWID", length = 100)
    private String makeOrganizationId; //todo  字段  制作单位ID

    @Column(name = "YAZZDW_DWMC", length = 200)
    private String makeOrganizationName; //todo  字段  制作单位名称

//    @Column(name = "ZZRQ")
    @Column(name = "YAZZ_RQSJ")
    private Long makeTime; // todo  字段  制作日期

    @Column(name = "CZYID", length = 100)
    private String operatorId; //数据操作人编号

    @Column(name = "CZYXM", length = 100)
    private String operatorName; //数据操作人姓名

    @Column(name = "CZJG", length = 100)
    private String operateOrganization; //操作机构

    @Column(name = "BZ", length = 800)
    private String remarks; //备注

    @Column(name = "JLZT")
    private Integer JLZT; //记录状态

    @Column(name = "CSZT")
    private Integer CSZT; //传输状态

    @Column(name = "SJC")
    private Long SJC; //时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB; //数据版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID; //业务系统部署

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB; //基库数据版本

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPlanBrieflyDesc() {
        return planBrieflyDesc;
    }

    public void setPlanBrieflyDesc(String planBrieflyDesc) {
        this.planBrieflyDesc = planBrieflyDesc;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getMakeOrganizationName() {
        return makeOrganizationName;
    }

    public void setMakeOrganizationName(String makeOrganizationName) {
        this.makeOrganizationName = makeOrganizationName;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPlanCategoryCode() {
        return planCategoryCode;
    }

    public void setPlanCategoryCode(String planCategoryCode) {
        this.planCategoryCode = planCategoryCode;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public Integer getPlanOrderNum() {
        return planOrderNum;
    }

    public void setPlanOrderNum(Integer planOrderNum) {
        this.planOrderNum = planOrderNum;
    }

    public String getMappingVersionNum() {
        return mappingVersionNum;
    }

    public void setMappingVersionNum(String mappingVersionNum) {
        this.mappingVersionNum = mappingVersionNum;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getSecurityClassification() {
        return securityClassification;
    }

    public void setSecurityClassification(String securityClassification) {
        this.securityClassification = securityClassification;
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

    public String getPlanStatusCode() {
        return planStatusCode;
    }

    public void setPlanStatusCode(String planStatusCode) {
        this.planStatusCode = planStatusCode;
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

    public String getMakePersonName() {
        return makePersonName;
    }

    public void setMakePersonName(String makePersonName) {
        this.makePersonName = makePersonName;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getWhetherParentPlan() {
        return whetherParentPlan;
    }

    public void setWhetherParentPlan(Integer whetherParentPlan) {
        this.whetherParentPlan = whetherParentPlan;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public Integer getWhetherExternalSystemData() {
        return whetherExternalSystemData;
    }

    public void setWhetherExternalSystemData(Integer whetherExternalSystemData) {
        this.whetherExternalSystemData = whetherExternalSystemData;
    }



    public String getOperateOrganization() {
        return operateOrganization;
    }

    public void setOperateOrganization(String operateOrganization) {
        this.operateOrganization = operateOrganization;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}