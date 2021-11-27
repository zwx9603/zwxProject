package com.dscomm.iecs.accept.graphql.typebean;

import java.util.List;

/**
 * 描述：值守力量bean(大队)
 * 
 * @author chenliping
 *
 */
public class DistrictChartBean {

//	private String incidentId; // 案件ID
//	private Long startTime;// 开始时间
//	private Long endTime;// 结束时间
//	private Integer vehicleNum;// 大队出车数
//	private Integer orgNum;//大队机构数
	private String districtName;// 辖区名称
	private String districtCode;// 辖区代码
	private String organizationId;//机构ID
	private String organizationName;//机构名称
	private Integer organizationNum;//执勤中队数
	private Integer fullTimeNum;// 专职队数量
	private Integer fireStationNum;// 微型消防站数量
	private Integer vechileNum;// 车辆数
	private List<DutyPowerBean> dutyPowerBean;


	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
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

	public Integer getOrganizationNum() {
		return organizationNum;
	}

	public void setOrganizationNum(Integer organizationNum) {
		this.organizationNum = organizationNum;
	}

	public Integer getFullTimeNum() {
		return fullTimeNum;
	}

	public void setFullTimeNum(Integer fullTimeNum) {
		this.fullTimeNum = fullTimeNum;
	}

	public Integer getFireStationNum() {
		return fireStationNum;
	}

	public void setFireStationNum(Integer fireStationNum) {
		this.fireStationNum = fireStationNum;
	}

	public Integer getVechileNum() {
		return vechileNum;
	}

	public void setVechileNum(Integer vechileNum) {
		this.vechileNum = vechileNum;
	}

	public List<DutyPowerBean> getDutyPowerBean() {
		return dutyPowerBean;
	}

	public void setDutyPowerBean(List<DutyPowerBean> dutyPowerBean) {
		this.dutyPowerBean = dutyPowerBean;
	}
}
