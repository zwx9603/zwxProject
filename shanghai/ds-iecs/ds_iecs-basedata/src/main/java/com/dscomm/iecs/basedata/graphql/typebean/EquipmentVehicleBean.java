package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 * 描述： 车辆信息
 *
 */
public class EquipmentVehicleBean extends BaseBean {

    private String  vehicleCode;//车辆编号

    private String vehicleName;// 装备名称 车辆名称

    private String vehicleShortName;// 车辆简称

    private String vehicleTypeCode;// 装备类型代码 车辆类型代码

    private String vehicleTypeName;// 装备类型代码 车辆类型名称

    private String vehicleLevelCode;// 车辆等级代码

    private String vehicleLevelName;// 车辆等级名称

    private String organizationId;// 所属消防机构ID

    private String organizationName;// 所属消防机构名称

    private String brigadeOrganizationId;// 所属大队 消防机构ID

    private String brigadeOrganizationName;// 所属大队 消防机构名称

    private String vehicleStatusCode;// 车辆状态代码 ( 车辆状态代码_灭火 )

    private String vehicleStatusName;// 车辆状态名称 ( 车辆状态代码_灭火 )

    private String vehicleNumber;// 车牌号码

    private String gpsNumber;// GPS编号

    private String locationNumber;//  定位编号 定位设备 通用唯一识别码

    private String radioCallSign;// 电台呼号

    private Float waterCarrier;//载水量

    private Float carrierBubble;//载泡量

    private Float maxFlow;//最大流量

    private Float minFlow;//最小流量

    private Float maxLiftingHeight;//最大举升高度

    private Float liftingWeight;//起吊重量

    private Float passengersNum;//载人数量

    private Float traction;//牵引力

    private Float heightLimit;//车辆限高

    private Float maxSmokeDischarge;//最大排烟量

    private Float handLiftPump ; // 手抬泵

    private Float fireMonitorFlow ; //消防炮流量

    private Float pumpFlow ; // 泵流量

    private String foam ; //车载泡沫

    private String foamName ; //车载泡沫

    private String equipment ; //车载器材

    private String equipmentName ; //车载器材

    private String incidentNumber;// 案件编号

    private Integer whetherAssembling;//是否装配

    private Integer whetherTraining;// 是否用于训练

    private Integer whetherDetachment;//是否跨支队

    private Integer whetherCorps;//是否跨总队

    private String singleEquipmentCode;// 单件装备编码

    private String equipmentCode;// 装备编码

    private String superEquipmentCode;// 上级装备编码

    private String singleEquipmentStautsCode;// 单件装备状况代码

    private String specificationsNumber;// 规格型号

    private String assetNumber ;// 资产编号

    private String referencePrice ;// 参考价

    private String trademark;// 商标

    private String color;// 颜色

    private String colorName;// 颜色

    private String countryCode;// 国别代码

    private String manufacturerCode;// 生产厂家代码

    private String manufacturerName;// 生产厂家名称

    private String agent ;// 进口装备国内代理商

    private String afterService ;// 售后服务

    private String contacts;//责任人ID

    private String contactsId;// 责任人ID

    private String contactsName;// 责任人姓名

    private String contactsPhone; //  责任人联系电话

    private Long productionTime;// 出厂日期

    private Long equipTime;// 装备日期

    private Long scrapTime;// 报废日期

    private Long effectiveTime;// 有效期至

    private Float cumulativeTransportTime;// 累计运输时间

    private Integer cumulativeUseFrequency;// 累计使用次数

    private Long maintenanceTime;// 本次保养日期

    private Long nextMaintenanceTime;// 下次保养日期

    private String frameNo;// 车架号

    private String engineNumber;// 发动机编号

    private String batchNo;// 批次号

    private String measurementCode;// 计量单位代码

    private String measurementName;// 计量单位名称

    private Integer lockingStatus;// 锁定状态

    private String checkResultStatus;// 检查结果状态

    private String remarks; // 备注

    private List<EquipmentVehicleLoadBean> equipmentVehicleLoadBeanList; //车辆 装载装备 列表


    //车库门号
    private String mappingGroupId;// 车库门编号/房间号

    //车辆顺序
    private Integer vehicleOrderNum = 99 ; // 车辆顺序 默认99

    private String vehicleDesc; // todo 字段   车辆性能指标_简要情况

    private String vehicleIdentification;//todo 字段    车辆标识 0 头车  1 指挥 2 尾车 可拓展 是否第一出动车辆_判断标识

    private String locationId;//todo 字段      定位设备id

    private String locationSIMNumber;// todo 字段       定位设备 SIM卡卡号

    private String vehicleLocation;// todo 字段 车辆位置

    private Float carrierBubbleA;//todo 字段     装载A类泡沫_容积 单位：升

    private Float carrierBubbleB;//todo 字段     装载B类泡沫_容积 单位：吨

    private String vehicleNature;//车辆性质代码 CLXZDM_ZZ(主战)  CLXZDM_ZY（支援）
    private String vehicleNatureName;//车辆性质名称 主战  支援


    public String getFoamName() {
        return foamName;
    }

    public void setFoamName(String foamName) {
        this.foamName = foamName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getVehicleOrderNum() {
        return vehicleOrderNum;
    }

    public void setVehicleOrderNum(Integer vehicleOrderNum) {
        this.vehicleOrderNum = vehicleOrderNum;
    }

    public String getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    public String getMappingGroupId() {
        return mappingGroupId;
    }

    public void setMappingGroupId(String mappingGroupId) {
        this.mappingGroupId = mappingGroupId;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleShortName() {
        return vehicleShortName;
    }

    public void setVehicleShortName(String vehicleShortName) {
        this.vehicleShortName = vehicleShortName;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleLevelCode() {
        return vehicleLevelCode;
    }

    public void setVehicleLevelCode(String vehicleLevelCode) {
        this.vehicleLevelCode = vehicleLevelCode;
    }

    public String getVehicleLevelName() {
        return vehicleLevelName;
    }

    public void setVehicleLevelName(String vehicleLevelName) {
        this.vehicleLevelName = vehicleLevelName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getBrigadeOrganizationId() {
        return brigadeOrganizationId;
    }

    public void setBrigadeOrganizationId(String brigadeOrganizationId) {
        this.brigadeOrganizationId = brigadeOrganizationId;
    }



    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getVehicleStatusName() {
        return vehicleStatusName;
    }

    public void setVehicleStatusName(String vehicleStatusName) {
        this.vehicleStatusName = vehicleStatusName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getGpsNumber() {
        return gpsNumber;
    }

    public void setGpsNumber(String gpsNumber) {
        this.gpsNumber = gpsNumber;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getRadioCallSign() {
        return radioCallSign;
    }

    public void setRadioCallSign(String radioCallSign) {
        this.radioCallSign = radioCallSign;
    }

    public Float getWaterCarrier() {
        return waterCarrier;
    }

    public void setWaterCarrier(Float waterCarrier) {
        this.waterCarrier = waterCarrier;
    }

    public Float getCarrierBubble() {
        return carrierBubble;
    }

    public void setCarrierBubble(Float carrierBubble) {
        this.carrierBubble = carrierBubble;
    }

    public Float getMaxFlow() {
        return maxFlow;
    }

    public void setMaxFlow(Float maxFlow) {
        this.maxFlow = maxFlow;
    }

    public Float getMinFlow() {
        return minFlow;
    }

    public void setMinFlow(Float minFlow) {
        this.minFlow = minFlow;
    }

    public Float getMaxLiftingHeight() {
        return maxLiftingHeight;
    }

    public void setMaxLiftingHeight(Float maxLiftingHeight) {
        this.maxLiftingHeight = maxLiftingHeight;
    }

    public Float getLiftingWeight() {
        return liftingWeight;
    }

    public void setLiftingWeight(Float liftingWeight) {
        this.liftingWeight = liftingWeight;
    }

    public Float getPassengersNum() {
        return passengersNum;
    }

    public void setPassengersNum(Float passengersNum) {
        this.passengersNum = passengersNum;
    }

    public Float getTraction() {
        return traction;
    }

    public void setTraction(Float traction) {
        this.traction = traction;
    }

    public Float getHeightLimit() {
        return heightLimit;
    }

    public void setHeightLimit(Float heightLimit) {
        this.heightLimit = heightLimit;
    }

    public Float getMaxSmokeDischarge() {
        return maxSmokeDischarge;
    }

    public void setMaxSmokeDischarge(Float maxSmokeDischarge) {
        this.maxSmokeDischarge = maxSmokeDischarge;
    }

    public Float getHandLiftPump() {
        return handLiftPump;
    }

    public void setHandLiftPump(Float handLiftPump) {
        this.handLiftPump = handLiftPump;
    }

    public Float getFireMonitorFlow() {
        return fireMonitorFlow;
    }

    public void setFireMonitorFlow(Float fireMonitorFlow) {
        this.fireMonitorFlow = fireMonitorFlow;
    }

    public Float getPumpFlow() {
        return pumpFlow;
    }

    public void setPumpFlow(Float pumpFlow) {
        this.pumpFlow = pumpFlow;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public Integer getWhetherAssembling() {
        return whetherAssembling;
    }

    public void setWhetherAssembling(Integer whetherAssembling) {
        this.whetherAssembling = whetherAssembling;
    }

    public Integer getWhetherDetachment() {
        return whetherDetachment;
    }

    public void setWhetherDetachment(Integer whetherDetachment) {
        this.whetherDetachment = whetherDetachment;
    }

    public Integer getWhetherCorps() {
        return whetherCorps;
    }

    public void setWhetherCorps(Integer whetherCorps) {
        this.whetherCorps = whetherCorps;
    }

    public String getSingleEquipmentCode() {
        return singleEquipmentCode;
    }

    public void setSingleEquipmentCode(String singleEquipmentCode) {
        this.singleEquipmentCode = singleEquipmentCode;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getSuperEquipmentCode() {
        return superEquipmentCode;
    }

    public void setSuperEquipmentCode(String superEquipmentCode) {
        this.superEquipmentCode = superEquipmentCode;
    }

    public String getSingleEquipmentStautsCode() {
        return singleEquipmentStautsCode;
    }

    public void setSingleEquipmentStautsCode(String singleEquipmentStautsCode) {
        this.singleEquipmentStautsCode = singleEquipmentStautsCode;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
        this.referencePrice = referencePrice;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAfterService() {
        return afterService;
    }

    public void setAfterService(String afterService) {
        this.afterService = afterService;
    }

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public Long getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Long productionTime) {
        this.productionTime = productionTime;
    }

    public Long getEquipTime() {
        return equipTime;
    }

    public void setEquipTime(Long equipTime) {
        this.equipTime = equipTime;
    }

    public Long getScrapTime() {
        return scrapTime;
    }

    public void setScrapTime(Long scrapTime) {
        this.scrapTime = scrapTime;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Float getCumulativeTransportTime() {
        return cumulativeTransportTime;
    }

    public void setCumulativeTransportTime(Float cumulativeTransportTime) {
        this.cumulativeTransportTime = cumulativeTransportTime;
    }

    public Integer getCumulativeUseFrequency() {
        return cumulativeUseFrequency;
    }

    public void setCumulativeUseFrequency(Integer cumulativeUseFrequency) {
        this.cumulativeUseFrequency = cumulativeUseFrequency;
    }

    public Long getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(Long maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public Long getNextMaintenanceTime() {
        return nextMaintenanceTime;
    }

    public void setNextMaintenanceTime(Long nextMaintenanceTime) {
        this.nextMaintenanceTime = nextMaintenanceTime;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public Integer getLockingStatus() {
        return lockingStatus;
    }

    public void setLockingStatus(Integer lockingStatus) {
        this.lockingStatus = lockingStatus;
    }

    public String getCheckResultStatus() {
        return checkResultStatus;
    }

    public void setCheckResultStatus(String checkResultStatus) {
        this.checkResultStatus = checkResultStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<EquipmentVehicleLoadBean> getEquipmentVehicleLoadBeanList() {
        return equipmentVehicleLoadBeanList;
    }

    public void setEquipmentVehicleLoadBeanList(List<EquipmentVehicleLoadBean> equipmentVehicleLoadBeanList) {
        this.equipmentVehicleLoadBeanList = equipmentVehicleLoadBeanList;
    }

    public String getFoam() {
        return foam;
    }

    public void setFoam(String foam) {
        this.foam = foam;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getBrigadeOrganizationName() {
        return brigadeOrganizationName;
    }

    public void setBrigadeOrganizationName(String brigadeOrganizationName) {
        this.brigadeOrganizationName = brigadeOrganizationName;
    }

    public Integer getWhetherTraining() {
        return whetherTraining;
    }

    public void setWhetherTraining(Integer whetherTraining) {
        this.whetherTraining = whetherTraining;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getVehicleDesc() {
        return vehicleDesc;
    }

    public void setVehicleDesc(String vehicleDesc) {
        this.vehicleDesc = vehicleDesc;
    }

    public String getVehicleIdentification() {
        return vehicleIdentification;
    }

    public void setVehicleIdentification(String vehicleIdentification) {
        this.vehicleIdentification = vehicleIdentification;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationSIMNumber() {
        return locationSIMNumber;
    }

    public void setLocationSIMNumber(String locationSIMNumber) {
        this.locationSIMNumber = locationSIMNumber;
    }

    public Float getCarrierBubbleA() {
        return carrierBubbleA;
    }

    public void setCarrierBubbleA(Float carrierBubbleA) {
        this.carrierBubbleA = carrierBubbleA;
    }

    public Float getCarrierBubbleB() {
        return carrierBubbleB;
    }

    public void setCarrierBubbleB(Float carrierBubbleB) {
        this.carrierBubbleB = carrierBubbleB;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getVehicleNature() {
        return vehicleNature;
    }

    public void setVehicleNature(String vehicleNature) {
        this.vehicleNature = vehicleNature;
    }

    public String getVehicleNatureName() {
        return vehicleNatureName;
    }

    public void setVehicleNatureName(String vehicleNatureName) {
        this.vehicleNatureName = vehicleNatureName;
    }
}
