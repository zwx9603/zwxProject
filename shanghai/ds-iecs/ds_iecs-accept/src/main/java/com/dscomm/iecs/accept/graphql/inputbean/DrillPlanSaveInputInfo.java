package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述:集合演练方案 保存修改参数
 */
public class DrillPlanSaveInputInfo {

    private String id ; //主键

    private String incidentTypeCode ;//案件类型

    private String drillPlanName ;//演练方案名称

    private String drillPlanContent; // 演练方案内容

    private String makeOrganizationId;//演练方案制作单位

    private String makePersonId; // 演练方案制作人编号

    private String makePersonName;//演练方案制作人姓名

    private Integer whetherEnable ;//是否启用

    private Long  enableTime ; //生效时间

    private Long invalidTime ; //过期时间

    private Long  makeTime ;//制作日期

    private String remarks;//备注

    private List<DrillPlanDispatchSaveInputInfo> drillPlanDispatchSaveInputInfos; // 集合演练方案调度信息集合

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<DrillPlanDispatchSaveInputInfo> getDrillPlanDispatchSaveInputInfos() {
        return drillPlanDispatchSaveInputInfos;
    }

    public void setDrillPlanDispatchSaveInputInfos(List<DrillPlanDispatchSaveInputInfo> drillPlanDispatchSaveInputInfos) {
        this.drillPlanDispatchSaveInputInfos = drillPlanDispatchSaveInputInfos;
    }
}