package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.PresentationModelExternal;
import com.deodio.elearning.persistence.model.PresentationModelExternalExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PresentationModelExternalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int countByExample(PresentationModelExternalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int deleteByExample(PresentationModelExternalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int insert(PresentationModelExternal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int insertSelective(PresentationModelExternal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    List<PresentationModelExternal> selectByExample(PresentationModelExternalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    PresentationModelExternal selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int updateByExampleSelective(@Param("record") PresentationModelExternal record, @Param("example") PresentationModelExternalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int updateByExample(@Param("record") PresentationModelExternal record, @Param("example") PresentationModelExternalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int updateByPrimaryKeySelective(PresentationModelExternal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_model_external
     *
     * @mbggenerated Wed Jul 13 14:41:05 CST 2016
     */
    int updateByPrimaryKey(PresentationModelExternal record);


}