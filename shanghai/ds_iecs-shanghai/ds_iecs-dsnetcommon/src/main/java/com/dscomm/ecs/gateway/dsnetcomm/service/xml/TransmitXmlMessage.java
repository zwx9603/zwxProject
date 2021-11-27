package com.dscomm.ecs.gateway.dsnetcomm.service.xml;

import com.dscomm.ecs.gateway.dsnetcomm.service.body.TransmitBody;

/**
 * 描述:转发消息（header是xml格式）
 *
 * @author YangFuxi
 * Date Time 2020/9/29 21:47
 */
public class TransmitXmlMessage {
    private String dstType, dstId, transType, msgId, reserved;
    private TransmitBody data;

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

    public TransmitBody getData() {
        return data;
    }

    public void setData(TransmitBody data) {
        this.data = data;
    }
}
