package com.dscomm.iecs.accept.service.bean.cad;

import java.io.Serializable;

/**
 * 描述 : 附件资料bo
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/9/29 17:39
 */
public class AttachmentBO implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 关联业务ID
     */
    private String relateBusinessId;
    /**
     * 关联业务类型（表名）. 事件核实历史记录 案事件信息 调派单 指令反馈单 领导批示
     */
    private String relateBusinessType;
    /**
     * 指挥实例id
     */
    private String commandInstanceId;
    /**
     * 事件单id
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
     * 附件名称
     */
    private String attachmentName;
    /**
     * 附件扩展名
     */
    private String attachmentNameExtension;
    /**
     * 附件url全路径
     */
    private String url;
    /**
     * ftp服务器编号
     */
    private Long ftpServerNumber;
    /**
     * 附件资料来源. 10-图像监控 20-情报研判 30-现场上传 40-本地上传 50-领导终端
     */
    private String attachmentSource;
    /**
     * 附件资料来源name
     */
    private String attachmentSourceName;
    /**
     * 附件类型: 0:其他，1:音频 , 2:视频 ,3:图片,4:文档
     */
    private String attachmentType;
    /**
     * 附件类型name
     */
    private String attachmentTypeName;
    /**
     * 描述
     */
    private String describe;
    /**
     * 上传单位编码
     */
    private String uploadUnitCode;
    /**
     * 上传单位名称
     */
    private String uploadUnitName;
    /**
     * 上传人工号
     */
    private String uploadPersonCode;
    /**
     * 上传人姓名
     */
    private String uploadPersonName;
    /**
     * 上传时间
     */
    private Long uploadTime;
    /**
     * 附件收集人工号
     */
    private String attachmentCollectorCode;
    /**
     * 附件收集人姓名
     */
    private String attachmentCollectorName;
    /**
     * 收集时间
     */
    private Long CollectTime;
    /**
     * 打开方式
     */
    private String openMode;
    /**
     * 打开方式name
     */
    private String openModeName;
    /**
     * 时间戳
     */
    private Long sjc;
    /**
     * 有效性
     */
    private Integer validState;
    /**
     * 有效性name
     */
    private String validStateName;
    /**
     * 扩展字段1
     */
    private String extendedField1;
    /**
     * 扩展字段2
     */
    private String extendedField2;
    /**
     * 上传系统(民警终端、领导终端、科所队、接警台、证据管理等)
     */
    private String uploadSystem;
    /**
     * 上传设备号(接处警台号，终端IMEI号等)
     */
    private String uploadDeviceNumber;
    /**
     * 附件编号
     */
    private String attachmentNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelateBusinessId() {
        return relateBusinessId;
    }

    public void setRelateBusinessId(String relateBusinessId) {
        this.relateBusinessId = relateBusinessId;
    }

    public String getRelateBusinessType() {
        return relateBusinessType;
    }

    public void setRelateBusinessType(String relateBusinessType) {
        this.relateBusinessType = relateBusinessType;
    }

    public String getCommandInstanceId() {
        return commandInstanceId;
    }

    public void setCommandInstanceId(String commandInstanceId) {
        this.commandInstanceId = commandInstanceId;
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

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentNameExtension() {
        return attachmentNameExtension;
    }

    public void setAttachmentNameExtension(String attachmentNameExtension) {
        this.attachmentNameExtension = attachmentNameExtension;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getFtpServerNumber() {
        return ftpServerNumber;
    }

    public void setFtpServerNumber(Long ftpServerNumber) {
        this.ftpServerNumber = ftpServerNumber;
    }

    public String getAttachmentSource() {
        return attachmentSource;
    }

    public void setAttachmentSource(String attachmentSource) {
        this.attachmentSource = attachmentSource;
    }

    public String getAttachmentSourceName() {
        return attachmentSourceName;
    }

    public void setAttachmentSourceName(String attachmentSourceName) {
        this.attachmentSourceName = attachmentSourceName;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentTypeName() {
        return attachmentTypeName;
    }

    public void setAttachmentTypeName(String attachmentTypeName) {
        this.attachmentTypeName = attachmentTypeName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUploadUnitCode() {
        return uploadUnitCode;
    }

    public void setUploadUnitCode(String uploadUnitCode) {
        this.uploadUnitCode = uploadUnitCode;
    }

    public String getUploadUnitName() {
        return uploadUnitName;
    }

    public void setUploadUnitName(String uploadUnitName) {
        this.uploadUnitName = uploadUnitName;
    }

    public String getUploadPersonCode() {
        return uploadPersonCode;
    }

    public void setUploadPersonCode(String uploadPersonCode) {
        this.uploadPersonCode = uploadPersonCode;
    }

    public String getUploadPersonName() {
        return uploadPersonName;
    }

    public void setUploadPersonName(String uploadPersonName) {
        this.uploadPersonName = uploadPersonName;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getAttachmentCollectorCode() {
        return attachmentCollectorCode;
    }

    public void setAttachmentCollectorCode(String attachmentCollectorCode) {
        this.attachmentCollectorCode = attachmentCollectorCode;
    }

    public String getAttachmentCollectorName() {
        return attachmentCollectorName;
    }

    public void setAttachmentCollectorName(String attachmentCollectorName) {
        this.attachmentCollectorName = attachmentCollectorName;
    }

    public Long getCollectTime() {
        return CollectTime;
    }

    public void setCollectTime(Long collectTime) {
        CollectTime = collectTime;
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

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }

    public Integer getValidState() {
        return validState;
    }

    public void setValidState(Integer validState) {
        this.validState = validState;
    }

    public String getValidStateName() {
        return validStateName;
    }

    public void setValidStateName(String validStateName) {
        this.validStateName = validStateName;
    }

    public String getExtendedField1() {
        return extendedField1;
    }

    public void setExtendedField1(String extendedField1) {
        this.extendedField1 = extendedField1;
    }

    public String getExtendedField2() {
        return extendedField2;
    }

    public void setExtendedField2(String extendedField2) {
        this.extendedField2 = extendedField2;
    }

    public String getUploadSystem() {
        return uploadSystem;
    }

    public void setUploadSystem(String uploadSystem) {
        this.uploadSystem = uploadSystem;
    }

    public String getUploadDeviceNumber() {
        return uploadDeviceNumber;
    }

    public void setUploadDeviceNumber(String uploadDeviceNumber) {
        this.uploadDeviceNumber = uploadDeviceNumber;
    }

    public String getAttachmentNumber() {
        return attachmentNumber;
    }

    public void setAttachmentNumber(String attachmentNumber) {
        this.attachmentNumber = attachmentNumber;
    }
}
