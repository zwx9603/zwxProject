package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 专家信息
 *
 */
@Entity
@Table(name = "T_COC_FIRE_ZJXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ExpertEntity extends BaseEntity {

    @Column(name = "MHJYZJ_TYWYSBM", length = 32)
    private String idCode; //todo 字段 灭火救援专家_通用唯一识别码

    @Column(name = "GMSFHM", length = 50)
    private String IDCard; //todo 字段 公民身份号码

    @Column(name="XM", length = 100)
    private String expertName;//专家姓名

//    @Column(name="XB", length = 100)
    @Column(name="XBDM", length = 100)
    private String expertSex ;//todo 字段 专家性别

//    @Column(name="XL", length = 100)
    @Column(name="XLDM", length = 100)
    private String education;//todo 字段 学历

    @Column(name = "ZP", length = 400 )
    private String picture; // 照片

//    @Column(name="CSRQ" )
    @Column(name = "CS_RQ")
    private Long dateBirth; //todo 字段 出生日期

    @Column(name="JZH_DZMC", length = 400 )
    private String addressName;//todo 字段 居住_地址名称

    @Column(name="TXDZ", length = 400 )
    private String address;//通讯地址

    @Column(name="YZBM", length = 50 )
    private String postalCode;//邮政编码


//    @Column(name="YDDH", length = 50)
    @Column(name = "YD_LXDH", length = 50)
    private String mobilePhone; //todo 字段 移动电话

//    @Column(name="JTDH", length = 50)
    @Column(name = "JT_LXDH", length = 50)
    private String homePhone; // todo 字段 家庭电话

//    @Column(name="BGDH", length = 50)
    @Column(name = "BG_LXDH", length = 50)
    private String officePhone; //todo 字段 办公电话

    @Column(name = "DWMC", length = 100)
    private String unitName;//todo 字段 单位名称

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "ZJLX", length = 100)
    private String expertType;//专家类型 主要分内部专家 社会专家两大类 0 社会专家 1 内部专家

    @Column(name="SFDWNBZJ_PDBZ" )
    private Integer whetherInnerExpert ;//todo 字段 是否队伍内部专家_判断标识

//    @Column(name = "ZJLY", length = 800)
    @Column(name = "XFZJLYLBDM", length = 800)
    private String expertField; //todo 字段 消防专家领域类别代码

//    @Column(name="SJJGID", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String organizationId; //todo 字段 消防救援机构_通用唯一识别码

    @Column(name = "XZQHDM", length = 100)
    private String districtCode; //todo 字段 行政区划代码

//  @Column(name="JG", length = 100)
    @Column(name="JGDM", length = 100)
    private String nativePlace;//todo 字段 籍贯

    @Column(name="ZWMC", length = 100)
    private String duties;//职务

    @Column(name="MZ", length = 100)
    private String nationCode;//民族 社会专家为空 null

    @Column(name="ZZMM", length = 100)
    private String political;//政治面貌 社会专家为空 null

    @Column(name="RYLB", length = 100)
    private String personCategory;//人员类别 社会专家为空 null

    @Column(name="RYZWQK", length = 100)
    private String personReign;//人员在位情况 社会专家为空 null

//    @Column(name="GW", length = 100)
    @Column(name="XFGWLBDM", length = 100)
    private String quarters;//todo 字段 岗位 社会专家为空 null

    @Column(name="RYZK", length = 100)
    private String person;//人员状况 社会专家为空 null

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Long dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }

    public Integer getWhetherInnerExpert() {
        return whetherInnerExpert;
    }

    public void setWhetherInnerExpert(Integer whetherInnerExpert) {
        this.whetherInnerExpert = whetherInnerExpert;
    }

    public String getExpertField() {
        return expertField;
    }

    public void setExpertField(String expertField) {
        this.expertField = expertField;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
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

    public String getQuarters() {
        return quarters;
    }

    public void setQuarters(String quarters) {
        this.quarters = quarters;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }
}
