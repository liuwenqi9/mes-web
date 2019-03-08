package com.egdfrm.unit.controller.reportManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.Storage;
import com.egdfrm.unit.model.report.Saot;
import com.egdfrm.unit.service.reportManagement.ISaotService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 借机统计报表
 * @author hgb
 * @date 2017-5-12
 */
@Controller
@RequestMapping("saot")
public class SaotReportController extends BaseController{

    @Autowired
    private ISaotService iSaotService;

    /**
     * 跳转到借机统计报表页面
     * @return
     */ 
    @RequestMapping("init")
    public String init(){
        return "unit/reportManagement/saot_report";
    }


    /**
     * 借机统计查询
     * @param page 分页信息
     * @param saot
     * @return
     */
    @RequestMapping("findPage")
    @ResponseBody
    public String findPage(Pagination page, Saot saot){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        pagination = iSaotService.findPage(pagination,saot);
        return JSON.toJSONString(pagination);
    }

    /**
     * 导出Excel
     * @param request
     * @param response
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, Saot saot){
        try {
            List<Saot> list = iSaotService.finAll(saot);
            //获取XSSFWorkbook
            XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "借机报表模板.xlsx");
            ExcelUtils.exportExcel(response,workBook,"借机报表.xlsx",list,2,0,5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
