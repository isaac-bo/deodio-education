package com.deodio.elearning.persistence.model;

import java.util.Date;

public class CourseHomework {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.course_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String courseId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_name
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String homeworkName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_end_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private Date homeworkEndTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_is_delay
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private Short homeworkIsDelay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_access_auth
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private Short homeworkAccessAuth;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_require
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String homeworkRequire;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_attach
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String homeworkAttach;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_is_publish
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private Short homeworkIsPublish;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.create_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.update_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.create_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.update_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.account_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private String accountId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_homework.homework_publish_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	private Date homeworkPublishTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.id
	 * @return  the value of t_course_homework.id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.id
	 * @param id  the value for t_course_homework.id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.course_id
	 * @return  the value of t_course_homework.course_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.course_id
	 * @param courseId  the value for t_course_homework.course_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_name
	 * @return  the value of t_course_homework.homework_name
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getHomeworkName() {
		return homeworkName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_name
	 * @param homeworkName  the value for t_course_homework.homework_name
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName == null ? null : homeworkName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_end_time
	 * @return  the value of t_course_homework.homework_end_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public Date getHomeworkEndTime() {
		return homeworkEndTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_end_time
	 * @param homeworkEndTime  the value for t_course_homework.homework_end_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkEndTime(Date homeworkEndTime) {
		this.homeworkEndTime = homeworkEndTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_is_delay
	 * @return  the value of t_course_homework.homework_is_delay
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public Short getHomeworkIsDelay() {
		return homeworkIsDelay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_is_delay
	 * @param homeworkIsDelay  the value for t_course_homework.homework_is_delay
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkIsDelay(Short homeworkIsDelay) {
		this.homeworkIsDelay = homeworkIsDelay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_access_auth
	 * @return  the value of t_course_homework.homework_access_auth
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public Short getHomeworkAccessAuth() {
		return homeworkAccessAuth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_access_auth
	 * @param homeworkAccessAuth  the value for t_course_homework.homework_access_auth
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkAccessAuth(Short homeworkAccessAuth) {
		this.homeworkAccessAuth = homeworkAccessAuth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_require
	 * @return  the value of t_course_homework.homework_require
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getHomeworkRequire() {
		return homeworkRequire;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_require
	 * @param homeworkRequire  the value for t_course_homework.homework_require
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkRequire(String homeworkRequire) {
		this.homeworkRequire = homeworkRequire == null ? null : homeworkRequire.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_attach
	 * @return  the value of t_course_homework.homework_attach
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getHomeworkAttach() {
		return homeworkAttach;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_attach
	 * @param homeworkAttach  the value for t_course_homework.homework_attach
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkAttach(String homeworkAttach) {
		this.homeworkAttach = homeworkAttach == null ? null : homeworkAttach.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_is_publish
	 * @return  the value of t_course_homework.homework_is_publish
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public Short getHomeworkIsPublish() {
		return homeworkIsPublish;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_is_publish
	 * @param homeworkIsPublish  the value for t_course_homework.homework_is_publish
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkIsPublish(Short homeworkIsPublish) {
		this.homeworkIsPublish = homeworkIsPublish;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.create_id
	 * @return  the value of t_course_homework.create_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.create_id
	 * @param createId  the value for t_course_homework.create_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.update_id
	 * @return  the value of t_course_homework.update_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.update_id
	 * @param updateId  the value for t_course_homework.update_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.create_time
	 * @return  the value of t_course_homework.create_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.create_time
	 * @param createTime  the value for t_course_homework.create_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.update_time
	 * @return  the value of t_course_homework.update_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.update_time
	 * @param updateTime  the value for t_course_homework.update_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.account_id
	 * @return  the value of t_course_homework.account_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.account_id
	 * @param accountId  the value for t_course_homework.account_id
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_homework.homework_publish_time
	 * @return  the value of t_course_homework.homework_publish_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public Date getHomeworkPublishTime() {
		return homeworkPublishTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_homework.homework_publish_time
	 * @param homeworkPublishTime  the value for t_course_homework.homework_publish_time
	 * @mbggenerated  Wed Sep 28 16:15:26 CST 2016
	 */
	public void setHomeworkPublishTime(Date homeworkPublishTime) {
		this.homeworkPublishTime = homeworkPublishTime;
	}
}