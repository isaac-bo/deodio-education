package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.PresentationDiscussion;
import com.deodio.elearning.persistence.model.PresentationDiscussionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PresentationDiscussionMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int countByExample(PresentationDiscussionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int deleteByExample(PresentationDiscussionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int insert(PresentationDiscussion record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int insertSelective(PresentationDiscussion record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	List<PresentationDiscussion> selectByExample(PresentationDiscussionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	PresentationDiscussion selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int updateByExampleSelective(@Param("record") PresentationDiscussion record,
			@Param("example") PresentationDiscussionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int updateByExample(@Param("record") PresentationDiscussion record,
			@Param("example") PresentationDiscussionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int updateByPrimaryKeySelective(PresentationDiscussion record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	int updateByPrimaryKey(PresentationDiscussion record);
}