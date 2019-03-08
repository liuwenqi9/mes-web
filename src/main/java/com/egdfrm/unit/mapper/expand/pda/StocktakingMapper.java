package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.Map;

import com.egdfrm.unit.model.expand.pda.StorageTransfer;

/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA库存
 */
public interface StocktakingMapper {


	/**
	 * @author lanjiming
	 * @date 2017年04月01日 
	 * 
	 * @param packBarcodeId
	 */
	public void callCheckBarcode(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月16日 
	 * 据包装条码Id获取物料编码和描述
	 * @param packBarcodeId
	 */
	public StorageTransfer getItemidByPackBarcodeId(BigDecimal packBarcodeId);

	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @param paramMap
	 * 写入MES临时接口表
	 */
	public void callTranInsertTransactionTemp(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @param paramMap
	 * 子库存/货位转移
	 */
	public void callMesBarcodesTransfer(Map<String, Object> paramMap);
	/**
	 * @author xoje
	 * @date 2016年12月29日 
	 * @param paramMap
	 * 盘点提交
	 */
	public void callStocktakingCommit(Map<String, Object> paramMap);
	
	
}