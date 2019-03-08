package com.egdfrm.unit.service.reportManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.reportManagement.QACheckReportMapper;
import com.egdfrm.unit.model.stock.QACheckReportExcel;

/**
 * QA检验报告-service
 * @author hgb 
 */
@Service
public class QACheckReportService {

	@Autowired
	private QACheckReportMapper qACheckReportMapper;
	
	public Pagination listByPage(Pagination pagination,QACheckReportExcel qACheckReportExcel) {
		long count = qACheckReportMapper.getCount(qACheckReportExcel);
		List<QACheckReportExcel> list = qACheckReportMapper.findPage(pagination, qACheckReportExcel);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	 
	public List<QACheckReportExcel> exportExcel(QACheckReportExcel qACheckReportExcel){
		return this.qACheckReportMapper.exportExcel(qACheckReportExcel);
	}
}
