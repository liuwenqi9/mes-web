package com.egdfrm.unit.mapper.sale;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.SaleShipExcel;

public interface SaleShipMapper {
	

    /**
     * 数据总数查询 
     */
    long getSaleShipCount(@Param("map") Map<String, Object> map);

    /**
     *  分页数据
     */
    List<Map<String,Object>> getSaleShipList(@Param("page") Pagination page, @Param("map") Map<String, Object> map);

    
    List<SaleShipExcel> exportExcel(@Param("map") Map<String, Object> map) ;
}
