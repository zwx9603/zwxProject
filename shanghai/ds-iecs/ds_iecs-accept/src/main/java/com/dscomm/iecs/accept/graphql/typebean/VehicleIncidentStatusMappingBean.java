package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 车辆案件状态关系 车辆状态对案件状态的影响
 *
 */
public class VehicleIncidentStatusMappingBean extends BaseBean {

    private String incidentTypeCode;// 案件类型编码

    private String incidentTypeName ;// 案件类型名称

    private String vehicleStatusCode;// 车辆状态编码

    private String vehicleStatusName ;// 车辆状态名称

    private String incidentStatusCode;// 案件状态编码

    private String incidentStatusName ;// 案件状态名称

    private String influenceMode;// 影响方式

    private String remarks;//备注

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getVehicleStatusCode() {
        return vehicleStatusCode;
    }

    public void setVehicleStatusCode(String vehicleStatusCode) {
        this.vehicleStatusCode = vehicleStatusCode;
    }

    public String getVehicleStatusName() {
        return vehicleStatusName;
    }

    public void setVehicleStatusName(String vehicleStatusName) {
        this.vehicleStatusName = vehicleStatusName;
    }

    public String getIncidentStatusCode() {
        return incidentStatusCode;
    }

    public void setIncidentStatusCode(String incidentStatusCode) {
        this.incidentStatusCode = incidentStatusCode;
    }

    public String getIncidentStatusName() {
        return incidentStatusName;
    }

    public void setIncidentStatusName(String incidentStatusName) {
        this.incidentStatusName = incidentStatusName;
    }

    public String getInfluenceMode() {
        return influenceMode;
    }

    public void setInfluenceMode(String influenceMode) {
        this.influenceMode = influenceMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
