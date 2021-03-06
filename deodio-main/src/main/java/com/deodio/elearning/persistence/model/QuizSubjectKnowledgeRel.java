package com.deodio.elearning.persistence.model;

import java.util.Date;

public class QuizSubjectKnowledgeRel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_subject_knowledge_rel.id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_subject_knowledge_rel.quiz_subject_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    private String quizSubjectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_subject_knowledge_rel.knowledge_point_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    private String knowledgePointId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_subject_knowledge_rel.create_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    private String createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_subject_knowledge_rel.create_time
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_subject_knowledge_rel.update_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    private String updateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_subject_knowledge_rel.update_time
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_subject_knowledge_rel.id
     *
     * @return the value of t_quiz_subject_knowledge_rel.id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_subject_knowledge_rel.id
     *
     * @param id the value for t_quiz_subject_knowledge_rel.id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_subject_knowledge_rel.quiz_subject_id
     *
     * @return the value of t_quiz_subject_knowledge_rel.quiz_subject_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public String getQuizSubjectId() {
        return quizSubjectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_subject_knowledge_rel.quiz_subject_id
     *
     * @param quizSubjectId the value for t_quiz_subject_knowledge_rel.quiz_subject_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public void setQuizSubjectId(String quizSubjectId) {
        this.quizSubjectId = quizSubjectId == null ? null : quizSubjectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_subject_knowledge_rel.knowledge_point_id
     *
     * @return the value of t_quiz_subject_knowledge_rel.knowledge_point_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public String getKnowledgePointId() {
        return knowledgePointId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_subject_knowledge_rel.knowledge_point_id
     *
     * @param knowledgePointId the value for t_quiz_subject_knowledge_rel.knowledge_point_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public void setKnowledgePointId(String knowledgePointId) {
        this.knowledgePointId = knowledgePointId == null ? null : knowledgePointId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_subject_knowledge_rel.create_id
     *
     * @return the value of t_quiz_subject_knowledge_rel.create_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_subject_knowledge_rel.create_id
     *
     * @param createId the value for t_quiz_subject_knowledge_rel.create_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_subject_knowledge_rel.create_time
     *
     * @return the value of t_quiz_subject_knowledge_rel.create_time
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_subject_knowledge_rel.create_time
     *
     * @param createTime the value for t_quiz_subject_knowledge_rel.create_time
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_subject_knowledge_rel.update_id
     *
     * @return the value of t_quiz_subject_knowledge_rel.update_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_subject_knowledge_rel.update_id
     *
     * @param updateId the value for t_quiz_subject_knowledge_rel.update_id
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_subject_knowledge_rel.update_time
     *
     * @return the value of t_quiz_subject_knowledge_rel.update_time
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_subject_knowledge_rel.update_time
     *
     * @param updateTime the value for t_quiz_subject_knowledge_rel.update_time
     *
     * @mbggenerated Mon May 16 15:52:29 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}