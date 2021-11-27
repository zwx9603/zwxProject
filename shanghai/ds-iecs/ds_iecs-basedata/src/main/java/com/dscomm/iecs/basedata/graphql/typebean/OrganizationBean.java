package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.tree.TreeNode;

/**
 * 描述： 消防机构信息
 *
 */
public class OrganizationBean extends TreeNode {


    private String organizationCode;// 机构代码

    private String organizationName;// 机构名称

    private String pinyinHeader; //机构名称拼音头

    private String organizationShortName  ;// 机构简称

    private String organizationAddress;// 机构地址

    private String organizationDesc;// 机构描述

    private String organizationParentId; // 上级机构ID

    private String organizationParentCode; // 上级机构代码

    private String organizationCategoryCode;// 机构类别

    private String organizationCategoryName;// 机构类别名称

    private String organizationTypeCode;// 机构类型代码

    private String organizationTypeName;// 机构类型名称

    private String organizationNatureCode;// 机构性质代码

    private String organizationNatureName;// 机构性质名称

    private String districtCode;// 行政区代码

    private String districtName;// 行政区名称

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String dispatchPhone;// 调度电话

    private String mailCode;// 邮政编码

    private String faxNumber;// 传真号码

    private String email;//电子信箱

    private String contactPerson;//   单位联系人

    private String contactPersonName ;//todo 字段 单位联系人名称

    private String contactPhone;// 单位联系电话

    private Integer organizationWeight;// 机构权重

    private String organizationInsideId;// 机构内部ID

    private String organizationTree;// 机构Tree

    private String searchPath;//查询码路径

    private String relationId;// 关联ID

    private String precinctRelevanceId;// 辖区管理ID

    private String domainName;// 所属域名

    private String picture;// 照片

    private Integer symbolOfDutyUnit;// 执勤单位标志

    private String precinctArea;// 辖区面积

    private String precinctRange;// 辖区范围

    private String organizationRepealStatus;// 机构撤销状态

    private  Long organizationRepealTime; // 机构撤销时间

    private String remarks; // 备注

    private Integer organizationOrderNum ; // 机构排序

    public Integer getOrganizationOrderNum() {
        return organizationOrderNum;
    }

    public void setOrganizationOrderNum(Integer organizationOrderNum) {
        this.organizationOrderNum = organizationOrderNum;
    }

    public String getPinyinHeader() {
        return pinyinHeader;
    }

    public void setPinyinHeader(String pinyinHeader) {
        this.pinyinHeader = pinyinHeader;
    }

    public String getOrganizationParentCode() {
        return organizationParentCode;
    }

    public void setOrganizationParentCode(String organizationParentCode) {
        this.organizationParentCode = organizationParentCode;
    }

    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        this.organizationShortName = organizationShortName;
    }

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationInsideId() {
        return organizationInsideId;
    }

    public void setOrganizationInsideId(String organizationInsideId) {
        this.organizationInsideId = organizationInsideId;
    }



    public String getOrganizationTree() {
        return organizationTree;
    }

    public void setOrganizationTree(String organizationTree) {
        this.organizationTree = organizationTree;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOrganizationNatureCode() {
        return organizationNatureCode;
    }

    public void setOrganizationNatureCode(String organizationNatureCode) {
        this.organizationNatureCode = organizationNatureCode;
    }

    public String getOrganizationTypeCode() {
        return organizationTypeCode;
    }

    public void setOrganizationTypeCode(String organizationTypeCode) {
        this.organizationTypeCode = organizationTypeCode;
    }

    public String getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(String organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public String getOrganizationCategoryCode() {
        return organizationCategoryCode;
    }

    public void setOrganizationCategoryCode(String organizationCategoryCode) {
        this.organizationCategoryCode = organizationCategoryCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setSymbolOfDutyUnit(Integer symbolOfDutyUnit) {
        this.symbolOfDutyUnit = symbolOfDutyUnit;
    }



    public Integer getOrganizationWeight() {
        return organizationWeight;
    }

    public void setOrganizationWeight(Integer organizationWeight) {
        this.organizationWeight = organizationWeight;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getDispatchPhone() {
        return dispatchPhone;
    }

    public void setDispatchPhone(String dispatchPhone) {
        this.dispatchPhone = dispatchPhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getOrganizationRepealStatus() {
        return organizationRepealStatus;
    }

    public void setOrganizationRepealStatus(String organizationRepealStatus) {
        this.organizationRepealStatus = organizationRepealStatus;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getPrecinctRelevanceId() {
        return precinctRelevanceId;
    }

    public void setPrecinctRelevanceId(String precinctRelevanceId) {
        this.precinctRelevanceId = precinctRelevanceId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public Integer getSymbolOfDutyUnit() {
        return symbolOfDutyUnit;
    }

    public String getOrganizationCategoryName() {
        return organizationCategoryName;
    }

    public void setOrganizationCategoryName(String organizationCategoryName) {
        this.organizationCategoryName = organizationCategoryName;
    }

    public String getOrganizationTypeName() {
        return organizationTypeName;
    }

    public void setOrganizationTypeName(String organizationTypeName) {
        this.organizationTypeName = organizationTypeName;
    }

    public String getOrganizationNatureName() {
        return organizationNatureName;
    }

    public void setOrganizationNatureName(String organizationNatureName) {
        this.organizationNatureName = organizationNatureName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getOrganizationRepealTime() {
        return organizationRepealTime;
    }

    public void setOrganizationRepealTime(Long organizationRepealTime) {
        this.organizationRepealTime = organizationRepealTime;
    }


    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPrecinctArea() {
        return precinctArea;
    }

    public void setPrecinctArea(String precinctArea) {
        this.precinctArea = precinctArea;
    }

    public String getPrecinctRange() {
        return precinctRange;
    }

    public void setPrecinctRange(String precinctRange) {
        this.precinctRange = precinctRange;
    }





}
