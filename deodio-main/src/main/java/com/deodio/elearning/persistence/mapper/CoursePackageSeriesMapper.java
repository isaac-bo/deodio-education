package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CoursePackageSeries;
import com.deodio.elearning.persistence.model.CoursePackageSeriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoursePackageSeriesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int countByExample(CoursePackageSeriesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int deleteByExample(CoursePackageSeriesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int insert(CoursePackageSeries record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int insertSelective(CoursePackageSeries record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	List<CoursePackageSeries> selectByExample(CoursePackageSeriesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	CoursePackageSeries selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByExampleSelective(@Param("record") CoursePackageSeries record,
			@Param("example") CoursePackageSeriesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByExample(@Param("record") CoursePackageSeries record,
			@Param("example") CoursePackageSeriesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByPrimaryKeySelective(CoursePackageSeries record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_series
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	int updateByPrimaryKey(CoursePackageSeries record);
}