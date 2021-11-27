package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：应急联动单位
 *
 */
public class UnitEmergencyBean extends BaseBean {

    private String districtCode; //所属行政区代码

    private String districtName; //所属行政区名称

    private String unitName; //单位名称

    private String unitAddress; //单位地址

    private String unitType; //单位类型

    private String unitTypeName; //单位类型

    private String emergencyContent; //应急服务内容

    private String contactPhone;// 联系电话

    private String faxNumber;// 传真号码

    private String organizationId;// 所属辖区 机构id

    private String organizationName;// 所属辖区 机构名称

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String gisRelationId ;//GIS关联ID

    private String picture;// 照片

    private String remarks; // 备注

    private String unitDesc;// todo 字段   单位_简要情况

    private String contactPerson ; //todo 字段  联系人

    private String contactPersonName ;// todo 字段  联系人 姓名




    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }


    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getEmergencyContent() {
        return emergencyContent;
    }

    public void setEmergencyContent(String emergencyContent) {
        this.emergencyContent = emergencyContent;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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

    public String getGisRelationId() {
        return gisRelationId;
    }

    public void setGisRelationId(String gisRelationId) {
        this.gisRelationId = gisRelationId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }
}
