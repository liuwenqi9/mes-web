package com.egdfrm.unit.service.productionManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.StockInExcel;
import com.egdfrm.unit.model.barcodeManagement.StockInPrintRow;

import java.util.List;
import java.util.Map;

public interface StockInServiceI {
	
	
	List<StockInExcel> exportExcel(Map<String, Object> map);
	
	/**
	 * 入库单打印的分页数据
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param workOrderNumberSubpool 工单子库
	 * @param stockInIdentify 入库标识
	 * @return   
	 * @author	hgb
	 * @date 2017-1-6
	 */
	List<Map<String, Object>> getStockInPrinter(String productionLine,
			String workOrderNumber, String workOrderNumberSubpool,
			String stockInIdentify);
	
	/**  
	 * 入库单打印的分页数据
	 * @param page 当前页
	 * @param rows 行数
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param workOrderNumberSubpool 工单子库
	 * @param stockInIdentify 入库标识 
	 * @author	hgb
	 * @date 2017-1-6
	 */
	Map<String, Object> getStockInPrinters(Integer page, Integer rows,
			String productionLine, String workOrderNumber,
			String workOrderNumberSubpool, String stockInIdentify);

	/**
	 * 入库单查询的分页数据
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param stockInNumber 入库单号
	 * @param stockInIdentify 入库标识
	 * @return   
	 * @author	hgb
	 * @date 2017-1-6
	 */
	List<Map<String, Object>> getStockInSearch(String productionLine,
			String workOrderNumber, String stockInNumber, String stockInIdentify);

	/**
	 * 
	 * 入库单查询的分页数据
	 * @param page 当前页 
	 * @param rows 行数
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param stockInNumber 入库单号
	 * @param stockInIdentify 入库标识
	 * @return  
	 * @author	hgb
	 * @date 2017-1-6
	 */
	Map<String, Object> getStockInSearchs(Integer page, Integer rows,
			String productionLine, String workOrderNumber,
			String stockInNumber, String stockInIdentify);
	
	  
	/**
	 * 生成入库单数据 
	 * @param loginName
	 * @param packingBarcodeIds
	 * @return  本次入库单数据
	 * @author	hgb
	 * @date 2017-1-9
	 */
	List<Map<String, Object>> getInvNumberList(String loginName,String[] packingBarcodeIds);
	
	 
	List<Map<String, Object>> getInvNumberByInvNumber(String invNumber);
	
	Boolean isInvNumber(String[] packingBarcodeIds);

	/**
	 * 根据包装箱号ID判断是否已生成入库单号
	 * @param pbIDs 包装箱号ID
	 * @return
	 */
    String isPackingBarCodeByNo(String pbIDs);

	/**
	 * 生成入库单
	 * @param map
	 */
	void generateStorageOrder(Map<String, Object> map);

	/**
	 * 修改入库单
	 * @param map
	 */
    void updateDataInv(Map<String, Object> map);

	/**
	 * 获取打印table数据
	 * @param number 入库单号
	 * @return
	 */
	List<StockInPrintRow> getTableRows(String number);

	/**
	 * 查询汇总数据
	 * @param number 入库单号
	 * @return
	 */
	Map<String,Object> getTableHZ(String number);

	/**
	 * 入库单打印_分页查询
	 * @param pagination 分页添加
	 * @param map 查询条件
	 * @return
	 */
	Pagination getStockInPrinters(Pagination pagination, Map<String, Object> map);
}
