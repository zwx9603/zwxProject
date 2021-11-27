package com.dscomm.iecs.accept.restful.vo.UdpVO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "MainMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class UdpIncidentVO {

    @XmlElement(name = "MsgType")
    private String msgType ;

    @XmlElement(name = "MsgSender")
    private String msgSender ;

    @XmlElement(name = "MsgReceiver")
    private String msgReceiver ;

    @XmlElement(name = "MsgSendTime")
    private String msgSendTime ;

    @XmlElement(name = "MsgContent")
    private UdpIncidentContentVO msgContent;

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
    public UdpIncidentContentVO getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(UdpIncidentContentVO msgContent) {
        this.msgContent = msgContent;
    }

    public String toStringReceive() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<MainMsg>" +
                "<MsgType>"+ msgType +"</MsgType>\n"+
                "<MsgSender>"+ msgSender +"</MsgSender>\n"+
                "<MsgReceiver>"+ msgReceiver +"</MsgReceiver>\n"+
                "<MsgSendTime>"+ msgSendTime +"</MsgSendTime>\n"+
                "\t <MsgContent>\n" +
                msgContent.toStringReceive() +
                "\t </MsgContent>\n"+
                "</MainMsg>";
    }

    public String toStringSend() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<MainMsg>" +
                "<MsgType>"+ msgType +"</MsgType>\n"+
                "<MsgSender>"+ msgSender +"</MsgSender>\n"+
                "<MsgReceiver>"+ msgReceiver +"</MsgReceiver>\n"+
                "<MsgSendTime>"+ msgSendTime +"</MsgSendTime>\n"+
                "\t <MsgContent>\n" +
                msgContent.toStringSend() +
                "\t </MsgContent>\n"+
                "</MainMsg>";
    }
}
