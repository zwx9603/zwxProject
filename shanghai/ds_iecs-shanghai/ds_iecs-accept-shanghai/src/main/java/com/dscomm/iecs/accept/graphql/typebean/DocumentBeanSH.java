package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;

import java.util.List;

/**
 * 描述：文书表
 *
 */
public class DocumentBeanSH extends BaseBean {

	private String incidentId;//案件ID

	private String dateSourceId ; //数据来源id

	private String type;//文书类型

	private String typeName;//文书类型名称

	private String typeSubitemCode; //文书类型 子项编码

	private String typeSubitemName; //文书类型 子项名称

	private String title ; //标题

	private String content;//文书内容

	private Long recordTime;//记录文书时间

	private Long feedbackTime;//  反馈_日期时间

	private String feedbackOrganization;// 反馈消防机构 反馈机构

	private String feedbackPerson;//反馈人姓名

	private String feedbackOrganizationId;//反馈消防机构代码

	private String feedbackOrganizationName;//反馈消防机构名称

	private String terminalId ;// 终端ID

	private String remarks;//备注

	private Integer whetherUpdate   ; //是否可以修改 0 不可以修改 1 可以修改 默认不可以修改

	private List<AttachmentBean> attachmentBeans;//附件信息

	public String getDateSourceId() {
		return dateSourceId;
	}

	public void setDateSourceId(String dateSourceId) {
		this.dateSourceId = dateSourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWhetherUpdate() {
		return whetherUpdate;
	}

	public void setWhetherUpdate(Integer whetherUpdate) {
		this.whetherUpdate = whetherUpdate;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Long recordTime) {
		this.recordTime = recordTime;
	}

	public String getFeedbackPerson() {
		return feedbackPerson;
	}

	public void setFeedbackPerson(String feedbackPerson) {
		this.feedbackPerson = feedbackPerson;
	}

	public String getFeedbackOrganizationId() {
		return feedbackOrganizationId;
	}

	public void setFeedbackOrganizationId(String feedbackOrganizationId) {
		this.feedbackOrganizationId = feedbackOrganizationId;
	}

	public String getFeedbackOrganizationName() {
		return feedbackOrganizationName;
	}

	public void setFeedbackOrganizationName(String feedbackOrganizationName) {
		this.feedbackOrganizationName = feedbackOrganizationName;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getFeedbackOrganization() {
		return feedbackOrganization;
	}

	public void setFeedbackOrganization(String feedbackOrganization) {
		this.feedbackOrganization = feedbackOrganization;
	}

	public String getTypeSubitemCode() {
		return typeSubitemCode;
	}

	public void setTypeSubitemCode(String typeSubitemCode) {
		this.typeSubitemCode = typeSubitemCode;
	}

	public String getTypeSubitemName() {
		return typeSubitemName;
	}

	public void setTypeSubitemName(String typeSubitemName) {
		this.typeSubitemName = typeSubitemName;
	}

	public List<AttachmentBean> getAttachmentBeans() {
		return attachmentBeans;
	}

	public void setAttachmentBeans(List<AttachmentBean> attachmentBeans) {
		this.attachmentBeans = attachmentBeans;
	}
}
