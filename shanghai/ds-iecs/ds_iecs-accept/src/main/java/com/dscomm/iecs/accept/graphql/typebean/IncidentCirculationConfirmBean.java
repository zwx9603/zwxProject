package com.dscomm.iecs.accept.graphql.typebean;

/**
 * 描述:警情接管成功通知
 *
 * @author YangFuxi
 * Date Time 2019/9/5 20:37
 */
public class IncidentCirculationConfirmBean {

    private String receiver; ; // 接收账号
    private String incidentId; //警情id

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }
}
