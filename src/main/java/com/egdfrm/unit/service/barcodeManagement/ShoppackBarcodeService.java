package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.mapper.barcodeManagement.ShoppackBarcodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 发运包装条码管理接口_实现
 * Created by tyq on 17/1/11.
 */
@Service
public class ShoppackBarcodeService implements IShoppackBarcodeService {


    @Autowired
    private ShoppackBarcodeMapper shoppackBarcodeMapper;

    /**
     * 获取当前条码
     * @return
     */
    @Override
    public String getCurrentBarcode() {
        return shoppackBarcodeMapper.getCurrentBarcode();
    }

    /**
     * 获取开始条码和结束条码
     * @param map
     */
    @Override
    public void getSEcode(Map<String, Object> map) {
        shoppackBarcodeMapper.getSEcode(map);
    }

    /**
     * 获取条码补丁
     * @return
     */
    @Override
    public List<String> getPatch() {
        return shoppackBarcodeMapper.getPatch();
    }
}
