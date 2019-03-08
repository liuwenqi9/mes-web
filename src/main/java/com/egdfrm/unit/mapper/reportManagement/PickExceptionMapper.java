package com.egdfrm.unit.mapper.reportManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.PickException;

public interface PickExceptionMapper {
	
	
	 /**
     * 挑库异常  总数据量
     * @param saot 查询条件
     * @return
     */
    long findPageCount(@Param("pick") PickException pickException);

    /**
     * 挑库异常  
     * @param pagination 分页数据
     * @param saot 查询条件
     * @return
     */
    List<PickException> findPage(@Param("page") Pagination pagination,@Param("pick") PickException pickException);

    /**
     *  导出Excel 
     * @author	hgb
     * @date 2017-5-18
     */
    List<PickException> export(@Param("pick") PickException pickException);
}
