package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 火场安全监控记录 保存
 *
 */
public class FireSafetyMonitoringSaveInputInfo   {

    private String organizationId; // 消防机构编号

    private String organizationName; // 消防机构名称

    private String personId; //人员ID

    private String personName ; // 人员名称

    private String remarks; // 备注

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
