package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.CourseOnlineItem;

/**
 * 设置线上课程信息 - 获取详细信息（关联查询图片路径）
 * @author p0085398
 *
 */
public interface CourseModelOnlineCustomizeMapper {
	
	public int insertCourseOnlineItem(List<CourseOnlineItem> items);
	
	public Map<String,Object> queryCourseOnlineInfoById(Map<String,Object> params); 
	
	public Map<String,Object> queryCourseOnlineProfileById(Map<String,Object> params); 
	
	public List<Map<String,Object>> queryCourseOnlineItem(Map<String,Object> params); 
	//获取课程引用课件列表
	public List<Map<String, Object>> findQuoteCoursewareList(Map<String,Object> params);
}
	