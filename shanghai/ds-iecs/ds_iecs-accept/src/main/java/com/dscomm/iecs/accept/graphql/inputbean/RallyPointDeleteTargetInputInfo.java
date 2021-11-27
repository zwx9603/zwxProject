package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 集结点删除参数
 *
 */
public class RallyPointDeleteTargetInputInfo {

    private String rallyPointId ; //任务点id

    private String organizationId;// 部署机构ID

    private String organizationName;// 启动机构名称

    private String personId;// 部署人Id

    private String personName;// 部署人名称

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getRallyPointId() {
        return rallyPointId;
    }

    public void setRallyPointId(String rallyPointId) {
        this.rallyPointId = rallyPointId;
    }
}
