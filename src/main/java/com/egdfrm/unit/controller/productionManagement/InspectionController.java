package com.egdfrm.unit.controller.productionManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.annotation.CurrentUser; 
import com.egdfrm.extend.common.DateUtil;
import com.egdfrm.unit.common.BarcodeUtil;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.standard.InspectionMapper;
import com.egdfrm.unit.service.productionManagement.InspectionService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
/**
 * 报检单
 * @author hgb
 * 
 */
@Controller
@RequestMapping("inspectionController")
public class InspectionController extends BaseController {
	
	@Autowired
	private InspectionService inspectionService; 
	
	/*
	 *  报检页面入口
	 */
	@RequestMapping("init")
	public String init(Model model, @CurrentUser String loginName){
		//生产线
		List<Map<String, Object>> planLines = inspectionService.getPlanLines(loginName);
		model.addAttribute("planLines",planLines);
		return "unit/productionManagement/inspection";
	}
	
	/**
	 * 报检单的分页数据(new)
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param fullPackIdentify 满包标识
	 * @param inspectionIdentify 报检标识
	 * @return
	 */
	@RequestMapping("getInspectionListNew")
	@ResponseBody
	public String getInspectionListNew (@CurrentUser String loginName,Pagination page, String productionLine,
			String workOrderNumber,String fullPackIdentify,String inspectionIdentify){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>(); 
		map.put("productionLine",productionLine);
		map.put("workOrderNumber",workOrderNumber);
		map.put("fullPackIdentify",fullPackIdentify);
		map.put("inspectionIdentify",inspectionIdentify);
		page = inspectionService.getInspectionListNew(loginName,pagination,map);
		return JSON.toJSONString(page);	

}
	
	/**
	 * 报检单的分页数据(作废)
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param fullPackIdentify 满包标识
	 * @param inspectionIdentify 报检标识
	 * @return
	 */
	@RequestMapping("getInspectionList")
	@ResponseBody
	public List<Map<String,Object>> getInspectionList(@CurrentUser String loginName, String productionLine,
			String workOrderNumber,String fullPackIdentify,String inspectionIdentify){
		
		return this.inspectionService.getInspectionList(loginName ,productionLine,
				workOrderNumber, fullPackIdentify, inspectionIdentify);
		
	}
	
	/*
	 * 跳转到报检单打印页面
	 */
	@RequestMapping("toInspectionPrintPage")
	public String toInspectionPrintPage(@Param("id[]") String[] id,HttpServletRequest req,@CurrentUser String loginName,String SEGMENT2,String WIP_ENTITY_NAME,String PLAN_LINE
			,String PACK_QUANTITY_S){
		String[] QUANTITY_S =  PACK_QUANTITY_S.split(",");
		int packNumber = 0;
		for (int i = 0; i < QUANTITY_S.length; i++) {
			packNumber += Integer.parseInt(QUANTITY_S[i]);
		}
		//生成报检单  
		String inspectionNumber =  inspectionService.generateInspection(id, loginName);
		req.setAttribute("date", DateUtil.getDate(new Date()));//日期
		req.setAttribute("hours",DateUtil.getTime(new Date())); //时间
		req.setAttribute("SEGMENT2",SEGMENT2);  //标准机型
		req.setAttribute("WIP_ENTITY_NAME",WIP_ENTITY_NAME);  //生产单号
		req.setAttribute("PLAN_LINE",PLAN_LINE);  //制造单位
		req.setAttribute("packNumber",packNumber);  //数量
		req.setAttribute("inspectionNumber",inspectionNumber);  //报检流水号
		req.setAttribute("loginName",loginName);  //交检人 
		req.setAttribute("CLIENT",this.inspectionService.getClientInfo(id));  //客户机型
		//req.setAttribute("data",this.inspectionService.getClientInfo(id)); 
		
		//去报检
		inspectionService.toInspection(id, loginName, inspectionNumber);
		return "unit/productionManagement/inspectionPrintPage";
	}
	
	/**
	 * 获取报检单数据
	 * @param id 
	 * @return
	 */
	@RequestMapping("getInspectionByKey")
	@ResponseBody
	public List<Map<String,Object>> getInspectionByKey(String id){
		return this.inspectionService.getInspectionByKey(id); 
	}
	
	/*
	 *  检验页面入口
	 */
	@RequestMapping("inits")
	public String inits(Model model, @CurrentUser String loginName){
		//生产线
		List<Map<String, Object>> planLines = inspectionService.getPlanLines(loginName);
		model.addAttribute("planLines",planLines);
		return "unit/productionManagement/inspections";
	}
	
	/**
	 *  检验页面的分页数据(作废)
	 * @param planLine 生产线
	 * @param workOrderNumber 工单号 
	 * @param checkStatus 报检结果
	 * @param inspectNumber 报检单号
	 * @return
	 */
	@RequestMapping("getInspectionsList")
	@ResponseBody
	public List<Map<String, Object>> getInspectionsList(String planLine,
			String workOrderNumber,String inspectNumber,String checkStatus){
		return this.inspectionService.getInspectionsList(planLine, workOrderNumber,inspectNumber, checkStatus);
	}

	/** 
	 * 检验页面的分页数据 (服务端)
	 * @param page 
	 * @param planLine 生产线
	 * @param workOrderNumber 工单号 
	 * @param checkStatus 报检结果
	 * @param inspectNumber 报检单号
	 * @param modelNum 型号
	 * @param moNum MO单
	 * @author	hgb
	 * @date 2017-2-5
	 */
	@RequestMapping("getInspectionsListNew")
	@ResponseBody
	public String getInspectionsListNew (Pagination page,String planLine, String workOrderNumber, String inspectNumber,String checkStatus,String modelNum,String moNum){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>(); 
		map.put("planLine",planLine);
		map.put("workOrderNumber",workOrderNumber);
		map.put("inspectNumber",inspectNumber);
		map.put("checkStatus",checkStatus);
		map.put("modelNum",modelNum);
		map.put("moNum",moNum);
		page = inspectionService.getInspectionsListNew(pagination,map);
		return JSON.toJSONString(page);	
	}
	
	/**
	 * 
	 * @return 生产线下拉框  （有限制 ）
	 * @author	hgb
	 * @date 2016-12-29
	 */
	@RequestMapping("getPlanLines")
	@ResponseBody
	public List<Map<String, Object>> getPlanLines(@CurrentUser String loginName){
		return inspectionService.getPlanLines(loginName);
	}
	
	/**
	 *  生产线下拉框  
	 * @return   
	 * @author	hgb
	 * @date 2017-1-6
	 */
	@RequestMapping("getPlanLinesAll")
	@ResponseBody
	public List<Map<String, Object>> getPlanLines(){
		return inspectionService.getPlanLines();
	}
	
	
	/**
	 *  检验OK
	 * @param checkStatus 报检状态
	 * @param packingBarcodeId 
	 * @param loginName
	 * @return   
	 * @author	hgb
	 * @date 2017-1-3
	 */
	@RequestMapping("toOK")
	@ResponseBody
	public Boolean toOK(String checkStatus,String[] packingBarcodeId,
			String checkMsg,String speQty,String majorQty,String secQty, String checkRemark, @CurrentUser String loginName ){
		return inspectionService.toInspection(checkStatus, packingBarcodeId, checkMsg,
				speQty, majorQty, secQty, checkRemark, loginName);
	}
	
	/**
	 *  检验NG
	 * @param checkStatus 报检状态
	 * @param packingBarcodeId 
	 * @author	hgb
	 * @date 2017-1-3
	 */
	@RequestMapping("toNG")
	@ResponseBody
	public Boolean toNG(String checkStatus,String[] packingBarcodeId,
			String checkMsg,String speQty,String majorQty,String secQty, String checkRemark,
			@CurrentUser String loginName ){  
		return inspectionService.toInspection(checkStatus, packingBarcodeId, checkMsg,
				speQty, majorQty, secQty, checkRemark, loginName);
	}
	/**
	 *  退回   
	 * @author	hgb
	 * @date 2017-6-27
	 */
	@RequestMapping("toBack")
	@ResponseBody
	public Boolean toBack(String checkStatus,String[] packingBarcodeId,
			String checkMsg,String speQty,String majorQty,String secQty, String checkRemark,
			@CurrentUser String loginName ){  
		return inspectionService.toInspection(checkStatus, packingBarcodeId, checkMsg,
				speQty, majorQty, secQty, checkRemark, loginName);
	}
	
	@Autowired
	private InspectionMapper inspectionMapper;
	
	@RequestMapping("test")
	@ResponseBody
	public String test(){
		String inspectionNumber = "";
		//生成报检单号
		Map<String, Object> paramsOut = new HashMap<String, Object>(0);
		paramsOut.put("inspectionNumberOut", null);
		inspectionMapper.callGenerateInspection(paramsOut);
		Object o = paramsOut.get("inspectionNumberOut");
		inspectionNumber = o.toString();
		
		return inspectionNumber;
	}
	
	/**
	 * 验证数据是否有效 <P> 
	 * @return   true：有效, false：无效
	 * @author	hgb
	 * @date 2017-1-5
	 */
	@RequestMapping("isValid")
	@ResponseBody
	public Boolean isValid(@Param("id[]")String[] id){
		return inspectionService.isValid(id);
	}
	
	
	/**
	 * 验证检验数据是否有效 <P> 
	 * @param ids
	 * @return   true：有效, false：无效
	 * @author	hgb
	 * @date 2017-1-6
	 */
	@RequestMapping("isValid2")
	@ResponseBody
	public Boolean isValid2(@Param("ids[]")String[] ids){
		return inspectionService.isValid2(ids);
	}
	
	/**
	 * 生成报检单 （PDF）
	 * @return   path
	 * @author	hgb
	 * @date 2017-2-8
	 */
	@RequestMapping(value="generateInspectionButton",method=RequestMethod.POST)
	@ResponseBody
	public String generateInspectionButton(HttpServletRequest request,@Param("id[]") String[] id,
			@CurrentUser String loginName,String SEGMENT2,String WIP_ENTITY_NAME,String PLAN_LINE,
			@Param("PACK_QUANTITY_S[]") String[] PACK_QUANTITY_S){
		
		try {
			Json json = new Json();
			int packNumber = 0;
			for (int i = 0; i < PACK_QUANTITY_S.length; i++) {
				packNumber += Integer.parseInt(PACK_QUANTITY_S[i]); 
			}
			//生成报检单  
			String inspectionNumber =  inspectionService.generateInspection(id, loginName);
			
			//去报检
			inspectionService.toInspection(id, loginName, inspectionNumber);
			
			//创建Document
			Map<String, Object> objectMap = getDocument(request);
			Document document = (Document)objectMap.get("document");
			String pdfPath = String.valueOf(objectMap.get("path"));
			document.open();
			//添加页首信息
			String rootPath = request.getServletContext().getRealPath("/");;
			//第一张图片
			InputStream image1 = new FileInputStream(rootPath+"/WEB-INF/static/unit/img/p1.png");
			InputStream image2 = new FileInputStream(rootPath+"/WEB-INF/static/unit/img/p2.png");
			byte[] bytes1 = IOUtils.toByteArray(image1);
			image1.close();
			byte[] bytes2 = IOUtils.toByteArray(image2);
			image2.close();
			//添加第一张图片
			Image backImgOne = Image.getInstance(bytes1);
			backImgOne.scaleAbsolute(194, 41);
			//backImgOne.setAbsolutePosition(61, 14);
			//添加第二张图片
			Image backImgTwo = Image.getInstance(bytes2);
			//控制图片位置
			//backImgTwo.setAbsolutePosition(520, 365);
			backImgTwo.setAbsolutePosition(520, 365);
			//添加第三张一维码图片
			Image backImgThree = Image.getInstance(BarcodeUtil.generate(inspectionNumber,false));
			backImgThree.scaleAbsolute(80, 23);
			backImgThree.setAbsolutePosition(430, 339);
			document.add(backImgOne);
			document.add(backImgTwo);
			document.add(backImgThree);
			//每列宽度
			float[] fs = {100,175,100,175};
			//创建一个pdfTable
			PdfPTable table = CreatePdf.createTable(fs);
			int cellHeight = 40;
			// 设置中文显示
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			Font headfont = new Font(bfChinese, 14, Font.BOLD);// 设置字体大小
			Font keyfont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
			Font textfont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
			
			table.addCell(CreatePdf.createCell("成品交检通知单", headfont, Element.ALIGN_LEFT, 2, false));
			table.addCell(CreatePdf.createCell(inspectionNumber+"     ", headfont, Element.ALIGN_RIGHT, 2, false));
			//设置有边框的table
			//第一行
			table.addCell(CreatePdf.createCell("日期", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(DateUtil.getDate(new Date()),textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("时间", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(DateUtil.getTime(new Date()), textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第二行
			table.addCell(CreatePdf.createCell("标准机型", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(SEGMENT2,textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("客户机型", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(inspectionService.getClientInfo(id), textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第三行
			table.addCell(CreatePdf.createCell("生产单号", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(WIP_ENTITY_NAME,textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("制造单位", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(PLAN_LINE, textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第四行
			table.addCell(CreatePdf.createCell("数量", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(String.valueOf(packNumber),textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("颜色", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("", textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第五行
			table.addCell(CreatePdf.createCell("包装形式", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("",textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("交检人", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(loginName, textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第六行
			table.addCell(CreatePdf.createCell("检验员", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("",textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("检验结果", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("", textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第七行
			table.addCell(CreatePdf.createCell("备注", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("",textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("", textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			document.add(table);
			document.close();
			json.setSuccess(true); 
			//访问PDF地址
			pdfPath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+pdfPath;
			json.setMessage(pdfPath);
			return JSON.toJSONString(json);
		} catch (Exception e) { 
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * 创建PDF
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getDocument(HttpServletRequest request) throws Exception{
		//生成PDF目录
		String realPath = "pdf/";
		//项目根目录(绝对路径)
		String absolutely_path = request.getServletContext().getRealPath("/");
		//pdf相对于项目的地址
		absolutely_path = absolutely_path + realPath;
		File file = new File(absolutely_path);
		if(!file.exists()){
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(Calendar.getInstance().getTime()) + new Random().nextInt(999999) + ".pdf";
		absolutely_path = absolutely_path + "/" + fileName;
		//创建文档,设置页面大小,左、右、上和下页边距。
		realPath += fileName;
		//Rectangle A5 = new RectangleReadOnly(920.0F, 650.0F);
		Rectangle A5 = new RectangleReadOnly(595.0F, 420.0F);
		Document document = new Document(A5, 10, 10, 10, 0);
		//document是创建的文档,out是输出
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
		Map<String,Object> map = new HashMap<>();
		map.put("document",document);
		map.put("path",realPath);
		map.put("pdfWriter",pdfWriter);
		return map;
	}
	
	
}
