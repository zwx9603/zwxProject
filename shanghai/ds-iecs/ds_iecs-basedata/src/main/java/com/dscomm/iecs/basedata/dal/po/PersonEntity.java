package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 消防救援 人员基本信息
 */
@Entity
@Table(name = "RYXX_RYJBXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PersonEntity extends BaseEntity {

    @Column(name = "XFJYRY_TYWYSBM", length = 32)
    private String idCode; //todo 字段 消防救援人员_通用唯一识别码

    @Column(name = "XM", length = 50)
    private String name; // 姓名

    @Column(name = "SFZH", length = 100)
    private String IDCard; // 身份证号

//   @Column(name = "ZJLX", length = 100)
   @Column(name = "CYZJLXDM", length = 100)
    private String credentialsType; //todo 字典 证件类型代码

    @Column(name = "ZJHM", length = 100)
    private String credentialsNumber; // 证件号码

    @Column(name = "XBDM", length = 100)
    private String sexCode; // 性别代码

//    @Column(name = "BZJGID", length = 100)
    @Column(name = "BZSZJG_TYWYSBM", length = 100)
    private String authorizedOrganizationId; //todo 字段 编制所在机构

//    @Column(name = "SJJGID", length = 100)
  @Column(name = "SJSZJG_TYWYSBM", length = 100)
    private String actualOrganizationId; //todo 字段  实际所在机构

    @Column(name = "MZDM", length = 100)
    private String nationCode; // 民族代码

    @Column(name = "JGDM", length = 100)
    private String nativePlaceCode; // 籍贯代码

    @Column(name = "ZZMMDM", length = 100)
    private String politicalStatusCode; // 政治面貌代码

    @Column(name = "DTSJ")
    private Long partyTime; // 党团时间

    @Column(name = "CSRQ")
    private Long dateBirth; // 出生日期

    @Column(name = "XLDM", length = 100)
    private String educationCode; // 学历代码

    @Column(name = "XWDM", length = 100)
    private String academicDegreeCode; // 学位代码

//    @Column(name = "ZYFLDM", length = 100)
   @Column(name = "XFZJLYLBDM", length = 100)
    private String professionCode; //todo 字段 消防专家领域类别代码

    @Column(name = "HYZKDM", length = 100)
    private String maritalStatusCode; // 婚姻状况代码

    @Column(name = "TXDZ", length = 400)
    private String address; // 通讯地址

    @Column(name = "YZBM", length = 50)
    private String postalCode; // 邮政编码

//   @Column(name = "RYLBDM", length = 100)
     @Column(name = "XFJYRYLBDM", length = 100)
    private String personCategoryCode; //todo 字段  消防救援人员类别代码

//    @Column(name = "RYZK", length = 200 )
    @Column(name = "XFJYRYZTDM", length = 200 )
    private String  personStatusCode; //todo 字段 消防救援人员状态代码

//    @Column(name = "RYZWQK", length = 100 )
    @Column(name = "XFJYRYZWQKDM", length = 100 )
    private String personReignStatusCode; //todo 字段 消防救援人员在位情况代码

//    @Column(name = "GWDM", length = 100)
    @Column(name = "XFGWFLYDM", length = 100)
    private String quartersCode; //todo 字段 消防岗位分类与代码

    @Column(name = "ZYJSZWLBDM", length = 100)
    private String dutiesCode; ///todo 字段 专业技术职务类别代码

    @Column(name = "ZWDM", length = 100)
    private String rankCode; ///todo 字段 职位代码

//    @Column(name = "JXDM", length = 100)
    @Column(name = "XFJYXJBDM", length = 100)
    private String policeRankCode; ///todo 字段 警衔代码

//   @Column(name = "SFZJ" )
    @Column(name = "SFZJ_PDBZ" )
    private Integer whetherExpert; ///todo 字段 是否专家
    
    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "ZWQKDM", length = 100)
    private String reignStatusCode   ; // 在位情况代码

    @Column(name = "ZP", length = 400 )
    private String picture; // 照片

    @Column(name = "RYXH")
    private Integer personOrderNum; // 人员序号 排序

   @Column(name = "YSSJJGID", length = 100 )
    private String baseParentOrganizationId; // 原始上级机构ID

    @Column(name = "SFZP"  )
    private Integer whetherLoad;//是否装配

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本


    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

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

    public String getAuthorizedOrganizationId() {
        return authorizedOrganizationId;
    }

    public void setAuthorizedOrganizationId(String authorizedOrganizationId) {
        this.authorizedOrganizationId = authorizedOrganizationId;
    }

    public String getActualOrganizationId() {
        return actualOrganizationId;
    }

    public void setActualOrganizationId(String actualOrganizationId) {
        this.actualOrganizationId = actualOrganizationId;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNativePlaceCode() {
        return nativePlaceCode;
    }

    public void setNativePlaceCode(String nativePlaceCode) {
        this.nativePlaceCode = nativePlaceCode;
    }

    public String getPoliticalStatusCode() {
        return politicalStatusCode;
    }

    public void setPoliticalStatusCode(String politicalStatusCode) {
        this.politicalStatusCode = politicalStatusCode;
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

    public String getAcademicDegreeCode() {
        return academicDegreeCode;
    }

    public void setAcademicDegreeCode(String academicDegreeCode) {
        this.academicDegreeCode = academicDegreeCode;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setMaritalStatusCode(String maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
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

    public String getReignStatusCode() {
        return reignStatusCode;
    }

    public void setReignStatusCode(String reignStatusCode) {
        this.reignStatusCode = reignStatusCode;
    }

    public String getQuartersCode() {
        return quartersCode;
    }

    public void setQuartersCode(String quartersCode) {
        this.quartersCode = quartersCode;
    }

    public String getDutiesCode() {
        return dutiesCode;
    }

    public void setDutiesCode(String dutiesCode) {
        this.dutiesCode = dutiesCode;
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

    public String getBaseParentOrganizationId() {
        return baseParentOrganizationId;
    }

    public void setBaseParentOrganizationId(String baseParentOrganizationId) {
        this.baseParentOrganizationId = baseParentOrganizationId;
    }

    public Integer getWhetherLoad() {
        return whetherLoad;
    }

    public void setWhetherLoad(Integer whetherLoad) {
        this.whetherLoad = whetherLoad;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode;
    }
}
