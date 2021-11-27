package com.dscomm.iecs.basedata.graphql.inputbean;

import java.util.List;

/**
 * 描述:预案基本信息 保存修改参数
 */
public class PlanSaveInputInfo {

    private String id ; //主键

    private String keyUnitId; //重点单位ID( 对象id )

    private String templateId;   //模板ID

    private String planType;   //预案种类

    private String planCode;   //预案编号

    private Integer planOrderNum;   //预案顺序号

    private String mappingVersionNum;   //类型与内容项对应

    private String versionNum;   //预案版本号

    private String securityClassification;   //保密等级

    private String planTypeCode;   //预案类型代码

    private String planName;   //预案名称

    private String planDesc; //预案描述

    private String positionId;   //部位ID

    private String positionName;   //部位名称

    private String planStatusCode;   //预案状态代码

    private String objectTypeCode;   //对象类型代码

    private Integer whetherCrossRegion;   //是否跨区域

    private String makePersonId;   //制作人ID

    private String makePersonName;   //制作人名称

    private String makeOrganizationId;   //制作单位ID

    private String makeOrganizationName;   //制作单位ID

    private Long makeTime;   //制作日期

    private String operatorId;   //数据操作人编号

    private String operatorName;   //数据操作人姓名

    private Integer whetherParentPlan;   //是否母预案

    private String organizationId;   //机构ID

    private String organizationName;   //机构ID

    private String objectName;   //对象名称

    private Integer whetherExternalSystemData;   //是否外部系统数据

    private String operateOrganization;   //操作机构

    private String remarks;   //备注

    private List<PlanDispatchSaveInputInfo> planDispatchSaveInputInfo ; //预案调派信息

    private List<AttachmentSaveInputInfo> attachmentSaveInputInfo;//附件信息

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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

    public String getMakeOrganizationName() {
        return makeOrganizationName;
    }

    public void setMakeOrganizationName(String makeOrganizationName) {
        this.makeOrganizationName = makeOrganizationName;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
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

    public List<PlanDispatchSaveInputInfo> getPlanDispatchSaveInputInfo() {
        return planDispatchSaveInputInfo;
    }

    public void setPlanDispatchSaveInputInfo(List<PlanDispatchSaveInputInfo> planDispatchSaveInputInfo) {
        this.planDispatchSaveInputInfo = planDispatchSaveInputInfo;
    }

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public List<AttachmentSaveInputInfo> getAttachmentSaveInputInfo() {
        return attachmentSaveInputInfo;
    }

    public void setAttachmentSaveInputInfo(List<AttachmentSaveInputInfo> attachmentSaveInputInfo) {
        this.attachmentSaveInputInfo = attachmentSaveInputInfo;
    }
}