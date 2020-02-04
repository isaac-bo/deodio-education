package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.CoursePackageItems;
import com.deodio.elearning.persistence.model.CoursePackageItemsElementsRel;
import com.deodio.elearning.persistence.model.CoursePackageItemsRel;
import com.deodio.elearning.persistence.model.CoursePackageSeries;
import com.deodio.elearning.persistence.model.customize.CoursePackageItemsBo;
import com.deodio.elearning.persistence.model.customize.CoursePackageItemsElementsRelBo;

public interface CoursePackageCustomizeMapper {
	
	List<CoursePackageItemsBo> queryCoursePackageItemsBo(Map<String,Object> params);
	
	List<CoursePackageItemsElementsRelBo> queryCoursePackageItemsElementsRelBo(Map<String,String> params);
	
	public int insertCoursePackageSeriesBatch(List<CoursePackageSeries> list);
	
	public int updateCoursePackageSeriesBatch(List<CoursePackageSeries> list);
	
	public int delCoursePackageSeriesBatch(Map<String,Object> params);
	
	public int insertCoursePackageItemsBatch(List<CoursePackageItems> list);
	
	public int updateCoursePackageItemsBatch(List<CoursePackageItems> list);
	
	public int delCoursePackageItemsBatch(Map<String,Object> params);
	
	public int insertCoursePackageItemsRelBatch(List<CoursePackageItemsRel> list);
	
	public int updateCoursePackageItemsRelBatch(List<CoursePackageItemsRel> list);
	
	public int delCoursePackageItemsRelBatch(List<CoursePackageItemsRel> list);
	
	public int insertCoursePackageItemsElementsRelBatch(List<CoursePackageItemsElementsRel> list);
	
	public int updateCoursePackageItemsElementsRelBatch(List<CoursePackageItemsElementsRel> list);
	
	public int delCoursePackageItemsElementsRelBatch(Map<String,Object> params);
	
	public List<Map<String,Object>> findCoursePackageList(Map<String,Object> params);

	public Integer publishCoursePackage(Map<String, Object> params);
}