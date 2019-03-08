package com.egdfrm.unit.mapper.barcodeManagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;  
import com.egdfrm.unit.model.barcodeManagement.InPro;

 

public interface InShipProMapper {

	/**
	 *  总条数
	 */
	Long getModalTableCount(@Param("model") InPro inPro);
	
	/**
	 * 分页列表
	 */
    List<InPro> getModalTableList(@Param("page")Pagination pagination,@Param("model") InPro inPro );

	 /**
	 *  添加
	 */
	int insertToShip(@Param("model")InPro inPro,@Param("uid")String uid);

	/**
	 *  update
	 */
	int updateToShip(@Param("model")InPro inPro,@Param("uid")String uid);
    
    List<String> isNull(@Param("model")InPro inPro);

	List<Map<String,Object>> isStatus(@Param("model")InPro inPro);
}
