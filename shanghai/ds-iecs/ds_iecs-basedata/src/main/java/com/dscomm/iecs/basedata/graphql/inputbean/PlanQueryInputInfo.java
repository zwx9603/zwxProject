package com.dscomm.iecs.basedata.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 * 描述:预案查询参数
 */
public class PlanQueryInputInfo   {


    private List<String> planCategoryCodes; //预案种类

    private List<String> planTypeCodes; //预案类型代码

    private String keyword ; //关键字 预案名称 预案说明 重点单位名称 部位名称  模糊匹配

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件


    public List<String> getPlanCategoryCodes() {
        return planCategoryCodes;
    }

    public void setPlanCategoryCodes(List<String> planCategoryCodes) {
        this.planCategoryCodes = planCategoryCodes;
    }

    public List<String> getPlanTypeCodes() {
        return planTypeCodes;
    }

    public void setPlanTypeCodes(List<String> planTypeCodes) {
        this.planTypeCodes = planTypeCodes;
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