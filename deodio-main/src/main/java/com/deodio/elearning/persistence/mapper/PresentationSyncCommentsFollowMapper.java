package com.deodio.elearning.persistence.mapper;

import com.deodio.elearning.persistence.model.PresentationSyncCommentsFollow;
import com.deodio.elearning.persistence.model.PresentationSyncCommentsFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PresentationSyncCommentsFollowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int countByExample(PresentationSyncCommentsFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int deleteByExample(PresentationSyncCommentsFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int insert(PresentationSyncCommentsFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int insertSelective(PresentationSyncCommentsFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    List<PresentationSyncCommentsFollow> selectByExample(PresentationSyncCommentsFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    PresentationSyncCommentsFollow selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int updateByExampleSelective(@Param("record") PresentationSyncCommentsFollow record, @Param("example") PresentationSyncCommentsFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int updateByExample(@Param("record") PresentationSyncCommentsFollow record, @Param("example") PresentationSyncCommentsFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int updateByPrimaryKeySelective(PresentationSyncCommentsFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_presentation_sync_comments_follow
     *
     * @mbggenerated Fri Jul 07 14:49:07 CST 2017
     */
    int updateByPrimaryKey(PresentationSyncCommentsFollow record);
}