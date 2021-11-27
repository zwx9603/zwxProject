package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述: 电话拨打(回拨) 保存参数
 *
 */
public class CallBackSaveInputInfo {

    private String incidentId;  //案件ID

    private String calledNumber;//被叫号码

    private String callingNumber;//主叫号码

    private String relayRecordNumber;//录音号

    private String callType ; //拨打类型

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }
}
