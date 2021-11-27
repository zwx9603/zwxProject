package com.dscomm.iecs.basedata.graphql.inputbean;

import java.util.List;

/**
 * 描述： 中队车辆与车库门对应关系 查询条件
 *
 */
public class VehicleGarageMappingQueryInputInfo   {

    private List<String> organizationIds;// 消防机构编号

    private List<String> mappingTypes;// 关系类型

    private List<String> mappingObjectIds ;// 车辆编号/班组号

    private List<String> mappingGroupIds;// 车库门编号/房间号

    public List<String> getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(List<String> organizationIds) {
        this.organizationIds = organizationIds;
    }

    public List<String> getMappingTypes() {
        return mappingTypes;
    }

    public void setMappingTypes(List<String> mappingTypes) {
        this.mappingTypes = mappingTypes;
    }

    public List<String> getMappingObjectIds() {
        return mappingObjectIds;
    }

    public void setMappingObjectIds(List<String> mappingObjectIds) {
        this.mappingObjectIds = mappingObjectIds;
    }

    public List<String> getMappingGroupIds() {
        return mappingGroupIds;
    }

    public void setMappingGroupIds(List<String> mappingGroupIds) {
        this.mappingGroupIds = mappingGroupIds;
    }
}
