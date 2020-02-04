package com.deodio.elearning.persistence.model.customize;

import com.deodio.elearning.persistence.model.CoursePackageItemsElementsRel;

public class CoursePackageItemsElementsRelBo extends CoursePackageItemsElementsRel{

	private String courseName;
	
	/**
	 * 操作标识  0：插入  1  更新  2删除
	 */
	private int operateType;
	
	private int courseType;
	
	private String createName;

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public int getCourseType() {
		return courseType;
	}

	public void setCourseType(int courseType) {
		this.courseType = courseType;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
} 
