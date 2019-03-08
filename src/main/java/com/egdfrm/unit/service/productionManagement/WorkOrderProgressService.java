package com.egdfrm.unit.service.productionManagement;

import java.util.List;
import java.util.Map;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.WorkOrderProgressExcel;
import com.egdfrm.unit.mapper.productionManagement.WorkOrderProgressMapper;
import com.egdfrm.unit.model.barcodeManagement.WorkOrderProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;

/**
 * 工单生产进度_实现
 */
@Service
public class WorkOrderProgressService implements IWorkOrderProgressService{

	@Autowired
	private WorkOrderProgressMapper workOrderProgressMapper;

	/**
	 * 工单生产进度分页查询
	 * @param pagination 分页条件
	 * @param wop 查询条件
	 * @return
	 */
	@Override
	public Pagination getwrokOrderList(Pagination pagination, WorkOrderProgress wop) {
		//查询数据总数
		long count = workOrderProgressMapper.getCount(wop);
		List<Map<String,Object>> list = workOrderProgressMapper.getwrokOrderList(pagination,wop);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}

	/**
	 * 获取生产线
	 * @return
     */
	@Override
	public List<Map<String, Object>> getLines() {
		return workOrderProgressMapper.getLines();
	}

	/**
	 * 获取装配件
	 * @param pagination
	 * @param map
     * @return
     */
	@Override
	public Pagination getPartsByPage(Pagination pagination, Map<String, Object> map) {
		//查询数据总数
		long count = workOrderProgressMapper.getPartsByCount(map);
		List<Map<String,Object>> list = workOrderProgressMapper.getPartsByPage(pagination,map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}

	@Override
	public List<WorkOrderProgressExcel> exportExcel(WorkOrderProgress wop) { 
		return workOrderProgressMapper.exportExcel(wop);
	}
}
