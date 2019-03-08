package com.egdfrm.unit.mapper.reportManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.model.stock.StockInStatisticsExcel;

public interface StockInStatisticsMapper {
	
	/**
     * 查询数据总数
     * @param finished 查询条件
     * @return
     */
    long getCount(@Param("model") StockInStatisticsExcel stockInStatisticsExcel);

    /**
     * 入库统计报表 分页查询
     * @param pagination 分页条件
     * @param finished 查询条件
     * @return 分页数据
     */
    List<StockInStatisticsExcel> findPage(@Param("page")Pagination pagination, @Param("model")StockInStatisticsExcel stockInStatisticsExcel);
   
    /**
     * 入库统计报表 excel 
     */
    List<StockInStatisticsExcel> exportExcel(@Param("model")StockInStatisticsExcel stockInStatisticsExcel);
    
}
