package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 车辆装载装备信息
 *
 */
public class EquipmentVehicleLoadBean extends BaseBean {

    private String organizationId;// 所属消防机构ID

    private String organizationName;// 所属消防机构名称

    private String vehicleId;// 车辆id

    private String  singleEquipmentCode;// 单件装备编码

    private String equipmentTypeCode;// 装备类型编码

    private String equipmentTypeName;// 装备类型名称

    private String equipmentCode;// 装备编码

    private String equipmentName;// 装备名称

    private String specificationsNumber  ;// 规格型号

    private String  loadNum;//装载数量

    private String measurementCode;// 计量单位代码

    private String measurementName;// 计量单位名称

    private Integer  whetherConsumptiveEquipment;//是否属于消耗器材 0 非消耗器材  1消耗器材

    public Integer getWhetherConsumptiveEquipment() {
        return whetherConsumptiveEquipment;
    }

    public void setWhetherConsumptiveEquipment(Integer whetherConsumptiveEquipment) {
        this.whetherConsumptiveEquipment = whetherConsumptiveEquipment;
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



    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getSingleEquipmentCode() {
        return singleEquipmentCode;
    }

    public void setSingleEquipmentCode(String singleEquipmentCode) {
        this.singleEquipmentCode = singleEquipmentCode;
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

    public String getMeasurementCode() {
        return measurementCode;
    }

    public String getLoadNum() {
        return loadNum;
    }

    public void setLoadNum(String loadNum) {
        this.loadNum = loadNum;
    }
}
