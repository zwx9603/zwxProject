package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 *  描述:参战人员设置 保存参数
 *
 */
public class ParticipantSaveInputInfo   {

    private String incidentId ; //案件id

    private String checkPersonId; //清点人员编号

    private String checkPersonName; //清点人员姓名

    private String vehicleId;//车辆编号

    private String handleId ; //处警信息id

    private List<ParticipantFeedbackSaveInputInfo> participantFeedbackSaveInputInfo ; //参战人员反馈

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCheckPersonId() {
        return checkPersonId;
    }

    public void setCheckPersonId(String checkPersonId) {
        this.checkPersonId = checkPersonId;
    }

    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public List<ParticipantFeedbackSaveInputInfo> getParticipantFeedbackSaveInputInfo() {
        return participantFeedbackSaveInputInfo;
    }

    public void setParticipantFeedbackSaveInputInfo(List<ParticipantFeedbackSaveInputInfo> participantFeedbackSaveInputInfo) {
        this.participantFeedbackSaveInputInfo = participantFeedbackSaveInputInfo;
    }

}
