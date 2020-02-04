package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.QuizExminationAnswer;
import com.deodio.elearning.persistence.model.QuizExminationAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuizExminationAnswerMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int countByExample(QuizExminationAnswerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int deleteByExample(QuizExminationAnswerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int insert(QuizExminationAnswer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int insertSelective(QuizExminationAnswer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	List<QuizExminationAnswer> selectByExample(
			QuizExminationAnswerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	QuizExminationAnswer selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int updateByExampleSelective(@Param("record") QuizExminationAnswer record,
			@Param("example") QuizExminationAnswerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int updateByExample(@Param("record") QuizExminationAnswer record,
			@Param("example") QuizExminationAnswerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int updateByPrimaryKeySelective(QuizExminationAnswer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_quiz_exmination_answer
	 * @mbggenerated  Mon Jun 13 11:12:48 CST 2016
	 */
	int updateByPrimaryKey(QuizExminationAnswer record);
}