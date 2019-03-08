package com.egdfrm.unit.controller.afterSaleManagement;

import java.util.Map;

import net.sf.ehcache.transaction.xa.processor.XARequest.RequestType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.AfterSaleManagement.AfterSaleBack;
import com.egdfrm.unit.service.afterSaleManagement.AfterSaleBackService;

/**
 * 售后退货
 * @author hgb
 * @date 2017-6-15
 */
@Controller
@RequestMapping("afterSaleBackController")
public class AfterSaleBackController  extends BaseController{
	
	@Autowired
	private AfterSaleBackService afterSaleBackService;
	
	@RequestMapping("init")
	public String init(){
		return "unit/afterSale/afterSaleBack";
	}


	/**
	 * CUSTOMER_NAME
	 * 根据客户名  取客户名、联系人、联系电话、收货地址
	 */
	@RequestMapping("getCustomerInfo")
	@ResponseBody
	public String getCustomerInfo(Pagination page,String customer){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		page =  afterSaleBackService.getCustomerInfo(pagination, customer); 
		return JSON.toJSONString(page); 
	}
	
	/**
	 * 验证产品条码 是否存在 不存在 return false
	 * 存在时 取（ 条码 物料编码，型号，描述 ） return true
	 * @param barcodeText  产品条码
	 * @author	hgb
	 * @date 2017-6-15
	 */
	@RequestMapping("getBarcodeInfo")
	@ResponseBody
	public String getBarcodeInfo(String barcodeText){
		Json json = new Json();
		boolean b = afterSaleBackService.isValid(barcodeText); 
		if(!b){//不存在
			json.setSuccess(false);
			json.setMessage(barcodeText+"不存在！"); 
		}else{ //取 条码相关信息
			json.setSuccess(true); 
			json.setMessage("请求成功");
			json.setResult( 
			afterSaleBackService.getBarcodeInfoByBarcodeText(barcodeText) );
		} 
		return JSON.toJSONString(json);
	}
	/**
	 * 保存
	 * @return    
	 * @author	hgb
	 * @date 2017-6-15
	 */
	@RequestMapping(value = "saveAfterSaleBarcode")  
	@ResponseBody
	public String saveAfterSaleBarcode(AfterSaleBack afterSaleBack,@CurrentLoginInfo ShiroUser user ){ 
		String uid =String.valueOf( user.getUserId());
		Json json = afterSaleBackService.saveAfterSaleBarcode(afterSaleBack,uid);
		return JSON.toJSONString(json);
		
	}
	
	
}
