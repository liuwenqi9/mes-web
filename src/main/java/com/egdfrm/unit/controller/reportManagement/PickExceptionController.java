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
import com.egdfrm.unit.excelmodel.PickException;
import com.egdfrm.unit.model.report.Saot;
import com.egdfrm.unit.service.reportManagement.PickExceptionService;

/**
 * 挑库发运异常 统计报表
 * @author hgb
 * @date 2017-5-18
 */
@Controller
@RequestMapping("pickExceptionController")
public class PickExceptionController extends BaseController {
	
	@Autowired
	private PickExceptionService pickExceptionService; 
	
	@RequestMapping("init")
	public String init(){ 
		return "unit/reportManagement/pickException"; 
	}
  
	@RequestMapping("listByPage") 
	@ResponseBody
	public String listByPage(Pagination page,PickException pickException){ 
		
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        pagination = pickExceptionService.findPage(pagination,pickException);
		
		return JSON.toJSONString(pagination);
	}
	
	
	 /**
     * 导出Excel
     * @param request
     * @param response
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, PickException pickException){
        try {
            List<PickException> list = pickExceptionService.export(pickException);
            //获取XSSFWorkbook
            XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "挑库发运异常数据检查模板.xlsx");
            ExcelUtils.exportExcel(response,workBook,"挑库发运异常数据检查.xlsx",list,2,0,0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
