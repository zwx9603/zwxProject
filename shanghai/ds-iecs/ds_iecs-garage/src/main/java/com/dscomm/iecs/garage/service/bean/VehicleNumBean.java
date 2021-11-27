package com.dscomm.iecs.garage.service.bean;


/**
 * 描述： 机构车辆数量信息
 *
 */
public class VehicleNumBean {

    private String organizationTree;// 机构Tree

    private String organizationId;// 机构id

    private String organizationName;// 机构名称

    private String brigadeOrganizationId; // 所属大队

    private String brigadeOrganizationName; // 所属大队名称

    private String detachmentOrganizationId; // 所属支队

    private String detachmentOrganizationName; // 所属支队

    private String dimensionMainNun = "0" ;// 车辆总数

    private String dimensionSecondaryNum  = "0"  ;//出动车辆总数

    private String contactPhone;// 单位联系电话

    private String organizationShortName;// 机构简称

    public String getOrganizationTree() {
        return organizationTree;
    }

    public void setOrganizationTree(String organizationTree) {
        this.organizationTree = organizationTree;
    }

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

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getBrigadeOrganizationName() {
        return brigadeOrganizationName;
    }

    public void setBrigadeOrganizationName(String brigadeOrganizationName) {
        this.brigadeOrganizationName = brigadeOrganizationName;
    }

    public String getDetachmentOrganizationId() {
        return detachmentOrganizationId;
    }

    public void setDetachmentOrganizationId(String detachmentOrganizationId) {
        this.detachmentOrganizationId = detachmentOrganizationId;
    }

    public String getDetachmentOrganizationName() {
        return detachmentOrganizationName;
    }

    public void setDetachmentOrganizationName(String detachmentOrganizationName) {
        this.detachmentOrganizationName = detachmentOrganizationName;
    }

    public String getDimensionMainNun() {
        return dimensionMainNun;
    }

    public void setDimensionMainNun(String dimensionMainNun) {
        this.dimensionMainNun = dimensionMainNun;
    }

    public String getDimensionSecondaryNum() {
        return dimensionSecondaryNum;
    }

    public void setDimensionSecondaryNum(String dimensionSecondaryNum) {
        this.dimensionSecondaryNum = dimensionSecondaryNum;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        this.organizationShortName = organizationShortName;
    }
}
