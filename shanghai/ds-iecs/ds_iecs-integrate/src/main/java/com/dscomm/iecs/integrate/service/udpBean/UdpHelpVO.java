package com.dscomm.iecs.integrate.service.udpBean;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "MainMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class UdpHelpVO {

    @XmlElement(name = "MsgType")
    private String msgType ;

    @XmlElement(name = "MsgSender")
    private String msgSender ;

    @XmlElement(name = "MsgReceiver")
    private String msgReceiver ;

    @XmlElement(name = "MsgSendTime")
    private String msgSendTime ;

    @XmlElement(name = "MsgContent")
    private UdpHelpContentVO msgContent;

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
    public UdpHelpContentVO getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(UdpHelpContentVO msgContent) {
        this.msgContent = msgContent;
    }
}
