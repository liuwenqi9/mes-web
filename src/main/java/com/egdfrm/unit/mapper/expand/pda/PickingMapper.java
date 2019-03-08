package com.egdfrm.unit.mapper.expand.pda;

import java.util.Map;

import com.egdfrm.unit.model.expand.pda.SaleOutStorage;


/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA挑库Mapper
 */
/**
 * @author sjf
 * @date 2016年12月22日 
 */
public interface PickingMapper {

	
	/**
	 * @author 兰继明
	 * @date 2017年02月22日 
	 * @param paramMap
	 * @description //检查搬运的货物是否是属于此搬运单
	 * @return
	 */
	public void callCheckIsBarcodeBelongMoveOrder(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月22日 
	 * @param paramMap
	 * @return
	 */
	public SaleOutStorage getNum(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月22日 
	 * @param paramMap
	 * 循环挑库
	 */
	public void callTranInsertTransactionTemp(Map<String, Object> paramMap);

	/**
	 * @author sjf
	 * @date 2016年12月22日 
	 * @param paramMap
	 * 挑库暂存
	 */
	public void callProcessMesTransaction(Map<String, Object> paramMap);
	

	/**
	 * @author sjf
	 * @date 2016年12月22日 
	 * @param paramMap
	 * 挑库ESB操作
	 */
	public void callMesBarcodesMoveOrder(Map<String, Object> paramMap);

}