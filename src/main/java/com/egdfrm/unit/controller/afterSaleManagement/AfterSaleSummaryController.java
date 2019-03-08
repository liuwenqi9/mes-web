package com.egdfrm.unit.controller.afterSaleManagement;
 
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egdfrm.unit.model.AfterSaleManagement.AfterSalePeople;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.AfterSaleSummaryExcel;
import com.egdfrm.unit.model.AfterSaleManagement.AfterSaleBack; 
import com.egdfrm.unit.service.afterSaleManagement.AfterSaleSummaryService;

/**
 * 售后返修汇总
 * @author hgb
 * @date 2017-6-15
 */ 
@Controller
@RequestMapping("afterSaleSummaryController")
public class AfterSaleSummaryController extends BaseController{
	
	@Autowired
	private AfterSaleSummaryService afterSaleSummaryService;
	
	@RequestMapping("init")
	public String init(){
		 return "unit/afterSale/afterSaleSummary"; 
	}
	/** 
	 *  分页数据     
	 * @author	hgb   
	 * @date 2017-6-17 
	 */
	@RequestMapping("getAfterSaleSummaryList") 
	@ResponseBody
	public String getAfterSaleSummaryList(Pagination page, AfterSaleBack afterSaleBack){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
		page =  afterSaleSummaryService.getAfterSaleSummaryList(pagination,afterSaleBack);    
		return JSON.toJSONString(page); 
	}  
	 
	/**
	 * 售后维修记录 （导出excel）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param afterSaleBack  页面模型 
	 * @author	hgb
	 * @date 2017-10-30
	 */
	@RequestMapping("exportExcel") 
	@ResponseBody
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,AfterSaleBack afterSaleBack){
		try { 
			List<AfterSaleSummaryExcel> list = afterSaleSummaryService.exportExcel(afterSaleBack);
			if(list != null && list.size() > 0){      
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "售后维修记录报表模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"售后维修记录报表.xlsx",list,2,0,0);
			 }
		} catch (Exception e) {  
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 生成维修单，并打印维修单
	 * @param IDs 售后表id[]
	 * @return   
	 * @author	hgb
	 * @date 2017-6-21
	 */
	@RequiresPermissions("afterSaleSummaryController:generaterRepWorkOrderAndPrint")
	@RequestMapping(value="generaterRepWorkOrderAndPrint",method=RequestMethod.POST)
	@ResponseBody 
	public String generaterRepWorkOrderAndPrint(HttpServletRequest request,String IDs,@CurrentLoginInfo ShiroUser us){ 
		 
		String msg = afterSaleSummaryService.generaterRepWorkOrderAndPrint(request,IDs,String.valueOf(us.getUserId()));
		if(msg==null){
			Json json = new Json();  
			json.setSuccess(false); 
			json.setMessage("生成维修单出错了");
			return JSON.toJSONString(json); 
		}
		//打印维修单
		return afterSaleSummaryService.printer(request, msg);
	}
	/**
	 * 补打 维修单
	 */
	@RequiresPermissions("afterSaleSummaryController:reprinter")
	@RequestMapping(value="reprinter",method=RequestMethod.POST)
	@ResponseBody 
	public String reprinter(HttpServletRequest request,String num){ 
		return afterSaleSummaryService.printer(request, num);  
	}
	/**
	 * 只是生成维修单，（不打印    ）
	 * @author	hgb
	 * @date 2017-10-13
	 */
	@RequiresPermissions("afterSaleSummaryController:generaterRepWorkOrder")
	@RequestMapping(value="generaterRepWorkOrder",method=RequestMethod.POST)
	@ResponseBody 
	public String generaterRepWorkOrder(HttpServletRequest request,String IDs,@CurrentLoginInfo ShiroUser us){ 
		 
		String msg = afterSaleSummaryService.generaterRepWorkOrderAndPrint(request,IDs,String.valueOf(us.getUserId()));
		Json json = new Json();  
		if(msg==null){ 
			json.setSuccess(false); 
			json.setMessage("生成维修单出错了");
			return JSON.toJSONString(json); 
		}
		json.setSuccess(true); 
		json.setMessage("生成维修单"+msg); 
		return  JSON.toJSONString(json); 
	}
	
	 /**
	  * 新版的打印维修单，《根据传过来的id，判断是否是同一个维修单上的id，是：允许打印，并修改打印状态为Y。不是：返回提示》
	  * 按维修单、售后表id 打印维修单 维修单 
	  * @author	hgb 
	  * @date 2017-10-13
	  */
	@RequiresPermissions("afterSaleSummaryController:reprinter2")
	@RequestMapping(value="reprinter2",method=RequestMethod.POST)
	@ResponseBody 
	public String reprinter2(HttpServletRequest request,String IDs){ 
		return afterSaleSummaryService.printer2(request,IDs);  
	}
	
	/**
	 * 弹出维修记录框
	 * 根据id 取 维修单号，维修人员，补号SO，处理类型，维修分析，故障描述 
	 */
	@RequiresPermissions("afterSaleSummaryController:getLineInfoById")
	@RequestMapping(value="getLineInfoById",method=RequestMethod.POST)
	@ResponseBody
	public String getLineInfoById(String IDs,@CurrentLoginInfo ShiroUser us){
		Json json = new Json();
		//String userName = us.getUserName();
		Map<String, Object> map =  afterSaleSummaryService.getLineInfoById(IDs.split(",")[0]); 
		//改成 获取 erp 表中的维修人员
		//List<Stri>
		//	map.put("REP_PEOPLE", userName);  
			json.setSuccess(true); 
			json.setResult(map);
			json.setMessage("请求成功！");
		return  JSON.toJSONString(json);		
	}
	
	/**
	 *  获取更新行信息
	 */
	@RequiresPermissions("afterSaleSummaryController:getUpdateLineInfoById")
	@RequestMapping(value="getUpdateLineInfoById",method=RequestMethod.POST)
	@ResponseBody
	public String getUpdateLineInfoById(String ID,@CurrentLoginInfo ShiroUser us){
		Json json = new Json(); 
		Map<String, Object> map =  afterSaleSummaryService.getUpdateLineInfoById(ID); 
			json.setSuccess(true); 
			json.setResult(map);
			json.setMessage("请求成功！");
		return  JSON.toJSONString(json);		
	}
	
	/**
	 * 获取所有的维修人员
	 * @return   
	 * @author	hgb
	 * @date 2017-7-19
	 */
	@RequestMapping(value="getRepPeopleAll",method=RequestMethod.POST)
	@ResponseBody
	public String getRepPeopleAll(){
		Json json = new Json(); 
		List<Map<String, Object>> list =  afterSaleSummaryService.getRepPeopleAll();    
			json.setSuccess(true); 
			json.setResult(list);
			json.setMessage("请求成功！");
		return  JSON.toJSONString(json);		
	}
	
	/**
	 *  维修记录维护（保存按钮）  
	 * @author	hgb
	 * @date 2017-6-18
	 */
	@RequestMapping(value="bacthUpdate",method=RequestMethod.POST)  
	@ResponseBody
	public String bacthUpdate(AfterSaleBack afterSaleBack,@CurrentLoginInfo ShiroUser user){
		String uid = String.valueOf(user.getUserId());
		Json json = afterSaleSummaryService.bacthUpdate(afterSaleBack, uid);
		return JSON.toJSONString(json); 
	} 
	
	
	/**
	 *  更新 一条记录
	 * @author	hgb
	 * @date 2017-6-18
	 */
	@RequestMapping(value="updateById",method=RequestMethod.POST)  
	@ResponseBody 
	public String updateById(AfterSaleBack afterSaleBack,@CurrentLoginInfo ShiroUser user){
		String uid = String.valueOf(user.getUserId());
		Json json = afterSaleSummaryService.updateById(afterSaleBack, uid);
		return JSON.toJSONString(json); 
	}
	
	/**
	 * 批量更新 交仓库日期       
	 * @author	hgb
	 * @date 2017-10-12
	 */
	@RequestMapping(value="updateHandOverByIds",method=RequestMethod.POST)   
	@ResponseBody 
	public String updateHandOverByIds(AfterSaleBack afterSaleBack,@CurrentLoginInfo ShiroUser user){
		String uid = String.valueOf(user.getUserId());
		String[] str = afterSaleBack.getIDs().split(",");
		Integer[] in = new Integer[str.length];
		for (int i = 0; i < str.length; i++) {
			in[i] = Integer.parseInt(str[i]);
		}
		afterSaleBack.setArrayId(in);
		Json json = afterSaleSummaryService.updateHandOverByIds(afterSaleBack, uid);
		return JSON.toJSONString(json); 
	}
// -------维修人员维护也页面
//afterSaleSummaryController/init2 入口页面
	@RequestMapping("init2")
	public String init2(){
		return "unit/afterSale/afterSalePeople";
	}

	/**
	 *  分页数据
	 * @author	hgb
	 * @date 2018-03-29
	 */
	@RequestMapping("getAfterSalePeopleList")
	@ResponseBody
	public String getAfterSalePeopleList(Pagination page, AfterSalePeople afterSalePeople){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		page =  afterSaleSummaryService.getAfterSalePeopleList(pagination,afterSalePeople);
		return JSON.toJSONString(page);
	}

	//updateById 更改维修人员
	@RequestMapping(value="peopleUpdate",method=RequestMethod.POST)
	@ResponseBody
	public String updatePeopleById(AfterSalePeople afterSalePeople,@CurrentLoginInfo ShiroUser user){
		String uid = String.valueOf(user.getUserId());
		Json json = afterSaleSummaryService.updatePeopleById(afterSalePeople, uid);
		return JSON.toJSONString(json);
	}

	//add 维修人员
	@RequestMapping(value="peopleAdd",method=RequestMethod.POST)
	@ResponseBody
	public String peopleAdd(AfterSalePeople afterSalePeople,@CurrentLoginInfo ShiroUser user){
		//构建
		String uid = String.valueOf(user.getUserId());
		Json json = afterSaleSummaryService.peopleAdd(afterSalePeople, uid);
		return JSON.toJSONString(json);
	}

	@RequestMapping(value="peopleValidation",method=RequestMethod.POST)
	@ResponseBody
	public String peopleValidation(AfterSalePeople afterSalePeople){
		Json json = afterSaleSummaryService.peopleValidation(afterSalePeople);
		return JSON.toJSONString(json);
	}

}
