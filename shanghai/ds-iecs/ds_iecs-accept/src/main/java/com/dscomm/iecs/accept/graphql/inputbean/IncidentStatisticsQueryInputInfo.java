package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 警情查询参数
 */
public class IncidentStatisticsQueryInputInfo {

    private String scopeSquadronId ; //查询范围辖区Id

    private Integer scopeType = 0 ; //查询范围辖区类型 0根机构 1非根机构

    private List<String> squadronIds ; //辖区id集合

    private String keyword ; //模糊匹配关键字（警情地址模糊匹配）

    private List<String> incidentTypeCodes ; //警情类型编码集合

    private List<String> incidentLevelCodes ; //警情等级编码集合

    private List<String> incidentStateCodes ; //警情状态编码集合

    private List<String> districtCodes ; // 行政区代码集合

    private  Long   startTime ; //查询开始时间

    private  Long   endTime ;  //查询结束时间

    private  Boolean whetherKeyUnit  = false  ; //是否查询重点单位

    private Boolean whetherImport = false ; //是否查询重大案件

    public List<String> getDistrictCodes() {
        return districtCodes;
    }

    public void setDistrictCodes(List<String> districtCodes) {
        this.districtCodes = districtCodes;
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

}
