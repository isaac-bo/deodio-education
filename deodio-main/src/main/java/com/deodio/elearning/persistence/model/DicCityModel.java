package com.deodio.elearning.persistence.model;

public class DicCityModel {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_dic_city.province_id
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	private Integer provinceId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_dic_city.city_id
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	private Integer cityId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_dic_city.name
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_dic_city.hot_flag
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	private String hotFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_dic_city.short_name
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	private String shortName;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_dic_city.province_id
	 * @return  the value of t_dic_city.province_id
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_dic_city.province_id
	 * @param provinceId  the value for t_dic_city.province_id
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_dic_city.city_id
	 * @return  the value of t_dic_city.city_id
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_dic_city.city_id
	 * @param cityId  the value for t_dic_city.city_id
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_dic_city.name
	 * @return  the value of t_dic_city.name
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_dic_city.name
	 * @param name  the value for t_dic_city.name
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_dic_city.hot_flag
	 * @return  the value of t_dic_city.hot_flag
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public String getHotFlag() {
		return hotFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_dic_city.hot_flag
	 * @param hotFlag  the value for t_dic_city.hot_flag
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public void setHotFlag(String hotFlag) {
		this.hotFlag = hotFlag == null ? null : hotFlag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_dic_city.short_name
	 * @return  the value of t_dic_city.short_name
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_dic_city.short_name
	 * @param shortName  the value for t_dic_city.short_name
	 * @mbggenerated  Thu Feb 25 17:17:55 CST 2016
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName == null ? null : shortName.trim();
	}
}