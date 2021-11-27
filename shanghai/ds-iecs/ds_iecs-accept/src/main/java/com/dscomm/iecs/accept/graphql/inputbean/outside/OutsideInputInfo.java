package com.dscomm.iecs.accept.graphql.inputbean.outside;

import com.dscomm.iecs.accept.graphql.inputbean.IncidentSaveInputInfo;

import java.util.List;

/**
 * 描述： 外部系统请求参数
 */
public class OutsideInputInfo {

    private String incidentId ; //警情id

    private IncidentSaveInputInfo  incident ; //警情信息

    private String explain ; //说明

    private List<String> type ; //类型
    /**
     INCIDENT_TRAFFICE_TYPE_QQXZ110("110","110"),
     INCIDENT_TRAFFICE_TYPE_QQXZ120("120","120"),
     INCIDENT_TRAFFICE_TYPE_QQXZ122("122","122"),
     INCIDENT_TRAFFICE_TYPE_QQXZYJ("130","应急中心"),

     */

    private String personId; // 人员编号

    private String personName; // 人员姓名

    private String seatNumber; // 坐席号

    private String handleUnit; //操作单位id

    private String receiveUnit; //接收单位id

    private Long outsideTime ; //操作时间

    public Long getOutsideTime() {
        return outsideTime;
    }

    public void setOutsideTime(Long outsideTime) {
        this.outsideTime = outsideTime;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public IncidentSaveInputInfo getIncident() {
        return incident;
    }

    public void setIncident(IncidentSaveInputInfo incident) {
        this.incident = incident;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getHandleUnit() {
        return handleUnit;
    }

    public void setHandleUnit(String handleUnit) {
        this.handleUnit = handleUnit;
    }

    public String getReceiveUnit() {
        return receiveUnit;
    }

    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }


    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }
}
