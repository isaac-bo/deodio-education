package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.TraineeExamRecordItem;
import com.deodio.elearning.persistence.model.TraineeExamRecordItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TraineeExamRecordItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    long countByExample(TraineeExamRecordItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int deleteByExample(TraineeExamRecordItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int insert(TraineeExamRecordItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int insertSelective(TraineeExamRecordItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    List<TraineeExamRecordItem> selectByExample(TraineeExamRecordItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    TraineeExamRecordItem selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int updateByExampleSelective(@Param("record") TraineeExamRecordItem record, @Param("example") TraineeExamRecordItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int updateByExample(@Param("record") TraineeExamRecordItem record, @Param("example") TraineeExamRecordItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int updateByPrimaryKeySelective(TraineeExamRecordItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trainee_exam_record_items
     *
     * @mbg.generated Thu May 17 13:56:26 CST 2018
     */
    int updateByPrimaryKey(TraineeExamRecordItem record);
}