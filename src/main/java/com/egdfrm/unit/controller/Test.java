package com.egdfrm.unit.controller;

import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.mapper.expand.overseas.OverseasDistributorMapper;
import com.egdfrm.unit.model.ws.OsLine;
import com.egdfrm.unit.model.ws.OsOrders;
import com.egdfrm.unit.service.overseas.ODService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hgb on 2018/8/9
 * Email xhy18650@sina.com
 **/
@Controller
@RequestMapping("test")
public class Test {
    @Autowired
    ODService odService;
    @RequestMapping("/save")
    @ResponseBody
    public String[] save(){
        String orderJson ="{\"BOOKED_FLAG\":\"Y\",\"COUNTRY\":\"Argentina\",\"CREATION_TIME\":\"2018-08-10 11:10:32\",\"FOB\":\"FOB NINGBO\",\"PAYMENT_TERM\":\"BP100\",\"SHIP_METHOD\":\"Sea\",\"SHIP_ORG\":\"香港\",\"crm_order_number\":\"201808090003\",\"customer_po\":\"10sa\",\"order_date\":\"2018-08-09\",\"order_type\":\"香港\",\"osLines\":[{\"booked_flag\":\"Y\",\"crm_order_number\":\"201808090003\",\"inventory_item_id\":\"10780\",\"line_number\":\"c99743037f7046dfb4c6aeb1462d6788\",\"order_quantity_uom\":\"台\",\"ordered_quantity\":100,\"org_id\":\"101\",\"unit_list_pric\":32340,\"unit_selling_pric\":29106}],\"price_list_id\":\"3356734\",\"remark\":\"测试\",\"salesrep_id\":\"100009054\",\"sold_to_org_id\":\"1875944\",\"transactional_curr_code\":\"USD\",\"transfer_flag\":\"N\"}";
        odService.insertOsOrderInterface(orderJson);
        return  odService.createOverseasOrder(orderJson);
    }

    @RequestMapping("/refresh")
    @ResponseBody
    public String[] overseasAllInterface(){
        return  odService.overseasAllInterface();
    }
}
