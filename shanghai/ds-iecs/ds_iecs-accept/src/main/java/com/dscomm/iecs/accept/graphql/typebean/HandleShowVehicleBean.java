package com.dscomm.iecs.accept.graphql.typebean;

/**
 *  描述: 处警记录 车辆 简要显示
 *
 */
public  class HandleShowVehicleBean {

    private String  vehicleTypeCode ; //车辆类型

    private String  vehicleTypeName ; //车辆类型名称

    private int  vehicleTypeNum = 0 ; //车辆类型数量 默认为0

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

    public int getVehicleTypeNum() {
        return vehicleTypeNum;
    }

    public void setVehicleTypeNum(int vehicleTypeNum) {
        this.vehicleTypeNum = vehicleTypeNum;
    }
}


