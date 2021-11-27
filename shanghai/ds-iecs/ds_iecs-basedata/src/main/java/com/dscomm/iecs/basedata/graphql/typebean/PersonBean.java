package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 人员基本信息
 */
public class PersonBean extends BaseBean {

    private String name; // 姓名

    private String IDCard; // 身份证号

    private String sexCode; // 性别代码

    private String sexName; // 性别名称

    private String authorizedOrganizationId; // 编制所在机构

    private String authorizedOrganizationName; // 编制所在机构名称

    private String actualOrganizationId; // 实际所在机构

    private String actualOrganizationName; // 实际所在机构名称

    private String nationCode; // 民族代码

    private String nationName; // 民族名称

    private String nativePlaceCode; // 籍贯代码

    private String nativePlaceName; // 籍贯名称

    private String politicalStatusCode; // 政治面貌代码

    private String politicalStatusName; // 政治面貌名称

    private Long partyTime; // 党团时间

    private Long dateBirth; // 出生日期

    private String educationCode; // 学历代码

    private String educationName; // 学历名称

    private String academicDegreeCode; // 学位代码

    private String academicDegreeName; // 学位名称

    private String professionCode; // 专业分类代码

    private String professionName; // 专业分类名称

    private String maritalStatusCode; // 婚姻状况代码

    private String maritalStatusName; // 婚姻状况名称

    private String address; // 通讯地址

    private String postalCode; // 邮政编码

    private String personCategoryCode; // 人员类别代码

    private String personCategoryName; // 人员类别名称

    private String reignStatusCode; // 在位情况代码

    private String reignStatusName; // 在位情况名称

    private String quartersCode; // 岗位代码

    private String quartersName; // 岗位代码

    private String dutiesCode; // 职务代码

    private String dutiesName; // 职务代码

    private String rankCode; // todo 字段  职位代码

    private String rankName; // todo 字段   职位代码

    private String credentialsType; // 证件类型

    private String credentialsTypeName; // 证件类型

    private String credentialsNumber; // 证件号码

    private String policeRankCode; // 警衔代码

    private String policeRankName; // 警衔名称

    private Integer whetherExpert; // 是否专家

    private String picture; // 照片

    private String remarks; // 备注

    private String personStatusCode; // 人员状况代码

    private String personStatusName; // 人员状况名称

    private Integer personOrderNum; // 人员序号

    private String personReignStatusCode; // 人员在位情况代码

    private String personReignStatusName; // 人员在位情况名称

    private String baseParentOrganizationId; // 原始上级机构ID

    private String baseParentOrganizationName; // 原始上级机构名称

    private Integer whetherLoad;//是否装配



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getAuthorizedOrganizationId() {
        return authorizedOrganizationId;
    }

    public void setAuthorizedOrganizationId(String authorizedOrganizationId) {
        this.authorizedOrganizationId = authorizedOrganizationId;
    }

    public String getAuthorizedOrganizationName() {
        return authorizedOrganizationName;
    }

    public void setAuthorizedOrganizationName(String authorizedOrganizationName) {
        this.authorizedOrganizationName = authorizedOrganizationName;
    }

    public String getActualOrganizationId() {
        return actualOrganizationId;
    }

    public void setActualOrganizationId(String actualOrganizationId) {
        this.actualOrganizationId = actualOrganizationId;
    }

    public String getActualOrganizationName() {
        return actualOrganizationName;
    }

    public void setActualOrganizationName(String actualOrganizationName) {
        this.actualOrganizationName = actualOrganizationName;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getNativePlaceCode() {
        return nativePlaceCode;
    }

    public void setNativePlaceCode(String nativePlaceCode) {
        this.nativePlaceCode = nativePlaceCode;
    }

    public String getNativePlaceName() {
        return nativePlaceName;
    }

    public void setNativePlaceName(String nativePlaceName) {
        this.nativePlaceName = nativePlaceName;
    }

    public String getPoliticalStatusCode() {
        return politicalStatusCode;
    }

    public void setPoliticalStatusCode(String politicalStatusCode) {
        this.politicalStatusCode = politicalStatusCode;
    }

    public String getPoliticalStatusName() {
        return politicalStatusName;
    }

    public void setPoliticalStatusName(String politicalStatusName) {
        this.politicalStatusName = politicalStatusName;
    }

    public Long getPartyTime() {
        return partyTime;
    }

    public void setPartyTime(Long partyTime) {
        this.partyTime = partyTime;
    }

    public Long getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Long dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getAcademicDegreeCode() {
        return academicDegreeCode;
    }

    public void setAcademicDegreeCode(String academicDegreeCode) {
        this.academicDegreeCode = academicDegreeCode;
    }

    public String getAcademicDegreeName() {
        return academicDegreeName;
    }

    public void setAcademicDegreeName(String academicDegreeName) {
        this.academicDegreeName = academicDegreeName;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setMaritalStatusCode(String maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPersonCategoryCode() {
        return personCategoryCode;
    }

    public void setPersonCategoryCode(String personCategoryCode) {
        this.personCategoryCode = personCategoryCode;
    }

    public String getPersonCategoryName() {
        return personCategoryName;
    }

    public void setPersonCategoryName(String personCategoryName) {
        this.personCategoryName = personCategoryName;
    }

    public String getReignStatusCode() {
        return reignStatusCode;
    }

    public void setReignStatusCode(String reignStatusCode) {
        this.reignStatusCode = reignStatusCode;
    }

    public String getReignStatusName() {
        return reignStatusName;
    }

    public void setReignStatusName(String reignStatusName) {
        this.reignStatusName = reignStatusName;
    }

    public String getQuartersCode() {
        return quartersCode;
    }

    public void setQuartersCode(String quartersCode) {
        this.quartersCode = quartersCode;
    }

    public String getQuartersName() {
        return quartersName;
    }

    public void setQuartersName(String quartersName) {
        this.quartersName = quartersName;
    }

    public String getDutiesCode() {
        return dutiesCode;
    }

    public void setDutiesCode(String dutiesCode) {
        this.dutiesCode = dutiesCode;
    }

    public String getDutiesName() {
        return dutiesName;
    }

    public void setDutiesName(String dutiesName) {
        this.dutiesName = dutiesName;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNumber() {
        return credentialsNumber;
    }

    public void setCredentialsNumber(String credentialsNumber) {
        this.credentialsNumber = credentialsNumber;
    }

    public String getPoliceRankCode() {
        return policeRankCode;
    }

    public void setPoliceRankCode(String policeRankCode) {
        this.policeRankCode = policeRankCode;
    }

    public String getPoliceRankName() {
        return policeRankName;
    }

    public void setPoliceRankName(String policeRankName) {
        this.policeRankName = policeRankName;
    }

    public Integer getWhetherExpert() {
        return whetherExpert;
    }

    public void setWhetherExpert(Integer whetherExpert) {
        this.whetherExpert = whetherExpert;
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

    public String getPersonStatusCode() {
        return personStatusCode;
    }

    public void setPersonStatusCode(String personStatusCode) {
        this.personStatusCode = personStatusCode;
    }

    public String getPersonStatusName() {
        return personStatusName;
    }

    public void setPersonStatusName(String personStatusName) {
        this.personStatusName = personStatusName;
    }

    public Integer getPersonOrderNum() {
        return personOrderNum;
    }

    public void setPersonOrderNum(Integer personOrderNum) {
        this.personOrderNum = personOrderNum;
    }

    public String getPersonReignStatusCode() {
        return personReignStatusCode;
    }

    public void setPersonReignStatusCode(String personReignStatusCode) {
        this.personReignStatusCode = personReignStatusCode;
    }

    public String getPersonReignStatusName() {
        return personReignStatusName;
    }

    public void setPersonReignStatusName(String personReignStatusName) {
        this.personReignStatusName = personReignStatusName;
    }

    public String getBaseParentOrganizationId() {
        return baseParentOrganizationId;
    }

    public void setBaseParentOrganizationId(String baseParentOrganizationId) {
        this.baseParentOrganizationId = baseParentOrganizationId;
    }

    public String getBaseParentOrganizationName() {
        return baseParentOrganizationName;
    }

    public void setBaseParentOrganizationName(String baseParentOrganizationName) {
        this.baseParentOrganizationName = baseParentOrganizationName;
    }

    public Integer getWhetherLoad() {
        return whetherLoad;
    }

    public void setWhetherLoad(Integer whetherLoad) {
        this.whetherLoad = whetherLoad;
    }

    public String getCredentialsTypeName() {
        return credentialsTypeName;
    }

    public void setCredentialsTypeName(String credentialsTypeName) {
        this.credentialsTypeName = credentialsTypeName;
    }

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
}
