package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述: 非话务报警
 *
 */
public class UnTrafficAlarmBean extends BaseBean {

    private String unTrafficAlarmId ; //非话务id

    private String incidentId ; //警情id

    /**  start 受理电话信息 */

    private String alarmPhone;//报警电话

    private String alarmPersonName;//报警人姓名

    private String alarmPersonSex;//报警人性别

    private String alarmPersonPhone;//报警人联系电话

    private String alarmAddress;//报警地址

    private Long alarmStartTime;  //报警开始时间（振铃时间）

    private Long receiveStartTime;  //接警开始时间（接听时间）

    private Long alarmEndTime;  //报警结束时间（挂机时间）

    private Long receiveEndTime;  //接警结束时间（立案时间）

    private String relayRecordNumber;//录音号

    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    /**  end受理电话信息 */


    private String alarmModeCode;  //报警方式代码

    private String alarmModeName;  //报警方式代码名称

    private String registerModeCode;// 立案方式代码

    private String registerModeName;// 立案方式代码名称

    private String incidentSource;  //案件来源

    private String organizationId;// 主管机构编号

    private String organizationName;// 主管机构名称

    private String districtCode;//  行政区域编号

    private String districtName;//  行政区域名称

    private Long alarmTime;  //报警时间

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String smogSituationCode;// 烟雾情况代码

    private String smogSituationName;// 烟雾情况名称

    private String buildingStructureCode;// 建筑结构代码

    private String buildingStructureName;// 建筑结构名称

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeName;// 案件类型名称

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentTypeSubitemName;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级代码

    private String incidentLevelName;// 案件等级名称

    private String disposalObjectCode;// 处置对象代码

    private String disposalObjectName;// 处置对象名称

    private String keyUnitId;// 重点单位

    private String keyUnitName;// 重点单位名称

    private String trappedCount;//被困人数

    private String injuredCount;//受伤人数

    private String deathCount;//死亡人数

    private String incidentDescribe;//案件描述

    private Integer storeysOfBuildings;// 楼房层数

    private String burningFloor;// 燃烧楼层

    private String burningArea;// 燃烧面积

    private String disasterSites;//灾害场所

    private String disasterSitesName;//灾害场所名称

    private String remarks;//备注

    private String originalIncidentNumber ;  // 原始案件编号 （其他系统警情id）

    public String getUnTrafficAlarmId() {
        return unTrafficAlarmId;
    }

    public void setUnTrafficAlarmId(String unTrafficAlarmId) {
        this.unTrafficAlarmId = unTrafficAlarmId;
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

    public String getDisasterSitesName() {
        return disasterSitesName;
    }

    public void setDisasterSitesName(String disasterSitesName) {
        this.disasterSitesName = disasterSitesName;
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

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
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

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSmogSituationName() {
        return smogSituationName;
    }

    public void setSmogSituationName(String smogSituationName) {
        this.smogSituationName = smogSituationName;
    }

    public String getBuildingStructureName() {
        return buildingStructureName;
    }

    public void setBuildingStructureName(String buildingStructureName) {
        this.buildingStructureName = buildingStructureName;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getIncidentLevelName() {
        return incidentLevelName;
    }

    public void setIncidentLevelName(String incidentLevelName) {
        this.incidentLevelName = incidentLevelName;
    }

    public String getDisposalObjectName() {
        return disposalObjectName;
    }

    public void setDisposalObjectName(String disposalObjectName) {
        this.disposalObjectName = disposalObjectName;
    }

    public String getKeyUnitName() {
        return keyUnitName;
    }

    public void setKeyUnitName(String keyUnitName) {
        this.keyUnitName = keyUnitName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
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

    public Long getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
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

    public String getSmogSituationCode() {
        return smogSituationCode;
    }

    public void setSmogSituationCode(String smogSituationCode) {
        this.smogSituationCode = smogSituationCode;
    }

    public String getBuildingStructureCode() {
        return buildingStructureCode;
    }

    public void setBuildingStructureCode(String buildingStructureCode) {
        this.buildingStructureCode = buildingStructureCode;
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

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
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

    public String getIncidentDescribe() {
        return incidentDescribe;
    }

    public void setIncidentDescribe(String incidentDescribe) {
        this.incidentDescribe = incidentDescribe;
    }

    public Integer getStoreysOfBuildings() {
        return storeysOfBuildings;
    }

    public void setStoreysOfBuildings(Integer storeysOfBuildings) {
        this.storeysOfBuildings = storeysOfBuildings;
    }


    public String getDisasterSites() {
        return disasterSites;
    }

    public void setDisasterSites(String disasterSites) {
        this.disasterSites = disasterSites;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
