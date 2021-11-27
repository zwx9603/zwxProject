package com.dscomm.iecs.agent.service.bean;

/**
 * 终端信息 车辆信息
 */
public class ImeiVehiclePortalBean {


    private String         id;           //车辆id
    private String         name;       //车辆名称
    private String         plateNumber;       //车牌号
    private String         category;       // 车辆类型

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
