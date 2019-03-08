package com.egdfrm.unit.controller.productionManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.service.productionManagement.ResetService;

/**
 * 报检单、入库单重置
 * @author hgb
 * @date 2017-2-13
 */
@Controller
@RequestMapping("reset")
public class ResetController extends BaseController{
	
	@Autowired
	private ResetService resetService;
	
	@RequestMapping("base/init")
	public String init(){
		return "unit/productionManagement/reset"; 
	} 
	
	/**
	 *  报检单重置
	 * @param inspectNum 报检单号 
	 * @date 2017-2-13
	 */
	@RequestMapping(value="inspect",method = RequestMethod.POST)
	@ResponseBody
	public String inspect(String inspectNum){
		Json json = new Json();
		//验证报检单
		if (!resetService.verificationInspect(inspectNum)) {
			json.setSuccess(false); 
			json.setMessage("报检单不存在！");
			return JSON.toJSONString(json);
		}
		resetService.resetInspect(inspectNum);
		json.setSuccess(true); 
		json.setMessage("重置成功！");
		return JSON.toJSONString(json);
	}
	
	
	/**
	 *  入库单重置
	 * @param stockNum 报检单号 
	 * @date 2017-2-13
	 */
	@RequestMapping(value="stock",method = RequestMethod.POST)
	@ResponseBody
	public String stock(String stockNum){
		Json json = new Json();
		//验证入库单
		if (!resetService.verificationStockNum(stockNum)) {
			json.setSuccess(false); 
			json.setMessage("入库单不存在！");
			return JSON.toJSONString(json);
		}
		resetService.resetStock(stockNum);
		json.setSuccess(true); 
		json.setMessage("重置成功！");
		return JSON.toJSONString(json);
	}
}
