package com.egdfrm.extend.controller.mdcategory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.model.standard.MdCategoryV;
import com.egdfrm.extend.service.mdcategory.MdCategoryVService;
import com.egdfrm.core.common.Constants;
import com.egdfrm.core.common.JsonResult;
import com.egdfrm.core.controller.base.BaseController;

@Controller
@RequestMapping("mdcategory")
public class MdCategoryVController extends BaseController {
	@Autowired
	private MdCategoryVService mdcategoryService;
	
	public MdCategoryVController() {
	}
	
	@RequiresPermissions("mdcategoryController:view")
	@RequestMapping("getList")
	@ResponseBody
	public Map<String, Object> getList(int page, int rows) throws Exception {
		return mdcategoryService.getList(page, rows);
	}
	
	@RequestMapping("insertrow")
	@ResponseBody
	public JsonResult<String> insertrow(MdCategoryV record) {
		record.setCreateBy(new BigDecimal(1));
		record.setCreateDate(new Date());
		DbReturnParameter dbreturn = mdcategoryService.insertrow(record);
		if (!"S".equals(dbreturn.getxStatus()))
			return new JsonResult<String>(Constants.FAIL, dbreturn.getxMessage());
		return new JsonResult<String>(Constants.SUCCESS, null);
	}
	
	@RequestMapping("updaterow")
	@ResponseBody
	public JsonResult<String> update(MdCategoryV record) {
		record.setUpdateBy(new BigDecimal(1));
		record.setUpdateDate(new Date());
		DbReturnParameter dbreturn = mdcategoryService.updaterow(record);
		if (!"S".equals(dbreturn.getxStatus()))
			return new JsonResult<String>(Constants.FAIL, dbreturn.getxMessage());
		return new JsonResult<String>(Constants.SUCCESS, null);
	}
	
	@RequestMapping("deleterow")
	@ResponseBody
	public JsonResult<String> deleterow(BigDecimal categoryId) {
		DbReturnParameter dbreturn = mdcategoryService.deleterow(categoryId);
		if (!"S".equals(dbreturn.getxStatus()))
			return new JsonResult<String>(Constants.FAIL, dbreturn.getxMessage());
		return new JsonResult<String>(Constants.SUCCESS, null);
	}
	
	@RequestMapping("selectrow")
	@ResponseBody
	public MdCategoryV selectrow(BigDecimal categoryId) {
		return mdcategoryService.selectrow(categoryId);
	}
}
