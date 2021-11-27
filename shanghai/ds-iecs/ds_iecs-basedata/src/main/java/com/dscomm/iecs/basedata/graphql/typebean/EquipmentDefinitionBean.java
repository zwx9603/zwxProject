package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 装备器材定义信息
 *
 */
public class EquipmentDefinitionBean extends BaseBean {

    private String  equipmentCode;//装备器材编码

    private String  equipmentName;//装备器材名称

    private String  equipmentTypeCode ;//装备器材类型代码

    private String  equipmentTypeName;//装备器材类型名称

    private String  vehicleTypeCode;//车辆类型代码

    private String  vehicleTypeName;//车辆类型名称

    private String  whetherConsumptiveEquipment ;//是否消耗性装备

    private String specificationsNumber;// 规格型号

    private String measurementCode;// 计量单位代码

    private String measurementName;// 计量单位名称

    private Float referencePrice ;// 参考价

    private Float weight ;// 重量

    private Float volume ;// 体积

    private String proportion ;// 比重

    private String checkBatchNo ;// 检测批号

    private String resistanceFire ;// 抗烧时间

    private String mainComponent ;// 主要成分

    private String applicationRange ;// 适用范围

    private String admittanceCheckCategoryCode ;// 准入检验类别代码

    private String agent ;// 进口装备国内代理商

    private String afterServiceUnit ;// 售后服务单位

    private Float maintenanceCycle ;// 保养周期

    private Float checkCycle ;// 检查周期


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

    public String getWhetherConsumptiveEquipment() {
        return whetherConsumptiveEquipment;
    }

    public void setWhetherConsumptiveEquipment(String whetherConsumptiveEquipment) {
        this.whetherConsumptiveEquipment = whetherConsumptiveEquipment;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public Float getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Float referencePrice) {
        this.referencePrice = referencePrice;
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

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getCheckBatchNo() {
        return checkBatchNo;
    }

    public void setCheckBatchNo(String checkBatchNo) {
        this.checkBatchNo = checkBatchNo;
    }

    public String getResistanceFire() {
        return resistanceFire;
    }

    public void setResistanceFire(String resistanceFire) {
        this.resistanceFire = resistanceFire;
    }

    public String getMainComponent() {
        return mainComponent;
    }

    public void setMainComponent(String mainComponent) {
        this.mainComponent = mainComponent;
    }

    public String getApplicationRange() {
        return applicationRange;
    }

    public void setApplicationRange(String applicationRange) {
        this.applicationRange = applicationRange;
    }

    public String getAdmittanceCheckCategoryCode() {
        return admittanceCheckCategoryCode;
    }

    public void setAdmittanceCheckCategoryCode(String admittanceCheckCategoryCode) {
        this.admittanceCheckCategoryCode = admittanceCheckCategoryCode;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAfterServiceUnit() {
        return afterServiceUnit;
    }

    public void setAfterServiceUnit(String afterServiceUnit) {
        this.afterServiceUnit = afterServiceUnit;
    }

    public Float getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(Float maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

    public Float getCheckCycle() {
        return checkCycle;
    }

    public void setCheckCycle(Float checkCycle) {
        this.checkCycle = checkCycle;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }
}
