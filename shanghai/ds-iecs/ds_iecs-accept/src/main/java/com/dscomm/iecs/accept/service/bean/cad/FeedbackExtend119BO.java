package com.dscomm.iecs.accept.service.bean.cad;

/**
 * 描述:119反馈扩展表对应BO
 *
 * @author ZhaiYanTao
 * Date Time 2019/7/26 16:13
 */
public class FeedbackExtend119BO {
    /**
     * 反馈单编号
     */
    private String id;
    /**
     * 出警人type（1：单位，2：unit，3：人员）
     */
    private String responsePoliceType;
    /**
     * 出警人（存的id）
     */
    private String responsePolice;
    /**
     * 出警人name（如果是unit就是unit name）
     */
    private String responsePersonName;
    /**
     * 出动时间
     */
    private Long startTime;
    /**
     * 到场时间
     */
    private Long arriveTime;
    /**
     * 事发地址分类
     */
    private String incidentAddressType;
    /**
     * 事发地址分类name
     */
    private String incidentAddressTypeName;
    /**
     * 一级事发地址分类
     */
    private String parentIncidentAddressType;
    /**
     * 一级地址分类name
     */
    private String parentIncidentAddressTypeName;
    /**
     * 火灾原因
     */
    private String fireReason;
    /**
     * 火灾原因name
     */
    private String fireReasonName;
    /**
     * 火灾发生时间
     */
    private Long fireOccurrenceTime;
    /**
     * 火灾扑灭时间
     */
    private Long fireExtinguishTime;
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
     * 过火面积
     */
    private Integer overFireArea;
    /**
     * 火灾损失
     */
    private Integer fireLoss;
    /**
     * 挽回经济损失
     */
    private Integer retrieveLoss;
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

    public String getResponsePoliceType() {
        return responsePoliceType;
    }

    public void setResponsePoliceType(String responsePoliceType) {
        this.responsePoliceType = responsePoliceType;
    }

    public String getResponsePolice() {
        return responsePolice;
    }

    public void setResponsePolice(String responsePolice) {
        this.responsePolice = responsePolice;
    }

    public String getResponsePersonName() {
        return responsePersonName;
    }

    public void setResponsePersonName(String responsePersonName) {
        this.responsePersonName = responsePersonName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getIncidentAddressType() {
        return incidentAddressType;
    }

    public void setIncidentAddressType(String incidentAddressType) {
        this.incidentAddressType = incidentAddressType;
    }

    public String getIncidentAddressTypeName() {
        return incidentAddressTypeName;
    }

    public void setIncidentAddressTypeName(String incidentAddressTypeName) {
        this.incidentAddressTypeName = incidentAddressTypeName;
    }

    public String getParentIncidentAddressType() {
        return parentIncidentAddressType;
    }

    public void setParentIncidentAddressType(String parentIncidentAddressType) {
        this.parentIncidentAddressType = parentIncidentAddressType;
    }

    public String getParentIncidentAddressTypeName() {
        return parentIncidentAddressTypeName;
    }

    public void setParentIncidentAddressTypeName(String parentIncidentAddressTypeName) {
        this.parentIncidentAddressTypeName = parentIncidentAddressTypeName;
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

    public Integer getOverFireArea() {
        return overFireArea;
    }

    public void setOverFireArea(Integer overFireArea) {
        this.overFireArea = overFireArea;
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
