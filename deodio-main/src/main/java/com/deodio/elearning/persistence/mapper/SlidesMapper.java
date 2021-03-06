package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.Slides;
import com.deodio.elearning.persistence.model.SlidesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlidesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int countByExample(SlidesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int deleteByExample(SlidesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int insert(Slides record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int insertSelective(Slides record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	List<Slides> selectByExample(SlidesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	Slides selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int updateByExampleSelective(@Param("record") Slides record, @Param("example") SlidesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int updateByExample(@Param("record") Slides record, @Param("example") SlidesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int updateByPrimaryKeySelective(Slides record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	int updateByPrimaryKey(Slides record);
}