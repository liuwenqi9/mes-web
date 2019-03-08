package com.egdfrm.core.mapper.standard;

import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.model.standard.TtApplicationUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtApplicationUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int countByExample(TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int deleteByExample(TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int deleteByPrimaryKey(String loginName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int insert(TtApplicationUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int insertSelective(TtApplicationUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    List<TtApplicationUser> selectByExample(TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    TtApplicationUser selectByPrimaryKey(String loginName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int updateByExampleSelective(@Param("record") TtApplicationUser record, @Param("example") TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int updateByExample(@Param("record") TtApplicationUser record, @Param("example") TtApplicationUserCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int updateByPrimaryKeySelective(TtApplicationUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_APPLICATION_USER
     *
     * @mbggenerated Wed Dec 28 12:11:08 CST 2016
     */
    int updateByPrimaryKey(TtApplicationUser record);
}