package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：事故情况
 *
 */
public class AccidentBean extends BaseBean {

    private String idCode; //  字段 警情归档记录_通用唯一识别码

    private String incidentId;//案件id

    private String actualCrimeAddress;//实际案发地址

    private String accidentUnitName;//事故单位名称

    private String accidentUnitNature;//事故单位性质

    private String accidentUnitNatureName;//事故单位性质

    private String responsiblePersonName;//负责人姓名

    private String responsiblePersonAge;//负责人年龄

    private String responsiblePersonCareer ;//负责人职业信息

    private String responsiblePersonNativePlace ;//负责人籍贯

    private String accidentReason;//事故原因

    private String burningMaterial;//燃烧物质

    private String burningArea;//燃烧面积

    private String protectArea;//保护面积

    private String economicLoss;//经济损失

    private String protectEconomic;//保护价值

    private String accidentDescribe;//主要情况

    private String rescueMeasures; //营救措施

    private String smogSituation; //烟雾情况

    private String smogSituationName; //烟雾情况

    private String spreadSituation;//蔓延情况

    private String waterSituation;//水源情况

    private String peripherySituation;//周边情况

    private String buildingUpLayerNumber;//建筑层数(地上)

    private String buildingDownLayerNumber;//建筑层数(地下)

    private String buildingStructure;//建筑结构

    private String buildingStructureName;//建筑结构

    private String buildingArea;//建筑面积

    private String burningFloor;//燃烧层

    private String leakageMaterial;//泄漏物质

    private String remarks;//备注

    private String incidentLevelCode ; //  警情等级代码

    private String incidentLevelName ; //  警情等级名称

    private Long accidentTime ; //  日期时间

    private Integer dispatchTrainNum; // 出动车次

    private Integer dispatchVehicleNum; // 出动车辆数

    private Integer dispatchWaterGunNum;//出动水枪数

    private Integer dispatchPersonNum; // 出动人数

    private String attendanceNum;//  到场人数

    private String trappedPersonNum;//  被困人数

    private String rescueNum;// 抢救人数

    private String massRescueNum;//  群众抢救人数

    private String troopsRescueNum;//  部队抢救人数

    private String injuredNum;//  受伤人数

    private String massInjuredNum;//  群众受伤人数

    private String troopsInjuredNum;//  部队受伤人数

    private String deathNum;//  死亡人数

    private String massDeathNum;//  群众死亡人数

    private String troopsDeathNum;//  部队死亡人数

    private String outContactNum  ; //  失联人员数

    private String massOutContactNum  ; //   群众失联人员

    private String troopsOutContactNum  ; //   部队失联人员

    private String seatNumber; //    坐席号

    private String personId;//  警员

    private String personNumber;//  警员工号

    private String personName;//   警员姓名

    /**
     * start 作战情况相关字段
     */
    private String combatSituation;//战斗情况

    private String localeCommander ;//现场总指挥

    private String localeDeputyCommander;//现场副指挥

    private String dispatcher;//119调度员

    private String localeCorrespondent;//现场通讯员

    private String detachmentCorrespondent;//支队通讯员

    private String squadronCommander;//中队指挥员

    private String squadronCorrespondent;//中队通讯员

    private String videographer;//录像人员

    private String combatProcessDesc ; // 战斗过程描述

    /**
     * end 作战情况相关字段
     *
     */

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getSmogSituationName() {
        return smogSituationName;
    }

    public void setSmogSituationName(String smogSituationName) {
        this.smogSituationName = smogSituationName;
    }

    public String getAccidentUnitNatureName() {
        return accidentUnitNatureName;
    }

    public void setAccidentUnitNatureName(String accidentUnitNatureName) {
        this.accidentUnitNatureName = accidentUnitNatureName;
    }

    public String getBuildingStructureName() {
        return buildingStructureName;
    }

    public void setBuildingStructureName(String buildingStructureName) {
        this.buildingStructureName = buildingStructureName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getActualCrimeAddress() {
        return actualCrimeAddress;
    }

    public void setActualCrimeAddress(String actualCrimeAddress) {
        this.actualCrimeAddress = actualCrimeAddress;
    }

    public String getAccidentUnitName() {
        return accidentUnitName;
    }

    public void setAccidentUnitName(String accidentUnitName) {
        this.accidentUnitName = accidentUnitName;
    }

    public String getAccidentUnitNature() {
        return accidentUnitNature;
    }

    public void setAccidentUnitNature(String accidentUnitNature) {
        this.accidentUnitNature = accidentUnitNature;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getResponsiblePersonAge() {
        return responsiblePersonAge;
    }

    public void setResponsiblePersonAge(String responsiblePersonAge) {
        this.responsiblePersonAge = responsiblePersonAge;
    }

    public String getResponsiblePersonCareer() {
        return responsiblePersonCareer;
    }

    public void setResponsiblePersonCareer(String responsiblePersonCareer) {
        this.responsiblePersonCareer = responsiblePersonCareer;
    }

    public String getResponsiblePersonNativePlace() {
        return responsiblePersonNativePlace;
    }

    public void setResponsiblePersonNativePlace(String responsiblePersonNativePlace) {
        this.responsiblePersonNativePlace = responsiblePersonNativePlace;
    }

    public String getAccidentReason() {
        return accidentReason;
    }

    public void setAccidentReason(String accidentReason) {
        this.accidentReason = accidentReason;
    }

    public String getBurningMaterial() {
        return burningMaterial;
    }

    public void setBurningMaterial(String burningMaterial) {
        this.burningMaterial = burningMaterial;
    }

    public String getBurningArea() {
        return burningArea;
    }

    public void setBurningArea(String burningArea) {
        this.burningArea = burningArea;
    }

    public String getProtectArea() {
        return protectArea;
    }

    public void setProtectArea(String protectArea) {
        this.protectArea = protectArea;
    }

    public String getEconomicLoss() {
        return economicLoss;
    }

    public void setEconomicLoss(String economicLoss) {
        this.economicLoss = economicLoss;
    }

    public String getProtectEconomic() {
        return protectEconomic;
    }

    public void setProtectEconomic(String protectEconomic) {
        this.protectEconomic = protectEconomic;
    }

    public String getAccidentDescribe() {
        return accidentDescribe;
    }

    public void setAccidentDescribe(String accidentDescribe) {
        this.accidentDescribe = accidentDescribe;
    }

    public String getRescueMeasures() {
        return rescueMeasures;
    }

    public void setRescueMeasures(String rescueMeasures) {
        this.rescueMeasures = rescueMeasures;
    }

    public String getSmogSituation() {
        return smogSituation;
    }

    public void setSmogSituation(String smogSituation) {
        this.smogSituation = smogSituation;
    }

    public String getSpreadSituation() {
        return spreadSituation;
    }

    public void setSpreadSituation(String spreadSituation) {
        this.spreadSituation = spreadSituation;
    }

    public String getWaterSituation() {
        return waterSituation;
    }

    public void setWaterSituation(String waterSituation) {
        this.waterSituation = waterSituation;
    }

    public String getPeripherySituation() {
        return peripherySituation;
    }

    public void setPeripherySituation(String peripherySituation) {
        this.peripherySituation = peripherySituation;
    }

    public String getBuildingUpLayerNumber() {
        return buildingUpLayerNumber;
    }

    public void setBuildingUpLayerNumber(String buildingUpLayerNumber) {
        this.buildingUpLayerNumber = buildingUpLayerNumber;
    }

    public String getBuildingDownLayerNumber() {
        return buildingDownLayerNumber;
    }

    public void setBuildingDownLayerNumber(String buildingDownLayerNumber) {
        this.buildingDownLayerNumber = buildingDownLayerNumber;
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getBurningFloor() {
        return burningFloor;
    }

    public void setBurningFloor(String burningFloor) {
        this.burningFloor = burningFloor;
    }

    public String getLeakageMaterial() {
        return leakageMaterial;
    }

    public void setLeakageMaterial(String leakageMaterial) {
        this.leakageMaterial = leakageMaterial;
    }

    public String getCombatSituation() {
        return combatSituation;
    }

    public void setCombatSituation(String combatSituation) {
        this.combatSituation = combatSituation;
    }

    public String getLocaleCommander() {
        return localeCommander;
    }

    public void setLocaleCommander(String localeCommander) {
        this.localeCommander = localeCommander;
    }

    public String getLocaleDeputyCommander() {
        return localeDeputyCommander;
    }

    public void setLocaleDeputyCommander(String localeDeputyCommander) {
        this.localeDeputyCommander = localeDeputyCommander;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getLocaleCorrespondent() {
        return localeCorrespondent;
    }

    public void setLocaleCorrespondent(String localeCorrespondent) {
        this.localeCorrespondent = localeCorrespondent;
    }

    public String getDetachmentCorrespondent() {
        return detachmentCorrespondent;
    }

    public void setDetachmentCorrespondent(String detachmentCorrespondent) {
        this.detachmentCorrespondent = detachmentCorrespondent;
    }

    public String getSquadronCommander() {
        return squadronCommander;
    }

    public void setSquadronCommander(String squadronCommander) {
        this.squadronCommander = squadronCommander;
    }

    public String getSquadronCorrespondent() {
        return squadronCorrespondent;
    }

    public void setSquadronCorrespondent(String squadronCorrespondent) {
        this.squadronCorrespondent = squadronCorrespondent;
    }

    public String getVideographer() {
        return videographer;
    }

    public void setVideographer(String videographer) {
        this.videographer = videographer;
    }

    public Integer getDispatchPersonNum() {
        return dispatchPersonNum;
    }

    public void setDispatchPersonNum(Integer dispatchPersonNum) {
        this.dispatchPersonNum = dispatchPersonNum;
    }

    public Integer getDispatchVehicleNum() {
        return dispatchVehicleNum;
    }

    public void setDispatchVehicleNum(Integer dispatchVehicleNum) {
        this.dispatchVehicleNum = dispatchVehicleNum;
    }

    public Integer getDispatchTrainNum() {
        return dispatchTrainNum;
    }

    public void setDispatchTrainNum(Integer dispatchTrainNum) {
        this.dispatchTrainNum = dispatchTrainNum;
    }

    public String getCombatProcessDesc() {
        return combatProcessDesc;
    }

    public void setCombatProcessDesc(String combatProcessDesc) {
        this.combatProcessDesc = combatProcessDesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Long getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Long accidentTime) {
        this.accidentTime = accidentTime;
    }

    public Integer getDispatchWaterGunNum() {
        return dispatchWaterGunNum;
    }

    public void setDispatchWaterGunNum(Integer dispatchWaterGunNum) {
        this.dispatchWaterGunNum = dispatchWaterGunNum;
    }

    public String getAttendanceNum() {
        return attendanceNum;
    }

    public void setAttendanceNum(String attendanceNum) {
        this.attendanceNum = attendanceNum;
    }

    public String getTrappedPersonNum() {
        return trappedPersonNum;
    }

    public void setTrappedPersonNum(String trappedPersonNum) {
        this.trappedPersonNum = trappedPersonNum;
    }

    public String getRescueNum() {
        return rescueNum;
    }

    public void setRescueNum(String rescueNum) {
        this.rescueNum = rescueNum;
    }

    public String getMassRescueNum() {
        return massRescueNum;
    }

    public void setMassRescueNum(String massRescueNum) {
        this.massRescueNum = massRescueNum;
    }

    public String getTroopsRescueNum() {
        return troopsRescueNum;
    }

    public void setTroopsRescueNum(String troopsRescueNum) {
        this.troopsRescueNum = troopsRescueNum;
    }

    public String getInjuredNum() {
        return injuredNum;
    }

    public void setInjuredNum(String injuredNum) {
        this.injuredNum = injuredNum;
    }

    public String getMassInjuredNum() {
        return massInjuredNum;
    }

    public void setMassInjuredNum(String massInjuredNum) {
        this.massInjuredNum = massInjuredNum;
    }

    public String getTroopsInjuredNum() {
        return troopsInjuredNum;
    }

    public void setTroopsInjuredNum(String troopsInjuredNum) {
        this.troopsInjuredNum = troopsInjuredNum;
    }

    public String getDeathNum() {
        return deathNum;
    }

    public void setDeathNum(String deathNum) {
        this.deathNum = deathNum;
    }

    public String getMassDeathNum() {
        return massDeathNum;
    }

    public void setMassDeathNum(String massDeathNum) {
        this.massDeathNum = massDeathNum;
    }

    public String getTroopsDeathNum() {
        return troopsDeathNum;
    }

    public void setTroopsDeathNum(String troopsDeathNum) {
        this.troopsDeathNum = troopsDeathNum;
    }

    public String getOutContactNum() {
        return outContactNum;
    }

    public void setOutContactNum(String outContactNum) {
        this.outContactNum = outContactNum;
    }

    public String getMassOutContactNum() {
        return massOutContactNum;
    }

    public void setMassOutContactNum(String massOutContactNum) {
        this.massOutContactNum = massOutContactNum;
    }

    public String getTroopsOutContactNum() {
        return troopsOutContactNum;
    }

    public void setTroopsOutContactNum(String troopsOutContactNum) {
        this.troopsOutContactNum = troopsOutContactNum;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
