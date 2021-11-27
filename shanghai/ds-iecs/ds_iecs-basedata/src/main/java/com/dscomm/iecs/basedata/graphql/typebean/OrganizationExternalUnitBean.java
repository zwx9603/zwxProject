package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 消防机构 装备外部单位
 *
 */
public class OrganizationExternalUnitBean extends BaseBean {

    private String externalUnitName; //外部单位名称

    private String externalUnitAddress; //单位地址

    private String responsiblePersonName;//负责人姓名

    private String contactPhone;// 联系电话

    private String mobilePhone; // 移动电话

    private String externalUnitWebsite; // 单位网址

    private String internetEmail; // 因特网电子邮箱

    private String synopsis;// 简介

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String relationId; // 关联ID

    private String organizationId;//所属机构

    private String organizationName;//所属机构

    private String remarks; // 备注

    public String getExternalUnitName() {
        return externalUnitName;
    }

    public void setExternalUnitName(String externalUnitName) {
        this.externalUnitName = externalUnitName;
    }

    public String getExternalUnitAddress() {
        return externalUnitAddress;
    }

    public void setExternalUnitAddress(String externalUnitAddress) {
        this.externalUnitAddress = externalUnitAddress;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getExternalUnitWebsite() {
        return externalUnitWebsite;
    }

    public void setExternalUnitWebsite(String externalUnitWebsite) {
        this.externalUnitWebsite = externalUnitWebsite;
    }

    public String getInternetEmail() {
        return internetEmail;
    }

    public void setInternetEmail(String internetEmail) {
        this.internetEmail = internetEmail;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
