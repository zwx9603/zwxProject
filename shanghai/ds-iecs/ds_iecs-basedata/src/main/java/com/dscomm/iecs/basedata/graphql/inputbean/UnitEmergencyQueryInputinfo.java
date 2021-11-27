package com.dscomm.iecs.basedata.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

public class UnitEmergencyQueryInputinfo {
    private String keyword ; // 关键字  模糊匹配 微站名称/地址

    private String districtCode;//    行政区代码

    private String organizationId;//   机构id

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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
