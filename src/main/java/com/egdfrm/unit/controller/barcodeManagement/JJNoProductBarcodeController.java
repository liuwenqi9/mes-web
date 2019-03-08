package com.egdfrm.unit.controller.barcodeManagement;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.common.WriteForPDF;
import com.egdfrm.unit.model.barcodeManagement.JJNoproductBarcode;
import com.egdfrm.unit.service.barcodeManagement.JJNoProductBarcodeService;

/**
 * 借机无工单产品条码维护
 * @author hgb
 * @date 2017-4-26 
 */
@RequestMapping("jJNoProductBarcodeController")
@Controller
public class JJNoProductBarcodeController extends BaseController{  
	
	@Autowired
	private JJNoProductBarcodeService jjNoProductBarcodeService;
	
	@RequestMapping("init") 
	public String init(){
		return "unit/barcodeManagement/JJNoProductBarcode"; 
	}
	
	@RequestMapping("verifyCodeId") 
	@ResponseBody
	public String verifyCodeId(String segment1){    
		return JSON.toJSONString(jjNoProductBarcodeService.verifyCodeId(segment1));
	}
	/**
	 *  取子库代码
	 */
	@RequestMapping("getSecondaryInventoryName") 
	@ResponseBody
	public String getSecondaryInventoryName(){
		return JSON.toJSONString(jjNoProductBarcodeService.getSecondaryInventoryName());
	} 
	/**
	 *  根据子库取货位
	 */
	@RequestMapping("getLocattionCode") 
	@ResponseBody
	public String getLocattionCode(String locattionCode){
		return JSON.toJSONString(jjNoProductBarcodeService.getLocattionCode(locattionCode));
	}
	/**
	 * 导入S_INV,SHIP.
	 * S_INV（存在时，并且为prod，更新为S_INV， 不存在时导入S_INV）
	 * SHIP（存在时，并且为REPROD，更新为 SHIP。不存在时导入SHIP）
	 * @param user
	 * @param model 
	 * @date 2017-4-26  改于2017-09-01
	 */
	@RequiresPermissions("jJNoProductBarcodeController:update")	
	@RequestMapping(value = "updateProductBarcode",method = RequestMethod.POST)
	@ResponseBody
	public String updateProductBarcode(@CurrentLoginInfo ShiroUser user,JJNoproductBarcode model){
		Json json = new Json();
		String type = model.getType().split(",")[0];
		String[] t  = model.getType().split(","); 
		for(int i=0;i<t.length;i++){
			if(!type.equals(t[i])){//不是一组操作，不允许
				json.setSuccess(false);
				json.setMessage("表单不是同一种操作类型！");
				return JSON.toJSONString(json);
			}
		}
		try {  
			if(type.equals("S_INV")){
				String[] re =jjNoProductBarcodeService.verifyProductBarcodes(model.getBarcodeText());
				String[] re2 =jjNoProductBarcodeService.verifyProductBarcodesAdd(model.getBarcodeText());
				if("S".equals(re[0])){//更新
					int result = jjNoProductBarcodeService.batchUpdate(Integer.parseInt(String.valueOf(user.getUserId())), model); 
					json.setSuccess(result > 0);
					json.setMessage(result > 0 ? "更新成功!" : "更新失败!");  
				}else if("S".equals(re2[0])){//添加 
					int result2 = jjNoProductBarcodeService.batchInsert(Integer.parseInt(String.valueOf(user.getUserId())), model); 
					json.setSuccess(result2 > 0);
					json.setMessage(result2 > 0 ? "导入成功!" : "导入失败!"); 
				}else if("E".equals(re[0])&&"E".equals(re[0])){//都不满足时
					json.setSuccess(false);
					json.setMessage("产品条码"+("".equals(re[1].toString())?re[1].toString():re[1].toString().subSequence(0, re[1].toString().length()-1))+"已经存在");
				}
			}else if(type.equals("SHIP")){//售后
				String[] re2 =jjNoProductBarcodeService.verifyProductBarcodesAdd(model.getBarcodeText());
				String[] re3 =jjNoProductBarcodeService.verifyProductBarcodeIsReprod(model.getBarcodeText());
				
				 if("S".equals(re2[0])){//添加  为发运 ship
					 int result = jjNoProductBarcodeService.batchInsertToShip(Integer.parseInt(String.valueOf(user.getUserId())), model);
					 json.setSuccess(result > 0);
					 json.setMessage(result > 0 ? "导入成功" : "导入失败");
				 }/*else if("E".equals(re3[0])){//验证条码是否为上线返修条码，是-更新为ship，不是则显示 条码已存在
					 int result = jjNoProductBarcodeService.batchUpdateToShip(Integer.parseInt(String.valueOf(user.getUserId())), model);
					 json.setSuccess(result > 0); 
					 json.setMessage(result > 0 ? "ship更新成功!" : "ship更新失败!"); 
				 }*/else{
					 	json.setSuccess(false);
						json.setMessage("产品条码"+("".equals(re2[1].toString())?re2[1].toString():re2[1].toString().subSequence(0, re2[1].toString().length()-1))+"已经存在"); 
				 }
			}
				
		} catch (Exception e) { 
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("服务器发生异常！");
		}

		return JSON.toJSONString(json);
	}
	
	
	/**
	 *  二维码补打
	 * @date 2017-4-26
	 */
	@RequestMapping(value = "printProductBarcode",method = RequestMethod.POST)
    @ResponseBody
	public String printProductBarcode(HttpServletRequest request,String barcode){
		Json json = new Json();
		List<Map<String, byte[]>> list = new ArrayList<>();
		try {
			if (barcode.length() > 18 && barcode.length() < 25) {// 说明是产品码时区间查询
				String[] prodCode = barcode.split("-");
				String startCode = prodCode[0];// C171280027
				String lastCode = prodCode[1];// C171280035
				String head = startCode.substring(0, 3);
				int startNum = Integer.valueOf(startCode.substring(3));
				int lastNum = Integer.valueOf(lastCode.substring(3));
				if(lastNum-startNum>3000 || lastNum-startNum<0){
					json.setSuccess(false);
					json.setMessage("输入条码有误!");
					return JSON.toJSONString(json);
				}
				for (int i = startNum; i <= lastNum; i++) {
					json = jjNoProductBarcodeService.verifyBarcode(head
							+ String.valueOf(i));
					if (!json.isSuccess())
						return JSON.toJSONString(json);
					// 根据字符串转二维码
					BufferedImage image = QRCodeUtil.createImage(
							head + String.valueOf(i), 50, null, true);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					ImageIO.write(image, "bmp", out);
					byte[] b = out.toByteArray();
					Map<String, byte[]> map = new LinkedHashMap<>();
					map.put(head + String.valueOf(i), b);
					list.add(map);
					if (out != null) {
						out.close();
					}
				}
			} else {
				json = jjNoProductBarcodeService.verifyBarcode(barcode);
				if (!json.isSuccess())
					return JSON.toJSONString(json);
				// 根据字符串转二维码
				BufferedImage image = QRCodeUtil.createImage(barcode, 50, null,
						true);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(image, "bmp", out);
				byte[] b = out.toByteArray();
				Map<String, byte[]> map = new LinkedHashMap<>();
				map.put(barcode, b);
				list.add(map);
				if (out != null) {
					out.close();
				}
			}
			String path = WriteForPDF.writePDF(request, list);
			if (!StringUtils.isEmpty(path)) {
				json.setSuccess(true);
				path = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort()+ request.getContextPath() + "/" + path;
				json.setMessage(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
	
	
	 
}
