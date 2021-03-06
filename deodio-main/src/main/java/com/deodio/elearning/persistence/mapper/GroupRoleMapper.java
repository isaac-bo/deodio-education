package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.GroupRole;
import com.deodio.elearning.persistence.model.GroupRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int countByExample(GroupRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int deleteByExample(GroupRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int insert(GroupRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int insertSelective(GroupRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    List<GroupRole> selectByExample(GroupRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    GroupRole selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int updateByExampleSelective(@Param("record") GroupRole record, @Param("example") GroupRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int updateByExample(@Param("record") GroupRole record, @Param("example") GroupRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int updateByPrimaryKeySelective(GroupRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role
     *
     * @mbggenerated Fri Mar 25 11:02:57 CST 2016
     */
    int updateByPrimaryKey(GroupRole record);
}