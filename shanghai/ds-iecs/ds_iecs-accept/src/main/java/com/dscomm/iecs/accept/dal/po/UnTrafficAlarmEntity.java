package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述: 非话务报警
 *
 */
@Entity
@Table(name = "JCJ_FHWBJ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UnTrafficAlarmEntity extends BaseEntity {

    @Column(name = "CLZT")
    private Integer dealState;//处理状态 0未处理 1已发送 2已受理 3已处警 等等

    @Column(name = "BJFS", length = 100 )
    private String alarmModeCode;  //报警方式代码

    @Column(name = "LAFS", length = 100)
    private String registerModeCode;// 立案方式代码

    /**  start 受理电话信息 */

    @Column(name = "ZJHM", length = 50)
    private String alarmPhone;//报警电话

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

    @Column(name = "LYH", length = 400 )
    private String relayRecordNumber;//录音号

    @Column(name = "SFBK"  )
    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    /**  end受理电话信息 */

    @Column(name = "AJLY", length = 100 )
    private String incidentSource;  //案件来源 iecs(119)  cad(110)

    @Column(name = "AJID", length = 100 )
    private String incidentId;  //案件ID

    @Column(name = "ZGJGBH", length = 100)
    private String organizationId;// 主管机构编号

    @Column(name = "XZQYBH", length = 100 )
    private String districtCode;//  行政区域编号

    @Column(name = "BJSJ"  )
    private Long alarmTime;  //报警时间

    @Column(name = "AFDZ", length = 400)
    private String crimeAddress;// 案发地址

    @Column(name = "GIS_X", length = 50)
    private String longitude;// 经度

    @Column(name = "GIS_Y", length = 50)
    private String latitude;// 纬度

    @Column(name = "GIS_H", length = 50)
    private String height;// 高度

    @Column(name = "AJLXDM", length = 100)
    private String incidentTypeCode;// 案件类型代码

    @Column(name = "AJLXZXDM", length = 100)
    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    @Column(name = "AJMS", length = 1000)
    private String incidentDescribe;//案件描述

    @Column(name = "AJDJDM", length = 100)
    private String incidentLevelCode;// 案件等级代码

    @Column(name = "CZDXDM", length = 100)
    private String disposalObjectCode;// 处置对象代码

    @Column(name = "ZDDWID", length = 100 )
    private String keyUnitId;// 重点单位

    @Column(name = "RYBGS" )
    private String trappedCount;//被困人数

    @Column(name = "RYSSS" , length = 100)
    private String injuredCount;//受伤人数

    @Column(name = "RYSWS" , length = 100)
    private String deathCount;//死亡人数

    @Column(name = "JZJGDM", length = 100)
    private String buildingStructureCode;// 建筑结构代码

    @Column(name = "YWQK", length = 100)
    private String smogSituationCode;// 烟雾情况代码

    @Column(name = "LFCS")
    private Integer storeysOfBuildings;// 楼房层数

    @Column(name = "RSLC")
    private String burningFloor;// 燃烧楼层

    @Column(name = "RSMJ" )
    private String burningArea;// 燃烧面积

    @Column(name = "ZHCSDM" , length = 200)
    private String disasterSites;//灾害场所

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //第三方原始案件编号

    @Column(name = "ZJJGBM", length = 100 )
    private String sendOrgCode; //转警机构编码

    public String getSendOrgCode() {
        return sendOrgCode;
    }

    public void setSendOrgCode(String sendOrgCode) {
        this.sendOrgCode = sendOrgCode;
    }

    public String getAlarmModeCode() {
        return alarmModeCode;
    }

    public void setAlarmModeCode(String alarmModeCode) {
        this.alarmModeCode = alarmModeCode;
    }

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
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

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
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

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Long getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
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

    public String getSmogSituationCode() {
        return smogSituationCode;
    }

    public void setSmogSituationCode(String smogSituationCode) {
        this.smogSituationCode = smogSituationCode;
    }

    public String getBuildingStructureCode() {
        return buildingStructureCode;
    }

    public void setBuildingStructureCode(String buildingStructureCode) {
        this.buildingStructureCode = buildingStructureCode;
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

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getTrappedCount() {
        return trappedCount;
    }

    public void setTrappedCount(String trappedCount) {
        this.trappedCount = trappedCount;
    }

    public String getIncidentDescribe() {
        return incidentDescribe;
    }

    public void setIncidentDescribe(String incidentDescribe) {
        this.incidentDescribe = incidentDescribe;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDealState() {
        return dealState;
    }

    public void setDealState(Integer dealState) {
        this.dealState = dealState;
    }
}
