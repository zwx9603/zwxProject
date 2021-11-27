package com.dscomm.iecs.basedata.graphql.inputbean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：值班汇总信息 更新值班人 值班联系电话
 *
 */
public class OnDutySummarySaveInputInfo   {

	private String id; // 值班记录id

	private String onDutyPersonId;//值班人id

	private String onDutyPersonName;//值班人姓名

	private String contactNumber;//联系电话

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnDutyPersonId() {
		return onDutyPersonId;
	}

	public void setOnDutyPersonId(String onDutyPersonId) {
		this.onDutyPersonId = onDutyPersonId;
	}

	public String getOnDutyPersonName() {
		return onDutyPersonName;
	}

	public void setOnDutyPersonName(String onDutyPersonName) {
		this.onDutyPersonName = onDutyPersonName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
