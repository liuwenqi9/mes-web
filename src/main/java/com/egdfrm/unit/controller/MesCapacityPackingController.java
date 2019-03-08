package com.egdfrm.unit.controller;
 
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.common.JsonResult;
import com.egdfrm.core.controller.base.BaseController;  
import com.egdfrm.core.security.annotation.CurrentUser;
import com.egdfrm.unit.model.standard.MesCapacityPacking;
import com.egdfrm.unit.service.MesCapacityPackingService;

@Controller
@RequestMapping("packagecontentController")
public class MesCapacityPackingController extends BaseController {
	
	@Autowired
	private MesCapacityPackingService service; 
	 
	
	/**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("packagecontentController:view")
    @RequestMapping("init")
    public String init() {
        return "unit/capacityPacking/capacityPackingMain";
    }

    /**
     * <p>
     * Description: 包装箱容量设置列表
     * </p>  
     * @param page 当前页
     * @param rows 每页行数
     * @param segment1 物料编号   
     * @return 包装箱容量设置列表
     * @throws Exception
     */
    @RequiresPermissions("packagecontentController:view")
    @RequestMapping("getMesCapacityPackingList")
    @ResponseBody
    public Map<String, Object> getMesCapacityPackingList(int page, int rows, String segment1) throws Exception {
        return this.service.getMesCapacityPackingList(page, rows, segment1);
    }
	
    /**
     * <p>
     * Description: 跳转到新增界面
     * </p>
     * 
     * @return 新增界面
     */
    @RequiresPermissions("packagecontentController:toMesCapacityPackingAdd")
    @RequestMapping("toMesCapacityPackingAdd")
    public String toMesCapacityPackingAdd() {
        return "unit/capacityPacking/capacityPackingAdd";
    }
    /**
     * <p>
     * Description: 跳转到物料选择界面
     * </p>
     * 
     * @return 物料选择界面
     */
   // @RequiresPermissions("packagecontentController:add")
    @RequestMapping("toSelect")
    public String toSelect() {
        return "unit/capacityPacking/select";
    }
    
     /**
      * 选择页面的数据
      * @param page 当前页
      * @param rows 每页行数
      * @param segment1 物料编号 
      * @param prodType 产品类型     
      * @return easyui 列表 分页数据
      * @throws Exception 
      */
    @RequiresPermissions("packagecontentController:view")
    @RequestMapping("getSegment1")
    @ResponseBody
    public Map<String, Object> getSegment1(int page, int rows, String segment1,String prodType) throws Exception{
    	 log.info("prodType:"+prodType);
    	 log.info("segment1:"+segment1);
    	return this.service.getSegment1(page, rows, segment1,prodType);
    }
     
    /**
     * 添加包装箱
     * @param loginName 当前登录名 
     * @param mcp model
     * @return x
     */
   // @RequiresPermissions("packagecontentController:addCapacityPacking")
    @RequestMapping("addCapacityPacking")
    @ResponseBody
    public JsonResult<String> addCapacityPacking(@CurrentUser String loginName,MesCapacityPacking mcp){
  	  return this.service.addCapacityPacking(loginName, mcp);
    }
    
    /**
     * 跳转去编辑页面
     * @return
     */
    @RequiresPermissions("packagecontentController:edit")
    @RequestMapping("toMesCapacityPackingEdit")   
    public String toMesCapacityPackingEdit(String HEADER_ID_EDIT, HttpServletRequest request){
    	return "unit/capacityPacking/capacityPackingEdit";
    }
     
    /**
     * <p>
     * Description: 跳转到物料选择界面
     * </p>
     * 
     * @return 物料选择界面
     */
   // @RequiresPermissions("packagecontentController:add")
    @RequestMapping("toSelectEdit")
    public String toSelectEdit() {
        return "unit/capacityPacking/selectEdit";
    } 
    /**
     * 编辑包装箱
     * @param loginName 当前登录名 
     * @param mcp model
     * @return x
     */
  //  @RequiresPermissions("packagecontentController:editCapacityPacking")
    @RequestMapping("editCapacityPacking")
    @ResponseBody
    public JsonResult<String>  editCapacityPacking(@CurrentUser String loginName,MesCapacityPacking mcp){
  	  return this.service.editCapacityPacking(loginName, mcp);
    } 
    
    
    /** 
     * @return 新入口页面
     */
    //@RequiresPermissions("packagecontentController:view")
    @RequestMapping("inits")
    public String inits() {
        return "unit/capacityPacking/capacityPacking";
    }
    
    @RequestMapping("getCapacityPackings")
    @ResponseBody
    public List<Map<String,Object>> getCapacityPackings(String segment1){ 
    	return this.service.getCapacityPackings(segment1);
    } 
    /**
     * 新版 选择物料页面数据
     * @param segment1
     * @param prodType
     * @return
     */
    @RequestMapping("getCapacityPackingselects")
    @ResponseBody
    public List<Map<String,Object>> getCapacityPackingselects(String segment1,String prodType){ 
    	return this.service.getCapacityPackingselects(segment1, prodType);
    } 
    /**
     * 删除 true成功
     */
   @RequestMapping("deleteCapacityPacking")
    @ResponseBody
    public boolean deleteCapacityPacking(String headerId){
    	return this.service.deleteCapacityPacking(headerId);
   }
    
}
