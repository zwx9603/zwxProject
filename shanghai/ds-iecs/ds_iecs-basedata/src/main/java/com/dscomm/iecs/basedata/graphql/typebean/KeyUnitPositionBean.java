package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:灭火重点单位 重点部位信息
 */
public class KeyUnitPositionBean extends BaseBean {

    private String idCode; //   重点部位_通用唯一识别码

    private String keyUnitId ; //重点单位_通用唯一识别码

    private String organizationId ; //消防救援机构_通用唯一识别码

    private String organizationName;//消防救援机构名称

    private String positionName; // 重点部位名称

    private String positionAddress; // 地点

    private String position; // 部位所在位置

    private Integer  positionFloor; // 部位所在 楼层

    private Float positionHeight; // 部位所在高度

    private String buildingStructureTypeCode; // 建筑结构类型代码

    private String buildingStructureTypeName; // 建筑结构类型名称

    private String buildingStructureNatureCode ; // 建筑物使用性质代码

    private String buildingStructureNatureName ; // 建筑物使用性质名称

    private Float builtUpArea; //  建筑面积

    private Integer fireElevatorNum; //  消防电梯_数量

    private Integer evacuationNum ; // 疏散出口_数量

    private Integer exitNum  ; //  安全出口_数量

    private String  fireFacilitiesDesc; //灭火设施_简要情况

    private String  dangerDesc; //危险性_简要情况

    private String  establishingDesc ; //确立原因_简要情况

    private String  fireRrotectionSignsDesc; //防火标识设立_简要情况

    private String  dangerousSourcesDesc; //危险源_简要情况

    private String  fireDesc; //火种_简要情况

    private String  liablePerson ; //责任人

    private String  liablePersonName; //责任人_姓名

    private String  fireSafetyDesc ; //消防安全管理_简要情况

    private String  buildingsResistanceFireLevelCode  ; //建筑物耐火等级代码

    private String  buildingsResistanceFireLevelName  ; //建筑物耐火等级名称

    private String remarks; //备注

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionAddress() {
        return positionAddress;
    }

    public void setPositionAddress(String positionAddress) {
        this.positionAddress = positionAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getPositionFloor() {
        return positionFloor;
    }

    public void setPositionFloor(Integer positionFloor) {
        this.positionFloor = positionFloor;
    }

    public Float getPositionHeight() {
        return positionHeight;
    }

    public void setPositionHeight(Float positionHeight) {
        this.positionHeight = positionHeight;
    }

    public String getBuildingStructureTypeCode() {
        return buildingStructureTypeCode;
    }

    public void setBuildingStructureTypeCode(String buildingStructureTypeCode) {
        this.buildingStructureTypeCode = buildingStructureTypeCode;
    }

    public String getBuildingStructureTypeName() {
        return buildingStructureTypeName;
    }

    public void setBuildingStructureTypeName(String buildingStructureTypeName) {
        this.buildingStructureTypeName = buildingStructureTypeName;
    }

    public String getBuildingStructureNatureCode() {
        return buildingStructureNatureCode;
    }

    public void setBuildingStructureNatureCode(String buildingStructureNatureCode) {
        this.buildingStructureNatureCode = buildingStructureNatureCode;
    }

    public String getBuildingStructureNatureName() {
        return buildingStructureNatureName;
    }

    public void setBuildingStructureNatureName(String buildingStructureNatureName) {
        this.buildingStructureNatureName = buildingStructureNatureName;
    }

    public Float getBuiltUpArea() {
        return builtUpArea;
    }

    public void setBuiltUpArea(Float builtUpArea) {
        this.builtUpArea = builtUpArea;
    }

    public Integer getFireElevatorNum() {
        return fireElevatorNum;
    }

    public void setFireElevatorNum(Integer fireElevatorNum) {
        this.fireElevatorNum = fireElevatorNum;
    }

    public Integer getEvacuationNum() {
        return evacuationNum;
    }

    public void setEvacuationNum(Integer evacuationNum) {
        this.evacuationNum = evacuationNum;
    }

    public Integer getExitNum() {
        return exitNum;
    }

    public void setExitNum(Integer exitNum) {
        this.exitNum = exitNum;
    }

    public String getFireFacilitiesDesc() {
        return fireFacilitiesDesc;
    }

    public void setFireFacilitiesDesc(String fireFacilitiesDesc) {
        this.fireFacilitiesDesc = fireFacilitiesDesc;
    }

    public String getDangerDesc() {
        return dangerDesc;
    }

    public void setDangerDesc(String dangerDesc) {
        this.dangerDesc = dangerDesc;
    }

    public String getEstablishingDesc() {
        return establishingDesc;
    }

    public void setEstablishingDesc(String establishingDesc) {
        this.establishingDesc = establishingDesc;
    }

    public String getFireRrotectionSignsDesc() {
        return fireRrotectionSignsDesc;
    }

    public void setFireRrotectionSignsDesc(String fireRrotectionSignsDesc) {
        this.fireRrotectionSignsDesc = fireRrotectionSignsDesc;
    }

    public String getDangerousSourcesDesc() {
        return dangerousSourcesDesc;
    }

    public void setDangerousSourcesDesc(String dangerousSourcesDesc) {
        this.dangerousSourcesDesc = dangerousSourcesDesc;
    }

    public String getFireDesc() {
        return fireDesc;
    }

    public void setFireDesc(String fireDesc) {
        this.fireDesc = fireDesc;
    }

    public String getLiablePerson() {
        return liablePerson;
    }

    public void setLiablePerson(String liablePerson) {
        this.liablePerson = liablePerson;
    }

    public String getLiablePersonName() {
        return liablePersonName;
    }

    public void setLiablePersonName(String liablePersonName) {
        this.liablePersonName = liablePersonName;
    }

    public String getFireSafetyDesc() {
        return fireSafetyDesc;
    }

    public void setFireSafetyDesc(String fireSafetyDesc) {
        this.fireSafetyDesc = fireSafetyDesc;
    }

    public String getBuildingsResistanceFireLevelCode() {
        return buildingsResistanceFireLevelCode;
    }

    public void setBuildingsResistanceFireLevelCode(String buildingsResistanceFireLevelCode) {
        this.buildingsResistanceFireLevelCode = buildingsResistanceFireLevelCode;
    }

    public String getBuildingsResistanceFireLevelName() {
        return buildingsResistanceFireLevelName;
    }

    public void setBuildingsResistanceFireLevelName(String buildingsResistanceFireLevelName) {
        this.buildingsResistanceFireLevelName = buildingsResistanceFireLevelName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
