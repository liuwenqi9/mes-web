package com.egdfrm.unit.mapper.reportManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.model.stock.CheckStatisticsExcel;
/**
 * 检验统计日报表 Mapper
 * @author hgb
 * @date 2017-6-12
 */
public interface CheckStatisticsMapper {
	
	/**
     * 查询数据总数
     * @param finished 查询条件
     * @return
     */
    long getCount(@Param("model") CheckStatisticsExcel checkStatisticsExcel);

    /**
     * 检验统计报表 分页查询
     * @param pagination 分页条件
     * @param finished 查询条件
     * @return 分页数据
     */
    List<CheckStatisticsExcel> findPage(@Param("page")Pagination pagination, @Param("model")CheckStatisticsExcel checkStatisticsExcel);
   
    /**
     * 检验统计报表 excel 
     */
    List<CheckStatisticsExcel> exportExcel(@Param("model")CheckStatisticsExcel checkStatisticsExcel);
    
}
