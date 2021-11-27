package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：安全提示接收者
 *@author YangFuXi
 *   Date: 2018年5月3日  下午3:06:35
 *
 */
public class SecurityReceiverBean extends BaseBean {

	private String incidentId;//事件ID

	private String commandId;//指挥ID

	private String securityHintsId;//安全提示ID

	private String receiverId;//接收者ID

	private String receiverType;//接收者类型
	/**
	 *     ORGANIZATION("ORGANIZATION","机构"),
	 *     VEHICLE("VEHICLE","车辆"),
	 *     PERSON("PERSON","人员"),
	 *     SAFEGUARDUNIT("SAFEGUARDUNIT","联勤单位"),
	 *     EMERGENCYUNIT("EMERGENCYUNIT","联动单位"),
	 *     TERMINAL_EQUIPMENT("TERMINAL_EQUIPMENT","终端设备"),
	 */

	private Long notificationTime ;//通知时间

	private Long signTime;//签收时间

	private String remarks; //备注

	private  SecurityBean securityBean ; //安全提示

	public SecurityBean getSecurityBean() {
		return securityBean;
	}

	public void setSecurityBean(SecurityBean securityBean) {
		this.securityBean = securityBean;
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

	public String getSecurityHintsId() {
		return securityHintsId;
	}

	public void setSecurityHintsId(String securityHintsId) {
		this.securityHintsId = securityHintsId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
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
