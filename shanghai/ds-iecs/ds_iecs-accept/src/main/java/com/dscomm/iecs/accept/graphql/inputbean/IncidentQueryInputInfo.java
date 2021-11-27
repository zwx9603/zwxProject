package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 * 警情查询参数
 */
public class IncidentQueryInputInfo {

    private String scopeSquadronId ; //查询范围辖区Id

    private Integer scopeType = 0 ; //查询范围ds_iecs_accept类型 0根机构 1非根机构

    private List<String> squadronIds ; //辖区id集合

    private String keyword ; //模糊匹配关键字（警情地址模糊匹配）

    private String receiverObjectId ; //接收对象id

    private List<String> incidentTypeCodes ; //警情类型编码集合

    private List<String> incidentLevelCodes ; //警情等级编码集合

    private List<String> incidentStateCodes ; //警情状态编码集合

    private List<String> filterIncidentIds  ; //过滤警情id

    private  Long   startTime ; //查询开始时间

    private  Long   endTime ;  //查询结束时间

    private  Boolean whetherKeyUnit  = false  ; //是否查询重点单位

    private Boolean whetherImport = false ; //是否查询重大案件

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public String getReceiverObjectId() {
        return receiverObjectId;
    }

    public void setReceiverObjectId(String receiverObjectId) {
        this.receiverObjectId = receiverObjectId;
    }

    public String getScopeSquadronId() {
        return scopeSquadronId;
    }

    public void setScopeSquadronId(String scopeSquadronId) {
        this.scopeSquadronId = scopeSquadronId;
    }

    public List<String> getSquadronIds() {
        return squadronIds;
    }

    public void setSquadronIds(List<String> squadronIds) {
        this.squadronIds = squadronIds;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getIncidentTypeCodes() {
        return incidentTypeCodes;
    }

    public void setIncidentTypeCodes(List<String> incidentTypeCodes) {
        this.incidentTypeCodes = incidentTypeCodes;
    }

    public List<String> getIncidentLevelCodes() {
        return incidentLevelCodes;
    }

    public void setIncidentLevelCodes(List<String> incidentLevelCodes) {
        this.incidentLevelCodes = incidentLevelCodes;
    }

    public List<String> getIncidentStateCodes() {
        return incidentStateCodes;
    }

    public void setIncidentStateCodes(List<String> incidentStateCodes) {
        this.incidentStateCodes = incidentStateCodes;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Boolean getWhetherKeyUnit() {
        return whetherKeyUnit;
    }

    public void setWhetherKeyUnit(Boolean whetherKeyUnit) {
        this.whetherKeyUnit = whetherKeyUnit;
    }

    public Boolean getWhetherImport() {
        return whetherImport;
    }

    public void setWhetherImport(Boolean whetherImport) {
        this.whetherImport = whetherImport;
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

    public List<String> getFilterIncidentIds() {
        return filterIncidentIds;
    }

    public void setFilterIncidentIds(List<String> filterIncidentIds) {
        this.filterIncidentIds = filterIncidentIds;
    }
}
