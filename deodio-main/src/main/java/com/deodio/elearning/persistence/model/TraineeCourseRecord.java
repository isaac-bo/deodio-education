package com.deodio.elearning.persistence.model;

import java.util.Date;

public class TraineeCourseRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.course_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.course_item_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private String courseItemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.course_score
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Integer courseScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.course_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Integer courseTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.account_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.trainee_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private String traineeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.create_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private String createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.update_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private String updateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.create_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.updte_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Date updteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.is_pass
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Integer isPass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.start_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.end_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_course_record.percent_complete
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    private Integer percentComplete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.id
     *
     * @return the value of t_trainee_course_record.id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.id
     *
     * @param id the value for t_trainee_course_record.id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.course_id
     *
     * @return the value of t_trainee_course_record.course_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.course_id
     *
     * @param courseId the value for t_trainee_course_record.course_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.course_item_id
     *
     * @return the value of t_trainee_course_record.course_item_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public String getCourseItemId() {
        return courseItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.course_item_id
     *
     * @param courseItemId the value for t_trainee_course_record.course_item_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setCourseItemId(String courseItemId) {
        this.courseItemId = courseItemId == null ? null : courseItemId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.course_score
     *
     * @return the value of t_trainee_course_record.course_score
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Integer getCourseScore() {
        return courseScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.course_score
     *
     * @param courseScore the value for t_trainee_course_record.course_score
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setCourseScore(Integer courseScore) {
        this.courseScore = courseScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.course_time
     *
     * @return the value of t_trainee_course_record.course_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Integer getCourseTime() {
        return courseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.course_time
     *
     * @param courseTime the value for t_trainee_course_record.course_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.account_id
     *
     * @return the value of t_trainee_course_record.account_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.account_id
     *
     * @param accountId the value for t_trainee_course_record.account_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.trainee_id
     *
     * @return the value of t_trainee_course_record.trainee_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public String getTraineeId() {
        return traineeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.trainee_id
     *
     * @param traineeId the value for t_trainee_course_record.trainee_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId == null ? null : traineeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.create_id
     *
     * @return the value of t_trainee_course_record.create_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.create_id
     *
     * @param createId the value for t_trainee_course_record.create_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.update_id
     *
     * @return the value of t_trainee_course_record.update_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.update_id
     *
     * @param updateId the value for t_trainee_course_record.update_id
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.create_time
     *
     * @return the value of t_trainee_course_record.create_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.create_time
     *
     * @param createTime the value for t_trainee_course_record.create_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.updte_time
     *
     * @return the value of t_trainee_course_record.updte_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Date getUpdteTime() {
        return updteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.updte_time
     *
     * @param updteTime the value for t_trainee_course_record.updte_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setUpdteTime(Date updteTime) {
        this.updteTime = updteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.is_pass
     *
     * @return the value of t_trainee_course_record.is_pass
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Integer getIsPass() {
        return isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.is_pass
     *
     * @param isPass the value for t_trainee_course_record.is_pass
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.start_time
     *
     * @return the value of t_trainee_course_record.start_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.start_time
     *
     * @param startTime the value for t_trainee_course_record.start_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.end_time
     *
     * @return the value of t_trainee_course_record.end_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.end_time
     *
     * @param endTime the value for t_trainee_course_record.end_time
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_course_record.percent_complete
     *
     * @return the value of t_trainee_course_record.percent_complete
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public Integer getPercentComplete() {
        return percentComplete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_course_record.percent_complete
     *
     * @param percentComplete the value for t_trainee_course_record.percent_complete
     *
     * @mbggenerated Tue Aug 01 15:30:31 CST 2017
     */
    public void setPercentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
    }
}