package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.mapper.barcodeManagement.NoPackageBarcodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 无工单包装条码接口实现
 * Created by tyq on 17/1/10.
 */
@Service
public class NoPackageBarcodeService implements INoPackageBarcodeService {

    @Autowired
    private NoPackageBarcodeMapper noPackageBarcodeMapper;

    /**
     * 获取当前条码
     * @return
     */
    @Override
    public String getCurrentBarcode() {
        return noPackageBarcodeMapper.getCurrentBarcode();
    }

    /**
     * 获取开始和结束条码
     * @param map
     */
    @Override
    public void getSEcode(Map<String, Object> map) {
        noPackageBarcodeMapper.getSEcode(map);
    }
}
