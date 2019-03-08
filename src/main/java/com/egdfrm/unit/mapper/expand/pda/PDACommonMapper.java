package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.Map;


/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA共通Mapper
 */
public interface PDACommonMapper {



	/**
	 * @author sjf
	 * @date 2016年12月20日 
	 * @param paramMap
	 * 写入MES_TRANSACTION
	 */
	public void callProcessBarcode(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月22日 
	 * @param packBarcodeId
	 * @return
	 */
	public BigDecimal getNumByPackBarcodeId(BigDecimal packBarcodeId);
	/**
	 * @author sjf
	 * @date 2016年12月20日 
	 * @param paramMap
	 * 外销产品校验
	 */
	public void callCheckWxBarcode(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * @param paramMap
	 * check状态
	 */
	public void callReprodCheck(Map<String, Object> paramMap);
	
	
}