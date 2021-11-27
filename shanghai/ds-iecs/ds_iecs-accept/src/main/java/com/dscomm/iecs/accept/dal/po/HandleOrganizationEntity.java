package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述: 调派信息 （ 调派 单位 信息 ）
 *
 */
@Entity
@Table(name = "JCJ_CJXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HandleOrganizationEntity extends BaseEntity {

    @Column(name = "AJID", length = 100)
    private String incidentId ; //案件ID

    @Column(name = "CJJLID", length = 100)
    private String handleId;// 处警记录ID

    @Column(name = "YSAJBH", length = 100 )
    private String originalIncidentNumber ;  //原始案件编号


    @Column(name = "JSDW", length = 100)
    private String organization; //todo 字段 消防机构 接收单位

//    @Column(name = "XFJGBH", length = 100)
    @Column(name = "JSDW_TYWYSBM", length = 100)
    private String organizationId; //todo 字段 消防机构编号 接收单位 通用标识

    @Column(name = "JSDW_DWMC", length = 100)
    private String organizationName; //todo 字段 消防机构名称 接收单位名称

    @Column(name = "TZSJ")
    private Long noticeTime;// 通知时间

    @Column(name = "XTFKSJ")
    private Long systemFeedbackTime;// 系统反馈时间

//    @Column(name = "RGFKSJ")
    @Column(name = "JS_RQSJ")
    private Long personFeedbackPersonTime;// todo 字段 人工反馈时间 接收_日期时间

    @Column(name = "ZLDZT", length = 100)
    private String handlePoliceStatus ;// 指令状态 （处警信息状态）

    @Column(name = "FKYHBH", length = 100)
    private String feedbackPersonId;// 反馈用户编号

    @Column(name = "FKRXM", length = 100)
    private String feedbackPersonName;// 反馈人姓名

    @Column(name = "BZ", length = 800)
    private String remarks;//备注

    @Column(name = "CSRWZXSJ")
    private Long overTimeHandleTime ; //新增 超时签收任务执行时间

    @Column(name = "TTSBFWJLJ", length = 400 )
    private String speakToFileUrl ; //新增 tts播放文件路径

    public String getSpeakToFileUrl() {
        return speakToFileUrl;
    }

    public void setSpeakToFileUrl(String speakToFileUrl) {
        this.speakToFileUrl = speakToFileUrl;
    }

    public Long getOverTimeHandleTime() {
        return overTimeHandleTime;
    }

    public void setOverTimeHandleTime(Long overTimeHandleTime) {
        this.overTimeHandleTime = overTimeHandleTime;
    }

    public String getOriginalIncidentNumber() {
        return originalIncidentNumber;
    }

    public void setOriginalIncidentNumber(String originalIncidentNumber) {
        this.originalIncidentNumber = originalIncidentNumber;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Long getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Long noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Long getSystemFeedbackTime() {
        return systemFeedbackTime;
    }

    public void setSystemFeedbackTime(Long systemFeedbackTime) {
        this.systemFeedbackTime = systemFeedbackTime;
    }

    public Long getPersonFeedbackPersonTime() {
        return personFeedbackPersonTime;
    }

    public void setPersonFeedbackPersonTime(Long personFeedbackPersonTime) {
        this.personFeedbackPersonTime = personFeedbackPersonTime;
    }

    public String getHandlePoliceStatus() {
        return handlePoliceStatus;
    }

    public void setHandlePoliceStatus(String handlePoliceStatus) {
        this.handlePoliceStatus = handlePoliceStatus;
    }

    public String getFeedbackPersonName() {
        return feedbackPersonName;
    }

    public void setFeedbackPersonName(String feedbackPersonName) {
        this.feedbackPersonName = feedbackPersonName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFeedbackPersonId() {
        return feedbackPersonId;
    }

    public void setFeedbackPersonId(String feedbackPersonId) {
        this.feedbackPersonId = feedbackPersonId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
