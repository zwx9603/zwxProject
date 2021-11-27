package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "MainMsg")
@XmlAccessorType(XmlAccessType.NONE)
public class UdpHandle110VO {

    @XmlElement(name = "MsgType")
    private String msgType ;
    @XmlElement(name = "MsgSender")
    private String msgSender ;
    @XmlElement(name = "MsgReceiver")
    private String msgReceiver ;
    @XmlElement(name = "MsgSendTime")
    private String msgSendTime ;
    @XmlElement(name = "MsgContent")
    private UdpHandle110ContentVO MsgContent;

    @XmlTransient
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @XmlTransient
    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    @XmlTransient
    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    @XmlTransient
    public String getMsgSendTime() {
        return msgSendTime;
    }

    public void setMsgSendTime(String msgSendTime) {
        this.msgSendTime = msgSendTime;
    }

    @XmlTransient
    public UdpHandle110ContentVO getMsgContent() {
        return MsgContent;
    }

    public void setMsgContent(UdpHandle110ContentVO msgContent) {
        MsgContent = msgContent;
    }

    @Override
    public String toString() {
        return "UdpHandleVO{" +
                "msgType='" + msgType + '\'' +
                ", msgSender='" + msgSender + '\'' +
                ", msgReceiver='" + msgReceiver + '\'' +
                ", msgSendTime='" + msgSendTime + '\'' +
                ", MsgContent=" + MsgContent +
                '}';
    }
}
