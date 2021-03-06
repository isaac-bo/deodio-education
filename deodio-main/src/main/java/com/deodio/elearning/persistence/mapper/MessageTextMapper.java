package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.MessageText;
import com.deodio.elearning.persistence.model.MessageTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageTextMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int countByExample(MessageTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int deleteByExample(MessageTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int deleteByPrimaryKey(String messageTextId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int insert(MessageText record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int insertSelective(MessageText record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	List<MessageText> selectByExample(MessageTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	MessageText selectByPrimaryKey(String messageTextId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int updateByExampleSelective(@Param("record") MessageText record, @Param("example") MessageTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int updateByExample(@Param("record") MessageText record, @Param("example") MessageTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int updateByPrimaryKeySelective(MessageText record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_message_text
	 * @mbggenerated  Wed Feb 22 15:10:44 CST 2017
	 */
	int updateByPrimaryKey(MessageText record);
}