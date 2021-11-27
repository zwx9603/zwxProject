package com.dscomm.iecs.accept.graphql.inputbean;

/**
 *  描述: 调派单位装备信息
 *
 */
public class HandleOrganizationEquipmentSaveInputInfo {


    private String organizationId; //消防机构编号

    private String equipmentId; //  装备 通用唯一识别码

    private String equipmentName; //   装备名称

    private String equipmentTypeCode; // 装备类型代码

    private Integer dispatchNum = 0;// 调派数量

    private String remarks;//备注

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
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

}
