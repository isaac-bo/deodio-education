package com.deodio.elearning.persistence.model;

import java.util.Date;

public class AccountAttachment {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.account_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String accountId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.header_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String headerImgUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.header_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String headerImgDir;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.logo_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String logoImgUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.logo_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String logoImgDir;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.footer_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String footerImgUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.footer_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String footerImgDir;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.banner_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String bannerImgDir;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.banner_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String bannerImgUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.create_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.create_time
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.update_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_account_attachment.update_time
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.id
	 * @return  the value of t_account_attachment.id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.id
	 * @param id  the value for t_account_attachment.id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.account_id
	 * @return  the value of t_account_attachment.account_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.account_id
	 * @param accountId  the value for t_account_attachment.account_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.header_img_url
	 * @return  the value of t_account_attachment.header_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getHeaderImgUrl() {
		return headerImgUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.header_img_url
	 * @param headerImgUrl  the value for t_account_attachment.header_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setHeaderImgUrl(String headerImgUrl) {
		this.headerImgUrl = headerImgUrl == null ? null : headerImgUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.header_img_dir
	 * @return  the value of t_account_attachment.header_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getHeaderImgDir() {
		return headerImgDir;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.header_img_dir
	 * @param headerImgDir  the value for t_account_attachment.header_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setHeaderImgDir(String headerImgDir) {
		this.headerImgDir = headerImgDir == null ? null : headerImgDir.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.logo_img_url
	 * @return  the value of t_account_attachment.logo_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getLogoImgUrl() {
		return logoImgUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.logo_img_url
	 * @param logoImgUrl  the value for t_account_attachment.logo_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setLogoImgUrl(String logoImgUrl) {
		this.logoImgUrl = logoImgUrl == null ? null : logoImgUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.logo_img_dir
	 * @return  the value of t_account_attachment.logo_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getLogoImgDir() {
		return logoImgDir;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.logo_img_dir
	 * @param logoImgDir  the value for t_account_attachment.logo_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setLogoImgDir(String logoImgDir) {
		this.logoImgDir = logoImgDir == null ? null : logoImgDir.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.footer_img_url
	 * @return  the value of t_account_attachment.footer_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getFooterImgUrl() {
		return footerImgUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.footer_img_url
	 * @param footerImgUrl  the value for t_account_attachment.footer_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setFooterImgUrl(String footerImgUrl) {
		this.footerImgUrl = footerImgUrl == null ? null : footerImgUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.footer_img_dir
	 * @return  the value of t_account_attachment.footer_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getFooterImgDir() {
		return footerImgDir;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.footer_img_dir
	 * @param footerImgDir  the value for t_account_attachment.footer_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setFooterImgDir(String footerImgDir) {
		this.footerImgDir = footerImgDir == null ? null : footerImgDir.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.banner_img_dir
	 * @return  the value of t_account_attachment.banner_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getBannerImgDir() {
		return bannerImgDir;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.banner_img_dir
	 * @param bannerImgDir  the value for t_account_attachment.banner_img_dir
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setBannerImgDir(String bannerImgDir) {
		this.bannerImgDir = bannerImgDir == null ? null : bannerImgDir.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.banner_img_url
	 * @return  the value of t_account_attachment.banner_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getBannerImgUrl() {
		return bannerImgUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.banner_img_url
	 * @param bannerImgUrl  the value for t_account_attachment.banner_img_url
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setBannerImgUrl(String bannerImgUrl) {
		this.bannerImgUrl = bannerImgUrl == null ? null : bannerImgUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.create_id
	 * @return  the value of t_account_attachment.create_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.create_id
	 * @param createId  the value for t_account_attachment.create_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.create_time
	 * @return  the value of t_account_attachment.create_time
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.create_time
	 * @param createTime  the value for t_account_attachment.create_time
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.update_id
	 * @return  the value of t_account_attachment.update_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.update_id
	 * @param updateId  the value for t_account_attachment.update_id
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_account_attachment.update_time
	 * @return  the value of t_account_attachment.update_time
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_account_attachment.update_time
	 * @param updateTime  the value for t_account_attachment.update_time
	 * @mbggenerated  Sun Aug 28 14:52:37 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}