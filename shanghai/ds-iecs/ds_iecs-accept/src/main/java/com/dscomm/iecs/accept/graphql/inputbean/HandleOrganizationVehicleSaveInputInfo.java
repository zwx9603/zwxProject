package com.dscomm.iecs.accept.graphql.inputbean;

/**
 *  描述: 调派单位车辆信息 （ 作战车辆信息 ）
 *
 */
public class  HandleOrganizationVehicleSaveInputInfo   {

    private String organizationId; //消防机构编号

    private String vehicleId; // 车辆编号

    private String vehicleTypeCode; // 装备类型代码 （车辆类型代码）

    private String fightFunctionCode; // 作战功能代码

    private String remarks;//备注


    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getFightFunctionCode() {
        return fightFunctionCode;
    }

    public void setFightFunctionCode(String fightFunctionCode) {
        this.fightFunctionCode = fightFunctionCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
