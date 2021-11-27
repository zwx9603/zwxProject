package com.dscomm.iecs.accept.graphql.typebean;


/**
 * 描述:警情拆分 合并推送
 *
 */
public class MergeSpiltWebSocketPushBean {

    private IncidentBean mainIncidentBean; //主案件信息

    private IncidentBean incidentBean; //操作案件信息

    public IncidentBean getMainIncidentBean() {
        return mainIncidentBean;
    }

    public void setMainIncidentBean(IncidentBean mainIncidentBean) {
        this.mainIncidentBean = mainIncidentBean;
    }

    public IncidentBean getIncidentBean() {
        return incidentBean;
    }

    public void setIncidentBean(IncidentBean incidentBean) {
        this.incidentBean = incidentBean;
    }
}
