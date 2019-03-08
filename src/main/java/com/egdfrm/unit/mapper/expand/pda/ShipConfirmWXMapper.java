package com.egdfrm.unit.mapper.expand.pda;

import java.util.Map;

import com.egdfrm.unit.model.expand.pda.SaleOutStorage;


/**
 * PDA外销发货Mapper
 * @author 兰继明
 * @date 2017年04月28日 
 */

public interface ShipConfirmWXMapper {

	
	/**
	 * @author 兰继明
	 * @date 2017年04月28日 
	 * @param paramMap
	 * @description PDA//外销发货-校验出货通知单
	 * @return
	 */
	public void callcheckOutSn(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年04月28日 
	 * @param paramMap
	 * @description PDA//检查出货通知单和包装箱，及出货通知单和包装箱的对应关系是否正确
	 * @return
	 */
	public void callCheckPackNo(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年04月28日 
	 * @param paramMap
	 * @description PDA//外销发货-取得包装物料信息 
	 * @return
	 */
	public Map<String, Object> getPackInfo(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年04月28日 
	 * @param paramMap
	 * @description PDA//取得包装ID
	 * @return
	 */
	public Map<String, String> getPackID(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年04月28日 
	 * @param paramMap
	 * @description PDA//外销发货-提交-插入临时表
	 * @return
	 */
	public void callCommitInsertTempTable(Map<String, Object> paramMap);
	/**
	 * @author 兰继明
	 * @date 2017年04月28日 
	 * @param paramMap
	 * @description PDA//外销发货-提交-处理临时表
	 * @return
	 */
	public void callCommitProcesTempTable(Map<String, Object> paramMap);
	
}