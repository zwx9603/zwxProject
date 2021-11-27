package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述:预案基本信息
 */
public class PlanBean extends BaseBean {

    private String idCode; //  应急预案_通用唯一识别码

    private String templateId; //模板ID

    private String planCategoryCode; //预案种类

    private String planCategoryName; //预案种类名称

    private String planTypeCode; //预案类型代码

    private String planTypeName; //预案类型名称

    private String planCode; //预案编号

    private String planName; //预案名称

    private String planDesc; //预案描述

    private String planBrieflyDesc; // 预案概述_简要情况

    private String planStatusCode; //预案状态代码

    private String planStatusName; //预案状态名称

    private String keyUnitId; //重点单位ID( 对象id )

    private String objectName; //对象名称

    private String objectTypeCode; //对象类型代码

    private String objectTypeName; //对象类型名称

    private Integer planOrderNum; //预案顺序号

    private String positionId; //部位ID

    private String positionName; //部位名称

    private String mappingVersionNum; //类型与内容项对应

    private String versionNum; //预案版本号

    private String securityClassification; //保密等级

    private String organizationId; //机构ID

    private String organizationName ; //机构名称

    private Integer whetherCrossRegion; //是否跨区域

    private Integer whetherParentPlan; //是否母预案

    private Integer whetherExternalSystemData; //是否外部系统数据

    private String makePersonId; //制作人ID

    private String makePersonName; //制作人名称

    private String makeOrganizationId; //制作单位ID

    private String makeOrganizationName ; //制作单位名称

    private Long makeTime; //制作日期

    private String operatorId; //数据操作人编号

    private String operatorName; //数据操作人姓名

    private String operateOrganization; //操作机构

    private String remarks; //备注

    private List<AttachmentBean> attachmentBean;//附件信息

    private List<PlanDispatchBean> planDispatch ; //预案调派信息

    private Integer  planDispatchNum = 0  ; //预案调派车辆数量

    private List<String>  planDispatchVehicleIdList ; // 预案调派车辆id

    private Integer dispatchStatusNum = 0  ; //预案可调派车辆数量

    private List<String> dispatchVehicleStatusIdList ; // 预案可调派车辆id

    private List<String> disabledDispatchVehicleIdList ; // 预案不调派车辆id

    private List<String> noVehicleIdList ; // 预案不调派未找到车辆信息 车辆id

    private String  planDispatchTips ; // 预案调派提示信息

    private List<PlanSupplementBean> planSupplementBeanList ; //预案如果 某个类型的车辆缺少 提供补充同类型车辆id ;

    public List<String> getNoVehicleIdList() {
        return noVehicleIdList;
    }

    public void setNoVehicleIdList(List<String> noVehicleIdList) {
        this.noVehicleIdList = noVehicleIdList;
    }

    public List<String> getPlanDispatchVehicleIdList() {
        return planDispatchVehicleIdList;
    }

    public void setPlanDispatchVehicleIdList(List<String> planDispatchVehicleIdList) {
        this.planDispatchVehicleIdList = planDispatchVehicleIdList;
    }


    public Integer getDispatchStatusNum() {
        return dispatchStatusNum;
    }

    public void setDispatchStatusNum(Integer dispatchStatusNum) {
        this.dispatchStatusNum = dispatchStatusNum;
    }

    public List<String> getDispatchVehicleStatusIdList() {
        return dispatchVehicleStatusIdList;
    }

    public void setDispatchVehicleStatusIdList(List<String> dispatchVehicleStatusIdList) {
        this.dispatchVehicleStatusIdList = dispatchVehicleStatusIdList;
    }

    public List<String> getDisabledDispatchVehicleIdList() {
        return disabledDispatchVehicleIdList;
    }

    public void setDisabledDispatchVehicleIdList(List<String> disabledDispatchVehicleIdList) {
        this.disabledDispatchVehicleIdList = disabledDispatchVehicleIdList;
    }

    public String getPlanDispatchTips() {
        return planDispatchTips;
    }

    public void setPlanDispatchTips(String planDispatchTips) {
        this.planDispatchTips = planDispatchTips;
    }

    public List<PlanSupplementBean> getPlanSupplementBeanList() {
        return planSupplementBeanList;
    }

    public void setPlanSupplementBeanList(List<PlanSupplementBean> planSupplementBeanList) {
        this.planSupplementBeanList = planSupplementBeanList;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public List<PlanDispatchBean> getPlanDispatch() {
        return planDispatch;
    }

    public void setPlanDispatch(List<PlanDispatchBean> planDispatch) {
        this.planDispatch = planDispatch;
    }

    public Integer getPlanDispatchNum() {
        return planDispatchNum;
    }

    public void setPlanDispatchNum(Integer planDispatchNum) {
        this.planDispatchNum = planDispatchNum;
    }

    public String getObjectTypeName() {
        return objectTypeName;
    }

    public void setObjectTypeName(String objectTypeName) {
        this.objectTypeName = objectTypeName;
    }

    public String getPlanCategoryName() {
        return planCategoryName;
    }

    public void setPlanCategoryName(String planCategoryName) {
        this.planCategoryName = planCategoryName;
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    public String getPlanStatusName() {
        return planStatusName;
    }

    public void setPlanStatusName(String planStatusName) {
        this.planStatusName = planStatusName;
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

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

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

    public List<AttachmentBean> getAttachmentBean() {
        return attachmentBean;
    }

    public void setAttachmentBean(List<AttachmentBean> attachmentBean) {
        this.attachmentBean = attachmentBean;
    }
}