package com.deodio.elearning.persistence.model;

import java.util.Date;

public class AccountCapability {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.account_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.capability_type
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private Integer capabilityType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.capability_item
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private String capabilityItem;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.create_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private String createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.create_time
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.update_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private String updateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_account_capability.update_time
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.id
     *
     * @return the value of t_account_capability.id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.id
     *
     * @param id the value for t_account_capability.id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.account_id
     *
     * @return the value of t_account_capability.account_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.account_id
     *
     * @param accountId the value for t_account_capability.account_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.capability_type
     *
     * @return the value of t_account_capability.capability_type
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public Integer getCapabilityType() {
        return capabilityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.capability_type
     *
     * @param capabilityType the value for t_account_capability.capability_type
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setCapabilityType(Integer capabilityType) {
        this.capabilityType = capabilityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.capability_item
     *
     * @return the value of t_account_capability.capability_item
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public String getCapabilityItem() {
        return capabilityItem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.capability_item
     *
     * @param capabilityItem the value for t_account_capability.capability_item
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setCapabilityItem(String capabilityItem) {
        this.capabilityItem = capabilityItem == null ? null : capabilityItem.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.create_id
     *
     * @return the value of t_account_capability.create_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.create_id
     *
     * @param createId the value for t_account_capability.create_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.create_time
     *
     * @return the value of t_account_capability.create_time
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.create_time
     *
     * @param createTime the value for t_account_capability.create_time
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.update_id
     *
     * @return the value of t_account_capability.update_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.update_id
     *
     * @param updateId the value for t_account_capability.update_id
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_account_capability.update_time
     *
     * @return the value of t_account_capability.update_time
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_account_capability.update_time
     *
     * @param updateTime the value for t_account_capability.update_time
     *
     * @mbggenerated Sun Aug 28 16:36:35 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}