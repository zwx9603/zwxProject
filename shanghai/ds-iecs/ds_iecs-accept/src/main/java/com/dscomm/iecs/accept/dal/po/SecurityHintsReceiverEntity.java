package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：安全提示接收者，受令者
 *@author YangFuXi
 *   Date: 2018年5月4日  下午3:08:49
 *
 */
@Entity
@Table(name="T_COC_FIRE_AQTSDX")
public class SecurityHintsReceiverEntity extends BaseEntity {

	@Column(name="SJID" ,length=100)
	private String incidentId;//事件ID

	@Column(name="ZHID" ,length=100)
	private String commandId;//指挥ID

	@Column(name="QATSID",length=100)
	private String securityHintsId;//安全提示ID

	@Column(name="SLDXLX",length=100)
	private String receiverType;//受令对象类型
	/**
	 *     ORGANIZATION("ORGANIZATION","机构"),
	 *     VEHICLE("VEHICLE","车辆"),
	 *     PERSON("PERSON","人员"),
	 *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
	 *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
	 *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
	 */
	@Column(name="SLDXID",length=100)
	private String receiverId;//受令对象ID

	@Column(name="TZSJ" )
	private Long notificationTime ;//通知时间

	@Column(name="QSSJ" )
	private Long signTime;//签收时间

	@Column(name = "BZ",length=800)
    private String remarks;


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

	public String getSecurityHintsId() {
		return securityHintsId;
	}

	public void setSecurityHintsId(String securityHintsId) {
		this.securityHintsId = securityHintsId;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public Long getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(Long notificationTime) {
		this.notificationTime = notificationTime;
	}

	public Long getSignTime() {
		return signTime;
	}

	public void setSignTime(Long signTime) {
		this.signTime = signTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
