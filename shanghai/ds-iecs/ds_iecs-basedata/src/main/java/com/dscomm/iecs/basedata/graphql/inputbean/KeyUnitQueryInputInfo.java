package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 重点单位查询参数
 */
public class KeyUnitQueryInputInfo {

    private String organizationId; //所属单位/上级单位 id

    private String unitAddress ; //单位地址模糊匹配

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }
}
