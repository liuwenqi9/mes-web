package com.egdfrm.unit.mapper.expand.pda;

import java.util.Map;

import com.egdfrm.unit.model.expand.pda.SaleOutStorage;


/**
 * PDA借机Mapper
 * @author 兰继明
 * @date 2017年03月14日 
 */

public interface BorrowProductMapper {

	
	/**
	 * @author 兰继明
	 * @date 2017年03月14日 
	 * @param paramMap
	 * @description PDA//借机--检查借机单号
	 * @return
	 */
	public void callCheckBorrowProductBill(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年03月14日 
	 * @param paramMap
	 * @description PDA//借机--检查产品条码 
	 * @return
	 */
	public void callCheckProduct(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年03月14日 
	 * @param paramMap
	 * @description PDA//借机--提交--插入临时表
	 * @return
	 */
	public void callCommitInsertTempTable(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年03月14日 
	 * @param paramMap
	 * @description PDA//借机--提交--处理临时表，插入正式表
	 * @return
	 */
	public void callCommitProcesTempTable(Map<String, Object> paramMap);

	

}