package com.egdfrm.unit.controller.productionManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.model.expand.pda.SecondaryPack;
import com.egdfrm.unit.service.pda.PackagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二次拼箱-拦截器
 * Created by tyq on 17/3/23.
 */
@Controller
@RequestMapping("secondaryLCL")
public class SecondaryLCLController {


    @Autowired
    private PackagingService packagingService;

    /**
     * 跳转到二次拼箱页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/productionManagement/secondaryLCL_manage";
    }

    /**
     * 包装条码验证
     * @param user 当前用户
     * @param packageBarcode 包装条码
     * @return
     */
    @RequestMapping("checkPackaging")
    @ResponseBody
    public String checkPackaging(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, String packageBarcode){
        Json json = new Json();
        String[] strs = new String[4];
        strs[0] = user.getLoginName();
        strs[1] = user.getOrgId().toString();
        strs[2] = packageBarcode;
        strs[3] = "SP";
        String[] strings = packagingService.packagingPackBarcode(strs);
        json.setSuccess(strings[0].equals("S"));
        json.setMessage(strings[1]);
        return JSON.toJSONString(json);
    }

    /**
     * 产品条码验证
     * @param user 当前用户
     * @param packageBarcode 包装条码
     * @param barcode 产品条码
     * @param barcode 解除限制 Y/N
     * @return
     */
    @RequestMapping("checkBarcode")
    @ResponseBody
    public String checkBarcode(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, String packageBarcode,String barcode,String relieveLimit){
        String[] strs = new String[5];
        strs[0] = user.getLoginName();
        strs[1] = user.getOrgId().toString();
        strs[2] = packageBarcode;
        strs[3] = barcode;
        strs[4] = relieveLimit;
        String[] strings = packagingService.packagingWIPBarcode(strs);
        return JSON.toJSONString(strings);
    }

    /**
     * 提交
     * @param user
     * @param packageBarcodes 包装条码
     * @param barcode_texts 产品条码
     * @param barcodes 产品编码
     * @param quantitys 数量
     * @param status 解除限制
     * @return
     */
    @RequestMapping("commit")
    @ResponseBody
    public String commit(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, @RequestParam("packageBarcodes[]") String[] packageBarcodes,@RequestParam("barcode_texts[]") String[] barcode_texts,@RequestParam("barcodes[]") String[] barcodes,@RequestParam("quantitys[]") String[] quantitys, String status){
        SecondaryPack secondaryPack = new SecondaryPack();
        secondaryPack.setUserid(user.getUserName());
        secondaryPack.setWarehouse(user.getOrgId().toString());
        List<SecondaryPack> list = new ArrayList<>();
        for (int i = 0; i < packageBarcodes.length; i++) {
            SecondaryPack sec = new SecondaryPack();
            sec.setPackNo(packageBarcodes[i]);
            sec.setSnNo(barcode_texts[i]);
            sec.setSnID(barcodes[i]);
            sec.setNum(quantitys[i]);
            list.add(sec);
        }
        secondaryPack.setSecondaryPack(list);
        String[] strings = packagingService.packagingCommit(JSON.toJSONString(secondaryPack), status);
        return JSON.toJSONString(strings);
    }
}
