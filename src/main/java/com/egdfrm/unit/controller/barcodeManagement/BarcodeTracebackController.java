package com.egdfrm.unit.controller.barcodeManagement;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;
/**
 * 条码信息追溯
 * @author hgb
 * @date 2016-12-23
 */
@Controller
@RequestMapping("barcodeTracebackController")
public class BarcodeTracebackController extends BaseController{
	
	
	/**
	 * 入口页面
	 * @return   
	 * @author	hgb
	 * @date 2016-12-23
	 */
	@RequestMapping("init")
	public String init(){
		return "unit/barcodeManagement/barcodeTraceback";
	}

}
