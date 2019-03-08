package com.egdfrm.unit.controller.reportManagement;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.model.report.DailyStatistics;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.unit.service.reportManagement.ProductionStatisticsDaliyReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * <p>
 * Description: 生产统计日报表controller
 * </p>
 * <p>
 * Author: sjf
 * </p>
 * <p>
 * Date: 2016年12月30日
 * </p>
 */ 
@Controller
@RequestMapping("productionStatisticsDaliyReportController")
public class ProductionStatisticsDaliyReportController extends BaseController {
	
	/**
     * <p>
     * Field ps: 生产统计日报表service
     * </p>
     */
    @Autowired
    private ProductionStatisticsDaliyReportService ps;
	
	/**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("productionStatisticsDaliyReportController:view")
    @RequestMapping("init")
    public String init() {
        return "unit/reportManagement/productionStatisticsDaliyReport";
    }  

    /**
     * @author sjf
     * @date 2017年1月6日 
     * @return
     * 获取生产线列表(多个地方使用)
     */
   // @RequiresPermissions("productionStatisticsDaliyReportController:view")
    @RequestMapping("getPlanLines")
    @ResponseBody
    public List<Map<String, Object>> getPlanLines() {
    	return ps.getPlanLines();
    }  
    
    /**
     * @author sjf
     * @date 2017年1月3日 
     * @return
     * @throws Exception
     * 查询生产统计日报表
     */
    @RequiresPermissions("productionStatisticsDaliyReportController:view")
    @RequestMapping("searchProductionStatistics")
    @ResponseBody
    public List<Map<String, Object>> searchProductionStatistics(String productionLine,Date dateFrom,Date dateTo) throws Exception  {
    	return ps.searchProductionStatistics(productionLine,dateFrom,dateTo);
    } 
    /**
     * @author sjf
     * @date 2017年1月9日 
     * @param productionLine
     * @param dateFrom
     * @param dateTo
     * @return
     * @throws Exception
     * 导出excel-服务器生成文件
     */
    @RequiresPermissions("productionStatisticsDaliyReportController:export")
    @RequestMapping("exportProductionStatistics")
    @ResponseBody
    public Map<String, Object> exportProductionStatistics(String productionLine,Date dateFrom,Date dateTo) throws Exception  {
    	return ps.exportProductionStatistics(productionLine,dateFrom,dateTo);
    } 

    /**
     * @author sjf
     * @date 2017年1月9日 
     * @param fileName
     * @return
     * @throws Exception
     * 下载服务器文件到本地
     */
    @RequiresPermissions("productionStatisticsDaliyReportController:export")
    @RequestMapping("downloadProductionStatistics")
    @ResponseBody
    public ResponseEntity<byte[]> downloadProductionStatistics(String fileName) throws Exception  {
    	return ps.downloadProductionStatistics(fileName);
    }

    /**
     * 导出EXCEL
     * @param response
     * @param proLine 生产线
     * @param startTime 开始日期
     * @param endTime 结束日期
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request,HttpServletResponse response, String proLine, String startTime, String endTime){
    	proLine = proLine.equals("0")?"":proLine;
    	//生产统计日报表_数据查询
        List<DailyStatistics> list = ps.queryDailyStatistics(proLine,startTime,endTime);
        //获取XSSFWorkbook
        XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "生产统计日报表.xlsx");
        ExcelUtils.exportExcel(response,workBook,"生产统计日报表.xlsx",list,2,0,0);
    }
}
