package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.accept.graphql.firetypebean.CommanderBean;
import com.dscomm.iecs.base.service.bean.BaseBean;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitBean;

import java.util.List;


/**
 * 描述:案件详情信息
 *
 */
public class IncidentBean extends BaseBean {

    private String idCode; // 字段  警情_通用唯一识别码

    /**  start 警情电话信息 */

    private String alarmPhone;//报警电话

    private String alarmPersonName;//报警人姓名

    private String alarmPersonSex;//报警人性别

    private String alarmPersonSexName;//报警人性别

    private String alarmPersonPhone;//报警人联系电话

    private String alarmAddress;//报警地址

    private Long alarmStartTime;  //报警开始时间（振铃时间）

    private Long receiveStartTime;  //接警开始时间（接听时间）

    private Long alarmEndTime;  //报警结束时间（挂机时间）

    private Long receiveEndTime;  //接警结束时间（立案时间）

    private String relayRecordNumber;//录音号（案件主案件录音号）

    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    /**  end 警情电话信息 */

    private String incidentSource;  //案件来源

    private String incidentNumber; // 案件编号（日期坐席工号）

    private String acceptanceId; // 受理单Id

    private String squadronOrganizationId;// 主管中队

    private String squadronOrganizationName;// 主管中队名称

    private String brigadeOrganizationId; // 所属大队

    private String brigadeOrganizationName; // 所属大队名称

    private String districtCode;//  行政区编码

    private String districtName;//  行政区名称

    private String alarmModeCode;  //报警方式代码

    private String alarmModeName;  //报警方式名称

    private String registerModeCode;// 立案方式代码

    private String registerModeName;// 立案方式名称

    private  Long registerIncidentTime;// 立案时间

    private String title; //   名称

    private String crimeAddress;// 案发地址

    private String longitude;// 经度

    private String latitude;// 纬度

    private String height;// 高度

    private String incidentTypeCode;// 案件类型代码

    private String incidentTypeName;// 案件类型名称

    private String incidentTypeSubitemCode;// 案件类型子项代码（细类）（案件类型备用）

    private String incidentTypeSubitemName;// 案件类型子项名称（细类）（案件类型备用）

    private String incidentLevelCode;// 案件等级代码

    private String incidentLevelName;// 案件等级名称

    private String disposalObjectCode;// 处置对象代码

    private String disposalObjectName;// 处置对象名称

    private String incidentStatusCode;// 案件状态代码

    private String incidentStatusName;// 案件状态名称

    private String incidentNatureCode;// 案件性质代码

    private String incidentNatureName;// 案件性质名称

    private Integer whetherImportSign;// 是否重大案件标识

    private String keyUnit;// 重点单位

    private String keyUnitId;// 重点单位ID

    private String keyUnitName;// 重点单位名称

    private String keyUnitTypeCode ;// 重点单位 警情对象类别代码

    private String keyUnitTypeName ;// 重点单位  警情对象类别名称

    private KeyUnitBean keyUnitBean  ; //重点单位信息

    private String supplementInfo;// 补充信息

    private String attentions;// 注意事项

    private String incidentDescribe;//案件描述

    private Integer mergeStatus;// 合并状态

    private Integer whetherMainMerge ;// 是否为合并主案件

    private String registerOrganizationId; // 立案机构ID

    private String registerOrganizationName; // 立案机构名称

    private String registerSeatNumber; // 立案接警坐席号

    private String acceptancePersonNumber;// 接警员工号

    private String acceptancePersonName;// 接警员姓名

    private String handleType; // 处理类型

    private String handleTypeName; // 处理类型名称

    private String dispatchOrganization; //出动机构

    private String dispatchVehicle; // 出动车辆

    private String incidentLabel; // 警情标签

    private String incidentLabelName; // todo 字段 警情标签

    private String buildingStructureCode;// 建筑结构代码

    private String buildingStructureName;// 建筑结构名称

    private String smogSituationCode;// 烟雾情况代码

    private String smogSituationName;// 烟雾情况名称

    private String trappedCount;//被困人数

    private Integer storeysOfBuildings;// 楼房层数

    private String burningFloor;// 燃烧楼层 救援楼层

    private String burningArea;// 燃烧面积

    private String disasterSites;//灾害场所

    private String disasterSitesName;//灾害场所名称

    private Integer whetherSensitiveArea;//是否敏感地区

    private Integer whetherWaterShortageArea;//是否缺水地区

    private Integer whetherSensitiveTime;//是否敏感时间

    private String securityContactPerson;//安保联系人

    private String contactPersonPhone;//安保联系人电话

    private Long transmitTime;// 下达时间

    private Long receiveTime;//接收时间

    private Long dispatchTime;// 出动时间

    private Long arriveTime;// 到达时间

    private Long fightTime;//  战斗展开时间

    private Long fightStartTime;// 作战开始时间

    private Long fireControlTime ;//  火势控制时间

    private Long extinguishTime ;//  基本扑灭时间

    private Long fightEdnTime;// 作战结束时间

    private Long returnTime;// 归队时间

    private Long halfwayReturnTime;//中返时间

    private String remarks;//备注

    private Integer whetherFocusOn = 0 ;//是否关注 0不关注 1 关注 默认 0 不关注

    private String injuredCount;//受伤人数

    private String deathCount;//死亡人数

    private Long securityStartTime;//安保开始时间

    private Long securityEndTime;//安保结束时间

    private Integer whetherTestSign;//是否测试警情 0非测试警情  1测试警情

    private Long placeFileIncidentTime;//案件归档时间

    private Long incidentEndTime;//案件结案时间

    private Integer whetherImportant  = 0 ;//重要警情标识 0非重要 1重要 默认0非重要

    private String incidentGroupId;//案件群组id 如果有多个群组 多个群组逗号分隔

    /**  start 出动力量数量 */

    private String handleBatchNum;  //调派次数

    private String handleOrganizationNum;//出动单位数
    private String handleOrganization;//出动单位 id列表 逗号分隔

    private String handleVehicleNum ; //出动车辆数

    private String handlePersonNum ; //出动人员数

    private String totalWater;//水量（总量）
    private String totalFoam;//泡沫总量

    /**  end  出动力量数量 */

    private String receiveTips;	//接警提示

    private String handleTips;//处警提示

    private String safetyTips;//安全提示

    private Integer attachmentNum = 0  ; //附件数量

    private List<SoundRecordBean> soundRecordList ; //录音文件信息

    private List<AttachmentBean> attachmentList ; //附件信息

    private List<HandleOrganizationVehicleBean> handleOrganizationVehicleList ; //案件出动车辆信息

    private List<HandleShowBean> handleShowBeanList ; //案件处警信息统计

    private List<CommanderBean> commanderBeans; // 现场指挥人

    private List<LocationRecordBean> locationRecordBeans;//定位记录

    private String originalIncidentNumber ;  //第三方原始案件编号  警情是从其他系统转入时存在

    private String uniformAddressId;//统一地址库id

    public String getUniformAddressId() {
        return uniformAddressId;
    }

    public void setUniformAddressId(String uniformAddressId) {
        this.uniformAddressId = uniformAddressId;
    }

    public String getHandleOrganization() {
        return handleOrganization;
    }

    public void setHandleOrganization(String handleOrganization) {
        this.handleOrganization = handleOrganization;
    }

    public String getReceiveTips() {
        return receiveTips;
    }

    public void setReceiveTips(String receiveTips) {
        this.receiveTips = receiveTips;
    }

    public String getHandleTips() {
        return handleTips;
    }

    public void setHandleTips(String handleTips) {
        this.handleTips = handleTips;
    }

    public String getSafetyTips() {
        return safetyTips;
    }

    public void setSafetyTips(String safetyTips) {
        this.safetyTips = safetyTips;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getIncidentLabelName() {
        return incidentLabelName;
    }

    public void setIncidentLabelName(String incidentLabelName) {
        this.incidentLabelName = incidentLabelName;
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

    public String getSquadronOrganizationName() {
        return squadronOrganizationName;
    }

    public void setSquadronOrganizationName(String squadronOrganizationName) {
        this.squadronOrganizationName = squadronOrganizationName;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }

    public String getBrigadeOrganizationName() {
        return brigadeOrganizationName;
    }

    public void setBrigadeOrganizationName(String brigadeOrganizationName) {
        this.brigadeOrganizationName = brigadeOrganizationName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAlarmModeCode() {
        return alarmModeCode;
    }

    public void setAlarmModeCode(String alarmModeCode) {
        this.alarmModeCode = alarmModeCode;
    }

    public String getAlarmModeName() {
        return alarmModeName;
    }

    public void setAlarmModeName(String alarmModeName) {
        this.alarmModeName = alarmModeName;
    }

    public String getRegisterModeCode() {
        return registerModeCode;
    }

    public void setRegisterModeCode(String registerModeCode) {
        this.registerModeCode = registerModeCode;
    }

    public String getRegisterModeName() {
        return registerModeName;
    }

    public void setRegisterModeName(String registerModeName) {
        this.registerModeName = registerModeName;
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

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getIncidentTypeSubitemCode() {
        return incidentTypeSubitemCode;
    }

    public void setIncidentTypeSubitemCode(String incidentTypeSubitemCode) {
        this.incidentTypeSubitemCode = incidentTypeSubitemCode;
    }

    public String getIncidentTypeSubitemName() {
        return incidentTypeSubitemName;
    }

    public void setIncidentTypeSubitemName(String incidentTypeSubitemName) {
        this.incidentTypeSubitemName = incidentTypeSubitemName;
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

    public String getDisposalObjectCode() {
        return disposalObjectCode;
    }

    public void setDisposalObjectCode(String disposalObjectCode) {
        this.disposalObjectCode = disposalObjectCode;
    }

    public String getDisposalObjectName() {
        return disposalObjectName;
    }

    public void setDisposalObjectName(String disposalObjectName) {
        this.disposalObjectName = disposalObjectName;
    }

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getIncidentStatusName() {
        return incidentStatusName;
    }

    public void setIncidentStatusName(String incidentStatusName) {
        this.incidentStatusName = incidentStatusName;
    }

    public String getIncidentNatureCode() {
        return incidentNatureCode;
    }

    public void setIncidentNatureCode(String incidentNatureCode) {
        this.incidentNatureCode = incidentNatureCode;
    }

    public String getIncidentNatureName() {
        return incidentNatureName;
    }

    public void setIncidentNatureName(String incidentNatureName) {
        this.incidentNatureName = incidentNatureName;
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

    public String getKeyUnitName() {
        return keyUnitName;
    }

    public void setKeyUnitName(String keyUnitName) {
        this.keyUnitName = keyUnitName;
    }

    public KeyUnitBean getKeyUnitBean() {
        return keyUnitBean;
    }

    public void setKeyUnitBean(KeyUnitBean keyUnitBean) {
        this.keyUnitBean = keyUnitBean;
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

    public Integer getWhetherMainMerge() {
        return whetherMainMerge;
    }

    public void setWhetherMainMerge(Integer whetherMainMerge) {
        this.whetherMainMerge = whetherMainMerge;
    }


    public String getRegisterOrganizationId() {
        return registerOrganizationId;
    }

    public void setRegisterOrganizationId(String registerOrganizationId) {
        this.registerOrganizationId = registerOrganizationId;
    }

    public String getRegisterOrganizationName() {
        return registerOrganizationName;
    }

    public void setRegisterOrganizationName(String registerOrganizationName) {
        this.registerOrganizationName = registerOrganizationName;
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

    public String getHandleTypeName() {
        return handleTypeName;
    }

    public void setHandleTypeName(String handleTypeName) {
        this.handleTypeName = handleTypeName;
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

    public String getBuildingStructureName() {
        return buildingStructureName;
    }

    public void setBuildingStructureName(String buildingStructureName) {
        this.buildingStructureName = buildingStructureName;
    }

    public String getSmogSituationCode() {
        return smogSituationCode;
    }

    public void setSmogSituationCode(String smogSituationCode) {
        this.smogSituationCode = smogSituationCode;
    }

    public String getSmogSituationName() {
        return smogSituationName;
    }

    public void setSmogSituationName(String smogSituationName) {
        this.smogSituationName = smogSituationName;
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

    public String getDisasterSitesName() {
        return disasterSitesName;
    }

    public void setDisasterSitesName(String disasterSitesName) {
        this.disasterSitesName = disasterSitesName;
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

    public Integer getWhetherFocusOn() {
        return whetherFocusOn;
    }

    public void setWhetherFocusOn(Integer whetherFocusOn) {
        this.whetherFocusOn = whetherFocusOn;
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

    public Integer getWhetherImportant() {
        return whetherImportant;
    }

    public void setWhetherImportant(Integer whetherImportant) {
        this.whetherImportant = whetherImportant;
    }

    public String getIncidentGroupId() {
        return incidentGroupId;
    }

    public void setIncidentGroupId(String incidentGroupId) {
        this.incidentGroupId = incidentGroupId;
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

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getHandleBatchNum() {
        return handleBatchNum;
    }

    public void setHandleBatchNum(String handleBatchNum) {
        this.handleBatchNum = handleBatchNum;
    }

    public String getHandleOrganizationNum() {
        return handleOrganizationNum;
    }

    public void setHandleOrganizationNum(String handleOrganizationNum) {
        this.handleOrganizationNum = handleOrganizationNum;
    }

    public String getHandleVehicleNum() {
        return handleVehicleNum;
    }

    public void setHandleVehicleNum(String handleVehicleNum) {
        this.handleVehicleNum = handleVehicleNum;
    }

    public String getHandlePersonNum() {
        return handlePersonNum;
    }

    public void setHandlePersonNum(String handlePersonNum) {
        this.handlePersonNum = handlePersonNum;
    }

    public List<SoundRecordBean> getSoundRecordList() {
        return soundRecordList;
    }

    public void setSoundRecordList(List<SoundRecordBean> soundRecordList) {
        this.soundRecordList = soundRecordList;
    }

    public Integer getAttachmentNum() {
        return attachmentNum;
    }

    public void setAttachmentNum(Integer attachmentNum) {
        this.attachmentNum = attachmentNum;
    }

    public List<AttachmentBean> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentBean> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<HandleOrganizationVehicleBean> getHandleOrganizationVehicleList() {
        return handleOrganizationVehicleList;
    }

    public void setHandleOrganizationVehicleList(List<HandleOrganizationVehicleBean> handleOrganizationVehicleList) {
        this.handleOrganizationVehicleList = handleOrganizationVehicleList;
    }

    public List<HandleShowBean> getHandleShowBeanList() {
        return handleShowBeanList;
    }

    public void setHandleShowBeanList(List<HandleShowBean> handleShowBeanList) {
        this.handleShowBeanList = handleShowBeanList;
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

    public String getAlarmPersonSexName() {
        return alarmPersonSexName;
    }

    public void setAlarmPersonSexName(String alarmPersonSexName) {
        this.alarmPersonSexName = alarmPersonSexName;
    }

    public String getIncidentSource() {
        return incidentSource;
    }

    public void setIncidentSource(String incidentSource) {
        this.incidentSource = incidentSource;
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

    public String getKeyUnitTypeName() {
        return keyUnitTypeName;
    }

    public void setKeyUnitTypeName(String keyUnitTypeName) {
        this.keyUnitTypeName = keyUnitTypeName;
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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public List<CommanderBean> getCommanderBeans() {
        return commanderBeans;
    }

    public void setCommanderBeans(List<CommanderBean> commanderBeans) {
        this.commanderBeans = commanderBeans;
    }

    public List<LocationRecordBean> getLocationRecordBeans() {
        return locationRecordBeans;
    }

    public void setLocationRecordBeans(List<LocationRecordBean> locationRecordBeans) {
        this.locationRecordBeans = locationRecordBeans;
    }

    public String getTotalWater() {
        return totalWater;
    }

    public void setTotalWater(String totalWater) {
        this.totalWater = totalWater;
    }

    public String getTotalFoam() {
        return totalFoam;
    }

    public void setTotalFoam(String totalFoam) {
        this.totalFoam = totalFoam;
    }
}
