package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:装备性能指标
 */
@Entity
@Table(name = "WL_ZBXNZB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EquipmentPerformanceEntity extends BaseEntity {

    @Column(name = "DJZBID", length = 100)
    private String singleEquipmentId; //单件装备ID

    @Column(name = "ZBLXDM", length = 100)
    private String equipmentCode; //装备类型代码

    @Column(name = "ZBMC", length = 200)
    private String equipmentName; //装备名称

    @Column(name = "ZBXSXH")
    private Integer showNumber; //指标显示序号

    @Column(name = "CPXH", length = 100)
    private String factoryPlateModel; //厂牌型号

    @Column(name = "XXBS", length = 100)
    private String lowerLimitState; //下限标识

    @Column(name = "XXZBZ")
    private Float lowerLimitValue; //下线指标值

    @Column(name = "SXBS", length = 100)
    private String upperLimitState; //上限标识

    @Column(name = "SXZBL")
    private Float upperLimitValue; //上线指标值

    @Column(name = "JLDWDM", length = 100)
    private String measurementCode; //计量单位代码

    @Column(name = "JLZT")
    private Integer JLZT; //记录状态

    @Column(name = "CSZT")
    private Integer CSZT; //传输状态

    @Column(name = "SJC")
    private Long SJC; //时间戳

    @Column(name = "BZ", length = 400)
    private String remarks; //备注

    public String getSingleEquipmentId() {
        return singleEquipmentId;
    }

    public void setSingleEquipmentId(String singleEquipmentId) {
        this.singleEquipmentId = singleEquipmentId;
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

    public Integer getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(Integer showNumber) {
        this.showNumber = showNumber;
    }

    public String getFactoryPlateModel() {
        return factoryPlateModel;
    }

    public void setFactoryPlateModel(String factoryPlateModel) {
        this.factoryPlateModel = factoryPlateModel;
    }

    public String getLowerLimitState() {
        return lowerLimitState;
    }

    public void setLowerLimitState(String lowerLimitState) {
        this.lowerLimitState = lowerLimitState;
    }

    public Float getLowerLimitValue() {
        return lowerLimitValue;
    }

    public void setLowerLimitValue(Float lowerLimitValue) {
        this.lowerLimitValue = lowerLimitValue;
    }

    public String getUpperLimitState() {
        return upperLimitState;
    }

    public void setUpperLimitState(String upperLimitState) {
        this.upperLimitState = upperLimitState;
    }

    public Float getUpperLimitValue() {
        return upperLimitValue;
    }

    public void setUpperLimitValue(Float upperLimitValue) {
        this.upperLimitValue = upperLimitValue;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}