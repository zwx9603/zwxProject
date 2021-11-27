package com.dscomm.iecs.basedata.graphql.typebean;

import java.util.List;

/**
 * 描述:预案基本信息
 */
public class PlanSupplementBean   {

    private String vehicleTypeCode ; //补充车辆类型编码

    private String vehicleTypeName ; //补充车辆类型名称

    private Integer planSupplementNum = 0 ; //需要补充车辆类型数量

    private List<String>  disabledDispatchVehicleIdList   ; // 车辆类型不可调用车辆ids

    private List<String>  planSupplementVehicleIdList  ; //补充类型车辆ids

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

    public Integer getPlanSupplementNum() {
        return planSupplementNum;
    }

    public void setPlanSupplementNum(Integer planSupplementNum) {
        this.planSupplementNum = planSupplementNum;
    }

    public List<String> getDisabledDispatchVehicleIdList() {
        return disabledDispatchVehicleIdList;
    }

    public void setDisabledDispatchVehicleIdList(List<String> disabledDispatchVehicleIdList) {
        this.disabledDispatchVehicleIdList = disabledDispatchVehicleIdList;
    }

    public List<String> getPlanSupplementVehicleIdList() {
        return planSupplementVehicleIdList;
    }

    public void setPlanSupplementVehicleIdList(List<String> planSupplementVehicleIdList) {
        this.planSupplementVehicleIdList = planSupplementVehicleIdList;
    }
}