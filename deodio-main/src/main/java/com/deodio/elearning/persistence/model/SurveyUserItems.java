package com.deodio.elearning.persistence.model;

import java.util.Date;

public class SurveyUserItems {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.group_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String groupId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.user_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.survey_subject_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String surveySubjectId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.survey_subject_name
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String surveySubjectName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.survey_item_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String surveyItemId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.survey_item_name
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String surveyItemName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.survey_item_order
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private Integer surveyItemOrder;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.create_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.create_time
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.update_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.updte_time
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private Date updteTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.check_flag
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String checkFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_survey_user_items.type
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	private String type;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.id
	 * @return  the value of t_survey_user_items.id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.id
	 * @param id  the value for t_survey_user_items.id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.group_id
	 * @return  the value of t_survey_user_items.group_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.group_id
	 * @param groupId  the value for t_survey_user_items.group_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.user_id
	 * @return  the value of t_survey_user_items.user_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.user_id
	 * @param userId  the value for t_survey_user_items.user_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.survey_subject_id
	 * @return  the value of t_survey_user_items.survey_subject_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getSurveySubjectId() {
		return surveySubjectId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.survey_subject_id
	 * @param surveySubjectId  the value for t_survey_user_items.survey_subject_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setSurveySubjectId(String surveySubjectId) {
		this.surveySubjectId = surveySubjectId == null ? null : surveySubjectId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.survey_subject_name
	 * @return  the value of t_survey_user_items.survey_subject_name
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getSurveySubjectName() {
		return surveySubjectName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.survey_subject_name
	 * @param surveySubjectName  the value for t_survey_user_items.survey_subject_name
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setSurveySubjectName(String surveySubjectName) {
		this.surveySubjectName = surveySubjectName == null ? null : surveySubjectName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.survey_item_id
	 * @return  the value of t_survey_user_items.survey_item_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getSurveyItemId() {
		return surveyItemId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.survey_item_id
	 * @param surveyItemId  the value for t_survey_user_items.survey_item_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setSurveyItemId(String surveyItemId) {
		this.surveyItemId = surveyItemId == null ? null : surveyItemId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.survey_item_name
	 * @return  the value of t_survey_user_items.survey_item_name
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getSurveyItemName() {
		return surveyItemName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.survey_item_name
	 * @param surveyItemName  the value for t_survey_user_items.survey_item_name
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setSurveyItemName(String surveyItemName) {
		this.surveyItemName = surveyItemName == null ? null : surveyItemName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.survey_item_order
	 * @return  the value of t_survey_user_items.survey_item_order
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public Integer getSurveyItemOrder() {
		return surveyItemOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.survey_item_order
	 * @param surveyItemOrder  the value for t_survey_user_items.survey_item_order
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setSurveyItemOrder(Integer surveyItemOrder) {
		this.surveyItemOrder = surveyItemOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.create_id
	 * @return  the value of t_survey_user_items.create_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.create_id
	 * @param createId  the value for t_survey_user_items.create_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.create_time
	 * @return  the value of t_survey_user_items.create_time
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.create_time
	 * @param createTime  the value for t_survey_user_items.create_time
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.update_id
	 * @return  the value of t_survey_user_items.update_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.update_id
	 * @param updateId  the value for t_survey_user_items.update_id
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.updte_time
	 * @return  the value of t_survey_user_items.updte_time
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public Date getUpdteTime() {
		return updteTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.updte_time
	 * @param updteTime  the value for t_survey_user_items.updte_time
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setUpdteTime(Date updteTime) {
		this.updteTime = updteTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.check_flag
	 * @return  the value of t_survey_user_items.check_flag
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getCheckFlag() {
		return checkFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.check_flag
	 * @param checkFlag  the value for t_survey_user_items.check_flag
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag == null ? null : checkFlag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_survey_user_items.type
	 * @return  the value of t_survey_user_items.type
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_survey_user_items.type
	 * @param type  the value for t_survey_user_items.type
	 * @mbggenerated  Mon Jun 04 10:03:15 CST 2018
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
}