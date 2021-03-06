package com.deodio.elearning.persistence.model;

import java.util.Date;

public class Message {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_message.message_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	private String messageId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_message.send_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	private String sendId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_message.rec_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	private String recId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_message.message_text_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	private String messageTextId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_message.view_status
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	private String viewStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_message.view_date
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	private Date viewDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_message.create_date
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	private Date createDate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_message.message_id
	 * @return  the value of t_message.message_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_message.message_id
	 * @param messageId  the value for t_message.message_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId == null ? null : messageId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_message.send_id
	 * @return  the value of t_message.send_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public String getSendId() {
		return sendId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_message.send_id
	 * @param sendId  the value for t_message.send_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public void setSendId(String sendId) {
		this.sendId = sendId == null ? null : sendId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_message.rec_id
	 * @return  the value of t_message.rec_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public String getRecId() {
		return recId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_message.rec_id
	 * @param recId  the value for t_message.rec_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public void setRecId(String recId) {
		this.recId = recId == null ? null : recId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_message.message_text_id
	 * @return  the value of t_message.message_text_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public String getMessageTextId() {
		return messageTextId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_message.message_text_id
	 * @param messageTextId  the value for t_message.message_text_id
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public void setMessageTextId(String messageTextId) {
		this.messageTextId = messageTextId == null ? null : messageTextId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_message.view_status
	 * @return  the value of t_message.view_status
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public String getViewStatus() {
		return viewStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_message.view_status
	 * @param viewStatus  the value for t_message.view_status
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus == null ? null : viewStatus.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_message.view_date
	 * @return  the value of t_message.view_date
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public Date getViewDate() {
		return viewDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_message.view_date
	 * @param viewDate  the value for t_message.view_date
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_message.create_date
	 * @return  the value of t_message.create_date
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_message.create_date
	 * @param createDate  the value for t_message.create_date
	 * @mbggenerated  Wed Feb 22 14:54:21 CST 2017
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}