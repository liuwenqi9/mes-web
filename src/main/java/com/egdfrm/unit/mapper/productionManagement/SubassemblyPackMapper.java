package com.egdfrm.unit.mapper.productionManagement;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (半成品)-Mapper 
 */
public interface SubassemblyPackMapper {

    /**
     * 根据工单号查询(半成品)
     * @param workOrderNumber 工单号
     * @return
     */
    List<Map<String,Object>> queryAll(@Param("workOrderNumber") String workOrderNumber);

    /**
     * 获取包装箱号(半成品)
     * @param map
     */
    void getPackageNum(@Param("map") Map<String, Object> map);

    /**
     * 保存包装数量(半成品)
     * @param map
     */
    void savePackingNumber(@Param("map") Map<String, Object> map);

    /**
     * 包装箱查询(半成品)
     * @param workOrderNumber 工单号
     * @return
     */
    List<Map<String,Object>> queryPackingAll(@Param("workOrderNumber")String workOrderNumber);

    /**
     * 根据包装ID查询包装信息(半成品)
     * @param barcodeId 包装ID
     * @return
     */
    Map<String,Object> findPackingById(@Param("barcodeId")String barcodeId);

    /**
     * 包装打印数据查询(半成品)
     * @param barcodeId 包装ID
     * @return
     */
    List<Map<String,Object>> getRows(@Param("barcodeId") String barcodeId);

    /**
     * 修改打印状态(半成品)
     * @param barcodeIds 包装箱ID
     * @return
     */
    int updateStatus(@Param("barcodeIds") String[] barcodeIds);
}
