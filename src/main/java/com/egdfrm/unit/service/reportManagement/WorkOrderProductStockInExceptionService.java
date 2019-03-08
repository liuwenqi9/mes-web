package com.egdfrm.unit.service.reportManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.WorkOrderProductStockInException;
import com.egdfrm.unit.mapper.reportManagement.WorkOrderProductStockInExceptionMapper;

@Service
public class WorkOrderProductStockInExceptionService {
	
	@Autowired
	private WorkOrderProductStockInExceptionMapper workOrderProductStockInExceptionMapper;
	
	public Pagination findPage(Pagination pagination,WorkOrderProductStockInException workOrderProductStockInException) {
        long count = workOrderProductStockInExceptionMapper.findPageCount(workOrderProductStockInException);
        List<WorkOrderProductStockInException> list = workOrderProductStockInExceptionMapper.findPage(pagination,workOrderProductStockInException);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }
	
	public List<WorkOrderProductStockInException> export(WorkOrderProductStockInException workOrderProductStockInException){
    	return workOrderProductStockInExceptionMapper.export(workOrderProductStockInException);
    }

}
