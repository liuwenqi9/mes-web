package com.egdfrm.unit.mapper.afterSaleMenagement;

import java.util.List;
import java.util.Map;

import com.egdfrm.unit.model.AfterSaleManagement.AfterSalePeople;
import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.AfterSaleSummaryExcel;
import com.egdfrm.unit.model.AfterSaleManagement.AfterSaleBack;
/**
 * 售后退货
 * @author hgb
 * @date 2017-6-15
 */
public interface AfterSaleBackMapper {

	/**
	 *  客户信息总数据
	 */
	public  long getCustomerInfoCount(@Param("customer") String customer);
	
	/**
	 * 客户信息 分页数据 
	 */
	public List<Map<String, Object>> getCustomerInfoList(@Param("page")Pagination pagination,@Param("customer") String customer);
	
	public List<String> isValid(@Param("barcodeText") String barcodeText);
	
	public List<Map<String, Object>> getBarcodeInfoByBarcodeText(@Param("barcodeText") String barcodeText);

	public int saveMoel(@Param("model")AfterSaleBack afterSaleBack,@Param("uid")String uid);
	
	/**
	 *  总条数
	 */
	public  long getAfterSaleSummaryCount(@Param("model")AfterSaleBack afterSaleBack);
	
	/**
	 * 分页列表
	 */
	public List<AfterSaleBack> getAfterSaleSummaryList(@Param("page")Pagination pagination,@Param("model")AfterSaleBack afterSaleBack);

	/**
	 *  售后维修记录 （ 导出excel）  
	 * @date 2017-10-30
	 */
	List<AfterSaleSummaryExcel> exportExcel(@Param("model")AfterSaleBack afterSaleBack);
	
	/**
	 * 获取维修单号
	 */ 
	public void callGetRepWorkOrder(Map<String, Object> map);
	
	/**
	 * 根据id 修改维修单
	 */
	public int updateByRepWorkOrder(@Param("ID") String ID,@Param("repWorkOrder")String repWorkOrder,@Param("uid")String uid);
	
	/** 
	 *  获取打印信息
	 */
	public List<AfterSaleBack> getPrinterInfo(@Param("repWorkOrder")String repWorkOrder);
	/**
	 * 按 行 获取打印信息 
	 * @author	hgb
	 * @date 2017-10-13
	 */
	public List<AfterSaleBack> getPrinterInfoByIds(@Param("IDS") Integer[] IDS);
	
	/**
	 * 更改为已打印状态
	 * @date 2017-10-13
	 */
	public void updatePrinterStatusByIds(@Param("IDS") Integer[] IDS);
	
	/**
	 * 弹出维修记录框
	 * 根据id 取 维修单号，维修人员，补号SO，处理类型，维修分析，故障描述
	 */
	public List<Map<String, Object>> getLineInfoById(@Param("ID")String ID);
	
	/**
	 * 获取更新行信息
	 */
	public List<Map<String, Object>> getUpdateLineInfoById(@Param("ID")String ID);
	
	/**
	 * 从erp获取所有的维修人
	 */
	public List<Map<String, Object>> getRepPeopleAll();
	/**
	 * 更新
	 */
	public int update(@Param("model")AfterSaleBack afterSaleBack,@Param("uid")String uid);
	 
	/**
	 * 更新 一条
	 */
	public int updateById(@Param("model")AfterSaleBack afterSaleBack,@Param("uid")String uid);
	
	/**
	 * 批量更新 交仓库日期 
	 */
	public int updateHandOverByIds(@Param("model")AfterSaleBack afterSaleBack,@Param("uid")String uid);
	
	
	public List<String> isValidToShip(@Param("PRODUCT_BARCODE") String PRODUCT_BARCODE,@Param("REP_WORK_ORDER")String REP_WORK_ORDER);
	
	public List<Map<String, Object>> getBarcodeInfoByBarcodeTextOrrEpWorkOrder(@Param("PRODUCT_BARCODE") String PRODUCT_BARCODE,@Param("REP_WORK_ORDER")String REP_WORK_ORDER);

	public int upateToShip(@Param("model")AfterSaleBack afterSaleBack,@Param("uid")String uid);
	
	public int upateToSave(@Param("model")AfterSaleBack afterSaleBack,@Param("uid")String uid);
	  
	/**
	 *  售后出货 tatol
	 */
	public long getAfterSaleOutCount(@Param("model")AfterSaleBack afterSaleBack);
	
	/**
	 * 分页列表
	 */
	public List<AfterSaleBack> getAfterSaleOutList(@Param("page")Pagination pagination,@Param("model")AfterSaleBack afterSaleBack);


	/**
	 *  总条数
	 */
	public  long getAfterSalePeopleCount(@Param("model")AfterSalePeople afterSalePeople);

	/**
	 * 分页列表
	 */
	public List<AfterSalePeople> getAfterSalePeopleList(@Param("page")Pagination pagination,@Param("model")AfterSalePeople afterSalePeople);

	/**
	 *  修改有效时间
	 */
	public int updatePeopleById(@Param("model")AfterSalePeople afterSalePeople,@Param("uid")String uid);

	public int peopleAdd(@Param("model")AfterSalePeople afterSalePeople,@Param("uid")String uid);

	public List<AfterSalePeople> peopleValidation(@Param("model")AfterSalePeople afterSalePeople);
}
