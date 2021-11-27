package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:案件基本信息
 *
 */
@Entity
@Table(name = "JCJ_AJXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class IncidentEntity extends BaseEntity {

    @Column(name = "JQ_TYWYSBM", length = 40)
    private String idCode; //todo 字段  警情_通用唯一识别码

    /**  start 受理电话信息 */

//    @Column(name = "ZJHM", length = 50)
    @Column(name = "BJDH", length = 50)
    private String alarmPhone;//todo 字段 报警电话

    @Column(name = "BJRXM", length = 100)
    private String alarmPersonName;//报警人姓名

    @Column(name = "BJRXB", length = 100 )
    private String alarmPersonSex;//报警人性别

    @Column(name = "BJRLXDH", length = 50)
    private String alarmPersonPhone;//报警人联系电话

    @Column(name = "BJDZ", length = 400)
    private String alarmAddress;//报警地址

//    @Column(name = "BJKSSJ"  )
    @Column(name = "BJSJ"  )
    private Long alarmStartTime;  //todo 字段 报警开始时间（振铃时间）

    @Column(name = "JJKSSJ"  )
    private Long receiveStartTime;  //接警开始时间（接听时间）

    @Column(name = "BJJSSJ"  )
    private Long alarmEndTime;  //报警结束时间（挂机时间）

    @Column(name = "JJJSSJ"  )
    private Long receiveEndTime;  //接警结束时间（立案时间）

    @Column(name = "LYH", length = 400 )
    private String relayRecordNumber;//录音号

    @Column(name = "SFBK"  )
    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    /**  end受理电话信息 */


    @Column(name = "AJLY", length = 100 )
    private String incidentSource;  //案件来源 iecs(119)  cad(110)

    @Column(name = "AJBH", length = 100)
    private String incidentNumber; // 案件编号（日期坐席工号）

//    @Column(name = "SLDID", length = 100)
    @Column(name = "BJ_TYWYSBM", length = 100 )
    private String acceptanceId; //todo 字段  受理单Id 报警_通用唯一识别码

//    @Column(name = "ZGZD", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String squadronOrganizationId;//todo 字段 主管中队 消防救援机构_通用唯一识别码

    @Column(name = "SSDD", length = 100)
    private String brigadeOrganizationId; // 所属大队

//    @Column(name = "XZQH", length = 100 )
    @Column(name = "XZQHDM", length = 100 )
    private String districtCode;// todo 字段  行政区编码

//    @Column(name = "BJFS", length = 100 )
    @Column(name = "BJFSLBDM", length = 100 )
    private String alarmModeCode;  //todo 字段 报警方式代码 报警方式类别代码

    @Column(name = "LAFS", length = 100)
    private String registerModeCode;// 立案方式代码

    @Column(name = "LASJ")
    private  Long registerIncidentTime;// 立案时间

   @Column(name = "MC", length = 200 )
    private String title; //todo 字段 属性  名称

//    @Column(name = "AFDZ", length = 400)
    @Column(name = "DDMC", length = 400)
    private String crimeAddress;//todo 字段 案发地址 地点名称

//    @Column(name = "GIS_X", length = 50)
    @Column(name = "DQJD", length = 50)
    private String longitude;//todo 字段  经度

//    @Column(name = "GIS_Y", length = 50)
    @Column(name = "DQWD", length = 50)
    private String latitude;//todo 字段  纬度

//    @Column(name = "GIS_H", length = 50)
    @Column(name = "DQGD", length = 50)
    private String height;//todo 字段 高度

//    @Column(name = "AJLX", length = 100)
    @Column(name = "JQFLYDM", length = 100)
    private String incidentTypeCode;// todo 字段 案件类型代码 警情分类与代码

//    @Column(name = "AJLXZX", length = 100)
    @Column(name = "JQFLYDMZX", length = 100)
    private String incidentTypeSubitemCode;//todo 字段  案件类型子项代码（细类）（案件类型备用）警情分类与代码子项

//    @Column(name = "AJDJ", length = 100)
    @Column(name = "JQDJDM", length = 100)
    private String incidentLevelCode;//todo 字段  案件等级代码

    @Column(name = "CZDX", length = 100)
    private String disposalObjectCode;// 处置对象代码

//    @Column(name = "AJZT", length = 100)
    @Column(name = "JQZT", length = 100)
    private String incidentStatusCode;//todo 字段  案件状态代码

    @Column(name = "AJXZ", length = 100)
    private String incidentNatureCode;// 案件性质代码 1 实警；0 虚警

    @Column(name = "SFZDAJ" )
    private Integer whetherImportSign;// 是否重大案件标识

    @Column(name = "JQDX", length = 100)
    private String keyUnit;//todo 字段 重点单位 警情对象

//    @Column(name = "ZDDWBH", length = 100 )
    @Column(name = "JQDX_TYWYSBM", length = 100)
    private String keyUnitId;//todo 字段 重点单位  警情对象通用唯一识别码

    @Column(name = "JQDX_JQDXLBDM", length = 100)
    private String keyUnitTypeCode ;//todo 字段 重点单位 警情对象类别代码

//    @Column(name = "AJMS", length = 1000)
    @Column(name = "JQ_JYQK", length = 1000)
    private String incidentDescribe;//todo 字段 警情要情况

    @Column(name = "BCXX", length = 1000)
    private String supplementInfo;// 补充信息

    @Column(name = "ZYSX", length = 1000)
    private String attentions;// 注意事项

    @Column(name = "HBZT"  )
    private Integer mergeStatus;// 合并状态 0非合并 1合并

    @Column(name = "GLAJBH", length = 100)
    private String relationIncidentNumber; // 关联案件单编号

    @Column(name = "BLAJBH", length = 100)
    private String retentionIncidentNumber; // 保留案件编号

    @Column(name = "LADWBH", length = 100)
    private String registerOrganizationId; // 立案机构ID

    @Column(name = "LAZXH", length = 50)
    private String registerSeatNumber; // 立案接警坐席号

    @Column(name = "JJYGH", length = 100)
    private String acceptancePersonNumber;// 接警员工号

    @Column(name = "JJYXM", length = 100)
    private String acceptancePersonName;// 接警员姓名

    @Column(name = "CLLX", length = 100)
    private String handleType; // 处理类型

    @Column(name = "CJDWS", length = 1000)
    private String dispatchOrganization; //出动机构

    @Column(name = "CDCLS", length = 4000)
    private String dispatchVehicle; // 出动车辆

//    @Column(name = "JQBQ", length = 100)
    @Column(name = "JQBSLBDM", length = 100)
    private String incidentLabel; // todo 字段 警情标签 警情标识类别代码

    @Column(name = "JZJG", length = 100)
    private String buildingStructureCode;// 建筑结构代码

//    @Column(name = "YWQK", length = 100)
    @Column(name = "YWQKDM", length = 100)
    private String smogSituationCode;//todo 字段  烟雾情况代码

    @Column(name = "RYBGS" , length = 100)
    private String trappedCount;//被困人数

//    @Column(name = "LFCS")
    @Column(name = "LC")
    private Integer storeysOfBuildings;//todo 字段 楼房层数

    @Column(name = "RSLC" , length = 100)
    private String burningFloor;// 燃烧楼层 救援楼层

    @Column(name = "RSMJ" , length = 100)
    private String burningArea;// 燃烧面积

    @Column(name = "ZHCS" , length = 200)
    private String disasterSites;//灾害场所

    @Column(name = "ABLXRXM" ,length =  200 )
    private String securityContactPerson;//安保联系人

    @Column(name = "LXRDH" ,length =  50 )
    private String contactPersonPhone;//安保联系人电话

    @Column(name = "SFMGDQ"  )
    private Integer whetherSensitiveArea;//是否敏感地区

    @Column(name = "SFQSDQ"  )
    private Integer whetherWaterShortageArea;//是否缺水地区

    @Column(name = "SFMGSJ"  )
    private Integer whetherSensitiveTime;//是否敏感时间

    @Column(name = "XDSJ")
    private Long transmitTime;// 下达时间

//    @Column(name = "JSSJ"  )
    @Column(name = "JSMLSJ")
    private Long receiveTime;//todo 字段 接收时间 接受命令时间

    @Column(name = "CDSJ")
    private Long dispatchTime;// 出动时间

//    @Column(name = "DDXCSJ")
    @Column(name = "DCSJ")
    private Long arriveTime;// todo 字段 到达时间

    @Column(name = "ZDZKSJ")
    private Long fightTime;//todo 字段 战斗展开时间

    @Column(name = "CSSJ")
    private Long fightStartTime;// 作战开始时间 出水

    @Column(name = "HSKZSJ")
    private Long fireControlTime ;//todo 字段 火势控制时间

    @Column(name = "JBPMSJ")
    private Long extinguishTime ;//todo 字段 基本扑灭时间

    @Column(name = "TSSJ")
    private Long fightEdnTime;// 作战结束时间 返回

    @Column(name = "GDSJ")
    private Long returnTime;// 归队时间

    @Column(name = "ZFSJ")
    private Long halfwayReturnTime;//中返时间

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    @Column(name = "SFHBZAJ"  )
    private Integer whetherMainMerge ;// 是否为合并主案件

    @Column(name = "RYSSS" , length = 100)
    private String injuredCount;//受伤人数

    @Column(name = "RYSWS" , length = 100)
    private String deathCount;//死亡人数

    @Column(name = "ABKASJ"   )
    private Long securityStartTime;//安保开始时间

    @Column(name = "ABJSSJ"  )
    private Long securityEndTime;//安保结束时间

    @Column(name = "SFCSJ"  )
    private Integer whetherTestSign;//是否测试警情 0非测试警 1 测试警

    @Column(name = "AJGDSJ")
    private Long placeFileIncidentTime;//案件归档时间

//    @Column(name = "AJJASJ")
    @Column(name = "JASJ")
    private Long incidentEndTime;// todo 字段 案件结案时间

    @Column(name = "AJQZID")
    private String incidentGroupId;//案件群组id 如果有多个群组 多个群组逗号分隔

    //2021-04-22 新增 统一地址库id
    @Column(name = "tydzkid")
    private String uniformAddressId;//统一地址库id

    public String getUniformAddressId() {
        return uniformAddressId;
    }

    public void setUniformAddressId(String uniformAddressId) {
        this.uniformAddressId = uniformAddressId;
    }
    //    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID")
//    private IncidentExtend119Entity  incidentExtend119 ;
//
//    public void setIncidentExtend119(IncidentExtend119Entity incidentExtend119) {
//        this.incidentExtend119 = incidentExtend119;
//    }
//
//
//    public IncidentExtend119Entity getIncidentExtend119() {
//        return incidentExtend119;
//    }


    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
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

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getIncidentGroupId() {
        return incidentGroupId;
    }

    public void setIncidentGroupId(String incidentGroupId) {
        this.incidentGroupId = incidentGroupId;
    }


    public Long getPlaceFileIncidentTime() {
        return placeFileIncidentTime;
    }

    public void setPlaceFileIncidentTime(Long placeFileIncidentTime) {
        this.placeFileIncidentTime = placeFileIncidentTime;
    }

    public Long getIncidentEndTime() {
        return incidentEndTime;
    }

    public void setIncidentEndTime(Long incidentEndTime) {
        this.incidentEndTime = incidentEndTime;
    }

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public String getSquadronOrganizationId() {
        return squadronOrganizationId;
    }

    public void setSquadronOrganizationId(String squadronOrganizationId) {
        this.squadronOrganizationId = squadronOrganizationId;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAlarmModeCode() {
        return alarmModeCode;
    }

    public void setAlarmModeCode(String alarmModeCode) {
        this.alarmModeCode = alarmModeCode;
    }

    public Long getRegisterIncidentTime() {
        return registerIncidentTime;
    }

    public void setRegisterIncidentTime(Long registerIncidentTime) {
        this.registerIncidentTime = registerIncidentTime;
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

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getIncidentNatureCode() {
        return incidentNatureCode;
    }

    public void setIncidentNatureCode(String incidentNatureCode) {
        this.incidentNatureCode = incidentNatureCode;
    }

    public Integer getWhetherImportSign() {
        return whetherImportSign;
    }

    public void setWhetherImportSign(Integer whetherImportSign) {
        this.whetherImportSign = whetherImportSign;
    }

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getSupplementInfo() {
        return supplementInfo;
    }

    public void setSupplementInfo(String supplementInfo) {
        this.supplementInfo = supplementInfo;
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions;
    }

    public String getIncidentDescribe() {
        return incidentDescribe;
    }

    public void setIncidentDescribe(String incidentDescribe) {
        this.incidentDescribe = incidentDescribe;
    }

    public Integer getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(Integer mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    public String getRelationIncidentNumber() {
        return relationIncidentNumber;
    }

    public void setRelationIncidentNumber(String relationIncidentNumber) {
        this.relationIncidentNumber = relationIncidentNumber;
    }

    public String getRetentionIncidentNumber() {
        return retentionIncidentNumber;
    }

    public void setRetentionIncidentNumber(String retentionIncidentNumber) {
        this.retentionIncidentNumber = retentionIncidentNumber;
    }

    public String getRegisterOrganizationId() {
        return registerOrganizationId;
    }

    public void setRegisterOrganizationId(String registerOrganizationId) {
        this.registerOrganizationId = registerOrganizationId;
    }

    public String getRegisterSeatNumber() {
        return registerSeatNumber;
    }

    public void setRegisterSeatNumber(String registerSeatNumber) {
        this.registerSeatNumber = registerSeatNumber;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getAcceptancePersonName() {
        return acceptancePersonName;
    }

    public void setAcceptancePersonName(String acceptancePersonName) {
        this.acceptancePersonName = acceptancePersonName;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getDispatchOrganization() {
        return dispatchOrganization;
    }

    public void setDispatchOrganization(String dispatchOrganization) {
        this.dispatchOrganization = dispatchOrganization;
    }

    public String getDispatchVehicle() {
        return dispatchVehicle;
    }

    public void setDispatchVehicle(String dispatchVehicle) {
        this.dispatchVehicle = dispatchVehicle;
    }

    public String getIncidentLabel() {
        return incidentLabel;
    }

    public void setIncidentLabel(String incidentLabel) {
        this.incidentLabel = incidentLabel;
    }

    public String getBuildingStructureCode() {
        return buildingStructureCode;
    }

    public void setBuildingStructureCode(String buildingStructureCode) {
        this.buildingStructureCode = buildingStructureCode;
    }

    public String getSmogSituationCode() {
        return smogSituationCode;
    }

    public void setSmogSituationCode(String smogSituationCode) {
        this.smogSituationCode = smogSituationCode;
    }

    public String getTrappedCount() {
        return trappedCount;
    }

    public void setTrappedCount(String trappedCount) {
        this.trappedCount = trappedCount;
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

    public String getSecurityContactPerson() {
        return securityContactPerson;
    }

    public void setSecurityContactPerson(String securityContactPerson) {
        this.securityContactPerson = securityContactPerson;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public Integer getWhetherSensitiveArea() {
        return whetherSensitiveArea;
    }

    public void setWhetherSensitiveArea(Integer whetherSensitiveArea) {
        this.whetherSensitiveArea = whetherSensitiveArea;
    }

    public Integer getWhetherWaterShortageArea() {
        return whetherWaterShortageArea;
    }

    public void setWhetherWaterShortageArea(Integer whetherWaterShortageArea) {
        this.whetherWaterShortageArea = whetherWaterShortageArea;
    }

    public Integer getWhetherSensitiveTime() {
        return whetherSensitiveTime;
    }

    public void setWhetherSensitiveTime(Integer whetherSensitiveTime) {
        this.whetherSensitiveTime = whetherSensitiveTime;
    }

    public Long getTransmitTime() {
        return transmitTime;
    }

    public void setTransmitTime(Long transmitTime) {
        this.transmitTime = transmitTime;
    }

    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
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

    public Long getFightStartTime() {
        return fightStartTime;
    }

    public void setFightStartTime(Long fightStartTime) {
        this.fightStartTime = fightStartTime;
    }

    public Long getFightEdnTime() {
        return fightEdnTime;
    }

    public void setFightEdnTime(Long fightEdnTime) {
        this.fightEdnTime = fightEdnTime;
    }

    public Long getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Long returnTime) {
        this.returnTime = returnTime;
    }

    public Long getHalfwayReturnTime() {
        return halfwayReturnTime;
    }

    public void setHalfwayReturnTime(Long halfwayReturnTime) {
        this.halfwayReturnTime = halfwayReturnTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getWhetherMainMerge() {
        return whetherMainMerge;
    }

    public void setWhetherMainMerge(Integer whetherMainMerge) {
        this.whetherMainMerge = whetherMainMerge;
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

    public Long getSecurityStartTime() {
        return securityStartTime;
    }

    public void setSecurityStartTime(Long securityStartTime) {
        this.securityStartTime = securityStartTime;
    }

    public Long getSecurityEndTime() {
        return securityEndTime;
    }

    public void setSecurityEndTime(Long securityEndTime) {
        this.securityEndTime = securityEndTime;
    }

    public Integer getWhetherTestSign() {
        return whetherTestSign;
    }

    public void setWhetherTestSign(Integer whetherTestSign) {
        this.whetherTestSign = whetherTestSign;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyUnit() {
        return keyUnit;
    }

    public void setKeyUnit(String keyUnit) {
        this.keyUnit = keyUnit;
    }

    public String getKeyUnitTypeCode() {
        return keyUnitTypeCode;
    }

    public void setKeyUnitTypeCode(String keyUnitTypeCode) {
        this.keyUnitTypeCode = keyUnitTypeCode;
    }

    public Long getFightTime() {
        return fightTime;
    }

    public void setFightTime(Long fightTime) {
        this.fightTime = fightTime;
    }

    public Long getFireControlTime() {
        return fireControlTime;
    }

    public void setFireControlTime(Long fireControlTime) {
        this.fireControlTime = fireControlTime;
    }

    public Long getExtinguishTime() {
        return extinguishTime;
    }

    public void setExtinguishTime(Long extinguishTime) {
        this.extinguishTime = extinguishTime;
    }
}
