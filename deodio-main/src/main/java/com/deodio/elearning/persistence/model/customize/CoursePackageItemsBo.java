package com.deodio.elearning.persistence.model.customize;

import com.deodio.elearning.persistence.model.CoursePackageItems;

public class CoursePackageItemsBo extends CoursePackageItems{

	private String seriesName;
	
	private String itemId;
	
	private short seriesOrder;
	
	/**
	 * 操作标识  0：插入  1  更新  2删除
	 */
	private int operateType;
	
	private String elementsName;

	public String getElementsName() {
		return elementsName;
	}

	public void setElementsName(String elementsName) {
		this.elementsName = elementsName;
	}

	public short getSeriesOrder() {
		return seriesOrder;
	}

	public void setSeriesOrder(short seriesOrder) {
		this.seriesOrder = seriesOrder;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	} 
} 
