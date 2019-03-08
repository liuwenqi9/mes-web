package com.egdfrm.unit.mapper.sale;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.WXOutExcel;

public interface WXOutMapper {
	

    /**
     * 数据总数查询 
     */
    long getWXOutCount(@Param("map") Map<String, Object> map);

    /**
     *  分页数据
     */
    List<Map<String,Object>> getWXOutList(@Param("page") Pagination page, @Param("map") Map<String, Object> map);

    
    List<WXOutExcel> exportExcel(@Param("map") Map<String, Object> map) ;
}
