package com.egdfrm.unit.mapper.expand;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * @author sjf
 * @date 2016年12月19日 
 * 共通MAPPER
 */
public interface CommonMapper {

	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @return
	 * 获取组织ID
	 */
	public BigDecimal getOrgId(String code);
	/**
	 * @author sjf
	 * @date 2016年12月20日 
	 * @param packBarcodeId
	 * 共通方法-根据序列名获取下一序列
	 */
	public BigDecimal getSeqByName(String seq); 

	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @return
	 * 获取数据字典列表
	 */
	public List<Map<String, Object>> getLookUpByCode(String code); 
	
}