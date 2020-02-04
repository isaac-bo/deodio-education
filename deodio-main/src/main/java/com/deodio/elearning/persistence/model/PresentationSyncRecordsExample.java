package com.deodio.elearning.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresentationSyncRecordsExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public PresentationSyncRecordsExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
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
			addCriterion("presentation_id between", value1, value2,
					"presentationId");
			return (Criteria) this;
		}

		public Criteria andPresentationIdNotBetween(String value1, String value2) {
			addCriterion("presentation_id not between", value1, value2,
					"presentationId");
			return (Criteria) this;
		}

		public Criteria andPointIdIsNull() {
			addCriterion("point_id is null");
			return (Criteria) this;
		}

		public Criteria andPointIdIsNotNull() {
			addCriterion("point_id is not null");
			return (Criteria) this;
		}

		public Criteria andPointIdEqualTo(String value) {
			addCriterion("point_id =", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdNotEqualTo(String value) {
			addCriterion("point_id <>", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdGreaterThan(String value) {
			addCriterion("point_id >", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdGreaterThanOrEqualTo(String value) {
			addCriterion("point_id >=", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdLessThan(String value) {
			addCriterion("point_id <", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdLessThanOrEqualTo(String value) {
			addCriterion("point_id <=", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdLike(String value) {
			addCriterion("point_id like", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdNotLike(String value) {
			addCriterion("point_id not like", value, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdIn(List<String> values) {
			addCriterion("point_id in", values, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdNotIn(List<String> values) {
			addCriterion("point_id not in", values, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdBetween(String value1, String value2) {
			addCriterion("point_id between", value1, value2, "pointId");
			return (Criteria) this;
		}

		public Criteria andPointIdNotBetween(String value1, String value2) {
			addCriterion("point_id not between", value1, value2, "pointId");
			return (Criteria) this;
		}

		public Criteria andRecordNameIsNull() {
			addCriterion("record_name is null");
			return (Criteria) this;
		}

		public Criteria andRecordNameIsNotNull() {
			addCriterion("record_name is not null");
			return (Criteria) this;
		}

		public Criteria andRecordNameEqualTo(String value) {
			addCriterion("record_name =", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameNotEqualTo(String value) {
			addCriterion("record_name <>", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameGreaterThan(String value) {
			addCriterion("record_name >", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameGreaterThanOrEqualTo(String value) {
			addCriterion("record_name >=", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameLessThan(String value) {
			addCriterion("record_name <", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameLessThanOrEqualTo(String value) {
			addCriterion("record_name <=", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameLike(String value) {
			addCriterion("record_name like", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameNotLike(String value) {
			addCriterion("record_name not like", value, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameIn(List<String> values) {
			addCriterion("record_name in", values, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameNotIn(List<String> values) {
			addCriterion("record_name not in", values, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameBetween(String value1, String value2) {
			addCriterion("record_name between", value1, value2, "recordName");
			return (Criteria) this;
		}

		public Criteria andRecordNameNotBetween(String value1, String value2) {
			addCriterion("record_name not between", value1, value2,
					"recordName");
			return (Criteria) this;
		}

		public Criteria andRecordSizeIsNull() {
			addCriterion("record_size is null");
			return (Criteria) this;
		}

		public Criteria andRecordSizeIsNotNull() {
			addCriterion("record_size is not null");
			return (Criteria) this;
		}

		public Criteria andRecordSizeEqualTo(Integer value) {
			addCriterion("record_size =", value, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeNotEqualTo(Integer value) {
			addCriterion("record_size <>", value, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeGreaterThan(Integer value) {
			addCriterion("record_size >", value, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeGreaterThanOrEqualTo(Integer value) {
			addCriterion("record_size >=", value, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeLessThan(Integer value) {
			addCriterion("record_size <", value, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeLessThanOrEqualTo(Integer value) {
			addCriterion("record_size <=", value, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeIn(List<Integer> values) {
			addCriterion("record_size in", values, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeNotIn(List<Integer> values) {
			addCriterion("record_size not in", values, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeBetween(Integer value1, Integer value2) {
			addCriterion("record_size between", value1, value2, "recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordSizeNotBetween(Integer value1, Integer value2) {
			addCriterion("record_size not between", value1, value2,
					"recordSize");
			return (Criteria) this;
		}

		public Criteria andRecordExtIsNull() {
			addCriterion("record_ext is null");
			return (Criteria) this;
		}

		public Criteria andRecordExtIsNotNull() {
			addCriterion("record_ext is not null");
			return (Criteria) this;
		}

		public Criteria andRecordExtEqualTo(String value) {
			addCriterion("record_ext =", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtNotEqualTo(String value) {
			addCriterion("record_ext <>", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtGreaterThan(String value) {
			addCriterion("record_ext >", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtGreaterThanOrEqualTo(String value) {
			addCriterion("record_ext >=", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtLessThan(String value) {
			addCriterion("record_ext <", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtLessThanOrEqualTo(String value) {
			addCriterion("record_ext <=", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtLike(String value) {
			addCriterion("record_ext like", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtNotLike(String value) {
			addCriterion("record_ext not like", value, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtIn(List<String> values) {
			addCriterion("record_ext in", values, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtNotIn(List<String> values) {
			addCriterion("record_ext not in", values, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtBetween(String value1, String value2) {
			addCriterion("record_ext between", value1, value2, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordExtNotBetween(String value1, String value2) {
			addCriterion("record_ext not between", value1, value2, "recordExt");
			return (Criteria) this;
		}

		public Criteria andRecordUrlIsNull() {
			addCriterion("record_url is null");
			return (Criteria) this;
		}

		public Criteria andRecordUrlIsNotNull() {
			addCriterion("record_url is not null");
			return (Criteria) this;
		}

		public Criteria andRecordUrlEqualTo(String value) {
			addCriterion("record_url =", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlNotEqualTo(String value) {
			addCriterion("record_url <>", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlGreaterThan(String value) {
			addCriterion("record_url >", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlGreaterThanOrEqualTo(String value) {
			addCriterion("record_url >=", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlLessThan(String value) {
			addCriterion("record_url <", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlLessThanOrEqualTo(String value) {
			addCriterion("record_url <=", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlLike(String value) {
			addCriterion("record_url like", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlNotLike(String value) {
			addCriterion("record_url not like", value, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlIn(List<String> values) {
			addCriterion("record_url in", values, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlNotIn(List<String> values) {
			addCriterion("record_url not in", values, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlBetween(String value1, String value2) {
			addCriterion("record_url between", value1, value2, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordUrlNotBetween(String value1, String value2) {
			addCriterion("record_url not between", value1, value2, "recordUrl");
			return (Criteria) this;
		}

		public Criteria andRecordDirIsNull() {
			addCriterion("record_dir is null");
			return (Criteria) this;
		}

		public Criteria andRecordDirIsNotNull() {
			addCriterion("record_dir is not null");
			return (Criteria) this;
		}

		public Criteria andRecordDirEqualTo(String value) {
			addCriterion("record_dir =", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirNotEqualTo(String value) {
			addCriterion("record_dir <>", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirGreaterThan(String value) {
			addCriterion("record_dir >", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirGreaterThanOrEqualTo(String value) {
			addCriterion("record_dir >=", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirLessThan(String value) {
			addCriterion("record_dir <", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirLessThanOrEqualTo(String value) {
			addCriterion("record_dir <=", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirLike(String value) {
			addCriterion("record_dir like", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirNotLike(String value) {
			addCriterion("record_dir not like", value, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirIn(List<String> values) {
			addCriterion("record_dir in", values, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirNotIn(List<String> values) {
			addCriterion("record_dir not in", values, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirBetween(String value1, String value2) {
			addCriterion("record_dir between", value1, value2, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordDirNotBetween(String value1, String value2) {
			addCriterion("record_dir not between", value1, value2, "recordDir");
			return (Criteria) this;
		}

		public Criteria andRecordLengthIsNull() {
			addCriterion("record_length is null");
			return (Criteria) this;
		}

		public Criteria andRecordLengthIsNotNull() {
			addCriterion("record_length is not null");
			return (Criteria) this;
		}

		public Criteria andRecordLengthEqualTo(Long value) {
			addCriterion("record_length =", value, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthNotEqualTo(Long value) {
			addCriterion("record_length <>", value, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthGreaterThan(Long value) {
			addCriterion("record_length >", value, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthGreaterThanOrEqualTo(Long value) {
			addCriterion("record_length >=", value, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthLessThan(Long value) {
			addCriterion("record_length <", value, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthLessThanOrEqualTo(Long value) {
			addCriterion("record_length <=", value, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthIn(List<Long> values) {
			addCriterion("record_length in", values, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthNotIn(List<Long> values) {
			addCriterion("record_length not in", values, "recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthBetween(Long value1, Long value2) {
			addCriterion("record_length between", value1, value2,
					"recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordLengthNotBetween(Long value1, Long value2) {
			addCriterion("record_length not between", value1, value2,
					"recordLength");
			return (Criteria) this;
		}

		public Criteria andRecordOrderIsNull() {
			addCriterion("record_order is null");
			return (Criteria) this;
		}

		public Criteria andRecordOrderIsNotNull() {
			addCriterion("record_order is not null");
			return (Criteria) this;
		}

		public Criteria andRecordOrderEqualTo(Integer value) {
			addCriterion("record_order =", value, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderNotEqualTo(Integer value) {
			addCriterion("record_order <>", value, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderGreaterThan(Integer value) {
			addCriterion("record_order >", value, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderGreaterThanOrEqualTo(Integer value) {
			addCriterion("record_order >=", value, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderLessThan(Integer value) {
			addCriterion("record_order <", value, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderLessThanOrEqualTo(Integer value) {
			addCriterion("record_order <=", value, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderIn(List<Integer> values) {
			addCriterion("record_order in", values, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderNotIn(List<Integer> values) {
			addCriterion("record_order not in", values, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderBetween(Integer value1, Integer value2) {
			addCriterion("record_order between", value1, value2, "recordOrder");
			return (Criteria) this;
		}

		public Criteria andRecordOrderNotBetween(Integer value1, Integer value2) {
			addCriterion("record_order not between", value1, value2,
					"recordOrder");
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
			addCriterion("create_time not between", value1, value2,
					"createTime");
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
			addCriterion("update_time not between", value1, value2,
					"updateTime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_presentation_sync_records
	 * @mbggenerated  Mon Apr 04 14:04:07 CST 2016
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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
     * This class corresponds to the database table t_presentation_sync_records
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 04 14:02:44 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}