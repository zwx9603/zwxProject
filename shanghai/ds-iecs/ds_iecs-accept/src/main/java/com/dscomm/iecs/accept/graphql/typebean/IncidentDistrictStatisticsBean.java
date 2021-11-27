package com.dscomm.iecs.accept.graphql.typebean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：辖区警情类型同
 *
 */
public class IncidentDistrictStatisticsBean {

	private Long dimensionCount; //总数

	private List<DimensionAssembleCodeNameStatisticsBean> dimensionAssembleBeanList = new ArrayList<DimensionAssembleCodeNameStatisticsBean>(); //辖区警情统计

	public Long getDimensionCount() {
		return dimensionCount;
	}

	public void setDimensionCount(Long dimensionCount) {
		this.dimensionCount = dimensionCount;
	}

	public List<DimensionAssembleCodeNameStatisticsBean> getDimensionAssembleBeanList() {
		return dimensionAssembleBeanList;
	}

	public void setDimensionAssembleBeanList(List<DimensionAssembleCodeNameStatisticsBean> dimensionAssembleBeanList) {
		this.dimensionAssembleBeanList = dimensionAssembleBeanList;
	}
}
