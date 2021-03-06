package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CourseHomework;
import com.deodio.elearning.persistence.model.CourseHomeworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseHomeworkMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int countByExample(CourseHomeworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int deleteByExample(CourseHomeworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int insert(CourseHomework record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int insertSelective(CourseHomework record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	List<CourseHomework> selectByExample(CourseHomeworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	CourseHomework selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int updateByExampleSelective(@Param("record") CourseHomework record,
			@Param("example") CourseHomeworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int updateByExample(@Param("record") CourseHomework record, @Param("example") CourseHomeworkExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int updateByPrimaryKeySelective(CourseHomework record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_homework
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	int updateByPrimaryKey(CourseHomework record);
}