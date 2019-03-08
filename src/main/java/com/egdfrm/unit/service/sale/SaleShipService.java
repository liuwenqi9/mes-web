package com.egdfrm.unit.service.sale;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.SaleShipExcel;
import com.egdfrm.unit.mapper.sale.SaleShipMapper;

/**
 * 挑库发运产品查询--service 
 */
@Service
public class SaleShipService extends BaseService{
	@Autowired
	private SaleShipMapper saleShipMapper;
	
	/** 
	 *  挑库发运产品 条码查询
	 * @author	hgb
	 * @date 2017-3-14
	 */
	 public Pagination getSaleShipLists(Pagination pagination, Map<String, Object> map) {
	        //数据总数查询
	        long count = saleShipMapper.getSaleShipCount(map);
	        List<Map<String, Object>> list = saleShipMapper.getSaleShipList(pagination, map);
	        pagination.setRows(list);
	        pagination.setTotal(count);
	        return pagination;
	    }

	 /*
	  * 导出
	  */
	 public List<SaleShipExcel> saleShipExportExcel(Map<String, Object> map){
		 return saleShipMapper.exportExcel(map);
	 }
}
