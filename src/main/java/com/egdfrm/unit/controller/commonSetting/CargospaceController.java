package com.egdfrm.unit.controller.commonSetting;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.common.JsonResult;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.model.standard.MesItemLocattions;
import com.egdfrm.unit.service.commonSetting.CargospaceService;

/** 
 * <p>
 * Description: 货位管理controller
 * </p>
 * <p>
 * Author: sjf
 * </p>
 * <p>
 * Date: 2016年12月30日
 * </p>
 */
@Controller
@RequestMapping("cargospaceController")
public class CargospaceController extends BaseController {
	
	/**
     * <p>
     * Field cs: 货位管理service
     * </p>
     */
    @Autowired
    private CargospaceService cs;
	
	/**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("cargospaceController:view")
    @RequestMapping("init")
    public String init() {
        return "unit/commonSetting/cargospace";
    }  
    /**
     * @author sjf
     * @date 2017年1月3日 
     * @return
     * @throws Exception
     * 货位查询
     */
   // @RequiresPermissions("cargospaceController:view")
    @RequestMapping("searchCargospace")
    @ResponseBody
    public List<Map<String, Object>> searchCargospace(String sublibraryName,String cargospaceName) throws Exception  {
    	return cs.searchCargospace(sublibraryName,cargospaceName);
    }
    /**
     * @author sjf
     * @date 2017年1月4日 
     * @return
     * @throws Exception
     * 货位新增保存
     */	
    @RequiresPermissions("cargospaceController:add")
    @RequestMapping("addCargospace")
    @ResponseBody
    public JsonResult<String> addCargospace(@CurrentLoginInfo ShiroUser su,MesItemLocattions mil) throws Exception  {
    	return cs.addCargospace(su,mil);
    }
    /**
     * @author sjf
     * @date 2017年1月5日 
     * @param loginName
     * @param mil
     * @return
     * @throws Exception
     * 货位修改保存
     */
    @RequiresPermissions("cargospaceController:edit")
    @RequestMapping("editCargospace")
    @ResponseBody
    public JsonResult<String> editCargospace(@CurrentLoginInfo ShiroUser su,MesItemLocattions mil) throws Exception  {
    	return cs.editCargospace(su,mil);
    }
    
}
