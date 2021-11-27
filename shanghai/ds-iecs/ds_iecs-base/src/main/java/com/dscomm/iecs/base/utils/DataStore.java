package com.dscomm.iecs.base.utils;

public class DataStore {

	/**
	 * 数据集
	 */
	private Object data;

	/**
	 * 查询项数据集（参数 和 分页条件）
	 */
	private Query query;

	public Object getData() {
		return data;
	}

	public Query getQuery() {
		return query;
	}

	public void setData(Object data) {
		this.data = data;
	}



	public void setQuery(Query query) {
		this.query = query;
	}

}
