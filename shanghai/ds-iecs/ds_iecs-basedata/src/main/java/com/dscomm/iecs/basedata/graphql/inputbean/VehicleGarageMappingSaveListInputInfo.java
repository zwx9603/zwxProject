package com.dscomm.iecs.basedata.graphql.inputbean;

import java.util.List;

/**
 * 描述： 中队车辆与车库门对应关系 保存参数
 *
 */
public class VehicleGarageMappingSaveListInputInfo {

    List<VehicleGarageMappingSaveInputInfo> vehicleGarageMappingSaveInputInfos ; //车库车辆对应关系

    public List<VehicleGarageMappingSaveInputInfo> getVehicleGarageMappingSaveInputInfos() {
        return vehicleGarageMappingSaveInputInfos;
    }

    public void setVehicleGarageMappingSaveInputInfos(List<VehicleGarageMappingSaveInputInfo> vehicleGarageMappingSaveInputInfos) {
        this.vehicleGarageMappingSaveInputInfos = vehicleGarageMappingSaveInputInfos;
    }
}
