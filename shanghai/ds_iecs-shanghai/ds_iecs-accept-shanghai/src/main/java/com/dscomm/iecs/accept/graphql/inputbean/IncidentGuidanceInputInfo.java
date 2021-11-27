package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;

import java.util.List;

/**
 * @author xushaohuang
 * @date 2021-05-12 21:13
 * @description 警情引导保存入参
 */
public class IncidentGuidanceInputInfo {

    private DocumentSaveInputInfoSH documentSaveInputInfoSH; //原火场文书入参

    private String feedbackNumber ;//反馈号码

    private String AddressTextInfo ; //地址文字信息

    private String reportDescription ; //上报描述

    private String whetherTrapped ; //是否有被困人

    private String incidentGis_x ; // 案发经度

    private String incidentGis_y ; // 案发纬度

    private String incidentAddress ; //案发地址

    private String reportGis_x ; //上报经度

    private String reportGis_y ; //上报纬度

    private String reportAddress ; //上报地址

    private List<AttachmentSaveInputInfo> attachmentSaveInputInfos; //附件信息

    public String getFeedbackNumber() {
        return feedbackNumber;
    }

    public void setFeedbackNumber(String feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getIncidentGis_x() {
        return incidentGis_x;
    }

    public void setIncidentGis_x(String incidentGis_x) {
        this.incidentGis_x = incidentGis_x;
    }

    public String getIncidentGis_y() {
        return incidentGis_y;
    }

    public void setIncidentGis_y(String incidentGis_y) {
        this.incidentGis_y = incidentGis_y;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public String getReportGis_x() {
        return reportGis_x;
    }

    public void setReportGis_x(String reportGis_x) {
        this.reportGis_x = reportGis_x;
    }

    public String getReportGis_y() {
        return reportGis_y;
    }

    public void setReportGis_y(String reportGis_y) {
        this.reportGis_y = reportGis_y;
    }

    public String getReportAddress() {
        return reportAddress;
    }

    public void setReportAddress(String reportAddress) {
        this.reportAddress = reportAddress;
    }

    public String getWhetherTrapped() {
        return whetherTrapped;
    }

    public void setWhetherTrapped(String whetherTrapped) {
        this.whetherTrapped = whetherTrapped;
    }

    public DocumentSaveInputInfoSH getDocumentSaveInputInfoSH() {
        return documentSaveInputInfoSH;
    }

    public void setDocumentSaveInputInfoSH(DocumentSaveInputInfoSH documentSaveInputInfoSH) {
        this.documentSaveInputInfoSH = documentSaveInputInfoSH;
    }

    public String getAddressTextInfo() {
        return AddressTextInfo;
    }

    public void setAddressTextInfo(String addressTextInfo) {
        AddressTextInfo = addressTextInfo;
    }

    public List<AttachmentSaveInputInfo> getAttachmentSaveInputInfos() {
        return attachmentSaveInputInfos;
    }

    public void setAttachmentSaveInputInfos(List<AttachmentSaveInputInfo> attachmentSaveInputInfos) {
        this.attachmentSaveInputInfos = attachmentSaveInputInfos;
    }
}
