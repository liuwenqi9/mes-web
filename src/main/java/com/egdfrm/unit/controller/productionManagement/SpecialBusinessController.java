package com.egdfrm.unit.controller.productionManagement;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.service.productionManagement.ISpecialBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 特殊业务处理——控制器
 * Created by tyq on 17/2/9.
 */
@Controller
@RequestMapping("special/business")
public class SpecialBusinessController {

    @Autowired
    private ISpecialBusinessService iSpecialBusinessService;

    /**
     * 跳转到特殊业务处理页面
     * @return
     */
    @RequestMapping("init")
    public String init(Model model){
        //获取包装类型
        List<Map<String,Object>> list = iSpecialBusinessService.getPackageTypes();
        model.addAttribute("listTypes",list);
        return "unit/productionManagement/special_business";
    }

    /**
     * 产品条码重置
     * @param productWordOrder 工单号
     * @return
     */
    @RequestMapping(value = "productNumReset",method = RequestMethod.POST)
    @ResponseBody
    public String productNumReset(String productWordOrder){
        Json json = new Json();
        Map<String,String> map = new HashMap<>();
        map.put("p_wip_name",productWordOrder);
        map.put("x_return_status","");
        map.put("x_msg_data","");
        iSpecialBusinessService.productNumReset(map);
        String status = map.get("x_return_status");
        String data = map.get("x_msg_data");
        json.setMessage(data);
        if("S".equals(status)){
            json.setSuccess(true);
            json.setMessage("重置成功");
        }
        return JSON.toJSONString(json);
    }

    /**
     * 包装条码重置
     * @param packageWordOrder 工单号
     * @param packageType 工单号
     * @return
     */
    @RequestMapping(value = "packageNumReset",method = RequestMethod.POST)
    @ResponseBody
    public String packageNumReset(String packageWordOrder,String packageType){
        Json json = new Json();
        Map<String,String> map = new HashMap<>();
        map.put("p_wip_name",packageWordOrder);
        map.put("p_pack_type",packageType);
        map.put("x_return_status","");
        map.put("x_msg_data","");
        iSpecialBusinessService.packageNumReset(map);
        String status = map.get("x_return_status");
        String data = map.get("x_msg_data");
        json.setMessage(data);
        if("S".equals(status)){
            json.setSuccess(true);
            json.setMessage("重置成功");
        }
        return JSON.toJSONString(json);
    }


    /**
     * 包装尾数重置
     * @return
     */
    @RequestMapping(value = "packageMantissaReset",method = RequestMethod.POST)
    @ResponseBody
    public String packageMantissaReset(String packingBox,String updateNumber){
        Json json = new Json();
        Map<String,String> map = new HashMap<>();
        map.put("p_pack_name",packingBox);
        map.put("p_quantity",updateNumber);
        map.put("x_return_status","");
        map.put("x_msg_data","");
        iSpecialBusinessService.packageMantissaReset(map);
        String status = map.get("x_return_status");
        String data = map.get("x_msg_data");
        json.setMessage(data);
        if("S".equals(status)){
            json.setSuccess(true);
            json.setMessage("重置成功");
        }
        return JSON.toJSONString(json);
    }

    /**
     * 额外新增工单包装箱
     * @param additionalWorkOrder 工单号
     * @param additionalTypes 类型
     * @return
     */
    @RequestMapping(value = "additional",method = RequestMethod.POST)
    @ResponseBody
    public String additional(@CurrentLoginInfo UserAuthenRealm.ShiroUser user,String additionalWorkOrder, String additionalTypes){
        Json json = new Json();
        Map<String,Object> map = new HashMap<>();
        map.put("p_wip_entity_name",additionalWorkOrder);
        map.put("p_user_id",user.getUserId().toString());
        map.put("p_pack_type",additionalTypes);
        map.put("x_return_status","");
        map.put("x_msg_data","");
        iSpecialBusinessService.additional(map);
        String status = map.get("x_return_status").toString();
        if("S".equals(status)){
            json.setSuccess(true);
            json.setMessage("新增成功");
        }else {
            json.setMessage(map.get("x_msg_data").toString());
        }
        return JSON.toJSONString(json);
    }


    /**
     * 工单转产
     * @param user 当前用户
     * @param theSingleTurn 包装箱
     * @return
     */
    @RequestMapping(value = "theSingleTurn",method = RequestMethod.POST)
    @ResponseBody
    public String theSingleTurn(@CurrentLoginInfo UserAuthenRealm.ShiroUser user,String theSingleTurn){
        Json json = new Json();
        Map<String,Object> map = new HashMap<>();
        map.put("p_pack_name",theSingleTurn);
        map.put("p_user_id",user.getUserId().toString());
        map.put("x_return_status","");
        map.put("x_msg_data","");
        iSpecialBusinessService.theSingleTurn(map);
        String status = map.get("x_return_status").toString();
        if("S".equals(status)){
            json.setSuccess(true);
            json.setMessage("转产成功");
        }else {
            json.setMessage(map.get("x_msg_data").toString());
        }
        return JSON.toJSONString(json);
    }

    /**
     * 线别重置
     * @param user 当前用户
     * @param packBarcode 包装箱
     * @return
     */
    @RequestMapping(value = "planLineReset",method = RequestMethod.POST)
    @ResponseBody
    public String planLineReset(@CurrentLoginInfo UserAuthenRealm.ShiroUser user,String packBarcode,String lineCode){
        Json json = new Json();
        Map<String,Object> map = new HashMap<>();
        map.put("packBarcode",packBarcode);
        map.put("lineCode",lineCode);
        map.put("userId",user.getUserId().toString()); 
        boolean status = iSpecialBusinessService.verifyPackBarcodeIsPlanLine(map);
        if(!status){
        	json.setSuccess(false);
            json.setMessage("包装箱状态不对 或线别不正确！");
            return JSON.toJSONString(json);
        }  
        if(iSpecialBusinessService.planLineReset(map)){
            json.setSuccess(true);
            json.setMessage("重置成功！");
        }else {
        	json.setSuccess(true);
            json.setMessage("重置失败！");
        }
        return JSON.toJSONString(json);
    }

    /**
     *  工单数量修改
     * @param wipName
     * @param number
     * @return
     */
    @RequestMapping(value = "wipNumReset",method = RequestMethod.POST)
    @ResponseBody
    public String wipNumReset(String wipName,String number){
        Json json = new Json();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("wipName",wipName);
        map.put("num",number);
        boolean status = iSpecialBusinessService.verifyWipName(map);
        if(!status){
            json.setSuccess(false);
            json.setMessage("请输入正确的工单号！");
            return JSON.toJSONString(json);
        }
        if(iSpecialBusinessService.wipNumReset(map)){
            json.setSuccess(true);
            json.setMessage("重置成功！");
        }else {
            json.setSuccess(true);
            json.setMessage("重置失败！");
        }
        return JSON.toJSONString(json);
    }
}
