package com.dscomm.iecs.agent.restful.vo;

import java.util.List;

/**
 * 描述:websocket测试
 *
 * @author YangFuxi
 * Date Time 2020/2/12 10:53
 */
public class WebsocketTestVO {
    private String code;
    private String body;
    private List<String> receivers;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }
}
