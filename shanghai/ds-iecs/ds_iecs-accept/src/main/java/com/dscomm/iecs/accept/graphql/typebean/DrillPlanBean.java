package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述:集合演练方案
 *
 */
public class DrillPlanBean extends BaseBean {

    private String incidentTypeCode ;//案件类型

    private String incidentTypeName ;//案件类型名称

    private String drillPlanName ;//演练方案名称

    private String drillPlanContent; // 演练方案内容

    private String makeOrganizationId;//演练方案制作单位

    private String makeOrganizationName;//演练方案制作单位名称

    private String makePersonId; // 演练方案制作人编号

    private String makePersonName;//演练方案制作人姓名

    private Integer whetherEnable ;//是否启用

    private Long  enableTime ; //生效时间

    private Long invalidTime ; //过期时间

    private Long  makeTime ;//制作日期

    private String remarks;//备注

    private List<DrillPlanDispatchBean> drillPlanDispatchBeans; // 集合演练方案调度信息集合
    private Integer usageTimes=0;//使用次数

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

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
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

    public String getMakeOrganizationName() {
        return makeOrganizationName;
    }

    public void setMakeOrganizationName(String makeOrganizationName) {
        this.makeOrganizationName = makeOrganizationName;
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

    public List<DrillPlanDispatchBean> getDrillPlanDispatchBeans() {
        return drillPlanDispatchBeans;
    }

    public void setDrillPlanDispatchBeans(List<DrillPlanDispatchBean> drillPlanDispatchBeans) {
        this.drillPlanDispatchBeans = drillPlanDispatchBeans;
    }

    public Integer getUsageTimes() {
        return usageTimes;
    }

    public void setUsageTimes(Integer usageTimes) {
        this.usageTimes = usageTimes;
    }
}
