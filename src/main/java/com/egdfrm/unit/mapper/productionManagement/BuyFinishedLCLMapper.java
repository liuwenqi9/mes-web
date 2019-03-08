package com.egdfrm.unit.mapper.productionManagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
 
 
/**
 * @author sjf
 * @date 2017年2月15日 
 * 采购成品拼箱
 */
public interface BuyFinishedLCLMapper {

	/**
	 * @author sjf
	 * @date 2017年2月15日 
	 * @param packageBarcode
	 * @param wipBarcode
	 * @return
	 * 获取包装箱信息
	 *
	 */
	List<Map<String, Object>> getWipBarcode(@Param("packageBarcode")String packageBarcode,
			@Param("wipBarcode")String wipBarcode);

	/**
	 * @author sjf
	 * @date 2017年2月16日  
	 * 提交
	 */  
	void callCreatePoPack(@Param("map") Map<String, Object> paramMap);


	/**
	 * 根据包装箱号查询可包装数量
	 * @param packageBarcode 包装箱号
	 * @return 可包装数量
	 */
	String queryPackageByCount(@Param("packageBarcode") String packageBarcode);

}
