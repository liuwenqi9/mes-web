package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.mapper.barcodeManagement.NoBigPackageBarcodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 无工单大包装条码接口实现
 * Created by tyq on 17/1/10.
 */
@Service
public class NoBigPackageBarcodeService implements INoBigPackageBarcodeService {

    @Autowired
    private NoBigPackageBarcodeMapper noBigPackageBarcodeMapper;

    /**
     * 获取当前 大包装
     * @return
     */
    @Override
    public String getCurrentBarcode() {
        return noBigPackageBarcodeMapper.getCurrentBarcode();
    }

    /**
     * 获取开始和结束条码
     * @param map
     */
    @Override
    public void getSEcode(Map<String, Object> map) {
        noBigPackageBarcodeMapper.getSEcode(map);
    }
}
