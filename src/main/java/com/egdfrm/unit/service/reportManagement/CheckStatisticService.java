package com.egdfrm.unit.service.reportManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.reportManagement.CheckStatisticsMapper;
import com.egdfrm.unit.model.stock.CheckStatisticsExcel;

/**
 * 检验统计日 报表 -service
 * @author hgb
 * @date 2017-06-12
 */
@Service
public class CheckStatisticService {

	@Autowired
	private CheckStatisticsMapper checkStatisticsMapper;
	
	public Pagination listByPage(Pagination pagination,CheckStatisticsExcel checkStatisticsExcel) {
		long count = checkStatisticsMapper.getCount(checkStatisticsExcel);
		List<CheckStatisticsExcel> list = checkStatisticsMapper.findPage(pagination, checkStatisticsExcel);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	 
	public List<CheckStatisticsExcel> exportExcel(CheckStatisticsExcel checkStatisticsExcel){
		return this.checkStatisticsMapper.exportExcel(checkStatisticsExcel);
	}
}
