package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WXOutExcel implements Serializable {
	private String document_num    ; //出货通知单号
    private String order_number    ; //销售订单
    private Integer line_number     ; //行号
    private String date_scheduled  ; //SO交期
    private String cust_po_number  ; //MO
    private String segment1        ; //产品编码
    private String prod_type       ; //型号
    private String custom_model; //客户机型
    private Integer requested_quantity    ; //需求数量
    private String wip_entity_name       ; //工单号
    private String status_type           ; //工单状态
    private String attribute7            ; //生产线
    private String scheduled_start_date  ; //计划生产日期
    private String scheduled_ship_date;    //; 计划出货日期
    private String cur_shipped_quantity;  //行发运数量
    private Integer start_quantity        ; //工单数量
    private Integer quantity_completed    ; //完工数量
    private Integer ship_quantity         ; //已发运数量 
    private Integer shipped_quantity         ; //erp运数量 
    private Integer prod_quantity  ;//  生产数量
    private String min_transaction_date;//   实际开工时间
    
	public String getCur_shipped_quantity() {
		return cur_shipped_quantity;
	}
	public void setCur_shipped_quantity(String cur_shipped_quantity) {
		this.cur_shipped_quantity = cur_shipped_quantity;
	}
	public String getDocument_num() {
		return document_num;
	}
	public void setDocument_num(String document_num) {
		this.document_num = document_num;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public Integer getLine_number() {
		return line_number;
	}
	public void setLine_number(Integer line_number) {
		this.line_number = line_number;
	}
	public String getDate_scheduled() {
		return date_scheduled;
	}
	public void setDate_scheduled(String date_scheduled) {
		this.date_scheduled = date_scheduled;
	}
	public String getCust_po_number() {
		return cust_po_number;
	}
	public void setCust_po_number(String cust_po_number) {
		this.cust_po_number = cust_po_number;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public String getCustom_model() {
		return custom_model;
	}
	public void setCustom_model(String custom_model) {
		this.custom_model = custom_model;
	}
	public Integer getRequested_quantity() {
		return requested_quantity;
	}
	public void setRequested_quantity(Integer requested_quantity) {
		this.requested_quantity = requested_quantity;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getStatus_type() {
		return status_type;
	}
	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}
	public String getAttribute7() {
		return attribute7;
	}
	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}
	public String getScheduled_start_date() {
		return scheduled_start_date;
	}
	public void setScheduled_start_date(String scheduled_start_date) {
		this.scheduled_start_date = scheduled_start_date;
	} 
	public String getScheduled_ship_date() {
		return scheduled_ship_date;
	}
	public void setScheduled_ship_date(String scheduled_ship_date) {
		this.scheduled_ship_date = scheduled_ship_date;
	}
	public Integer getStart_quantity() {
		return start_quantity;
	}
	public void setStart_quantity(Integer start_quantity) {
		this.start_quantity = start_quantity;
	}
	public Integer getQuantity_completed() {
		return quantity_completed;
	}
	public void setQuantity_completed(Integer quantity_completed) {
		this.quantity_completed = quantity_completed;
	}
	public Integer getShip_quantity() {
		return ship_quantity;
	}
	public void setShip_quantity(Integer ship_quantity) {
		this.ship_quantity = ship_quantity;
	}
	public Integer getShipped_quantity() {
		return shipped_quantity;
	}
	public void setShipped_quantity(Integer shipped_quantity) {
		this.shipped_quantity = shipped_quantity;
	}
	public Integer getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(Integer prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public String getMin_transaction_date() {
		return min_transaction_date;
	}
	public void setMin_transaction_date(String min_transaction_date) {
		this.min_transaction_date = min_transaction_date;
	}
    
    
    
	
}
