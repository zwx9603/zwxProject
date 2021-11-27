package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;

import java.util.List;

/**
 * 描述：现场信息 保存参数 ( 包含 处警反馈  指令反馈 集结反馈)
 *
 */
public class LocaleSaveInputInfo {

    private String id ; //主键

    private String incidentId;//案件ID

    private String commandId;// 指挥ID

    private String instructId;// 指令单ID （ 处警记录id  集结点id ）

    private String instructRecordId;// 指令记录ID ( 处警信息id  集结积极力量id )

    private Integer  localeType = 0; // 反馈类型 ( 0现场反馈 1指令反馈 2 集结力量反馈 ) 默认0  现场反馈

    private Integer  localeSource = 1  ; //反馈来源 （ 1接处警 2实战指挥 3移动反馈  ） 默认1 接处警

    private String localeDesc;//现场描述

    private String localeExtension;// 现场详细 扩展内容

    private String fireSitesTemperature;//火场温度

    private String weatherInformationCode;//天气字典代码

    private String weatherTemperature;//气温

    private String windDirection;//风向

    private String windGrade;//风力等级

    private String relativeHumidity;//相对湿度

    private String disasterGradeCode;//灾害等级代码

    private String burningArea;//燃烧面积

    private String smogSituationCode;//烟雾情况代码

    private Integer whetherSmogSituation;// 是否冒烟 1 冒烟 0 不冒烟

    private Integer whetherFire;// 是否着火 1 着火 0 不着火

    private String tempRallyPointAddress;//临时集结点地址

    private String longitude;// 临时集结点 经度

    private String latitude;// 临时集结点 纬度

    private String crimeAddress;// 案发地址

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String feedbackObjectId;// 反馈对象id

    private String feedbackObjectName;// 反馈对象名称

    private String feedbackOrganizationId;// 反馈单位编码

    private String feedbackOrganizationName ;// 反馈单位名称

    private String  operatorPerson ; //操作人 填写人

    private String remarks; // 备注

    private Integer whetherFileFeedback = 0   ; //是否为附件反馈 0文字反馈 1附件反馈 默认0 文件反馈

    private List<AttachmentSaveInputInfo>  attachments  ; //附件

    private String attendanceNum;// 到场人数

    private String trappedPersonNum;// 被困人数

    private String rescueNum;// 抢救人数

    private String massRescueNum;//群众抢救人数

    private String troopsRescueNum;// 部队抢救人数

    private String injuredNum;//属性 受伤人数

    private String massInjuredNum;// 群众受伤人数

    private String troopsInjuredNum;// 部队受伤人数

    private String deathNum;// 死亡人数

    private String massDeathNum;// 群众死亡人数

    private String troopsDeathNum;// 部队死亡人数

    private String outContactNum  ; //  失联人员

    private String massOutContactNum  ; //  群众失联人员

    private String troopsOutContactNum  ; //  部队失联人员

    private String fireSitesDesc;// 火势_简要情况

    private String  burnMaterialCode;// 燃烧物质_编码

    private String burnMaterialName;// 燃烧物质_名称

    private String buildingUpLayerNumber;//建筑层数(地上)

    private String buildingDownLayerNumber;//建筑层数(地下)

    private String buildingStructure;//建筑结构

    private String buildingArea;//建筑面积

    private String burningFloor;//燃烧层

    private String windSpeed;// 风_速度

    private String pressure;//  气压

    private String smogSituationDesc;//  烟雾_简要情况

    private String localeCommander;//  现场指挥人_姓名


    public Integer getWhetherFileFeedback() {
        return whetherFileFeedback;
    }

    public void setWhetherFileFeedback(Integer whetherFileFeedback) {
        this.whetherFileFeedback = whetherFileFeedback;
    }

    public List<AttachmentSaveInputInfo> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentSaveInputInfo> attachments) {
        this.attachments = attachments;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getLocaleDesc() {
        return localeDesc;
    }

    public void setLocaleDesc(String localeDesc) {
        this.localeDesc = localeDesc;
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

    public String getFireSitesTemperature() {
        return fireSitesTemperature;
    }

    public void setFireSitesTemperature(String fireSitesTemperature) {
        this.fireSitesTemperature = fireSitesTemperature;
    }

    public String getWeatherInformationCode() {
        return weatherInformationCode;
    }

    public void setWeatherInformationCode(String weatherInformationCode) {
        this.weatherInformationCode = weatherInformationCode;
    }

    public String getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(String weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindGrade() {
        return windGrade;
    }

    public void setWindGrade(String windGrade) {
        this.windGrade = windGrade;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getDisasterGradeCode() {
        return disasterGradeCode;
    }

    public void setDisasterGradeCode(String disasterGradeCode) {
        this.disasterGradeCode = disasterGradeCode;
    }

    public String getBurningArea() {
        return burningArea;
    }

    public void setBurningArea(String burningArea) {
        this.burningArea = burningArea;
    }

    public String getSmogSituationCode() {
        return smogSituationCode;
    }

    public void setSmogSituationCode(String smogSituationCode) {
        this.smogSituationCode = smogSituationCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOperatorPerson() {
        return operatorPerson;
    }

    public void setOperatorPerson(String operatorPerson) {
        this.operatorPerson = operatorPerson;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getInstructId() {
        return instructId;
    }

    public void setInstructId(String instructId) {
        this.instructId = instructId;
    }

    public String getInstructRecordId() {
        return instructRecordId;
    }

    public void setInstructRecordId(String instructRecordId) {
        this.instructRecordId = instructRecordId;
    }

    public Integer getLocaleType() {
        return localeType;
    }

    public void setLocaleType(Integer localeType) {
        this.localeType = localeType;
    }

    public Integer getLocaleSource() {
        return localeSource;
    }

    public void setLocaleSource(Integer localeSource) {
        this.localeSource = localeSource;
    }

    public String getLocaleExtension() {
        return localeExtension;
    }

    public void setLocaleExtension(String localeExtension) {
        this.localeExtension = localeExtension;
    }

    public String getDeathNum() {
        return deathNum;
    }

    public void setDeathNum(String deathNum) {
        this.deathNum = deathNum;
    }

    public String getInjuredNum() {
        return injuredNum;
    }

    public void setInjuredNum(String injuredNum) {
        this.injuredNum = injuredNum;
    }

    public Integer getWhetherSmogSituation() {
        return whetherSmogSituation;
    }

    public void setWhetherSmogSituation(Integer whetherSmogSituation) {
        this.whetherSmogSituation = whetherSmogSituation;
    }

    public Integer getWhetherFire() {
        return whetherFire;
    }

    public void setWhetherFire(Integer whetherFire) {
        this.whetherFire = whetherFire;
    }

    public String getTempRallyPointAddress() {
        return tempRallyPointAddress;
    }

    public void setTempRallyPointAddress(String tempRallyPointAddress) {
        this.tempRallyPointAddress = tempRallyPointAddress;
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

    public String getCrimeAddress() {
        return crimeAddress;
    }

    public void setCrimeAddress(String crimeAddress) {
        this.crimeAddress = crimeAddress;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getFeedbackObjectId() {
        return feedbackObjectId;
    }

    public void setFeedbackObjectId(String feedbackObjectId) {
        this.feedbackObjectId = feedbackObjectId;
    }

    public String getFeedbackObjectName() {
        return feedbackObjectName;
    }

    public void setFeedbackObjectName(String feedbackObjectName) {
        this.feedbackObjectName = feedbackObjectName;
    }

    public String getFeedbackOrganizationId() {
        return feedbackOrganizationId;
    }

    public void setFeedbackOrganizationId(String feedbackOrganizationId) {
        this.feedbackOrganizationId = feedbackOrganizationId;
    }

    public String getFeedbackOrganizationName() {
        return feedbackOrganizationName;
    }

    public void setFeedbackOrganizationName(String feedbackOrganizationName) {
        this.feedbackOrganizationName = feedbackOrganizationName;
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

    public String getFireSitesDesc() {
        return fireSitesDesc;
    }

    public void setFireSitesDesc(String fireSitesDesc) {
        this.fireSitesDesc = fireSitesDesc;
    }

    public String getBurnMaterialCode() {
        return burnMaterialCode;
    }

    public void setBurnMaterialCode(String burnMaterialCode) {
        this.burnMaterialCode = burnMaterialCode;
    }

    public String getBurnMaterialName() {
        return burnMaterialName;
    }

    public void setBurnMaterialName(String burnMaterialName) {
        this.burnMaterialName = burnMaterialName;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSmogSituationDesc() {
        return smogSituationDesc;
    }

    public void setSmogSituationDesc(String smogSituationDesc) {
        this.smogSituationDesc = smogSituationDesc;
    }

    public String getLocaleCommander() {
        return localeCommander;
    }

    public void setLocaleCommander(String localeCommander) {
        this.localeCommander = localeCommander;
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
}
