package com.dscomm.iecs.basedata.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 * 装备查询参数
 */
public class EquipmentQueryInputInfo {

    private String scopeSquadronId ; //查询范围辖区Id

    private Integer scopeType = 0 ; //查询范围辖区类型 0根机构 1非根机构

    private String keyword ; //模糊匹配关键字（装备器材名称模糊）

    private List<String> equipmentTypeCodes ; //装备器材类型编码集合

    private List<String> organizationIds ; //装备器材所在机构编码集合

    private List<String> equipmentStatusCodes ; //装备器材状态编码集合

    private Boolean whetherConsumptiveEquipment = false  ; //是否查询消耗性装备

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




    public List<String> getEquipmentTypeCodes() {
        return equipmentTypeCodes;
    }

    public void setEquipmentTypeCodes(List<String> equipmentTypeCodes) {
        this.equipmentTypeCodes = equipmentTypeCodes;
    }

    public List<String> getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(List<String> organizationIds) {
        this.organizationIds = organizationIds;
    }

    public List<String> getEquipmentStatusCodes() {
        return equipmentStatusCodes;
    }

    public void setEquipmentStatusCodes(List<String> equipmentStatusCodes) {
        this.equipmentStatusCodes = equipmentStatusCodes;
    }

    public Boolean getWhetherConsumptiveEquipment() {
        return whetherConsumptiveEquipment;
    }

    public void setWhetherConsumptiveEquipment(Boolean whetherConsumptiveEquipment) {
        this.whetherConsumptiveEquipment = whetherConsumptiveEquipment;
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
}
