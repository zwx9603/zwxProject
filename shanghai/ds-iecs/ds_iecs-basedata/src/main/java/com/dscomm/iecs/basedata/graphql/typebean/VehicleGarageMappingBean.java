package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 中队车辆与车库门对应关系
 *
 */
public class VehicleGarageMappingBean extends BaseBean {

    private String organizationId;// 消防机构编号

    private String organizationName;// 消防机构名称

    private String mappingType;// 关系类型

    private String mappingTypeName;// 关系类型

    private String mappingObjectId ;// 车辆编号/班组号

    private String mappingObjectName ;// 车辆名称/班组号名称1

    private String mappingObjectName2 ; // 车辆车牌号/班组号名称2

    private String mappingGroupId;// 车库门编号/房间号

    private String mappingGroupName;// 车库门编号/房间号 名称

    private String remarks;//备注

    public String getMappingTypeName() {
        return mappingTypeName;
    }

    public void setMappingTypeName(String mappingTypeName) {
        this.mappingTypeName = mappingTypeName;
    }

    public String getMappingObjectName() {
        return mappingObjectName;
    }

    public void setMappingObjectName(String mappingObjectName) {
        this.mappingObjectName = mappingObjectName;
    }

    public String getMappingObjectName2() {
        return mappingObjectName2;
    }

    public void setMappingObjectName2(String mappingObjectName2) {
        this.mappingObjectName2 = mappingObjectName2;
    }

    public String getMappingGroupName() {
        return mappingGroupName;
    }

    public void setMappingGroupName(String mappingGroupName) {
        this.mappingGroupName = mappingGroupName;
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
