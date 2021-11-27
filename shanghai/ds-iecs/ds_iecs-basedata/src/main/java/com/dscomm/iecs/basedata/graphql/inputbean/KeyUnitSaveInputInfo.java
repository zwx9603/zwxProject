package com.dscomm.iecs.basedata.graphql.inputbean;


public class KeyUnitSaveInputInfo {

    private String KeyUnitId ; //重点单位id

    private String fireproofUnit;  //防火单位ID

    private String unitName;  //单位名称

    private String economicOwnership;  //经济所有制代码

    private Long unitFoundingTime;  //单位成立时间

    private String unitFireTypeCode ; //  单位火灾危害性分类与代码

    private String unitDesc; //todo 字段 单位_简要情况

    private String fireEscapeDesc; //todo 字段 消防通道或疏散通道_简要情况

    private String internalFireFacilitiesDesc; //todo 字段 内部消防设施_简要情况

    private String firePreventionFacilitiesDesc; //todo 字段 防火设施_简要情况

    private String fireControlRoomDesc ; //todo 字段 消防控制室_简要情况

    private String unitClassCode; //单位分类 01防火单位 02灭火单位

    private String unitLevelCode;  //单位等级

    private String unitCategoryCode;// 单位类别

    private String unitTypeCode; //单位类型

    private String unitNatureCode; //单位性质

    private String districtCode;  //行政区域

    private String legalPerson;  //法人代表

    private String legalPersonName; //  法人代表姓名

    private String legalPersonId;  //法人代表身份证

    private String legalPersonPhone;  //法人电话

    private String securityPerson;  //安全责任人

    private String securityPersonName; //  消防安全责任人 姓名

    private String securityPersonId;  //责任人身份证

    private String securityPersonPhone;  //责任人电话

    private String unitEmail;  //单位电子邮箱

    private String securityManage;  //安全管理人

    private String securityManageName; // 消防安全管理人姓名

    private String securityManageId;  //管理人身份证

    private String securityManagePhone;  //管理人电话

    private String fireManager;  //专兼职消防管理人

    private String fireManagerName; // 专兼职消防管理人姓名

    private String fireManagerId;  //专兼职消防管理人身份证

    private String fireManagerPhone;  //专兼职消防管理人电话

    private String unitAddress;  //单位地址

    private String unitPhone;  //单位电话

    private String mailCode;  //单位邮政编码

    private Float fixedAssets;  //固定资产

    private Integer unitWorkers;  //职工人数

    private Float constructionArea;  //占地面积

    private Float builtUpArea;  //建筑面积

    private String unitPrincipalAttribute;  //单位主属性

    private String unitAttribute;  //单位次属性

    private Integer fireConDev;  //自动消防设施情况

    private String jurisdictionOrganizationId;  //消防管辖机构ID

    private String longitude;  //经度

    private String latitude;  //纬度

    private String geographicalPosition;  //地理位置

    private Integer buildNum;  //建筑数量

    private String  otherSystemUrl ;//第三方重点单位信息 访问路径

    private String organizationId; //机构ID

    private String operatorId; //操作员ID

    private String remarks;  //备注

    private String relationId ; //关联id

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getOtherSystemUrl() {
        return otherSystemUrl;
    }

    public void setOtherSystemUrl(String otherSystemUrl) {
        this.otherSystemUrl = otherSystemUrl;
    }

    public String getUnitClassCode() {
        return unitClassCode;
    }

    public void setUnitClassCode(String unitClassCode) {
        this.unitClassCode = unitClassCode;
    }

    public String getKeyUnitId() {
        return KeyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        KeyUnitId = keyUnitId;
    }

    public String getFireproofUnit() {
        return fireproofUnit;
    }

    public void setFireproofUnit(String fireproofUnit) {
        this.fireproofUnit = fireproofUnit;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getUnitFireTypeCode() {
        return unitFireTypeCode;
    }

    public void setUnitFireTypeCode(String unitFireTypeCode) {
        this.unitFireTypeCode = unitFireTypeCode;
    }

    public String getUnitLevelCode() {
        return unitLevelCode;
    }

    public void setUnitLevelCode(String unitLevelCode) {
        this.unitLevelCode = unitLevelCode;
    }

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

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
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

    public String getSecurityPersonName() {
        return securityPersonName;
    }

    public void setSecurityPersonName(String securityPersonName) {
        this.securityPersonName = securityPersonName;
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

    public String getSecurityManageName() {
        return securityManageName;
    }

    public void setSecurityManageName(String securityManageName) {
        this.securityManageName = securityManageName;
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

    public String getFireManagerName() {
        return fireManagerName;
    }

    public void setFireManagerName(String fireManagerName) {
        this.fireManagerName = fireManagerName;
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
}
