package com.egdfrm.unit.common;

import com.egdfrm.unit.model.barcodeManagement.CusHeader;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel解析工具类
 * User: 谭永强
 * Date: 2017/01/17
 */
public class ExcelUtils {

    /**
     * 根据不同的文件调用不同的解析方法
     *
     * @param fileName 文件名称
     * @param file     文件
     * @return
     */
    public List<Object[]> getList(String fileName, File file) {
        List<Object[]> list = new ArrayList<>();
        try {
            InputStream input = new FileInputStream(file);
            ExcelUtils excelUtils = new ExcelUtils();
            if (fileName.endsWith(".xlsx")) {
                //2007
                list = excelUtils.excel2007(input);
            } else if (fileName.endsWith(".xls")) {
                //2003
                list = excelUtils.execl2003(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析excel2003
     *
     * @param input 文件流
     */
    public List<Object[]> execl2003(InputStream input) {
        List<Object[]> list = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(input);
            //得到第一页
            HSSFSheet sheet = workbook.getSheetAt(0);
            //获取最大行数
            int rowNum = sheet.getLastRowNum();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i <= rowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                //获取最大列数
                int cellNum = row.getLastCellNum();
                Object[] objects = new Object[cellNum];
                for (int j = 0; j < cellNum; j++) {
                    HSSFCell cell = row.getCell(j);
                    if (cell == null) {
                        objects[j] = "";
                        continue;
                    }
                    int type = cell.getCellType();
                    if (type == XSSFCell.CELL_TYPE_NUMERIC) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            //时间
                            Date date = cell.getDateCellValue();
                            String str = sdf.format(date);
                            objects[j] = str;
                        } else {
                            //数字
                            Double value = cell.getNumericCellValue();
                            objects[j] = value;
                        }
                    } else if (type == XSSFCell.CELL_TYPE_STRING) {
                        //字符串
                        String value = cell.getStringCellValue();
                        objects[j] = value;
                    }
                }
                list.add(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析excel2007
     *
     * @param input 文件流
     */
    public List<Object[]> excel2007(InputStream input) {
        List<Object[]> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            //获取第一章
            XSSFSheet sheet = workbook.getSheetAt(0);
            //获取最大行数
            int rowNum = sheet.getLastRowNum();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i <= rowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                int cellNum = row.getLastCellNum();
                Object[] objects = new Object[cellNum];
                for (int j = 0; j < cellNum; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell == null) {
                        objects[j] = "";
                        continue;
                    }
                    int type = cell.getCellType();
                    if (type == cell.CELL_TYPE_NUMERIC) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            String str_date = sdf.format(date);
                            objects[j] = str_date;
                        } else {
                            //数字
                            Double value = cell.getNumericCellValue();
                            objects[j] = value;
                        }
                    } else if (type == cell.CELL_TYPE_STRING) {
                        //字符串
                        String value = cell.getStringCellValue();
                        objects[j] = value;
                    } else if (type == cell.CELL_TYPE_BLANK) {
                        //空白
                        String value = cell.getStringCellValue();
                        objects[j] = value;
                    } else if (type == cell.CELL_TYPE_BOOLEAN) {
                        //布尔型
                        boolean value = cell.getBooleanCellValue();
                        objects[j] = value;
                    }
                }
                list.add(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取工作薄
     *
     * @param tmpName 模板名称
     */
    public static XSSFWorkbook getWorkBook(HttpServletRequest request, String tmpName) {
        try {
            //获取项目根路径
            String projectPath = request.getSession().getServletContext().getRealPath("/");
            //模板地址
            String temPath = projectPath + "/template/" + tmpName;
            File file = new File(temPath);
            InputStream input = new FileInputStream(file);
            //创建excel2007工作簿
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            input.close();
            return workbook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出Excel设置主体内容
     *
     * @param fileName  保存到本地的文件名称
     * @param list      待写入的数据
     * @param startRow  开始写入行 从0开始
     * @param startCell 开始写入列 从0开始
     * @param index     从实体第几个字段开始去数据 从0开始
     * @return
     */
    public static void exportExcel(HttpServletResponse response, XSSFWorkbook workBook, String fileName, List<?> list, int startRow, int startCell, int index) {
        OutputStream out = null;
        try {
            //设置文件ContentType类型,这样设置会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
            //获取第一页
            XSSFSheet sheet = workBook.getSheetAt(0);
            //样式
            //XSSFCellStyle style = workBook.createCellStyle();
            /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框*/
            for (int i = 0; i < list.size(); i++) {
                //获取行
                XSSFRow row = sheet.getRow(i + startRow);
                if (row == null) {
                    row = sheet.createRow(i + startRow);
                }
                Object object = list.get(i);
                Class cla = list.get(0).getClass();
                Field[] fields = cla.getDeclaredFields();
                for (int j = 0; j < sheet.getRow(1).getLastCellNum(); j++) {
                    int num = j + startCell;
                    XSSFCell cell = row.getCell(num);
                    if (cell == null) {
                        cell = row.createCell(num);
                    }
                    //设置样式
                    //cell.setCellStyle(style);
                    Field field = fields[j + index];
                    //设置压制访问类型检查
                    ReflectionUtils.makeAccessible(field);
                    //使用反射获取数据
                    Object value = ReflectionUtils.getField(field, object);
                    if (value != null && value != "") {
                        Class type = field.getType();
                        if (Long.class.equals(type)) {
                            cell.setCellValue((long) value);
                        } else if (Integer.class.equals(type)) {
                            cell.setCellValue((int) value);
                        } else if (Short.class.equals(type)) {
                            cell.setCellValue((short) value);
                        } else if (Byte.class.equals(type)) {
                            cell.setCellValue((byte) value);
                        } else if (Double.class.equals(type)) {
                            cell.setCellValue((double) value);
                        } else if (Float.class.equals(type)) {
                            cell.setCellValue((float) value);
                        } else if (Boolean.class.equals(type)) {
                            cell.setCellValue((boolean) value);
                        } else if (Date.class.equals(type)) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String date = sdf.format((Date) value);
                            cell.setCellValue(date);
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
            //保存到本地
            out = response.getOutputStream();
            workBook.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出Excel 只适用于导出MO
     *
     * @param fileName  保存到本地的文件名称
     * @param cusHeader 表头
     * @param list1     明细1
     * @param list2     明细2
     * @param startRow  开始写入行 从0开始
     * @param startCell 开始写入列 从0开始
     * @param index     从实体第几个字段开始去数据 从0开始
     * @author hgb
     * @date 2017-6-30
     */
    public static void exportMO(HttpServletResponse response, XSSFWorkbook workBook, String fileName, CusHeader cusHeader,
                                List<?> list1, List<?> list2, int startRow, int startCell, int index) {
        OutputStream out = null;
        try {
            //设置文件ContentType类型,这样设置会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
            //获取第一页
            XSSFSheet sheet = workBook.getSheetAt(0);
            //创建 表头
            //第二行 sheet.getRow(1)   row.getCell(0) 从0 始
            XSSFRow rowHeaders2 = sheet.getRow(1);

            XSSFCell cell_2_1 = rowHeaders2.getCell(1);
            cell_2_1.setCellValue(cusHeader.getCust_po_number());

            //第三行 sheet.getRow(2) row.getCell(0) 从0 始
            XSSFRow rowHeaders3 = sheet.getRow(2);
            XSSFCell cell_3_1 = rowHeaders3.getCell(1);
            XSSFCell cell_3_2 = rowHeaders3.getCell(3);
            cell_3_1.setCellValue(cusHeader.getParty_number());
            cell_3_2.setCellValue(cusHeader.getParty_name());

            //第四行 sheet.getRow(3) row.getCell(0) 从0 始
            XSSFRow rowHeaders4 = sheet.getRow(3);
            XSSFCell cell_4_1 = rowHeaders4.getCell(1);
            XSSFCell cell_4_2 = rowHeaders4.getCell(3);
            XSSFCell cell_4_3 = rowHeaders4.getCell(5);
            XSSFCell cell_4_4 = rowHeaders4.getCell(7);
            cell_4_1.setCellValue(cusHeader.getSop_yh());
            cell_4_2.setCellValue(cusHeader.getYh_date());
            cell_4_3.setCellValue(cusHeader.getSop_rh());
            cell_4_4.setCellValue(cusHeader.getSop_box());

            //第五行 sheet.getRow(4)   row.getCell(0) 从0 始
            XSSFRow rowHeaders5 = sheet.getRow(4);
            XSSFCell cell_5_1 = rowHeaders5.getCell(1);
            XSSFCell cell_5_2 = rowHeaders5.getCell(3);
            XSSFCell cell_5_3 = rowHeaders5.getCell(5);
            XSSFCell cell_5_4 = rowHeaders5.getCell(7);
            cell_5_1.setCellValue(cusHeader.getYour_po());
            cell_5_2.setCellValue(cusHeader.getOur_pi());
            cell_5_3.setCellValue(cusHeader.getSop_battery());
            cell_5_4.setCellValue(cusHeader.getCountry());

            //第六行 sheet.getRow(5) row.getCell(0) 从0 始
            XSSFRow rowHeaders6 = sheet.getRow(5);
            XSSFCell cell_6_1 = rowHeaders6.getCell(1);
            XSSFCell cell_6_2 = rowHeaders6.getCell(3);
            XSSFCell cell_6_3 = rowHeaders6.getCell(5);
            XSSFCell cell_6_4 = rowHeaders6.getCell(7);
            cell_6_1.setCellValue(cusHeader.getPs_number());
            cell_6_2.setCellValue(cusHeader.getPs_version());
            cell_6_3.setCellValue(cusHeader.getVs_date());
            cell_6_4.setCellValue(cusHeader.getCreation_date());

            //第六行 sheet.getRow(6) row.getCell(0) 从0 始
            XSSFRow rowHeaders7 = sheet.getRow(6);
            XSSFCell cell_7_1 = rowHeaders7.getCell(1);
            cell_7_1.setCellValue(cusHeader.getRemark());


            // 循环插入MO要求
            if (list1.size() > 0) {
                if(list1.size()!=1){
                    sheet.shiftRows(startRow, sheet.getLastRowNum(), list1.size() - 1, true, false);
                }
                for (int i = 0; i < list1.size(); i++) {
                    //在 第12 行插入一行(在 12 行后面插入一行)
                    if ((i + 1) != list1.size()) {//最后一行不复制
                        //复制行
                        copyRows(i + startRow - 1, i + startRow, i + startRow - 1, sheet);
                    }
                }
                for (int i = 0; i < list1.size(); i++) {
                    //获取行
                    XSSFRow row = sheet.getRow(i + startRow - 2);
                    if (row == null) {
                        row = sheet.createRow(i + startRow - 2);
                    }
                    Object object = list1.get(i);
                    Class cla = list1.get(0).getClass();
                    Field[] fields = cla.getDeclaredFields();
                    int dd = sheet.getRow(9).getLastCellNum();
                    for (int j = 0; j < 6; j++) {
                        int num = j + startCell;
                        XSSFCell cell = row.getCell(num);
                        if (cell == null) {
                            cell = row.createCell(num);
                        }
                        Field field = fields[j + index];
                        //设置压制访问类型检查
                        ReflectionUtils.makeAccessible(field);
                        //使用反射获取数据
                        Object value = ReflectionUtils.getField(field, object);
                        if (value != null && value != "") {
                            Class type = field.getType();
                            if (Long.class.equals(type)) {
                                cell.setCellValue((long) value);
                            } else if (Integer.class.equals(type)) {
                                cell.setCellValue((int) value);
                            } else if (Short.class.equals(type)) {
                                cell.setCellValue((short) value);
                            } else if (Byte.class.equals(type)) {
                                cell.setCellValue((byte) value);
                            } else if (Double.class.equals(type)) {
                                cell.setCellValue((double) value);
                            } else if (Float.class.equals(type)) {
                                cell.setCellValue((float) value);
                            } else if (Boolean.class.equals(type)) {
                                cell.setCellValue((boolean) value);
                            } else if (Date.class.equals(type)) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String date = sdf.format((Date) value);
                                cell.setCellValue(date);
                            } else {
                                cell.setCellValue(value.toString());
                            }
                        } else {
                            cell.setCellValue("");
                        }
                    }
                }
            }

            //启动行变为 2 （明细2 的标题，表头,+1）

            if(list1==null||list1.isEmpty()){
                startRow =  startRow + 3;

            }else {
                startRow = list1.size() + startRow + 2;
            }



            // 循环插入order要求
            if (list2.size() > 0) {
                //   sheet.shiftRows(startRow, sheet.getLastRowNum(), list2.size() -1 , true, false);
                for (int i = 0; i < list2.size(); i++) {
                    //在 第12 行插入一行(在 12 行后面插入一行)
                    if ((i + 1) != list2.size()) {//最后一行不复制
                        //复制行
                        copyRows(i + startRow, i + startRow + 1, i + startRow, sheet);
                    }
                }
                for (int i = 0; i < list2.size(); i++) {
                    //获取行
                    XSSFRow row = sheet.getRow(i + startRow-1);

                    if (row == null) {
                        row = sheet.createRow(i + startRow-1);
                    }
                    Object object = list2.get(i);
                    Class cla = list2.get(0).getClass();
                    Field[] fields = cla.getDeclaredFields();
                    for (int j = 0; j < 11; j++) {
                        int num = j + startCell;
                        XSSFCell cell = row.getCell(num);
                        if (cell == null) {
                            cell = row.createCell(num);
                        }
                        Field field = fields[j + index];
                        //设置压制访问类型检查
                        ReflectionUtils.makeAccessible(field);
                        //使用反射获取数据
                        Object value = ReflectionUtils.getField(field, object);
                        if (value != null && value != "") {
                            Class type = field.getType();

                            if (Long.class.equals(type)) {
                                cell.setCellValue((long) value);
                            } else if (Integer.class.equals(type)) {
                                cell.setCellValue((int) value);
                            } else if (Short.class.equals(type)) {
                                cell.setCellValue((short) value);
                            } else if (Byte.class.equals(type)) {
                                cell.setCellValue((byte) value);
                            } else if (Double.class.equals(type)) {
                                cell.setCellValue((double) value);
                            } else if (Float.class.equals(type)) {
                                cell.setCellValue((float) value);
                            } else if (Boolean.class.equals(type)) {
                                cell.setCellValue((boolean) value);
                            } else if (Date.class.equals(type)) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String date = sdf.format((Date) value);
                                cell.setCellValue(date);
                            } else {
                                cell.setCellValue(value.toString());
                            }
                        } else {
                            cell.setCellValue("");
                        }
                    }
                }
            }


            //保存到本地
            out = response.getOutputStream();
            workBook.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 复制行
     *
     * @param startRow  为想要复制第一行的标示
     * @param endRow    到此结束复制行的标示
     * @param pPosition 复制内容后要放到指定的位置的标示
     * @param sheet
     * @author hgb
     * @date 2017-7-3
     */
    public static void copyRows(int startRow, int endRow, int pPosition,
                                XSSFSheet sheet) {
        int pStartRow = startRow - 1;
        int pEndRow = endRow - 1;
        int targetRowFrom;
        int targetRowTo;
        int columnCount;
        CellRangeAddress region = null;
        int i;
        int j;
        if (pStartRow == -1 || pEndRow == -1) {
            return;
        }

        // 设置列宽
        for (i = pStartRow; i <= pEndRow; i++) {
            XSSFRow sourceRow = sheet.getRow(i);
            columnCount = sourceRow.getLastCellNum();
            if (sourceRow != null) {
                XSSFRow newRow = sheet.createRow(pPosition - pStartRow + i);
                newRow.setHeight(sourceRow.getHeight());
                for (j = 0; j < columnCount; j++) {
                    XSSFCell templateCell = sourceRow.getCell(j);
                    if (templateCell != null) {
                        XSSFCell newCell = newRow.createCell(j);
                        copyCell(templateCell, newCell);
                    }
                }
            }
        }
        // 拷贝合并的单元格
        for (i = 0; i < sheet.getNumMergedRegions(); i++) {
            region = sheet.getMergedRegion(i);
            if ((region.getFirstRow() >= pStartRow)
                    && (region.getLastRow() <= pEndRow)) {
                targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                targetRowTo = region.getLastRow() - pStartRow + pPosition;
                CellRangeAddress newRegion = region.copy();
                newRegion.setFirstRow(targetRowFrom);
                newRegion.setFirstColumn(region.getFirstColumn());
                newRegion.setLastRow(targetRowTo);
                newRegion.setLastColumn(region.getLastColumn());
//                sheet.addMergedRegion(newRegion);
                sheet.addMergedRegionUnsafe(newRegion);
            }
        }

    }

    /**
     * 复制列
     *
     * @param srcCell  原来列
     * @param distCell 新的列
     */
    private static void copyCell(XSSFCell srcCell, XSSFCell distCell) {
        distCell.setCellStyle(srcCell.getCellStyle());
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        int srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                distCell.setCellValue(srcCell.getDateCellValue());
            } else {
                distCell.setCellValue(srcCell.getNumericCellValue());
            }
        } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
            distCell.setCellValue(srcCell.getRichStringCellValue());
        } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
            // nothing21
        } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
            distCell.setCellValue(srcCell.getBooleanCellValue());
        } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
            distCell.setCellErrorValue(srcCell.getErrorCellValue());
        } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
            distCell.setCellFormula(srcCell.getCellFormula());
        } else { // nothing29

        }
    }
}
