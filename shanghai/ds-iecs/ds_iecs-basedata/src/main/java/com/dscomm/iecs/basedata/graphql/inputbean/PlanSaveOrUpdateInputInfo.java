package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 描述:重点单位预案保存修改参数
 */
public class PlanSaveOrUpdateInputInfo   {

    private String keyUnitId ; //重点单位id

    private PlanSaveInputInfo planSaveInputInfo;   //预案基本信息

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public PlanSaveInputInfo getPlanSaveInputInfo() {
        return planSaveInputInfo;
    }

    public void setPlanSaveInputInfo(PlanSaveInputInfo planSaveInputInfo) {
        this.planSaveInputInfo = planSaveInputInfo;
    }
}