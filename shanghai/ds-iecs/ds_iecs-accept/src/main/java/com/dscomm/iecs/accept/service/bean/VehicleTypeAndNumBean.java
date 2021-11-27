package com.dscomm.iecs.accept.service.bean;

import java.util.List;

/**
 * 描述:车辆类型和数量
 *
 * @author YangFuxi
 * Date Time 2021/8/4 8:59
 */
public class VehicleTypeAndNumBean {
    private String vehicleTypeCode;//车辆类型code
    private String vehicleTypeName;//车辆类型名称
    private int num;//车辆数量
    private List<String> vehicleIds;//车辆id集合

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
