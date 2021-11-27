package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:器材性能指标
 */
@Entity
@Table(name = "WL_QCXNZB")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EquipmentMaterialPerformanceEntity extends BaseEntity {

    @Column(name = "ZBQCDYID", length = 100)
    private String equipmentId; //装备器材ID

    @Column(name = "ZBMC", length = 200)
    private String equipmentName; //装备名称

    @Column(name = "ZBXSXH", length = 100)
    private Integer showNum; //指标显示序号

    @Column(name = "XXBS", length = 100)
    private String lowerLimitState; //下限标识

    @Column(name = "XXZBZ", length = 100)
    private Float lowerLimitValue; //下线指标值

    @Column(name = "SXBS", length = 100)
    private String upperLimitState; //上限标识

    @Column(name = "SXZBL", length = 100)
    private Float upperLimitValue; //上线指标值

    @Column(name = "JLDWDM", length = 100)
    private String measurementCode; //计量单位代码

    @Column(name = "BZ", length = 400)
    private String remarks; //备注

    @Column(name = "JLZT")
    private Integer JLZT; //记录状态

    @Column(name = "CSZT")
    private Integer CSZT; //传输状态

    @Column(name = "SJC")
    private Long SJC; //时间戳

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getShowNum() {
        return showNum;
    }

    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
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