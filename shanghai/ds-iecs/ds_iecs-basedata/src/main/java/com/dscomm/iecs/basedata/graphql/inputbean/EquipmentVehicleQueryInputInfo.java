package com.dscomm.iecs.basedata.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 * 车辆查询参数
 */
public class EquipmentVehicleQueryInputInfo {

    private String scopeSquadronId ; //机构Id

    private Integer scopeType = 0 ; //是否递归查询 0不递归，只查询本单位 1向下递归查询

    private String keyword ; //模糊匹配关键字（车辆名称 车辆简称 车牌号）

    private List<String> vehicleTypeCodes ; //车辆类型编码集合

//    private List<String> brigadeOrganizationIds ; //所属大队 机构编码集合
//
//    private List<String> squadronOrganizationIds ; //所属中队 机构编码集合
//
//    private List<String> organizationIds ; //机构编码集合

    private List<String> vehicleStatusCodes ; //车辆状态编码集合

    private Integer nature = -1  ; //机构性质 -1 查询全部  0 总队 1 支队 2 大队 3救援站（中队） 默认 -1

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

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

//    public List<String> getBrigadeOrganizationIds() {
//        return brigadeOrganizationIds;
//    }
//
//    public void setBrigadeOrganizationIds(List<String> brigadeOrganizationIds) {
//        this.brigadeOrganizationIds = brigadeOrganizationIds;
//    }
//
//    public List<String> getSquadronOrganizationIds() {
//        return squadronOrganizationIds;
//    }
//
//    public void setSquadronOrganizationIds(List<String> squadronOrganizationIds) {
//        this.squadronOrganizationIds = squadronOrganizationIds;
//    }

    public List<String> getVehicleStatusCodes() {
        return vehicleStatusCodes;
    }

    public void setVehicleStatusCodes(List<String> vehicleStatusCodes) {
        this.vehicleStatusCodes = vehicleStatusCodes;
    }

    public Boolean getWhetherPage() {
        return whetherPage;
    }

    public void setWhetherPage(Boolean whetherPage) {
        this.whetherPage = whetherPage;
    }

    public PaginationInputInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInputInfo pagination) {
        this.pagination = pagination;
    }

//    public List<String> getOrganizationIds() {
//        return organizationIds;
//    }
//
//    public void setOrganizationIds(List<String> organizationIds) {
//        this.organizationIds = organizationIds;
//    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }
}
