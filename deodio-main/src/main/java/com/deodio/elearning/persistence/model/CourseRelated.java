package com.deodio.elearning.persistence.model;

import java.util.Date;

public class CourseRelated {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.course_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.related_course_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private String relatedCourseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.course_related_type
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private Short courseRelatedType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.create_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private String createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.create_time
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.update_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private String updateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_course_related.update_time
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.id
     *
     * @return the value of t_course_related.id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.id
     *
     * @param id the value for t_course_related.id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.course_id
     *
     * @return the value of t_course_related.course_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.course_id
     *
     * @param courseId the value for t_course_related.course_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.related_course_id
     *
     * @return the value of t_course_related.related_course_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public String getRelatedCourseId() {
        return relatedCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.related_course_id
     *
     * @param relatedCourseId the value for t_course_related.related_course_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setRelatedCourseId(String relatedCourseId) {
        this.relatedCourseId = relatedCourseId == null ? null : relatedCourseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.course_related_type
     *
     * @return the value of t_course_related.course_related_type
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public Short getCourseRelatedType() {
        return courseRelatedType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.course_related_type
     *
     * @param courseRelatedType the value for t_course_related.course_related_type
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setCourseRelatedType(Short courseRelatedType) {
        this.courseRelatedType = courseRelatedType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.create_id
     *
     * @return the value of t_course_related.create_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.create_id
     *
     * @param createId the value for t_course_related.create_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.create_time
     *
     * @return the value of t_course_related.create_time
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.create_time
     *
     * @param createTime the value for t_course_related.create_time
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.update_id
     *
     * @return the value of t_course_related.update_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.update_id
     *
     * @param updateId the value for t_course_related.update_id
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_course_related.update_time
     *
     * @return the value of t_course_related.update_time
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_course_related.update_time
     *
     * @param updateTime the value for t_course_related.update_time
     *
     * @mbggenerated Wed Oct 12 10:35:09 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}