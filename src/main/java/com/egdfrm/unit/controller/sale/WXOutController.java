package com.egdfrm.unit.controller.sale;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.egdfrm.unit.excelmodel.WXOutExcel;
import com.egdfrm.unit.service.sale.WXOutService;

/*
 * 外销出货通知单--控制器
 */
@Controller
@RequestMapping("wXOutController")
public class WXOutController extends BaseController {
	
	@Autowired private WXOutService wXOutService;
	 
    @RequestMapping("init")
    public String init(){
        return "unit/sale/wXOut";
    }
    
    @RequestMapping("getwXOutpLists")
    @ResponseBody
    public String getwXOutpLists(Pagination page,String document_num
    		,String cust_po_number,String start_date,String end_date ){ 
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap<String, Object>(); 
        map.put("document_num",document_num);//出货通知单
        map.put("cust_po_number",cust_po_number);//MO单 
        map.put("start_date",start_date);//计划出货开始日期
        map.put("end_date",end_date);//计划出货结束日期 
        pagination = wXOutService.getWXOutLists(pagination, map);
        return JSON.toJSONString(pagination);
    }
    
    @RequestMapping("wXOutExportExcel")
    public void wXOutExportExcel(HttpServletRequest request,HttpServletResponse response
    		,String document_num,String cust_po_number,String start_date,String end_date){ 
    	try { 
    		 
        	Map<String,Object> map = new HashMap<String, Object>();
        	map.put("document_num",document_num);//出货通知单
            map.put("cust_po_number",cust_po_number);//MO单
            map.put("start_date",start_date);//计划出货开始日期
            map.put("end_date",end_date);//计划出货结束日期 
            List<WXOutExcel> list = wXOutService.wXOutExportExcel(map);
            if(list != null && list.size() > 0){ 
                //获取XSSFWorkbook
                XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "外销出货通知单模板.xlsx");
                ExcelUtils.exportExcel(response,workBook,"外销出货通知单.xlsx",list,2,0,0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    	
    }
}
