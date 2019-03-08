package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MtlTxnRequestHeaders;
import com.egdfrm.unit.model.standard.MtlTxnRequestHeadersCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MtlTxnRequestHeadersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_TXN_REQUEST_HEADERS
     *
     * @mbggenerated Thu Dec 22 16:20:30 CST 2016
     */
    int countByExample(MtlTxnRequestHeadersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_TXN_REQUEST_HEADERS
     *
     * @mbggenerated Thu Dec 22 16:20:30 CST 2016
     */
    int deleteByExample(MtlTxnRequestHeadersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_TXN_REQUEST_HEADERS
     *
     * @mbggenerated Thu Dec 22 16:20:30 CST 2016
     */
    int insert(MtlTxnRequestHeaders record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_TXN_REQUEST_HEADERS
     *
     * @mbggenerated Thu Dec 22 16:20:30 CST 2016
     */
    int insertSelective(MtlTxnRequestHeaders record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_TXN_REQUEST_HEADERS
     *
     * @mbggenerated Thu Dec 22 16:20:30 CST 2016
     */
    List<MtlTxnRequestHeaders> selectByExample(MtlTxnRequestHeadersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_TXN_REQUEST_HEADERS
     *
     * @mbggenerated Thu Dec 22 16:20:30 CST 2016
     */
    int updateByExampleSelective(@Param("record") MtlTxnRequestHeaders record, @Param("example") MtlTxnRequestHeadersCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_TXN_REQUEST_HEADERS
     *
     * @mbggenerated Thu Dec 22 16:20:30 CST 2016
     */
    int updateByExample(@Param("record") MtlTxnRequestHeaders record, @Param("example") MtlTxnRequestHeadersCriteria example);
}