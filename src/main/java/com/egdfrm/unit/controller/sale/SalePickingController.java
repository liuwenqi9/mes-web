package com.egdfrm.unit.controller.sale;

import com.alibaba.fastjson.JSON;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.sale.Picking;
import com.egdfrm.unit.model.stock.Finished;
import com.egdfrm.unit.service.sale.ISalePickingService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 挑库检查-控制器
 * Created by tyq on 17/2/23.
 */
@Controller
@RequestMapping("picking")
public class SalePickingController {

    @Autowired
    private ISalePickingService iSalePickingService;

    /**
     * 跳转到挑库检查页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/sale/picking";
    }

    /**
     * 挑库检查分页查询
     * @param page 分页数据
     * @param pickingNum 挑库单
     * @param startDate 搬运单 开始日期
     * @param endDate 搬运单 结束日期
     * @return
     */
    @RequestMapping("queryPages")
    @ResponseBody
    public String queryPages(Pagination page,String pickingNum,String startDate,String endDate){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        Map<String,Object> map = new HashMap();
        map.put("pickingNum",pickingNum);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        pagination = iSalePickingService.queryPages(pagination,map);
        return JSON.toJSONString(pagination);
    }

    /**
     * 导出Excel
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String pickingNum,String startDate,String endDate){
        try {
            Map<String,Object> map = new HashMap();
            map.put("pickingNum",pickingNum);
            map.put("startDate",startDate);
            map.put("endDate",endDate);
            List<Picking> list = iSalePickingService.getAll(map);
            if(list != null && list.size() > 0){
                //获取XSSFWorkbook
                XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "挑库检查.xlsx");
                ExcelUtils.exportExcel(response,workBook,"挑库检查.xlsx",list,2,0,0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
