package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.UserAccountRel;
import com.deodio.elearning.persistence.model.UserAccountRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int countByExample(UserAccountRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int deleteByExample(UserAccountRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int insert(UserAccountRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int insertSelective(UserAccountRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    List<UserAccountRel> selectByExample(UserAccountRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    UserAccountRel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByExampleSelective(@Param("record") UserAccountRel record, @Param("example") UserAccountRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByExample(@Param("record") UserAccountRel record, @Param("example") UserAccountRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByPrimaryKeySelective(UserAccountRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_account_rel
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    int updateByPrimaryKey(UserAccountRel record);
}