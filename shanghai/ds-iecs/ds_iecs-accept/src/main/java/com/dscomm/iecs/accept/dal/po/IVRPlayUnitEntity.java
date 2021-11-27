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
 * 播放录音机构（表名不能改）
 */
@Entity
@Table(name = "ds_ivr_bflyjg")
@DynamicInsert(true)
@DynamicUpdate(true)
public class IVRPlayUnitEntity {

    @Id
    @Column(name = "lsh",   length = 40)
    private String id;

    @Column(name = "bflyjlbh" , length =  40  )
    private String ivrPlayRecordId  ;//播放录音记录编号

    @Column(name = "fyjtdh", length = 100)
    private String playPhoneNumber;//放音接听电话

    @Column(name = "fyzt", length = 20)
    private String  playStatus ; //  放音状态

    @Column(name = "fysj" )
    private Date playTime ;// 放音时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIvrPlayRecordId() {
        return ivrPlayRecordId;
    }

    public void setIvrPlayRecordId(String ivrPlayRecordId) {
        this.ivrPlayRecordId = ivrPlayRecordId;
    }

    public String getPlayPhoneNumber() {
        return playPhoneNumber;
    }

    public void setPlayPhoneNumber(String playPhoneNumber) {
        this.playPhoneNumber = playPhoneNumber;
    }

    public String getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus;
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }
}
