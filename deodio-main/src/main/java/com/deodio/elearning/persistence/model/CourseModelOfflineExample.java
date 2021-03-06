package com.deodio.elearning.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseModelOfflineExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public CourseModelOfflineExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
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

		public Criteria andCourseTrainTimesIsNull() {
			addCriterion("course_train_times is null");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesIsNotNull() {
			addCriterion("course_train_times is not null");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesEqualTo(Integer value) {
			addCriterion("course_train_times =", value, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesNotEqualTo(Integer value) {
			addCriterion("course_train_times <>", value, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesGreaterThan(Integer value) {
			addCriterion("course_train_times >", value, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesGreaterThanOrEqualTo(Integer value) {
			addCriterion("course_train_times >=", value, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesLessThan(Integer value) {
			addCriterion("course_train_times <", value, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesLessThanOrEqualTo(Integer value) {
			addCriterion("course_train_times <=", value, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesIn(List<Integer> values) {
			addCriterion("course_train_times in", values, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesNotIn(List<Integer> values) {
			addCriterion("course_train_times not in", values, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesBetween(Integer value1, Integer value2) {
			addCriterion("course_train_times between", value1, value2, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseTrainTimesNotBetween(Integer value1, Integer value2) {
			addCriterion("course_train_times not between", value1, value2, "courseTrainTimes");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameIsNull() {
			addCriterion("course_contact_name is null");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameIsNotNull() {
			addCriterion("course_contact_name is not null");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameEqualTo(String value) {
			addCriterion("course_contact_name =", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameNotEqualTo(String value) {
			addCriterion("course_contact_name <>", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameGreaterThan(String value) {
			addCriterion("course_contact_name >", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameGreaterThanOrEqualTo(String value) {
			addCriterion("course_contact_name >=", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameLessThan(String value) {
			addCriterion("course_contact_name <", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameLessThanOrEqualTo(String value) {
			addCriterion("course_contact_name <=", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameLike(String value) {
			addCriterion("course_contact_name like", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameNotLike(String value) {
			addCriterion("course_contact_name not like", value, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameIn(List<String> values) {
			addCriterion("course_contact_name in", values, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameNotIn(List<String> values) {
			addCriterion("course_contact_name not in", values, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameBetween(String value1, String value2) {
			addCriterion("course_contact_name between", value1, value2, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactNameNotBetween(String value1, String value2) {
			addCriterion("course_contact_name not between", value1, value2, "courseContactName");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelIsNull() {
			addCriterion("course_contact_tel is null");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelIsNotNull() {
			addCriterion("course_contact_tel is not null");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelEqualTo(String value) {
			addCriterion("course_contact_tel =", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelNotEqualTo(String value) {
			addCriterion("course_contact_tel <>", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelGreaterThan(String value) {
			addCriterion("course_contact_tel >", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelGreaterThanOrEqualTo(String value) {
			addCriterion("course_contact_tel >=", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelLessThan(String value) {
			addCriterion("course_contact_tel <", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelLessThanOrEqualTo(String value) {
			addCriterion("course_contact_tel <=", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelLike(String value) {
			addCriterion("course_contact_tel like", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelNotLike(String value) {
			addCriterion("course_contact_tel not like", value, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelIn(List<String> values) {
			addCriterion("course_contact_tel in", values, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelNotIn(List<String> values) {
			addCriterion("course_contact_tel not in", values, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelBetween(String value1, String value2) {
			addCriterion("course_contact_tel between", value1, value2, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseContactTelNotBetween(String value1, String value2) {
			addCriterion("course_contact_tel not between", value1, value2, "courseContactTel");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumIsNull() {
			addCriterion("course_trainee_num is null");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumIsNotNull() {
			addCriterion("course_trainee_num is not null");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumEqualTo(Integer value) {
			addCriterion("course_trainee_num =", value, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumNotEqualTo(Integer value) {
			addCriterion("course_trainee_num <>", value, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumGreaterThan(Integer value) {
			addCriterion("course_trainee_num >", value, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("course_trainee_num >=", value, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumLessThan(Integer value) {
			addCriterion("course_trainee_num <", value, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumLessThanOrEqualTo(Integer value) {
			addCriterion("course_trainee_num <=", value, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumIn(List<Integer> values) {
			addCriterion("course_trainee_num in", values, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumNotIn(List<Integer> values) {
			addCriterion("course_trainee_num not in", values, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumBetween(Integer value1, Integer value2) {
			addCriterion("course_trainee_num between", value1, value2, "courseTraineeNum");
			return (Criteria) this;
		}

		public Criteria andCourseTraineeNumNotBetween(Integer value1, Integer value2) {
			addCriterion("course_trainee_num not between", value1, value2, "courseTraineeNum");
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

		public Criteria andCourseRuleIsNull() {
			addCriterion("course_rule is null");
			return (Criteria) this;
		}

		public Criteria andCourseRuleIsNotNull() {
			addCriterion("course_rule is not null");
			return (Criteria) this;
		}

		public Criteria andCourseRuleEqualTo(Short value) {
			addCriterion("course_rule =", value, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleNotEqualTo(Short value) {
			addCriterion("course_rule <>", value, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleGreaterThan(Short value) {
			addCriterion("course_rule >", value, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleGreaterThanOrEqualTo(Short value) {
			addCriterion("course_rule >=", value, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleLessThan(Short value) {
			addCriterion("course_rule <", value, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleLessThanOrEqualTo(Short value) {
			addCriterion("course_rule <=", value, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleIn(List<Short> values) {
			addCriterion("course_rule in", values, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleNotIn(List<Short> values) {
			addCriterion("course_rule not in", values, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleBetween(Short value1, Short value2) {
			addCriterion("course_rule between", value1, value2, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseRuleNotBetween(Short value1, Short value2) {
			addCriterion("course_rule not between", value1, value2, "courseRule");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberIsNull() {
			addCriterion("course_substitute_number is null");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberIsNotNull() {
			addCriterion("course_substitute_number is not null");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberEqualTo(Integer value) {
			addCriterion("course_substitute_number =", value, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberNotEqualTo(Integer value) {
			addCriterion("course_substitute_number <>", value, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberGreaterThan(Integer value) {
			addCriterion("course_substitute_number >", value, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("course_substitute_number >=", value, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberLessThan(Integer value) {
			addCriterion("course_substitute_number <", value, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberLessThanOrEqualTo(Integer value) {
			addCriterion("course_substitute_number <=", value, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberIn(List<Integer> values) {
			addCriterion("course_substitute_number in", values, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberNotIn(List<Integer> values) {
			addCriterion("course_substitute_number not in", values, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberBetween(Integer value1, Integer value2) {
			addCriterion("course_substitute_number between", value1, value2, "courseSubstituteNumber");
			return (Criteria) this;
		}

		public Criteria andCourseSubstituteNumberNotBetween(Integer value1, Integer value2) {
			addCriterion("course_substitute_number not between", value1, value2, "courseSubstituteNumber");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_course_model_offline
	 * @mbggenerated  Tue Jul 10 09:53:35 CST 2018
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
     * This class corresponds to the database table t_course_model_offline
     *
     * @mbggenerated do_not_delete_during_merge Tue Oct 25 10:53:00 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}