package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：联勤保障单位
 *
 */
public class UnitJointServiceBean extends BaseBean {

    private String unitName; //单位名称

    private String supportability; //保障能力

    private String mainSupportCategoryCode; //主要保障类别代码

    private String mainSupportCategoryName; //主要保障类别名称

    private String dutyPhone; //应急值班电话

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String relationId; //关联ID

    private String organizationId; //维护消防机构ID

    private String organizationName; //维护消防机构名称

    private String whetherInnerOrganization; //是否为内部机构

    private String innerOrganizationId; //内部机构ID

    private String outerOrganizationId; //外部机构ID

    private String hospitalLevelCode ; //医院等级代码

    private String hospitalLevelName ;//医院等级名称

    private String remarks; // 备注

    private String unitAddress; //todo 字段 单位地址

    private String faxNumber;//todo 字段  传真号码

    private String unitDesc;// todo 字段 单位_简要情况

    private String  supportDesc; //todo 字段 保障概述_简要情况

    private String contactPerson ;//todo 字段  联系人

    private String contactPersonName ;// todo 字段 联系人 姓名

    private String contactPhone;//todo 字段 联系人  联系电话

    private String districtCode;// todo 字段  行政区代码

    private String districtName;//  todo 字段 行政区代码名称

    public String getSupportDesc() {
        return supportDesc;
    }

    public void setSupportDesc(String supportDesc) {
        this.supportDesc = supportDesc;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSupportability() {
        return supportability;
    }

    public void setSupportability(String supportability) {
        this.supportability = supportability;
    }

    public String getMainSupportCategoryCode() {
        return mainSupportCategoryCode;
    }

    public void setMainSupportCategoryCode(String mainSupportCategoryCode) {
        this.mainSupportCategoryCode = mainSupportCategoryCode;
    }

    public String getDutyPhone() {
        return dutyPhone;
    }

    public void setDutyPhone(String dutyPhone) {
        this.dutyPhone = dutyPhone;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getWhetherInnerOrganization() {
        return whetherInnerOrganization;
    }

    public void setWhetherInnerOrganization(String whetherInnerOrganization) {
        this.whetherInnerOrganization = whetherInnerOrganization;
    }

    public String getInnerOrganizationId() {
        return innerOrganizationId;
    }

    public void setInnerOrganizationId(String innerOrganizationId) {
        this.innerOrganizationId = innerOrganizationId;
    }

    public String getOuterOrganizationId() {
        return outerOrganizationId;
    }

    public void setOuterOrganizationId(String outerOrganizationId) {
        this.outerOrganizationId = outerOrganizationId;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMainSupportCategoryName() {
        return mainSupportCategoryName;
    }

    public void setMainSupportCategoryName(String mainSupportCategoryName) {
        this.mainSupportCategoryName = mainSupportCategoryName;
    }

    public String getHospitalLevelCode() {
        return hospitalLevelCode;
    }

    public void setHospitalLevelCode(String hospitalLevelCode) {
        this.hospitalLevelCode = hospitalLevelCode;
    }

    public String getHospitalLevelName() {
        return hospitalLevelName;
    }

    public void setHospitalLevelName(String hospitalLevelName) {
        this.hospitalLevelName = hospitalLevelName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
