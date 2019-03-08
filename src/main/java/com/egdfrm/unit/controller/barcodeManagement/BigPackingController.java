package com.egdfrm.unit.controller.barcodeManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.common.CreatePdf;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.model.barcodeManagement.BigPacking;
import com.egdfrm.unit.model.barcodeManagement.BigPackingPrint;
import com.egdfrm.unit.model.barcodeManagement.StockInPrintRow;
import com.egdfrm.unit.service.barcodeManagement.BigPackingService;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 大包装箱清单
 */
@Controller
@RequestMapping("bigPackingController")
public class BigPackingController extends BaseController {

    @Autowired
    private BigPackingService bigPackingService;

    @RequestMapping("init")
    public String init() {
        return "unit/barcodeManagement/big_packing_list";
    }

    /**
     * 大包装箱清单 分页查询
     *
     * @param page       分页条件
     * @param bigPacking 查询条件
     * @return 数据集
     */
    @RequestMapping("findPage")
    @ResponseBody
    public String findPage(Pagination page, BigPacking bigPacking) {
        Pagination pagination = new Pagination(page.getOffset(), page.getLimit());
        page = bigPackingService.findPage(pagination, bigPacking);
        return JSON.toJSONString(page);
    }

    /**
     * 明细查看
     *
     * @param id 大包装箱ID
     * @return
     */
    @RequestMapping("findDetail")
    @ResponseBody
    public String findDetail(@RequestParam("id") String id) {
        List<BigPackingPrint> list = bigPackingService.findDetail(id);
        return JSON.toJSONString(list);
    }


    /**
     * 大包装箱打印
     *
     * @param request
     * @param ids     大包装箱ID
     * @return
     */
    @RequestMapping("printCode")
    @ResponseBody
    public String printCode(HttpServletRequest request, @RequestParam("ids[]") String[] ids) {
        Json json = new Json();
        json.setSuccess(true);
        if (ids == null) {
            json.setSuccess(false);
            json.setMessage("ids为空");
        }
        String pdfPath = createPdf(request, ids);
        json.setMessage(pdfPath);
        return JSON.toJSONString(json);
    }

    public final String createPdf(HttpServletRequest request, String[] ids) {
        String pdfPath = null;
        try {
            List<BigPackingPrint> listRows = bigPackingService.findPrint(ids);
            //表头
            String[] head = {"大包装箱", "大包装数量", "小包装箱", "产品型号", "物料编码", "小包装数量"};
            float[] fs = {100, 85, 100, 100, 100, 85};
            //每页数据条数
            int pageSize = 15;
            //数据集总条数
            int count = listRows.size();
            //分页
            int pages = new BigDecimal(count).divide(new BigDecimal(pageSize), BigDecimal.ROUND_CEILING).intValue();
            //创建Document
            Map<String, Object> objectMap = getDocument(request, 10, 10, 10, 0);
            Document document = (Document) objectMap.get("document");
            pdfPath = String.valueOf(objectMap.get("path"));
            document.open();
            for (int i = 0; i < pages; i++) {
                //添加页首信息
                String rootPath = request.getServletContext().getRealPath("/");
                BufferedImage image = QRCodeUtil.createImage(listRows.get(0).getBig_barcode_text(),500, null, true);
                ByteArrayOutputStream  out = new ByteArrayOutputStream();
                ImageIO.write(image, "png", out);
                byte[] b = out.toByteArray();
                //添加图片
                Image backImgOne = Image.getInstance(b);

                //图片大小
                backImgOne.scaleAbsolute(50, 50);
                //控制图片位置
                backImgOne.setAbsolutePosition(520, 365);
                /*************************为表格添加数据 start************************/
                //获取数据最大下标
                int index = (i + 1) * pageSize;
                if (count < index) {
                    index = count;
                }
                List<BigPackingPrint> rows = new ArrayList<BigPackingPrint>(30);
                for (int j = i * pageSize; j < index; j++) {
                    rows.add(listRows.get(j));
                }
                CreatePdf.bigPackTableData(document, objectMap, head, fs, rows);
                /*************************为表格添加数据 end************************/
                document.add(backImgOne);
                document.newPage();
                document.close();
                //访问PDF地址
                pdfPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + pdfPath;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfPath;
    }


    /**
     * 创建PDF
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, Object> getDocument(HttpServletRequest request, float marginLeft, float marginRight, float marginTop, float marginBottom) throws Exception {
        //生成PDF目录
        String realPath = "pdf/";
        //项目根目录(绝对路径)
        String absolutely_path = request.getServletContext().getRealPath("/");
        //pdf相对于项目的地址
        absolutely_path = absolutely_path + realPath;
        File file = new File(absolutely_path);
        if (!file.exists()) {
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
        Map<String, Object> map = new HashMap<>();
        map.put("document", document);
        map.put("path", realPath);
        map.put("pdfWriter", pdfWriter);
        return map;
    }
}
