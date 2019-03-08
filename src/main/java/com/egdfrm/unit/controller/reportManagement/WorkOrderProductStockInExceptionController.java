package com.egdfrm.unit.controller.reportManagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.WorkOrderProductStockInException;
import com.egdfrm.unit.service.reportManagement.WorkOrderProductStockInExceptionService;

/**
 * 工单生产入库异常数据检查 
 * @author hgb
 * @date 2017-5-25
 */
@Controller
@RequestMapping("workOrderProductStockInExceptionController") 
public class WorkOrderProductStockInExceptionController extends BaseController {

	@Autowired
	private WorkOrderProductStockInExceptionService workOrderProductStockInExceptionService;
	
	@RequestMapping("init") 
	public String init(){ 
		return "unit/reportManagement/workOrderProductStockInException"; 
	}
	@RequestMapping("listByPage") 
	@ResponseBody
	public String listByPage(Pagination page,WorkOrderProductStockInException workOrderProductStockInException){
		
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        pagination = workOrderProductStockInExceptionService.findPage(pagination,workOrderProductStockInException);
		
		return JSON.toJSONString(pagination);
	}
	
	 /**
     * 导出Excel
     * @param request
     * @param response
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, WorkOrderProductStockInException workOrderProductStockInException){
        try {
            List<WorkOrderProductStockInException> list = workOrderProductStockInExceptionService.export(workOrderProductStockInException);
            //获取XSSFWorkbook
            XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "工单生产入库异常数据检查模板.xlsx");
            ExcelUtils.exportExcel(response,workBook,"工单生产入库异常数据检查.xlsx",list,2,0,0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
