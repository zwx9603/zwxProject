package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 *  描述: 调派记录（处警记录）
 *
 */
public class HandleSaveInputInfo   {

    private String incidentId; // 案件信息ID

    private String handleNumber;// 处警编号

    private Long handleStartTime;// 处警开始时间

    private Long handleEndTime;// 处警结束时间

    private String handleOrganizationId; // 处警单位编号

    private String handleSeatNumber; // 处警台号（坐席号）

    private String handlePersonNumber; // 处警员工号

    private String handlePersonName; //处警员姓名

    private Long waitHandleDuration; //等待处警时长

    private Long handleDuration; //处警时长

    private String handleStatus; //处警单状态

    private String handleSource; // 处警来源（ 1 接处警/ 2实战指挥/ 3移动终端）

    private String handleTypeCode  ; // 警情调派形式类别代码

    private String handleContent ; //  调派指令_文件内容

    private String remarks;//备注

    private List<HandleOrganizationSaveInputInfo> handleOrganizationSaveInputInfo ; //调派单位信息 （ 处警信息 ）

    private List<HandleOrganizationVehicleSaveInputInfo> handleOrganizationVehicleSaveInputInfo ; //调派单位车辆信息 （ 作战车辆信息 ）

    private List<HandleOrganizationEquipmentSaveInputInfo> handleOrganizationEquipmentSaveInputInfo ; //调派单位装备信息

    private List<HandleMiniatureStationSaveInputInfo> handleMiniatureStationSaveInputInfo ; //调派微站

    public String getHandleTypeCode() {
        return handleTypeCode;
    }

    public void setHandleTypeCode(String handleTypeCode) {
        this.handleTypeCode = handleTypeCode;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleNumber() {
        return handleNumber;
    }

    public void setHandleNumber(String handleNumber) {
        this.handleNumber = handleNumber;
    }

    public Long getHandleStartTime() {
        return handleStartTime;
    }

    public void setHandleStartTime(Long handleStartTime) {
        this.handleStartTime = handleStartTime;
    }

    public Long getHandleEndTime() {
        return handleEndTime;
    }

    public void setHandleEndTime(Long handleEndTime) {
        this.handleEndTime = handleEndTime;
    }

    public String getHandleOrganizationId() {
        return handleOrganizationId;
    }

    public void setHandleOrganizationId(String handleOrganizationId) {
        this.handleOrganizationId = handleOrganizationId;
    }

    public String getHandleSeatNumber() {
        return handleSeatNumber;
    }

    public void setHandleSeatNumber(String handleSeatNumber) {
        this.handleSeatNumber = handleSeatNumber;
    }

    public String getHandlePersonNumber() {
        return handlePersonNumber;
    }

    public void setHandlePersonNumber(String handlePersonNumber) {
        this.handlePersonNumber = handlePersonNumber;
    }

    public String getHandlePersonName() {
        return handlePersonName;
    }

    public void setHandlePersonName(String handlePersonName) {
        this.handlePersonName = handlePersonName;
    }

    public Long getWaitHandleDuration() {
        return waitHandleDuration;
    }

    public void setWaitHandleDuration(Long waitHandleDuration) {
        this.waitHandleDuration = waitHandleDuration;
    }

    public Long getHandleDuration() {
        return handleDuration;
    }

    public void setHandleDuration(Long handleDuration) {
        this.handleDuration = handleDuration;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleSource() {
        return handleSource;
    }

    public void setHandleSource(String handleSource) {
        this.handleSource = handleSource;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<HandleOrganizationSaveInputInfo> getHandleOrganizationSaveInputInfo() {
        return handleOrganizationSaveInputInfo;
    }

    public void setHandleOrganizationSaveInputInfo(List<HandleOrganizationSaveInputInfo> handleOrganizationSaveInputInfo) {
        this.handleOrganizationSaveInputInfo = handleOrganizationSaveInputInfo;
    }

    public List<HandleOrganizationVehicleSaveInputInfo> getHandleOrganizationVehicleSaveInputInfo() {
        return handleOrganizationVehicleSaveInputInfo;
    }

    public void setHandleOrganizationVehicleSaveInputInfo(List<HandleOrganizationVehicleSaveInputInfo> handleOrganizationVehicleSaveInputInfo) {
        this.handleOrganizationVehicleSaveInputInfo = handleOrganizationVehicleSaveInputInfo;
    }

    public List<HandleOrganizationEquipmentSaveInputInfo> getHandleOrganizationEquipmentSaveInputInfo() {
        return handleOrganizationEquipmentSaveInputInfo;
    }

    public void setHandleOrganizationEquipmentSaveInputInfo(List<HandleOrganizationEquipmentSaveInputInfo> handleOrganizationEquipmentSaveInputInfo) {
        this.handleOrganizationEquipmentSaveInputInfo = handleOrganizationEquipmentSaveInputInfo;
    }

    public List<HandleMiniatureStationSaveInputInfo> getHandleMiniatureStationSaveInputInfo() {
        return handleMiniatureStationSaveInputInfo;
    }

    public void setHandleMiniatureStationSaveInputInfo(List<HandleMiniatureStationSaveInputInfo> handleMiniatureStationSaveInputInfo) {
        this.handleMiniatureStationSaveInputInfo = handleMiniatureStationSaveInputInfo;
    }
}
