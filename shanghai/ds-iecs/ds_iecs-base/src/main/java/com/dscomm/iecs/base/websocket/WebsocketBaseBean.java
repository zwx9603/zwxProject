package com.dscomm.iecs.base.websocket;

import com.dscomm.iecs.base.graphql.typebean.IncidentBase;

/**
 * 描述: webscoket 消息基本信息 推送
 *
 * @author YangFuxi
 * Date Time 2019/9/5 16:59
 */
public class WebsocketBaseBean {

    private String  websocketCode ; //消息号

    private String incidentId ; // 案件id

    private Object websocketBody ; //消息内容

    public String getWebsocketCode() {
        return websocketCode;
    }

    public void setWebsocketCode(String websocketCode) {
        this.websocketCode = websocketCode;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public Object getWebsocketBody() {
        return websocketBody;
    }

    public void setWebsocketBody(Object websocketBody) {
        this.websocketBody = websocketBody;
    }
}
