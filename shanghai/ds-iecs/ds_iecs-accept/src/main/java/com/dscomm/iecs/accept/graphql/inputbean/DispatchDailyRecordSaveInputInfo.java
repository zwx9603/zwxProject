package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 调度日志 保存参数
 *
 */
public class DispatchDailyRecordSaveInputInfo  {

    private String incidentId;//案件ID

    private String callingNumber;//主叫号码

    private String calledNumber;//被叫号码

    private String seatNumber; // 坐席号

    private Long conversationStartTime;  //通话开始时间

    private Long conversationEndTime;  //通话结束时间

    private Integer conversationDuration;  //通话时长

    private String  relayRecordNumber ; //录音号

    private String remarks; // 备注

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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


}
