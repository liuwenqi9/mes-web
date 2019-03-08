package com.egdfrm.unit.service.sale;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.WXOutExcel;
import com.egdfrm.unit.mapper.sale.WXOutMapper;

/**
 * 外销出货通知单--service 
 */
@Service
public class WXOutService extends BaseService{
	@Autowired
	private WXOutMapper wXOutMapper;
	
	/** 
	 *  挑库发运产品 条码查询
	 * @author	hgb
	 * @date 2017-3-14
	 */
	 public Pagination getWXOutLists(Pagination pagination, Map<String, Object> map) {
	        //数据总数查询
	        long count = wXOutMapper.getWXOutCount(map);
	        List<Map<String, Object>> list = wXOutMapper.getWXOutList(pagination, map);
	        pagination.setRows(list);
	        pagination.setTotal(count);
	        return pagination;
	    }

	 /*
	  * 导出
	  */
	 public List<WXOutExcel> wXOutExportExcel(Map<String, Object> map){
		 return wXOutMapper.exportExcel(map);
	 }
}
