package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述： 等级调度 保存参数
 *
 */
public class HierarchicalDispatchSaveInputInfo   {

    private String id  ; //主键

    private String title; // 等级调派名称

    private String organizationId; // 消防机构编号

    private String incidentTypeCode;// 案件类型代码

    private String disposalObjectCode;// 处置对象代码

    private String incidentLevelCode;// 案件等级代码

    private List<HierarchicalDispatchVehicleSaveInputInfo> hierarchicalDispatchVehicleSaveInputInfoList ; // 车辆类型调派数量

    private String remarks; // 备注

    public List<HierarchicalDispatchVehicleSaveInputInfo> getHierarchicalDispatchVehicleSaveInputInfoList() {
        return hierarchicalDispatchVehicleSaveInputInfoList;
    }

    public void setHierarchicalDispatchVehicleSaveInputInfoList(List<HierarchicalDispatchVehicleSaveInputInfo> hierarchicalDispatchVehicleSaveInputInfoList) {
        this.hierarchicalDispatchVehicleSaveInputInfoList = hierarchicalDispatchVehicleSaveInputInfoList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
