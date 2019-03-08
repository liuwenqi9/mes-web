package com.egdfrm.unit.service.inventoryManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.PDDetailExcel;
import com.egdfrm.unit.excelmodel.PDSummaryExcel;
import com.egdfrm.unit.mapper.expand.inventoryManagement.TakeStockMapper;
/**
 * 盘点service 类
 * @author hgb
 * @date 2017-1-16
 */
@Service
public class TakeStockService  extends BaseService{
	
	@Autowired
	private TakeStockMapper takeStockMapper;
	/**
	 *  子库查询分页数据
	 * @date 2017-1-16
	 */
	public Pagination getSubpoolList(Pagination pagination,Map<String, Object> map){
		//查询数据总数
		long count = takeStockMapper.getSubpoolCount(map);
		List<Map<String, Object>> list = takeStockMapper.getSubpoolList(pagination, map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	/**
	 *    货位查询分页数据
	 * @author	hgb
	 * @date 2017-1-16
	 */
	public Pagination getLocationList(Pagination pagination,Map<String, Object> map){
		//查询数据总数
		long count = takeStockMapper.getLocationCount(map);
		List<Map<String, Object>> list = takeStockMapper.getLocationList(pagination, map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	/**
	 *  物料编码查询分页数据
	 * @date 2017-1-16
	 */
	public Pagination getItemList(Pagination pagination,Map<String, Object> map){
		//查询数据总数
		long count = takeStockMapper.getItemCount(map);
		List<Map<String, Object>> list = takeStockMapper.getItemList(pagination, map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	/**
	 * 生成盘点报表
	 * @param su 
	 * @param subinventoryCode 子库
	 * @param locationCode 货位
	 * @return    DbReturnParameter
	 * @author	hgb
	 * @date 2017-1-17
	 */
	public DbReturnParameter callGeneratePD(ShiroUser su,String subinventoryCode,
			String locationCodeStart,String locationCodeEnd,String item){
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("organizationId",su.getOrgId());
		params.put("subinventoryCode",subinventoryCode); 
		params.put("locationCodeStart",locationCodeStart); 
		params.put("locationCodeEnd",locationCodeEnd); 
		params.put("item",item); 
		params.put("userId",su.getUserId()); 
		params.put(MesConstants.DBRETURN, dbreturn);
		try {
			this.takeStockMapper.callGeneratePD(params);
		} catch (Exception e) { 
			dbreturn.setxStatus("N");
			dbreturn.setxMessage("网络异常！");
			e.printStackTrace();
			return dbreturn;
		}
		return dbreturn;
	}

	/**
	 *  盘点查询分页数据
	 * @author	hgb
	 * @date 2017-1-16
	 */
	public Pagination getpdHeadersList(Pagination pagination,Map<String, Object> map){
		//查询数据总数
		long count = takeStockMapper.getpdHeadersCount(map);
		List<Map<String, Object>> list = takeStockMapper.getpdHeadersList(pagination, map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	/**
	 *  盘点汇总数据
	 * @author	hgb
	 * @date 2017-1-16
	 */
	public List<Map<String, Object>> getIterationSummary(Map<String, Object> map){
		// 查询数据总数
		List<Map<String, Object>> list = takeStockMapper.getIterationSummaryList(map); 
		return list;
	}
	/**  
	 * 盘点汇总数据 EXCEL
	 * @author	hgb
	 * @date 2017-1-17
	 */
	public List<PDSummaryExcel> getIterationSummaryExcel(Map<String, Object> map){
		 
		return takeStockMapper.getIterationSummaryExcel(map);
	}
	
	/**
	 *  盘点明细数据
	 * @author	hgb
	 * @date 2017-1-18
	 */
	public Pagination getDetailList(Pagination pagination,Map<String, Object> map){
		// 查询数据总数
		long count = takeStockMapper.getDetailCount(map);
		List<Map<String, Object>> list = takeStockMapper.getDetailList(pagination,map); 
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	/**
	 *   构建 盘点明细  excel数据
	 * @author	hgb
	 * @date 2017-1-18
	 */
	public List<PDDetailExcel> exportDetailExcel(Map<String, Object> map){ 
		List<PDDetailExcel> listnew = new ArrayList<PDDetailExcel>();
		List<PDDetailExcel> list2 = takeStockMapper.getDetailListExcel(map);
		for (PDDetailExcel pd : list2) { 
			pd.setQuantity(pd.getBarcodeQuantity()-pd.getPdQuantity());
			listnew.add(pd);
		}
		return listnew;
		
	}
	
	public Json updatePdNumberToNewPdNumber(String[] pdNumber){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("修改成功！"); 
		for (int i = 0; i < pdNumber.length; i++) {
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> params = new HashMap<String, Object>(); 
			params.put("pdNumber", pdNumber[i]);
			params.put(MesConstants.DBRETURN, dbreturn);
			takeStockMapper.callpdUpdate(params); 
			String status = dbreturn.getxStatus();
			String message = dbreturn.getxMessage();
			if(!MesConstants.SUCCESS.equals(status)){ 
				json.setSuccess(false);
				json.setMessage(message);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				break;
			} 
		}  
		return json;
	}
	
	
}
