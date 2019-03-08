package com.egdfrm.unit.controller.stock;

import com.alibaba.fastjson.JSON;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.stock.Finished;
import com.egdfrm.unit.service.stock.IFinishedService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 成品库存_控制器
 * Created by tyq on 17/1/16.
 */
@Controller
@RequestMapping("stock/finished")
public class FinishedController {

    @Autowired
    private IFinishedService iFinishedService;

    /**
     * 跳转到成品库存页面
     * @return
     */
    @RequestMapping(value = "init",method = RequestMethod.GET)
    public String init() {
        return "unit/stock/finished_manage";  
    }


    /**
     * 成品库存分页查询
     * @param page 分页条件
     * @param finished 查询条件
     * @return
     */
    @RequestMapping("findPage")
    @ResponseBody
    public String findPage(Pagination page, Finished finished){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iFinishedService.findPage(pagination,finished);
        return JSON.toJSONString(page);
    }

    /**
     * 子库分页查询
     * @param page 分页信息
     * @param id 子库ID
     * @return 分页集合
     */
    @RequestMapping("getZKByPage")
    @ResponseBody
    public String getZKByPage(Pagination page,String id){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iFinishedService.getZKByPage(pagination,id);
        return JSON.toJSONString(page);
    }

    /**
     * 货位分页查询
     * @param page 分页信息
     * @param id 货位ID
     * @param code 子库ID
     * @return 分页集合
     */
    @RequestMapping("getHWByPage")
    @ResponseBody
    public String getHWByPage(Pagination page,String id,String code){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iFinishedService.getHWByPage(pagination,id,code);
        return JSON.toJSONString(page);
    }

    /**
     * 导出Excel
     */
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,Finished finished){
        try {
            List<Finished> list = iFinishedService.getAll(finished);
            if(list != null && list.size() > 0){
                //获取XSSFWorkbook
                XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "成品库存模板.xlsx");
                ExcelUtils.exportExcel(response,workBook,"成品库存.xlsx",list,2,0,1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
