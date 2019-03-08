package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.Map;

import com.egdfrm.unit.model.expand.pda.StorageTransfer;

/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA库存
 */
public interface InventoryMapper {


	/**
	 * @author 兰继明
	 * @date 2017年03月10日 
	 *   <!-- 验证产品是否可以转移货位（放在包装箱内的不行，） -->
	 */
	public void callCheckIsProductLocationCanTransfer(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年03月10日 
	 *   <!-- 根据包产品物料ID获取关联的产品物料编码和描述 -->
	 */
	public StorageTransfer getProductItemAndDesc(Map<String, Object> paramMap);
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
	
	
}