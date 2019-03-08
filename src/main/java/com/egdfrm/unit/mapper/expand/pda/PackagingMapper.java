package com.egdfrm.unit.mapper.expand.pda;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA二次包装
 */
public interface PackagingMapper {


	/**
	 * @author sjf
	 * @date 2016年12月16日 
	 * 据包装条码Id获取包装剩余可包装数量
	 * @param string
	 */
	public String getNumByPackBarcodeId(String string);
	
	/**
	 * PDA二次包装-包装条码/产品条码，验证是否是上线返修，E ，上线返修，
	 * @param paramMap   
	 * @author	hgb
	 * @date 2017-7-13
	 */
	public void callCheckPackForReprod(Map<String, Object> paramMap);
	
	/**
	 * @author sjf
	 * @date 2016年12月16日 
	 * PDA二次包装-产品条码校验
	 * @param record
	 * @param dbreturn
	 */
	public void callProcessPackCheck(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月16日 
	 * PDA发运包装-产品条码校验
	 * @param record
	 * @param dbreturn
	 */
	public void callProcessShipCheck(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月19日 
	 * 包装
	 * @param packingBarcodeId
	 * @param wipBarcodeId
	 * @param num
	 * @param status
	 * @param string
	 * @param string2
	 */
	public void callProcessPack(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月19日 
	 * 处理mes ，insert tmp用于EBS操作
	 * @param processId
	 * @param packingBarcodeId
	 * @param string
	 * @param string2
	 */
	public void callPackInsertTransactionTemp(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月19日 
	 * ebs
	 * @param processId
	 * @param string
	 * @param string2
	 */
	public void callWipMoveTransaction(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月19日 
	 * @param paramMap
	 * PDA拆包-扫描-校验
	 */
	public void callCheckDeleteBarcode(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月19日 
	 * @param paramMap
	 * PDA拆包-提交
	 */
	public void callDeleteBarcode(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月23日 
	 * @param paramMap
	 * PDA发运包装-提交
	 */
	public void callProcessShipPack(Map<String, Object> paramMap);

	/**
	 * 获取大包装箱子
	 * @param parameter 大包装
	 * @return
	 */
	public List<Map<String,Object>> getBigPackByBarcode(@Param("parameter") String parameter);

	/**
	 * 获取包装箱子
	 */
	public List<Map<String,Object>> getPackByBarcode(@Param("parameter") String parameter);
	/**
	 * 获取小包装箱子
	 * @param parameter 大包装
	 * @return
	 */
	public List<Map<String,Object>> getSmallPackByBarcode(@Param("parameter") String parameter);
}