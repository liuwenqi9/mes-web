package com.egdfrm.unit.common;

import com.egdfrm.unit.model.AfterSaleManagement.AfterSaleBack;
import com.egdfrm.unit.model.barcodeManagement.BigPackingPrint;
import com.egdfrm.unit.model.barcodeManagement.ShipmentPrint;
import com.egdfrm.unit.model.barcodeManagement.StockInPrintRow;
import com.egdfrm.unit.model.stock.SeizeAnOpportunity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 创建PDF
 * Created by tyq on 17/1/13.
 */
public class CreatePdf {
	private static Logger log = LoggerFactory.getLogger(CreatePdf.class);

    private static Font headfont;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小
    private static BaseFont bfChinese = null;

    static {
        try {
            // 设置中文显示
            bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 14, Font.BOLD);// 设置字体大小
            keyfont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
            textfont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * 为表格添加一个内容
     *
     * @param value 值
     * @param font  字体
     * @return 添加的文本框
     */
    public static PdfPCell createCell(String value, Font font,int cellHeight,int verticalAlignment,int horizontalAlignment) {
        PdfPCell cell = new PdfPCell();
        //垂直排列规则 Element.ALIGN_MIDDLE
        cell.setVerticalAlignment(verticalAlignment);
        //水平排列规则 Element.ALIGN_CENTER
        cell.setHorizontalAlignment(horizontalAlignment);
        //设置cell高度 当高度=0时，表示自适应高度
        cell.setFixedHeight(cellHeight);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 为表格添加一个内容
     *
     * @param value     值
     * @param font      字体
     * @param align     对齐方式
     * @param colspan   占多少列
     * @param boderFlag 是否有有边框
     * @return 添加的文本框
     */
    public static PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    /**
     * 创建一个表格对象
     *
     * @param fs 每列表格的宽度
     * @return 生成的表格对象
     */
    public static PdfPTable createTable(float[] fs) {
        PdfPTable table = new PdfPTable(fs.length);
        try {
            //设置table的总宽度
            //table.setTotalWidth(400);
            //设置每列宽度
            table.setTotalWidth(fs);
            //锁定宽度
            table.setLockedWidth(true);
            //数据居中显示
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 创建表格并添加数据
     * 仅提供入库单打印使用
     * @param document 文档对象
     * @param cMap 汇总数据
     * @param head 头数据
     * @param fs 每列宽度
     * @param list 数据集
     * @throws Exception
     */
    public static void pdfAddTableData(Document document, Map<String,Object> cMap,String[] head, float[] fs, List<StockInPrintRow> list) throws Exception {
        // 创建一个只有head.length列的表格
        PdfPTable table = createTable(fs);
        // 标题，居中，不显示边框
        table.addCell(createCell("入库通知单", headfont, Element.ALIGN_CENTER, head.length, false));
        //汇总数据
        table.addCell(createCell("编号: "+cMap.get("INV_NUMBER"), keyfont, Element.ALIGN_LEFT, 2, false));
        table.addCell(createCell("子库: "+cMap.get("SUBINVENTORY_CODE")+"("+cMap.get("SUBINVENTORY_DESC")+")", keyfont, Element.ALIGN_LEFT, 3, false)); 
        table.addCell(createCell("总箱数: "+cMap.get("PACK_QTY"), keyfont, Element.ALIGN_CENTER, 2, false));
        table.addCell(createCell("日期: "+cMap.get("sysDate"), keyfont, Element.ALIGN_CENTER, 3, false));
        //设置表头
        for (int i = 0; i < head.length; i++) {
            table.addCell(createCell(head[i], keyfont,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
        }
        //填充每个单元格的数据
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                StockInPrintRow sipr = list.get(i);
                log.debug("编号为: "+cMap.get("INV_NUMBER")+"\n"+sipr.toString());
                table.addCell(createCell(sipr.getMo_order(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getWip_entity_name(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getPlan_line(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getSegment2(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getCustomer_type(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getSegment1(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getStart_quantity(),textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getPack_quantity(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(sipr.getLocattion_code(), textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell("", textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
            }
        }
        //将表格添加到文档中
        document.add(table);
    }

    /**
     * 创建表格并添加数据
     * 仅提供发运清单条码打印使用
     * @param document
     * @param head
     * @param list
     * @date 修改于 2017-04-10下午
     * @throws Exception
     */
    public static void shipmentTableData(Document document,Map<String,Object> cMap,String[] head, float[] fs,List<ShipmentPrint> list) throws Exception{
        // 创建一个只有head.length列的表格
        PdfPTable table = createTable(fs);
        /**
         * @date  2017-04-10
         */ 
        Font keyfont1 = new Font(bfChinese, 16, Font.BOLD);// 设置字体大小
        Font textfont1 = new Font(bfChinese, 21, Font.NORMAL);// 设置字体大小
         
        //汇总数据
        table.addCell(createCell(String.valueOf(cMap.get("city")), keyfont1, Element.ALIGN_LEFT, 1, false));
        table.addCell(createCell(String.valueOf(cMap.get("number")), keyfont1, Element.ALIGN_LEFT, 1, false));
        table.addCell(createCell(String.valueOf(cMap.get("accountName")), keyfont1, Element.ALIGN_RIGHT, 1, false));
        //设置表头
        for (int i = 0; i < head.length; i++) {
            table.addCell(createCell(head[i], keyfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
        } 
        //填充每个单元格的数据
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ShipmentPrint ship = list.get(i);
                table.addCell(createCell(ship.getSegment1(), textfont1,33,Element.ALIGN_CENTER,Element.ALIGN_CENTER));
                table.addCell(createCell(ship.getProd_type(), textfont1,33,Element.ALIGN_CENTER,Element.ALIGN_LEFT));
                table.addCell(createCell(String.valueOf(ship.getQuantity()), textfont1,33,Element.ALIGN_CENTER,Element.ALIGN_CENTER));
            }
        }
        //将表格添加到文档中
        document.add(table);
    }

    /**
     * 创建表格并添加数据
     * 仅提供[配件包装]打印使用
     * @param document 文档对象
     * @param cMap 描述信息
     * @param head 头数据
     * @param fs 每列宽度
     * @param list 数据集
     * @throws Exception
     */
    public static void pdfAddTableDataByParts(Document document,Map<String,Object> cMap, String[] head, float[] fs, List<Map<String,Object>> list) throws Exception {
        // 创建一个只有head.length列的表格
        PdfPTable table = createTable(fs);
        //标题
        table.addCell(createCell("非成品配件装箱清单", headfont, Element.ALIGN_CENTER, 3, false));
        //汇总数据
        table.addCell(createCell("工单号: "+cMap.get("WIP_ENTITY_NAME"), keyfont, Element.ALIGN_LEFT, 2, false));
        table.addCell(createCell("包箱号: "+cMap.get("BARCODE_TEXT"), keyfont, Element.ALIGN_CENTER, 1, false));
        //设置表头
        for (int i = 0; i < head.length; i++) {
            table.addCell(createCell(head[i], keyfont,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
        }
        PdfPCell cell = new PdfPCell();
        //垂直排列规则
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        //水平排列规则
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //填充每个单元格的数据
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> map = list.get(i);
                //编码
                String segment1 = StringUtils.isEmpty(map.get("SEGMENT1")) ? "" : map.get("SEGMENT1").toString();
                //数量
                String quantity = StringUtils.isEmpty(map.get("QUANTITY")) ? "" : map.get("QUANTITY").toString();
                //物料描述
                String description = StringUtils.isEmpty(map.get("DESCRIPTION")) ? "" : map.get("DESCRIPTION").toString();
                table.addCell(createCell(segment1, textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(quantity, textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                //设置cell高度
                cell.setFixedHeight(15);
                cell.setPhrase(new Phrase(description, textfont));
                table.addCell(cell);
            }
        }
        //将表格添加到文档中
        document.add(table);
    }

    /**
     * 创建表格并添加数据
     * 仅提供[半成品包装]打印使用
     * @param document 文档对象
     * @param cMap 描述信息
     * @param head 头数据
     * @param fs 每列宽度
     * @param list 数据集
     * @throws Exception
     */
    public static void pdfAddTableDataBySubbassembly(Document document,Map<String,Object> cMap, String[] head, float[] fs, List<Map<String,Object>> list) throws Exception {
        // 创建一个只有head.length列的表格
        PdfPTable table = createTable(fs);
        //标题
        table.addCell(createCell("半成品配件装箱清单", headfont, Element.ALIGN_CENTER, 3, false));
        //汇总数据
        table.addCell(createCell("工单号: "+cMap.get("WIP_ENTITY_NAME"), keyfont, Element.ALIGN_LEFT, 2, false));
        table.addCell(createCell("包箱号: "+cMap.get("BARCODE_TEXT"), keyfont, Element.ALIGN_CENTER, 1, false));
        //设置表头
        for (int i = 0; i < head.length; i++) {
            table.addCell(createCell(head[i], keyfont,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
        }
        PdfPCell cell = new PdfPCell();
        //垂直排列规则
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        //水平排列规则
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //填充每个单元格的数据
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> map = list.get(i);
                //编码
                String segment1 = StringUtils.isEmpty(map.get("SEGMENT1")) ? "" : map.get("SEGMENT1").toString();
                //数量
                String quantity = StringUtils.isEmpty(map.get("QUANTITY")) ? "" : map.get("QUANTITY").toString();
                //物料描述
                String description = StringUtils.isEmpty(map.get("DESCRIPTION")) ? "" : map.get("DESCRIPTION").toString();
                table.addCell(createCell(segment1, textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(quantity, textfont,15,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                //设置cell高度
                cell.setFixedHeight(15);
                cell.setPhrase(new Phrase(description, textfont));
                table.addCell(cell);
            }
        }
        //将表格添加到文档中
        document.add(table);
    }
    
    /**
     * 创建表格并添加数据
     * 仅提借机单打印使用
     * @param document
     * @param head
     * @param list
     * @date 修改于2017-04-10下午
     * @throws Exception
     */
    public static void seizeAnOpportunityTableData(Document document, SeizeAnOpportunity seaoty, String[] head, float[] fs, List<SeizeAnOpportunity> list) throws Exception{
        // 创建一个只有head.length列的表格
        PdfPTable table = createTable(fs);
        /**
         * 修改
         * @date  2017-04-10
         */ 
        Font headfont1 = new Font(bfChinese, 24, Font.BOLD); 
        Font keyfont1 = new Font(bfChinese, 20, Font.BOLD);
        Font textfont1 = new Font(bfChinese, 16, Font.NORMAL); 
        
        String number = StringUtils.isEmpty(seaoty.getNumber()) ? "":seaoty.getNumber();
        String dept = StringUtils.isEmpty(seaoty.getDeptName()) ? "":seaoty.getDeptName();
        String person = StringUtils.isEmpty(seaoty.getPerson()) ? "":seaoty.getPerson();
        String purpose = StringUtils.isEmpty(seaoty.getTypeName()) ? "":seaoty.getTypeName();
        String remark = StringUtils.isEmpty(seaoty.getRemark()) ? "":seaoty.getRemark(); 
        String createTime =  StringUtils.isEmpty(seaoty.getCreateTime())?"":new SimpleDateFormat("yyyy-MM-dd").format(seaoty.getCreateTime());
        
        //借机标题 
        table.addCell(createCell("成品借机单", headfont1, Element.ALIGN_CENTER, head.length, false));
        //借机头信息
        table.addCell(createCell("借机单号: "+number, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("借机部门: "+dept, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("借机时间："+createTime, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("借机人员: "+person, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("借机用途: "+purpose, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("", keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("备注: "+remark, keyfont1, Element.ALIGN_LEFT, 9, false));
        table.addCell(createCell("", keyfont1, Element.ALIGN_LEFT, 9, false));
        //设置表头
        for (int i = 0; i < head.length; i++) {
            table.addCell(createCell(head[i], keyfont1,50,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
        } 
        //填充每个单元格的数据
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                SeizeAnOpportunity seo = list.get(i);
                table.addCell(createCell(seo.getCode(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(seo.getModel(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(seo.getQuantity(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                String returnTime = seo.getReturnTime();
                if(!StringUtils.isEmpty(returnTime)){
                    returnTime = returnTime.substring(0,10);
                }
                table.addCell(createCell(returnTime, textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(seo.getSourceSubLibrary(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(seo.getGoalSubLibrary(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(seo.getGoalLocation(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(seo.getDescribe(), textfont1,0,Element.ALIGN_MIDDLE,Element.ALIGN_LEFT));
            }
        }
        //将表格添加到文档中
        document.add(table);
    }
    
    /**
     * 创建表格并添加数据
     * 仅提维修单打印使用
     * @param document
     * @param head
     * @param list
     * @date 修改于2017-04-10下午
     * @throws Exception
     */
    public static void afterSaleSummaryTableData(Document document, AfterSaleBack back, String[] head, float[] fs, List<AfterSaleBack> list) throws Exception{
        // 创建一个只有head.length列的表格
        PdfPTable table = createTable(fs);
        /**
         * 修改
         * @date  2017-04-10
         */ 
        Font headfont1 = new Font(bfChinese, 24, Font.BOLD); 
        Font keyfont1 = new Font(bfChinese, 20, Font.BOLD);
        Font textfont1 = new Font(bfChinese, 16, Font.NORMAL); 
        //维修单号
        String repWorkOrder = StringUtils.isEmpty(back.getREP_WORK_ORDER()) ? "":back.getREP_WORK_ORDER();
        //客户
        String customName = StringUtils.isEmpty(back.getCUSTOM_NAME()) ? "":back.getCUSTOM_NAME();
        //联系电话
        String phone = StringUtils.isEmpty(back.getPHONE()) ? "":back.getPHONE();
        //收货人
        String contactName = StringUtils.isEmpty(back.getCONTACT_NAME()) ? "":back.getCONTACT_NAME();
        //收货地址
        String address = StringUtils.isEmpty(back.getADDRESS()) ? "":back.getADDRESS();
        
        //日期()退货日期
        String reDate =  StringUtils.isEmpty(back.getRETURN_OPERATION_TIME()) ? "":back.getRETURN_OPERATION_TIME();
        //String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        //维修单标题
        table.addCell(createCell("售后维修服务单", headfont1, Element.ALIGN_CENTER, head.length, false));
        //借机头信息
        table.addCell(createCell("维修单号: "+repWorkOrder, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("收货人: "+contactName, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("日期："+reDate, keyfont1, Element.ALIGN_LEFT, 4, false));
        table.addCell(createCell("联系电话: "+phone, keyfont1, Element.ALIGN_LEFT, 3, false));
        table.addCell(createCell("客户: "+customName, keyfont1, Element.ALIGN_LEFT, 7, false));
        table.addCell(createCell("收货地址 ："+address,keyfont1, Element.ALIGN_LEFT, 10, false)); 
        //设置表头
        for (int i = 0; i < head.length; i++) {
            table.addCell(createCell(head[i], keyfont1,50,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
        } 
        //填充每个单元格的数据
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                AfterSaleBack backRow = list.get(i);
                //序列号
                table.addCell(createCell(String.valueOf(backRow.getID()), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(backRow.getMODEL(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(backRow.getSEGMENT1(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(backRow.getPRODUCT_BARCODE(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                //数量
                table.addCell(createCell(backRow.getRETURN_QTY(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(backRow.getREP_TYPE(), textfont1,30,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
               
                table.addCell(createCell(backRow.getSO_NO(), textfont1,0,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(backRow.getCUSTOMER_FEEDBACK(), textfont1,0,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
                table.addCell(createCell(backRow.getISSUE_DESCRIPTION(), textfont1,0,Element.ALIGN_MIDDLE,Element.ALIGN_LEFT));
                table.addCell(createCell(backRow.getREP_REASON(), textfont1,0,Element.ALIGN_MIDDLE,Element.ALIGN_LEFT));
                
            }
        }
        //将表格添加到文档中
        document.add(table);
    }
    /**
     * 创建表格并添加数据
     * 仅提供大包装清单打印使用
     * @param document
     * @param head
     * @param list
     * @date 修改于 2017-04-10下午
     * @throws Exception
     */
    public static void bigPackTableData(Document document,Map<String,Object> cMap,String[] head, float[] fs,List<BigPackingPrint> list) throws Exception{
        // 创建一个只有head.length列的表格
        PdfPTable table = createTable(fs);

        Font keyfont1 = new Font(bfChinese, 14, Font.BOLD);// 设置字体大小
        Font textfont1 = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小

        table.addCell(createCell("", keyfont1, Element.ALIGN_LEFT, 6, false));
        table.addCell(createCell("", keyfont1, Element.ALIGN_LEFT, 6, false));
      /*  //汇总数据
        table.addCell(createCell(String.valueOf(cMap.get("city")), keyfont1, Element.ALIGN_LEFT, 1, false));
        table.addCell(createCell(String.valueOf(cMap.get("number")), keyfont1, Element.ALIGN_LEFT, 1, false));
        table.addCell(createCell(String.valueOf(cMap.get("accountName")), keyfont1, Element.ALIGN_RIGHT, 1, false));
       */
        //设置表头
        for (int i = 0; i < head.length; i++) {
            table.addCell(createCell(head[i], keyfont1,20,Element.ALIGN_MIDDLE,Element.ALIGN_CENTER));
        }
        //填充每个单元格的数据
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                BigPackingPrint print = list.get(i);
                table.addCell(createCell(print.getBig_barcode_text(), textfont1,18,Element.ALIGN_CENTER,Element.ALIGN_CENTER));
                table.addCell(createCell(print.getBig_pack_quantity(), textfont1,18,Element.ALIGN_CENTER,Element.ALIGN_CENTER));

                table.addCell(createCell(print.getSmall_barcode_text(), textfont1,18,Element.ALIGN_CENTER,Element.ALIGN_CENTER));
                table.addCell(createCell(print.getSmall_prod_type(), textfont1,18,Element.ALIGN_CENTER,Element.ALIGN_CENTER));
                table.addCell(createCell(print.getSmall_segment1(), textfont1,18,Element.ALIGN_CENTER,Element.ALIGN_CENTER));
                table.addCell(createCell(print.getSmall_pack_quantity(), textfont1,18,Element.ALIGN_CENTER,Element.ALIGN_CENTER));
            }
        }
        //将表格添加到文档中
        document.add(table);
    }
    
}
