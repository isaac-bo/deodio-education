package com.deodio.elearning.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoursePackageItemsRelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public CoursePackageItemsRelExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
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

		public Criteria andItemFrontIdIsNull() {
			addCriterion("item_front_id is null");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdIsNotNull() {
			addCriterion("item_front_id is not null");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdEqualTo(String value) {
			addCriterion("item_front_id =", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdNotEqualTo(String value) {
			addCriterion("item_front_id <>", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdGreaterThan(String value) {
			addCriterion("item_front_id >", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdGreaterThanOrEqualTo(String value) {
			addCriterion("item_front_id >=", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdLessThan(String value) {
			addCriterion("item_front_id <", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdLessThanOrEqualTo(String value) {
			addCriterion("item_front_id <=", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdLike(String value) {
			addCriterion("item_front_id like", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdNotLike(String value) {
			addCriterion("item_front_id not like", value, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdIn(List<String> values) {
			addCriterion("item_front_id in", values, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdNotIn(List<String> values) {
			addCriterion("item_front_id not in", values, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdBetween(String value1, String value2) {
			addCriterion("item_front_id between", value1, value2, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemFrontIdNotBetween(String value1, String value2) {
			addCriterion("item_front_id not between", value1, value2, "itemFrontId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdIsNull() {
			addCriterion("item_end_id is null");
			return (Criteria) this;
		}

		public Criteria andItemEndIdIsNotNull() {
			addCriterion("item_end_id is not null");
			return (Criteria) this;
		}

		public Criteria andItemEndIdEqualTo(String value) {
			addCriterion("item_end_id =", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdNotEqualTo(String value) {
			addCriterion("item_end_id <>", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdGreaterThan(String value) {
			addCriterion("item_end_id >", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdGreaterThanOrEqualTo(String value) {
			addCriterion("item_end_id >=", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdLessThan(String value) {
			addCriterion("item_end_id <", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdLessThanOrEqualTo(String value) {
			addCriterion("item_end_id <=", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdLike(String value) {
			addCriterion("item_end_id like", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdNotLike(String value) {
			addCriterion("item_end_id not like", value, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdIn(List<String> values) {
			addCriterion("item_end_id in", values, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdNotIn(List<String> values) {
			addCriterion("item_end_id not in", values, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdBetween(String value1, String value2) {
			addCriterion("item_end_id between", value1, value2, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemEndIdNotBetween(String value1, String value2) {
			addCriterion("item_end_id not between", value1, value2, "itemEndId");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeIsNull() {
			addCriterion("item_rel_type is null");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeIsNotNull() {
			addCriterion("item_rel_type is not null");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeEqualTo(Short value) {
			addCriterion("item_rel_type =", value, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeNotEqualTo(Short value) {
			addCriterion("item_rel_type <>", value, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeGreaterThan(Short value) {
			addCriterion("item_rel_type >", value, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeGreaterThanOrEqualTo(Short value) {
			addCriterion("item_rel_type >=", value, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeLessThan(Short value) {
			addCriterion("item_rel_type <", value, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeLessThanOrEqualTo(Short value) {
			addCriterion("item_rel_type <=", value, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeIn(List<Short> values) {
			addCriterion("item_rel_type in", values, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeNotIn(List<Short> values) {
			addCriterion("item_rel_type not in", values, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeBetween(Short value1, Short value2) {
			addCriterion("item_rel_type between", value1, value2, "itemRelType");
			return (Criteria) this;
		}

		public Criteria andItemRelTypeNotBetween(Short value1, Short value2) {
			addCriterion("item_rel_type not between", value1, value2, "itemRelType");
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

		public Criteria andPackageIdIsNull() {
			addCriterion("package_id is null");
			return (Criteria) this;
		}

		public Criteria andPackageIdIsNotNull() {
			addCriterion("package_id is not null");
			return (Criteria) this;
		}

		public Criteria andPackageIdEqualTo(String value) {
			addCriterion("package_id =", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdNotEqualTo(String value) {
			addCriterion("package_id <>", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdGreaterThan(String value) {
			addCriterion("package_id >", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdGreaterThanOrEqualTo(String value) {
			addCriterion("package_id >=", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdLessThan(String value) {
			addCriterion("package_id <", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdLessThanOrEqualTo(String value) {
			addCriterion("package_id <=", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdLike(String value) {
			addCriterion("package_id like", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdNotLike(String value) {
			addCriterion("package_id not like", value, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdIn(List<String> values) {
			addCriterion("package_id in", values, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdNotIn(List<String> values) {
			addCriterion("package_id not in", values, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdBetween(String value1, String value2) {
			addCriterion("package_id between", value1, value2, "packageId");
			return (Criteria) this;
		}

		public Criteria andPackageIdNotBetween(String value1, String value2) {
			addCriterion("package_id not between", value1, value2, "packageId");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_course_package_items_rel
	 * @mbggenerated  Wed Nov 16 11:15:30 CST 2016
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
     * This class corresponds to the database table t_course_package_items_rel
     *
     * @mbggenerated do_not_delete_during_merge Fri Oct 21 23:53:07 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}