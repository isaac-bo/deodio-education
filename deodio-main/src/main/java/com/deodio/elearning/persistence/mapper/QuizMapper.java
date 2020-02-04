package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.Quiz;
import com.deodio.elearning.persistence.model.QuizExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuizMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	long countByExample(QuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int deleteByExample(QuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int insert(Quiz record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int insertSelective(Quiz record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	List<Quiz> selectByExample(QuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	Quiz selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int updateByExampleSelective(@Param("record") Quiz record, @Param("example") QuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int updateByExample(@Param("record") Quiz record, @Param("example") QuizExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int updateByPrimaryKeySelective(Quiz record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz
	 * @mbg.generated  Thu Jun 07 23:31:35 CST 2018
	 */
	int updateByPrimaryKey(Quiz record);
}