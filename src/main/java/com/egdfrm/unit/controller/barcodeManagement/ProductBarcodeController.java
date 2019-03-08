package com.egdfrm.unit.controller.barcodeManagement;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.common.WriteForPDF;
import com.egdfrm.unit.service.barcodeManagement.IProductBarcodeService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;

/**
 * 产品条码管理——控制器
 * @author hgb
 * @date 2016-12-21
 */
@Controller
@RequestMapping("productBarcodeController")
public class ProductBarcodeController extends BaseController{
	
	@Autowired
	private IProductBarcodeService iProductBarcodeService;

	/**
	 * 跳转到条码管理页面
	 * @return
     */
    @RequiresPermissions("productBarcodeController:view")
	@RequestMapping("init")
	public String init(Model model){
		List<String> barcodeStatus = iProductBarcodeService.getBarcodeStatus();
		model.addAttribute("codeStatus",barcodeStatus);
		return "unit/barcodeManagement/productBarcode";
	}

	/**
	 * 获取产品条码列表
	 * @param page 分页数据
	 * @param workOrderNumber 工单
	 * @param productBarcode 产品条码
	 * @date 修改于 2017-04-20
     * @return
     */
    @RequiresPermissions("productBarcodeController:view")
	@RequestMapping("getProductBarcode")
	@ResponseBody
	public String getProductBarcode(Pagination page, String workOrderNumber, String productBarcode,String workStatus,String printFlag){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit()); 
		Map<String,Object>  map = new HashMap<>();
		map.put("workOrderNumber",workOrderNumber);
		if(productBarcode.length()>18&&productBarcode.length()<25){//说明是产品码时区间查询
			String[] prodCode = productBarcode.split("-"); 
			String startCode = prodCode[0];//C171280027
			String lastCode = prodCode[1];//C171280035
			String head = startCode.substring(0,1);
			int startNum = Integer.valueOf(startCode.substring(1));
			int lastNum = Integer.valueOf(lastCode.substring(1));
			String prodCodes ="";
			for(int i=startNum;i<=lastNum;i++){ 
				prodCodes +=(head+String.valueOf(i)+","); 
			}
			map.put("productBarcode",prodCodes.substring(0, prodCodes.length()-1).split(",")); 
		
		}else{ 
			if(!"".equals(productBarcode))
			map.put("productBarcode",new String[]{productBarcode});
		}
		map.put("barcodeStatus",Utils.unicodeToString(workStatus));
		map.put("printFlag",printFlag);
		page = iProductBarcodeService.getProductBarcode(pagination,map);
		return JSON.toJSONString(page);
	}

	/**
	 * 获取工单号列表
	 * @param page 分页数据
	 * @param workOrderNumber 工单
     * @return
     */
    @RequiresPermissions("productBarcodeController:view")
	@RequestMapping(value = "getWorkOrderByPage")
	@ResponseBody
	public String getWorkOrderByPage(Pagination page, String workOrderNumber){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>();
		map.put("workOrderNumber",workOrderNumber);
		page = iProductBarcodeService.getWorkOrderByPage(pagination,map);
		return JSON.toJSONString(page);
	}

	/**
	 * 条码生成
	 * @param user 当前用户
	 * @param workOrder 工单ID
     * @return
     */
    @RequiresPermissions("productBarcodeController:generate")
	@RequestMapping("/barcodeProduction")
	@ResponseBody
	public String barcodeProduction(@CurrentLoginInfo UserAuthenRealm.ShiroUser user,String workOrder){
		Json json = new Json();
		Map<String,Object> map = new HashMap<>();
		try{
			String userId = user.getUserId().toString();
			map.put("p_organization_id",user.getOrgId());
			map.put("p_wip_entity_id",workOrder);
			map.put("p_user_id",userId);
			map.put("x_return_status","");
			map.put("x_msg_data","");
			iProductBarcodeService.barcodeProduction(map);
			Object status = map.get("x_return_status");
			String msg = String.valueOf(map.get("x_msg_data"));
			json.setSuccess("S".equals(status)?true:false);
			json.setMessage("S".equals(status)?"条码生成成功":msg);
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}

	/**
	 * 条码作废
	 * @param user 当前用户
	 * @param codes 待作废条码
	 * @param invalidReason 作废原因
     * @return
     */
    @RequiresPermissions("productBarcodeController:disabled")
	@RequestMapping(value = "abolish",method = RequestMethod.POST)
	@ResponseBody
	public String abolish(@CurrentLoginInfo UserAuthenRealm.ShiroUser user,String codes,String invalidReason){
		Json json = new Json();
		Map<String,Object> map = new HashMap<>();
		try{
			String userId = user.getUserId().toString();
			map.put("p_organization_id",user.getOrgId());
			map.put("p_user_id",userId);
			map.put("p_barcode",codes);
			map.put("invalidReason",invalidReason);
			map.put("x_return_status","");
			map.put("x_msg_data","");
			iProductBarcodeService.abolish(map);
			Object status = map.get("x_return_status");
			String msg = String.valueOf(map.get("x_msg_data"));
			json.setSuccess("S".equals(status)?true:false);
			json.setMessage("S".equals(status)?"条码作废成功":msg);
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
	
	/**
	 * 根据序列号生成二维码
	 * @param codes
     */
    @RequiresPermissions("productBarcodeController:print")
	@RequestMapping("toBarCodeByQRCode")
	@ResponseBody
	public String toBarCodeByQRCode(HttpServletRequest request,@RequestParam("codes[]") String[] codes){
		Json json = new Json();
		List<Map<String,byte[]>> list = new ArrayList<>();
		try{
			if(codes.length == 0){
				json.setMessage("序列号不能为空");
				return JSON.toJSONString(json);
			}
			for (int i = 0; i < codes.length; i++) {
				String barCode = codes[i];
				//根据字符串转二维码
				BufferedImage image = QRCodeUtil.createImage(barCode,50, null, true);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(image, "bmp", out);
				byte[] b = out.toByteArray();
				Map<String,byte[]> map = new LinkedHashMap<>();
				map.put(barCode,b);
				list.add(map);
				if(out != null){
					out.close();
				}
			}
			String path = WriteForPDF.writePDF(request,list);
			if(!StringUtils.isEmpty(path)){
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
				//修改条码打印状态
				iProductBarcodeService.updatePrintStatus(codes);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
    
    /**
	 * 根据序列号生成（一份）二维码
	 * @param codes
     */
    @RequiresPermissions("productBarcodeController:print1")
	@RequestMapping("toBarCodeByQRCode1")
	@ResponseBody
	public String toBarCodeByQRCode1(HttpServletRequest request,@RequestParam("codes[]") String[] codes){
    	Json json = new Json();
		List<Map<String,byte[]>> list = new ArrayList<>();
		try{
			if(codes.length == 0){
				json.setMessage("序列号不能为空");
				return JSON.toJSONString(json);
			}
			for (int i = 0; i < codes.length; i++) {
				String barCode = codes[i];
				//根据字符串转二维码
				BufferedImage image = QRCodeUtil.createImage(barCode,50, null, true);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(image, "bmp", out);
				byte[] b = out.toByteArray();
				Map<String,byte[]> map = new LinkedHashMap<>();
				map.put(barCode,b);
				list.add(map);
				if(out != null){
					out.close();
				}
			}
			String path = WriteForPDF.writePDF1(request,list);
			if(!StringUtils.isEmpty(path)){
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
				//修改条码打印状态
				iProductBarcodeService.updatePrintStatus(codes);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
    }
}
