package com.dscomm.iecs.basedata.graphql.typebean;


import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：联勤保障单位 装备保障能力
 *
 */
public class UnitJoinServiceSupportabilityBean extends BaseBean {

    private String joinServiceUnitId; //保障单位ID 联勤保障单位ID

    private String equipmentCode; //装备编码

    private String supportResourceName; //保障资源名称

    private String specificationsNumber;// 规格型号

    private Float dailyProductionNum; //每日生产能力

    private String measuringUnitCode; //计量单位代码

    private String measurementName;// 计量单位名称

    private String supportCategoryCode; //保障类别代码

    private String supportCategoryName; //保障类别名称

    private String maxSupportNum; //最大保障数量

    private String remarks; // 备注


    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public String getSupportCategoryName() {
        return supportCategoryName;
    }

    public void setSupportCategoryName(String supportCategoryName) {
        this.supportCategoryName = supportCategoryName;
    }

    public String getJoinServiceUnitId() {
        return joinServiceUnitId;
    }

    public void setJoinServiceUnitId(String joinServiceUnitId) {
        this.joinServiceUnitId = joinServiceUnitId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getSupportResourceName() {
        return supportResourceName;
    }

    public void setSupportResourceName(String supportResourceName) {
        this.supportResourceName = supportResourceName;
    }

    public String getSpecificationsNumber() {
        return specificationsNumber;
    }

    public void setSpecificationsNumber(String specificationsNumber) {
        this.specificationsNumber = specificationsNumber;
    }

    public Float getDailyProductionNum() {
        return dailyProductionNum;
    }

    public void setDailyProductionNum(Float dailyProductionNum) {
        this.dailyProductionNum = dailyProductionNum;
    }

    public String getMeasuringUnitCode() {
        return measuringUnitCode;
    }

    public void setMeasuringUnitCode(String measuringUnitCode) {
        this.measuringUnitCode = measuringUnitCode;
    }

    public String getSupportCategoryCode() {
        return supportCategoryCode;
    }

    public void setSupportCategoryCode(String supportCategoryCode) {
        this.supportCategoryCode = supportCategoryCode;
    }

    public String getMaxSupportNum() {
        return maxSupportNum;
    }

    public void setMaxSupportNum(String maxSupportNum) {
        this.maxSupportNum = maxSupportNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
