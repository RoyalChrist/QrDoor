package com.yan.utils;

import java.util.List;

public class PageBean {
	// 分页 属性：当前第几页 currentPage 每页显示数量 pageSize 总共多少页 pageCount
	// =rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
	// 总共行数 rowCount 是否有上一页 isBefore 是否有下一页 isNext 数据 List
	private int currentPage = 1;
	private int pageSize = 5;
	private int rowCount;
	private int pageCount;
	private boolean isBefore;
	private boolean isNext;
	private List list;

	public PageBean(int c, int p) {
		currentPage = c;
		pageSize = p;
	}

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

	public int getRowCount() {
		return rowCount;
	}

	// 计算 总页数
	public void setRowCount(int rowCount) {
		pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount
				/ pageSize + 1;
		// 第几页 每页显示多少
		// 是否有前一页 是否有后一页
		isBefore = false;
		isNext = false;
		// 只有一页
		if (currentPage == pageCount && pageCount == 1) {
			isBefore = false;
			isNext = false;
		}
		// 有前一页 没有下一页
		else if (currentPage == pageCount && pageCount != 1) {
			isBefore = true;
			isNext = false;
		} else if (currentPage != pageCount && currentPage == 1) {
			isBefore = false;
			isNext = true;
		} else if (currentPage != pageCount && currentPage != 1) {
			isBefore = true;
			isNext = true;
		}
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public boolean getIsBefore() {
		return isBefore;
	}

	public void setBefore(boolean isBefore) {
		this.isBefore = isBefore;
	}

	public boolean getIsNext() {
		return isNext;
	}

	public void setNext(boolean isNext) {
		this.isNext = isNext;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
