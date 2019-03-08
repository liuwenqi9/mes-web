package com.egdfrm.unit.controller.productionManagement;

import java.util.List;
import java.util.Map;

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
import com.egdfrm.unit.service.productionManagement.OutsourcingSplitService;
/**
 * 外购机拆箱
 * @author hgb
 * @date 2017-2-23
 */
@Controller()
@RequestMapping("outsourcingSplitController")
public class OutsourcingSplitController extends BaseController {
	
	
	@Autowired
	private OutsourcingSplitService outsourcingSplitService;
	
	@RequestMapping("init")
	public String init(){
		return "unit/productionManagement/outsourcingSplit";
	}
	
	
	@RequestMapping(value="getOutsourcingByBarcode")
	@ResponseBody
	public List<Map<String, Object>> getOutsourcingByBarcode(String barcodeText){ 
		return outsourcingSplitService.getOutsourcingByBarcode(barcodeText);
	}
	
	
	@RequestMapping(value="confirm",method = RequestMethod.POST)
	@ResponseBody
	public String confirm(String prodIds,String barcodeId,@CurrentLoginInfo ShiroUser su ){
		String [] ids = prodIds.split(",");
		Json json = new Json();  
		try { 
			outsourcingSplitService.confirmSplit(ids, barcodeId,su.getUserId());
			json.setSuccess(true); 
			json.setMessage("拆箱成功！");
			return JSON.toJSONString(json);
		} catch (Exception e) {  
			e.printStackTrace();
			json.setSuccess(false); 
			json.setMessage("拆箱出错！");
			return JSON.toJSONString(json);
		}
		
	}
}
