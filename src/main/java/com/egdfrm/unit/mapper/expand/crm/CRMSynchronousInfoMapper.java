package com.egdfrm.unit.mapper.expand.crm;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * @author sjf
 * @date 2017年01月16日 
 * CRM获取同步信息用MAPPER
 */
public interface CRMSynchronousInfoMapper {

	/**
	 * 更新Customer 的状态
	 * @param paramMap  customer_id,customer_status 
	 * @author	hgb
	 * @date 2017-8-23
	 */
	public void updateERPCustomerStatus(Map<String, Object> paramMap);

//	/**
//	 * @author sjf
//	 * @date 2017年1月17日 
//	 * @param paramMap
//	 * 插入客户信息中间表
//	 */
//	public void callGetCustomer(Map<String, Object> paramMap);
//	/**
//	 * @author sjf
//	 * @date 2017年1月17日 
//	 * @param paramMap
//	 *  插入收货方信息中间表
//	 */
//	public void callGetCustomerBuyer(Map<String, Object> paramMap);
//	/**
//	 * @author sjf
//	 * @date 2017年1月17日 
//	 * @param paramMap
//	 *  插入价目表中间表
//	 */
//	public void callGetPricList(Map<String, Object> paramMap);
//	/**
//	 * @author sjf
//	 * @date 2017年1月17日 
//	 * @param paramMap
//	 *  插入销售员信息中间表
//	 */
//	public void callGetSalesreps(Map<String, Object> paramMap);
//	/**
//	 * @author sjf
//	 * @date 2017年1月17日 
//	 * @param paramMap
//	 *  插入物料主数据中间表
//	 */
//	public void callGetItem(Map<String, Object> paramMap); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月17日 
//	 * @param paramMap
//	 *  插入专供信息中间表
//	 */
//	public void callGetItemExclusively(Map<String, Object> paramMap); 
	/**
	 * @author sjf
	 * @date 2017年1月17日 
	 * @param paramMap
	 *  插入优利德库存查询（实时接口）中间表 
	 */
	public void callGetInbentory(Map<String, Object> paramMap);  
//	/**
//	 * @author sjf
//	 * @date 2017年1月17日 
//	 * @param paramMap
//	 *  插入订单中间表
//	 */
//	public void callGetOrderVerification(Map<String, Object> paramMap);  

	/**
	 * @author sjf
	 * @date 2017年1月17日 
	 * @param paramMap
	 * 创建销售订单
	 */
	public void callSetOrder(Map<String, Object> paramMap);  
	
	 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取同步客户信息
	 */
	public List<Map<String, Object>> synchronousCuxCustomer(); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取收货方信息
	 */
	public List<Map<String, Object>> synchronousCuxCustomerReceivingParty(); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取价目表
	 */
	public List<Map<String, Object>> synchronousCuxPricList(); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取销售员信息 
	 */
	public List<Map<String, Object>> synchronousCuxSalesreps(); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取物料主数据 
	 */
	public List<Map<String, Object>> synchronousCuxItem(); 
	/**
	 *    物料主数据  修改状态 
	 * @author	hgb
	 * @date 2017-9-26
	 */
	public void updateSynchronousCuxItem();
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取专供信息 
	 */
	public List<Map<String, Object>> synchronousCuxItemExclusively(); 
	/**
	 * 专供信息 修改状态
	 * @return   
	 * @author	hgb
	 * @date 2017-9-26
	 */
	public void updateSynchronousCuxItemExclusively(); 
	/**
	 * @author sjf
	 * @param itemId 
	 * @date 2017年1月16日 
	 * @return
	 * 优利德库存查询（实时接口）
	 */
	public List<Map<String, Object>> synchronousCuxInventory(BigDecimal batchId); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取订单头 
	 */
	public List<Map<String, Object>> synchronousCuxOrderHeade(); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取订单行
	 */
	public List<Map<String, Object>> synchronousCuxOrderLine(); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取发运条码主表
	 */
	public List<Map<String, Object>> synchronousCuxShipmentBarCodeHeade(); 
	/**
	 * 发运条码主表 修改状态
	 *    
	 * @author	hgb
	 * @date 2017-9-26
	 */
	public void updateSynchronousCuxShipmentBarCodeHeade(); 
	
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 根据批次获取发运条码明细
	 */
	public List<Map<String, Object>> synchronousCuxShipmentBarCodeLine();
	/**
	 * 发运条码明细 修改状态
	 *    
	 * @author	hgb
	 * @date 2017-9-26
	 **/
	public void updateSynchronousCuxShipmentBarCodeLine();
	
	/**
	 * 同步 CUX_ITEM_SALE_RULE 表
	 * @return   
	 * @author	hgb
	 * @date 2017-5-23
	 */
	public List<Map<String, Object>> synchronousCuxItemsaleRule();
	/**
	 * CUX_ITEM_SALE_RULE 表 修改状态哦
	 * @return   
	 * @author	hgb
	 * @date 2017-9-26
	 */
	public void updateSynchronousCuxItemsaleRule();
	
	/**
	 *  同步修改订单数量，订单行取消
	 * @return   
	 * @author	hgb
	 * @date 2017-6-1
	 */
	public List<Map<String, Object>> chageOrCancelCuxOrder();
	
	/** 
	 * 同步修改订单数量，订单行取消  (修改标志，防止多次同步)   
	 * @author	hgb
	 * @date 2017-6-1
	 */
	public void chageOrCancelCuxOrderToUpdate();
	
	/**
	 * 同步默认地址
	 * @return   
	 * @author	hgb
	 * @date 2017-6-10
	 */
	public List<Map<String, Object>> synchronousPrimaryFlag();
	
	/**
	 * 同步默认地址 后（修改为TRANSFER_FLAG = 'Y'）
	 * @author	hgb
	 * @date 2017-6-10
	 */
	public void synchronousPrimaryFlagToUpdate(@Param("id") String id);
	
	/**
	 * 同步发运物流信息
	 * @return   
	 * @author	hgb
	 * @date 2017-9-25
	 */
	public List<Map<String, Object>> synchronousShoppingLogisticsInfo();
	/**
	 * 同步发运物流信息(全部修改为Y)
	 * @return   
	 * @author	hgb
	 * @date 2017-9-25
	 */
	public void updateShoppingLogisticsInfoToYes();
	 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除同步客户信息
//	 */
//	public void deleteCuxCustomer(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除收货方信息
//	 */
//	public void deleteCuxCustomerReceivingParty(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除价目表
//	 */
//	public void deleteCuxPricList(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除销售员信息 
//	 */
//	public void deleteCuxSalesreps(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除物料主数据 
//	 */
//	public void deleteCuxItem(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除专供信息 
//	 */
//	public void deleteCuxItemExclusively(BigDecimal batchId); 
	/**
	 * @author sjf
	 * @date 2017年1月16日 
	 * @return
	 * 优利德库存查询（实时接口）
	 */
	public void deleteCuxInventory(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除订单头 
//	 */
//	public void deleteCuxOrderHeade(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除订单行
//	 */
//	public void deleteCuxOrderLine(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除发运条码主表
//	 */
//	public void deleteCuxShipmentBarCodeHeade(BigDecimal batchId); 
//	/**
//	 * @author sjf
//	 * @date 2017年1月16日 
//	 * @return
//	 * 根据批次删除发运条码明细
//	 */
//	public void deleteCuxShipmentBarCodeLine(BigDecimal batchId); 
	
}