package com.egdfrm.unit.controller.barcodeManagement;
 

import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.common.WriteForPDF;
import com.egdfrm.unit.service.barcodeManagement.INoProductBarcodeService;
import com.egdfrm.unit.service.barcodeManagement.JJNoProductBarcodeService;
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
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.barcodeManagement.InPro;
import com.egdfrm.unit.service.barcodeManagement.InShipProService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * 借机无工单产品条码维护
 * @author hgb
 * @date 2017-4-26 
 */
@RequestMapping("inShipProController")
@Controller
public class InShipProController extends BaseController{  
	
	@Autowired 
	private InShipProService inShipProService;
	
	@RequestMapping("init")  
	public String init(){ 
		return "unit/barcodeManagement/inShipPro"; 
	}
	
	/**
	 *  模态框 分页数据
	 */
	@RequestMapping("getModalTableLists")  
	@ResponseBody
	public String getModalTableLists(Pagination page,InPro inPro){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		page = inShipProService.getModalTableLists(pagination, inPro);
		return JSON.toJSONString(page);
	}  
	
	/**
	 *  批量添加
	 */
	@RequestMapping(value = "batchInsertToShip",method = RequestMethod.POST)
	@ResponseBody 
	public String batchInsertToShip(@CurrentLoginInfo ShiroUser user, InPro inPro){ 
		Json json = new Json();
		String[] msg = inShipProService.isNull(inPro);
		if("S".equals(msg[0])){
			return JSON.toJSONString(inShipProService.batchInsertToShip(String.valueOf(user.getUserId()),json, inPro));
		}else {
			String[] msg2 = inShipProService.isSatus(inPro);
			if("S".equals(msg2[0])){
				return JSON.toJSONString(inShipProService.batchUpdateToShip(String.valueOf(user.getUserId()),json, inPro));
			}else {
				json.setSuccess(false);
				json.setMessage(msg[1]+"状态不正确!");
			}
			return JSON.toJSONString(json);
		}
	}
//-----------------------------------------------------init2------------------------------------------
	/**
	 * 导入无工单产品条码，并改为发运状态。
	 * 以便 CRM销售退货会来没有改代码时，方便产线帮打印出来， 打印出来的条码直接为ship状态
	 * inShipProController
	 */

	@Autowired  INoProductBarcodeService iNoProductBarcodeService;
	@RequestMapping("init2")
	public String init2(){
		return "unit/barcodeManagement/inShipPro2";
	}
	/**
	 * 获取当前条码
	 * @return
	 */
	@RequestMapping(value = "getCurrentBarcode",method = RequestMethod.POST)
	@ResponseBody
	public String getCurrentBarcode(){
		Json json = new Json();
		String barcode = iNoProductBarcodeService.getCurrentBarcode();
		if(!StringUtils.isEmpty(barcode)){
			json.setSuccess(true);
			json.setMessage(barcode);
		}
		return JSON.toJSONString(json);
	}
	/**
	 * 获取开始和结束条码
	 * @param printNum
	 * @return
	 */
	@RequestMapping(value = "getSEcode",method = RequestMethod.POST)
	@ResponseBody
	public String getSEcode(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, int printNum){
		Json json = new Json();
		Map<String,Object> map = new HashMap<>();
		try{
			String userId = user.getUserId().toString();
			map.put("p_organization_id",user.getOrgId());
			map.put("p_print_quantity",printNum);
			map.put("p_user_id",userId);
			map.put("x_return_status","");
			map.put("x_msg_data","");
			iNoProductBarcodeService.getSEcode(map);
			Object status = map.get("x_return_status");
			String msg = String.valueOf(map.get("x_msg_data"));
			json.setSuccess("S".equals(status)?true:false);
			json.setMessage("S".equals(status)?msg:"系统繁忙,请稍后再试!");
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}

	/**
	 * 打印二维码
	 * @param startCode 开始条码
	 * @param endCode 结束条码
	 * @return
	 */
	@RequestMapping(value = "toBarCodeByQRCode",method = RequestMethod.POST)
	@ResponseBody
	public String toBarCodeByQRCode(HttpServletRequest request, String startCode, String endCode){
		Json json = new Json();
		List<Map<String,byte[]>> list = new ArrayList<>();
		try {
			String head = startCode.substring(0,3);
			int startNum = Integer.parseInt(1+startCode.substring(3));
			int endNum = Integer.parseInt(1+endCode.substring(3));
			for (int i = startNum; i <= endNum ; i++) {
				String code = head + String.valueOf(i).substring(1);
				//根据字符串转二维码
				BufferedImage image = QRCodeUtil.createImage(code,50, null, true);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(image, "bmp", out);
				byte[] b = out.toByteArray();
				Map<String,byte[]> map = new LinkedHashMap<>();
				map.put(code,b);
				list.add(map);
				System.out.println();
				if(out != null){
					out.close();
				}
			}
			System.out.println();
			String path = WriteForPDF.writePDF(request,list);
			if(!StringUtils.isEmpty(path)){
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
			}
		}catch (Exception e){
			System.out.println();
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}

	/**
	 * 保存单个条码
	 * @return
	 */
	@RequestMapping("/save2")
	@ResponseBody
	public Json save2(@CurrentLoginInfo ShiroUser user, InPro inPro){
		Json json = new Json();
		String userId = user.getUserId().toString();
		int result =  inShipProService.updateShip(inPro ,userId);
		if(result>0){
			json.setSuccess(true);
			json.setMessage(inPro.getBarcodeText()+"导入成功！");
			return json;
		}

		return json;
	}


//-----------------------------------------------------init2------------------------------------------


// ----------------------------------------------------init3--------------------------------

	@Autowired JJNoProductBarcodeService jjNoProductBarcodeService;
	@RequestMapping("init3")
	public String init3(){
		return "unit/barcodeManagement/printProductBarcode";
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
