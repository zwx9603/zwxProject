package com.dscomm.iecs.accept.graphql.typebean;

/*
* 统计出动车辆的数量
* */
public class VehicleNumBean {
    private String carTypeName; // 车辆类型名称
    private String carNum;  // 应派车辆数目
    private String czNum; //参战车辆数目
    private String dpNum; // 本次调派数量

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCzNum() {
        return czNum;
    }

    public void setCzNum(String czNum) {
        this.czNum = czNum;
    }

    public String getDpNum() {
        return dpNum;
    }

    public void setDpNum(String dpNum) {
        this.dpNum = dpNum;
    }
}
