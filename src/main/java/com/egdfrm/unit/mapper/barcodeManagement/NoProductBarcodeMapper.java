package com.egdfrm.unit.mapper.barcodeManagement;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 无工单产品条码_mapper
 * Created by tyq on 17/1/10.
 */
public interface NoProductBarcodeMapper {

    /**
     * 获取当前产品条码
     * @return 产品条码
     */
    String getProductBarcodeByCount();

    /**
     * 获取开始和介绍条码
     * @param map
     */
    void getSEcode(@Param("map") Map<String, Object> map);
}
