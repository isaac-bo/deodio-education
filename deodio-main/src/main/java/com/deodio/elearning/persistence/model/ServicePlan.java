package com.deodio.elearning.persistence.model;

import java.util.Date;

public class ServicePlan {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.plan_type
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private Integer planType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.plan_desc
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private String planDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.create_id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private String createId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.create_time
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.update_id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private String updateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.update_time
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.plan_order
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private Short planOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_service_plan.use_flag
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    private Short useFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.id
     *
     * @return the value of sys_service_plan.id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.id
     *
     * @param id the value for sys_service_plan.id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.plan_type
     *
     * @return the value of sys_service_plan.plan_type
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public Integer getPlanType() {
        return planType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.plan_type
     *
     * @param planType the value for sys_service_plan.plan_type
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.plan_desc
     *
     * @return the value of sys_service_plan.plan_desc
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public String getPlanDesc() {
        return planDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.plan_desc
     *
     * @param planDesc the value for sys_service_plan.plan_desc
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc == null ? null : planDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.create_id
     *
     * @return the value of sys_service_plan.create_id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.create_id
     *
     * @param createId the value for sys_service_plan.create_id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.create_time
     *
     * @return the value of sys_service_plan.create_time
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.create_time
     *
     * @param createTime the value for sys_service_plan.create_time
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.update_id
     *
     * @return the value of sys_service_plan.update_id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.update_id
     *
     * @param updateId the value for sys_service_plan.update_id
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.update_time
     *
     * @return the value of sys_service_plan.update_time
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.update_time
     *
     * @param updateTime the value for sys_service_plan.update_time
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.plan_order
     *
     * @return the value of sys_service_plan.plan_order
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public Short getPlanOrder() {
        return planOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.plan_order
     *
     * @param planOrder the value for sys_service_plan.plan_order
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setPlanOrder(Short planOrder) {
        this.planOrder = planOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_service_plan.use_flag
     *
     * @return the value of sys_service_plan.use_flag
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public Short getUseFlag() {
        return useFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_service_plan.use_flag
     *
     * @param useFlag the value for sys_service_plan.use_flag
     *
     * @mbggenerated Tue Nov 22 15:49:56 CST 2016
     */
    public void setUseFlag(Short useFlag) {
        this.useFlag = useFlag;
    }
}