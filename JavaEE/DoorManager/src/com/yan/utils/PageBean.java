package com.yan.utils;

import java.util.List;

public class PageBean {
	// ��ҳ ���ԣ���ǰ�ڼ�ҳ currentPage ÿҳ��ʾ���� pageSize �ܹ�����ҳ pageCount
	// =rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
	// �ܹ����� rowCount �Ƿ�����һҳ isBefore �Ƿ�����һҳ isNext ���� List
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

	// ���� ��ҳ��
	public void setRowCount(int rowCount) {
		pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount
				/ pageSize + 1;
		// �ڼ�ҳ ÿҳ��ʾ����
		// �Ƿ���ǰһҳ �Ƿ��к�һҳ
		isBefore = false;
		isNext = false;
		// ֻ��һҳ
		if (currentPage == pageCount && pageCount == 1) {
			isBefore = false;
			isNext = false;
		}
		// ��ǰһҳ û����һҳ
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
