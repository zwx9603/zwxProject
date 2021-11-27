package com.dscomm.iecs.accept.graphql.typebean;


/**
 * 描述:websocket消息体 包含警情信息 以及 其他信息
 *
 */
public class HandleWebSocketPushBean {

    private IncidentBean incidentBean; //案件详情

    private HandleBean handleBean; //派车详情

    private String  speakToFileUrl  ; // tts播报语音文件路径

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }

    public HandleBean getHandleBean() {
        return handleBean;
    }

    public void setHandleBean(HandleBean handleBean) {
        this.handleBean = handleBean;
    }

    public String getSpeakToFileUrl() {
        return speakToFileUrl;
    }

    public void setSpeakToFileUrl(String speakToFileUrl) {
        this.speakToFileUrl = speakToFileUrl;
    }
}
