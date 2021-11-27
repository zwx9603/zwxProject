package com.dscomm.iecs.accept.graphql.fireinputbean;

import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;

import java.util.List;

public class PlanWordSaveInputInfo {


    private String id;

    private String planTypeCode;// 预案统计分类

    private String incidentTypeCode ;//案件类型编码

    private String planName;// 预案名称

    private String organizationCode;// 预案使用机构编码

    private Integer  whetherKeyUnit;//是否重点单位预案

    private String keyUnitId ;//重点单位id

    private String remarks; //备注

    private List<AttachmentSaveInputInfo> attachmentSaveInputInfo;

    public List<AttachmentSaveInputInfo> getAttachmentSaveInputInfo() {
        return attachmentSaveInputInfo;
    }

    public void setAttachmentSaveInputInfo(List<AttachmentSaveInputInfo> attachmentSaveInputInfo) {
        this.attachmentSaveInputInfo = attachmentSaveInputInfo;
    }

    public String getPlanTypeCode() {
        return planTypeCode;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPlanTypeCode(String planTypeCode) {
        this.planTypeCode = planTypeCode;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }


    public Integer getWhetherKeyUnit() {
        return whetherKeyUnit;
    }

    public void setWhetherKeyUnit(Integer whetherKeyUnit) {
        this.whetherKeyUnit = whetherKeyUnit;
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
