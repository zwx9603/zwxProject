package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述: 调派单位信息 （ 处警信息 ）
 *
 */
public class HandleOrganizationSaveInputInfo {

    private String organizationId; //消防机构编号

    private String handlePoliceStatus  = "0"  ; //指令单状态

    private String remarks;//备注

    public String getHandlePoliceStatus() {
        return handlePoliceStatus;
    }

    public void setHandlePoliceStatus(String handlePoliceStatus) {
        this.handlePoliceStatus = handlePoliceStatus;
    }

    private List<HandleOrganizationVehicleSaveInputInfo> handleOrganizationVehicleSaveInputInfo ; //调派单位车辆信息 （ 作战车辆信息 ）

    private List<HandleOrganizationEquipmentSaveInputInfo> handleOrganizationEquipmentSaveInputInfo ; //调派单位装备信息

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
