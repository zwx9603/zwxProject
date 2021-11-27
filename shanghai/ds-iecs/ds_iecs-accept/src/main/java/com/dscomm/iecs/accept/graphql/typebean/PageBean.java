package com.dscomm.iecs.accept.graphql.typebean;

public class PageBean {

	private Boolean isByPage = false ;
	private int page = 1;
	private int size = 20 ;
	private int total=0;

	public Boolean getIsByPage() {
		return isByPage;
	}
	public void setIsByPage(Boolean isByPage) {
		this.isByPage = isByPage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
