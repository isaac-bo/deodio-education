package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.CourseOnliveItem;

//import com.deodio.elearning.persistence.model.CourseOnliveItem;

/**
 * 设置线上课程信息 - 获取详细信息（关联查询图片路径）
 * @author p0085398
 *
 */
public interface CourseModelOnliveCustomizeMapper {
	
//	public int insertCourseOnliveItem(List<CourseOnliveItem> items);
	
	public Map<String,Object> queryCourseOnliveInfoById(Map<String,Object> params); 
	
	public Map<String,Object> queryCourseOnliveProfileById(Map<String,Object> params); 
	
	public List<Map<String,Object>> queryCourseOnliveItem(Map<String,Object> params); 
	
	public int insertCourseOnliveItemBatch(List<CourseOnliveItem> list);
	
	public int updateCourseOnliveItemBatch(List<CourseOnliveItem> list);
	
	public int delCourseOnliveItemBatch(List<CourseOnliveItem> list);
}
	