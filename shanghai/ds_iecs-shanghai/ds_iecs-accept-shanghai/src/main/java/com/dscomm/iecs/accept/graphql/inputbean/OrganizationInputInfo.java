package com.dscomm.iecs.accept.graphql.inputbean;

public class OrganizationInputInfo {

    private String organizationName; // 机构名称
    private String organizationAddress; // 中队地址
    private String contactPhone; // 联系电话
    private String organizationInsideId; // 机构编号
    private String organizationShortName; // LED名称(机构简称)
    private Integer organizationOrderNum; // LED顺序
    private String dlwz; // 地理位置
    private String organizationDesc; // 详细描述
    private String organizationRepealStatus ; // 是否是作战机构

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getOrganizationInsideId() {
        return organizationInsideId;
    }

    public void setOrganizationInsideId(String organizationInsideId) {
        this.organizationInsideId = organizationInsideId;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        this.organizationShortName = organizationShortName;
    }

    public Integer getOrganizationOrderNum() {
        return organizationOrderNum;
    }

    public void setOrganizationOrderNum(Integer organizationOrderNum) {
        this.organizationOrderNum = organizationOrderNum;
    }

    public String getDlwz() {
        return dlwz;
    }

    public void setDlwz(String dlwz) {
        this.dlwz = dlwz;
    }

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
    }

    public String getOrganizationRepealStatus() {
        return organizationRepealStatus;
    }

    public void setOrganizationRepealStatus(String organizationRepealStatus) {
        this.organizationRepealStatus = organizationRepealStatus;
    }
}
