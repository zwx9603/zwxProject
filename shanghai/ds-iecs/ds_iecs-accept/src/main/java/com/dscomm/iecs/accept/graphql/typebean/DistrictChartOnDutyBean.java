package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 值守力量bean(支队)
 *
 *
 * */
public class DistrictChartOnDutyBean {

	private Integer vehicleNum;// 车辆数
	private Integer fullTimeNum;// 企业专职队数
	private Integer organizationNum;//执勤中队数
	private Integer fireStationNum;// 微型消防站数量
//	private String name;// 辖区名称
	private List<DistrictChartBean> districtChartBean;
	public Integer getVehicleNum() {
		return vehicleNum;
	}
	public void setVehicleNum(Integer vehicleNum) {
		this.vehicleNum = vehicleNum;
	}
	public Integer getFullTimeNum() {
		return fullTimeNum;
	}
	public void setFullTimeNum(Integer fullTimeNum) {
		this.fullTimeNum = fullTimeNum;
	}

	public Integer getOrganizationNum() {
		return organizationNum;
	}

	public void setOrganizationNum(Integer organizationNum) {
		this.organizationNum = organizationNum;
	}

	//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public List<DistrictChartBean> getDistrictChartBean() {
		return districtChartBean;
	}
	public void setDistrictChartBean(List<DistrictChartBean> districtChartBean) {
		this.districtChartBean = districtChartBean;
	}
	public Integer getFireStationNum() {
		return fireStationNum;
	}
	public void setFireStationNum(Integer fireStationNum) {
		this.fireStationNum = fireStationNum;
	}
	
	
}
