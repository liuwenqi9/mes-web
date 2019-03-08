package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WorkOrderProductStockInException implements Serializable{

	 
	private String wip_entity_name;        //工单号
	private String mo_order;            //MO单号
	private String plan_line_code;        //生产线
	private String item_no ;              //物料编码
	private String prod_type ;             //型号
	private String description;          //物料描述
	private String wip_status ;           //工单状态
	private Integer start_quantity ;       //工单数量
	private Integer prod_quantity ;        //条码生产数
	private Integer mes_inv_quantity;      //条码完工数
	private Integer erp_inv_quantity ;     //ERP完工数
	private String scheduled_start_date;  //计划开工时间
	private String min_transaction_date ; //实际开工时间
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getMo_order() {
		return mo_order;
	}
	public void setMo_order(String mo_order) {
		this.mo_order = mo_order;
	}
	public String getPlan_line_code() {
		return plan_line_code;
	}
	public void setPlan_line_code(String plan_line_code) {
		this.plan_line_code = plan_line_code;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWip_status() {
		return wip_status;
	}
	public void setWip_status(String wip_status) {
		this.wip_status = wip_status;
	}
	public Integer getStart_quantity() {
		return start_quantity;
	}
	public void setStart_quantity(Integer start_quantity) {
		this.start_quantity = start_quantity;
	}
	public Integer getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(Integer prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public Integer getMes_inv_quantity() {
		return mes_inv_quantity;
	}
	public void setMes_inv_quantity(Integer mes_inv_quantity) {
		this.mes_inv_quantity = mes_inv_quantity;
	}
	public Integer getErp_inv_quantity() {
		return erp_inv_quantity;
	}
	public void setErp_inv_quantity(Integer erp_inv_quantity) {
		this.erp_inv_quantity = erp_inv_quantity;
	}
	public String getScheduled_start_date() {
		return scheduled_start_date;
	}
	public void setScheduled_start_date(String scheduled_start_date) {
		this.scheduled_start_date = scheduled_start_date;
	}
	public String getMin_transaction_date() {
		return min_transaction_date;
	}
	public void setMin_transaction_date(String min_transaction_date) {
		this.min_transaction_date = min_transaction_date;
	}
	
}
