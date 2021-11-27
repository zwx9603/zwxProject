package com.dscomm.iecs.garage.service.bean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述：值班汇总信息 由 值班信息实体 值班类型 以及机构 人员 关联组成 视图
 *
 */
public class OnDutySummaryBean extends BaseBean {

	private String organizationIntegrated; //单位一体化id

    private String organizationId; //机构Id

	private String organizationName;//机构名称

	private Long onDutyTime;//值班日期

	private String onDutyType;//值班类型

	private String onDutyName;  //岗位名称

	private String responsibilities;  //值班职责

	private String onDutyPersonId;//值班人id

	private String onDutyPersonNumber;//值班人编号

	private String onDutyPersonName;//值班人姓名

	private String contactNumber;//联系电话

	private String onDutyNumber;   //值班人次

	private Integer showNumber;    //显示序号

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Long getOnDutyTime() {
		return onDutyTime;
	}

	public void setOnDutyTime(Long onDutyTime) {
		this.onDutyTime = onDutyTime;
	}

	public String getOrganizationIntegrated() {
		return organizationIntegrated;
	}

	public void setOrganizationIntegrated(String organizationIntegrated) {
		this.organizationIntegrated = organizationIntegrated;
	}

	public String getOnDutyType() {
		return onDutyType;
	}

	public void setOnDutyType(String onDutyType) {
		this.onDutyType = onDutyType;
	}

	public String getOnDutyName() {
		return onDutyName;
	}

	public void setOnDutyName(String onDutyName) {
		this.onDutyName = onDutyName;
	}

	public String getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public String getOnDutyPersonId() {
		return onDutyPersonId;
	}

	public void setOnDutyPersonId(String onDutyPersonId) {
		this.onDutyPersonId = onDutyPersonId;
	}

	public String getOnDutyPersonNumber() {
		return onDutyPersonNumber;
	}

	public void setOnDutyPersonNumber(String onDutyPersonNumber) {
		this.onDutyPersonNumber = onDutyPersonNumber;
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

	public String getOnDutyNumber() {
		return onDutyNumber;
	}

	public void setOnDutyNumber(String onDutyNumber) {
		this.onDutyNumber = onDutyNumber;
	}

	public Integer getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(Integer showNumber) {
		this.showNumber = showNumber;
	}
}
