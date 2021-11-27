package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 *  描述: 调派应急联动单位
 *
 */
public class HandleUnitEmergencyBean extends BaseBean {

    private String incidentId; //案件ID

    private String handleId;// 处警记录ID

    private String emergencyUnitId ;// 应急联动单位ID

    private String emergencyUnitName;//应急联动单位名称

    private Long dispatchTime;// 调派时间

    private String relayRecordNumber;//录音号

    private String remarks;//备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getEmergencyUnitId() {
        return emergencyUnitId;
    }

    public void setEmergencyUnitId(String emergencyUnitId) {
        this.emergencyUnitId = emergencyUnitId;
    }

    public String getEmergencyUnitName() {
        return emergencyUnitName;
    }

    public void setEmergencyUnitName(String emergencyUnitName) {
        this.emergencyUnitName = emergencyUnitName;
    }

    public Long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
