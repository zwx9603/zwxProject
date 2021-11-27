package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 车辆 装载装备 信息
 *
 */
@Entity
@Table(name = "V_CLXX_ZZZB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EquipmentVehicleLoadEntity extends BaseEntity {

    @Column(name = "SSXFJGID", length = 100)
    private String organizationId;// 所属消防机构ID

    @Column(name = "SSXFJGMC", length = 200)
    private String organizationName;// 所属消防机构名称

    @Column(name = "CLID", length = 100)
    private String vehicleId;// 车辆id

    @Column(name = "DJZBBM", length = 100)
    private String  singleEquipmentCode;// 单件装备编码

    @Column(name = "ZBLXDM", length = 100 )
    private String  equipmentTypeCode;//装备类型编码

    @Column(name = "ZBQCBM", length = 100 )
    private String  equipmentCode;//装备器材编码

    @Column(name = "ZBQCMC", length = 200 )
    private String  equipmentName;//装备器材名称

    @Column(name = "GGXH", length = 100)
    private String specificationsNumber;// 规格型号

    @Column(name = "ZZSL" )
    private Float loadNum;//装载数量

    @Column(name = "JLDWDM", length = 100)
    private String measurementCode;// 计量单位代码

    @Column(name = "SFXHZB"  )
    private Integer  whetherConsumptiveEquipment;//是否属于消耗器材 0 非消耗器材  1消耗器材

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public Float getLoadNum() {
        return loadNum;
    }

    public void setLoadNum(Float loadNum) {
        this.loadNum = loadNum;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public Integer getWhetherConsumptiveEquipment() {
        return whetherConsumptiveEquipment;
    }

    public void setWhetherConsumptiveEquipment(Integer whetherConsumptiveEquipment) {
        this.whetherConsumptiveEquipment = whetherConsumptiveEquipment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
