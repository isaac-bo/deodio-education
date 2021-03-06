package com.deodio.elearning.persistence.model;

import java.util.Date;

public class Classification {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.classification_name
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String classificationName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.classification_desc
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String classificationDesc;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.classification_parent_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String classificationParentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.classification_rel_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String classificationRelId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.account_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String accountId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.create_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.create_time
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.update_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.update_time
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_classification.classification_level
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	private Long classificationLevel;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.id
	 * @return  the value of t_classification.id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.id
	 * @param id  the value for t_classification.id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.classification_name
	 * @return  the value of t_classification.classification_name
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getClassificationName() {
		return classificationName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.classification_name
	 * @param classificationName  the value for t_classification.classification_name
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName == null ? null
				: classificationName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.classification_desc
	 * @return  the value of t_classification.classification_desc
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getClassificationDesc() {
		return classificationDesc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.classification_desc
	 * @param classificationDesc  the value for t_classification.classification_desc
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setClassificationDesc(String classificationDesc) {
		this.classificationDesc = classificationDesc == null ? null
				: classificationDesc.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.classification_parent_id
	 * @return  the value of t_classification.classification_parent_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getClassificationParentId() {
		return classificationParentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.classification_parent_id
	 * @param classificationParentId  the value for t_classification.classification_parent_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setClassificationParentId(String classificationParentId) {
		this.classificationParentId = classificationParentId == null ? null
				: classificationParentId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.classification_rel_id
	 * @return  the value of t_classification.classification_rel_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getClassificationRelId() {
		return classificationRelId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.classification_rel_id
	 * @param classificationRelId  the value for t_classification.classification_rel_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setClassificationRelId(String classificationRelId) {
		this.classificationRelId = classificationRelId == null ? null
				: classificationRelId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.account_id
	 * @return  the value of t_classification.account_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.account_id
	 * @param accountId  the value for t_classification.account_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.create_id
	 * @return  the value of t_classification.create_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.create_id
	 * @param createId  the value for t_classification.create_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.create_time
	 * @return  the value of t_classification.create_time
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.create_time
	 * @param createTime  the value for t_classification.create_time
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.update_id
	 * @return  the value of t_classification.update_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.update_id
	 * @param updateId  the value for t_classification.update_id
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.update_time
	 * @return  the value of t_classification.update_time
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.update_time
	 * @param updateTime  the value for t_classification.update_time
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_classification.classification_level
	 * @return  the value of t_classification.classification_level
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public Long getClassificationLevel() {
		return classificationLevel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_classification.classification_level
	 * @param classificationLevel  the value for t_classification.classification_level
	 * @mbggenerated  Thu Jun 16 13:42:58 CST 2016
	 */
	public void setClassificationLevel(Long classificationLevel) {
		this.classificationLevel = classificationLevel;
	}
}