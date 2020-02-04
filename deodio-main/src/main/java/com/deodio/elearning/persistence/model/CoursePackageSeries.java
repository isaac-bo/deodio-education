package com.deodio.elearning.persistence.model;

import java.util.Date;

public class CoursePackageSeries {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.package_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private String packageId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.series_name
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private String seriesName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.create_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.create_time
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.update_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.update_time
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.series_order
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private Short seriesOrder;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_course_package_series.account_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	private String accountId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.id
	 * @return  the value of t_course_package_series.id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.id
	 * @param id  the value for t_course_package_series.id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.package_id
	 * @return  the value of t_course_package_series.package_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public String getPackageId() {
		return packageId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.package_id
	 * @param packageId  the value for t_course_package_series.package_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setPackageId(String packageId) {
		this.packageId = packageId == null ? null : packageId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.series_name
	 * @return  the value of t_course_package_series.series_name
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public String getSeriesName() {
		return seriesName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.series_name
	 * @param seriesName  the value for t_course_package_series.series_name
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName == null ? null : seriesName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.create_id
	 * @return  the value of t_course_package_series.create_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.create_id
	 * @param createId  the value for t_course_package_series.create_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.create_time
	 * @return  the value of t_course_package_series.create_time
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.create_time
	 * @param createTime  the value for t_course_package_series.create_time
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.update_id
	 * @return  the value of t_course_package_series.update_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.update_id
	 * @param updateId  the value for t_course_package_series.update_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.update_time
	 * @return  the value of t_course_package_series.update_time
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.update_time
	 * @param updateTime  the value for t_course_package_series.update_time
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.series_order
	 * @return  the value of t_course_package_series.series_order
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public Short getSeriesOrder() {
		return seriesOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.series_order
	 * @param seriesOrder  the value for t_course_package_series.series_order
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setSeriesOrder(Short seriesOrder) {
		this.seriesOrder = seriesOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_course_package_series.account_id
	 * @return  the value of t_course_package_series.account_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_course_package_series.account_id
	 * @param accountId  the value for t_course_package_series.account_id
	 * @mbggenerated  Mon Nov 21 13:11:34 CST 2016
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
}