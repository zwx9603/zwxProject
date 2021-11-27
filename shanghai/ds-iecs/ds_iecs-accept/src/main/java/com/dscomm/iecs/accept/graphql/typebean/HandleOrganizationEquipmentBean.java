package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述: 调派单位装备信息
 *
 */
public class HandleOrganizationEquipmentBean extends BaseBean {

    private String incidentId; //案件ID

    private String handleId;// 处警记录ID

    private String handleBatch;// 处警批次

    private Long handleStartTime;// 处警开始时间

    private Long handleEndTime;// 处警结束时间

    private String handlePoliceId; // 处警信息ID

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String equipment; //   装备

    private String equipmentId; //   装备 通用唯一识别码

    private String equipmentName; //   装备名称

    private Long dispatchStartTime;//   调派开始时间

    private Long dispatchEndTime;//  调派结束时间

    private String equipmentTypeCode; // 装备类型代码

    private String equipmentTypeName; // 装备类型名称

    private Integer dispatchNum;// 调派数量

    private String remarks;//备注


    public String getHandleBatch() {
        return handleBatch;
    }

    public void setHandleBatch(String handleBatch) {
        this.handleBatch = handleBatch;
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

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getHandlePoliceId() {
        return handlePoliceId;
    }

    public void setHandlePoliceId(String handlePoliceId) {
        this.handlePoliceId = handlePoliceId;
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

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Long getDispatchStartTime() {
        return dispatchStartTime;
    }

    public void setDispatchStartTime(Long dispatchStartTime) {
        this.dispatchStartTime = dispatchStartTime;
    }

    public Long getDispatchEndTime() {
        return dispatchEndTime;
    }

    public void setDispatchEndTime(Long dispatchEndTime) {
        this.dispatchEndTime = dispatchEndTime;
    }
}
