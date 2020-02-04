package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.CoursePackage;
import com.deodio.elearning.persistence.model.CoursePackageItemsRel;

public class CoursePackageBo extends CoursePackage{

	private List<CoursePackageSeriesBo> series;
	
	private List<CoursePackageItemsRel> itemsRel;
	
	public List<CoursePackageSeriesBo> getSeries() {
		return series;
	}

	public void setSeries(List<CoursePackageSeriesBo> series) {
		this.series = series;
	}

	public List<CoursePackageItemsRel> getItemsRel() {
		return itemsRel;
	}

	public void setItemsRel(List<CoursePackageItemsRel> itemsRel) {
		this.itemsRel = itemsRel;
	}
	
	
} 
