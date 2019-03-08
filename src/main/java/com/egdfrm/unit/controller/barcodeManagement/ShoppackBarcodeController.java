package com.egdfrm.unit.controller.barcodeManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.common.WriteForPDF;
import com.egdfrm.unit.service.barcodeManagement.IShoppackBarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egdfrm.core.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * 发运包装条码管理
 * @author hgb
 * @date 2016-12-22
 */
@Controller
@RequestMapping("shoppackBarcodeController")
public class ShoppackBarcodeController extends BaseController {

	@Autowired
	private IShoppackBarcodeService iShoppackBarcodeService;

	/**
	 * 跳转到发运包装条码管理页面
	 * @return
     */
	@RequestMapping(value = "init",method = RequestMethod.GET)
	public String init(Model model){
		//获取条码补丁
		//List<String> list = iShoppackBarcodeService.getPatch();
		//model.addAttribute("list",list);
		return "unit/barcodeManagement/shoppackBarcode";
	}

	/**
	 * 获取当前条码
	 * @return
	 */
	@RequestMapping(value = "getCurrentBarcode",method = RequestMethod.POST)
	@ResponseBody
	public String getCurrentBarcode(){
		Json json = new Json();
		String barcode = iShoppackBarcodeService.getCurrentBarcode();
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
			iShoppackBarcodeService.getSEcode(map);
			Object status = map.get("x_return_status");
			String msg = String.valueOf(map.get("x_msg_data"));
			json.setSuccess("S".equals(status)?true:false);
			json.setMessage("S".equals(status)?msg:"系统繁忙,请稍后再试");
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
			String head = startCode.substring(0,5);
			int startNum = Integer.parseInt(1+startCode.substring(5));
			int endNum = Integer.parseInt(1+endCode.substring(5));
			for (int i = startNum; i <= endNum ; i++) {
				String code = head + String.valueOf(i).substring(1);
				//根据字符串转二维码
				BufferedImage image = QRCodeUtil.createImage(code,500, null, true);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(image, "bmp", out);
				byte[] b = out.toByteArray();
				Map<String,byte[]> map = new LinkedHashMap<>();
				map.put(code,b);
				list.add(map);
				if(out != null){
					out.close();
				}
			}
			String path = WriteForPDF.writePdfToPack(request,list);
			if(!StringUtils.isEmpty(path)){
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}


	/**
	 * 补码打印
	 * @param request
	 * @param code 条码
     * @return
     */
	@RequestMapping(value = "/printPatch",method = RequestMethod.POST)
	@ResponseBody
	public String printPatch(HttpServletRequest request, String code){
		Json json = new Json();
		List<Map<String,byte[]>> list = new ArrayList<>();
		try {
			//根据字符串转二维码
			BufferedImage image = QRCodeUtil.createImage(code,500, null, true);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(image, "bmp", out);
			byte[] b = out.toByteArray();
			Map<String,byte[]> map = new LinkedHashMap<>();
			map.put(code,b);
			list.add(map);
			if(out != null){
				out.close();
			}
			String path = WriteForPDF.writePdfToPack(request,list);
			if(!StringUtils.isEmpty(path)){
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
}
