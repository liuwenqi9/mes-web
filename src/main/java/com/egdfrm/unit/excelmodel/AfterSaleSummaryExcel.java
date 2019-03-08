package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

/**
 * 售后维修记录 excel 模板
 * @author hgb
 * @date 2017-10-27
 */

@SuppressWarnings("serial")
public class AfterSaleSummaryExcel implements Serializable{
	
	 private String rep_work_order;
	 private String model;
     private String segment1;
     private String product_barcode;
     private String custom_name;
     private String re_exp_no;
     private String re_logi_com;
     private String return_qty;
     private String return_operation_time; 
     private String contact_name;
     private String phone;
     private String address;
     private String customer_feedback;
     private String issue_description;
     private String rep_reason;
     private String rep_people;
     private String rep_type;
     private String so_no;
     private String delivery_operation_time;
     private String printer_status;
     private String description;
     
	public String getRep_work_order() {
		return rep_work_order;
	}
	public void setRep_work_order(String rep_work_order) {
		this.rep_work_order = rep_work_order;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getProduct_barcode() {
		return product_barcode;
	}
	public void setProduct_barcode(String product_barcode) {
		this.product_barcode = product_barcode;
	}
	public String getCustom_name() {
		return custom_name;
	}
	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}
	public String getRe_exp_no() {
		return re_exp_no;
	}
	public void setRe_exp_no(String re_exp_no) {
		this.re_exp_no = re_exp_no;
	}
	public String getRe_logi_com() {
		return re_logi_com;
	}
	public void setRe_logi_com(String re_logi_com) {
		this.re_logi_com = re_logi_com;
	}
	public String getReturn_qty() {
		return return_qty;
	}
	public void setReturn_qty(String return_qty) {
		this.return_qty = return_qty;
	}
	public String getReturn_operation_time() {
		return return_operation_time;
	}
	public void setReturn_operation_time(String return_operation_time) {
		this.return_operation_time = return_operation_time;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomer_feedback() {
		return customer_feedback;
	}
	public void setCustomer_feedback(String customer_feedback) {
		this.customer_feedback = customer_feedback;
	}
	public String getIssue_description() {
		return issue_description;
	}
	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}
	public String getRep_reason() {
		return rep_reason;
	}
	public void setRep_reason(String rep_reason) {
		this.rep_reason = rep_reason;
	}
	public String getRep_people() {
		return rep_people;
	}
	public void setRep_people(String rep_people) {
		this.rep_people = rep_people;
	}
	public String getRep_type() {
		return rep_type;
	}
	public void setRep_type(String rep_type) {
		this.rep_type = rep_type;
	}
	public String getSo_no() {
		return so_no;
	}
	public void setSo_no(String so_no) {
		this.so_no = so_no;
	}
	public String getDelivery_operation_time() {
		return delivery_operation_time;
	}
	public void setDelivery_operation_time(String delivery_operation_time) {
		this.delivery_operation_time = delivery_operation_time;
	}
	public String getPrinter_status() {
		return printer_status;
	}
	public void setPrinter_status(String printer_status) {
		this.printer_status = printer_status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
     

}
