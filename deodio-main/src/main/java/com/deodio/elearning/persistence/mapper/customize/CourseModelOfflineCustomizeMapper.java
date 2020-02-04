package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.CourseOfflineItem;

/**
 * 设置线下课程信息 - 获取详细信息（关联查询图片路径）
 * @author p0085398
 *
 */
public interface CourseModelOfflineCustomizeMapper {
	
	public int insertCourseOfflineItemBatch(List<CourseOfflineItem> list);
	
	public int updateCourseOfflineItemBatch(List<CourseOfflineItem> list);
	
	public int delCourseOfflineItemBatch(List<CourseOfflineItem> list);
	
	public Map<String,Object> queryCourseOfflineInfo(Map<String,Object> params); 
	
	public Map<String,Object> queryCourseOfflineProfile(Map<String,Object> params); 
	
	/**
	 * @param params  courseId  accountId  stepNo
	 * @return
	 */
	public List<Map<String,Object>> queryCourseOfflineItem(Map<String,Object> params); 
	
	/**
	 * 分页查询课程对应培训人员信息
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> findCourseOfflineTrainees(Map<String,Object> params); 
	public String getCourseTrainLocation(Map<String,Object> params); 
	public List<Map<String,Object>> getCourseTrainTime(Map<String,Object> params); 
	public int getCourseOfflineItemCount(Map<String,Object> params);
	public int queryCourseOfflineContentTraineeSum(Map<String,Object> params);
}
	