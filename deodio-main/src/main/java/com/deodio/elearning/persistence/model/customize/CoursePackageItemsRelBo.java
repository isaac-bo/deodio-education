package com.deodio.elearning.persistence.model.customize;

import com.deodio.elearning.persistence.model.CoursePackageItemsRel;

public class CoursePackageItemsRelBo extends CoursePackageItemsRel{

	/**
	 * 操作标识  0：插入  1  更新  2删除
	 */
	private int operateType;
	
	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
} 
