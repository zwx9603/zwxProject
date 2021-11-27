package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

/**
 * 描述:消息bo
 *
 * @author YangFuxi
 * Date Time 2020/9/18 9:20
 */
public class MessageBO {
    private HeaderBO header;
    private String message;

    public HeaderBO getHeader() {
        return header;
    }

    public void setHeader(HeaderBO header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
