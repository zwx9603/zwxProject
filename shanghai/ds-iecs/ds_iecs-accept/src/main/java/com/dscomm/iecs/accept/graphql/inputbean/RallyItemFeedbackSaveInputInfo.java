package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 集结反馈保存修改参数
 */
public class RallyItemFeedbackSaveInputInfo {

	private String id ;

	private String incidentId;// 事件ID

	private String commandId;// 指挥ID

	private String rallyPointId;// 集结点ID

	private String rallyItemId;// 集结项ID

	private String feedbackObjectId;// 反馈对象id

	private String feedbackObjectName;// 反馈对象名称

	private String feedbackOrganizationId;// 反馈单位编码

	private String feedbackOrganizationName ;// 反馈单位名称

	private String writerName;// 填写人

	private String feedbackContent;// 反馈内容

	private String customcredit1;// 保留字段1

	private String customcredit2;// 保留字段2

	private String remarks;// 备注


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

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getRallyPointId() {
		return rallyPointId;
	}

	public void setRallyPointId(String rallyPointId) {
		this.rallyPointId = rallyPointId;
	}

	public String getRallyItemId() {
		return rallyItemId;
	}

	public void setRallyItemId(String rallyItemId) {
		this.rallyItemId = rallyItemId;
	}

	public String getFeedbackObjectId() {
		return feedbackObjectId;
	}

	public void setFeedbackObjectId(String feedbackObjectId) {
		this.feedbackObjectId = feedbackObjectId;
	}

	public String getFeedbackObjectName() {
		return feedbackObjectName;
	}

	public void setFeedbackObjectName(String feedbackObjectName) {
		this.feedbackObjectName = feedbackObjectName;
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

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public String getCustomcredit1() {
		return customcredit1;
	}

	public void setCustomcredit1(String customcredit1) {
		this.customcredit1 = customcredit1;
	}

	public String getCustomcredit2() {
		return customcredit2;
	}

	public void setCustomcredit2(String customcredit2) {
		this.customcredit2 = customcredit2;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
