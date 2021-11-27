package com.dscomm.iecs.schedule.service.excel.bean;

import java.util.List;

public class AddressBean {

    private String status;
    private Integer totalCount;
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private List<AddressResultBean> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<AddressResultBean> getResults() {
        return results;
    }

    public void setResults(List<AddressResultBean> results) {
        this.results = results;
    }
}
