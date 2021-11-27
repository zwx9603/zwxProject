package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/5/25 13:43
 * @Describe 呼入记录查询参数
 */
public class CtiCallQueryInputInfo {
    private String phoneNumber;
    private String personName;
    private Long startTime;
    private Long endTime;

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
