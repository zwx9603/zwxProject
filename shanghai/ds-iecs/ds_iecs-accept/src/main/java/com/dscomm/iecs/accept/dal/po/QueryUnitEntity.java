package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：单位查询
 *
 * @author chenliping 
 */
@Entity
@Table(name="V_E_LINKAGEUNIT")
public class QueryUnitEntity extends BaseEntity {

	@Column(name="UNITNAME")
	private String unitName;//单位名称
	@Column(name="UNITADDR")
	private String unitAddr;//单位地址
	@Column(name="CONTACTS")
	private String contacts;//联系人
	@Column(name="MOBILEPHONE")
	private String mobilePhone;//联系电话
	@Column(name="UNITTYPE")
	private String unitType;//emergencyunit（应急联动）或safeguardunit（联勤保障）
	@Column(name="CONTEXT")
	private String context;//单位描述
	@Column(name="bznl")
	private String supportability;//保障能力
	@Column(name="gis_x")
	private Double longitude;//经度
	@Column(name="gis_y")
	private Double latitude;//维度
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitAddr() {
		return unitAddr;
	}
	public void setUnitAddr(String unitAddr) {
		this.unitAddr = unitAddr;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getSupportability() {
		return supportability;
	}
	public void setSupportability(String supportability) {
		this.supportability = supportability;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
	
}
