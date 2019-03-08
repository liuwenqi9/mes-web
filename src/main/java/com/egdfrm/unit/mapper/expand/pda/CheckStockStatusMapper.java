package com.egdfrm.unit.mapper.expand.pda;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CheckStockStatusMapper {
	
	/*
	 * 入库单号
	 */
	List<Map<String, Object>> getPackBarcodeByStockNum(@Param("stockNum")String stockNum);

	List<String> verificationStockNum(@Param("stockNum")String stockNum);
	
}
