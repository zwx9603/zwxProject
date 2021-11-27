package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.DistrictChartBean;
import com.dscomm.iecs.accept.graphql.typebean.DistrictChartOnDutyBean;

/**
 * 描述：值守力量
 *
 * @author chenliping
 */
public interface DistrictChartService {

	/**
	 * 支队值守力量
	 *
	 * */
	DistrictChartOnDutyBean findDistrictChartOnDutyBean();

	/**
	 * 大队值守力量
	 * @param orgId 机构id
	 * @return 该机构下全部值勤车辆中队微站专属队统计+该机构下全部辖区力量统计
	 */
	DistrictChartBean findDistrictChartBean(String orgId);

//	/**
//	 *
//	 * */
//
//	/**
//	 * 值守力量
//	 * @param orgId 大/中队机构id
//	 * @return 值勤车辆中队微站专属队统计
//	 * */
//	DutyPowerBean findDutyPower(String orgId);
}
