package com.deodio.elearning.persistence.model.customize;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.elearning.persistence.model.Course;

/**  
    * @ClassName: CourseDto  
    * @Description: 课程实体类 
    * @author P0113869  
    * @date 2018年4月18日 上午9:56:08  
    *    
    */
public class CourseDto extends Course {
	
	private String attachUrl;
	
	private String nickName;
	
	private Short itemType;
	
	private String tabType;
	
	private PageRequest pagination;
	
	private String[] classificationIdList;
	
	private String[] tagIdList;

	public String getAttachUrl() {
		return attachUrl;
	}

	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public Short getItemType() {
		return itemType;
	}

	public void setItemType(Short itemType) {
		this.itemType = itemType;
	}

	public String getTabType() {
		return tabType;
	}

	public void setTabType(String tabType) {
		this.tabType = tabType;
	}

	public PageRequest getPagination() {
		return pagination;
	}

	public void setPagination(PageRequest pagination) {
		this.pagination = pagination;
	}

	public String[] getClassificationIdList() {
		return classificationIdList;
	}

	public void setClassificationIdList(String[] classificationIdList) {
		this.classificationIdList = classificationIdList;
	}

	public String[] getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(String[] tagIdList) {
		this.tagIdList = tagIdList;
	}
	
}
