package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.CourseHomework;

/**
 * 设置线上课程信息 - 获取详细信息（关联查询图片路径）
 * @author p0085398
 *
 */

public interface CourseCustomizeMapper {
	
	public List<Map<String,Object>> isExistName(Map<String,Object> params);
	
	public List<Map<String,Object>> findCourseHomeworkQuiz(Map<String,Object> params);
	
	public List<Map<String,Object>>  findCourseList(Map<String,Object> params);
	
	public List<Map<String,Object>> findCourseOfflineItems(Map<String,Object> params);
	
	public Integer publishCourse(Map<String,Object> params);
	public Integer cancelPublishCourse(Map<String,Object> params);
	public List<Map<String,Object>>  findCourseHomeworkList(Map<String,Object> params);
	public List<Map<String,Object>>  findCourseHomeworkManagerList(Map<String,Object> params);
	
	public Map<String,Object>  queryCourseHomeworkById(Map<String,Object> params);
	
	public Integer  updateCourseHomeworkByPrimaryKey(CourseHomework courseHomework);
	
	public List<Map<String,Object>>  findCourseNoticeList(Map<String,Object> params);
	
	public Map<String,Object>  queryCourseNoticeById(Map<String,Object> params);
	
	public List<Map<String,Object>>  findCourseMaterialList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findCourseQuizList(Map<String,Object> params);
	public List<Map<String,Object>>  findCourseTestQuizList(Map<String,Object> params);
	public List<Map<String,Object>>  getCourseQuizList(Map<String,Object> params);
	public List<Map<String,Object>>  findCourseQuizManagerList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findCourseSurveyList(Map<String,Object> params);
	public List<Map<String,Object>>  getCourseSurveyList(Map<String,Object> params);
	public List<Map<String,Object>>  findCourseSurveyManagerList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findCourseDataListByKeywords(Map<String,Object> params);
	
	public List<Map<String,Object>>  getCourseRelatedList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findOnlineCourseList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findOfflineCourseList(Map<String,Object> params);
	
	public Map<String,Object>  queryCourseRelatedById(Map<String,Object> params);
	
	public Integer numCourseTrainee(Map<String,Object> params);
	
	public Integer numCourseTraineeUserStatus(Map<String,Object> params);
	
	public List<Map<String,Object>>  findCourseShortcutList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findGroupCourseList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findGroupCourseSelectList(Map<String,Object> params);
	
	public List<Map<String,Object>>  findUnSelectedGroupCourseList(Map<String,Object> params);
	
	public List<Map<String,Object>>  getItemsByCreator(Map<String,Object> params);
	
	public List<Map<String,Object>>  getItemsChartByCreator(Map<String,Object> params);
	
	public List<Map<String,Object>>  findCourseViewerList(Map<String,Object> params);
	
	
	/**
	 * 查询学员课程详细信息
	 * @param params
	 * @return
	 */
	public Map<String,Object> queryTraineeCourseInfo(Map<String,Object> params);
	
	/**
	 * 查询学员课程属性信息
	 * @param params
	 * @return
	 */
	public Map<String,Object> queryCourseModelInfo(Map<String,Object> params);
	
	/**
	 * 查询学员课程章节信息
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryTraineeCourseItemInfo(Map<String,Object> params);
	
	/**
	 * 查询课程章节数 
	 * @param params
	 * @return
	 */
	public Integer queryCourseItemNum(Map<String,Object> params);
	
	/**
	 * 查询学员课程已完成章节数
	 * @param params
	 * @return
	 */
	public Integer queryTraineeCompleteCourseItemNum(Map<String,Object> params);
	
	/**
	 * 查询学员课程章节次序信息
	 * @param params
	 * @return
	 */
	public Map<String,Object> queryTraineeCourseItemSortInfo(Map<String,Object> params);
	
	/**
	 * 获取课程指定偏移量的课程
	 * @param params
	 * @return
	 */
	public Map<String,Object> queryCourseItemOffset(Map<String,Object> params);
	
	public List<Map<String,Object>>  findPackageCourseList(Map<String,Object> params);
	
	/**获取课程作业信息
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> findCourseHomeworkInfo(Map<String,Object> params);
}
	