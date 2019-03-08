package com.egdfrm.unit.mapper.barcodeManagement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.barcodeManagement.PackagePrint;

/**
 * Created by tyq on 17/1/9.
 */
public interface PackageBarcodeMapper {


    /**
     * 包装条码列表总数查询
     * @param map 查询条件
     * @return
     */
    Long getConnt(@Param("map") Map<String, Object> map);

    /**
     * 包装条码分页查询
     * @param page 分页条件
     * @param map 查询条件
     * @return
     */
    List<Map<String,Object>> gePageData(@Param("page") Pagination page, @Param("map") Map<String, Object> map);

    /**
     * 获取包装类型
     * @return
     */
    List<Map<String,Object>> getPackageType();

    /**
     * 条码生成
     * @param map
     */
    void barcodePackage(@Param("map") Map<String, Object> map);

    /**
     * 获取包装条码打印数据
     * @param codes 学序列号
     * @return
     */
    List<PackagePrint> getPrintData(@Param("codes") String[] codes);

    /**
     * 修改打印状态
     * @param codes 序列号
     * @return
     */
    int updatePrintStatus(@Param("codes") String[] codes);

    /**
     * 根据工单号获取贴箱标志
     * @param workOrder 工单号
     * @return
     */
    String getFlagMark(@Param("workOrder") String workOrder);

	/**
	 * @author sjf
	 * @date 2017年2月5日 
	 * @param packageBarcodeId
	 * @return
     * 包装条码信息查询
	 *
	 */
	List<Map<String, Object>> searchPackageBarcode(@Param("packageBarcodeId") BigDecimal packageBarcodeId);
}
