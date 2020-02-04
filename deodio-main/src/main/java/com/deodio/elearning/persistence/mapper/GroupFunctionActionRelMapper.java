package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.GroupFunctionActionRel;
import com.deodio.elearning.persistence.model.GroupFunctionActionRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupFunctionActionRelMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int countByExample(GroupFunctionActionRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int deleteByExample(GroupFunctionActionRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int insert(GroupFunctionActionRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int insertSelective(GroupFunctionActionRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	List<GroupFunctionActionRel> selectByExample(GroupFunctionActionRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	GroupFunctionActionRel selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int updateByExampleSelective(@Param("record") GroupFunctionActionRel record,
			@Param("example") GroupFunctionActionRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int updateByExample(@Param("record") GroupFunctionActionRel record,
			@Param("example") GroupFunctionActionRelExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int updateByPrimaryKeySelective(GroupFunctionActionRel record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_group_function_action_rel
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	int updateByPrimaryKey(GroupFunctionActionRel record);
}