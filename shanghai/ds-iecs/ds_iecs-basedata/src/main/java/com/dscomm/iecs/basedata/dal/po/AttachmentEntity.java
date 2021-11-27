package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：附件信息
 *
 */
@Entity
@Table(name = "JCJ_FJXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AttachmentEntity extends BaseEntity {

    @Column(name = "AJID", length = 100)
    private String incidentId; // 案件ID

    @Column(name = "GLDX", length = 100)
    private String relationObject;//关联对象

    @Column(name = "GLID", length = 100)
    private String relationId; // 关联ID

    @Column(name = "SCSJ" )
    private Long uploadTime ; //附件 上传时间


    @Column(name = "FJLX" , length = 100)
    private String attachmentType;//附件类型: 参考  部局规范 电子文件类型代码

    @Column(name = "WJLX", length = 100)
    private String  attachmentFileType;   //文件类型 参考  部局规范   图片格式类型代码表

    @Column(name = "WJMC", length = 400)
    private String  attachmentFileName;   // 文件名称

    @Column(name = "WJHZ" , length = 100)
    private String attachmentSuffix;// 文件后缀

    @Column(name = "URL", length = 1000)
    private String  attachmentFileUrl;   // 路径

    @Column(name = "MS", length = 1000)
    private String  attachmentFileDesc;   // 描述

    @Column(name = "XFJGBH", length = 100)
    private String organizationId; // 消防机构编号

    @Column(name = "ZXH", length = 100)
    private String seatNumber; // 坐席号

    @Column(name = "JJYGH", length = 100)
    private String acceptancePersonNumber; // 接警员工号

    @Column(name = "BZ", length = 800 )
    private String remarks; // 备注

    public Long getUploadTime() {
        return uploadTime;
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

    public String getAttachmentSuffix() {
        return attachmentSuffix;
    }

    public void setAttachmentSuffix(String attachmentSuffix) {
        this.attachmentSuffix = attachmentSuffix;
    }
}
