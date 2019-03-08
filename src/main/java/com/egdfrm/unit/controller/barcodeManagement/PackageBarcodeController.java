package com.egdfrm.unit.controller.barcodeManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.common.WriteForPDF;
import com.egdfrm.unit.model.barcodeManagement.PackagePrint;
import com.egdfrm.unit.service.barcodeManagement.IPackageBarcodeService;

/**
 * 包装条码管理
 * @author hgb
 * @date 2016-12-22
 */
@Controller
@RequestMapping("packageBarcodeController")
public class PackageBarcodeController extends BaseController{
	
	@Autowired
	private IPackageBarcodeService iPackageBarcodeService;

	/**
	 * 入口页面
	 * @author	hgb
	 * @date 2016-12-22
	 */
	@RequestMapping("init")
	public String init(Model model){
		List<Map<String,Object>> list = iPackageBarcodeService.getPackageType();
		model.addAttribute("types",list);
		return "unit/barcodeManagement/packageBarcode";
	}
	
	/**
	 * 获取包装条码列表数据
	 * @param packageBarcode 包装条码
	 * @param workOrder 工单
	 * @param typeCode 包装类型编码
	 * @return   数据
	 * @author	hgb
	 * @date 2016-12-22
	 */
	@RequestMapping("getPackageBarcode")
	@ResponseBody
	public String getPackageBarcode(Pagination page,String packageBarcode, String workOrder, String typeCode,String printStatus){
		try {
			Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
			Map<String,Object>  map = new HashMap<>();
			map.put("workOrder",workOrder);
			map.put("packageBarcode",packageBarcode);
			map.put("typeCode",typeCode);
			map.put("printStatus",printStatus);
			page = iPackageBarcodeService.getProductBarcode(pagination,map);
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(page);
	}

	/**
	 * 根据工单号获取贴箱标志
	 * @param workOrder 工单号
	 * @return
	 */
	@RequestMapping(value = "getFlagMark",method = RequestMethod.POST)
	@ResponseBody
	public String getFlagMark(String workOrder){
		return iPackageBarcodeService.getFlagMark(workOrder);
	}

	/**
	 * 条码生成
	 * @param user
	 * @param workOrder
	 * @param packageType
     * @return
     */
	@RequestMapping(value = "barcodePackage",method = RequestMethod.POST)
	@ResponseBody
	public String barcodePackage(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, String workOrder, String packageType){
		Json json = new Json();
		Map<String,Object> map = new HashMap<>();
		try{
			String userId = user.getUserId().toString();
			map.put("p_organization_id",user.getOrgId());
			map.put("p_wip_entity_id",workOrder);
			map.put("p_user_id",userId);
			map.put("p_pack_type",packageType);
			map.put("x_return_status","");
			map.put("x_msg_data","");
			iPackageBarcodeService.barcodePackage(map);
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
	 * 根据序列号生成二维码（打印两份）
	 * @param codes
	 */
	@RequestMapping("toBarCodeByQRCode")
	@ResponseBody
	public String toBarCodeByQRCode(HttpServletRequest request, @RequestParam("codes[]") String[] codes){
		Json json = new Json();
		//保存二维码字节流
		List<Map<String,byte[]>> list = new ArrayList<>();
		//保存打印数据
		List<byte[]> dataBytes = new ArrayList<>();
		try{
			if(codes.length == 0){
				json.setMessage("序列号不能为空！");
				return JSON.toJSONString(json);
			}
			List<PackagePrint> dataList = iPackageBarcodeService.getPrintData(codes);
			for (int i = 0; i < codes.length; i++) {
				String barCode = codes[i];
				//根据字符串转二维码
				BufferedImage image = QRCodeUtil.createImage(codes[i],500, null, true);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(image, "png", out);
				byte[] b = out.toByteArray();
				Map<String,byte[]> map = new LinkedHashMap<>();
				map.put(barCode,b);
				list.add(map);
				if(out != null){
					System.out.println();
					out.close();
				}
				image = graphicsGenerationByPackage(null,dataList.get(i));
				out = new ByteArrayOutputStream();
				ImageIO.write(image, "JPG", out);
				dataBytes.add(out.toByteArray());
				if(out != null){
					out.close();
				}
			}
			String path = WriteForPDF.writePdfToPackNew(request,list,dataBytes,2);
			if(!StringUtils.isEmpty(path)){
				System.out.println(path);
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
				//修改打印状态
				iPackageBarcodeService.updatePrintStatus(codes);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
	/**
	 * 根据序列号生成二维码 (打印一份)
	 * @param codes
	 */
	@RequestMapping("toBarCodeByQRCode1")
	@ResponseBody
	public String toBarCodeByQRCode1(HttpServletRequest request, @RequestParam("codes[]") String[] codes){
		Json json = new Json();
		//保存二维码字节流
		List<Map<String,byte[]>> list = new ArrayList<>();
		//保存打印数据
		List<byte[]> dataBytes = new ArrayList<>();
		try{
			if(codes.length == 0){
				json.setMessage("序列号不能为空!");
				return JSON.toJSONString(json);
			}
			List<PackagePrint> dataList = iPackageBarcodeService.getPrintData(codes);
			for (int i = 0; i < codes.length; i++) {
				String barCode = codes[i];
				//根据字符串转二维码
				BufferedImage image = QRCodeUtil.createImage(codes[i],500, null, true);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(image, "png", out);
				byte[] b = out.toByteArray();
				Map<String,byte[]> map = new LinkedHashMap<>();
				map.put(barCode,b);
				list.add(map);
				if(out != null){
					out.close();
				}
				image = graphicsGenerationByPackage(null,dataList.get(i));
				out = new ByteArrayOutputStream();
				ImageIO.write(image, "JPG", out);
				dataBytes.add(out.toByteArray());
				if(out != null){
					out.close();
				}
			}
			String path = WriteForPDF.writePdfToPackNew(request,list,dataBytes,1);
			if(!StringUtils.isEmpty(path)){
				System.out.println("path:"+path);
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
				//修改打印状态
				iPackageBarcodeService.updatePrintStatus(codes);
			}
		}catch (Exception e){
			System.out.println();
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}

	/**
	 * 向图片中加入文本内容
	 * @param imgurl
	 */
	public final static BufferedImage graphicsGenerationByPackage(String imgurl, PackagePrint print) {
		int imageWidth = 500;// 图片的宽度
		int imageHeight = 500;// 图片的高度
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("微软雅黑", 1, 34));//Arial Black、Kartika
		
		Graphics graphics2 = image.getGraphics();//年份的画笔
		graphics2.setColor(Color.WHITE);
		graphics2.fillRect(0, 0, imageWidth, imageHeight);
		graphics2.setColor(Color.BLACK);
		graphics2.setFont(new Font("微软雅黑", 1, 48));
		
		String modelNo = StringUtils.isEmpty(print.getModelNo()) ? "" : print.getModelNo();
		String partNumber = StringUtils.isEmpty(print.getPartNumber()) ? "" : print.getPartNumber();
		String piNo = StringUtils.isEmpty(print.getPiNo()) ? "" : print.getPiNo();
		String poNo = StringUtils.isEmpty(print.getPoNo()) ? "" : print.getPoNo();
		String moNo = StringUtils.isEmpty(print.getMoNo()) ? "" : print.getMoNo();
		String wo = StringUtils.isEmpty(print.getOrderNo()) ? "" : print.getOrderNo();
		String pcs = StringUtils.isEmpty(print.getPcs()) ? "" : print.getPcs();
		String cNo = StringUtils.isEmpty(print.getcNo()) ? "" : print.getcNo();
		String str = "";
		if(poNo!=""){//优先取po
			str = poNo;
		}else{
			str = piNo;
		}
		graphics.drawString("MODEL : " + modelNo, 5, 70);
		graphics.drawString("P/N : " + partNumber, 5, 120);
		graphics.drawString("P.I/P.O : " + str, 5, 170);
		graphics.drawString("M.O : " + moNo, 5, 220);
		graphics.drawString("WO : " + wo, 5, 270);
		graphics.drawString("PCS : " + pcs, 5, 320);
		graphics.drawString("C/NO : " + cNo, 5, 370); 
		if( print.getDate()==null){//
			graphics.drawString("DATE : ", 5, 420);
		}else{
			String date[] =  print.getDate().split("-");
			String year = date[0];
			String month = date[1];
			String day = date[2];
			graphics.drawString("DATE : ", 5, 420);
			graphics2.drawString(year, 125, 420);
			graphics.drawString("-"+month+"-"+day, 245, 420);
		} 
		return image;
	}
	

	@RequestMapping("packageBarcodeSearchInit")
	public String packageBarcodeSearchInit(){
		return "unit/barcodeManagement/packageBarcodeSearch";
	}
    /**
     * @author sjf
     * @date 2017年2月5日 
     * @param packageBarcode
     * @return
     * @throws Exception
     * 包装条码信息查询
     *
     */
    @RequestMapping("packageBarcodeSearch")
    @ResponseBody
    public List<Map<String, Object>> searchPackageBarcode(@CurrentLoginInfo ShiroUser su,String packageBarcode) throws Exception  {
    	return iPackageBarcodeService.searchPackageBarcode(su,packageBarcode);
    }
}
