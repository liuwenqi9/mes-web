package com.egdfrm.unit.common;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by tyq on 17/3/17.
 */
public class CommonUtils {

    /**
     * 创建PDF
     * @param request
     * @param urx PDF宽度
     * @param ury PDF高度
     * @param marginLeft 左边距
     * @param marginRight 右边距
     * @param marginTop 上边距
     * @param marginBottom
     * @return
     * @throws Exception
     */
    public static Map<String,Object> getDocument(HttpServletRequest request,float urx,float ury,int marginLeft,int marginRight,int marginTop,int marginBottom) throws Exception{
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
        Rectangle A5 = new RectangleReadOnly(urx, ury);
        Document document = new Document(A5, marginLeft, marginRight, marginTop, marginBottom);
        //document是创建的文档,out是输出
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
        //PDF路径
        realPath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+realPath+fileName;
        Map<String,Object> map = new HashMap<>();
        map.put("document",document);
        map.put("path",realPath);
        map.put("pdfWriter",pdfWriter);
        return map;
    }
}
