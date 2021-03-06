package com.deodio.elearning.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoursePackageExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public CoursePackageExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
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

		public Criteria andPackageNameIsNull() {
			addCriterion("package_name is null");
			return (Criteria) this;
		}

		public Criteria andPackageNameIsNotNull() {
			addCriterion("package_name is not null");
			return (Criteria) this;
		}

		public Criteria andPackageNameEqualTo(String value) {
			addCriterion("package_name =", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameNotEqualTo(String value) {
			addCriterion("package_name <>", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameGreaterThan(String value) {
			addCriterion("package_name >", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameGreaterThanOrEqualTo(String value) {
			addCriterion("package_name >=", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameLessThan(String value) {
			addCriterion("package_name <", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameLessThanOrEqualTo(String value) {
			addCriterion("package_name <=", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameLike(String value) {
			addCriterion("package_name like", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameNotLike(String value) {
			addCriterion("package_name not like", value, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameIn(List<String> values) {
			addCriterion("package_name in", values, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameNotIn(List<String> values) {
			addCriterion("package_name not in", values, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameBetween(String value1, String value2) {
			addCriterion("package_name between", value1, value2, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageNameNotBetween(String value1, String value2) {
			addCriterion("package_name not between", value1, value2, "packageName");
			return (Criteria) this;
		}

		public Criteria andPackageDescIsNull() {
			addCriterion("package_desc is null");
			return (Criteria) this;
		}

		public Criteria andPackageDescIsNotNull() {
			addCriterion("package_desc is not null");
			return (Criteria) this;
		}

		public Criteria andPackageDescEqualTo(String value) {
			addCriterion("package_desc =", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescNotEqualTo(String value) {
			addCriterion("package_desc <>", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescGreaterThan(String value) {
			addCriterion("package_desc >", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescGreaterThanOrEqualTo(String value) {
			addCriterion("package_desc >=", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescLessThan(String value) {
			addCriterion("package_desc <", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescLessThanOrEqualTo(String value) {
			addCriterion("package_desc <=", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescLike(String value) {
			addCriterion("package_desc like", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescNotLike(String value) {
			addCriterion("package_desc not like", value, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescIn(List<String> values) {
			addCriterion("package_desc in", values, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescNotIn(List<String> values) {
			addCriterion("package_desc not in", values, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescBetween(String value1, String value2) {
			addCriterion("package_desc between", value1, value2, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageDescNotBetween(String value1, String value2) {
			addCriterion("package_desc not between", value1, value2, "packageDesc");
			return (Criteria) this;
		}

		public Criteria andPackageTypeIsNull() {
			addCriterion("package_type is null");
			return (Criteria) this;
		}

		public Criteria andPackageTypeIsNotNull() {
			addCriterion("package_type is not null");
			return (Criteria) this;
		}

		public Criteria andPackageTypeEqualTo(Short value) {
			addCriterion("package_type =", value, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeNotEqualTo(Short value) {
			addCriterion("package_type <>", value, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeGreaterThan(Short value) {
			addCriterion("package_type >", value, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeGreaterThanOrEqualTo(Short value) {
			addCriterion("package_type >=", value, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeLessThan(Short value) {
			addCriterion("package_type <", value, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeLessThanOrEqualTo(Short value) {
			addCriterion("package_type <=", value, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeIn(List<Short> values) {
			addCriterion("package_type in", values, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeNotIn(List<Short> values) {
			addCriterion("package_type not in", values, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeBetween(Short value1, Short value2) {
			addCriterion("package_type between", value1, value2, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageTypeNotBetween(Short value1, Short value2) {
			addCriterion("package_type not between", value1, value2, "packageType");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgIsNull() {
			addCriterion("package_cover_img is null");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgIsNotNull() {
			addCriterion("package_cover_img is not null");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgEqualTo(String value) {
			addCriterion("package_cover_img =", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgNotEqualTo(String value) {
			addCriterion("package_cover_img <>", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgGreaterThan(String value) {
			addCriterion("package_cover_img >", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgGreaterThanOrEqualTo(String value) {
			addCriterion("package_cover_img >=", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgLessThan(String value) {
			addCriterion("package_cover_img <", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgLessThanOrEqualTo(String value) {
			addCriterion("package_cover_img <=", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgLike(String value) {
			addCriterion("package_cover_img like", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgNotLike(String value) {
			addCriterion("package_cover_img not like", value, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgIn(List<String> values) {
			addCriterion("package_cover_img in", values, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgNotIn(List<String> values) {
			addCriterion("package_cover_img not in", values, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgBetween(String value1, String value2) {
			addCriterion("package_cover_img between", value1, value2, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageCoverImgNotBetween(String value1, String value2) {
			addCriterion("package_cover_img not between", value1, value2, "packageCoverImg");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesIsNull() {
			addCriterion("package_series is null");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesIsNotNull() {
			addCriterion("package_series is not null");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesEqualTo(Short value) {
			addCriterion("package_series =", value, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesNotEqualTo(Short value) {
			addCriterion("package_series <>", value, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesGreaterThan(Short value) {
			addCriterion("package_series >", value, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesGreaterThanOrEqualTo(Short value) {
			addCriterion("package_series >=", value, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesLessThan(Short value) {
			addCriterion("package_series <", value, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesLessThanOrEqualTo(Short value) {
			addCriterion("package_series <=", value, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesIn(List<Short> values) {
			addCriterion("package_series in", values, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesNotIn(List<Short> values) {
			addCriterion("package_series not in", values, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesBetween(Short value1, Short value2) {
			addCriterion("package_series between", value1, value2, "packageSeries");
			return (Criteria) this;
		}

		public Criteria andPackageSeriesNotBetween(Short value1, Short value2) {
			addCriterion("package_series not between", value1, value2, "packageSeries");
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

		public Criteria andIsPublishIsNull() {
			addCriterion("is_publish is null");
			return (Criteria) this;
		}

		public Criteria andIsPublishIsNotNull() {
			addCriterion("is_publish is not null");
			return (Criteria) this;
		}

		public Criteria andIsPublishEqualTo(Short value) {
			addCriterion("is_publish =", value, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishNotEqualTo(Short value) {
			addCriterion("is_publish <>", value, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishGreaterThan(Short value) {
			addCriterion("is_publish >", value, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishGreaterThanOrEqualTo(Short value) {
			addCriterion("is_publish >=", value, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishLessThan(Short value) {
			addCriterion("is_publish <", value, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishLessThanOrEqualTo(Short value) {
			addCriterion("is_publish <=", value, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishIn(List<Short> values) {
			addCriterion("is_publish in", values, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishNotIn(List<Short> values) {
			addCriterion("is_publish not in", values, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishBetween(Short value1, Short value2) {
			addCriterion("is_publish between", value1, value2, "isPublish");
			return (Criteria) this;
		}

		public Criteria andIsPublishNotBetween(Short value1, Short value2) {
			addCriterion("is_publish not between", value1, value2, "isPublish");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgIsNull() {
			addCriterion("package_background_img is null");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgIsNotNull() {
			addCriterion("package_background_img is not null");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgEqualTo(String value) {
			addCriterion("package_background_img =", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgNotEqualTo(String value) {
			addCriterion("package_background_img <>", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgGreaterThan(String value) {
			addCriterion("package_background_img >", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgGreaterThanOrEqualTo(String value) {
			addCriterion("package_background_img >=", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgLessThan(String value) {
			addCriterion("package_background_img <", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgLessThanOrEqualTo(String value) {
			addCriterion("package_background_img <=", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgLike(String value) {
			addCriterion("package_background_img like", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgNotLike(String value) {
			addCriterion("package_background_img not like", value, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgIn(List<String> values) {
			addCriterion("package_background_img in", values, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgNotIn(List<String> values) {
			addCriterion("package_background_img not in", values, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgBetween(String value1, String value2) {
			addCriterion("package_background_img between", value1, value2, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageBackgroundImgNotBetween(String value1, String value2) {
			addCriterion("package_background_img not between", value1, value2, "packageBackgroundImg");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveIsNull() {
			addCriterion("package_objective is null");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveIsNotNull() {
			addCriterion("package_objective is not null");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveEqualTo(String value) {
			addCriterion("package_objective =", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveNotEqualTo(String value) {
			addCriterion("package_objective <>", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveGreaterThan(String value) {
			addCriterion("package_objective >", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveGreaterThanOrEqualTo(String value) {
			addCriterion("package_objective >=", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveLessThan(String value) {
			addCriterion("package_objective <", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveLessThanOrEqualTo(String value) {
			addCriterion("package_objective <=", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveLike(String value) {
			addCriterion("package_objective like", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveNotLike(String value) {
			addCriterion("package_objective not like", value, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveIn(List<String> values) {
			addCriterion("package_objective in", values, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveNotIn(List<String> values) {
			addCriterion("package_objective not in", values, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveBetween(String value1, String value2) {
			addCriterion("package_objective between", value1, value2, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageObjectiveNotBetween(String value1, String value2) {
			addCriterion("package_objective not between", value1, value2, "packageObjective");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionIsNull() {
			addCriterion("package_infomartion is null");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionIsNotNull() {
			addCriterion("package_infomartion is not null");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionEqualTo(String value) {
			addCriterion("package_infomartion =", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionNotEqualTo(String value) {
			addCriterion("package_infomartion <>", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionGreaterThan(String value) {
			addCriterion("package_infomartion >", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionGreaterThanOrEqualTo(String value) {
			addCriterion("package_infomartion >=", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionLessThan(String value) {
			addCriterion("package_infomartion <", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionLessThanOrEqualTo(String value) {
			addCriterion("package_infomartion <=", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionLike(String value) {
			addCriterion("package_infomartion like", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionNotLike(String value) {
			addCriterion("package_infomartion not like", value, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionIn(List<String> values) {
			addCriterion("package_infomartion in", values, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionNotIn(List<String> values) {
			addCriterion("package_infomartion not in", values, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionBetween(String value1, String value2) {
			addCriterion("package_infomartion between", value1, value2, "packageInfomartion");
			return (Criteria) this;
		}

		public Criteria andPackageInfomartionNotBetween(String value1, String value2) {
			addCriterion("package_infomartion not between", value1, value2, "packageInfomartion");
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

		public Criteria andGroupIdIsNull() {
			addCriterion("group_id is null");
			return (Criteria) this;
		}

		public Criteria andGroupIdIsNotNull() {
			addCriterion("group_id is not null");
			return (Criteria) this;
		}

		public Criteria andGroupIdEqualTo(String value) {
			addCriterion("group_id =", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotEqualTo(String value) {
			addCriterion("group_id <>", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdGreaterThan(String value) {
			addCriterion("group_id >", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdGreaterThanOrEqualTo(String value) {
			addCriterion("group_id >=", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLessThan(String value) {
			addCriterion("group_id <", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLessThanOrEqualTo(String value) {
			addCriterion("group_id <=", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLike(String value) {
			addCriterion("group_id like", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotLike(String value) {
			addCriterion("group_id not like", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdIn(List<String> values) {
			addCriterion("group_id in", values, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotIn(List<String> values) {
			addCriterion("group_id not in", values, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdBetween(String value1, String value2) {
			addCriterion("group_id between", value1, value2, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotBetween(String value1, String value2) {
			addCriterion("group_id not between", value1, value2, "groupId");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_course_package
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
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
     * This class corresponds to the database table t_course_package
     *
     * @mbggenerated do_not_delete_during_merge Fri Oct 21 23:53:07 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}