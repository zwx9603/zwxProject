package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:灭火重点单位
 */
@Entity
@Table(name = "YAGL_MHDW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class KeyUnitEntity extends BaseEntity {

    @Column(name = "ZDDW_TYWYSBM", length = 100)
    private String idCode; //todo  字段 重点单位_通用唯一识别码

    @Column(name = "FHDWID", length = 100)
    private String fireproofUnit; //防火单位ID

    @Column(name = "DWMC", length = 200)
    private String unitName; //单位名称

    @Column(name = "DWPYJC", length = 200)
    private String unitShortName; //单位拼音简称

//    @Column(name = "JJSYZ", length = 100)
    @Column(name = "JJSYZLXDM", length = 100)
    private String economicOwnership; //todo 字段 经济所有制代码

//    @Column(name = "DWCLSJ")
    @Column(name = "CL_RQ")
    private Long unitFoundingTime; //todo 字段 单位成立时间 成立_日期

    @Column(name = "DWHZWHXFLYDM" , length = 100 )
    private String unitFireTypeCode; //todo 字段 单位火灾危害性分类与代码

    @Column(name = "DWDJ", length = 100)
    private String unitLevelCode; //单位等级

    @Column(name = "DWFL", length = 100)
    private String unitClassCode; //单位分类 01防火单位 02灭火单位

    @Column(name = "DWLB", length = 100)
    private String unitCategoryCode;// 机构类别

    @Column(name = "DWLX", length = 100)
    private String unitTypeCode; //单位类型

//    @Column(name = "DWXZ", length = 100)
    @Column(name = "DWZRXZDM", length = 100)
    private String unitNatureCode; //todo 字段 单位自然性质代码

//    @Column(name = "XZQY", length = 100)
    @Column(name = "XZQHDM", length = 100)
    private String districtCode; //todo 字段 行政区域

    @Column(name = "DW_JYQK", length = 800)
    private String unitDesc; //todo 字段 单位_简要情况

    @Column(name = "SFTDHSSTD_JYQK", length = 800)
    private String fireEscapeDesc; //todo 字段 消防通道或疏散通道_简要情况

    @Column(name = "NBXFSS_JYQK", length = 800)
    private String internalFireFacilitiesDesc; //todo 字段 内部消防设施_简要情况

    @Column(name = "FHSS_JYQK", length = 800)
    private String firePreventionFacilitiesDesc; //todo 字段 防火设施_简要情况

    @Column(name = "XFKZS_JYQK", length = 800)
    private String fireControlRoomDesc ; //todo 字段 消防控制室_简要情况


    @Column(name = "FRDB", length = 100)
    private String legalPerson; //法人代表id

    @Column(name = "FRDB_XM", length = 100)
    private String legalPersonName; //todo 字段 法人代表姓名

//    @Column(name = "FRDBSFZ", length = 100)
    @Column(name = "FRDB_GMSFZH", length = 100)
    private String legalPersonId; //todo 字段 法人代表身份证

//    @Column(name = "FRDBDH", length = 100)
    @Column(name = "FRDB_LXDH", length = 100)
    private String legalPersonPhone; //todo 字典 法人电话

//    @Column(name = "AQZRR", length = 100)
    @Column(name = "XFAQZRR", length = 100)
    private String securityPerson; //todo 字段 消防安全责任人

    @Column(name = "XFAQZRR_XM", length = 100)
    private String securityPersonName; //todo 字段 消防安全责任人 姓名

//    @Column(name = "AQZRRSFZ", length = 100)
    @Column(name = "XFAQZRR_GMSFHM", length = 100)
    private String securityPersonId; //todo 字段 消防安全责任人 公民身份证号

//    @Column(name = "AQZRRDH", length = 100)
    @Column(name = "XFAQZRR_LXDH", length = 100)
    private String securityPersonPhone; //todo 字段 消防安全责任人 联系电话

//    @Column(name = "DWDZYX", length = 100)
    @Column(name = "DZXX", length = 100)
    private String unitEmail; //todo 字段 单位电子邮箱

//    @Column(name = "AQGLR", length = 100)
    @Column(name = "XFAQGLR", length = 100)
    private String securityManage; //todo 字段 消防安全管理人

    @Column(name = "XFAQGLR_XM", length = 100)
    private String securityManageName; //todo 字段 消防安全管理人姓名

//    @Column(name = "AQGLRSFZ", length = 100)
    @Column(name = "XFAQGLR_GMSFHM", length = 100)
    private String securityManageId; //todo 字段 消防安全管理人身份证

//    @Column(name = "AQGLRDH", length = 100)
    @Column(name = "XFAQGLR_LXDH", length = 100)
    private String securityManagePhone; //todo 字段 消防安全管理人电话

    @Column(name = "ZJZXFGLR", length = 100)
    private String fireManager; //专兼职消防管理人

    @Column(name = "ZJZXFGLR_XM", length = 100)
    private String fireManagerName; //todo 字段专兼职消防管理人姓名

//    @Column(name = "ZJZXFGLRSFZ", length = 100)
    @Column(name = "ZJZXFGLR_GMSFHM", length = 100)
    private String fireManagerId; //todo 字段专兼职消防管理人身份证

//    @Column(name = "ZJZXFGLRDH", length = 100)
    @Column(name = "ZJZXFGLR_LXDH", length = 100)
    private String fireManagerPhone; //todo 字段专兼职消防管理人电话

//    @Column(name = "DWDZ", length = 400)
    @Column(name = "DZMC", length = 400)
    private String unitAddress; //todo 字段 单位地址

//    @Column(name = "DWDH", length = 100)
    @Column(name = "LXDH", length = 100)
    private String unitPhone; //todo 字段 单位电话 联系电话

    @Column(name = "YZBM", length = 100)
    private String mailCode; //单位邮政编码

    @Column(name = "GDZC")
    private Float fixedAssets; //固定资产

//    @Column(name = "ZGRS")
    @Column(name = "RS")
    private Integer unitWorkers; //todo 职工人数

//    @Column(name = "ZDMJ" )
    @Column(name = "ZD_MJ" )
    private Float constructionArea; //todo 字段 占地面积

//    @Column(name = "JZMJ" )
    @Column(name = "JZ_MJ" )
    private Float builtUpArea; //todo 字段 建筑面积

    @Column(name = "DWZSX", length = 400)
    private String unitPrincipalAttribute; //单位主属性

    @Column(name = "DWCSX", length = 400)
    private String unitAttribute; //单位次属性

    @Column(name = "ZDXFSS")
    private Integer fireConDev; //自动消防设施情况 0无自动 1有自动

//    @Column(name = "XFGXJGID", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String jurisdictionOrganizationId; //todo 字段 消防管辖机构ID

//    @Column(name = "GIS_X", length = 50)
    @Column(name = "DQJD", length = 50)
    private String longitude; //todo 字段 经度

//    @Column(name = "GIS_Y", length = 50)
    @Column(name = "DQWD", length = 50)
    private String latitude; //todo 字段 纬度

    @Column(name = "DLWZ", length = 400)
    private String geographicalPosition; //地理位置

//    @Column(name = "JZSL")
    @Column(name = "JZ_SL")
    private Integer buildNum; //todo 字段 建筑数量

    @Column(name = "QTXTLJ" , length =  400 )
    private String  otherSystemUrl ;//第三方系统重点单位信息访问路径

    @Column(name = "CZYID", length = 100)
    private String operatorId; //操作员ID

    @Column(name = "JGID", length = 100)
    private String organizationId; //机构ID

    @Column(name = "GLID", length = 100)
    private String relationId; //关联ID

    @Column(name = "BZ", length = 800)
    private String remarks; //备注

    @Column(name = "VERCOL")
    private Long versionStamp; //版本戳

    @Column(name = "SJBB", length = 100)
    private String SJBB; //数据版本

    @Column(name = "CSZT")
    private Integer CSZT; //传输状态

    @Column(name = "JLZT")
    private Integer JLZT; //记录状态

    @Column(name = "SJC")
    private Long SJC; //时间戳

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID; //业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB; //基库数据版本


    public String getUnitCategoryCode() {
        return unitCategoryCode;
    }

    public void setUnitCategoryCode(String unitCategoryCode) {
        this.unitCategoryCode = unitCategoryCode;
    }

    public String getUnitTypeCode() {
        return unitTypeCode;
    }

    public void setUnitTypeCode(String unitTypeCode) {
        this.unitTypeCode = unitTypeCode;
    }

    public String getFireproofUnit() {
        return fireproofUnit;
    }

    public void setFireproofUnit(String fireproofUnit) {
        this.fireproofUnit = fireproofUnit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitShortName() {
        return unitShortName;
    }

    public void setUnitShortName(String unitShortName) {
        this.unitShortName = unitShortName;
    }

    public String getEconomicOwnership() {
        return economicOwnership;
    }

    public void setEconomicOwnership(String economicOwnership) {
        this.economicOwnership = economicOwnership;
    }

    public Long getUnitFoundingTime() {
        return unitFoundingTime;
    }

    public void setUnitFoundingTime(Long unitFoundingTime) {
        this.unitFoundingTime = unitFoundingTime;
    }

    public String getUnitLevelCode() {
        return unitLevelCode;
    }

    public void setUnitLevelCode(String unitLevelCode) {
        this.unitLevelCode = unitLevelCode;
    }

    public String getUnitNatureCode() {
        return unitNatureCode;
    }

    public void setUnitNatureCode(String unitNatureCode) {
        this.unitNatureCode = unitNatureCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId;
    }

    public String getLegalPersonPhone() {
        return legalPersonPhone;
    }

    public void setLegalPersonPhone(String legalPersonPhone) {
        this.legalPersonPhone = legalPersonPhone;
    }

    public String getSecurityPerson() {
        return securityPerson;
    }

    public void setSecurityPerson(String securityPerson) {
        this.securityPerson = securityPerson;
    }

    public String getSecurityPersonId() {
        return securityPersonId;
    }

    public void setSecurityPersonId(String securityPersonId) {
        this.securityPersonId = securityPersonId;
    }

    public String getSecurityPersonPhone() {
        return securityPersonPhone;
    }

    public void setSecurityPersonPhone(String securityPersonPhone) {
        this.securityPersonPhone = securityPersonPhone;
    }

    public String getUnitEmail() {
        return unitEmail;
    }

    public void setUnitEmail(String unitEmail) {
        this.unitEmail = unitEmail;
    }

    public String getSecurityManage() {
        return securityManage;
    }

    public void setSecurityManage(String securityManage) {
        this.securityManage = securityManage;
    }

    public String getSecurityManageId() {
        return securityManageId;
    }

    public void setSecurityManageId(String securityManageId) {
        this.securityManageId = securityManageId;
    }

    public String getSecurityManagePhone() {
        return securityManagePhone;
    }

    public void setSecurityManagePhone(String securityManagePhone) {
        this.securityManagePhone = securityManagePhone;
    }

    public String getFireManager() {
        return fireManager;
    }

    public void setFireManager(String fireManager) {
        this.fireManager = fireManager;
    }

    public String getFireManagerId() {
        return fireManagerId;
    }

    public void setFireManagerId(String fireManagerId) {
        this.fireManagerId = fireManagerId;
    }

    public String getFireManagerPhone() {
        return fireManagerPhone;
    }

    public void setFireManagerPhone(String fireManagerPhone) {
        this.fireManagerPhone = fireManagerPhone;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public Float getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(Float fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public Integer getUnitWorkers() {
        return unitWorkers;
    }

    public void setUnitWorkers(Integer unitWorkers) {
        this.unitWorkers = unitWorkers;
    }

    public Float getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Float constructionArea) {
        this.constructionArea = constructionArea;
    }

    public Float getBuiltUpArea() {
        return builtUpArea;
    }

    public void setBuiltUpArea(Float builtUpArea) {
        this.builtUpArea = builtUpArea;
    }

    public String getUnitPrincipalAttribute() {
        return unitPrincipalAttribute;
    }

    public void setUnitPrincipalAttribute(String unitPrincipalAttribute) {
        this.unitPrincipalAttribute = unitPrincipalAttribute;
    }

    public String getUnitAttribute() {
        return unitAttribute;
    }

    public void setUnitAttribute(String unitAttribute) {
        this.unitAttribute = unitAttribute;
    }

    public Integer getFireConDev() {
        return fireConDev;
    }

    public void setFireConDev(Integer fireConDev) {
        this.fireConDev = fireConDev;
    }

    public String getJurisdictionOrganizationId() {
        return jurisdictionOrganizationId;
    }

    public void setJurisdictionOrganizationId(String jurisdictionOrganizationId) {
        this.jurisdictionOrganizationId = jurisdictionOrganizationId;
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

    public String getGeographicalPosition() {
        return geographicalPosition;
    }

    public void setGeographicalPosition(String geographicalPosition) {
        this.geographicalPosition = geographicalPosition;
    }

    public Integer getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(Integer buildNum) {
        this.buildNum = buildNum;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public Long getVersionStamp() {
        return versionStamp;
    }

    public void setVersionStamp(Long versionStamp) {
        this.versionStamp = versionStamp;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getUnitFireTypeCode() {
        return unitFireTypeCode;
    }

    public void setUnitFireTypeCode(String unitFireTypeCode) {
        this.unitFireTypeCode = unitFireTypeCode;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public String getFireEscapeDesc() {
        return fireEscapeDesc;
    }

    public void setFireEscapeDesc(String fireEscapeDesc) {
        this.fireEscapeDesc = fireEscapeDesc;
    }

    public String getInternalFireFacilitiesDesc() {
        return internalFireFacilitiesDesc;
    }

    public void setInternalFireFacilitiesDesc(String internalFireFacilitiesDesc) {
        this.internalFireFacilitiesDesc = internalFireFacilitiesDesc;
    }

    public String getFirePreventionFacilitiesDesc() {
        return firePreventionFacilitiesDesc;
    }

    public void setFirePreventionFacilitiesDesc(String firePreventionFacilitiesDesc) {
        this.firePreventionFacilitiesDesc = firePreventionFacilitiesDesc;
    }

    public String getFireControlRoomDesc() {
        return fireControlRoomDesc;
    }

    public void setFireControlRoomDesc(String fireControlRoomDesc) {
        this.fireControlRoomDesc = fireControlRoomDesc;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getSecurityPersonName() {
        return securityPersonName;
    }

    public void setSecurityPersonName(String securityPersonName) {
        this.securityPersonName = securityPersonName;
    }

    public String getSecurityManageName() {
        return securityManageName;
    }

    public void setSecurityManageName(String securityManageName) {
        this.securityManageName = securityManageName;
    }

    public String getFireManagerName() {
        return fireManagerName;
    }

    public void setFireManagerName(String fireManagerName) {
        this.fireManagerName = fireManagerName;
    }

    public String getUnitClassCode() {
        return unitClassCode;
    }

    public void setUnitClassCode(String unitClassCode) {
        this.unitClassCode = unitClassCode;
    }

    public String getOtherSystemUrl() {
        return otherSystemUrl;
    }

    public void setOtherSystemUrl(String otherSystemUrl) {
        this.otherSystemUrl = otherSystemUrl;
    }
}