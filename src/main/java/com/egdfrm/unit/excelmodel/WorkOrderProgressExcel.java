package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WorkOrderProgressExcel implements Serializable {
	
	
	
	private String wip_entity_name;
	private String mo_order;
	private String plan_line_code;
	private String item_no;
	private int start_quantity;//工单数量
	private int prod_quantity;//生产数量
	private int inv_quantity;//入库数量
	private int p_quantity;//检验通过数
	private int e_quantity;//检验NG数
	private String wip_status;
	private String prod_type;
	private String ATTRIBUTE4;//MO交期时间
	private String scheduled_start_date;//计划开工时间
	private String min_transaction_date;//实际开工时间
	private String description;
	
	
	public String getATTRIBUTE4() {
		return ATTRIBUTE4;
	}
	public void setATTRIBUTE4(String aTTRIBUTE4) {
		ATTRIBUTE4 = aTTRIBUTE4;
	}
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
	public int getStart_quantity() {
		return start_quantity;
	}
	public void setStart_quantity(int start_quantity) {
		this.start_quantity = start_quantity;
	}
	public int getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public int getInv_quantity() {
		return inv_quantity;
	}
	public void setInv_quantity(int inv_quantity) {
		this.inv_quantity = inv_quantity;
	}
	public int getP_quantity() {
		return p_quantity;
	}
	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}
	public int getE_quantity() {
		return e_quantity;
	}
	public void setE_quantity(int e_quantity) {
		this.e_quantity = e_quantity;
	}
	public String getWip_status() {
		return wip_status;
	}
	public void setWip_status(String wip_status) {
		this.wip_status = wip_status;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
