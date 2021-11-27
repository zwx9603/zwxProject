package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述：安全提示接收者
 */
public class SecurityReceiverInputInfo {

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

	private String remarks; //备注

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
