package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 描述： 中队车辆与车库门对应关系 保存参数
 *
 */
public class VehicleGarageMappingSaveInputInfo   {


    private String organizationId;// 消防机构编号

    private String mappingType;// 关系类型

    private String mappingObjectId ;// 车辆编号/班组号

    private String mappingGroupId;// 车库门编号/房间号

    private String remarks;//备注


    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getMappingObjectId() {
        return mappingObjectId;
    }

    public void setMappingObjectId(String mappingObjectId) {
        this.mappingObjectId = mappingObjectId;
    }

    public String getMappingGroupId() {
        return mappingGroupId;
    }

    public void setMappingGroupId(String mappingGroupId) {
        this.mappingGroupId = mappingGroupId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
