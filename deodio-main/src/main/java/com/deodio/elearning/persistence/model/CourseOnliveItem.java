package com.deodio.elearning.persistence.model;

import java.util.Date;

public class CourseOnliveItem {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.course_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.item_step_no
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private Integer itemStepNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.start_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.expire_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private Date expireTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.item_name
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private String itemName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.item_trainers
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private String itemTrainers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.account_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.create_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private String createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.create_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.update_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private String updateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.update_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.item_type
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private Short itemType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_onlive_item.item_period
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    private Integer itemPeriod;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.id
     *
     * @return the value of t_course_onlive_item.id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.id
     *
     * @param id the value for t_course_onlive_item.id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.course_id
     *
     * @return the value of t_course_onlive_item.course_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.course_id
     *
     * @param courseId the value for t_course_onlive_item.course_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.item_step_no
     *
     * @return the value of t_course_onlive_item.item_step_no
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public Integer getItemStepNo() {
        return itemStepNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.item_step_no
     *
     * @param itemStepNo the value for t_course_onlive_item.item_step_no
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setItemStepNo(Integer itemStepNo) {
        this.itemStepNo = itemStepNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.start_time
     *
     * @return the value of t_course_onlive_item.start_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.start_time
     *
     * @param startTime the value for t_course_onlive_item.start_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.expire_time
     *
     * @return the value of t_course_onlive_item.expire_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.expire_time
     *
     * @param expireTime the value for t_course_onlive_item.expire_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.item_name
     *
     * @return the value of t_course_onlive_item.item_name
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.item_name
     *
     * @param itemName the value for t_course_onlive_item.item_name
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.item_trainers
     *
     * @return the value of t_course_onlive_item.item_trainers
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public String getItemTrainers() {
        return itemTrainers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.item_trainers
     *
     * @param itemTrainers the value for t_course_onlive_item.item_trainers
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setItemTrainers(String itemTrainers) {
        this.itemTrainers = itemTrainers == null ? null : itemTrainers.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.account_id
     *
     * @return the value of t_course_onlive_item.account_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.account_id
     *
     * @param accountId the value for t_course_onlive_item.account_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.create_id
     *
     * @return the value of t_course_onlive_item.create_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.create_id
     *
     * @param createId the value for t_course_onlive_item.create_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.create_time
     *
     * @return the value of t_course_onlive_item.create_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.create_time
     *
     * @param createTime the value for t_course_onlive_item.create_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.update_id
     *
     * @return the value of t_course_onlive_item.update_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.update_id
     *
     * @param updateId the value for t_course_onlive_item.update_id
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.update_time
     *
     * @return the value of t_course_onlive_item.update_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.update_time
     *
     * @param updateTime the value for t_course_onlive_item.update_time
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.item_type
     *
     * @return the value of t_course_onlive_item.item_type
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public Short getItemType() {
        return itemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.item_type
     *
     * @param itemType the value for t_course_onlive_item.item_type
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setItemType(Short itemType) {
        this.itemType = itemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_onlive_item.item_period
     *
     * @return the value of t_course_onlive_item.item_period
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public Integer getItemPeriod() {
        return itemPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_onlive_item.item_period
     *
     * @param itemPeriod the value for t_course_onlive_item.item_period
     *
     * @mbggenerated Thu Mar 16 15:49:00 CST 2017
     */
    public void setItemPeriod(Integer itemPeriod) {
        this.itemPeriod = itemPeriod;
    }
}