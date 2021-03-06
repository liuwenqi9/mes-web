package com.egdfrm.unit.mapper.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.StockSendExcel;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 待发货Mapper
 * Created by tyq on 17/1/18.
 */
public interface ToDeliveryMapper {

    /**
     * 查询数据总数
     * @param orgId 组织ID
     * @return 总数
     */
    long getCount(@Param("orgId") int orgId);

    /**
     * 待发货分页查询
     * @param pagination 分页条件
     * @return 数据集
     */
    List<Map<String,Object>> findPage(@Param("page")Pagination pagination, @Param("orgId")int orgId);


     /**
      * 
      * @param orgId
      * @return   
      * @author	hgb
      * @date 2017-5-24
      */
    long getCount1(@Param("orgId") int orgId);
 
    /**
     * 
     * @param orgId
     * @return   
     * @author	hgb
     * @date 2017-5-24
     */ 
    List<Map<String,Object>> findPage1(@Param("page")Pagination pagination, @Param("orgId")int orgId);

    
    Map<String,Object> sum_findPage1();
    /**
     * 待发货分页查询
     * @param pagination 分页条件
     * @return 数据集
     */
    List<StockSendExcel> findPageExcel( @Param("orgId")int orgId);

}
