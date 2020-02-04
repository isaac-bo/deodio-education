package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	long countByExample(CourseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int deleteByExample(CourseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int insert(Course record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int insertSelective(Course record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	List<Course> selectByExample(CourseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	Course selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int updateByPrimaryKeySelective(Course record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course
	 * @mbg.generated  Tue Jul 10 14:30:23 CST 2018
	 */
	int updateByPrimaryKey(Course record);
}