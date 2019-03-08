package com.egdfrm.unit.controller.reportManagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.egdfrm.unit.excelmodel.StockSendExcel;
import com.egdfrm.unit.service.board.IToDeliveryService;
import com.egdfrm.unit.service.board.IToStockingService;

/**
 * 待发货导出
 * @author hgb
 * @date 2017-3-21
 */
@Controller
@RequestMapping("stockingSendController")
public class StockingSendController extends BaseController{
	@Autowired
    private IToDeliveryService iToDeliveryService;
	 
    @RequestMapping("init")
    public String init() {
        return "unit/reportManagement/stocking_send";
    }  
    /**
     * 待发货分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("getStockingSendList")
    @ResponseBody
    public String getStockingSendList(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, Pagination page){
    	  Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
          page = iToDeliveryService.findPage(pagination,user.getOrgId().intValue());
          return JSON.toJSONString(page);
    }
    
    
    /**
     *  导出待发货
     */
    @RequestMapping("exportStockingSendList")
    @ResponseBody
    public void exportStockingSendList(HttpServletRequest request,HttpServletResponse response,
    		@CurrentLoginInfo UserAuthenRealm.ShiroUser user){
    	
    	try {
			List<StockSendExcel> list = iToDeliveryService.findPageExcel(user.getOrgId().intValue());
			if(list != null && list.size() > 0){
			     //获取XSSFWorkbook
			     XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "待发货模板.xlsx");
			     ExcelUtils.exportExcel(response,workBook,"待发货.xlsx",list,1,0,0);
			 }
		} catch (Exception e) { 
			e.printStackTrace();
		}
    }
    
}
