package com.deodio.elearning.persistence.model;

import java.util.Date;

public class TraineeExamRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.course_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.exam_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String examId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.exam_type
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Integer examType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.status
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.account_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.trainee_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String traineeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.create_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.update_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.create_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.updte_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Date updteTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.is_pass
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Integer isPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.score
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Integer score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.start_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.finish_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Date finishTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.item_num
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Integer itemNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.leave_times
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private Integer leaveTimes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_trainee_exam_record.group_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    private String groupId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.id
     *
     * @return the value of t_trainee_exam_record.id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.id
     *
     * @param id the value for t_trainee_exam_record.id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.course_id
     *
     * @return the value of t_trainee_exam_record.course_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.course_id
     *
     * @param courseId the value for t_trainee_exam_record.course_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.exam_id
     *
     * @return the value of t_trainee_exam_record.exam_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getExamId() {
        return examId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.exam_id
     *
     * @param examId the value for t_trainee_exam_record.exam_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setExamId(String examId) {
        this.examId = examId == null ? null : examId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.exam_type
     *
     * @return the value of t_trainee_exam_record.exam_type
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Integer getExamType() {
        return examType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.exam_type
     *
     * @param examType the value for t_trainee_exam_record.exam_type
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.status
     *
     * @return the value of t_trainee_exam_record.status
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.status
     *
     * @param status the value for t_trainee_exam_record.status
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.account_id
     *
     * @return the value of t_trainee_exam_record.account_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.account_id
     *
     * @param accountId the value for t_trainee_exam_record.account_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.trainee_id
     *
     * @return the value of t_trainee_exam_record.trainee_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getTraineeId() {
        return traineeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.trainee_id
     *
     * @param traineeId the value for t_trainee_exam_record.trainee_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId == null ? null : traineeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.create_id
     *
     * @return the value of t_trainee_exam_record.create_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.create_id
     *
     * @param createId the value for t_trainee_exam_record.create_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.update_id
     *
     * @return the value of t_trainee_exam_record.update_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.update_id
     *
     * @param updateId the value for t_trainee_exam_record.update_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.create_time
     *
     * @return the value of t_trainee_exam_record.create_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.create_time
     *
     * @param createTime the value for t_trainee_exam_record.create_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.updte_time
     *
     * @return the value of t_trainee_exam_record.updte_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Date getUpdteTime() {
        return updteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.updte_time
     *
     * @param updteTime the value for t_trainee_exam_record.updte_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setUpdteTime(Date updteTime) {
        this.updteTime = updteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.is_pass
     *
     * @return the value of t_trainee_exam_record.is_pass
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Integer getIsPass() {
        return isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.is_pass
     *
     * @param isPass the value for t_trainee_exam_record.is_pass
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.score
     *
     * @return the value of t_trainee_exam_record.score
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Integer getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.score
     *
     * @param score the value for t_trainee_exam_record.score
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.start_time
     *
     * @return the value of t_trainee_exam_record.start_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.start_time
     *
     * @param startTime the value for t_trainee_exam_record.start_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.finish_time
     *
     * @return the value of t_trainee_exam_record.finish_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.finish_time
     *
     * @param finishTime the value for t_trainee_exam_record.finish_time
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.item_num
     *
     * @return the value of t_trainee_exam_record.item_num
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Integer getItemNum() {
        return itemNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.item_num
     *
     * @param itemNum the value for t_trainee_exam_record.item_num
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.leave_times
     *
     * @return the value of t_trainee_exam_record.leave_times
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public Integer getLeaveTimes() {
        return leaveTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.leave_times
     *
     * @param leaveTimes the value for t_trainee_exam_record.leave_times
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setLeaveTimes(Integer leaveTimes) {
        this.leaveTimes = leaveTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_trainee_exam_record.group_id
     *
     * @return the value of t_trainee_exam_record.group_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_trainee_exam_record.group_id
     *
     * @param groupId the value for t_trainee_exam_record.group_id
     *
     * @mbg.generated Tue May 15 16:00:58 CST 2018
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }
}