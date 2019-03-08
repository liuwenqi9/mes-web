package com.egdfrm.unit.controller.sale;

import com.alibaba.fastjson.JSON;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.PickingExcelModel;
import com.egdfrm.unit.service.sale.IPickingCheckService;

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
 * 挑库拼箱检查
 * Created by tyq on 17/2/14.
 */
@Controller
@RequestMapping("picking/check")
public class PickingCheckController {

    @Autowired
    private IPickingCheckService iPickingCheckService;

    /**
     * 跳转到挑库拼箱检查管理页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/sale/picking_check";  
    }


    /**
     * 挑库拼箱检查查询
     * @param page 分页数据
     * @param pickingNum 挑库单
     * @param checkType 检查类型
     * @return 数据集
     */
    @RequestMapping("queryPages")
    @ResponseBody
    public String queryPages(Pagination page,String pickingNum,String checkType){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap();
        map.put("pickingNum",pickingNum);
        map.put("checkType",checkType);
        pagination = iPickingCheckService.queryPages(pagination,map);
        return JSON.toJSONString(pagination);
    }
    
   /**
    * 挑库拼箱检查  导出excel
    * @param page
    * @param pickingNum
    * @param checkType
    * @return   
    * @author	hgb
    * @date 2017-5-3
    */
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,String pickingNum,String checkType){
        
        try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("pickingNum",pickingNum);
			map.put("checkType",checkType);
			List<PickingExcelModel> list =  iPickingCheckService.exportExcel(map);
			if(list != null && list.size() > 0){
			    //获取XSSFWorkbook
			    XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "挑库拼箱检查模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"挑库拼箱检查.xlsx",list,2,0,0);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
        
    }
    
    
}
