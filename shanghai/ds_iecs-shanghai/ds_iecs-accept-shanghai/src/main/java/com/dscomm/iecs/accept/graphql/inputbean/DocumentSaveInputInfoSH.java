package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;

import java.util.List;

/**
 * 描述：文书保存修改参数
 *
 */
public class DocumentSaveInputInfoSH {

	private  String id ; //主键

	private String incidentId;//案件ID

	private String dateSourceId ; //数据来源id

	private String type;//文书类型

	private String typeSubitemCode; //文书类型 子项

	private String title ; //标题

	private String content;//文书内容

	private String feedbackPerson;//反馈人姓名

	private Long feedbackTime;//  反馈_日期时间

	private String feedbackOrganizationId;//反馈消防机构代码

	private String terminalId ;// 终端ID

	private String remarks;//备注

	private Integer whetherUpdate = 0  ; //是否可以修改 0 不可以修改 1 可以修改 默认不可以修改

	private List<AttachmentSaveInputInfo> attachmentSaveInputInfos;

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}


	public Long getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getTypeSubitemCode() {
		return typeSubitemCode;
	}

	public void setTypeSubitemCode(String typeSubitemCode) {
		this.typeSubitemCode = typeSubitemCode;
	}

	public List<AttachmentSaveInputInfo> getAttachmentSaveInputInfos() {
		return attachmentSaveInputInfos;
	}

	public void setAttachmentSaveInputInfos(List<AttachmentSaveInputInfo> attachmentSaveInputInfos) {
		this.attachmentSaveInputInfos = attachmentSaveInputInfos;
	}
}
