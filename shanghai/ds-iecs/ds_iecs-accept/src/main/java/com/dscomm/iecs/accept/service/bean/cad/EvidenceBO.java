package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述 : 证据 BO
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/9/9 14:36
 */
public class EvidenceBO implements Serializable {
    /**
     * 流水号
     */
    private String id;
    /**
     * 证据编号
     */
    private String evidenceId;
    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 处警单编号
     */
    private String dispatchId;
    /**
     * 反馈单编号
     */
    private String feedbackId;
    /**
     * 单位编号
     */
    private String orgCode;
    /**
     * 单位name
     */
    private String orgName;
    /**
     * 上级单位编号
     */
    private String orgParentCode;
    /**
     * 上级单位name
     */
    private String orgParentName;
    /**
     * 上传人工号
     */
    private String uploaderId;
    /**
     * 证据收集人工号
     */
    private String collectorId;
    /**
     * 上传时间
     */
    private Long uploadTime;
    /**
     * 上传日期
     */
    private Long uploadDate;
    /**
     * 文件地址
     */
    private String evidenceUrl;
    /**
     * 证据类型
     */
    private Integer evidenceType;
    /**
     * 证据类型name
     */
    private String evidenceTypeName;
    /**
     * 证据名称
     */
    private String evidenceName;
    /**
     * 证据描述
     */
    private String evidenceDescription;
    /**
     * ftp服务器编号
     */
    private Long ftpServerNumber;
    /**
     * 文件扩展名
     */
    private String fileExtension;
    /**
     * 上传人姓名
     */
    private String uploaderName;
    /**
     * 证据收集人姓名
     */
    private String collectorName;
    /**
     * 打开方式
     */
    private String openMode;
    /**
     * 打开方式name
     */
    private String openModeName;
    /**
     * 通知流水号
     */
    private String informId;

    /**
     * 证据缩略图
     */
    private String thumbnail;
    /**
     * 研判推送编号
     */
    private String judgementRecordId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgParentCode() {
        return orgParentCode;
    }

    public void setOrgParentCode(String orgParentCode) {
        this.orgParentCode = orgParentCode;
    }

    public String getOrgParentName() {
        return orgParentName;
    }

    public void setOrgParentName(String orgParentName) {
        this.orgParentName = orgParentName;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Long uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getEvidenceUrl() {
        return evidenceUrl;
    }

    public void setEvidenceUrl(String evidenceUrl) {
        this.evidenceUrl = evidenceUrl;
    }

    public Integer getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(Integer evidenceType) {
        this.evidenceType = evidenceType;
    }

    public String getEvidenceTypeName() {
        return evidenceTypeName;
    }

    public void setEvidenceTypeName(String evidenceTypeName) {
        this.evidenceTypeName = evidenceTypeName;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }

    public String getEvidenceDescription() {
        return evidenceDescription;
    }

    public void setEvidenceDescription(String evidenceDescription) {
        this.evidenceDescription = evidenceDescription;
    }

    public Long getFtpServerNumber() {
        return ftpServerNumber;
    }

    public void setFtpServerNumber(Long ftpServerNumber) {
        this.ftpServerNumber = ftpServerNumber;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    public String getOpenModeName() {
        return openModeName;
    }

    public void setOpenModeName(String openModeName) {
        this.openModeName = openModeName;
    }

    public String getInformId() { return informId; }

    public void setInformId(String informId) { this.informId = informId; }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getJudgementRecordId() { return judgementRecordId; }

    public void setJudgementRecordId(String judgementRecordId) { this.judgementRecordId = judgementRecordId; }
}
