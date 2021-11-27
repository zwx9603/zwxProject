package com.dscomm.iecs.basedata.graphql.typebean;


import java.util.List;

/**
 * 描述：值班汇总信息 由 值班信息实体 值班类型 以及机构 人员 关联组成 视图
 *
 */
public class OnDutySummaryOrganizationBean   {


    private String organizationId; //机构Id

	private String organizationName;//机构名称

	//单位值班信息
	private List<OnDutySummaryBean> onDutySummaryOrganizations ;

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

	public List<OnDutySummaryBean> getOnDutySummaryOrganizations() {
		return onDutySummaryOrganizations;
	}

	public void setOnDutySummaryOrganizations(List<OnDutySummaryBean> onDutySummaryOrganizations) {
		this.onDutySummaryOrganizations = onDutySummaryOrganizations;
	}
}
