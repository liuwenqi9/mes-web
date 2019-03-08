package com.egdfrm.unit.service.barcodeManagement;

import java.util.Map;

/**
 * 无工单产品条码相关接口
 * Created by tyq on 17/1/10.
 */
public interface INoProductBarcodeService {


    /**
     * 获取当前条码
     * @return
     */
    String getCurrentBarcode();

    /**
     * 获取开始和结束条码
     * @param map
     */
    void getSEcode(Map<String, Object> map);
}
