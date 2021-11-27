package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述：集结项保存修改参数
 * 
 */
public class RallyItemSaveInputInfo {

	private String id;// 主键

	private String rallyPowerId;// 集结项Id

	private String rallyPowerType;// 集结项类型   VEHICLE( 车辆 ) PERSON( 人员 ) EQUIPMENT( 装备 ) ORGANIZATION( 机构 ) ;

	private String rallyPowerName;// 集结项名称

	private String organizationId;// 所属消防机构ID

	private String organizationName;// 所属消防机构名称

	private String customcredit1;// 保留字段1

	private String customcredit2;// 保留字段2

	private String remarks;// 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRallyPowerId() {
		return rallyPowerId;
	}

	public void setRallyPowerId(String rallyPowerId) {
		this.rallyPowerId = rallyPowerId;
	}

	public String getRallyPowerType() {
		return rallyPowerType;
	}

	public void setRallyPowerType(String rallyPowerType) {
		this.rallyPowerType = rallyPowerType;
	}

	public String getRallyPowerName() {
		return rallyPowerName;
	}

	public void setRallyPowerName(String rallyPowerName) {
		this.rallyPowerName = rallyPowerName;
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
}
