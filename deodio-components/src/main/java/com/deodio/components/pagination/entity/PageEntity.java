package com.deodio.components.pagination.entity;

import com.deodio.components.pagination.annotation.Paging;
import com.deodio.components.pagination.page.Page;

@Paging(field = "pagination")
public class PageEntity {

	private Page pagination;

	public Page getPagination() {
		return pagination;
	}

	public void setPagination(Page pagination) {
		this.pagination = pagination;
	}

}
