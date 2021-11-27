package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述: 电话报警信息（呼入记录）
 *
 */
public class TelephoneBean extends BaseBean {

    private String telephoneId ; //电话报警信息id

    private String idCode;// 通话记录_通用唯一识别码

    private String acceptanceId; // 受理单ID

    private String incidentId;  //案件ID
    private String incidentNumber;//案件编号

    private String callDirection;  //呼叫方向

    private String callDirectionName;  //呼叫方向名称

    private String seatNumber; // 坐席号

    private String personId; // 警员id

    private String personName;// 警员姓名

    private String personNumber;// 警员工号

    private String callingNumber;//主叫号码

    private String calledNumber;//被叫号码

    private String acdGroupNumber;//ACD组号

    private Long ringingTime;  //振铃时间 ( 报警开始时间 )

    private Long answerTime;  //接听时间 ( 接警开始时间 )

    private Long endTime;  //结束时间 ( 报警结束时间 )

    private Long ringingDuration ;  //振铃时长

    private Long conversationDuration;  //通话时长

    private String relayRecordNumber;//录音号

    private String installedUserName;//装机用户名

    private String installedAddress;//装机地址

    private String alarmPersonName;//报警人姓名

    private String alarmPersonSex;//报警人性别

    private String alarmPersonPhone;//报警人联系电话

    private String alarmAddress;//报警地址

    private String longitude;// 报警坐标 经度

    private String latitude;// 报警坐标 纬度

    private String height;// 报警坐标 高度

    private String remarks;//备注

    private Integer whetherTrappedSign;//报警人是否为被困 0非被困人员 1被困人员

    private String relayRecord;// 录音文件

    private String relayRecordName;//  录音文件名称

    private String relayRecordTypeCode;// 录音文件 电子文件类型代码

    private String relayRecordUrl;//  录音文件 电子文件位置

    private VoiceTranscribeBean voiceTranscribeBean; //语音转译


    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRelayRecord() {
        return relayRecord;
    }

    public void setRelayRecord(String relayRecord) {
        this.relayRecord = relayRecord;
    }

    public String getRelayRecordName() {
        return relayRecordName;
    }

    public void setRelayRecordName(String relayRecordName) {
        this.relayRecordName = relayRecordName;
    }

    public String getRelayRecordTypeCode() {
        return relayRecordTypeCode;
    }

    public void setRelayRecordTypeCode(String relayRecordTypeCode) {
        this.relayRecordTypeCode = relayRecordTypeCode;
    }

    public String getRelayRecordUrl() {
        return relayRecordUrl;
    }

    public void setRelayRecordUrl(String relayRecordUrl) {
        this.relayRecordUrl = relayRecordUrl;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getCallDirection() {
        return callDirection;
    }

    public void setCallDirection(String callDirection) {
        this.callDirection = callDirection;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
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

    public String getAcdGroupNumber() {
        return acdGroupNumber;
    }

    public void setAcdGroupNumber(String acdGroupNumber) {
        this.acdGroupNumber = acdGroupNumber;
    }

    public Long getRingingTime() {
        return ringingTime;
    }

    public void setRingingTime(Long ringingTime) {
        this.ringingTime = ringingTime;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getRingingDuration() {
        return ringingDuration;
    }

    public void setRingingDuration(Long ringingDuration) {
        this.ringingDuration = ringingDuration;
    }

    public Long getConversationDuration() {
        return conversationDuration;
    }

    public void setConversationDuration(Long conversationDuration) {
        this.conversationDuration = conversationDuration;
    }

    public String getRelayRecordNumber() {
        return relayRecordNumber;
    }

    public void setRelayRecordNumber(String relayRecordNumber) {
        this.relayRecordNumber = relayRecordNumber;
    }

    public String getInstalledUserName() {
        return installedUserName;
    }

    public void setInstalledUserName(String installedUserName) {
        this.installedUserName = installedUserName;
    }

    public String getInstalledAddress() {
        return installedAddress;
    }

    public void setInstalledAddress(String installedAddress) {
        this.installedAddress = installedAddress;
    }

    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
    }

    public String getAlarmPersonSex() {
        return alarmPersonSex;
    }

    public void setAlarmPersonSex(String alarmPersonSex) {
        this.alarmPersonSex = alarmPersonSex;
    }

    public String getAlarmPersonPhone() {
        return alarmPersonPhone;
    }

    public void setAlarmPersonPhone(String alarmPersonPhone) {
        this.alarmPersonPhone = alarmPersonPhone;
    }

    public String getAlarmAddress() {
        return alarmAddress;
    }

    public void setAlarmAddress(String alarmAddress) {
        this.alarmAddress = alarmAddress;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCallDirectionName() {
        return callDirectionName;
    }

    public void setCallDirectionName(String callDirectionName) {
        this.callDirectionName = callDirectionName;
    }

    public VoiceTranscribeBean getVoiceTranscribeBean() {
        return voiceTranscribeBean;
    }

    public void setVoiceTranscribeBean(VoiceTranscribeBean voiceTranscribeBean) {
        this.voiceTranscribeBean = voiceTranscribeBean;
    }
}
