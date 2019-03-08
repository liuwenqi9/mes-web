package com.egdfrm.unit.mapper.productionManagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.WeighMaintenanceExcel; 

public interface WeighMaintenanceMapper {
	/**
     * 查询数据总数
     * @param map
     * @return
     */
    long getCount(@Param("map") Map<String, Object> map);
	
    /**
     * 查询数据列表
     * @param map
     * @return
     */
	List<WeighMaintenanceExcel> getWeighMainList(@Param("page")Pagination pagination,@Param("map") Map<String, Object> map);

	
	/**
     * 查询数据总数
     * @param map 
     * @return
     */
    long getWipEntityNameCount(@Param("map") Map<String, Object> map);
	
    /** 
     * 查询数据列表
     * @param map
     * @return
     */
	List<Map<String, Object>> getWipEntityNameList(@Param("page")Pagination pagination,@Param("map") Map<String, Object> map);
	
	/**  
	 * 确认
     */
	List<Map<String, Object>> getObjectWipEntityName(@Param("map") Map<String, Object> map); 
	
	/**
     * 查询数据列表 
     * @return
     */
	List<String> getListByWipEntityId(@Param("wip_entity_id") String wip_entity_id);
	 
	void update(@Param("map") Map<String, Object> map);
	
	void add(@Param("map") Map<String, Object> map);
	
	List<WeighMaintenanceExcel> exportExcel(@Param("map")Map<String, Object> map);
	
}
