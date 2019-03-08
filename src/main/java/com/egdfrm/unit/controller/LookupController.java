/**
 * LookupController.java
 * Created at 2016-12-01
 * Created by 兰继明
 * Copyright (C) unit
 */
package com.egdfrm.unit.controller;

import com.egdfrm.core.controller.base.BaseController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.common.JsonResult;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.security.annotation.CurrentUser;
import com.egdfrm.core.service.user.UserService;
import com.egdfrm.unit.model.standard.MesLookups;
import com.egdfrm.unit.model.standard.MesLookupsType;
import com.egdfrm.unit.service.LookupService;

/**
 * <p>
 * ClassName: LookupController
 * </p>
 * <p>
 * Description: 数据字典设置
 * </p>
 * <p>
 * Author: 兰继明兰继明
 * </p>
 * <p>
 * Date: 2016年12月01日
 * </p>
 */
@Controller
@RequestMapping("lookupController")
public class LookupController extends BaseController {
	
	/**
     * <p>
     * Field us: 数据字典服务
     * </p>
     */
    @Autowired
    private LookupService ls;
	
	/**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("lookupController:view")
    @RequestMapping("init")
    public String init() {
        return "unit/commonSetting/dataDictionary/lookupMain";
    }
    
    /**
     * <p>
     * Description: 初始化方法(bootstrap界面版本)
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("lookupController:view")
    @RequestMapping("toLookupTypeList")
    public String toLookupTypeList() {
        return "unit/commonSetting/dataDictionary/lookup";
    }
    
    /**
     * <p>
     * Description: 获取数据字典类型列表
     * </p>
     * 
     * @return 
     */
    @RequiresPermissions("lookupController:view")
    @RequestMapping("getLookupTypeList")
    @ResponseBody
    public Map<String, Object> getLookupTypeList(int page,int rows,String lookupType) throws Exception {
    	
    	return ls.getLookupTypeList(page,rows,lookupType);
    }
    /**
     * <p>
     * Description: 获取数据字典列表
     * </p>
     * 
     * @return 
     */
    @RequiresPermissions("lookupController:view")
    @RequestMapping("getLookupList")
    @ResponseBody
    public List<Map<String, Object>> getLookupList(String lookupType) throws Exception  {
    	
    	return ls.getLookupList(lookupType);
    }
    /**
     * <p>
     * Description: 获取单条数据字典
     * </p>
     * 
     * @return 
     */
    @RequiresPermissions("lookupController:view")
    @RequestMapping("getLookup")
    @ResponseBody
    public List<Map<String, Object>> getLookup(String lookupCode) throws Exception  {
    	
    	return ls.getLookup(lookupCode);
    }
    /**
     * <p>
     * Description: 增加数据字典类型
     * </p>
     * 
     * @return 
     */
    @RequiresPermissions("lookupController:addLookupType")
    @RequestMapping("addLookupType")
    @ResponseBody
    public JsonResult<String> addLookupType(MesLookupsType mesLookupsType) throws Exception  {
    	
    	return ls.addLookupType(mesLookupsType);
    }
    /**
     * <p>
     * Description: 跳转到数据字典类型新增界面
     * </p>
     * 
     * @return 数据字典类型新增界面
     */
    @RequiresPermissions("lookupController:addLookupType")
    @RequestMapping("toAddLookupType")
    public String toAddLookupType() {
        return "unit/commonSetting/dataDictionary/addLookupType";
    }

    /**
     * <p>
     * Description: 校验数据字典类型名称是否唯一
     * </p>
     * 
     * @param lookupType 校验数据字典类型名
     * @return true:通过,false:不通过
     */
    @RequiresPermissions("lookupController:addLookupType")
    @RequestMapping("lookupTypeUniqueCheck")
    @ResponseBody
    public boolean lookupTypeUniqueCheck(String lookupType) {
        return this.ls.lookupTypeUniqueCheck(lookupType);
    }
    
    /**
     * <p>
     * Description: 更新数据字典类型
     * </p>
     * 
     */
    @RequiresPermissions("lookupController:editLookupType")
    @RequestMapping("editLookupType")
    @ResponseBody
    public JsonResult<String> editLookupType(MesLookupsType mesLookupsType) {
    	return this.ls.editLookupType( mesLookupsType);
    }
    
    /**
     * <p>
     * Description: 跳转到数据字典类型更新界面
     * </p>
     * 
     * @return 数据字典类型更新界面
     */
    @RequiresPermissions("lookupController:editLookupType")
    @RequestMapping("toEditLookupType")
    public String toEditLookupType(String lookupType, HttpServletRequest request) {
    	request.setAttribute("lookupType", lookupType);
        return "unit/commonSetting/dataDictionary/editLookupType";
    }
    
    /**
     * <p>
     * Description: 跳转到数据字典新增界面
     * </p>
     * 
     * @return 数据字典新增界面
     */
    @RequiresPermissions("lookupController:addLookup")
    @RequestMapping("toAddLookup")
    public String toAddLookup() {
        return "unit/commonSetting/dataDictionary/addLookup";
    }
    /**
     * <p>
     * Description: 新增数据字典
     * </p>
     * 
     */
    @RequiresPermissions("lookupController:addLookup")
    @RequestMapping("addLookup")
    @ResponseBody
    public JsonResult<String> addLookup(MesLookups lookup) throws Exception {
    	return ls.addLookup(lookup);
    }
    /**
     * <p>
     * Description: 编辑数据字典
     * </p>
     * 
     */
    @RequiresPermissions("lookupController:editLookup")
    @RequestMapping("editLookup")
    @ResponseBody
    public JsonResult<String> editLookup(MesLookups lookup) throws Exception {
    	return ls.editLookup(lookup);
    }
}
