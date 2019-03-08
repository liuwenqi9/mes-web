package com.egdfrm.unit.controller.productionManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.service.productionManagement.ISubassemblyPackService;
import com.lowagie.text.*;
import com.lowagie.text.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 半成品完工-控制器 
 */
@Controller
@RequestMapping("subassemblyPackController")
public class SubassemblyPackController {

    @Autowired
    private ISubassemblyPackService iSubassemblyPackService;

    /**
     * 跳转到配件包装管理页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/productionManagement/subassemblyPack";
    }


    /**
     * 半成品 查询
     * @param workOrderNumber 工单号
     * @return
     */
    @RequestMapping("queryAll")
    @ResponseBody
    public String queryAll(String workOrderNumber){
        List<Map<String,Object>> list = iSubassemblyPackService.queryAll(workOrderNumber);
        return JSON.toJSONString(list);
    }

    /**
     * 半成品 包装箱查询
     * @param workOrderNumber
     * @return
     */
    @RequestMapping("queryPackingAll")
    @ResponseBody
    public String queryPackingAll(String workOrderNumber){
        List<Map<String,Object>> list = iSubassemblyPackService.queryPackingAll(workOrderNumber);
        return JSON.toJSONString(list);
    }

    /**
     * 保存包装数量（半成品）
     * @param user 当前用户
     * @param wipEntityIds 工单ID
     * @param inventoryItemIds 物料ID
     * @param pacgingNums 包装数量
     * @return
     */
    @RequestMapping(value = "savePackingNumber",method = RequestMethod.POST)
    @ResponseBody
    public String savePackingNumber(@CurrentLoginInfo UserAuthenRealm.ShiroUser user,String wipEntityIds, String inventoryItemIds, String pacgingNums){
        Json json = new Json();
        try {
            String[] strSipEntityIds = wipEntityIds.split(",");
            String[] strInventoryItemIds = inventoryItemIds.split(",");
            String[] strPacgingNums = pacgingNums.split(",");
            int count = iSubassemblyPackService.savePackingNumber(user.getUserId(),strSipEntityIds,strInventoryItemIds,strPacgingNums);
            if(count > 0){
                json.setSuccess(true);
                json.setMessage("请求成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
            json.setMessage("系统繁忙，请稍后再试!");
        }
        return JSON.toJSONString(json);
    }

    /**
     * 包装箱打印（半成品）
     * @param packingBarcodeIds 包装ID
     * @return
     */
    @RequestMapping(value = "printerPacking",method = RequestMethod.POST)
    @ResponseBody
    public String printerPacking(HttpServletRequest request,String packingBarcodeIds){
        Json json = new Json();
        String pdfPath = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String nowDate = sdf.format(Calendar.getInstance().getTime());
            String[] barcodeIds = packingBarcodeIds.split(",");
            //表头
            String[] head = {"编码","数量","物料描述"};
            //每列宽度
            float[] fs = {90,80,380};
            //每页数据条数
            int pageSize = 15;
            StockInController stockIn = new StockInController();
            //创建Document
            Map<String, Object> objectMap = stockIn.getDocument(request,10,10,40,0);
            Document document = (Document)objectMap.get("document");
            pdfPath = String.valueOf(objectMap.get("path"));
            document.open();
            for (int i = 0; i < barcodeIds.length; i++) {
                String barcodeId = barcodeIds[i];
                /**
                 * 头信息查询
                 */
                Map<String,Object> map = iSubassemblyPackService.findPackingById(barcodeId);
                /************** 第一张图片 ****************/
                String barcodeText = null;
                if(map.get("BARCODE_TEXT")!=null){barcodeText = map.get("BARCODE_TEXT").toString();}
                //根据字符串转二维码
                BufferedImage image2 = QRCodeUtil.createImage(barcodeText,500, null, true);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(image2, "png", out);
                byte[] b2 = out.toByteArray();
                if(out != null){
                    out.close();
                }
                Image backImgTwo = Image.getInstance(b2);
                //图片大小
                backImgTwo.scaleAbsolute(90, 90);
                //控制图片位置
                backImgTwo.setAbsolutePosition(480, 310);
                /**
                 * 行信息查询
                 */
                List<Map<String,Object>> listRows = iSubassemblyPackService.getRows(barcodeId);
                //数据集总条数
                int count = listRows.size();
                //分页
                int pages = new BigDecimal(count).divide(new BigDecimal(pageSize),BigDecimal.ROUND_CEILING).intValue();
                for (int j = 0; j < pages; j++) {
                    //二维码
                    document.add(backImgTwo);
                    //获取数据最大下标
                    int index = (j+1) * pageSize;
                    if(count < index){
                        index = count;
                    }
                    List<Map<String,Object>> rows = new ArrayList<>();
                    for (int k = j*pageSize; k < index; k++) {
                        rows.add(listRows.get(k));
                    }
                    CreatePdf.pdfAddTableDataBySubbassembly(document,map,head,fs,rows);
                    //添加页脚
                    BufferedImage image3 = stockIn.graphicsGenerationByPackage("填表：");
                    BufferedImage image4 = stockIn.graphicsGenerationByPackage("审批：");
                    BufferedImage image5 = stockIn.graphicsGenerationByPackage("QA/QC：");
                    BufferedImage image6 = stockIn.graphicsGenerationByPackage("收货：");
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
                    bottomImg3.setAbsolutePosition(50, 0);
                    bottomImg4.setAbsolutePosition(165, 0);
                    bottomImg5.setAbsolutePosition(300, 0);
                    bottomImg6.setAbsolutePosition(450, 0);
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
            }
            document.close();
            //修改打印状态
            int result = iSubassemblyPackService.updateStatus(barcodeIds);
            if(result > 0){
                json.setSuccess(true);
                //访问PDF地址
                pdfPath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+pdfPath;
                json.setMessage(pdfPath);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(json);
    }


    /**
     * 向图片中加入文本内容
     * @param imgurl
     */
    public final static BufferedImage graphicsGenerationByPackage(String imgurl, Map<String,Object> map) {
        int imageWidth = 300;// 图片的宽度
        int imageHeight = 150;// 图片的高度
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new java.awt.Font("微软雅黑", 1, 16));//Arial Black、Kartika
        //工单号
        String wipEntityName = StringUtils.isEmpty(map.get("WIP_ENTITY_NAME")) ? "" : map.get("WIP_ENTITY_NAME").toString();
        //包箱号
        String barcodeText = StringUtils.isEmpty(map.get("BARCODE_TEXT")) ? "" : map.get("BARCODE_TEXT").toString();
        //包装数量
        String packQuantity = StringUtils.isEmpty(map.get("PACK_QUANTITY")) ? "" : map.get("PACK_QUANTITY").toString();
        //当前时间
        String nowDate = map.get("nowDate").toString();
        graphics.drawString("    WO NO : " + wipEntityName, 5, 30);
        graphics.drawString("    P.O : " + barcodeText, 5, 60);
        graphics.drawString("    QTY  : " + packQuantity, 5, 90);
        graphics.drawString("    DATE : " + nowDate, 5, 120);
        return image;
    }

}
