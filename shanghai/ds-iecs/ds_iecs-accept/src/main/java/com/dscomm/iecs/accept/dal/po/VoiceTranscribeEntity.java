package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 自动语音转写
 *
 */
@Entity
@Table(name = "JCJ_YYZXJL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VoiceTranscribeEntity extends BaseEntity {

    @Column(name = "DHBJID" , length =  100 )
    private String telephoneId ;//todo 字段 电话报警id

    @Column(name = "AJID" , length =  100 )
    private String incidentId;//案件ID


//    @Column(name = "ZXNR" , length =  4000 )
    @Column(name = "YYZYNR_JYQK" , length =  4000 )
    private String transcribeContent;//转写内容 语音转译内容_简要情况


    @Column(name = "GJZ" , length =  500 )
    private String keyword;//关键字

    @Column(name = "ZY" , length =  1000 )
    private String abstractContent;//摘要

//    @Column(name = "FJ" , length =  100 )
    @Column(name = "LYWJ", length = 100 )
    private String attachment ;//todo 字段 附件 录音文件

//    @Column(name = "FJMC" , length =  100 )
    @Column(name = "LYWJ_MC", length = 100 )
    private String attachmentName;//todo 字段 附件名称 录音文件名称

//    @Column(name = "FJLX" , length =  100 )
    @Column(name = "DZWJLXDM", length = 100 )
    private String attachmentType;//todo 字段 附件类型 录音文件 电子文件类型代码

//    @Column(name = "URL" , length =  400 )
    @Column(name = "LYWJ_DZWJWZ", length = 1000 )
    private String attachmentUrl;//todo 字段 附件URL 录音文件 电子文件位置

    @Column(name = "LYH", length = 100 )
    private String relayRecordNumber;//录音号

    @Column(name = "ZXH", length = 50)
    private String seatNumber; //  坐席号

    @Column(name = "JJY", length = 100)
    private String acceptancePerson;// 接警员

    @Column(name = "JJYXM", length = 100)
    private String acceptancePersonName;// 接警员姓名

    @Column(name = "JJYGH", length = 100)
    private String acceptancePersonNumber;// 接警员工号

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

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
