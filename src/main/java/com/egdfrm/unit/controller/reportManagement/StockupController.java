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
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.StockupExcel; 
import com.egdfrm.unit.service.board.IToStockingService;

/**
 * 待备货导出
 * @author hgb
 * @date 2017-3-21
 */
@Controller
@RequestMapping("stockupController")
public class StockupController extends BaseController{
	 @Autowired
	 private IToStockingService iToStockingService;
	 
    @RequestMapping("init")
    public String init() {
        return "unit/reportManagement/stocking_up";
    }  
    /**
     * 待备货分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("getStockupList")
    @ResponseBody
    public String getStockupList(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, Pagination page){
    	  Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
          page = iToStockingService.findPage(pagination, user.getOrgId().intValue());
          return JSON.toJSONString(page);
    }
    
    
    /**
     *  导出备货
     */
    @RequestMapping("exportStockupList")
    @ResponseBody
    public void exportStockupList(HttpServletRequest request,HttpServletResponse response,
    		@CurrentLoginInfo UserAuthenRealm.ShiroUser user){
    	
    	try {
			List<StockupExcel> list = iToStockingService.findPageExcel(user.getOrgId().intValue());
			if(list != null && list.size() > 0){
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "待备货模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"待备货.xlsx",list,1,0,0);
			 }
		} catch (Exception e) { 
			e.printStackTrace();
		}
    }
    
}
