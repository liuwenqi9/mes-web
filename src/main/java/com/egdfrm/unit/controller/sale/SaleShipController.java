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
import com.egdfrm.unit.excelmodel.SaleShipExcel;
import com.egdfrm.unit.service.sale.SaleShipService;

/*
 * 销售发运条码查询--控制器
 */
@Controller
@RequestMapping("saleShipController")
public class SaleShipController extends BaseController {
	
	@Autowired private SaleShipService saleShipService;
	 
    @RequestMapping("init")
    public String init(){
        return "unit/sale/saleShip";
    }
    
    @RequestMapping("getSaleShipLists")
    @ResponseBody
    public String getSaleShipLists(Pagination page,String pickNum,String sourceHeaderNumber
    		,String productBarcode,String customerName,String startDate,String endDate,String segment1){
    	if(startDate!=null&&!startDate.equals(""))
    		startDate +=" 00:00:00";
    	if(endDate!=null&&!endDate.equals(""))
    		endDate +=" 23:59:59";
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pickNum",pickNum);
        map.put("sourceHeaderNumber",sourceHeaderNumber);
        map.put("productBarcode",productBarcode);
        map.put("customerName",customerName);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("segment1",segment1);
        pagination = saleShipService.getSaleShipLists(pagination, map);
        return JSON.toJSONString(pagination);
    }
    
    @RequestMapping("saleShipExportExcel")
    public void saleShipExportExcel(HttpServletRequest request,HttpServletResponse response,String pickNum
    		,String sourceHeaderNumber,String productBarcode,String customerName,String startDate,String endDate,String segment1){ 
    	try {
    		if(startDate!=null&&!startDate.equals(""))
        		startDate +=" 00:00:00";
        	if(endDate!=null&&!endDate.equals(""))
        		endDate +=" 23:59:59";
        	Map<String,Object> map = new HashMap<String, Object>();
            map.put("pickNum",pickNum);
            map.put("sourceHeaderNumber",sourceHeaderNumber);
            map.put("productBarcode",productBarcode);
            map.put("customerName",customerName);
            map.put("startDate",startDate);
            map.put("endDate",endDate);
            map.put("segment1",segment1);
            List<SaleShipExcel> list = saleShipService.saleShipExportExcel(map);
            if(list != null && list.size() > 0){ 
                //获取XSSFWorkbook
                XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "挑库发运条码模板.xlsx");
                ExcelUtils.exportExcel(response,workBook,"挑库发运条码.xlsx",list,2,0,0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    	
    }
}
