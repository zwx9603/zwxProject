package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:受理单（报警记录）
 *
 */
@Entity
@Table(name = "JCJ_SLD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AcceptanceEntity extends BaseEntity {

    @Column(name = "BJ_TYWYSBM", length = 40)
    private String idCode; //todo  字段 报警_通用唯一识别码

//    @Column(name = "DHBJID", length = 40)
    @Column(name = "THJL_TYWYSBM", length = 100)
    private String telephoneId ; //todo  字段 电话报警id  通话记录_通用唯一识别码

    @Column(name = "RQSJ")
    private Long  acceptanceTime ; //todo  字段  受理单时间

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

    @Column(name = "BJKSSJ"  )
    private Long alarmStartTime;  //报警开始时间（振铃时间）

    @Column(name = "JJKSSJ"  )
    private Long receiveStartTime;  //接警开始时间（接听时间）

    @Column(name = "BJJSSJ"  )
    private Long alarmEndTime;  //报警结束时间（挂机时间）

    @Column(name = "JJJSSJ"  )
    private Long receiveEndTime;  //接警结束时间（立案时间）

    @Column(name = "JJSC"  )
    private Long receiveDuration;  //接警时长

    @Column(name = "LYH", length = 400 )
    private String relayRecordNumber;//录音号

    @Column(name = "SFBK"  )
    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    /**  end受理电话信息 */

    @Column(name = "AJLY", length = 100 )
    private String incidentSource;  //案件来源

    @Column(name = "AJID", length = 100 )
    private String incidentId;  //案件ID

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //原始案件编号

//    @Column(name = "BJFSDM", length = 100 )
    @Column(name = "BJFSLBDM", length = 100 )
    private String alarmModeCode;  //todo 字段 报警方式代码

    @Column(name = "ZGJGBH", length = 100)
    private String squadronOrganizationId;// 主管机构ID

    @Column(name = "SSDD", length = 100)
    private String brigadeOrganizationId; // 所属大队

//    @Column(name = "XZQYBH", length = 100 )
    @Column(name = "XZQHDM", length = 100 )
    private String districtCode;//  todo 字段 行政区编码

    @Column(name = "LAFS", length = 100)
    private String registerModeCode;// 立案方式代码

    @Column(name = "LASJ")
    private  Long registerIncidentTime;// 立案时间

    @Column(name = "MC", length = 200 )
    private String title; //todo 字段 属性  名称

//    @Column(name = "AFDZ", length = 400)
    @Column(name = "DZMC", length = 400)
    private String crimeAddress;// todo 字段 案发地址

//    @Column(name = "GIS_X", length = 50)
    @Column(name = "DQJD", length = 50)
    private String longitude; //todo 字段 经度

//    @Column(name = "GIS_Y", length = 50)
    @Column(name = "DQWD", length = 50)
    private String latitude; //todo 字段 纬度

//    @Column(name = "GIS_H", length = 50)
    @Column(name = "DQGD", length = 50)
    private String height;// todo 字段 高度

    @Column(name = "AJLXDM", length = 100)
    private String incidentTypeCode;// 案件类型代码

    @Column(name = "AJLXZX", length = 100)
    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    @Column(name = "AJDJDM", length = 100)
    private String incidentLevelCode;// 案件等级代码

    @Column(name = "CZDXDM", length = 100)
    private String disposalObjectCode;// 处置对象代码

    @Column(name = "AJZTDM", length = 100)
    private String incidentStatusCode;// 案件状态代码

    @Column(name = "SFZDAJ" )
    private Integer whetherImportSign;// 是否重大案件标识

    @Column(name = "JQDX", length = 100)
    private String keyUnit;//todo 字段 重点单位 警情对象

//    @Column(name = "ZDDWID", length = 100 )
    @Column(name = "JQDX_TYWYSBM", length = 100)
    private String keyUnitId;//todo 字段 重点单位  警情对象通用唯一识别码

    @Column(name = "JQDX_JQDXLBDM", length = 100)
    private String keyUnitTypeCode ;//todo 字段 重点单位 警情对象类别代码

    @Column(name = "BCXX", length = 1000)
    private String supplementInfo;// 补充信息

    @Column(name = "ZYSX", length = 1000)
    private String attentions;// 注意事项

    @Column(name = "AJMS", length = 1000)
    private String incidentDescribe;//案件描述

    @Column(name = "LADWID", length = 100)
    private String registerOrganizationId; // 立案机构ID

    @Column(name = "LAJJXH", length = 50)
    private String registerSeatNumber; // 立案接警坐席号

    @Column(name = "XFJYRY", length = 100)
    private String acceptancePerson ;//todo 字段 接警员

    @Column(name = "XFRY_TYWYSBM", length = 100)
    private String acceptancePersonId ;//todo 字段 接警员id

//    @Column(name = "JJYXM", length = 100)
    @Column(name = "XFRY_XM", length = 100)
    private String acceptancePersonName;//todo 字段 接警员姓名

    @Column(name = "JJYGH", length = 100)
    private String acceptancePersonNumber;// 接警员工号

    @Column(name = "CLLX", length = 100)
    private String handleType; // 处理类型

    @Column(name = "JZJG", length = 100)
    private String buildingStructureCode;// 建筑结构代码

    @Column(name = "YWQK", length = 100)
    private String smogSituationCode;// 烟雾情况代码

    @Column(name = "RYBGS" , length = 100)
    private String trappedCount;//被困人数

    @Column(name = "LFCS")
    private Integer storeysOfBuildings;// 楼房层数

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

    @Column(name = "BZ",length = 800)
    private String remarks;//备注

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


//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID")
//    private AcceptanceExtend119Entity  acceptanceExtend119 ;
//
//    public AcceptanceExtend119Entity getAcceptanceExtend119() {
//        return acceptanceExtend119;
//    }
//
//    public void setAcceptanceExtend119(AcceptanceExtend119Entity acceptanceExtend119) {
//        this.acceptanceExtend119 = acceptanceExtend119;
//    }


    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
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

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getAlarmModeCode() {
        return alarmModeCode;
    }

    public void setAlarmModeCode(String alarmModeCode) {
        this.alarmModeCode = alarmModeCode;
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

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
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

    public String getAcceptancePersonName() {
        return acceptancePersonName;
    }

    public void setAcceptancePersonName(String acceptancePersonName) {
        this.acceptancePersonName = acceptancePersonName;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public Long getReceiveDuration() {
        return receiveDuration;
    }

    public void setReceiveDuration(Long receiveDuration) {
        this.receiveDuration = receiveDuration;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

    public Long getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(Long acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public String getAcceptancePerson() {
        return acceptancePerson;
    }

    public void setAcceptancePerson(String acceptancePerson) {
        this.acceptancePerson = acceptancePerson;
    }

    public String getAcceptancePersonId() {
        return acceptancePersonId;
    }

    public void setAcceptancePersonId(String acceptancePersonId) {
        this.acceptancePersonId = acceptancePersonId;
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
}
