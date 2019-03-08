package com.egdfrm.unit.ws;

import javax.jws.WebService;

@WebService
public interface CRMService {
	// 同步客户表
	public String[] synchronousCuxCustomer(); 
	// 同步收货方信息
	public String[] synchronousCuxCustomerReceivingParty(); 
	// 同步价目表
	public String[] synchronousCuxPricList(); 
	// 同步销售员信息 
	public String[] synchronousCuxSalesreps(); 
	// 同步物料主数据 
	public String[] synchronousCuxItem(); 
	// 同步专供信息 
	public String[] synchronousCuxItemExclusively(); 
	// 优利德库存查询（实时接口）
	public String[] synchronousCuxInventory(String itemId); 
	// 同步订单
	public String[] synchronousCuxOrder(String orderJson); 
	// 同步发运条码
	public String[] synchronousShipmentBarcode(); 
	// @date 2017-5-23   同步 CUX_ITEM_SALE_RULE 表  
	public String[] synchronousCuxItemsaleRule(); 
	// @date 2017 06-01 同步修改订单数量，订单行取消
	public String[] chageOrCancelCuxOrder();
	// @date 2017 06-10 同步默认地址
	public String[] synchronousPrimaryFlag();
	// @date 2017-08-23 时时，更新经销商 状态
	public String[] updateERPCustomerStatus(String dealerJson);
	//@date 2017-09-25 同步发运物流信息
	public String[] synchronousShoppingLogisticsInfo();
	
}
