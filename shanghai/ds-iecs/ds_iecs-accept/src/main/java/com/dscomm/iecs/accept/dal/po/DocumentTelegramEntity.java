package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 文电信息
 *
 */
@Entity
@Table(name = "JCJ_WDXX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DocumentTelegramEntity extends BaseEntity {

    @Column(name = "AJID", length = 100 )
    private String incidentId;//案件ID

	@Column(name = "LX", length = 100 )
	private String type;//文电类型

	@Column(name = "JSXFJGDM", length = 100 )
	private String receiveOrganizationId; //接收消防机构代码

	@Column(name = "FSXFJGDM", length = 100 )
	private String sendOrganizationId; //发送消防机构代码

	@Column(name = "JSZ", length = 100 )
	private String receivePerson; //接收人

	@Column(name = "FSZ", length = 100 )
	private String sendPerson; //发送人

	@Column(name = "ZLNR", length = 4000)
	private String content;//内容

	@Column(name = "FSSJ")
	private Long sendTime;//发送时间

	@Column(name = "BZ", length = 800)
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
}
