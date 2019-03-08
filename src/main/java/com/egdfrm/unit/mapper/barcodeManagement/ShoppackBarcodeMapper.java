package com.egdfrm.unit.mapper.barcodeManagement;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 发运包装条码管理_mapper
 * Created by tyq on 17/1/11.
 */
public interface ShoppackBarcodeMapper {

    /**
     * 获取当前条码
     * @return
     */
    String getCurrentBarcode();

    /**
     * 获取开始条码和结束条码
     * @param map
     */
    void getSEcode(@Param("map") Map<String,Object> map);

    /**
     * 获取条码补丁
     * @return
     */
    List<String> getPatch();

}
