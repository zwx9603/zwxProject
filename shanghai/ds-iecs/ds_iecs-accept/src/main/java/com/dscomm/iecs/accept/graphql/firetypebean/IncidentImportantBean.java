package com.dscomm.iecs.accept.graphql.firetypebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:重大案件 标志信息
 */
public class IncidentImportantBean extends BaseBean {

	private Integer combatVehicleNum;//出动车辆数量维度

	private Integer deathsNumber ;//死亡人数

	private Integer deathsState ;//死亡人数标识

	private String disposalObject;// 处置对象维度

	private String incidentLevelCode;//案件等级维度

	private String incidentLevelName;// 案件等级名称

	private String incidentTypeCode;//案件类型维度

	private String incidentTypeName;// 案件类型名称

	private Integer injuredNumber ;//受伤人数

	private Integer injuredState;// 受伤标识

	private String keyword;//关键字维度

	private Long  overtime ;//超时时间 毫秒为计量单位

	private String title;// 模板标题

	private Integer trappedCount;// 人员被困数

	private Integer trappedState ;//人员被困标识

	public Integer getCombatVehicleNum() {
		return combatVehicleNum;
	}

	public void setCombatVehicleNum(Integer combatVehicleNum) {
		this.combatVehicleNum = combatVehicleNum;
	}

	public Integer getDeathsNumber() {
		return deathsNumber;
	}

	public void setDeathsNumber(Integer deathsNumber) {
		this.deathsNumber = deathsNumber;
	}

	public Integer getDeathsState() {
		return deathsState;
	}

	public void setDeathsState(Integer deathsState) {
		this.deathsState = deathsState;
	}

	public String getDisposalObject() {
		return disposalObject;
	}

	public void setDisposalObject(String disposalObject) {
		this.disposalObject = disposalObject;
	}

	public String getIncidentLevelCode() {
		return incidentLevelCode;
	}

	public void setIncidentLevelCode(String incidentLevelCode) {
		this.incidentLevelCode = incidentLevelCode;
	}

	public String getIncidentLevelName() {
		return incidentLevelName;
	}

	public void setIncidentLevelName(String incidentLevelName) {
		this.incidentLevelName = incidentLevelName;
	}

	public String getIncidentTypeCode() {
		return incidentTypeCode;
	}

	public void setIncidentTypeCode(String incidentTypeCode) {
		this.incidentTypeCode = incidentTypeCode;
	}

	public String getIncidentTypeName() {
		return incidentTypeName;
	}

	public void setIncidentTypeName(String incidentTypeName) {
		this.incidentTypeName = incidentTypeName;
	}

	public Integer getInjuredNumber() {
		return injuredNumber;
	}

	public void setInjuredNumber(Integer injuredNumber) {
		this.injuredNumber = injuredNumber;
	}

	public Integer getInjuredState() {
		return injuredState;
	}

	public void setInjuredState(Integer injuredState) {
		this.injuredState = injuredState;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getOvertime() {
		return overtime;
	}

	public void setOvertime(Long overtime) {
		this.overtime = overtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTrappedCount() {
		return trappedCount;
	}

	public void setTrappedCount(Integer trappedCount) {
		this.trappedCount = trappedCount;
	}

	public Integer getTrappedState() {
		return trappedState;
	}

	public void setTrappedState(Integer trappedState) {
		this.trappedState = trappedState;
	}
}
