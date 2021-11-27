package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述： 文电信息
 *
 */
public class DocumentTelegramBean extends BaseBean {

    private String incidentId;//案件ID

	private String type;//文电类型

	private String receiveOrganizationId; //接收消防机构代码

	private String receiveOrganizationName; //接收消防机构名称

	private String sendOrganizationId; //发送消防机构代码

	private String sendOrganizationName; //发送消防机构名称

	private String receivePerson; //接收人

	private String sendPerson; //发送人

	private String content;//内容

	private Long sendTime;//发送时间

	private String remarks;//备注

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

	public String getReceiveOrganizationId() {
		return receiveOrganizationId;
	}

	public void setReceiveOrganizationId(String receiveOrganizationId) {
		this.receiveOrganizationId = receiveOrganizationId;
	}

	public String getSendOrganizationId() {
		return sendOrganizationId;
	}

	public void setSendOrganizationId(String sendOrganizationId) {
		this.sendOrganizationId = sendOrganizationId;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getSendPerson() {
		return sendPerson;
	}

	public void setSendPerson(String sendPerson) {
		this.sendPerson = sendPerson;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReceiveOrganizationName() {
		return receiveOrganizationName;
	}

	public void setReceiveOrganizationName(String receiveOrganizationName) {
		this.receiveOrganizationName = receiveOrganizationName;
	}

	public String getSendOrganizationName() {
		return sendOrganizationName;
	}

	public void setSendOrganizationName(String sendOrganizationName) {
		this.sendOrganizationName = sendOrganizationName;
	}
}
