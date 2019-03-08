package com.egdfrm.unit.service.reportManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.reportManagement.AfterSaleStatisticsMapper;
import com.egdfrm.unit.model.stock.AfterSaleStatisticsExcel;

/**
 * 售后服务机品质分析报表-service
 * @author hgb 
 */
@Service
public class AfterSaleStatisticsService {

	@Autowired
	private AfterSaleStatisticsMapper afterSaleStatisticsMapper;
	
	public Pagination listByPage(Pagination pagination,AfterSaleStatisticsExcel afterSaleStatisticsExcel) {
		
		long count = afterSaleStatisticsMapper.getCount(afterSaleStatisticsExcel);
		List<AfterSaleStatisticsExcel> list = afterSaleStatisticsMapper.findPage(pagination, afterSaleStatisticsExcel);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	 
	public List<AfterSaleStatisticsExcel> exportExcel(AfterSaleStatisticsExcel afterSaleStatisticsExcel){
		
		return this.afterSaleStatisticsMapper.exportExcel(afterSaleStatisticsExcel);
	}
}
