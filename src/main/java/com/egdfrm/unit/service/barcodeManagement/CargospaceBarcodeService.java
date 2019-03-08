package com.egdfrm.unit.service.barcodeManagement;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.model.expand.PageResult;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.common.WriteForPDF;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.model.standard.MesItemLocattions;
import com.egdfrm.unit.model.standard.MesItemLocattionsCriteria;
import com.egdfrm.unit.service.pagequery.CustomOracelPageResultService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.PdfWriter;
/**
 * 货位管理service类
 * @author hgb
 * @date 2016-12-22
 */
@Service
public class CargospaceBarcodeService extends BaseService{

	@Autowired
	MesItemLocattionsMapper mesItemLocattionsMapper;
	@Autowired
	CustomOracelPageResultService<Object> customOracelPageResultService;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCargospaceBarcode(int limit,int offset,MesItemLocattions mesItemLocattions) throws Exception{

        PageResult pr = customOracelPageResultService.pageQuery(mesItemLocattionsMapper,mesItemLocattions, limit, offset,true);
        Map<String, Object> rv = new HashMap<String, Object>();

        rv.put("total", pr.getTotalRecords());
        rv.put("rows", pr.getRecords());
        return rv;
	}
	
	/**
	 * 根据序列号生成二维码
	 * @param codes
     */
	@RequestMapping("toBarCodeByQRCode")
	@ResponseBody
	public String printCargospaceCode(HttpServletRequest request,@RequestParam("codes[]") String[] codes){
		Json json = new Json();
		List<Map<String,byte[]>> list = new ArrayList<>();
		try{
			if(codes.length == 0){
				json.setMessage("序列号不能为空");
				return JSON.toJSONString(json);
			}
			for (int i = 0; i < codes.length; i++) {
				String barCode = codes[i];
				//根据字符串转二维码
				//BufferedImage image = QRCodeUtil.createImage(codes[i], null, false);
				BufferedImage image = createImage(codes[i], null, false);
				
				//输出到文件
		        //String file = new Random().nextInt(99999999) + ".jpg";
		        //ImageIO.write(image, "bmp", new File(request.getContextPath()+"QR_img_out" + "/" + file));
				//return;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				//如果转换成Jpg格式会造成图像有多余斑点
				ImageIO.write(image, "bmp", out);
				byte[] b = out.toByteArray();
				Map<String,byte[]> map = new LinkedHashMap<>();
				map.put(barCode,b);
				list.add(map);
				if(out != null){
					out.close();
				}
			}
			//String path = WriteForPDF.writePDF(request,list);
			String path =writePDF(request,list);
			if(!StringUtils.isEmpty(path)){
				json.setSuccess(true);
				path = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+path;
				json.setMessage(path);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
	
	
	private static final String CHARSET = "utf-8";
    
    // 二维码尺寸  
    private static final int QRCODE_SIZE = 120;
//    private static final int QRCODE_SIZE = 12;
    
	/**
     * 二维码生成
     * @param content 待生成二维码的数据
     * @param imgPath logo的路径
     * @param needCompress 是否压缩
     * @return
     * @throws Exception
     */
    public static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
//        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
//                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        return image;
    }
    
    

	/**
	 * 创建pdf文件,并把图片放入PDF文件中
	 * 
	 * @param list
	 *            图片流
	 * @throws Exception
	 */
	public static String writePDF(HttpServletRequest request,List<Map<String, byte[]>> list) {
		//得到路径
		String realPath = "pdf/";
		try {
			String absolutely_path = request.getServletContext().getRealPath(
					"/");
			absolutely_path = absolutely_path + realPath;
			//创建目录
			File file = new File(absolutely_path);
			if (!file.exists()) {
				file.mkdirs();
			}
			//取得随机文件名
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = sdf.format(Calendar.getInstance().getTime())
					+ new Random().nextInt(999999) + ".pdf";
			absolutely_path = absolutely_path + "/" + fileName;
			// 自定义纸张大小
			//因为在谷歌浏览器中勾中了适合页面大小，原先的值打印正常，但在火狐浏览器中不使用适合页面大小，所以需要调整大小为原来的3倍左右
			float paperWidth=148F*3;
			float paperHeight=210F*3;
			Rectangle A5 = new RectangleReadOnly(paperWidth, paperHeight);
			//字体大小
			int fontSize=20*3;
			
			// 图片大小
			int picScaleWidth=100*3;
			int picScaleHeight=picScaleWidth;
			// 创建文档,设置页面大小,左、右、上和下页边距。
			int spaceLeft=((int)paperWidth-picScaleWidth)/2;
			int spaceRight=spaceLeft;
			int spaceTop=((int)paperHeight-(picScaleHeight+fontSize))/2;
			//字跑到下一行的时候调整这个值//要刚刚好才行，有误差则不是跳到下一行就是有2行的字在一起，或者最后一个码不显示，所以要不断微调
			//如果是第一行有两行字重叠，则把正值增大
			//int spaceBottom=(int)(spaceTop-25);
			//因为在谷歌浏览器中勾中了适合页面大小，原先的值打印正常，但在火狐浏览器中不使用适合页面大小，所以需要调整大小为原来的3倍左右，此处需要在后面加数字，而不是减//这里spaceBottom=150
			int spaceBottom=(int)(spaceTop+15);
			
			
			Document document = new Document(A5, spaceLeft, spaceRight, spaceTop, spaceBottom);
			// document是创建的文档,out是输出
			PdfWriter.getInstance(document, new FileOutputStream(
					absolutely_path));
			document.open();
			int listSize = list.size();
			//货位条码一行只打印一个//所以这里为1
			int page = new BigDecimal(listSize).divide(new BigDecimal(1),
					BigDecimal.ROUND_CEILING).intValue();
			for (int i = 0; i < page; i++) {
				Paragraph pg1 = new Paragraph();
				Font font = FontFactory.getFont(FontFactory.defaultEncoding,fontSize,
						Font.COURIER, new Color(0, 0, 0));
				/*************** 第一张二维码 *****************/
				//货位条码一行只打印一个
				//int indexOne = i * 2;
				int indexOne = i;
				if (indexOne < listSize) {
					Map<String, byte[]> mapOne = list.get(indexOne);
					Map.Entry<String, byte[]> entryOne = mapOne.entrySet()
							.iterator().next();
					String codeOne = entryOne.getKey();
					byte[] bOne = entryOne.getValue();
					// 添加图片
					Image backImgOne = Image.getInstance(bOne);
					// 图片大小
					backImgOne.scaleAbsolute(picScaleWidth, picScaleHeight);
					// 控制图片位置
					//此处设置同一位置会导致图片重叠
					//用调整页边距的方式调整位置
					//backImgOne.setAbsolutePosition(1, 25);
					
					Chunk chunk1 = new Chunk(codeOne, font);
					
					//计算出字的位置
					//字体的图像大小值比字体值大点，算出来的偏移位置才能居中//20号字这里字图像宽度就是10
					//要刚刚好才行，有误差则不同的字数的字偏移量不一样
					int fontOffset=((int)paperWidth-spaceLeft-spaceRight-(codeOne.length()*(10*3)))/2;
					
					document.add(backImgOne);
					
					//加个空行
					Chunk chunkNull = new Chunk("                  ", font);
					pg1.add(chunkNull);
					
					pg1.setIndentationLeft(fontOffset);
					pg1.add(chunk1);
					
				}
//				/*************** 第二张二维码 *****************/
//				int indexTwo = i * 2 + 1;
//				if (indexTwo < listSize) {
//					Map<String, byte[]> mapTwo = list.get(indexTwo);
//					Map.Entry<String, byte[]> entryTwo = mapTwo.entrySet()
//							.iterator().next();
//					String codeTwo = entryTwo.getKey();
//					byte[] bTwo = entryTwo.getValue();
//					Image backImgTwo = Image.getInstance(bTwo);
//					// 图片大小
//					backImgTwo.scaleAbsolute(36, 36);
//					// 控制图片位置
//					backImgTwo.setAbsolutePosition(53, 25);
//					document.add(backImgTwo);
//					Chunk chunkNull = new Chunk("                  ", font);
//					Chunk chunk2 = new Chunk(codeTwo, font);
//					pg.add(chunkNull);
//					pg.add(chunk2);
//				}
				/*************** 二维码序列号 *****************/
				// 左边距
				//pg.setIndentationLeft(3);
				
				document.add(pg1);
			}
			// 关闭文档
			document.close();
			realPath += fileName;
		} catch (Exception ee) {
			ee.printStackTrace();
			realPath = null;
		}
		return realPath;
	}
	
	
}
