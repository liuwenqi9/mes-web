package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.CuxOrderHeade;
import com.egdfrm.unit.model.standard.CuxOrderHeadeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CuxOrderHeadeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_HEADE
     *
     * @mbggenerated Tue May 02 17:13:36 CST 2017
     */
    int countByExample(CuxOrderHeadeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_HEADE
     *
     * @mbggenerated Tue May 02 17:13:36 CST 2017
     */
    int deleteByExample(CuxOrderHeadeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_HEADE
     *
     * @mbggenerated Tue May 02 17:13:36 CST 2017
     */
    int insert(CuxOrderHeade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_HEADE
     *
     * @mbggenerated Tue May 02 17:13:36 CST 2017
     */
    int insertSelective(CuxOrderHeade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_HEADE
     *
     * @mbggenerated Tue May 02 17:13:36 CST 2017
     */
    List<CuxOrderHeade> selectByExample(CuxOrderHeadeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_HEADE
     *
     * @mbggenerated Tue May 02 17:13:36 CST 2017
     */
    int updateByExampleSelective(@Param("record") CuxOrderHeade record, @Param("example") CuxOrderHeadeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_HEADE
     *
     * @mbggenerated Tue May 02 17:13:36 CST 2017
     */
    int updateByExample(@Param("record") CuxOrderHeade record, @Param("example") CuxOrderHeadeCriteria example);
}