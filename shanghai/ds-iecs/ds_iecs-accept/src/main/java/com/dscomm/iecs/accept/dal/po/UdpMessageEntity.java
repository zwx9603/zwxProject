package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接受udp内容
 * */
@Entity
@Table(name = "udp_jsxx")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UdpMessageEntity extends BaseEntity {

    @Column(name = "xxlx")
    private String msgType;
    @Column(name = "fsip")
    private String msgSender;
    @Column(name = "jsip")
    private String msgReceiver;
    @Column(name = "fssj")
    private Long msgSendTime;
    @Column(name = "fsnr")
    private String msgContent;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    public Long getMsgSendTime() {
        return msgSendTime;
    }

    public void setMsgSendTime(Long msgSendTime) {
        this.msgSendTime = msgSendTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
