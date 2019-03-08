package com.egdfrm.unit.common;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * pdf创建
 */
public class WriteForPDF {

    /**
     * 创建pdf文件,并把图片放入PDF文件中
     * 产品条码
     * @param list 图片流
     * @throws Exception
     */
    public static String writePDF(HttpServletRequest request,List<Map<String,byte[]>> list){
        String realPath = "pdf/";
        try {
            String absolutely_path = request.getServletContext().getRealPath("/");
            absolutely_path = absolutely_path + realPath;
            File file = new File(absolutely_path);
            if(!file.exists()){
                file.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = sdf.format(Calendar.getInstance().getTime()) + new Random().nextInt(999999) + ".pdf";
            absolutely_path = absolutely_path + "/" + fileName;
            //自定义纸张大小
            Rectangle A10 = new RectangleReadOnly(105.0F, 60.0F);
            //创建文档,设置页面大小,左、右、上和下页边距。
            Document document = new Document(A10, 0, 0, 36, -10);
            //document是创建的文档,out是输出
            PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
            document.open();
            for (int i = 0; i < list.size(); i++) {
                Paragraph pg = new Paragraph();
                Map<String,byte[]> map = list.get(i);
                Map.Entry<String, byte[]> entryOne = map.entrySet().iterator().next();
                String code = entryOne.getKey();
                byte[] b = entryOne.getValue();
                Font font = FontFactory.getFont(FontFactory.defaultEncoding, 7, Font.BOLD, new Color(0, 0, 0));
                /*************** 第一张二维码 *****************/
                //添加图片
                Image backImgOne = Image.getInstance(b);
                //图片大小
                backImgOne.scaleAbsolute(42, 42);
                //控制图片位置
                backImgOne.setAbsolutePosition(4, 14);
                document.add(backImgOne);
                Chunk chunk1 = new Chunk(code,font);
                pg.add(chunk1);
                /*************** 第二张二维码 *****************/
                Image backImgTwo = Image.getInstance(b);
                //图片大小
                backImgTwo.scaleAbsolute(42, 42);
                //控制图片位置
                backImgTwo.setAbsolutePosition(61, 14);
                document.add(backImgTwo);
                Chunk chunkNull = new Chunk("        ",font);
                Chunk chunk2 = new Chunk(code,font);
                pg.add(chunkNull);
                pg.add(chunk2);
                /*************** 二维码序列号 *****************/
                //左边距
                pg.setIndentationLeft(5);
                document.add(pg);
            }
            //关闭文档
            document.close();
            realPath += fileName;
        } catch (Exception ee) {
            ee.printStackTrace();
            realPath = null;
        }
        return realPath;
    }

    /**
     * (一份)创建pdf文件,并把图片放入PDF文件中
     * 产品条码
     * @param list 图片流
     * @throws Exception
     */
    public static String writePDF1(HttpServletRequest request,List<Map<String,byte[]>> list){
        String realPath = "pdf/";
        try {
            String absolutely_path = request.getServletContext().getRealPath("/");
            absolutely_path = absolutely_path + realPath;
            File file = new File(absolutely_path);
            if(!file.exists()){
                file.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = sdf.format(Calendar.getInstance().getTime()) + new Random().nextInt(999999) + ".pdf";
            absolutely_path = absolutely_path + "/" + fileName;
            //自定义纸张大小
            Rectangle A10 = new RectangleReadOnly(105.0F, 60.0F);
            //创建文档,设置页面大小,左、右、上和下页边距。
            Document document = new Document(A10, 0, 0, 36, -10);
            //document是创建的文档,out是输出
            PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
            document.open();
            for (int i = 0; i < list.size(); i++) {
                Paragraph pg = new Paragraph();
                Map<String,byte[]> map = list.get(i);
                Map.Entry<String, byte[]> entryOne = map.entrySet().iterator().next();
                String code = entryOne.getKey();
                byte[] b = entryOne.getValue();
                Font font = FontFactory.getFont(FontFactory.defaultEncoding, 7, Font.BOLD, new Color(0, 0, 0));
                /*************** 第一张二维码 *****************/
                //添加图片
                Image backImgOne = Image.getInstance(b);
                //图片大小
                backImgOne.scaleAbsolute(42, 42);
                //控制图片位置
                backImgOne.setAbsolutePosition(4, 14);
                document.add(backImgOne);
                Chunk chunk1 = new Chunk(code,font);
                pg.add(chunk1);
                /*************** 第二张二维码 *****************/
                i++;//i+1，表示一次 循环两个list数据
                if(list.size()>i){
                	Map<String,byte[]> map2 = list.get(i);
                	Map.Entry<String, byte[]> entryOne2 = map2.entrySet().iterator().next();
                	String code2 = entryOne2.getKey();
                	byte[] b2 = entryOne2.getValue(); 
                	Image backImgTwo = Image.getInstance(b2);
                	//图片大小
                	backImgTwo.scaleAbsolute(42, 42);
                	//控制图片位置
                	backImgTwo.setAbsolutePosition(61, 14);
                	document.add(backImgTwo);
                	Chunk chunkNull = new Chunk("        ",font);
                	Chunk chunk2 = new Chunk(code2,font);
                	pg.add(chunkNull);
                	pg.add(chunk2); 
                }
                /*************** 二维码序列号 *****************/
                //左边距
                pg.setIndentationLeft(5);
                document.add(pg);
            }
            //关闭文档
            document.close();
            realPath += fileName;
        } catch (Exception ee) {
            ee.printStackTrace();
            realPath = null;
        }
        return realPath;
    }
    
    /**
     * 创建pdf文件,并把图片放入PDF文件中
     * 产品条码 Minipa
     * @param list 图片流
     * @throws Exception
     * @author hgb
     * @date  2017-07-17
     */
    public static String writePDFMinipa(HttpServletRequest request,List<Map<String,byte[]>> list){
        String realPath = "pdf/";
        try {
            String absolutely_path = request.getServletContext().getRealPath("/");
            absolutely_path = absolutely_path + realPath;
            File file = new File(absolutely_path);
            if(!file.exists()){
                file.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = sdf.format(Calendar.getInstance().getTime()) + new Random().nextInt(999999) + ".pdf";
            absolutely_path = absolutely_path + "/" + fileName;
            //自定义纸张大小
            Rectangle A10 = new RectangleReadOnly(105.0F, 60.0F);
            //创建文档,设置页面大小,左、右、上和下页边距。
            Document document = new Document(A10, 0, 0, 36, -10);
            //document是创建的文档,out是输出
            PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
            document.open();
            for (int i = 0; i < list.size(); i++) { 
                Map<String,byte[]> map = list.get(i);
                Map.Entry<String, byte[]> entryOne = map.entrySet().iterator().next();
                String code = entryOne.getKey();
                byte[] b = entryOne.getValue();
                Font font = FontFactory.getFont(FontFactory.defaultEncoding, 7, Font.BOLD, new Color(0, 0, 0));
                /**************** pdf 最上边的 文本 ***************/
                String text = "made in china";
                float locationTop = 40 ; 
                Paragraph pg = new Paragraph();//段落
                Font font2 = FontFactory.getFont(FontFactory.defaultEncoding, 6, Font.BOLD, new Color(0, 0, 0));
                Phrase phrase = new Phrase();//块
                Chunk chunk = new Chunk(" "+text,font2);
                chunk.setTextRise(locationTop);
                phrase.add(chunk);
                Chunk chunk1 = new Chunk("        ",font2);
                chunk1.setTextRise(locationTop);
                phrase.add(chunk1);
                Chunk chunk2 = new Chunk(text,font2);
                chunk2.setTextRise(locationTop);
                phrase.add(chunk2); 
                 
                /**************** pdf 最上边的 文本 ***************/
                 
                /*************** 第一张二维码 *****************/
               //添加图片
                Image backImgOne = Image.getInstance(b);
                //图片大小
                backImgOne.scaleAbsolute(39, 39);
                //控制图片位置
                backImgOne.setAbsolutePosition(8, 10);
                document.add(backImgOne); 
                
                /*************** 第二张二维码 *****************/
                 Image backImgTwo = Image.getInstance(b);
                //图片大小
                backImgTwo.scaleAbsolute(39,39);
                //控制图片位置
                backImgTwo.setAbsolutePosition(62, 10);
                document.add(backImgTwo); 
                /*************** 二维码序列号 *****************/
                
                /***************pdf 最下边的 文本 *****************/  
                phrase.add("\n");//换行
                float locattionBottom = 15; 
                Chunk ch = new Chunk(code,font);
                ch.setTextRise(locattionBottom);
                phrase.add(ch); 
                Chunk chNull = new Chunk("       ",font); 
                chNull.setTextRise(locattionBottom);
                phrase.add(chNull); 
                Chunk ch2 = new Chunk(code,font);
                ch2.setTextRise(locattionBottom); 
                phrase.add(ch2);
                pg.add(phrase);
                //左边距
                pg.setIndentationLeft(5);
                document.add(pg); 
                /***************pdf 最下边的 文本 *****************/
                
            }
            //关闭文档
            document.close();
            realPath += fileName;
        } catch (Exception ee) {
            ee.printStackTrace();
            realPath = null;
        }
        return realPath;
    }
    /**
     * 包装条码
     * 创建pdf文件,并把图片放入PDF文件中_new
     * @return
     */
    public final static String writePdfToPackNew(HttpServletRequest request,List<Map<String,byte[]>> list,List<byte[]> dataBytes,int copies){
        String realPath = "pdf/";
        try {
            String absolutely_path = request.getServletContext().getRealPath("/");
            absolutely_path = absolutely_path + realPath;
            File file = new File(absolutely_path);
            if(!file.exists()){
                file.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = sdf.format(Calendar.getInstance().getTime()) + new Random().nextInt(999999) + ".pdf";
            absolutely_path = absolutely_path + "/" + fileName;
            //自定义纸张大小
            Rectangle customPageSize = new RectangleReadOnly(350.0F, 175.0F);
            //创建文档,设置页面大小,左、右、上和下页边距。获取纸张大小：PageSize.A5
            Document document = new Document(customPageSize, 0, 0, 130, -10);
            //document是创建的文档,out是输出
            PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
            document.open();
            for (int i = 0; i < list.size(); i++) {
                for(int j=0;j < copies;j++) {
                    Paragraph pg = new Paragraph();
                    Font font = FontFactory.getFont(FontFactory.defaultEncoding, 14, Font.BOLD, new Color(0, 0, 0));
                    /************** 第一张图片 ****************/
                    //添加图片
                    Image backImgOne = Image.getInstance(dataBytes.get(i));
                    //图片大小
                    backImgOne.scaleAbsolute(180, 175);
                    //控制图片位置
                    backImgOne.setAbsolutePosition(10, -5);
                    document.add(backImgOne);
                    /************** 第二张二维码 ****************/
                    Map<String, byte[]> mapTwo = list.get(i);
                    Map.Entry<String, byte[]> entryTwo = mapTwo.entrySet().iterator().next();
                    String codeTwo = entryTwo.getKey();
                    byte[] bTwo = entryTwo.getValue();
                    Image backImgTwo = Image.getInstance(bTwo);
                    //图片大小
                    backImgTwo.scaleAbsolute(110, 110);
                    //控制图片位置
                    backImgTwo.setAbsolutePosition(220, 40);
                    document.add(backImgTwo);
                    Chunk chunk2 = new Chunk(codeTwo, font);
                    pg.add(chunk2);
                    /************** 二维码序列号 ****************/
                    //左边距
                    pg.setIndentationLeft(230);
                    document.add(pg);
                    document.newPage();
                }
            }
            //关闭文档
            document.close();
            realPath += fileName;
        } catch (Exception ee) {
            ee.printStackTrace();
            realPath = null;
        }
        return realPath;
    }

    /**
     * 包装条码
     * 创建pdf文件,并把图片放入PDF文件中
     * @param request
     * @param list
     * @return
     */
    public static String writePdfToPack(HttpServletRequest request,List<Map<String,byte[]>> list){
        String realPath = "pdf/";
        try {
            String absolutely_path = request.getServletContext().getRealPath("/");
            absolutely_path = absolutely_path + realPath;
            File file = new File(absolutely_path);
            if(!file.exists()){
                file.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = sdf.format(Calendar.getInstance().getTime()) + new Random().nextInt(999999) + ".pdf";
            absolutely_path = absolutely_path + "/" + fileName;
            //自定义纸张大小
            Rectangle A10 = new RectangleReadOnly(265.0F, 165.0F);
            //创建文档,设置页面大小,左、右、上和下页边距。
            Document document = new Document(A10, 0, 0, 128, -20);
            //document是创建的文档,out是输出
            PdfWriter.getInstance(document, new FileOutputStream(absolutely_path));
            document.open();
            int listSize = list.size();
            int page = new BigDecimal(listSize).divide(new BigDecimal(2),BigDecimal.ROUND_CEILING).intValue();
            for (int i = 0; i < page; i++) {
                Paragraph pg = new Paragraph();
                Font font = FontFactory.getFont(FontFactory.defaultEncoding, 14, Font.BOLD, new Color(0, 0, 0));
                /************** 第一张二维码 ****************/
                int indexOne = i * 2;
                if(indexOne < listSize){
                    Map<String,byte[]> mapOne = list.get(indexOne);
                    Map.Entry<String, byte[]> entryOne = mapOne.entrySet().iterator().next();
                    String codeOne = entryOne.getKey();
                    byte[] bOne = entryOne.getValue();
                    //添加图片
                    Image backImgOne = Image.getInstance(bOne);
                    //图片大小
                    backImgOne.scaleAbsolute(110, 110);
                    //控制图片位置
                    backImgOne.setAbsolutePosition(10, 32);
                    document.add(backImgOne);
                    Chunk chunk1 = new Chunk(codeOne,font);
                    pg.add(chunk1);
                }
                /************** 第二张二维码 ****************/
                int indexTwo = i * 2 + 1;
                if(indexTwo < listSize){
                    Map<String,byte[]> mapTwo = list.get(indexTwo);
                    Map.Entry<String, byte[]> entryTwo = mapTwo.entrySet().iterator().next();
                    String codeTwo = entryTwo.getKey();
                    byte[] bTwo = entryTwo.getValue();
                    Image backImgTwo = Image.getInstance(bTwo);
                    //图片大小
                    backImgTwo.scaleAbsolute(110, 110);
                    //控制图片位置
                    backImgTwo.setAbsolutePosition(145, 32);
                    document.add(backImgTwo);
                    Chunk chunkNull = new Chunk("             ",font);
                    Chunk chunk2 = new Chunk(codeTwo,font);
                    pg.add(chunkNull);
                    pg.add(chunk2);
                }
                /************** 二维码序列号 ****************/
                //左边距
                pg.setIndentationLeft(20);
                document.add(pg);
                document.newPage();
            }
            //关闭文档
            document.close();
            realPath += fileName;
        } catch (Exception ee) {
            ee.printStackTrace();
            realPath = null;
        }
        return realPath;
    }
}