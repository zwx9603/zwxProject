package com.dscomm.iecs.accept.graphql.inputbean;


import com.dscomm.iecs.accept.graphql.typebean.PageBean;

/**
 * 描述：联勤联动单位info
 *
 */
public class OrgUnitInfo {

	private String type;
	private String incidentId;//案件id

	//查询分页
	private PageBean pageBean ;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
