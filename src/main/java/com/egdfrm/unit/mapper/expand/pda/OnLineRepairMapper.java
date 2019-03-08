package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.egdfrm.unit.model.expand.pda.OnLineRepairData;

/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA上线返修 
 */
public interface OnLineRepairMapper {

	/**
	 * 验证领料单
	 * @param paramMap
	 */
	public void callPdaCheckDoc(Map<String, Object> paramMap);

	/**
	 * (上线返修) 验证领料单和条码
	 * @param paramMap
	 */
	public void callPdaReprodCheck(Map<String, Object> paramMap);

	/**
	 * (上线返修退回) 验证回退领料单和条码
	 * @param paramMap
	 */
	public void reTurnCallPdaReprodCheck(Map<String, Object> paramMap);

	/**
	 * (上线返修回退) 提交
	 * @param paramMap
	 */
	public void reTurnCallPdaProcessReprod(Map<String, Object> paramMap);

	/**
	 * (上线返修回退) 提交 插入
	 * @param paramMap
	 */
	public void reTurnCallPdaReprodTransactionTemp(Map<String, Object> paramMap);

	/**
	 * (上线返修) 提交
	 * @param paramMap
	 */
	public void callPdaProcessReprod(Map<String, Object> paramMap);
	/**
	 * (上线返修) 提交
	 * @param paramMap
	 */
	public void callPdaReprodTransactionTemp(Map<String, Object> paramMap);


	/**
	 * @author sjf
	 * @date 2016年12月16日 
	 * PDA上线返修-扫描包装/产品条码
	 * @param packBarcodeId
	 */
	public List<OnLineRepairData> getRepairStyle(BigDecimal packBarcodeId);
	
	/**
	 * PDA上线返修-扫描包装/产品条码
	 * @param packBarcodeId
	 * @return   
	 * @author	hgb
	 * @date 2017-8-1
	 */
	public List<OnLineRepairData> getRepairStyle2(BigDecimal packBarcodeId);
	
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * @param
	 * @return
	 * PDA上线返修-提交-循环插入
	 */
	public void callReprodTransactionTemp(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * @param
	 * @return
	 * PDA上线返修-提交-更新状态
	 */
	public void callReprodProcessMes(Map<String, Object> paramMap);
	
	
}