package com.egdfrm.unit.mapper.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tyq on 17/1/6.
 */
public interface ProductBarcodeMapper {

    /**
     * 获取产品条码列表_总数据条数
     * @return
     */
    Long getProductBarcodeByCount(@Param("map") Map<String, Object> map);

    /**
     * 获取产品条码列表
     * @param page 分页数据
     * @param map 查询条件
     * @return
     */
    List<Map<String,Object>> getProductBarcode(@Param("page")Pagination page, @Param("map")Map<String, Object> map);

    /**
     * 条码生成
     * @param map
     */
    void barcodeProduction(@Param("map") Map<String, Object> map);

    /**
     * 查询工单总数据
     * @param map
     * @return
     */
    Long getWorkOrderByCount(@Param("map")Map<String, Object> map);

    /**
     * 工单分页查询
     * @param pagination
     * @param map
     * @return
     */
    List<Map<String,Object>> getWorkOrderByPage(@Param("page") Pagination pagination, @Param("map") Map<String, Object> map);

    /**
     * 条码作废
     * @param map
     */
    void abolish(@Param("map") Map<String, Object> map);

    /**
     * 获取条码状态
     * @return
     */
    List<String> getBarcodeStatus();

    /**
     * 修改条码打印状态
     * @param codes
     * @return
     */
    int updatePrintStatus(@Param("codes") String[] codes);
}
