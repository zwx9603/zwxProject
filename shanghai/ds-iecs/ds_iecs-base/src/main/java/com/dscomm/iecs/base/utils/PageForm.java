package com.dscomm.iecs.base.utils;

public class PageForm {
	/**
	 * 当前页
	 */
	public String currentPage;

	/**
	 * 页码
	 */
	public String pageSize;

	/**
	 * 结果总条数
	 */
	public String totalSize;

	public PageForm() {
		super();
	}

	public PageForm(String currentPage, String pageSize, String totalSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public String getTotalSize() {
		return totalSize;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}

}
