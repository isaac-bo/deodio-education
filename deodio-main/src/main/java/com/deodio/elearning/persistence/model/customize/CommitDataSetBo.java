package com.deodio.elearning.persistence.model.customize;

import java.util.List;

public class CommitDataSetBo<T>{

	private List<T> insertList;
	
	private List<T> updateList;
	
	private List<T> deleteList;

	public List<T> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<T> insertList) {
		this.insertList = insertList;
	}

	public List<T> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<T> updateList) {
		this.updateList = updateList;
	}

	public List<T> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<T> deleteList) {
		this.deleteList = deleteList;
	}
	
} 
