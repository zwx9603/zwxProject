package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：现场信息 ( 案件反馈  指令反馈 )
 *
 */
@Entity
@Table(name = "JCJ_XCXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LocaleEntity extends BaseEntity {

    @Column(name = "JQXC_TYWYSBM", length = 40)
    private String idCode; //todo  警情现场_通用唯一识别码

//    @Column(name = "AJID", length = 100)
    @Column(name = "JQ_TYWYSBM", length = 100)
    private String incidentId;// 案件ID 警情_通用唯一识别码

    @Column(name = "ZHID", length = 100)
    private String commandId;// 指挥ID

    @Column(name = "ZLDID", length = 100)
    private String instructId;// 指令单ID （ 处警记录id  集结点id ）

    @Column(name = "ZLJLID", length = 100)
    private String instructRecordId;// 指令记录ID ( 处警信息id  集结积极力量id )

    @Column(name = "XCXXLX" )
    private Integer  localeType ; // 反馈类型 ( 0现场反馈 1指令反馈 2 集结力量反馈 ) 默认0  现场反馈

    @Column(name = "XCXXLY" )
    private Integer  localeSource   ; //反馈来源 （ 1接处警 2实战指挥 3移动反馈  ） 默认1 接处警

//    @Column(name = "CAIJSJ")
    @Column(name = "JL_RQSJ")
    private Long collectionTime;//todo 字段 采集时间 反馈时间

//    @Column(name = "XCMS", length = 2000)
    @Column(name = "XC_JYQK", length = 2000)
    private String localeDesc;//todo 字段 现场描述  反馈内容 现场_简要情况

//    @Column(name = "XCMSKZ", length = 4000)
    @Column(name = "XCMSKZ_JYQK", length = 4000)
    private String localeExtension;//todo 字段 现场详细 扩展内容

//    @Column(name = "DCRS", length = 100)
    @Column(name = "DC_RS", length = 100)
    private String attendanceNum;//todo 字段 到场人数

//    @Column(name = "BKRS", length = 100)
    @Column(name = "BK_RS", length = 100)
    private String trappedPersonNum;//todo 字段 被困人数

    @Column(name = "QJRS"  , length = 100 )
    private String rescueNum;//todo 字段 抢救人数

    @Column(name = "QZQJ_RS"  , length = 100 )
    private String massRescueNum;//todo  属性 群众抢救人数

    @Column(name = "DWQJ_RS"  , length = 100 )
    private String troopsRescueNum;//todo  属性 部队抢救人数

    @Column(name = "SSRS", length = 100)
    private String injuredNum;//todo  字段 属性 受伤人数

//    @Column(name = "QZSSRS", length = 100)
    @Column(name = "QZSS_RS", length = 100)
    private String massInjuredNum;//todo  字段 群众受伤人数

//    @Column(name = "BDSSRS", length = 100)
    @Column(name = "DWSS_RS", length = 100)
    private String troopsInjuredNum;//todo  字段 部队受伤人数

    @Column(name = "SWRS"  , length = 100 )
    private String deathNum;//todo  字段 属性 死亡人数

//    @Column(name = "QZSWRS", length = 100)
    @Column(name = "QZSW_RS", length = 100)
    private String massDeathNum;//todo  字段 群众死亡人数

//    @Column(name = "BDSWRS", length = 100)
    @Column(name = "DWSW_RS", length = 100)
    private String troopsDeathNum;//todo  字段 部队死亡人数

    @Column(name = "SLRS" , length = 100)
    private String outContactNum  ; //todo  字段  失联人员

    @Column(name = "QZSL_RS" , length = 100)
    private String massOutContactNum  ; //todo  字段  群众失联人员

    @Column(name = "DWSL_RS" , length = 100)
    private String troopsOutContactNum  ; //todo  字段  部队失联人员


    @Column(name = "HS_JYQK" , length = 1000)
    private String fireSitesDesc;//todo 字段 火势_简要情况

    @Column(name = "RSWZ_BM" , length = 100)
    private String  burnMaterialCode;//todo 字段 燃烧物质_编码

    @Column(name = "RSWZ_MC" , length = 200)
    private String burnMaterialName;//todo 字段 燃烧物质_名称

    @Column(name = "JZCSDS"  , length = 200 )
    private String buildingUpLayerNumber;//建筑层数(地上)

    @Column(name = "JZCSDX"  , length = 200 )
    private String buildingDownLayerNumber;//建筑层数(地下)

    @Column(name = "JZJG", length = 200 )
    private String buildingStructure;//建筑结构

    @Column(name = "JZMJ"  , length = 200 )
    private String buildingArea;//建筑面积

    @Column(name = "RSC", length = 200 )
    private String burningFloor;//燃烧层

//    @Column(name = "HCWD" , length = 100)
    @Column(name = "HC_WD" , length = 100)
    private String fireSitesTemperature;//todo 字段 火场温度

//    @Column(name = "TQDM", length = 100)
    @Column(name = "TQZKLBDM", length = 100)
    private String weatherInformationCode;//todo 字段 天气字典代码

//    @Column(name = "QW" , length = 100)
    @Column(name = "KQ_WD" , length = 100)
    private String weatherTemperature;//todo 字段 气温

//    @Column(name = "FX", length = 100)
    @Column(name = "FXLBDM", length = 100)
    private String windDirection;//todo 字段 风向

    @Column(name = "F_SD", length = 100)
    private String windSpeed;//todo 字段 风_速度

//    @Column(name = "FLDJ", length = 100)
    @Column(name = "FLDJDM", length = 100)
    private String windGrade;//todo 字段 风力等级

    @Column(name = "DQ_YL", length = 100)
    private String pressure;//todo 字段 气压

    @Column(name = "XDSD", length = 100)
    private String relativeHumidity;//相对湿度

    @Column(name = "ZHDJDM", length = 100)
    private String disasterGradeCode;//灾害等级代码

//    @Column(name = "RSMJ" , length = 100)
    @Column(name = "RS_MJ" , length = 100)
    private String burningArea;//todo 字段 燃烧面积

    @Column(name = "YWQKDM", length = 100 )
    private String smogSituationCode;//烟雾情况代码

    @Column(name = "YAW_JYQK", length = 100 )
    private String smogSituationDesc;//todo 字段 烟雾_简要情况

    @Column(name = "ZHR_XM", length = 100 )
    private String localeCommander;//todo 字段 现场指挥人_姓名


    @Column(name = "SFMY" )
    private Integer whetherSmogSituation;// 是否冒烟 1 冒烟 0 不冒烟

    @Column(name = "SFZH"  )
    private Integer whetherFire;// 是否着火 1 着火 0 不着火

    @Column(name = "LSJJDDZ", length = 400)
    private String tempRallyPointAddress;//临时集结点地址

    @Column(name = "GIS_X", length = 50)
    private String longitude;// 临时集结点 经度

    @Column(name = "GIS_Y", length = 50)
    private String latitude;// 临时集结点 纬度


    @Column(name = "AFDZ", length = 400)
    private String crimeAddress;// 案发地址

    @Column(name = "AJLX", length = 100)
    private String incidentTypeCode;// 案件类型代码

    @Column(name = "AJLXZX", length = 100)
    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）


//    @Column(name = "FKDXID", length = 100)
    @Column(name = "FKR_ID", length = 100)
    private String feedbackObjectId;//todo 字段 反馈对象id

//    @Column(name = "FKDXMC", length = 200)
    @Column(name = "FKR_XM", length = 200)
    private String feedbackObjectName;//todo 字段 反馈对象名称

//    @Column(name = "FKDWBM", length = 100)
    @Column(name = "FKJG_DWBM", length = 100)
    private String feedbackOrganizationId;//todo 字段 反馈单位编码

//    @Column(name = "FKDWMC", length = 200)
    @Column(name = "FKJG_DWMC", length = 200)
    private String feedbackOrganizationName ;//todo 字段 反馈单位名称

//    @Column(name = "CZR", length = 100 )
    @Column(name = "JLR_XM", length = 100 )
    private String  operatorPerson ; //todo 字段 操作人 填写人 记录人_姓名

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "SFFJFK"  )
    private Integer whetherFileFeedback  ; //是否为附件反馈 0文字反馈 1附件反馈 默认0 文字反馈

    public Integer getWhetherFileFeedback() {
        return whetherFileFeedback;
    }

    public void setWhetherFileFeedback(Integer whetherFileFeedback) {
        this.whetherFileFeedback = whetherFileFeedback;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public Long getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Long collectionTime) {
        this.collectionTime = collectionTime;
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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
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
