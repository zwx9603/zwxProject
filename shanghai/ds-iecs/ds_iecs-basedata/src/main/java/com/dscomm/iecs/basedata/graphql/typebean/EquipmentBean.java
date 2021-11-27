package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 装备明细账
 *
 */
public class EquipmentBean extends BaseBean {

    private String  warehouseId;//仓库ID

    private String  warehouseName;//仓库名称

    private String  locationId;//货位ID

    private String  locationName;//货位名称

    private String  equipmentCode;//装备器材编码

    private String  equipmentName;//装备器材名称

    private String specificationsNumber;// 规格型号

    private String batchNo;// 批次号

    private Long effectiveTime;// 有效期至

    private String singleEquipmentId;// 单件装备ID

    private String singleEquipmentCode;// 单件装备编码

    private String vehicleNumber;// 车牌号

    private String equipmentStatusCode;//装备状态代码

    private String equipmentStatusName;//装备状态名称

    private Integer whetherFight;//是否可作战

    private Integer whetherInCar;//是否上车

    private Float stockNum;//库存数量

    private Float collectingNum;//领用数量

    private Float loadNum;//装载数量

    private Float repairNum;//维修数量

    private Float onWayNum;//在途数量

    private String measurementCode;// 计量单位代码

    private String measurementName;// 计量单位名称

    private Float unitPrice;// 单价

    private Float sumMoney ;// 金额

    private String originalSubLedgerId;// 原明细账ID

    private String standingBookId;// 台账记录ID

    private String loadSingleEquipmentId;// 装载单件装备ID

    private String loadSingleEquipmentCode;// 装载单件装备编码

    private Long maintenanceTime;// 保养时间

    private Long checkTime;// 检查时间

    private Long buildAccountTime;// 建账时间

    private String organizationId;// 所属消防机构ID

    private String organizationName;// 所属消防机构名称

    private String remarks; // 备注

    private String  equipmentTypeCode;//装备类型编码

    private String  equipmentTypeName;//装备类型名称

    private Integer  whetherConsumptiveEquipment;//是否属于消耗器材 0 非消耗器材  1消耗器材


    private Float equipNum;//todo 字段 装备_数量

    private String equipmentDesc;//todo 字段 消防装备_简要情况

    private String afterService ;//todo 字段  售后服务_单位名称

    private Integer whetherAssembling;//todo 字段   是否装配_判断标识

    private Integer whetherTraining;//todo 字段 是否用于训练_判断标识

    private Float   weight;//todo 字段   重量 单位：千克

    private Float  volume;//todo 字段  体积 单位：立方米

    private String  mainComponents ;//todo 字段  主要成分_简要情况

    private String  scopeApplication;//todo 字段 适用范围_简要情况

    private String  equipmentPerformance;//todo 字段  装备性能指标_简要情况

    private String brandName ;//todo 字段   品牌_名称

    private String manufacturerName;//todo 字段   生产厂家名称

    private String chargePerson;//todo 字段   责任人

    private String chargePersonName;//todo 字段   责任人姓名

    private String chargePersonPhone;//todo 字段   责任人联系电话

    private Long warehousingTime;//todo 字段 装备入库_日期

    public Integer getWhetherConsumptiveEquipment() {
        return whetherConsumptiveEquipment;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public void setWhetherConsumptiveEquipment(Integer whetherConsumptiveEquipment) {
        this.whetherConsumptiveEquipment = whetherConsumptiveEquipment;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getSingleEquipmentId() {
        return singleEquipmentId;
    }

    public void setSingleEquipmentId(String singleEquipmentId) {
        this.singleEquipmentId = singleEquipmentId;
    }

    public String getSingleEquipmentCode() {
        return singleEquipmentCode;
    }

    public void setSingleEquipmentCode(String singleEquipmentCode) {
        this.singleEquipmentCode = singleEquipmentCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getEquipmentStatusCode() {
        return equipmentStatusCode;
    }

    public void setEquipmentStatusCode(String equipmentStatusCode) {
        this.equipmentStatusCode = equipmentStatusCode;
    }

    public Integer getWhetherFight() {
        return whetherFight;
    }

    public void setWhetherFight(Integer whetherFight) {
        this.whetherFight = whetherFight;
    }

    public Integer getWhetherInCar() {
        return whetherInCar;
    }

    public void setWhetherInCar(Integer whetherInCar) {
        this.whetherInCar = whetherInCar;
    }

    public String getEquipmentDesc() {
        return equipmentDesc;
    }

    public void setEquipmentDesc(String equipmentDesc) {
        this.equipmentDesc = equipmentDesc;
    }

    public String getAfterService() {
        return afterService;
    }

    public void setAfterService(String afterService) {
        this.afterService = afterService;
    }

    public Integer getWhetherAssembling() {
        return whetherAssembling;
    }

    public void setWhetherAssembling(Integer whetherAssembling) {
        this.whetherAssembling = whetherAssembling;
    }

    public Integer getWhetherTraining() {
        return whetherTraining;
    }

    public void setWhetherTraining(Integer whetherTraining) {
        this.whetherTraining = whetherTraining;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getMainComponents() {
        return mainComponents;
    }

    public void setMainComponents(String mainComponents) {
        this.mainComponents = mainComponents;
    }

    public String getScopeApplication() {
        return scopeApplication;
    }

    public void setScopeApplication(String scopeApplication) {
        this.scopeApplication = scopeApplication;
    }

    public String getEquipmentPerformance() {
        return equipmentPerformance;
    }

    public void setEquipmentPerformance(String equipmentPerformance) {
        this.equipmentPerformance = equipmentPerformance;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getChargePersonName() {
        return chargePersonName;
    }

    public void setChargePersonName(String chargePersonName) {
        this.chargePersonName = chargePersonName;
    }

    public String getChargePersonPhone() {
        return chargePersonPhone;
    }

    public void setChargePersonPhone(String chargePersonPhone) {
        this.chargePersonPhone = chargePersonPhone;
    }

    public Long getWarehousingTime() {
        return warehousingTime;
    }

    public void setWarehousingTime(Long warehousingTime) {
        this.warehousingTime = warehousingTime;
    }

    public Float getStockNum() {
        return stockNum;
    }

    public void setStockNum(Float stockNum) {
        this.stockNum = stockNum;
    }

    public Float getCollectingNum() {
        return collectingNum;
    }

    public void setCollectingNum(Float collectingNum) {
        this.collectingNum = collectingNum;
    }

    public Float getLoadNum() {
        return loadNum;
    }

    public void setLoadNum(Float loadNum) {
        this.loadNum = loadNum;
    }

    public Float getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(Float repairNum) {
        this.repairNum = repairNum;
    }

    public Float getOnWayNum() {
        return onWayNum;
    }

    public void setOnWayNum(Float onWayNum) {
        this.onWayNum = onWayNum;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Float sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getOriginalSubLedgerId() {
        return originalSubLedgerId;
    }

    public void setOriginalSubLedgerId(String originalSubLedgerId) {
        this.originalSubLedgerId = originalSubLedgerId;
    }

    public String getStandingBookId() {
        return standingBookId;
    }

    public void setStandingBookId(String standingBookId) {
        this.standingBookId = standingBookId;
    }

    public String getLoadSingleEquipmentId() {
        return loadSingleEquipmentId;
    }

    public void setLoadSingleEquipmentId(String loadSingleEquipmentId) {
        this.loadSingleEquipmentId = loadSingleEquipmentId;
    }

    public String getLoadSingleEquipmentCode() {
        return loadSingleEquipmentCode;
    }

    public void setLoadSingleEquipmentCode(String loadSingleEquipmentCode) {
        this.loadSingleEquipmentCode = loadSingleEquipmentCode;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public Long getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(Long maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public Long getBuildAccountTime() {
        return buildAccountTime;
    }

    public void setBuildAccountTime(Long buildAccountTime) {
        this.buildAccountTime = buildAccountTime;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEquipmentStatusName() {
        return equipmentStatusName;
    }

    public void setEquipmentStatusName(String equipmentStatusName) {
        this.equipmentStatusName = equipmentStatusName;
    }

    public Float getEquipNum() {
        return equipNum;
    }

    public void setEquipNum(Float equipNum) {
        this.equipNum = equipNum;
    }
}
