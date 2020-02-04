package com.deodio.elearning.persistence.model;

import java.util.Date;

public class TraineeCourse {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.item_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private String itemId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.item_type
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Short itemType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.create_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.create_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.update_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.update_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.trainee_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private String traineeId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.purchase_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Date purchaseTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.deadline_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Date deadlineTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.is_charge
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Short isCharge;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.is_free
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Short isFree;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.chapter_count
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Short chapterCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.chapter_finish
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Short chapterFinish;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.account_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private String accountId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.study_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private Date studyTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_trainee_course.group_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	private String groupId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.id
	 * @return  the value of t_trainee_course.id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.id
	 * @param id  the value for t_trainee_course.id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.item_id
	 * @return  the value of t_trainee_course.item_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.item_id
	 * @param itemId  the value for t_trainee_course.item_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.item_type
	 * @return  the value of t_trainee_course.item_type
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Short getItemType() {
		return itemType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.item_type
	 * @param itemType  the value for t_trainee_course.item_type
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setItemType(Short itemType) {
		this.itemType = itemType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.create_id
	 * @return  the value of t_trainee_course.create_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.create_id
	 * @param createId  the value for t_trainee_course.create_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.create_time
	 * @return  the value of t_trainee_course.create_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.create_time
	 * @param createTime  the value for t_trainee_course.create_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.update_id
	 * @return  the value of t_trainee_course.update_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.update_id
	 * @param updateId  the value for t_trainee_course.update_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.update_time
	 * @return  the value of t_trainee_course.update_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.update_time
	 * @param updateTime  the value for t_trainee_course.update_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.trainee_id
	 * @return  the value of t_trainee_course.trainee_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public String getTraineeId() {
		return traineeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.trainee_id
	 * @param traineeId  the value for t_trainee_course.trainee_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setTraineeId(String traineeId) {
		this.traineeId = traineeId == null ? null : traineeId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.purchase_time
	 * @return  the value of t_trainee_course.purchase_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Date getPurchaseTime() {
		return purchaseTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.purchase_time
	 * @param purchaseTime  the value for t_trainee_course.purchase_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.deadline_time
	 * @return  the value of t_trainee_course.deadline_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Date getDeadlineTime() {
		return deadlineTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.deadline_time
	 * @param deadlineTime  the value for t_trainee_course.deadline_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setDeadlineTime(Date deadlineTime) {
		this.deadlineTime = deadlineTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.is_charge
	 * @return  the value of t_trainee_course.is_charge
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Short getIsCharge() {
		return isCharge;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.is_charge
	 * @param isCharge  the value for t_trainee_course.is_charge
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setIsCharge(Short isCharge) {
		this.isCharge = isCharge;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.is_free
	 * @return  the value of t_trainee_course.is_free
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Short getIsFree() {
		return isFree;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.is_free
	 * @param isFree  the value for t_trainee_course.is_free
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setIsFree(Short isFree) {
		this.isFree = isFree;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.chapter_count
	 * @return  the value of t_trainee_course.chapter_count
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Short getChapterCount() {
		return chapterCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.chapter_count
	 * @param chapterCount  the value for t_trainee_course.chapter_count
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setChapterCount(Short chapterCount) {
		this.chapterCount = chapterCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.chapter_finish
	 * @return  the value of t_trainee_course.chapter_finish
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Short getChapterFinish() {
		return chapterFinish;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.chapter_finish
	 * @param chapterFinish  the value for t_trainee_course.chapter_finish
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setChapterFinish(Short chapterFinish) {
		this.chapterFinish = chapterFinish;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.account_id
	 * @return  the value of t_trainee_course.account_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.account_id
	 * @param accountId  the value for t_trainee_course.account_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.study_time
	 * @return  the value of t_trainee_course.study_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public Date getStudyTime() {
		return studyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.study_time
	 * @param studyTime  the value for t_trainee_course.study_time
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setStudyTime(Date studyTime) {
		this.studyTime = studyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_trainee_course.group_id
	 * @return  the value of t_trainee_course.group_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_trainee_course.group_id
	 * @param groupId  the value for t_trainee_course.group_id
	 * @mbggenerated  Mon Mar 19 17:06:11 CST 2018
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}
}