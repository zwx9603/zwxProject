package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "MainMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class UdpMessageTypeVO {

    @XmlElement(name = "MsgType")
    private String MsgType;
    @XmlElement(name = "MsgSender")
    private String MsgSender;
    @XmlElement(name = "MsgReceiver")
    private String MsgReceiver;
    @XmlElement(name = "MsgSendTime")
    private String MsgSendTime;
    @XmlElement(name = "MsgContent")
    private String MsgContent;

    @XmlTransient
    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    @XmlTransient
    public String getMsgSender() {
        return MsgSender;
    }

    public void setMsgSender(String msgSender) {
        MsgSender = msgSender;
    }

    @XmlTransient
    public String getMsgReceiver() {
        return MsgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        MsgReceiver = msgReceiver;
    }

    @XmlTransient
    public String getMsgSendTime() {
        return MsgSendTime;
    }

    public void setMsgSendTime(String msgSendTime) {
        MsgSendTime = msgSendTime;
    }

    @XmlTransient
    public String getMsgContent() {
        return MsgContent;
    }

    public void setMsgContent(String msgContent) {
        MsgContent = msgContent;
    }

    @Override
    public String toString() {
        return MsgType;
    }
}
