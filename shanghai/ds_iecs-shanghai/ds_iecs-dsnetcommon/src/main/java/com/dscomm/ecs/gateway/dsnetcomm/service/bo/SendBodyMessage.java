package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

import com.dscomm.ecs.gateway.dsnetcomm.service.xml.Body;

/**
 * 描述:
 *
 * @author YangFuxi
 * Date Time 2020/9/24 9:48
 */
public class SendBodyMessage {
    private String dstType, dstId, transType, msgId, reserved;
    private Body data;

    public String getDstType() {
        return dstType;
    }

    public void setDstType(String dstType) {
        this.dstType = dstType;
    }

    public String getDstId() {
        return dstId;
    }

    public void setDstId(String dstId) {
        this.dstId = dstId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public Body getData() {
        return data;
    }

    public void setData(Body data) {
        this.data = data;
    }
}
