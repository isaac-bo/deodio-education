package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.GroupModel;
import com.deodio.elearning.persistence.model.GroupModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int countByExample(GroupModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int deleteByExample(GroupModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int insert(GroupModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int insertSelective(GroupModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    List<GroupModel> selectByExample(GroupModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    GroupModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByExampleSelective(@Param("record") GroupModel record, @Param("example") GroupModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByExample(@Param("record") GroupModel record, @Param("example") GroupModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByPrimaryKeySelective(GroupModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByPrimaryKey(GroupModel record);
}