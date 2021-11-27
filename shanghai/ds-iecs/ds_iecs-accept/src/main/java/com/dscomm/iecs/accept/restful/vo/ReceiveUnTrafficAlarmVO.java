package com.dscomm.iecs.accept.restful.vo;

public class ReceiveUnTrafficAlarmVO {


    private UnTrafficAlarmVO unTrafficAlarmVO ; //警情信息

    private Boolean whetherSaveIncident = false ; //默认false

    private Integer trafficAlarType = 1 ; //转警类型 0 电话转警 1 警情转警 默认警情转警

    private String originalIncidentNumber  ; //转警系统 警情id

    private String  incidentId  ; //消防警情id

    private Integer backResult ; //转警结果 backResult 0 拒绝 1接收

    public Boolean getWhetherSaveIncident() {
        return whetherSaveIncident;
    }

    public void setWhetherSaveIncident(Boolean whetherSaveIncident) {
        this.whetherSaveIncident = whetherSaveIncident;
    }

    public Integer getTrafficAlarType() {
        return trafficAlarType;
    }

    public void setTrafficAlarType(Integer trafficAlarType) {
        this.trafficAlarType = trafficAlarType;
    }

    public UnTrafficAlarmVO getUnTrafficAlarmVO() {
        return unTrafficAlarmVO;
    }

    public void setUnTrafficAlarmVO(UnTrafficAlarmVO unTrafficAlarmVO) {
        this.unTrafficAlarmVO = unTrafficAlarmVO;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public Integer getBackResult() {
        return backResult;
    }

    public void setBackResult(Integer backResult) {
        this.backResult = backResult;
    }
}
