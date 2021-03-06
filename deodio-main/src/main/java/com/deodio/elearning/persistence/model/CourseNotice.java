package com.deodio.elearning.persistence.model;

import java.util.Date;

public class CourseNotice {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.course_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private String courseId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.notice_name
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private String noticeName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.notice_content
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private String noticeContent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.notice_publish_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private Date noticePublishTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.notice_publish_scope
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private Short noticePublishScope;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.create_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.update_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.create_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.update_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_notice.account_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	private String accountId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.id
	 * @return  the value of t_course_notice.id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.id
	 * @param id  the value for t_course_notice.id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.course_id
	 * @return  the value of t_course_notice.course_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.course_id
	 * @param courseId  the value for t_course_notice.course_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId == null ? null : courseId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.notice_name
	 * @return  the value of t_course_notice.notice_name
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public String getNoticeName() {
		return noticeName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.notice_name
	 * @param noticeName  the value for t_course_notice.notice_name
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName == null ? null : noticeName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.notice_content
	 * @return  the value of t_course_notice.notice_content
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public String getNoticeContent() {
		return noticeContent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.notice_content
	 * @param noticeContent  the value for t_course_notice.notice_content
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent == null ? null : noticeContent.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.notice_publish_time
	 * @return  the value of t_course_notice.notice_publish_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public Date getNoticePublishTime() {
		return noticePublishTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.notice_publish_time
	 * @param noticePublishTime  the value for t_course_notice.notice_publish_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setNoticePublishTime(Date noticePublishTime) {
		this.noticePublishTime = noticePublishTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.notice_publish_scope
	 * @return  the value of t_course_notice.notice_publish_scope
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public Short getNoticePublishScope() {
		return noticePublishScope;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.notice_publish_scope
	 * @param noticePublishScope  the value for t_course_notice.notice_publish_scope
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setNoticePublishScope(Short noticePublishScope) {
		this.noticePublishScope = noticePublishScope;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.create_id
	 * @return  the value of t_course_notice.create_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.create_id
	 * @param createId  the value for t_course_notice.create_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.update_id
	 * @return  the value of t_course_notice.update_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.update_id
	 * @param updateId  the value for t_course_notice.update_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.create_time
	 * @return  the value of t_course_notice.create_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.create_time
	 * @param createTime  the value for t_course_notice.create_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.update_time
	 * @return  the value of t_course_notice.update_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.update_time
	 * @param updateTime  the value for t_course_notice.update_time
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_notice.account_id
	 * @return  the value of t_course_notice.account_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_notice.account_id
	 * @param accountId  the value for t_course_notice.account_id
	 * @mbggenerated  Thu Sep 29 17:45:16 CST 2016
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
}