package com.deodio.elearning.persistence.model;

import java.util.Date;

public class TagsItemsRel {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.item_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private String itemId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.item_type
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private Short itemType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.tags_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private String tagsId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.create_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.create_time
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.update_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_tags_items_rel.update_time
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.id
	 * @return  the value of t_tags_items_rel.id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.id
	 * @param id  the value for t_tags_items_rel.id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.item_id
	 * @return  the value of t_tags_items_rel.item_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.item_id
	 * @param itemId  the value for t_tags_items_rel.item_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.item_type
	 * @return  the value of t_tags_items_rel.item_type
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public Short getItemType() {
		return itemType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.item_type
	 * @param itemType  the value for t_tags_items_rel.item_type
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setItemType(Short itemType) {
		this.itemType = itemType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.tags_id
	 * @return  the value of t_tags_items_rel.tags_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public String getTagsId() {
		return tagsId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.tags_id
	 * @param tagsId  the value for t_tags_items_rel.tags_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setTagsId(String tagsId) {
		this.tagsId = tagsId == null ? null : tagsId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.create_id
	 * @return  the value of t_tags_items_rel.create_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.create_id
	 * @param createId  the value for t_tags_items_rel.create_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.create_time
	 * @return  the value of t_tags_items_rel.create_time
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.create_time
	 * @param createTime  the value for t_tags_items_rel.create_time
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.update_id
	 * @return  the value of t_tags_items_rel.update_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.update_id
	 * @param updateId  the value for t_tags_items_rel.update_id
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_tags_items_rel.update_time
	 * @return  the value of t_tags_items_rel.update_time
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_tags_items_rel.update_time
	 * @param updateTime  the value for t_tags_items_rel.update_time
	 * @mbggenerated  Sat Oct 22 00:55:06 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}