package com.egdfrm.unit.controller.afterSaleManagement;
 

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
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.AfterSaleManagement.AfterSaleBack;
import com.egdfrm.unit.service.afterSaleManagement.AfterSaleOutService;

/**
 * 售后出货
 * @author hgb
 * @date 2017-6-15
 */
@Controller
@RequestMapping("afterSaleOutController")
public class AfterSaleOutController  extends BaseController{
	
	@Autowired
	private AfterSaleOutService afterSaleOutService;
	
	@RequestMapping("init")
	public String init(){
		return "unit/afterSale/afterSaleOut";
	}
	
	/** 
	 *  分页数据    
	 * @author	hgb  
	 * @date 2017-6-17 
	 */
	@RequestMapping("getAfterSaleOutList") 
	@ResponseBody
	public String getAfterSaleOutList(Pagination page, AfterSaleBack afterSaleBack){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
		page =  afterSaleOutService.getAfterSaleOutList(pagination,afterSaleBack);  
		return JSON.toJSONString(page);
	}
	/**
	 *  售后 出货保存
	 * @author	hgb
	 * @date 2017-6-29
	 */
	@RequestMapping(value="updateToSave",method=RequestMethod.POST) 
	@ResponseBody 
	public String updateToSave(AfterSaleBack afterSaleBack,@CurrentLoginInfo ShiroUser user){
		String uid =String.valueOf( user.getUserId()); 
		Json json = afterSaleOutService.updateToSave(afterSaleBack,uid);  
		return JSON.toJSONString(json);
	} 
	 
	/**
	 *  售后 出货确认  
	 * @author	hgb    
	 * @date 2017-6-29
	 */
	@RequestMapping(value="upateToShip",method=RequestMethod.POST) 
	@ResponseBody  
	public String upateToShip(AfterSaleBack afterSaleBack,@CurrentLoginInfo ShiroUser user){
		String uid =String.valueOf( user.getUserId()); 
		Json json = afterSaleOutService.upateToShip(afterSaleBack,uid); 
		return JSON.toJSONString(json);
	
	}
	
}
