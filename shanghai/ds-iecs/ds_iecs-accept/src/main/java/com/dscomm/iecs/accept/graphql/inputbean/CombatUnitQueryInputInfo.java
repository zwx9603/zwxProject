package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

/**
 * 描述:作战单元参数
 * author:YangFuXi
 * Date:2021/6/8 Time:16:50
 **/
public class CombatUnitQueryInputInfo {
    private String keyWord;//关键字，匹配作战单元名称和说明
    private String orgId;//所属单位id  如果不传则取登陆人所属单位
    private Integer scope;//是否分权分域 1向下递归查询 默认不递归
    private String unitPersonName;//队长姓名
    private PaginationInputInfo pagination;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getUnitPersonName() {
        return unitPersonName;
    }

    public void setUnitPersonName(String unitPersonName) {
        this.unitPersonName = unitPersonName;
    }

    public PaginationInputInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInputInfo pagination) {
        this.pagination = pagination;
    }
}
