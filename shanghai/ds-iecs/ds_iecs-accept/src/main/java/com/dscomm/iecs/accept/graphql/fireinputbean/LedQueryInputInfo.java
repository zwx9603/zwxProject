package com.dscomm.iecs.accept.graphql.fireinputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

public class LedQueryInputInfo {

    private String orgId; //机构id
    private String type; //类型   vehicle 查询led-车辆  organization 查询led-机构
    private Integer isDisPlay;// 0没有显示再led 1 显示再led
    private String name;//led名称
    private String oldName;//原始名称
    private Boolean whetherPage = true  ; //是否分页查询 默认分页
    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getIsDisPlay() {
        return isDisPlay;
    }

    public void setIsDisPlay(Integer isDisPlay) {
        this.isDisPlay = isDisPlay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
}
