package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 集结反馈查询参数
 */
public class RallyItemFeedbackQueryInputInfo {


	private String incidentId;// 事件ID

	private String commandId;// 指挥ID

	private String rallyPointId;// 集结点ID

	private String rallyItemId;// 集结项ID

	private String feedbackObjectId;// 反馈对象id

	private String keyword ; //关键字

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
