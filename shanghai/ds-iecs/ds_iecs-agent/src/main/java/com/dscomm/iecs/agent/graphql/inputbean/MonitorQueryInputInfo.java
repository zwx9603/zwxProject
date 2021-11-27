package com.dscomm.iecs.agent.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 * 监控查询参数
 */
public class MonitorQueryInputInfo {

    private String scopeSquadronId ; //查询范围辖区Id

    private  Long   startTime ; //查询开始时间

    private  Long   endTime ;  //查询结束时间

    private  Integer  searchTimeType  = 1 ; //时间查询类型  0 今日  1 24小时 2 一周 3 半月 4 一月 5 半年

    private String keyword ; //关键字

    private List<String> typeCode ; // 类型

    private  String seatNumber ; //坐席号

    private String personNumber ; //人员工号

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件





    public Integer getSearchTimeType() {
        return searchTimeType;
    }

    public void setSearchTimeType(Integer searchTimeType) {
        this.searchTimeType = searchTimeType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(List<String> typeCode) {
        this.typeCode = typeCode;
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

    public String getScopeSquadronId() {
        return scopeSquadronId;
    }

    public void setScopeSquadronId(String scopeSquadronId) {
        this.scopeSquadronId = scopeSquadronId;
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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }
}
