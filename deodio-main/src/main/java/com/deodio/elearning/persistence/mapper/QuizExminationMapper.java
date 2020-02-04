package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.QuizExmination;
import com.deodio.elearning.persistence.model.QuizExminationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuizExminationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int countByExample(QuizExminationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int deleteByExample(QuizExminationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int insert(QuizExmination record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int insertSelective(QuizExmination record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    List<QuizExmination> selectByExample(QuizExminationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    QuizExmination selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int updateByExampleSelective(@Param("record") QuizExmination record, @Param("example") QuizExminationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int updateByExample(@Param("record") QuizExmination record, @Param("example") QuizExminationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int updateByPrimaryKeySelective(QuizExmination record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quiz_exmination
     *
     * @mbggenerated Sun Jun 12 21:39:29 CST 2016
     */
    int updateByPrimaryKey(QuizExmination record);
}