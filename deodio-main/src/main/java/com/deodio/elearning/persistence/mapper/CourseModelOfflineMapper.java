package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CourseModelOffline;
import com.deodio.elearning.persistence.model.CourseModelOfflineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseModelOfflineMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int countByExample(CourseModelOfflineExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int deleteByExample(CourseModelOfflineExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int insert(CourseModelOffline record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int insertSelective(CourseModelOffline record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	List<CourseModelOffline> selectByExample(CourseModelOfflineExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	CourseModelOffline selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int updateByExampleSelective(@Param("record") CourseModelOffline record,
			@Param("example") CourseModelOfflineExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int updateByExample(@Param("record") CourseModelOffline record,
			@Param("example") CourseModelOfflineExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int updateByPrimaryKeySelective(CourseModelOffline record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	int updateByPrimaryKey(CourseModelOffline record);
}