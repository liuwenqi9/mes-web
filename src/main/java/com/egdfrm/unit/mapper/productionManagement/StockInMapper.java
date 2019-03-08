package com.egdfrm.unit.mapper.productionManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.StockInExcel;
import com.egdfrm.unit.model.barcodeManagement.StockInPrintRow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by tyq on 17/1/13.
 */
public interface StockInMapper {

	
	/**
	 * 入库单查询 导出excel
	 * @param map
	 * @return   
	 * @author	hgb
	 * @date 2017-5-5
	 */
	List<StockInExcel> stockInExportExcel(@Param("map") Map<String, Object> map);
	
    /**
     * 根据包装箱号ID判断是否已生成入库单号
     * @param pbIDs 包装箱号ID
     * @return
     */
    String isPackingBarCodeByNo(@Param("ids") String pbIDs);

    /**
     * 生成入库单
     * @param map
     */
    void generateStorageOrder(@Param("map") Map<String, Object> map);

    /**
     * 修改入库单
     * @param map
     */
    void updateDataInv(@Param("map") Map<String, Object> map);

    /**
     * 获取打印table数据
     * @param number 入库单号
     * @return
     */
    List<StockInPrintRow> getTableRows(@Param("number") String number);

    /**
     * 查询汇总数据
     * @param number 入库单号
     * @return
     */
    Map<String,Object> getTableHZ(@Param("number") String number);

    /**
     * 入库单打印_数据总数
     * @param map 查询条件
     * @return
     */
    long getStockInPrintersByCount(@Param("map") Map<String, Object> map);

    /**
     * 入库单打印_分页查询
     * @param pagination 分页添加
     * @param map 查询条件
     * @return
     */
    List<Map<String,Object>> getStockInPrinters(@Param("page") Pagination pagination, @Param("map") Map<String, Object> map);
}
