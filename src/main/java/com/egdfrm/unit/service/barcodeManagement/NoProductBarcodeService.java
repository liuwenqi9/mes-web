package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.mapper.barcodeManagement.NoProductBarcodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 无工单产品条码接口实现
 * Created by tyq on 17/1/10.
 */
@Service
public class NoProductBarcodeService implements INoProductBarcodeService {

    @Autowired
    private NoProductBarcodeMapper noProductBarcodeMapper;

    /**
     * 获取当前产品条码
     * @return 产品条码
     */
    @Override
    public String getCurrentBarcode() {
        return noProductBarcodeMapper.getProductBarcodeByCount();
    }

    /**
     * 获取开始和结束条码
     * @param map
     */
    @Override
    public void getSEcode(Map<String, Object> map) {
        noProductBarcodeMapper.getSEcode(map);
    }
}
