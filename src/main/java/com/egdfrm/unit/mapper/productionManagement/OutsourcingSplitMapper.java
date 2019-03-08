package com.egdfrm.unit.mapper.productionManagement;
import java.util.List;
import java.util.Map; 

import org.apache.ibatis.annotations.Param;

public interface OutsourcingSplitMapper {
	
	List<Map<String, Object>> getOutsourcingByBarcode(@Param("barcodeText") String barcodeText);

	Map<String, String> getOutsourcingSplitProduct(@Param("map")Map<String, Object> map);
	
	void callOutsourcedUnpack(@Param("map") Map<String, Object> map);
	
	void updatePackQuantity(@Param("map") Map<String, Object> map);
	
}