package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.common.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by tyq on 17/1/6.
 */
public interface IProductBarcodeService {

    /**
     * 获取产品条码列表
     * @param pagination 分页数据
     * @param map 查询条件
     * @return
     */
    Pagination getProductBarcode(Pagination pagination, Map<String, Object> map);

    /**
     * 条码生成
     * @param map
     */
    void barcodeProduction(Map<String, Object> map);

    /**
     * 工单分页查询
     * @param pagination 分页条件想
     * @param map
     * @return
     */
    Pagination getWorkOrderByPage(Pagination pagination, Map<String, Object> map);

    /**
     * 条码作废
     * @param map
     */
    void abolish(Map<String, Object> map);

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
    int updatePrintStatus(String[] codes);
}
