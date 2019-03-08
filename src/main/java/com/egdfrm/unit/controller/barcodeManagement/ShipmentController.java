package com.egdfrm.unit.controller.barcodeManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.model.barcodeManagement.Shipment;
import com.egdfrm.unit.model.barcodeManagement.ShipmentPrint;
import com.egdfrm.unit.service.barcodeManagement.IShipmentService;
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 发运清单条码管理_控制器
 * Created by tyq on 17/1/19.
 */
@Controller
@RequestMapping("shipment")
public class ShipmentController {


    @Autowired
    private IShipmentService iShipmentService;

    /**
     * 跳转到发运清单条码管理页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/barcodeManagement/shipment_manage";
    }

    /**
     * 发运清单分页查询
     * @param page 分页条件
     * @param shipment 查询条件
     * @return 数据集
     */
    @RequestMapping("findPage")
    @ResponseBody
    public String findPage(Pagination page, Shipment shipment){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        shipment.setLogistics(Utils.unicodeToString(shipment.getLogistics()));
        page = iShipmentService.findPage(pagination,shipment);
        return JSON.toJSONString(page);
    }


    /**
     * 条码打印
     * @param request
     * @param codes 包装条码
     * @param citys 目的城市
     * @return
     */
    @RequestMapping("printCode")
    @ResponseBody
    public String printCode(HttpServletRequest request,@RequestParam("codes[]") String[] codes,@RequestParam("citys[]") String[] citys,@RequestParam("names[]") String[] names){
        Json json = new Json();
        try {
            //创建Document
            Map<String, Object> objectMap = getDocument(request);
            Document document = (Document)objectMap.get("document");
            String pdfPath = String.valueOf(objectMap.get("path"));
            //每列宽度
            float[] fs = {130,100,90};
            //表头
            String[] head = {"产品编码","型号","数量"};
            document.open();
            for (int i = 0; i < codes.length; i++) {
                String code = codes[i];
                String city = citys[i];
                String name = names[i];
                //根据条码查询打印数据
                List<ShipmentPrint> list =  iShipmentService.getCodeByData(code);
                Map<String,Object> hzMap = new HashMap<>();
                hzMap.put("number",code);
                hzMap.put("city", city);
                hzMap.put("accountName", name);
                //每页数据条数
                int pageSize = 8;
                //数据集总条数
                int count = list.size();
                //分页
                int pages = new BigDecimal(count).divide(new BigDecimal(pageSize),BigDecimal.ROUND_CEILING).intValue();
                if (pages == 0){
                    pages = 1;
                }
                for (int j = 0; j < pages; j++) {
                    List<ShipmentPrint> listRows = new ArrayList<>();
                    //获取数据最大下标
                    int index = (j+1) * pageSize;
                    if(count < index){
                        index = count;
                    }
                    for (int k = j*pageSize; k < index; k++) {
                        listRows.add(list.get(k));
                    }
                    //创建打印PDF
                    CreatePdf.shipmentTableData(document,hzMap,head,fs,listRows);
                    document.newPage();
                }
            }
            document.close();
            //访问PDF地址
            pdfPath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+pdfPath;
            json.setMessage(pdfPath);
            json.setSuccess(true);
            //修改打印状态
            iShipmentService.updatePrintState(codes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(json);
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
        //自定义纸张大小
        Rectangle customPageSize = new RectangleReadOnly(350.0F, 350.0F);
        Document document = new Document(customPageSize, 10, 10, 0, 0);
        //document是创建的文档,out是输出
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
        Map<String,Object> map = new HashMap<>();
        map.put("document",document);
        map.put("path",realPath);
        return map;
    }
}
