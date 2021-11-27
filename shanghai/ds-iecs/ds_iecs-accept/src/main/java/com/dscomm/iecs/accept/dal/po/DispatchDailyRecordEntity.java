package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 调度日志
 *
 */
@Entity
@Table(name = "JCJ_DDRZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DispatchDailyRecordEntity extends BaseEntity {

    @Column(name = "AJID" , length =  100 )
    private String incidentId;//案件ID

    @Column(name = "ZJHM", length = 50 )
    private String callingNumber;//主叫号码

    @Column(name = "BJHM", length = 50)
    private String calledNumber;//被叫号码

    @Column(name = "ZX", length = 50 )
    private String seatNumber; // 坐席号

    @Column(name = "KSSJ"  )
    private Long conversationStartTime;  //通话开始时间

    @Column(name = "JSSJ"  )
    private Long conversationEndTime;  //通话结束时间

    @Column(name = "THSC"  )
    private Integer conversationDuration;  //通话时长

    @Column(name = "LYH", length = 100 )
    private String relayRecordNumber;//录音号

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注


    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getConversationStartTime() {
        return conversationStartTime;
    }

    public void setConversationStartTime(Long conversationStartTime) {
        this.conversationStartTime = conversationStartTime;
    }

    public Long getConversationEndTime() {
        return conversationEndTime;
    }

    public void setConversationEndTime(Long conversationEndTime) {
        this.conversationEndTime = conversationEndTime;
    }

    public Integer getConversationDuration() {
        return conversationDuration;
    }

    public void setConversationDuration(Integer conversationDuration) {
        this.conversationDuration = conversationDuration;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
