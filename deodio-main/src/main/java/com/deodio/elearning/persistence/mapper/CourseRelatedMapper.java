package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CourseRelated;
import com.deodio.elearning.persistence.model.CourseRelatedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseRelatedMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int countByExample(CourseRelatedExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int deleteByExample(CourseRelatedExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int insert(CourseRelated record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int insertSelective(CourseRelated record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    List<CourseRelated> selectByExample(CourseRelatedExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    CourseRelated selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int updateByExampleSelective(@Param("record") CourseRelated record, @Param("example") CourseRelatedExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int updateByExample(@Param("record") CourseRelated record, @Param("example") CourseRelatedExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int updateByPrimaryKeySelective(CourseRelated record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_related
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    int updateByPrimaryKey(CourseRelated record);
}