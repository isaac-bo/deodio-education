package com.deodio.elearning.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SlidesExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public SlidesExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
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

		public Criteria andSlideNameIsNull() {
			addCriterion("slide_name is null");
			return (Criteria) this;
		}

		public Criteria andSlideNameIsNotNull() {
			addCriterion("slide_name is not null");
			return (Criteria) this;
		}

		public Criteria andSlideNameEqualTo(String value) {
			addCriterion("slide_name =", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameNotEqualTo(String value) {
			addCriterion("slide_name <>", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameGreaterThan(String value) {
			addCriterion("slide_name >", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameGreaterThanOrEqualTo(String value) {
			addCriterion("slide_name >=", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameLessThan(String value) {
			addCriterion("slide_name <", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameLessThanOrEqualTo(String value) {
			addCriterion("slide_name <=", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameLike(String value) {
			addCriterion("slide_name like", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameNotLike(String value) {
			addCriterion("slide_name not like", value, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameIn(List<String> values) {
			addCriterion("slide_name in", values, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameNotIn(List<String> values) {
			addCriterion("slide_name not in", values, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameBetween(String value1, String value2) {
			addCriterion("slide_name between", value1, value2, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideNameNotBetween(String value1, String value2) {
			addCriterion("slide_name not between", value1, value2, "slideName");
			return (Criteria) this;
		}

		public Criteria andSlideSizeIsNull() {
			addCriterion("slide_size is null");
			return (Criteria) this;
		}

		public Criteria andSlideSizeIsNotNull() {
			addCriterion("slide_size is not null");
			return (Criteria) this;
		}

		public Criteria andSlideSizeEqualTo(Long value) {
			addCriterion("slide_size =", value, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeNotEqualTo(Long value) {
			addCriterion("slide_size <>", value, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeGreaterThan(Long value) {
			addCriterion("slide_size >", value, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeGreaterThanOrEqualTo(Long value) {
			addCriterion("slide_size >=", value, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeLessThan(Long value) {
			addCriterion("slide_size <", value, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeLessThanOrEqualTo(Long value) {
			addCriterion("slide_size <=", value, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeIn(List<Long> values) {
			addCriterion("slide_size in", values, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeNotIn(List<Long> values) {
			addCriterion("slide_size not in", values, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeBetween(Long value1, Long value2) {
			addCriterion("slide_size between", value1, value2, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideSizeNotBetween(Long value1, Long value2) {
			addCriterion("slide_size not between", value1, value2, "slideSize");
			return (Criteria) this;
		}

		public Criteria andSlideExtIsNull() {
			addCriterion("slide_ext is null");
			return (Criteria) this;
		}

		public Criteria andSlideExtIsNotNull() {
			addCriterion("slide_ext is not null");
			return (Criteria) this;
		}

		public Criteria andSlideExtEqualTo(String value) {
			addCriterion("slide_ext =", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtNotEqualTo(String value) {
			addCriterion("slide_ext <>", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtGreaterThan(String value) {
			addCriterion("slide_ext >", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtGreaterThanOrEqualTo(String value) {
			addCriterion("slide_ext >=", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtLessThan(String value) {
			addCriterion("slide_ext <", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtLessThanOrEqualTo(String value) {
			addCriterion("slide_ext <=", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtLike(String value) {
			addCriterion("slide_ext like", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtNotLike(String value) {
			addCriterion("slide_ext not like", value, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtIn(List<String> values) {
			addCriterion("slide_ext in", values, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtNotIn(List<String> values) {
			addCriterion("slide_ext not in", values, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtBetween(String value1, String value2) {
			addCriterion("slide_ext between", value1, value2, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideExtNotBetween(String value1, String value2) {
			addCriterion("slide_ext not between", value1, value2, "slideExt");
			return (Criteria) this;
		}

		public Criteria andSlideUrlIsNull() {
			addCriterion("slide_url is null");
			return (Criteria) this;
		}

		public Criteria andSlideUrlIsNotNull() {
			addCriterion("slide_url is not null");
			return (Criteria) this;
		}

		public Criteria andSlideUrlEqualTo(String value) {
			addCriterion("slide_url =", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlNotEqualTo(String value) {
			addCriterion("slide_url <>", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlGreaterThan(String value) {
			addCriterion("slide_url >", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlGreaterThanOrEqualTo(String value) {
			addCriterion("slide_url >=", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlLessThan(String value) {
			addCriterion("slide_url <", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlLessThanOrEqualTo(String value) {
			addCriterion("slide_url <=", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlLike(String value) {
			addCriterion("slide_url like", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlNotLike(String value) {
			addCriterion("slide_url not like", value, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlIn(List<String> values) {
			addCriterion("slide_url in", values, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlNotIn(List<String> values) {
			addCriterion("slide_url not in", values, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlBetween(String value1, String value2) {
			addCriterion("slide_url between", value1, value2, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideUrlNotBetween(String value1, String value2) {
			addCriterion("slide_url not between", value1, value2, "slideUrl");
			return (Criteria) this;
		}

		public Criteria andSlideDirIsNull() {
			addCriterion("slide_dir is null");
			return (Criteria) this;
		}

		public Criteria andSlideDirIsNotNull() {
			addCriterion("slide_dir is not null");
			return (Criteria) this;
		}

		public Criteria andSlideDirEqualTo(String value) {
			addCriterion("slide_dir =", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirNotEqualTo(String value) {
			addCriterion("slide_dir <>", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirGreaterThan(String value) {
			addCriterion("slide_dir >", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirGreaterThanOrEqualTo(String value) {
			addCriterion("slide_dir >=", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirLessThan(String value) {
			addCriterion("slide_dir <", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirLessThanOrEqualTo(String value) {
			addCriterion("slide_dir <=", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirLike(String value) {
			addCriterion("slide_dir like", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirNotLike(String value) {
			addCriterion("slide_dir not like", value, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirIn(List<String> values) {
			addCriterion("slide_dir in", values, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirNotIn(List<String> values) {
			addCriterion("slide_dir not in", values, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirBetween(String value1, String value2) {
			addCriterion("slide_dir between", value1, value2, "slideDir");
			return (Criteria) this;
		}

		public Criteria andSlideDirNotBetween(String value1, String value2) {
			addCriterion("slide_dir not between", value1, value2, "slideDir");
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

		public Criteria andSlideOriginalNameIsNull() {
			addCriterion("slide_original_name is null");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameIsNotNull() {
			addCriterion("slide_original_name is not null");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameEqualTo(String value) {
			addCriterion("slide_original_name =", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameNotEqualTo(String value) {
			addCriterion("slide_original_name <>", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameGreaterThan(String value) {
			addCriterion("slide_original_name >", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameGreaterThanOrEqualTo(String value) {
			addCriterion("slide_original_name >=", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameLessThan(String value) {
			addCriterion("slide_original_name <", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameLessThanOrEqualTo(String value) {
			addCriterion("slide_original_name <=", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameLike(String value) {
			addCriterion("slide_original_name like", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameNotLike(String value) {
			addCriterion("slide_original_name not like", value, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameIn(List<String> values) {
			addCriterion("slide_original_name in", values, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameNotIn(List<String> values) {
			addCriterion("slide_original_name not in", values, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameBetween(String value1, String value2) {
			addCriterion("slide_original_name between", value1, value2, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andSlideOriginalNameNotBetween(String value1, String value2) {
			addCriterion("slide_original_name not between", value1, value2, "slideOriginalName");
			return (Criteria) this;
		}

		public Criteria andPresentationIdIsNull() {
			addCriterion("presentation_id is null");
			return (Criteria) this;
		}

		public Criteria andPresentationIdIsNotNull() {
			addCriterion("presentation_id is not null");
			return (Criteria) this;
		}

		public Criteria andPresentationIdEqualTo(String value) {
			addCriterion("presentation_id =", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdNotEqualTo(String value) {
			addCriterion("presentation_id <>", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdGreaterThan(String value) {
			addCriterion("presentation_id >", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdGreaterThanOrEqualTo(String value) {
			addCriterion("presentation_id >=", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdLessThan(String value) {
			addCriterion("presentation_id <", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdLessThanOrEqualTo(String value) {
			addCriterion("presentation_id <=", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdLike(String value) {
			addCriterion("presentation_id like", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdNotLike(String value) {
			addCriterion("presentation_id not like", value, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdIn(List<String> values) {
			addCriterion("presentation_id in", values, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdNotIn(List<String> values) {
			addCriterion("presentation_id not in", values, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdBetween(String value1, String value2) {
			addCriterion("presentation_id between", value1, value2, "presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdNotBetween(String value1, String value2) {
			addCriterion("presentation_id not between", value1, value2, "presentationId");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_slides
	 * @mbggenerated  Tue Aug 21 10:28:08 CST 2018
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
     * This class corresponds to the database table t_slides
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 25 15:34:59 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}