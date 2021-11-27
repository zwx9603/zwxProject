package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：事故情况
 *
 */
@Entity
@Table(name = "JCJ_SGQK ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AccidentEntity extends BaseEntity {

    @Column(name = "JQGDJL_TYWYSBM", length = 40)
    private String idCode; //todo  字段 警情归档记录_通用唯一识别码

//    @Column(name = "AJID", length = 100 )
    @Column(name = "JQ_TYWYSBM", length = 100 )
    private String incidentId;//todo  字段 案件id 警情_通用唯一识别码

    @Column(name = "JQDJDM" , length =  100 )
    private String incidentLevelCode ; //todo  字段 警情等级代码

    @Column(name = "RQSJ"  )
    private Long accidentTime ; //todo  字段 填写时间 日期时间

    @Column(name = "AFSJDZ", length = 400 )
    private String actualCrimeAddress;//实际案发地址

    @Column(name = "SGDWMC", length = 200 )
    private String accidentUnitName;//事故单位名称

    @Column(name = "SGDWXZ", length = 100 )
    private String accidentUnitNature;//事故单位性质

    @Column(name = "FZRXM", length = 100 )
    private String responsiblePersonName;//负责人姓名

    @Column(name = "FZRNL", length = 50 )
    private String responsiblePersonAge;//负责人年龄

    @Column(name = "FZRZYXX", length = 400 )
    private String responsiblePersonCareer ;//负责人职业信息

    @Column(name = "FZRJG", length = 100 )
    private String responsiblePersonNativePlace ;//负责人籍贯

//    @Column(name = "SGYY", length = 2000 )
    @Column(name = "ZHYY_JYQK", length = 2000 )
    private String accidentReason;//todo  字段 事故原因 灾害原因_简要情况

    @Column(name = "RSWZ", length = 200 )
    private String burningMaterial;//燃烧物质

//    @Column(name = "RSMJ" , length = 200 )
    @Column(name = "RS_MJ" , length = 200 )
    private String burningArea;//todo  字段 燃烧面积

    @Column(name = "BHMJ"  , length = 200 )
    private String protectArea;//保护面积

//    @Column(name = "JJSS"  , length = 800 )
    @Column(name = "CCSS_JYQK"  , length = 800 )
    private String economicLoss;//todo  字段 经济损失 财产损失_简要情况

    @Column(name = "BHJZ", length = 800 )
    private String protectEconomic;//保护价值

    @Column(name = "ZYQK", length = 2000 )
    private String accidentDescribe;//主要情况

    @Column(name = "YJCS", length = 1000 )
    private String rescueMeasures; //营救措施

    @Column(name = "YWQK", length = 200 )
    private String smogSituation; //烟雾情况

    @Column(name = "MYQK", length = 1000 )
    private String spreadSituation;//蔓延情况

    @Column(name = "SYQK", length = 1000 )
    private String waterSituation;//水源情况

    @Column(name = "ZBQK", length = 1000 )
    private String peripherySituation;//周边情况

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

    @Column(name = "XLWZ", length = 1000 )
    private String leakageMaterial;//泄漏物质

    @Column(name = "CDCC" )
    private Integer dispatchTrainNum; //todo  字段 出动车次

    @Column(name = "CDCL_SL" )
    private Integer dispatchVehicleNum; //todo  字段 出动车辆数

    @Column(name = "CDRS" )
    private Integer dispatchPersonNum; //todo  字段 出动人数

    @Column(name = "CDSQS"  )
    private Integer dispatchWaterGunNum;//todo  字段 出动水枪数

    @Column(name = "DCRY_RS", length = 100)
    private String attendanceNum;//todo  字段 到场人数

    @Column(name = "BKRY_RS", length = 100)
    private String trappedPersonNum;//todo  字段 被困人数

    @Column(name = "QJRS"  , length = 100 )
    private String rescueNum;//todo  属性 抢救人数

    @Column(name = "QZQJ_RS"  , length = 100 )
    private String massRescueNum;//todo  属性 群众抢救人数

    @Column(name = "DWQJ_RS"  , length = 100 )
    private String troopsRescueNum;//todo  属性 部队抢救人数

    @Column(name = "SSRS", length = 100)
    private String injuredNum;//todo  字段 属性 受伤人数

    @Column(name = "QZSS_RS", length = 100)
    private String massInjuredNum;//todo  字段 群众受伤人数

    @Column(name = "DWSS_RS", length = 100)
    private String troopsInjuredNum;//todo  字段 部队受伤人数

    @Column(name = "SWRS"  , length = 100 )
    private String deathNum;//todo  字段 属性 死亡人数

    @Column(name = "QZSW_RS", length = 100)
    private String massDeathNum;//todo  字段 群众死亡人数

    @Column(name = "DWSW_RS", length = 100)
    private String troopsDeathNum;//todo  字段 部队死亡人数

    @Column(name = "SLRS" , length = 100)
    private String outContactNum  ; //todo  字段  失联人员数

    @Column(name = "QZSL_RS" , length = 100)
    private String massOutContactNum  ; //todo  字段  群众失联人员

    @Column(name = "DWSL_RS" , length = 100)
    private String troopsOutContactNum  ; //todo  字段  部队失联人员


    @Column(name = "GDRZXH", length = 50)
    private String seatNumber; // todo  字段  坐席号

    @Column(name = "GDR", length = 100)
    private String personId;//todo  字段 警员

    @Column(name = "GDR_GH", length = 100)
    private String personNumber;//todo  字段 警员工号

    @Column(name = "GDR_XM", length = 100)
    private String personName;// todo  字段 警员姓名

    @Column(name = "BZ",length = 800)
    private String remarks;//备注

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

    public String getBurningMaterial() {
        return burningMaterial;
    }

    public void setBurningMaterial(String burningMaterial) {
        this.burningMaterial = burningMaterial;
    }

    public String getProtectEconomic() {
        return protectEconomic;
    }

    public void setProtectEconomic(String protectEconomic) {
        this.protectEconomic = protectEconomic;
    }


    public String getAccidentReason() {
        return accidentReason;
    }

    public void setAccidentReason(String accidentReason) {
        this.accidentReason = accidentReason;
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

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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


    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }


    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getIncidentLevelCode() {
        return incidentLevelCode;
    }

    public void setIncidentLevelCode(String incidentLevelCode) {
        this.incidentLevelCode = incidentLevelCode;
    }

    public Long getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Long accidentTime) {
        this.accidentTime = accidentTime;
    }

    public Integer getDispatchTrainNum() {
        return dispatchTrainNum;
    }

    public void setDispatchTrainNum(Integer dispatchTrainNum) {
        this.dispatchTrainNum = dispatchTrainNum;
    }

    public Integer getDispatchVehicleNum() {
        return dispatchVehicleNum;
    }

    public void setDispatchVehicleNum(Integer dispatchVehicleNum) {
        this.dispatchVehicleNum = dispatchVehicleNum;
    }

    public Integer getDispatchPersonNum() {
        return dispatchPersonNum;
    }

    public void setDispatchPersonNum(Integer dispatchPersonNum) {
        this.dispatchPersonNum = dispatchPersonNum;
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
