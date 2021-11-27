package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description:交互记录po
 *
 * @author: xyy
 * Date: created in 16:30 2020/9/18
 */
@Entity
@Table(name = "jcj_bj_jhjl")
public class InteractiveRecordEntity extends BaseEntity {

    /**
     * 报警编号
     */
    @Column(name = "bjbh")
    private String alarmId;

    /**
     * 报警编号
     */
    @Column(name = "ajid")
    private String incidentId;

    /**
     * 发送人证件号码
     */
    @Column(name = "fsrdm")
    private String senderIdNum;
    /**
     * 发送人电话号码
     */
    @Column(name = "fsrhm")
    private String senderPhoneNum;
    /**
     * 发送人姓名
     */
    @Column(name = "fsrxm")
    private String senderName;
    /**
     * 发送时间
     */
    @Column(name = "fssj")
    private Long sendTime;
    /**
     * 消息内容
     */
    @Column(name = "xxnr")
    private String messageContent;
    /**
     * 消息类型
     */
    @Column(name = "xxlx")
    private Integer messageType;//1文字、2语音、3视频、4图片、5文件
    /**
     * 消息来源
     */
    @Column(name = "xxly")
    private Integer messageSource;//0接受 1发送
    /**
     * 资源url
     */
    @Column(name = "zyurl")
    private String resourceUrl;
    /**
     * 备注
     */
    @Column(name = "bz")
    private String remark;
    /**
     * 状态
     */
    @Column(name = "zt")
    private Integer state;//0未发生 1发送成功 2发送失败
    /**
     * x坐标
     */
    @Column(name = "gis_x")
    private String xlabel;
    /**
     * y坐标
     */
    @Column(name = "gis_y")
    private String ylabel;
    /**
     * 已读标志
     */
    @Column(name = "ydbz")
    private Integer readMark;//0未读 1已读


    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getSenderIdNum() {
        return senderIdNum;
    }

    public void setSenderIdNum(String senderIdNum) {
        this.senderIdNum = senderIdNum;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhoneNum() {
        return senderPhoneNum;
    }

    public void setSenderPhoneNum(String senderPhoneNum) {
        this.senderPhoneNum = senderPhoneNum;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(Integer messageSource) {
        this.messageSource = messageSource;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReadMark() {
        return readMark;
    }

    public void setReadMark(Integer readMark) {
        this.readMark = readMark;
    }

    public String getXlabel() {
        return xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public String getYlabel() {
        return ylabel;
    }

    public void setYlabel(String ylabel) {
        this.ylabel = ylabel;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }
}
