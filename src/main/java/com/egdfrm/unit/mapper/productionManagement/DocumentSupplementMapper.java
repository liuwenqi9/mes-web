package com.egdfrm.unit.mapper.productionManagement;

import java.util.List;
import java.util.Map;
 

/**
 * @author sjf
 * @date 2017年2月8日 
 */
public interface DocumentSupplementMapper {

 
	/**
	 * @author sjf
	 * @date 2017年2月8日 
	 * @param inspectionNumber
	 * @return
	 * 根据报检单号获取报检单
	 */
	List<Map<String, Object>> getInspectionByInspectionNumber(String inspectionNumber);
}
