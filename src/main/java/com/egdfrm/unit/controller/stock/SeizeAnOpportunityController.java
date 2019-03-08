package com.egdfrm.unit.controller.stock;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.common.CommonUtils;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.model.stock.SeizeAnOpportunity;
import com.egdfrm.unit.service.stock.ISeizeAnOpportunityService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 成品借机_控制器
 * Created by tyq on 17/3/14.
 */ 
@Controller
@RequestMapping("seizeAnOpportunity")
public class SeizeAnOpportunityController {

    @Autowired
    private ISeizeAnOpportunityService iSeizeAnOpportunityService;

    /**
     * 跳转到成品借机管理页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/stock/seizeAnOpportunity_manage";
    }

    /**
     * 跳转到借机新增页面
     * @return
     */
    @RequestMapping("addModal")
    public String addModal(Model model){
        //获取部门
        List<Map<String,Object>> depts = iSeizeAnOpportunityService.queryDepts();
        //获取用途
        List<Map<String,Object>> purposes = iSeizeAnOpportunityService.queryPurposes();
        Map<String,Object> map = new HashMap();
        map.put("number","");
        //获取借机单号
        iSeizeAnOpportunityService.getNumber(map);
        //获取子库
        List<Map<String,Object>> librarys = iSeizeAnOpportunityService.queryLibrarys();
        //目的货位
        // TODO 借机修改默认
        List<Map<String,Object>> maps = iSeizeAnOpportunityService.queryGoalLocations("F008");
        model.addAttribute("depts",depts);
        model.addAttribute("purposes",purposes);
        model.addAttribute("jjNumber",map.get("number"));
        model.addAttribute("librarys",librarys);
        model.addAttribute("maps",maps);
        return "unit/stock/seizeAnOpportunity_add_modal";
    }
    /**
     * 借机头删除
     * @param header_ids 头id
     * @return   
     * @author	hgb
     * @date 2017-4-18
     */
    @RequiresPermissions("seizeAnOpportunity:deleteHeaders")
    @RequestMapping("deleteHeaders")
    @ResponseBody
    public String deleteHeaders(@Param("ids")String[] ids){ 
    	Json json = new Json();
    	String[] re =  iSeizeAnOpportunityService.deleteHeaders(ids);
    	if("S".equals(re[0])){
    		json.setSuccess(true);
    		json.setMessage("删除成功！");
    	}else{
    		json.setSuccess(false);
    		json.setMessage(re[1]);
    	}
    	
    	return JSON.toJSONString(json);
    }
    /**
     * 借机行 删除
     * @param line_ids 行id
     * @return   
     * @author	hgb
     * @date 2017-4-18
     */
    @RequiresPermissions("seizeAnOpportunity:deleteLines")
    @RequestMapping("deleteLines")
    @ResponseBody
    public String deleteLines(@Param("ids")String[] ids){  
    	Json json = new Json();
    	String[] re =  iSeizeAnOpportunityService.deleteLines(ids);
    	if("S".equals(re[0])){
    		json.setSuccess(true);
    		json.setMessage("删除成功！");
    	}else{
    		json.setSuccess(false);
    		json.setMessage(re[1]);
    	} 
    	return JSON.toJSONString(json);
    }
    
    /**
     * 跳转到借机详情页面
     * @param model
     * @param number 借机单号
     * @return
     */
    @RequestMapping("detailedModal")
    public String detailedModal(Model model,String number){
        //获取部门
        List<Map<String,Object>> depts = iSeizeAnOpportunityService.queryDepts();
        //获取用途
        List<Map<String,Object>> purposes = iSeizeAnOpportunityService.queryPurposes();
        //查询借机头信息
        SeizeAnOpportunity seaoty = iSeizeAnOpportunityService.findByNumber(number);
        model.addAttribute("depts",depts);
        model.addAttribute("purposes",purposes);
        model.addAttribute("seaoty",seaoty);
        return "unit/stock/seizeAnOpportunity_detailed";
    }

    /**
     * 跳转到物流管理页面
     * @param model
     * @return
     */
    @RequestMapping("expressModal")
    public String expressModal(Model model){
        List<Map<String,Object>> list = iSeizeAnOpportunityService.queryExpress();
        model.addAttribute("expressList",list);
        return "unit/stock/expressModal";
    }


    /**
     * 获取目的货位
     * @param goalSubLibrary 目的子库
     * @return 目的货位数据集
     */
    @RequestMapping(value = "queryGoalLocations",method = RequestMethod.POST)
    @ResponseBody
    public String queryGoalLocations(String goalSubLibrary){
        List<Map<String,Object>> maps = iSeizeAnOpportunityService.queryGoalLocations(goalSubLibrary);
        return JSON.toJSONString(maps);
    }

    /**
     * 根据编码查询物料信息
     * @param code 编码
     * @return 物料信息
     */
    @RequestMapping(value = "queryDescribe",method = RequestMethod.POST)
    @ResponseBody
    public String queryDescribe(String code){
        Map<String,Object> map = iSeizeAnOpportunityService.queryDescribe(code);
        return JSON.toJSONString(map);
    }

    /**
     * 借机新增
     * @param user 当前用户
     * @param saoty
     * @return
     */
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    @ResponseBody
    public String insert(@CurrentLoginInfo UserAuthenRealm.ShiroUser user,SeizeAnOpportunity saoty){
        Json json = new Json();
        try {
            saoty.setOrgId(Integer.parseInt(user.getOrgId().toString()));
            saoty.setUserId(Integer.parseInt(user.getUserId().toString()));
            int result = iSeizeAnOpportunityService.insert(saoty);
            json.setSuccess(result > 0);
            json.setMessage(result > 0 ? "保存成功!":"保存失败!");
        }catch (Exception e){
            e.printStackTrace();
            json.setMessage("系统繁忙，请稍后再试");
        }
        return JSON.toJSONString(json);
    }

    /**
     * 借机汇总分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    @RequestMapping("findSummaryPage")
    @ResponseBody
    public String findSummaryPage(Pagination page,SeizeAnOpportunity seaoty){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iSeizeAnOpportunityService.findSummaryPage(pagination,seaoty);
        return JSON.toJSONString(page);
    }


    /**
     * 借机详情分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    @RequestMapping("findDetailedPage")
    @ResponseBody
    public String findDetailedPage(Pagination page,SeizeAnOpportunity seaoty){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iSeizeAnOpportunityService.findDetailedPage(pagination,seaoty);
        return JSON.toJSONString(page);
    }


    /**
     * 修改物流信息
     * @param seaoty
     * @return
     */
    @RequestMapping(value = "updateHeader",method = RequestMethod.POST)
    @ResponseBody
    public String updateHeader(SeizeAnOpportunity seaoty){
        Json json = new Json();
        try {
            int result = iSeizeAnOpportunityService.updateHeader(seaoty);
            json.setSuccess(result > 0);
            json.setMessage(result > 0 ? "保存成功" : "保存失败");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(json);
    }

    /**
     * 销售 
     * @param ids  行id
     * @param barcodeId 产品码 id
     * @param evaluation text文本 
     * @return   
     * @author	hgb
     * @date 修改于 2017-4-19
     */
    @RequestMapping(value = "updateSale",method = RequestMethod.POST)
    @ResponseBody
    public String updateSale(@RequestParam("ids[]") int[] ids,
    		@RequestParam("barcodeId[]")String[] barcodeId, String evaluation,@CurrentLoginInfo ShiroUser us){
        Json json = new Json();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(Calendar.getInstance().getTime());
        String[] re = iSeizeAnOpportunityService.updateSale(ids,barcodeId,evaluation,date,String.valueOf(us.getUserId()));
        if("S".equals(re[0])){
        	json.setSuccess(true);
        	json.setMessage("保存成功！");
        }else{
        	json.setSuccess(false);
        	json.setMessage(re[1]);
        }
        return JSON.toJSONString(json);
    }

    /**
     * 打印
     * @param request
     * @param number 借机单号,多个以，分隔
     * @return
     */
    @RequestMapping(value = "printer",method = RequestMethod.POST)
    @ResponseBody
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
                json.setMessage("借机单号不能为空");
                return JSON.toJSONString(json);
            }
            String[] numbers = number.split(",");
            Map<String, Object> objectMap = CommonUtils.getDocument(request, 1190F, 840F, 10, 10, 10, 0);
            //文档对象
            document = (Document)objectMap.get("document");
            //文档地址
            String pdfPath = String.valueOf(objectMap.get("path"));
            //表头
            String[] head = {"编码","型号","申请数量","计划归还时间","来源子库","目的子库","目的货位","物料描述"};
            //每列宽度
            float[] fs = {120,100,100,150,80,80,80,380};
            //每页数据条数
            int pageSize = 10;
            document.open();
            for (int i = 0; i < numbers.length; i++) {
                String num = numbers[i];
                //查询借机头信息
                SeizeAnOpportunity seaoty = iSeizeAnOpportunityService.findByNumber(num);
                //查询借机行信息
                List<SeizeAnOpportunity> listRows = iSeizeAnOpportunityService.findLines(num);
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
				BufferedImage image3 = graphicsGenerationByPackage("制单人：");
				BufferedImage image4 = graphicsGenerationByPackage("审核人：");
				BufferedImage image5 = graphicsGenerationByPackage("PMC：");
				BufferedImage image6 = graphicsGenerationByPackage("仓库：");
				BufferedImage image7 = graphicsGenerationByPackage("返回人：");
				BufferedImage image8 = graphicsGeneration("返回收货人：",1200,500);
				out3 = new ByteArrayOutputStream();
				out4 = new ByteArrayOutputStream();
				out5 = new ByteArrayOutputStream();
				out6 = new ByteArrayOutputStream();
				out7 = new ByteArrayOutputStream();
				out8 = new ByteArrayOutputStream();
				ImageIO.write(image3, "png", out3);
				ImageIO.write(image4, "png", out4);
				ImageIO.write(image5, "png", out5);
				ImageIO.write(image6, "png", out6);
				ImageIO.write(image7, "png", out7);
				ImageIO.write(image8, "png", out8);
				//添加图片
				Image bottomImg3 = Image.getInstance(out3.toByteArray());
				Image bottomImg4 = Image.getInstance(out4.toByteArray());
				Image bottomImg5 = Image.getInstance(out5.toByteArray());
				Image bottomImg6 = Image.getInstance(out6.toByteArray());
				Image bottomImg7 = Image.getInstance(out7.toByteArray());
				Image bottomImg8 = Image.getInstance(out8.toByteArray());
				//图片大小
				bottomImg3.scaleAbsolute(70, 40);
				bottomImg4.scaleAbsolute(70, 40);
				bottomImg5.scaleAbsolute(70, 40);
				bottomImg6.scaleAbsolute(70, 40);
				bottomImg7.scaleAbsolute(70, 40);
				bottomImg8.scaleAbsolute(100, 40);
				//控制图片位置
				bottomImg3.setAbsolutePosition(50, 80);
				bottomImg4.setAbsolutePosition(310, 80);
				bottomImg5.setAbsolutePosition(620, 80);
				bottomImg6.setAbsolutePosition(930, 80);
				bottomImg7.setAbsolutePosition(50, 40);
				bottomImg8.setAbsolutePosition(930, 40); 
				 // -------添加页脚END-------
                
                //数据集总条数
                int count = listRows.size();
                //分页
                int pages = new BigDecimal(count).divide(new BigDecimal(pageSize),BigDecimal.ROUND_CEILING).intValue();
                for (int j = 0;j<pages;j++){
                    //获取数据最大下标
                    int index = (j+1) * pageSize;
                    if(count < index){
                        index = count;
                    }
                    List<SeizeAnOpportunity> rows = new ArrayList<>();
                    for (int k = j*pageSize; k < index; k++) {
                        rows.add(listRows.get(k));
                    }
                    CreatePdf.seizeAnOpportunityTableData(document,seaoty,head,fs,rows);
                    //向pdf中添加二维码
                    document.add(backImgOne);
                    //向bdf重添加页脚
                    document.add(bottomImg3);
    				document.add(bottomImg4);
    				document.add(bottomImg5);
    				document.add(bottomImg6);
    				document.add(bottomImg7);
    				document.add(bottomImg8);
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
        		if(out7!=null){out7.close();}
        		if(out8!=null){out8.close();}
				if(out !=null){ out.close();}
			} catch (IOException e) { 
				e.printStackTrace();
			}
        }
        return JSON.toJSONString(json);
    }
    
    /**
     *  向文本添加 内容
     * @param name
     * @return
     * @throws Exception   
     * @author	hgb
     * @date 2017-4-7
     */
	public final static BufferedImage graphicsGenerationByPackage(String name) throws Exception{
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
	
	public final static BufferedImage graphicsGeneration(String name,int imageWidth,int imageHeight) throws Exception{
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
	
}
