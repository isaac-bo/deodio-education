package com.deodio.elearning.persistence.model;

import java.util.Date;

public class PresentationSyncSlides {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.presentation_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String presentationId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String slideId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_name
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String slideName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_size
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private Long slideSize;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_ext
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String slideExt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_url
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String slideUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_dir
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String slideDir;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_order
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private Integer slideOrder;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.create_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.create_time
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.update_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.update_time
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_presentation_sync_slides.slide_original_name
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	private String slideOriginalName;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.id
	 * @return  the value of t_presentation_sync_slides.id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.id
	 * @param id  the value for t_presentation_sync_slides.id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.presentation_id
	 * @return  the value of t_presentation_sync_slides.presentation_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getPresentationId() {
		return presentationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.presentation_id
	 * @param presentationId  the value for t_presentation_sync_slides.presentation_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setPresentationId(String presentationId) {
		this.presentationId = presentationId == null ? null : presentationId
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_id
	 * @return  the value of t_presentation_sync_slides.slide_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getSlideId() {
		return slideId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_id
	 * @param slideId  the value for t_presentation_sync_slides.slide_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideId(String slideId) {
		this.slideId = slideId == null ? null : slideId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_name
	 * @return  the value of t_presentation_sync_slides.slide_name
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getSlideName() {
		return slideName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_name
	 * @param slideName  the value for t_presentation_sync_slides.slide_name
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideName(String slideName) {
		this.slideName = slideName == null ? null : slideName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_size
	 * @return  the value of t_presentation_sync_slides.slide_size
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public Long getSlideSize() {
		return slideSize;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_size
	 * @param slideSize  the value for t_presentation_sync_slides.slide_size
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideSize(Long slideSize) {
		this.slideSize = slideSize;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_ext
	 * @return  the value of t_presentation_sync_slides.slide_ext
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getSlideExt() {
		return slideExt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_ext
	 * @param slideExt  the value for t_presentation_sync_slides.slide_ext
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideExt(String slideExt) {
		this.slideExt = slideExt == null ? null : slideExt.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_url
	 * @return  the value of t_presentation_sync_slides.slide_url
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getSlideUrl() {
		return slideUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_url
	 * @param slideUrl  the value for t_presentation_sync_slides.slide_url
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideUrl(String slideUrl) {
		this.slideUrl = slideUrl == null ? null : slideUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_dir
	 * @return  the value of t_presentation_sync_slides.slide_dir
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getSlideDir() {
		return slideDir;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_dir
	 * @param slideDir  the value for t_presentation_sync_slides.slide_dir
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideDir(String slideDir) {
		this.slideDir = slideDir == null ? null : slideDir.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_order
	 * @return  the value of t_presentation_sync_slides.slide_order
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public Integer getSlideOrder() {
		return slideOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_order
	 * @param slideOrder  the value for t_presentation_sync_slides.slide_order
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideOrder(Integer slideOrder) {
		this.slideOrder = slideOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.create_id
	 * @return  the value of t_presentation_sync_slides.create_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.create_id
	 * @param createId  the value for t_presentation_sync_slides.create_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.create_time
	 * @return  the value of t_presentation_sync_slides.create_time
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.create_time
	 * @param createTime  the value for t_presentation_sync_slides.create_time
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.update_id
	 * @return  the value of t_presentation_sync_slides.update_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.update_id
	 * @param updateId  the value for t_presentation_sync_slides.update_id
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.update_time
	 * @return  the value of t_presentation_sync_slides.update_time
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.update_time
	 * @param updateTime  the value for t_presentation_sync_slides.update_time
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_presentation_sync_slides.slide_original_name
	 * @return  the value of t_presentation_sync_slides.slide_original_name
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public String getSlideOriginalName() {
		return slideOriginalName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_presentation_sync_slides.slide_original_name
	 * @param slideOriginalName  the value for t_presentation_sync_slides.slide_original_name
	 * @mbggenerated  Tue May 17 09:47:23 CST 2016
	 */
	public void setSlideOriginalName(String slideOriginalName) {
		this.slideOriginalName = slideOriginalName == null ? null
				: slideOriginalName.trim();
	}
}