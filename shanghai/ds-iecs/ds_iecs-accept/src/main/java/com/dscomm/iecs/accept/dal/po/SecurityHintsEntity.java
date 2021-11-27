package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：安全提示类
 *@author YangFuXi
 *   Date: 2018年5月4日  下午1:05:47
 *
 */
@Entity
@Table(name="T_COC_FIRE_AQTS")
public class SecurityHintsEntity extends BaseEntity {

	@Column(name="SJID",length=100 )
	private String incidentId;//事件ID

	@Column(name="ZHID",length=100 )
	private String commandId;//指挥ID

	@Column(name="BT",length=200)
	private String title;//标题

	@Column(name="TSNR",length=4000)
	private String content;//提示内容

	@Column(name="TZSJ" )
	private Long notificationTime ;//通知时间

	@Column(name="YXSJ" )
	private Long effectiveTime;//有效时间

	@Column(name="FSRXM",length=100)
	private String senderName;//发送人姓名

	@Column(name="TXR",length=100)
	private String writerId;//填写人ID

	@Column(name = "BZ",length=800 )
    private String remarks;			//备注

	public Long getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(Long notificationTime) {
		this.notificationTime = notificationTime;
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

	public Long getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Long effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
