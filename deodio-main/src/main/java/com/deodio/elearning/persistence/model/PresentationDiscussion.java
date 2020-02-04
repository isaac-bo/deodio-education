package com.deodio.elearning.persistence.model;

import java.util.Date;

public class PresentationDiscussion {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.presentation_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private String presentationId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.discussion_content
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private String discussionContent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.create_date
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private Date createDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.update_date
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private Date updateDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.create_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.update_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.reply_count
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private Integer replyCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_discussion.like_count
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	private Integer likeCount;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.id
	 * @return  the value of t_presentation_discussion.id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.id
	 * @param id  the value for t_presentation_discussion.id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.presentation_id
	 * @return  the value of t_presentation_discussion.presentation_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public String getPresentationId() {
		return presentationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.presentation_id
	 * @param presentationId  the value for t_presentation_discussion.presentation_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setPresentationId(String presentationId) {
		this.presentationId = presentationId == null ? null : presentationId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.discussion_content
	 * @return  the value of t_presentation_discussion.discussion_content
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public String getDiscussionContent() {
		return discussionContent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.discussion_content
	 * @param discussionContent  the value for t_presentation_discussion.discussion_content
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setDiscussionContent(String discussionContent) {
		this.discussionContent = discussionContent == null ? null : discussionContent.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.create_date
	 * @return  the value of t_presentation_discussion.create_date
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.create_date
	 * @param createDate  the value for t_presentation_discussion.create_date
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.update_date
	 * @return  the value of t_presentation_discussion.update_date
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.update_date
	 * @param updateDate  the value for t_presentation_discussion.update_date
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.create_id
	 * @return  the value of t_presentation_discussion.create_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.create_id
	 * @param createId  the value for t_presentation_discussion.create_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.update_id
	 * @return  the value of t_presentation_discussion.update_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.update_id
	 * @param updateId  the value for t_presentation_discussion.update_id
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.reply_count
	 * @return  the value of t_presentation_discussion.reply_count
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public Integer getReplyCount() {
		return replyCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.reply_count
	 * @param replyCount  the value for t_presentation_discussion.reply_count
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_discussion.like_count
	 * @return  the value of t_presentation_discussion.like_count
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public Integer getLikeCount() {
		return likeCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_discussion.like_count
	 * @param likeCount  the value for t_presentation_discussion.like_count
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
}