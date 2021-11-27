package com.dscomm.iecs.basedata.graphql.inputbean;


import java.util.List;

/**
 * 描述：车载人员表
 *
 */
public class VehiclePersonsSaveInputInfo {

    private List<VehiclePersonsListSaveInputInfo> vehiclePersonsList; //车辆角色信息


    public List<VehiclePersonsListSaveInputInfo> getVehiclePersonsList() {
        return vehiclePersonsList;
    }

    public void setVehiclePersonsList(List<VehiclePersonsListSaveInputInfo> vehiclePersonsList) {
        this.vehiclePersonsList = vehiclePersonsList;
    }
}
