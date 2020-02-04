package com.deodio.elearning.persistence.model;

import java.util.Date;

public class Locations {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.location_name
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String locationName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.location_desc
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String locationDesc;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.location_url
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String locationUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.is_enabled
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private Integer isEnabled;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.create_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.create_time
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.update_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.update_time
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.location_address
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String locationAddress;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.location_longitude
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String locationLongitude;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.location_latitude
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String locationLatitude;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.account_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private String accountId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.location_zoom
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private Integer locationZoom;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.country_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private Integer countryId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.province_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private Integer provinceId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_locations.city_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	private Integer cityId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.id
	 * @return  the value of t_locations.id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.id
	 * @param id  the value for t_locations.id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.location_name
	 * @return  the value of t_locations.location_name
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.location_name
	 * @param locationName  the value for t_locations.location_name
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName == null ? null : locationName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.location_desc
	 * @return  the value of t_locations.location_desc
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getLocationDesc() {
		return locationDesc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.location_desc
	 * @param locationDesc  the value for t_locations.location_desc
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc == null ? null : locationDesc.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.location_url
	 * @return  the value of t_locations.location_url
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getLocationUrl() {
		return locationUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.location_url
	 * @param locationUrl  the value for t_locations.location_url
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl == null ? null : locationUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.is_enabled
	 * @return  the value of t_locations.is_enabled
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public Integer getIsEnabled() {
		return isEnabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.is_enabled
	 * @param isEnabled  the value for t_locations.is_enabled
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.create_id
	 * @return  the value of t_locations.create_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.create_id
	 * @param createId  the value for t_locations.create_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.create_time
	 * @return  the value of t_locations.create_time
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.create_time
	 * @param createTime  the value for t_locations.create_time
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.update_id
	 * @return  the value of t_locations.update_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.update_id
	 * @param updateId  the value for t_locations.update_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.update_time
	 * @return  the value of t_locations.update_time
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.update_time
	 * @param updateTime  the value for t_locations.update_time
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.location_address
	 * @return  the value of t_locations.location_address
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getLocationAddress() {
		return locationAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.location_address
	 * @param locationAddress  the value for t_locations.location_address
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress == null ? null : locationAddress.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.location_longitude
	 * @return  the value of t_locations.location_longitude
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getLocationLongitude() {
		return locationLongitude;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.location_longitude
	 * @param locationLongitude  the value for t_locations.location_longitude
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude == null ? null : locationLongitude.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.location_latitude
	 * @return  the value of t_locations.location_latitude
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getLocationLatitude() {
		return locationLatitude;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.location_latitude
	 * @param locationLatitude  the value for t_locations.location_latitude
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude == null ? null : locationLatitude.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.account_id
	 * @return  the value of t_locations.account_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.account_id
	 * @param accountId  the value for t_locations.account_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.location_zoom
	 * @return  the value of t_locations.location_zoom
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public Integer getLocationZoom() {
		return locationZoom;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.location_zoom
	 * @param locationZoom  the value for t_locations.location_zoom
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setLocationZoom(Integer locationZoom) {
		this.locationZoom = locationZoom;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.country_id
	 * @return  the value of t_locations.country_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.country_id
	 * @param countryId  the value for t_locations.country_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.province_id
	 * @return  the value of t_locations.province_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.province_id
	 * @param provinceId  the value for t_locations.province_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_locations.city_id
	 * @return  the value of t_locations.city_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_locations.city_id
	 * @param cityId  the value for t_locations.city_id
	 * @mbggenerated  Tue Jun 21 11:07:06 CST 2016
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}