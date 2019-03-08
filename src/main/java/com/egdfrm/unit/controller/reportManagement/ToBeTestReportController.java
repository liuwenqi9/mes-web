package com.egdfrm.unit.controller.reportManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.TobeTest;
import com.egdfrm.unit.service.board.ITobeTestService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 待检验报表_控制器
 * Created by tyq on 17/3/21.
 */
@Controller
@RequestMapping("to/be/test/report")
public class ToBeTestReportController {


    @Autowired
    private ITobeTestService iTobeTestService;

    /**
     * 跳转到待检验报表页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/reportManagement/toBeTest_report";
    }
    
    
    
    /**
     * 待检验分页查询 报表
     * @param page 分页条件
     * @return
     */
    @RequestMapping("findPage2")
    @ResponseBody
    public String findPage2(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, Pagination page){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iTobeTestService.findPage2(pagination,user.getOrgId().intValue());
        return JSON.toJSONString(page);
    }
    
    
    /**
     * 导出Excel
     * @param request
     * @param response
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,@CurrentLoginInfo UserAuthenRealm.ShiroUser user){
        try {
            List<TobeTest> list = iTobeTestService.finAll(user.getOrgId().intValue());
            //获取XSSFWorkbook
            XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "待检验报表模板.xlsx");
            ExcelUtils.exportExcel(response,workBook,"待检验.xlsx",list,2,0,1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
