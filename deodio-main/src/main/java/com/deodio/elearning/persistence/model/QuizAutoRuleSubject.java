package com.deodio.elearning.persistence.model;

public class QuizAutoRuleSubject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_auto_rule_subject.id
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_auto_rule_subject.subject_type
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    private Integer subjectType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_auto_rule_subject.subject_order
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    private Integer subjectOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_auto_rule_subject.subject_score
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    private Integer subjectScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_auto_rule_subject.subject_items_count
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    private Integer subjectItemsCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_auto_rule_subject.subject_remark
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    private String subjectRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_quiz_auto_rule_subject.quiz_id
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    private String quizId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_auto_rule_subject.id
     *
     * @return the value of t_quiz_auto_rule_subject.id
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_auto_rule_subject.id
     *
     * @param id the value for t_quiz_auto_rule_subject.id
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_auto_rule_subject.subject_type
     *
     * @return the value of t_quiz_auto_rule_subject.subject_type
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public Integer getSubjectType() {
        return subjectType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_auto_rule_subject.subject_type
     *
     * @param subjectType the value for t_quiz_auto_rule_subject.subject_type
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_auto_rule_subject.subject_order
     *
     * @return the value of t_quiz_auto_rule_subject.subject_order
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public Integer getSubjectOrder() {
        return subjectOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_auto_rule_subject.subject_order
     *
     * @param subjectOrder the value for t_quiz_auto_rule_subject.subject_order
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public void setSubjectOrder(Integer subjectOrder) {
        this.subjectOrder = subjectOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_auto_rule_subject.subject_score
     *
     * @return the value of t_quiz_auto_rule_subject.subject_score
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public Integer getSubjectScore() {
        return subjectScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_auto_rule_subject.subject_score
     *
     * @param subjectScore the value for t_quiz_auto_rule_subject.subject_score
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public void setSubjectScore(Integer subjectScore) {
        this.subjectScore = subjectScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_auto_rule_subject.subject_items_count
     *
     * @return the value of t_quiz_auto_rule_subject.subject_items_count
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public Integer getSubjectItemsCount() {
        return subjectItemsCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_auto_rule_subject.subject_items_count
     *
     * @param subjectItemsCount the value for t_quiz_auto_rule_subject.subject_items_count
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public void setSubjectItemsCount(Integer subjectItemsCount) {
        this.subjectItemsCount = subjectItemsCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_auto_rule_subject.subject_remark
     *
     * @return the value of t_quiz_auto_rule_subject.subject_remark
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public String getSubjectRemark() {
        return subjectRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_auto_rule_subject.subject_remark
     *
     * @param subjectRemark the value for t_quiz_auto_rule_subject.subject_remark
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public void setSubjectRemark(String subjectRemark) {
        this.subjectRemark = subjectRemark == null ? null : subjectRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_quiz_auto_rule_subject.quiz_id
     *
     * @return the value of t_quiz_auto_rule_subject.quiz_id
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public String getQuizId() {
        return quizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_quiz_auto_rule_subject.quiz_id
     *
     * @param quizId the value for t_quiz_auto_rule_subject.quiz_id
     *
     * @mbggenerated Fri May 27 14:43:19 CST 2016
     */
    public void setQuizId(String quizId) {
        this.quizId = quizId == null ? null : quizId.trim();
    }
}