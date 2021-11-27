package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 装备明细账
 *
 */
@Entity
@Table(name = "WL_ZBMXZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EquipmentEntity extends BaseEntity {

    @Column(name = "XFZB_TYWYSBM", length = 32)
    private String idCode; //todo 字段 消防装备_通用唯一识别码

    @Column(name = "ZBQCBM", length = 100 )
    private String  equipmentCode;//装备器材编码

//    @Column(name = "ZBQCMC", length = 200 )
    @Column(name = "XFZB_MC", length = 200 )
    private String  equipmentName;//todo 字段 装备器材名称

//    @Column(name = "ZBLXDM", length = 100 )
    @Column(name = "XFZBLXDM", length = 100 )
    private String  equipmentTypeCode;//todo 装备类型编码 消防装备器材分类与代码

//    @Column(name = "SSXFJGID", length = 100)
    @Column(name = "XFJYJG_TYWYSBM", length = 100)
    private String organizationId;//todo 字段  所属消防机构ID 消防救援机构_通用唯一识别码

//    @Column(name = "SSXFJGMC", length = 200 )
    @Column(name = "XFJYJG_TYWYSMC", length = 200 )
    private String organizationName;//todo 字段  所属消防机构名称

    @Column(name = "GGXH", length = 100)
    private String specificationsNumber;// 规格型号

//    @Column(name = "JE" )
    @Column(name = "CKJ_JE" )
    private Float sumMoney ;//todo 字段  参考价_金额

    @Column(name = "PP_MC" , length = 200 )
    private String brandName ;//todo 字段  品牌_名称

    @Column(name = "SCCJ_DWMC", length = 200)
    private String manufacturerName;// todo 字段 生产厂家名称

    @Column(name = "ZRR", length = 100)
    private String chargePerson;// todo 字段 责任人

    @Column(name = "ZRR_XM", length = 200)
    private String chargePersonName;// todo 字段 责任人姓名

    @Column(name = "ZRR_LXDH", length = 50)
    private String chargePersonPhone;// todo 字段 责任人联系电话

    @Column(name = "ZBRK_RQ")
    private Long warehousingTime;//todo 字段 装备入库_日期

//    @Column(name = "YXQZ")
    @Column(name = "ZBSX_RQ")
    private Long effectiveTime;//todo 字段 有效期至 装备失效_日期

    @Column(name = "PCH", length = 100)
    private String batchNo;// 批次号

//    @Column(name = "JLDWDM", length = 100)
    @Column(name = "JLDLDWDM", length = 100)
    private String measurementCode;//todo 字段  计量单位代码 计量度量单位代码

//    @Column(name = "SFXHZB"  )
    @Column(name = "SFXHXZB_PDBZ"  )
    private Integer  whetherConsumptiveEquipment;//todo 字段 是否属于消耗器材 0 非消耗器材  1消耗器材 是否消耗型装备_判断标识

    @Column(name = "ZL"  )
    private Float   weight;//todo 字段    重量 单位：千克

    @Column(name = "TJ"  )
    private Float  volume;//todo 字段 体积 单位：立方米

    @Column(name = "ZYCF_JYQK" ,length =  800 )
    private String  mainComponents ;//todo 字段 主要成分_简要情况

    @Column(name = "SYFW_JYQK" ,length =  800 )
    private String  scopeApplication;//todo 字段 适用范围_简要情况

    @Column(name = "ZBXNZB_JYQK" ,length =  800 )
    private String  equipmentPerformance;//todo 字段 装备性能指标_简要情况

//    @Column(name = "ZBZTDM", length = 100)
    @Column(name = "XFZBZTLBDM", length = 100)
    private String equipmentStatusCode;//todo 字段 消防装备状态类别代码

    @Column(name = "XFZB_JYQK", length = 100)
    private String equipmentDesc;//todo 字段消防装备_简要情况

    @Column(name = "SHFW_DWMC", length = 200)
    private String afterService ;//todo 字段 售后服务_单位名称

    @Column(name = "SFZP_PDBZ" )
    private Integer whetherAssembling;// todo 字段 是否装配_判断标识

    @Column(name = "SFYYXL_PDBZ" )
    private Integer whetherTraining;//todo 字段 是否用于训练_判断标识

    @Column(name = "ZB_SL" )
    private Float equipNum;//todo 字段 装备_数量

//    @Column(name = "KCSL" )
    @Column(name = "KC_SL" )
    private Float stockNum;//todo 字段 库存_数量

//    @Column(name = "LYSL" )
    @Column(name = "LY_SL" )
    private Float collectingNum;//todo 字段 领用_数量

//    @Column(name = "ZZSL" )
    @Column(name = "ZZ_SL" )
    private Float loadNum;//todo 字段 装载_数量

//    @Column(name = "WXSL" )
    @Column(name = "WX_SL" )
    private Float repairNum;//todo 字段 维修_数量

//    @Column(name = "ZTSL" )
    @Column(name = "ZT_SL" )
    private Float onWayNum;// todo 字段 在途_数量


    @Column(name = "DJZBID", length = 100)
    private String singleEquipmentId;// 单件装备ID 车辆存在

    @Column(name = "DJZBBM", length = 100)
    private String singleEquipmentCode;// 单件装备编码 车辆存在

    @Column(name = "CPH", length = 50)
    private String vehicleNumber;// 车牌号

    @Column(name = "SFKZZ" )
    private Integer whetherFight;//是否可作战

    @Column(name = "SFSC" )
    private Integer whetherInCar;//是否上车


    @Column(name = "CKID", length = 100 )
    private String  warehouseId;//仓库ID

    @Column(name = "CKMC", length = 200 )
    private String  warehouseName;//仓库名称

    @Column(name = "HWID", length = 100 )
    private String  locationId;//货位ID

    @Column(name = "HWMC", length = 200 )
    private String  locationName;//货位名称


    @Column(name = "DJ" )
    private Float unitPrice;// 单价

    @Column(name = "YMXZID" , length = 100)
    private String originalSubLedgerId;// 原明细账ID

    @Column(name = "TZJLID" , length = 100)
    private String standingBookId;// 台账记录ID

    @Column(name = "ZZDJZBID" , length = 100)
    private String loadSingleEquipmentId;// 装载单件装备ID 车辆id

    @Column(name = "ZZDJZBBM" , length = 100)
    private String loadSingleEquipmentCode;// 装载单件装备编码 车辆编码

    @Column(name = "BYSJ"  )
    private Long maintenanceTime;// 保养时间

    @Column(name = "JCSJ"  )
    private Long checkTime;// 检查时间

    @Column(name = "JZSJ"  )
    private Long buildAccountTime;// 建账时间

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


    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public Integer getWhetherConsumptiveEquipment() {
        return whetherConsumptiveEquipment;
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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public Float getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Float sumMoney) {
        this.sumMoney = sumMoney;
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

    public Float getEquipNum() {
        return equipNum;
    }

    public void setEquipNum(Float equipNum) {
        this.equipNum = equipNum;
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
}
