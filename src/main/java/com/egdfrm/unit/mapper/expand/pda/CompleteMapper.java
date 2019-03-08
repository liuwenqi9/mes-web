package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.Map;

import com.egdfrm.unit.model.expand.pda.WoInStorage;

/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA工单
 */
public interface CompleteMapper {


	/**
	 * @author sjf
	 * @date 2016年12月16日 
	 * @param packBarcodeId
	 * PDA工单完工入库-获取建议货位
	 */
	public String getAdviseLocations(BigDecimal packBarcodeId);

	/**
	 * @author 兰继明
	 * @date 2017年01月15日 
	 * @param 
	 * PDA工单完工入库-较验货位
	 */
	public void callCheckLocation(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月20日 
	 * @param packBarcodeId
	 * PDA工单完工入库-扫描货位
	 */
	public WoInStorage completeScanLocations(BigDecimal packBarcodeId);

	/**
	 * @author sjf
	 * @date 2016年12月20日 
	 * @param paramMap
	 * PDA工单完工入库-循环提交
	 */
	public void callInvInsertTransactionTemp(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月20日 
	 * @param paramMap
	 * PDA工单完工入库-完工入库
	 */
	public void callWipCompleteTransaction(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年03月13日 
	 * @param paramMap
	 * PDA工单完工入库-检查包装箱是否可以入库
	 */
	public void callCheckCanInStock(Map<String, Object> paramMap);
	
	
}