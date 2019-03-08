package com.egdfrm.unit.mapper.standard;
 
import java.util.List;
import java.util.Map; 

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;

/**
 * 
 * @author hgb
 * @date 2017-1-3
 */
public interface InspectionMapper {


	
	 /**
	  * 检验 (通过或不通过)
	  * @param params   
	  * @author	hgb
	  * @date 2017-1-3
	  */
	public void callInspection(Map<String, Object> params);
	
	
	 /**
	  * 报检
	  * @param params   
	  * @author	hgb
	  * @date 2017-1-4
	  */
	public void callInspections(Map<String, Object> params);
	
	
	/**
	 * 生成报检单
	 * @param params   
	 * @author	hgb
	 * @date 2017-1-4
	 */
	public void callGenerateInspection(Map<String, Object> params);
	
	
	/**
	 * 根据id获取报检状态
	 * @param id
	 * @return   null&报检单号
	 * @author	hgb
	 * @date 2017-1-5
	 */ 
	public List<String> getCheckStatusById(String id);
	
	/**
	 * 生成入库单号
	 * @param params invNumberOut  
	 * @author	hgb
	 * @date 2017-1-9
	 */
	public void callGenerateInvNumber(Map<String, Object> params);
	
	
	/**
	 * update 入库单
	 * @param params  ( userId,invNumber,packingBarcodeId)
	 * @author	hgb
	 * @date 2017-1-9
	 */
	public void callUpdateInvNumber(Map<String, Object> params);
	
	
	/**
	 * 获取本次入库单据
	 * @param invNumber 
	 * @return   
	 * @author	hgb
	 * @date 2017-1-9
	 */
	public List<Map<String, Object>> getInvNumberList(String invNumber);
	
	/**
	 * 验证是否已入库
	 * @param packingBarcodeId 
	 * @return   
	 * @author	hgb
	 * @date 2017-1-10
	 */
	public List<String> isInvNumber(String packingBarcodeId);
	
	
	/**
	 * 报检的分页list 
	 * @return   
	 * @author	hgb
	 * @date 2017-2-5
	 */
	List<Map<String,Object>> getInspectionList(@Param("page")Pagination pagination,@Param("map")Map<String, Object> map);
 
	/**
	 * 报检验数据的总记录数
	 * @return   
	 * @author	hgb
	 * @date 2017-2-5
	 */
	long getInspectionCount( @Param("map") Map<String, Object> map);
	
	
	/**
	 * 检验的分页list 
	 * @return   
	 * @author	hgb
	 * @date 2017-2-5
	 */
	List<Map<String,Object>> getInspectionsList(@Param("page")Pagination pagination,@Param("map")Map<String, Object> map);
 
	/**
	 * 检验数据的总记录数
	 * @return   
	 * @author	hgb
	 * @date 2017-2-5
	 */
	long getInspectionsCount( @Param("map") Map<String, Object> map); 
	
	
	
}