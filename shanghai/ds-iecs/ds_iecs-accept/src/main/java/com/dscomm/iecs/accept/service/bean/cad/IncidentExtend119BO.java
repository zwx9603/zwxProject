package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述:119事件扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/26 16:13
 */
public class IncidentExtend119BO implements Serializable {
    /**
     * 事件编号
     */
    private String id;
    /**
     * 出动时间
     */
    private Long dispatchTime;
    /**
     * 到场时间
     */
    private Long arriveTime;
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
     * 当前火场状态
     */
    private Integer currentFireState;
    /**
     * 当前火场状态name
     */
    private String currentFireStateName;
    /**
     * 火灾发生时间
     */
    private Long fireOccurrenceTime;
    /**
     * 火灾扑灭时间
     */
    private Long fireExtinguishTime;
    /**
     * 火灾原因
     */
    private String fireReason;
    /**
     * 火灾原因name
     */
    private String fireReasonName;
    /**
     * 火灾损失
     */
    private Integer fireLoss;
    /**
     * 挽回经济损失
     */
    private Integer retrieveLoss;
    /**
     * 过火面积
     */
    private Integer verfireArea;
    /**
     * 中队受伤人数
     */
    private Integer squadronInjuresNumber;
    /**
     * 中队死亡人数
     */
    private Integer squadronDeathNumber;
    /**
     * 出动车次
     */
    private Integer dispatchTrainNumber;
    /**
     * 出动人次
     */
    private Integer dispatchedPersonNumber;
    /**
     * 轻伤人数
     */
    private Integer minorInjuriesNumber;
    /**
     * 重伤人数
     */
    private Integer seriousInjuryNumber;
    /**
     * 死亡人数
     */
    private Integer deathNumber;
    /**
     * 救助人数
     */
    private Integer rescuedPersonsNumber;
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

    public Long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
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

    public Integer getCurrentFireState() {
        return currentFireState;
    }

    public void setCurrentFireState(Integer currentFireState) {
        this.currentFireState = currentFireState;
    }

    public String getCurrentFireStateName() {
        return currentFireStateName;
    }

    public void setCurrentFireStateName(String currentFireStateName) {
        this.currentFireStateName = currentFireStateName;
    }

    public Long getFireOccurrenceTime() {
        return fireOccurrenceTime;
    }

    public void setFireOccurrenceTime(Long fireOccurrenceTime) {
        this.fireOccurrenceTime = fireOccurrenceTime;
    }

    public Long getFireExtinguishTime() {
        return fireExtinguishTime;
    }

    public void setFireExtinguishTime(Long fireExtinguishTime) {
        this.fireExtinguishTime = fireExtinguishTime;
    }

    public String getFireReason() {
        return fireReason;
    }

    public void setFireReason(String fireReason) {
        this.fireReason = fireReason;
    }

    public String getFireReasonName() {
        return fireReasonName;
    }

    public void setFireReasonName(String fireReasonName) {
        this.fireReasonName = fireReasonName;
    }

    public Integer getFireLoss() {
        return fireLoss;
    }

    public void setFireLoss(Integer fireLoss) {
        this.fireLoss = fireLoss;
    }

    public Integer getRetrieveLoss() {
        return retrieveLoss;
    }

    public void setRetrieveLoss(Integer retrieveLoss) {
        this.retrieveLoss = retrieveLoss;
    }

    public Integer getVerfireArea() { return verfireArea; }

    public void setVerfireArea(Integer verfireArea) { this.verfireArea = verfireArea; }

    public Integer getSquadronInjuresNumber() {
        return squadronInjuresNumber;
    }

    public void setSquadronInjuresNumber(Integer squadronInjuresNumber) {
        this.squadronInjuresNumber = squadronInjuresNumber;
    }

    public Integer getSquadronDeathNumber() {
        return squadronDeathNumber;
    }

    public void setSquadronDeathNumber(Integer squadronDeathNumber) {
        this.squadronDeathNumber = squadronDeathNumber;
    }

    public Integer getDispatchTrainNumber() {
        return dispatchTrainNumber;
    }

    public void setDispatchTrainNumber(Integer dispatchTrainNumber) {
        this.dispatchTrainNumber = dispatchTrainNumber;
    }

    public Integer getDispatchedPersonNumber() {
        return dispatchedPersonNumber;
    }

    public void setDispatchedPersonNumber(Integer dispatchedPersonNumber) {
        this.dispatchedPersonNumber = dispatchedPersonNumber;
    }

    public Integer getMinorInjuriesNumber() {
        return minorInjuriesNumber;
    }

    public void setMinorInjuriesNumber(Integer minorInjuriesNumber) {
        this.minorInjuriesNumber = minorInjuriesNumber;
    }

    public Integer getSeriousInjuryNumber() {
        return seriousInjuryNumber;
    }

    public void setSeriousInjuryNumber(Integer seriousInjuryNumber) {
        this.seriousInjuryNumber = seriousInjuryNumber;
    }

    public Integer getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(Integer deathNumber) {
        this.deathNumber = deathNumber;
    }

    public Integer getRescuedPersonsNumber() {
        return rescuedPersonsNumber;
    }

    public void setRescuedPersonsNumber(Integer rescuedPersonsNumber) {
        this.rescuedPersonsNumber = rescuedPersonsNumber;
    }

    public Long getLastedhandleTime() {
        return lastedhandleTime;
    }

    public void setLastedhandleTime(Long lastedhandleTime) {
        this.lastedhandleTime = lastedhandleTime;
    }
}
