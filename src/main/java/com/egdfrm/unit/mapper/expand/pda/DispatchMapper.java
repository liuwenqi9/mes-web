package com.egdfrm.unit.mapper.expand.pda;

import java.util.Map;


/**
 * @author sjf
 * @date 2016年12月19日 
 * PDA发运确认Mapper
 */
public interface DispatchMapper {

	/**
	 * @author sjf
	 * @date 2016年12月23日 
	 * @param paramMap
	 * PDA发运确认-提交-循环插入
	 */
	public void callInsertTransactionTemp(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月23日 
	 * @param paramMap
	 * PDA发运确认-提交-插入temp表
	 */
	public void callProcessMesTransaction(Map<String, Object> paramMap);
	/**
	 * @author sjf
	 * @date 2016年12月23日 
	 * @param paramMap
	 * PDA发运确认-提交-ESB
	 */
	public void callBarcodesAutoShip(Map<String, Object> paramMap);
	
}