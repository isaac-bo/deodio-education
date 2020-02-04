package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.GroupRoleFuncRel;
import com.deodio.elearning.persistence.model.GroupRoleFuncRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupRoleFuncRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int countByExample(GroupRoleFuncRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int deleteByExample(GroupRoleFuncRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int insert(GroupRoleFuncRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int insertSelective(GroupRoleFuncRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    List<GroupRoleFuncRel> selectByExample(GroupRoleFuncRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    GroupRoleFuncRel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int updateByExampleSelective(@Param("record") GroupRoleFuncRel record, @Param("example") GroupRoleFuncRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int updateByExample(@Param("record") GroupRoleFuncRel record, @Param("example") GroupRoleFuncRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int updateByPrimaryKeySelective(GroupRoleFuncRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group_role_func_rel
     *
     * @mbggenerated Mon Mar 28 14:17:17 CST 2016
     */
    int updateByPrimaryKey(GroupRoleFuncRel record);
}