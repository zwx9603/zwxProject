package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

import java.util.List;

/**
 *  电话记录查询参数
 */
public class TelephoneQueryInputInfo {

    private  Long   startTime ; //查询开始时间

    private  Long   endTime ;  //查询结束时间

    private String seatNumber; // 坐席号

    private String personNumber;// 警员工号

    private String  phoneKeyword ; //电话号码 模糊

    private String  nameKeyword ; // 接警员姓名 报警人名称 模糊

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public String getPhoneKeyword() {
        return phoneKeyword;
    }

    public void setPhoneKeyword(String phoneKeyword) {
        this.phoneKeyword = phoneKeyword;
    }

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
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
