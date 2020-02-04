package com.deodio.elearning.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscussionModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public DiscussionModelExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andAccountIdIsNull() {
			addCriterion("account_id is null");
			return (Criteria) this;
		}

		public Criteria andAccountIdIsNotNull() {
			addCriterion("account_id is not null");
			return (Criteria) this;
		}

		public Criteria andAccountIdEqualTo(String value) {
			addCriterion("account_id =", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdNotEqualTo(String value) {
			addCriterion("account_id <>", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdGreaterThan(String value) {
			addCriterion("account_id >", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdGreaterThanOrEqualTo(String value) {
			addCriterion("account_id >=", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdLessThan(String value) {
			addCriterion("account_id <", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdLessThanOrEqualTo(String value) {
			addCriterion("account_id <=", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdLike(String value) {
			addCriterion("account_id like", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdNotLike(String value) {
			addCriterion("account_id not like", value, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdIn(List<String> values) {
			addCriterion("account_id in", values, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdNotIn(List<String> values) {
			addCriterion("account_id not in", values, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdBetween(String value1, String value2) {
			addCriterion("account_id between", value1, value2, "accountId");
			return (Criteria) this;
		}

		public Criteria andAccountIdNotBetween(String value1, String value2) {
			addCriterion("account_id not between", value1, value2, "accountId");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextIsNull() {
			addCriterion("discussion_text is null");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextIsNotNull() {
			addCriterion("discussion_text is not null");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextEqualTo(String value) {
			addCriterion("discussion_text =", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextNotEqualTo(String value) {
			addCriterion("discussion_text <>", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextGreaterThan(String value) {
			addCriterion("discussion_text >", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextGreaterThanOrEqualTo(String value) {
			addCriterion("discussion_text >=", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextLessThan(String value) {
			addCriterion("discussion_text <", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextLessThanOrEqualTo(String value) {
			addCriterion("discussion_text <=", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextLike(String value) {
			addCriterion("discussion_text like", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextNotLike(String value) {
			addCriterion("discussion_text not like", value, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextIn(List<String> values) {
			addCriterion("discussion_text in", values, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextNotIn(List<String> values) {
			addCriterion("discussion_text not in", values, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextBetween(String value1, String value2) {
			addCriterion("discussion_text between", value1, value2, "discussionText");
			return (Criteria) this;
		}

		public Criteria andDiscussionTextNotBetween(String value1, String value2) {
			addCriterion("discussion_text not between", value1, value2, "discussionText");
			return (Criteria) this;
		}

		public Criteria andCourseIdIsNull() {
			addCriterion("course_id is null");
			return (Criteria) this;
		}

		public Criteria andCourseIdIsNotNull() {
			addCriterion("course_id is not null");
			return (Criteria) this;
		}

		public Criteria andCourseIdEqualTo(String value) {
			addCriterion("course_id =", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdNotEqualTo(String value) {
			addCriterion("course_id <>", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdGreaterThan(String value) {
			addCriterion("course_id >", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdGreaterThanOrEqualTo(String value) {
			addCriterion("course_id >=", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdLessThan(String value) {
			addCriterion("course_id <", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdLessThanOrEqualTo(String value) {
			addCriterion("course_id <=", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdLike(String value) {
			addCriterion("course_id like", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdNotLike(String value) {
			addCriterion("course_id not like", value, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdIn(List<String> values) {
			addCriterion("course_id in", values, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdNotIn(List<String> values) {
			addCriterion("course_id not in", values, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdBetween(String value1, String value2) {
			addCriterion("course_id between", value1, value2, "courseId");
			return (Criteria) this;
		}

		public Criteria andCourseIdNotBetween(String value1, String value2) {
			addCriterion("course_id not between", value1, value2, "courseId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdIsNull() {
			addCriterion("related_id is null");
			return (Criteria) this;
		}

		public Criteria andRelatedIdIsNotNull() {
			addCriterion("related_id is not null");
			return (Criteria) this;
		}

		public Criteria andRelatedIdEqualTo(String value) {
			addCriterion("related_id =", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdNotEqualTo(String value) {
			addCriterion("related_id <>", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdGreaterThan(String value) {
			addCriterion("related_id >", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdGreaterThanOrEqualTo(String value) {
			addCriterion("related_id >=", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdLessThan(String value) {
			addCriterion("related_id <", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdLessThanOrEqualTo(String value) {
			addCriterion("related_id <=", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdLike(String value) {
			addCriterion("related_id like", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdNotLike(String value) {
			addCriterion("related_id not like", value, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdIn(List<String> values) {
			addCriterion("related_id in", values, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdNotIn(List<String> values) {
			addCriterion("related_id not in", values, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdBetween(String value1, String value2) {
			addCriterion("related_id between", value1, value2, "relatedId");
			return (Criteria) this;
		}

		public Criteria andRelatedIdNotBetween(String value1, String value2) {
			addCriterion("related_id not between", value1, value2, "relatedId");
			return (Criteria) this;
		}

		public Criteria andAgreeNumIsNull() {
			addCriterion("agree_num is null");
			return (Criteria) this;
		}

		public Criteria andAgreeNumIsNotNull() {
			addCriterion("agree_num is not null");
			return (Criteria) this;
		}

		public Criteria andAgreeNumEqualTo(Integer value) {
			addCriterion("agree_num =", value, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumNotEqualTo(Integer value) {
			addCriterion("agree_num <>", value, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumGreaterThan(Integer value) {
			addCriterion("agree_num >", value, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("agree_num >=", value, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumLessThan(Integer value) {
			addCriterion("agree_num <", value, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumLessThanOrEqualTo(Integer value) {
			addCriterion("agree_num <=", value, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumIn(List<Integer> values) {
			addCriterion("agree_num in", values, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumNotIn(List<Integer> values) {
			addCriterion("agree_num not in", values, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumBetween(Integer value1, Integer value2) {
			addCriterion("agree_num between", value1, value2, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andAgreeNumNotBetween(Integer value1, Integer value2) {
			addCriterion("agree_num not between", value1, value2, "agreeNum");
			return (Criteria) this;
		}

		public Criteria andCreateIdIsNull() {
			addCriterion("create_id is null");
			return (Criteria) this;
		}

		public Criteria andCreateIdIsNotNull() {
			addCriterion("create_id is not null");
			return (Criteria) this;
		}

		public Criteria andCreateIdEqualTo(String value) {
			addCriterion("create_id =", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdNotEqualTo(String value) {
			addCriterion("create_id <>", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdGreaterThan(String value) {
			addCriterion("create_id >", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdGreaterThanOrEqualTo(String value) {
			addCriterion("create_id >=", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdLessThan(String value) {
			addCriterion("create_id <", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdLessThanOrEqualTo(String value) {
			addCriterion("create_id <=", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdLike(String value) {
			addCriterion("create_id like", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdNotLike(String value) {
			addCriterion("create_id not like", value, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdIn(List<String> values) {
			addCriterion("create_id in", values, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdNotIn(List<String> values) {
			addCriterion("create_id not in", values, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdBetween(String value1, String value2) {
			addCriterion("create_id between", value1, value2, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateIdNotBetween(String value1, String value2) {
			addCriterion("create_id not between", value1, value2, "createId");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andUpdateIdIsNull() {
			addCriterion("update_id is null");
			return (Criteria) this;
		}

		public Criteria andUpdateIdIsNotNull() {
			addCriterion("update_id is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateIdEqualTo(String value) {
			addCriterion("update_id =", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdNotEqualTo(String value) {
			addCriterion("update_id <>", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdGreaterThan(String value) {
			addCriterion("update_id >", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdGreaterThanOrEqualTo(String value) {
			addCriterion("update_id >=", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdLessThan(String value) {
			addCriterion("update_id <", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdLessThanOrEqualTo(String value) {
			addCriterion("update_id <=", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdLike(String value) {
			addCriterion("update_id like", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdNotLike(String value) {
			addCriterion("update_id not like", value, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdIn(List<String> values) {
			addCriterion("update_id in", values, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdNotIn(List<String> values) {
			addCriterion("update_id not in", values, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdBetween(String value1, String value2) {
			addCriterion("update_id between", value1, value2, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateIdNotBetween(String value1, String value2) {
			addCriterion("update_id not between", value1, value2, "updateId");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeEqualTo(Date value) {
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotEqualTo(Date value) {
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThan(Date value) {
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThan(Date value) {
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIn(List<Date> values) {
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotIn(List<Date> values) {
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeBetween(Date value1, Date value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("update_time not between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andReplyNumIsNull() {
			addCriterion("reply_num is null");
			return (Criteria) this;
		}

		public Criteria andReplyNumIsNotNull() {
			addCriterion("reply_num is not null");
			return (Criteria) this;
		}

		public Criteria andReplyNumEqualTo(Integer value) {
			addCriterion("reply_num =", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumNotEqualTo(Integer value) {
			addCriterion("reply_num <>", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumGreaterThan(Integer value) {
			addCriterion("reply_num >", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("reply_num >=", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumLessThan(Integer value) {
			addCriterion("reply_num <", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumLessThanOrEqualTo(Integer value) {
			addCriterion("reply_num <=", value, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumIn(List<Integer> values) {
			addCriterion("reply_num in", values, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumNotIn(List<Integer> values) {
			addCriterion("reply_num not in", values, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumBetween(Integer value1, Integer value2) {
			addCriterion("reply_num between", value1, value2, "replyNum");
			return (Criteria) this;
		}

		public Criteria andReplyNumNotBetween(Integer value1, Integer value2) {
			addCriterion("reply_num not between", value1, value2, "replyNum");
			return (Criteria) this;
		}

		public Criteria andScanNumIsNull() {
			addCriterion("scan_num is null");
			return (Criteria) this;
		}

		public Criteria andScanNumIsNotNull() {
			addCriterion("scan_num is not null");
			return (Criteria) this;
		}

		public Criteria andScanNumEqualTo(Integer value) {
			addCriterion("scan_num =", value, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumNotEqualTo(Integer value) {
			addCriterion("scan_num <>", value, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumGreaterThan(Integer value) {
			addCriterion("scan_num >", value, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("scan_num >=", value, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumLessThan(Integer value) {
			addCriterion("scan_num <", value, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumLessThanOrEqualTo(Integer value) {
			addCriterion("scan_num <=", value, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumIn(List<Integer> values) {
			addCriterion("scan_num in", values, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumNotIn(List<Integer> values) {
			addCriterion("scan_num not in", values, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumBetween(Integer value1, Integer value2) {
			addCriterion("scan_num between", value1, value2, "scanNum");
			return (Criteria) this;
		}

		public Criteria andScanNumNotBetween(Integer value1, Integer value2) {
			addCriterion("scan_num not between", value1, value2, "scanNum");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_discussion
	 * @mbggenerated  Mon Jul 24 16:38:19 CST 2017
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_discussion
     *
     * @mbggenerated do_not_delete_during_merge Fri Jun 16 17:14:24 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}