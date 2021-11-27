package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:重要案件 规则信息
 */
@Entity
@Table(name="T_COC_FIRE_ZYAJBZXX")
public class IncidentImportantRuleEntity extends BaseEntity {

	@Column(name="MBBT" , length=200)
	private String title ; //模板标题

	@Column(name="SFQYMB")
	private Integer  whetherEnable ;//是否启用模板 0 不启用  1启用

	@Column(name="AJLXWD" , length=100)
	private String incidentTypeCode;//案件类型维度

	@Column(name="AJDJWD" , length=400)
	private String incidentLevelCode;//案件等级维度


	@Column(name="AJCZDXWD" , length=100)
	private String disposalObject ; //处置对象

	@Column(name="BKBZ")
	private Integer trappedEnable ;//人员被困启用标识 0不启用 1 启用
	@Column(name = "RYBKWD" )
	private Integer trappedNumber;// 人员被困数

	@Column(name = "SSBZ" )
	private Integer injuredEnable;// 人员受伤启用标识  0不启用 1 启用
	@Column(name="RYSSWD")
	private Integer injuredNumber ;//受伤人数

	@Column(name="SWBZ")
	private Integer deathsEnable ;//人员死亡启用标识  0不启用 1 启用
	@Column(name="RYSWWD")
	private Integer deathsNumber ;//死亡人数

	@Column(name="CDCLSLWD" )
	private Integer combatVehicleNum;//出动车辆数量维度

	@Column(name="GZJWD" ,length=400)
	private String keyword;//关键字维度

	@Column(name="CSSJWD")
	private Long  overtime ;//超时时间 毫秒为计量单位






	public String getIncidentTypeCode() {
		return incidentTypeCode;
	}

	public void setIncidentTypeCode(String incidentTypeCode) {
		this.incidentTypeCode = incidentTypeCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIncidentLevelCode() {
		return incidentLevelCode;
	}

	public void setIncidentLevelCode(String incidentLevelCode) {
		this.incidentLevelCode = incidentLevelCode;
	}

	public String getDisposalObject() {
		return disposalObject;
	}

	public void setDisposalObject(String disposalObject) {
		this.disposalObject = disposalObject;
	}

	public Integer getCombatVehicleNum() {
		return combatVehicleNum;
	}

	public void setCombatVehicleNum(Integer combatVehicleNum) {
		this.combatVehicleNum = combatVehicleNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getWhetherEnable() {
		return whetherEnable;
	}

	public void setWhetherEnable(Integer whetherEnable) {
		this.whetherEnable = whetherEnable;
	}

	public Integer getTrappedEnable() {
		return trappedEnable;
	}

	public void setTrappedEnable(Integer trappedEnable) {
		this.trappedEnable = trappedEnable;
	}

	public Integer getTrappedNumber() {
		return trappedNumber;
	}

	public void setTrappedNumber(Integer trappedNumber) {
		this.trappedNumber = trappedNumber;
	}

	public Integer getInjuredEnable() {
		return injuredEnable;
	}

	public void setInjuredEnable(Integer injuredEnable) {
		this.injuredEnable = injuredEnable;
	}

	public Integer getInjuredNumber() {
		return injuredNumber;
	}

	public void setInjuredNumber(Integer injuredNumber) {
		this.injuredNumber = injuredNumber;
	}

	public Integer getDeathsEnable() {
		return deathsEnable;
	}

	public void setDeathsEnable(Integer deathsEnable) {
		this.deathsEnable = deathsEnable;
	}

	public Integer getDeathsNumber() {
		return deathsNumber;
	}

	public void setDeathsNumber(Integer deathsNumber) {
		this.deathsNumber = deathsNumber;
	}

	public Long getOvertime() {
		return overtime;
	}

	public void setOvertime(Long overtime) {
		this.overtime = overtime;
	}
}
