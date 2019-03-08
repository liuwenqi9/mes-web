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
import com.egdfrm.unit.model.stock.QACheckReportExcel;
import com.egdfrm.unit.service.reportManagement.QACheckReportService;

/**
 * QA检验 报告
 * @author hgb 
 */
@Controller()
@RequestMapping("qACheckReportController")
public class QACheckReportController extends BaseController{

	@Autowired
	private QACheckReportService qACheckReportService;
	
	@RequestMapping("init") 
    public String init() {
        return "unit/reportManagement/qACheckReport";
    }
	
	
	/**
     * 分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("listByPage")     
    @ResponseBody 
    public String listByPage(Pagination page,QACheckReportExcel qACheckReportExcel){
    	  Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
    	  page = qACheckReportService.listByPage(pagination, qACheckReportExcel);
          return JSON.toJSONString(page);
    }
     
    /**
     *  QA检验报告
     */
    @RequestMapping("exportExcel") 
    @ResponseBody
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,QACheckReportExcel qACheckReportExcel){
    	
    	try {
			List<QACheckReportExcel> list = qACheckReportService.exportExcel(qACheckReportExcel);
			if(list != null && list.size() > 0){   
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "QA检验报告模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"QA检验报告.xlsx",list,3,0,2);
			 }
		} catch (Exception e) {  
			e.printStackTrace();
		}
    }
    
}
