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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.exception.CommonExceptionType;
import com.egdfrm.core.exception.MesThrowException;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentUser;
import com.egdfrm.extend.common.DateUtil;
import com.egdfrm.unit.common.BarcodeUtil;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.service.productionManagement.DocumentSupplementService;
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
 * @author sjf
 * @date 2017年2月8日 
 * 单据补打
 */
@Controller
@RequestMapping("documentSupplementController")
public class DocumentSupplementController extends BaseController {

	@Autowired
	private DocumentSupplementService documentSupplementService; 
	@Autowired
	private InspectionService inspectionService; 
	
	
	/*
	 *  单据补打主页
	 */
	@RequestMapping("init")
	public String init(){
		return "unit/productionManagement/documentSupplement";
	}
	 
	/*
	 * 跳转到报检单打印页面
	 */
	@RequestMapping("toInspectionPrintPage")
	public String toInspectionPrintPage(String inspectionNumber,HttpServletRequest req,@CurrentUser String loginName){
		List<Map<String,Object>> inspectionList=documentSupplementService.getInspectionByInspectionNumber(inspectionNumber);
		if(inspectionList.isEmpty()){
			//报检单不存在！
			throw new MesThrowException(CommonExceptionType.INSPECTIONNUMBER_NOT_EXISTS);
		}
		Map<String,Object> inspection=inspectionList.get(0);
		//报检单设值   
		req.setAttribute("date", DateUtil.getDate(new Date()));//日期
		req.setAttribute("hours",DateUtil.getTime(new Date())); //时间
		req.setAttribute("SEGMENT2",inspection.get("SEGMENT2"));  //标准机型
		req.setAttribute("CLIENT",inspection.get("CUSTOM_MODEL"));  //客户机型
		req.setAttribute("WIP_ENTITY_NAME",inspection.get("WIP_ENTITY_NAME"));  //生产单号
		req.setAttribute("PLAN_LINE",inspection.get("PLAN_LINE"));  //制造单位
		req.setAttribute("packNumber",inspection.get("PACKNUMBER"));  //数量
		req.setAttribute("inspectionNumber",inspectionNumber);  //报检流水号
		req.setAttribute("loginName",loginName);  //交检人 
		//req.setAttribute("data",this.inspectionService.getClientInfo(id)); 
		
		//去报检 
		return "unit/productionManagement/inspectionPrintPage";
	}
	
	/**
	 * 报检单的补打、PDF
	 * @param inspectionNumber 单号
	 * @return   
	 * @author	hgb
	 * @date 2017-2-10
	 */
	@RequestMapping(value="reprintInspect")
	@ResponseBody
	public String reprintInspect(String inspectionNumber,HttpServletRequest request,@CurrentUser String loginName){
		Json json = new Json();
		List<Map<String,Object>> inspectionList=documentSupplementService.getInspectionByInspectionNumber(inspectionNumber);
		if(inspectionList.isEmpty()){
			json.setSuccess(false);
			json.setMessage("报检单不存在！");
			return JSON.toJSONString(json);
		}
		try {  
			Map<String,Object> inspection=inspectionList.get(0);
			String SEGMENT2 = inspection.get("SEGMENT2")==null?"":inspection.get("SEGMENT2").toString();
			String CLIENT = inspection.get("CUSTOM_MODEL")==null?"":inspection.get("CUSTOM_MODEL").toString();
			String WIP_ENTITY_NAME = inspection.get("WIP_ENTITY_NAME")==null?"":inspection.get("WIP_ENTITY_NAME").toString();
			String PLAN_LINE = inspection.get("PLAN_LINE")==null?"":inspection.get("PLAN_LINE").toString();
			String packNumber = inspection.get("PACKNUMBER")==null?"":inspection.get("PACKNUMBER").toString();
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
			table.addCell(CreatePdf.createCell(CLIENT, textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第三行
			table.addCell(CreatePdf.createCell("生产单号", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(WIP_ENTITY_NAME,textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell("制造单位", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(PLAN_LINE, textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			//第四行
			table.addCell(CreatePdf.createCell("数量", keyfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
			table.addCell(CreatePdf.createCell(packNumber,textfont,cellHeight,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
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
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("document",document);
		map.put("path",realPath);
		map.put("pdfWriter",pdfWriter);
		return map;
	}
	
	
}
