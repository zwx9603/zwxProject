package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 机构查询参数
 */
public class OrganizationQueryInputInfo {

    private String squadronId ; //辖区id 必填

    private String keyword ; //模糊查询关键字

    private Boolean whetherOnlySquadron; //是否只查询中队

    private int returnType = 0  ; //返回类型 0是列表结构 1是树形结构 默认列表结构

    public String getSquadronId() {
        return squadronId;
    }

    public void setSquadronId(String squadronId) {
        this.squadronId = squadronId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getWhetherOnlySquadron() {
        return whetherOnlySquadron;
    }

    public void setWhetherOnlySquadron(Boolean whetherOnlySquadron) {
        this.whetherOnlySquadron = whetherOnlySquadron;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }
}
