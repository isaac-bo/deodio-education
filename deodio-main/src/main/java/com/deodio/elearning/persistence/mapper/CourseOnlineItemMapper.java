package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.CourseOnlineItem;
import com.deodio.elearning.persistence.model.CourseOnlineItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseOnlineItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int countByExample(CourseOnlineItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int deleteByExample(CourseOnlineItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int insert(CourseOnlineItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int insertSelective(CourseOnlineItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    List<CourseOnlineItem> selectByExample(CourseOnlineItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    CourseOnlineItem selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int updateByExampleSelective(@Param("record") CourseOnlineItem record, @Param("example") CourseOnlineItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int updateByExample(@Param("record") CourseOnlineItem record, @Param("example") CourseOnlineItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int updateByPrimaryKeySelective(CourseOnlineItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_online_item
     *
     * @mbggenerated Mon Oct 24 10:07:38 CST 2016
     */
    int updateByPrimaryKey(CourseOnlineItem record);
}