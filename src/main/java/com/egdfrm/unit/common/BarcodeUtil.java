package com.egdfrm.unit.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 

import javax.imageio.ImageIO;
 
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * 条形码工具类
 * 
 *@author	hgb
 */
public class BarcodeUtil {
	
	/** 
     * @param msg 条形码文本（适用于报检单号）
     * @param displayText  是否显示文本
     */
    public static byte[] generate(String msg,boolean displayText) {
         
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        try {
			ImageIO.write(generateBarcodeImage(msg ,displayText), "png", ous);
			return ous.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        return null;
    }
    /**
     * 生成图片到文件中
     * @param msg 
     * @param path
     * @return
     */
    public static void generateFile(String msg,String path){
    	File file = new File(path);
    	FileOutputStream os = null;
			try {
				os =  new FileOutputStream(file);
				os.write(generate(msg,false));
				 
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					os.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		  
    	
    }
   
    /** 
     * @param msg 条形码文本(适用于报检单号)
     * @param displayText  是否显示文本
     */
    public static BufferedImage generateBarcodeImage(String msg,boolean displayText){
    	 
    	Code39Bean bean = new Code39Bean();
    	 // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
    	// 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);
        bean.doQuietZone(false);//两边无空白
        bean.setBarHeight(4);
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);       
        bean.generateBarcode(canvas, msg);
        BufferedImage image = canvas.getBufferedImage(); 
        if(displayText){
        	 return image;
        }
        return image.getSubimage(0, 0, 160, 23); 
    }
    
    /**
     *  生成入库单条形码（只用于入库单号）
     * @author	hgb
     * @date 2017-2-17
     */
    public static byte[] generateStockNum(String msg){
    	 
    	Code39Bean bean = new Code39Bean();
    	 // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
    	// 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);
        bean.doQuietZone(false);//两边无空白
        bean.setBarHeight(4);
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);       
        bean.generateBarcode(canvas, msg);
        BufferedImage image = canvas.getBufferedImage(); 
        image = image.getSubimage(0, 0, 208, 23);
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        try {
			ImageIO.write(image,"png",ous);
			return ous.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
    	
    }
    
    public static void main(String[] args) {
        String msg = "20170217012";
        String path = "C:barcode.png";
        
        generateFile(msg, path);
    }
}
