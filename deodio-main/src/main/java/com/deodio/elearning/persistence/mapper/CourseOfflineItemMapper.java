package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CourseOfflineItem;
import com.deodio.elearning.persistence.model.CourseOfflineItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseOfflineItemMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int countByExample(CourseOfflineItemExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int deleteByExample(CourseOfflineItemExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int insert(CourseOfflineItem record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int insertSelective(CourseOfflineItem record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	List<CourseOfflineItem> selectByExample(CourseOfflineItemExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	CourseOfflineItem selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int updateByExampleSelective(@Param("record") CourseOfflineItem record,
			@Param("example") CourseOfflineItemExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int updateByExample(@Param("record") CourseOfflineItem record, @Param("example") CourseOfflineItemExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int updateByPrimaryKeySelective(CourseOfflineItem record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_offline_item
	 * @mbggenerated  Tue Jul 10 16:39:17 CST 2018
	 */
	int updateByPrimaryKey(CourseOfflineItem record);
}