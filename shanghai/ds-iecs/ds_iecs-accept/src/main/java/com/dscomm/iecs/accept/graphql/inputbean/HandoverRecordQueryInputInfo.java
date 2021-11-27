package com.dscomm.iecs.accept.graphql.inputbean;

import org.mx.dal.Pagination;

/**
 * 交接班 记录内容 查询信息
 */
public class HandoverRecordQueryInputInfo {

    private Long startTime; //开始时间

    private Long endTime; //结束时间

    private String keyWord; //关键字

    private Boolean whetherPage = true; //是否分页，默认分页

    private Pagination pagination;//分页信息

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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Boolean getWhetherPage() {
        return whetherPage;
    }

    public void setWhetherPage(Boolean whetherPage) {
        this.whetherPage = whetherPage;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
