package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CoursePackageItemsRel;
import com.deodio.elearning.persistence.model.CoursePackageItemsRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoursePackageItemsRelMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int countByExample(CoursePackageItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int deleteByExample(CoursePackageItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int insert(CoursePackageItemsRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int insertSelective(CoursePackageItemsRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	List<CoursePackageItemsRel> selectByExample(CoursePackageItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	CoursePackageItemsRel selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int updateByExampleSelective(@Param("record") CoursePackageItemsRel record,
			@Param("example") CoursePackageItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int updateByExample(@Param("record") CoursePackageItemsRel record,
			@Param("example") CoursePackageItemsRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int updateByPrimaryKeySelective(CoursePackageItemsRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	int updateByPrimaryKey(CoursePackageItemsRel record);
}