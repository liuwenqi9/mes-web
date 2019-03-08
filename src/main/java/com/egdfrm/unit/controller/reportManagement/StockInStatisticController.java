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
import com.egdfrm.unit.model.stock.StockInStatisticsExcel;
import com.egdfrm.unit.service.reportManagement.StockInStatisticService;

/**
 * 入库统计日 报表
 * @author hgb
 * @date 2017-5-10
 */
@Controller()
@RequestMapping("stockInStatisticController")
public class StockInStatisticController extends BaseController{

	@Autowired
	private StockInStatisticService stockInStatisticService;
	
	@RequestMapping("init")
    public String init() {
        return "unit/reportManagement/stockInStatistic";
    }
	
	
	/**
     * 分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("listByPage")    
    @ResponseBody
    public String listByPage(Pagination page,StockInStatisticsExcel stockInStatisticsExcel){
    	  Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
    	  page = stockInStatisticService.listByPage(pagination, stockInStatisticsExcel);
          return JSON.toJSONString(page);
    }
     
    /**
     *  导出入库日统计报表
     */
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,StockInStatisticsExcel stockInStatisticsExcel){
    	
    	try {
			List<StockInStatisticsExcel> list = stockInStatisticService.exportExcel(stockInStatisticsExcel);
			if(list != null && list.size() > 0){
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "入库统计日报表模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"入库统计日报表.xlsx",list,2,0,3);
			 }
		} catch (Exception e) {  
			e.printStackTrace();
		}
    }
    
}
