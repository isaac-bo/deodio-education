package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.PresentationSyncSurvey;
import com.deodio.elearning.persistence.model.PresentationSyncSurveyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PresentationSyncSurveyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int countByExample(PresentationSyncSurveyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int deleteByExample(PresentationSyncSurveyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int insert(PresentationSyncSurvey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int insertSelective(PresentationSyncSurvey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    List<PresentationSyncSurvey> selectByExample(PresentationSyncSurveyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    PresentationSyncSurvey selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int updateByExampleSelective(@Param("record") PresentationSyncSurvey record, @Param("example") PresentationSyncSurveyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int updateByExample(@Param("record") PresentationSyncSurvey record, @Param("example") PresentationSyncSurveyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int updateByPrimaryKeySelective(PresentationSyncSurvey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_survey
     *
     * @mbggenerated Tue Aug 21 16:15:08 CST 2018
     */
    int updateByPrimaryKey(PresentationSyncSurvey record);
}