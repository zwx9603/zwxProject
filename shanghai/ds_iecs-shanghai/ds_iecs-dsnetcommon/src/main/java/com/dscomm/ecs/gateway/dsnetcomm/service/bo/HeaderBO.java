package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

/**
 * 描述:消息头
 *
 * @author YangFuxi
 * Date Time 2020/9/18 9:20
 */
public class HeaderBO {
    private int transType;
    private String  msgID, destType, destID, srcType, srcID;//去除0x前缀

    public int getTransType() {
        return transType;
    }

    public void setTransType(int transType) {
        this.transType = transType;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getDestType() {
        return destType;
    }

    public void setDestType(String destType) {
        this.destType = destType;
    }

    public String getDestID() {
        return destID;
    }

    public void setDestID(String destID) {
        this.destID = destID;
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType;
    }

    public String getSrcID() {
        return srcID;
    }

    public void setSrcID(String srcID) {
        this.srcID = srcID;
    }
}
