package com.dscomm.iecs.integrate.restful.vo;

import java.util.List;

public class OutsideVO {

    private String incidentId;  //案件ID

    private String incidentSource;  //案件来源
    private String incidentSourceName;  //案件来源名称

    private String organizationId;// 主管机构编号
    private String organizationName;// 主管机构名称

    private String districtCode;//  行政区域编号
    private String districtName;//  行政区域名称

    private  Long registerIncidentTime;// 立案时间

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

    private String incidentLevelCode;// 案件等级代码
    private String incidentLevelName;// 案件等级名称

    private String disposalObjectCode;// 处置对象代码
    private String disposalObjectName;// 处置对象名称

    private String keyUnitId;// 重点单位
    private String keyUnitName;// 重点单位名称

    private String trappedCount;//被困人数

    private String incidentDescribe;//案件描述

    private Integer storeysOfBuildings;// 楼房层数

    private String burningFloor;// 燃烧楼层

    private String burningArea;// 燃烧面积

    private String disasterSites;//灾害场所
    private String disasterSitesName;//灾害场所名称

    private String remarks;//备注

    private List<String> type ; //类型  1、110  2、122  3、应急

    private String explain ; //说明

    public String getIncidentSourceName() {
        return incidentSourceName;
    }

    public void setIncidentSourceName(String incidentSourceName) {
        this.incidentSourceName = incidentSourceName;
    }

    public Long getRegisterIncidentTime() {
        return registerIncidentTime;
    }

    public void setRegisterIncidentTime(Long registerIncidentTime) {
        this.registerIncidentTime = registerIncidentTime;
    }

    public String getDisasterSitesName() {
        return disasterSitesName;
    }

    public void setDisasterSitesName(String disasterSitesName) {
        this.disasterSitesName = disasterSitesName;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
