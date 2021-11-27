package com.dscomm.iecs.accept.service.bean.cad;

/**
 * 描述:119受理扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/26 16:13
 */
public class AcceptExtend119BO {
    /**
     * 受理单编号
     */
    private String id;
    /**
     * 建筑类型
     */
    private String buildingType;
    /**
     * 建筑类型name
     */
    private String buildingTypeName;
    /**
     * 起火部位
     */
    private String fireArea;
    /**
     * 起火部位name
     */
    private String fireAreaName;
    /**
     * 起火楼层
     */
    private String fireFloor;
    /**
     * 燃烧物质
     */
    private String burningSubstances;
    /**
     * 燃烧物质name
     */
    private String burningSubstancesName;
    /**
     * 燃烧面积
     */
    private Integer burningArea;
    /**
     * 是否有化学危险品
     */
    private Integer chemistryDangerous;
    /**
     * 是否有化学危险品name
     */
    private String chemistryDangerousName;
    /**
     * 被困人员
     */
    private Integer trappedPersonsNumber;
    /**
     * 水源信息
     */
    private String waterSourceInfo;
    /**
     * 周边信息
     */
    private String nearbyInfo;
    /**
     * 中队信息
     */
    private String squadronInfo;
    /**
     * 时间戳
     */
    private Long lastedhandleTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingTypeName() {
        return buildingTypeName;
    }

    public void setBuildingTypeName(String buildingTypeName) {
        this.buildingTypeName = buildingTypeName;
    }

    public String getFireArea() {
        return fireArea;
    }

    public void setFireArea(String fireArea) {
        this.fireArea = fireArea;
    }

    public String getFireAreaName() {
        return fireAreaName;
    }

    public void setFireAreaName(String fireAreaName) {
        this.fireAreaName = fireAreaName;
    }

    public String getFireFloor() {
        return fireFloor;
    }

    public void setFireFloor(String fireFloor) {
        this.fireFloor = fireFloor;
    }

    public String getBurningSubstances() {
        return burningSubstances;
    }

    public void setBurningSubstances(String burningSubstances) {
        this.burningSubstances = burningSubstances;
    }

    public String getBurningSubstancesName() {
        return burningSubstancesName;
    }

    public void setBurningSubstancesName(String burningSubstancesName) {
        this.burningSubstancesName = burningSubstancesName;
    }

    public Integer getBurningArea() {
        return burningArea;
    }

    public void setBurningArea(Integer burningArea) {
        this.burningArea = burningArea;
    }

    public Integer getChemistryDangerous() {
        return chemistryDangerous;
    }

    public void setChemistryDangerous(Integer chemistryDangerous) {
        this.chemistryDangerous = chemistryDangerous;
    }

    public String getChemistryDangerousName() {
        return chemistryDangerousName;
    }

    public void setChemistryDangerousName(String chemistryDangerousName) {
        this.chemistryDangerousName = chemistryDangerousName;
    }

    public Integer getTrappedPersonsNumber() {
        return trappedPersonsNumber;
    }

    public void setTrappedPersonsNumber(Integer trappedPersonsNumber) {
        this.trappedPersonsNumber = trappedPersonsNumber;
    }

    public String getWaterSourceInfo() {
        return waterSourceInfo;
    }

    public void setWaterSourceInfo(String waterSourceInfo) {
        this.waterSourceInfo = waterSourceInfo;
    }

    public String getNearbyInfo() {
        return nearbyInfo;
    }

    public void setNearbyInfo(String nearbyInfo) {
        this.nearbyInfo = nearbyInfo;
    }

    public String getSquadronInfo() {
        return squadronInfo;
    }

    public void setSquadronInfo(String squadronInfo) {
        this.squadronInfo = squadronInfo;
    }

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }
}
