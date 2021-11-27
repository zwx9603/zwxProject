package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

/**
 * 警情查询参数
 */
public class TagLabelQueryInputInfo {


    private String keyword ; //模糊匹配关键字（ 电话号码 用户姓名 ）

    private String phoneKeyword ; //电话号码模糊匹配

    private String  personNameKeyword  ; //用户名称模糊匹配

    private String businessTable;  //业务表名 1电话标签

    private  Long  startTime  ; //查询开始时间

    private Long  endTime  ; //查询结束时间

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public String getPhoneKeyword() {
        return phoneKeyword;
    }

    public void setPhoneKeyword(String phoneKeyword) {
        this.phoneKeyword = phoneKeyword;
    }

    public String getPersonNameKeyword() {
        return personNameKeyword;
    }

    public void setPersonNameKeyword(String personNameKeyword) {
        this.personNameKeyword = personNameKeyword;
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

    public String getBusinessTable() {
        return businessTable;
    }

    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
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
