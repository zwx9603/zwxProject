package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 描述:案件基本信息
 *
 */
public class IncidentSaveInputInfo {

    private  String id  ; //警情id

    private String incidentSource;  //案件来源

    private String acceptanceId ; //受理单id

    private String repeatPrimaryIncidentId;  //合并案件id（事前合并、重复报警时，传已存在案件的id）

    private String telephoneId ;   //电话报警记录

    private String unTrafficAlarmId ; // 非话务报警信息id

    private String alarmPhone;//报警电话

    private String alarmPersonName;//报警人姓名

    private String alarmPersonSex;//报警人性别

    private String alarmPersonPhone;//报警人联系电话

    private String alarmAddress;//报警地址

    private String relayRecordNumber;//录音号

    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    private String alarmModeCode;  //报警方式代码

    private Long alarmStartTime;  //报警开始时间（振铃时间）

    private Long receiveStartTime;  //接警开始时间（接听时间）

    private Long alarmEndTime;  //报警结束时间（挂机时间） 服务器填充

    private Long receiveEndTime;  //接警结束时间（立案时间） 服务器填充

    private String incidentNumber; // 案件编号（日期坐席工号） //后端 生成

    private String registerModeCode;// 立案方式代码

    private String squadronOrganizationId;// 主管中队

    private String brigadeOrganizationId; // 所属大队

    private String districtCode;//  行政区编码

    private  Long registerIncidentTime;// 立案时间

    private String title; //  名称

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级代码

    private String disposalObjectCode;// 处置对象代码

    private String incidentNatureCode;// 案件性质代码

    private String incidentStatusCode;// 案件状态代码

    private Integer whetherImportSign;// 是否重大案件标识

    private String keyUnitId;// 重点单位

    private String supplementInfo;// 补充信息

    private String attentions;// 注意事项

    private String incidentDescribe;//案件描述

    private String registerOrganizationId; // 立案机构ID 服务器填充

    private String acceptancePerson ;//  接警员 服务器填充

    private String acceptancePersonId ;//  接警员id 服务器填充

    private String registerSeatNumber; // 立案接警坐席号 服务器填充

    private String acceptancePersonNumber;// 接警员工号 服务器填充

    private String acceptancePersonName;// 接警员姓名 服务器填充

    private String handleType; // 处理类型

    private String incidentLabel; // 警情标签

    private String buildingStructureCode;// 建筑结构代码

    private String smogSituationCode;// 烟雾情况代码

    private String trappedCount;//被困人数

    private Integer storeysOfBuildings;// 楼房层数

    private String burningFloor;// 燃烧楼层  救援楼层

    private String burningArea;// 燃烧面积

    private String disasterSites;//灾害场所

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

    private Integer whetherTestSign = 0 ;//是否测试警情 0非测试警情  1测试警情

    private String uniformAddressId;//统一地址库id

    public String getUniformAddressId() {
        return uniformAddressId;
    }

    public void setUniformAddressId(String uniformAddressId) {
        this.uniformAddressId = uniformAddressId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
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

    public String getRepeatPrimaryIncidentId() {
        return repeatPrimaryIncidentId;
    }

    public void setRepeatPrimaryIncidentId(String repeatPrimaryIncidentId) {
        this.repeatPrimaryIncidentId = repeatPrimaryIncidentId;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
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

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

    public String getUnTrafficAlarmId() {
        return unTrafficAlarmId;
    }

    public void setUnTrafficAlarmId(String unTrafficAlarmId) {
        this.unTrafficAlarmId = unTrafficAlarmId;
    }

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
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




    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getSquadronOrganizationId() {
        return squadronOrganizationId;
    }

    public void setSquadronOrganizationId(String squadronOrganizationId) {
        this.squadronOrganizationId = squadronOrganizationId;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAlarmModeCode() {
        return alarmModeCode;
    }

    public void setAlarmModeCode(String alarmModeCode) {
        this.alarmModeCode = alarmModeCode;
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

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getIncidentNatureCode() {
        return incidentNatureCode;
    }

    public void setIncidentNatureCode(String incidentNatureCode) {
        this.incidentNatureCode = incidentNatureCode;
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

    public String getRegisterSeatNumber() {
        return registerSeatNumber;
    }

    public void setRegisterSeatNumber(String registerSeatNumber) {
        this.registerSeatNumber = registerSeatNumber;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getAcceptancePersonName() {
        return acceptancePersonName;
    }

    public void setAcceptancePersonName(String acceptancePersonName) {
        this.acceptancePersonName = acceptancePersonName;
    }

    public String getIncidentLabel() {
        return incidentLabel;
    }

    public void setIncidentLabel(String incidentLabel) {
        this.incidentLabel = incidentLabel;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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



    public Integer getStoreysOfBuildings() {
        return storeysOfBuildings;
    }

    public void setStoreysOfBuildings(Integer storeysOfBuildings) {
        this.storeysOfBuildings = storeysOfBuildings;
    }

    public String getTrappedCount() {
        return trappedCount;
    }

    public void setTrappedCount(String trappedCount) {
        this.trappedCount = trappedCount;
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
}
