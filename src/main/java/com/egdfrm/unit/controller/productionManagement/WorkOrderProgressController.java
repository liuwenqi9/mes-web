package com.egdfrm.unit.controller.productionManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.excelmodel.SaleShipExcel;
import com.egdfrm.unit.excelmodel.WorkOrderProgressExcel;
import com.egdfrm.unit.model.barcodeManagement.WorkOrderProgress;
import com.egdfrm.unit.service.productionManagement.IWorkOrderProgressService;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工单生产进度
 * @author hgb
 */
@Controller
@RequestMapping("workOrderProgressController")
public class WorkOrderProgressController extends BaseController{
	
	@Autowired
	private IWorkOrderProgressService iWorkOrderProgressService;
	
	/**
	 * 工单生产进度页面入口
	 * @return view localction
	 */
	@RequestMapping("init")
	public String init(Model model){
		List<Map<String,Object>> lines = iWorkOrderProgressService.getLines();
		model.addAttribute("lines",lines);
		return "unit/productionManagement/workOrderProgress";
	}
	
	/**
	 * 工单生产进度 分页数据
	 * @return
	 */
	@RequestMapping("getWorkOrderList")
	@ResponseBody
	public String getwrokOrderList(Pagination page, WorkOrderProgress wop){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		page = iWorkOrderProgressService.getwrokOrderList(pagination,wop);
		return JSON.toJSONString(page);
	}

	/**
	 * 获取装配件列表
	 * @param page 分页数据
	 * @param workOrderNumber 装配件
	 * @return
	 */
	@RequestMapping(value = "getPartsByPage")
	@ResponseBody
	public String getPartsByPage(Pagination page, String workOrderNumber){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>();
		map.put("parts",workOrderNumber);
		page = iWorkOrderProgressService.getPartsByPage(pagination,map);
		return JSON.toJSONString(page);
	}
	
	/**
	 *  工单生产进度--导出excel
	 * @param wop    
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,WorkOrderProgress wop){
		try {  
			List<WorkOrderProgressExcel> list = iWorkOrderProgressService.exportExcel(wop);
            if(list != null && list.size() > 0){
                //获取XSSFWorkbook
                XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "工单生产进度模板.xlsx");
                ExcelUtils.exportExcel(response,workBook,"工单生产进度.xlsx",list,2,0,0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
}
