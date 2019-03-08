package com.egdfrm.unit.mapper.barcodeManagement;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 无工单包装条码_mapper
 * Created by tyq on 17/1/10.
 */
public interface NoPackageBarcodeMapper {

    /**
     * 获取当前条码
     * @return
     */
    String getCurrentBarcode();


    /**
     * 获取开始和结束条码
     * @param map
     */
    void getSEcode(@Param("map") Map<String, Object> map);
}
