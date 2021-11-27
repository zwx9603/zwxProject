package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 * 描述:集合演练方案 查询参数
 */
public class DrillPlanQueryInputInfo {

    private List<String> incidentTypeCodes; //案件类型

    private String keyword ; //关键字 演练方案名称 案内容 制作人姓名  模糊匹配

    private Boolean whetherEnableStatus = false  ;//是否查询启用状态

    private Boolean whetherEnableTime = false   ;//是否查询当前时间是否在有效时间内

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public Boolean getWhetherEnableStatus() {
        return whetherEnableStatus;
    }

    public void setWhetherEnableStatus(Boolean whetherEnableStatus) {
        this.whetherEnableStatus = whetherEnableStatus;
    }

    public Boolean getWhetherEnableTime() {
        return whetherEnableTime;
    }

    public void setWhetherEnableTime(Boolean whetherEnableTime) {
        this.whetherEnableTime = whetherEnableTime;
    }

    public List<String> getIncidentTypeCodes() {
        return incidentTypeCodes;
    }

    public void setIncidentTypeCodes(List<String> incidentTypeCodes) {
        this.incidentTypeCodes = incidentTypeCodes;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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