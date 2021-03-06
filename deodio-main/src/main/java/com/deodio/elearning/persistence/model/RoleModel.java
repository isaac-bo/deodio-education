package com.deodio.elearning.persistence.model;

import java.util.Date;

public class RoleModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.role_name
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.role_description
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    private String roleDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.create_id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    private String createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.update_id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    private String updateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.create_time
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.update_time
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.id
     *
     * @return the value of t_role.id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.id
     *
     * @param id the value for t_role.id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.role_name
     *
     * @return the value of t_role.role_name
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.role_name
     *
     * @param roleName the value for t_role.role_name
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.role_description
     *
     * @return the value of t_role.role_description
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.role_description
     *
     * @param roleDescription the value for t_role.role_description
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.create_id
     *
     * @return the value of t_role.create_id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.create_id
     *
     * @param createId the value for t_role.create_id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.update_id
     *
     * @return the value of t_role.update_id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.update_id
     *
     * @param updateId the value for t_role.update_id
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.create_time
     *
     * @return the value of t_role.create_time
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.create_time
     *
     * @param createTime the value for t_role.create_time
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.update_time
     *
     * @return the value of t_role.update_time
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.update_time
     *
     * @param updateTime the value for t_role.update_time
     *
     * @mbggenerated Wed Feb 17 13:57:06 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}