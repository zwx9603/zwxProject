package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述： 等级调度车辆信息
 *
 */
public class HierarchicalDispatchVehicleBean  {

    private String score;//推荐分数
    private String vehicleTypeCode;// 车辆类型代码

    private String vehicleTypeName;;// 车辆类型名称

    private Integer dispatchNum=0;// 调派数量

    private Integer vehicleNum=0 ; //符合条件可用数量

    private List<String> vehicles ; //符合条件的车辆信息id


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

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public Integer getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(Integer vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
