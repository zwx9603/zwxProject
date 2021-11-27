package com.dscomm.iecs.accept.service.bean;

/**
 * 描述:推荐的车辆方案
 * author:YangFuXi
 * Date:2021/6/11 Time:14:07
 **/
public class RecommendedVehicleBean {
    private String vehicleType;//车辆类型
    private int vehicleNumber;//车辆数量

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
