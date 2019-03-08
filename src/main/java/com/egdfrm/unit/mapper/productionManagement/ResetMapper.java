package com.egdfrm.unit.mapper.productionManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ResetMapper {
	
	/**
	 *  验证报检单
	 */
	List<String> verificationInspect(@Param("inspectNum") String inspectNum);

	/**
	 *  重置报检单
	 */
	void resetInspect(@Param("inspectNum") String inspectNum);
	
	
	/**
	 *  验证入库单
	 */
	List<String> verificationStock(@Param("stockNum") String stockNum);

	/**
	 *  重置入库单
	 */
	void resetStock(@Param("stockNum") String stockNum);
	
}
