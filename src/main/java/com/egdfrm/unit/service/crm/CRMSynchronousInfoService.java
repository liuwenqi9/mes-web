package com.egdfrm.unit.service.crm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.egdfrm.extend.common.DateUtil;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.mapper.expand.crm.CRMSynchronousInfoMapper;
import com.egdfrm.unit.mapper.standard.CuxOrderHeadeHisMapper;
import com.egdfrm.unit.mapper.standard.CuxOrderHeadeMapper;
import com.egdfrm.unit.mapper.standard.CuxOrderLineMapper;
import com.egdfrm.unit.model.standard.CuxOrderHeade;
import com.egdfrm.unit.model.standard.CuxOrderHeadeCriteria;
import com.egdfrm.unit.model.standard.CuxOrderHeadeHis;
import com.egdfrm.unit.model.standard.CuxOrderHeadeHisCriteria;
import com.egdfrm.unit.model.standard.CuxOrderLine;
import com.egdfrm.unit.model.standard.CuxOrderLineCriteria;
/**
 * @author sjf
 * @date 20170116 CRM获取同步信息用SERVICE
 **/
@Service
public class CRMSynchronousInfoService {
	/**
	 * <p>
	 * Field crmsim: CRM获取同步信息用
	 * </p>
	 */
	@Autowired
	private CRMSynchronousInfoMapper crmsim;
	/**
	 * <p>
	 * Field cohm: 订单头Mapper
	 * </p>
	 */
	@Autowired
	private CuxOrderHeadeMapper cohm;
	/**
	 * <p>
	 * Field cohm: 订单头历史表Mapper
	 * </p>
	 */
	@Autowired
	private CuxOrderHeadeHisMapper cohhm;
	/**
	 * <p>
	 * Field colm: 订单行Mapper
	 * </p>
	 */
	@Autowired
	private CuxOrderLineMapper colm;

	/**
	 * @author sjf
	 * @date 2017年1月16日
	 * @return 同步客户信息
	 */
	public String[] synchronousCuxCustomer() {
		String[] retVal = new String[2];
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// //插入中间表
		// crmsim.callGetCustomer(paramMap);
		// //返回批次
		// BigDecimal batchId=(BigDecimal) paramMap.get(MesConstants.BATCH_ID);
		// //返回状态
		// String status=(String) paramMap.get(MesConstants.STATUS);
		// //返回信息
		// String message=(String) paramMap.get(MesConstants.MESSAGE);
		// if(!MesConstants.SUCCESS.equals(status)){
		// retVal[0] = status;
		// retVal[1] = message;
		// return retVal;
		// }
		// 查询
		List<Map<String, Object>> ccList = crmsim.synchronousCuxCustomer();
		// 删除
		// crmsim.deleteCuxCustomer(batchId);

		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(ccList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月17日
	 * @return 获取收货方信息
	 */
	public String[] synchronousCuxCustomerReceivingParty() {
		String[] retVal = new String[2];
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// //插入中间表
		// crmsim.callGetCustomerBuyer(paramMap);
		// //返回批次
		// BigDecimal batchId=(BigDecimal) paramMap.get(MesConstants.BATCH_ID);
		// //返回状态
		// String status=(String) paramMap.get(MesConstants.STATUS);
		// //返回信息
		// String message=(String) paramMap.get(MesConstants.MESSAGE);
		// if(!MesConstants.SUCCESS.equals(status)){
		// retVal[0] = status;
		// retVal[1] = message;
		// return retVal;
		// }
		// 查询
		List<Map<String, Object>> ccList = crmsim
				.synchronousCuxCustomerReceivingParty();
		// //删除
		// crmsim.deleteCuxCustomerReceivingParty(batchId);
		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(ccList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月17日
	 * @return 同步价目表
	 */
	public String[] synchronousCuxPricList() {
		String[] retVal = new String[2];
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// //插入中间表
		// crmsim.callGetPricList(paramMap);
		// //返回批次
		// BigDecimal batchId=(BigDecimal) paramMap.get(MesConstants.BATCH_ID);
		// //返回状态
		// String status=(String) paramMap.get(MesConstants.STATUS);
		// //返回信息
		// String message=(String) paramMap.get(MesConstants.MESSAGE);
		// if(!MesConstants.SUCCESS.equals(status)){
		// retVal[0] = status;
		// retVal[1] = message;
		// return retVal;
		// }
		// 查询
		List<Map<String, Object>> ccList = crmsim.synchronousCuxPricList();
		// 删除
		// crmsim.deleteCuxPricList(batchId);
		// 处理日期
		Utils.formatListMapDate(ccList);
		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(ccList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月17日
	 * @return 同步销售员信息
	 */
	public String[] synchronousCuxSalesreps() {
		String[] retVal = new String[2];
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// //插入中间表
		// crmsim.callGetSalesreps(paramMap);
		// //返回批次
		// BigDecimal batchId=(BigDecimal) paramMap.get(MesConstants.BATCH_ID);
		// //返回状态
		// String status=(String) paramMap.get(MesConstants.STATUS);
		// //返回信息
		// String message=(String) paramMap.get(MesConstants.MESSAGE);
		// if(!MesConstants.SUCCESS.equals(status)){
		// retVal[0] = status;
		// retVal[1] = message;
		// return retVal;
		// }
		// 查询
		List<Map<String, Object>> ccList = crmsim.synchronousCuxSalesreps();
		// 删除
		// crmsim.deleteCuxSalesreps(batchId);
		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(ccList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月17日
	 * @return 同步物料主数据
	 */
	public String[] synchronousCuxItem() {
		String[] retVal = new String[2];
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// //插入中间表
		// crmsim.callGetItem(paramMap);
		// //返回批次
		// BigDecimal batchId=(BigDecimal) paramMap.get(MesConstants.BATCH_ID);
		// //返回状态
		// String status=(String) paramMap.get(MesConstants.STATUS);
		// //返回信息
		// String message=(String) paramMap.get(MesConstants.MESSAGE);
		// if(!MesConstants.SUCCESS.equals(status)){
		// retVal[0] = status;
		// retVal[1] = message;
		// return retVal;
		// }
		// 查询
		List<Map<String, Object>> ccList = crmsim.synchronousCuxItem();
		
		//修改为Y
		crmsim.updateSynchronousCuxItem();
		// 删除
		// crmsim.deleteCuxItem(batchId);
		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(ccList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月17日
	 * @return 同步专供信息
	 */
	public String[] synchronousCuxItemExclusively() {
		String[] retVal = new String[2];
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// //插入中间表
		// crmsim.callGetItemExclusively(paramMap);
		// //返回批次
		// BigDecimal batchId=(BigDecimal) paramMap.get(MesConstants.BATCH_ID);
		// //返回状态
		// String status=(String) paramMap.get(MesConstants.STATUS);
		// //返回信息
		// String message=(String) paramMap.get(MesConstants.MESSAGE);
		// if(!MesConstants.SUCCESS.equals(status)){
		// retVal[0] = status;
		// retVal[1] = message;
		// return retVal;
		// }
		// 查询
		List<Map<String, Object>> ccList = crmsim
				.synchronousCuxItemExclusively();
		//修改状态
		crmsim.updateSynchronousCuxItemExclusively();
		// 删除
		// crmsim.deleteCuxItemExclusively(batchId);
		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(ccList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月17日
	 * @return 优利德库存查询（实时接口）
	 */
	public String[] synchronousCuxInventory(String itemId) {
		String[] retVal = new String[2];
		// 传入值校验
		if (StringUtils.isEmpty(itemId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "请传入物料ID";
			return retVal;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 传入参数物料ID
		paramMap.put("item_id", Integer.parseInt(itemId));
		// 插入中间表
		crmsim.callGetInbentory(paramMap);
		// 返回批次
		BigDecimal batchId = (BigDecimal) paramMap.get(MesConstants.BATCH_ID);
		// 返回状态
		String status = (String) paramMap.get(MesConstants.STATUS);
		// 返回信息
		String message = (String) paramMap.get(MesConstants.MESSAGE);
		if (!MesConstants.SUCCESS.equals(status)) {
			retVal[0] = status;
			retVal[1] = message;
			return retVal;
		}
		// 查询
		List<Map<String, Object>> ccList = crmsim
				.synchronousCuxInventory(batchId);
		// 删除
		crmsim.deleteCuxInventory(batchId);
		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(ccList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月17日
	 * @return 同步订单
	 */
	public String[] synchronousCuxOrder(String orderJson) {
		String[] retVal = new String[2];
		// 转成Map对象
		Map<String, Object> orderHeadMap = JsonObjectConverTools.jsonToObject(
				orderJson, Map.class);
		// crm订单号
		Object crmOrderNumberObj = orderHeadMap.get("crm_order_number");
		String customerPo = null;
		if(orderHeadMap.get("custom_po")!=null){
			customerPo = orderHeadMap.get("custom_po").toString();
		}
		String attribute1 = null;
		if(orderHeadMap.get("remark")!=null){
			attribute1 = orderHeadMap.get("remark").toString();
		}
		// 发运方法 SHIP_METHOD   发货地址备注 ADDRESS 联系人 CONTACTS_NAME   电话 PHONE 
		String shipMethod = null;
		if(orderHeadMap.get("express_way")!=null){
			shipMethod = orderHeadMap.get("express_way").toString();
		}
		String address = null;
		if(orderHeadMap.get("unusual_add")!=null){
			address = orderHeadMap.get("unusual_add").toString();
		}
		String contactsName = null;
		if(orderHeadMap.get("contact_name")!=null){
			contactsName = orderHeadMap.get("contact_name").toString();
		}
		String phone = null;
		if(orderHeadMap.get("contact_tel")!=null){
			phone = orderHeadMap.get("contact_tel").toString();
		}
		
		System.out.println("log===>"+shipMethod+","+address+","+contactsName+","+phone);
		
		if(crmOrderNumberObj==null){
			retVal[0]=MesConstants.ERROR;
			retVal[1]="crm订单号不能为空";
			return retVal;
		}
		String crmOrderNumber =(String) crmOrderNumberObj;
		/**
		 * 根据传过来的CRM订单号判断，如果之前创建过销售订单，则取历史表数据，不再创建销售订单 
		 */
		List<CuxOrderHeadeHis> cohHistoryList =new ArrayList<CuxOrderHeadeHis>();
		CuxOrderHeadeHis cohHistory=new CuxOrderHeadeHis();
		CuxOrderHeadeHisCriteria cohc=new CuxOrderHeadeHisCriteria();
		cohc.createCriteria().andCrmOrderNumberEqualTo(crmOrderNumber).andProcessStatusEqualTo(MesConstants.Big1001);
		cohHistoryList=cohhm.selectByExample(cohc);
		if(!cohHistoryList.isEmpty()){
			//能取到，则之前创建过销售订单，直接返回
			cohHistory=cohHistoryList.get(0);
			
			retVal[0]=MesConstants.SUCCESS;
			retVal[1]=cohHistory.getErpOrderNumber().toString();
			return retVal;
		}  
		/**
		 * 插入订单头
		 */
		// erp订单号，erp生成订单后回写
		// 状态
		BigDecimal processStatus = MesConstants.Big1000;
		// 错误描述
		// 默认101
		Object orgIdObj=orderHeadMap.get("org_id");
		BigDecimal orgId = (orgIdObj==null?MesConstants.Big101:new BigDecimal((String)orgIdObj)); 
		// 订单来源，默认0
		Object orderSourceIdObj = orderHeadMap.get("order_source_id");
		BigDecimal orderSourceId =(orderSourceIdObj==null?MesConstants.Big0:new BigDecimal((String) orderSourceIdObj)); 
		// 订单日期
		Object orderDateObj = orderHeadMap.get("order_date");
		if(orderDateObj==null){
			retVal[0]=MesConstants.ERROR;
			retVal[1]="订单日期不能为空";
			return retVal;
		}  
		Date orderDate = DateUtil.StringToDate((String) orderDateObj); 
		// 订单类型
		Object orderTypeObj = orderHeadMap.get("order_type");
		if(orderTypeObj==null){
			retVal[0]=MesConstants.ERROR;
			retVal[1]="订单类型不能为空";
			return retVal;
		}
		String orderType = orderTypeObj.toString(); 
		// 价目表ID
		Object priceListIdObj = orderHeadMap.get("price_list_id");
		if(priceListIdObj==null){
			retVal[0]=MesConstants.ERROR;
			retVal[1]="价目表ID不能为空";
			return retVal;
		}
		BigDecimal priceListId = new BigDecimal((String)priceListIdObj); 
		// 销售人员
		Object salesrepIdObj=orderHeadMap.get("salesrep_id");
		BigDecimal salesrepId =(salesrepIdObj==null?null:new BigDecimal((String)salesrepIdObj)); 
		// 客户ID
		Object soldToOrgIdObj = orderHeadMap.get("sold_to_org_id");
		if(soldToOrgIdObj==null){
			retVal[0]=MesConstants.ERROR;
			retVal[1]="客户ID不能为空";
			return retVal;
		}
		BigDecimal soldToOrgId = new BigDecimal((String)soldToOrgIdObj); 
		// 销售OU,取销售员信息表中的org_id字段
		Object soldFromOrgIdObj = orderHeadMap.get("sold_from_org_id");
		BigDecimal soldFromOrgId =(soldFromOrgIdObj==null?null:new BigDecimal((String)soldFromOrgIdObj)); 
		// 发运地址id
		Object shipToOrgIdObj = orderHeadMap.get("ship_to_org_id");
		BigDecimal shipToOrgId = (shipToOrgIdObj==null?null:new BigDecimal((String)shipToOrgIdObj)); 
		// 货币单位，默认：CNY
		Object transactionalCurrCodeObj = orderHeadMap.get("transactional_curr_code");
		if(transactionalCurrCodeObj==null){
			retVal[0]=MesConstants.ERROR;
			retVal[1]="货币单位不能为空";
			return retVal;
		}
		String transactionalCurrCode = (String)transactionalCurrCodeObj; 
		// 登记，默认给'Y'
		Object bookedFlagObj = orderHeadMap.get("booked_flag");
		String bookedFlag = (bookedFlagObj==null?MesConstants.YES:(String)bookedFlagObj);  
		/*
		 * 存入对象中
		 */
		CuxOrderHeade coh = new CuxOrderHeade();
		coh.setCrmOrderNumber(crmOrderNumber);
		coh.setProcessStatus(processStatus);
		coh.setOrgId(orgId);
		coh.setOrderSourceId(orderSourceId);
		coh.setOrderDate(orderDate);
		coh.setOrderType(orderType);
		coh.setPriceListId(priceListId);
		coh.setSalesrepId(salesrepId);
		coh.setSoldToOrgId(soldToOrgId);
		coh.setSoldFromOrgId(soldFromOrgId);
		coh.setShipToOrgId(shipToOrgId);
		coh.setTransactionalCurrCode(transactionalCurrCode);
		coh.setBookedFlag(bookedFlag);
		coh.setCustomerPo(customerPo);
		coh.setAttribute1(attribute1);
		coh.setShipMethod(shipMethod);
		coh.setAddress(address);
		coh.setContactsName(contactsName);
		coh.setPhone(phone);
		
		// 插入订单头
		this.cohm.insert(coh);
		
		//获取订单行
		Object orderLineListObj = orderHeadMap.get("dlist");
		if(orderLineListObj==null){
			retVal[0]=MesConstants.ERROR;
			retVal[1]="订单行不能为空";
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
			return retVal;
		}
		List<Map<String, Object>> orderLineList = (List<Map<String, Object>>)orderLineListObj;
		/**
		 * 循环插入订单行
		 */
		for (Map<String, Object> orderLineMap : orderLineList) {
			// CRM订单号
			Object lineCrmOrderNumberObj = orderLineMap.get("crm_order_number");
			if(lineCrmOrderNumberObj==null){
				retVal[0]=MesConstants.ERROR;
				retVal[1]="订单行CRM订单号不能为空";
				TransactionAspectSupport.currentTransactionStatus()
				.setRollbackOnly();
				return retVal;
			}
			String lineCrmOrderNumber = (String)lineCrmOrderNumberObj; 
			// OU ID
			Object lineOrgIdObj = orderLineMap.get("org_id");
			BigDecimal lineOrgId =(lineOrgIdObj==null?MesConstants.Big101:new BigDecimal((String)lineOrgIdObj)); 
			// 行号
			Object lineLineNumberObj = orderLineMap.get("line_number");
			String lineLineNumber = (String) lineLineNumberObj; 
			// 订单来源
			Object lineOrderSourceIdObj = orderLineMap.get("order_source_id");
			BigDecimal lineOrderSourceId = (lineOrderSourceIdObj==null?MesConstants.Big0:new BigDecimal((String)lineOrderSourceIdObj)); 
			// 物料ID
			Object lineInventoryItemIdObj = orderLineMap.get("inventory_item_id");
			if(lineInventoryItemIdObj==null){
				retVal[0]=MesConstants.ERROR;
				retVal[1]="订单行物料ID不能为空";
				TransactionAspectSupport.currentTransactionStatus()
				.setRollbackOnly();
				return retVal;
			}
			BigDecimal lineInventoryItemId = new BigDecimal((String)lineInventoryItemIdObj); 
			// 订单数量
			Object lineOrderQuantityObj = orderLineMap.get("ordered_quantity");
			if(lineOrderQuantityObj==null){
				retVal[0]=MesConstants.ERROR;
				retVal[1]="订单行订单数量不能为空";
				TransactionAspectSupport.currentTransactionStatus()
				.setRollbackOnly();
				return retVal;
			}
			BigDecimal lineOrderQuantity = new BigDecimal(lineOrderQuantityObj.toString()); 
			// 单位
			Object lineOrderQuantityUomObj = orderLineMap.get("order_quantity_uom");
			String lineOrderQuantityUom = (String)lineOrderQuantityUomObj;
			// 价目表价格
			Object lineUnitListPricObj = orderLineMap.get("unit_list_pric");
			if(lineUnitListPricObj==null){
				retVal[0]=MesConstants.ERROR;
				retVal[1]="订单行价目表价格不能为空";
				TransactionAspectSupport.currentTransactionStatus()
				.setRollbackOnly();
				return retVal;
			}
			BigDecimal lineUnitListPric = new BigDecimal(lineUnitListPricObj.toString()); 
			// 实际销售价格
			Object lineUnitSellingPricObj = orderLineMap.get("unit_selling_pric");
			//销售订单是项目订单，并且实际销售价格为空，则报错
			if(crmOrderNumber.charAt(0)==MesConstants.CRMORDERNUMBER_X&&lineUnitSellingPricObj==null){
				retVal[0]=MesConstants.ERROR;
				retVal[1]="项目订单的订单行实际销售价格不能为空";
				TransactionAspectSupport.currentTransactionStatus()
				.setRollbackOnly();
				return retVal;
			}
			BigDecimal lineUnitSellingPric = (lineUnitSellingPricObj==null?null:new BigDecimal(lineUnitSellingPricObj.toString())); 
			// 登记
			Object lineBookedFlagObj = orderLineMap.get("booked_flag");
			String lineBookedFlag = (lineBookedFlagObj==null?MesConstants.YES:(String)lineBookedFlagObj);  
			// 备注
			String lineLineRemarks = (String) orderLineMap.get("line_remarks"); 
			// 收货地点
			String lineLineShipTo = (String) orderLineMap.get("line_ship_to"); 
			// Y：为成功，导入失败就为错误信息
			/*
			 * 存入对象中
			 */
			CuxOrderLine col = new CuxOrderLine();
			col.setCrmOrderNumber(lineCrmOrderNumber);
			col.setOrgId(lineOrgId);
			col.setLineNumber(lineLineNumber);
			col.setOrderSourceId(lineOrderSourceId);
			col.setInventoryItemId(lineInventoryItemId);
			col.setOrderQuantity(lineOrderQuantity);
			col.setOrderQuantityUom(lineOrderQuantityUom);
			col.setUnitListPric(lineUnitListPric);
			col.setUnitSellingPric(lineUnitSellingPric);
			col.setBookedFlag(lineBookedFlag);
			col.setLineRemarks(lineLineRemarks);
			col.setLineShipTo(lineLineShipTo);
//			col.setProcessStatus(lineProcessStatus);

			// 插入订单行
			this.colm.insert(col);
		}
		/**
		 * 创建销售订单
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("crmOrderNumber", crmOrderNumber);
		// 创建销售订单
		crmsim.callSetOrder(paramMap); 
		// 返回状态
		String status = (String) paramMap.get(MesConstants.STATUS);
		// 返回错误信息
		String message = (String) paramMap.get(MesConstants.MESSAGE);
		//如果创建失败返回错误信息
		if (!MesConstants.SUCCESS.equals(status)) {
			retVal[0] = status;
			retVal[1] = message;
			return retVal;
		} 
		/*
		 *  获取创建完销售订单的订单头表
		 */
		CuxOrderHeadeCriteria newCohc=new CuxOrderHeadeCriteria();
		newCohc.createCriteria().andCrmOrderNumberEqualTo(crmOrderNumber);
		
		List<CuxOrderHeade> newCohList=new ArrayList<CuxOrderHeade>();
		CuxOrderHeade newCoh = new CuxOrderHeade();
		newCohList=cohm.selectByExample(newCohc);

		newCoh = newCohList.get(0);
		//删除订单头、行接口表数据
		cohm.deleteByExample(newCohc);
		CuxOrderLineCriteria newColc=new CuxOrderLineCriteria();
		newColc.createCriteria().andCrmOrderNumberEqualTo(crmOrderNumber);
		colm.deleteByExample(newColc);
		//如果创建成功返回ERP订单号
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = newCoh.getErpOrderNumber().toString();
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2017年1月19日
	 * @return 同步发运条码
	 */
	public String[] synchronousShipmentBarcode() {
		String[] retVal = new String[2];
		// 查询发运条码头表
		List<Map<String, Object>> barcodeHeadList = crmsim
				.synchronousCuxShipmentBarCodeHeade();
		// 查询发运条码行表
		List<Map<String, Object>> barcodeLineList = crmsim
				.synchronousCuxShipmentBarCodeLine();
		
		//修改为Y
		crmsim.updateSynchronousCuxShipmentBarCodeHeade();
		crmsim.updateSynchronousCuxShipmentBarCodeLine();
		
		// 处理日期型
		Utils.formatListMapDate(barcodeHeadList);
		// 处理日期型
		Utils.formatListMapDate(barcodeLineList);
		// 将行List放入头List
		Utils.headPutLine(barcodeHeadList, barcodeLineList,
				"MO_REQUEST_NUMBER", "MO_REQUEST_NUMBER");
		// 转换json格式
		String retJson = JsonObjectConverTools.listToJson(barcodeHeadList);

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
	}

	/**
	 * 同步 CUX_ITEM_SALE_RULE 表
	 * @return   
	 * @author	hgb
	 * @date 2017-5-23
	 */
	public String[] synchronousCuxItemsaleRule(){
		String[] retVal = new String[2];
		List<Map<String, Object>> list = crmsim.synchronousCuxItemsaleRule();
		crmsim.updateSynchronousCuxItemsaleRule();
		Utils.formatListMapDate(list);
		String retJson = JsonObjectConverTools.listToJson(list);
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
		
	}
	
	/**
	 *  同步修改订单数量，订单行取消
	 * @return   
	 * @author	hgb
	 * @date 2017-6-1
	 */
	public String[] chageOrCancelCuxOrder() { 
		String[] retVal = new String[2];
		List<Map<String, Object>> list = crmsim.chageOrCancelCuxOrder();
		crmsim.chageOrCancelCuxOrderToUpdate();
		Utils.formatListMapDate(list);
		String retJson = JsonObjectConverTools.listToJson(list);
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
		}
	/**
	 * 同步默认地址
	 * @return   
	 * @author	hgb
	 * @date 2017-6-10
	 */
	public String[] synchronousPrimaryFlag() { 
		String[] retVal = new String[2];
		List<Map<String, Object>> list = crmsim.synchronousPrimaryFlag(); 
		for (Map<String, Object> map : list) {
			if(map!=null && !map.isEmpty()){
				String id = null;
				if(map.get("SHIP_TO_LOCATION_ID")!=null){
					id = map.get("SHIP_TO_LOCATION_ID").toString(); 
				}
				//根据收货方id 去修改
				if(id!=null){crmsim.synchronousPrimaryFlagToUpdate(id);}
			} 
		}  
		Utils.formatListMapDate(list);
		String retJson = JsonObjectConverTools.listToJson(list);
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;
		}
	
	/**
	 *  更新经销商 状态  
	 * @author	hgb
	 * @date 2017-8-23
	 */
	@SuppressWarnings("unchecked")
	public String[] updateERPCustomerStatus(String dealerJson) {
		String[] retVal = new String[2];
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = "更新成功";
		// 转成Map对象 
		Map<String, Object> customerMap = JsonObjectConverTools.jsonToObject(dealerJson, Map.class);
		// customer_id
		Object customer_id = customerMap.get("dealer_id"); 
		//customer_status
		Object customer_status = customerMap.get("dealer_status");
		if(customer_id == null){
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "dealer_id is not null";
			return retVal;
		}
		if(customer_status == null){
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "customer_status is not null";
			return retVal;
		} else{
			if("10021001".equals(customer_status)){
				customer_status = "Y";
			}else{
				customer_status = "N";
			}
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customer_id", Integer.parseInt(customer_id.toString()));
		
		paramMap.put("customer_status", customer_status);
		paramMap.put("status", null);
		paramMap.put("message", null);
		crmsim.updateERPCustomerStatus(paramMap);
		if(!"S".equals(paramMap.get("status"))){
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "updateERPCustomerStatus is errer.";
			return retVal;
		} 
		return retVal;
		}
	
	
	/**
	 * 同步发运物流信息
	 * @return   
	 * @author	hgb
	 * @date 2017-9-25
	 */
	public String[] synchronousShoppingLogisticsInfo() {
		String[] retVal = new String[2];
		List<Map<String, Object>> list = crmsim.synchronousShoppingLogisticsInfo();
		//全表 修改为 Y, 
		crmsim.updateShoppingLogisticsInfoToYes();
		
		Utils.formatListMapDate(list);
		String retJson = JsonObjectConverTools.listToJson(list);
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = retJson;
		return retVal;

	}
}
