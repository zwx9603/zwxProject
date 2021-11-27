package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 车辆信息
 *
 */
@Entity
@Table(name = "WL_CLXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EquipmentVehicleEntity extends BaseEntity {

    @Column(name = "XFCL_TYWYSBM", length = 32)
    private String idCode; //todo 字段 消防车辆_通用唯一识别码

//    @Column(name = "CLBH", length = 100)
    @Column(name = "CLSBDH", length = 100)
    private String  vehicleCode;//todo 字段 车辆编号 车辆识别代号

    @Column(name = "ZBMC", length = 200)
    private String vehicleName;// 装备名称 车辆名称

    @Column(name = "CLJC", length = 100)
    private String vehicleShortName;// 车辆简称

    @Column(name = "CLXNZB_JYQK", length = 2000)
    private String vehicleDesc;//todo 车辆性能指标_简要情况

//    @Column(name = "ZBLXDM", length = 100)
    @Column(name = "XFZBLXDM", length = 100)
    private String vehicleTypeCode;//todo 字段  消防装备器材分类与代码 车辆类型代码

//    @Column(name = "CLDJDM", length = 100)
    @Column(name = "XFCLDJDM", length = 100)
    private String vehicleLevelCode;//todo 字段 车辆等级代码

//    @Column(name = "SSXFJGID", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String organizationId;//todo 字段 所属消防机构ID 消防救援机构_通用唯一识别码

//    @Column(name = "CLZTDM_MH", length = 100)
    @Column(name = "CLQWZTLBDM", length = 100)
    private String vehicleStatusCode;//todo 字段 车辆状态代码 ( 车辆状态代码_灭火 )

//    @Column(name = "CPHM", length = 50)
    @Column(name = "JDCHPHM", length = 50)
    private String vehicleNumber;//todo 字段 车牌号码 机动车号牌号码

    @Column(name = "DWSB", length = 100)
    private String locationId;// todo 字段 定位设备id

//    @Column(name = "DWBH", length = 100)
    @Column(name = "DWSB_TYWYSBM", length = 100)
    private String locationNumber;// todo 字段 定位编号 定位设备 通用唯一识别码

    @Column(name = "DWSB_SIMKKH", length = 100)
    private String locationSIMNumber;//todo 字段    定位设备 SIM卡卡号

    @Column(name = "CLWZ", length = 100)
    private String vehicleLocation;// todo 字段 车辆位置

    @Column(name = "GPSBH", length = 100)
    private String gpsNumber;//   GPS编号

    @Column(name = "SFDYCDCL_TYWYSBM", length = 20)
    private String vehicleIdentification;//todo 字段  车辆标识 0 头车  1 指挥 2 尾车 可拓展 是否第一出动车辆_判断标识

    @Column(name = "DTHH", length = 50)
    private String radioCallSign;// 电台呼号

//    @Column(name = "ZS", length = 100)
    @Column(name = "ZZS_RJ" )
    private Float waterCarrier;//todo 字段 载水量 装载水_容积 单位：吨

    @Column(name = "ZP", length = 100)
    private Float carrierBubble;//载泡量

    @Column(name = "ZZALPM_RJ" )
    private Float carrierBubbleA;//todo 字段 装载A类泡沫_容积 单位：升

    @Column(name = "ZZBLPM_RJ" )
    private Float carrierBubbleB;//todo 字段 装载B类泡沫_容积 单位：吨

    @Column(name = "ZDLL", length = 100)
    private Float maxFlow;//最大流量

    @Column(name = "ZXLL", length = 100)
    private Float minFlow;//最小流量

    @Column(name = "ZDJSGD", length = 100)
    private Float maxLiftingHeight;//最大举升高度

    @Column(name = "QDZL", length = 100)
    private Float liftingWeight;//起吊重量

    @Column(name = "ZRSL" )
    private Float passengersNum;//载人数量

    @Column(name = "QYL", length = 100)
    private Float traction;//牵引力

    @Column(name = "CLXG", length = 100)
    private Float heightLimit;//车辆限高

    @Column(name = "ZDPYL", length = 100)
    private Float maxSmokeDischarge;//最大排烟量


    @Column(name = "STB" )
    private Float handLiftPump ; // 手抬泵

    @Column(name = "BLL" )
    private Float pumpFlow ; // 泵流量

    @Column(name = "XFPLL" )
    private Float fireMonitorFlow ; //消防炮流量

    @Column(name = "CZPM" ,length =  2000 )
    private String foam ; //车载泡沫

    @Column(name = "CZQC",length =  2000 )
    private String equipment ; //车载器材


    @Column(name = "AJBH", length = 100)
    private String incidentNumber;// 案件编号


    @Column(name = "CFDD", length = 100)
    private String vehiclePosition;// todo 字段 车辆存放地址

//    @Column(name = "SFZP" )
    @Column(name = "SFZP_PDBZ" )
    private Integer whetherAssembling;// todo 字段 是否装配_判断标识

//    @Column(name = "SFYYXL" )
    @Column(name = "SFYYXL_PDBZ" )
    private Integer whetherTraining;//todo 字段 是否用于训练_判断标识

    @Column(name = "SFZHDKQY" )
    private Integer whetherDetachment;//是否跨支队

    @Column(name = "SFZDKQY" )
    private Integer whetherCorps;//是否跨总队

    @Column(name = "DJZBBM", length = 100)
    private String singleEquipmentCode;// 单件装备编码

    @Column(name = "ZBBM", length = 100)
    private String equipmentCode;// 装备编码

    @Column(name = "SJZBBM", length = 100)
    private String superEquipmentCode;// 上级装备编码

    @Column(name = "DJZBZKDM", length = 100)
    private String singleEquipmentStautsCode;// 单件装备状况代码

    @Column(name = "GGXH", length = 100)
    private String specificationsNumber;// 规格型号

    @Column(name = "ZCBH", length = 100)
    private String assetNumber ;// 资产编号

//    @Column(name = "CKJ", length = 50 )
    @Column(name = "CKJ_JE", length = 50 )
    private String referencePrice ;//todo 字段 参考价

    @Column(name = "SB", length = 50 )
    private String trademark;// 商标

//    @Column(name = "YS", length = 50)
    @Column(name = "JDCCSYSLBDM", length = 50)
    private String color ;//todo 字段 颜色

    @Column(name = "GBDM", length = 100)
    private String countryCode;// 国别代码

//    @Column(name = "SCCJID", length = 100)
    @Column(name = "SCCJ_DWID", length = 100)
    private String manufacturerCode;// todo 字段  生产厂家代码

//    @Column(name = "SCCJMC", length = 200)
    @Column(name = "SCCJ_DWMC", length = 200)
    private String manufacturerName;// todo 字段 生产厂家名称

    @Column(name = "JKGN", length = 200)
    private String agent ;// 进口装备国内代理商

//    @Column(name = "SHFW", length = 200)
    @Column(name = "SHFW_DWMC", length = 200)
    private String afterService ;//todo 字段 售后服务_单位名称


    @Column(name = "FZR", length = 100)
    private String contacts;//todo 字段 责任人ID

//    @Column(name = "ZRRID", length = 100)
    @Column(name = "FZR_TYWYSBM", length = 100)
    private String contactsId;//todo 字段 责任人ID 通用唯一识别码

//    @Column(name = "ZRRXM", length = 200)
    @Column(name = "FZR_XM", length = 200)
    private String contactsName;// todo 字段 责任人姓名

    @Column(name = "FZR_LXDH", length = 50)
    private String contactsPhone;//todo 字段 责任人联系电话

    @Column(name = "CCRQ")
    private Long productionTime;// 出厂日期

    @Column(name = "ZBRQ")
    private Long equipTime;// 装备日期

    @Column(name = "BFRQ")
    private Long scrapTime;// 报废日期

    @Column(name = "YXQZ")
    private Long effectiveTime;// 有效期至

    @Column(name = "LJYXSJ"  )
    private Float cumulativeTransportTime;// 累计运输时间

    @Column(name = "LJSYCS"  )
    private Integer cumulativeUseFrequency;// 累计使用次数

    @Column(name = "BCBYRQ")
    private Long maintenanceTime;// 本次保养日期

    @Column(name = "XCBYRQ")
    private Long nextMaintenanceTime;// 下次保养日期

    @Column(name = "CJH", length = 100)
    private String frameNo;// 车架号

//    @Column(name = "FDJBH", length = 100)
    @Column(name = "JDCFDJDDJH", length = 100)
    private String engineNumber;// todo 字段 机动车发动机（电动机）号

    @Column(name = "PCH", length = 100)
    private String batchNo;// 批次号

    @Column(name = "PX")
    private Integer vehicleOrderNum ; // 车辆在机构排序

    @Column(name = "JLDWDM", length = 100)
    private String measurementCode;// 计量单位代码

    @Column(name = "SDZT" )
    private Integer lockingStatus;// 锁定状态

    @Column(name = "JCJGZT", length = 100)
    private String checkResultStatus;// 检查结果状态

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本

    @Column(name = "CLXZDM")
    private String vehicleNature;//车辆性质代码 CLXZDM_ZZ(主战)  CLXZDM_ZY（支援）


    public String getVehiclePosition() {
        return vehiclePosition;
    }

    public void setVehiclePosition(String vehiclePosition) {
        this.vehiclePosition = vehiclePosition;
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

    public String getVehicleLevelCode() {
        return vehicleLevelCode;
    }

    public void setVehicleLevelCode(String vehicleLevelCode) {
        this.vehicleLevelCode = vehicleLevelCode;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }


    public String getRadioCallSign() {
        return radioCallSign;
    }

    public void setRadioCallSign(String radioCallSign) {
        this.radioCallSign = radioCallSign;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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



    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
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

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
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

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
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

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }

    public Float getWaterCarrier() {
        return waterCarrier;
    }

    public void setWaterCarrier(Float waterCarrier) {
        this.waterCarrier = waterCarrier;
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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getVehicleDesc() {
        return vehicleDesc;
    }

    public void setVehicleDesc(String vehicleDesc) {
        this.vehicleDesc = vehicleDesc;
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

    public String getVehicleIdentification() {
        return vehicleIdentification;
    }

    public void setVehicleIdentification(String vehicleIdentification) {
        this.vehicleIdentification = vehicleIdentification;
    }

    public Float getCarrierBubble() {
        return carrierBubble;
    }

    public void setCarrierBubble(Float carrierBubble) {
        this.carrierBubble = carrierBubble;
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

    public Integer getWhetherTraining() {
        return whetherTraining;
    }

    public void setWhetherTraining(Integer whetherTraining) {
        this.whetherTraining = whetherTraining;
    }

    public String getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
        this.referencePrice = referencePrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    public Integer getVehicleOrderNum() {
        return vehicleOrderNum;
    }

    public void setVehicleOrderNum(Integer vehicleOrderNum) {
        this.vehicleOrderNum = vehicleOrderNum;
    }

    public String getVehicleNature() {
        return vehicleNature;
    }

    public void setVehicleNature(String vehicleNature) {
        this.vehicleNature = vehicleNature;
    }
}
