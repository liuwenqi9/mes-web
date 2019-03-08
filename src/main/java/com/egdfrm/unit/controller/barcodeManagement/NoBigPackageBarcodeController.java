package com.egdfrm.unit.controller.barcodeManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.QRCodeUtil;
import com.egdfrm.unit.common.WriteForPDF;
import com.egdfrm.unit.service.barcodeManagement.INoBigPackageBarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * 无工单包大装条码——控制器
 * Created by tyq on 17/1/10.
 */
@Controller
@RequestMapping("noBigPackageBarcode")
public class NoBigPackageBarcodeController {

    @Autowired
    private INoBigPackageBarcodeService iNoBigPackageBarcodeService;

    /**
     * 跳转到无工单大包装条码管理页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "init")
    public String init(){
        return "unit/barcodeManagement/noBigPackageBarcode";
    }

    /**
     * 获取当前条码
     * @return
     */
    @RequestMapping(value = "getCurrentBarcode",method = RequestMethod.POST)
    @ResponseBody
    public String getCurrentBarcode(){
        Json json = new Json();
        String barcode = iNoBigPackageBarcodeService.getCurrentBarcode();
        if(!StringUtils.isEmpty(barcode)){
            json.setSuccess(true);
            json.setMessage(barcode);
        }
        return JSON.toJSONString(json);
    }

    /**
     * 获取开始和结束条码
     * @param printNum
     * @return
     */
    @RequestMapping(value = "getSEcode",method = RequestMethod.POST)
    @ResponseBody
    public String getSEcode(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, int printNum){
        Json json = new Json();
        Map<String,Object> map = new HashMap<>();
        try{
            String userId = user.getUserId().toString();
            map.put("p_organization_id",user.getOrgId());
            map.put("p_print_quantity",printNum);
            map.put("p_user_id",userId);
            map.put("x_return_status","");
            map.put("x_msg_data","");
            System.out.println("1");
            iNoBigPackageBarcodeService.getSEcode(map);
            Object status = map.get("x_return_status");
            String msg = String.valueOf(map.get("x_msg_data"));
            json.setSuccess("S".equals(status)?true:false);
            json.setMessage("S".equals(status)?msg:"系统繁忙,请稍后再试");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(json);
    }

    /**
     * 打印二维码
     * @param startCode 开始条码
     * @param endCode 结束条码
     * @return
     */
    @RequestMapping(value = "toBarCodeByQRCode",method = RequestMethod.POST)
    @ResponseBody
    public String toBarCodeByQRCode(HttpServletRequest request, String startCode, String endCode){
        Json json = new Json();
        List<Map<String,byte[]>> list = new ArrayList<Map<String,byte[]>>();
        try {
            String head = startCode.substring(0,5);
            int startNum = Integer.parseInt(1+startCode.substring(5));
            int endNum = Integer.parseInt(1+endCode.substring(5));
            for (int i = startNum; i <= endNum ; i++) {
                String code = head + String.valueOf(i).substring(1);
                BufferedImage image = QRCodeUtil.createImage(code,500, null, true);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(image, "bmp", out);
                byte[] b = out.toByteArray();
                Map<String,byte[]> map = new LinkedHashMap<>();
                map.put(code,b);
                list.add(map);
                System.out.println("1");
                if(out  != null){
                    out.close();
                }
            }
            String path = WriteForPDF.writePdfToPack(request,list);
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
}
