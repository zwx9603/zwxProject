package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:消防机构毗邻优先级
 * *
 */
public class OrganizationAdjacentPriorityBean extends BaseBean {

    private String chargeOrganizationId; //主管单位代码
    private String chargeOrganizationName; //主管单位名称

    private String adjacentOrganizationId; //毗邻单位代码
    private String adjacentOrganizationName; //毗邻单位名称

    private Double priority; //优先级

    private String adjacentLevel; //毗邻级别

    private String organizationId; //消防机构代码
    private String organizationName; //消防机构名称

    private String remarks; //备注

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getChargeOrganizationName() {
        return chargeOrganizationName;
    }

    public void setChargeOrganizationName(String chargeOrganizationName) {
        this.chargeOrganizationName = chargeOrganizationName;
    }

    public String getAdjacentOrganizationName() {
        return adjacentOrganizationName;
    }

    public void setAdjacentOrganizationName(String adjacentOrganizationName) {
        this.adjacentOrganizationName = adjacentOrganizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getChargeOrganizationId() {
        return chargeOrganizationId;
    }

    public void setChargeOrganizationId(String chargeOrganizationId) {
        this.chargeOrganizationId = chargeOrganizationId;
    }

    public String getAdjacentOrganizationId() {
        return adjacentOrganizationId;
    }

    public void setAdjacentOrganizationId(String adjacentOrganizationId) {
        this.adjacentOrganizationId = adjacentOrganizationId;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public String getAdjacentLevel() {
        return adjacentLevel;
    }

    public void setAdjacentLevel(String adjacentLevel) {
        this.adjacentLevel = adjacentLevel;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }


}