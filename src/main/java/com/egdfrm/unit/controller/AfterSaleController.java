package com.egdfrm.unit.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.unit.model.standard.MesAfterSale;
import com.egdfrm.unit.service.LookupService;
import com.egdfrm.unit.service.pda.AfterSaleService;


/**
 * <p>
 * ClassName: LookupController
 * </p>
 * <p>
 * Description: 售后服务
 * </p>
 * <p>
 * Author: 兰继明
 * </p>
 * <p>
 * Date: 2016年12月21日
 * </p>
 */
@Controller
@RequestMapping("afterSaleController")
public class AfterSaleController extends BaseController {
	
	/**
     * <p>
     * Field us: 售后服务服务
     * </p>
     */
    @Autowired
    private AfterSaleService afterSaleService;
	
	
	/**
     * <p>
     * Description: 初始化方法(bootstrap界面版本)
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("afterSaleController:view")
    // TODO 测试权限
    @RequestMapping("toAfterSaleList")
    public String toAfterSaleList() {
        return "unit/afterSale/afterSaleManage/afterSale";
    }
    //getAfterSaleList
    /**
     * <p>
     * Description: 获取售后退货/发货列表
     * </p>
     * 
     * @return 
     */
    @RequiresPermissions("afterSaleController:view")
    @RequestMapping("getAfterSaleList")
    @ResponseBody
    public Map<String, Object> getAfterSaleList(int limit,int offset,String expressNo,String status,String timeType,String productBarcode,String time_from,String time_to) throws Exception {
    	
    	return afterSaleService.getAfterSaleList(limit,offset,expressNo,status,timeType,productBarcode,time_from,time_to);
    }
}
