package com.dscomm.iecs.accept.graphql.typebean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：子机构警情分类统计
 *
 */
public class OrganizationDisasterStatisticsBean {

	private String organizationName; //机构名称

	private String organizationId; //机构id

	private Long dimensionCount; //案件数量

	private List<DimensionAssembleBean> dimensionAssembleBeanList = new ArrayList<DimensionAssembleBean>(); //案件分类统计

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Long getDimensionCount() {
		return dimensionCount;
	}

	public void setDimensionCount(Long dimensionCount) {
		this.dimensionCount = dimensionCount;
	}

	public List<DimensionAssembleBean> getDimensionAssembleBeanList() {
		return dimensionAssembleBeanList;
	}

	public void setDimensionAssembleBeanList(List<DimensionAssembleBean> dimensionAssembleBeanList) {
		this.dimensionAssembleBeanList = dimensionAssembleBeanList;
	}
}
