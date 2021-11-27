package com.dscomm.iecs.accept.graphql.inputbean;

/**交互记录输入信息*/
public class InteractiveRecordSaveInputInfo {

    private String id;
    private String alarmId;//报警编号
    private String incidentId; //案件id
    private String senderIdNum;//发送人证件号码
    private String senderPhoneNum;//发送人电话号码
    private String senderName;//发送人姓名
    private Long sendTime;//发送时间
    private String messageContent;//消息内容
    private Integer messageType;//消息类型 文字、语音、视频、图片、文件
    private Integer messageSource;//消息来源 0接受 1发送
    private String resourceUrl;//资源url
    private String remark;//备注
    private Integer state;//状态 0未发生 1发送成功 2发送失败
    private String xlabel;//x坐标
    private String ylabel;
    private Integer readMark;//已读标志 0未读 1已读


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSenderPhoneNum() {
        return senderPhoneNum;
    }

    public void setSenderPhoneNum(String senderPhoneNum) {
        this.senderPhoneNum = senderPhoneNum;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
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


    public Integer getReadMark() {
        return readMark;
    }

    public void setReadMark(Integer readMark) {
        this.readMark = readMark;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }
}
