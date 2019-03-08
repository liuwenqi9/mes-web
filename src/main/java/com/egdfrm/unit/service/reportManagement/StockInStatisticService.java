package com.egdfrm.unit.service.reportManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.reportManagement.StockInStatisticsMapper;
import com.egdfrm.unit.model.stock.StockInStatisticsExcel;

/**
 * 入库统计日 报表 -service
 * @author hgb
 * @date 2017-5-10
 */
@Service
public class StockInStatisticService {

	@Autowired
	private StockInStatisticsMapper stockInStatisticsMapper;
	
	public Pagination listByPage(Pagination pagination,StockInStatisticsExcel stockInStatisticsExcel) {
		long count = stockInStatisticsMapper.getCount(stockInStatisticsExcel);
		List<StockInStatisticsExcel> list = stockInStatisticsMapper.findPage(pagination, stockInStatisticsExcel);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	 
	public List<StockInStatisticsExcel> exportExcel(StockInStatisticsExcel stockInStatisticsExcel){
		return this.stockInStatisticsMapper.exportExcel(stockInStatisticsExcel);
	}
}
