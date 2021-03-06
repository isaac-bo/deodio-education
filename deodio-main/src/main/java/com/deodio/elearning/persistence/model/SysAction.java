package com.deodio.elearning.persistence.model;

import java.util.Date;

public class SysAction {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_action.id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_action.action_name
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	private String actionName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_action.action_url
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	private String actionUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_action.create_id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_action.create_time
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_action.update_id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_action.update_time
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_action.id
	 * @return  the value of sys_action.id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_action.id
	 * @param id  the value for sys_action.id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_action.action_name
	 * @return  the value of sys_action.action_name
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_action.action_name
	 * @param actionName  the value for sys_action.action_name
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName == null ? null : actionName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_action.action_url
	 * @return  the value of sys_action.action_url
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public String getActionUrl() {
		return actionUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_action.action_url
	 * @param actionUrl  the value for sys_action.action_url
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl == null ? null : actionUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_action.create_id
	 * @return  the value of sys_action.create_id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_action.create_id
	 * @param createId  the value for sys_action.create_id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_action.create_time
	 * @return  the value of sys_action.create_time
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_action.create_time
	 * @param createTime  the value for sys_action.create_time
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_action.update_id
	 * @return  the value of sys_action.update_id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_action.update_id
	 * @param updateId  the value for sys_action.update_id
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_action.update_time
	 * @return  the value of sys_action.update_time
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_action.update_time
	 * @param updateTime  the value for sys_action.update_time
	 * @mbggenerated  Mon Jan 09 09:59:27 CST 2017
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}