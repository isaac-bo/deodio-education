package com.deodio.elearning.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresentationDiscussionExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public PresentationDiscussionExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
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

		public Criteria andDiscussionContentIsNull() {
			addCriterion("discussion_content is null");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentIsNotNull() {
			addCriterion("discussion_content is not null");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentEqualTo(String value) {
			addCriterion("discussion_content =", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentNotEqualTo(String value) {
			addCriterion("discussion_content <>", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentGreaterThan(String value) {
			addCriterion("discussion_content >", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentGreaterThanOrEqualTo(String value) {
			addCriterion("discussion_content >=", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentLessThan(String value) {
			addCriterion("discussion_content <", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentLessThanOrEqualTo(String value) {
			addCriterion("discussion_content <=", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentLike(String value) {
			addCriterion("discussion_content like", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentNotLike(String value) {
			addCriterion("discussion_content not like", value, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentIn(List<String> values) {
			addCriterion("discussion_content in", values, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentNotIn(List<String> values) {
			addCriterion("discussion_content not in", values, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentBetween(String value1, String value2) {
			addCriterion("discussion_content between", value1, value2, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andDiscussionContentNotBetween(String value1, String value2) {
			addCriterion("discussion_content not between", value1, value2, "discussionContent");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("create_date is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_date =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_date <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_date >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_date >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_date <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_date <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("create_date in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("create_date not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_date between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_date not between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNull() {
			addCriterion("update_date is null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNotNull() {
			addCriterion("update_date is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateEqualTo(Date value) {
			addCriterion("update_date =", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotEqualTo(Date value) {
			addCriterion("update_date <>", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThan(Date value) {
			addCriterion("update_date >", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("update_date >=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThan(Date value) {
			addCriterion("update_date <", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
			addCriterion("update_date <=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIn(List<Date> values) {
			addCriterion("update_date in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotIn(List<Date> values) {
			addCriterion("update_date not in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateBetween(Date value1, Date value2) {
			addCriterion("update_date between", value1, value2, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
			addCriterion("update_date not between", value1, value2, "updateDate");
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

		public Criteria andReplyCountIsNull() {
			addCriterion("reply_count is null");
			return (Criteria) this;
		}

		public Criteria andReplyCountIsNotNull() {
			addCriterion("reply_count is not null");
			return (Criteria) this;
		}

		public Criteria andReplyCountEqualTo(Integer value) {
			addCriterion("reply_count =", value, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountNotEqualTo(Integer value) {
			addCriterion("reply_count <>", value, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountGreaterThan(Integer value) {
			addCriterion("reply_count >", value, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("reply_count >=", value, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountLessThan(Integer value) {
			addCriterion("reply_count <", value, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountLessThanOrEqualTo(Integer value) {
			addCriterion("reply_count <=", value, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountIn(List<Integer> values) {
			addCriterion("reply_count in", values, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountNotIn(List<Integer> values) {
			addCriterion("reply_count not in", values, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountBetween(Integer value1, Integer value2) {
			addCriterion("reply_count between", value1, value2, "replyCount");
			return (Criteria) this;
		}

		public Criteria andReplyCountNotBetween(Integer value1, Integer value2) {
			addCriterion("reply_count not between", value1, value2, "replyCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountIsNull() {
			addCriterion("like_count is null");
			return (Criteria) this;
		}

		public Criteria andLikeCountIsNotNull() {
			addCriterion("like_count is not null");
			return (Criteria) this;
		}

		public Criteria andLikeCountEqualTo(Integer value) {
			addCriterion("like_count =", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountNotEqualTo(Integer value) {
			addCriterion("like_count <>", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountGreaterThan(Integer value) {
			addCriterion("like_count >", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("like_count >=", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountLessThan(Integer value) {
			addCriterion("like_count <", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountLessThanOrEqualTo(Integer value) {
			addCriterion("like_count <=", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountIn(List<Integer> values) {
			addCriterion("like_count in", values, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountNotIn(List<Integer> values) {
			addCriterion("like_count not in", values, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountBetween(Integer value1, Integer value2) {
			addCriterion("like_count between", value1, value2, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountNotBetween(Integer value1, Integer value2) {
			addCriterion("like_count not between", value1, value2, "likeCount");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_presentation_discussion
	 * @mbggenerated  Wed Aug 01 18:39:29 CST 2018
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
     * This class corresponds to the database table t_presentation_discussion
     *
     * @mbggenerated do_not_delete_during_merge Wed Aug 01 11:00:27 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}