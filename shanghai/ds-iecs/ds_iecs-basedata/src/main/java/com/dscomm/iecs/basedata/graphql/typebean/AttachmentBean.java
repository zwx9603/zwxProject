package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：附件信息
 *
 */
public class AttachmentBean extends BaseBean {

    private String incidentId; // 案件ID

    private String relationObject;//关联对象

    private String relationId; // 关联ID

    private Long uploadTime ; //上传时间

    private String attachmentType;//附件类型

    private String attachmentTypeName;//附件类型

    private String  attachmentFileType;   //文件类型

    private String  attachmentFileTypeName; //文件类型名称

    private String  attachmentFileName;   // 文件名称

    private String  attachmentFileUrl;   // 路径

    private String  attachmentFileDesc;   // 描述

    private String organizationId; // 消防机构编号

    private String organizationName ; // 消防机构名称

    private String seatNumber; // 坐席号

    private String acceptancePersonNumber; // 接警员工号

    private String remarks; // 备注

    private String attachmentSuffix;// 文件后缀

    public String getAttachmentSuffix() {
        return attachmentSuffix;
    }

    public void setAttachmentSuffix(String attachmentSuffix) {
        this.attachmentSuffix = attachmentSuffix;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public String getAttachmentTypeName() {
        return attachmentTypeName;
    }

    public void setAttachmentTypeName(String attachmentTypeName) {
        this.attachmentTypeName = attachmentTypeName;
    }

    public String getAttachmentFileTypeName() {
        return attachmentFileTypeName;
    }

    public void setAttachmentFileTypeName(String attachmentFileTypeName) {
        this.attachmentFileTypeName = attachmentFileTypeName;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getRelationObject() {
        return relationObject;
    }

    public void setRelationObject(String relationObject) {
        this.relationObject = relationObject;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }


    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentFileType() {
        return attachmentFileType;
    }

    public void setAttachmentFileType(String attachmentFileType) {
        this.attachmentFileType = attachmentFileType;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public String getAttachmentFileUrl() {
        return attachmentFileUrl;
    }

    public void setAttachmentFileUrl(String attachmentFileUrl) {
        this.attachmentFileUrl = attachmentFileUrl;
    }

    public String getAttachmentFileDesc() {
        return attachmentFileDesc;
    }

    public void setAttachmentFileDesc(String attachmentFileDesc) {
        this.attachmentFileDesc = attachmentFileDesc;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }


}
