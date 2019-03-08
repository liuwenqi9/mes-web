package com.egdfrm.unit.mapper.reportManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.model.stock.QACheckReportExcel;
/**
 * QA检验报告Mapper
 * @author hgb 
 */
public interface QACheckReportMapper {
	
	/**
     * 查询数据总数
     * @param finished 查询条件
     * @return
     */
    long getCount(@Param("model") QACheckReportExcel qACheckReportExcel);

    /**
     * QA检验报告 分页查询
     * @param pagination 分页条件
     * @param finished 查询条件
     * @return 分页数据
     */
    List<QACheckReportExcel> findPage(@Param("page")Pagination pagination, @Param("model")QACheckReportExcel qACheckReportExcel);
   
    /**
     * QA检验报告 excel 
     */
    List<QACheckReportExcel> exportExcel(@Param("model")QACheckReportExcel qACheckReportExcel);
    
}
