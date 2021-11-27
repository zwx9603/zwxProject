package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 专家信息 由 内部专家与社会专家 组成  视图
 *
 */
public class ExpertBean extends BaseBean {

    private String IDCard; //todo 字段 公民身份号码

    private String expertName;//专家姓名

    private String expertSex ;//专家性别

    private String expertSexName ;//专家性别

    private String organizationId;//所属机构

    private String organizationName;//机构名称

    private String  unitName ; //单位名称

    private String expertType;//专家类型 主要分内部专家 社会专家两大类 0 社会专家 1 内部专家

    private Integer whetherInnerExpert ;//todo 字段 是否队伍内部专家_判断标识

    private String expertField;//专家领域

    private String expertFieldName;//专家领域

    private String nativePlace;//籍贯

    private String nativePlaceName;//籍贯

    private Long dateBirth;//出生日期

    private String duties;//职务

    private String education;//学历

    private String educationName;//学历名称

    private String address;//通讯地址

    private String postalCode;//todo 字段 邮政编码

    private String homePhone;//家庭电话

    private String mobilePhone;//todo 字段 移动电话

    private String officePhone;//办公电话

    private String nationCode;//民族 社会专家为空 null

    private String political;//政治面貌 社会专家为空 null

    private String personCategory;//人员类别 社会专家为空 null

    private String personReign;//人员在位情况 社会专家为空 null

    private String quarters;//todo 字段 消防岗位类别代码

    private String quartersName;//岗位 社会专家为空 null 消防岗位类别名称

    private String personStatus;//人员状况 社会专家为空 null

    private String picture; // 照片

    private String remarks; // 备注

    private String addressName;//todo 字段 居住_地址名称

    private String districtCode; //todo 字段 行政区划代码

    private String districtName; //todo 字段 行政区划代码

    private String person;//人员状况 社会专家为空 null


    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getExpertSex() {
        return expertSex;
    }

    public void setExpertSex(String expertSex) {
        this.expertSex = expertSex;
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

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }

    public String getExpertField() {
        return expertField;
    }

    public void setExpertField(String expertField) {
        this.expertField = expertField;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Long getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Long dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getExpertSexName() {
        return expertSexName;
    }

    public void setExpertSexName(String expertSexName) {
        this.expertSexName = expertSexName;
    }

    public Integer getWhetherInnerExpert() {
        return whetherInnerExpert;
    }

    public void setWhetherInnerExpert(Integer whetherInnerExpert) {
        this.whetherInnerExpert = whetherInnerExpert;
    }

    public String getExpertFieldName() {
        return expertFieldName;
    }

    public void setExpertFieldName(String expertFieldName) {
        this.expertFieldName = expertFieldName;
    }

    public String getNativePlaceName() {
        return nativePlaceName;
    }

    public void setNativePlaceName(String nativePlaceName) {
        this.nativePlaceName = nativePlaceName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getQuarters() {
        return quarters;
    }

    public void setQuarters(String quarters) {
        this.quarters = quarters;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getPersonCategory() {
        return personCategory;
    }

    public void setPersonCategory(String personCategory) {
        this.personCategory = personCategory;
    }

    public String getPersonReign() {
        return personReign;
    }

    public void setPersonReign(String personReign) {
        this.personReign = personReign;
    }

    public String getQuartersName() {
        return quartersName;
    }

    public void setQuartersName(String quartersName) {
        this.quartersName = quartersName;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
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
}
