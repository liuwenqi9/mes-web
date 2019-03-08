package com.egdfrm.unit.controller.productionManagement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.common.BarcodeUtil;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.StockInExcel;
import com.egdfrm.unit.model.barcodeManagement.StockInPrintRow;
import com.egdfrm.unit.service.productionManagement.InspectionService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;   
import com.egdfrm.core.security.annotation.CurrentUser;
import com.egdfrm.extend.common.DateUtil;
import com.egdfrm.unit.service.productionManagement.StockInServiceI;

/**
 * 入库单<br>查询&打印
 * @author hgb
 * @date 2016-12-30
 */
@Controller
@RequestMapping("stockInController")
public class StockInController extends BaseController{
	
	@Autowired
	private StockInServiceI service;
	@Autowired
	private InspectionService inspectionService;

	/**
	 * 
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号 
	 * @param stockMark 入库标识
	 * @param stockInNumber  入库单号 
	 * @author	hgb
	 * @date 2017-5-5
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,
			String productionLine,String workOrderNumber,String stockMark,String stockInNumber){
		Map<String,Object>  map = new HashMap<String, Object>();
		map.put("productionLine",productionLine);
		map.put("workOrderNumber",workOrderNumber);
		if("WIP_COM".equals(stockMark)){
			//未入库
			map.put("stockMark",0);
		}else if("Y".equals(stockMark)){
			//已入库
			map.put("stockMark",1);
		} 
		map.put("stockInNumber",stockInNumber);
		try {
			List<StockInExcel> list = service.exportExcel(map); 
			if(list != null && list.size() > 0){
			    //获取XSSFWorkbook
			    XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "入库单查询模板.xlsx");
			    ExcelUtils.exportExcel(response,workBook,"入库单查询.xlsx",list,2,0,0);
			}  
		} catch (Exception e) { 
			
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * 跳转到入库单打印页面
	 * @return 入库单打印页面
	 */
	@RequestMapping("initPrinter")
	public String initPrinter(Model model,@CurrentUser String loginName){
		//生产线
		List<Map<String, Object>> planLines = inspectionService.getPlanLines(loginName);
		model.addAttribute("planLines",planLines);
		return "unit/productionManagement/stockInPrinter";
	}

	/**
	 * 跳转到入库单查询页面
	 * @return 入库单查询页面
	 */
	@RequestMapping("initSearch")
	public String initSearch(Model model,@CurrentUser String loginName){
		//生产线
		List<Map<String, Object>> planLines = inspectionService.getPlanLines(loginName);
		model.addAttribute("planLines",planLines);
		return "unit/productionManagement/stockInSearch";
	}
	

	/**
	 * 入库单查询&&入库单打印的分页数据
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param workOrderNumberSubpool 工单子库
	 * @param stockInIdentify 入库单标识
	 * @param stockInNumber 入库单号
	 * @param stockMark 入库标识
	 * @param checkBeginDate 检验开始时间
	 * @param checkEndDate 检验结束时间
	 * @param mark 标记：search 入库单查询、printer：入库单打印
	 */
	@RequestMapping("getStockInPrinters")
	@ResponseBody
	public String getStockInPrinters(Pagination page, String productionLine, String workOrderNumber, String workOrderNumberSubpool, String stockInIdentify,
			String stockInNumber,String stockMark,String checkBeginDate,String checkEndDate,String mark){
		Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
		Map<String,Object>  map = new HashMap<>();
		map.put("productionLine",productionLine);
		map.put("workOrderNumber",workOrderNumber);
		map.put("workOrderNumberSubpool", workOrderNumberSubpool);
		if(!StringUtils.isEmpty(checkBeginDate)){
			map.put("checkBeginDate",!checkBeginDate.equals("")?(checkBeginDate+" 00:00:00"):"" );
		}
		if(!StringUtils.isEmpty(checkEndDate)){
			map.put("checkEndDate",!checkEndDate.equals("") ?(checkEndDate+" 23:59:59"):"");
		}
		if("Y".equals(stockInIdentify)){
			map.put("stockInIdentify",1);
		}else if("N".equals(stockInIdentify)){
			map.put("stockInIdentify",0);
		}
		map.put("stockInNumber",stockInNumber);
		map.put("mark",mark);
		if("WIP_COM".equals(stockMark)){
			//未入库
			map.put("stockMark",0);
		}else if("Y".equals(stockMark)){
			//已入库
			map.put("stockMark",1);
		}
		
		page = service.getStockInPrinters(pagination,map);
		return JSON.toJSONString(page);
	}


	/**
	 * 根据包装箱号ID判断是否已生成入库单号
	 * @param pbIDs 包装箱号ID
	 * @return
	 */
	@RequestMapping(value = "isPackingBarCodeByNo",method = RequestMethod.POST)
	@ResponseBody
	public String isPackingBarCodeByNo(String pbIDs){
		Json json = new Json();
		try {
			String message = service.isPackingBarCodeByNo(pbIDs);
			if("Y".equals(message)){
				json.setSuccess(true);
				json.setMessage(message);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}


	/**
	 * 生成入库单
	 * @param request
	 * @param pbIDs
	 * @return
	 */
	@RequestMapping(value = "generateStorageOrder",method = RequestMethod.POST)
	@ResponseBody
	public String generateStorageOrder(HttpServletRequest request,String pbIDs){
		Json json = new Json();
		Map<String,Object> map = new HashMap();
		map.put("number","");
		try {
			//获取入库单号
			service.generateStorageOrder(map);
			String number = String.valueOf(map.get("number"));
			map.put("p_user_id","");
			map.put("p_packing_barcode_id",pbIDs);
			map.put("x_return_status","");
			map.put("x_msg_data","");
			//修改入库单号
			service.updateDataInv(map);
			//访问PDF地址
			String pdfPath = createPdf(request,number);
			if("S".equals(map.get("x_return_status"))){
				json.setSuccess(pdfPath != null ? true : false);
				json.setMessage(pdfPath);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}

	/**
	 * 入库单打印
	 * @param request
	 * @param stockNum
	 * @return
	 */
	@RequestMapping(value = "printerStock",method = RequestMethod.POST)
	@ResponseBody
	public String printerStock(HttpServletRequest request,String stockNum){
		Json json = new Json();
		//访问PDF地址
		String pdfPath = createPdf(request,stockNum);
		if(!"入库单号不存在".equals(pdfPath)){
			json.setSuccess(true);
		}
		json.setMessage(pdfPath);
		return JSON.toJSONString(json);
	}

	/**
	 * 根据入库单生成PDF
	 * @param request
	 * @param stockNum
	 * @return
	 */
	public final String createPdf(HttpServletRequest request,String stockNum){
		String pdfPath = null;
		try {
			//创建PDF
			//获取打印表格数据
			List<StockInPrintRow> listRows = service.getTableRows(stockNum);
			if(listRows != null && listRows.size() == 0){
				return "入库单号不存在";
			}
			//获取打印表格汇总数据
			Map<String,Object> hzMap = service.getTableHZ(stockNum);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			hzMap.put("sysDate",sdf.format(Calendar.getInstance().getTime()));
			//表头
			String[] head = {"订单号","任务号","线别","型号","客户机型","物料编码","订单数量","入库数量","建议货位","备注"};
			//每列宽度
			float[] fs = {80,100,30,60,80,80,30,30,30,30};
			//每页数据条数
			int pageSize = 10;
			//数据集总条数
			int count = listRows.size();
			//分页
			int pages = new BigDecimal(count).divide(new BigDecimal(pageSize),BigDecimal.ROUND_CEILING).intValue();
			//创建Document
			Map<String, Object> objectMap = getDocument(request,10,10,10,0);
			Document document = (Document)objectMap.get("document");
			pdfPath = String.valueOf(objectMap.get("path"));
			document.open();
			for (int i = 0;i<pages;i++){
				//添加页首信息
				String rootPath = request.getServletContext().getRealPath("/");
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
				//图片大小
				backImgTwo.scaleAbsolute(56, 41);
				//控制图片位置
				backImgTwo.setAbsolutePosition(520, 365);
				//添加第三张一维码图片
				Image backImgThree = Image.getInstance(BarcodeUtil.generateStockNum(stockNum));
				backImgThree.scaleAbsolute(104, 23);
				backImgThree.setAbsolutePosition(400, 365);
				document.add(backImgOne);
				document.add(backImgTwo);
				document.add(backImgThree);
				/*************************为表格添加数据 start************************/
				//获取数据最大下标
				int index = (i+1) * pageSize;
				if(count < index){
					index = count;
				}
				List<StockInPrintRow> rows = new ArrayList<>();
				for (int j = i*pageSize; j < index; j++) {
					rows.add(listRows.get(j));
				}
				CreatePdf.pdfAddTableData(document,hzMap,head,fs,rows);
				/*************************为表格添加数据 end************************/
				//添加页脚
				BufferedImage image3 = graphicsGenerationByPackage("填表：");
				BufferedImage image4 = graphicsGenerationByPackage("审批：");
				BufferedImage image5 = graphicsGenerationByPackage("QA/QC：");
				BufferedImage image6 = graphicsGenerationByPackage("收货：");
				ByteArrayOutputStream out3 = new ByteArrayOutputStream();
				ByteArrayOutputStream out4 = new ByteArrayOutputStream();
				ByteArrayOutputStream out5 = new ByteArrayOutputStream();
				ByteArrayOutputStream out6 = new ByteArrayOutputStream();
				ImageIO.write(image3, "png", out3);
				ImageIO.write(image4, "png", out4);
				ImageIO.write(image5, "png", out5);
				ImageIO.write(image6, "png", out6);
				//添加图片
				Image bottomImg3 = Image.getInstance(out3.toByteArray());
				Image bottomImg4 = Image.getInstance(out4.toByteArray());
				Image bottomImg5 = Image.getInstance(out5.toByteArray());
				Image bottomImg6 = Image.getInstance(out6.toByteArray());
				//图片大小
				bottomImg3.scaleAbsolute(50, 40);
				bottomImg4.scaleAbsolute(50, 40);
				bottomImg5.scaleAbsolute(50, 40);
				bottomImg6.scaleAbsolute(50, 40);
				//控制图片位置
				bottomImg3.setAbsolutePosition(50, 10);
				bottomImg4.setAbsolutePosition(160, 10);
				bottomImg5.setAbsolutePosition(300, 10);
				bottomImg6.setAbsolutePosition(450, 10);
				document.add(bottomImg3);
				document.add(bottomImg4);
				document.add(bottomImg5);
				document.add(bottomImg6);
				out3.close();
				out4.close();
				out5.close();
				out6.close();
				document.newPage();
			}
			document.close();
			//访问PDF地址
			pdfPath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+pdfPath;
		}catch (Exception e){
			e.printStackTrace();
		}
		return pdfPath;
	}

	/**
	 * 创建PDF
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getDocument(HttpServletRequest request,float marginLeft,float marginRight,float marginTop,float marginBottom) throws Exception{
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
		Document document = new Document(A5, marginLeft, marginRight, marginTop, marginBottom);
		//document是创建的文档,out是输出
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
		Map<String,Object> map = new HashMap<>();
		map.put("document",document);
		map.put("path",realPath);
		map.put("pdfWriter",pdfWriter);
		return map;
	}

	/**
	 * 向图片中加入文本内容
	 */
	public final static BufferedImage graphicsGenerationByPackage(String name) throws Exception{
		int imageWidth = 500;// 图片的宽度
		int imageHeight = 500;// 图片的高度
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new java.awt.Font("宋体", Font.BOLD, 120));//Arial Black、Kartika
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		graphics.drawString(name, 1, 220);
		graphics.dispose();
		return image;
	}
}
