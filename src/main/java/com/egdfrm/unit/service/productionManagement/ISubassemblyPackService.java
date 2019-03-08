package com.egdfrm.unit.service.productionManagement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *  半成品 接口实现 
 */
public interface ISubassemblyPackService {

    /**
     * 根据工单号查询(半成品)
     * @param workOrderNumber 工单号
     * @return
     */
    List<Map<String,Object>> queryAll(String workOrderNumber);

    /**
     * 保存包装数量(半成品)
     * @param userId 当前用户
     * @param sipEntityIds 工单ID
     * @param inventoryItemIds 物料ID
     * @param pacgingNums 包装数量
     * @return
     */
    int savePackingNumber(BigDecimal userId, String[] sipEntityIds, String[] inventoryItemIds, String[] pacgingNums) throws Exception;

    /**
     * 包装箱查询(半成品)
     * @param workOrderNumber
     * @return
     */
    List<Map<String,Object>> queryPackingAll(String workOrderNumber);

    /**
     * 根据包装ID查询包装信息(半成品)
     * @param barcodeId 包装ID
     * @return
     */
    Map<String,Object> findPackingById(String barcodeId);

    /**
     * 包装打印数据查询(半成品)
     * @param barcodeId 包装ID
     * @return
     */
    List<Map<String,Object>> getRows(String barcodeId);

    /**
     * 修改打印状态(半成品)
     * @param barcodeIds 包装箱ID
     * @return
     */
    int updateStatus(String[] barcodeIds);
}
