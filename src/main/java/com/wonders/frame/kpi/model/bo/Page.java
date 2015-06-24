package com.wonders.frame.kpi.model.bo;

public class Page {	
	private int currentPage = 1;	
	private int pageSize = 15;		
//	private int begin;
//	private int end;
	private int rows;

	private int totalPage;	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotalPage() {  
		if(rows%pageSize == 0) {
			totalPage = rows/pageSize;
		} else {
			totalPage = rows/pageSize+1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
//	public int getBegin() {
//		begin = (currentPage-1)*pageSize;
//		return begin;
//	}
//	public void setBegin(int begin) {
//		this.begin = begin;
//	}
//	public int getEnd() {
//		end = currentPage*pageSize+1;
//		return end;
//	}
//	public void setEnd(int end) {
//		this.end = end;
//	}
//	public int getCurrentPage() {
//		return currentPage;
//	}
//	public void setCurrentPage(int currentPage) {
//		this.currentPage = currentPage;
//	}
//	public int getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}