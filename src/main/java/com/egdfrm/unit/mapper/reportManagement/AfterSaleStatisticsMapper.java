package com.egdfrm.unit.mapper.reportManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.model.stock.AfterSaleStatisticsExcel;
/**
 * 售后服务机品质分析报表Mapper
 * @author hgb 
 */
public interface AfterSaleStatisticsMapper {
	
	/**
     * 查询数据总数
     * @param finished 查询条件
     * @return
     */
    long getCount(@Param("model") AfterSaleStatisticsExcel afterSaleStatisticsExcel);

    /**
     * 售后服务机品质分析报表分页查询
     * @param pagination 分页条件
     * @param finished 查询条件
     * @return 分页数据
     */
    List<AfterSaleStatisticsExcel> findPage(@Param("page")Pagination pagination, @Param("model")AfterSaleStatisticsExcel afterSaleStatisticsExcel);
   
    /**
     * 售后服务机品质分析报表 excel 
     */
    List<AfterSaleStatisticsExcel> exportExcel(@Param("model")AfterSaleStatisticsExcel afterSaleStatisticsExcel);
    
}
