package com.egdfrm.unit.controller.barcodeManagement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.unit.model.standard.MesItemLocattions;
import com.egdfrm.unit.service.barcodeManagement.CargospaceBarcodeService;

/**
 * 货位条码管理
 * @author hgb
 * @date 2016-12-22
 */
@Controller
@RequestMapping("cargospaceBarcodeController")
public class CargospaceBarcodeController extends BaseController{
	
	@Autowired
	private CargospaceBarcodeService service;
	
	/**
	 * 入口页面
	 * @return   
	 * @author	hgb
	 * @date 2016-12-22
	 */
	@RequestMapping("init")
	public String init(){
		return "unit/barcodeManagement/cargospaceBarcode";
	}
	
	/**
	 * 货位列表数据
	 * @param cargospace 货位
	 * @return   
	 * @author	hgb
	 * @throws Exception 
	 * @date 2016-12-22
	 */
	@RequestMapping("getCargospaceBarcode")
	@ResponseBody
	public Map<String, Object> getCargospaceBarcode(int limit,int offset,MesItemLocattions mesItemLocattions) throws Exception{
		return service.getCargospaceBarcode(limit,offset,mesItemLocattions);
	}
	
	/**
	 * 根据序列号生成二维码
	 * @param codes
     */
	@RequestMapping("printCargospaceCode")
	@ResponseBody
	public String printCargospaceCode(HttpServletRequest request,@RequestParam("codes[]") String[] codes){
		return service.printCargospaceCode(request, codes);
		
	}
	
	
}
