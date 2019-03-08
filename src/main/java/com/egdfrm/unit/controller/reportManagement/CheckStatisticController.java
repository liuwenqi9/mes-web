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
import com.egdfrm.unit.model.stock.CheckStatisticsExcel;
import com.egdfrm.unit.service.reportManagement.CheckStatisticService;

/**
 * 检验统计日 报表
 * @author hgb
 * @date 2017-06-12
 */
@Controller()
@RequestMapping("checkStatisticController")
public class CheckStatisticController extends BaseController{

	@Autowired
	private CheckStatisticService checkStatisticService;
	
	@RequestMapping("init")
    public String init() {
        return "unit/reportManagement/checkStatistic";
    }
	
	
	/**
     * 分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("listByPage")    
    @ResponseBody
    public String listByPage(Pagination page,CheckStatisticsExcel checkStatisticsExcel){
    	  Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
    	  page = checkStatisticService.listByPage(pagination, checkStatisticsExcel);
          return JSON.toJSONString(page);
    }
     
    /**
     *  导出检验统计日报表
     */
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,CheckStatisticsExcel checkStatisticsExcel){
    	
    	try {
			List<CheckStatisticsExcel> list = checkStatisticService.exportExcel(checkStatisticsExcel);
			if(list != null && list.size() > 0){
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "检验统计日报表模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"检验统计日报表.xlsx",list,2,0,3);
			 }
		} catch (Exception e) {  
			e.printStackTrace();
		}
    }
    
}
