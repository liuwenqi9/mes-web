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
import com.egdfrm.unit.model.stock.AfterSaleStatisticsExcel;
import com.egdfrm.unit.service.reportManagement.AfterSaleStatisticsService;

/**
 *  售后服务机品质分析报表 --控制器
 * @author hgb 
 */
@Controller()
@RequestMapping("afterSaleStatisticsController")
public class AfterSaleStatisticsController extends BaseController{

	@Autowired
	private AfterSaleStatisticsService afterSaleStatisticsService;
	
	@RequestMapping("init") 
    public String init() {
        return "unit/reportManagement/afterSaleStatistics";
    }   
	
	
	/**
     * 分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("listByPage")     
    @ResponseBody 
    public String listByPage(Pagination page,AfterSaleStatisticsExcel afterSaleStatisticsExcel){
    	  Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
    	  page = afterSaleStatisticsService.listByPage(pagination, afterSaleStatisticsExcel);
          return JSON.toJSONString(page);
    }  
     
    /**
     *   售后服务机品质分析报表 excel 
     */
    @RequestMapping("exportExcel") 
    @ResponseBody
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,AfterSaleStatisticsExcel afterSaleStatisticsExcel){
    	
    	try { 
			List<AfterSaleStatisticsExcel> list = afterSaleStatisticsService.exportExcel(afterSaleStatisticsExcel);
			if(list != null && list.size() > 0){      
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "售后服务机品质分析报表模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"售后服务机品质分析报表.xlsx",list,2,0,5);
			 }
		} catch (Exception e) {  
			e.printStackTrace();
		}
    }
    
}
