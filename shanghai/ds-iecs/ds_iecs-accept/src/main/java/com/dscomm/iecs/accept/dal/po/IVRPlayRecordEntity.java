package com.dscomm.iecs.accept.dal.po;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 * 播放录音记录（表名不能改）
 */
@Entity
@Table(name = "ds_ivr_bflyjl")
@DynamicInsert(true)
@DynamicUpdate(true)
public class IVRPlayRecordEntity   {

    @Id
    @Column(name = "bflyjlbh", nullable = false, length = 40)
    private String id;

    @Column(name = "dpdid" , length =  40  )
    private String handleMiniatureStationId ;//调派单ID

    @Column(name = "lybh", length = 100)
    private String relayRecordNumber;//录音编号

    @Column(name = "fysj" )
    private Date playTime ;// 放音时间

    @Column(name = "tzdxnr", length = 200)
    private String smsContent ;//通知短信内容

    @Column(name = "hfdjsj" )
    private Date visitTime ;// 回访登记时间

    @Column(name = "zt" )
    private Integer  recordStatus ; //  状态（默认值0） 0代表未发送语音服务,1代表已发送

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHandleMiniatureStationId() {
        return handleMiniatureStationId;
    }

    public void setHandleMiniatureStationId(String handleMiniatureStationId) {
        this.handleMiniatureStationId = handleMiniatureStationId;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }
}
