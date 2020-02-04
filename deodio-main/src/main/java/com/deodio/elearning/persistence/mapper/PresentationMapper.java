package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PresentationMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int countByExample(PresentationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int deleteByExample(PresentationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int insert(Presentation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int insertSelective(Presentation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	List<Presentation> selectByExample(PresentationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	Presentation selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int updateByExampleSelective(@Param("record") Presentation record, @Param("example") PresentationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int updateByExample(@Param("record") Presentation record, @Param("example") PresentationExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int updateByPrimaryKeySelective(Presentation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation
	 * @mbggenerated  Thu Jul 26 15:00:54 CST 2018
	 */
	int updateByPrimaryKey(Presentation record);
}