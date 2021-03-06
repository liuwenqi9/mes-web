package com.egdfrm.core.mapper.standard;

import com.egdfrm.core.model.standard.TtJobRole;
import com.egdfrm.core.model.standard.TtJobRoleCriteria;
import com.egdfrm.core.model.standard.TtJobRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtJobRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int countByExample(TtJobRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByExample(TtJobRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int deleteByPrimaryKey(TtJobRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insert(TtJobRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int insertSelective(TtJobRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    List<TtJobRole> selectByExample(TtJobRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    TtJobRole selectByPrimaryKey(TtJobRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExampleSelective(@Param("record") TtJobRole record, @Param("example") TtJobRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByExample(@Param("record") TtJobRole record, @Param("example") TtJobRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKeySelective(TtJobRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_JOB_ROLE
     *
     * @mbggenerated Wed Jul 16 09:44:39 CST 2014
     */
    int updateByPrimaryKey(TtJobRole record);
}