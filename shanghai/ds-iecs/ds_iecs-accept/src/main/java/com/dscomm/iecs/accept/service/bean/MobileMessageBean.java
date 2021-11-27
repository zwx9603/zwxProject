package com.dscomm.iecs.accept.service.bean;


import java.util.List;

public class MobileMessageBean {

    private String orgId ; // 单位id
    private String orgCode ; // 单位编码
    private String incidentId ; // 案件id
    private String instructionId ; //处警记录id 指令单id
    private String instructionsTypeCode;// 指令类型编码
    private String content ; // 指令内容
    private List<String> vehicleIds ; //车辆id集合
    private String vehicles ; //车辆id集合 用逗号分隔
    private List<String> personIds ; //人员id集合
    private String persons ; //人员id集合 用逗号分隔

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getInstructionsTypeCode() {
        return instructionsTypeCode;
    }

    public void setInstructionsTypeCode(String instructionsTypeCode) {
        this.instructionsTypeCode = instructionsTypeCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public String getVehicles() {
        return vehicles;
    }

    public void setVehicles(String vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }
}
