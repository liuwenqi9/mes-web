package com.egdfrm.core.model.standard;

import java.io.Serializable;
import java.util.Date;

public class TtUserRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_ROLE.LOGIN_NAME
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_ROLE.ROLE_CODE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    private String roleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_ROLE.CREATE_BY
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TT_USER_ROLE.CREATE_DATE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_USER_ROLE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_ROLE.LOGIN_NAME
     *
     * @return the value of TT_USER_ROLE.LOGIN_NAME
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_ROLE.LOGIN_NAME
     *
     * @param loginName the value for TT_USER_ROLE.LOGIN_NAME
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_ROLE.ROLE_CODE
     *
     * @return the value of TT_USER_ROLE.ROLE_CODE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_ROLE.ROLE_CODE
     *
     * @param roleCode the value for TT_USER_ROLE.ROLE_CODE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_ROLE.CREATE_BY
     *
     * @return the value of TT_USER_ROLE.CREATE_BY
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_ROLE.CREATE_BY
     *
     * @param createBy the value for TT_USER_ROLE.CREATE_BY
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TT_USER_ROLE.CREATE_DATE
     *
     * @return the value of TT_USER_ROLE.CREATE_DATE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TT_USER_ROLE.CREATE_DATE
     *
     * @param createDate the value for TT_USER_ROLE.CREATE_DATE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ROLE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TtUserRole other = (TtUserRole) that;
        return (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_USER_ROLE
     *
     * @mbggenerated Mon Mar 28 15:47:43 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
}