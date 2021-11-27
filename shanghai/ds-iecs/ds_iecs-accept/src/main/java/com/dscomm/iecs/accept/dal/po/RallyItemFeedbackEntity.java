package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 集结项（集结力量）反馈表
 */
@Entity
@Table(name = "T_COC_FIRE_JJLLFK")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RallyItemFeedbackEntity extends BaseEntity {

	@Column(name = "SJID"   , length = 100 )
	private String incidentId;// 事件ID

	@Column(name = "ZHID" , length = 100)
	private String commandId;// 指挥ID

	@Column(name = "JJDID" , length = 100 )
	private String rallyPointId;// 集结点ID

	@Column(name="JJXID", length = 100)
	private String rallyItemId;// 集结项ID

	@Column(name = "FKDXID", length = 100)
	private String feedbackObjectId;// 反馈对象id

	@Column(name = "FKDXMC", length = 200)
	private String feedbackObjectName;// 反馈对象名称

	@Column(name = "FKDWBM", length = 100)
	private String feedbackOrganizationId;// 反馈单位编码

	@Column(name = "FKDWMC", length = 200)
	private String feedbackOrganizationName ;// 反馈单位名称

	@Column(name = "TXR", length = 100)
	private String writerName;// 填写人

	@Column(name="FKNR" ,length = 4000)
	private String feedbackContent;// 反馈内容

	@Column(name = "FKSJ")
	private Long feedbackTime;// 反馈时间

	@Column(name="TXT1",length = 800)
	private String customcredit1;// 保留字段1

	@Column(name="TXT2",length = 800)
	private String customcredit2;// 保留字段2

	@Column(name="BZ",length = 800)
	private String remarks;// 备注


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

	public Long getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
}
