package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述： 自动语音转写
 *
 */
public class VoiceTranscribeSaveInputInfo   {

    private String id  ; // 主键

    private String telephoneId ;//电话报警id

    private String incidentId;//案件ID

    private String transcribeContent;//转写内容

    private String keyword;//关键字

    private String abstractContent;//摘要

    private String attachment;//附件

    private String attachmentType;//附件类型

    private String attachmentName;//附件名称

    private String attachmentUrl;//附件URL

    private String relayRecordNumber;//录音号

    private String remarks; // 备注

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getTranscribeContent() {
        return transcribeContent;
    }

    public void setTranscribeContent(String transcribeContent) {
        this.transcribeContent = transcribeContent;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
