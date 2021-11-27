package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 自动语音转写
 *
 */
public class VoiceTranscribeBean extends BaseBean {

    private String telephoneId ;//电话报警id

    private String incidentId;//案件ID

    private String transcribeContent;//转写内容

    private String keyword;//关键字

    private String abstractContent;//摘要

    private String attachment ;//附件类型

    private String attachmentType;//附件类型代码

    private String attachmentTypeName;//附件类型名称

    private String attachmentName;//附件名称

    private String attachmentUrl;//附件URL

    private String relayRecordNumber;//录音号

    private String seatNumber; //  坐席号

    private String acceptancePerson;// 接警员

    private String acceptancePersonName;// 接警员姓名

    private String acceptancePersonNumber;// 接警员工号

    private String remarks; // 备注

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentTypeName() {
        return attachmentTypeName;
    }

    public void setAttachmentTypeName(String attachmentTypeName) {
        this.attachmentTypeName = attachmentTypeName;
    }

    public String getAcceptancePerson() {
        return acceptancePerson;
    }

    public void setAcceptancePerson(String acceptancePerson) {
        this.acceptancePerson = acceptancePerson;
    }

    public String getAcceptancePersonName() {
        return acceptancePersonName;
    }

    public void setAcceptancePersonName(String acceptancePersonName) {
        this.acceptancePersonName = acceptancePersonName;
    }

    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getAcceptancePersonNumber() {
        return acceptancePersonNumber;
    }

    public void setAcceptancePersonNumber(String acceptancePersonNumber) {
        this.acceptancePersonNumber = acceptancePersonNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
