package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;


/**
 * 描述:受理单（报警记录）
 *
 */
public class AcceptanceBean extends BaseBean {

    private String idCode; //todo  字段 报警_通用唯一识别码

    private String telephoneId ; //todo  字段 电话报警id

    private Long  acceptanceTime ; //todo  字段  受理单时间

    /**  start 受理电话信息 */

    private String alarmPhone;//报警电话

    private String alarmPersonName;//报警人姓名

    private String alarmPersonSex;//报警人性别

    private String alarmPersonSexName;//报警人性别

    private String alarmPersonPhone;//报警人联系电话

    private String alarmAddress;//报警地址

    private Long alarmStartTime;  //报警开始时间（振铃时间）

    private Long receiveStartTime;  //接警开始时间（接听时间）

    private Long alarmEndTime;  //报警结束时间（挂机时间）

    private Long receiveEndTime;  //接警结束时间（立案时间）

    private String relayRecordNumber;//录音号

    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    /**  end受理电话信息 */

    private String incidentId;  //案件ID

    private String incidentSource;  //案件来源

    private String originalIncidentNumber ;  //原始案件编号

    private String alarmModeCode;  //报警方式代码

    private String alarmModeName;  //报警方式名称

    private String organizationId;// 主管机构ID

    private String organizationName;// 主管机构名称

    private String brigadeOrganizationId; // 所属大队

    private String brigadeOrganizationName; // 所属大队名称

    private String districtCode;//  行政区编码

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

    private String districtName;//  行政区名称

    private String registerModeCode;// 立案方式代码

    private String registerModeName;// 立案方式名称

    private  Long registerIncidentTime;// 立案时间

    private String title; //   名称

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeName;// 案件类型名称

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentTypeSubitemName;// 案件类型子项名称（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级代码

    private String incidentLevelName;// 案件等级名称

    private String disposalObjectCode;// 处置对象代码

    private String disposalObjectName;// 处置对象名称

    private String incidentStatusCode;// 案件状态代码

    private String incidentStatusName;// 案件状态名称

    private Integer whetherImportSign;// 是否重大案件标识

    private String keyUnit;// 重点单位

    private String keyUnitId;// 重点单位ID

    private String keyUnitName;// 重点单位名称

    private String keyUnitTypeCode ;// 重点单位 警情对象类别代码

    private String keyUnitTypeName ;// 重点单位  警情对象类别名称

    private String supplementInfo;// 补充信息

    private String attentions;// 注意事项

    private String incidentDescribe;//案件描述

    private String registerOrganizationId; // 立案机构ID

    private String registerOrganizationName; // 立案机构名称

    private String acceptancePerson ;//t  接警员

    private String acceptancePersonId ;//  接警员id

    private String registerSeatNumber; // 立案接警坐席号

    private String acceptancePersonName;// 接警员姓名

    private String acceptancePersonNumber;// 接警员工号

    private String handleType; // 处理类型

    private Long receiveDuration;  //接警时长

    private String buildingStructureCode;// 建筑结构代码

    private String buildingStructureName;// 建筑结构代码

    private String smogSituationCode;// 烟雾情况代码

    private String smogSituationName;// 烟雾情况代码

    private String trappedCount;//被困人数

    private Integer storeysOfBuildings;// 楼房层数

    private String burningFloor;// 燃烧楼层  救援楼层

    private String burningArea;// 燃烧面积

    private String disasterSites;//灾害场所

    private String disasterSitesName;//灾害场所名称

    private String securityContactPerson;//安保联系人

    private String contactPersonPhone;//安保联系人电话

    private Integer whetherSensitiveArea;//是否敏感地区

    private Integer whetherWaterShortageArea;//是否缺水地区

    private Integer whetherSensitiveTime;//是否敏感时间

    private String remarks;//备注


    private String injuredCount;//受伤人数

    private String deathCount;//死亡人数

    private Long securityStartTime;//安保开始时间

    private Long securityEndTime;//安保结束时间

    private Integer whetherTestSign;//是否测试警情 0非测试警情  1测试警情

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getDisasterSitesName() {
        return disasterSitesName;
    }

    public void setDisasterSitesName(String disasterSitesName) {
        this.disasterSitesName = disasterSitesName;
    }

    public String getInjuredCount() {
        return injuredCount;
    }

    public void setInjuredCount(String injuredCount) {
        this.injuredCount = injuredCount;
    }

    public String getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(String deathCount) {
        this.deathCount = deathCount;
    }

    public Long getSecurityStartTime() {
        return securityStartTime;
    }

    public void setSecurityStartTime(Long securityStartTime) {
        this.securityStartTime = securityStartTime;
    }

    public Long getSecurityEndTime() {
        return securityEndTime;
    }

    public void setSecurityEndTime(Long securityEndTime) {
        this.securityEndTime = securityEndTime;
    }

    public Integer getWhetherTestSign() {
        return whetherTestSign;
    }

    public void setWhetherTestSign(Integer whetherTestSign) {
        this.whetherTestSign = whetherTestSign;
    }

    public String getAlarmPhone() {
        return alarmPhone;
    }

    public void setAlarmPhone(String alarmPhone) {
        this.alarmPhone = alarmPhone;
    }

    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
    }

    public String getAlarmPersonSex() {
        return alarmPersonSex;
    }

    public void setAlarmPersonSex(String alarmPersonSex) {
        this.alarmPersonSex = alarmPersonSex;
    }

    public String getAlarmPersonPhone() {
        return alarmPersonPhone;
    }

    public void setAlarmPersonPhone(String alarmPersonPhone) {
        this.alarmPersonPhone = alarmPersonPhone;
    }

    public String getAlarmAddress() {
        return alarmAddress;
    }

    public void setAlarmAddress(String alarmAddress) {
        this.alarmAddress = alarmAddress;
    }

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getAlarmModeCode() {
        return alarmModeCode;
    }

    public void setAlarmModeCode(String alarmModeCode) {
        this.alarmModeCode = alarmModeCode;
    }

    public String getAlarmModeName() {
        return alarmModeName;
    }

    public void setAlarmModeName(String alarmModeName) {
        this.alarmModeName = alarmModeName;
    }

    public Long getAlarmStartTime() {
        return alarmStartTime;
    }

    public void setAlarmStartTime(Long alarmStartTime) {
        this.alarmStartTime = alarmStartTime;
    }

    public Long getReceiveStartTime() {
        return receiveStartTime;
    }

    public void setReceiveStartTime(Long receiveStartTime) {
        this.receiveStartTime = receiveStartTime;
    }

    public Long getAlarmEndTime() {
        return alarmEndTime;
    }

    public void setAlarmEndTime(Long alarmEndTime) {
        this.alarmEndTime = alarmEndTime;
    }

    public Long getReceiveEndTime() {
        return receiveEndTime;
    }

    public void setReceiveEndTime(Long receiveEndTime) {
        this.receiveEndTime = receiveEndTime;
    }

//    public String getSquadronOrganizationId() {
//        return squadronOrganizationId;
//    }
//
//    public void setSquadronOrganizationId(String squadronOrganizationId) {
//        this.squadronOrganizationId = squadronOrganizationId;
//    }
//
//    public String getSquadronOrganizationName() {
//        return squadronOrganizationName;
//    }
//
//    public void setSquadronOrganizationName(String squadronOrganizationName) {
//        this.squadronOrganizationName = squadronOrganizationName;
//    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getBrigadeOrganizationName() {
        return brigadeOrganizationName;
    }

    public void setBrigadeOrganizationName(String brigadeOrganizationName) {
        this.brigadeOrganizationName = brigadeOrganizationName;
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

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
    }

    public String getRegisterModeName() {
        return registerModeName;
    }

    public void setRegisterModeName(String registerModeName) {
        this.registerModeName = registerModeName;
    }

    public Long getRegisterIncidentTime() {
        return registerIncidentTime;
    }

    public void setRegisterIncidentTime(Long registerIncidentTime) {
        this.registerIncidentTime = registerIncidentTime;
    }

    public String getCrimeAddress() {
        return crimeAddress;
    }

    public void setCrimeAddress(String crimeAddress) {
        this.crimeAddress = crimeAddress;
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

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getIncidentLevelName() {
        return incidentLevelName;
    }

    public void setIncidentLevelName(String incidentLevelName) {
        this.incidentLevelName = incidentLevelName;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getDisposalObjectName() {
        return disposalObjectName;
    }

    public void setDisposalObjectName(String disposalObjectName) {
        this.disposalObjectName = disposalObjectName;
    }

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getIncidentStatusName() {
        return incidentStatusName;
    }

    public void setIncidentStatusName(String incidentStatusName) {
        this.incidentStatusName = incidentStatusName;
    }

    public Integer getWhetherImportSign() {
        return whetherImportSign;
    }

    public void setWhetherImportSign(Integer whetherImportSign) {
        this.whetherImportSign = whetherImportSign;
    }

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getKeyUnitName() {
        return keyUnitName;
    }

    public void setKeyUnitName(String keyUnitName) {
        this.keyUnitName = keyUnitName;
    }

    public String getSupplementInfo() {
        return supplementInfo;
    }

    public void setSupplementInfo(String supplementInfo) {
        this.supplementInfo = supplementInfo;
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions;
    }

    public String getIncidentDescribe() {
        return incidentDescribe;
    }

    public void setIncidentDescribe(String incidentDescribe) {
        this.incidentDescribe = incidentDescribe;
    }

    public String getRegisterOrganizationId() {
        return registerOrganizationId;
    }

    public void setRegisterOrganizationId(String registerOrganizationId) {
        this.registerOrganizationId = registerOrganizationId;
    }

    public String getRegisterOrganizationName() {
        return registerOrganizationName;
    }

    public void setRegisterOrganizationName(String registerOrganizationName) {
        this.registerOrganizationName = registerOrganizationName;
    }

    public String getRegisterSeatNumber() {
        return registerSeatNumber;
    }

    public void setRegisterSeatNumber(String registerSeatNumber) {
        this.registerSeatNumber = registerSeatNumber;
    }

    public String getAcceptancePersonName() {
        return acceptancePersonName;
    }

    public void setAcceptancePersonName(String acceptancePersonName) {
        this.acceptancePersonName = acceptancePersonName;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public Long getReceiveDuration() {
        return receiveDuration;
    }

    public void setReceiveDuration(Long receiveDuration) {
        this.receiveDuration = receiveDuration;
    }

    public String getBuildingStructureCode() {
        return buildingStructureCode;
    }

    public void setBuildingStructureCode(String buildingStructureCode) {
        this.buildingStructureCode = buildingStructureCode;
    }

    public String getSmogSituationCode() {
        return smogSituationCode;
    }

    public void setSmogSituationCode(String smogSituationCode) {
        this.smogSituationCode = smogSituationCode;
    }

    public String getTrappedCount() {
        return trappedCount;
    }

    public void setTrappedCount(String trappedCount) {
        this.trappedCount = trappedCount;
    }

    public Integer getStoreysOfBuildings() {
        return storeysOfBuildings;
    }

    public void setStoreysOfBuildings(Integer storeysOfBuildings) {
        this.storeysOfBuildings = storeysOfBuildings;
    }

    public String getBurningFloor() {
        return burningFloor;
    }

    public void setBurningFloor(String burningFloor) {
        this.burningFloor = burningFloor;
    }

    public String getBurningArea() {
        return burningArea;
    }

    public void setBurningArea(String burningArea) {
        this.burningArea = burningArea;
    }

    public String getDisasterSites() {
        return disasterSites;
    }

    public void setDisasterSites(String disasterSites) {
        this.disasterSites = disasterSites;
    }

    public String getSecurityContactPerson() {
        return securityContactPerson;
    }

    public void setSecurityContactPerson(String securityContactPerson) {
        this.securityContactPerson = securityContactPerson;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public Integer getWhetherSensitiveArea() {
        return whetherSensitiveArea;
    }

    public void setWhetherSensitiveArea(Integer whetherSensitiveArea) {
        this.whetherSensitiveArea = whetherSensitiveArea;
    }

    public Integer getWhetherWaterShortageArea() {
        return whetherWaterShortageArea;
    }

    public void setWhetherWaterShortageArea(Integer whetherWaterShortageArea) {
        this.whetherWaterShortageArea = whetherWaterShortageArea;
    }

    public Integer getWhetherSensitiveTime() {
        return whetherSensitiveTime;
    }

    public void setWhetherSensitiveTime(Integer whetherSensitiveTime) {
        this.whetherSensitiveTime = whetherSensitiveTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getIncidentTypeSubitemName() {
        return incidentTypeSubitemName;
    }

    public void setIncidentTypeSubitemName(String incidentTypeSubitemName) {
        this.incidentTypeSubitemName = incidentTypeSubitemName;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getBuildingStructureName() {
        return buildingStructureName;
    }

    public void setBuildingStructureName(String buildingStructureName) {
        this.buildingStructureName = buildingStructureName;
    }

    public String getSmogSituationName() {
        return smogSituationName;
    }

    public void setSmogSituationName(String smogSituationName) {
        this.smogSituationName = smogSituationName;
    }

    public String getAlarmPersonSexName() {
        return alarmPersonSexName;
    }

    public void setAlarmPersonSexName(String alarmPersonSexName) {
        this.alarmPersonSexName = alarmPersonSexName;
    }

    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
    }

    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

    public Long getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(Long acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public String getAcceptancePerson() {
        return acceptancePerson;
    }

    public void setAcceptancePerson(String acceptancePerson) {
        this.acceptancePerson = acceptancePerson;
    }

    public String getAcceptancePersonId() {
        return acceptancePersonId;
    }

    public void setAcceptancePersonId(String acceptancePersonId) {
        this.acceptancePersonId = acceptancePersonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyUnit() {
        return keyUnit;
    }

    public void setKeyUnit(String keyUnit) {
        this.keyUnit = keyUnit;
    }

    public String getKeyUnitTypeCode() {
        return keyUnitTypeCode;
    }

    public void setKeyUnitTypeCode(String keyUnitTypeCode) {
        this.keyUnitTypeCode = keyUnitTypeCode;
    }

    public String getKeyUnitTypeName() {
        return keyUnitTypeName;
    }

    public void setKeyUnitTypeName(String keyUnitTypeName) {
        this.keyUnitTypeName = keyUnitTypeName;
    }
}
