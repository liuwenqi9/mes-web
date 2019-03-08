package com.egdfrm.unit.controller.barcodeManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.BackPageExcel;
import com.egdfrm.unit.excelmodel.BasicPageExcel;
import com.egdfrm.unit.excelmodel.MiscellaneousPageExcel;
import com.egdfrm.unit.excelmodel.RepairPageExcel;
import com.egdfrm.unit.excelmodel.ShipmentPageExcel;
import com.egdfrm.unit.service.barcodeManagement.IInfoReviewService;

import org.apache.http.HttpRequest;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 条码信息追溯-控制器
 * Created by tyq on 17/2/20.
 */
@Controller
@RequestMapping("/info/review")
public class InfoReviewController {

    @Autowired
    private IInfoReviewService iInfoReviewService;

    /**
     * 跳转到条码信息追溯页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/barcodeManagement/inforeview_manage";
    }


    /**
     * 基本信息查询
     * @param page 分页条件
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("basicPage")
    @ResponseBody
    public String basicPage(Pagination page,String barcodeNum,String workOrderNumber,String moNumber){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap<>();
        map.put("barcodeNum",barcodeNum);
        map.put("workOrderNumber",workOrderNumber);
        map.put("moNumber",moNumber);
        page = iInfoReviewService.getBasicPage(pagination,map);
        return JSON.toJSONString(page);
    }

    /**
     * 基本信息 导出excel 
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("basicPageExportExcel") 
    public void basicPageExportExcel(HttpServletRequest request, HttpServletResponse response,
    		String barcodeNum,String workOrderNumber,String moNumber){  
    	try {
			Map<String,Object> map = new HashMap<>();
			map.put("barcodeNum",barcodeNum);
			map.put("workOrderNumber",workOrderNumber);
			map.put("moNumber",moNumber);
			List<BasicPageExcel> list = iInfoReviewService.basicPageExportExcel(map); 
			if(list != null && list.size() > 0){
			    //获取XSSFWorkbook
			    XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "基本信息模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"基本信息.xlsx",list,2,0,0);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
    }
    
    
    /**
     * 出货信息查询
     * @param page 分页条件
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("shipmentPage")
    @ResponseBody
    public String shipmentPage(Pagination page,String barcodeNum,String workOrderNumber,String moNumber){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap<>();
        map.put("barcodeNum",barcodeNum);
        map.put("workOrderNumber",workOrderNumber);
        map.put("moNumber",moNumber);
        page = iInfoReviewService.getShipmentPage(pagination,map);
        return JSON.toJSONString(page);
    }

    /**
     * 出货信息 导出excel 
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("shipmentPageExportExcel") 
    public void shipmentPageExportExcel(HttpServletRequest request, HttpServletResponse response,
    		String barcodeNum,String workOrderNumber,String moNumber){  
    	try {
			Map<String,Object> map = new HashMap<>();
			map.put("barcodeNum",barcodeNum);
			map.put("workOrderNumber",workOrderNumber);
			map.put("moNumber",moNumber);
			List<ShipmentPageExcel> list = iInfoReviewService.shipmentPageExportExcel(map);
			if(list != null && list.size() > 0){
			    //获取XSSFWorkbook
			    XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "出货信息模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"出货信息.xlsx",list,2,0,0);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
    }
    
    /**
     * 退货信息查询
     * @param page 分页条件
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("backPage")
    @ResponseBody
    public String backPage(Pagination page,String barcodeNum,String workOrderNumber,String moNumber){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap<>();
        map.put("barcodeNum",barcodeNum);
        map.put("workOrderNumber",workOrderNumber);
        map.put("moNumber",moNumber);
        page = iInfoReviewService.getBackPage(pagination,map);
        return JSON.toJSONString(page);
    }

    /**
     * 退货货信息 导出excel 
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("backPageExportExcel") 
    public void backPageExportExcel(HttpServletRequest request, HttpServletResponse response,
    		String barcodeNum,String workOrderNumber,String moNumber){  
    	try {
			Map<String,Object> map = new HashMap<>();
			map.put("barcodeNum",barcodeNum);
			map.put("workOrderNumber",workOrderNumber);
			map.put("moNumber",moNumber);
			List<BackPageExcel> list = iInfoReviewService.backPageExportExcel(map);
			if(list != null && list.size() > 0){
			    //获取XSSFWorkbook
			    XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "退货信息模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"退货信息.xlsx",list,2,0,0);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
    }
    
    /**
     * 返修信息查询
     * @param page 分页条件
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("repairPage")
    @ResponseBody
    public String repairPage(Pagination page,String barcodeNum,String workOrderNumber,String moNumber){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap<>();
        map.put("barcodeNum",barcodeNum);
        map.put("workOrderNumber",workOrderNumber);
        map.put("moNumber",moNumber);
        page = iInfoReviewService.getRepairPage(pagination,map);
        return JSON.toJSONString(page);
    }

    /**
     * 返修信息 导出excel 
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("repairPageExportExcel") 
    public void repairPageExportExcel(HttpServletRequest request, HttpServletResponse response,
    		String barcodeNum,String workOrderNumber,String moNumber){  
    	try {
			Map<String,Object> map = new HashMap<>();
			map.put("barcodeNum",barcodeNum);
			map.put("workOrderNumber",workOrderNumber);
			map.put("moNumber",moNumber);
			List<RepairPageExcel> list = iInfoReviewService.repairPageExportExcel(map);
			if(list != null && list.size() > 0){
			    //获取XSSFWorkbook
			    XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "返修信息模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"返修信息.xlsx",list,2,0,0);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
    }
    
    /**
     * 杂项交易查询
     * @param page 分页条件
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("miscellaneousPage")
    @ResponseBody
    public String miscellaneousPage(Pagination page,String barcodeNum,String workOrderNumber,String moNumber){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap<>();
        map.put("barcodeNum",barcodeNum);
        map.put("workOrderNumber",workOrderNumber);
        map.put("moNumber",moNumber);
        page = iInfoReviewService.getMiscellaneousPage(pagination,map);
        return JSON.toJSONString(page);
    }
    /**
     *  杂项交易 导出excel 
     * @param barcodeNum 产品条码
     * @param workOrderNumber 工单号
     * @param moNumber MO单
     * @return
     */
    @RequestMapping("miscellaneousPageExportExcel") 
    public void miscellaneousPageExportExcel(HttpServletRequest request, HttpServletResponse response,
    		String barcodeNum,String workOrderNumber,String moNumber){  
    	try {
			Map<String,Object> map = new HashMap<>();
			map.put("barcodeNum",barcodeNum);
			map.put("workOrderNumber",workOrderNumber);
			map.put("moNumber",moNumber);
			List<MiscellaneousPageExcel> list = iInfoReviewService.miscellaneousPageExportExcel(map);
			if(list != null && list.size() > 0){
			    //获取XSSFWorkbook
			    XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "杂项交易模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"杂项交易 .xlsx",list,2,0,0);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} 
    }
}
