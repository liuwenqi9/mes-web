package com.egdfrm.unit.mapper.expand.inventoryManagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.PDDetailExcel;
import com.egdfrm.unit.excelmodel.PDSummaryExcel;

public interface TakeStockMapper {
	
	/**
	 *  子库列表查询
	 */
	List<Map<String,Object>> getSubpoolList(@Param("page")Pagination pagination,@Param("map")Map<String, Object> map);

	/**
	 *  子库列表总条数
	 * @date 2017-1-16
	 */
	long getSubpoolCount(@Param("map") Map<String, Object> map);
	
	
	/**
	 * 货位列表查询
	 */
	List<Map<String,Object>> getLocationList(@Param("page")Pagination pagination,@Param("map")Map<String, Object> map);
	
	
	/**
	 *  货位列表总条数
	 * @date 2017-1-16
	 */
	long getLocationCount( @Param("map") Map<String, Object> map);
	
	/**
	 * 货位列表查询
	 */
	List<Map<String,Object>> getItemList(@Param("page")Pagination pagination,@Param("map")Map<String, Object> map);
	
	
	/**
	 *  货位列表总条数
	 * @date 2017-1-16
	 */
	long getItemCount( @Param("map") Map<String, Object> map);
	
	/**
	 *  生成盘点报表
	 * @date 2017-1-17
	 */
	void callGeneratePD(Map<String, Object> map);
	
	
	/**
	 * 盘点记录列表查询
	 */
	List<Map<String,Object>> getpdHeadersList(@Param("page")Pagination pagination,@Param("map")Map<String, Object> map);
	
	
	/**
	 *  盘点记录列表总条数
	 * @date 2017-1-16
	 */
	long getpdHeadersCount( @Param("map") Map<String, Object> map);
	
	
	/**
	 * 盘点汇总 数据列表
	 */
	List<Map<String,Object>> getIterationSummaryList(@Param("map")Map<String, Object> map);
	
	/**
	 * 盘点汇总 Excel
	 */
	List<PDSummaryExcel> getIterationSummaryExcel(@Param("map")Map<String, Object> map);
	
	
	/**
	 *  导出盘点明细excel
	 * @date 2017-3-8
	 */
	List<PDDetailExcel> getDetailListExcel(@Param("map")Map<String, Object> map);
	
	/**
	 * 盘点 明细 数据总个数 
	 * @date 2017-3-8
	 */
	long getDetailCount(@Param("map")Map<String, Object> map);
	
	/**
	 * 盘点 明细 数据列表
	 * @date 2017-3-8
	 */
	List<Map<String,Object>> getDetailList(@Param("page")Pagination pagination,@Param("map")Map<String, Object> map);
	
	
	
	void callpdUpdate(Map<String, Object> map);
	
}
