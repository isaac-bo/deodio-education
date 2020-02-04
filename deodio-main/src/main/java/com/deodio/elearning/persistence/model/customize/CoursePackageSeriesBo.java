package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.deodio.elearning.persistence.model.CoursePackageSeries;

public class CoursePackageSeriesBo extends CoursePackageSeries {

	/**
	 * 操作标识  0：插入  1  更新  2删除
	 */
	private int operateType;
	
	private String seriesId;
	
	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	private List<CoursePackageItemsBo> items;

	public List<CoursePackageItemsBo> getItems() {
		return items;
	}

	public void setItems(List<CoursePackageItemsBo> items) {
		this.items = items;
	}	

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.seriesId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof CoursePackageSeriesBo)){
			return false;
		}
		CoursePackageSeriesBo seriesBo = (CoursePackageSeriesBo)obj;
		return this.seriesId.equals(seriesBo.getSeriesId());
	}
	
	
	
} 
