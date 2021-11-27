package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述： 等级调度
 *
 */
public class HierarchicalDispatchBean extends BaseBean {

    private String title; // 等级调派名称

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeName;// 案件类型名称

    private String disposalObjectCode;// 处置对象代码

    private String disposalObjectName;// 处置对象名称

    private String incidentLevelCode;// 案件等级代码

    private String incidentLevelName;// 案件等级名称

    private List<HierarchicalDispatchVehicleBean> hierarchicalDispatchVehicleBeanList ; //调派信息

    private String  hierarchicalDispatchTips ; //等级调派提示信息

    private List<String> hierarchicalDispatchVehicleIdList ; //等级调派车辆id

    private Long makeTime  ; //制作时间

    private String makePersonId ;  //制作人id

    private String makePersonName ; //制作人姓名

    private String remarks; // 备注

    private Integer usageTimes=0;//使用次数


    public String getHierarchicalDispatchTips() {
        return hierarchicalDispatchTips;
    }

    public void setHierarchicalDispatchTips(String hierarchicalDispatchTips) {
        this.hierarchicalDispatchTips = hierarchicalDispatchTips;
    }

    public List<String> getHierarchicalDispatchVehicleIdList() {
        return hierarchicalDispatchVehicleIdList;
    }

    public void setHierarchicalDispatchVehicleIdList(List<String> hierarchicalDispatchVehicleIdList) {
        this.hierarchicalDispatchVehicleIdList = hierarchicalDispatchVehicleIdList;
    }

    public List<HierarchicalDispatchVehicleBean> getHierarchicalDispatchVehicleBeanList() {
        return hierarchicalDispatchVehicleBeanList;
    }

    public void setHierarchicalDispatchVehicleBeanList(List<HierarchicalDispatchVehicleBean> hierarchicalDispatchVehicleBeanList) {
        this.hierarchicalDispatchVehicleBeanList = hierarchicalDispatchVehicleBeanList;
    }

    public Long getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Long makeTime) {
        this.makeTime = makeTime;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getDisposalObjectName() {
        return disposalObjectName;
    }

    public void setDisposalObjectName(String disposalObjectName) {
        this.disposalObjectName = disposalObjectName;
    }

    public String getIncidentLevelName() {
        return incidentLevelName;
    }

    public void setIncidentLevelName(String incidentLevelName) {
        this.incidentLevelName = incidentLevelName;
    }



    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getUsageTimes() {
        return usageTimes;
    }

    public void setUsageTimes(Integer usageTimes) {
        this.usageTimes = usageTimes;
    }
}
