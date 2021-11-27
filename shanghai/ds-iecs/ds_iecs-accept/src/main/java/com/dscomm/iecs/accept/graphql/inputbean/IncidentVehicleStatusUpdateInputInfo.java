package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 描述： 车辆状态 保存参数
 *
 */
public class IncidentVehicleStatusUpdateInputInfo {

    private String incidentId; // 案件id

    private String vehicleStatusCode;// 车辆状态

    private String changeSource ; //更新来源 1接处警 2 实战指挥  3 移动 

    private List<IncidentVehicleHandleUpdateInputInfo> incidentVehicleHandles;// 案件车辆id 处警记录id 集合

    private List<String> vehicleIds ; //车辆id

    public String getChangeSource() {
        return changeSource;
    }

    public void setChangeSource(String changeSource) {
        this.changeSource = changeSource;
    }


    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public List<IncidentVehicleHandleUpdateInputInfo> getIncidentVehicleHandles() {
        return incidentVehicleHandles;
    }

    public void setIncidentVehicleHandles(List<IncidentVehicleHandleUpdateInputInfo> incidentVehicleHandles) {
        this.incidentVehicleHandles = incidentVehicleHandles;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }
}
