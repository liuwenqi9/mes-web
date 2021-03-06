package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MtlSystemItemsB;
import com.egdfrm.unit.model.standard.MtlSystemItemsBCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MtlSystemItemsBMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_SYSTEM_ITEMS_B
     *
     * @mbggenerated Tue Dec 27 12:02:01 CST 2016
     */
    int countByExample(MtlSystemItemsBCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_SYSTEM_ITEMS_B
     *
     * @mbggenerated Tue Dec 27 12:02:01 CST 2016
     */
    int deleteByExample(MtlSystemItemsBCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_SYSTEM_ITEMS_B
     *
     * @mbggenerated Tue Dec 27 12:02:01 CST 2016
     */
    int insert(MtlSystemItemsB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_SYSTEM_ITEMS_B
     *
     * @mbggenerated Tue Dec 27 12:02:01 CST 2016
     */
    int insertSelective(MtlSystemItemsB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_SYSTEM_ITEMS_B
     *
     * @mbggenerated Tue Dec 27 12:02:01 CST 2016
     */
    List<MtlSystemItemsB> selectByExample(MtlSystemItemsBCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_SYSTEM_ITEMS_B
     *
     * @mbggenerated Tue Dec 27 12:02:01 CST 2016
     */
    int updateByExampleSelective(@Param("record") MtlSystemItemsB record, @Param("example") MtlSystemItemsBCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTL_SYSTEM_ITEMS_B
     *
     * @mbggenerated Tue Dec 27 12:02:01 CST 2016
     */
    int updateByExample(@Param("record") MtlSystemItemsB record, @Param("example") MtlSystemItemsBCriteria example);
}