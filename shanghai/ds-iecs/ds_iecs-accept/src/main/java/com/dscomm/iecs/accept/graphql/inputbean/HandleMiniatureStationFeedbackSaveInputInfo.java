package com.dscomm.iecs.accept.graphql.inputbean;


/**
 * 微站调派 反馈信息
 *
 */
public class HandleMiniatureStationFeedbackSaveInputInfo   {

    private String incidentId; //案件ID

    private String handleId ;//处警单ID

    private String handleMiniatureStationId ;//微站调派单ID

    private Long feedbackTime ; // 反馈时间

    private String localeDesc ; //  处置情况

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

    public String getHandleMiniatureStationId() {
        return handleMiniatureStationId;
    }

    public void setHandleMiniatureStationId(String handleMiniatureStationId) {
        this.handleMiniatureStationId = handleMiniatureStationId;
    }

    public String getLocaleDesc() {
        return localeDesc;
    }

    public void setLocaleDesc(String localeDesc) {
        this.localeDesc = localeDesc;
    }

    public Long getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Long feedbackTime) {
        this.feedbackTime = feedbackTime;
    }
}
