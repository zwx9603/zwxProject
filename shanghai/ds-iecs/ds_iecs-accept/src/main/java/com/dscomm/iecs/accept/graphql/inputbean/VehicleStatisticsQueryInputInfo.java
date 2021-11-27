package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 车辆统计查询参数
 */
public class VehicleStatisticsQueryInputInfo {

    private String scopeSquadronId ; //查询范围辖区Id

    private Integer scopeType = 0 ; //查询范围辖区类型 0根机构 1非根机构

    private String keyword ; //模糊匹配关键字（车辆名称 车辆简称 车牌号）

    private List<String> vehicleTypeCodes ; //车辆类型编码集合

    private List<String> organizationIds ; //车辆机构编码集合

    private List<String> vehicleStatusCodes ; //车辆状态编码集合

    private Integer nature = -1  ; //机构性质 -1 查询全部  0 总队 1 支队 2 大队 3救援站（中队） 默认 -1

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getScopeSquadronId() {
        return scopeSquadronId;
    }

    public void setScopeSquadronId(String scopeSquadronId) {
        this.scopeSquadronId = scopeSquadronId;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getVehicleTypeCodes() {
        return vehicleTypeCodes;
    }

    public void setVehicleTypeCodes(List<String> vehicleTypeCodes) {
        this.vehicleTypeCodes = vehicleTypeCodes;
    }

    public List<String> getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(List<String> organizationIds) {
        this.organizationIds = organizationIds;
    }

    public List<String> getVehicleStatusCodes() {
        return vehicleStatusCodes;
    }

    public void setVehicleStatusCodes(List<String> vehicleStatusCodes) {
        this.vehicleStatusCodes = vehicleStatusCodes;
    }


}
