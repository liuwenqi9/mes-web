package com.egdfrm.unit.controller.barcodeManagement;


import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.unit.common.ExcelUtils;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.barcodeManagement.*;
import com.egdfrm.unit.service.barcodeManagement.IShipmentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customerSopController")
public class CustomerSopController {
    /**
     * 方便
     */
    @Autowired
    private IShipmentService iShipmentService;


    @RequestMapping("/init")
    public String init(){
        return "unit/barcodeManagement/customer_sop";
    }


    @RequestMapping("cusHeader")
    @ResponseBody
    public Json cusHeader(CusHeader cusHeader){
        Json json = new Json();
         //2018-12-07 新增的验证
        //包验证，是否正确， E 直接弹出
        DbReturnParameter dbreturn = new DbReturnParameter();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cust_po_number", cusHeader.getCust_po_number());
        paramMap.put(MesConstants.DBRETURN, dbreturn);
        iShipmentService.callCheckMo(paramMap);
        if(!"S".equals(dbreturn.getxStatus())){
            json.setSuccess(false);
            json.setMessage(dbreturn.getxMessage());
            json.setResult(cusHeader);
            return json;
        }

        //验证是否存在，
        cusHeader =  iShipmentService.cusHeader(cusHeader);
        if(cusHeader!=null){
            json.setSuccess(true);
            json.setMessage("请求完成！");
            json.setResult(cusHeader);
            return json;
        }
        json.setSuccess(false);
        json.setMessage("未找到数据！");
        json.setResult(cusHeader);
        return json;
    }
    @RequestMapping("moPage")
    @ResponseBody
    public String moPage(Pagination page, CustomerSop customerSop){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iShipmentService.moPage(pagination,customerSop);
        return JSON.toJSONString(page);
    }

    @RequestMapping("orderPage")
    @ResponseBody
    public String orderPage(Pagination page, CustomerSop customerSop){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iShipmentService.orderPage(pagination,customerSop);
        return JSON.toJSONString(page);
    }

    @RequestMapping(value="getFiles",method = RequestMethod.GET)
    @ResponseBody
    public void getFiles(String FILE_ID, HttpServletRequest request, HttpServletResponse response){

        ServletOutputStream out = null;
        if(StringUtils.isNotBlank(FILE_ID)){
            try {
                FileData fileData = iShipmentService.getFiles(FILE_ID);
                //获取blob字段
                byte[] contents =  fileData.getFile_data();
                System.out.println("内容是:"+contents.length);
                InputStream is = new ByteArrayInputStream(contents);
                if(fileData.getFile_name().endsWith(".pdf")||fileData.getFile_name().endsWith(".PDF")||
                        fileData.getFile_name().endsWith(".png")||fileData.getFile_name().endsWith("PNG")||
                        fileData.getFile_name().endsWith(".jpg")|| fileData.getFile_name().endsWith(".JPG")||
                        fileData.getFile_name().endsWith(".gif")|| fileData.getFile_name().endsWith(".GIF")){//
                    response.setContentType(fileData.getFile_content_type());//直接预览
                }else {
                    //设置文件ContentType类型,这样设置会自动判断下载文件类型
                    response.setContentType("multipart/form-data");
                    String fileName = fileData.getFile_name();
                    String iso8859 = new String(fileName.toString().getBytes("iso8859-1"));
                    String gbk = new String(fileName.toString().getBytes("gbk"));
                    String utf8 = new String(fileName.toString().getBytes("utf-8"));
                    if(iso8859.equals(fileName.toString())){
                        System.out.println("iso8859");
                    }else  if(gbk.equals(fileName.toString())){
                        System.out.println("gbk");
                    }else  if(utf8.equals(fileName.toString())){
                        System.out.println("utf8");
                    }
                    fileName = fileName.replace(" ","");
                    fileName = new String(fileName.getBytes(),"iso8859-1");
                    //设置文件头：最后一个参数是设置下载文件名
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("Content-Disposition","attachment;fileName=" + fileName);
//                    response.setHeader("Content-Disposition","attachment;fileName=" + fileName);

                }
                out = response.getOutputStream();
                int len=0;
                byte[] buf=new byte[1024];
                while((len=is.read(buf,0,1024))!=-1){
                    out.write(buf, 0, len);
                }

                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     *  导出MO
     */
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,String cust_po_number){
        CusHeader cusHeader = new CusHeader();
        cusHeader.setCust_po_number(cust_po_number);
        try {
            // 头
            CusHeader header = iShipmentService.cusHeader(cusHeader);
            // 明细1
            CustomerMoRequire customerMoRequire = new CustomerMoRequire();
            customerMoRequire.setCust_po_number(cust_po_number);
            List<CustomerMoRequire> list1 = iShipmentService.getMoList(customerMoRequire);
            // 明细2
            CustomerOrderRequire customerOrderRequire = new CustomerOrderRequire();
            customerOrderRequire.setCust_po_number(cust_po_number);
            List<CustomerOrderRequire> list2 = iShipmentService.getOrderList(customerOrderRequire);
            //获取XSSFWorkbook
            XSSFWorkbook workBook = ExcelUtils.getWorkBook(request, "MO模板.xlsx");
            ExcelUtils.exportMO(response, workBook, "MO.xlsx", header, list1, list2, 12, 0, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
