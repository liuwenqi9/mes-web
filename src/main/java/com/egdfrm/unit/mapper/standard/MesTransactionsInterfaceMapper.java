package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesTransactionsInterface;
import com.egdfrm.unit.model.standard.MesTransactionsInterfaceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesTransactionsInterfaceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_TRANSACTIONS_INTERFACE
     *
     * @mbggenerated Thu Jan 12 13:59:33 CST 2017
     */
    int countByExample(MesTransactionsInterfaceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_TRANSACTIONS_INTERFACE
     *
     * @mbggenerated Thu Jan 12 13:59:33 CST 2017
     */
    int deleteByExample(MesTransactionsInterfaceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_TRANSACTIONS_INTERFACE
     *
     * @mbggenerated Thu Jan 12 13:59:33 CST 2017
     */
    int insert(MesTransactionsInterface record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_TRANSACTIONS_INTERFACE
     *
     * @mbggenerated Thu Jan 12 13:59:33 CST 2017
     */
    int insertSelective(MesTransactionsInterface record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_TRANSACTIONS_INTERFACE
     *
     * @mbggenerated Thu Jan 12 13:59:33 CST 2017
     */
    List<MesTransactionsInterface> selectByExample(MesTransactionsInterfaceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_TRANSACTIONS_INTERFACE
     *
     * @mbggenerated Thu Jan 12 13:59:33 CST 2017
     */
    int updateByExampleSelective(@Param("record") MesTransactionsInterface record, @Param("example") MesTransactionsInterfaceCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_TRANSACTIONS_INTERFACE
     *
     * @mbggenerated Thu Jan 12 13:59:33 CST 2017
     */
    int updateByExample(@Param("record") MesTransactionsInterface record, @Param("example") MesTransactionsInterfaceCriteria example);
}