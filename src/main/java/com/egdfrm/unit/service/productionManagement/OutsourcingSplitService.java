package com.egdfrm.unit.service.productionManagement;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.controller.productionManagement.BuyFinishedLCLController;
import com.egdfrm.unit.model.expand.pda.SecondaryPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.mapper.productionManagement.OutsourcingSplitMapper;
import org.springframework.util.StringUtils;

@Service
public class OutsourcingSplitService extends BaseService {

    @Autowired
    private OutsourcingSplitMapper outsourcingSplitMapper;

    public List<Map<String, Object>> getOutsourcingByBarcode(String barcodeText) {
        return outsourcingSplitMapper.getOutsourcingByBarcode(barcodeText);
    }

    /**
     * @param ids       产品的id
     * @param barcodeId 箱号id
     * @author hgb
     * @date 2017-2-24
     */
    public void confirmSplit(String[] ids, String barcodeId, BigDecimal userId) {

        for (int i = 0; i < ids.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("barcodeId", barcodeId);
            map.put("prodId", ids[i]);
            map.put("x_return_status", "");
            map.put("x_msg_data", "");
            outsourcingSplitMapper.callOutsourcedUnpack(map);
            if (map.get("x_return_status").toString().equals("E")) {
                this.log.info(ids[i] + "数据异常!");
                throw new RuntimeException();
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("packBarcodeId", barcodeId);
        map.put("packQuantity", ids.length);
        map.put("userId", userId);
        outsourcingSplitMapper.updatePackQuantity(map);

    }

    /**
     * 外购机拆箱-----检查包装条码
     *
     * @param input
     * @return
     * @athor 罗港
     */
    public String[] outsourcingSplitCheckPack(String[] input) {
        String[] returnValue = new String[10];
        String packageBarcode = input[2];
        if (StringUtils.isEmpty(packageBarcode)) {
            returnValue[0] = MesConstants.ERROR;
            returnValue[1] = "包装箱号不能为空，请联系管理员";
            return returnValue;
        }
        List list = outsourcingSplitMapper.getOutsourcingByBarcode(packageBarcode);
        Utils.printWebServiceLog("PackageBarcode:" + list.size());
        if (list.size() <= 0) {
            returnValue[0] = MesConstants.ERROR;
            returnValue[1] = "没有此包装箱";
            return returnValue;
        }
        returnValue[0] = MesConstants.SUCCESS;
        returnValue[1] = "包装箱号正确";
		returnValue[2] = "" + list.size();
        return returnValue;
    }

    /**
     * 外购机拆箱-----检查产品条码
     *
     * author 罗港
     * @param input
     * @return
     */
    public String[] outsourcingSplitCheckProduct(String[] input) {
        String[] returnValue = new String[10];
        String packageBarcode = input[2];
        String productBarcode = input[3];
        if (StringUtils.isEmpty(packageBarcode) || StringUtils.isEmpty(productBarcode)) {
            returnValue[0] = MesConstants.ERROR;
            returnValue[1] = "包装箱号或产品条码不能为空，请联系管理员";
            return returnValue;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("packageBarcode", packageBarcode);
        map.put("productBarcode", productBarcode);
        Map<String, String> product = outsourcingSplitMapper.getOutsourcingSplitProduct(map);
        if (product == null) {
            returnValue[0] = MesConstants.ERROR;
            returnValue[1] = "没有此产品";
            return returnValue;
        }
        returnValue[0] = MesConstants.SUCCESS;
        returnValue[1] = product.get("WIP_ENTITY_NAME");
        returnValue[2] = product.get("ITEM");
        returnValue[3] = product.get("SEGMENT2");
        returnValue[4] = product.get("DESCRIPTION");
        returnValue[5] = product.get("WIP_BARCODE_ID");
        return returnValue;
    }

    public String[] outsourcingSplitCommit(String input) {
        String[] returnValue = new String[6];
        if (StringUtils.isEmpty(input)) {
            returnValue[0] = MesConstants.ERROR;
            returnValue[1] = "输入参数不能为空，请联系管理员";
            return returnValue;
        }
        returnValue[0] = MesConstants.SUCCESS;
        returnValue[1] = "拼包成功";
        return returnValue;
    }

}
