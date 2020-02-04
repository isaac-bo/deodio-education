package com.deodio.components.pagination.page;

import java.util.List;

public class PageData<T> {
	
	private PageRequest pageRequest;
	
	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}

	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageSize() {
		return this.pageRequest.getPageSize();
	}

	public int getCurrePage() {
		return this.pageRequest.getPageNo();
	}

	public int getTotalRow() {
		return this.pageRequest.getTotalRow();
	}

	public int getTotalPage() {
		return this.pageRequest.getPageTotal();
	}

}
