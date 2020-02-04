package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CourseQuiz;
import com.deodio.elearning.persistence.model.CourseQuizExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CourseQuizMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int countByExample(CourseQuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int deleteByExample(CourseQuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int insert(CourseQuiz record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int insertSelective(CourseQuiz record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	List<CourseQuiz> selectByExample(CourseQuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	CourseQuiz selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int updateByExampleSelective(@Param("record") CourseQuiz record, @Param("example") CourseQuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int updateByExample(@Param("record") CourseQuiz record, @Param("example") CourseQuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int updateByPrimaryKeySelective(CourseQuiz record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_quiz
	 * @mbggenerated  Sat Oct 08 17:02:15 CST 2016
	 */
	int updateByPrimaryKey(CourseQuiz record);

	void delAllCourseQuizByCourseId(Map<String, Object> params);
}