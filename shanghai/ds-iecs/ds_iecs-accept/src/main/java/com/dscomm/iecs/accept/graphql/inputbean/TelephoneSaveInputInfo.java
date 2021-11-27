package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述: 电话报警信息（呼入记录）
 *
 */
public class TelephoneSaveInputInfo   {

    private String id ; //主键

    private String callDirection;  //呼叫方向  呼入/呼出

    private String seatNumber; // 坐席号

    private String callingNumber;//主叫号码

    private String calledNumber;//被叫号码

    private String acdGroupNumber;//ACD组号

    private Long incomingCallTime;  //呼叫时间

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

    public Integer getWhetherTrappedSign() {
        return whetherTrappedSign;
    }

    public void setWhetherTrappedSign(Integer whetherTrappedSign) {
        this.whetherTrappedSign = whetherTrappedSign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAcdGroupNumber() {
        return acdGroupNumber;
    }

    public void setAcdGroupNumber(String acdGroupNumber) {
        this.acdGroupNumber = acdGroupNumber;
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

    public Long getIncomingCallTime() {
        return incomingCallTime;
    }

    public void setIncomingCallTime(Long incomingCallTime) {
        this.incomingCallTime = incomingCallTime;
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
}
