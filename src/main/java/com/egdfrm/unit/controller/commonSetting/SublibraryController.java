package com.egdfrm.unit.controller.commonSetting;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.unit.service.commonSetting.SublibraryService;

/** 
 * <p>
 * Description: 子库一览controller
 * </p>
 * <p>
 * Author: sjf
 * </p>
 * <p>
 * Date: 2016年12月30日
 * </p>
 */
@Controller
@RequestMapping("sublibraryController")
public class SublibraryController extends BaseController {
	
	/**
     * <p>
     * Field ss: 子库service
     * </p>
     */
    @Autowired
    private SublibraryService ss;
	
	/**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("sublibraryController:view")
    @RequestMapping("init")
    public String init() {
        return "unit/commonSetting/sublibrary";
    }  

    /**
     * @author sjf
     * @date 2017年1月3日 
     * @return
     * @throws Exception
     * 子库查询
     */
   // @RequiresPermissions("sublibraryController:view")
    @RequestMapping("searchSublibrary")
    @ResponseBody
    public List<Map<String, Object>> searchSublibrary(String sublibraryName) throws Exception  {
    	
    	return ss.searchSublibrary(sublibraryName);
    }
    
}
