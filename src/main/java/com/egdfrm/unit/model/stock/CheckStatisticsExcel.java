package com.egdfrm.unit.model.stock;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CheckStatisticsExcel implements Serializable{
	
	
	private String start_date;
	private String end_date;
	private String transaction_date;
	/**
	 * excel model param
	 */
	private String plan_line_code; 
	private String wip_entity_name; 
	private String segment1; 
	private String segment2; 
	private String mo_order;
	private Integer start_quantity;
	private Integer transaction_quantity;
	private String status_type;
	private String description ;
	
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getStatus_type() {
		return status_type;
	}
	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getPlan_line_code() {
		return plan_line_code;
	}
	public void setPlan_line_code(String plan_line_code) {
		this.plan_line_code = plan_line_code;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getSegment2() {
		return segment2;
	}
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
	public String getMo_order() {
		return mo_order;
	}
	public void setMo_order(String mo_order) {
		this.mo_order = mo_order;
	}
	public Integer getStart_quantity() {
		return start_quantity;
	}
	public void setStart_quantity(Integer start_quantity) {
		this.start_quantity = start_quantity;
	}
	public Integer getTransaction_quantity() {
		return transaction_quantity;
	}
	public void setTransaction_quantity(Integer transaction_quantity) {
		this.transaction_quantity = transaction_quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
