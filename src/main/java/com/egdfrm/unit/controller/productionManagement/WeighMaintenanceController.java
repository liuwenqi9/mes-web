package com.egdfrm.unit.controller.productionManagement;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.WeighMaintenanceExcel;
import com.egdfrm.unit.service.productionManagement.WeighMaintenanceService;

/**
 * 包装箱重量维护--控制器
 * @author hgb
 * @date 2017-4-17
 */
@Controller
@RequestMapping("weighMaintenanceController")
public class WeighMaintenanceController extends BaseController{

	@Autowired
	private WeighMaintenanceService weighMaintenanceService;
	
	/**
     * rukou
     * @return
     */
    @RequestMapping("init")
    public String init(){ 
        return "unit/productionManagement/weigh";
    }
    
    /**
	 * 毛重分页数据  
	 * @return
	 */
	@RequestMapping("getList")   
	@ResponseBody
	public String getList(Pagination page,String name,String startDate,String endDate,String segment1){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>(); 
		map.put("name",name);
		map.put("startDate",startDate==""?"":(startDate+" 00:00:00"));
		map.put("endDate",endDate==""?"":(endDate+" 23:59:59")); 
		map.put("segment1",segment1);
		page = weighMaintenanceService.getList(pagination,map);
		return JSON.toJSONString(page);
	}
    
	/**
	 *  确认
	 * @author	hgb
	 * @date 2017-4-20
	 */
	@RequiresPermissions("weighMaintenanceController:comfrm")
	@RequestMapping("getObjectByWipEntityName")
	@ResponseBody
	public String getObjectByWipEntityName(String wip_entity_name){
		Json json = new Json();
		Map<String,Object>  map = new HashMap<>(); 
		map.put("wip_entity_name",wip_entity_name); 
		Map<String, Object> map2=weighMaintenanceService.getObjectWipEntityName(map);
		if(map2==null){
			json.setSuccess(false);
			json.setMessage("你输入的工单有误");
		}else{
			json.setSuccess(true);
			json.setMessage("请求成功！");
			json.setResult(map2);
		} 
		return JSON.toJSONString(json);
	}
	
	
	/**
	 * 工单选择(无效了)
	 * @return
	 */
	@RequiresPermissions("weighMaintenanceController:check")
	@RequestMapping("getWipEntityName")
	@ResponseBody
	public String getWipEntityName(Pagination page,String wip_entity_name){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>(); 
		map.put("wip_entity_name",wip_entity_name);  
		page = weighMaintenanceService.getWipEntityName(pagination,map);
		return JSON.toJSONString(page);
	} 
	
	/**
	 * 保存数据
	 * save or update  
	 * @param wip_entity_id 工单
	 * @param gross_weight 实际毛重 kg 
	 * @date 2017-4-17
	 */
	@RequiresPermissions("weighMaintenanceController:save")
	@RequestMapping(value="saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(String wip_entity_id,String gross_weight,String pack_weight,@CurrentLoginInfo UserAuthenRealm.ShiroUser user){ 
		String uid = String.valueOf(user.getUserId());
		Json json = new Json(); 
		boolean b = weighMaintenanceService.saveOrUpdate(wip_entity_id, gross_weight,pack_weight,uid);
		if(b){
			json.setSuccess(b); 
			json.setMessage("保存成功！");
		}else{
			json.setSuccess(b); 
			json.setMessage("数据异常！");
		} 
		return JSON.toJSONString(json);
	}
	
	/**
	 * 
	 *    导出excel
	 * @author	hgb
	 * @date 2017-4-18
	 */
	@RequiresPermissions("weighMaintenanceController:export")
	@RequestMapping("exportExcel") 
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,String name,String startDate,String endDate,String segment1){
		
		Map<String,Object>  map = new HashMap<>(); 
		map.put("name",name);
		map.put("startDate",startDate==""?"":(startDate+" 00:00:00"));
		map.put("endDate",endDate==""?"":(endDate+" 23:59:59"));
		map.put("segment1",segment1);
		try {
			List<WeighMaintenanceExcel> list = weighMaintenanceService.exportExcel(map);
			if(list != null && list.size() > 0){
				XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "成品工单标准包装箱重量维护模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"成品工单标准包装箱重量维护.xlsx",list,1,0,0);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	
}
