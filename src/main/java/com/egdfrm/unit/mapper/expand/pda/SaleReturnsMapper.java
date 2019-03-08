package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.egdfrm.unit.model.expand.pda.SaleReturn;
import com.egdfrm.unit.model.expand.pda.StorageTransfer;

/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA库存
 */
public interface SaleReturnsMapper {

	/**
	 * 销售退货 取包装箱个数
	 */

	public List<SaleReturn> getSaleReturnStyle(BigDecimal packBarcodeId);

	/**
	 *  销售 退货确认
	 * @param paramMap
	 */
	public void callUpdateOrder(Map<String, Object> paramMap);

	/**
	 *  验证RMA订单号
	 * @param paramMap
	 */
	public void callReturnCheckRma(Map<String, Object> paramMap);


	/**
	 *  -提交前 验证数据
	 * @param paramMap
	 */
	public void callReturnCheck(Map<String, Object> paramMap);

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
	 * @author sjf
	 * @date 2016年12月27日 
	 * @param paramMap
	 * PDA销售退货-提交-循环插入
	 */
	public void callReturnTransactionTemp(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月27日 
	 * @param paramMap
	 * PDA销售退货-提交-更新状态
	 */
	public void callReturnProcessMes(Map<String, Object> paramMap);
	
	
}