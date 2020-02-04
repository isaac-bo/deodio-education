package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CoursePackage;
import com.deodio.elearning.persistence.model.CoursePackageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoursePackageMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int countByExample(CoursePackageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int deleteByExample(CoursePackageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int insert(CoursePackage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int insertSelective(CoursePackage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	List<CoursePackage> selectByExample(CoursePackageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	CoursePackage selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByExampleSelective(@Param("record") CoursePackage record, @Param("example") CoursePackageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByExample(@Param("record") CoursePackage record, @Param("example") CoursePackageExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByPrimaryKeySelective(CoursePackage record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByPrimaryKey(CoursePackage record);
}