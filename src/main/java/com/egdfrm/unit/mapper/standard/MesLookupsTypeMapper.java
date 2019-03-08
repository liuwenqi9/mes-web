package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesLookupsType;
import com.egdfrm.unit.model.standard.MesLookupsTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesLookupsTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_LOOKUPS_TYPE
     *
     * @mbggenerated Mon Dec 05 14:32:50 CST 2016
     */
    int countByExample(MesLookupsTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_LOOKUPS_TYPE
     *
     * @mbggenerated Mon Dec 05 14:32:50 CST 2016
     */
    int deleteByExample(MesLookupsTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_LOOKUPS_TYPE
     *
     * @mbggenerated Mon Dec 05 14:32:50 CST 2016
     */
    int insert(MesLookupsType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_LOOKUPS_TYPE
     *
     * @mbggenerated Mon Dec 05 14:32:50 CST 2016
     */
    int insertSelective(MesLookupsType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_LOOKUPS_TYPE
     *
     * @mbggenerated Mon Dec 05 14:32:50 CST 2016
     */
    List<MesLookupsType> selectByExample(MesLookupsTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_LOOKUPS_TYPE
     *
     * @mbggenerated Mon Dec 05 14:32:50 CST 2016
     */
    int updateByExampleSelective(@Param("record") MesLookupsType record, @Param("example") MesLookupsTypeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MES_LOOKUPS_TYPE
     *
     * @mbggenerated Mon Dec 05 14:32:50 CST 2016
     */
    int updateByExample(@Param("record") MesLookupsType record, @Param("example") MesLookupsTypeCriteria example);
}