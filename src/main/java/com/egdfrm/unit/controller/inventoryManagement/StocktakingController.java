package com.egdfrm.unit.controller.inventoryManagement;

 
import java.io.IOException;
import java.io.OutputStream; 
import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser; 
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.ExportExcel;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.PDDetailExcel;
import com.egdfrm.unit.excelmodel.PDSummaryExcel;
import com.egdfrm.unit.excelmodel.StockSendExcel;
import com.egdfrm.unit.service.inventoryManagement.TakeStockService;

/**
 * 盘点
 * @author hgb
 * @date 2017-1-16
 */
@Controller
@RequestMapping("stocktakingController")
public class StocktakingController extends BaseController {
	 
	
	@Autowired
	private TakeStockService takeStockService;
	
	/**  
     * @return 主页面
     */ 
    @RequestMapping("init")
    public String init() { 
   	 	return "unit/inventoryManagement/takeStock";
    }
	
    /**
     * 子库选择查询
     * @param subpool 子库
     * @return   数据列表
     * @author	hgb
     * @date 2017-1-16
     */
    @RequestMapping("getSubpoolList")
    @ResponseBody
	public String getSubpoolList(Pagination page,String subpool){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>();
		map.put("subpool",subpool);
		page = takeStockService.getSubpoolList(pagination,map);
		return JSON.toJSONString(page); 
	}
	

	 /**
     * 货位选择查询
     * @param subpool1 子库
     * @param subpool 货位
     * @return   数据列表
     * @author	hgb
     * @date 2017-1-16
     */ 
    @RequestMapping("getLocationList")
    @ResponseBody
	public String getLocationList(Pagination page,String subpool1,String subpool){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>();
		map.put("subpool",subpool1);
		map.put("location",subpool); 
		page = takeStockService.getLocationList(pagination,map);
		return JSON.toJSONString(page); 
	}
    
    /**
     * 物料选择选择查询
     * @param subpool 物料编码
     * @return   数据列表
     * @author	hgb
     * @date 2017-1-16
     */
    @RequestMapping("getItemList")    
    @ResponseBody 
	public String getItemList(Pagination page,String subpool){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>(); 
		map.put("segment1",subpool);
		page = takeStockService.getItemList(pagination,map);
		return JSON.toJSONString(page); 
	}
    
	/**
	 * 生成盘点报表
	 * @param su 
	 * @param subinventoryCode 子库
	 * @param locationCode 货位
	 * @return   String
	 * @author	hgb
	 * @date 2017-1-17
	 */
    @RequestMapping("callGeneratePD")
    @ResponseBody
    public String callGeneratePD(@CurrentLoginInfo ShiroUser su, String subinventoryCode,
			String locationCodeStart,String locationCodeEnd,String item){ 
    	return JSON.toJSONString( 
    			this.takeStockService.
    			callGeneratePD(su, subinventoryCode, locationCodeStart, locationCodeEnd, item));
    }
    
    /** 
     * @param beginDate 盘点开始时间
     * @param endDate 盘点结束时间
     * @param status 盘点状态
     * @return    分页数据
     * @author	hgb
     * @date 2017-1-17
     */
    @RequestMapping("getpdHeadersList")
    @ResponseBody
    public String getpdHeadersList(Pagination page,String beginDate,String endDate,String status){
    	Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>();
		map.put("beginDate",beginDate.replaceAll("-", ""));
		map.put("endDate",endDate.replaceAll("-", ""));
		map.put("status",status);
		page = takeStockService.getpdHeadersList(pagination,map);
		return JSON.toJSONString(page); 
    }
    
    
    
    /**
     * 跳转至 盘点汇总信息页面 
     * @date 2017-1-17
     */
    @RequestMapping("toIterationSummary")
    public String toIterationSummary(HttpServletRequest request,String headerId,String organizationId){
    	request.setAttribute("organizationId", organizationId);
    	request.setAttribute("headerId", headerId); 
    	return "unit/inventoryManagement/iterationSummary";
    }
    
    /**
     * 
     * @param heardId 头ID
     * @return  汇总信息页面 的数据 
     * @author	hgb
     * @date 2017-1-17
     */
    @RequestMapping("getIterationSummary")
    @ResponseBody
    public String getIterationSummary(String headerId,String organizationId){ 
		Map<String,Object>  map = new HashMap<>();
		map.put("headerId",headerId );
		map.put("organizationId",organizationId); 
		List<Map<String, Object>> list = takeStockService.getIterationSummary(map);
		return JSON.toJSONString(list); 
    }
    
    /**
     *  导出盘点汇总EXCEL
     * @param headerId 头 id
     * @param organizationId  组织id  
     * @author	hgb
     * @date 2017-1-17
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,
    		String headerId,String organizationId){
    	Map<String,Object>  map = new HashMap<>();
		map.put("headerId",headerId );
		map.put("organizationId",organizationId); 
		try {
			List<PDSummaryExcel> list = this.takeStockService.getIterationSummaryExcel(map);
			if(list != null && list.size() > 0){
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "盘点汇总模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"盘点汇总.xlsx",list,1,0,0);
			 }
		} catch (Exception e) { 
			e.printStackTrace();
		} 
 
    }
    
    /**
     * 跳转至 盘点明细信息 
     * @date 2017-1-18
     */
    @RequestMapping("toDetailList")
    public String toDetailList(HttpServletRequest request,String headerId,String organizationId){
    	request.setAttribute("organizationId", organizationId);
    	request.setAttribute("headerId", headerId); 
    	return "unit/inventoryManagement/pdDetail";
    }
    
    /**
     * @param organizationId 组织ID
     * @param heardId 头ID
     * @return  明细信息页面 的数据 
     * @author	hgb
     * @date 2017-1-17
     */
    @RequestMapping("getDetailList")
    @ResponseBody
    public String getDetailList(Pagination page,String headerId,String organizationId){
    	Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
		Map<String,Object>  map = new HashMap<>();
		map.put("headerId",headerId );
		map.put("organizationId",organizationId); 
		page = takeStockService.getDetailList(pagination,map);
		return JSON.toJSONString(page); 
    }
    
    /**
     *  导出盘点明细EXCEL
     * @param headerId 头 id
     * @param organizationId  组织id  
     * @author	hgb
     * @date 2017-1-18
     */
    @RequestMapping("exportDetailExcel")
    public void exportDetailExcel(HttpServletRequest request,HttpServletResponse response,
    		String headerId,String organizationId){
    	Map<String,Object>  map = new HashMap<>();
		map.put("headerId",headerId );
		map.put("organizationId",organizationId); 

    	try {
			List<PDDetailExcel> list = this.takeStockService.exportDetailExcel(map);
			if(list != null && list.size() > 0){
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "盘点明细模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"盘点明细.xlsx",list,1,0,0);
			 }
		} catch (Exception e) { 
			e.printStackTrace();
		} 
    }
    
    /**
     * 
     * @param pdNumbers 
     * @return   
     * @author	hgb
     * @date 2017-5-5
     */
    @RequestMapping("updatePdNumberToNewPdNumber")
    @ResponseBody
    public String updatePdNumberToNewPdNumber(String pdNumbers){
    	String[] pdNumber = pdNumbers.split(",");
    	Json json =  takeStockService.updatePdNumberToNewPdNumber(pdNumber); 
		return JSON.toJSONString(json); 
    }
    
}
