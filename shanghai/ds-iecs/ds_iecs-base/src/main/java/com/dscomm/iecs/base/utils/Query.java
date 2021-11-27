package com.dscomm.iecs.base.utils;

import java.util.Map;

public class Query {

	/**
	 * 分页条件相关集合
	 */
	private PageForm pageForm;

	/**
	 * 参数相关集合 格式为key:value结构
	 */
	private Map<String, Object> queryForm;

	public Query() {
		super();
	}

	public Query(Map<String, Object> queryForm, PageForm pageForm) {
		super();
		this.queryForm = queryForm;
		this.pageForm = pageForm;
	}

	public PageForm getPageForm() {
		return pageForm;
	}

	public Map<String, Object> getQueryForm() {
		return queryForm;
	}

	public void setPageForm(PageForm pageForm) {
		this.pageForm = pageForm;
	}

	public void setQueryForm(Map<String, Object> queryForm) {
		this.queryForm = queryForm;
	}

}
