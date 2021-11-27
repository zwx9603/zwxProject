package com.dscomm.iecs.basedata.graphql.inputbean;

/**
 * 描述：附件信息 保存参数
 *
 */
public class AttachmentSaveInputInfo  {

    private String id ; //附件信息id

    private String incidentId; // 案件ID

    private String relationObject;//关联对象

    private String relationId; // 关联ID

    private String attachmentType;//附件类型

    private String  attachmentFileType;   //文件类型

    private String  attachmentFileName;   // 文件名称

    private String  attachmentFileUrl;   // 路径

    private String  attachmentFileDesc;   // 描述

    private String organizationId; // 消防机构编号

    private String seatNumber; // 坐席号

    private String acceptancePersonNumber; // 接警员工号

    private String remarks; // 备注


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
