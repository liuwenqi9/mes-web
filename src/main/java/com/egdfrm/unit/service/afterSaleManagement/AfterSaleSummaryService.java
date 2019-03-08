package com.egdfrm.unit.service.afterSaleManagement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.egdfrm.unit.model.AfterSaleManagement.AfterSalePeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.common.CommonUtils;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.excelmodel.AfterSaleSummaryExcel;
import com.egdfrm.unit.mapper.afterSaleMenagement.AfterSaleBackMapper;
import com.egdfrm.unit.model.AfterSaleManagement.AfterSaleBack;
import com.egdfrm.unit.model.stock.SeizeAnOpportunity;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
/**
 * 售后返修汇总
 * @author hgb
 * @date 2017-6-16
 */
@Service
public class AfterSaleSummaryService {
	
	@Autowired
	public AfterSaleBackMapper afterSaleBackMapper;
	
	/* 
	 * 售后返修 分页数据
	 */
	public Pagination getAfterSaleSummaryList(Pagination pagination,AfterSaleBack afterSaleBack) {
		if( afterSaleBack.getSTATUS()!=null && !"".equals(afterSaleBack.getSTATUS()) ){
			if("Y".equals(afterSaleBack.getSTATUS()))
				afterSaleBack.setFalg(1);
			else{ 
				afterSaleBack.setFalg(0); 
			}
		}
        Long total = afterSaleBackMapper.getAfterSaleSummaryCount(afterSaleBack); 
        List<AfterSaleBack> rows = afterSaleBackMapper.getAfterSaleSummaryList(pagination,afterSaleBack);
        pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
    }

	/**
	 *  售后维修记录 （ 导出excel）  
	 * @date 2017-10-30
	 */
	public List<AfterSaleSummaryExcel> exportExcel(AfterSaleBack afterSaleBack){
		if( afterSaleBack.getSTATUS()!=null && !"".equals(afterSaleBack.getSTATUS()) ){
			if("Y".equals(afterSaleBack.getSTATUS()))
				afterSaleBack.setFalg(1);
			else{ 
				afterSaleBack.setFalg(0); 
			}
		}
		return afterSaleBackMapper.exportExcel(afterSaleBack);
	}
	
	/**
	 * 生成维修单，并update
	 */
	public String generaterRepWorkOrderAndPrint(HttpServletRequest request,String IDs,String uid){
		
		//生成维修单 
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("repWorkOrder", null);
		afterSaleBackMapper.callGetRepWorkOrder(map); 
		String repWorkOrder = map.get("repWorkOrder").toString();
		System.out.println("当前维修单号为："+repWorkOrder);
		//修改售后表
		String[] ID = IDs.split(",");
		for (int i = 0; i < ID.length; i++) {
			int result = afterSaleBackMapper.updateByRepWorkOrder(ID[i], repWorkOrder, uid);
			if(result<0){
				 System.out.println("维修单号："+repWorkOrder+"更新失败"); 
				 return null;
			}
		} 
		return repWorkOrder;
	}
	
	/**
	 * 维修单打印（只适用于维修单打印）
	 * @param request HttpServletRequest
	 * @param number 维修单号   
	 * @author	hgb
	 * @date 2017-6-21
	 */
	public String printer(HttpServletRequest request, String number){
        Json json = new Json();
        Document document = null;
        ByteArrayOutputStream out  = null; 
		ByteArrayOutputStream out3 = null;
		ByteArrayOutputStream out4 = null;
		ByteArrayOutputStream out5 = null;
		ByteArrayOutputStream out6 = null;
		ByteArrayOutputStream out7 = null;
		ByteArrayOutputStream out8 = null;
        try {
            if(StringUtils.isEmpty(number)){
                json.setMessage("维修单号不能为空");
                return JSON.toJSONString(json);
            }
            String[] numbers = number.split(",");
            Map<String, Object> objectMap = CommonUtils.getDocument(request, 1190F, 840F, 10, 10, 10, 0);
            //文档对象
            document = (Document)objectMap.get("document");
            //文档地址
            String pdfPath = String.valueOf(objectMap.get("path"));
            //表头
            String[] head = {"序号","机型","料号","机身码","数量","处理类别","补货SO","客户反馈","故障描述","维修分析"};
            //每列宽度
            float[] fs = {50,100,100,100,50,100,100,100,180,200};
            //每页数据条数
            int pageSize = 15;
            document.open();
            for (int i = 0; i < numbers.length; i++) {
                String num = numbers[i];
                //查询维修单信息
                List<AfterSaleBack> lists = afterSaleBackMapper.getPrinterInfo(num); 
                AfterSaleBack back = lists.get(0);
                //根据字符串转二维码
                BufferedImage image = QRCodeUtil.createImage(num,500, null, true);
                out = new ByteArrayOutputStream();
                ImageIO.write(image, "png", out);
                byte[] b = out.toByteArray(); 
                //添加图片
                Image backImgOne = Image.getInstance(b);
                
                //图片大小
                backImgOne.scaleAbsolute(100, 100);
                //控制图片位置
                backImgOne.setAbsolutePosition(1030, 710);
              //添加页脚------start--------
				BufferedImage image3 = graphicsGenerationByPackage1("售后维修人员：");
				BufferedImage image4 = graphicsGenerationByPackage1("仓库接收人：");
				BufferedImage image5 = graphicsGenerationByPackage2("日期：");
				BufferedImage image6 = graphicsGenerationByPackage2("日期：");  
				out3 = new ByteArrayOutputStream();
				out4 = new ByteArrayOutputStream();
				out5 = new ByteArrayOutputStream();
				out6 = new ByteArrayOutputStream(); 
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
				bottomImg3.scaleAbsolute(150, 40);
				bottomImg4.scaleAbsolute(150, 40);
				bottomImg5.scaleAbsolute(70, 40);
				bottomImg6.scaleAbsolute(70, 40); 
				//控制图片位置
				bottomImg3.setAbsolutePosition(50, 80);
				bottomImg4.setAbsolutePosition(930, 80);
				bottomImg5.setAbsolutePosition(50, 40);
				bottomImg6.setAbsolutePosition(930, 40);  
				 // -------添加页脚END-------
                
                //数据集总条数
                int count = lists.size();
                //分页
                int pages = new BigDecimal(count).divide(new BigDecimal(pageSize),BigDecimal.ROUND_CEILING).intValue();
                for (int j = 0;j<pages;j++){
                    //获取数据最大下标
                    int index = (j+1) * pageSize;
                    if(count < index){
                        index = count;
                    }
                    List<AfterSaleBack> rows = new ArrayList<>();
                    for (int k = j*pageSize; k < index; k++) {
                        rows.add(lists.get(k));
                    }
                    CreatePdf.afterSaleSummaryTableData(document,back,head,fs,rows);
                    //向pdf中添加二维码
                    document.add(backImgOne);
                    //向bdf重添加页脚
                    document.add(bottomImg3);
    				document.add(bottomImg4);
    				document.add(bottomImg5);
    				document.add(bottomImg6); 
                    document.newPage();
                }
            } 
            json.setSuccess(true);
            json.setMessage(pdfPath);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
        	 try {
        		document.close();
        		if(out3!=null){out3.close();}
        		if(out4!=null){out4.close();}
        		if(out5!=null){out5.close();}
        		if(out6!=null){out6.close();} 
				if(out !=null){ out.close();}
			} catch (IOException e) { 
				e.printStackTrace();
			}
        }
        return JSON.toJSONString(json);
    }
	/**
	 * 维修单打印（只适用于维修单打印）按行、维修单 打印
	 * @param request HttpServletRequest
	 * @param IDs 行id   
	 * @author	hgb
	 * @date 2017-10-13
	 */
	public String printer2(HttpServletRequest request, String IDs){
        Json json = new Json();
        Document document = null;
        ByteArrayOutputStream out  = null; 
		ByteArrayOutputStream out3 = null;
		ByteArrayOutputStream out4 = null;
		ByteArrayOutputStream out5 = null;
		ByteArrayOutputStream out6 = null;
		ByteArrayOutputStream out7 = null;
		ByteArrayOutputStream out8 = null;
        try {
            if(StringUtils.isEmpty(IDs)){
                json.setMessage("请选择数据！");
                return JSON.toJSONString(json);
            }
            String[] ids = IDs.split(",");
            Integer[] idsInteger = new Integer[ids.length];
            for (int i = 0; i < ids.length; i++) {
            	idsInteger[i]=Integer.parseInt(ids[i]);
			}
            Map<String, Object> objectMap = CommonUtils.getDocument(request, 1190F, 840F, 10, 10, 10, 0);
            //文档对象
            document = (Document)objectMap.get("document");
            //文档地址
            String pdfPath = String.valueOf(objectMap.get("path"));
            //表头
            String[] head = {"序号","机型","料号","机身码","数量","处理类别","补货SO","客户反馈","故障描述","维修分析"};
            //每列宽度
            float[] fs = {50,100,100,100,50,100,100,100,180,200};
            //每页数据条数
            int pageSize = 15;
            document.open(); 
            
                //查询维修单信息
                List<AfterSaleBack> lists = afterSaleBackMapper.getPrinterInfoByIds(idsInteger); 
                afterSaleBackMapper.updatePrinterStatusByIds(idsInteger);
                AfterSaleBack back = lists.get(0);
                //根据字符串转二维码
                BufferedImage image = QRCodeUtil.createImage(back.getREP_WORK_ORDER(),500, null, true);
                out = new ByteArrayOutputStream();
                ImageIO.write(image, "png", out);
                byte[] b = out.toByteArray(); 
                //添加图片
                Image backImgOne = Image.getInstance(b);
                
                //图片大小
                backImgOne.scaleAbsolute(100, 100);
                //控制图片位置
                backImgOne.setAbsolutePosition(1030, 710);
              //添加页脚------start--------
				BufferedImage image3 = graphicsGenerationByPackage1("售后维修人员：");
				BufferedImage image4 = graphicsGenerationByPackage1("仓库接收人：");
				BufferedImage image5 = graphicsGenerationByPackage2("日期：");
				BufferedImage image6 = graphicsGenerationByPackage2("日期：");  
				out3 = new ByteArrayOutputStream();
				out4 = new ByteArrayOutputStream();
				out5 = new ByteArrayOutputStream();
				out6 = new ByteArrayOutputStream(); 
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
				bottomImg3.scaleAbsolute(150, 40);
				bottomImg4.scaleAbsolute(150, 40);
				bottomImg5.scaleAbsolute(70, 40);
				bottomImg6.scaleAbsolute(70, 40); 
				//控制图片位置
				bottomImg3.setAbsolutePosition(50, 80);
				bottomImg4.setAbsolutePosition(930, 80);
				bottomImg5.setAbsolutePosition(50, 40);
				bottomImg6.setAbsolutePosition(930, 40);  
				 // -------添加页脚END-------
                
                //数据集总条数
                int count = lists.size();
                //分页
                int pages = new BigDecimal(count).divide(new BigDecimal(pageSize),BigDecimal.ROUND_CEILING).intValue();
                for (int j = 0;j<pages;j++){
                    //获取数据最大下标
                    int index = (j+1) * pageSize;
                    if(count < index){
                        index = count;
                    }
                    List<AfterSaleBack> rows = new ArrayList<>();
                    for (int k = j*pageSize; k < index; k++) {
                        rows.add(lists.get(k));
                    }
                    CreatePdf.afterSaleSummaryTableData(document,back,head,fs,rows);
                    //向pdf中添加二维码
                    document.add(backImgOne);
                    //向bdf重添加页脚
                    document.add(bottomImg3);
    				document.add(bottomImg4);
    				document.add(bottomImg5);
    				document.add(bottomImg6); 
                    document.newPage();
                }
             
            json.setSuccess(true);
            json.setMessage(pdfPath);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
        	 try {
        		document.close();
        		if(out3!=null){out3.close();}
        		if(out4!=null){out4.close();}
        		if(out5!=null){out5.close();}
        		if(out6!=null){out6.close();} 
				if(out !=null){ out.close();}
			} catch (IOException e) { 
				e.printStackTrace();
			}
        }
        return JSON.toJSONString(json);
    }
	
	/**
	  * 获取更新行信息
	  */
	public Map<String, Object> getUpdateLineInfoById(String ID){ 
		
		List<Map<String, Object>> list = afterSaleBackMapper.getUpdateLineInfoById(ID);
		
		return  list.get(0)==null?new HashMap<String, Object>():list.get(0);	
	}
	
	
	/**
	 * 弹出维修记录框
	 * 根据id 取 维修单号，维修人员，补号SO，处理类型，维修分析，故障描述
	 */
	public Map<String, Object> getLineInfoById(String ID){ 
		
		List<Map<String, Object>> list = afterSaleBackMapper.getLineInfoById(ID);
		
		return  list.get(0)==null?new HashMap<String, Object>():list.get(0);	
	}
	/**
	 *  从erp获取所有的维修人
	 */
	public List<Map<String, Object>> getRepPeopleAll(){
		return afterSaleBackMapper.getRepPeopleAll();
	}
	
	/**
	 *  维修记录    
	 */
	public Json bacthUpdate(AfterSaleBack afterSaleBack,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功");
		String[] IDs =  afterSaleBack.getIDs().split(",");
		for (int i = 0; i < IDs.length; i++) {
			afterSaleBack.setID(Integer.parseInt(IDs[i]));
			int result = 0;
			result = afterSaleBackMapper.update(afterSaleBack,uid);
			if(result<0){
				json.setSuccess(false);
				json.setMessage("数据异常");
				return json; 
			} 
		}
		return json;
	}
	
	/**
	 *  维修记录    
	 */
	public Json updateById(AfterSaleBack afterSaleBack,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功");   
		int result = 0;
		result = afterSaleBackMapper.updateById(afterSaleBack, uid);
		if (result < 0) {
			json.setSuccess(false);
			json.setMessage("数据异常");
			return json;
		}
		 
		return json;
	}
	/**
	 *  批量更新 交仓库日期    
	 * @author	hgb
	 * @date 2017-10-12
	 */
	public Json updateHandOverByIds(AfterSaleBack afterSaleBack,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功");   
		int result = 0;   
		result = afterSaleBackMapper.updateHandOverByIds(afterSaleBack, uid);
		if (result < 0) {
			json.setSuccess(false);
			json.setMessage("数据异常");
			return json;
		}
		 
		return json;
	}
	/**
     *  向文本添加 内容
     * @param name
     * @return
     * @throws Exception   
     * @author	hgb
     * @date 2017-4-7
     */
	private final static BufferedImage graphicsGenerationByPackage1(String name) throws Exception{
		int imageWidth = 1500;// 图片的宽度
		int imageHeight = 500;// 图片的高度
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new java.awt.Font("宋体", Font.BOLD, 200));//Arial Black、Kartika
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		graphics.drawString(name, 1, 220);
		graphics.dispose();
		return image;
	}
	
	/**
     *  向文本添加 内容
     * @param name
     * @return
     * @throws Exception   
     * @author	hgb
     * @date 2017-4-7
     */
	private final static BufferedImage graphicsGenerationByPackage2(String name) throws Exception{
		int imageWidth = 700;// 图片的宽度
		int imageHeight = 500;// 图片的高度
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new java.awt.Font("宋体", Font.BOLD, 200));//Arial Black、Kartika
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		graphics.drawString(name, 1, 220);
		graphics.dispose();
		return image;
	}



	public Pagination getAfterSalePeopleList(Pagination pagination,AfterSalePeople afterSalePeople) {
		Long total = afterSaleBackMapper.getAfterSalePeopleCount(afterSalePeople);
		List<AfterSalePeople> rows = afterSaleBackMapper.getAfterSalePeopleList(pagination,afterSalePeople);
		pagination.setTotal(total);
		pagination.setRows(rows);
		return pagination;
	}

	public Json updatePeopleById(AfterSalePeople afterSalePeople,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功");
		int result = 0;
		result = afterSaleBackMapper.updatePeopleById(afterSalePeople, uid);
		if (result < 0) {
			json.setSuccess(false);
			json.setMessage("数据异常");
			return json;
		}
		return json;
	}

	public Json peopleAdd(AfterSalePeople afterSalePeople,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功");
		List<AfterSalePeople> list = afterSaleBackMapper.peopleValidation(afterSalePeople);
		if(list != null && !list.isEmpty()){
			json.setSuccess(false);
			json.setMessage(afterSalePeople.getRepPeople()+" 已经存在");
			return json;
		}
		int result = 0;
		result = afterSaleBackMapper.peopleAdd(afterSalePeople, uid);
		if (result < 0) {
			json.setSuccess(false);
			json.setMessage("数据异常");
			return json;
		}
		return json;
	}

	public Json peopleValidation(AfterSalePeople afterSalePeople){
		Json json = new Json();
		List<AfterSalePeople> list = afterSaleBackMapper.peopleValidation(afterSalePeople);
	   	if(list!=null&&!list.isEmpty()){
			json.setSuccess(false);
			json.setMessage(afterSalePeople.getRepPeople()+" 已经存在");
		}else {
			json.setSuccess(true);
			json.setMessage("数据正确");
		}
		return json;
	}
}
