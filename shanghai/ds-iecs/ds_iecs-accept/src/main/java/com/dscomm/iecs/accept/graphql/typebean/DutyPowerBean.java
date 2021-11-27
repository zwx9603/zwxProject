package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.basedata.graphql.typebean.EquipmentVehicleBean;

import java.util.List;

/**
 * 描述：值守力量统计（中队、专职队）
 * 
 * @author chenliping
 *
 */
public class DutyPowerBean {

	private String organizationName;//中队名
	private List<EquipmentVehicleBean> vehicleBean;//车辆信息
	private Integer vechileNum;// 车辆数


	public Integer getVechileNum() {
		return vechileNum;
	}

	public void setVechileNum(Integer vechileNum) {
		this.vechileNum = vechileNum;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<EquipmentVehicleBean> getVehicleBean() {
		return vehicleBean;
	}

	public void setVehicleBean(List<EquipmentVehicleBean> vehicleBean) {
		this.vehicleBean = vehicleBean;
	}
}
